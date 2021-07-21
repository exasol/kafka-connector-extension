package com.exasol.cloudetl.kafka.deserialization

import scala.jdk.CollectionConverters.CollectionHasAsScala

import com.exasol.common.avro.AvroConverter

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Deserializer

/**
 * Extract a set of fields from an Avro [[org.apache.avro.generic.GenericRecord]].
 *
 * If no field list is defined, all fields in the record are emitted.
 */
class GenericRecordDeserializer(
  fieldSpecs: Seq[FieldSpecification],
  deserializer: Deserializer[GenericRecord]
) extends Deserializer[Map[FieldSpecification, Seq[Any]]] {

  private[this] val converter = new AvroConverter()

  override final def deserialize(
    topic: String,
    data: Array[Byte]
  ): Map[FieldSpecification, Seq[Any]] = {
    val record = deserializer.deserialize(topic, data)
    val recordSchema = record.getSchema()

    fieldSpecs.map {
      case fieldSpec: AllFieldsSpecification =>
        (
          fieldSpec,
          recordSchema.getFields.asScala
            .map(recordField => converter.convert(record.get(recordField.name()), recordField.schema()))
            .toSeq
        )
      case fieldSpec: ConcreteField =>
        (
          fieldSpec,
          Seq(
            Option(recordSchema.getField(fieldSpec.fieldName))
              .map(field => converter.convert(record.get(field.name()), field.schema()))
              .orNull
          )
        )
      case fieldSpec: FullRecord => (fieldSpec, Seq(converter.convert(record, record.getSchema)))
    }.toMap
  }
}
