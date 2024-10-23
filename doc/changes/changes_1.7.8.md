# Kafka Connector Extension 1.7.8, released 2024-??-??

Code name:

## Summary

## Security

* #106: CVE-2024-47561: org.apache.avro:avro:jar:1.11.3:compile

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `io.confluent:kafka-avro-serializer:7.6.0` to `7.7.1`
* Updated `org.apache.avro:avro:1.11.3` to `1.11.4`
* Updated `org.apache.kafka:kafka-clients:3.6.0` to `3.7.1`
* Removed `org.xerial.snappy:snappy-java:1.1.10.5`

#### Test Dependency Updates

* Updated `io.confluent:kafka-streams-avro-serde:7.6.0` to `7.7.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.6.0` to `7.7.1`
* Removed `joda-time:joda-time:2.12.7`
* Removed `org.apache.kafka:kafka-metadata:3.6.2`
* Removed `org.apache.zookeeper:zookeeper:3.9.2`
* Removed `org.bitbucket.b_c:jose4j:0.9.6`
* Removed `org.eclipse.jetty.http2:http2-server:9.4.54.v20240208`
* Added `org.eclipse.jetty:jetty-http:12.0.14`
* Added `org.eclipse.jetty:jetty-server:12.0.14`
* Removed `org.json:json:20240303`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.3.3` to `4.4.0`
* Added `com.exasol:quality-summarizer-maven-plugin:0.2.0`
* Updated `io.github.zlika:reproducible-build-maven-plugin:0.16` to `0.17`
* Updated `org.apache.maven.plugins:maven-clean-plugin:2.5` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.5` to `3.5.1`
* Updated `org.apache.maven.plugins:maven-install-plugin:2.4` to `3.1.3`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.4.1` to `3.4.2`
* Updated `org.apache.maven.plugins:maven-resources-plugin:2.6` to `3.3.1`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.3` to `3.9.1`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.5` to `3.5.1`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.2` to `2.17.1`
