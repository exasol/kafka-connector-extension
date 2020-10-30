package com.exasol.cloudetl.kafka

import scala.collection.JavaConverters._

import com.exasol.ExaImportSpecification
import com.exasol.ExaMetadata

import com.typesafe.scalalogging.LazyLogging

/**
 * This object is referenced from the UDF script that is called from the
 * Exasol's {@code IMPORT FROM SCRIPT} SQL statement.
 *
 * It returns an SQL query that is run to import data from a Kafka
 * topic.
 */
object KafkaConsumerQueryGenerator extends LazyLogging {

  /**
   * An entry point for the Exasol IMPORT FROM SCRIPT user-defined
   * function.
   *
   * The generated SQL query calls metadata and import UDF scripts
   * internally. Additionally, the generated query runs in a single
   * transaction.
   */
  def generateSqlForImportSpec(
    metadata: ExaMetadata,
    importSpec: ExaImportSpecification
  ): String = {
    val kafkaProperties = KafkaConsumerProperties(importSpec.getParameters.asScala.toMap)
    val tableName = kafkaProperties.getTableName()
    val topic = kafkaProperties.getTopic()
    if (topic.contains(",")) {
      throw new IllegalArgumentException(
        "Please provide only a single topic name. Importing data " +
          "from multiple topics is not supported."
      )
    }
    logger.info(s"Generating a SQL query to import from '$topic'.")
    val kvPairs = kafkaProperties.mkString()
    val scriptSchema = metadata.getScriptSchema

    s"""SELECT
       |  $scriptSchema.KAFKA_IMPORT(
       |    '$kvPairs', partition_index, max_offset
       |)
       |FROM (
       |  SELECT $scriptSchema.KAFKA_METADATA(
       |    '$kvPairs', kafka_partition, kafka_offset
       |  ) FROM (
       |    SELECT kafka_partition, MAX(kafka_offset) AS kafka_offset
       |    FROM $tableName
       |    GROUP BY kafka_partition
       |    UNION ALL
       |    SELECT 0, -1
       |    FROM DUAL
       |    WHERE NOT EXISTS (SELECT * FROM $tableName LIMIT 2)
       |  )
       |)
       |GROUP BY
       |  partition_index;
       |""".stripMargin
  }

}
