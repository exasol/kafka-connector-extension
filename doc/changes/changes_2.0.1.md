# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-75596, CVE-2026-19880

## Summary

This release fixes the following 2 vulnerabilities:

### CVE-2026-75596 (CWE-407) in dependency `io.netty:netty-handler:jar:4.2.16.Final:test`
io.netty:netty-handler - Inefficient Algorithmic Complexity
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-75596?component-type=maven&component-name=io.netty%2Fnetty-handler&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-75596
* https://github.com/netty/netty/security/advisories/GHSA-fccg-mwvh-qqg4

### CVE-2026-19880 (CWE-22) in dependency `ch.qos.logback:logback-classic:jar:1.6.1:compile`
Path-traversal vulnerability in QOS.CH Sarl Logback-classic on Java (logback-classic module) allows path-traversal vulnerability. More specifically, an 
MDC-based discriminator value flows unsanitized into a nested 
FileAppender path, letting an attacker who influences that MDC value 
(e.g. via an HTTP header)
 create and append log files outside the intended directory. 

This issue affects Logback-classic: from 0.9.14 through 1.6.2.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-19880?component-type=maven&component-name=ch.qos.logback%2Flogback-classic&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-19880
* https://logback.qos.ch/news.html#1.6.3

## Security

* #225: Fixed vulnerability CVE-2026-75596 in dependency `io.netty:netty-handler:jar:4.2.16.Final:test`
* #226: Fixed vulnerability CVE-2026-19880 in dependency `ch.qos.logback:logback-classic:jar:1.6.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.3`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.1`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.1-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.8.4`

### Test Dependency Updates

* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.2`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.1`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.1-ce`
