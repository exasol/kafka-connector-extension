package com.exasol.cloudetl.kafka.deserialization

import scala.jdk.CollectionConverters.CollectionHasAsScala

import com.exasol.common.avro.AvroConverter

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Deserializer

/**
 * Extract a set of fields from an Avro GenericRecord. If no field list is defined,
 * all fields in the record are emitted.
 */
class GenericRecordDeserializer(
  fieldSpecs: Seq[FieldSpecification],
  deserializer: Deserializer[GenericRecord]
) extends Deserializer[Map[FieldSpecification, Seq[Any]]] {

  private val converter = new AvroConverter()

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  final override def deserialize(
    topic: String,
    data: Array[Byte]
  ): Map[FieldSpecification, Seq[Any]] = {
    val record = deserializer.deserialize(topic, data)

    fieldSpecs.map {
      case fieldSpec: AllFieldsSpecification =>
        (
          fieldSpec,
          record.getSchema.getFields.asScala
            .map(
              recordField =>
                converter convert (record.get(recordField.name()), recordField.schema())
            )
            .toSeq
        )
      case fieldSpec: ConcreteField =>
        (
          fieldSpec,
          Seq(
            Option(record.getSchema.getField(fieldSpec.fieldName))
              .map(field => converter.convert(record.get(field.name()), field.schema()))
              .orNull
          )
        )
      case fieldSpec: FullRecord => (fieldSpec, Seq(converter.convert(record, record.getSchema)))
    }.toMap
  }
}
