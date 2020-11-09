# Kafka Connector Extension 0.2.1, released DD-MM-YYYY

## Summary

This release includes new feature to import Kafka message as whole JSON document
in a single column in database

## Features / Improvements

* #16: Option-to-import-JSON-as-is (PR #18)
* #14: Updated Kafka Client Versions (PR #19)

## Dependency Updates

### Runtime Dependency Updates

* Updated `sbt.version` from `1.3.13` to `1.4.1`.
* Added Scala version to `2.13.3` as main compiler.
* Updated Scala version to `2.12.11` for cross compilation.
* Updated `org.apache.kafka:kafka-clients` from `2.5.0` to `2.6.0`.
* Updated `io.confluent:kafka-avro-serializer` from `5.4.0` to `6.0.0`.
* Added `org.scala-lang.modules:scala-collection-compat` version `2.2.0`.

### Test Dependency Updates

* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry` from `5.4.0` to `6.0.0`.
* Updated `org.scalatest:scalatest` from `3.2.2` to `3.2.3`.

### Plugin Updates
