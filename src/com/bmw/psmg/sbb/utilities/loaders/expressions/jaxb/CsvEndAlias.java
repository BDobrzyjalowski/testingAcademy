package com.bmw.psmg.sbb.utilities.loaders.expressions.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes "csvEndAlias" directive loader xml.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvEndAlias")
public class CsvEndAlias {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endAlias";

    public String getHandler() {
        return HANDLER;
    }

}
