# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-45799, CVE-2026-42577, CVE-2026-24281, CVE-2026-24308, CVE-2026-41409, CVE-2026-41635, CVE-2026-42779, CVE-2025-33042

## Summary

This release fixes the following 8 vulnerabilities:

### CVE-2026-45799 (CWE-129) in dependency `com.squareup.wire:wire-runtime-jvm:jar:5.1.0:test`
Wire's protobuf - Improper Validation of Array Index

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-45799 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-45799?component-type=maven&component-name=com.squareup.wire%2Fwire-runtime-jvm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-45799
* https://github.com/square/wire/security/advisories/GHSA-7xpr-hc2w-34m9

### CVE-2026-42577 (CWE-772) in dependency `io.netty:netty-transport-classes-epoll:jar:4.2.7.Final:test`
Netty is an asynchronous, event-driven network application framework. From 4.2.0.Final to 4.2.13.Final , Netty's epoll transport fails to detect and close TCP connections that receive a RST after being half-closed, leading to stale channels that are never cleaned up and, in some code paths, a 100% CPU busy-loop in the event loop thread. This vulnerability is fixed in 4.2.13.Final.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-42577 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-42577?component-type=maven&component-name=io.netty%2Fnetty-transport-classes-epoll&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-42577
* https://github.com/advisories/GHSA-rwm7-x88c-3g2p

### CVE-2026-24281 (CWE-295) in dependency `org.apache.zookeeper:zookeeper:jar:3.8.4:test`
Hostname verification in Apache ZooKeeper ZKTrustManager falls back to reverse DNS (PTR) when IP SAN validation fails, allowing attackers who control or spoof PTR records to impersonate ZooKeeper servers or clients with a valid certificate for the PTR name. It's important to note that attacker must present a certificate which is trusted by ZKTrustManager which makes the attack vector harder to exploit. Users are recommended to upgrade to version 3.8.6 or 3.9.5, which fixes this issue by introducing a new configuration option to disable reverse DNS lookup in client and quorum protocols.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-24281 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-24281?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-24281
* https://issues.apache.org/jira/browse/ZOOKEEPER-4986
* https://github.com/advisories/GHSA-7xrh-hqfc-g7qr

### CVE-2026-24308 (CWE-532) in dependency `org.apache.zookeeper:zookeeper:jar:3.8.4:test`
Improper handling of configuration values in ZKConfig in Apache ZooKeeper 3.8.5 and 3.9.4 on all platforms allows an attacker to expose sensitive information stored in client configuration in the client's logfile. Configuration values are exposed at INFO level logging rendering potential production systems affected by the issue.Â Users are recommended to upgrade to version 3.8.6 or 3.9.5 which fixes this issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-24308?component-type=maven&component-name=org.apache.zookeeper%2Fzookeeper&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-24308
* https://github.com/advisories/GHSA-crhr-qqj8-rpxc

### CVE-2026-41409 (CWE-502) in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
The fix for CVE-2024-52046 in Apache MINA AbstractIoBuffer.getObject() was incomplete. The classname allowlist of classes allowed to be deserialized was applied too late after a static initializer in a class to be read might already have been executed.

Affected versions are Apache MINA 2.0.0 <= 2.0.27, 2.1.0 <= 2.1.10, and 2.2.0 <= 2.2.5.

The problem is resolved in Apache MINA 2.0.28, 2.1.11, and 2.2.6 by 
applying the classname allowlist earlier.

Affected are applications using Apache MINA that call IoBuffer.getObject().

Applications using Apache MINA are advised to upgrade
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-41409?component-type=maven&component-name=org.apache.mina%2Fmina-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-41409
* https://github.com/advisories/GHSA-f2wh-grmh-r6jm
* https://lists.apache.org/thread/9ddvsq6c4l5bhwq8l14sob4f8qjvx5c9

