package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes "csvEndChoice" directive loader xml.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvEndChoice")
public class CsvEndChoice {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endChoice";

    public String getHandler() {
        return HANDLER;
    }

}
