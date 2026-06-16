package com.exasol.cloudetl.kafka.deserialization;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.exasol.cloudetl.kafka.*;
import com.exasol.errorreporting.ExaError;

public final class JsonDeserialization {
    private JsonDeserialization() {
    }

    public static Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        for (final FieldSpecification fieldSpec : ScalaCollections.javaList(fieldSpecs)) {
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
