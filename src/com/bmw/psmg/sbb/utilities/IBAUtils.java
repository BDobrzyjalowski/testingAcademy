package com.bmw.psmg.sbb.utilities;

import com.ptc.core.businessfield.common.BusinessField;
import com.ptc.core.businessfield.common.BusinessFieldIdFactoryHelper;
import com.ptc.core.businessfield.common.BusinessFieldServiceHelper;
import com.ptc.core.businessfield.server.BusinessFieldIdentifier;
import com.ptc.core.businessfield.server.businessObject.BusinessObject;
import com.ptc.core.businessfield.server.businessObject.BusinessObjectHelper;
import com.ptc.core.businessfield.server.businessObject.BusinessObjectHelperFactory;
import com.ptc.core.components.rendering.guicomponents.DateFormatter;
import com.ptc.core.lwc.server.PersistableAdapter;
import com.ptc.core.meta.common.DataSet;
import com.ptc.core.meta.common.DiscreteSet;
import com.ptc.core.meta.common.DisplayOperationIdentifier;
import com.ptc.core.meta.common.EnumerationEntryIdentifier;
import com.ptc.core.meta.common.FloatingPoint;
import com.ptc.core.meta.common.OperationIdentifier;
import com.ptc.core.meta.common.OperationIdentifierConstants;
import com.ptc.core.meta.common.TypeIdentifier;
import com.ptc.core.meta.common.UpdateOperationIdentifier;
import com.ptc.core.meta.container.common.AttributeTypeSummary;
import com.ptc.core.meta.descriptor.common.DefinitionDescriptor;
import com.ptc.core.meta.descriptor.common.DefinitionDescriptorFactory;
import com.ptc.windchill.partslink.units.RealNumberWithUnitsHelper;
import com.ptc.windchill.uwgm.common.attributes.IBAHelper;
import org.apache.log4j.Logger;
import wt.epm.EPMDocument;
import wt.epm.EPMDocumentMaster;
import wt.epm._EPMAuthoringAppType;
import wt.fc.Persistable;
import wt.fc.PersistenceHelper;
import wt.fc.PersistenceServerHelper;
import wt.iba.constraint.AttributeConstraint;
import wt.iba.constraint.ConstraintGroup;
import wt.iba.constraint.Immutable;
import wt.iba.definition.litedefinition.AttributeDefDefaultView;
import wt.iba.definition.litedefinition.BooleanDefView;
import wt.iba.definition.litedefinition.FloatDefView;
import wt.iba.definition.litedefinition.IntegerDefView;
import wt.iba.definition.litedefinition.StringDefView;
import wt.iba.definition.litedefinition.TimestampDefView;
import wt.iba.definition.service.IBADefinitionHelper;
import wt.iba.definition.service.StandardIBADefinitionService;
import wt.iba.value.DefaultAttributeContainer;
import wt.iba.value.IBAHolder;
import wt.iba.value.litevalue.AbstractValueView;
import wt.iba.value.litevalue.BooleanValueDefaultView;
import wt.iba.value.litevalue.FloatValueDefaultView;
import wt.iba.value.litevalue.IntegerValueDefaultView;
import wt.iba.value.litevalue.StringValueDefaultView;
import wt.iba.value.litevalue.TimestampValueDefaultView;
import wt.iba.value.service.IBAValueDBService;
import wt.iba.value.service.IBAValueDBServiceInterface;
import wt.iba.value.service.IBAValueHelper;
import wt.iba.value.service.IBAValueService;
import wt.iba.value.service.StandardIBAValueService;
import wt.preference.PreferenceHelper;
import wt.services.ManagerServiceFactory;
import wt.services.ServiceProviderHelper;
import wt.session.SessionHelper;
import wt.type.TypedUtility;
import wt.units.FloatingPointWithUnits;
import wt.units.service.MeasurementSystemDefaultView;
import wt.units.service.UnitsHelper;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Vector;

/**
 * IBA Utilities Class
 *
 * @author Mickael Garnaud
 * @author Jacek Miszczyk
 */
public class IBAUtils {

