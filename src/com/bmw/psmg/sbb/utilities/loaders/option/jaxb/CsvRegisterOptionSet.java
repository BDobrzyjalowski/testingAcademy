package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "csvRegisterOptionSet" directive in the XML load file.
 * Registers a option set with a container.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvRegisterOptionSet")
public class CsvRegisterOptionSet {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.registerOptionSet";

    @XmlElement(name = "csvname")
    private String name;

    public CsvRegisterOptionSet() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates "csvRegisterOptionSet" object which will be marshalled.
     *
     * @param name of the registered OptionSet
     */
    public CsvRegisterOptionSet(String name) {
        this.name = name;
    }

    public String getHandler() {
        return HANDLER;
    }

    public String getName() {
        return name;
    }

}
