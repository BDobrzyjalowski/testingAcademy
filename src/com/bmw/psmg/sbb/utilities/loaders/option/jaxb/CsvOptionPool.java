package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "NmLoader" directive in the XML load file., which contains OptionPool directives.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "NmLoader")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvOptionPool {

    @XmlElement(name = "csvOptionGroup")
    private Set<CsvOptionGroup> groups;

    @XmlElements({@XmlElement(name = "csvBeginOption", type = CsvBeginOption.class), @XmlElement(name = "csvBeginChoice", type = CsvBeginChoice.class), @XmlElement(name = "csvEndChoice", type =
            CsvEndChoice.class), @XmlElement(name = "csvEndOption", type = CsvEndOption.class)})
    private List<Object> objects;

    public CsvOptionPool() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    public Collection<CsvOptionGroup> getGroups() {
        return Collections.unmodifiableCollection(groups);
    }

    public void setGroups(Set<CsvOptionGroup> groups) {
        this.groups = Collections.unmodifiableSet(groups);
    }

    public Collection<Object> getObjects() {
        return Collections.unmodifiableCollection(objects);
    }

    public void setObjects(List<Object> objects) {
        this.objects = Collections.unmodifiableList(objects);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvOptionPool that = (CsvOptionPool) o;
        return Objects.equals(groups, that.groups) && Objects.equals(objects, that.objects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groups, objects);
    }

    @Override
    public String toString() {
        return "CsvOptionPool{" + "groups=" + groups + ", objects=" + objects + '}';
    }

}
