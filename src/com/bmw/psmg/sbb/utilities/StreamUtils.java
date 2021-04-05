package com.bmw.psmg.sbb.utilities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Util class for easier handling functionalities inside stream
 */
public class StreamUtils {

    private StreamUtils() {
        throw new UnsupportedOperationException("Class contains only static methods");
    }

    /**
     * Method to be appended into filter - together it will return stream of objects unique by the key passed as argument
     *
     * @param keyExtractor key by which method validates uniqueness
     * @return predicate
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
