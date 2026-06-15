package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.apache.kafka.common.serialization.Deserializer

object RecordKey extends KeySpecification with FullRecord
object RecordValue extends ValueSpecification with FullRecord
object RecordKeyFields extends KeySpecification with AllFieldsSpecification
object RecordValueFields extends ValueSpecification with AllFieldsSpecification
case object TimestampField extends GlobalFieldSpecification

object AvroDeserialization extends RecordDeserialization {
  override def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[AnyRef]]] =
    AvroDeserializationImpl.getDeserializer(properties, fieldSpecs)
}

object JsonDeserialization extends RecordDeserialization {
  override def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[AnyRef]]] =
    JsonDeserializationImpl.getDeserializer(properties, fieldSpecs)
}

object StringDeserialization extends RecordDeserialization {
  override def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[AnyRef]]] =
    StringDeserializationImpl.getDeserializer(properties, fieldSpecs)
}

object IgnoreKeyDeserializer extends Deserializer[Map[FieldSpecification, Seq[AnyRef]]] {
  override def deserialize(topic: String, data: Array[Byte]): Map[FieldSpecification, Seq[AnyRef]] =
    Map.empty[FieldSpecification, Seq[AnyRef]]
}
