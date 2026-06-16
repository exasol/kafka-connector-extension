# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Migrate to Java

## Summary

This release includes internal refactoring. It migrates the source code from Scala to Java to simplify development. It also improves test coverage.

## Refactoring

* #195: Migrate production code from Scala to Java

## Dependency Updates

### Exasol Kafka Connector Extension

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.3.0`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.20`
* Removed `com.sksamuel.avro4s:avro4s-core_2.13:4.1.2`
* Added `nl.jqno.equalsverifier:equalsverifier:3.19.4`
* Removed `org.scalatestplus:scalatestplus-mockito_2.13:1.0.0-SNAP5`
* Removed `org.scalatest:scalatest_2.13:3.3.0-SNAP4`

#### Plugin Dependency Updates

* Removed `com.diffplug.spotless:spotless-maven-plugin:2.44.4`
* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.5` to `2.0.7`
* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.6.2`
* Removed `io.github.evis:scalafix-maven-plugin_2.13:0.1.10_0.11.0`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.2` to `10.0.0`
* Removed `net.alchim31.maven:scala-maven-plugin:4.9.5`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.7.1` to `3.8.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.1` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.4` to `3.5.5`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.4.2` to `3.5.0`
* Removed `org.apache.maven.plugins:maven-javadoc-plugin:3.11.2`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.4` to `3.5.5`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.19.1` to `2.21.0`
* Removed `org.scalastyle:scalastyle-maven-plugin:1.0.0`
* Removed `org.scalatest:scalatest-maven-plugin:2.2.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.2.0.4988` to `5.5.0.6356`
