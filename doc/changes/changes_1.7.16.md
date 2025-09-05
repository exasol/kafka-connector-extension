# Kafka Connector Extension 1.7.16, released 2025-??-??

Code name: Fixed vulnerability CVE-2025-58057 in io.netty:netty-codec-compression:jar:4.2.4.Final:test

## Summary

This release fixes the following vulnerability:

### CVE-2025-58057 (CWE-409) in dependency `io.netty:netty-codec-compression:jar:4.2.4.Final:test`
netty-codec - Improper Handling of Highly Compressed Data (Data Amplification)
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2025-58057?component-type=maven&component-name=io.netty%2Fnetty-codec-compression&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-58057
* https://github.com/netty/netty/security/advisories/GHSA-3p8m-j85q-pgmj

## Security

* #151: Fixed vulnerability CVE-2025-58057 in dependency `io.netty:netty-codec-compression:jar:4.2.4.Final:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.0.0`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`

#### Test Dependency Updates

* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `3.6.3`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.0.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.0.0`
* Updated `org.mockito:mockito-core:5.18.0` to `5.19.0`
