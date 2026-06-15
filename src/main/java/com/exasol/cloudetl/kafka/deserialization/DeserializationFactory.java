package com.exasol.cloudetl.kafka.deserialization;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;
import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.errorreporting.ExaError;

import org.apache.kafka.common.serialization.Deserializer;

public final class DeserializationFactory {
    private static final Map<String, RecordDeserialization> SUPPORTED_DESERIALIZATIONS = Map.of(
            "avro", AvroDeserializationImpl::getDeserializer,
            "json", JsonDeserializationImpl::getDeserializer,
            "string", StringDeserializationImpl::getDeserializer);

    private DeserializationFactory() {
    }

    public static RecordDeserializers getSerializers(
            final scala.collection.immutable.Seq<GlobalFieldSpecification> fieldSpecs,
            final KafkaConsumerProperties kafkaProperties) {
        final java.util.List<FieldSpecification> keyFieldSpecs = new java.util.ArrayList<>();
        final java.util.List<FieldSpecification> valueFieldSpecs = new java.util.ArrayList<>();
        for (final GlobalFieldSpecification spec : ScalaCollections.javaList(fieldSpecs)) {
            if (spec instanceof KeySpecification) {
                keyFieldSpecs.add((FieldSpecification) spec);
            } else if (spec instanceof ValueSpecification) {
                valueFieldSpecs.add((FieldSpecification) spec);
            }
        }
        final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> keyDeserializer =
                keyFieldSpecs.isEmpty() ? IgnoreKeyDeserializer$.MODULE$
                        : getDeserialization(kafkaProperties.getRecordKeyFormat())
                                .getDeserializer(kafkaProperties, ScalaCollections.seq(keyFieldSpecs));
        final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> valueDeserializer =
                getDeserialization(kafkaProperties.getRecordValueFormat())
                        .getDeserializer(kafkaProperties, ScalaCollections.seq(valueFieldSpecs));
        return new RecordDeserializers(keyDeserializer, valueDeserializer);
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
        public final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> keyDeserializer;
        public final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> valueDeserializer;

        public RecordDeserializers(
                final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> keyDeserializer,
                final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> valueDeserializer) {
            this.keyDeserializer = keyDeserializer;
            this.valueDeserializer = valueDeserializer;
        }

        public Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> keyDeserializer() {
            return this.keyDeserializer;
        }

        public Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> valueDeserializer() {
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
