package com.exasol.cloudetl.kafka;

import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

import java.io.FileNotFoundException;
import java.nio.file.*;
import java.sql.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;

import com.exasol.bucketfs.BucketAccessException;
import com.exasol.dbbuilder.dialects.Table;
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectFactory;
import com.exasol.dbbuilder.dialects.exasol.ExasolSchema;
import com.exasol.exasoltestsetup.ExasolTestSetup;
import com.exasol.exasoltestsetup.ExasolTestSetupFactory;
import com.exasol.extensionmanager.itest.ExasolVersionCheck;
import com.exasol.extensionmanager.itest.ExtensionManagerSetup;
import com.exasol.extensionmanager.itest.base.AbstractScriptExtensionIT;
import com.exasol.extensionmanager.itest.base.ExtensionITConfig;
import com.exasol.extensionmanager.itest.builder.ExtensionBuilder;
import com.exasol.matcher.TypeMatchMode;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

import junit.framework.AssertionFailedError;

class ExtensionIT extends AbstractScriptExtensionIT {
    private static final Logger LOGGER = Logger.getLogger(ExtensionIT.class.getName());

    private static final String EXTENSION_ID = "kafka-connector-extension.js";
    private static final Path EXTENSION_SOURCE_DIR = Paths.get("extension").toAbsolutePath();
    private static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    private static final Path ADAPTER_JAR = getAdapterJar();

    private static ExasolTestSetup exasolTestSetup;
    private static ExtensionManagerSetup setup;
    private static KafkaTestSetup kafkaSetup;
    private static Connection connection;
    private static ExasolObjectFactory exasolObjectFactory;

    @BeforeAll
    static void setup() throws FileNotFoundException, BucketAccessException, TimeoutException, SQLException,
            ExecutionException, InterruptedException {
        if (System.getProperty("com.exasol.dockerdb.image") == null) {
            System.setProperty("com.exasol.dockerdb.image", "8.23.1");
        }
        exasolTestSetup = new ExasolTestSetupFactory(Paths.get("no-cloud-setup")).getTestSetup();
        ExasolVersionCheck.assumeExasolVersion8(exasolTestSetup);
        setup = ExtensionManagerSetup.create(exasolTestSetup, ExtensionBuilder.createDefaultNpmBuilder(
                EXTENSION_SOURCE_DIR, EXTENSION_SOURCE_DIR.resolve("dist").resolve(EXTENSION_ID)));
        exasolTestSetup.getDefaultBucket().uploadFile(ADAPTER_JAR, ADAPTER_JAR.getFileName().toString());
        kafkaSetup = KafkaTestSetup.create();
        connection = exasolTestSetup.createConnection();
        exasolObjectFactory = new ExasolObjectFactory(connection);
    }

    private static Path getAdapterJar() {
        final Path jar = Paths.get("target").resolve("exasol-kafka-connector-extension-" + PROJECT_VERSION + ".jar")
                .toAbsolutePath();
        if (Files.exists(jar)) {
            return jar;
        } else {
            throw new AssertionFailedError("Adapter jar " + jar + " does not exist. Run 'mvn package'.");
        }
    }

    @AfterAll
    static void teardown() throws Exception {
        if (connection != null) {
            connection.close();
        }
        if (setup != null) {
            setup.close();
        }
        if (exasolTestSetup != null) {
            exasolTestSetup.getDefaultBucket().deleteFileNonBlocking(ADAPTER_JAR.getFileName().toString());
            exasolTestSetup.close();
        }
        if (kafkaSetup != null) {
            kafkaSetup.close();
        }
    }

    @Test
    void importWorksAfterInstallation() throws SQLException {
        setup.client().install();
        kafkaSetup.produceTestTopicRecords();
        assertScriptsExist();
    }

    @Override
    protected ExtensionManagerSetup getSetup() {
        return setup;
    }

    @Override
    protected ExtensionITConfig createConfig() {
        final String previousVersion = "1.7.1";
        final String previousVersionJarFile = "exasol-kafka-connector-extension-" + previousVersion + ".jar";
        return ExtensionITConfig.builder().projectName("kafka-connector-extension").currentVersion(PROJECT_VERSION)
                .extensionId(EXTENSION_ID).previousVersion(previousVersion)
                .previousVersionJarFile(previousVersionJarFile).expectedParameterCount(-1)
                .extensionName("Kafka Connector Extension") //
                .extensionDescription("Exasol Kafka Extension for accessing Apache Kafka") //
                .build();
    }

