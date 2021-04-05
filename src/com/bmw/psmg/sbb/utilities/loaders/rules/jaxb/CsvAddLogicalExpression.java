package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "CsvAddLogicalExpression" directive in the XML load file.
 * Create a add logical expression object.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
@XmlType(propOrder = {"number", "expression", "description"})
public class CsvAddLogicalExpression {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.addLogicalExpression";

    @XmlElement(name = "csvnumber")
    private String number= "EXP0";

    @XmlElement(name = "csvexpression")
    private String expression;

    @XmlElement(name = "csvdescription")
    private String description;

    public CsvAddLogicalExpression() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Initializes CsvBeginConditionalRule directive object with required fields.
     * @param expression the conditional expression
     * @param description the conditional description
     */
    public CsvAddLogicalExpression(String description, String expression) {
        this.description = description;
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvAddLogicalExpression that = (CsvAddLogicalExpression) o;

        return new EqualsBuilder().append(description, that.description).append(expression, that.expression).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(description).append(expression).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvAddLogicalExpression{" + "number='" + number + '\''+ ", description='" + description + '\'' + ", expression='" + expression + '\'' + '}';
    }

}
