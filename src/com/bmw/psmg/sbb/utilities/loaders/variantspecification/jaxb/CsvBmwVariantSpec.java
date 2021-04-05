package com.bmw.psmg.sbb.utilities.loaders.variantspecification.jaxb;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Processes the 'csvBmwVariantSpec' directive in the XML load file
 */
@XmlRootElement(name = "csvBmwVariantSpec")
public class CsvBmwVariantSpec {

    private static final String DEFAULT_FOLDER_PATH = "/Default/Type Filter";

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.bmw.psmg.sbb.load.variantspecification.LoadVariantSpec.createVariantSpec";

    @XmlElement(name = "csvBmwVariantSpecNumber")
    private String number;

    @XmlElement(name = "csvBmwVariantSpecName")
    private String name;

    @XmlElement(name = "csvdescription")
    private String description;

    @XmlElement(name = "csvfolder")
    private String folder;

    @XmlElement(name = "csvparentContainerPath")
    private String containerPath;

    @XmlElement(name = "csvBmwChoicesList")
    private String choicesList;

    @XmlElement(name = "csvtypedef")
    private String typeInternalName;

    @XmlElement(name = "csvBmwAttributes")
    private String bmwAttributes;

    @XmlElement(name = "csvlifecyclestate")
    private String state;

    @XmlElement(name = "csvfilterMode")
    private String filterMode;

    @XmlTransient
    private String modelCode;

    @XmlTransient
    private List<String> choices;

    public CsvBmwVariantSpec() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Constructor that requires the most important fields, rest are filled with empty string or default value
     *
     * @param number number of VariantSpecification
     * @param name   name of VariantSpecification
     */
    public CsvBmwVariantSpec(String number, String name) {
        this.number = number;
        this.name = name;
        this.description = "";
        this.folder = DEFAULT_FOLDER_PATH;
        this.containerPath = "";
        this.choicesList = "";
        this.typeInternalName = PSMGSBBConstants.Types.VARIANT_SPEC_INTERNAL_NAME;
        this.state = "";
        this.filterMode = "";
    }

    /**
     * Set Variant Specification's description
     *
     * @param description description value
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set Variant Specification's folder path
     *
     * @param folder folder path
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     * Set Variant Specification's container path
     *
     * @param containerPath container path
     */
    public void setContainerPath(String containerPath) {
        this.containerPath = containerPath;
    }

    /**
     * Set Variant Specification's choices as string value
     *
     * @param choicesList list of choices as one string value
     */
    public void setChoicesList(String choicesList) {
        this.choicesList = choicesList;
    }

    /**
     * Set Variant Specification's Type Internal Name
     *
     * @param typeInternalName internal name of type
     */
    public void setTypeInternalName(String typeInternalName) {
        this.typeInternalName = typeInternalName;
    }

    /**
     * Set Variant Specification's attributes as string value
     *
     * @param bmwAttributes list of attributes as one string value
     */
    public void setBmwAttributes(String bmwAttributes) {
        this.bmwAttributes = bmwAttributes;
    }

    /**
     * Set Variant Specification's state
     *
     * @param state string value of state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Set Variant Specification's filter mode
     *
     * @param filterMode string value of filter mode
     */
    public void setFilterMode(String filterMode) {
        this.filterMode = filterMode;
    }

    /**
     * Set Variant Specification's choices
     *
     * @param choices list of choices names
     */
    public void setChoices(List<String> choices) {
        this.choices = new LinkedList<>(choices);
    }

    /**
     * Gett model code
     *
     * @return model code value
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * Set model code value
     *
     * @param modelCode value of model code
     */
    @XmlTransient
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * Get list of choices names
     *
     * @return list of choices names
     */
    @XmlTransient
    public List<String> getChoices() {
        return Collections.unmodifiableList(choices);
    }
}
