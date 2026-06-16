package com.exasol.cloudetl.kafka;

import java.io.File;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Arrays;

import org.junit.jupiter.api.TestInstance;

import com.exasol.containers.ExasolContainer;
import com.exasol.dbbuilder.dialects.Column;
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectFactory;
import com.exasol.dbbuilder.dialects.exasol.ExasolSchema;
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseDockerIntegrationTest {
    private static final String JAR_NAME_PATTERN = "exasol-kafka-connector-extension-";
    private static final String DEFAULT_EXASOL_DOCKER_IMAGE = "2026.1.0";

    final DockerNamedNetwork network = DockerNamedNetwork.get("kafka-it-tests", true);
    final ExasolContainer<? extends ExasolContainer<?>> exasolContainer = createExasolContainer();
    ExasolObjectFactory factory;
    ExasolSchema schema;
    Connection connection;
    final String assembledJarName = getAssembledJarName();

    void beforeAllDocker() {
        this.exasolContainer.start();
        this.connection = getConnection();
    }

    void afterAllDocker() throws SQLException {
        this.connection.close();
        this.exasolContainer.stop();
    }

    void installKafkaConnector(final String schemaName) {
        executeStmt("DROP SCHEMA IF EXISTS " + schemaName + " CASCADE;");
        this.factory = new ExasolObjectFactory(createConnection());
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
        container.withReuse(true);
        return container;
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
