package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt, Long => JLong}

import com.exasol.ExaMetadata

import org.apache.kafka.common.serialization.{Serializer, StringSerializer}
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.{times, verify, when}
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

class KafkaTopicDataImporterJsonToColumnsIT extends KafkaIntegrationTest {

  implicit val serializer: Serializer[String] = new StringSerializer

  override def additionalProperties: Map[String, String] = Map(
    "RECORD_FORMAT" -> "JSON",
    "RECORD_FIELDS" -> "value.col_str,value.col_int,value.col_object"
  )

  test("must deserialize json to exasol row") {
    createCustomTopic(topic)
    publishToKafka(
      topic,
      """
        | {
        |   "col_str": "val1",
        |   "col_int": 11,
        |   "col_ignore": "not_to_include",
        |   "col_object": { "field": "value"}
        |}""".stripMargin
    )

    publishToKafka(
      topic,
      """
        | {
        |   "col_str": "val2",
        |   "col_int": 22,
        |   "col_ignore": "not_to_include"
        |}""".stripMargin
    )

    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    val meta = mock[ExaMetadata]
    when(meta.getOutputColumnCount()).thenReturn(5L)
    when(meta.getOutputColumnType(anyInt())).thenAnswer(
      new Answer[Class[_]]() {
        override def answer(invocation: InvocationOnMock): Class[_] = {
          val columnIndex = invocation.getArguments()(0).asInstanceOf[JInt]
          Seq(
            classOf[String],
            classOf[JInt],
            classOf[String],
            classOf[JInt],
            classOf[JLong],
          )(columnIndex)
        }
      })
    KafkaTopicDataImporter.run(meta, iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(1)).emit(
      "val1",
      JInt.valueOf(11),
      """{"field":"value"}""",
      JInt.valueOf(0),
      JLong.valueOf(0)
    )
    verify(iter, times(1)).emit(
      "val2",
      JInt.valueOf(22),
      null,
      JInt.valueOf(0),
      JLong.valueOf(1)
    )
  }
}
