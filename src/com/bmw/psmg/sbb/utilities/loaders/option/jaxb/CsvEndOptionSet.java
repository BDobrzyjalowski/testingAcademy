package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes csvEndOptionSet directive loader xml.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvEndOptionSet")
public class CsvEndOptionSet {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endCreateOptionSet";

    public String getHandler() {
        return HANDLER;
    }

}
