# Kafka Connector Extension 1.4.0, released 2021-09-10

Code name: Automatic Timestamp Converter

## Summary

In this release, we added feature to convert long values to timestamps since epoch. In addition, we improved exceptions by adding our unified error codes.

## Features

* #51: Added timestamp converter for long values

## Refactorings

* #12: Added unified error codes

## Dependency Updates

### Runtime Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:4.0.0` to `4.0.1`
* Updated `org.mockito:mockito-core:3.11.2` to `3.12.4`
* Added `com.sksamuel.avro4s:avro4s-core:4.0.10`
* Added `io.confluent:kafka-streams-avro-serde:6.2.0`
* Removed `org.testcontainers:kafka:1.16.0`

### Plugin Updates

* Updated `com.eed3si9n:sbt-assembly:1.0.0` to `1.1.0`
* Updated `net.bzzt:sbt-reproducible-builds:0.28` to `0.30`
