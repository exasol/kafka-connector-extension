package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

import scala.collection.immutable.Seq;

public final class TestCollections {
    private TestCollections() {
    }

    @SafeVarargs
    @SuppressWarnings("varargs")
    public static <T> Seq<T> seq(final T... values) {
        return ScalaCollections.seq(Arrays.asList(values));
    }

    public static <T> Seq<T> seq(final List<T> values) {
        return ScalaCollections.seq(values);
    }

    @SafeVarargs
    public static <K, V> scala.collection.immutable.Map<K, V> map(final Map.Entry<K, V>... entries) {
        final Map<K, V> values = new LinkedHashMap<>();
        for (final Map.Entry<K, V> entry : entries) {
            values.put(entry.getKey(), entry.getValue());
        }
        return ScalaCollections.immutableMap(values);
    }

    public static <K, V> Map.Entry<K, V> entry(final K key, final V value) {
        return new AbstractMap.SimpleImmutableEntry<>(key, value);
    }

    public static <T> List<T> javaList(final scala.collection.Iterable<T> values) {
        return ScalaCollections.javaList(values);
    }

    public static <T> List<T> javaList(final List<T> values) {
        return values;
    }

    public static <K, V> Map<K, V> javaMap(final scala.collection.Map<K, V> values) {
        return ScalaCollections.javaMap(values);
    }

    public static <K, V> Map<K, V> javaMap(final Map<K, V> values) {
        return values;
    }

    public static <T> void assertSeqEquals(final List<T> expected, final scala.collection.Iterable<T> actual) {
        assertEquals(expected, javaList(actual));
    }

    public static <T> void assertSeqEquals(final List<T> expected, final List<T> actual) {
        assertEquals(expected, actual);
    }

    public static <K, V> void assertMapOfSeqEquals(final Map<K, List<V>> expected,
            final scala.collection.Map<K, Seq<V>> actual) {
        final Map<K, List<V>> actualJava = new LinkedHashMap<>();
        javaMap(actual).forEach((key, value) -> actualJava.put(key, javaList(value)));
        assertEquals(expected, actualJava);
    }

    public static <K, V> void assertMapOfSeqEquals(final Map<K, List<V>> expected, final Map<K, List<V>> actual) {
        assertEquals(expected, actual);
    }
}
