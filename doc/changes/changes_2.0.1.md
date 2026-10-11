# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-108104, CVE-2026-108106, CVE-2026-104721, CVE-2026-106449, CVE-2026-106450, CVE-2026-106451, CVE-2026-106452, CVE-2026-106453

## Summary

This release fixes the following 8 vulnerabilities:

### CVE-2026-108104 (CWE-415) in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
Xerial snappy-java from 1.1.7.4 before 1.1.10.10 contains a double release vulnerability in SnappyFramedInputStream that returns pooled buffers twice when replacement allocation fails. Attackers can supply framed data with a large declared chunk length to trigger OutOfMemoryError, causing shared backing arrays that expose or overwrite other streams' decompressed data.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-108104?component-type=maven&component-name=org.xerial.snappy%2Fsnappy-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-108104
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/108xxx/CVE-2026-108104.json

### CVE-2026-108106 (CWE-789) in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
Xerial snappy-java before 1.1.10.9 contains an unbounded memory allocation vulnerability that allows attackers to exhaust JVM memory by declaring a large uncompressed length in compressed input. Attackers can supply a few crafted bytes to Snappy.uncompress, uncompressString, SnappyInputStream or SnappyFramedInputStream to force allocations up to 2 GB, causing OutOfMemoryError and denial of service.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-108106?component-type=maven&component-name=org.xerial.snappy%2Fsnappy-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-108106
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/108xxx/CVE-2026-108106.json

### CVE-2026-104721 (CWE-22) in dependency `ch.qos.logback:logback-classic:jar:1.6.1:compile`
Path-traversal vulnerability in QOS.CH Sarl Logback-classic on Java (logback-classic module) allows path-traversal vulnerability. More specifically, an 
MDC-based discriminator value flows unsanitized into a nested 
FileAppender path, letting an attacker who influences that MDC value 
(e.g. via an HTTP header)
 create and append log files outside the intended directory. 

This issue affects Logback-classic: from 0.9.14 through 1.6.4.Â  This vulnerability is similar to CVE-2026-19880 but involves other attack techniques.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-104721?component-type=maven&component-name=ch.qos.logback%2Flogback-classic&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-104721
* https://logback.qos.ch/news.html#1.6.5

### CVE-2026-106449 (CWE-674) in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
yawkat LZ4 Java provides LZ4 compression for Java. Prior to 1.11.4, net.jpountz.lz4.LZ4BlockInputStream configured with stopOnEmptyBlock set to false handles each well-formed empty LZ4Block by recursively calling refill(), allowing a long sequence of empty blocks in an attacker-controlled compressed stream to exhaust the decoding thread's stack and throw StackOverflowError. The default stopOnEmptyBlock setting is true and is not affected, and the issue does not cause memory corruption. This issue is fixed in version 1.11.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-106449?component-type=maven&component-name=at.yawk.lz4%2Flz4-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-106449
* https://github.com/yawkat/lz4-java/releases/tag/v1.11.4
* https://github.com/yawkat/lz4-java/security/advisories/GHSA-343h-94h5-c4wr

### CVE-2026-106450 (CWE-770) in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
yawkat LZ4 Java provides LZ4 compression for Java. Prior to 1.11.4, net.jpountz.lz4.LZ4FrameInputStream readHeader() allocates two new 4 MiB block buffers whenever a maximum-block-size frame header is read, and the default concatenated-frame mode allows attacker-controlled streams containing many minimal empty frames to trigger roughly 8 MiB of allocation for every 11 input bytes. The stream produces no decompressed output while consuming CPU and garbage-collection time, so decompressed-size limits do not mitigate the issue; readSingleFrame mode is not affected. This issue is fixed in version 1.11.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-106450?component-type=maven&component-name=at.yawk.lz4%2Flz4-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-106450
* https://github.com/advisories/GHSA-gm45-99xc-r7wv

### CVE-2026-106451 (CWE-367) in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
yawkat LZ4 Java provides LZ4 compression for Java. From 1.7.0 until 1.11.4, net.jpountz.util.Native.load() uses File.createTempFile to create an exclusive temporary .lck file but derives the native-library path by removing the suffix, then FileOutputStream opens that predictable path without exclusive creation, allowing another local user with access to the same shared temporary directory to create or replace the library file before System.load() uses it. Successful exploitation depends on shared-directory permissions, host protections, and winning the race, and can execute native code as the victim; hardened systems may instead cause library loading to fail and fall back to Java implementations. Configurations using a system library, a private java.io.tmpdir, or Java-only implementations are not affected. This issue is fixed in version 1.11.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-106451?component-type=maven&component-name=at.yawk.lz4%2Flz4-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-106451
* https://github.com/yawkat/lz4-java/releases/tag/v1.11.4
* https://github.com/yawkat/lz4-java/security/advisories/GHSA-mcr4-qmvw-px4g

### CVE-2026-106452 (CWE-789) in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
yawkat LZ4 Java provides LZ4 compression for Java. Prior to 1.11.2, net.jpountz.lz4.LZ4BlockInputStream refill() validates that the compressedLen field in a legacy LZ4Block header is nonnegative but allocates a compressed-input buffer of that attacker-controlled size before reading payload data, allowing a header-only stream to request a near-2 GiB allocation and exhaust the JVM heap. Canonical writers emit raw blocks when compression is not smaller than the original block, but vulnerable readers accept non-canonical oversized compressed blocks. This issue is fixed in version 1.11.2.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-106452?component-type=maven&component-name=at.yawk.lz4%2Flz4-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-106452
* https://github.com/yawkat/lz4-java/security/advisories/GHSA-4v53-57pg-c464

### CVE-2026-106453 (CWE-789) in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
yawkat LZ4 Java provides LZ4 compression for Java. Prior to 1.11.2, LZ4DecompressorWithLength uses getDecompressedLength to trust the four-byte decompressed-length header before validating the compressed input, allowing a five-byte attacker-supplied input whose header declares a large output size to request up to approximately 2 GiB and exhaust the JVM heap. Convenience overloads backed by LZ4FastDecompressor or LZ4SafeDecompressor allocate the untrusted size, while overloads that write to a caller-provided destination buffer are not affected because the caller controls the destination size. This issue is fixed in version 1.11.2.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-106453?component-type=maven&component-name=at.yawk.lz4%2Flz4-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-106453
* https://github.com/advisories/GHSA-6cx8-rjf8-pr8g

## Security

* #257: Fixed vulnerability CVE-2026-108104 in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #258: Fixed vulnerability CVE-2026-108106 in dependency `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #259: Fixed vulnerability CVE-2026-104721 in dependency `ch.qos.logback:logback-classic:jar:1.6.1:compile`
* #260: Fixed vulnerability CVE-2026-106449 in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #261: Fixed vulnerability CVE-2026-106450 in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #262: Fixed vulnerability CVE-2026-106451 in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #263: Fixed vulnerability CVE-2026-106452 in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #264: Fixed vulnerability CVE-2026-106453 in dependency `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.5`
* Updated `com.exasol:import-export-udf-common-scala:2.0.3` to `2.0.4`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.2`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.2-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.10.0`
* Updated `org.slf4j:slf4j-api:2.0.18` to `2.0.20`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:8.0.1` to `8.0.2`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.3` to `1.7.4`
* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.3`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.2`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.commons:commons-lang3:3.20.0` to `3.21.0`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.2-ce`
* Updated `org.apache.zookeeper:zookeeper:3.9.5` to `3.9.6`
* Updated `org.mockito:mockito-junit-jupiter:5.23.0` to `5.24.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.6`
