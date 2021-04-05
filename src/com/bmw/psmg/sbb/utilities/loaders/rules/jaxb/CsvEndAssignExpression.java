package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "csnEndAssignExpression" directive in the XML load file.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "csnEndAssignExpression")
public class CsvEndAssignExpression {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endAssignExpression";

    public String getHandler() {
        return HANDLER;
    }

}
