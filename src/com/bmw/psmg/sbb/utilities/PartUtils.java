package com.bmw.psmg.sbb.utilities;

import static java.util.stream.Collectors.toSet;
import static wt.part._WTPartMaster.NAME;
import static wt.part._WTPartMaster.NUMBER;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;
import com.ptc.core.lwc.common.view.AttributeDefinitionReadView;
import com.ptc.core.lwc.common.view.ConstraintDefinitionReadView;
import com.ptc.core.lwc.server.PersistableAdapter;
import com.ptc.core.lwc.server.TypeDefinitionServiceHelper;
import com.ptc.core.meta.common.AnalogSet;
import com.ptc.core.meta.common.AttributeTypeIdentifier;
import com.ptc.core.meta.common.TypeIdentifier;
import com.ptc.core.meta.common.UpdateOperationIdentifier;
import com.ptc.core.meta.container.common.impl.StringLengthConstraint;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import wt.fc.ObjectIdentifier;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.fc.WTObject;
import wt.fc.collections.WTArrayList;
import wt.fc.collections.WTList;
import wt.part.WTPart;
import wt.part.WTPartHelper;
import wt.part.WTPartMaster;
import wt.part.WTPartUsageLink;
import wt.pds.StatementSpec;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.session.SessionHelper;
import wt.type.Typed;
import wt.type.TypedUtility;
import wt.type.TypedUtilityServiceHelper;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;
import wt.vc.VersionControlHelper;
import wt.vc.config.ConfigSpec;
import wt.vc.config.LatestConfigSpec;
import wt.vc.wip.CheckoutLink;
import wt.vc.wip.WorkInProgressHelper;
import wt.vc.wip.Workable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The class simplify operations between parent part and his children(and only on parent object).
 *
 * @author Bartlomiej Dobrzyjalowski
 * @author Karol Poliszkiewicz
 */
public class PartUtils {

    private static final Logger LOGGER = Logger.getLogger(PartUtils.class.getName());
    private static final List<ConfigSpec> LATEST_CONFIG_SPECS = Collections.singletonList(new LatestConfigSpec());

    private PartUtils() {
    }

    /**
     * Get Persistable Array with Uses WTParts using Navigation Criteria and parent Part Object
     *
     * @param parts List of parents parts
     * @return array of persistable with usage links
     * @throws WTException exception in case of problem with getUsesWTParts method
     */
    private static Persistable[][][] getPersistable(WTList parts) throws WTException {
        return WTPartHelper.service.getUsesWTParts(parts, LATEST_CONFIG_SPECS.get(0));
    }

    /**
     * Get List of parts from 3d array of Persistable Objects
     *
     * @param usages array with Persistable Objects
     * @return List of WTParts
     */
    public static List<WTPart> getPartsFromUsages(Persistable[][][] usages) {
        List<WTPart> result = new LinkedList<>();
        if (usages != null && usages.length > 0 && usages[0] != null) {
            for (Persistable[] partLink : usages[0]) {
                if (partLink.length == 2 && partLink[1] instanceof WTPart) {
                    WTPart part = (WTPart) partLink[1];
                    result.add(part);
                }
            }
        }
        return result;
    }

    /**
     * Get usages part by parent persistable.
     *
     * @param persistable parent Persistable Object
     * @return List of WTParts
     * @throws WTException when cannot get usages persistables from persistable.
     */
    public static List<WTPart> getUsagesOf(Persistable persistable) throws WTException {
        Persistable[][][] usages = getPersistable(new WTArrayList(Collections.singletonList(persistable)));
        return getPartsFromUsages(usages);
    }


    /**
     * Gets the latest iteration of the latest version of a WTPart by name and/or number
     *
     * @param name   name of the part
     * @param number number of the part
     * @return the latest iteration of the latest revision of the specified part
     * @throws WTException Thrown when there is an issue with running a query
     */
    public static WTPart getPartLatestVersionAndIteration(String name, String number) throws WTException {
        WTPartMaster partMaster = getWTPartMaster(name, number);
        return getPartLatestVersionAndIteration(partMaster);
    }

    /**
     * Gets the latest iteration of the latest version of a WTPart by it's WTPartMaster
     *
     * @param partMaster WTPartMaster object of a specific part
     * @return the latest iteration of the latest revision of the specified part
     * @throws WTException Thrown when there is an error with running a query
     */
    public static WTPart getPartLatestVersionAndIteration(WTPartMaster partMaster) throws WTException {
        if (partMaster == null) {
            return null;
        }
        WTPart part = null;
        QueryResult result = VersionControlHelper.service.allIterationsOf(partMaster);
        if (result.hasMoreElements()) {
            part = (WTPart) result.nextElement();
        }
        return part;
    }

