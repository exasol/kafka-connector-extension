package com.exasol.cloudetl.kafka.deserialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.*;
import com.exasol.errorreporting.ExaError;

public final class DeserializationFactory {
    private static final Map<String, RecordDeserialization> SUPPORTED_DESERIALIZATIONS = Map.of(
            "avro", AvroDeserialization::getDeserializer,
            "json", JsonDeserialization::getDeserializer,
            "string", StringDeserialization::getDeserializer);

    private DeserializationFactory() {
    }

    public static RecordDeserializers getSerializers(
            final List<GlobalFieldSpecification> fieldSpecs, final KafkaConsumerProperties kafkaProperties) {
        final List<FieldSpecification> keyFieldSpecs = new ArrayList<>();
        final List<FieldSpecification> valueFieldSpecs = new ArrayList<>();
        for (final GlobalFieldSpecification spec : fieldSpecs) {
            if (spec instanceof KeySpecification) {
                keyFieldSpecs.add((FieldSpecification) spec);
            } else if (spec instanceof ValueSpecification) {
                valueFieldSpecs.add((FieldSpecification) spec);
            }
        }
        final Deserializer<Map<FieldSpecification, List<Object>>> keyDeserializer = keyFieldSpecs.isEmpty()
                ? FieldSpecificationSingletons.ignoreKeyDeserializer()
                : getDeserialization(kafkaProperties.getRecordKeyFormat()).getDeserializer(kafkaProperties, keyFieldSpecs);
        final Deserializer<Map<FieldSpecification, List<Object>>> valueDeserializer =
                getDeserialization(kafkaProperties.getRecordValueFormat()).getDeserializer(kafkaProperties, valueFieldSpecs);
        return new RecordDeserializers(keyDeserializer, valueDeserializer);
    }

    public static RecordDeserializers getSerializers(final scala.collection.immutable.Seq<GlobalFieldSpecification> fieldSpecs,
            final KafkaConsumerProperties kafkaProperties) {
        return getSerializers(ScalaCollections.javaList(fieldSpecs), kafkaProperties);
    }

    public static RecordDeserialization getDeserialization(final String format) {
        final RecordDeserialization deserialization = SUPPORTED_DESERIALIZATIONS.get(format);
        if (deserialization == null) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-19")
                    .message("The format {{FORMAT}} is not supported.", format)
                    .mitigation("Please use one of {{SUPPORTED_FORMATS}} formats.",
                            String.join(",", SUPPORTED_DESERIALIZATIONS.keySet()))
                    .toString());
        }
        return deserialization;
    }

    public static final class RecordDeserializers {
        public final Deserializer<Map<FieldSpecification, List<Object>>> keyDeserializer;
        public final Deserializer<Map<FieldSpecification, List<Object>>> valueDeserializer;

        public RecordDeserializers(
                final Deserializer<Map<FieldSpecification, List<Object>>> keyDeserializer,
                final Deserializer<Map<FieldSpecification, List<Object>>> valueDeserializer) {
            this.keyDeserializer = keyDeserializer;
            this.valueDeserializer = valueDeserializer;
        }

        public Deserializer<Map<FieldSpecification, List<Object>>> getKeyDeserializer() {
            return this.keyDeserializer;
        }

        public Deserializer<Map<FieldSpecification, List<Object>>> getValueDeserializer() {
            return this.valueDeserializer;
        }

        @Override
        public boolean equals(final Object other) {
            if (!(other instanceof RecordDeserializers)) {
                return false;
            }
            final RecordDeserializers that = (RecordDeserializers) other;
            return Objects.equals(this.keyDeserializer, that.keyDeserializer)
                    && Objects.equals(this.valueDeserializer, that.valueDeserializer);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.keyDeserializer, this.valueDeserializer);
        }
    }
}
