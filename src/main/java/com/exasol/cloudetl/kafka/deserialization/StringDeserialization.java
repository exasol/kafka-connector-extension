package com.exasol.cloudetl.kafka.deserialization;

import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.*;
import com.exasol.errorreporting.ExaError;

public final class StringDeserialization {
    private StringDeserialization() {
    }

    public static Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        for (final FieldSpecification fieldSpec : ScalaCollections.javaList(fieldSpecs)) {
            if (!(fieldSpec instanceof FullRecord)) {
                throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-18")
                        .message("String deserialization can only use full record format specification 'key' or 'value'.")
                        .mitigation("Please check that record specification does not contains field selections.")
                        .toString());
            }
        }
        return new AsStringDeserializer(fieldSpecs);
    }
}
