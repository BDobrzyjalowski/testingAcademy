package com.bmw.psmg.sbb.utilities.loaders.rules.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Processes "csvEndRuleMemberAction" directive loader xml.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
@XmlRootElement(name = "csvEndRuleMemberAction")
public class CsvEndRuleMemberAction {

    @XmlAttribute(name = "handler")
    private static final String HANDLER = "com.ptc.windchill.option.load.Loader.endRuleMemberAction";

    public String getHandler() {
        return HANDLER;
    }

}
