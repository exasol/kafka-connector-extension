package com.exasol.cloudetl.kafka

import java.nio.file.Files
import java.nio.file.Paths
import java.util.Locale

import scala.collection.mutable.{Map => MMap}
import scala.jdk.CollectionConverters._

import com.exasol.ExaMetadata
import com.exasol.common.AbstractProperties
import com.exasol.common.CommonProperties

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.config.SslConfigs

/**
 * A specific implementation of [[com.exasol.common.AbstractProperties]]
 * that handles user provided key-value parameters for import
 * user-defined-functions (UDFs) as Kafka consumer application.
 *
 * This class also provides builder methods for Kafka consumers.
 */
class KafkaConsumerProperties(private val properties: Map[String, String]) extends AbstractProperties(properties) {

  import KafkaConsumerProperties._

  /** Returns user provided Kafka bootstrap servers string. */
  final def getBootstrapServers(): String =
    getString(BOOTSTRAP_SERVERS.userPropertyName)

  /**
   * Returns user provided group id, if it is not provided by user
   * returns default value.
   */
  final def getGroupId(): String =
    get(GROUP_ID.userPropertyName).fold(GROUP_ID.defaultValue)(identity)

  /**
   * Return the strategy to use when the last committed offset is empty
   * or out of range.
   *
   * Defaults to {@code earliest}.
   */
  final def getAutoOffsetReset(): String =
    get(AUTO_OFFSET_RESET.userPropertyName).fold(AUTO_OFFSET_RESET.defaultValue)(identity)

  /**
   * Checks if user wants to import single column JSON.
   *
   * If it is not provided by user returns default value of {@code false}.
   */
  final def isSingleColumnJsonEnabled(): Boolean =
    isEnabled(AS_JSON_DOC)

  /**
   * Returns the type we expect on the Kafka record value.
   *
   * It is one of {@code json}, {@code avro}, {@code string} values.
   * Defaults to {@code avro}.
   */
  final def getRecordValueFormat(): String = {
    val obsoleteFormatValue = get(RECORD_FORMAT).fold("avro")(identity)
    val recordValueFormat = get(RECORD_VALUE_FORMAT).fold(obsoleteFormatValue)(identity)
    recordValueFormat.toLowerCase(Locale.ENGLISH)
  }

  /**
   * Returns the type we expect on the Kafka record key.
   *
   * It is one of {@code json}, {@code avro}, {@code string} values.
   * Defaults to {@code string}.
   */
  final def getRecordKeyFormat(): String =
    get(RECORD_KEY_FORMAT).fold("string")(identity).toLowerCase(Locale.ENGLISH)

  /**
   * Returns sequence of records fields from comma separated string.
   */
  final def getRecordFields(): Seq[String] =
    get(RECORD_FIELDS).fold(defaultRecordFields()) { fields =>
      fields.split(",").map(_.trim).toSeq
    }

  private[this] def defaultRecordFields(): Seq[String] = {
    val recordField = if (isSingleColumnJsonEnabled()) {
      "value"
    } else {
      getRecordValueFormat() match {
        case "avro" => "value.*"
        case _      => "value"
      }
    }
    Seq(recordField)
  }

  /** Returns the user provided topic name. */
  final def getTopic(): String =
    getString(TOPIC_NAME)

  /**
   * Returns the user provided Exasol table name; otherwise returns
   * default value.
   */
  final def getTableName(): String =
    getString(TABLE_NAME)

  /**
   * Checks if the user wants to consume all offsets of topic
   * partitions.
   *
   * If it is not set by user returns {@code false} as default value.
   */
  final def isConsumeAllOffsetsEnabled(): Boolean =
    isEnabled(CONSUME_ALL_OFFSETS)

  /**
   * Returns poll timeout millisecords if provided by user; otherwise
   * returns default value.
   *
   * throws java.lang.NumberFormatException If value is not a Long.
   */
  @throws[NumberFormatException]("If value is not a Long.")
  final def getPollTimeoutMs(): Long =
    get(POLL_TIMEOUT_MS.userPropertyName).fold(POLL_TIMEOUT_MS.defaultValue)(_.toLong)

  /**
   * Returns minimum records per run property value when provided by
   * user; otherwise returns default value.
   *
   * throws java.lang.NumberFormatException If value is not an Int.
   */
  @throws[NumberFormatException]("If value is not an Int.")
  final def getMinRecordsPerRun(): Int =
    get(MIN_RECORDS_PER_RUN.userPropertyName).fold(MIN_RECORDS_PER_RUN.defaultValue)(_.toInt)

