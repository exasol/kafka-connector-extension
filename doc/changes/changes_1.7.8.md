# Kafka Connector Extension 1.7.8, released 2024-??-??

Code name: Fixed vulnerability CVE-2024-47561 in org.apache.avro:avro:jar:1.11.3:compile

## Summary

This release fixes the following vulnerability:

### CVE-2024-47561 (CWE-502) in dependency `org.apache.avro:avro:jar:1.11.3:compile`
Schema parsing in the Java SDK of Apache Avro 1.11.3 and previous versions allows bad actors to execute arbitrary code.
Users are recommended to upgrade to version 1.11.4Â  or 1.12.0, which fix this issue.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-47561?component-type=maven&component-name=org.apache.avro%2Favro&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-47561
* https://lists.apache.org/thread/c2v7mhqnmq0jmbwxqq3r5jbj1xg43h5x

## Security

* #106: Fixed vulnerability CVE-2024-47561 in dependency `org.apache.avro:avro:jar:1.11.3:compile`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.6` to `1.5.8`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.18.0`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.1-jre`
* Updated `io.confluent:kafka-avro-serializer:7.6.0` to `7.7.1`
* Updated `org.apache.avro:avro:1.11.3` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`
* Updated `org.xerial.snappy:snappy-java:1.1.10.5` to `1.1.10.7`

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `com.google.protobuf:protobuf-java:3.25.5` to `4.28.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.6.0` to `7.7.1`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.177`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.6.0` to `7.7.1`
* Updated `joda-time:joda-time:2.12.7` to `2.13.0`
* Updated `org.apache.kafka:kafka-metadata:3.6.2` to `7.7.1-ce`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.1`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.2`
