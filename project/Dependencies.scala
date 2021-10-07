package com.exasol.cloudetl.sbt

import sbt.{ExclusionRule, _}
import sbt.librarymanagement.InclExclRule

/** A list of required dependencies */
object Dependencies {

  // Runtime dependencies versions
  private val ImportExportUDFVersion = "0.3.0-SNAPSHOT"
  private val KafkaClientsVersion = "2.8.0"
  private val KafkaAvroSerializerVersion = "6.2.0"
  private val ScalaCollectionCompatVersion = "2.5.0"

  // Test dependencies versions
  private val ScalaTestVersion = "3.2.9"
  private val ScalaTestPlusVersion = "1.0.0-M2"
  private val MockitoCoreVersion = "3.12.4"
  private val KafkaSchemaRegistryVersion = "6.2.0"
  private val ExasolTestDBBuilderVersion = "3.2.1"
  private val ExasolTestContainersVersion = "4.0.1"
  private val ExasolHamcrestMatcherVersion = "1.4.1"

  val Resolvers: Seq[Resolver] = Seq(
     Resolver.mavenLocal,
    "jitpack.io" at "https://jitpack.io",
    "Confluent Maven Repo" at "https://packages.confluent.io/maven/",
    "Exasol Releases" at "https://maven.exasol.com/artifactory/exasol-releases"
  )

  lazy val RuntimeDependencies: Seq[ModuleID] = Seq(
    "com.exasol" %% "import-export-udf-common-scala" % ImportExportUDFVersion,
    "com.exasol" % "error-reporting-java" % "0.4.0",
    "org.apache.kafka" % "kafka-clients" % KafkaClientsVersion,
    "io.confluent" % "kafka-avro-serializer" % KafkaAvroSerializerVersion
      exclude ("org.slf4j", "slf4j-api")
      exclude ("org.apache.avro", "avro")
      exclude ("org.apache.commons", "commons-lang3")
      exclude ("com.google.guava", "guava")
      exclude ("com.fasterxml.jackson.core", "jackson-databind")
      exclude ("io.swagger", "swagger-core")
      exclude ("io.swagger", "swagger-models"),
    "org.scala-lang.modules" %% "scala-collection-compat" % ScalaCollectionCompatVersion
  )

  lazy val TestDependencies: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % ScalaTestVersion,
    "org.scalatestplus" %% "scalatestplus-mockito" % ScalaTestPlusVersion,
    "org.mockito" % "mockito-core" % MockitoCoreVersion,
    "io.github.embeddedkafka" %% "embedded-kafka-schema-registry" % KafkaSchemaRegistryVersion
      exclude ("com.fasterxml.jackson.core", "jackson-annotations")
      exclude ("com.fasterxml.jackson.core", "jackson-core")
      exclude ("com.fasterxml.jackson.core", "jackson-databind"),
    "com.exasol" % "exasol-testcontainers" % ExasolTestContainersVersion,
    "com.exasol" % "test-db-builder-java" % ExasolTestDBBuilderVersion,
    "com.exasol" % "hamcrest-resultset-matcher" % ExasolHamcrestMatcherVersion,
    "io.confluent" % "kafka-streams-avro-serde" % KafkaAvroSerializerVersion,
    "com.sksamuel.avro4s" %% "avro4s-core" % "4.0.10"
  ).map(_ % Test)

  lazy val AllDependencies: Seq[ModuleID] = RuntimeDependencies ++ TestDependencies

  lazy val ExcludedDependencies: Seq[InclExclRule] = Seq(
    ExclusionRule("org.openjfx", "javafx.base")
  )

}
