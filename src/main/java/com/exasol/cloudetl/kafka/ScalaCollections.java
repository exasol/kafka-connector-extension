package com.exasol.cloudetl.kafka;

import java.util.*;

import scala.Tuple2;
import scala.jdk.javaapi.CollectionConverters;

public final class ScalaCollections {
    private ScalaCollections() {
    }

    public static <K, V> scala.collection.immutable.Map<K, V> immutableMap(final Map<K, V> values) {
        final List<Tuple2<K, V>> tuples = new ArrayList<>();
        values.forEach((key, value) -> tuples.add(new Tuple2<>(key, value)));
        return scala.collection.immutable.Map.from(CollectionConverters.asScala(tuples));
    }

    public static <K, V> java.util.Map<K, V> javaMap(final scala.collection.Map<K, V> values) {
        return CollectionConverters.asJava(values);
    }
}
