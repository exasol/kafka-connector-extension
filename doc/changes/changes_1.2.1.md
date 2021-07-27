# Kafka Connector Extension 1.2.1, released 2021-07-27

Code name: Bug Fixes and Refactorings

## Summary

In this release we fixed a bug that occurs when Kafka resets offsets while user consuming all records until the latest offset. We also migrated the continuous integration (CI) environment to Github actions.

## Bug Fixes

* #41: Fixed failing consumer when Kafka topic offset resets

## Refactoring

* #44: Migrated to Github Actions build infrastructure

### Runtime Dependency Updates

* Updated `org.scala-lang.modules:scala-collection-compat:2.4.4` to `2.5.0`

### Test Dependency Updates

* Updated `org.testcontainers:kafka:test:1.15.3` to `1.16.0`

### Plugin Updates

* Added `org.scalameta:sbt-scalafmt:2.4.3`
* Updated `org.wartremover:sbt-wartremover:2.4.15` to `2.4.16`
* Removed `com.lucidchart:sbt-scalafmt-coursier:1.16`
* Removed `com.typesafe.sbt:sbt-git:1.0.1`
* Removed `com.github.cb372:sbt-explicit-dependencies:0.2.16`
* Updated `org.wartremover:sbt-wartremover:2.4.15` to `2.4.16`