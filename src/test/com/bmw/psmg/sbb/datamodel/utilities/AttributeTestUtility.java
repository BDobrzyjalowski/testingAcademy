package test.com.bmw.psmg.sbb.datamodel.utilities;

import static com.ptc.core.lwc.common.PropertyDefinitionConstants.DISPLAY_NAME_PROPERTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;
import com.bmw.psmg.sbb.generic.PSMGSBBSoftTypeUtility;
import com.bmw.psmg.sbb.generic.container.WTContainersUtility;

import com.ptc.core.lwc.common.view.AttributeDefinitionReadView;
import com.ptc.core.lwc.common.view.ConstraintDefinitionReadView;
import com.ptc.core.lwc.server.PersistableAdapter;
import com.ptc.core.lwc.server.TypeDefinitionServiceHelper;
import com.ptc.core.meta.common.AnalogSet;
import com.ptc.core.meta.common.AttributeTypeIdentifier;
import com.ptc.core.meta.common.TypeIdentifier;
import com.ptc.core.meta.common.UpdateOperationIdentifier;
import com.ptc.core.meta.container.common.impl.StringLengthConstraint;
import com.ptc.windchill.enterprise.data.EnterpriseData;
import com.ptc.windchill.mpml.pmi.MPMStandardCC;
import com.ptc.wpcfg.doc.VariantSpec;
import org.apache.log4j.Logger;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.inf.container.WTContainer;
import wt.part.WTPart;
import wt.session.SessionHelper;
import wt.type.Typed;
import wt.type.TypedUtility;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;
import wt.vc.wip.CheckoutLink;
import wt.vc.wip.WorkInProgressHelper;
import wt.vc.wip.Workable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The class simplify testing legal values from specific attribute.
 *
 * @author Bartlomiej Dobrzyjalowski Pawel Miron Patryk Zajko
 */
public class AttributeTestUtility {

    private static final Logger LOGGER = Logger.getLogger(AttributeTestUtility.class.getName());
    private static final String TESTING_CUSTOM_ATTRIBUTES = "%s - Testing %s custom attributes";

    private AttributeTestUtility() {
    }

    /**
     * The method which editing object attribute.
     *
     * @param workable       test object
     * @param attributeName  Internal Name of attribute
     * @param attributeValue Value of attribute
     * @return WTPart object with new attribute value
     * @throws WTException             when cannot find checkout folder
     * @throws WTPropertyVetoException when cannot checkin/checkout object
     */
    public static Workable setAttributeValueOnObject(Workable workable, String attributeName, Object attributeValue) throws WTException, WTPropertyVetoException {
        CheckoutLink checkOutLink = WorkInProgressHelper.service.checkout(workable, WorkInProgressHelper.service.getCheckoutFolder(), "Object check out");
        Workable editObject = checkOutLink.getWorkingCopy();
        PersistableAdapter persistableAdapter = new PersistableAdapter(editObject, null, SessionHelper.getLocale(), new UpdateOperationIdentifier());
        persistableAdapter.load(attributeName);
        persistableAdapter.set(attributeName, attributeValue);
        editObject = (Workable) persistableAdapter.apply();
        PersistenceHelper.manager.modify(editObject);
        editObject = WorkInProgressHelper.service.checkin(editObject, "Object is checked in");
        return editObject;
    }

    /**
     * The method returns message containing all attributes that doesn't exists
     *
     * @param persistableAdapter Persistable adapter
     * @param attributes         List of attributes
     * @return String object message with list of not existing attributes seperated with space
     * @throws WTException Exception during loading attributes
     */
    public static String getAllAttributesWhichNotExistsMessage(PersistableAdapter persistableAdapter, String[] attributes) throws WTException {
        persistableAdapter.load(attributes);
        StringBuilder messageOutputBuilder = new StringBuilder();
        for (String attribute : attributes) {
            try {
                persistableAdapter.get(attribute);
            } catch (WTException e) {
                LOGGER.error(e);
                messageOutputBuilder.append(attribute).append(" ");
            }
        }
        return messageOutputBuilder.toString();
    }

