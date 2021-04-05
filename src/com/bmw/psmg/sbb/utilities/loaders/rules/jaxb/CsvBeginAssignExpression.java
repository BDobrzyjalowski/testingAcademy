package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "CsvBeginAssignExpression" directive in the XML load file.
 * Create a begin assign expression object.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
public class CsvBeginAssignExpression {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginAssignExpression";

    @XmlElement(name = "csvexpression")
    private String expression = "EXP0";

    public CsvBeginAssignExpression() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    @Override
    public String toString() {
        return "CsvBeginAssignExpression{" + "expression='" + expression + '\'' + '}';
    }

}
