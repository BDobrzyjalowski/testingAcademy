package com.bmw.psmg.sbb.generic;

import com.ptc.core.htmlcomp.util.TypeHelper;
import com.ptc.core.meta.common.TypeIdentifier;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import wt.session.SessionHelper;
import wt.type.Typed;
import wt.type.TypedUtility;
import wt.util.WTException;

import java.util.Locale;

/**
 * Utility class containing useful methods for operations on soft types.
 *
 * @author Bartlomiej Dobrzyjalowski
 */
public class PSMGSBBSoftTypeUtility {

    private static final Logger LOGGER = Logger.getLogger(PSMGSBBSoftTypeUtility.class.getName());

    private PSMGSBBSoftTypeUtility() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Get Internal name of Object type(short or long version) using only Typed object.
     * <p>
     * Example :
     * For MMG Material object:
     * short version looks : "com.bmw.psmg.sbb.MaterialMMG"
     * long version looks : "WCTYPE|wt.part.WTPart|com.bmw.psmg.sbb.Material|com.bmw.psmg.sbb.MaterialMMG"
     *
     * @param typed      object which internal name we want to get
     * @param longFormat true for long format of internal name, false for short format of type
     * @return internal name of type in wanted option
     */
    public static String getTypeString(Typed typed, boolean longFormat) {
        String typeId = TypedUtility.getTypeIdentifier(typed).toExternalForm();
        if (longFormat) {
            return typeId;
        }
        if (typeId != null && typeId.lastIndexOf('|') > 0) {
            typeId = typeId.substring(typeId.lastIndexOf('|') + 1, typeId.length());
        }
        if (typeId == null) {
            typeId = "";
        }
        return typeId;
    }

    /**
     * Gets Display Name of a given object's type
     *
     * @param typed typed object
     * @return display name of a type
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public static String getTypeDisplayName(Typed typed) throws WTException {
        Locale locale = SessionHelper.getLocale();
        return getTypeDisplayName(typed, locale);
    }

    /**
     * Gets Display Name of a given object's type
     *
     * @param typed  typed object
     * @param locale localization identifier
     * @return display name of a type
     * @throws WTException thrown when a descriptor of the given object's type cannot be obtained.
     */
    public static String getTypeDisplayName(Typed typed, Locale locale) throws WTException {
        TypeIdentifier typeId = TypedUtility.getTypeIdentifier(typed);
        return TypeHelper.getTypeIdentifierDisplayName(typeId, locale);
    }

    /**
     * Compare the internal name type with a type of Typed object.
     *
     * @param typed        - object implementing the Typed interface
     * @param internalName - expected internal name of soft type
     * @return wen the typed object's type equals with the internal name return true, otherwise return false.
     */
    public static boolean hasSoftType(Typed typed, String internalName) {
        boolean equals = false;
        if (typed != null && StringUtils.isNotEmpty(internalName)) {
            String type = getTypeString(typed, false);
            LOGGER.debug(String.format("Typed Object is '%s'", type));
            equals = internalName.equalsIgnoreCase(type);
        }
        return equals;
    }

}
