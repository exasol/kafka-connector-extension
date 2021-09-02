package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt, Long => JLong}

import com.exasol.ExaMetadata

import org.apache.kafka.common.serialization.{Serializer, StringSerializer}
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.{times, verify, when}
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

class KafkaTopicDataImporterJsonToJsonIT extends KafkaIntegrationTest {

  implicit val serializer: Serializer[String] = new StringSerializer

  override def additionalProperties: Map[String, String] =
    Map("RECORD_FORMAT" -> "json", "AS_JSON_DOC" -> "true")

  test("must deserialize json to exasol row as full record") {
    createCustomTopic(topic)

    val inputRecord1 =
      """
        | {
        |"col_str": "val1",
        |"col_int": 11,
        |"col_object": {"field": "value"}
     }""".stripMargin

    val inputRecord2 =
      """
        |{
        |"col_str": "val2",
        |"col_int": 22,
        |"col_object": { "field": "value"}
     }""".stripMargin

    publishToKafka(topic, inputRecord1)
    publishToKafka(topic, inputRecord2)

    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    val meta = mock[ExaMetadata]
    when(meta.getOutputColumnCount()).thenReturn(3L)
    when(meta.getOutputColumnType(anyInt())).thenAnswer(new Answer[Class[_]]() {
      override def answer(invocation: InvocationOnMock): Class[_] = {
        val columnIndex = invocation.getArguments()(0).asInstanceOf[JInt]
        Seq(
          classOf[String],
          classOf[JInt],
          classOf[JLong]
        )(columnIndex)
      }
    })
    KafkaTopicDataImporter.run(meta, iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(1)).emit(
      jsonMatcher(inputRecord1),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(0))
    )
    verify(iter, times(1)).emit(
      jsonMatcher(inputRecord2),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(1))
    )
  }
}
