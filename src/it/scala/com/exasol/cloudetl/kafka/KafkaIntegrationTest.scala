package com.exasol.cloudetl.kafka

import scala.util.Random

import com.exasol.ExaIterator

import net.manub.embeddedkafka.schemaregistry.EmbeddedKafka
import org.mockito.Mockito.when
import org.scalatest.BeforeAndAfterAll
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

trait KafkaIntegrationTest
    extends AnyFunSuite
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with MockitoSugar
    with EmbeddedKafka {

  var topic: String = _
  var properties: Map[String, String] = _

  val bootstrapServers = "localhost:6001"

  val defaultProperties = Map(
    "BOOTSTRAP_SERVERS" -> bootstrapServers,
    "TABLE_NAME" -> "exasolTable"
  )

  def getTopic(): String =
    Random.alphanumeric.take(4).mkString

  def additionalProperties: Map[String, String] = Map()

  override final def beforeEach(): Unit = {
    topic = getTopic()
    properties = defaultProperties ++ additionalProperties ++ Map("TOPIC_NAME" -> topic)
  }

  override final def beforeAll(): Unit = {
    EmbeddedKafka.start()
    ()
  }

  override final def afterAll(): Unit = {
    EmbeddedKafka.stop()
    ()
  }

  final def mockExasolIterator(
    params: Map[String, String],
    partitions: Seq[Int],
    offsets: Seq[Long]
  ): ExaIterator = {
    val mockedIterator = mock[ExaIterator]
    when(mockedIterator.getString(0)).thenReturn(KafkaConsumerProperties(params).mkString())

    val brokersHead :: brokersTail = Seq.fill(partitions.size - 1)(true) ++ Seq(false)
    when(mockedIterator.next()).thenReturn(brokersHead, brokersTail: _*)
    val partitionsHead :: partitionsTail = partitions.map(Integer.valueOf)
    when(mockedIterator.getInteger(1)).thenReturn(partitionsHead, partitionsTail: _*)
    val offsetsHead :: offsetsTail = offsets.map(java.lang.Long.valueOf)
    when(mockedIterator.getLong(2)).thenReturn(offsetsHead, offsetsTail: _*)

    mockedIterator
  }

}
