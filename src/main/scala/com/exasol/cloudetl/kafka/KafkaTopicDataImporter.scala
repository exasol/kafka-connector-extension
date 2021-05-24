package com.exasol.cloudetl.kafka

import java.time.Duration
import java.util.Arrays

import scala.jdk.CollectionConverters._

import com.exasol.ExaIterator
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.deserialization.{AvroDeserialization, JsonDeserialization}

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.common.TopicPartition

/**
 * This object is referenced from the UDF script that imports data from
 * a Kafka topic into an Exasol table.
 */
object KafkaTopicDataImporter extends LazyLogging {

  /**
   * Consumes Kafka topic records and emits them into an Exasol table.
   *
   * The function is called for each Kafka topic partition in parallel.
   * It polls all the record offsets that have not been consumed before
   * and emits them to the Exasol table.
   *
   * Together with the record data, it emits the partition id and record
   * offset as metadata.
   */
  def run(metadata: ExaMetadata, iterator: ExaIterator): Unit = {
    val kafkaProperties = KafkaConsumerProperties(iterator.getString(0))
    val partitionId = iterator.getInteger(1)
    val partitionOffset = iterator.getLong(2)
    val partitionNextOffset = partitionOffset + 1L
    val nodeId = metadata.getNodeId
    val vmId = metadata.getVmId
    logger.info(
      s"Starting Kafka consumer for partition '$partitionId' at next offset " +
        s"'$partitionNextOffset' for node '$nodeId' and vm '$vmId'."
    )

    val topic = kafkaProperties.getTopic()
    val topicPartition = new TopicPartition(topic, partitionId)

    val recordDeserialization = kafkaProperties.getRecordFormat() match {
      case "avro" => AvroDeserialization
      case "json" => JsonDeserialization
    }
    val recordFields = kafkaProperties.getRecordFields()
    val derserializer = if (kafkaProperties.getSingleColJson()) {
      recordDeserialization.getSingleColumnJsonDeserializer(kafkaProperties, recordFields)
    } else {
      recordDeserialization.getColumnDeserializer(kafkaProperties, recordFields)
    }
    val kafkaConsumer = KafkaConsumerFactory(kafkaProperties, derserializer, metadata)
    kafkaConsumer.assign(Arrays.asList(topicPartition))
    kafkaConsumer.seek(topicPartition, partitionNextOffset)

    val partitionEndOffsets = kafkaConsumer.endOffsets(Arrays.asList(topicPartition))
    val lastRecordOffset = partitionEndOffsets.get(topicPartition) - 1
    logger.info(
      s"Last Record Offset for partition '$partitionId' is '$lastRecordOffset'."
    )

    val maxRecords = kafkaProperties.getMaxRecordsPerRun()
    val minRecords = kafkaProperties.getMinRecordsPerRun()
    val timeout = kafkaProperties.getPollTimeoutMs()

    try {
      var latestOffset: Long = 0
      var recordCount = 0
      var totalRecordCount = 0

      do {
        val records = kafkaConsumer.poll(Duration.ofMillis(timeout))
        recordCount = records.count()
        totalRecordCount += recordCount
        records.asScala.foreach { record =>
          logger.debug(
            s"Read record from partition '${record.partition()}' at offset " +
              s"'${record.offset()}' with key '${record.key()}' and " +
              s"value '${record.value()}'"
          )

          latestOffset = record.offset()

          val metadata: Seq[Object] = Seq(
            record.partition().asInstanceOf[AnyRef],
            record.offset().asInstanceOf[AnyRef]
          )

          val recordValue = record.value()
          val exasolRow: Seq[Any] = recordValue ++ metadata
          iterator.emit(exasolRow: _*)

        }
        logger.info(
          s"Emitted total '$totalRecordCount' records for partition " +
            s"'$partitionId' in node '$nodeId' and vm '$vmId'."
        )

      } while (latestOffset < lastRecordOffset) //(recordCount >= minRecords && totalRecordCount < maxRecords)
    } catch {
      case exception: Throwable =>
        throw new KafkaConnectorException(
          s"Error consuming Kafka topic '$topic' data. " +
            s"It occurs for partition '$partitionId' in node '$nodeId' and vm '$vmId' " +
            "Cause: " + exception.getMessage(),
          exception
        )
    } finally {
      kafkaConsumer.close();
    }
  }

}
