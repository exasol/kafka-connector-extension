package com.exasol.cloudetl.kafka

import java.nio.file.Path
import java.nio.file.Paths

import com.exasol.ExaConnectionInformation
import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaConsumerProperties._

import org.mockito.Mockito.when
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaConsumerPropertiesTest extends AnyFunSuite with BeforeAndAfterEach with MockitoSugar {

  private[this] var properties: Map[String, String] = _

  override final def beforeEach(): Unit = {
    properties = Map.empty[String, String]
    ()
  }

  private[this] def errorMessage(key: String): String =
    s"Please provide key-value pairs for '$key' property."

  test("consumer properties can encode and decode property with empty value") {
    properties = Map(
      "BOOTSTRAP_SERVERS" -> "kafka01",
      "SSL_ENDPOINT_IDENTIFICATION_ALGORITHM" -> "",
      "GROUP_ID" -> ""
    )
    val consumer = KafkaConsumerProperties(KafkaConsumerProperties(properties).mkString())
    assert(consumer.getSSLEndpointIdentificationAlgorithm() === "")
    assert(consumer.getGroupId() === "")
  }

  test("getBootstrapServers returns bootstrap servers property value") {
    val bootstrapServers = "kafka01.example.com,kafka02.example.com"
    properties = Map("BOOTSTRAP_SERVERS" -> bootstrapServers)
    assert(BaseProperties(properties).getBootstrapServers() === bootstrapServers)
  }

  test("getBootstrapServers throws if bootstrap servers property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getBootstrapServers()
    }
    assert(thrown.getMessage().contains(errorMessage("BOOTSTRAP_SERVERS")))
  }

  test("getGroupId returns user provided value") {
    properties = Map("GROUP_ID" -> "groupId")
    assert(BaseProperties(properties).getGroupId() === "groupId")
  }

  test("getGroupId returns default value if group id is not set") {
    assert(BaseProperties(properties).getGroupId() === "EXASOL_KAFKA_UDFS_CONSUMERS")
  }

  test("getTopics returns topics property value") {
    properties = Map("TOPIC_NAME" -> "Metamorphosis")
    assert(BaseProperties(properties).getTopic() === "Metamorphosis")
  }

  test("getTopics throws if topics property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getTopic()
    }
    assert(thrown.getMessage().contains(errorMessage("TOPIC_NAME")))
  }

  test("getTableName returns Exasol table name property value") {
    properties = Map("TABLE_NAME" -> "table")
    assert(BaseProperties(properties).getTableName() === "table")
  }

  test("getTableName throws if table name property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getTableName()
    }
    assert(thrown.getMessage().contains(errorMessage("TABLE_NAME")))
  }

  test("getPollTimeoutMs returns provided poll timeout value") {
    properties = Map("POLL_TIMEOUT_MS" -> "10")
    assert(BaseProperties(properties).getPollTimeoutMs() === 10L)
  }

  test("getPollTimeoutMs returns default value if poll timeout is not set") {
    assert(BaseProperties(properties).getPollTimeoutMs() === 30000L)
  }

  test("getPollTimeoutMs throws if value cannot be converted to long") {
    properties = Map("POLL_TIMEOUT_MS" -> "1l")
    intercept[NumberFormatException] {
      BaseProperties(properties).getPollTimeoutMs()
    }
  }

  test("getMinRecordsPerRun returns provided minimum value") {
    properties = Map("MIN_RECORDS_PER_RUN" -> "7")
    assert(BaseProperties(properties).getMinRecordsPerRun() === 7)
  }

  test("getMinRecordsPerRun returns default value if minimum value is not set") {
    assert(BaseProperties(properties).getMinRecordsPerRun() === 100)
  }

  test("getMinRecordsPerRun throws if value cannot be converted to int") {
    properties = Map("MIN_RECORDS_PER_RUN" -> "e")
    intercept[NumberFormatException] {
      BaseProperties(properties).getMinRecordsPerRun()
    }
  }

  test("getMaxRecordsPerRun returns provided maximum value") {
    properties = Map("MAX_RECORDS_PER_RUN" -> "43")
    assert(BaseProperties(properties).getMaxRecordsPerRun() === 43)
  }

  test("getMaxRecordsPerRun returns default value if maximum value is not set") {
    assert(BaseProperties(properties).getMaxRecordsPerRun() === 1000000)
  }

  test("getMaxRecordsPerRun throws if value cannot be converted to int") {
    properties = Map("MAX_RECORDS_PER_RUN" -> "max")
    intercept[NumberFormatException] {
      BaseProperties(properties).getMaxRecordsPerRun()
    }
  }

  test("isSSLEnabled & isSASLEnabled both returns false if SECURITY_PROTOCOL is not set (default PLAINTEXT)") {
    assert(BaseProperties(properties).isSSLEnabled() === false)
    assert(BaseProperties(properties).isSASLEnabled() === false)
  }

  test("isSSLEnabled returns true & isSASLEnabled returns false if SECURITY_PROTOCOL=SSL") {
    properties = Map("SECURITY_PROTOCOL" -> "SSL")
    assert(BaseProperties(properties).isSSLEnabled() === true)
    assert(BaseProperties(properties).isSASLEnabled() === false)
  }

  test("isSSLEnabled returns false & isSASLEnabled returns true if SECURITY_PROTOCOL=SASL_PLAINTEXT") {
    properties = Map("SECURITY_PROTOCOL" -> "SASL_PLAINTEXT")
    assert(BaseProperties(properties).isSSLEnabled() === false)
    assert(BaseProperties(properties).isSASLEnabled() === true)
  }

  test("isSSLEnabled returns false & isSASLEnabled returns true if SECURITY_PROTOCOL=SASL_SSL") {
    properties = Map("SECURITY_PROTOCOL" -> "SASL_SSL")
    assert(BaseProperties(properties).isSSLEnabled() === false)
    assert(BaseProperties(properties).isSASLEnabled() === true)
  }

  test("hasSchemaRegistryUrl returns true if schema registry url is provided") {
    properties = Map("SCHEMA_REGISTRY_URL" -> "https://schema-registry.example.com")
    assert(BaseProperties(properties).hasSchemaRegistryUrl() === true)
  }

  test("hasSchemaRegistryUrl returns false if schema registry url is not set") {
    assert(BaseProperties(properties).hasSchemaRegistryUrl() === false)
  }

  test("getSchemaRegistryUrl returns schema registry url property value") {
    properties = Map("SCHEMA_REGISTRY_URL" -> "http://a-schema.url")
    assert(BaseProperties(properties).getSchemaRegistryUrl() === "http://a-schema.url")
  }

  test("getSchemaRegistryUrl throws if schema registry url property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getSchemaRegistryUrl()
    }
    assert(thrown.getMessage().contains(errorMessage("SCHEMA_REGISTRY_URL")))
  }

  test("getProperties throws if schema registry is not set and record format is avro") {
    val properties = Map("BOOTSTRAP_SERVERS" -> "server", "RECORD_FORMAT" -> "avro")
    val thrown = intercept[IllegalArgumentException] {
      KafkaConsumerProperties(properties).getProperties()
    }
    assert(thrown.getMessage().contains(errorMessage("SCHEMA_REGISTRY_URL")))
  }

  test("getMaxPollRecords returns max poll records value") {
    properties = Map("MAX_POLL_RECORDS" -> "9")
    assert(BaseProperties(properties).getMaxPollRecords() === "9")
  }

  test("getMaxPollRecords returns default value if max poll records is not set") {
    assert(BaseProperties(properties).getMaxPollRecords() === "500")
  }

  test("getFetchMinBytes returns minimum fetch bytes property value") {
    properties = Map("FETCH_MIN_BYTES" -> "23")
    assert(BaseProperties(properties).getFetchMinBytes() === "23")
  }

  test("getFetchMinBytes returns default value if property is not set") {
    assert(BaseProperties(properties).getFetchMinBytes() === "1")
  }

  test("getFetchMaxBytes returns maximum fetch bytes property value") {
    properties = Map("FETCH_MAX_BYTES" -> "27")
    assert(BaseProperties(properties).getFetchMaxBytes() === "27")
  }

  test("getFetchMaxBytes returns default value if property is not set") {
    // intentionally hardcoded, get alert if it changes.
    assert(BaseProperties(properties).getFetchMaxBytes() === "52428800")
  }

  test("getMaxPartitionFetchBytes returns maximum partition fetch bytes property value") {
    properties = Map("MAX_PARTITION_FETCH_BYTES" -> "4")
    assert(BaseProperties(properties).getMaxPartitionFetchBytes() === "4")
  }

  test("getMaxPartitionFetchBytes returns default value if property is not set") {
    assert(BaseProperties(properties).getMaxPartitionFetchBytes() === "1048576")
  }

  test("getOffsetResetStrategy returns default value 'earliest' if property is not set") {
    assert(BaseProperties(properties).getAutoOffsetReset() === "earliest")
  }

  test("getSecurityProtocol returns user provided security protocol property value") {
    properties = Map("SECURITY_PROTOCOL" -> "SSL")
    assert(BaseProperties(properties).getSecurityProtocol() === "SSL")
  }

  test("getSecurityProtocol returns default value if security protocol is not set") {
    // default value is intentionally hardcoded, should alert if things change
    assert(BaseProperties(properties).getSecurityProtocol() === "PLAINTEXT")
  }

  test("getSSLKeyPassword returns ssl key password property value") {
    properties = Map("SSL_KEY_PASSWORD" -> "1337")
    assert(BaseProperties(properties).getSSLKeyPassword() === "1337")
  }

  test("getSSLKeyPassword throws if ssl key password property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getSSLKeyPassword()
    }
    assert(thrown.getMessage().contains(errorMessage("SSL_KEY_PASSWORD")))
  }

  test("getSSLKeystorePassword returns ssl keystore password property value") {
    properties = Map("SSL_KEYSTORE_PASSWORD" -> "p@ss")
    assert(BaseProperties(properties).getSSLKeystorePassword() === "p@ss")
  }

  test("getSSLKeystorePassword throws if ssl keystore password property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getSSLKeystorePassword()
    }
    assert(thrown.getMessage().contains(errorMessage("SSL_KEYSTORE_PASSWORD")))
  }

  test("getSSLKeystoreLocation returns ssl keystore location property value") {
    properties = Map("SSL_KEYSTORE_LOCATION" -> "/path/keystore.jks")
    assert(BaseProperties(properties).getSSLKeystoreLocation() === "/path/keystore.jks")
  }

  test("getSSLKeystoreLocation throws if ssl keystore location property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getSSLKeystoreLocation()
    }
    assert(thrown.getMessage().contains(errorMessage("SSL_KEYSTORE_LOCATION")))
  }

  test("getSSLTruststorePassword returns ssl truststore password property value") {
    properties = Map("SSL_TRUSTSTORE_PASSWORD" -> "tp@ss")
    assert(BaseProperties(properties).getSSLTruststorePassword() === "tp@ss")
  }

  test("getSSLTruststorePassword throws if ssl truststore password property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getSSLTruststorePassword()
    }
    assert(thrown.getMessage().contains(errorMessage("SSL_TRUSTSTORE_PASSWORD")))
  }

  test("getSSLTruststoreLocation returns ssl truststore location property value") {
    properties = Map("SSL_TRUSTSTORE_LOCATION" -> "/path/truststore.jks")
    assert(BaseProperties(properties).getSSLTruststoreLocation() === "/path/truststore.jks")
  }

  test("getSSLTruststorePassword throws if ssl truststore location property is not set") {
    val thrown = intercept[IllegalArgumentException] {
      BaseProperties(properties).getSSLTruststoreLocation()
    }
    assert(thrown.getMessage().contains(errorMessage("SSL_TRUSTSTORE_LOCATION")))
  }

  test("getSSLEndpointIdentificationAlgorithm returns user provided property value") {
    properties = Map("SSL_ENDPOINT_IDENTIFICATION_ALGORITHM" -> "none")
    assert(BaseProperties(properties).getSSLEndpointIdentificationAlgorithm() === "none")
  }

  test("getSSLEndpointIdentificationAlgorithm returns default value if it is not set") {
    // default value is intentionally hardcoded, should alert if things change
    assert(BaseProperties(properties).getSSLEndpointIdentificationAlgorithm() === "https")
  }

  test("getSASLJaasConfig returns JAAS content with username & password") {
    properties = Map("SASL_MECHANISM" -> "PLAIN", "SASL_USERNAME" -> "kafka", "SASL_PASSWORD" -> "kafkapw")
    assert(
      BaseProperties(properties).getSASLJaasConfig() ===
        "org.apache.kafka.common.security.plain.PlainLoginModule required " +
        "username=\"kafka\" " +
        "password=\"kafkapw\";"
    )
  }

  test("getSASLJaasConfig returns JAAS content from file") {
    properties = Map("SASL_JAAS_LOCATION" -> s"$DUMMY_SASL_JAAS_FILE")
    assert(
      BaseProperties(properties).getSASLJaasConfig() ===
        "org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required " +
        "unsecuredLoginStringClaim_sub=\"alice\";"
    )
  }

  test("isConsumeAllOffsetsEnabled returns true if it is set to true") {
    properties = Map("CONSUME_ALL_OFFSETS" -> "true")
    assert(BaseProperties(properties).isConsumeAllOffsetsEnabled() === true)
  }

  test("isConsumeAllOffsetsEnabled returns false if it is not set") {
    assert(BaseProperties(properties).isConsumeAllOffsetsEnabled() === false)
  }

  test("getRecordKeyFormat returns user provided property value") {
    properties = Map("RECORD_KEY_FORMAT" -> "keyFormat")
    assert(BaseProperties(properties).getRecordKeyFormat() === "keyformat")
  }

  test("getRecordKeyFormat returns default value if not set") {
    assert(BaseProperties(properties).getRecordKeyFormat() === "string")
  }

  test("getRecordValueFormat returns user provided property value") {
    Map("avro" -> "avro", "JsOn" -> "json", "String" -> "string").foreach { case (input, expected) =>
      properties = Map("RECORD_VALUE_FORMAT" -> input)
      assert(BaseProperties(properties).getRecordValueFormat() === expected)
    }
  }

  test("getRecordValueFormat returns uses record format if it is set") {
    properties = Map("RECORD_FORMAT" -> "new_FormaT")
    assert(BaseProperties(properties).getRecordValueFormat() === "new_format")
  }

  test("getRecordValueFormat returns default value if not set") {
    assert(BaseProperties(properties).getRecordValueFormat() === "avro")
  }

  test("getRecordFields returns user provided property value") {
    properties = Map("RECORD_FIELDS" -> "value.name,value.Sur_Name, value.address1, timestamp")
    val expected = Seq("value.name", "value.Sur_Name", "value.address1", "timestamp")
    assert(BaseProperties(properties).getRecordFields() === expected)
  }

  test("getRecordFields returns default value if not set") {
    assert(BaseProperties(properties).getRecordFields() === Seq("value.*"))
  }

  test("getRecordFields returns default value when record value is not avro") {
    properties = Map("RECORD_VALUE_FORMAT" -> "json")
    assert(BaseProperties(properties).getRecordFields() === Seq("value"))
  }

  test("getRecordFields returns default value when single JSON column is requested") {
    properties = Map("AS_JSON_DOC" -> "true")
    assert(BaseProperties(properties).getRecordFields() === Seq("value"))
  }

  test("getProperties returns Java map properties") {
    import KafkaConsumerProperties._

    @SuppressWarnings(Array("scala:S2068")) // Password keyword in tests
    val requiredProperties = Map(
      BOOTSTRAP_SERVERS -> "kafka.broker.com:9092",
      SCHEMA_REGISTRY_URL -> "http://schema-registry.com:8080",
      SECURITY_PROTOCOL -> "SSL",
      SSL_KEY_PASSWORD -> "sslKeyPass",
      SSL_KEYSTORE_PASSWORD -> "sslKeystorePass",
      SSL_KEYSTORE_LOCATION -> "/bucket/keystore.JKS",
      SSL_TRUSTSTORE_PASSWORD -> "sslTruststorePass",
      SSL_TRUSTSTORE_LOCATION -> "/bucket/truststore.JKS"
    )
    val optionalProperties = Map(
      ENABLE_AUTO_COMMIT -> "false",
      GROUP_ID -> "EXASOL_KAFKA_UDFS_CONSUMERS",
      MAX_POLL_RECORDS -> "500",
      FETCH_MIN_BYTES -> "1",
      FETCH_MAX_BYTES -> "52428800",
      MAX_PARTITION_FETCH_BYTES -> "1048576"
    )

    properties = Map("SSL_ENABLED" -> "true") ++ requiredProperties.map { case (key, value) =>
      key.userPropertyName -> value
    }
    val javaProps = BaseProperties(properties).getProperties()
    assert(javaProps.isInstanceOf[java.util.Map[String, Object]])
    (requiredProperties ++ optionalProperties).foreach { case (key, value) =>
      assert(javaProps.get(key.kafkaPropertyName) === value)
    }
  }

  test("mergeWithConnectionObject returns new KafkaConsumerProperties") {
    val propertiesMap = Map("TOPICS" -> "test-topic", "CONNECTION_NAME" -> "MY_CONNECTION")
    val kafkaConsumerProperties = new BaseProperties(propertiesMap)
    val metadata = mock[ExaMetadata]
    val connectionInformation = mock[ExaConnectionInformation]
    when(metadata.getConnection("MY_CONNECTION")).thenReturn(connectionInformation)
    when(connectionInformation.getUser()).thenReturn("")
    when(connectionInformation.getPassword())
      .thenReturn(
        """BOOTSTRAP_SERVERS=MY_BOOTSTRAP_SERVERS;
          |SCHEMA_REGISTRY_URL=MY_SCHEMA_REGISTRY;
          |SECURITY_PROTOCOL=SSL;
          |SSL_KEYSTORE_LOCATION=MY_KEYSTORE_LOCATION;
          |SSL_KEYSTORE_PASSWORD=MY_KEYSTORE_PASSWORD;
          |SSL_KEY_PASSWORD=MY_SSL_KEY_PASSWORD;
          |SSL_TRUSTSTORE_LOCATION=MY_TRUSTSTORE_LOCATION;
          |SSL_TRUSTSTORE_PASSWORD=MY_TRUSTSTORE_PASSWORD""".stripMargin.replace("\n", "")
      )
    val mergedKafkaConsumerProperties = kafkaConsumerProperties.mergeWithConnectionObject(metadata)
    assert(
      mergedKafkaConsumerProperties.mkString() ===
        """BOOTSTRAP_SERVERS -> MY_BOOTSTRAP_SERVERS;
          |CONNECTION_NAME -> MY_CONNECTION;
          |SCHEMA_REGISTRY_URL -> MY_SCHEMA_REGISTRY;
          |SECURITY_PROTOCOL -> SSL;
          |SSL_KEYSTORE_LOCATION -> MY_KEYSTORE_LOCATION;
          |SSL_KEYSTORE_PASSWORD -> MY_KEYSTORE_PASSWORD;
          |SSL_KEY_PASSWORD -> MY_SSL_KEY_PASSWORD;
          |SSL_TRUSTSTORE_LOCATION -> MY_TRUSTSTORE_LOCATION;
          |SSL_TRUSTSTORE_PASSWORD -> MY_TRUSTSTORE_PASSWORD;
          |TOPICS -> test-topic""".stripMargin.replace("\n", "")
    )
  }

  test("apply throws if secure SSL properties are provided without connection object") {
    val properties = Map("SECURITY_PROTOCOL" -> "SSL", "SSL_KEY_PASSWORD" -> "PASSWORD")
    val thrown = intercept[KafkaConnectorException] {
      KafkaConsumerProperties(properties, mock[ExaMetadata])
    }
    val message = thrown.getMessage()
    assert(message.contains("Please use a named Exasol connection object to provide secure properties."))
  }

  test("apply obtains bootstrap servers and schema registry url from connection object") {
    val params = Map("CONNECTION_NAME" -> "MY_CONNECTION")
    val metadata = mock[ExaMetadata]
    val connectionInformation = mock[ExaConnectionInformation]
    when(metadata.getConnection("MY_CONNECTION")).thenReturn(connectionInformation)
    when(connectionInformation.getUser()).thenReturn("")
    when(connectionInformation.getPassword())
      .thenReturn("BOOTSTRAP_SERVERS=localhost:1000;SCHEMA_REGISTRY_URL=http://n11:1001")
    val properties = KafkaConsumerProperties(params, metadata)
    assert(properties.getBootstrapServers() === "localhost:1000")
    assert(properties.getSchemaRegistryUrl() === "http://n11:1001")
  }

  private[this] val DUMMY_KEYSTORE_FILE =
    Paths.get(getClass.getResource("/kafka.consumer.keystore.jks").toURI).toAbsolutePath

  private[this] val DUMMY_TRUSTSTORE_FILE =
    Paths.get(getClass.getResource("/kafka.consumer.truststore.jks").toURI).toAbsolutePath

  private[this] val DUMMY_SASL_JAAS_FILE =
    Paths.get(getClass.getResource("/kafka_client_jaas.conf").toURI).toAbsolutePath

  private[this] val DUMMY_KRB5CONF_FILE =
    Paths.get(getClass.getResource("/test_krb5.conf").toURI).toAbsolutePath

  test("apply returns a SSL enabled consumer properties") {
    val properties =
      getSecurityEnabledConsumerProperties("SSL", Option(DUMMY_KEYSTORE_FILE), Option(DUMMY_TRUSTSTORE_FILE))
    assert(properties.getSSLKeystoreLocation() === s"$DUMMY_KEYSTORE_FILE")
    assert(properties.getSSLTruststoreLocation() === s"$DUMMY_TRUSTSTORE_FILE")
  }

  test("apply throws if SSL Keystore JKS file is not available") {
    val thrown = intercept[KafkaConnectorException] {
      getSecurityEnabledConsumerProperties("SSL", Option(Paths.get("ssl_keystore_file")), Option(DUMMY_TRUSTSTORE_FILE))
    }
    val message = thrown.getMessage()
    assert(message.contains("Unable to find the SSL keystore file"))
    assert(message.contains("Please make sure it is successfully uploaded to BucketFS bucket"))
  }

  test("apply throws if SSL Truststore JKS file is not available") {
    val thrown = intercept[KafkaConnectorException] {
      getSecurityEnabledConsumerProperties("SSL", Option(DUMMY_KEYSTORE_FILE), Option(Paths.get("ssl_truststore_file")))
    }
    val message = thrown.getMessage()
    assert(message.contains("Unable to find the SSL truststore file"))
    assert(message.contains("Please make sure it is successfully uploaded to BucketFS bucket"))
  }

  test("apply returns a SSL_SASL enabled consumer properties") {
    val properties = getSecurityEnabledConsumerProperties("SASL_SSL")
    assert(properties.getSASLMechanism() === "PLAIN")
  }

  test("apply optionally check keystore and truststore files with SSL_SASL protocol") {
    val properties =
      getSecurityEnabledConsumerProperties("SASL_SSL", Option(DUMMY_KEYSTORE_FILE), Option(DUMMY_TRUSTSTORE_FILE))
    assert(properties.getSASLJaasConfig().contains(s""""pass""""))
    assert(properties.getSSLKeystoreLocation() === s"$DUMMY_KEYSTORE_FILE")
    assert(properties.getSSLTruststoreLocation() === s"$DUMMY_TRUSTSTORE_FILE")
  }

  test("apply throws if provided keystore file is not available with SSL_SASL protocol") {
    val thrown = intercept[KafkaConnectorException] {
      getSecurityEnabledConsumerProperties("SASL_SSL", Option(Paths.get("ssl_keystore_file")))
    }
    assert(thrown.getMessage().contains("Unable to find the SSL keystore file"))
  }

  test("apply throws if provided truststore file is not available with SSL_SASL protocol") {
    val thrown = intercept[KafkaConnectorException] {
      getSecurityEnabledConsumerProperties(
        "SASL_SSL",
        Option(DUMMY_KEYSTORE_FILE),
        Option(Paths.get("ssl_truststore_file"))
      )
    }
    assert(thrown.getMessage().contains("Unable to find the SSL truststore file"))
  }

  test("getProperties contains keystore file with SSL_SASL protocol") {
    val properties = getSecurityEnabledConsumerProperties("SASL_SSL", Option(DUMMY_KEYSTORE_FILE))
    assert(properties.getProperties().get(SSL_KEYSTORE_LOCATION.kafkaPropertyName) === s"$DUMMY_KEYSTORE_FILE")
  }

  test("getProperties contains truststore file with SSL_SASL protocol") {
    val properties = getSecurityEnabledConsumerProperties("SASL_SSL", None, Option(DUMMY_TRUSTSTORE_FILE))
    assert(properties.getProperties().get(SSL_TRUSTSTORE_LOCATION.kafkaPropertyName) === s"$DUMMY_TRUSTSTORE_FILE")
  }

  test("getProperties contains keystore and truststore files with SSL_SASL protocol") {
    val properties =
      getSecurityEnabledConsumerProperties("SASL_SSL", Option(DUMMY_KEYSTORE_FILE), Option(DUMMY_TRUSTSTORE_FILE))
    assert(properties.getProperties().get(SSL_KEYSTORE_LOCATION.kafkaPropertyName) === s"$DUMMY_KEYSTORE_FILE")
    assert(properties.getProperties().get(SSL_TRUSTSTORE_LOCATION.kafkaPropertyName) === s"$DUMMY_TRUSTSTORE_FILE")
  }

  test("getProperties contains SSL related information with SSL_SASL protocol") {
    val properties = getSecurityEnabledConsumerProperties("SASL_SSL", None, None)
    assert(properties.getProperties().get(SSL_KEY_PASSWORD.kafkaPropertyName) === "kpw")
    assert(properties.getProperties().get(SSL_KEYSTORE_PASSWORD.kafkaPropertyName) === "kspw")
    assert(properties.getProperties().get(SSL_TRUSTSTORE_PASSWORD.kafkaPropertyName) === "tspw")
  }

  test("error is throws when non-existent krb5.conf file passed") {
    val properties =
      getSecurityEnabledConsumerProperties("SASL_SSL", krb5confFile = Option(Paths.get("krb5_non_existing")))
    val thrown = intercept[KafkaConnectorException] {
      properties.getProperties()
    }
    val message = thrown.getMessage()
    assert(message.contains("Unable to find the custom krb5.conf file"))
    assert(message.contains("Please make sure it is successfully uploaded to BucketFS bucket"))
  }

  test("property is set when existing krb5.conf file passed") {
    val properties = getSecurityEnabledConsumerProperties("SASL_SSL", krb5confFile = Option(DUMMY_KRB5CONF_FILE))
    val props = properties.getProperties()
    assert(props.get(SASL_KRB5CONF_LOCATION.kafkaPropertyName) === s"$DUMMY_KRB5CONF_FILE")
  }

  private[this] def getSecurityEnabledConsumerProperties(
    securityProtocol: String,
    keystoreFile: Option[Path] = None,
    truststoreFile: Option[Path] = None,
    krb5confFile: Option[Path] = None
  ): KafkaConsumerProperties = {
    val properties = Map(
      "BOOTSTRAP_SERVERS" -> "kafka01",
      "RECORD_FORMAT" -> "string",
      "SECURITY_PROTOCOL" -> securityProtocol,
      "CONNECTION_NAME" -> "SSL_CONNECTION"
    )
    val metadata = mock[ExaMetadata]
    val connectionInformation = mock[ExaConnectionInformation]
    when(metadata.getConnection("SSL_CONNECTION")).thenReturn(connectionInformation)
    when(connectionInformation.getUser()).thenReturn("")
    val stringBuilder = new StringBuilder()
    keystoreFile.foreach(file => stringBuilder.append("SSL_KEYSTORE_LOCATION=").append(file).append(";"))
    truststoreFile.foreach(file => stringBuilder.append("SSL_TRUSTSTORE_LOCATION=").append(file).append(";"))
    krb5confFile.foreach(file => stringBuilder.append("SASL_KRB5CONF_LOCATION=").append(file).append(";"))
    if (securityProtocol === "SSL") {
      addSimpleSSLParameters(stringBuilder)
    } else if (securityProtocol === "SASL_SSL") {
      addSimpleSSLParameters(stringBuilder)
      stringBuilder.append(";")
      addSimpleSASLParameters(stringBuilder)
    }
    when(connectionInformation.getPassword()).thenReturn(stringBuilder.toString())
    KafkaConsumerProperties(properties, metadata)
  }

  private[this] def addSimpleSSLParameters(sb: StringBuilder): Unit = {
    sb.append("SSL_KEY_PASSWORD=kpw;SSL_KEYSTORE_PASSWORD=kspw;SSL_TRUSTSTORE_PASSWORD=tspw")
    ()
  }

  private[this] def addSimpleSASLParameters(sb: StringBuilder): Unit = {
    sb.append("SASL_MECHANISM=PLAIN;SASL_USERNAME=user;SASL_PASSWORD=pass")
    ()
  }

  private[this] case class BaseProperties(val params: Map[String, String]) extends KafkaConsumerProperties(params)

}
