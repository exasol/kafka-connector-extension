package com.exasol.cloudetl.kafka;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaFuture;
import org.testcontainers.utility.DockerImageName;

//This class was created because the original kafkaContainer only accepted localhost connections and was tough to set up to do otherwise manually
//This is a Q&D workaround for this issue
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
    }

    public org.testcontainers.containers.KafkaContainer getContainer() {
        return this.container;
    }

    public String getTopicName() {
        return this.topicName;
    }

    static KafkaTestSetup create() throws ExecutionException, InterruptedException {

        @SuppressWarnings("resource")
        final org.testcontainers.containers.KafkaContainer kafkaContainer = new KafkaContainerTweaked(
                DockerImageName.parse("confluentinc/cp-kafka:7.4.1"))//
                .withEmbeddedZookeeper()//
                .withReuse(true);
        kafkaContainer.start();

        final Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaContainer.getBootstrapServers());
        final String topicName = "testTopic";
        createTopic(properties, topicName);
        return new KafkaTestSetup(kafkaContainer, topicName);
    }
    
    public void produceTestTopicRecords() {
        final Properties producerProps = new Properties();
        producerProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, this.container.getBootstrapServers());
        producerProps.put("acks", "all");
        producerProps.put("retries", 0);
        producerProps.put("batch.size", 16384);
        producerProps.put("linger.ms", 1);
        producerProps.put("buffer.memory", 33554432);
        producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        final Producer<String, String> producer = new KafkaProducer<>(producerProps);
        try {

            producer.send(new ProducerRecord<String, String>(this.topicName, Integer.toString(1), "OK"));
            producer.send(new ProducerRecord<String, String>(this.topicName, Integer.toString(2), "WARN"));

        } catch (final Exception ex) {
            LOG.warning("Exception occurred producing Kafka records: '" + ex.getMessage() + "'");
            throw ex;
        } finally {
            producer.close();
        }
    }
    private static void createTopic(final Properties properties, final String topicName) throws InterruptedException, ExecutionException {
        // CREATE TOPIC AND WAIT
        try (Admin admin = Admin.create(properties)) {
            final int partitions = 1;
            final short replicationFactor = 1;
            final NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

            final CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));

            final KafkaFuture<Void> future = result.values().get(topicName);
            future.get();
            LOG.info(() -> "Succesfully created topic " + topicName);
        } catch (final Exception ex) {
            LOG.warning("Exception occurred during Kafka topic creation: '" + ex.getMessage() + "'");
            throw ex;
        }
    }

    @Override
    public void close() {
        this.container.close();
    }

}
