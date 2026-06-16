package com.exasol.cloudetl.kafka;

import static com.exasol.matcher.ResultSetStructureMatcher.table;
import static com.exasol.matcher.TypeMatchMode.NO_JAVA_TYPE_CHECK;
import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.*;
import java.util.*;

import org.apache.avro.*;
import org.apache.avro.generic.*;
import org.apache.kafka.common.serialization.*;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.Container.ExecResult;

import com.exasol.dbbuilder.dialects.Table;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

class KafkaImportIT extends BaseKafkaDockerIntegrationTest {
    private static final String SCHEMA_NAME = "KAFKA_SCHEMA";
    private static final String TABLE_NAME = "KAFKA_TABLE";
    private String topicName;

    @BeforeAll
    void beforeAllKafkaImport() {
        installKafkaConnector(SCHEMA_NAME);
        updateExasolHostsFile();
    }

    @BeforeEach
    void beforeEachKafkaImport() {
        this.topicName = "kafka-topic-" + UUID.randomUUID();
        createTopic(this.topicName);
    }

    @AfterEach
    void afterEachKafkaImport() {
        executeStmt("DROP TABLE IF EXISTS " + getTableName());
        deleteTopic(this.topicName);
    }

    @Test
    void importsLongsAsTimestampValues() {
        TimeZone.setDefault(this.exasolContainer.getTimeZone());
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        new KafkaImportChecker(Map.of("COLUMN", "TIMESTAMP"))
                .withImportProperties(Map.of("RECORD_VALUE_FORMAT", "avro"))
                .withTopicValues(List.of(timestampRecord(0L), timestampRecord(1337L), timestampRecord(timestamp.getTime())),
                        timestampAvroSerializer())
                .assertResult(table().row(new Timestamp(0L), 0L, 0L).row(new Timestamp(1337L), 0L, 1L)
                        .row(timestamp, 0L, 2L).matches(NO_JAVA_TYPE_CHECK));
    }

    @Test
    void importsStringValues() {
        new KafkaImportChecker(Map.of("COLUMN", "VARCHAR(20)")).withTopicValues(List.of("a", "b", "c"),
                new StringSerializer()).assertResult(table().row("a", 0L, 0L).row("b", 0L, 1L).row("c", 0L, 2L)
                        .matches(NO_JAVA_TYPE_CHECK));
    }

    @Test
    void importWithConsumeAllOffsetParameterEmptyTopic() {
        new KafkaImportChecker(Map.of("COLUMN", "VARCHAR(20)"))
                .withImportProperties(Map.of("CONSUME_ALL_OFFSETS", "true"))
                .assertResult(table("VARCHAR", "BIGINT", "DECIMAL").matches());
    }

    @Test
    void importWithConsumeAllOffsetParameter() {
        final Map<String, String> properties = Map.of("CONSUME_ALL_OFFSETS", "true", "MAX_POLL_RECORDS", "2",
                "MIN_RECORDS_PER_RUN", "1", "MAX_RECORDS_PER_RUN", "2");
        new KafkaImportChecker(Map.of("COLUMN", "VARCHAR(20)")).withImportProperties(properties)
                .withTopicValues(List.of("one", "two", "three"), new StringSerializer())
                .assertResult(table().row("one", 0L, 0L).row("two", 0L, 1L).row("three", 0L, 2L)
                        .matches(NO_JAVA_TYPE_CHECK));
    }

    void updateExasolHostsFile() {
        final String brokerIpAddress = getContainerNetworkAddress(this.kafkaBrokerContainer);
        final String schemaRegistryIpAddress = getContainerNetworkAddress(this.schemaRegistryContainer);
        final List<String> commands = List.of("cp /etc/hosts /tmp/hosts", "sed --in-place '/kafka01/d' /tmp/hosts",
                "sed --in-place '/schema-registry/d' /tmp/hosts", "echo '" + brokerIpAddress + " kafka01' >> /tmp/hosts",
                "echo '" + schemaRegistryIpAddress + " schema-registry' >> /tmp/hosts", "cp /tmp/hosts /etc/hosts");
        for (final String command : commands) {
            try {
                final ExecResult exitCode = this.exasolContainer.execInContainer("/bin/sh", "-c", command);
                if (exitCode.getExitCode() != 0) {
                    throw new IllegalStateException("Could not run command '" + command + "' in Exasol container.");
                }
            } catch (final Exception exception) {
                throw new IllegalStateException("Could not run command '" + command + "' in Exasol container.", exception);
            }
        }
    }