    public static final String OTHER = "OTHER";
    private static final Logger LOGGER = Logger.getLogger(IBAUtils.class.getName());
    private static final BusinessObjectHelper BUSINESS_OBJECT_HELPER = BusinessObjectHelperFactory.getBusinessObjectHelper();
    private static final String SDF = "yyyy-MM-dd HH:mm:ss.S";
    private static final String DISPLAY_UNITS_SYSTEM = "/wt/units/display/displayUnitsSystem";
    private static final String CLIENT_NAME = "WINDCHILL";

    private IBAUtils() {
    }

    /**
     * Set business object field's value by identifier.
     *
     * @param persistable           identifier of business object
     * @param attributeInternalName identifier of business object's field
     * @param value                 new value of business object's field
     * @throws WTException when cannot get session or business field type
     * @deprecated the method has a very low performance, use PersistableAdapter to set attribute value.
     */
    @Deprecated
    public static void setAttribute(Persistable persistable, String attributeInternalName, Object value) throws WTException {
        Optional<TypeIdentifier> typeIdentifier = Optional.ofNullable(TypedUtility.getTypeIdentifier(persistable));
        if (typeIdentifier.isPresent()) {
            List<BusinessObject> businessObjects = BUSINESS_OBJECT_HELPER.newBusinessObjects(SessionHelper.getLocale(), new DisplayOperationIdentifier(), false, persistable);

            List<BusinessField> businessFields = Collections.singletonList(getTypeBusinessField(typeIdentifier.get().getTypeInternalName(), attributeInternalName));
            BUSINESS_OBJECT_HELPER.load(businessObjects, businessFields);

            if (!businessObjects.isEmpty()) {
                BusinessObject businessObject = businessObjects.get(0);
                businessObject.set(businessFields.get(0), value);
                BUSINESS_OBJECT_HELPER.apply(businessObjects);
                LOGGER.debug("Successful apply value of business object's field");
            }
        }
    }

    /**
     * Retrieves IBA Value based on its internal name
     *
     * @param persistable           determine source of attribute
     * @param attributeInternalName identifying, which attribute will be retrieved
     * @return attribute value
     * @throws WTException when cannot get session or business field type
     * @deprecated the method has a very low performance, use PersistableAdapter to get attribute value.
     */
    @Deprecated
    public static Object retrieveAttribute(Persistable persistable, String attributeInternalName) throws WTException {
        Object attribute = null;
        Optional<TypeIdentifier> optional = Optional.ofNullable(TypedUtility.getTypeIdentifier(persistable));
        if (optional.isPresent()) {
            List<BusinessObject> businessObjects = BUSINESS_OBJECT_HELPER.newBusinessObjects(SessionHelper.getLocale(), new DisplayOperationIdentifier(), false, persistable);

            String typeInternalName = optional.get().getTypeInternalName();
            List<BusinessField> businessFields = Collections.singletonList(getTypeBusinessField(typeInternalName, attributeInternalName));
            BUSINESS_OBJECT_HELPER.load(businessObjects, businessFields);

            if (!businessObjects.isEmpty()) {
                BusinessObject businessObject = businessObjects.get(0);
                attribute = (businessObject.get(businessFields.get(0)));
            }
        }
        return attribute;
    }

    /**
     * Retrieves business field.
     *
     * @param typeName internal name of type
     * @param attrName internal name of attribute
     * @return         business field
     * @throws WTException if system cannot get BusinessFieldIdentifier or BusinessField for passed type-attribute pair
     */
    public static BusinessField getTypeBusinessField(final String typeName, final String attrName) throws WTException {
        BusinessFieldIdentifier bfid = BusinessFieldIdFactoryHelper.FACTORY.getTypeBusinessFieldIdentifier(attrName, typeName);
        return BusinessFieldServiceHelper.SERVICE.getBusinessField(bfid);
    }

