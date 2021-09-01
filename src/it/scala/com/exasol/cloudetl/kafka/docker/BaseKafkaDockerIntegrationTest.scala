package com.exasol.cloudetl.kafka

import java.util.List
import java.util.Map
import java.util.concurrent.TimeUnit
import java.util.stream.Stream

import com.exasol.cloudetl.kafka.serde._

import org.apache.kafka.clients.admin._
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.testcontainers.containers.Container
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.lifecycle.Startables
import org.testcontainers.utility.DockerImageName

trait BaseKafkaDockerIntegrationTest extends BaseDockerIntegrationTest {

  private[this] val DEFAULT_CONFLUENT_PLATFORM_VERSION = "6.2.0"
  private[this] val ZOOKEEPER_PORT = 2181
  private[this] val KAFKA_EXTERNAL_PORT = 29092
  private[this] val SCHEMA_REGISTRY_PORT = 8081
  private[this] val ADMIN_TIMEOUT_MILLIS = 5000

  var adminClient: AdminClient = _

  val zookeeperContainer = {
    val image = DockerImageName.parse("confluentinc/cp-zookeeper").withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION)
    val c: GenericContainer[_] = new GenericContainer(image)
    c.withNetwork(network)
    c.withNetworkAliases("zookeeper")
    c.withEnv("ZOOKEEPER_CLIENT_PORT", s"$ZOOKEEPER_PORT")
    c.withReuse(true)
    c
  }

  val kafkaBrokerContainer = {
    val image = DockerImageName.parse("confluentinc/cp-kafka").withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION)
    val c: GenericContainer[_] = new KafkaContainer(image, KAFKA_EXTERNAL_PORT)
    c.dependsOn(zookeeperContainer)
    c.withNetwork(network)
    c.withNetworkAliases("kafka01")
    c.withEnv("KAFKA_ZOOKEEPER_CONNECT", s"zookeeper:$ZOOKEEPER_PORT")
    c.withEnv("KAFKA_BROKER_ID", "0")
    c.withEnv("KAFKA_LISTENER_SECURITY_PROTOCOL_MAP", "EXTERNAL:PLAINTEXT,INTERNAL:PLAINTEXT")
    c.withEnv("KAFKA_LISTENERS", s"INTERNAL://0.0.0.0:9092,EXTERNAL://0.0.0.0:$KAFKA_EXTERNAL_PORT")
    c.withEnv("KAFKA_ADVERTISED_LISTENERS", s"INTERNAL://kafka01:9092,EXTERNAL://127.0.0.1:$KAFKA_EXTERNAL_PORT")
    c.withEnv("KAFKA_INTER_BROKER_LISTENER_NAME", "INTERNAL")
    c.withEnv("KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR", "1")
    c.withExtraHost("kafka01", "127.0.0.1")
    c.withReuse(true)
    c
  }

  val schemaRegistryContainer = {
    val image = DockerImageName.parse("confluentinc/cp-schema-registry").withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION)
    val c: GenericContainer[_] = new SchemaRegistryContainer(image, SCHEMA_REGISTRY_PORT)
    c.dependsOn(kafkaBrokerContainer)
    c.withNetwork(network)
    c.withNetworkAliases("schema-registry")
    c.withEnv("SCHEMA_REGISTRY_HOST_NAME", "localhost")
    c.withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "PLAINTEXT://kafka01:9092")
    c.withEnv("SCHEMA_REGISTRY_KAFKASTORE_TOPIC_REPLICATION_FACTOR", "1")
    c.withExposedPorts(SCHEMA_REGISTRY_PORT)
    c.waitingFor(Wait.forHttp("/subjects").forStatusCode(200))
    c.withReuse(true)
    c
  }

  def getExternalSchemaRegistryUrl(): String =
    s"http://localhost:$SCHEMA_REGISTRY_PORT"

  def getExternalBootstrapServers(): String =
    s"localhost:$KAFKA_EXTERNAL_PORT"

  def setupAdminClient(): Unit = {
    val properties: Map[String, Object] =
      Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, getExternalBootstrapServers())
    adminClient = AdminClient.create(properties)
  }

  def isTopicExist(topicName: String): Boolean = {
    val options = new ListTopicsOptions()
    options.timeoutMs(ADMIN_TIMEOUT_MILLIS)
    options.listInternal(false)
    val topicNames = adminClient.listTopics(options).names().get()
    topicNames.contains(topicName)
  }

  def deleteTopic(topicName: String): Unit = {
    val options = new DeleteTopicsOptions()
    options.timeoutMs(ADMIN_TIMEOUT_MILLIS)
    adminClient.deleteTopics(List.of(topicName), options).all().get()
    ()
  }

  def createTopic(topicName: String): Unit =
    if (!isTopicExist(topicName)) {
      val topics = List.of(new NewTopic(topicName, 1, 1.toShort))
      adminClient.createTopics(topics).all().get(30, TimeUnit.SECONDS)
      ()
    }

  def produceRecords[V: ValueSerde](topicName: String, values: Seq[V]): Unit = {
    val properties: Map[String, Object] = Map.of(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
      getExternalBootstrapServers(),
      ProducerConfig.CLIENT_ID_CONFIG,
      "consumer-group"
    )
    val producer = new KafkaProducer[String, V](
      properties,
      new StringSerializer(),
      ValueSerializer[V]
    )
    values.foreach { case value =>
      producer.send(new ProducerRecord[String, V](topicName, "key", value))
    }
    producer.flush()
    producer.close()
    ()
  }

  override def beforeAll(): Unit = {
    super.beforeAll()
    zookeeperContainer.start()
    kafkaBrokerContainer.start()
    schemaRegistryContainer.start()
    setupAdminClient()
  }

  override def afterAll(): Unit = {
    adminClient.close()
    schemaRegistryContainer.stop()
    kafkaBrokerContainer.stop()
    zookeeperContainer.stop()
    super.afterAll()
  }

  def getContainerNetworkAddress(container: Container[_]): String =
    container
      .getContainerInfo()
      .getNetworkSettings()
      .getNetworks()
      .values()
      .iterator()
      .next()
      .getIpAddress()

}
