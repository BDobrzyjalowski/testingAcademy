package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes the "CsvBeginRuleMemberAction" directive in the XML load file.
 * Create a begin rule member action object.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "NmLoader")
public class CsvBeginRuleMemberAction {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.beginRuleMemberAction";

    @XmlElement(name = "csvruleMemberAction")
    private String action = "SELECT";

    public CsvBeginRuleMemberAction() {
        // JAXB need default a no-arg constructor for marshalling XML elements.
    }

    @Override
    public String toString() {
        return "CsvBeginRuleMemberAction{" + "action='" + action + '\'' + '}';
    }

}
