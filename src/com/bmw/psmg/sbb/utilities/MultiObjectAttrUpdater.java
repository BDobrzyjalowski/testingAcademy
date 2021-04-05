package com.bmw.psmg.sbb.utilities;

import com.ptc.core.businessfield.common.BusinessField;
import com.ptc.core.businessfield.server.businessObject.BusinessObject;
import com.ptc.core.businessfield.server.businessObject.BusinessObjectHelper;
import com.ptc.core.businessfield.server.businessObject.BusinessObjectHelperFactory;
import com.ptc.core.lwc.common.view.AttributeDefinitionReadView;
import com.ptc.core.lwc.common.view.TypeDefinitionReadView;
import com.ptc.core.lwc.server.LWCHardAttDefinition;
import com.ptc.core.lwc.server.TypeDefinitionServiceHelper;
import com.ptc.core.meta.common.TypeIdentifier;
import com.ptc.core.meta.common.UpdateOperationIdentifier;
import org.apache.log4j.Logger;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.PersistenceServerHelper;
import wt.fc.collections.WTArrayList;
import wt.iba.value.service.MultiObjIBAValueService;
import wt.session.SessionHelper;
import wt.type.TypedUtility;
import wt.util.WTException;
import wt.util.WTRuntimeException;
import wt.vc.wip.WorkInProgressServerHelper;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Wraps new Windchills Multi-Object API to update persistables attributes in simplified manner.<br/>
 * Hard modeled MBAs are not supported.<br/><br/>
 * <p>
 * Example of usage:<br/>
 * MultiObjectAttrUpdater multiObjectAttrUpdater = new MultiObjectAttrUpdater();<br/>
 * List<Persistable> persistableList = Arrays.asList(persistable1, persistable2);<br/>
 * multiObjectAttrUpdater.addAttributeToUpdate(persistableList, "testGlobalString", " Global test");<br/>
 * multiObjectAttrUpdater.addAttributeToUpdate(persistable1, "testLocalInteger", 5);<br/>
 * multiObjectAttrUpdater.persist();<br/>
 * ....OR....<br/>
 * multiObjectAttrUpdater.persist(true); //updates modification date for the "Last Modified" attribute
 */
public class MultiObjectAttrUpdater {

    private static final Logger LOGGER = Logger.getLogger(MultiObjectAttrUpdater.class.getName());
    private static final BusinessObjectHelper BUSINESS_OBJECT_HELPER = BusinessObjectHelperFactory.getBusinessObjectHelper();
    private static final String LCW_HARD_ATT_DEFINITION = LWCHardAttDefinition.class.getName();

    private final Map<String, Map<Persistable, Map<BusinessField, Object>>> typesToPersistablesMapping;
    private WTArrayList updatedObjects = new WTArrayList();

    /**
     * MultiObjectWrapper constructor
     */
    public MultiObjectAttrUpdater() {
        this.typesToPersistablesMapping = new HashMap<>();
    }

    /**
     * Checks if attribute is hard modeled MBA or not.
     *
     * @param typeIdentifier type identifier of object
     * @param attributeName  internal name of attribute
     * @return if attribute is hard modeled true, otherwise false
     * @throws WTException throws an exception if TypeDefinitionReadView cannot be found for TypeIdentifier
     */
    public static boolean isHardModeledAttribute(TypeIdentifier typeIdentifier, String attributeName) throws WTException {
        TypeDefinitionReadView typeDefinitionReadView = TypeDefinitionServiceHelper.service.getTypeDefView(typeIdentifier);
        AttributeDefinitionReadView attributeDefinitionReadView = typeDefinitionReadView.getAttributeByName(attributeName);
        return LCW_HARD_ATT_DEFINITION.equals(attributeDefinitionReadView.getAttDefClass());
    }

    private Set<Persistable> getPersistables() {
        return this.typesToPersistablesMapping.values().stream()
                .flatMap(persistablesToBusinessFieldsMapping -> persistablesToBusinessFieldsMapping.keySet().stream())
                .collect(Collectors.toSet());
    }

    private List<BusinessObject> getBusinessObjectsForPersistables() throws WTException {
        Set<Persistable> persistablesSet = getPersistables();
        Persistable[] persistables = persistablesSet.toArray(new Persistable[0]);
        return BUSINESS_OBJECT_HELPER.newBusinessObjects(SessionHelper.getLocale(), new UpdateOperationIdentifier(), false, persistables);
    }

    private Map<Persistable, Map<BusinessField, Object>> getPersistablesToBusinessFieldsMapping(String typeName) {
        Map<Persistable, Map<BusinessField, Object>> persistablesToBusinessFieldsMapping;
        if (this.typesToPersistablesMapping.containsKey(typeName)) {
            persistablesToBusinessFieldsMapping = this.typesToPersistablesMapping.get(typeName);
        } else {
            persistablesToBusinessFieldsMapping = new HashMap<>();
            this.typesToPersistablesMapping.put(typeName, persistablesToBusinessFieldsMapping);
        }
        return persistablesToBusinessFieldsMapping;
    }

    private Map<BusinessField, Object> getBusinessFieldsToValuesMapping(Persistable persistable) {
        String typeName = TypedUtility.getTypeIdentifier(persistable).getTypename();
        Map<Persistable, Map<BusinessField, Object>> persistablesToBusinessFieldsMapping = getPersistablesToBusinessFieldsMapping(typeName);
        Map<BusinessField, Object> businessFieldsToValuesMapping;
        if (persistablesToBusinessFieldsMapping.containsKey(persistable)) {
            businessFieldsToValuesMapping = persistablesToBusinessFieldsMapping.get(persistable);
        } else {
            businessFieldsToValuesMapping = new HashMap<>();
            persistablesToBusinessFieldsMapping.put(persistable, businessFieldsToValuesMapping);
        }
        return businessFieldsToValuesMapping;
    }

