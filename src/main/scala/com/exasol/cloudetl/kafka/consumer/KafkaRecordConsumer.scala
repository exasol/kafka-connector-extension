package com.exasol.cloudetl.kafka.consumer

import java.time.Duration
import java.util.Arrays

import scala.jdk.CollectionConverters._

import com.exasol.ExaIterator
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka._
import com.exasol.cloudetl.kafka.deserialization._

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

/**
 * A class that polls data from Kafka topic and emits records into an
 * Exasol table.
 */
final case class KafkaRecordConsumer(
  properties: KafkaConsumerProperties,
  partitionId: Int,
  partitionStartOffset: Long,
  tableColumnCount: Int,
  nodeId: Long,
  vmId: String,
  consumer: KafkaConsumer[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]]
) extends LazyLogging {

  private[this] val topic = properties.getTopic()
  private[this] val partitionEndOffset = getPartitionEndOffset()
  private[this] val maxRecordsPerRun = properties.getMaxRecordsPerRun()
  private[this] val minRecordsPerRun = properties.getMinRecordsPerRun()
  private[this] val timeout = Duration.ofMillis(properties.getPollTimeoutMs())
  private[this] val recordFieldSpecifications = FieldParser.get(properties.getRecordFields())

  /**
   * Emits Kafka polled records as rows for Exasol table.
   *
   * @param iterator an Exasol iterator for emitting
   */
  def emit(iterator: ExaIterator): Unit = {
    var recordOffset = 0L
    var recordCount = 0
    var totalRecordCount = 0L
    try {
      do {
        val records = consumer.poll(timeout)
        recordCount = records.count()
        totalRecordCount += recordCount
        recordOffset = emitRecords(iterator, records)
        logger.info(
          s"Polled '$recordCount' records, total '$totalRecordCount' records for partition " +
            s"'$partitionId' in node '$nodeId' and vm '$vmId'."
        )
      } while (shouldContinue(recordOffset, recordCount, totalRecordCount))
    } catch {
      case exception: Throwable =>
        throw new KafkaConnectorException(
          s"Error consuming Kafka topic '$topic' data. " +
            s"It occurs for partition '$partitionId' in node '$nodeId' and vm '$vmId' " +
            "Cause: " + exception.getMessage(),
          exception
        )
    } finally {
      consumer.close();
    }
  }

  private[this] def emitRecords(
    iterator: ExaIterator,
    r: ConsumerRecords[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]]
  ): Long = {
    var lastRecordOffset = -1L
    r.asScala.foreach { record =>
      lastRecordOffset = record.offset()
      val metadata: Seq[Object] = Seq(
        record.partition().asInstanceOf[AnyRef],
        record.offset().asInstanceOf[AnyRef]
      )
      val columnsCount = tableColumnCount - metadata.size
      val rowValues = RowBuilder.buildRow(recordFieldSpecifications, record, columnsCount)
      val rows: Seq[Any] = rowValues ++ metadata
      iterator.emit(rows: _*)
    }
    lastRecordOffset
  }

  private[this] def shouldContinue(
    recordOffset: Long,
    recordCount: Int,
    totalRecordCount: Long
  ): Boolean =
    (properties
      .isConsumeAllOffsetsEnabled() && recordOffset >= 0 && recordOffset < partitionEndOffset) ||
      (recordCount >= minRecordsPerRun && totalRecordCount < maxRecordsPerRun)

  private[this] def getPartitionEndOffset(): Long = {
    val topicPartition = new TopicPartition(topic, partitionId)
    val partitionEndOffsets = consumer.endOffsets(Arrays.asList(topicPartition))
    val lastOffset = partitionEndOffsets.get(topicPartition) - 1
    logger.info(s"The last record offset for partition '$partitionId' is '$lastOffset'.")
    lastOffset
  }
}