    /**
     * Deletes given IBA attribute from IBA Holder. No need to persist holder afterwards
     *
     * @param ibaHolder ibaHolder
     * @param ibaName   iba internal name
     * @throws RemoteException exception
     * @throws WTException     exception
     */
    public static void deleteIBAAttribute(IBAHolder ibaHolder, String ibaName) throws WTException, RemoteException {
        IBAHolder holder = IBAValueHelper.service.refreshAttributeContainer(ibaHolder, null, SessionHelper.manager.getLocale(), null);
        DefaultAttributeContainer container = (DefaultAttributeContainer) holder.getAttributeContainer();
        suppressConstraint(container);
        // get exact attribute definer
        AttributeDefDefaultView attDefiner = IBADefinitionHelper.service.getAttributeDefDefaultViewByPath(ibaName);
        // remove attribute values from container if any
        AbstractValueView[] avv = container.getAttributeValues(attDefiner);
        for (AbstractValueView abstractValueView : avv) {
            container.deleteAttributeValue(abstractValueView);
        }

        ManagerServiceFactory.getDefault().getManager(IBAValueService.class);
        StandardIBAValueService.theIBAValueDBService.updateAttributeContainer(ibaHolder, container.getConstraintParameter(), null, null);
    }

    private static void suppressConstraint(DefaultAttributeContainer theContainer) {
        Vector cgs = theContainer.getConstraintGroups();
        Vector newCgs = new Vector();

        try {
            for (int i = 0; i < cgs.size(); i++) {
                ConstraintGroup cg = (ConstraintGroup) cgs.elementAt(i);
                if (cg != null) {
                    newCgs = getImmutableConstraints(cg);
                }
            }
            theContainer.setConstraintGroups(newCgs);
        } catch (Exception ex) {
            LOGGER.error("Got unexpected exception when suppressing constrains on container.", ex);
        }
    }

    private static Vector getImmutableConstraints(ConstraintGroup cg) throws WTPropertyVetoException {
        Vector newCgs = new Vector();
        Enumeration<?> enums = cg.getConstraints();
        ConstraintGroup newCg = new ConstraintGroup();
        newCg.setConstraintGroupLabel(cg.getConstraintGroupLabel());
        while (enums.hasMoreElements()) {
            AttributeConstraint ac = (AttributeConstraint) enums.nextElement();
            if (!(ac.getValueConstraint() instanceof Immutable)) {
                newCg.addConstraint(ac);
            }
        }
        newCgs.addElement(newCg);
        return newCgs;
    }

    /**
     * setAndUpdateStringIBAValue method
     *
     * @param ibaHolder iba holder
     * @param ibaName   iba internal name
     * @param ibaValue  value to set
     * @throws WTException exception
     */
    public static void setAndUpdateStringIBAValue(IBAHolder ibaHolder, String ibaName, String ibaValue) throws WTException {
        try {
            DefaultAttributeContainer attributeContainer = getRefreshableAttributeContainer(ibaHolder, false);
            ibaHolder = (IBAHolder) PersistenceHelper.manager.refresh((Persistable) ibaHolder);

            if (ibaHolder instanceof EPMDocument && (((EPMDocument) ibaHolder).isGeneric() || ((EPMDocument) ibaHolder).isInstance())) {
                suppressConstraint(attributeContainer);
                EPMDocumentMaster master = (EPMDocumentMaster) ((EPMDocument) ibaHolder).getMaster();
                master.setAuthoringApplication(_EPMAuthoringAppType.toEPMAuthoringAppType(OTHER));
            }

            // Get attribute definition
            AttributeDefDefaultView attributeDefinition = getAttributeDefDefaultView(ibaName);
            if (!(attributeDefinition instanceof StringDefView)) {//
                throw new WTException("IBA " + ibaName + " is not of type String");
            }
            StringDefView stringAttrDefinition = (StringDefView) attributeDefinition;
            // Check if the attribute is already defined
            AbstractValueView[] abstractValueView = attributeContainer.getAttributeValues(stringAttrDefinition);
            if (abstractValueView.length == 0) {
                // Add new attribute value
                StringValueDefaultView attrValue = new StringValueDefaultView(stringAttrDefinition, ibaValue);
                attributeContainer.addAttributeValue(attrValue);
            } else {
                // Update current attribute value
                StringValueDefaultView attrValue = (StringValueDefaultView) abstractValueView[0];
                attrValue.setValue(ibaValue);
                attributeContainer.updateAttributeValue(attrValue);
            }
            ibaHolder.setAttributeContainer(attributeContainer);
            updateAttributeContainer(ibaHolder);
            PersistenceHelper.manager.refresh((Persistable) ibaHolder);
        } catch (WTPropertyVetoException | RemoteException ex) {
            throw new WTException(ex);
        }
    }

