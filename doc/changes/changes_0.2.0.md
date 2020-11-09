# Kafka Connector Extension 0.2.0, released 2020-10-30

## Summary

This release includes Avro Complex (Array, Map, Nested Record) and
Logical (BigDecimal, Date, Timestamp) data type mapping support. In
addition, it fixes a bug related to the missing logging dependency.

## Bug Fixes

* #13: Fixed logging issue (PR #15)

## Features

* #17: Added Support for Avro Complex and Logical Types (PR #15)

## Dependency Updates

### Runtime Dependency Updates

* Updated `com.exasol:import-export-udf-common-scala` from `0.1.0` to `0.2.0`.

### Test Dependency Updates

* Updated `org.mockito:mockito-core` from `3.5.10` to `3.6.0`.

### Plugin Updates

* Updated `com.github.cb372:sbt-explicit-dependencies` from `0.2.13` to `0.2.15`.
* Updated `org.wartremover:sbt-wartremover` from `2.4.10` to `2.4.12`.
* Updated `org.wartremover:sbt-wartremover-contib` from `1.3.8` to `1.3.10`.