    /**
     * The method returns message containing all display values with wrong value.
     *
     * @param typeInternalName   Internal name of type
     * @param attributes         Map of attributes with theirs display values
     * @param persistableAdapter Persistable adapter with access to attributes
     * @return String object message with list of not existing attributes seperated with space
     * @throws WTException Exception during loading attributes
     */
    public static String getAllAttributesWhichIncorrectDisplayValue(Map<String, String[]> attributes, String typeInternalName, PersistableAdapter persistableAdapter) throws WTException {
        StringBuilder messageOutputBuilder = new StringBuilder();
        persistableAdapter.load(attributes.keySet());
        for (Map.Entry<String, String[]> entry : attributes.entrySet()) {
            AttributeTypeIdentifier attributetypeidentifier = TypedUtility.getAttributeTypeIdentifier(entry.getKey(), TypedUtility.getTypeIdentifier(typeInternalName));
            AttributeDefinitionReadView attributeDefinitionReadView = TypeDefinitionServiceHelper.service.getAttributeDefView(attributetypeidentifier);
            String[] displayValues = entry.getValue();
            if (!StringTestUtility.equalsWithUmlauts(displayValues[1], attributeDefinitionReadView.getPropertyValueByName(DISPLAY_NAME_PROPERTY).getValueAsString(Locale.GERMANY, true))) {
                String errorMessage = String.format("Wrong display DE value on attribute %s, Expected : %s, actual : %s", entry.getKey(), displayValues[1],
                        attributeDefinitionReadView.getPropertyValueByName(DISPLAY_NAME_PROPERTY)
                        .getValueAsString(Locale.GERMANY, true));
                LOGGER.error(errorMessage);
                messageOutputBuilder.append(entry.getKey()).append(" ");
            }
            if (!displayValues[0].equals(attributeDefinitionReadView.getPropertyValueByName(DISPLAY_NAME_PROPERTY).getValueAsString(Locale.ENGLISH, true))) {
                String errorMessage = String.format("Wrong display EN value on attribute %s, Expected : %s, actual : %s", entry.getKey(), displayValues[0],
                        attributeDefinitionReadView.getPropertyValueByName(DISPLAY_NAME_PROPERTY)
                        .getValueAsString(Locale.ENGLISH, true));
                LOGGER.error(errorMessage);
                messageOutputBuilder.append(entry.getKey()).append(" ");
            }
        }
        return messageOutputBuilder.toString();
    }

    /**
     * The method returns message containing all values with wrong value on calculated attributes.
     *
     * @param calculatedAttributes Map of attributes with theirs excepted values
     * @param persistableAdapter   Persistable adapter with access to attributes
     * @return String object message with list of not existing attributes seperated with space
     * @throws WTException Exception during loading attributes
     */
    public static String getAllAttributesWhichIncorrectValue(Map<String, String> calculatedAttributes, PersistableAdapter persistableAdapter) throws WTException {
        StringBuilder messageOutputBuilder = new StringBuilder();
        persistableAdapter.load(calculatedAttributes.keySet());
        for (Map.Entry<String, String> entry : calculatedAttributes.entrySet()) {
            String value = entry.getValue();
            String attributeFromInstance = persistableAdapter.get(entry.getKey()).toString();
            if (!value.equals(attributeFromInstance)) {
                String errorMessage = String.format("Wrong value on attribute %s, Expected : %s, actual : %s", entry.getKey(), value, attributeFromInstance);
                LOGGER.error(errorMessage);
                messageOutputBuilder.append(entry.getKey()).append(" ");
            }
        }
        return messageOutputBuilder.toString();
    }

    /**
     * The method return max length of attribute specified in string length constraint
     *
     * @param attributeDefinition AttributeDefinitionReadView
     * @return Max String length value constraint for given AttributeDefinitionView object
     */
    public static long getLengthConstraintForStringAttribute(AttributeDefinitionReadView attributeDefinition) {
        Collection<ConstraintDefinitionReadView> constraintDefinition = attributeDefinition.getAllConstraints();
        Iterator<ConstraintDefinitionReadView> iterator = constraintDefinition.iterator();
        long max = Long.MAX_VALUE;
        while (iterator.hasNext()) {
            ConstraintDefinitionReadView x = iterator.next();
            if (StringLengthConstraint.class.getName().equals(x.getRule().getRuleClassname())) {
                AnalogSet ruleData = (AnalogSet) x.getRuleData();
                long upperBound = (Long) ruleData.getBoundingRange().getUpperBoundValue();
                if (upperBound < max) {
                    max = upperBound;
                }
            }
        }
        return max;
    }

