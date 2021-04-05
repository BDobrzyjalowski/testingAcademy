package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collections;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "NmLoader" directive in the XML load file., which contains Conditional Rule directives.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvConditionalRule {

    @XmlElements({
            @XmlElement(name = "csvBeginConditionalRule", type = CsvBeginConditionalRule.class),
            @XmlElement(name = "csvBeginAssignExpression", type = CsvBeginAssignExpression.class),
            @XmlElement(name = "csvAddLogicalExpression", type = CsvAddLogicalExpression.class),
            @XmlElement(name = "csvAddLogicalExpressionMember", type = CsvAddLogicalExpressionMember.class),
            @XmlElement(name = "csvEndLogicalExpression", type = CsvEndLogicalExpression.class),
            @XmlElement(name = "csvEndAssignExpression", type = CsvEndAssignExpression.class),
            @XmlElement(name = "csvBeginRuleMemberAction", type = CsvBeginRuleMemberAction.class),
            @XmlElement(name = "csvAddRuleActionMember", type = CsvAddRuleActionMember.class),
            @XmlElement(name = "csvEndRuleMemberAction", type = CsvEndRuleMemberAction.class),
            @XmlElement(name = "csvEndConditionalRule", type = CsvEndConditionalRule.class)
    })
    private List<Object> objects;

    public CsvConditionalRule() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvConditionalRule that = (CsvConditionalRule) o;

        return new EqualsBuilder().append(objects, that.objects).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(objects).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvConditionalRule{" + "objects=" + objects + '}';
    }

    public void setObjects(List<Object> objects) {
        this.objects =  Collections.unmodifiableList(objects);
    }
}
