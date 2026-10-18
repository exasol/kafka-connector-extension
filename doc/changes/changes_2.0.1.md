# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-110262, CVE-2026-110270, CVE-2026-76184, CVE-2026-77711, CVE-2026-18660, CVE-2026-76182, CVE-2026-18661, CVE-2026-93879, CVE-2026-102554, CVE-2026-18659, CVE-2026-18662, CVE-2026-110257, CVE-2026-110258, CVE-2026-110259, CVE-2026-110260, CVE-2026-110261, CVE-2026-110263, CVE-2026-19864

## Summary

This release fixes the following 18 vulnerabilities:

### CVE-2026-110262 (CWE-770) in dependency `org.ow2.asm:asm-commons:jar:9.10.1:test`
OW2 ASM 3.1 before 9.11 contains an excessive memory allocation vulnerability in the asm-commons AdviceAdapter that allows attackers to exhaust memory by supplying crafted class files. Attackers can craft bytecode with many forward jump targets in constructors, causing unbounded stack copies in forwardJumpStackFrames and allocation of hundreds of megabytes, leading to denial of service.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110262?component-type=maven&component-name=org.ow2.asm%2Fasm-commons&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110262
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110262.json

### CVE-2026-110270 (CWE-407) in dependency `org.ow2.asm:asm-commons:jar:9.10.1:test`
OW2 ASM 3.0 before 9.11 contains an uncontrolled memory allocation vulnerability in the asm-commons JSRInlinerAdapter that inlines JSR/RET subroutines without memory or work limits. Attackers can supply a crafted class file with nested subroutines to force O(2^n) memory allocation, causing an OutOfMemoryError that crashes the JVM.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110270?component-type=maven&component-name=org.ow2.asm%2Fasm-commons&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110270
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110270.json

### CVE-2026-76184 (CWE-200) in dependency `org.eclipse.jetty:jetty-client:jar:12.0.37:test`
In Eclipse Jetty, HttpClient may receive a redirect of the form:

```

/secure/../evil

```

If an attacker is able to control the redirects, then HttpClient matches authentication credentials against `/secure` rather than normalizing the path to `/evil`, and will therefore follow the redirect sending the `Authorization` header for `/secure` to `/evil`.

This also mean that the attacker must have an evil application deployed on the same domain as the target.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-76184?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-client&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-76184
* https://github.com/jetty/jetty.project/security/advisories/GHSA-qfr2-43g5-g768

### CVE-2026-77711 (CWE-200) in dependency `org.eclipse.jetty:jetty-client:jar:12.0.37:test`
When Jetty's `HttpClient` make a request to a server through a forward proxy, the server may reply with a 407 (Proxy Authentication Required).

The client mistakenly interprets the 407 as coming from the proxy, so it re-sends the request with the `Proxy-Authentication` header, which arrives to the server, that now has access to the proxy credentials.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-77711?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-client&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-77711
* https://github.com/jetty/jetty.project/security/advisories/GHSA-r2c5-3p8g-wpvw

### CVE-2026-18660 (CWE-178) in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
In Eclipse Jetty, across various versions of `jetty-server`, and `jetty-proxy`, the host whitelist/blacklist is case-sensitive, and would allow hosts through that only differ by case.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-18660?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-server&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-18660
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/18xxx/CVE-2026-18660.json

### CVE-2026-76182 (CWE-74) in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
In Eclipse Jetty, `TryPathsHandler` and `TryFilesFilter` can be configured to forward requests to template URIs such as `index?p=$path`, where `$path` is the request path in context.

However, URI paths may contain characters that are reserved in URI queries so that a path of `/s&a=1` when applied to the template becomes `index?p=/s&a=1`, which is interpreted as 2 query parameters: `p=/s` and `a=1`, rather than just one query parameter `p` whose value is `/s&a=1`.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-76182?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-server&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-76182
* https://github.com/jetty/jetty.project/security/advisories/GHSA-4hxv-32vm-5h2m
* https://github.com/advisories/GHSA-x7vq-gfp7-hvw6

### CVE-2026-18661 (CWE-863) in dependency `org.eclipse.jetty.ee10:jetty-ee10-annotations:jar:12.0.35:test`
In Eclipse Jetty, across various versions of `jetty-annotations`, and `jetty-eeN-annotations`, the Jakarta Spec authoritative url-pattern rules for mixed `@ServletSecurity` annotation, and `web.xml` defined `security-constraint` on the same `url-pattern` were not being applied if the `web.xml` had more than 1 `url-pattern` for a servlet, and had a `security-constraint` applying to only some of those `url-pattern` definitions.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-18661?component-type=maven&component-name=org.eclipse.jetty.ee10%2Fjetty-ee10-annotations&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-18661
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/18xxx/CVE-2026-18661.json

