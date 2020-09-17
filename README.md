# Kafka Connector Extension

[![Build Status][travis-badge]][travis-link]
[![Coveralls][coveralls-badge]][coveralls-link]
[![GitHub Release][gh-release-badge]][gh-release-link]

Exasol Kafka Extension provides UDF scripts that allow to access Apache Kafka
and import data from Kafka topic into an Exasol table.

## Features

* Imports Apache Avro formatted data from Apache Kafka clusters
* Allows secure connection to Apache Kafka clusters

## Information for Users

* [User Guide](doc/user_guide/user_guide.md)
* [Changelog](doc/changes/changelog.md)

Additional resources:

* [Tech Blog: How to import data from Apache Kafka with Exasol][tech-blog-part1]
* [Tech Blog: How to import data from Apache Kafka using our user-defined functions][tech-blog-part2]

## Information for Contributors

* [Developer Guide](doc/development/developer_guide.md)
* [Releasing Guide](doc/development/releasing.md)

## Dependencies

### Runtime Dependencies

| Dependency                                  | Purpose                                                         | License            |
|---------------------------------------------|-----------------------------------------------------------------|--------------------|
| [Exasol Script API][exasol-script-api-link] | Accessing Exasol IMPORT / EXPORT API                            | MIT License        |
| [Exasol Import Export UDF Common][ieudf]    | Common Import Export Libraries                                  | MIT License        |
| [Apache Kafka Clients][kafka-clients-link]  | An Apache Kafka client support for Java / Scala                 | Apache License 2.0 |
| [Kafka Avro Serializer][kafka-avro-link]    | Support for serializing / deserializing Avro formats with Kafka | Apache License 2.0 |
| [Scala Logging Library][scala-logging-link] | Scala logging library wrapping SLF4J                            | Apache License 2.0 |

### Test Dependencies

| Dependency                                  | Purpose                                                         | License            |
|---------------------------------------------|-----------------------------------------------------------------|--------------------|
| [Scalatest][scalatest-link]                 | Testing tool for Scala and Java developers                      | Apache License 2.0 |
| [Scalatest Plus][scalatestplus-link]        | Integration support between Scalatest and Mockito               | Apache License 2.0 |
| [Mockito Core][mockitocore-link]            | Mocking framework for unit tests                                | MIT License        |
| [Embedded Kafka Schema Registry][kafka-link]| In memory instances of Kafka and Schema registry for tests      | MIT License        |

### Compiler Plugin Dependencies

These plugins help with project development.

| Plugin Name                                 | Purpose                                                         | License              |
|---------------------------------------------|-----------------------------------------------------------------|----------------------|
| [SBT Coursier][sbt-coursier-link]           | Pure Scala artifact fetching                                    | Apache License 2.0   |
| [SBT Wartremover][sbt-wartremover-link]     | Flexible Scala code linting tool                                | Apache License 2.0   |
| [SBT Wartremover Contrib][sbt-wcontrib-link]| Community managed additional warts for wartremover              | Apache License 2.0   |
| [SBT Assembly][sbt-assembly-link]           | Create fat jars with all project dependencies                   | MIT License          |
| [SBT API Mappings][sbt-apimapping-link]     | A plugin that fetches API mappings for common Scala libraries   | Apache License 2.0   |
| [SBT Scoverage][sbt-scoverage-link]         | Integrates the scoverage code coverage library                  | Apache License 2.0   |
| [SBT Coveralls][sbt-coveralls-link]         | Uploads scala code coverage results to https://coveralls.io     | Apache License 2.0   |
| [SBT Updates][sbt-updates-link]             | Checks Maven and Ivy repositories for dependency updates        | BSD 3-Clause License |
| [SBT Scalafmt][sbt-scalafmt-link]           | A plugin for https://scalameta.org/scalafmt/ formatting         | Apache License 2.0   |
| [SBT Scalastyle][sbt-style-link]            | A plugin for http://www.scalastyle.org/ Scala style checker     | Apache License 2.0   |
| [SBT Dependency Graph][sbt-depgraph-link]   | A plugin for visualizing dependency graph of your project       | Apache License 2.0   |
| [SBT Explicit Dependencies][sbt-expdep-link]| Checks which direct libraries required to compile your code     | Apache License 2.0   |
| [SBT Git][sbt-git-link]                     | A plugin for Git integration, used to version the release jars  | BSD 2-Clause License |

[travis-badge]: https://img.shields.io/travis/exasol/kafka-connector-extension/master.svg?logo=travis
[travis-link]: https://travis-ci.com/exasol/kafka-connector-extension
[coveralls-badge]: https://coveralls.io/repos/github/exasol/kafka-connector-extension/badge.svg?branch=master
[coveralls-link]: https://coveralls.io/github/exasol/kafka-connector-extension?branch=master
[gh-release-badge]: https://img.shields.io/github/release/exasol/kafka-connector-extension.svg?logo=github
[gh-release-link]: https://github.com/exasol/kafka-connector-extension/releases/latest
[exasol-script-api-link]: https://docs.exasol.com/database_concepts/udf_scripts.htm
[ieudf]: https://github.com/exasol/import-export-udf-common-scala
[kafka-clients-link]: https://github.com/apache/kafka/tree/trunk/clients
[kafka-avro-link]: https://github.com/confluentinc/schema-registry/tree/master/avro-serializer
[scala-logging-link]: https://github.com/lightbend/scala-logging
[scalatest-link]: http://www.scalatest.org/
[scalatestplus-link]: https://github.com/scalatest/scalatestplus-mockito
[mockitocore-link]: https://site.mockito.org/
[kafka-link]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[sbt-coursier-link]: https://github.com/coursier/coursier
[sbt-wartremover-link]: http://github.com/puffnfresh/wartremover
[sbt-wcontrib-link]: http://github.com/wartremover/wartremover-contrib
[sbt-assembly-link]: https://github.com/sbt/sbt-assembly
[sbt-apimapping-link]: https://github.com/ThoughtWorksInc/sbt-api-mappings
[sbt-scoverage-link]: http://github.com/scoverage/sbt-scoverage
[sbt-coveralls-link]: https://github.com/scoverage/sbt-coveralls
[sbt-updates-link]: http://github.com/rtimush/sbt-updates
[sbt-scalafmt-link]: https://github.com/lucidsoftware/neo-sbt-scalafmt
[sbt-style-link]: https://github.com/scalastyle/scalastyle-sbt-plugin
[sbt-depgraph-link]: https://github.com/jrudolph/sbt-dependency-graph
[sbt-git-link]: https://github.com/sbt/sbt-git
[sbt-expdep-link]: https://github.com/cb372/sbt-explicit-dependencies
[tech-blog-part1]: https://community.exasol.com/t5/tech-blog/how-to-import-data-from-apache-kafka-with-exasol/ba-p/1409
[tech-blog-part2]: https://community.exasol.com/t5/tech-blog/how-to-import-data-from-apache-kafka-using-our-user-defined/ba-p/1699
