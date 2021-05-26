package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.apache.kafka.common.serialization.Deserializer

/**
 * A factory for Kafka deserializers.
 */
trait RecordDeserialization {

  /**
   * Creates a serializer for the given field specifications. The specs can be either referencing
   * key or value records, but not both. This is mainly for checking correct settings for
   * the format implemented and making sure no invalid fieldspecifications are not used.
   */
  def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[Any]]]
}
