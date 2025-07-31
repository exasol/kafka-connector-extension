# Kafka Connector Extension 1.7.14, released 2025-07-31

Code name: Fixed test dependencies 

## Summary

This release updates dependencies to fix CVE-2025-53864 and CVE-2025-48924 in transitive test dependencies 
`com.google.code.gson:gson:jar:2.10.1:test` and `org.apache.commons:commons-lang3:jar:3.17.0:test` respectively

## Security

* #145 Fix CVE-2025-53864 in com.google.code.gson:gson:jar:2.10.1:test
* #144 Fix CVE-2025-48924 in org.apache.commons:commons-lang3:jar:3.17.0:test

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Added `com.google.code.gson:gson:2.13.1`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.4` to `7.1.7`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.0` to `1.7.1`
* Updated `com.exasol:test-db-builder-java:3.6.0` to `3.6.2`
* Updated `org.mockito:mockito-core:5.17.0` to `5.18.0`
* Updated `org.testcontainers:kafka:1.20.6` to `1.21.3`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.3` to `2.0.4`
* Updated `com.exasol:project-keeper-maven-plugin:5.2.2` to `5.2.3`
