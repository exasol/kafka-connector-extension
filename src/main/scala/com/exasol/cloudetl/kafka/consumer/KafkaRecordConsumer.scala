package com.exasol.cloudetl.kafka.consumer

import java.time.Duration
import java.util.Arrays

import scala.jdk.CollectionConverters._

import com.exasol.ExaIterator
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka._
import com.exasol.cloudetl.kafka.deserialization._
import com.exasol.errorreporting.ExaError

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.errors._

/**
 * A class that polls data from Kafka topic and emits records into an
 * Exasol table.
 */
class KafkaRecordConsumer(
  properties: KafkaConsumerProperties,
  partitionId: Int,
  partitionStartOffset: Long,
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
      case exception: IllegalStateException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-20")
            .message("Error consuming Kafka topic {{TOPIC}} data. ", topic)
            .message("Consumer is not subscribed to any topic or assigned any partition.")
            .mitigation("Please check that the Kafka topic is available and valid.")
            .toString(),
          exception
        )
      case exception: InvalidTopicException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-21")
            .message(generalErrorMessage(), topic)
            .message("Provided topic is not valid.")
            .mitigation("Please check that the Kafka topic is available and valid.")
            .toString(),
          exception
        )
      case exception: TimeoutException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-22")
            .message(generalErrorMessage(), topic)
            .message("Timeout trying to connect to Kafka brokers.")
            .mitigation(
              "Please ensure that there is network connection between Kafka brokers and Exasol datanode." +
                "Similarly check that Kafka advertised listeners are reachable from Exasol cluster."
            )
            .toString(),
          exception
        )
      case exception: AuthorizationException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-23")
            .message(generalErrorMessage(), topic)
            .message("Consumer or consumer group is not allowed to read given topic. Cause: " + exception.getMessage())
            .mitigation("Please make sure that topic is readable by the this consumer or consumer groups")
            .toString(),
          exception
        )
      case exception: AuthenticationException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-24")
            .message(generalErrorMessage(), topic)
            .message("Failed to authenticate to Kafka cluster. Cause: " + exception.getMessage())
            .mitigation("Please ensure that SASL credentials and mechanisms are correct for authentication.")
            .toString(),
          exception
        )
      case exception: Throwable =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("F-KCE-4")
            .message(generalErrorMessage(), topic)
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

  private[this] def generalErrorMessage(): String =
    "Error consuming Kafka topic {{TOPIC}} data."

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
    records.asScala.foreach { record =>
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
