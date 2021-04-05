package com.bmw.psmg.sbb.utilities.loaders.expressions.jaxb;

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
 * Processes the "NmLoader" directive in the XML load file., which contains Alias Expression directives.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "NmLoader")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvAliasExpression {

    @XmlElements({@XmlElement(name = "csvBeginAlias", type = CsvBeginAlias.class), @XmlElement(name = "csvEndAlias", type = CsvEndAlias.class)})
    private List<Object> objects;

    public CsvAliasExpression() {
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

        CsvAliasExpression that = (CsvAliasExpression) o;

        return new EqualsBuilder().append(objects, that.objects).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(objects).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvAliasExpression{" + "objects=" + objects + '}';
    }

    public void setObjects(List<Object> objects) {
        this.objects =  Collections.unmodifiableList(objects);
    }
}
