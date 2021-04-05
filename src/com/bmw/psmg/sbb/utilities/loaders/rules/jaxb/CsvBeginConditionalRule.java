package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "CsvBeginConditionalRule" directive in the XML load file.
 * Create a begin conditional rule object.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
@XmlType(propOrder = {"number", "ruleType"})
public class CsvBeginConditionalRule {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginConditionalRule";

    @XmlElement(name = "csvnumber")
    private String number;

    @XmlElement(name = "csvruleType")
    private String ruleType = "CONDITIONAL_RULE";

    public CsvBeginConditionalRule() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Initializes CsvBeginConditionalRule directive object with required fields.
     * @param number the conditional rule name
     */
    public CsvBeginConditionalRule(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvBeginConditionalRule that = (CsvBeginConditionalRule) o;

        return new EqualsBuilder().append(number, that.number).append(ruleType, that.ruleType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(number).append(ruleType).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvBeginConditionalRule{" + "number='" + number + '\'' + ", ruleType='" + ruleType + '\'' + '}';
    }

}
