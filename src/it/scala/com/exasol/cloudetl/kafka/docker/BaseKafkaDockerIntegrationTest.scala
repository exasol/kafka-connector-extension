package com.exasol.cloudetl.kafka

import java.util.List
import java.util.Map
import java.util.concurrent.TimeUnit

import org.apache.kafka.clients.admin._
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.testcontainers.containers.Container
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName

trait BaseKafkaDockerIntegrationTest extends BaseDockerIntegrationTest {

  private[this] val DEFAULT_CONFLUENT_PLATFORM_VERSION = "6.2.0"
  private[this] val ZOOKEEPER_PORT = 2181
  private[this] val SCHEMA_REGISTRY_PORT = 8081
  var adminClient: AdminClient = _

  val zookeeperContainer = {
    val image = DockerImageName
      .parse("confluentinc/cp-zookeeper")
      .withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION)
    val c: GenericContainer[_] = new GenericContainer(image)
    c.withNetwork(network)
    c.withNetworkAliases("zookeeper")
    c.withEnv("ZOOKEEPER_CLIENT_PORT", s"$ZOOKEEPER_PORT")
    c.withReuse(true)
    c
  }

  val schemaRegistryContainer = {
    val image = DockerImageName
      .parse("confluentinc/cp-schema-registry")
      .withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION)
    val c: GenericContainer[_] = new GenericContainer(image)
    c.withNetwork(network)
    c.withNetworkAliases("schema-registry")
    c.withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
    c.withEnv(
      "SCHEMA_REGISTRY_KAFKASTORE_CONNECTION_URL",
      s"${zookeeperContainer.getHost()}:$ZOOKEEPER_PORT"
    )
    c.withReuse(true)
    c
  }

  val kafkaBrokerContainer = {
    val image = DockerImageName
      .parse("confluentinc/cp-kafka")
      .withTag(DEFAULT_CONFLUENT_PLATFORM_VERSION)
    val c = new KafkaContainer(image)
    c.withCreateContainerCmdModifier {
      case cmd =>
        cmd.withHostName("kafka01")
        ()
    }
    c.dependsOn(zookeeperContainer)
    c.withNetwork(network)
    c.withNetworkAliases("kafka01")
    c.withExternalZookeeper(s"zookeeper:$ZOOKEEPER_PORT")
    c.withEnv("KAFKA_BROKER_ID", "0")
    c.withReuse(true)
    c
  }

  def getSchemaRegistryURL(): String = {
    val schemaRegistryHost = getContainerNetworkAddress(schemaRegistryContainer)
    val schemaRegistryPort = schemaRegistryContainer.getMappedPort(SCHEMA_REGISTRY_PORT)
    s"http://$schemaRegistryHost:$schemaRegistryPort"
  }

  def getBootstrapServers(): String = {
    val brokerHost = getContainerNetworkAddress(kafkaBrokerContainer)
    val brokerPort = kafkaBrokerContainer.getMappedPort(9093)
    s"$brokerHost:$brokerPort"
  }

  def prepareKafkaCluster(): Unit = {
    val properties: Map[String, Object] =
      Map.of(
        AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaBrokerContainer.getBootstrapServers()
      )
    adminClient = AdminClient.create(properties)
  }

  private[this] val ADMIN_TIMEOUT_MILLIS = 5000

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

  def produceRecords(topicName: String, values: Seq[String]): Unit = {
    val properties: Map[String, Object] = Map.of(
      ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
      kafkaBrokerContainer.getBootstrapServers(),
      ProducerConfig.CLIENT_ID_CONFIG,
      "consumer-group"
    )
    val producer = new KafkaProducer(
      properties,
      new StringSerializer(),
      new StringSerializer()
    )
    values.foreach {
      case value =>
        producer.send(new ProducerRecord(topicName, "key", value))
    }
    producer.flush()
    producer.close()
    ()
  }

  override def beforeAll(): Unit = {
    super.beforeAll()
    zookeeperContainer.start()
    schemaRegistryContainer.start()
    kafkaBrokerContainer.start()
    prepareKafkaCluster()
  }

  override def afterAll(): Unit = {
    zookeeperContainer.stop()
    schemaRegistryContainer.stop()
    kafkaBrokerContainer.stop()
    adminClient.close()
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