### CVE-2026-93879 (CWE-1284) in dependency `org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:test`
Hibernate Validator - EL injection causes denial of service

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-93879 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-93879?component-type=maven&component-name=org.hibernate.validator%2Fhibernate-validator&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-93879
* https://access.redhat.com/security/cve/cve-2026-93879

### CVE-2026-102554 (CWE-502) in dependency `com.google.guava:guava:jar:32.0.1-jre:compile`
Allocation of resources without limits or throttling (CWE-770) during Java object deserialization in Google Guava versions 4.0 through 33.7.1 allows an attacker to cause a Denial of Service via OutOfMemoryError. When deserializing CompactHashMap, CompactHashSet, or MapMakerInternalMap instances, Guava eagerly allocates an array based on a caller-specified size parameter without throttling, permitting memory exhaustion from crafted serialization streams.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-102554?component-type=maven&component-name=com.google.guava%2Fguava&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-102554
* https://github.com/google/guava/security/advisories/GHSA-xxph-c9ww-hj94

### CVE-2026-18659 (CWE-863) in dependency `org.eclipse.jetty.ee10:jetty-ee10-servlet:jar:12.0.35:test`
In Eclipse Jetty, across various versions of `jetty-security`, and `jetty-eeN-security`, and `jetty-eeN-servlet`, the Jakarta Spec constraints defined as `http-method-omission` can be bypassed by using substrings of the defined HTTP Methods.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-18659?component-type=maven&component-name=org.eclipse.jetty.ee10%2Fjetty-ee10-servlet&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-18659
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/18xxx/CVE-2026-18659.json

### CVE-2026-18662 (CWE-863) in dependency `org.eclipse.jetty.ee10:jetty-ee10-servlet:jar:12.0.35:test`
In Eclipse Jetty, across various versions of `jetty-security`, and `jetty-eeN-security`, two or more security constraints with the same http-method-omission set, and no url-patterns, are not combined properly per Jakarta Servlet Spec.

Resulting in later http-method-omission sets overriding previous constraints with the same http-method-omission set.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-18662?component-type=maven&component-name=org.eclipse.jetty.ee10%2Fjetty-ee10-servlet&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-18662
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/18xxx/CVE-2026-18662.json

### CVE-2026-110257 (CWE-789) in dependency `org.ow2.asm:asm:jar:9.10.1:test`
OW2 ASM 5.0 before 9.11 contains an excessive memory allocation vulnerability in ClassReader.readBootstrapMethodsAttribute() that sizes an int array from num_bootstrap_methods without validating attribute_length. Attackers can supply a crafted class file of about 100 bytes that forces roughly 2 GB allocation, causing an OutOfMemoryError that crashes the JVM or consuming thread.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110257?component-type=maven&component-name=org.ow2.asm%2Fasm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110257
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110257.json

### CVE-2026-110258 (CWE-789) in dependency `org.ow2.asm:asm:jar:9.10.1:test`
OW2 ASM 3.0 before 9.11 contains an excessive memory allocation vulnerability in Label.addSubroutineRetSuccessors() that fails to enforce the single-RET-per-JSR rule. Attackers can supply a crafted invalid class file with multiple RET instructions for one JSR, triggering a roughly 2 GB allocation that crashes the JVM.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110258?component-type=maven&component-name=org.ow2.asm%2Fasm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110258
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110258.json

### CVE-2026-110259 (CWE-674) in dependency `org.ow2.asm:asm:jar:9.10.1:test`
OW2 ASM 3.0 before 9.11 contains an uncontrolled recursion vulnerability in ClassWriter.replaceAsmInstructions() that allows attackers to cause denial of service through crafted class files. Attackers can supply a class with a large method containing many long forward jumps, triggering unbounded toByteArray() recursion and a StackOverflowError during transformation.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110259?component-type=maven&component-name=org.ow2.asm%2Fasm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110259
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110259.json

### CVE-2026-110260 (CWE-407) in dependency `org.ow2.asm:asm:jar:9.10.1:test`
OW2 ASM 3.0 before 9.11 contains a quadratic memory allocation vulnerability in MethodWriter.computeMaxStackAndLocal() and computeAllFrames() when using COMPUTE_MAXS or COMPUTE_FRAMES. Attackers can supply a crafted class file with thousands of overlapping exception handlers, causing excessive Edge allocations and an OutOfMemoryError that crashes the JVM.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110260?component-type=maven&component-name=org.ow2.asm%2Fasm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110260
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110260.json

