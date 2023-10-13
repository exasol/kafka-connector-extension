package com.exasol.cloudetl.kafka;

import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.sql.*;
import java.util.List;
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
import com.exasol.extensionmanager.client.model.ExtensionsResponseExtension;
import com.exasol.extensionmanager.client.model.InstallationsResponseInstallation;
import com.exasol.extensionmanager.itest.*;
import com.exasol.extensionmanager.itest.builder.ExtensionBuilder;
import com.exasol.matcher.TypeMatchMode;
import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

import junit.framework.AssertionFailedError;

class ExtensionIT {
    private static final Logger LOGGER = Logger.getLogger(ExtensionIT.class.getName());
    private static final String PREVIOUS_VERSION = "2.7.2";
    private static final String PREVIOUS_VERSION_JAR_FILE = "exasol-kafka-connector-extension-" + PREVIOUS_VERSION
            + ".jar";
    private static final String EXTENSION_ID = "kafka-connector-extension.js";
    private static final Path EXTENSION_SOURCE_DIR = Paths.get("extension").toAbsolutePath();
    private static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    private static final Path ADAPTER_JAR = getAdapterJar();

    private static ExasolTestSetup exasolTestSetup;
    private static ExtensionManagerSetup setup;
    private static ExtensionManagerClient client;
    private static KafkaTestSetup kafkaSetup;
    private static Connection connection;
    private static ExasolObjectFactory exasolObjectFactory;

