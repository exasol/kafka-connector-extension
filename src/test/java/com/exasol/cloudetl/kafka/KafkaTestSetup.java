package com.exasol.cloudetl.kafka;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaFuture;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

class KafkaTestSetup implements AutoCloseable {
    private static final Logger LOG = Logger.getLogger(KafkaTestSetup.class.getName());
    private final KafkaContainer container;

    private KafkaTestSetup(final KafkaContainer container) {
        this.container = container;
    }

    public String getBootstrapServers() {
        return this.container.getBootstrapServers() //
                .replaceAll("localhost", IntegrationTestConstants.DOCKER_IP_ADDRESS);
    }

    static KafkaTestSetup create() throws ExecutionException, InterruptedException {
        @SuppressWarnings("resource")
        final KafkaContainer kafkaContainer = new KafkaContainerTweaked(
                DockerImageName.parse("confluentinc/cp-kafka:7.4.1"))//
                .withEmbeddedZookeeper()//
                .withReuse(true);
        kafkaContainer.start();
        return new KafkaTestSetup(kafkaContainer);
    }

    public void produceTestTopicRecords(final String topicName) {
        final Producer<String, String> producer = new KafkaProducer<>(getProducerProps());
        try {
            producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(1), "OK"));
            producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(2), "WARN"));
        } finally {
            producer.close();
        }
    }

    private Properties getProducerProps() {
        final Properties producerProps = new Properties();
        producerProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.container.getBootstrapServers());
        producerProps.put("acks", "all");
        producerProps.put("retries", 0);
        producerProps.put("batch.size", 16384);
        producerProps.put("linger.ms", 1);
        producerProps.put("buffer.memory", 33554432);
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return producerProps;
    }

    void createTopic(final String topicName) {
        final Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, container.getBootstrapServers());
        try (Admin admin = Admin.create(properties)) {
            final int partitions = 1;
            final short replicationFactor = 1;
            final NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
            final CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));
            final KafkaFuture<Void> future = result.values().get(topicName);
            future.get();
            LOG.info(() -> "Successfully created topic " + topicName);
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
        } catch (final ExecutionException exception) {
            throw new IllegalStateException("Failed to create topic " + topicName + ": " + exception.getMessage(),
                    exception);
        }
    }

    @Override
    public void close() {
        this.container.close();
    }

    /**
     * This class was created because the original kafkaContainer only accepted localhost connections and was tough to
     * set up to do otherwise manually.
     * <p>
     * This is a Q&D workaround for this issue.
     */
    private static class KafkaContainerTweaked extends KafkaContainer {
        public KafkaContainerTweaked(final DockerImageName dockerImage) {
            super(dockerImage);
        }

        @Override
        public String getHost() {
            return IntegrationTestConstants.DOCKER_IP_ADDRESS;
        }
    }
}
