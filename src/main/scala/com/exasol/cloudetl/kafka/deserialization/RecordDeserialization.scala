package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.apache.kafka.common.serialization.Deserializer

/**
 * A factory dor kafka deserializers
 */
trait RecordDeserialization {

  /**
   * Create a deserializer that transforms the kafka record into a columnar structure.
   * If a field list is provided, only those fields must be included in the seq
   */
  def getColumnDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]]

  /**
   * Create a deserializer that emits the kafka record as a plain Json object
   */
  def getSingleColumnJsonDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]]
}
