package com.exasol.cloudetl.kafka

import com.exasol.ExaIterator
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.consumer.KafkaRecordConsumer
import com.exasol.cloudetl.kafka.deserialization._

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.clients.consumer.KafkaConsumer
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
    val partitionNextOffset = iterator.getLong(2) + 1L
    val outputColumnCount = metadata.getOutputColumnCount().toInt
    val nodeId = metadata.getNodeId()
    val vmId = metadata.getVmId()
    logger.info(
      s"Starting Kafka consumer for partition '$partitionId' at next offset " +
        s"'$partitionNextOffset' for node '$nodeId' and vm '$vmId'."
    )
    val recordConsumer = getRecordConsumer(kafkaProperties, partitionId, partitionNextOffset)
    KafkaRecordConsumer(
      kafkaProperties,
      partitionId,
      partitionNextOffset,
      outputColumnCount,
      nodeId,
      vmId,
      recordConsumer
    ).emit(iterator)
  }

  private[this] def getRecordConsumer(
    properties: KafkaConsumerProperties,
    partitionId: Int,
    partitionStartOffset: Long
  ): KafkaConsumer[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]] = {
    val topic = properties.getTopic()
    val topicPartition = new TopicPartition(topic, partitionId)
    val recordFields = FieldParser.get(properties.getRecordFields())
    val recordDeserializers = DeserializationFactory.getSerializers(recordFields, properties)
    val consumer = KafkaConsumerFactory(
      properties,
      recordDeserializers.keyDeserializer,
      recordDeserializers.valueDeserializer
    )
    consumer.assign(java.util.Arrays.asList(topicPartition))
    consumer.seek(topicPartition, partitionStartOffset)
    consumer
  }

}
