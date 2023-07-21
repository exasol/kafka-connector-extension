package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.cloudetl.kafka.deserialization.JsonDeserializer.{jsonNodeToObject, objectMapper}
import com.exasol.errorreporting.ExaError

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.fasterxml.jackson.databind.node.JsonNodeType.{BOOLEAN, NUMBER, STRING}
import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

class JsonDeserializer(fieldSpecs: Seq[FieldSpecification], stringDeserializer: StringDeserializer)
    extends Deserializer[Map[FieldSpecification, Seq[Any]]] {

  override final def deserialize(topic: String, data: Array[Byte]): Map[FieldSpecification, Seq[Any]] = {
    val tree = objectMapper.readTree(stringDeserializer.deserialize(topic, data))
    fieldSpecs.map {
      case fieldSpec: ConcreteField =>
        (fieldSpec, Seq(Option(tree.get(fieldSpec.fieldName)).map(jsonNodeToObject).orNull))
      case fieldSpec: FullRecord => (fieldSpec, Seq(objectMapper.writeValueAsString(tree)))
      case _ =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("E-KCE-15")
            .message("JSON records can only be used as full record or for extracting explicit fields.")
            .mitigation("Please check that the provided JSON record specification is correct.")
            .toString()
        )
    }.toMap
  }
}

object JsonDeserializer {

  private val objectMapper = new ObjectMapper

  private def jsonNodeToObject(jsonNode: JsonNode): Any =
    jsonNode.getNodeType match {
      case STRING  => jsonNode.asText()
      case NUMBER  => jsonNode.numberValue()
      case BOOLEAN => jsonNode.asBoolean()
      case _       => jsonNode.toString
    }
}