  /**
   * Returns maximum records per run property value when provided by
   * user; otherwise returns default value.
   *
   * throws java.lang.NumberFormatException If value is not an Int.
   */
  @throws[NumberFormatException]("If value is not an Int.")
  final def getMaxRecordsPerRun(): Int =
    get(MAX_RECORDS_PER_RUN.userPropertyName).fold(MAX_RECORDS_PER_RUN.defaultValue)(_.toInt)

  /** Checks if the {@code SSL_ENABLED} property is set. */
  final def isSSLEnabled(): Boolean =
    isEnabled(SSL_ENABLED)

  /** Checks if the Schema Registry URL property is set. */
  final def hasSchemaRegistryUrl(): Boolean =
    containsKey(SCHEMA_REGISTRY_URL.userPropertyName)

  /** Returns the user provided schema registry url property. */
  final def getSchemaRegistryUrl(): String =
    getString(SCHEMA_REGISTRY_URL.userPropertyName)

  /**
   * Returns {@code MAX_POLL_RECORDS} property value if provided,
   * otherwise returns default value.
   */
  final def getMaxPollRecords(): String =
    get(MAX_POLL_RECORDS.userPropertyName).fold(MAX_POLL_RECORDS.defaultValue)(identity)

  /**
   * Returns {@code FETCH_MIN_BYTES} property value if provided,
   * otherwise returns the default value.
   */
  final def getFetchMinBytes(): String =
    get(FETCH_MIN_BYTES.userPropertyName).fold(FETCH_MIN_BYTES.defaultValue)(identity)

  /**
   * Returns {@code FETCH_MAX_BYTES} property value if provided,
   * otherwise returns the default value.
   */
  final def getFetchMaxBytes(): String =
    get(FETCH_MAX_BYTES.userPropertyName).fold(FETCH_MAX_BYTES.defaultValue)(identity)

  /**
   * Returns {@code MAX_PARTITION_FETCH_BYTES} property value if
   * provided, otherwise returns the default value.
   */
  final def getMaxPartitionFetchBytes(): String =
    get(MAX_PARTITION_FETCH_BYTES.userPropertyName)
      .fold(MAX_PARTITION_FETCH_BYTES.defaultValue)(identity)

  // Secure Connection Related Properties

  /**
   * Returns {@code SECURITY_PROTOCOL} property value if provided,
   * otherwise returns the default value.
   */
  final def getSecurityProtocol(): String =
    get(SECURITY_PROTOCOL.userPropertyName).fold(SECURITY_PROTOCOL.defaultValue)(identity)

  /**
   * Returns the user provided {@code SSL_KEY_PASSWORD} property value.
   */
  final def getSSLKeyPassword(): String =
    getString(SSL_KEY_PASSWORD.userPropertyName)

  /**
   * Returns the user provided {@code SSL_KEYSTORE_PASSWORD} property
   * value.
   */
  final def getSSLKeystorePassword(): String =
    getString(SSL_KEYSTORE_PASSWORD.userPropertyName)

  /**
   * Returns the user provided {@code SSL_KEYSTORE_LOCATION} property
   * value.
   */
  final def getSSLKeystoreLocation(): String =
    getString(SSL_KEYSTORE_LOCATION.userPropertyName)

  /**
   * Returns the user provided {@code SSL_TRUSTSTORE_PASSWORD} property
   * value.
   */
  final def getSSLTruststorePassword(): String =
    getString(SSL_TRUSTSTORE_PASSWORD.userPropertyName)

  /**
   * Returns the user provided {@code SSL_TRUSTSTORE_LOCATION} property
   * value.
   */
  final def getSSLTruststoreLocation(): String =
    getString(SSL_TRUSTSTORE_LOCATION.userPropertyName)

  /**
   * Returns {@code SSL_ENDPOINT_IDENTIFICATION_ALGORITHM} property
   * value if provided, otherwise returns the default value.
   */
  final def getSSLEndpointIdentificationAlgorithm(): String =
    get(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM.userPropertyName)
      .fold(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM.defaultValue)(identity)

