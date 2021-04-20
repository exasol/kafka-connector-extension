package com.exasol.cloudetl.kafka.deserialization

import scala.jdk.CollectionConverters.CollectionHasAsScala

import com.exasol.common.avro.AvroConverter

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Deserializer

/**
 * Extract a set of fields from an Avro GenericRecord. If no field list is defined,
 * all fields in the record are emitted.
 *
 */
class GenericRecordDeserializer(
  fieldList: Option[Seq[String]],
  deserializer: Deserializer[GenericRecord]
) extends Deserializer[Seq[Any]] {

  private val converter = new AvroConverter()

  final override def deserialize(topic: String, data: Array[Byte]): Seq[Any] = {
    val record = deserializer.deserialize(topic, data)
    val avroFields = record.getSchema.getFields.asScala
    val fieldsToRead = fieldList
      .map(_.map(filterField => Option(record.getSchema.getField(filterField))))
      .getOrElse(avroFields.map(Option(_)))
      .toSeq

    fieldsToRead.map(
      _.map(field => converter.convert(record.get(field.name()), field.schema())).orNull
    )
  }
}
