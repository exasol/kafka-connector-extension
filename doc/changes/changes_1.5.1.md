# Kafka Connector Extension 1.5.1, released 2021-10-15

Code name: Fixed setting SSL keystore and truststore files

## Summary

This release fixes a bug that skips setting SSL keystore and truststore files when using `SASL_SSL` protocol.

## Bug Fixes

* #61: Fixed bug that skips setting SSL files when using SASL_SSL protocol

## Dependency Updates

### Runtime Dependency Updates

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:5.1.0` to `5.1.1`

### Plugin Updates
