package com.bmw.psmg.sbb.utilities;

import com.ptc.core.meta.common.TypeIdentifier;
import com.ptc.windchill.baseserver.logger.BaseLogger;
import com.ptc.windchill.baseserver.util.VersionedComparator;
import wt.fc.ObjectIdentifier;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.inf.container.WTContainer;
import wt.org.WTOrganization;
import wt.part.WTPart;
import wt.pds.StatementSpec;
import wt.query.QueryException;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.type.TypedUtility;
import wt.type.TypedUtilityServiceHelper;
import wt.util.WTAttributeNameIfc;
import wt.util.WTException;
import wt.vc.views.View;
import wt.vc.views.ViewHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;

/**
 * A class provides creation of commons QuerySpec to find WTPart objects in the Windchill database.
 * Between every statement it needs to append operator 'AND'/'OR'
 *
 * @author Karol Poliszkiewicz
 * @author Piotr Otapowicz
 */
public final class WTPartQueryBuilder {

    private static final BaseLogger LOGGER = BaseLogger.getLogger(WTPartQueryBuilder.class.getName());
    private static final String CONTAINER_REF_CLASS = "containerReference.key.classname";
    private static final String CONTAINER_REF_KEY = "containerReference.key.id";
    private static final String CONDITION_ERROR = "Error with target search condition.";
    private static final String EXECUTION_ERROR = "Exception thrown during query execution.";
    private static final String ORGANIZATION_OID = WTPart.ORGANIZATION_REFERENCE + '.' + WTAttributeNameIfc.REF_OBJECT_ID;

    private final QuerySpec findCriteria;
    private final int partIndex;

    private boolean requiredOperator;

    /**
     *  Initializes query builder's object.
     */
    public WTPartQueryBuilder() {
        this.partIndex = 0;
        try {
            this.findCriteria = new QuerySpec(WTPart.class);
        } catch (QueryException e) {
            LOGGER.error(() -> "Could not initialize QuerySpec for " + WTPart.class.getName(), e);
            throw new IllegalStateException("Could not initialize WTPartQueryBuilder's object.");
        }
        this.requiredOperator = false;
    }

    /**
     * Return current query spec object.
     *
     * @return QuerySpec's object with the criteria
     */
    public QuerySpec getFindCriteria() {
        return this.findCriteria;
    }

    /**
     * Append 'AND' operator to search criteria
     *
     * @return search criteria object
     */
    public WTPartQueryBuilder and() {
        this.findCriteria.appendAnd();
        this.requiredOperator = false;
        return this;
    }

    /**
     * Append 'OR' operator to search criteria.
     *
     * @return search criteria object.
     */
    public WTPartQueryBuilder or() {
        this.findCriteria.appendOr();
        this.requiredOperator = false;
        return this;
    }

    private void requireOperator() {
        if (this.requiredOperator) {
            throw new IllegalArgumentException("'AND' or 'OR' operator is required.");
        }
    }

    /**
     * Search WTPart by WTContainer.
     *
     * @param container requested WTContainer
     * @return search criteria object.
     */
    public WTPartQueryBuilder withContainer(WTContainer container) {
        if (container == null || container.getPersistInfo() == null) {
            LOGGER.warn(() -> "Cannot find reference because WTContainer is null");
        } else {
            requireOperator();
            ObjectIdentifier identifier = container.getPersistInfo().getObjectIdentifier();
            try {
                this.findCriteria.appendWhere(new SearchCondition(WTPart.class, CONTAINER_REF_CLASS, SearchCondition.EQUAL, identifier.getClassname()), new int[]{partIndex});
                this.findCriteria.appendAnd();
                this.findCriteria.appendWhere(new SearchCondition(WTPart.class, CONTAINER_REF_KEY, SearchCondition.EQUAL, identifier.getId()), new int[]{partIndex});
                this.requiredOperator = true;
            } catch (QueryException e) {
                LOGGER.error(() -> CONDITION_ERROR, e);
            }
        }
        return this;
    }

