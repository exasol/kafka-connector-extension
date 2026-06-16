package com.exasol.cloudetl.kafka;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.*;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.*;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

class BaseKafkaDockerIntegrationTest extends BaseDockerIntegrationTest {
    private static final String DEFAULT_CONFLUENT_PLATFORM_VERSION = KafkaTestSetup.DEFAULT_CONFLUENT_PLATFORM_VERSION;
    private static final int ZOOKEEPER_PORT = 2181;
    private static final int KAFKA_EXTERNAL_PORT = 29092;
    private static final int SCHEMA_REGISTRY_PORT = 8081;
    private static final int ADMIN_TIMEOUT_MILLIS = 5000;

    AdminClient adminClient;

    final GenericContainer<?> zookeeperContainer = createZookeeperContainer();
    final GenericContainer<?> kafkaBrokerContainer = createKafkaBrokerContainer();
    final GenericContainer<?> schemaRegistryContainer = createSchemaRegistryContainer();

    String getExternalSchemaRegistryUrl() {
        return "http://localhost:" + SCHEMA_REGISTRY_PORT;
    }

    String getExternalBootstrapServers() {
        return "localhost:" + KAFKA_EXTERNAL_PORT;
    }

    void setupAdminClient() {
        this.adminClient = AdminClient
                .create(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, getExternalBootstrapServers()));
    }

    boolean isTopicExist(final String topicName) {
        final ListTopicsOptions options = new ListTopicsOptions();
        options.timeoutMs(ADMIN_TIMEOUT_MILLIS);
        options.listInternal(false);
        try {
            return this.adminClient.listTopics(options).names().get().contains(topicName);
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to list Kafka topics", exception);
        }
    }

    void deleteTopic(final String topicName) {
        final DeleteTopicsOptions options = new DeleteTopicsOptions();
        options.timeoutMs(ADMIN_TIMEOUT_MILLIS);
        try {
            this.adminClient.deleteTopics(List.of(topicName), options).all().get();
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to delete topic " + topicName, exception);
        }
    }

    void createTopic(final String topicName) {
        if (!isTopicExist(topicName)) {
            try {
                this.adminClient.createTopics(List.of(new NewTopic(topicName, 1, (short) 1))).all().get(30,
                        TimeUnit.SECONDS);
            } catch (final Exception exception) {
                throw new IllegalStateException("Failed to create topic " + topicName, exception);
            }
        }
    }

    <V> void produceRecords(final String topicName, final List<V> values, final Serializer<V> valueSerializer) {
        final Map<String, Object> properties = Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                getExternalBootstrapServers(), ProducerConfig.CLIENT_ID_CONFIG, "consumer-group");
        try (KafkaProducer<String, V> producer = new KafkaProducer<>(properties, new StringSerializer(), valueSerializer)) {
            for (final V value : values) {
                producer.send(new ProducerRecord<>(topicName, "key", value));
            }
            producer.flush();
        }
    }

    @BeforeAll
    void beforeAllKafkaDocker() throws Exception {
        super.beforeAllDocker();
        this.zookeeperContainer.start();
        this.kafkaBrokerContainer.start();
        this.schemaRegistryContainer.start();
        setupAdminClient();
    }

    @AfterAll
    void afterAllKafkaDocker() throws Exception {
        this.adminClient.close();
        this.schemaRegistryContainer.stop();
        this.kafkaBrokerContainer.stop();
        this.zookeeperContainer.stop();
        super.afterAllDocker();
    }

    String getContainerNetworkAddress(final Container<?> container) {
        return container.getContainerInfo().getNetworkSettings().getNetworks().values().iterator().next().getIpAddress();
    }

    private GenericContainer<?> createZookeeperContainer() {
        final var image = DockerImageName.parse("confluentinc/cp-zookeeper").withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION);
        return new GenericContainer<>(image).withNetwork(this.network).withNetworkAliases("zookeeper")
                .withEnv("ZOOKEEPER_CLIENT_PORT", String.valueOf(ZOOKEEPER_PORT)).withReuse(true);
    }

    private GenericContainer<?> createKafkaBrokerContainer() {
        final var image = DockerImageName.parse("confluentinc/cp-kafka").withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION);
        return new KafkaContainer(image, KAFKA_EXTERNAL_PORT).dependsOn(this.zookeeperContainer).withNetwork(this.network)
                .withNetworkAliases("kafka01").withEnv("KAFKA_ZOOKEEPER_CONNECT", "zookeeper:" + ZOOKEEPER_PORT)
                .withEnv("KAFKA_BROKER_ID", "0")
                .withEnv("KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "EXTERNAL:PLAINTEXT,INTERNAL:PLAINTEXT")
                .withEnv("KAFKA_LISTENERS", "INTERNAL://0.0.0.0:9092,EXTERNAL://0.0.0.0:" + KAFKA_EXTERNAL_PORT)
                .withEnv("KAFKA_ADVERTISED_LISTENERS", "INTERNAL://kafka01:9092,EXTERNAL://127.0.0.1:" + KAFKA_EXTERNAL_PORT)
                .withEnv("KAFKA_INTER_BROKER_LISTENER_NAME", "INTERNAL")
                .withEnv("KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR", "1").withExtraHost("kafka01", "127.0.0.1")
                .withReuse(true);
    }

    private GenericContainer<?> createSchemaRegistryContainer() {
        final var image = DockerImageName.parse("confluentinc/cp-schema-registry")
                .withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION);
        return new SchemaRegistryContainer(image, SCHEMA_REGISTRY_PORT).dependsOn(this.kafkaBrokerContainer)
                .withNetwork(this.network).withNetworkAliases("schema-registry")
                .withEnv("SCHEMA_REGISTRY_HOST_NAME", "localhost")
                .withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "PLAINTEXT://kafka01:9092")
                .withEnv("SCHEMA_REGISTRY_KAFKASTORE_TOPIC_REPLICATION_FACTOR", "1")
                .withExposedPorts(SCHEMA_REGISTRY_PORT).waitingFor(Wait.forHttp("/subjects").forStatusCode(200))
                .withReuse(true);
    }
}
