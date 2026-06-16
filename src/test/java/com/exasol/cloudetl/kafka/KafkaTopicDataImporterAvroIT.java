package com.exasol.cloudetl.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

class KafkaTopicDataImporterAvroIT extends KafkaIntegrationTest {
    @Override
    Map<String, String> additionalProperties() {
        return Map.of("SCHEMA_REGISTRY_URL", SCHEMA_REGISTRY_URL);
    }

    Serializer<AvroRecord> avroSerializer() {
        final KafkaAvroSerializer serializer = new KafkaAvroSerializer();
        serializer.configure(Map.of("schema.registry.url", SCHEMA_REGISTRY_URL), false);
        @SuppressWarnings("unchecked")
        final Serializer<AvroRecord> typed = (Serializer<AvroRecord>) (Serializer<?>) serializer;
        return typed;
    }

    void publishAvro(final String topicName, final AvroRecord value) {
        publishToKafka(new ProducerRecord<>(topicName, value), avroSerializer());
    }

    void publishAvro(final String topicName, final String key, final AvroRecord value) {
        publishToKafka(new ProducerRecord<>(topicName, key, value), avroSerializer());
    }
}
