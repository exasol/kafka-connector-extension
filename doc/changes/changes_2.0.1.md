# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-86231, CVE-2026-12611, CVE-2026-19204, CVE-2026-87795, CVE-2026-87823, CVE-2026-89045, CVE-2026-19203

## Summary

This release fixes the following 7 vulnerabilities:

### CVE-2026-86231 (CWE-298) in dependency `com.github.mwiede:jsch:jar:2.28.5:test`
A security flaw has been discovered in mwiede jsch up to 2.28.5. Affected is the function getRevokedKeys of the file src/main/java/com/jcraft/jsch/KnownHosts.java. Performing a manipulation of the argument known_hosts results in improper check for certificate revocation. The attack is possible to be carried out remotely. The attack is considered to have high complexity. The exploitability is told to be difficult. The exploit has been released to the public and may be used for attacks. Upgrading to version 2.28.6 is able to address this issue. The patch is named 194a2f76a5c0f1c3f778565be3fd66bcafc42d23. You should upgrade the affected component.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-86231?component-type=maven&component-name=com.github.mwiede%2Fjsch&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-86231
* https://github.com/mwiede/jsch/issues/1091
* https://github.com/mwiede/jsch/pull/1098
* https://github.com/mwiede/jsch/releases/tag/jsch-2.28.6

### CVE-2026-12611 (CWE-400) in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
A client may issue HTTP/2 requests to a Jetty server that result in blocking writes that are never unblocked, eventually causing all threads to be blocked and the whole server to become unresponsive.

This is caused by a race condition in the server when handling RST_STREAM frames and GOAWAY frames sent by the client.

The race condition "resets" the HTTP2Flusher.terminated, previously set to a non-null value, to the null value, allowing entries to be enqueued in the flusher that however will never be processed. These unprocessed entries are the ones that would unblock the write-blocked threads.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-12611?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-server&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-12611
* https://github.com/jetty/jetty.project/security/advisories/GHSA-gpg9-4257-cfm3

### CVE-2026-19204 (CWE-770) in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
A client may send a WebSocket frame with an unknown opcode and a very large declared payload length, causing Jetty to attempt a large memory allocation and potentially exhaust the JVM heap.

This occurs when auto-fragmentation is enabled, as unknown opcodes bypass the normal maximum frame size handling and payload allocation occurs before the opcode is validated.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-19204?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-server&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-19204
* https://github.com/hapifhir/hapi-fhir/issues/8359

### CVE-2026-87795 (CWE-125) in dependency `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
zstd-jni versions before 1.5.7-14 fail to validate offset and length parameters in the ZstdDictCompress constructor, allowing out-of-bounds memory reads. Attackers can supply untrusted offset or length values to read native heap memory into the compression dictionary, typically causing JVM crashes.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-87795?component-type=maven&component-name=com.github.luben%2Fzstd-jni&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-87795
* https://github.com/luben/zstd-jni/security/advisories/GHSA-ff36-7w3w-g8rm

### CVE-2026-87823 (CWE-190) in dependency `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
zstd-jni before 1.5.7-14 performs 32-bit signed bounds checks on three direct-ByteBuffer frame-size native methods, allowing out-of-bounds memory reads via negative or overflowing offsets. Attackers can supply negative offset values near Integer.MIN_VALUE to read unmapped memory, causing JVM termination or extracting arbitrary frame size data from unintended memory locations.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-87823?component-type=maven&component-name=com.github.luben%2Fzstd-jni&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-87823
* https://github.com/luben/zstd-jni/security/advisories/GHSA-jfr6-9xqw-2g2q
* https://www.vulncheck.com/advisories/zstd-jni-1.1.1-through-1.5.7-13-out-of-bounds-read-via-direct-bytebuffer-frame-size-methods

### CVE-2026-89045 (CWE-835) in dependency `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
zstd-jni versions 1.4.8-4 through 1.5.7-13 fail to validate negative length parameters in ZstdInputStreamNoFinalizer.read(), allowing attackers to trigger infinite loops. Attackers can pass negative length values to cause the read method to spin indefinitely while holding the stream monitor, blocking all other threads from accessing the stream.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-89045?component-type=maven&component-name=com.github.luben%2Fzstd-jni&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-89045
* https://github.com/luben/zstd-jni/releases/tag/v1.5.7-14
* https://github.com/luben/zstd-jni/security/advisories/GHSA-9jx2-gfp9-phfm

### CVE-2026-19203 (CWE-444) in dependency `org.eclipse.jetty:jetty-http:jar:12.0.37:test`
A client may issue specially crafted HTTP/1.1 chunked requests to a Jetty server that cause Jetty and an intermediary proxy to interpret different request boundaries, potentially resulting in HTTP request smuggling.

This is caused by Jetty accepting a lone LF character as a terminator in parts of chunked request parsing. Depending on the Jetty version and configured HTTP compliance mode, this may occur in chunk extensions, chunk data termination, or trailer termination.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-19203?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-http&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-19203
* https://github.com/advisories/GHSA-p2j5-5566-vpv9
* https://github.com/jetty/jetty.project/releases/tag/jetty-12.0.38
* https://github.com/jetty/jetty.project/releases/tag/jetty-12.1.12

## Security

* #232: Fixed vulnerability CVE-2026-86231 in dependency `com.github.mwiede:jsch:jar:2.28.5:test`
* #233: Fixed vulnerability CVE-2026-12611 in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #234: Fixed vulnerability CVE-2026-19204 in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #235: Fixed vulnerability CVE-2026-87795 in dependency `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
* #236: Fixed vulnerability CVE-2026-87823 in dependency `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
* #237: Fixed vulnerability CVE-2026-89045 in dependency `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
* #238: Fixed vulnerability CVE-2026-19203 in dependency `org.eclipse.jetty:jetty-http:jar:12.0.37:test`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.3`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.1`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.1-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.9.0`
* Updated `org.slf4j:slf4j-api:2.0.18` to `2.0.19`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:8.0.1` to `8.0.2`
* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.2`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.1-ce`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.5`
