package com.exasol.cloudetl.kafka

import java.io.File
import java.nio.file.Paths

import com.exasol.containers.ExasolContainer
import com.exasol.dbbuilder.dialects.Column
import com.exasol.dbbuilder.dialects.exasol.ExasolObjectFactory
import com.exasol.dbbuilder.dialects.exasol.ExasolSchema
import com.exasol.dbbuilder.dialects.exasol.udf.UdfScript

import org.scalatest.BeforeAndAfterAll
import org.scalatest.funsuite.AnyFunSuite

trait BaseDockerIntegrationTest extends AnyFunSuite with BeforeAndAfterAll {
  private[this] val JAR_DIRECTORY_PATTERN = "scala-"
  private[this] val JAR_NAME_PATTERN = "exasol-kafka-connector-extension-"
  private[this] val DEFAULT_EXASOL_DOCKER_IMAGE = "7.0.10"

  val network = DockerNamedNetwork("kafka-it-tests", true)
  val exasolContainer = {
    val c: ExasolContainer[_] = new ExasolContainer(getExasolDockerImageVersion())
    c.withExposedPorts(8563, 2580)
    c.withNetwork(network)
    c.withReuse(true)
    c
  }
  var factory: ExasolObjectFactory = _
  var schema: ExasolSchema = _
  val assembledJarName = getAssembledJarName()

  override def beforeAll(): Unit =
    exasolContainer.start()

  override def afterAll(): Unit =
    exasolContainer.stop()

  def prepareExasolDatabase(schemaName: String): Unit = {
    executeStmt(s"DROP SCHEMA IF EXISTS $schemaName CASCADE;")
    factory = new ExasolObjectFactory(getConnection())
    schema = factory.createSchema(schemaName)
    createKafkaImportDeploymentScripts()
    uploadJarToBucket()
  }

  def executeStmt(sql: String): Unit = {
    println(s"Executing: $sql")
    getConnection().createStatement().execute(sql)
    ()
  }

  def executeQuery(sql: String): java.sql.ResultSet =
    getConnection().createStatement().executeQuery(sql)

  private[this] def getConnection(): java.sql.Connection =
    exasolContainer.createConnection("")

  private[this] def getAssembledJarName(): String = {
    val jarDir = findFileOrDirectory("target", JAR_DIRECTORY_PATTERN)
    findFileOrDirectory("target/" + jarDir, JAR_NAME_PATTERN)
  }

  private[this] def createKafkaImportDeploymentScripts(): Unit = {
    val jarPath = s"/buckets/bfsdefault/default/$assembledJarName"
    schema
      .createUdfBuilder("KAFKA_CONSUMER")
      .language(UdfScript.Language.JAVA)
      .inputType(UdfScript.InputType.SET)
      .emits()
      .bucketFsContent("com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator", jarPath)
      .build()
    schema
      .createUdfBuilder("KAFKA_IMPORT")
      .language(UdfScript.Language.JAVA)
      .inputType(UdfScript.InputType.SET)
      .emits()
      .bucketFsContent("com.exasol.cloudetl.kafka.KafkaTopicDataImporter", jarPath)
      .build()
    schema
      .createUdfBuilder("KAFKA_METADATA")
      .language(UdfScript.Language.JAVA)
      .inputType(UdfScript.InputType.SET)
      .parameter("params", "VARCHAR(2000)")
      .parameter("kafka_partition", "DECIMAL(18, 0)")
      .parameter("kafka_offset", "DECIMAL(36, 0)")
      .emits(
        new Column("partition_index", "DECIMAL(18, 0)"),
        new Column("max_offset", "DECIMAL(36, 0)")
      )
      .bucketFsContent("com.exasol.cloudetl.kafka.KafkaTopicMetadataReader", jarPath)
      .build()
    ()
  }

  private[this] def uploadJarToBucket(): Unit = {
    val jarDir = findFileOrDirectory("target", JAR_DIRECTORY_PATTERN)
    val jarPath = Paths.get("target", jarDir, assembledJarName)
    exasolContainer.getDefaultBucket.uploadFile(jarPath, assembledJarName)
  }

  private[this] def findFileOrDirectory(searchDirectory: String, name: String): String = {
    val files = listDirectoryFiles(searchDirectory)
    val jarFile = files.find(_.getName.contains(name))
    jarFile match {
      case Some(jarFilename) => jarFilename.getName
      case None =>
        throw new IllegalArgumentException(
          s"Cannot find a file or a directory with pattern '$name' in '$searchDirectory'"
        )
    }
  }

  private[this] def listDirectoryFiles(directoryName: String): List[File] = {
    val directory = new File(directoryName)
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.toList
    } else {
      List.empty[File]
    }
  }

  private[this] def getExasolDockerImageVersion(): String =
    System.getProperty("EXASOL_DOCKER_VERSION", DEFAULT_EXASOL_DOCKER_IMAGE)

}