    /**
     * The method returns message containing all attributes names with wrongly set String length constraint
     *
     * @param typeIdentifier                          TypeIdentifier of attribute
     * @param mapOfStringLengthConstraintByAttributes Map of attribute names and their expected String constraints
     * @return String object message with list of attributes names with wrongly set String length constraint
     * @throws WTException Exception during getting AttributeDefinitionReadView object
     */
    public static String getAllAttributesWithWrongStringLengthConstraintMessage(TypeIdentifier typeIdentifier, Map<String, Integer> mapOfStringLengthConstraintByAttributes) throws WTException {
        StringBuilder messageOutputBuilder = new StringBuilder();
        Set<String> namesOfAttributes = mapOfStringLengthConstraintByAttributes.keySet();
        for (String attributeName : namesOfAttributes) {
            AttributeTypeIdentifier attributetypeidentifier = TypedUtility.getAttributeTypeIdentifier(attributeName, typeIdentifier);
            long maxStringLength = 0L;
            if (attributetypeidentifier != null) {
                AttributeDefinitionReadView attributeView = TypeDefinitionServiceHelper.service.getAttributeDefView(attributetypeidentifier);
                maxStringLength = AttributeTestUtility.getLengthConstraintForStringAttribute(attributeView);
            }
            if (!Long.valueOf(mapOfStringLengthConstraintByAttributes.get(attributeName)).equals(maxStringLength)) {
                messageOutputBuilder.append(attributeName).append(" ");
            }
        }
        return messageOutputBuilder.toString();
    }

    /**
     * Test case verifies if all of custom attributes has correct display values on Plant Functional Data instance
     *
     * @param persistable tested PlantFunctionalData object
     * @param attributes  map with internal names of all custom attributes and theirs display values
     * @param usNumber    number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    public static void checkAttributesDisplayNamesForObjectInstance(Persistable persistable, Map<String, String[]> attributes, String usNumber) throws WTException {
        LOGGER.info(String.format(TESTING_CUSTOM_ATTRIBUTES, usNumber, PSMGSBBSoftTypeUtility.getTypeString((Typed) persistable, false)));
        PersistableAdapter persistableAdapter = new PersistableAdapter(persistable, null, SessionHelper.getLocale(), null);
        String notExistingAttributesMessage = AttributeTestUtility.getAllAttributesWhichIncorrectDisplayValue(attributes, PSMGSBBSoftTypeUtility.getTypeString((Typed) persistable, false),
                persistableAdapter);
        String errorMessage = String.format("%s Attributes with names %s has incorrect display values", usNumber, notExistingAttributesMessage);
        assertEquals(errorMessage, "", notExistingAttributesMessage);
    }

    /**
     * Test case verifies if all of custom attributes has correct display values on object type
     *
     * @param objectInternalName internal name of Object
     * @param attributes         map with internal names of all custom attributes and theirs display values
     * @param usNumber           number of user story     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    public static void checkAttributesDisplayNamesForObjectTypeDefinition(String objectInternalName, Map<String, String[]> attributes, String usNumber) throws WTException {
        LOGGER.info(String.format(TESTING_CUSTOM_ATTRIBUTES, usNumber, objectInternalName));
        PersistableAdapter persistableAdapter = new PersistableAdapter(objectInternalName, SessionHelper.getLocale(), null);
        String notExistingAttributesMessage = getAllAttributesWhichIncorrectDisplayValue(attributes, objectInternalName, persistableAdapter);
        String errorMessage = String.format("%s Attributes with names %s has incorrect display values", usNumber, notExistingAttributesMessage);
        assertEquals(errorMessage, "", notExistingAttributesMessage);
    }

    /**
     * Test case verifies if all of custom attributes are set on VariantSpec
     *
     * @param objectInternalName internal name of Object
     * @param attributes         map with internal names of all custom attributes and theirs display values
     * @param usNumber           number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes or cant create VariantSpec
     */
    public static void checkAttributesForVariantSpecInstance(String objectInternalName, Map<String, String[]> attributes, String usNumber) throws WTException {
        LOGGER.info(String.format(TESTING_CUSTOM_ATTRIBUTES, usNumber, objectInternalName));
        WTContainer testProductContainer = WTContainersUtility.getContainerObjectByName(PSMGSBBConstants.ContainerNames.TEST_PRODUCT);
        VariantSpec variantSpec = new VariantSpecificationBuilder(objectInternalName).container(testProductContainer).build();
        checkAttributesForObjectInstance(variantSpec, attributes, usNumber);
    }

