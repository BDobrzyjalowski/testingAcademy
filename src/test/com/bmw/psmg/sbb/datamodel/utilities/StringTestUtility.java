package test.com.bmw.psmg.sbb.datamodel.utilities;

/**
 * Utility class to operate on a sequence of German characters.
 *
 * @author Bartlomiej Dobrzyjalowski
 * @author Karol Poliszkiewicz
 */
public final class StringTestUtility {

    private StringTestUtility() {
        throw new IllegalStateException("Cannot initialize a new utils class object - please use public static methods of this class.");
    }

    /**
     * Compares two Strings, returning true if they represent equal sequences of characters.
     * nulls are handled without exceptions. Two null references are considered to be equal. The comparison is umlaut character sensitive.
     *
     * @param str1 - the first String, may be null
     * @param str2 - the second String, may be null
     * @return true if the Strings are equal (umlaut-sensitive), or both null
     */
    public static boolean equalsWithUmlauts(String str1, String str2) {
        return replaceUmlaut(str1).equals(replaceUmlaut(str2));
    }

    /**
     * Normalize a sequence of char values and replace all german umlaute.
     * The sequence will be normalized according to the specified normalization from.
     *
     * @param str The String value to normalize.
     * @return The normalized String
     */
    private static String replaceUmlaut(String str) {
        return str.replace("\u00fc", "ue")
                .replace("\u00f6", "oe")
                .replace("\u00e4", "ae")
                .replace("\u00df", "ss")
                .replaceAll("\u00dc(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ue")
                .replaceAll("\u00d6(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Oe")
                .replaceAll("\u00c4(?=[a-z\u00e4\u00f6\u00fc\u00df ])", "Ae")
                .replace("\u00dc", "UE")
                .replace("\u00d6", "OE")
                .replace("\u00c4", "AE");
    }

}
