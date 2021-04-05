package com.bmw.psmg.sbb.utilities.loaders.variantspecification.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "NmLoader" directive in the XML load file, which contains csvBmwVariantSpec directives.
 */
@XmlRootElement(name = "NmLoader")
@XmlAccessorType(XmlAccessType.FIELD)
public class CsvVariantSpecLoader {

    @XmlElement(name = "csvBmwVariantSpec")
    private List<CsvBmwVariantSpec> variantSpecs;

    public CsvVariantSpecLoader() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates object with required set of csvBmwVariantSpec objects
     *
     * @param variantSpecs set of variant spec models compatible with loader
     */
    public CsvVariantSpecLoader(List<CsvBmwVariantSpec> variantSpecs) {
        this.variantSpecs = new ArrayList<>(variantSpecs);
    }
}