  /** Returns the Kafka consumer properties as Java map. */
  @SuppressWarnings(Array("org.wartremover.warts.NonUnitStatements"))
  final def getProperties(): java.util.Map[String, AnyRef] = {
    val props = MMap.empty[String, String]
    props.put(ENABLE_AUTO_COMMIT.kafkaPropertyName, ENABLE_AUTO_COMMIT.defaultValue)
    props.put(BOOTSTRAP_SERVERS.kafkaPropertyName, getBootstrapServers())
    props.put(GROUP_ID.kafkaPropertyName, getGroupId())
    props.put(AUTO_OFFSET_RESET.kafkaPropertyName, getAutoOffsetReset())
    if ("avro".equals(getRecordValueFormat())) {
      props.put(SCHEMA_REGISTRY_URL.kafkaPropertyName, getSchemaRegistryUrl())
    }
    props.put(MAX_POLL_RECORDS.kafkaPropertyName, getMaxPollRecords())
    props.put(FETCH_MIN_BYTES.kafkaPropertyName, getFetchMinBytes())
    props.put(FETCH_MAX_BYTES.kafkaPropertyName, getFetchMaxBytes())
    props.put(MAX_PARTITION_FETCH_BYTES.kafkaPropertyName, getMaxPartitionFetchBytes())
    if (isSSLEnabled()) {
      props.put(SECURITY_PROTOCOL.kafkaPropertyName, getSecurityProtocol())
      props.put(SSL_KEY_PASSWORD.kafkaPropertyName, getSSLKeyPassword())
      props.put(SSL_KEYSTORE_PASSWORD.kafkaPropertyName, getSSLKeystorePassword())
      props.put(SSL_KEYSTORE_LOCATION.kafkaPropertyName, getSSLKeystoreLocation())
      props.put(SSL_TRUSTSTORE_PASSWORD.kafkaPropertyName, getSSLTruststorePassword())
      props.put(SSL_TRUSTSTORE_LOCATION.kafkaPropertyName, getSSLTruststoreLocation())
      props.put(
        SSL_ENDPOINT_IDENTIFICATION_ALGORITHM.kafkaPropertyName,
        getSSLEndpointIdentificationAlgorithm()
      )
    }
    props.toMap.asInstanceOf[Map[String, AnyRef]].asJava
  }

  /**
   * Returns a new [[KafkaConsumerProperties]] that merges the key-value pairs
   * parsed from user provided Exasol named connection object.
   */
  final def mergeWithConnectionObject(metadata: ExaMetadata): KafkaConsumerProperties = {
    val connectionParsedMap =
      parseConnectionInfo(BOOTSTRAP_SERVERS.userPropertyName, Option(metadata))
    val newProperties = properties ++ connectionParsedMap
    new KafkaConsumerProperties(newProperties)
  }

  /**
   * Returns a string value of key-value property pairs.
   *
   * The resulting string is sorted by keys ordering.
   */
  final def mkString(): String =
    mkString(KEY_VALUE_SEPARATOR, PROPERTY_SEPARATOR)

}

/**
 * A companion object for [[KafkaConsumerProperties]] class.
 */
object KafkaConsumerProperties extends CommonProperties {

  /**
   * Internal configuration helper class.
   *
   * @param userPropertyName A UDF user provided property key name
   * @param kafkaPropertyName An equivalent property in Kafka
   *        configuration that maps user property key name
   * @param defaultValue A default value for the property key name
   */
  private[kafka] final case class Config[T](
    val userPropertyName: String,
    val kafkaPropertyName: String,
    val defaultValue: T
  )

  /**
   * A required property key name for a Kafka topic name to import data
   * from.
   */
  private[kafka] final val TOPIC_NAME: String = "TOPIC_NAME"

  /**
   * A required property key name for a Exasol table name to import data
   * into.
   */
  private[kafka] final val TABLE_NAME: String = "TABLE_NAME"

  /**
   * An optional property key name to set SSL secure connections to
   * Kafka cluster.
   */
  private[kafka] final val SSL_ENABLED: String = "SSL_ENABLED"

  /**
   * An optional property that defines whether data should be imported
   * as JSON into a single column.
   */
  private[kafka] final val AS_JSON_DOC: String = "AS_JSON_DOC"

  /**
   * An optional property that specifies to consume all available
   * offsets in a topic partition.
   */
  private[kafka] final val CONSUME_ALL_OFFSETS: String = "CONSUME_ALL_OFFSETS"