    /**
     * setAndUpdateBooleanIBAValue method
     *
     * @param ibaHolder ibaHolder
     * @param ibaName   iba internal name
     * @param ibaValue  new iba value
     * @throws WTException exception
     */
    public static void setAndUpdateBooleanIBAValue(IBAHolder ibaHolder, String ibaName, Boolean ibaValue) throws WTException {
        try {
            DefaultAttributeContainer attributeContainer = getRefreshableAttributeContainer(ibaHolder, false);
            ibaHolder = (IBAHolder) PersistenceHelper.manager.refresh((Persistable) ibaHolder);

            if (ibaHolder instanceof EPMDocument && (((EPMDocument) ibaHolder).isGeneric() || ((EPMDocument) ibaHolder).isInstance())) {
                suppressConstraint(attributeContainer);
                EPMDocumentMaster master = (EPMDocumentMaster) ((EPMDocument) ibaHolder).getMaster();
                master.setAuthoringApplication(_EPMAuthoringAppType.toEPMAuthoringAppType(OTHER));
            }

            // Get attribute definition
            AttributeDefDefaultView attributeDefinition = getAttributeDefDefaultView(ibaName);
            if (!(attributeDefinition instanceof BooleanDefView)) {//
                throw new WTException("IBA " + ibaName + " is not of type Boolean");
            }
            BooleanDefView booleanAttrDefinition = (BooleanDefView) attributeDefinition;
            // Check if the attribute is already defined
            AbstractValueView[] abstractValueView = attributeContainer.getAttributeValues(booleanAttrDefinition);
            if (abstractValueView.length == 0) {
                // Add new attribute value
                BooleanValueDefaultView attrValue = new BooleanValueDefaultView(booleanAttrDefinition, ibaValue);
                attributeContainer.addAttributeValue(attrValue);
            } else {
                // Update current attribute value
                BooleanValueDefaultView attrValue = (BooleanValueDefaultView) abstractValueView[0];
                attrValue.setValue(ibaValue);
                attributeContainer.updateAttributeValue(attrValue);
            }
            ibaHolder.setAttributeContainer(attributeContainer);
            updateAttributeContainer(ibaHolder);
            PersistenceHelper.manager.refresh((Persistable) ibaHolder);
        } catch (WTPropertyVetoException | RemoteException ex) {
            throw new WTException(ex);
        }
    }