    private String getTableName() {
        return "\"" + SCHEMA_NAME + "\".\"" + TABLE_NAME + "\"";
    }

    private GenericRecord timestampRecord(final long timestamp) {
        final Schema schema = new Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TimestampRecord\","
                + "\"fields\":[{\"name\":\"timestamp\",\"type\":\"long\"}]}");
        return new GenericRecordBuilder(schema).set("timestamp", timestamp).build();
    }

    private Serializer<GenericRecord> timestampAvroSerializer() {
        final KafkaAvroSerializer serializer = new KafkaAvroSerializer();
        serializer.configure(Map.of("schema.registry.url", getExternalSchemaRegistryUrl()), false);
        @SuppressWarnings("unchecked")
        final Serializer<GenericRecord> typed = (Serializer<GenericRecord>) (Serializer<?>) serializer;
        return typed;
    }

    private String defaultImportStatement(final Table table) {
        return "IMPORT INTO " + table.getFullyQualifiedName() + "\n"
                + "FROM SCRIPT " + SCHEMA_NAME + ".KAFKA_CONSUMER WITH\n"
                + "BOOTSTRAP_SERVERS   = 'kafka01:9092'\n"
                + "SCHEMA_REGISTRY_URL = 'http://schema-registry:8081'\n"
                + "TOPIC_NAME          = '" + this.topicName + "'\n"
                + "TABLE_NAME          = '" + table.getFullyQualifiedName() + "'\n"
                + "POLL_TIMEOUT_MS     = '300'\n"
                + "GROUP_ID            = 'exasol-kafka-udf-consumers'\n";
    }

    private final class KafkaImportChecker {
        private final Table table;
        private final Map<String, String> columns;
        private Map<String, String> properties;

        private KafkaImportChecker(final Map<String, String> columns) {
            this.columns = columns;
            this.table = getTable();
        }

        KafkaImportChecker withImportProperties(final Map<String, String> properties) {
            this.properties = properties;
            return this;
        }

        <V> KafkaImportChecker withTopicValues(final List<V> values, final Serializer<V> serializer) {
            produceRecords(topicName, values, serializer);
            return this;
        }

        void assertResult(final Matcher<ResultSet> matcher) {
            executeStmt(getImportStatement());
            try (ResultSet resultSet = executeQuery("SELECT * FROM " + getTableName())) {
                assertThat(resultSet, matcher);
            } catch (final SQLException exception) {
                throw new IllegalStateException("Failed to close result set", exception);
            }
        }

        private Table getTable() {
            var tableBuilder = schema.createTableBuilder(TABLE_NAME);
            final Map<String, String> allColumns = new LinkedHashMap<>(this.columns);
            allColumns.put("KAFKA_PARTITION", "DECIMAL(18, 0)");
            allColumns.put("KAFKA_OFFSET", "DECIMAL(36, 0)");
            for (final Map.Entry<String, String> column : allColumns.entrySet()) {
                tableBuilder = tableBuilder.column(column.getKey(), column.getValue());
            }
            return tableBuilder.build();
        }

        private String getImportStatement() {
            final StringBuilder builder = new StringBuilder(defaultImportStatement(this.table));
            if (this.properties == null || !this.properties.containsKey("RECORD_KEY_FORMAT")) {
                builder.append("\nRECORD_KEY_FORMAT = 'string'");
            }
            if (this.properties == null || !this.properties.containsKey("RECORD_VALUE_FORMAT")) {
                builder.append("\nRECORD_VALUE_FORMAT = 'string'");
            }
            if (this.properties != null) {
                this.properties.forEach((key, value) -> builder.append("\n").append(key).append(" = '").append(value).append("'"));
            }
            builder.append(";");
            return builder.toString();
        }
    }
}
