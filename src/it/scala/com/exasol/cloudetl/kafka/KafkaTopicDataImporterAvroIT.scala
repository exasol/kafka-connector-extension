package com.exasol.cloudetl.kafka

import scala.jdk.CollectionConverters._

import com.exasol.cloudetl.kafka.KafkaTopicDataImporterAvroIT.schemaRegistryUrl

import io.confluent.kafka.serializers.KafkaAvroSerializer
import org.apache.avro.{AvroRuntimeException, Schema}
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.serialization.Serializer

class KafkaTopicDataImporterAvroIT extends KafkaIntegrationTest {

  override def additionalProperties: Map[String, String] =
    Map("SCHEMA_REGISTRY_URL" -> schemaRegistryUrl)

  implicit val serializer: Serializer[AvroRecord] = {
    val properties = Map("schema.registry.url" -> schemaRegistryUrl)
    val serializer = new KafkaAvroSerializer()
    serializer.configure(properties.asJava, false)
    serializer.asInstanceOf[Serializer[AvroRecord]]
  }
}

case class AvroRecord(var col_str: String, var col_int: Int, var col_long: Long) extends SpecificRecordBase {

  private[this] val avroRecordSchema =
    new Schema.Parser().parse(
      s"""|{
          | "namespace": "com.exasol.cloudetl",
          | "type": "record",
          | "name": "AvroRecordSchemaForIT",
          | "fields": [
          |     {"name": "col_str", "type": "string"},
          |     {"name": "col_int", "type": "int"},
          |     {"name": "col_long", "type": "long"}
          | ]
          |}""".stripMargin
    )

  def this() = this("", 0, 0)

  override def get(index: Int): AnyRef = index match {
    case 0 => col_str
    case 1 => col_int.asInstanceOf[AnyRef]
    case 2 => col_long.asInstanceOf[AnyRef]
    case _ => throw new AvroRuntimeException(s"Unknown index $index!")
  }

  override def put(index: Int, value: Any): Unit = index match {
    case 0 =>
      col_str = value match {
        case (utf8: org.apache.avro.util.Utf8) => utf8.toString
        case _                                 => value.asInstanceOf[String]
      }
    case 1 =>
      col_int = value.asInstanceOf[Int]
    case 2 =>
      col_long = value.asInstanceOf[Long]
    case _ => throw new AvroRuntimeException(s"Unknown index $index!")
  }

  override def getSchema(): Schema = avroRecordSchema
}
object KafkaTopicDataImporterAvroIT {
  val schemaRegistryUrl = "http://localhost:6002"
}