    /**
     * setAndUpdateIntegerIBAValue method
     *
     * @param ibaHolder ibaHolder
     * @param ibaName   attribute internal name
     * @param ibaValue  new attribute value (Integer)
     * @throws WTException exception
     */
    public static void setAndUpdateIntegerIBAValue(IBAHolder ibaHolder, String ibaName, Integer ibaValue) throws WTException {
        try {
            DefaultAttributeContainer attributeContainer = getRefreshableAttributeContainer(ibaHolder, false);
            ibaHolder = (IBAHolder) PersistenceHelper.manager.refresh((Persistable) ibaHolder);

            if (ibaHolder instanceof EPMDocument && (((EPMDocument) ibaHolder).isGeneric() || ((EPMDocument) ibaHolder).isInstance())) {
                suppressConstraint(attributeContainer);
                EPMDocumentMaster master = (EPMDocumentMaster) ((EPMDocument) ibaHolder).getMaster();
                master.setAuthoringApplication(_EPMAuthoringAppType.toEPMAuthoringAppType(OTHER));
            }

            // Get attribute definition
            AttributeDefDefaultView attributeDefinition = getAttributeDefDefaultView(ibaName);
            if (!(attributeDefinition instanceof IntegerDefView)) {//
                throw new WTException("IBA " + ibaName + " is not of type Integer");
            }
            IntegerDefView integerAttrDefinition = (IntegerDefView) attributeDefinition;
            // Check if the attribute is already defined
            AbstractValueView[] abstractValueView = attributeContainer.getAttributeValues(integerAttrDefinition);
            if (abstractValueView.length == 0) {
                // Add new attribute value
                IntegerValueDefaultView attrValue = new IntegerValueDefaultView(integerAttrDefinition, ibaValue);
                attributeContainer.addAttributeValue(attrValue);
            } else {
                // Update current attribute value
                IntegerValueDefaultView attrValue = (IntegerValueDefaultView) abstractValueView[0];
                attrValue.setValue(ibaValue);
                attributeContainer.updateAttributeValue(attrValue);
            }
            ibaHolder.setAttributeContainer(attributeContainer);
            updateAttributeContainer(ibaHolder);
            PersistenceHelper.manager.refresh((Persistable) ibaHolder);
        } catch (WTPropertyVetoException | RemoteException ex) {
            throw new WTException(ex);
        }
    }

    /**
     * setAndUpdateDateIBAValue
     *
     * @param ibaHolder ibaHolder
     * @param ibaName   attribute internal name
     * @param ibaValue  new attribute value
     * @throws WTException exception
     */
    public static void setAndUpdateDateIBAValue(IBAHolder ibaHolder, String ibaName, Timestamp ibaValue) throws WTException {
        try {
            DefaultAttributeContainer attributeContainer = getRefreshableAttributeContainer(ibaHolder, false);
            ibaHolder = (IBAHolder) PersistenceHelper.manager.refresh((Persistable) ibaHolder);

            if (ibaHolder instanceof EPMDocument && (((EPMDocument) ibaHolder).isGeneric() || ((EPMDocument) ibaHolder).isInstance())) {
                suppressConstraint(attributeContainer);
                EPMDocumentMaster master = (EPMDocumentMaster) ((EPMDocument) ibaHolder).getMaster();
                master.setAuthoringApplication(_EPMAuthoringAppType.toEPMAuthoringAppType(OTHER));
            }

            // Get attribute definition
            AttributeDefDefaultView attributeDefinition = getAttributeDefDefaultView(ibaName);
            if (!(attributeDefinition instanceof TimestampDefView)) {//
                throw new WTException("IBA " + ibaName + " is not of type Date");
            }
            TimestampDefView timestampAttrDefinition = (TimestampDefView) attributeDefinition;
            // Check if the attribute is already defined
            AbstractValueView[] abstractValueView = attributeContainer.getAttributeValues(timestampAttrDefinition);
            if (abstractValueView.length == 0) {
                // Add new attribute value
                TimestampValueDefaultView attrValue = new TimestampValueDefaultView(timestampAttrDefinition, ibaValue);
                attributeContainer.addAttributeValue(attrValue);
            } else {
                // Update current attribute value
                TimestampValueDefaultView attrValue = (TimestampValueDefaultView) abstractValueView[0];
                attrValue.setValue(ibaValue);
                attributeContainer.updateAttributeValue(attrValue);
            }
            ibaHolder.setAttributeContainer(attributeContainer);
            updateAttributeContainer(ibaHolder);
            PersistenceHelper.manager.refresh((Persistable) ibaHolder);
        } catch (WTPropertyVetoException | RemoteException ex) {
            throw new WTException(ex);
        }
    }

