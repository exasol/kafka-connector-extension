# Exasol Kafka Connector Extension 2.0.0, released 2026-07-29

Code name: Fixed vulnerabilities CVE-2026-10532, CVE-2026-13006, CVE-2025-12383, CVE-2026-54512, CVE-2026-54513, CVE-2026-54514, CVE-2026-54515, CVE-2026-54518, CVE-2026-59888, CVE-2026-59889, CVE-2026-9563, CVE-2026-59901, CVE-2026-10050, CVE-2026-59949

## Summary

This release fixes the following 14 vulnerabilities:

### CVE-2026-10050 (CWE-173) in dependency `org.eclipse.jetty:jetty-security:jar:9.4.57.v20241219:test`
org.eclipse.jetty:jetty-security - Improper Handling of Alternate Encoding
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-10050?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-security&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-10050
* https://github.com/advisories/GHSA-2fvj-hgj9-j2gr

### CVE-2026-59949 (CWE-125) in dependency `at.yawk.lz4:lz4-java:jar:1.10.1:runtime`
at.yawk.lz4:lz4-java - Out-of-bounds Read
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59949?component-type=maven&component-name=at.yawk.lz4%2Flz4-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59949
* https://github.com/advisories/GHSA-xx22-p4ch-683r

### CVE-2026-59901 (CWE-835) in dependency `io.netty:netty-codec-compression:jar:4.2.15.Final:test`
netty-codec - Bzip2Decoder infinite loop DoS via malformed stream
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59901?component-type=maven&component-name=io.netty%2Fnetty-codec-compression&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59901
* https://github.com/netty/netty/security/advisories/GHSA-558v-64gr-wgg4

### CVE-2026-10532 (CWE-502) in dependency `ch.qos.logback:logback-core:jar:1.5.34:compile`
Deserialization of untrusted data vulnerability in QOS.CH Sarl logback logback-core (HardenedObjectInputStream (logback-core) modules) allows Object Injection, albeit heavily restricted.

More precisely, an attacker able to influence serialized data sent to
SimpleSocketServer or SimpleSSLSocketServer can instantiate Proxy objects.

Although deserialization is heavily restricted by HardenedObjectInputStream and no
practical way to achieve remote code execution or significant privilege
escalation has been identified, this issue constitutes a bypass of the
intended security restrictions.

This issue affects logback: through 1.5.33 inclusive.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-10532?component-type=maven&component-name=ch.qos.logback%2Flogback-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-10532
* https://github.com/advisories/GHSA-jhq6-gfmj-v8fx
* https://logback.qos.ch/news.html#1.5.34

### CVE-2026-13006 (CWE-20) in dependency `ch.qos.logback:logback-core:jar:1.5.34:compile`
ACE vulnerability in conditional configuration file processing  by QOS.CH logback-core up to and including version 1.5.36 in Java applications, allows an attacker to execute arbitrary code circumventing existing protections against CVE-2025-11226 byÂ compromising an existing logback configuration file or by injecting an environment variable before program execution.

A successful attack requires the presence of Janino library to be present on the user's class path. In addition, the attacker mustÂ  have write access to a
configuration file. Alternatively, the attacker could inject a malicious
environment variable pointing to a malicious configuration file. In both
cases, the attack requires existing privilege.

Please note that in logack version 1.5.37 conditional processing using Janino was removed.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-13006?component-type=maven&component-name=ch.qos.logback%2Flogback-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-13006
* https://logback.qos.ch/news.html#1.5.35

### CVE-2025-12383 (CWE-362) in dependency `org.glassfish.jersey.core:jersey-client:jar:2.36:test`
In Eclipse Jersey versions 2.45, 3.0.16, 3.1.9 a race condition can cause ignoring of critical SSL configurations - such as mutual authentication, custom key/trust stores, and other security settings. This issue may result in SSLHandshakeException under normal circumstances, but under certain conditions, it could lead to unauthorized trust in insecure servers (see PoC)
#### References
* https://guide.sonatype.com/vulnerability/CVE-2025-12383?component-type=maven&component-name=org.glassfish.jersey.core%2Fjersey-client&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-12383
* https://github.com/advisories/GHSA-7p63-w6x9-6gr7

