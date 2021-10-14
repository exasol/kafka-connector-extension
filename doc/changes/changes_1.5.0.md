# Kafka Connector Extension 1.5.0, released 2021-10-??

Code name:

## Summary

## Bug Fixes

* #54: Fixed issue with setting empty values to property keys

## Refactoring

* #55: Added optional check for keystore or truststore files when using `SASL_SSL` protocol

## Dependency Updates

### Runtime Dependency Updates

* Updated `io.confluent:kafka-avro-serializer:6.2.0` to `6.2.1`
* Updated `org.apache.kafka:kafka-clients:2.8.0` to `3.0.0`

### Test Dependency Updates

* Updated `org.scalatest:scalatest:3.2.9` to `3.2.10`
* Updated `org.mockito:mockito-core:3.12.4` to `4.0.0`
* Updated `com.exasol:exasol-testcontainers:4.0.1` to `5.1.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.4.1` to `1.5.1`
* Updated `com.sksamuel.avro4s:avro4s-core:4.0.10` to `4.0.11`
* Updated `io.confluent:kafka-streams-avro-serde:6.2.0` to `6.2.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry:6.2.0` to `6.2.1`

### Plugin Updates

* Updated `org.scoverage:sbt-scoverage:1.8.2` to `1.9.0`
