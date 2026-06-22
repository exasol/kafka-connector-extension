package com.exasol.cloudetl.kafka.deserialization;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.exasol.cloudetl.kafka.*;
import com.exasol.errorreporting.ExaError;

public final class JsonDeserialization {
    private JsonDeserialization() {
    }

    @SuppressWarnings("java:S1172") // Argument properties is unused but required for consistency with other deserializations
    public static Deserializer<Map<FieldSpecification, List<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final List<FieldSpecification> fieldSpecs) {
        for (final FieldSpecification fieldSpec : fieldSpecs) {
            if (fieldSpec instanceof AllFieldsSpecification) {
                throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-16")
                        .message("Referencing all fields with key.* or value.* is not supported "
                                + "for JSON as the order is not deterministic.")
                        .mitigation("Please use specific field references for JSON, for example, value.fieldName.")
                        .toString());
            }
        }
        return new JsonDeserializer(fieldSpecs, new StringDeserializer());
    }
}
