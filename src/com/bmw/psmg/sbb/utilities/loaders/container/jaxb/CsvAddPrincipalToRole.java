package com.bmw.psmg.sbb.utilities.loaders.container.jaxb;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "csvContainer" directive in the XML load file.
 * Adds the choice object to option set object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvAddPrincipalToRole")
@XmlType(propOrder = {"containerClass", "containerName", "role", "principalNameOrDN", "serviceName", "useSharedTeam"})
public class CsvAddPrincipalToRole {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "wt.inf.team.LoadContainerTeam.addPrincipalToRole";

    @XmlElement(name = "csvcontainerClass")
    private String containerClass;

    @XmlElement(name = "csvcontainerName")
    private String containerName;

    @XmlElement(name = "csvrole")
    private String role;

    @XmlElement(name = "csvprincipalNameOrDN")
    private String principalNameOrDN;

    @XmlElement(name = "csvserviceName")
    private String serviceName;

    @XmlElement(name = "csvuseSharedTeam")
    private String useSharedTeam;

    public CsvAddPrincipalToRole() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * CsvAddPrincipalToRole constructor initialize default Add Principal To Role XML schema.
     *
     * @param principalNameOrDN - a String which contains name of principal Or DN
     * @param role              - a String which contains name of principal role
     * @param containerName     - a String which contains name of Product Container
     */
    public CsvAddPrincipalToRole(String principalNameOrDN, String role, String containerName) {
        this.containerName = containerName;
        this.role = role;
        this.principalNameOrDN = principalNameOrDN;
        this.containerClass = "wt.pdmlink.PDMLinkProduct";
        this.serviceName = "";
        this.useSharedTeam = "";
    }

    public String getContainerClass() {
        return containerClass;
    }

    public String getContainerName() {
        return containerName;
    }

    public String getRole() {
        return role;
    }

    public String getPrincipalNameOrDN() {
        return principalNameOrDN;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getUseSharedTeam() {
        return useSharedTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CsvAddPrincipalToRole that = (CsvAddPrincipalToRole) o;
        return Objects.equals(containerClass, that.containerClass) && Objects.equals(containerName, that.containerName) && Objects.equals(role, that.role) && Objects.equals(principalNameOrDN,
                that.principalNameOrDN) && Objects.equals(serviceName, that.serviceName) && Objects.equals(useSharedTeam, that.useSharedTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(containerClass, containerName, role, principalNameOrDN, serviceName, useSharedTeam);
    }

    @Override
    public String toString() {
        return "CsvAddPrincipalToRole{" + "containerClass='" + containerClass + '\'' + ", containerName='" + containerName + '\'' + ", role='" + role + '\'' + ", principalNameOrDN='" + principalNameOrDN + '\'' + ", serviceName='" + serviceName + '\'' + ", useSharedTeam='" + useSharedTeam + '\'' + '}';
    }

}
