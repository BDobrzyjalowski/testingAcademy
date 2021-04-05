package com.bmw.psmg.sbb.generic.part;

import org.apache.log4j.Logger;
import wt.fc.Persistable;
import wt.fc.ReferenceFactory;
import wt.util.WTException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assembler to produce Persistable object based on Object Identifier and reference.
 *
 * @author mlangen
 * @author Karol Poliszkiewicz
 */
public final class ReferenceAssembler {

    private static final Logger LOGGER = Logger.getLogger(ReferenceAssembler.class.getName());
    private static final Pattern SHORT_OBJECT_ID_PATTERN = Pattern.compile("([OV]R:[^:]+:\\d+)");
    private static final int START_REFERENCE_INDEX = 8;

    private final ReferenceFactory referenceFactory = new ReferenceFactory();

    /**
     * Try find Persistable object based on Object Identifier and reference.
     *
     * @param objectIdentifier the Object Identifier
     * @return Persistable object
     * @throws WTException when cannot find Persistable object reference.
     */
    public Persistable getPersistable(String objectIdentifier) throws WTException {
        LOGGER.debug("Get persistable from objectIdentifier: " + objectIdentifier);
        return referenceFactory.getReference(toShortObjectIdentifier(objectIdentifier)).getObject();
    }

    private String toShortObjectIdentifier(String objectIdentifier) {
        String longForm = objectIdentifier;
        if (longForm.startsWith("product~")) {
            objectIdentifier = "OR:" + objectIdentifier.substring(START_REFERENCE_INDEX);
        }

        Matcher standardMatcher = SHORT_OBJECT_ID_PATTERN.matcher(longForm);
        if (standardMatcher.find()) {
            LOGGER.debug("Standard matcher result: " + standardMatcher.group());
            return standardMatcher.group(1);
        }

        Matcher objectReferenceMatcher = SHORT_OBJECT_ID_PATTERN.matcher("OR:" + longForm);
        if (objectReferenceMatcher.find()) {
            LOGGER.debug("Object reference matcher result: " + standardMatcher.group());
            return objectReferenceMatcher.group(1);
        }

        String shortForm = objectIdentifier;
        if (longForm.startsWith("WCTYPE|")) {
            String[] sa = longForm.substring(7).split("[|]");
            if (sa.length > 1) {
                shortForm = "OR:" + sa[0].replaceFirst("~.*", "") + ":" + sa[1];
            }
        }
        return shortForm;
    }
}
