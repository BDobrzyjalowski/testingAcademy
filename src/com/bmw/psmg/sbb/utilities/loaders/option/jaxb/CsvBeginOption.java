package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "csvBeginOption" directive in the XML load file. Create a option object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvBeginOption")
@XmlType(propOrder = { "typeDef", "name", "description", "optionDataType", "group", "selection", "number", "choices" })
public class CsvBeginOption {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginOption";

    @XmlElement(name = "csvtypedef")
    private String typeDef;

    @XmlElement(name = "csvnumber")
    private String number;

    @XmlElement(name = "csvdescription")
    private String description;

    @XmlElement(name = "csvoptionGroup")
    private String group;

    @XmlElement(name = "csvsingleChoiceSelection")
    private boolean selection = false;

    @XmlElement(name = "csvautoCreateBooleanChoices")
    private boolean choices = false;

    @XmlElement(name = "csvoptionDataType")
    private String optionDataType = "STRING";

    @XmlElement(name = "csvname")
    private String name;

    public CsvBeginOption() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates "csvBeginOption" object which will be marshalled.
     *
     * @param name of option
     * @param description of option
     * @param typeDef option type to be created
     * @param group name of option group
     */
    public CsvBeginOption(String name, String number, String description, String typeDef, boolean isSingleChoice, String group, String optionDataType) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.typeDef = typeDef;
        this.selection = isSingleChoice;
        this.group = group;
        this.optionDataType = optionDataType;
    }

    public String getHandler() {
        return HANDLER;
    }

    public String getTypeDef() {
        return typeDef;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getOptionDataType() {
        return optionDataType;
    }

    public String getGroup() {
        return group;
    }

    public boolean isSelection() {
        return selection;
    }

    public boolean isChoices() {
        return choices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvBeginOption that = (CsvBeginOption) o;

        return new EqualsBuilder().append(optionDataType, that.optionDataType).append(choices, that.choices).append(selection, that.selection).append(typeDef, that.typeDef).append(name, that.name).append(number, that.number).append(description, that.description).append(group, that.group).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(typeDef).append(name).append(number).append(description).append(group).append(selection).append(choices).append(optionDataType).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvBeginOption{" + "typeDef='" + typeDef + '\'' + ", name='" + name + '\'' + ", number='" + number + '\'' + ", description='" + description + '\'' + ", group='" + group + '\'' + ", " + "selection=" + selection + ", choices='" + choices + "', optionDataType='" + optionDataType + '}';
    }
}