    /**
     * Search WTPart by WTPart number.
     *
     * @param number requested identity number of WTPart
     * @return this class instance.
     */
    public WTPartQueryBuilder withNumber(String number) {
        requireOperator();
        try {
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.NUMBER, SearchCondition.EQUAL, number), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (QueryException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by WTPart Object Identifier ID.
     *
     * @param id requested Object Identifier id of WTPart
     * @return search criteria object.
     * @deprecated unused method will be removed
     */
    @Deprecated
    public WTPartQueryBuilder withObjectIdentifier(Long id) {
        requireOperator();
        try {
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.PERSIST_INFO + ".theObjectIdentifier.id", SearchCondition.EQUAL, id), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (QueryException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by WTPart type internal name
     *
     * @param internalName string representation of internal name
     * @return this class instance.
     */
    public WTPartQueryBuilder withType(String internalName) {
        requireOperator();
        try {
            TypeIdentifier typeIdentifier = TypedUtility.getTypeIdentifier(internalName);
            SearchCondition searchCondition = TypedUtilityServiceHelper.service.getSearchCondition(typeIdentifier, true);
            this.findCriteria.appendWhere(searchCondition, new int[]{partIndex});
            this.requiredOperator = true;
        } catch (WTException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by WTPart name
     *
     * @param name the requested name of WTPart
     * @return search criteria object.
     */
    public WTPartQueryBuilder withName(String name) {
        requireOperator();
        try {
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.NAME, SearchCondition.EQUAL, name), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (QueryException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by View
     *
     * @param view the requested view of WTPart
     * @return search criteria object.
     */
    public WTPartQueryBuilder withView(View view) {
        requireOperator();
        try {
            ObjectIdentifier oid = view.getPersistInfo().getObjectIdentifier();
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.VIEW + ".key.id", SearchCondition.EQUAL, oid.getId()), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (WTException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by View name.
     *
     * @param stringView requested view of WTPart as String value
     * @return search criteria object.
     */
    public WTPartQueryBuilder withView(String stringView) {
        try {
            View view = ViewHelper.service.getView(stringView);
            return withView(view);
        } catch (WTException e) {
            LOGGER.error(() -> String.format("Cannot find View with NAME = '%s'", stringView), e);
        }
        throw new IllegalArgumentException("Cannot create Query Condition because the View value is invalid.");
    }

    /**
     * Search WTPart by Version.
     *
     * @param version the requested identifier of version id of WTPart
     * @return search criteria object.
     */
    public WTPartQueryBuilder withVersion(String version) {
        requireOperator();
        try {
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.VERSION_IDENTIFIER + ".versionId", SearchCondition.EQUAL, version), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (WTException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by Iteration.
     *
     * @param iteration the requested identifier of iteration id of WTPart
     * @return search criteria object.
     */
    public WTPartQueryBuilder withIteration(String iteration) {
        requireOperator();
        try {
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.ITERATION_IDENTIFIER + ".iterationId", SearchCondition.EQUAL, iteration), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (WTException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Searched WTPart must be in the last Iteration.
     *
     * @return search criteria object.
     */
    public WTPartQueryBuilder isLatestIteration() {
        requireOperator();
        try {
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, WTPart.LATEST_ITERATION, SearchCondition.IS_TRUE), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (QueryException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Search WTPart by Organization.
     *
     * @param organization requested organization of WTPart
     * @return search criteria object.
     */
    public WTPartQueryBuilder withOrganization(WTOrganization organization) {
        requireOperator();
        try {
            ObjectIdentifier objectIdentifier = PersistenceHelper.getObjectIdentifier(organization);
            this.findCriteria.appendWhere(new SearchCondition(WTPart.class, ORGANIZATION_OID, SearchCondition.EQUAL, objectIdentifier.getId()), new int[]{partIndex});
            this.requiredOperator = true;
        } catch (WTException e) {
            LOGGER.error(() -> CONDITION_ERROR, e);
        }
        return this;
    }

    /**
     * Run the query to find all WTPart objects, which fulfilling search criteria.
     *
     * @return the unmodifiable collection of WTPart objects.
     */
    public Collection<WTPart> findAll() {
        LOGGER.debug(() -> "Executing query: " + this.findCriteria.toString());
        List<WTPart> result = new LinkedList<>();
        try {
            QueryResult findResult = PersistenceHelper.manager.find((StatementSpec) this.findCriteria);
            LOGGER.debug(() -> "Query results: " + findResult.size());
            while (findResult.hasMoreElements()) {
                result.add((WTPart) findResult.nextElement());
            }
        } catch (WTException e) {
            LOGGER.error(() -> EXECUTION_ERROR, e);
        }
        return Collections.unmodifiableCollection(result);
    }

    /**
     * Run the query to find the first WTPart object, which fulfilling search criteria.
     *
     * @return the first searched WTPart object or empty optional wrapper.
     */
    public Optional<WTPart> findFirst() {
        LOGGER.debug(() -> "Executing query: " + this.findCriteria.toString());
        try {
            QueryResult findResult = PersistenceHelper.manager.find((StatementSpec) this.findCriteria);
            LOGGER.debug(() -> "Query results: " + findResult.size());
            if (findResult.hasMoreElements()) {
                return Optional.ofNullable((WTPart) findResult.nextElement());
            }
        } catch (WTException e) {
            LOGGER.error(() -> EXECUTION_ERROR, e);
        }
        return Optional.empty();
    }

    /**
     * Run the query to try find the WTPart object, which fulfilling search criteria.
     *
     * @return the first searched WTPart object or null.
     */
    @Nullable
    public WTPart getFirst() {
        return findFirst().orElse(null);
    }

    /**
     * Run the query to find the latest revision of WTPart object, which fulfilling search criteria.
     *
     * @return the latest revision of WTPart or empty optional wrapper.
     */
    public Optional<WTPart> findLatestRevision() {
        return findAll().stream().max(new VersionedComparator());
    }
}
