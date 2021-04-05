package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes "csvEndConditionalRule" directive loader xml.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "csvEndConditionalRule")
public class CsvEndConditionalRule {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endConditionalRule";

    public String getHandler() {
        return HANDLER;
    }

}
