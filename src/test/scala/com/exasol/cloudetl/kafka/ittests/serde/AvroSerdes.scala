package com.exasol.cloudetl.kafka.serde

import java.util.Map

import scala.language.implicitConversions

import com.sksamuel.avro4s.RecordFormat
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.Serializer
import org.apache.kafka.common.serialization.Deserializer
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde

trait AvroSerdes {

  implicit def valueAvroSerde[T >: Null](schemaRegistryUrl: String)(implicit
    recordFormat: RecordFormat[T]
  ): ValueSerde[T] =
    kafkaSerde(genericAvroSerde(schemaRegistryUrl, false), recordFormat).asInstanceOf[ValueSerde[T]]

  private[kafka] def kafkaSerde[T >: Null](
    genericAvroSerde: GenericAvroSerde,
    recordFormat: RecordFormat[T]
  ): Serde[T] = Serdes.serdeFrom(
    new Serializer[T] {
      override def serialize(topic: String, data: T): Array[Byte] =
        if (data == null) {
          null
        } else {
          genericAvroSerde.serializer().serialize(topic, recordFormat.to(data))
        }
    },
    new Deserializer[T] {
      override def deserialize(topic: String, data: Array[Byte]): T =
        if (data == null) {
          null
        } else {
          recordFormat.from(genericAvroSerde.deserializer().deserialize(topic, data))
        }
    }
  )

  private[kafka] def genericAvroSerde(schemaRegistryUrl: String, isKey: Boolean): GenericAvroSerde = {
    val serde = new GenericAvroSerde()
    val properties = Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl)
    serde.configure(properties, isKey)
    serde
  }

}

object AvroSerdes {
  object Implicits extends AvroSerdes
}