### CVE-2026-41635 (CWE-502) in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
Apache MINA's AbstractIoBuffer.resolveClass() contains two branches, one of them (for static classes or primitive types) does not check the class at all, bypassing the classname allowlist and allowing arbitrary code to be executed.

The fix checks if the class is present in the accepted class filterÂ before callingÂ Class.forName().Â 

Affected versions are Apache MINA 2.0.0 <= 2.0.27, 2.1.0 <= 2.1.10, and

2.2.0 <= 2.2.5.

The problem is resolved in Apache MINA 2.0.28, 2.1.11, and 2.2.6 by 
applying the classname allowlist earlier.

Affected are applications using Apache MINA that callÂ  IoBuffer.getObject().

Applications using Apache MINA are advised to upgrade.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-41635?component-type=maven&component-name=org.apache.mina%2Fmina-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-41635
* https://lists.apache.org/thread/1l91w1mqsb3lwfd504fs045ylxntt2tm
* https://lists.apache.org/thread/fhlx5k91hrkgyzh7yk1nghrn3k27gxy0
* http://www.openwall.com/lists/oss-security/2026/04/27/4
* https://github.com/advisories/GHSA-8297-v2rf-2p32

### CVE-2026-42779 (CWE-502) in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
The fix for CVE-2026-41635 was not applied to the 2.1.X and 2.2.X branches. Here was the original issue description:

Apache MINA's AbstractIoBuffer.resolveClass() contains two branches, one of them (for static classes or primitive types) does not check the class at all, bypassing the classname allowlist and allowing arbitrary code to be executed.

The fix checks if the class is present in the accepted class filter before calling Class.forName(). 

Affected versions are Apache MINA 2.1.0 <= 2.1.11, and 2.2.0 <= 2.2.6.

The problem is resolved in Apache MINA 2.1.12, and 2.2.7 by 
applying the classname allowlist earlier.

Affected are applications using Apache MINA that call  IoBuffer.getObject().

Applications using Apache MINA are advised to upgrade.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-42779?component-type=maven&component-name=org.apache.mina%2Fmina-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-42779
* https://github.com/advisories/GHSA-vf5j-865m-mq7c

### CVE-2025-33042 (CWE-94) in dependency `org.apache.avro:avro:jar:1.12.0:compile`
Improper Control of Generation of Code ('Code Injection') vulnerability in Apache Avro Java SDK when generating specific records from untrusted Avro schemas.

This issue affects Apache Avro Java SDK: all versions through 1.11.4 and versionÂ 1.12.0.

Users are recommended to upgrade to version 1.12.1 or 1.11.5, which fix the issue.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2025-33042?component-type=maven&component-name=org.apache.avro%2Favro&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-33042
* https://github.com/advisories/GHSA-rp46-r563-jrc7

## Security

* #182: Fixed vulnerability CVE-2026-45799 in dependency `com.squareup.wire:wire-runtime-jvm:jar:5.1.0:test`
* #183: Fixed vulnerability CVE-2026-42577 in dependency `io.netty:netty-transport-classes-epoll:jar:4.2.7.Final:test`
* #184: Fixed vulnerability CVE-2026-24281 in dependency `org.apache.zookeeper:zookeeper:jar:3.8.4:test`
* #185: Fixed vulnerability CVE-2026-24308 in dependency `org.apache.zookeeper:zookeeper:jar:3.8.4:test`
* #186: Fixed vulnerability CVE-2026-41409 in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
* #187: Fixed vulnerability CVE-2026-41635 in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
* #188: Fixed vulnerability CVE-2026-42779 in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
* #189: Fixed vulnerability CVE-2025-33042 in dependency `org.apache.avro:avro:jar:1.12.0:compile`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.34`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.google.code.gson:gson:2.13.1` to `2.14.0`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.2.1`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.4`
* Updated `org.slf4j:slf4j-api:2.0.17` to `2.0.18`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.3.0`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.19`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.2.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.2.0`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.8`
* Updated `org.mockito:mockito-core:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.6.2`
