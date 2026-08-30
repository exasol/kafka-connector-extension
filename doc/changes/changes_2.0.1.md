# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-75595, CVE-2026-62243, CVE-2026-71290

## Summary

This release fixes the following 3 vulnerabilities:

### CVE-2026-75595 (CWE-754) in dependency `io.netty:netty-handler:jar:4.2.16.Final:test`
Netty is an asynchronous, event-driven network application framework. Prior to 4.1.137.Fina and 4.2.17.Final, io.netty.handler.ssl.SslClientHelloHandler#decode checks the wrong offset before reading the four-byte TLS handshake header, so a ClientHello whose handshake header spans records can cause an IndexOutOfBoundsException and invoke select(ctx, null). This selects the default SslContext instead of the SNI-specific context. In deployments where per-SNI clientAuth=REQUIRE is the sole mutual TLS gate, the default SslContext uses clientAuth=NONE or clientAuth=OPTIONAL, and no application-layer certificate verification exists, an unauthenticated remote attacker can bypass the protected route's mutual TLS requirement. This issue is fixed in versions 4.1.137.Final and 4.2.17.Final.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-75595?component-type=maven&component-name=io.netty%2Fnetty-handler&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-75595
* https://github.com/netty/netty/pull/17213
* https://github.com/netty/netty/pull/17217
* https://github.com/netty/netty/security/advisories/GHSA-c4c3-7fpv-j4q5

### CVE-2026-62243 (CWE-297) in dependency `io.netty:netty-handler:jar:4.2.16.Final:test`
io.netty:netty-handler - Improper Validation of Certificate with Host Mismatch
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-62243?component-type=maven&component-name=io.netty%2Fnetty-handler&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-62243
* https://github.com/netty/netty/security/advisories/GHSA-p85m-gvr3-788c

### CVE-2026-71290 (CWE-295) in dependency `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:compile`
Improper TLS hostname verification vulnerability in Apache HttpComponents Client 5.4 or newer.Â HostnameVerificationPolicy#BUILTIN setting has no effect when used with the async version of HttpClient. An attacker that can intercept and modify traffic between the client and the server can impersonate the server by presenting a valid certificate for a different domain.Â 

Please note the classic version of HttpClient is not affected by this vulnerability.Â 

Affected users are recommended to upgrade to at least version 5.6.4, which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-71290?component-type=maven&component-name=org.apache.httpcomponents.client5%2Fhttpclient5&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-71290
* https://lists.apache.org/thread/bhf7g2zwpom2ohvwjjjlonc93br2s8vq
* https://github.com/advisories/GHSA-72q8-9rgw-5g6j

## Security

* #228: Fixed vulnerability CVE-2026-75595 in dependency `io.netty:netty-handler:jar:4.2.16.Final:test`
* #229: Fixed vulnerability CVE-2026-62243 in dependency `io.netty:netty-handler:jar:4.2.16.Final:test`
* #230: Fixed vulnerability CVE-2026-71290 in dependency `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.3`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.1`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.1-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.9.0`

### Test Dependency Updates

* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.2`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.1-ce`
