# Kafka Connector Extension 1.3.0, released 2021-08-06

Code name: Support SASL Authentication

## Summary

This release adds support for SASL authentication. Importer client can choose SASL mechanism (_PLAIN_, _DIGEST-*_, _SCRAM-*_) and provide JAAS configuration for authentication. Please check the user guide for more information.

## Features

* #42: Added support for SASL_PLAINTEXT and SASL_SSL security protocols

### Runtime Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:3.5.3` to `4.0.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.4.0` to `1.4.1`
* Updated `com.exasol:test-db-builder-java:3.2.0` to `3.2.1`

### Plugin Updates

* Updated `com.timushev.sbt:sbt-updates:0.5.3` to `0.6.0`
