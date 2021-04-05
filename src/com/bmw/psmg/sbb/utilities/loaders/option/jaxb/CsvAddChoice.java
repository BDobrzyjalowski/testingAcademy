package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "csvAddChoice" directive in the XML load file.
 * Adds the choice object to option set object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvAddChoice")
public class CsvAddChoice {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.addChoice";

    @XmlElement(name = "csvname")
    private String name;

    @XmlElement(name = "csvcontainerPath")
    private String containerPath;

    public CsvAddChoice() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates "csvAddChoice" object which will be marshalled.
     *
     * @param name          of choice which will be added to OptionSet
     * @param containerPath container of choice to search for choice to be added
     * @see CsvOptionSet
     */
    public CsvAddChoice(String name, String containerPath) {
        this.name = name;
        this.containerPath = containerPath;
    }

    public String getHandler() {
        return HANDLER;
    }

    public String getName() {
        return name;
    }

    public String getContainerPath() {
        return containerPath;
    }

}