    @Override
    protected void assertScriptsWork() {
        final ExasolSchema schema = exasolObjectFactory.createSchema("TESTING_SCHEMA_" + System.currentTimeMillis());
        try {
            final Table targetTable = schema.createTableBuilder("TARGET").column("STATUS", "VARCHAR(10)") //
                    .column("KAFKA_PARTITION", "DECIMAL(18, 0)")//
                    .column("KAFKA_OFFSET", "DECIMAL(36, 0)")//
                    .build();
            executeKafkaImport(targetTable, kafkaSetup);
            assertQueryResult(
                    "select status, kafka_partition, kafka_offset from " + targetTable.getFullyQualifiedName()
                            + " order by status",
                    table("VARCHAR", "BIGINT", "DECIMAL") //
                            .row("OK", 0L, 0) //
                            .row("WARN", 0L, 1) //
                            .matches(TypeMatchMode.NO_JAVA_TYPE_CHECK));
        } finally {
            schema.drop();
        }
    }

    private void executeKafkaImport(final Table targetTable, final KafkaTestSetup kafkaSetup) {
        final String bootstrapServers = kafkaSetup.getContainer().getBootstrapServers();

        final String modifiedBootstrapServers = bootstrapServers.replaceAll("localhost",
                IntegrationTestConstants.DOCKER_IP_ADDRESS);
        executeStatement("OPEN SCHEMA " + ExtensionManagerSetup.EXTENSION_SCHEMA_NAME);
        final String sql = "IMPORT INTO " + targetTable.getFullyQualifiedName() + "\n" + //
                " FROM SCRIPT " + ExtensionManagerSetup.EXTENSION_SCHEMA_NAME + ".KAFKA_CONSUMER WITH\n" + //
                " BOOTSTRAP_SERVERS = '" + modifiedBootstrapServers + "'\n" + //
                " RECORD_KEY_FORMAT = 'string'" + "\n" + //
                " RECORD_VALUE_FORMAT = 'string'" + "\n" + //
                " TOPIC_NAME = '" + kafkaSetup.getTopicName() + "'\n" + //
                " TABLE_NAME = '" + targetTable.getFullyQualifiedName() + "'\n" + //
                " GROUP_ID = 'exaudf' \n" + //
                " CONSUME_ALL_OFFSETS = 'true' \n";
        executeStatement(sql);
    }

    private void executeStatement(final String sql) {
        LOGGER.info(() -> "Running statement '" + sql + "'...");
        try (var statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to execute statement '" + sql + "': " + exception.getMessage(),
                    exception);
        }
    }

    private void assertQueryResult(final String sql, final Matcher<ResultSet> matcher) {
        try (Statement statement = connection.createStatement()) {
            final ResultSet result = statement.executeQuery(sql);
            assertThat(result, matcher);
        } catch (final SQLException exception) {
            throw new IllegalStateException("Failed to execute query '" + sql + "': " + exception.getMessage(),
                    exception);
        }
    }

    @Override
    protected void assertScriptsExist() {
        setup.exasolMetadata().assertScript(table() //
                .row(setScript("KAFKA_CONSUMER", "com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator")) //
                .row(setScript("KAFKA_IMPORT", "com.exasol.cloudetl.kafka.KafkaTopicDataImporter")) //
                .row(setScript("KAFKA_METADATA", "com.exasol.cloudetl.kafka.KafkaTopicMetadataReader")) //
                .matches());
    }

    private Object[] setScript(final String name, final String scriptClass) {
        return script(name, "SET", scriptClass);
    }

    private Object[] script(final String name, final String inputType, final String scriptClass) {
        final String comment = "Created by Extension Manager for Kafka Connector Extension " + PROJECT_VERSION;
        final String jarPath = "/buckets/bfsdefault/default/" + ADAPTER_JAR.getFileName().toString();
        return new Object[] { name, "UDF", inputType, "EMITS", allOf(//
                containsString("%jar " + jarPath + ";"), //
                containsString("%scriptclass " + scriptClass + ";")), //
                comment };
    }
}
