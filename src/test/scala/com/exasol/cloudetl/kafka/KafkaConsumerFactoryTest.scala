package com.exasol.cloudetl.kafka

import java.nio.file.Paths

import com.exasol.ExaConnectionInformation
import com.exasol.ExaMetadata

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaConsumerFactoryTest extends AnyFunSuite with MockitoSugar {

  private[this] val defaultProperties: Map[String, String] = Map(
    "BOOTSTRAP_SERVERS" -> "localhost:6001",
    "SCHEMA_REGISTRY_URL" -> "http://localhost:6002",
    "TOPIC_NAME" -> "topic",
    "TABLE_NAME" -> "exasolTable"
  )

  test("apply returns a default kafka consumer type") {
    val consumerProperties = KafkaConsumerProperties(defaultProperties)
    val kafkaConsumer = KafkaConsumerFactory(consumerProperties, mock[ExaMetadata])
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, GenericRecord]])
  }

  test("apply returns a SSL enabled kafka consumer") {
    val properties = defaultProperties ++ Map(
      "SSL_ENABLED" -> "true",
      "SECURITY_PROTOCOL" -> "SSL",
      "CONNECTION_NAME" -> "SSL_CONNECTION"
    )
    val consumerProperties = KafkaConsumerProperties(properties)
    val exaMetadata = mock[ExaMetadata]
    val exaConnectionInformation = mock[ExaConnectionInformation]
    val keystoreFile =
      Paths.get(getClass.getResource("/kafka.consumer.keystore.jks").toURI).toAbsolutePath
    val truststoreFile =
      Paths.get(getClass.getResource("/kafka.consumer.truststore.jks").toURI).toAbsolutePath
    when(exaMetadata.getConnection("SSL_CONNECTION")).thenReturn(exaConnectionInformation)
    when(exaConnectionInformation.getUser()).thenReturn("")
    when(exaConnectionInformation.getPassword()).thenReturn(
      s"""SSL_KEY_PASSWORD=pass123;
         |SSL_KEYSTORE_LOCATION=$keystoreFile;
         |SSL_KEYSTORE_PASSWORD=pass123;
         |SSL_TRUSTSTORE_LOCATION=$truststoreFile;
         |SSL_TRUSTSTORE_PASSWORD=pass123
      """.stripMargin.replace("\n", "")
    )
    val kafkaConsumer = KafkaConsumerFactory(consumerProperties, exaMetadata)
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, GenericRecord]])
  }

  test("apply throws if required SCHEMA_REGISTRY_URL is not set") {
    val properties = defaultProperties - ("SCHEMA_REGISTRY_URL")
    val consumerProperties = KafkaConsumerProperties(properties)
    val thrown = intercept[KafkaConnectorException] {
      KafkaConsumerFactory(consumerProperties, mock[ExaMetadata])
    }
    assert(thrown.getMessage.contains("Error creating a Kafka consumer for topic 'topic'."))
    assert(
      thrown.getMessage.contains(
        "Please provide a value for the SCHEMA_REGISTRY_URL property!"
      )
    )
  }

  test("apply throws if required BOOTSTRAP_SERVERS is not set") {
    val properties = defaultProperties - ("BOOTSTRAP_SERVERS")
    val consumerProperties = KafkaConsumerProperties(properties)
    val thrown = intercept[KafkaConnectorException] {
      KafkaConsumerFactory(consumerProperties, mock[ExaMetadata])
    }
    assert(
      thrown.getMessage.contains(
        "Please provide a value for the BOOTSTRAP_SERVERS property!"
      )
    )
  }

  test("apply throws if secure SSL properties are provided without connection object") {
    val properties = defaultProperties ++ Map(
      "SSL_ENABLED" -> "true",
      "SECURITY_PROTOCOL" -> "SSL",
      "SSL_KEY_PASSWORD" -> "PASSWORD"
    )
    val consumerProperties = KafkaConsumerProperties(properties)
    val thrown = intercept[KafkaConnectorException] {
      KafkaConsumerFactory(consumerProperties, mock[ExaMetadata])
    }
    assert(
      thrown.getMessage ===
        "Please use a named connection object to provide secure SSL properties."
    )
  }

  test("apply throws if secure SSL JKS files are not available") {
    val properties = defaultProperties ++ Map(
      "SSL_ENABLED" -> "true",
      "SECURITY_PROTOCOL" -> "SSL",
      "CONNECTION_NAME" -> "SSL_CONNECTION"
    )
    val consumerProperties = KafkaConsumerProperties(properties)
    val exaMetadata = mock[ExaMetadata]
    val exaConnectionInformation = mock[ExaConnectionInformation]
    when(exaMetadata.getConnection("SSL_CONNECTION")).thenReturn(exaConnectionInformation)
    when(exaConnectionInformation.getUser()).thenReturn("")
    when(exaConnectionInformation.getPassword()).thenReturn(
      s"""SSL_KEY_PASSWORD=pass123;
         |SSL_KEYSTORE_LOCATION=SSL_KeystoreFile;
         |SSL_KEYSTORE_PASSWORD=pass123;
         |SSL_TRUSTSTORE_LOCATION=SSL_TruststoreFile;
         |SSL_TRUSTSTORE_PASSWORD=pass123
      """.stripMargin.replace("\n", "")
    )
    val thrown = intercept[KafkaConnectorException] {
      KafkaConsumerFactory(consumerProperties, exaMetadata)
    }
    assert(thrown.getMessage.contains("Unable to find the SSL keystore file"))
    assert(
      thrown.getMessage
        .contains("Please make sure it is successfully uploaded to BucketFS bucket")
    )
  }

}
