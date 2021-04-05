package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "CsvAddLogicalExpressionMember" directive in the XML load file.
 * Create add logical expression member object.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
@XmlType(propOrder = {"memberName", "memberNumber", "memberClass", "container"})
public class CsvAddLogicalExpressionMember {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.addLogicalExpressionMember";

    @XmlElement(name = "csvmemberName")
    private String memberName;

    @XmlElement(name = "csvmemberNumber")
    private String memberNumber = "";

    @XmlElement(name = "csvmemberClass")
    private String memberClass;

    @XmlElement(name = "csvcontainerPath")
    private String container;

    public CsvAddLogicalExpressionMember() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Initializes CsvBeginConditionalRule directive object with required fields.
     *
     * @param memberName  the logical expression member mame
     * @param memberClass the logical expression member class
     * @param container   container for logical expression member to be added
     */
    public CsvAddLogicalExpressionMember(String memberName, String memberClass, String container) {
        this.memberName = memberName;
        this.memberClass = memberClass;
        this.container = container;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvAddLogicalExpressionMember that = (CsvAddLogicalExpressionMember) o;

        return new EqualsBuilder().append(memberName, that.memberName).append(memberClass, that.memberClass).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(memberName).append(memberClass).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvAddLogicalExpressionMember{" + "container='" + container + '\'' + ", memberName='" + memberName + '\'' + ", memberClass='" + memberClass + '\'' + ", memberNumber='" + memberNumber + '\'' + '}';
    }

}
