package com.exasol.cloudetl.kafka;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.*;

import com.exasol.ExaIterator;

import io.github.embeddedkafka.schemaregistry.EmbeddedKafka;
import io.github.embeddedkafka.schemaregistry.EmbeddedKafkaConfig;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class KafkaIntegrationTest {
    static final String BOOTSTRAP_SERVERS = "localhost:6001";
    static final String SCHEMA_REGISTRY_URL = "http://localhost:6002";

    String topic;
    Map<String, String> properties;

    Map<String, String> defaultProperties() {
        return Map.of("BOOTSTRAP_SERVERS", BOOTSTRAP_SERVERS, "TABLE_NAME", "exasolTable");
    }

    Map<String, String> additionalProperties() {
        return Map.of();
    }

    @BeforeAll
    void startEmbeddedKafka() {
        final var emptyMap = ScalaCollections.immutableMap(Map.<String, String>of());
        EmbeddedKafka.start(EmbeddedKafkaConfig.apply(6001, 6000, 6002, emptyMap, emptyMap, emptyMap, emptyMap));
    }

    @AfterAll
    void stopEmbeddedKafka() {
        EmbeddedKafka.stop();
    }

    @BeforeEach
    void beforeEachIntegrationTest() {
        this.topic = randomTopic();
        this.properties = new LinkedHashMap<>(defaultProperties());
        this.properties.putAll(additionalProperties());
        this.properties.put("TOPIC_NAME", this.topic);
    }

    String randomTopic() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }

    void createCustomTopic(final String topicName) {
        createCustomTopic(topicName, 1);
    }

    void createCustomTopic(final String topicName, final int partitions) {
        final Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        try (Admin admin = Admin.create(adminProperties)) {
            admin.createTopics(List.of(new NewTopic(topicName, partitions, (short) 1))).all().get();
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while creating topic " + topicName, exception);
        } catch (final ExecutionException exception) {
            throw new IllegalStateException("Failed to create topic " + topicName, exception);
        }
    }

    void publishStringToKafka(final String topicName, final String value) {
        publishToKafka(new ProducerRecord<>(topicName, value), new StringSerializer());
    }

    <T> void publishToKafka(final ProducerRecord<String, T> producerRecord, final Serializer<T> valueSerializer) {
        final Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, "consumer-group");
        try (Producer<String, T> producer = new KafkaProducer<>(producerProperties, new StringSerializer(), valueSerializer)) {
            producer.send(producerRecord);
            producer.flush();
        }
    }

    ExaIterator mockExasolIterator(final Map<String, String> params, final List<Integer> partitions,
            final List<Long> offsets) throws Exception {
        final ExaIterator mockedIterator = mock(ExaIterator.class);
        when(mockedIterator.getString(0)).thenReturn(KafkaConsumerPropertiesSupport
                .create(params).mkString());

        final Boolean[] brokers = new Boolean[partitions.size()];
        for (int index = 0; index < partitions.size(); index++) {
            brokers[index] = index < partitions.size() - 1;
        }
        when(mockedIterator.next()).thenReturn(brokers[0], Arrays.copyOfRange(brokers, 1, brokers.length));

        final Integer[] partitionIds = partitions.toArray(Integer[]::new);
        when(mockedIterator.getInteger(1)).thenReturn(partitionIds[0],
                Arrays.copyOfRange(partitionIds, 1, partitionIds.length));

        final Long[] offsetIds = offsets.toArray(Long[]::new);
        when(mockedIterator.getLong(2)).thenReturn(offsetIds[0], Arrays.copyOfRange(offsetIds, 1, offsetIds.length));

        return mockedIterator;
    }

    JsonArgumentMatcher matchesJson(final String json) {
        return new JsonArgumentMatcher(json);
    }
}