  /**
   * An optional property that specify fields and field order when
   * inserting data into the target table.
   *
   * It is a comma separated list of fields that are present in a Kafka
   * record. This property is required when the source record is JSON
   * since the field order is not guaranteed in JSON record.
   */
  private[kafka] final val RECORD_FIELDS: String = "RECORD_FIELDS"

  /**
   * An optional property that define the serialization format of the
   * topic record.
   *
   * It is either Avro serialized with the Confluent Schema Registry,
   * JSON or plain string.
   *
   * @deprecated("Use RECORD_VALUE_FORMAT", "1.1.0")
   */
  private[kafka] final val RECORD_FORMAT: String = "RECORD_FORMAT"

  /**
   * An optional property to define the serialization format of the
   * record key of a Kafka topic.
   *
   * It is either Avro serialized with the Confluent Schema Registry,
   * JSON or plain string.
   */
  private[kafka] final val RECORD_KEY_FORMAT: String = "RECORD_KEY_FORMAT"

  /**
   * An optional property specifying the serialization format of the
   * record value in a Kafka topic.
   *
   * Similar to key format, it is either Avro serialized with the
   * Confluent Schema Registry, JSON or plain string.
   */
  private[kafka] final val RECORD_VALUE_FORMAT: String = "RECORD_VALUE_FORMAT"

  /**
   * A number of milliseconds to wait for Kafka consumer {@code poll} to
   * return any data.
   */
  private[kafka] final val POLL_TIMEOUT_MS: Config[Long] = Config[Long](
    "POLL_TIMEOUT_MS",
    "",
    30000L // scalastyle:ignore magic.number
  )

  /**
   * An upper bound on the minimum number of records to consume per UDF
   * run.
   *
   * That is, if the {@code poll} returns fewer records than this
   * number, consume them and finish the process. Otherwise, continue
   * polling more data until the total number of records reaches
   * [[MAX_RECORDS_PER_RUN]].
   *
   * See [[MAX_RECORDS_PER_RUN]].
   */
  private[kafka] final val MIN_RECORDS_PER_RUN: Config[Int] = Config[Int](
    "MIN_RECORDS_PER_RUN",
    "",
    100 // scalastyle:ignore magic.number
  )

  /**
   * An lower bound on the maximum number of records to consumer per UDF
   * run.
   *
   * When the returned number of records from {@code poll} is more than
   * [[MIN_RECORDS_PER_RUN]], it continues polling for more records
   * until total number reaches this number.
   *
   * See [[MIN_RECORDS_PER_RUN]].
   */
  private[kafka] final val MAX_RECORDS_PER_RUN: Config[Int] = Config[Int](
    "MAX_RECORDS_PER_RUN",
    "",
    1000000 // scalastyle:ignore magic.number
  )

  /**
   * Below are relavant Kafka consumer configuration parameters are
   * defined.
   *
   * See [[https://kafka.apache.org/documentation.html#consumerconfigs]]
   */
  /**
   * This is the {@code enable.auto.commit} configuration setting.
   *
   * If set to true the offset of consumer will be periodically
   * committed to the Kafka cluster in the background. This is `false`
   * by default, since we manage the offset commits ourselves in the
   * Exasol table.
   */
  private[kafka] final val ENABLE_AUTO_COMMIT: Config[String] = Config[String](
    "ENABLE_AUTO_COMMIT",
    ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
    "false"
  )

  /**
   * This is the {@code bootstrap.servers} configuration setting.
   *
   * A list of host and port pairs to use for establishing the initial
   * connection to the Kafka cluster.
   *
   * It is a required property that should be provided by the user.
   */
  private[kafka] final val BOOTSTRAP_SERVERS: Config[String] = Config[String](
    "BOOTSTRAP_SERVERS",
    ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
    ""
  )

  /**
   * This is the {@code group.id} configuration setting.
   *
   * It is a unique string that identifies the consumer group this
   * consumer belongs to.
   */
  private[kafka] final val GROUP_ID: Config[String] = Config[String](
    "GROUP_ID",
    ConsumerConfig.GROUP_ID_CONFIG,
    "EXASOL_KAFKA_UDFS_CONSUMERS"
  )

  /**
   * This is the {@code auto.offset.reset} configuration setting.
   *
   * This controls where the consumer starts when no previous offsets have been inserted in the
   * table or the offset stored in the table is out of range in the partition.
   * Defaults to {@code earliest}.
   */
  private[kafka] final val AUTO_OFFSET_RESET: Config[String] = Config[String](
    "AUTO_OFFSET_RESET",
    ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
    "earliest"
  )

