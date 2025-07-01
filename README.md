# Kafka Connector Extension

[![Build Status](https://github.com/exasol/kafka-connector-extension/actions/workflows/ci-build.yml/badge.svg)](https://github.com/exasol/kafka-connector-extension/actions/workflows/ci-build.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=security_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)

[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=code_smells)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=coverage)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.exasol%3Akafka-connector-extension&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.exasol%3Akafka-connector-extension)

Exasol Kafka Extension provides UDF scripts that allow accessing Apache Kafka
and importing data from a Kafka topic into an Exasol table.

## Features

* Imports Apache Avro formatted data from Apache Kafka clusters
* Imports JSON formatted data from Apache Kafka clusters
* Allows selecting record fields when importing
* Allows secure connection to Apache Kafka clusters

## Information for Users

* [User Guide](doc/user_guide/user_guide.md)
* [Changelog](doc/changes/changelog.md)

## Information for Contributors

* [Developer Guide](doc/development/developer_guide.md)
* [Dependencies](dependencies.md)
