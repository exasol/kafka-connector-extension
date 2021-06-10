package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt}
import java.lang.{Long => JLong}

import scala.jdk.CollectionConverters._

import com.exasol.ExaDataTypeException
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaTopicDataImporterAvroIT.schemaRegistryUrl

import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.Serializer
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.clients.producer.ProducerRecord
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.{times, verify, when}

class KafkaTopicDataImporterNullValueIT extends KafkaIntegrationTest {
  override def additionalProperties: Map[String, String] =
    Map("SCHEMA_REGISTRY_URL" -> schemaRegistryUrl)

  implicit val serializer: Serializer[String] = {
    val properties = Map("schema.registry.url" -> schemaRegistryUrl)
    val serializer = new StringSerializer()
    serializer.configure(properties.asJava, false)
    serializer
  }

  test("emit fills columns with null if record value is null") {
    createCustomTopic(topic)
    implicit val serializer = new StringSerializer
    publishToKafka(new ProducerRecord(topic, null: String))
    publishToKafka(new ProducerRecord(topic, null: String))

    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    val meta = mock[ExaMetadata]
    when(meta.getOutputColumnCount()).thenReturn(4)
    KafkaTopicDataImporter.run(meta, iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(1)).emit(null, null, JInt.valueOf(0), JLong.valueOf(0))
    verify(iter, times(1)).emit(null, null, JInt.valueOf(0), JLong.valueOf(1))
  }

}