  /**
   * This is the {@code max.poll.records} configuration setting.
   *
   * It is the maximum number of records returned in a single call to
   * poll() function. Default value is `500`.
   */
  private[kafka] final val MAX_POLL_RECORDS: Config[String] = Config[String](
    "MAX_POLL_RECORDS",
    ConsumerConfig.MAX_POLL_RECORDS_CONFIG,
    "500"
  )

  /**
   * This is the {@code fetch.min.bytes} configuration setting.
   *
   * It is the minimum amount of data the server should return for a
   * fetch request. Default value is `1`.
   */
  private[kafka] final val FETCH_MIN_BYTES: Config[String] = Config[String](
    "FETCH_MIN_BYTES",
    ConsumerConfig.FETCH_MIN_BYTES_CONFIG,
    "1"
  )

  /**
   * This is the {@code fetch.max.bytes} configuration setting.
   *
   * It is the maximum amount of data the server should return for a
   * fetch request. Default value is
   * [[ConsumerConfig.DEFAULT_FETCH_MAX_BYTES]].
   */
  private[kafka] final val FETCH_MAX_BYTES: Config[String] = Config[String](
    "FETCH_MAX_BYTES",
    ConsumerConfig.FETCH_MAX_BYTES_CONFIG,
    s"${ConsumerConfig.DEFAULT_FETCH_MAX_BYTES}"
  )

  /**
   * This is the {@code max.partition.fetch.bytes} configuration
   * setting.
   *
   * It is the maximum amount of data the server will return per
   * partition. Default value is
   * [[ConsumerConfig.DEFAULT_MAX_PARTITION_FETCH_BYTES]].
   */
  private[kafka] final val MAX_PARTITION_FETCH_BYTES: Config[String] = Config[String](
    "MAX_PARTITION_FETCH_BYTES",
    ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
    s"${ConsumerConfig.DEFAULT_MAX_PARTITION_FETCH_BYTES}"
  )

  /**
   * An optional schema registry url.
   *
   * The Avro value deserializer will be used when user sets this
   * property value.
   */
  private[kafka] final val SCHEMA_REGISTRY_URL: Config[String] = Config[String](
    "SCHEMA_REGISTRY_URL",
    AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
    ""
  )

  /**
   * This is the {@code security.protocol} configuration setting.
   *
   * It is the protocol used to communicate with brokers, when
   * [[SSL_ENABLED]] is set to {@code true}. Default value is
   * [[SslConfigs.DEFAULT_SSL_PROTOCOL]].
   */
  private[kafka] final val SECURITY_PROTOCOL: Config[String] = Config[String](
    "SECURITY_PROTOCOL",
    CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
    SslConfigs.DEFAULT_SSL_PROTOCOL
  )

  /**
   * This is the {@code ssl.key.password} configuration setting.
   *
   * It represents the password of the private key in the key store
   * file. It is required property when [[SSL_ENABLED]] is set to {@code
   * true}.
   */
  private[kafka] final val SSL_KEY_PASSWORD: Config[String] = Config[String](
    "SSL_KEY_PASSWORD",
    SslConfigs.SSL_KEY_PASSWORD_CONFIG,
    ""
  )

  /**
   * This is the {@code ssl.keystore.password} confguration setting.
   *
   * It the store password for the keystore file. It is required
   * property when [[SSL_ENABLED]] is set to {@code true}.
   */
  private[kafka] final val SSL_KEYSTORE_PASSWORD: Config[String] = Config[String](
    "SSL_KEYSTORE_PASSWORD",
    SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG,
    ""
  )

  /**
   * This is the {@code ssl.keystore.location} configuration setting.
   *
   * It represents the location of the keystore file. It is required
   * property when [[SSL_ENABLED]] is set to {@code true} and can be
   * used for two-way authentication for the clients.
   */
  private[kafka] final val SSL_KEYSTORE_LOCATION: Config[String] = Config[String](
    "SSL_KEYSTORE_LOCATION",
    SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG,
    ""
  )

  /**
   * This is the {@code ssl.truststore.password} configuration setting.
   *
   * It is the password for the truststore file, and required property
   * when [[SSL_ENABLED]] is set to {@code true}.
   */
  private[kafka] final val SSL_TRUSTSTORE_PASSWORD: Config[String] = Config[String](
    "SSL_TRUSTSTORE_PASSWORD",
    SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,
    ""
  )

