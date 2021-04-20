package com.exasol.cloudetl.kafka

import java.nio.file.Path
import java.nio.file.Paths

import com.exasol.ExaConnectionInformation
import com.exasol.ExaMetadata

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.VoidDeserializer
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
  private[this] val DUMMY_KEYSTORE_FILE =
    Paths.get(getClass.getResource("/kafka.consumer.keystore.jks").toURI).toAbsolutePath
  private[this] val DUMMY_TRUSTSTORE_FILE =
    Paths.get(getClass.getResource("/kafka.consumer.truststore.jks").toURI).toAbsolutePath

  test("apply returns a default kafka consumer type") {
    val consumerProperties = KafkaConsumerProperties(defaultProperties)
    val kafkaConsumer =
      KafkaConsumerFactory(consumerProperties, new VoidDeserializer, mock[ExaMetadata])
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, Void]])
  }

  test("apply throws if required SCHEMA_REGISTRY_URL is not set and RECORD_FORMAT is avro") {
    val properties = defaultProperties - ("SCHEMA_REGISTRY_URL")
    val consumerProperties = KafkaConsumerProperties(properties)
    val thrown = intercept[KafkaConnectorException] {
      KafkaConsumerFactory(consumerProperties, new VoidDeserializer, mock[ExaMetadata])
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
      KafkaConsumerFactory(consumerProperties, new VoidDeserializer, mock[ExaMetadata])
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
      KafkaConsumerFactory(consumerProperties, new VoidDeserializer, mock[ExaMetadata])
    }
    assert(
      thrown.getMessage ===
        "Please use a named connection object to provide secure SSL properties."
    )
  }

  test("apply returns a SSL enabled kafka consumer") {
    val kafkaConsumer = createSSLEnabledKafkaConsumer(DUMMY_KEYSTORE_FILE, DUMMY_TRUSTSTORE_FILE)
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, _]])
  }

  test("apply throws if SSL Keystore JKS file is not available") {
    val thrown = intercept[KafkaConnectorException] {
      createSSLEnabledKafkaConsumer(Paths.get("ssl_keystore_file"), DUMMY_TRUSTSTORE_FILE)
    }
    val msg = thrown.getMessage()
    assert(msg.contains("Unable to find the SSL keystore file"))
    assert(msg.contains("Please make sure it is successfully uploaded to BucketFS bucket"))
  }

  test("apply throws if SSL Truststore JKS file is not available") {
    val thrown = intercept[KafkaConnectorException] {
      createSSLEnabledKafkaConsumer(DUMMY_KEYSTORE_FILE, Paths.get("ssl_truststore_file"))
    }
    val msg = thrown.getMessage()
    assert(msg.contains("Unable to find the SSL truststore file"))
    assert(msg.contains("Please make sure it is successfully uploaded to BucketFS bucket"))
  }

  final def createSSLEnabledKafkaConsumer(
    keystoreFile: Path,
    truststoreFile: Path
  ): KafkaConsumer[String, _] = {
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
         |SSL_KEYSTORE_LOCATION=$keystoreFile;
         |SSL_KEYSTORE_PASSWORD=pass123;
         |SSL_TRUSTSTORE_LOCATION=$truststoreFile;
         |SSL_TRUSTSTORE_PASSWORD=pass123
      """.stripMargin.replace("\n", "")
    )
    KafkaConsumerFactory(consumerProperties, new VoidDeserializer, exaMetadata)
  }

}