    /**
     * Test case verifies if all of custom attributes are set on Plant Function Data object
     *
     * @param persistable tested PlantFunctionalData object
     * @param attributes  map with internal names of all custom attributes and theirs display values
     * @param usNumber    number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes or cant create part
     */
    public static void checkAttributesForObjectInstance(Persistable persistable, Map<String, String[]> attributes, String usNumber) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(persistable, null, SessionHelper.getLocale(), null);
        assertNotNull(usNumber + " - " + PSMGSBBSoftTypeUtility.getTypeString((Typed) persistable, false) + " attributes cannot be loaded.", persistableAdapter);
        String[] keys = Arrays.copyOf(attributes.keySet().toArray(), attributes.keySet().toArray().length, String[].class);
        String notExistingAttributesMessage = getAllAttributesWhichNotExistsMessage(persistableAdapter, keys);
        String errorMessage = String.format("%s Attributes with names %s does not exist on %s", usNumber, notExistingAttributesMessage, PSMGSBBSoftTypeUtility.getTypeString((Typed) persistable,
                false));
        assertEquals(errorMessage, "", notExistingAttributesMessage);
    }

    /**
     * Test case verifies if all of custom attributes are set on object type
     *
     * @param objectInternalName internal name of Object
     * @param attributes         map with internal names of all custom attributes and theirs display values
     * @param usNumber           number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    public static void checkAttributesForTypeDefinition(String objectInternalName, Map<String, String[]> attributes, String usNumber) throws WTException {
        LOGGER.info(String.format(TESTING_CUSTOM_ATTRIBUTES, usNumber, objectInternalName));
        PersistableAdapter persistableAdapter = new PersistableAdapter(objectInternalName, SessionHelper.getLocale(), null);
        assertNotNull(usNumber + " - " + objectInternalName + " attributes cannot be loaded.", persistableAdapter);
        String[] keys = Arrays.copyOf(attributes.keySet().toArray(), attributes.keySet().toArray().length, String[].class);
        String notExistingAttributesMessage = getAllAttributesWhichNotExistsMessage(persistableAdapter, keys);
        String errorMessage = String.format("%s Attributes with names %s does not exist on %s", usNumber, notExistingAttributesMessage, objectInternalName);
        assertEquals(errorMessage, "", notExistingAttributesMessage);
    }

    /**
     * Test case verifies if calculated attribute have correct value
     *
     * @param part          part Object
     * @param attributeName calculated attribute internal name
     * @param expectedValue rexpected value of calculated attribute
     * @param usNumber      number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    public static void checkCalculatedAttributeValue(WTPart part, String attributeName, String expectedValue, String usNumber) throws WTException {
        LOGGER.info(String.format(TESTING_CUSTOM_ATTRIBUTES, usNumber, part));
        PersistableAdapter persistableAdapter = new PersistableAdapter(part, null, SessionHelper.getLocale(), null);
        persistableAdapter.load(attributeName);
        String attributeValue = (String) persistableAdapter.get(attributeName);
        assertEquals(expectedValue, attributeValue);
    }

    /**
     * Test case verifies it calculated attribute have correct value
     *
     * @param enterpriseData EnterpriseData Object
     * @param attributeName  calculated attribute internal name
     * @param expectedValue  expected value of calculated attribute
     * @param usNumber       number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    public static void checkCalculatedAttributeValueForViewSpecificEnterpriseData(EnterpriseData enterpriseData, String attributeName, String expectedValue, String usNumber) throws WTException {
        LOGGER.info(String.format(TESTING_CUSTOM_ATTRIBUTES, usNumber, enterpriseData));
        PersistableAdapter persistableAdapter = new PersistableAdapter(enterpriseData, null, SessionHelper.getLocale(), null);
        persistableAdapter.load(attributeName);
        String attributeValue = (String) persistableAdapter.get(attributeName);
        assertEquals(expectedValue, attributeValue);
    }

    /**
     * Test case verifies if calculated attributes have correct values
     *
     * @param part                part Object
     * @param attributesValuesMap map with all calculated attributes internam names and theirs values
     * @param usNumber            number of user story
     * @throws WTException when can't access Locale from session or can't get Message with list of not existing attributes
     */
    public static void checkCalculatedAttributesValueOnInstance(WTPart part, Map<String, String> attributesValuesMap, String usNumber) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(part, null, SessionHelper.getLocale(), null);
        String notExistingAttributesMessage = getAllAttributesWhichIncorrectValue(attributesValuesMap, persistableAdapter);
        String errorMessage = String.format("%s Calculated Attributes with names %s has incorrect values", usNumber, notExistingAttributesMessage);
        assertEquals(errorMessage, "", notExistingAttributesMessage);
    }

    /**
     * Test case verifies if calculated attributes have correct values
     *
     * @param controlCharacteristic Bolting case object
     * @param attributeName         internal name of bolting case attribute
     * @param expectedValue         attribute value from position variant
     * @throws WTException thrown in case of getting attribute value
     */
    public static void checkCalculatedAttributesValueOnBoltingCase(MPMStandardCC controlCharacteristic, String attributeName, String expectedValue) throws WTException {
        PersistableAdapter persistableAdapter = new PersistableAdapter(controlCharacteristic, null, SessionHelper.getLocale(), null);
        persistableAdapter.load(attributeName);
        String attributeValue = (String) persistableAdapter.get(attributeName);
        assertEquals(" Calculated attributes value mismatch ", expectedValue, attributeValue);
    }
}
