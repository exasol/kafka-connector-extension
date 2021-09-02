package com.exasol.cloudetl.kafka

import java.lang.Integer
import java.lang.Long

import scala.collection.mutable.HashMap
import scala.jdk.CollectionConverters._

import com.exasol._
import com.exasol.cloudetl.kafka.KafkaConnectorConstants._
import com.exasol.errorreporting.ExaError

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.errors._
import org.apache.kafka.common.serialization.VoidDeserializer

/**
 * The object class that is referenced in the UDF scripts.
 *
 * It reads Kafka topic metadata, partition id and record offset.
 */
object KafkaTopicMetadataReader extends LazyLogging {

  private[this] val PREVIOUS_PARTITION_ID_INDEX: Int = 1
  private[this] val PREVIOUS_OFFSET_INDEX: Int = 2

  /**
   * Reads the Kafka topic partition id-s and the maximum offset for the
   * previously consumed record.
   */
  def run(metadata: ExaMetadata, iterator: ExaIterator): Unit = {
    val kafkaProperties = KafkaConsumerProperties(iterator.getString(KEY_VALUE_PROPERTIES_INDEX), metadata)
    val kafkaConsumer = KafkaConsumerFactory(kafkaProperties, new VoidDeserializer, new VoidDeserializer)
    emitMetadata(iterator, kafkaConsumer, kafkaProperties.getTopic())
  }

  private[this] def emitMetadata[K, V](iterator: ExaIterator, consumer: KafkaConsumer[K, V], topic: String): Unit = {
    val seenPartitionOffsets = getPreviousPartitionOffsets(iterator)
    val topicPartitionIds = getTopicPartitions(consumer, topic)
    emitTopicPartitionOffsets(iterator, consumer, topic, topicPartitionIds, seenPartitionOffsets)
  }

  private[this] def getPreviousPartitionOffsets(iterator: ExaIterator): HashMap[Integer, Long] = {
    val partitionOffsets = HashMap.empty[Integer, Long]
    do {
      val partitionId = iterator.getInteger(PREVIOUS_PARTITION_ID_INDEX)
      val partitionOffset = iterator.getLong(PREVIOUS_OFFSET_INDEX)
      partitionOffsets += (partitionId -> partitionOffset)
    } while (iterator.next())
    partitionOffsets
  }

  private[this] def getTopicPartitions[K, V](consumer: KafkaConsumer[K, V], topic: String): List[Int] =
    try {
      consumer.partitionsFor(topic).asScala.toList.map(_.partition())
    } catch {
      case exception: TimeoutException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-24")
            .message(ERROR_READING_TOPIC_PARTITION, topic)
            .message(TIMEOUT_ERROR_MESSAGE)
            .mitigation(TIMEOUT_ERROR_MITIGATION_1)
            .mitigation(TIMEOUT_ERROR_MITIGATION_2)
            .toString(),
          exception
        )
      case exception: AuthorizationException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-25")
            .message(ERROR_READING_TOPIC_PARTITION, topic)
            .message(AUTHORIZATION_ERROR_MESSAGE + exception.getMessage())
            .mitigation(AUTHORIZATION_ERROR_MITIGATION)
            .toString(),
          exception
        )
      case exception: AuthenticationException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-26")
            .message(ERROR_READING_TOPIC_PARTITION, topic)
            .message(AUTHENTICATION_ERROR_MESSAGE + exception.getMessage())
            .mitigation(AUTHENTICATION_ERROR_MITIGATION)
            .toString(),
          exception
        )
      case exception: Throwable =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("F-KCE-27")
            .message(ERROR_READING_TOPIC_PARTITION, topic)
            .ticketMitigation()
            .toString(),
          exception
        )
    } finally {
      consumer.close()
    }

  private[this] def emitTopicPartitionOffsets[K, V](
    iterator: ExaIterator,
    consumer: KafkaConsumer[K, V],
    topic: String,
    partitionIds: List[Int],
    offsets: HashMap[Integer, Long]
  ): Unit = {
    logger.info(s"Emitting metadata for '${partitionIds.mkString(",")}' topic partitions")
    try {
      partitionIds.foreach { partitionId =>
        val offset: Long = offsets.getOrElse(partitionId, -1L)
        iterator.emit(Integer.valueOf(partitionId), offset)
      }
    } catch {
      case exception: ExaIterationException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("F-KCE-2")
            .message("Error iterating Exasol metadata iterator for topic {{TOPIC}}.", topic)
            .mitigation("Please check that source Exasol table is available.")
            .toString(),
          exception
        )
      case exception: ExaDataTypeException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("F-KCE-3")
            .message("Error emitting metadata information for topic {{TOPIC}}.", topic)
            .mitigation("Please check that Exasol metadata script contains output columns.")
            .toString(),
          exception
        )
    } finally {
      consumer.close()
    }
  }

}
