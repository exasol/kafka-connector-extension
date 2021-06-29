# Kafka Connector Extension 1.2.0, released 2021-06-29

Code name: Fixed bug in consuming all offsets

## Summary

In this release, we fixed a bug that can go into infinite waiting loop if polled records from Kafka are empty.

## Bugfixes

* #39: Fixed bug related to consuming all offsets when already at end of partition

### Runtime Dependency Updates

* Updated `io.confluent:kafka-avro-serializer:6.1.1` to `6.2.0`

### Test Dependency Updates

* Added `com.exasol:test-db-builder-java:3.2.0`
* Added `com.exasol:exasol-testcontainers:3.5.3`
* Added `com.exasol:hamcrest-resultset-matcher:1.4.0`
* Added `org.testcontainers:kafka:1.15.3`
* Updated `org.mockito:mockito-core:3.11.0` to `3.11.2`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry:6.1.1` to `6.2.0`

### Plugin Updates

* Updated `org.scoverage:sbt-coveralls:1.2.7` to `1.3.1`
* Updated `net.bzzt:sbt-reproducible-builds:0.25` to `0.28`
