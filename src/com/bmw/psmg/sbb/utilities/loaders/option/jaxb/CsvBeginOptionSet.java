package com.bmw.psmg.sbb.utilities.loaders.option.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "csvBeginOptionSet" directive in the XML load file.
 * Create a option set object.
 *
 * @author Karol Poliszkiewicz
 */
@XmlRootElement(name = "csvBeginOptionSet")
public class CsvBeginOptionSet {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginCreateOptionSet";

    @XmlElement(name = "csvname")
    private String name;

    @XmlElement(name = "csvdescription")
    private String description;

    @XmlElement(name = "csvsupportExclusionOptionChoices")
    private boolean supportExclusionOptionChoices = false;

    @XmlElement(name = "csvsupportItemOptionChoices")
    private boolean supportItemOptionChoices = true;

    @XmlElement(name = "csvsupportEffectivityOnOptionChoices")
    private boolean supportEffectivityOnOptionChoices = true;

    @XmlElement(name = "csvreferenceOptionPoolRulesAutomatically")
    private boolean referenceOptionPoolRulesAutomatically = true;

    @XmlElement(name = "csvmanageLocalRuleChangesIndependently")
    private boolean manageLocalRuleChangesIndependently = false;

    public CsvBeginOptionSet() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    /**
     * Creates "csvBeginOptionSet" object which will be marshalled.
     *
     * @param name        of OptionSet
     * @param description of created OptionSet
     */
    public CsvBeginOptionSet(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getHandler() {
        return HANDLER;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSupportExclusionOptionChoices() {
        return supportExclusionOptionChoices;
    }

    public boolean isSupportItemOptionChoices() {
        return supportItemOptionChoices;
    }

    public boolean isSupportEffectivityOnOptionChoices() {
        return supportEffectivityOnOptionChoices;
    }

    public boolean isReferenceOptionPoolRulesAutomatically() {
        return referenceOptionPoolRulesAutomatically;
    }

    public boolean isManageLocalRuleChangesIndependently() {
        return manageLocalRuleChangesIndependently;
    }

}
