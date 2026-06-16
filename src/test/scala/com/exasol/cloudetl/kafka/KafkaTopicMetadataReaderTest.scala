package com.exasol.cloudetl.kafka

import java.util.Arrays

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.PartitionInfo
import org.apache.kafka.common.errors.AuthenticationException
import org.apache.kafka.common.errors.AuthorizationException
import org.apache.kafka.common.errors.TimeoutException
import org.mockito.Mockito.verify
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaTopicMetadataReaderTest extends AnyFunSuite with MockitoSugar {

  private[this] val topic = "orders"

  test("must read topic partitions and close the consumer") {
    val consumer = mock[KafkaConsumer[Void, Void]]
    when(consumer.partitionsFor(topic)).thenReturn(
      Arrays.asList(
        new PartitionInfo(topic, 0, null, null, null),
        new PartitionInfo(topic, 2, null, null, null)
      )
    )

    val partitions = KafkaTopicMetadataReader.getTopicPartitions(consumer, topic)

    assert(partitions === List(0, 2))
    verify(consumer).close()
  }

  test("must map timeout errors to a connector exception") {
    val consumer = mock[KafkaConsumer[Void, Void]]
    when(consumer.partitionsFor(topic)).thenThrow(new TimeoutException("timed out"))

    val thrown = intercept[KafkaConnectorException] {
      KafkaTopicMetadataReader.getTopicPartitions(consumer, topic)
    }

    assert(thrown.getMessage.contains("E-KCE-24"))
    assert(thrown.getMessage.contains("Timeout trying to connect to Kafka brokers"))
    verify(consumer).close()
  }

  test("must map authorization errors to a connector exception") {
    val consumer = mock[KafkaConsumer[Void, Void]]
    when(consumer.partitionsFor(topic)).thenThrow(new AuthorizationException("missing ACL"))

    val thrown = intercept[KafkaConnectorException] {
      KafkaTopicMetadataReader.getTopicPartitions(consumer, topic)
    }

    assert(thrown.getMessage.contains("E-KCE-25"))
    assert(thrown.getMessage.contains("does not have access to read the given topic"))
    verify(consumer).close()
  }

  test("must map authentication errors to a connector exception") {
    val consumer = mock[KafkaConsumer[Void, Void]]
    when(consumer.partitionsFor(topic)).thenThrow(new AuthenticationException("bad credentials"))

    val thrown = intercept[KafkaConnectorException] {
      KafkaTopicMetadataReader.getTopicPartitions(consumer, topic)
    }

    assert(thrown.getMessage.contains("E-KCE-26"))
    assert(thrown.getMessage.contains("Failed to authenticate to the Kafka cluster"))
    verify(consumer).close()
  }

  test("must map unexpected errors to a connector exception") {
    val consumer = mock[KafkaConsumer[Void, Void]]
    when(consumer.partitionsFor(topic)).thenThrow(new IllegalStateException("boom"))

    val thrown = intercept[KafkaConnectorException] {
      KafkaTopicMetadataReader.getTopicPartitions(consumer, topic)
    }

    assert(thrown.getMessage.contains("F-KCE-27"))
    assert(thrown.getCause.isInstanceOf[IllegalStateException])
    verify(consumer).close()
  }
}
