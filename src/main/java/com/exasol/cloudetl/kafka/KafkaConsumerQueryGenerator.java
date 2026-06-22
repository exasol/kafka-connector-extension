package com.exasol.cloudetl.kafka;

import com.exasol.ExaImportSpecification;
import com.exasol.ExaMetadata;
import com.exasol.errorreporting.ExaError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KafkaConsumerQueryGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerQueryGenerator.class);

    private KafkaConsumerQueryGenerator() {
    }

    public static String generateSqlForImportSpec(final ExaMetadata metadata, final ExaImportSpecification importSpec) {
        final KafkaConsumerProperties kafkaProperties = KafkaConsumerProperties.apply(importSpec.getParameters());
        final String tableName = kafkaProperties.getTableName();
        final String topic = kafkaProperties.getTopic();
        if (topic.contains(",")) {
            throw new IllegalArgumentException(ExaError.messageBuilder("E-KCE-10")
                    .message("Importing data from multiple topics is not supported.")
                    .mitigation("Please provide only a single topic name.")
                    .toString());
        }
        LOGGER.info("Generating a SQL query to import from '{}'.", topic);
        final String kvPairs = kafkaProperties.mkString();
        final String scriptSchema = metadata.getScriptSchema();
        return "SELECT\n"
                + "  " + scriptSchema + ".KAFKA_IMPORT(\n"
                + "    '" + kvPairs + "', partition_index, max_offset\n"
                + ")\n"
                + "FROM (\n"
                + "  SELECT " + scriptSchema + ".KAFKA_METADATA(\n"
                + "    '" + kvPairs + "', kafka_partition, kafka_offset\n"
                + "  ) FROM (\n"
                + "    SELECT kafka_partition, MAX(kafka_offset) AS kafka_offset\n"
                + "    FROM " + tableName + "\n"
                + "    GROUP BY kafka_partition\n"
                + "    UNION ALL\n"
                + "    SELECT 0, -1\n"
                + "    FROM DUAL\n"
                + "    WHERE NOT EXISTS (SELECT * FROM " + tableName + " LIMIT 2)\n"
                + "  )\n"
                + ")\n"
                + "GROUP BY\n"
                + "  partition_index;\n";
    }
}
