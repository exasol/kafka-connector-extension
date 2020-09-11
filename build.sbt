import com.exasol.cloudetl.sbt.Dependencies
import com.exasol.cloudetl.sbt.Settings
import com.exasol.cloudetl.sbt.IntegrationTestPlugin

lazy val orgSettings = Seq(
  name := "cloud-storage-etl-udfs",
  description := "Exasol Public Cloud Storage ETL User Defined Functions",
  organization := "com.exasol",
  organizationHomepage := Some(url("http://www.exasol.com"))
)

lazy val buildSettings = Seq(
  scalaVersion := "2.12.12",
  crossScalaVersions := Seq("2.11.12", "2.12.12")
)

lazy val root =
  project
    .in(file("."))
    .settings(orgSettings)
    .settings(buildSettings)
    .settings(Settings.commonSettings(scalaVersion))
    .disablePlugins(AssemblyPlugin, IntegrationTestPlugin, GitVersioning)
    .aggregate(streamingkafka)

lazy val streamingkafka =
  project
    .in(file("streaming-kafka"))
    .settings(moduleName := "exasol-kafka-connector-extension")
    .settings(orgSettings)
    .settings(buildSettings)
    .settings(Settings.commonSettings(scalaVersion))
    .settings(Settings.integrationTestSettings)
    .settings(Settings.assemblySettings)
    .settings(
      resolvers ++= Dependencies.ExasolResolvers,
      resolvers ++= Dependencies.ConfluentResolvers,
      libraryDependencies ++= Seq("com.exasol" %% "import-export-udf-common-scala" % "0.1.0"),
      libraryDependencies ++= Dependencies.CommonDependencies,
      libraryDependencies ++= Dependencies.KafkaDependencies,
      excludeDependencies ++= Dependencies.KafkaExcludedDependencies
    )
    .enablePlugins(IntegrationTestPlugin, GitVersioning)

addCommandAlias("pluginUpdates", ";reload plugins;dependencyUpdates;reload return")
