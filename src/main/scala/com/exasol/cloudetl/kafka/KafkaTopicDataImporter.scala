package com.exasol.cloudetl.kafka

import java.time.Duration
import java.util.Arrays

import scala.jdk.CollectionConverters._

import com.exasol.{ExaIterator, ExaMetadata}
import com.exasol.cloudetl.kafka.deserialization.{DeserializationFactory, FieldParser, RowBuilder}

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
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
    val kafkaProperties = KafkaConsumerProperties(iterator.getString(0), metadata)
    val partitionId = iterator.getInteger(1)
    val partitionOffset = iterator.getLong(2)
    val partitionNextOffset = partitionOffset + 1L
    val nodeId = metadata.getNodeId()
    val vmId = metadata.getVmId()
    logger.info(
      s"Starting Kafka consumer for partition '$partitionId' at next offset " +
        s"'$partitionNextOffset' for node '$nodeId' and vm '$vmId'."
    )

    val topic = kafkaProperties.getTopic()
    val topicPartition = new TopicPartition(topic, partitionId)

    val fieldSpecs = FieldParser.get(kafkaProperties.getRecordFields())
    val recordDeserializers = DeserializationFactory.getSerializers(fieldSpecs, kafkaProperties)
    val kafkaConsumer = KafkaConsumerFactory(
      kafkaProperties,
      recordDeserializers.keyDeserializer,
      recordDeserializers.valueDeserializer
    )
    kafkaConsumer.assign(Arrays.asList(topicPartition))
    kafkaConsumer.seek(topicPartition, partitionNextOffset)

    val maxRecords = kafkaProperties.getMaxRecordsPerRun()
    val minRecords = kafkaProperties.getMinRecordsPerRun()
    val timeout = kafkaProperties.getPollTimeoutMs()

    try {
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

          val kafkaMetadata: Seq[Object] = Seq(
            record.partition().asInstanceOf[AnyRef],
            record.offset().asInstanceOf[AnyRef]
          )

          val rowValues = RowBuilder.buildRow(
            fieldSpecs,
            record,
            metadata.getOutputColumnCount.toInt - kafkaMetadata.size
          )

          val exasolRow: Seq[Any] = rowValues ++ kafkaMetadata
          iterator.emit(exasolRow: _*)
        }
        logger.info(
          s"Emitted total '$totalRecordCount' records for partition " +
            s"'$partitionId' in node '$nodeId' and vm '$vmId'."
        )
      } while (recordCount >= minRecords && totalRecordCount < maxRecords)
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
