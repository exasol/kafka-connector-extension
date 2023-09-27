package com.exasol.cloudetl.kafka;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.KafkaFuture;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

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

        final DockerImageName dockerImageName = DockerImageName.parse("confluentinc/cp-kafka:latest");
        @SuppressWarnings("resource")
        final GenericContainer<?> zookeeperContainer = new GenericContainer<>("confluentinc/cp-zookeeper:7.4.1")
                // .withNetwork(kafka.getNetwork())
                // .withNetworkAliases("zookeeper")
                // .withEnv("ZOOKEEPER_CLIENT_PORT", "2181");
                .withExposedPorts(ZOOKEEPER_PORT)// ;
                .withEnv("ZOOKEEPER_CLIENT_PORT", Integer.toString(ZOOKEEPER_PORT));
        // c.withReuse(true)
        zookeeperContainer.start();

        final String zookeeperConnString = "172.17.0.1" + ":" + zookeeperContainer.getMappedPort(ZOOKEEPER_PORT);
        @SuppressWarnings("resource") // Container will be stopped in close() method
        final org.testcontainers.containers.KafkaContainer kafkaContainer = new KafkaContainer(
                DockerImageName.parse("confluentinc/cp-kafka:7.4.1"))//
                .withExternalZookeeper(zookeeperConnString)
                // .withEnv("KAFKA_ZOOKEEPER_CONNECT", "zookeeper:$ZOOKEEPER_PORT")//
                .withEnv("KAFKA_BROKER_ID", "0")//
                // .withEnv("KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "INTERNAL:PLAINTEXT,EXTERNAL:PLAINTEXT")//
                // .withEnv("KAFKA_LISTENERS", "INTERNAL://0.0.0.0:9092,EXTERNAL://0.0.0.0:" + KAFKA_EXTERNAL_PORT)
                // .withEnv("KAFKA_ADVERTISED_LISTENERS",
                // "INTERNAL://kafka01:9092,EXTERNAL://127.0.0.1:" + KAFKA_EXTERNAL_PORT)
                // .withEnv("KAFKA_INTER_BROKER_LISTENER_NAME", "INTERNAL")//
                .withEnv("KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR", "1")//
                .withExtraHost("kafka01", "127.0.0.1")//
                .dependsOn(zookeeperContainer);// .withEmbeddedZookeeper();//

        LOG.info("zookeeper: " + zookeeperConnString); // .withKraft();
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