### CVE-2026-54512 (CWE-184) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.10.0 until 2.18.8, 2.21.4, and 3.1.4, jackson-databind's PolymorphicTypeValidator (PTV) is the primary safety mechanism guarding polymorphic deserialization. When polymorphic typing is enabled and a type identifier contains generic parameters (i.e. the type ID string contains <), DatabindContext._resolveAndValidateGeneric() validates only the raw container class name (the substring before <) against the configured PTV. If the container type is approved, the method parses the full canonical type string via TypeFactory.constructFromCanonical() and returns the fully parameterized type without ever validating the nested type arguments against the PTV. The nested type arguments are then resolved, instantiated, and populated as beans during deserialization. An attacker who controls the type ID can therefore place a denied class as a generic type parameter of an allowed container â for example java.util.ArrayList<com.evil.Gadget> when only java.util.ArrayList is allow-listed. The container passes the PTV check; com.evil.Gadget is loaded via Class.forName(name, true, loader), instantiated, and its properties are set from attacker-controlled JSON. This completely bypasses an explicitly configured PTV allow-list. This vulnerability is fixed in 2.18.8, 2.21.4, and 3.1.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54512?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54512
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-j3rv-43j4-c7qm

### CVE-2026-54513 (CWE-184) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.10.0 until 2.18.8, 2.21.4, and 3.1.4, BasicPolymorphicTypeValidator.Builder.allowIfSubTypeIsArray() allowlists any array type based only on clazz.isArray(), without validating the array's component (element) type against the configured allowlist. A PTV built with allowIfSubTypeIsArray() plus an explicit concrete-type allowlist therefore still permits EvilType[] even though EvilType is not allowlisted. When Jackson deserializes the elements and no per-element type IDs are present, it instantiates the component type directly with no further PTV check, bypassing the allowlist. This vulnerability is fixed in 2.18.8, 2.21.4, and 3.1.4.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54513 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54513?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54513
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-rmj7-2vxq-3g9f

### CVE-2026-54514 (CWE-918) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.0.0 until 2.18.8, 2.21.4, and 3.1.4, JDKFromStringDeserializer constructed InetSocketAddress with new InetSocketAddress(host, port), which performs eager DNS name resolution for hostname inputs at deserialization time. An application that binds untrusted JSON into a type containing an InetSocketAddress field issues an attacker-chosen DNS query during readValue, before any application-level validation or connect logic. The fix uses InetSocketAddress.createUnresolved(host, port), deferring DNS to an explicit connect. This vulnerability is fixed in 2.18.8, 2.21.4, and 3.1.4.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54514 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54514?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54514
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-hgj6-7826-r7m5

### CVE-2026-54515 (CWE-915) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.8.0 until 2.18.9, 2.21.5, and 3.1.4, in BeanDeserializerBase.createContextual(), per-property @JsonIgnoreProperties exclusions are applied by _handleByNameInclusion(), producing a contextual deserializer whose BeanPropertyMap has the ignored properties removed. The subsequent per-property case-insensitivity block (triggered by @JsonFormat(ACCEPT_CASE_INSENSITIVE_PROPERTIES)) rebuilds from this._beanProperties (the original, unfiltered map) instead of contextual._beanProperties, then overwrites the filtered map â restoring every property _handleByNameInclusion had just removed. The ignored property becomes writable again. This vulnerability is fixed in 2.18.9, 2.21.5, and 3.1.4.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54515?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54515
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-5jmj-h7xm-6q6v

### CVE-2026-54518 (CWE-863) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
jackson-databind contains the general-purpose data-binding functionality and tree-model for Jackson Data Processor. From 2.21.0 until 2.21.4 and 3.1.4, UnwrappedPropertyHandler.processUnwrappedCreatorProperties() replays buffered JSON into creator parameters but never consults prop.visibleInView(activeView). The normal property-based creator path gates creator properties on the active view, but this unwrapped-creator replay path bypasses that check, so a constructor parameter annotated with both @JsonView(AdminView.class) and @JsonUnwrapped is populated from attacker JSON even when a more restrictive view is active. This vulnerability is fixed in 2.21.4 and 3.1.4.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-54518 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-54518?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-54518
* https://github.com/FasterXML/jackson-databind/security/advisories/GHSA-rcqc-6cw3-h962

### CVE-2026-59888 (CWE-706) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
Jackson Databind -  JsonIgnore annotations Bypass
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59888?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59888
* https://github.com/FasterXML/jackson-databind/pull/5974

### CVE-2026-59889 (CWE-863) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
Jackson Databind -  Authorization bypass on JsonView Setter/Field
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59889?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59889
* https://github.com/FasterXML/jackson-databind/issues/6060
* https://github.com/FasterXML/jackson-databind/pull/6056

### CVE-2026-9563 (CWE-400) in dependency `org.eclipse.parsson:parsson:jar:1.1.7:test`
In Eclipse Parsson published Maven Central artifacts before version 1.1.8, the JSON parser did not enforce a default maximum on the number of characters consumed while parsing a single JSON document. Applications that parse attacker- controlled JSON can be forced to consume excessive CPU and memory by processing very large documents, including large arrays, objects, strings, numbers, whitespace, or nested structures, resulting in a denial of service. Eclipse Parsson 1.1.8 introduces a configurable maximum parsing limit with a default limit of 15 million parser-consumed characters.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-9563?component-type=maven&component-name=org.eclipse.parsson%2Fparsson&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-9563
* https://github.com/eclipse-ee4j/parsson/pull/169
* https://gitlab.eclipse.org/security/vulnerability-reports/-/work_items/444

