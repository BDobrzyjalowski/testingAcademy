package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "csnEndLogicalExpression" directive in the XML load file.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "csnEndLogicalExpression")
public class CsvEndLogicalExpression {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endLogicalExpression";

    public String getHandler() {
        return HANDLER;
    }

}
