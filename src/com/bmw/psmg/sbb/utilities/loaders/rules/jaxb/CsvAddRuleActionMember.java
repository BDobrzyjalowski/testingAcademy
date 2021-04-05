package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "CsvAddRuleActionMember" directive in the XML load file.
 * Create add rule action member object.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
@XmlType(propOrder = {"memberName", "memberClass", "container"})
public class CsvAddRuleActionMember {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.addRuleActionMember";

    @XmlElement(name = "csvmemberName")
    private String memberName;

    @XmlElement(name = "csvmemberClass")
    private String memberClass;

    @XmlElement(name = "csvcontainerPath")
    private String container;

    public CsvAddRuleActionMember() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Initializes CsvBeginConditionalRule directive object with required fields.
     * @param memberName  the logical expression member mame
     * @param memberClass the logical expression member class
     * @param container   container for logical expression member to be added     */
    public CsvAddRuleActionMember(String memberName, String memberClass, String container) {
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

        CsvAddRuleActionMember that = (CsvAddRuleActionMember) o;

        return new EqualsBuilder().append(memberName, that.memberName).append(memberClass, that.memberClass).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(memberName).append(memberClass).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvAddRuleActionMember{" + "memberName='" + memberName + '\'' + ", memberClass='" + memberClass + '\'' + '}';
    }

}
