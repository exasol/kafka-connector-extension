package com.exasol.cloudetl.kafka

import com.exasol.ExaIterator
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaConnectorConstants.KEY_VALUE_PROPERTIES_INDEX
import com.exasol.cloudetl.kafka.consumer.KafkaRecordConsumer

import com.typesafe.scalalogging.LazyLogging

/**
 * This object is referenced from the UDF script that imports data from
 * a Kafka topic into an Exasol table.
 */
object KafkaTopicDataImporter extends LazyLogging {

  private[this] val PARTITION_ID_INDEX: Int = 1
  private[this] val MAXIMUM_OFFSET_INDEX: Int = 2

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
    val kafkaProperties = KafkaConsumerProperties(iterator.getString(KEY_VALUE_PROPERTIES_INDEX), metadata)
    val partitionId = iterator.getInteger(PARTITION_ID_INDEX)
    val partitionNextOffset = iterator.getLong(MAXIMUM_OFFSET_INDEX) + 1L
    val outputColumnCount = metadata.getOutputColumnCount().toInt
    val nodeId = metadata.getNodeId()
    val vmId = metadata.getVmId()
    logger.info(
      s"Starting Kafka consumer for partition '$partitionId' at next offset " +
        s"'$partitionNextOffset' for node '$nodeId' and vm '$vmId'."
    )
    new KafkaRecordConsumer(
      kafkaProperties,
      partitionId,
      partitionNextOffset,
      outputColumnCount,
      nodeId,
      vmId
    ).emit(iterator)
  }

}
