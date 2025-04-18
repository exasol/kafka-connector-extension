package com.exasol.cloudetl.kafka

import java.sql.ResultSet
import java.sql.Timestamp
import java.util.TimeZone
import java.util.UUID

import com.exasol.cloudetl.kafka.serde.AvroRecordFormat.Implicits._
import com.exasol.cloudetl.kafka.serde.AvroSerdes.Implicits._
import com.exasol.cloudetl.kafka.serde.PrimitiveSerdes.Implicits._
import com.exasol.cloudetl.kafka.serde._
import com.exasol.dbbuilder.dialects.Table
import com.exasol.matcher.ResultSetStructureMatcher.table
import com.exasol.matcher.TypeMatchMode.NO_JAVA_TYPE_CHECK

import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.scalatest.BeforeAndAfterEach

class KafkaImportIT extends BaseKafkaDockerIntegrationTest with BeforeAndAfterEach {

  private[this] val schemaName = "KAFKA_SCHEMA"
  private[this] val tableName = "KAFKA_TABLE"
  private[this] var topicName: String = _

  private[this] def getTableName(): String = s""""$schemaName"."$tableName""""

  override final def beforeAll(): Unit = {
    super.beforeAll()
    installKafkaConnector(schemaName)
    updateExasolHostsFile()
  }

  override final def beforeEach(): Unit = {
    topicName = "kafka-topic-" + UUID.randomUUID()
    createTopic(topicName)
  }

  override final def afterEach(): Unit = {
    executeStmt(s"DROP TABLE IF EXISTS ${getTableName()}")
    deleteTopic(topicName)
  }

  test("import longs as timestamp values") {
    final case class TimestampRecord(timestamp: Long)
    implicit val timestampRecordValueSerde = valueAvroSerde[TimestampRecord](getExternalSchemaRegistryUrl())

    TimeZone.setDefault(exasolContainer.getTimeZone())

    val timestamp = new Timestamp(System.currentTimeMillis())
    KafkaImportChecker(Map("COLUMN" -> "TIMESTAMP"))
      .withImportProperties(Map("RECORD_VALUE_FORMAT" -> "avro"))
      .withTopicValues(Seq(TimestampRecord(0L), TimestampRecord(1337L), TimestampRecord(timestamp.getTime())))
      .assert(
        table()
          .row(new Timestamp(0L), 0L, 0L)
          .row(new Timestamp(1337L), 0L, 1L)
          .row(timestamp, 0L, 2L)
          .matches(NO_JAVA_TYPE_CHECK)
      )
  }

  test("import string values") {
    KafkaImportChecker(Map("COLUMN" -> "VARCHAR(20)"))
      .withTopicValues(Seq("a", "b", "c"))
      .assert(
        table()
          .row("a", 0L, 0L)
          .row("b", 0L, 1L)
          .row("c", 0L, 2L)
          .matches(NO_JAVA_TYPE_CHECK)
      )
  }

  test("import with CONSUME_ALL_OFFSET parameter empty topic") {
    KafkaImportChecker(Map("COLUMN" -> "VARCHAR(20)"))
      .withImportProperties(Map("CONSUME_ALL_OFFSETS" -> "true"))
      .assert(table("VARCHAR", "BIGINT", "DECIMAL").matches())
  }

  test("import with CONSUME_ALL_OFFSET parameter") {
    val properties = Map(
      "CONSUME_ALL_OFFSETS" -> "true",
      "MAX_POLL_RECORDS" -> "2",
      "MIN_RECORDS_PER_RUN" -> "1",
      "MAX_RECORDS_PER_RUN" -> "2"
    )
    KafkaImportChecker(Map("COLUMN" -> "VARCHAR(20)"))
      .withImportProperties(properties)
      .withTopicValues(Seq("one", "two", "three"))
      .assert(
        table()
          .row("one", 0L, 0L)
          .row("two", 0L, 1L)
          .row("three", 0L, 2L)
          .matches(NO_JAVA_TYPE_CHECK)
      )
  }

  def updateExasolHostsFile(): Unit = {
    val brokerIpAddress = getContainerNetworkAddress(kafkaBrokerContainer)
    val schemaRegistryIpAddress = getContainerNetworkAddress(schemaRegistryContainer)
    val commands = Seq(
      // Workaround for sed failing on 8.32.0 with
      // sed: cannot rename /etc/sedipVlut: Device or resource busy
      s"cp /etc/hosts /tmp/hosts",
      s"sed --in-place '/kafka01/d' /tmp/hosts",
      s"sed --in-place '/schema-registry/d' /tmp/hosts",
      s"echo '$brokerIpAddress kafka01' >> /tmp/hosts",
      s"echo '$schemaRegistryIpAddress schema-registry' >> /tmp/hosts",
      s"cp /tmp/hosts /etc/hosts"
    )
    commands.foreach { case cmd =>
      val exitCode = exasolContainer.execInContainer("/bin/sh", "-c", cmd)
      if (exitCode.getExitCode() != 0) {
        throw new RuntimeException(s"Could not run command '$cmd' in Exasol container.")
      }
    }
  }

  case class KafkaImportChecker(columns: Map[String, String] = Map.empty) {
    private[this] val table = getTable()
    private[this] var properties: Map[String, String] = _

    def withImportProperties(props: Map[String, String]): KafkaImportChecker = {
      properties = props
      this
    }

    def withTopicValues[V: ValueSerde](values: Seq[V]): KafkaImportChecker = {
      produceRecords(topicName, values)
      this
    }

    def assert(matcher: Matcher[ResultSet]): Unit = {
      assertResultSet(assertThat(_, matcher))
      ()
    }

    private[this] def getTable(): Table = {
      var tableBuilder = schema.createTableBuilder(tableName)
      val metadataColumns = Map(
        "KAFKA_PARTITION" -> "DECIMAL(18, 0)",
        "KAFKA_OFFSET" -> "DECIMAL(36, 0)"
      )
      (columns ++ metadataColumns).foreach { case (columnName, columnType) =>
        tableBuilder = tableBuilder.column(columnName, columnType)
      }
      tableBuilder.build()
    }

    private[this] def getImportStatement(): String = {
      val builder = new StringBuilder(defaultImportStatement(table))
      if (properties == null || !properties.contains("RECORD_KEY_FORMAT")) {
        builder.append("\n").append("RECORD_KEY_FORMAT = 'string'")
      }
      if (properties == null || !properties.contains("RECORD_VALUE_FORMAT")) {
        builder.append("\n").append("RECORD_VALUE_FORMAT = 'string'")
      }
      if (properties != null) {
        properties.foreach { case (key, value) =>
          builder.append("\n").append(s"$key = '$value'")
        }
      }
      builder.append(";")
      builder.toString()
    }

    private[this] def assertResultSet(block: ResultSet => Unit): Unit = {
      executeStmt(getImportStatement())
      val rs = executeQuery(s"SELECT * FROM ${getTableName()}")
      block(rs)
      rs.close()
      ()
    }
  }

  private[this] def defaultImportStatement(table: Table): String =
    s"""|IMPORT INTO ${table.getFullyQualifiedName()}
        |FROM SCRIPT $schemaName.KAFKA_CONSUMER WITH
        |BOOTSTRAP_SERVERS   = 'kafka01:9092'
        |SCHEMA_REGISTRY_URL = 'http://schema-registry:8081'
        |TOPIC_NAME          = '$topicName'
        |TABLE_NAME          = '${table.getFullyQualifiedName()}'
        |POLL_TIMEOUT_MS     = '300'
        |GROUP_ID            = 'exasol-kafka-udf-consumers'
    """.stripMargin

}
