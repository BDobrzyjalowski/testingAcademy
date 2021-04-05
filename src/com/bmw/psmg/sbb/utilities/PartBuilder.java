package com.bmw.psmg.sbb.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import wt.fc.PersistenceHelper;
import wt.generic.GenericType;
import wt.inf.container.WTContainer;
import wt.part.PartType;
import wt.part.QuantityUnit;
import wt.part.WTPart;
import wt.type.TypedUtilityServiceHelper;
import wt.util.WTException;
import wt.util.WTPropertyVetoException;
import wt.vc.views.View;
import wt.vc.views.ViewReference;

import java.rmi.RemoteException;

/**
 * Class provide creation of WTPart from components like Internal Name, Container, View, etc.
 *
 * @author Karol Poliszkiewicz
 */
public class PartBuilder {

    private static final Logger LOGGER = Logger.getLogger(PartBuilder.class.getName());
    private static final String PREFIX = "psmgsbb-test-";

    private final String internalName;
    private String number;
    private String name;
    private View view;
    private WTContainer container;
    private boolean endItem = false;
    private PartType partType;
    private GenericType genericType;
    private QuantityUnit defaultUnit;

    /**
     * Method set Internal name of creating WTPart.
     *
     * @param internalName of creating WTPart
     */
    public PartBuilder(String internalName) {
        this.internalName = internalName;
    }

    /**
     * Generate random name for WTPart object.
     *
     * @param prefix if true add testing prefix before generated name
     * @return random name
     */
    public static String randomName(boolean prefix) {
        LOGGER.debug("Start generating WTPart name");
        String random = RandomStringUtils.random(7, true, true);
        return prefix ? PREFIX + random : random;
    }

    /**
     * Method set Number of creating WTPart.
     *
     * @param number of creating WTPart
     * @return this creating object
     */
    public PartBuilder number(String number) {
        this.number = number;
        return this;
    }

    /**
     * Method set Name of creating WTPart.
     *
     * @param name of creating WTPart
     * @return this creating object
     */
    public PartBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Method set View of creating WTPart.
     *
     * @param view of creating WTPart
     * @return this creating object
     */
    public PartBuilder view(View view) {
        this.view = view;
        return this;
    }

    /**
     * Method set Container of creating WTPart.
     *
     * @param container of creating WTPart
     * @return this creating object
     */
    public PartBuilder container(WTContainer container) {
        this.container = container;
        return this;
    }

    /**
     * Method set End Item of creating WTPart
     *
     * @param endItem of creating WTPart
     * @return this creating object
     */
    public PartBuilder endItem(boolean endItem) {
        this.endItem = endItem;
        return this;
    }

    /**
     * Method set Assembly Mode of creating WTPart
     *
     * @param partType of creating WTPart
     * @return this creating object
     */
    public PartBuilder partType(PartType partType) {
        this.partType = partType;
        return this;
    }

    /**
     * Method set Configurable Module of creating WTPart
     *
     * @param genericType of creating WTPart
     * @return this creating object
     */
    public PartBuilder genericType(GenericType genericType) {
        this.genericType = genericType;
        return this;
    }

    /**
     * Method set Default Quantity Unit of creating WTPart
     *
     * @param defaultUnit of creating WTPart
     * @return this creating object
     */
    public PartBuilder defaultUnit(QuantityUnit defaultUnit) {
        this.defaultUnit = defaultUnit;
        return this;
    }
    /**
     * Method create and save WTPart in Database.
     *
     * @return object of WTPart
     * @throws WTException when cannot create WTPart or not find type definition reference
     * @see WTPart
     * @see wt.type.TypeDefinitionReference
     */
    public WTPart build() throws WTException {
        return build(true);
    }

    /**
     * Method create WTPart.
     *
     * @param save - when true the method save WTPart object
     * @return object of WTPart
     * @throws WTException when cannot create WTPart or not find type definition reference
     * @see WTPart
     * @see wt.type.TypeDefinitionReference
     */
    public WTPart build(boolean save) throws WTException {
        if (StringUtils.isEmpty(internalName)) {
            throw new IllegalArgumentException("Internal name is required to create WTPart!");
        }
        if (StringUtils.isEmpty(name)) {
            name = randomName(true);
        }
        WTPart part = WTPart.newWTPart();
        try {
            if (number != null) {
                part.setNumber(number);
            }
            part.setName(name);
            if (view != null) {
                part.setView(ViewReference.newViewReference(view));
            }
            if (container != null) {
                part.setContainer(container);
            }
            part.setEndItem(endItem);
            if (partType != null) {
                part.setPartType(partType);
            }
            if (genericType != null) {
                part.setGenericType(genericType);
            }
            part.setTypeDefinitionReference(TypedUtilityServiceHelper.service.getTypeDefinitionReference(internalName));
            if (defaultUnit != null) {
                part.setDefaultUnit(defaultUnit);
            }
            return save ? (WTPart) PersistenceHelper.manager.save(part) : part;
        } catch (WTPropertyVetoException | RemoteException e) {
            LOGGER.error("Internal exception during WTPart creation", e);
            throw new WTException(e, String.format("Cannot create WTPart of '%s' type.", internalName));
        }
    }

}
