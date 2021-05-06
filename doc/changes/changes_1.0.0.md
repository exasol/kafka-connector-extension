# Kafka Connector Extension 1.0.0, released 2021-05-DD

Code name: v1.0.0 - Support for JSON Records import and offset reset strategy

## Summary

The release `1.0.0` added support for importing JSON records and consumer offset
reset strategy. By using offset strategy consumer imports new records after
topic retention.

## Features

* #24: Added support for importing JSON records (PR #25).
* Added support for offset reset strategy (PR #26).

## Bugfixes

* #20: Fixed the typo in the CI deployment (PR #21).

### Runtime Dependency Updates

* Updated `sbt.version:1.4.2` to `1.4.4`
* Updated Scala version `2.12.11` to `2.12.13` for cross compilation
* Updated `org.apache.kafka:kafka-clients:2.6.0` to `2.8.0`
* Updated `io.confluent:kafka-avro-serializer:6.0.0` to `6.1.1`
* Updated `org.scala-lang.modules:scala-collection-compat:2.2.0` to `2.4.3`

### Test Dependency Updates

* Updated `org.scalatest:scalatest:3.2.3` to `3.2.8`
* Updated `org.mockito:mockito-core:3.6.0` to `3.9.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry:6.0.0` to `6.1.1`

### Plugin Updates

* Updated `com.timushev.sbt:sbt-updates:0.5.1` to `0.5.3`
* Updated `org.scoverage:sbt-scoverage:1.6.1` to `1.7.3`
* Updated `org.wartremover:sbt-wartremover:2.4.12` to `2.4.13`
* Updated `org.wartremover:sbt-wartremover-contib:1.3.10` to `1.3.11`
* Updated `com.github.cb372:sbt-explicit-dependencies:0.2.15` to `0.2.16`

