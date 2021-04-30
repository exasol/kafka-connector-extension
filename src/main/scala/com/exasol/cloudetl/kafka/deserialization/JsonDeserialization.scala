package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}

import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

/**
 * Creates deserializers for JSON records.
 */
object JsonDeserialization extends RecordDeserialization {

  override def getSingleColumnJsonDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]] = {
    if (fields.isDefined) {
      throw new KafkaConnectorException("""Specifying fields when dumping the Json into the DB
                                          |is currently not supported""".stripMargin)
    }
    new ToStringDeserializer(new StringDeserializer)
  }

  override def getColumnDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]] =
    fields
      .filter(_.nonEmpty)
      .map(new JsonDeserializer(_, new StringDeserializer))
      .getOrElse(
        throw new KafkaConnectorException(
          """Record format JSON requires RECORD_FIELDS to be set to a comma separated list
           of fields to guarantee field order"""
        )
      )
}