    private void setBusinessFieldsAndValuesForBusinessObject(BusinessObject businessObject, Map<BusinessField, Object> businessFieldObjectMapping) throws WTException {
        LOGGER.debug("Updating attributes/values for " + businessObject.getWTReference().getObject());
        for (Map.Entry<BusinessField, Object> entry : businessFieldObjectMapping.entrySet()) {
            BusinessField currentBusinessField = entry.getKey();
            if (!isHardModeledAttribute(businessObject.getTypeIdentifier(), currentBusinessField.getName())) {
                LOGGER.debug("Sets " + entry.getValue() + " for " + currentBusinessField.getName());
                businessObject.set(currentBusinessField, entry.getValue());
            } else {
                throw new WTException(currentBusinessField.getName() + " is hard modeled MBA attribute. Hard Modeled attributes are not supported. Please use setX method to set data or Identity API.");
            }
        }
        LOGGER.debug("Updating ends");
    }

    private void clear() {
        LOGGER.debug("Persistable map size before clear execution: " + this.typesToPersistablesMapping.size());
        this.typesToPersistablesMapping.clear();
        LOGGER.debug("Persistable map size after clear execution: " + this.typesToPersistablesMapping.size());
    }

    /**
     * Adds an persistable attribute to update.
     *
     * @param persistable   persistable
     * @param attributeName internal attribute name
     * @param value         new value of attribute
     * @throws WTException if cannot get type identifier or business field
     */
    public void addAttributeToUpdate(Persistable persistable, String attributeName, Object value) throws WTException {
        addAttributeToUpdate(Collections.singletonList(persistable), attributeName, value);
    }

    /**
     * Adds an persistables attribute to update.
     *
     * @param persistables  list of persistables
     * @param attributeName internal attribute name
     * @param value         new value of attribute
     * @throws WTException if cannot get business field
     */
    public void addAttributeToUpdate(List<Persistable> persistables, String attributeName, Object value) throws WTException {
        for (Persistable persistable : persistables) {
            Map<BusinessField, Object> businessFieldsToValuesMapping = getBusinessFieldsToValuesMapping(persistable);
            TypeIdentifier typeIdentifier = TypedUtility.getTypeIdentifier(persistable);
            BusinessField businessField = IBAUtils.getTypeBusinessField(typeIdentifier.getTypeInternalName(), attributeName);
            businessFieldsToValuesMapping.put(businessField, value);
        }
    }

    /**
     * Updates attributes of added persistables.
     * MultiObjectAPIWrapper object is ready to reuse after execution of this method.
     *
     * @return list of updated persistables
     * @throws WTException        when cannot get business objects, load business fields or set/apply values
     * @throws WTRuntimeException when cannot get type identifier for persistable/business object
     */
    public WTArrayList updateAttributes() throws WTException {
        List<BusinessObject> businessObjects = getBusinessObjectsForPersistables();
        for (Map.Entry<String, Map<Persistable, Map<BusinessField, Object>>> entry : this.typesToPersistablesMapping.entrySet()) {
            String currentTypeName = entry.getKey();
            List<BusinessObject> businessObjectsPerType = businessObjects.stream().filter(currentBusinessObject -> {
                try {
                    return currentBusinessObject.getTypeIdentifier().getTypename().equals(currentTypeName);
                } catch (WTException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                    throw new WTRuntimeException(e);
                }
            }).collect(Collectors.toList());
            Set<BusinessField> businessFieldsRelatedToType = entry.getValue().values().stream()
                    .flatMap(currentBusinessFieldToValueMapping -> currentBusinessFieldToValueMapping.keySet().stream())
                    .collect(Collectors.toSet());
            BUSINESS_OBJECT_HELPER.load(businessObjectsPerType, businessFieldsRelatedToType);
        }
        for (BusinessObject businessObject : businessObjects) {
            Map<BusinessField, Object> businessFieldsToValuesMapping = getBusinessFieldsToValuesMapping(businessObject.getWTReference().getObject());
            setBusinessFieldsAndValuesForBusinessObject(businessObject, businessFieldsToValuesMapping);
        }
        updatedObjects = new WTArrayList(BUSINESS_OBJECT_HELPER.apply(businessObjects));
        clear();
        return new WTArrayList(updatedObjects);
    }

    /**
     * Get list of updated objects. Not persisted yet.
     *
     * @return list of updated objects
     */
    public WTArrayList getUpdatedObjects() {
        return new WTArrayList(updatedObjects);
    }

    /**
     * Persist the modified objects in the datastore.
     *
     * @throws WTException exception
     */
    public void persist() throws WTException {
        persist(false);
    }

    /**
     * Persist the modified objects in the datastore.
     *
     * @param updateModifiedDate update "Last Modified" attribute or not
     * @throws WTException exception
     */
    public void persist(boolean updateModifiedDate) throws WTException {
        if (!updatedObjects.isEmpty()) {
            if (updateModifiedDate) {
                WorkInProgressServerHelper.putInTxMapForValidateModifiable(updatedObjects);
                PersistenceHelper.manager.modify(updatedObjects);
            } else {
                PersistenceServerHelper.manager.update(updatedObjects, false);
                MultiObjIBAValueService.theIBAValueDBService.updateAttributeContainer(updatedObjects);
            }
            updatedObjects.clear();
            LOGGER.debug("Modified objects have been persisted!");
        } else {
            LOGGER.debug("No modified objects. Nothing to persist!");
        }
    }

}
