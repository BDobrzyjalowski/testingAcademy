package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "csvBeginChoice" directive in the XML load file.
 * Create a choice object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvBeginChoice")
@XmlType(propOrder = {"name", "description", "option", "typeDef", "number"})
public class CsvBeginChoice {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginChoice";

    @XmlElement(name = "csvtypedef")
    private String typeDef;

    @XmlElement(name = "csvname")
    private String name;

    @XmlElement(name = "csvnumber")
    private String number;

    @XmlElement(name = "csvdescription")
    private String description;

    @XmlElement(name = "csvoption")
    private String option;

    public CsvBeginChoice() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates "csvBeginOptionSet" object which will be marshalled.
     *
     * @param name        of choice
     * @param description of choice
     * @param typeDef     choice type to be created
     * @param option      for choice
     */
    public CsvBeginChoice(String name, String description, String typeDef, String option) {
        this.name = name;
        this.number = option;
        this.description = description;
        this.typeDef = typeDef;
        this.option = option;
    }

    /**
     * Creates "csvBeginOptionSet" object which will be marshalled.
     *
     * @param name        of choice
     * @param number      of choice
     * @param description of choice
     * @param typeDef     choice type to be created
     * @param option      for choice
     */
    public CsvBeginChoice(String name, String number, String description, String typeDef, String option) {
        this.name = name;
        this.number = number;
        this.description = description;
        this.typeDef = typeDef;
        this.option = option;
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

    public String getOption() {
        return option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvBeginChoice that = (CsvBeginChoice) o;
        return Objects.equals(typeDef, that.typeDef) && Objects.equals(name, that.name) && Objects.equals(number, that.number) && Objects.equals(description, that.description) && Objects.equals(option, that.option);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeDef, name, number, description, option);
    }

    @Override
    public String toString() {
        return "CsvBeginChoice{" + "typeDef='" + typeDef + '\'' + ", name='" + name + '\'' + ", number='" + number + '\'' + ", description='" + description + '\'' + ", option='" + option + '\'' + '}';
    }

}
