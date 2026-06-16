package com.exasol.cloudetl.kafka.deserialization;

import java.util.LinkedHashMap;
import java.util.Map;

import com.exasol.cloudetl.kafka.ScalaCollections;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class AsStringDeserializer
        implements Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> {
    private final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs;
    private final StringDeserializer deserializer = new StringDeserializer();

    public AsStringDeserializer(final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        this.fieldSpecs = fieldSpecs;
    }

    @Override
    public scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> deserialize(
            final String topic, final byte[] data) {
        final Map<FieldSpecification, scala.collection.immutable.Seq<Object>> result = new LinkedHashMap<>();
        final Object value = this.deserializer.deserialize(topic, data);
        for (final FieldSpecification fieldSpec : ScalaCollections.javaList(this.fieldSpecs)) {
            result.put(fieldSpec, ScalaCollections.seqOf(value));
        }
        return ScalaCollections.immutableMap(result);
    }
}
