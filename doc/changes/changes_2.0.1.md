# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-59739, CVE-2026-59969, CVE-2026-79993, CVE-2026-84439, CVE-2026-84501, CVE-2026-91776, CVE-2026-91777, CVE-2026-83557, CVE-2026-90559, CVE-2026-68498

## Summary

This release fixes the following 10 vulnerabilities:

### CVE-2026-59739 (CWE-862) in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
Information disclosure via SetWatches reconnect replay in Apache ZooKeeper due to missing ACL check.Â An attacker can discover ACL-restricted paths by registering exists-watches on non-existent paths, then reconnecting after the paths are created with restricted ACLs. Issue is caused by incomplete fix forÂ CVE-2024-23944 (ZOOKEEPER-4799).Â The fix added ACL checking to WatchManager.triggerWatch(). However, DataTree.setWatches() â the SetWatches/SetWatches2 reconnect replay handler â still calls watcher.process(event) with null ACL, bypassing the check entirely. It's important to note that only the path is exposed by this vulnerability, not the data of znode, but since znode path can contain sensitive information like user name or login ID, this issue is potentially critical.

Users are recommended to upgrade to version 3.9.6, 3.8.7 which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59739?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59739
* https://lists.apache.org/thread/v0lrwsbdxvx6w67p3z7fxf3hzyncq55l

### CVE-2026-59969 (CWE-297) in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
Apache ZooKeeper quorum TLS fails to enforce peer hostname verification in FIPS-mode deployments. When sslQuorum=true, zookeeper.fips-mode=true, ssl.quorum.hostnameVerification=true, and ssl.quorum.clientHostnameVerification=trueÂ are enabled, the Java SSLSocketÂ quorum path accepts a CA-trusted peer certificate whose SAN does not match the connected host. A malicious or misissued peer certificate can therefore join quorum traffic, participate in leader election, and enter replication flows.

Users are recommended to upgrade to version 3.8.7 or 3.9.6, which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59969?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59969
* https://lists.apache.org/thread/qf9kwsgtgbgtvwls6f41281846xg0rss

### CVE-2026-79993 (CWE-862) in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
The `deleteContainer` opcode (0x14/20) is processed without verifying the caller's ACL permissions, allowing any authenticated client to delete specific znodes in the data tree regardless of the ACL restrictions on the znode or its parent. This opcode is considered internal-only and the official client doesn't have API for it, but aÂ client that can open a plain TCP session on the ZooKeeper client port (2181 by default)Â - with NO authentication and NO ACL permissions - can delete anyÂ empty persistent znode (including regular persistent nodes, containerÂ nodes, and TTL nodes) by issuing the raw protocol OpCode deleteContainer (20).Â The deleteContainer request path completely skips both the session check andÂ the DELETE ACL check that are enforced by the regular delete (OpCode 2) path.Â This is an authorization bypass / ACL enforcement bug.

This issue affects Apache ZooKeeper: from 3.9.0 through 3.9.5, from 3.8.0 through 3.8.6.

Users are recommended to upgrade to version 3.9.6 or 3.8.7, which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-79993?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-79993
* https://lists.apache.org/thread/9cw3rmrzhqpct26v8j18fy41bghlq8y5
* https://www.openwall.com/lists/oss-security/2026/09/15/5

### CVE-2026-84439 (CWE-117) in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
When audit logging is enabled (zookeeper.audit.enable=true), an unauthenticated attacker can inject arbitrary fields into Apache ZooKeeper's audit log by sending a digest authentication request with tab characters (\t) embedded in the username. Because the audit log uses tab-separatedÂ key=valueÂ format, the injected tabs are parsed as legitimate field separators, allowing the attacker to spoof audit results (e.g., injectingÂ result=success), forge operation types, and corrupt forensic evidence.

A log injection vulnerability in Apache ZooKeeper allows a client that can callÂ setACLÂ to inject forged key-value fields intoÂ zookeeper_audit.log. When audit logging is enabled, the server serializes attacker-controlled digest ACL ids into theÂ acl=Â audit field without escaping tab characters. Because audit events are emitted as tab-separatedÂ key=valueÂ records, a crafted ACL id can make one successfulÂ setAclÂ event appear to contain forged fields such asÂ operation=deleteÂ andÂ znode=/forged. This undermines the integrity of downstream audit parsing, alerting, and incident response.

