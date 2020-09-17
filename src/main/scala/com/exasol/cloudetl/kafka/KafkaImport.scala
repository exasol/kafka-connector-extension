package com.exasol.cloudetl.scriptclasses

import java.time.Duration
import java.util.Arrays

import scala.collection.JavaConverters._

import com.exasol.ExaIterator
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaConsumerProperties
import com.exasol.common.avro.AvroRow

import com.typesafe.scalalogging.LazyLogging
import org.apache.kafka.common.TopicPartition

object KafkaImport extends LazyLogging {

  def run(metadata: ExaMetadata, iterator: ExaIterator): Unit = {
    val kafkaProperties = KafkaConsumerProperties(iterator.getString(0))
    val partitionId = iterator.getInteger(1)
    val partitionOffset = iterator.getLong(2)
    val partitionNextOffset = partitionOffset + 1L
    val nodeId = metadata.getNodeId
    val vmId = metadata.getVmId
    logger.info(
      s"Kafka consumer for node=$nodeId, vm=$vmId using " +
        s"partition=$partitionId and startOffset=$partitionNextOffset"
    )

    val topic = kafkaProperties.getTopic()
    val topicPartition = new TopicPartition(topic, partitionId)
    val kafkaConsumer = kafkaProperties.build(metadata)
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
            s"Read record partition=${record.partition()} offset=${record.offset()} " +
              s"key=${record.key()} value=${record.value()}"
          )
          val metadata: Seq[Object] = Seq(
            record.partition().asInstanceOf[AnyRef],
            record.offset().asInstanceOf[AnyRef]
          )
          val avroRow = AvroRow(record.value()).getValues().map(_.asInstanceOf[AnyRef])
          val exasolRow: Seq[Object] = metadata ++ avroRow
          iterator.emit(exasolRow: _*)
        }
        logger.info(
          s"Emitted total=$totalRecordCount records in " +
            s"node=$nodeId, vm=$vmId, partition=$partitionId"
        )
      } while (recordCount >= minRecords && totalRecordCount < maxRecords)
    } catch {
      case exception: Throwable =>
        logger.error(
          s"Could not consume data from Kafka topic: '$topic', partition: '$partitionId' " +
            s" in node: '$nodeId' and vm: '$vmId'"
        )
        throw new RuntimeException(
          "Error consuming Kafka topic data. Cause: " + exception.getMessage(),
          exception
        )
    } finally {
      kafkaConsumer.close();
    }
  }

}
