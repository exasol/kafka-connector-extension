package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public final class TestCollections {
    private TestCollections() {
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> List<T> seq(final T... values) {
        return Arrays.asList(values);
    }

    public static <T> List<T> seq(final List<T> values) {
        return values;
    }

    @SafeVarargs
    public static <K, V> Map<K, V> map(final Map.Entry<K, V>... entries) {
        final Map<K, V> values = new LinkedHashMap<>();
        for (final Map.Entry<K, V> entry : entries) {
            values.put(entry.getKey(), entry.getValue());
        }
        return values;
    }

    public static <K, V> Map.Entry<K, V> entry(final K key, final V value) {
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    public static <T> List<T> javaList(final List<T> values) {
        return values;
    }

    public static <K, V> Map<K, V> javaMap(final Map<K, V> values) {
        return values;
    }

    public static <T> void assertSeqEquals(final List<T> expected, final List<T> actual) {
        assertEquals(expected, actual);
    }

    public static <K, V> void assertMapOfSeqEquals(final Map<K, List<V>> expected, final Map<K, List<V>> actual) {
        assertEquals(expected, actual);
    }
}