    /**
     * setAndUpdateFloatIBAValue
     *
     * @param ibaHolder ibaHolder
     * @param ibaName   attribute internal name
     * @param ibaValue  new attribute value
     * @throws WTException exception
     */
    public static void setAndUpdateFloatIBAValue(IBAHolder ibaHolder, String ibaName, Float ibaValue) throws WTException {
        try {
            DefaultAttributeContainer attributeContainer = getRefreshableAttributeContainer(ibaHolder, false);
            ibaHolder = (IBAHolder) PersistenceHelper.manager.refresh((Persistable) ibaHolder);

            if (ibaHolder instanceof EPMDocument && (((EPMDocument) ibaHolder).isGeneric() || ((EPMDocument) ibaHolder).isInstance())) {
                suppressConstraint(attributeContainer);
                EPMDocumentMaster master = (EPMDocumentMaster) ((EPMDocument) ibaHolder).getMaster();
                master.setAuthoringApplication(_EPMAuthoringAppType.toEPMAuthoringAppType(OTHER));
            }

            // Get attribute definition
            AttributeDefDefaultView attributeDefinition = getAttributeDefDefaultView(ibaName);
            if (!(attributeDefinition instanceof FloatDefView)) {//
                throw new WTException("IBA " + ibaName + " is not of type Float");
            }
            FloatDefView floatAttrDefinition = (FloatDefView) attributeDefinition;
            // Check if the attribute is already defined
            AbstractValueView[] abstractValueView = attributeContainer.getAttributeValues(floatAttrDefinition);
            if (abstractValueView.length == 0) {
                // Add new attribute value
                FloatValueDefaultView attrValue = new FloatValueDefaultView(floatAttrDefinition, Double.parseDouble(ibaValue.toString()), 10);
                attributeContainer.addAttributeValue(attrValue);
            } else {
                // Update current attribute value
                FloatValueDefaultView attrValue = (FloatValueDefaultView) abstractValueView[0];
                attrValue.setValue(Double.parseDouble(ibaValue.toString()));
                attributeContainer.updateAttributeValue(attrValue);
            }
            ibaHolder.setAttributeContainer(attributeContainer);
            updateAttributeContainer(ibaHolder);
            PersistenceHelper.manager.refresh((Persistable) ibaHolder);
        } catch (WTPropertyVetoException | RemoteException ex) {
            throw new WTException(ex);
        }
    }

    private static void updateAttributeContainer(IBAHolder ibaHolder) throws WTException {
        IBAValueDBServiceInterface dbService = (IBAValueDBServiceInterface) ManagerServiceFactory.getDefault().getManager(IBAValueDBService.class);
        DefaultAttributeContainer defaultattributecontainer = (DefaultAttributeContainer) ibaHolder.getAttributeContainer();
        dbService.updateAttributeContainer(ibaHolder, defaultattributecontainer != null ? defaultattributecontainer.getConstraintParameter() : null, null, null);
    }

    private static AttributeDefDefaultView getAttributeDefDefaultView(String ibaName) throws RemoteException, WTException {
        StandardIBADefinitionService defService = new StandardIBADefinitionService();
        return defService.getAttributeDefDefaultViewByPath(ibaName);
    }

    private static DefaultAttributeContainer getRefreshableAttributeContainer(IBAHolder ibaHolder, boolean refreshFlag) throws WTException, RemoteException {
        DefaultAttributeContainer attributeContainer = (DefaultAttributeContainer) ibaHolder.getAttributeContainer();
        if ((attributeContainer == null) || (PersistenceHelper.isPersistent(ibaHolder) && refreshFlag)) {
            ibaHolder = IBAValueHelper.service.refreshAttributeContainer(ibaHolder, null, null, null);
            attributeContainer = (DefaultAttributeContainer) ibaHolder.getAttributeContainer();
        }
        return attributeContainer;
    }

