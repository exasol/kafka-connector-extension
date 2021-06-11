# Kafka Connector Extension 1.1.0, released 2021-06-11

Code name: Support for fields selection and consuming all offset records

## Summary

The release `1.1.0` added support for accessing fields of Kafka record, added an option to consume records until the last offset of partition. In addition, we fixed bugs related to null record values, missing properties and case sensitive parameters.

## Features

* #28: Added support to access all fields in Kafka record
* #32: Added option to read until last offset of a partition

## Bugfixes

* #29: Fixed issues with record null values in a topic
* #30: Fixed bug with missing properties in connection object
* #35: Fixed bug with case sensitivity in record format parameters

### Runtime Dependency Updates

* Updated `sbt.version:1.4.4` to `1.5.3`
* Updated Scala version `2.13.5` to `2.13.6`
* Updated `org.apache.kafka:kafka-clients:2.6.0` to `2.8.0`
* Updated `org.scala-lang.modules:scala-collection-compat:2.4.3` to `2.4.4`

### Test Dependency Updates

* Updated `org.scalatest:scalatest:3.2.8` to `3.2.9`
* Updated `org.mockito:mockito-core:3.9.0` to `3.11.0`

### Plugin Updates

* Updated `com.eed3si9n:sbt-assembly:0.15.0` to `1.0.0`
* Updated `com.typesafe.sbt:sbt-git:1.0.0` to `1.0.1`
* Updated `org.scoverage:sbt-scoverage:1.7.3` to `1.8.2`
* Updated `org.wartremover:sbt-wartremover:2.4.13` to `2.4.15`
* Updated `org.wartremover:sbt-wartremover-contib:1.3.11` to `1.3.12`
