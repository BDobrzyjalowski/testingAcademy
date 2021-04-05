package com.bmw.psmg.sbb.utilities.loaders.expressions.jaxb;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Processes the "CsvBeginAlias" directive in the XML load file.
 * Create a alias expression csv object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvBeginChoice")
@XmlType(propOrder = {"typeDef", "name", "expression", "number", "description", "containerPath", "folder", "lifecycleTemplate", "lifecycleState"})
public class CsvBeginAlias {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginAlias";

    @XmlElement(name = "csvtypedef")
    private String typeDef = "com.ptc.windchill.option.model.ExpressionAlias";

    @XmlElement(name = "csvname")
    private String name;

    @XmlElement(name = "csvexpressionString")
    private String expression;

    @XmlElement(name = "csvnumber")
    private String number;

    @XmlElement(name = "csvdescription")
    private String description;

    @XmlElement(name = "csvcontainerPath")
    private String containerPath;

    @XmlElement(name = "csvfolder")
    private String folder ="";

    @XmlElement(name = "csvlifecycletemplate")
    private String lifecycleTemplate = "Default";

    @XmlElement(name = "csvlifecyclestate")
    private String lifecycleState = "INWORK";

    public CsvBeginAlias() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Initializes CsvBeginAlias directive object with required fields.
     * @param name the alias expression name
     * @param expression the value of the expression
     * @param containerPath path to container
     */
    public CsvBeginAlias(String name, String expression, String containerPath) {
        this.name = name;
        this.number = name;
        this.description = name;
        this.expression = expression;
        this.containerPath = containerPath;
    }

    /**
     * Initializes CsvBeginAlias directive object with required fields.
     * @param typeDef the alias expression's type definition
     * @param name the alias expression's name
     * @param number the alias expression's number
     * @param description the alias expression's description
     * @param expression the value of the expression
     * @param containerPath path to container
     */
    public CsvBeginAlias(String typeDef, String name, String number, String description, String expression, String containerPath) {
        this.typeDef = typeDef;
        this.name = name;
        this.number = number;
        this.description = description;
        this.expression = expression;
        this.containerPath = containerPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CsvBeginAlias that = (CsvBeginAlias) o;

        return new EqualsBuilder().append(typeDef, that.typeDef).append(name, that.name).append(expression, that.expression).append(number, that.number).append(description, that.description).append(containerPath, that.containerPath).append(folder, that.folder).append(lifecycleTemplate, that.lifecycleTemplate).append(lifecycleState, that.lifecycleState).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(typeDef).append(name).append(expression).append(number).append(description).append(containerPath).append(folder).append(lifecycleTemplate).append(lifecycleState).toHashCode();
    }

    @Override
    public String toString() {
        return "CsvBeginAlias{" + "typeDef='" + typeDef + '\'' + ", name='" + name + '\'' + ", expression='" + expression + '\'' + ", number='" + number + '\'' + ", description='" + description + '\'' + ", containerPath='" + containerPath + '\'' + ", folder='" + folder + '\'' + ", lifecycleTemplate='" + lifecycleTemplate + '\'' + ", lifecycleState='" + lifecycleState + '\'' + '}';
    }

}
