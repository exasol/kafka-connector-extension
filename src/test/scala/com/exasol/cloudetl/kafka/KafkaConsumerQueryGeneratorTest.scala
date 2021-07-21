package com.exasol.cloudetl.kafka

import scala.jdk.CollectionConverters._

import org.mockito.Mockito._

class KafkaConsumerQueryGeneratorTest extends PathTest {

  private[this] val kafkaConsumerProperties = Map(
    "BOOTSTRAP_SERVERS" -> "kafka.broker01.example.com:9092",
    "TOPIC_NAME" -> "kafkaTopic",
    "TABLE_NAME" -> "exasolTable"
  )

  override final def beforeEach(): Unit = {
    super.beforeEach()
    properties = kafkaConsumerProperties
    when(metadata.getScriptSchema()).thenReturn(schema)
    ()
  }

  test("generateSqlForImportSpec returns SQL statement") {
    when(importSpec.getParameters()).thenReturn(properties.asJava)
    val propertyPairs = KafkaConsumerProperties(properties).mkString()

    val expectedSQLStatement =
      s"""SELECT
         |  $schema.KAFKA_IMPORT(
         |    '$propertyPairs', partition_index, max_offset
         |)
         |FROM (
         |  SELECT $schema.KAFKA_METADATA(
         |    '$propertyPairs', kafka_partition, kafka_offset
         |  ) FROM (
         |    SELECT kafka_partition, MAX(kafka_offset) AS kafka_offset
         |    FROM exasolTable
         |    GROUP BY kafka_partition
         |    UNION ALL
         |    SELECT 0, -1
         |    FROM DUAL
         |    WHERE NOT EXISTS (SELECT * FROM exasolTable LIMIT 2)
         |  )
         |)
         |GROUP BY
         |  partition_index;
         |""".stripMargin

    val generatedSQL = KafkaConsumerQueryGenerator.generateSqlForImportSpec(metadata, importSpec)
    assert(generatedSQL === expectedSQLStatement)
    verify(metadata, atLeastOnce).getScriptSchema
    verify(importSpec, times(1)).getParameters
  }

  test("generateSqlForImportSpec throws if table name property is not set") {
    properties -= "TABLE_NAME"
    when(importSpec.getParameters()).thenReturn(properties.asJava)
    val thrown = intercept[IllegalArgumentException] {
      KafkaConsumerQueryGenerator.generateSqlForImportSpec(metadata, importSpec)
    }
    assert(thrown.getMessage === "Please provide a value for the TABLE_NAME property!")
  }

  test("generateSqlForImportSpec throws if topics property is not set") {
    properties -= "TOPIC_NAME"
    when(importSpec.getParameters()).thenReturn(properties.asJava)
    val thrown = intercept[IllegalArgumentException] {
      KafkaConsumerQueryGenerator.generateSqlForImportSpec(metadata, importSpec)
    }
    assert(thrown.getMessage === "Please provide a value for the TOPIC_NAME property!")
  }

  test("generateSqlForImportSpec throws if topics contains more than one topic") {
    properties += ("TOPIC_NAME" -> "topic1,topic2,topic3")
    when(importSpec.getParameters()).thenReturn(properties.asJava)
    val thrown = intercept[IllegalArgumentException] {
      KafkaConsumerQueryGenerator.generateSqlForImportSpec(metadata, importSpec)
    }
    assert(thrown.getMessage.contains("Please provide only a single topic name."))
  }

}
