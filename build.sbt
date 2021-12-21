import com.exasol.cloudetl.sbt.Dependencies
import com.exasol.cloudetl.sbt.IntegrationTestPlugin
import com.exasol.cloudetl.sbt.Settings

lazy val orgSettings = Seq(
  name := "kafka-connector-extension",
  description := "Exasol Kafka Connector Extension",
  organization := "com.exasol",
  organizationHomepage := Some(url("http://www.exasol.com"))
)

lazy val buildSettings = Seq(
  scalaVersion := "2.13.7"
)

lazy val root =
  project
    .in(file("."))
    .settings(moduleName := "exasol-kafka-connector-extension")
    .settings(version := "1.5.1")
    .settings(orgSettings)
    .settings(buildSettings)
    .settings(Settings.projectSettings(scalaVersion))
    .settings(
      resolvers ++= Dependencies.Resolvers,
      libraryDependencies ++= Dependencies.AllDependencies,
      excludeDependencies ++= Dependencies.ExcludedDependencies
    )
    .enablePlugins(IntegrationTestPlugin, ReproducibleBuildsPlugin)

addCommandAlias("pluginUpdates", ";reload plugins;dependencyUpdates;reload return")
