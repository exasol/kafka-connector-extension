package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.deserialization.JsonDeserializer.objectMapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeType.{BOOLEAN, NUMBER, STRING}
import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

class JsonDeserializer(
  fields: Seq[String],
  stringDeserializer: StringDeserializer
) extends Deserializer[Seq[Any]] {

  @SuppressWarnings(Array("org.wartremover.warts.ToString"))
  final override def deserialize(topic: String, data: Array[Byte]): Seq[Any] = {
    val tree =
      objectMapper.readTree(stringDeserializer.deserialize(topic, data))

    fields
      .map(
        field =>
          Option(tree.get(field))
            .map(
              jsonNode =>
                jsonNode.getNodeType match {
                  case STRING  => jsonNode.asText()
                  case NUMBER  => jsonNode.numberValue()
                  case BOOLEAN => jsonNode.asBoolean()
                  case _       => jsonNode.toString
              }
            )
            .orNull
      )
  }
}

object JsonDeserializer {
  private val objectMapper = new ObjectMapper
}
