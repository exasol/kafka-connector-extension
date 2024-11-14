# Kafka Connector Extension 1.7.9, released 2024-??-??

Code name: Fixed vulnerability CVE-2024-47535 in io.netty:netty-common:jar:4.1.108.Final:test

## Summary

This release fixes the following vulnerability:

### CVE-2024-47535 (CWE-400) in dependency `io.netty:netty-common:jar:4.1.108.Final:test`
Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients. An unsafe reading of environment file could potentially cause a denial of service in Netty. When loaded on an Windows application, Netty attempts to load a file that does not exist. If an attacker creates such a large file, the Netty application crashes. This vulnerability is fixed in 4.1.115.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-47535?component-type=maven&component-name=io.netty%2Fnetty-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-47535
* https://github.com/advisories/GHSA-xq3w-v528-46rv

## Security

* #118: Fixed vulnerability CVE-2024-47535 in dependency `io.netty:netty-common:jar:4.1.108.Final:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.6` to `1.5.12`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.18.1`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.1-jre`
* Updated `org.apache.avro:avro:1.11.4` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `com.google.protobuf:protobuf-java:3.25.5` to `4.28.3`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.179`
* Updated `org.eclipse.jetty:jetty-http:9.4.56.v20240826` to `12.0.15`
* Updated `org.eclipse.jetty:jetty-server:9.4.56.v20240826` to `12.0.15`
* Updated `org.eclipse.jetty:jetty-servlets:9.4.56.v20240826` to `11.0.24`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.2`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.3`