This issue affects Apache ZooKeeper: from 3.9.0 through 3.9.5, from 3.8.0 through 3.8.6.

Users are recommended to upgrade to version 3.9.6 or 3.8.7, which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-84439?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-84439
* https://lists.apache.org/thread/b7qnjvqjh393l0j07tmnb5ggg40sx3m6
* http://www.openwall.com/lists/oss-security/2026/09/15/6

### CVE-2026-84501 (CWE-117) in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
An unauthenticated attacker can inject arbitrary fake log lines into Apache ZooKeeper's operational log by sending a crafted add_auth("ensemble", ...) request containing newline characters (\n). When the ensemble name doesn't match, EnsembleAuthenticationProvider.handleAuthentication() logs the raw, unsanitized name via LOG.warn(). Because SLF4J's {} placeholder preserves embedded newlines, the attacker can forge complete log entries â with arbitrary timestamps, log levels, class names, and messages â that are visually indistinguishable from genuine ZooKeeper log output.

This issue affects Apache ZooKeeper: from 3.9.0 through 3.9.5, from 3.8.0 through 3.8.6.

Users are recommended to upgrade to version 3.8.7 or 3.9.6, which fixes the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-84501?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-84501
* https://lists.apache.org/thread/ohby1g6zjt72p0w79rhbr41nczzvr33y
* http://www.openwall.com/lists/oss-security/2026/09/15/7

### CVE-2026-91776 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
Jackson-databind - Unbounded growth of type id cache in TypeDeserializer
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-91776?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-91776
* https://github.com/FasterXML/jackson-databind/pull/6203

### CVE-2026-91777 (CWE-407) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
Jackson-databind - Denial of Service (DoS)
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-91777?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-91777
* https://github.com/FasterXML/jackson-databind/pull/6204

### CVE-2026-83557 (CWE-502) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
jackson-databind - Deserialization of untrusted data [CVE-2026-83557]
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-83557?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-83557
* https://github.com/FasterXML/jackson-databind/issues/6156
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-gx83-3vf8-gh7j

### CVE-2026-90559 (CWE-787) in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
snappy-java through 1.1.10.8 contains an out-of-bounds write vulnerability in Snappy.uncompress(ByteBuffer, ByteBuffer) because destination buffer capacity is never validated against decompressed size. Attackers can supply valid compressed data that decompresses larger than the destination buffer, causing writes past buffer boundaries and JVM termination.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-90559?component-type=maven&component-name=org.xerial.snappy%2Fsnappy-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-90559
* https://github.com/xerial/snappy-java/issues/728

### CVE-2026-68498 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
jackson-core - Allocation of Resources Without Limits or Throttling

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-68498 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-68498?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-68498
* https://github.com/FasterXML/jackson-core/security/advisories/GHSA-649p-m576-vr99

## Security

* #240: Fixed vulnerability CVE-2026-59739 in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #241: Fixed vulnerability CVE-2026-59969 in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #242: Fixed vulnerability CVE-2026-79993 in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #243: Fixed vulnerability CVE-2026-84439 in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #244: Fixed vulnerability CVE-2026-84501 in dependency `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #245: Fixed vulnerability CVE-2026-91776 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #246: Fixed vulnerability CVE-2026-91777 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #247: Fixed vulnerability CVE-2026-83557 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #248: Fixed vulnerability CVE-2026-90559 in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #249: Fixed vulnerability CVE-2026-68498 in dependency `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.3`
* Updated `com.exasol:import-export-udf-common-scala:2.0.3` to `2.0.4`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.2`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.2-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.9.0`
* Updated `org.slf4j:slf4j-api:2.0.18` to `2.0.19`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:8.0.1` to `8.0.2`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.3` to `1.7.4`
* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.3`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.2`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.2-ce`
* Updated `org.apache.zookeeper:zookeeper:3.9.5` to `3.9.6`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.6`