### CVE-2026-110261 (CWE-407) in dependency `org.ow2.asm:asm:jar:9.10.1:test`
OW2 ASM 3.0 before 9.11 contains a denial of service vulnerability that allows attackers to exhaust CPU by supplying crafted class files processed with ClassWriter.COMPUTE_FRAMES. Attackers can craft methods that make the unbounded MethodWriter.computeAllFrames() fix point algorithm converge slowly, tying up transforming threads for unbounded time.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110261?component-type=maven&component-name=org.ow2.asm%2Fasm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110261
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110261.json

### CVE-2026-110263 (CWE-674) in dependency `org.ow2.asm:asm:jar:9.10.1:test`
OW2 ASM 3.0 before 9.11 contains an uncontrolled recursion vulnerability in SignatureReader.parseType() that allows attackers to exhaust the stack via deeply nested generic type signatures. Attackers can supply a crafted class file with a long Signature attribute containing many nested type arguments to trigger a StackOverflowError during class transformation, causing denial of service.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-110263?component-type=maven&component-name=org.ow2.asm%2Fasm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-110263
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/110xxx/CVE-2026-110263.json

### CVE-2026-19864 (CWE-312) in dependency `org.eclipse.jetty:jetty-security:jar:12.0.37:test`
Eclipse Jetty's `SessionAuthentication` retains authentication credentials in a serializable object. When the credentials contain a plaintext password and the authenticated session is persisted to a session data store, the password may consequently be written to persistent storage in plaintext.

An attacker with sufficient privileges to read the persisted session data may obtain the plaintext passwords of authenticated users.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-19864?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-security&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-19864
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/19xxx/CVE-2026-19864.json

## Security

* #266: Fixed vulnerability CVE-2026-110262 in dependency `org.ow2.asm:asm-commons:jar:9.10.1:test`
* #267: Fixed vulnerability CVE-2026-110270 in dependency `org.ow2.asm:asm-commons:jar:9.10.1:test`
* #268: Fixed vulnerability CVE-2026-76184 in dependency `org.eclipse.jetty:jetty-client:jar:12.0.37:test`
* #269: Fixed vulnerability CVE-2026-77711 in dependency `org.eclipse.jetty:jetty-client:jar:12.0.37:test`
* #270: Fixed vulnerability CVE-2026-18660 in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #271: Fixed vulnerability CVE-2026-76182 in dependency `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #272: Fixed vulnerability CVE-2026-18661 in dependency `org.eclipse.jetty.ee10:jetty-ee10-annotations:jar:12.0.35:test`
* #273: Fixed vulnerability CVE-2026-93879 in dependency `org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:test`
* #274: Fixed vulnerability CVE-2026-102554 in dependency `com.google.guava:guava:jar:32.0.1-jre:compile`
* #275: Fixed vulnerability CVE-2026-18659 in dependency `org.eclipse.jetty.ee10:jetty-ee10-servlet:jar:12.0.35:test`
* #276: Fixed vulnerability CVE-2026-18662 in dependency `org.eclipse.jetty.ee10:jetty-ee10-servlet:jar:12.0.35:test`
* #277: Fixed vulnerability CVE-2026-110257 in dependency `org.ow2.asm:asm:jar:9.10.1:test`
* #278: Fixed vulnerability CVE-2026-110258 in dependency `org.ow2.asm:asm:jar:9.10.1:test`
* #279: Fixed vulnerability CVE-2026-110259 in dependency `org.ow2.asm:asm:jar:9.10.1:test`
* #280: Fixed vulnerability CVE-2026-110260 in dependency `org.ow2.asm:asm:jar:9.10.1:test`
* #281: Fixed vulnerability CVE-2026-110261 in dependency `org.ow2.asm:asm:jar:9.10.1:test`
* #282: Fixed vulnerability CVE-2026-110263 in dependency `org.ow2.asm:asm:jar:9.10.1:test`
* #283: Fixed vulnerability CVE-2026-19864 in dependency `org.eclipse.jetty:jetty-security:jar:12.0.37:test`

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
* Updated `com.exasol:udf-debugging-java:0.6.20` to `0.6.21`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.2`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.commons:commons-lang3:3.20.0` to `3.21.0`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.2-ce`
* Updated `org.apache.mina:mina-core:2.2.9` to `2.2.10`
* Updated `org.apache.zookeeper:zookeeper:3.9.5` to `3.9.6`
* Updated `org.mockito:mockito-junit-jupiter:5.23.0` to `5.24.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.7.7`
