package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException

/**
 * Parse the fields specified to be extracted from a single Kafka record.
 */
object FieldParser {

  private val FieldReference = raw"(key|value)\.([\S]+)+".r

  def get(fields: Seq[String]): Seq[GlobalFieldSpecification] =
    fields.map {
      case "key.*"     => RecordKeyFields
      case "value.*"   => RecordValueFields
      case "timestamp" => TimestampField
      case "key"       => RecordKey
      case "value"     => RecordValue
      case anythingElse =>
        anythingElse match {
          case FieldReference(keyOrValue, fieldName) =>
            keyOrValue match {
              case "key"   => RecordKeyField(fieldName)
              case "value" => RecordValueField(fieldName)
              case _       => throw new KafkaConnectorException("Regex matched so this cannot be")
            }
          case _ =>
            throw new KafkaConnectorException(
              s"Field reference $anythingElse " +
                s"does not have the correct format. It must be one of " +
                s"[key, value, key.*, value.*, key.fieldName, value.fieldName, timestamp]"
            )
        }
    }

  def get(columnString: String): Seq[GlobalFieldSpecification] =
    get(columnString.split(",").map(_.trim).toSeq)
}
