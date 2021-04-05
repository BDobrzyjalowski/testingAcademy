package com.bmw.psmg.sbb.utilities.loaders.container.jaxb;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "NmLoader" directive in the XML load file, which contains CsvProductContainer directives.
 */
@XmlRootElement(name = "NmLoader")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvContainerLoader {

    @XmlElement(name = "csvProductContainer")
    private CsvProductContainer container;

    @XmlElement(name = "csvAddPrincipalToRole")
    private Set<CsvAddPrincipalToRole> principals;

    public CsvContainerLoader() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    public CsvProductContainer getContainer() {
        return container;
    }

    public void setContainer(CsvProductContainer container) {
        this.container = container;
    }

    public Collection<CsvAddPrincipalToRole> getPrincipals() {
        return Collections.unmodifiableCollection(principals);
    }

    public void setPrincipals(Set<CsvAddPrincipalToRole> principals) {
        this.principals = Collections.unmodifiableSet(principals);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvContainerLoader that = (CsvContainerLoader) o;
        return Objects.equals(container, that.container) && Objects.equals(principals, that.principals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(container, principals);
    }

    @Override
    public String toString() {
        return "CsvContainerLoader{" + "container=" + container + ", principals=" + principals + '}';
    }

}
