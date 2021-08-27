package com.exasol.cloudetl.kafka.consumer

import java.time.Duration
import java.util.Arrays

import scala.jdk.CollectionConverters._

import com.exasol.ExaIterator
import com.exasol.cloudetl.kafka._
import com.exasol.cloudetl.kafka.deserialization._
import com.exasol.errorreporting.ExaError

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition

/**
 * A class that polls data from Kafka topic and emits records into an
 * Exasol table.
 */
class KafkaRecordConsumer(
  properties: KafkaConsumerProperties,
  partitionId: Int,
  partitionStartOffset: Long,
  outputColumnTypes: Seq[Class[_]],
  tableColumnCount: Int,
  nodeId: Long,
  vmId: String
) extends RecordConsumer
    with LazyLogging {

  private[this] val topic = properties.getTopic()
  private[this] val consumer = getRecordConsumer()
  private[this] val partitionEndOffset = getPartitionEndOffset()
  private[this] val maxRecordsPerRun = properties.getMaxRecordsPerRun()
  private[this] val minRecordsPerRun = properties.getMinRecordsPerRun()
  private[this] val timeout = Duration.ofMillis(properties.getPollTimeoutMs())
  private[this] val recordFieldSpecifications = FieldParser.get(properties.getRecordFields())

  /**
   * @inheritdoc
   */
  override final def emit(iterator: ExaIterator): Unit = {
    var recordOffset = partitionStartOffset
    var recordCount = 0
    var totalRecordCount = 0L
    try {
      do {
        val records = consumer.poll(timeout)
        recordCount = records.count()
        totalRecordCount += recordCount
        recordOffset = updateRecordOffset(emitRecords(iterator, records))
        logger.info(
          s"Polled '$recordCount' records, total '$totalRecordCount' records for partition " +
            s"'$partitionId' in node '$nodeId' and vm '$vmId'."
        )
      } while (shouldContinue(recordOffset, recordCount, totalRecordCount))
    } catch {
      case exception: Throwable =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("F-KCE-4")
            .message("Error consuming Kafka topic {{TOPIC}} data. ", topic)
            .message("It occurs for partition {{PARTITION_ID}} in node {{NODE_ID}} and vm {{VM_ID}}.")
            .parameter("PARTITION_ID", String.valueOf(partitionId))
            .parameter("NODE_ID", String.valueOf(nodeId))
            .parameter("VM_ID", vmId)
            .ticketMitigation()
            .toString(),
          exception
        )
    } finally {
      consumer.close()
    }
  }

  private[this] def updateRecordOffset(currentOffset: Long): Long =
    if (currentOffset == -1) {
      getPartitionCurrentOffset()
    } else {
      currentOffset
    }

  private[this] type FieldType = Map[FieldSpecification, Seq[Any]]

  // This is okay, since it is only overridden in tests.
  @SuppressWarnings(Array("org.wartremover.contrib.warts.UnsafeInheritance"))
  protected def getRecordConsumer(): KafkaConsumer[FieldType, FieldType] = {
    val topicPartition = new TopicPartition(topic, partitionId)
    val recordFields = FieldParser.get(properties.getRecordFields())
    val recordDeserializers = DeserializationFactory.getSerializers(recordFields, properties)
    val consumer = KafkaConsumerFactory(
      properties,
      recordDeserializers.keyDeserializer,
      recordDeserializers.valueDeserializer
    )
    consumer.assign(Arrays.asList(topicPartition))
    consumer.seek(topicPartition, partitionStartOffset)
    consumer
  }

  private[this] def emitRecords(
    iterator: ExaIterator,
    records: ConsumerRecords[FieldType, FieldType]
  ): Long = {
    var lastRecordOffset = -1L
    val fieldConverter = new FieldConverter(outputColumnTypes)
    records.asScala.foreach { record =>
      lastRecordOffset = record.offset()
      val metadata: Seq[Object] = Seq(
        record.partition().asInstanceOf[AnyRef],
        record.offset().asInstanceOf[AnyRef]
      )
      val columnsCount = tableColumnCount - metadata.size
      val rowValues = RowBuilder.buildRow(recordFieldSpecifications, record, columnsCount)
      val row: Seq[Any] = rowValues ++ metadata
      val converted_row: Seq[Any] = fieldConverter.convertRow(row)
      iterator.emit(converted_row: _*)
    }
    lastRecordOffset
  }

  private[this] def shouldContinue(
    recordOffset: Long,
    recordCount: Int,
    totalRecordCount: Long
  ): Boolean =
    (properties
      .isConsumeAllOffsetsEnabled() && recordOffset < partitionEndOffset) ||
      (recordCount >= minRecordsPerRun && totalRecordCount < maxRecordsPerRun)

  private[this] def getPartitionCurrentOffset(): Long = {
    val topicPartition = new TopicPartition(topic, partitionId)
    val currentOffset = consumer.position(topicPartition) - 1
    logger.info(s"The current record offset for partition '$partitionId' is '$currentOffset'.")
    currentOffset
  }

  private[this] def getPartitionEndOffset(): Long = {
    val topicPartition = new TopicPartition(topic, partitionId)
    val partitionEndOffsets = consumer.endOffsets(Arrays.asList(topicPartition))
    val endOffset = partitionEndOffsets.get(topicPartition) - 1
    logger.info(s"The last record offset for partition '$partitionId' is '$endOffset'.")
    endOffset
  }

}