  /**
   * This is the {@code ssl.truststore.location} configuration setting.
   *
   * It is the location of the truststore file, and required property
   * when [[SSL_ENABLED]] is set to {@code true}.
   */
  private[kafka] final val SSL_TRUSTSTORE_LOCATION: Config[String] = Config[String](
    "SSL_TRUSTSTORE_LOCATION",
    SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG,
    ""
  )

  /**
   * This is the {@code ssl.endpoint.identification.algorithm}
   * configuration setting.
   *
   * It is the endpoint identification algorithm to validate server
   * hostname using server certificate. It is used when [[SSL_ENABLED]]
   * is set to {@code true}. Default value is
   * [[SslConfigs.DEFAULT_SSL_ENDPOINT_IDENTIFICATION_ALGORITHM]].
   */
  private[kafka] final val SSL_ENDPOINT_IDENTIFICATION_ALGORITHM: Config[String] = Config[String](
    "SSL_ENDPOINT_IDENTIFICATION_ALGORITHM",
    SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG,
    SslConfigs.DEFAULT_SSL_ENDPOINT_IDENTIFICATION_ALGORITHM
  )

  /**
   * Creates [[KafkaConsumerProperties]] instance.
   *
   * @param params key value map
   * @return an instance of [[KafkaConsumerProperties]]
   */
  def apply(params: Map[String, String]): KafkaConsumerProperties =
    createConsumerProperties(params, None)

  /**
   * Creates [[KafkaConsumerProperties]] instance.
   *
   * @param params key value map
   * @param metadata an Exasol metadata object
   * @return an instance of [[KafkaConsumerProperties]]
   */
  def apply(params: Map[String, String], metadata: ExaMetadata): KafkaConsumerProperties =
    createConsumerProperties(params, Option(metadata))

  /**
   * Creates [[KafkaConsumerProperties]] instance.
   *
   * @param string key value separated string
   * @return an instance of [[KafkaConsumerProperties]]
   */
  def apply(string: String): KafkaConsumerProperties =
    createConsumerProperties(mapFromString(string), None)

  /**
   * Creates [[KafkaConsumerProperties]] instance.
   *
   * @param string key value separated string
   * @param metadata an Exasol metadata object
   * @return an instance of [[KafkaConsumerProperties]]
   */
  def apply(string: String, metadata: ExaMetadata): KafkaConsumerProperties =
    createConsumerProperties(mapFromString(string), Option(metadata))

  private[this] def createConsumerProperties(
    params: Map[String, String],
    metadataOpt: Option[ExaMetadata]
  ): KafkaConsumerProperties = {
    val properties = new KafkaConsumerProperties(params)
    metadataOpt.fold(properties) { metadata =>
      validateNoSSLCredentials(properties)
      if (properties.hasNamedConnection()) {
        val newProperties = properties.mergeWithConnectionObject(metadata)
        validateSSLLocationFilesExist(newProperties)
        newProperties
      } else {
        properties
      }
    }
  }

  private[this] def validateNoSSLCredentials(properties: KafkaConsumerProperties): Unit =
    if (properties.isSSLEnabled()) {
      val secureConnectionProperties = List(
        SSL_KEYSTORE_LOCATION,
        SSL_KEYSTORE_PASSWORD,
        SSL_KEY_PASSWORD,
        SSL_TRUSTSTORE_LOCATION,
        SSL_TRUSTSTORE_PASSWORD
      ).map(_.userPropertyName)
      if (secureConnectionProperties.exists(p => properties.containsKey(p))) {
        throw new KafkaConnectorException(
          "Please use a named connection object to provide secure SSL properties."
        )
      }
    }

  private[this] def validateSSLLocationFilesExist(properties: KafkaConsumerProperties): Unit =
    if (properties.isSSLEnabled()) {
      if (!Files.isRegularFile(Paths.get(properties.getSSLKeystoreLocation()))) {
        throw new KafkaConnectorException(
          s"Unable to find the SSL keystore file '${properties.getSSLKeystoreLocation()}'. " +
            s"Please make sure it is successfully uploaded to BucketFS bucket."
        )
      }
      if (!Files.isRegularFile(Paths.get(properties.getSSLTruststoreLocation()))) {
        throw new KafkaConnectorException(
          s"Unable to find the SSL truststore file '${properties.getSSLTruststoreLocation()}'. " +
            s"Please make sure it is successfully uploaded to BucketFS bucket."
        )
      }
    }

}
