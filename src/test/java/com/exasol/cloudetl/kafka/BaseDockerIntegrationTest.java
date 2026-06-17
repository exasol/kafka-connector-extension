package com.exasol.cloudetl.kafka;

import java.io.File;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import org.junit.jupiter.api.TestInstance;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.Testcontainers;

import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.Column;
import com.exasol.dbbuilder.dialects.exasol.*;
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;
import com.exasol.udfdebugging.UdfTestSetup;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseDockerIntegrationTest {
    private static final String JAR_NAME_PATTERN = "exasol-kafka-connector-extension-";
    private static final String DEFAULT_EXASOL_DOCKER_IMAGE = "2026.1.0";
    private static final String TESTCONTAINERS_SSHD_IMAGE = "testcontainers/sshd:1.3.0";
    private static final int JACOCO_SERVER_PORT = 3002;

    final DockerNamedNetwork network = DockerNamedNetwork.get("kafka-it-tests", true);
    final ExasolContainer<? extends ExasolContainer<?>> exasolContainer = createExasolContainer();
    private UdfTestSetup udfTestSetup;
    private ExasolObjectFactory factory;
    ExasolSchema schema;
    Connection connection;
    final String assembledJarName = getAssembledJarName();

    void beforeAllDocker() {
        this.exasolContainer.start();
        this.connection = getConnection();
    }

    void afterAllDocker() throws SQLException {
        if (this.udfTestSetup != null) {
            this.udfTestSetup.close();
        }
        this.connection.close();
        this.exasolContainer.stop();
    }

    void installKafkaConnector(final String schemaName) {
        executeStmt("DROP SCHEMA IF EXISTS " + schemaName + " CASCADE;");
        this.udfTestSetup = new UdfTestSetup(getTestcontainersPortForwarderAddress(), exasolContainer.getDefaultBucket(), this.connection);
        this.factory = new ExasolObjectFactory(this.connection, ExasolObjectConfiguration.builder().withJvmOptions(udfTestSetup.getJvmOptions()).build());
        this.schema = this.factory.createSchema(schemaName);
        createKafkaImportDeploymentScripts();
        uploadJarToBucket();
    }

    void executeStmt(final String sql) {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute(sql);
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to execute statement: " + sql, exception);
        }
    }

    ResultSet executeQuery(final String sql) {
        try {
            return this.connection.createStatement().executeQuery(sql);
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to execute query: " + sql, exception);
        }
    }

    private ExasolContainer<? extends ExasolContainer<?>> createExasolContainer() {
        @SuppressWarnings("resource") // Will be closed by afterAllDocker()
        final ExasolContainer<? extends ExasolContainer<?>> container = new ExasolContainer<>(getExasolDockerImageVersion());
        container.withNetwork(this.network);
        Testcontainers.exposeHostPorts(JACOCO_SERVER_PORT);
        container.withReuse(!isUdfCoverageEnabled());
        return container;
    }

    private boolean isUdfCoverageEnabled() {
        return Boolean.getBoolean("test.coverage");
    }

    private String getTestcontainersPortForwarderAddress() {
        return DockerClientFactory.lazyClient().listContainersCmd()
                .withAncestorFilter(List.of(TESTCONTAINERS_SSHD_IMAGE))
                .withStatusFilter(List.of("running"))
                .exec().stream()
                .max(Comparator.comparing(com.github.dockerjava.api.model.Container::getCreated))
                .map(container -> container.getNetworkSettings().getNetworks().values().iterator().next().getIpAddress())
                .orElseThrow(() -> new IllegalStateException("Could not find running " + TESTCONTAINERS_SSHD_IMAGE
                        + " container for forwarding host port " + JACOCO_SERVER_PORT));
    }

    private Connection getConnection() {
        return createConnection();
    }

    private Connection createConnection() {
        try {
            return this.exasolContainer.createConnection("");
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to create Exasol connection", exception);
        }
    }

    private String getAssembledJarName() {
        return findFileOrDirectory("target/", JAR_NAME_PATTERN);
    }

    private void createKafkaImportDeploymentScripts() {
        final String jarPath = "/buckets/bfsdefault/default/" + this.assembledJarName;
        this.schema.createUdfBuilder("KAFKA_CONSUMER").language(UdfScript.Language.JAVA)
                .inputType(UdfScript.InputType.SET).emits()
                .bucketFsContent("com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator", jarPath).build();
        this.schema.createUdfBuilder("KAFKA_IMPORT").language(UdfScript.Language.JAVA)
                .inputType(UdfScript.InputType.SET).emits()
                .bucketFsContent("com.exasol.cloudetl.kafka.KafkaTopicDataImporter", jarPath).build();
        this.schema.createUdfBuilder("KAFKA_METADATA").language(UdfScript.Language.JAVA)
                .inputType(UdfScript.InputType.SET)
                .parameter("params", "VARCHAR(2000)")
                .parameter("kafka_partition", "DECIMAL(18, 0)")
                .parameter("kafka_offset", "DECIMAL(36, 0)")
                .emits(new Column("partition_index", "DECIMAL(18, 0)"), new Column("max_offset", "DECIMAL(36, 0)"))
                .bucketFsContent("com.exasol.cloudetl.kafka.KafkaTopicMetadataReader", jarPath).build();
    }

    private void uploadJarToBucket() {
        try {
            this.exasolContainer.getDefaultBucket().uploadFile(Paths.get("target", this.assembledJarName),
                    this.assembledJarName);
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to upload connector jar to BucketFS", exception);
        }
    }

    private String findFileOrDirectory(final String searchDirectory, final String name) {
        final File directory = new File(searchDirectory);
        if (directory.exists() && directory.isDirectory()) {
            return Arrays.stream(directory.listFiles()).filter(file -> file.getName().contains(name)).findAny()
                    .map(File::getName)
                    .orElseThrow(() -> new IllegalArgumentException("Cannot find a file or a directory with pattern '"
                            + name + "' in '" + searchDirectory + "'"));
        }
        throw new IllegalArgumentException("Cannot find a file or a directory with pattern '" + name + "' in '"
                + searchDirectory + "'");
    }

    private String getExasolDockerImageVersion() {
        final String dockerVersion = System.getenv("EXASOL_DB_VERSION");
        return dockerVersion == null ? DEFAULT_EXASOL_DOCKER_IMAGE : dockerVersion;
    }
}
