import com.exasol.cloudetl.sbt.Dependencies
import com.exasol.cloudetl.sbt.Settings
import com.exasol.cloudetl.sbt.IntegrationTestPlugin

lazy val orgSettings = Seq(
  name := "kafka-connector-extension",
  description := "Exasol Kafka Connector Extension",
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
    .settings(moduleName := "exasol-kafka-connector-extension")
    .settings(orgSettings)
    .settings(buildSettings)
    .settings(Settings.projectSettings(scalaVersion))
    .settings(
      resolvers ++= Dependencies.Resolvers,
      libraryDependencies ++= Dependencies.AllDependencies,
      excludeDependencies ++= Dependencies.ExcludedDependencies
    )
    .enablePlugins(IntegrationTestPlugin, GitVersioning)

addCommandAlias("pluginUpdates", ";reload plugins;dependencyUpdates;reload return")
