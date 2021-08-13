package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}
import com.exasol.errorreporting.ExaError

import org.apache.kafka.common.serialization.Deserializer

object DeserializationFactory {

  final case class RecordDeserializers(
    keyDeserializer: Deserializer[Map[FieldSpecification, Seq[Any]]],
    valueDeserializer: Deserializer[Map[FieldSpecification, Seq[Any]]]
  )

  def getSerializers(
    fieldSpecs: Seq[GlobalFieldSpecification],
    kafkaProperties: KafkaConsumerProperties
  ): RecordDeserializers = {

    val keyFieldSpecs = fieldSpecs.flatMap {
      case keySpec: KeySpecification => Option(keySpec)
      case _                         => None
    }

    val valueFieldSpecs = fieldSpecs.flatMap {
      case valueSpec: ValueSpecification => Option(valueSpec)
      case _                             => None
    }

    val keyDeserializer = Option(keyFieldSpecs)
      .filter(_.nonEmpty)
      .map { keyFields =>
        getDeserialization(kafkaProperties.getRecordKeyFormat())
          .getDeserializer(kafkaProperties, keyFields)
      }
      .getOrElse(IgnoreKeyDeserializer)

    val valueDeserializer = getDeserialization(kafkaProperties.getRecordValueFormat())
      .getDeserializer(kafkaProperties, valueFieldSpecs)

    RecordDeserializers(keyDeserializer, valueDeserializer)
  }

  def getDeserialization(format: String): RecordDeserialization =
    format match {
      case "avro"   => AvroDeserialization
      case "json"   => JsonDeserialization
      case "string" => StringDeserialization
      case _ =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-19")
            .message("The format {{FORMAT}} is not supported.", format)
            .mitigation("Please use one of 'avro', 'json' or 'string' formats.")
            .toString()
        )
    }
}

/**
 * If we don't need the key we just return an empty map.
 */
object IgnoreKeyDeserializer extends Deserializer[Map[FieldSpecification, Seq[Any]]] {
  override def deserialize(topic: String, data: Array[Byte]): Map[FieldSpecification, Seq[Any]] =
    Map.empty[FieldSpecification, Seq[Any]]
}
