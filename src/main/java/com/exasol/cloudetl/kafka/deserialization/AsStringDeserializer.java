package com.exasol.cloudetl.kafka.deserialization;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import com.exasol.cloudetl.kafka.ScalaCollections;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class AsStringDeserializer implements Deserializer<Map<FieldSpecification, List<Object>>> {
    private final List<FieldSpecification> fieldSpecs;
    private final StringDeserializer deserializer = new StringDeserializer();

    public AsStringDeserializer(final List<FieldSpecification> fieldSpecs) {
        this.fieldSpecs = fieldSpecs;
    }

    public AsStringDeserializer(final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        this(ScalaCollections.javaList(fieldSpecs));
    }

    @Override
    public Map<FieldSpecification, List<Object>> deserialize(final String topic, final byte[] data) {
        final Map<FieldSpecification, List<Object>> result = new LinkedHashMap<>();
        final Object value = this.deserializer.deserialize(topic, data);
        for (final FieldSpecification fieldSpec : this.fieldSpecs) {
            result.put(fieldSpec, Collections.singletonList(value));
        }
        return result;
    }
}
