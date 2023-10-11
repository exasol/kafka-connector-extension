package com.exasol.cloudetl.kafka;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaFuture;
import org.testcontainers.utility.DockerImageName;

class KafkaContainerTweaked extends org.testcontainers.containers.KafkaContainer {
    public KafkaContainerTweaked(final DockerImageName dockerImageName) {
        super(dockerImageName);
    }

    @Override
    public String getHost() {
        return "172.17.0.1";
    }
}

class KafkaTestSetup implements AutoCloseable {

    private static final Logger LOG = Logger.getLogger(KafkaTestSetup.class.getName());
    private final String topicName;

    org.testcontainers.containers.KafkaContainer container;

    private KafkaTestSetup(final org.testcontainers.containers.KafkaContainer container, final String topicName) {
        this.container = container;
        this.topicName = topicName;
        // this.client = client;
    }

    public org.testcontainers.containers.KafkaContainer getContainer() {
        return this.container;
    }

    public String getTopicName() {
        return this.topicName;
    }

    private static final int ZOOKEEPER_PORT = 2181;
    private static final int KAFKA_EXTERNAL_PORT = 29092;
    private static final int SCHEMA_REGISTRY_PORT = 8081;
    private static final int ADMIN_TIMEOUT_MILLIS = 5000;

    static KafkaTestSetup create() throws ExecutionException, InterruptedException {

        @SuppressWarnings("resource")
        final org.testcontainers.containers.KafkaContainer kafkaContainer = new KafkaContainerTweaked(
                DockerImageName.parse("confluentinc/cp-kafka:7.4.1"))//
                .withEmbeddedZookeeper()//
                .withReuse(true);
        kafkaContainer.start();
        // return String.format("PLAINTEXT://%s:%s", this.getHost(), this.getMappedPort(9093));
        // container.start();

        final Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        final String topicName = "testTopic";
        LOG.info("Topicname: " + topicName);
        // CREATE TOPIC AND WAIT
        try (Admin admin = Admin.create(properties)) {
            final int partitions = 1;
            final short replicationFactor = 1;
            final NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

            final CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));

            final KafkaFuture<Void> future = result.values().get(topicName);
            future.get();

            final Set<String> topicNames = admin.listTopics().names().get();

            LOG.info("Succesfully created topic");
        } catch (final Exception ex) {
            LOG.warning("Exception occurred during Kafka topic creation: '" + ex.getMessage() + "'");
        }
        // PRODUCE
        final Properties producerProps = new Properties();
        producerProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        producerProps.put("acks", "all");
        producerProps.put("retries", 0);
        producerProps.put("batch.size", 16384);
        producerProps.put("linger.ms", 1);
        producerProps.put("buffer.memory", 33554432);
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        final Producer<String, String> producer = new KafkaProducer<>(producerProps);
        try {

            // for (int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(1), "OK"));
            producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(2), "WARN"));

        } catch (final Exception ex) {
            LOG.warning("Exception occurred producing Kafka records: '" + ex.getMessage() + "'");
        } finally {
            producer.close();
        }
        // RETURN kafkaTestSetup object
        return new KafkaTestSetup(kafkaContainer, topicName);
    }

    @Override
    public void close() {
        // this.client.shutdown();
        this.container.close();
    }

}
