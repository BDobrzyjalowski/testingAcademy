package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "NmLoader" directive in the XML load file, which contains OptionSet directives.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "NmLoader")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvOptionSet {

    @XmlElement(name = "csvBeginOptionSet")
    private CsvBeginOptionSet beginOptionSet;

    @XmlElement(name = "csvAddChoice")
    private List<CsvAddChoice> addChoices;

    @XmlElement(name = "csvEndOptionSet")
    private CsvEndOptionSet endOptionSet;

    @XmlElement(name = "csvRegisterOptionSet")
    private CsvRegisterOptionSet registerOptionSet;

    public CsvOptionSet() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    public CsvBeginOptionSet getBeginOptionSet() {
        return beginOptionSet;
    }

    public void setBeginOptionSet(CsvBeginOptionSet beginOptionSet) {
        this.beginOptionSet = beginOptionSet;
    }

    public Collection<CsvAddChoice> getAddChoices() {
        return Collections.unmodifiableCollection(addChoices);
    }

    public void setAddChoices(List<CsvAddChoice> addChoices) {
        this.addChoices = Collections.unmodifiableList(addChoices);
    }

    public CsvEndOptionSet getEndOptionSet() {
        return endOptionSet;
    }

    public void setEndOptionSet(CsvEndOptionSet endOptionSet) {
        this.endOptionSet = endOptionSet;
    }

    public CsvRegisterOptionSet getRegisterOptionSet() {
        return registerOptionSet;
    }

    public void setRegisterOptionSet(CsvRegisterOptionSet registerOptionSet) {
        this.registerOptionSet = registerOptionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvOptionSet optionSet = (CsvOptionSet) o;
        return Objects.equals(beginOptionSet, optionSet.beginOptionSet) && Objects.equals(addChoices, optionSet.addChoices) && Objects.equals(endOptionSet, optionSet.endOptionSet) && Objects.equals(registerOptionSet, optionSet.registerOptionSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginOptionSet, addChoices, endOptionSet, registerOptionSet);
    }

    @Override
    public String toString() {
        return "CsvOptionSet{" + "beginOptionSet=" + beginOptionSet + ", addChoices=" + addChoices + ", endOptionSet=" + endOptionSet + ", registerOptionSet=" + registerOptionSet + '}';
    }

}
