package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "csvOptionGroup" directive in the XML load file.
 * Create a group object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvOptionGroup")
public class CsvOptionGroup {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.createGroup";

    @XmlElement(name = "csvname")
    private String name;

    @XmlElement(name = "csvoptionTypeDef")
    private String optionTypeDef;

    public CsvOptionGroup() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates "csvOptionGroup" object which will be marshalled.
     *
     * @param name          of creating group
     * @param optionTypeDef option type with which group is used
     */
    public CsvOptionGroup(String name, String optionTypeDef) {
        this.name = name;
        this.optionTypeDef = optionTypeDef;
    }

    public String getHandler() {
        return HANDLER;
    }

    public String getName() {
        return name;
    }

    public String getOptionTypeDef() {
        return optionTypeDef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvOptionGroup that = (CsvOptionGroup) o;
        return Objects.equals(name, that.name) && Objects.equals(optionTypeDef, that.optionTypeDef);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, optionTypeDef);
    }

    @Override
    public String toString() {
        return "CsvOptionGroup{" + "name='" + name + '\'' + ", optionTypeDef='" + optionTypeDef + '\'' + '}';
    }

}
