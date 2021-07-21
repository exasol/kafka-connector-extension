package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt, Long => JLong}
import java.util.Collections

import scala.jdk.CollectionConverters.{CollectionHasAsScala, MapHasAsJava, MapHasAsScala}

import com.exasol.{ExaDataTypeException, ExaMetadata}

import org.apache.kafka.clients.admin.RecordsToDelete
import org.apache.kafka.common.TopicPartition
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.{times, verify, when}

class KafkaTopicDataImporterAvroToColumnsIT extends KafkaTopicDataImporterAvroIT {

  test("run emits records from starting initial offset") {
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))

    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(2)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "abc",
      JInt.valueOf(3),
      JLong.valueOf(13),
      JInt.valueOf(0),
      JLong.valueOf(0)
    )
    verify(iter, times(1)).emit(
      "hello",
      JInt.valueOf(4),
      JLong.valueOf(14),
      JInt.valueOf(0),
      JLong.valueOf(1)
    )
  }

  test("run emits records when the starting offset of the topic is greater zero") {
    createCustomTopic(topic)

    val startingOffset = 12
    0.until(startingOffset)
      .foreach { recordNr =>
        publishToKafka(
          topic,
          AvroRecord(
            "Some record that we delete" +
              "to ensure the offset does not start at zero",
            recordNr,
            13
          )
        )
      }
    deleteRecordsFromTopic(topic, startingOffset)

    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))

    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(2)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "abc",
      JInt.valueOf(3),
      JLong.valueOf(13),
      JInt.valueOf(0),
      JLong.valueOf(startingOffset + 0L)
    )
    verify(iter, times(1)).emit(
      "hello",
      JInt.valueOf(4),
      JLong.valueOf(14),
      JInt.valueOf(0),
      JLong.valueOf(startingOffset + 1L)
    )
  }

  test("run emits records starting from provided offset") {
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))
    publishToKafka(topic, AvroRecord("def", 7, 17))
    publishToKafka(topic, AvroRecord("xyz", 13, 23))

    // records at 0, 1 are already read, committed
    val iter = mockExasolIterator(properties, Seq(0), Seq(1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(2)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "def",
      JInt.valueOf(7),
      JLong.valueOf(17),
      JInt.valueOf(0),
      JLong.valueOf(2)
    )
    verify(iter, times(1)).emit(
      "xyz",
      JInt.valueOf(13),
      JLong.valueOf(23),
      JInt.valueOf(0),
      JLong.valueOf(3)
    )
  }

  test("run emits records within min / max records per run") {
    val newProperties = properties ++ Map(
      "MAX_POLL_RECORDS" -> "2",
      "MIN_RECORDS_PER_RUN" -> "2",
      "MAX_RECORDS_PER_RUN" -> "4"
    )
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))
    publishToKafka(topic, AvroRecord("def", 7, 17))
    publishToKafka(topic, AvroRecord("xyz", 13, 23))
    publishToKafka(topic, AvroRecord("last", 11, 22))

    // comsumer in two batches each with 2 records
    val iter = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(4)).emit(Seq(any[Object]): _*)
    verify(iter, times(4)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
  }

  test("run emits records until the end of partition offset") {
    val newProperties = properties ++ Map(
      "MAX_POLL_RECORDS" -> "2",
      "MIN_RECORDS_PER_RUN" -> "2",
      "MAX_RECORDS_PER_RUN" -> "4",
      "CONSUME_ALL_OFFSETS" -> "true"
    )
    createCustomTopic(topic)
    for (i <- 1 to 5) {
      publishToKafka(topic, AvroRecord(s"$i", i, i.toLong))
    }
    val iter = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(5)).emit(Seq(any[Object]): _*)
    verify(iter, times(5)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
  }

  test("run catches when emit throws ExaDataTypeException") {
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("first", 1, 2))
    publishToKafka(topic, AvroRecord("second", 3, 4))
    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    when(
      iter.emit("second", JInt.valueOf(3), JLong.valueOf(4), JInt.valueOf(0), JLong.valueOf(1))
    ).thenThrow(classOf[ExaDataTypeException])
    val thrown = intercept[KafkaConnectorException] {
      KafkaTopicDataImporter.run(mock[ExaMetadata], iter)
    }
    val message = thrown.getMessage()
    assert(message.contains(s"Error consuming Kafka topic '$topic' data."))
    assert(message.contains("It occurs for partition '0' in node '0' and vm"))
  }

  private[this] def deleteRecordsFromTopic(topic: String, beforeOffset: Int): Unit = {
    withAdminClient { client =>
      val allPartitions = client
        .describeTopics(Collections.singletonList(topic))
        .values
        .asScala
        .values
        .head
        .get()
        .partitions()
        .asScala
        .map(tpi => new TopicPartition(topic, tpi.partition()))
      client
        .deleteRecords(
          allPartitions.map((_, RecordsToDelete.beforeOffset(beforeOffset.toLong))).toMap.asJava
        )
        .all()
        .get()
    }
    ()
  }
}
