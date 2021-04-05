package test.com.bmw.psmg.sbb.junit.parameter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Utilities class using to simplify complex parameters tests.
 *
 * @author Karol Poliszkiewicz
 */
public final class JUnitParamsUtils {

    private JUnitParamsUtils() {
    }

    /**
     * Convert Map where key is a String and the values are a Set of Strings to Parameters (Objects) array.
     *
     * @param parameters map to convert
     * @return objects array
     */
    public static Object[] toArray(Map<String, Set<String>> parameters) {
        int index = 0;
        Object[] entries = new Object[parameters.size()];
        for (Entry<String, Set<String>> entry : parameters.entrySet()) {
            entries[index] = new Object[]{entry.getKey(), entry.getValue()};
            index++;
        }
        return entries;
    }
}
