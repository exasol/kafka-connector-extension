package com.exasol.cloudetl.kafka.deserialization;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.*;
import com.exasol.errorreporting.ExaError;

public final class StringDeserialization {
    private StringDeserialization() {
    }

    @SuppressWarnings("java:S1172") // Argument properties is unused but required for consistency with other deserializations
    public static Deserializer<Map<FieldSpecification, List<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final List<FieldSpecification> fieldSpecs) {
        for (final FieldSpecification fieldSpec : fieldSpecs) {
            if (!(fieldSpec instanceof FullRecord)) {
                throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-18")
                        .message("String deserialization can only use full record format specification 'key' or 'value'.")
                        .mitigation("Please check that record specification does not contains field selections.")
                        .toString());
            }
        }
        return new AsStringDeserializer(fieldSpecs);
    }

    @Deprecated
    public static Deserializer<Map<FieldSpecification, List<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        return getDeserializer(properties, ScalaCollections.javaList(fieldSpecs));
    }
}
