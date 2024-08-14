# Kafka Connector Extension 1.7.7, released 2024-??-??

Code name: Fix logging

## Summary

This release fixes logging of the UDF by adding required libraries. The log level is `WARN` by default and can be changed by rebuilding the adapter JAR. See the [Exasol documentation](https://docs.exasol.com/db/latest/database_concepts/udf_scripts/debug_udf_script_output.htm) for how to configure logging of UDFs.

## Features

* ISSUE_NUMBER: description

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Added `ch.qos.logback:logback-classic:1.5.6`
* Added `org.slf4j:slf4j-api:2.0.16`

#### Test Dependency Updates

* Removed `ch.qos.logback:logback-classic:1.5.3`
* Removed `ch.qos.logback:logback-core:1.5.3`
* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.1`

#### Plugin Dependency Updates

* Updated `org.itsallcode:openfasttrace-maven-plugin:1.8.0` to `2.0.0`