## Remaining Known Vulnerabilities

The following findings are excluded from the OSS Index audit: HdrHistogram 2.2.2 is the latest published version, and `wire-runtime-jvm` is required by the embedded Schema Registry test dependency.

### CVE-2026-14683 (CWE-400) in dependency `org.hdrhistogram:HdrHistogram:jar:2.2.2:test`

Uncontrolled Resource Consumption ('Resource Exhaustion').

#### References

* https://guide.sonatype.com/vulnerability/CVE-2026-14683?component-type=maven&component-name=org.hdrhistogram%2FHdrHistogram&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1

### CVE-2026-14686 (CWE-697) in dependency `org.hdrhistogram:HdrHistogram:jar:2.2.2:test`

Incorrect Comparison.

#### References

* https://guide.sonatype.com/vulnerability/CVE-2026-14686?component-type=maven&component-name=org.hdrhistogram%2FHdrHistogram&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1

### CVE-2026-45799 (CWE-129) in dependency `com.squareup.wire:wire-runtime-jvm:jar:5.1.0:test`

Improper Validation of Array Index.

#### References

* https://guide.sonatype.com/vulnerability/CVE-2026-45799?component-type=maven&component-name=com.squareup.wire%2Fwire-runtime-jvm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1

## Security

* #204: Fixed vulnerability CVE-2026-10532 in dependency `ch.qos.logback:logback-core:jar:1.5.34:compile`
* #205: Fixed vulnerability CVE-2026-13006 in dependency `ch.qos.logback:logback-core:jar:1.5.34:compile`
* #206: Fixed vulnerability CVE-2025-12383 in dependency `org.glassfish.jersey.core:jersey-client:jar:2.36:test`
* #207: Fixed vulnerability CVE-2026-54512 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #208: Fixed vulnerability CVE-2026-54513 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #209: Fixed vulnerability CVE-2026-54514 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #210: Fixed vulnerability CVE-2026-54515 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #211: Fixed vulnerability CVE-2026-54518 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #212: Fixed vulnerability CVE-2026-59888 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #213: Fixed vulnerability CVE-2026-59889 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.18.1:compile`
* #214: Fixed vulnerability CVE-2026-9563 in dependency `org.eclipse.parsson:parsson:jar:1.1.7:test`
* #216: Fixed vulnerability CVE-2026-59901 in dependency `io.netty:netty-codec-compression:jar:4.2.15.Final:test`
* #218: Fixed vulnerability CVE-2026-10050 in dependency `org.eclipse.jetty:jetty-security:jar:9.4.57.v20241219:test`
* #219: Fixed vulnerability CVE-2026-59949 in dependency `at.yawk.lz4:lz4-java:jar:1.10.1:runtime`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.34` to `1.6.1`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.exasol:import-export-udf-common-scala:2.0.2` to `2.0.3`
* Updated `io.confluent:kafka-avro-serializer:7.9.2` to `8.3.0`
* Added `jakarta.xml.bind:jakarta.xml.bind-api:4.0.5`
* Updated `org.apache.kafka:kafka-clients:3.9.2` to `4.3.0`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `2.13.18`
* Updated `org.slf4j:slf4j-api:2.0.17` to `2.0.18`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.3.0` to `8.0.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.3`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.1`
* Updated `com.exasol:udf-debugging-java:0.6.18` to `0.6.20`
* Updated `com.google.code.gson:gson:2.13.1` to `2.14.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.5` to `8.3.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.2` to `8.3.0`
* Updated `nl.jqno.equalsverifier:equalsverifier:3.19.4` to `4.5`
* Updated `org.apache.kafka:kafka_2.13:3.9.2` to `4.3.0`
* Updated `org.apache.mina:mina-core:2.2.8` to `2.2.9`
* Updated `org.jacoco:org.jacoco.agent:0.8.14` to `0.8.15`
* Updated `org.mockito:mockito-junit-jupiter:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.4.4` to `1.0.1`
* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.7` to `2.1.0`
* Updated `com.exasol:project-keeper-maven-plugin:5.6.2` to `5.7.4`
* Removed `com.exasol:quality-summarizer-maven-plugin:0.2.1`
* Updated `org.apache.maven.plugins:maven-dependency-plugin:3.10.0` to `3.11.0`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.5` to `3.5.6`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.21.0` to `3.22.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.5` to `3.5.6`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.14` to `0.8.15`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.5.0.6356` to `5.7.0.6970`
* Added `org.spdx:spdx-maven-plugin:1.0.4`
