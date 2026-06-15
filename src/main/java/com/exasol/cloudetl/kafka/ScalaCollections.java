package com.exasol.cloudetl.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.jdk.javaapi.CollectionConverters;

public final class ScalaCollections {
    private ScalaCollections() {
    }

    public static <T> Seq<T> seq(final List<T> values) {
        return scala.collection.immutable.Seq.from(CollectionConverters.asScala(values));
    }

    public static <T> scala.collection.immutable.List<T> list(final List<T> values) {
        return scala.collection.immutable.List.from(CollectionConverters.asScala(values));
    }

    @SafeVarargs
    public static <T> Seq<T> seqOf(final T... values) {
        final List<T> list = new ArrayList<>();
        for (final T value : values) {
            list.add(value);
        }
        return seq(list);
    }

    public static <K, V> scala.collection.immutable.Map<K, V> immutableMap(final Map<K, V> values) {
        final List<Tuple2<K, V>> tuples = new ArrayList<>();
        values.forEach((key, value) -> tuples.add(new Tuple2<>(key, value)));
        return scala.collection.immutable.Map.from(CollectionConverters.asScala(tuples));
    }

    public static <K, V> java.util.Map<K, V> javaMap(final scala.collection.Map<K, V> values) {
        return CollectionConverters.asJava(values);
    }

    public static <T> List<T> javaList(final scala.collection.Iterable<T> values) {
        final List<T> result = new ArrayList<>();
        final scala.collection.Iterator<T> iterator = values.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }
}
