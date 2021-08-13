package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.errorreporting.ExaError

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
              case _ =>
                throw new KafkaConnectorException(
                  ExaError
                    .messageBuilder("E-KCE-13")
                    .message("Field reference can only contain 'key' or 'value' fields.")
                    .mitigation("Please check that the provided field reference is correct.")
                    .toString()
                )
            }
          case _ =>
            throw new KafkaConnectorException(
              ExaError
                .messageBuilder("E-KCE-14")
                .message(s"Field reference {{REFERENCE}} does not have the correct format.", anythingElse)
                .mitigation(
                  "It must be one of [key, value, key.*, value.*, key.fieldName, value.fieldName, timestamp] values."
                )
                .toString()
            )
        }
    }

  def get(columnString: String): Seq[GlobalFieldSpecification] =
    get(columnString.split(",").map(_.trim).toSeq)
}