    /**
     * Set and update IBA attribute value
     *
     * @param persistable  IBAHolder
     * @param internalName internal name of attribute
     * @param dataType     class type of attrybute
     * @param value        new value to set
     * @throws WTException exception
     */
    public static void setAndUpdateIBAValue(Persistable persistable, String internalName, Class<?> dataType, String value) throws WTException {
        PersistableAdapter pa = new PersistableAdapter(persistable, null, SessionHelper.getLocale(), new UpdateOperationIdentifier());
        pa.load(internalName);

        if (String.class == dataType) {
            pa.set(internalName, value);
            setAndUpdateStringIBAValue((IBAHolder) persistable, internalName, value);
        } else if (Boolean.class == dataType) {
            Boolean booleanValue = Boolean.valueOf(value);
            pa.set(internalName, booleanValue);
            setAndUpdateBooleanIBAValue((IBAHolder) persistable, internalName, booleanValue);
        } else if (Long.class == dataType) {
            Integer integerValue = Integer.valueOf(value);
            setAndUpdateIntegerIBAValue((IBAHolder) persistable, internalName, integerValue);
        } else if (Timestamp.class == dataType) {
            Timestamp timestampValue;
            try {
                timestampValue = new Timestamp(new SimpleDateFormat(SDF).parse(value).getTime());
            } catch (ParseException parseException) {
                LOGGER.error(parseException);
                throw new WTException(parseException.getMessage());
            }
            pa.set(internalName, timestampValue);
            setAndUpdateDateIBAValue((IBAHolder) persistable, internalName, timestampValue);
        } else if (FloatingPoint.class == dataType) {
            Float floatValue = Float.valueOf(value);
            setAndUpdateFloatIBAValue((IBAHolder) persistable, internalName, floatValue);
        } else {
            throw new WTException("Unsupported data type. Attribute: " + internalName + ", data type: " + dataType);
        }
    }

    /**
     * Sets IBA value and updates passed persistable with specified modify date.
     *
     * @param p              Persistable whose IBA should be updated
     * @param attributeName  IBA name
     * @param attributeValue IBA value
     * @param isCheckedOut   Specifies if p is checked out causing different update method to be used.
     * @param modifyDate     When isCheckedOut is true, modifyDate flag will be passed to
     *                       {@link wt.fc.PersistenceManagerSvr#update(Persistable, boolean)} method
     * @throws WTException exception
     */
    public static void setAttributeValue(Persistable p, String attributeName, Object attributeValue, boolean isCheckedOut, boolean modifyDate) throws WTException {
        PersistableAdapter obj = new PersistableAdapter(p, null, SessionHelper.getLocale(), new UpdateOperationIdentifier());
        obj.load(attributeName);
        obj.set(attributeName, attributeValue);
        obj.apply();
        if (isCheckedOut) {
            PersistenceHelper.manager.modify(p);
        } else {
            PersistenceServerHelper.manager.update(p, modifyDate);
        }
    }

    /**
     * Gets attribute value String as shown in the UI, respecting Locale settings.
     * The method can be used to get display value of attribute, when attribute has <i>Enumerated Value List</i> constraint using <i>Global Enumeration</i>.
     * For example, <i>BMW_L_CODE</i> attribute on <i>Material</i> type has the constraint using <i>BMW L Marking</i> enumeration. 
     * According to this enumeration, for saved value <i>XX</i>, display will be <i>XX - Unknown</i> in English and <i>XX - Unbekannt</i> in German.
     * This method helps to fetch the display value, when internal value <i>XX</i> is given.
     * @param typeInternalName internal name of object type; for example wt.part.WTPart|com.bmw.psmg.sbb.Material
     * @param attrName name of attribute; for example BMW_L_CODE
     * @param attrValue value of attribute obtained from a Persistable object
     * @return attribute value as a String as shown in UI
     * @throws WTException if failed to load attribute or get AttributeDescriptor
     */
    public static String getAttributeDisplayValue(String typeInternalName, String attrName, Object attrValue) throws WTException {
        String attrDisplayValue = null;
        if(attrValue != null) {
            attrDisplayValue = attrValue.toString();
            Locale locale = SessionHelper.getLocale();
            PersistableAdapter obj = new PersistableAdapter(typeInternalName, locale, OperationIdentifier.newOperationIdentifier(OperationIdentifierConstants.VIEW));
            obj.load(attrName);
            AttributeTypeSummary attTypeSummary = obj.getAttributeDescriptor(attrName);
            DataSet dataSet = attTypeSummary.getLegalValueSet();
            if(dataSet instanceof DiscreteSet) {
                Object[] dataSetElements = ((DiscreteSet) dataSet).getElements();
                int dataSetLength = dataSetElements.length;
                DefinitionDescriptorFactory factory = (DefinitionDescriptorFactory) ServiceProviderHelper.getService(DefinitionDescriptorFactory.class, "default");
                for (int count = 0; count < dataSetLength; count++) {
                    Object dataSetElement = dataSetElements[count];
                    if (dataSetElement instanceof EnumerationEntryIdentifier && ((EnumerationEntryIdentifier) dataSetElement).getKey().equals(attrValue)) {
                        DefinitionDescriptor desc = factory.get((EnumerationEntryIdentifier)dataSetElement, null, locale);
                        attrDisplayValue = desc.getDisplay();
                    }
                }
            }
        }
        return attrDisplayValue;
    }

