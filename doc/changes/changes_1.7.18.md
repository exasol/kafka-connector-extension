# Exasol Kafka Connector Extension 1.7.18, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-59901 in io.netty:netty-codec-compression:jar:4.2.15.Final:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-59901 (CWE-835) in dependency `io.netty:netty-codec-compression:jar:4.2.15.Final:test`
netty-codec - Bzip2Decoder infinite loop DoS via malformed stream
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59901?component-type=maven&component-name=io.netty%2Fnetty-codec-compression&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59901
* https://github.com/netty/netty/security/advisories/GHSA-558v-64gr-wgg4

## Security

* #216: Fixed vulnerability CVE-2026-59901 in dependency `io.netty:netty-codec-compression:jar:4.2.15.Final:test`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.34` to `1.5.38`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `io.confluent:kafka-avro-serializer:7.9.2` to `8.3.0`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.4`
* Updated `org.slf4j:slf4j-api:2.0.17` to `2.0.18`

### Test Dependency Updates

* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.1`
* Updated `com.exasol:udf-debugging-java:0.6.18` to `0.6.20`
* Updated `com.google.code.gson:gson:2.13.1` to `2.14.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.5` to `8.3.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.2` to `8.3.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5`
* Updated `org.apache.kafka:kafka_2.13:3.9.2` to `8.3.0-ce`
* Updated `org.apache.mina:mina-core:2.2.8` to `2.2.9`
* Updated `org.mockito:mockito-junit-jupiter:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.4`
