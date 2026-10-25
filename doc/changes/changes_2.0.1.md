# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-110879 in com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile

## Summary

This release fixes the following vulnerability:

### CVE-2026-110879 (CWE-502) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
jackson-databind - Deserialization of Untrusted Data
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110879?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110879
* https://github.com/FasterXML/jackson/wiki/Jackson-Release-2.18.12
* https://github.com/FasterXML/jackson-databind/pull/6287

## Security

* #285: Fixed vulnerability CVE-2026-110879 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.6`
* Updated `com.exasol:import-export-udf-common-scala:2.0.3` to `2.0.5`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.2`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.2-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.10.0`
* Updated `org.slf4j:slf4j-api:2.0.18` to `2.0.20`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:8.0.1` to `8.0.2`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.3` to `1.7.4`
* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.3`
* Updated `com.exasol:udf-debugging-java:0.6.20` to `0.6.22`
* Updated `com.google.code.gson:gson:2.14.0` to `2.15.0`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.2`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.commons:commons-lang3:3.20.0` to `3.21.0`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.2-ce`
* Updated `org.apache.mina:mina-core:2.2.9` to `2.2.10`
* Updated `org.apache.zookeeper:zookeeper:3.9.5` to `3.9.6`
* Updated `org.mockito:mockito-junit-jupiter:5.23.0` to `5.24.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.8.1`
