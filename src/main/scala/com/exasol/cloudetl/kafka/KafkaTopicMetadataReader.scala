package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt}
import java.lang.{Long => JLong}

import scala.collection.mutable.HashMap
import scala.jdk.CollectionConverters._

import com.exasol._
import com.exasol.errorreporting.ExaError

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.common.serialization.VoidDeserializer

/**
 * The object class that is referenced in the UDF scripts.
 *
 * It reads Kafka topic metadata, partition id and record offset.
 */
object KafkaTopicMetadataReader extends LazyLogging {

  /**
   * Reads the Kafka topic partition id-s and the maximum offset for the
   * previously consumed record.
   */
  def run(metadata: ExaMetadata, iterator: ExaIterator): Unit = {
    val kafkaProperties = KafkaConsumerProperties(iterator.getString(0), metadata)
    val topic = kafkaProperties.getTopic()
    val seenPartitionOffsets = HashMap.empty[JInt, JLong]
    do {
      val partitionId = iterator.getInteger(1)
      val partitionOffset = iterator.getLong(2)
      seenPartitionOffsets += (partitionId -> partitionOffset)
    } while (iterator.next())

    val kafkaConsumer = KafkaConsumerFactory(kafkaProperties, new VoidDeserializer, new VoidDeserializer)
    val topicPartitions = kafkaConsumer.partitionsFor(topic).asScala.toList.map(_.partition())
    logger.info(s"Reading metadata for '${topicPartitions.mkString(",")}' topic partitions")
    try {
      topicPartitions.foreach { partitionId =>
        val offset: JLong = seenPartitionOffsets.getOrElse(partitionId, -1L)
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
            .message("Error iterating Exasol metadata iterator for topic {{TOPIC}}.", topic)
            .mitigation("Please check that Exasol metadata script contains output columns.")
            .toString(),
          exception
        )
    } finally {
      kafkaConsumer.close()
    }

  }

}