    @BeforeAll
    static void setup() throws FileNotFoundException, BucketAccessException, TimeoutException, SQLException,
            ExecutionException, InterruptedException {
        exasolTestSetup = new ExasolTestSetupFactory(Paths.get("no-cloud-setup")).getTestSetup();
        setup = ExtensionManagerSetup.create(exasolTestSetup, ExtensionBuilder.createDefaultNpmBuilder(
                EXTENSION_SOURCE_DIR, EXTENSION_SOURCE_DIR.resolve("dist").resolve(EXTENSION_ID)));
        exasolTestSetup.getDefaultBucket().uploadFile(ADAPTER_JAR, ADAPTER_JAR.getFileName().toString());
        client = setup.client();
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

    @AfterEach
    void cleanup() throws SQLException {
        setup.cleanup();
    }

    @Test
    void listExtensions() {
        final List<ExtensionsResponseExtension> extensions = client.getExtensions();
        assertAll(() -> assertThat(extensions, hasSize(1)), //
                () -> assertThat(extensions.get(0).getName(), equalTo("Kafka Connector Extension")),
                () -> assertThat(extensions.get(0).getCategory(), equalTo("cloud-storage-importer")),
                () -> assertThat(extensions.get(0).getInstallableVersions().get(0).getName(), equalTo(PROJECT_VERSION)),
                () -> assertThat(extensions.get(0).getInstallableVersions().get(0).isLatest(), is(true)),
                () -> assertThat(extensions.get(0).getInstallableVersions().get(0).isDeprecated(), is(false)),
                () -> assertThat(extensions.get(0).getDescription(),
                        equalTo("Exasol Kafka Extension for accessing Apache Kafka")));
    }

    @Test
    void getInstallationsReturnsEmptyList() {
        assertThat(client.getInstallations(), hasSize(0));
    }

    @Test
    void getInstallationsReturnsResult() {
        client.install();
        assertThat(client.getInstallations(), contains(
                new InstallationsResponseInstallation().name("Kafka Connector Extension").version(PROJECT_VERSION)));
    }

    @Test
    void installingWrongVersionFails() {
        client.assertRequestFails(() -> client.install("wrongVersion"),
                equalTo("Installing version 'wrongVersion' not supported, try '" + PROJECT_VERSION + "'."),
                equalTo(400));
        setup.exasolMetadata().assertNoScripts();
    }

    @Test
    void installCreatesScripts() {
        setup.client().install();
        assertScriptsInstalled();
    }

    @Test
    void installingTwiceCreatesScripts() {
        setup.client().install();
        setup.client().install();
        assertScriptsInstalled();
    }

    @Test
    void importWorksAfterInstallation() throws SQLException {
        setup.client().install();
        verifyImportWorks();
    }

    @Test
    void uninstallExtensionWithoutInstallation() throws SQLException {
        assertDoesNotThrow(() -> client.uninstall());
    }

    @Test
    void uninstallExtensionRemovesScripts() throws SQLException {
        client.install();
        client.uninstall();
        setup.exasolMetadata().assertNoScripts();
    }

    @Test
    void uninstallWrongVersionFails() {
        client.assertRequestFails(() -> client.uninstall("wrongVersion"),
                equalTo("Uninstalling version 'wrongVersion' not supported, try '" + PROJECT_VERSION + "'."),
                equalTo(404));
    }

    @Test
    void listingInstancesNotSupported() {
        client.assertRequestFails(() -> client.listInstances(), equalTo("Finding instances not supported"),
                equalTo(404));
    }

    @Test
    void creatingInstancesNotSupported() {
        client.assertRequestFails(() -> client.createInstance(emptyList()), equalTo("Creating instances not supported"),
                equalTo(404));
    }

    @Test
    void deletingInstancesNotSupported() {
        client.assertRequestFails(() -> client.deleteInstance("inst"), equalTo("Deleting instances not supported"),
                equalTo(404));
    }

    @Test
    void getExtensionDetailsInstancesNotSupported() {
        client.assertRequestFails(() -> client.getExtensionDetails(PROJECT_VERSION),
                equalTo("Creating instances not supported"), equalTo(404));
    }

    @Test
    void upgradeFailsWhenNotInstalled() {
        setup.client().assertRequestFails(() -> setup.client().upgrade(),
                "Not all required scripts are installed: Validation failed: Script 'KAFKA_METADATA' is missing, Script 'KAFKA_IMPORT' is missing, Script 'KAFKA_CONSUMER' is missing",
                412);
    }

    @Test
    void upgradeFailsWhenAlreadyUpToDate() {
        setup.client().install();
        setup.client().assertRequestFails(() -> setup.client().upgrade(),
                "Extension is already installed in latest version " + PROJECT_VERSION, 412);
    }

    @Test
    @Disabled("No previous version available yet")
    void upgradeFromPreviousVersion() throws InterruptedException, BucketAccessException, TimeoutException,
            FileNotFoundException, URISyntaxException, SQLException {
        final PreviousExtensionVersion previousVersion = createPreviousVersion();
        previousVersion.prepare();
        previousVersion.install();
        verifyImportWorks();
        assertInstalledVersion("Kafka Connector Extension", PREVIOUS_VERSION);
        previousVersion.upgrade();
        assertInstalledVersion("Kafka Connector Extension", PROJECT_VERSION);
        verifyImportWorks();
    }

    private void assertInstalledVersion(final String expectedName, final String expectedVersion) {
        final List<InstallationsResponseInstallation> installations = setup.client().getInstallations();
        final InstallationsResponseInstallation expectedInstallation = new InstallationsResponseInstallation()
                .name(expectedName).version(expectedVersion);
        // The extension is installed twice (previous and current version), so each one returns the same installation.
        assertAll(() -> assertThat(installations, hasSize(2)),
                () -> assertThat(installations.get(0), equalTo(expectedInstallation)),
                () -> assertThat(installations.get(1), equalTo(expectedInstallation)));
    }

    private PreviousExtensionVersion createPreviousVersion() {
        return setup.previousVersionManager().newVersion().currentVersion(PROJECT_VERSION) //
                .previousVersion(PREVIOUS_VERSION) //
                .adapterFileName(PREVIOUS_VERSION_JAR_FILE) //
                .extensionFileName(EXTENSION_ID) //
                .project("kafka-connector-extension") //
                .build();
    }

    private void verifyImportWorks() {
        final ExasolSchema schema = exasolObjectFactory.createSchema("TESTING_SCHEMA_" + System.currentTimeMillis());

        try {

            final Table targetTable = schema.createTableBuilder("TARGET")

                    //.column("SENSOR_ID", "INTEGER")//
                    .column("STATUS", "VARCHAR(10)") //
                    .column("KAFKA_PARTITION", "DECIMAL(18, 0)")//
                    .column("KAFKA_OFFSET", "DECIMAL(36, 0)")//
                    .build();
            // CREATE CONNECTION (optional, see
            // https://github.com/exasol/kafka-connector-extension/blob/main/doc/user_guide/user_guide.md#importing-records)

            executeKafkaImport(targetTable, kafkaSetup);

            assertQueryResult(
                    "select status, kafka_partition, kafka_offset from " + targetTable.getFullyQualifiedName()
                            + " order by status",
                    table("VARCHAR", "BIGINT", "DECIMAL") //
                            .row( "OK", 0L, 0) //
                            .row( "WARN", 0L, 1) //
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
                // " SCHEMA_REGISTRY_URL = '" + kafkaConnection + "'\n" + //is an URL to the Schema Registry server.
                // This is only required if you are importing Avro records. It is used to retrieve Avro schemas of Kafka
                // topics. Avro is set as default record value format.
                " TOPIC_NAME = '" + kafkaSetup.getTopicName() + "'\n" + //
                " TABLE_NAME = '" + targetTable.getFullyQualifiedName() + "'\n" + //
                " GROUP_ID = 'exaudf' \n" + //
                " CONSUME_ALL_OFFSETS = 'true' \n";
        LOGGER.info("Executing query '" + sql + "'");
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

    private void assertScriptsInstalled() {
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
