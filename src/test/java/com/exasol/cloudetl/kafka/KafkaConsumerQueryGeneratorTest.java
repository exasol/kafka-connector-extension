package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.*;

class KafkaConsumerQueryGeneratorTest {
    private final PathTestSupport support = new PathTestSupport();

    @BeforeEach
    void beforeEach() {
        this.support.beforeEach();
        this.support.properties = new LinkedHashMap<>(Map.of(
                "BOOTSTRAP_SERVERS", "kafka.broker01.example.com:9092",
                "TOPIC_NAME", "kafkaTopic",
                "TABLE_NAME", "exasolTable"));
        when(this.support.metadata.getScriptSchema()).thenReturn(this.support.schema);
    }

    @Test
    void generateSqlForImportSpecReturnsSqlStatement() {
        when(this.support.importSpec.getParameters()).thenReturn(this.support.properties);
        final String propertyPairs = KafkaConsumerPropertiesSupport
                .create(ScalaCollections.immutableMap(this.support.properties)).mkString();

        final String expectedSqlStatement = String.join("\n",
                "SELECT",
                "  " + this.support.schema + ".KAFKA_IMPORT(",
                "    '" + propertyPairs + "', partition_index, max_offset",
                ")",
                "FROM (",
                "  SELECT " + this.support.schema + ".KAFKA_METADATA(",
                "    '" + propertyPairs + "', kafka_partition, kafka_offset",
                "  ) FROM (",
                "    SELECT kafka_partition, MAX(kafka_offset) AS kafka_offset",
                "    FROM exasolTable",
                "    GROUP BY kafka_partition",
                "    UNION ALL",
                "    SELECT 0, -1",
                "    FROM DUAL",
                "    WHERE NOT EXISTS (SELECT * FROM exasolTable LIMIT 2)",
                "  )",
                ")",
                "GROUP BY",
                "  partition_index;",
                "");

        final String generatedSql = KafkaConsumerQueryGenerator.generateSqlForImportSpec(this.support.metadata,
                this.support.importSpec);
        assertEquals(expectedSqlStatement, generatedSql);
        verify(this.support.metadata, atLeastOnce()).getScriptSchema();
        verify(this.support.importSpec, times(1)).getParameters();
    }

    @Test
    void generateSqlForImportSpecThrowsIfTableNamePropertyIsNotSet() {
        this.support.properties.remove("TABLE_NAME");
        assertInvalidProperties("Please provide key-value pairs for 'TABLE_NAME' property.");
    }

    @Test
    void generateSqlForImportSpecThrowsIfTopicNamePropertyIsNotSet() {
        this.support.properties.remove("TOPIC_NAME");
        assertInvalidProperties("Please provide key-value pairs for 'TOPIC_NAME' property.");
    }

    @Test
    void generateSqlForImportSpecThrowsIfTopicsContainMoreThanOneTopic() {
        this.support.properties.put("TOPIC_NAME", "topic1,topic2,topic3");
        assertInvalidProperties("Please provide only a single topic name.");
    }

    private void assertInvalidProperties(final String expectedMessage) {
        when(this.support.importSpec.getParameters()).thenReturn(this.support.properties);

        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> KafkaConsumerQueryGenerator.generateSqlForImportSpec(this.support.metadata, this.support.importSpec));

        assertTrue(thrown.getMessage().contains(expectedMessage));
    }
}
