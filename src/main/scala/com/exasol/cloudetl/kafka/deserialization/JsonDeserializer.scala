package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.cloudetl.kafka.deserialization.JsonDeserializer.{jsonNodeToObject, objectMapper}

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.JsonNodeType.{BOOLEAN, NUMBER, STRING}
import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

class JsonDeserializer(
  fieldSpecs: Seq[FieldSpecification],
  stringDeserializer: StringDeserializer
) extends Deserializer[Map[FieldSpecification, Seq[Any]]] {

  final override def deserialize(
    topic: String,
    data: Array[Byte]
  ): Map[FieldSpecification, Seq[Any]] = {
    val tree =
      objectMapper.readTree(stringDeserializer.deserialize(topic, data))

    fieldSpecs.map {
      case fieldSpec: ConcreteField =>
        (
          fieldSpec,
          Seq(
            Option(tree.get(fieldSpec.fieldName))
              .map(jsonNodeToObject)
              .orNull
          )
        )
      case fieldSpec: FullRecord =>
        (fieldSpec, Seq(objectMapper.writeValueAsString(tree)))
      case _ =>
        throw new KafkaConnectorException(
          "JSON records only be used for extracting explicit fields"
        )
    }.toMap
  }
}

object JsonDeserializer {

  private val objectMapper = new ObjectMapper

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  private def jsonNodeToObject(jsonNode: JsonNode): Any =
    jsonNode.getNodeType match {
      case STRING  => jsonNode.asText()
      case NUMBER  => jsonNode.numberValue()
      case BOOLEAN => jsonNode.asBoolean()
      case _       => jsonNode.toString
    }
}
