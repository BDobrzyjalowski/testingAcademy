package com.bmw.psmg.sbb.utilities.loaders.container.jaxb;

import com.bmw.psmg.sbb.generic.PSMGSBBConstants;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "csvProductContainer" directive in the XML load file.
 * Adds the choice object to option set object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvProductContainer")
@XmlType(propOrder = {"user", "name", "number", "description", "view", "source", "defaultUnit", "type", "containerTemplate", "organizationName", "organizationId"})
public class CsvProductContainer {

    private static final String GENERAL_PRODUCT = "General Product";
    private static final String TEMPLATE_TYPE = "separable";
    private static final String TEMPLATE_SOURCE = "make";

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "wt.part.LoadPart.createProductContainer";

    @XmlElement(name = "csvuser")
    private String user;

    @XmlElement(name = "csvname")
    private String name;

    @XmlElement(name = "csvnumber")
    private String number;

    @XmlElement(name = "csvdescription")
    private String description;

    @XmlElement(name = "csvview")
    private String view;

    @XmlElement(name = "csvsource")
    private String source;

    @XmlElement(name = "csvdefaultUnit")
    private String defaultUnit;

    @XmlElement(name = "csvtype")
    private String type;

    @XmlElement(name = "csvcontainerTemplate")
    private String containerTemplate;

    @XmlElement(name = "csvorganizationName")
    private String organizationName;

    @XmlElement(name = "csvorganizationID")
    private String organizationId;

    public CsvProductContainer() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * CsvProductContainer constructor initialize default Product Container XML schema.
     *
     * @param name - a String which contains name of Product Container
     */
    public CsvProductContainer(String name) {
        this.user = "";
        this.name = name;
        this.number = "";
        this.description = name;
        this.view = PSMGSBBConstants.Views.DESIGN;
        this.source = TEMPLATE_SOURCE;
        this.type = TEMPLATE_TYPE;
        this.defaultUnit = "";
        this.containerTemplate = GENERAL_PRODUCT;
        this.organizationName = PSMGSBBConstants.ORGANIZATION_NAME;
        this.organizationId = "";
    }

    public String getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public String getView() {
        return view;
    }

    public String getSource() {
        return source;
    }

    public String getDefaultUnit() {
        return defaultUnit;
    }

    public String getType() {
        return type;
    }

    public String getContainerTemplate() {
        return containerTemplate;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvProductContainer container = (CsvProductContainer) o;

        return new EqualsBuilder().append(user, container.user).append(name, container.name).append(number, container.number).append(description, container.description).append(view, container.view).append(source, container.source).append(defaultUnit, container.defaultUnit).append(type, container.type).append(containerTemplate, container.containerTemplate).append(organizationName, container.organizationName).append(organizationId, container.organizationId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(user).append(name).append(number).append(description).append(view).append(source).append(defaultUnit).append(type).append(containerTemplate).append(organizationName).append(organizationId).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvProductContainer{" + "user='" + user + '\'' + ", name='" + name + '\'' + ", number='" + number + '\'' + ", description='" + description + '\'' + ", view='" + view + '\'' + ", " + "source='" + source + '\'' + ", defaultUnit='" + defaultUnit + '\'' + ", type='" + type + '\'' + ", containerTemplate='" + containerTemplate + '\'' + ", organizationName='" + organizationName + '\'' + ", organizationId='" + organizationId + '\'' + '}';
    }

}