    /**
     * Find the latest iteration of the latest version of a WTPart by it's WTPartMaster
     *
     * @param partMaster WTPartMaster object of a specific part
     * @return the latest iteration of the latest revision of the specified part
     */
    public static Optional<WTPart> findPartLatestVersionAndIteration(WTPartMaster partMaster) {
        WTPart part = null;
        try {
            part = getPartLatestVersionAndIteration(partMaster);
        } catch (WTException e) {
            LOGGER.error("Cannot find WTPart by WTPartMaster", e);
        }
        return Optional.ofNullable(part);
    }

    /**
     * Gets WTPartMaster  by name and/or number
     *
     * @param name   name of the part
     * @param number number of the part
     * @return part master of the given part
     * @throws WTException Thrown when there is an issue with running a query
     */
    public static WTPartMaster getWTPartMaster(String name, String number) throws WTException {
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty((number))) {
            throw new IllegalArgumentException("Both Part Name and Part Number are null/empty. At least one of them needs a value!");
        }

        WTPartMaster partMaster = null;
        QuerySpec query = new QuerySpec(WTPartMaster.class);
        if (!StringUtils.isEmpty(name)) {
            query.appendWhere(new SearchCondition(WTPartMaster.class, NAME, SearchCondition.EQUAL, name), new int[]{0});
        }
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(number)) {
            query.appendAnd();
        }
        if (!StringUtils.isEmpty(number)) {
            query.appendWhere(new SearchCondition(WTPartMaster.class, NUMBER, SearchCondition.EQUAL, number), new int[]{0});
        }
        QueryResult result = PersistenceHelper.manager.find((StatementSpec) query);
        if (result.hasMoreElements()) {
            partMaster = (WTPartMaster) result.nextElement();
        }
        return partMaster;
    }

    /**
     * Search and return parent of WTPart.
     *
     * @param part - child of expected parent
     * @return existing part's parent or empty optional
     */
    public static Optional<WTPart> findParent(WTPart part) {
        WTPart parent = null;
        try {
            QueryResult usedBy = PersistenceHelper.manager.navigate(part.getMaster(), PSMGSBBConstants.WTLinkAttributes.USED_BY_ROLE, WTPartUsageLink.class, true);
            LatestConfigSpec configSpec = new LatestConfigSpec();
            usedBy = configSpec.process(usedBy);
            while (usedBy.hasMoreElements()) {
                WTPart p = (WTPart) usedBy.nextElement();
                if (p.isLatestIteration()) {
                    parent = p;
                    break;
                }
            }
        } catch (WTException e) {
            LOGGER.error("Cannot find parent for part: " + part.getNumber() + ", " + part.getName(), e);
            return Optional.empty();
        }
        return Optional.ofNullable(parent);
    }

    /**
     * Search and return parents of WTPart.
     *
     * @param part - child of expected parents
     * @return list of existing part parents
     */
    public static List<WTPart> findParents(WTPart part) {
        List<WTPart> parents = new ArrayList<>();
        try {
            QueryResult usedBy = PersistenceHelper.manager.navigate(part.getMaster(), PSMGSBBConstants.WTLinkAttributes.USED_BY_ROLE, WTPartUsageLink.class, true);
            LatestConfigSpec configSpec = new LatestConfigSpec();
            usedBy = configSpec.process(usedBy);
            while (usedBy.hasMoreElements()) {
                WTPart parent = (WTPart) usedBy.nextElement();
                if (parent.isLatestIteration()) {
                    parents.add(parent);
                }
            }
        } catch (WTException exception) {
            LOGGER.error("Cannot find parents for part: " + part.getNumber() + ", " + part.getName(), exception);
            return parents;
        }
        return parents;
    }

    /**
     * Method attach child object to new created position variant.
     *
     * @param part          part object
     * @param attributesMap part attributes map
     * @return part object with updated attributes
     * @throws WTException             when cannot create part or attach child
     * @throws WTPropertyVetoException when cannot set attributes map
     * @deprecated use {@link com.bmw.psmg.sbb.generic.persistable.AttributeService#setValues(Persistable, Map)}.
     */
    @Deprecated
    public static WTPart setPartAttributesValues(WTPart part, Map<String, String> attributesMap) throws WTException, WTPropertyVetoException {
        for (Map.Entry<String, String> entry : attributesMap.entrySet()) {
            CheckoutLink checkOutLink = WorkInProgressHelper.service.checkout(part, WorkInProgressHelper.service.getCheckoutFolder(), "Object check out");
            Workable editObject = checkOutLink.getWorkingCopy();
            PersistableAdapter persistableAdapter = new PersistableAdapter(editObject, null, SessionHelper.getLocale(), new UpdateOperationIdentifier());
            persistableAdapter.load(entry.getKey());
            persistableAdapter.set(entry.getKey(), entry.getValue());
            editObject = (Workable) persistableAdapter.apply();
            PersistenceHelper.manager.modify(editObject);
            part = (WTPart) WorkInProgressHelper.service.checkin(editObject, "Object is checked in");
        }
        return part;
    }

    /**
     * Checkout and get WorkingCopy of WTPart object.
     *
     * @param part WTPart object
     * @return The workable object of WTPart
     * @throws WTException             when cannot get checkout folder
     * @throws WTPropertyVetoException when can not checkout object
     * @see Workable
     */
    public static WTPart getWorkingCopy(WTPart part) throws WTException, WTPropertyVetoException {
        if (WorkInProgressHelper.isWorkingCopy(part)) {
            LOGGER.debug("Incoming WTPart is already Working copy");
            return part;
        }
        return (WTPart) WorkInProgressHelper.service.checkout(part, WorkInProgressHelper.service.getCheckoutFolder(), "").getWorkingCopy();
    }

    /**
     * Create WTPartUsageLink between parent and child.
     *
     * @param parent WorkingCopy of WTPart parent object
     * @param child  WTPart child object
     * @return WTPartUsageLink object between Parent and Child
     * @throws WTException when cannot create usage link between parent and child
     * @see WTPartUsageLink
     */
    public static WTPartUsageLink createUsageLink(WTPart parent, WTPart child) throws WTException {
        return (WTPartUsageLink) PersistenceHelper.manager.save(WTPartUsageLink.newWTPartUsageLink(parent, child.getMaster()));
    }

    /**
     * Find usage link by parent and child.
     *
     * @param parent roleA of usage link
     * @param child  roleB of usage link
     * @return optional of usage link
     */
    public static Optional<WTPartUsageLink> findUsageLink(WTPart parent, WTPart child) {
        Optional<WTPartUsageLink> link = Optional.empty();
        try {
            QuerySpec querySpec = new QuerySpec(WTPartUsageLink.class);
            querySpec.appendWhere(new SearchCondition(WTPartUsageLink.class, "roleAObjectRef.key.id", "=", parent.getPersistInfo().getObjectIdentifier().getId()), new int[]{0});
            querySpec.appendAnd();
            querySpec.appendWhere(new SearchCondition(WTPartUsageLink.class, "roleBObjectRef.key.id", "=", child.getMaster().getPersistInfo().getObjectIdentifier().getId()), new int[]{0});
            QueryResult queryResult = PersistenceHelper.manager.find(querySpec);
            if (queryResult.hasMoreElements()) {
                link = Optional.ofNullable((WTPartUsageLink) queryResult.nextElement());
            }
        } catch (WTException e) {
            LOGGER.error("Cannot find WTPartUsageLink by RoleA and RoleB", e);
        }
        return link;
    }

    /**
     * Get all Usage Links where part is roleA
     *
     * @param part as roleA
     * @return set of usage links
     */
    public static Set<WTPartUsageLink> findAllUsageLinks(WTPart part) {
        Set<WTPartUsageLink> links = new HashSet<>();
        try {
            QuerySpec querySpec = new QuerySpec(WTPartUsageLink.class);
            querySpec.appendWhere(new SearchCondition(WTPartUsageLink.class, "roleAObjectRef.key.id", "=", part.getPersistInfo().getObjectIdentifier().getId()), new int[]{0});
            QueryResult queryResult = PersistenceHelper.manager.find(querySpec);
            Stream<WTPartUsageLink> stream = queryResult.getObjectVectorIfc().getVector().stream().filter(WTPartUsageLink.class::isInstance).map(WTPartUsageLink.class::cast);
            links = stream.collect(toSet());
        } catch (WTException e) {
            LOGGER.error("Cannot find WTPartUsageLinks by RoleA", e);
        }
        return links;
    }

    /**
     * Method set custom attribute value.
     *
     * @param part  WTPart object - holder of attribute
     * @param key   attribute internal name
     * @param value value to be set
     * @return WTPart object instance after store operation
     * @throws WTException when cannot set the attribute value
     * @deprecated use {@link com.bmw.psmg.sbb.generic.persistable.AttributeService#setValue(Persistable, String, Object)}.
     */
    @Deprecated
    public static WTPart setCustomAttribute(WTPart part, String key, Object value) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(part, null, Locale.getDefault(), null);
        persistableAdapter.load(key);
        persistableAdapter.set(key, value);
        persistableAdapter.apply();
        return (WTPart) PersistenceHelper.manager.store(part);
    }

    /**
     * Method get custom attribute value by key from WTPart.
     *
     * @param part WTPart object - holder of attribute
     * @param key  attribute internal name
     * @return WTPart object instance after store operation
     * @deprecated use {@link com.bmw.psmg.sbb.generic.persistable.AttributeService#getValue(WTObject, String)}.
     */
    @Deprecated
    public static Optional<Object> getCustomAttribute(WTPart part, String key) {
        Object value = null;
        try {
            PersistableAdapter persistableAdapter = new PersistableAdapter(part, null, Locale.getDefault(), null);
            persistableAdapter.load(key);
            value = persistableAdapter.get(key);
        } catch (WTException e) {
            LOGGER.error(String.format("Cannot get custom standard attribute with KEY = '%s'", key), e);
        }
        return Optional.ofNullable(value);
    }

    /**
     * Gets the part from obid
     *
     * @param obid long obid value (sometimes named ufid)
     * @return optional of WTPart
     */
    public static Optional<WTPart> getWTPartFromObid(String obid) {
        try {
            ObjectIdentifier objectIdentifier = ObjectIdentifier.newObjectIdentifier(parseObidToShorterVersion(obid));
            Persistable persistable = PersistenceHelper.manager.refresh(objectIdentifier);
            WTPart part = null;
            if (persistable instanceof WTPartMaster) {
                part = getPartLatestVersionAndIteration((WTPartMaster) persistable);
            } else if (persistable instanceof WTPart) {
                part = (WTPart) persistable;
            }
            return Optional.ofNullable(part);
        } catch (WTException e) {
            LOGGER.error("Can not find WTPart", e);
            return Optional.empty();
        }
    }

    private static String parseObidToShorterVersion(String obid) {
        String[] obidTab = obid.split(":");
        return (obidTab[1] + ":" + obidTab[2]);
    }


    /**
     * Returns max length of attribute specified in string length constraint for WTPart
     *
     * @return Max String length value constraint for WTPart name
     * @throws WTException if fails to get TypeIdentifier for WTPart or AttributeDefinitionReadView for name
     */
    public static long getMaximumLengthForPartName() throws WTException {
        TypeIdentifier typeIdentifier = TypedUtilityServiceHelper.service.getTypeIdentifier(PSMGSBBConstants.Types.WT_PART_INTERNAL_NAME);
        AttributeTypeIdentifier attributeTypeIdentifier = TypedUtility.getAttributeTypeIdentifier(PSMGSBBConstants.Attributes.NAME, typeIdentifier);
        AttributeDefinitionReadView attributeDefReadView = TypeDefinitionServiceHelper.service.getAttributeDefView(attributeTypeIdentifier);
        Collection<ConstraintDefinitionReadView> constraintDefReadViews = attributeDefReadView.getAllConstraints();
        Iterator<ConstraintDefinitionReadView> iterator = constraintDefReadViews.iterator();
        long maxNameLength = Long.MAX_VALUE;
        while (iterator.hasNext()) {
            ConstraintDefinitionReadView constraintDefReadView = iterator.next();
            if (StringLengthConstraint.class.getName().equals(constraintDefReadView.getRule().getRuleClassname())) {
                AnalogSet ruleData = (AnalogSet) constraintDefReadView.getRuleData();
                long upperBound = Long.parseLong(ruleData.getBoundingRange().getUpperBoundValue().toString());
                if (upperBound < maxNameLength) {
                    maxNameLength = upperBound;
                }
            }
        }
        return maxNameLength;
    }


    /**
     * Get all children from set of WTPartUsageLinks
     *
     * @param allUsageLinks Collection of WTPartUsageLinks
     * @return Collection of child objects(latest version and iteration)
     */
    public static Set<WTPart> getChildrenFromUsageLinks(Set<WTPartUsageLink> allUsageLinks) {
        return allUsageLinks.stream().map(WTPartUsageLink::getRoleBObject).map(WTPartMaster.class::cast).map(PartUtils::findLatestVersionAndIteration).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static WTPart findLatestVersionAndIteration(WTPartMaster master) {
        try {
            return getPartLatestVersionAndIteration(master);
        } catch (WTException e) {
            LOGGER.error(String.format("Failed to get latest version and iteration for master %s", master.getDisplayIdentity()), e);
            return null;
        }
    }
}