    /**
     * Converts Timestamp value of attribute to String, respecting Locale settings
     * @param typeInternalName internal name of object type; for example wt.part.WTPart|com.bmw.psmg.sbb.Material
     * @param attrName name of attribute; for example BMW_LAST_UPDATED
     * @param attrValue Timestamp value of attribute
     * @return Timestamp value converted to String (as shown in UI)
     * @throws WTException if failed to load attribute or get AttributeDescriptor
     */
    public static String getConvertedTimestampValue(String typeInternalName, String attrName, Timestamp attrValue) throws WTException {
        PersistableAdapter obj = new PersistableAdapter(typeInternalName, Locale.US, OperationIdentifier.newOperationIdentifier(OperationIdentifierConstants.VIEW));
        obj.load(attrName);
        AttributeTypeSummary attTypeSummary = obj.getAttributeDescriptor(attrName);
        String formatString = attTypeSummary.getDateDisplayFormat();
        Locale locale = SessionHelper.getLocale();
        if(formatString == null) {
            formatString = DateFormatter.getDefaultInputFormat(false, locale);
        }
        DateFormat dateFormat = new SimpleDateFormat(formatString, locale);
        dateFormat.setTimeZone(DateTimeUtils.getTimeZonePreference(SessionHelper.getPrincipal()));
        return dateFormat.format(attrValue);
    }

    /**
     * Gets string from FloatingPointWithUnits
     * @param fpWithUnits given FloatingPointWithUnits
     * @return FloatingPointWithUnits converted to String
     * @throws WTException if failed to get base value symbol
     * @throws RemoteException if failed to get base value symbol
     */
    public static String getConvertedFloatingPointValue(FloatingPointWithUnits fpWithUnits) throws WTException, RemoteException {
        String defaultMeasurementSystem = String.valueOf(PreferenceHelper.service.getValue(DISPLAY_UNITS_SYSTEM, CLIENT_NAME));
        if (!defaultMeasurementSystem.equals(IBAHelper.WC_MEASUREMENT_SYSTEM)) {
            String baseSymbol = getValueBaseSymbol(fpWithUnits, defaultMeasurementSystem);
            String convertedAttributeValue = RealNumberWithUnitsHelper.convertValueFromBaseUnitToSpecifiedUnit(baseSymbol, fpWithUnits);
            if (convertedAttributeValue.endsWith(".0")) {
                convertedAttributeValue = convertedAttributeValue.substring(0, convertedAttributeValue.length() - 2);
            }
            return convertedAttributeValue + " " + baseSymbol;
        }
        return String.valueOf(fpWithUnits);
    }

    /**
     * Gets base value symbol for FloatingPointWithUnits in a given measurement system
     * @param fpWithUnits given FloatingPointWithUnits
     * @param measurementSystemStr measurement system, like SI
     * @return base symbol
     * @throws RemoteException if failed to get measurement system default view
     * @throws WTException if failed to get measurement system default view
     */
    private static String getValueBaseSymbol(FloatingPointWithUnits fpWithUnits, String measurementSystemStr) throws RemoteException, WTException {
        String attributeDefaultUnit = fpWithUnits.getUnits();
        MeasurementSystemDefaultView measurementSystem = UnitsHelper.service.getMeasurementSystemDefaultView(measurementSystemStr);
        return measurementSystem.getBaseSymbol(attributeDefaultUnit);
    }
}
