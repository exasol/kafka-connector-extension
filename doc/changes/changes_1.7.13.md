# Kafka Connector Extension 1.7.13, released 2025-07-01

Code name: Fixed vulnerabilities

## Summary

This release removes broken links from the readme.md file (start page).
This release fixes the following vulnerabilities:


###  CVE-2025-1948 (CWE: CWE-400) in dependency 
`org.eclipse.jetty.http2:http2-common:jar:9.4.57.v20241219:test`
In Eclipse Jetty versions 12.0.0 to 12.0.16 included, an HTTP/2 client can specify a very large value for the HTTP/2 settings parameter SETTINGS_MAX_HEADER_LIST_SIZE.
The Jetty HTTP/2 server does not perform validation on this setting, and tries to allocate a ByteBuffer of the specified capacity to encode HTTP responses, likely resulting in OutOfMemoryError being thrown, or even the JVM process exiting.

#### References
https://ossindex.sonatype.org/vulnerability/CVE-2025-1948?component-type=maven&component-name=org.eclipse.jetty.http2%2Fhttp2-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-1948GHSA-889j-63jv-qhr8

###  CVE-2025-27817 (CWE: CWE-918) in dependency `org.apache.kafka:kafka-clients:jar:3.9.0:compile`
A possible arbitrary file read and SSRF vulnerability has been identified in Apache Kafka Client. Apache Kafka Clients accept configuration data for setting the SASL/OAUTHBEARER connection with the brokers, including "sasl.oauthbearer.token.endpoint.url" and "sasl.oauthbearer.jwks.endpoint.url". Apache Kafka allows clients to read an arbitrary file and return the content in the error log, or sending requests to an unintended location. In applications where Apache Kafka Clients configurations can be specified by an untrusted party, attackers may use the "sasl.oauthbearer.token.endpoint.url" and "sasl.oauthbearer.jwks.endpoint.url" configuratin to read arbitrary contents of the disk and environment variables or make requests to an unintended location. In particular, this flaw may be used in Apache Kafka Connect to escalate from REST API access to filesystem/environment/URL access, which may be undesirable in certain environments, including SaaS products.

Since Apache Kafka 3.9.1/4.0.0, we have added a system property ("-Dorg.apache.kafka.sasl.oauthbearer.allowed.urls") to set the allowed urls in SASL JAAS configuration. In 3.9.1, it accepts all urls by default for backward compatibility. However in 4.0.0 and newer, the default value is empty list and users have to set the allowed urls explicitly.

#### References
https://ossindex.sonatype.org/vulnerability/CVE-2025-27817?component-type=maven&component-name=org.apache.kafka%2Fkafka-clients&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-27817
https://access.redhat.com/security/cve/cve-2025-27817
https://www.openwall.com/lists/oss-security/2025/06/09/1

### CVE-2025-27818 (CWE: CWE-502) in dependency `org.apache.kafka:kafka-clients:jar:3.9.0:compile`
A possible security vulnerability has been identified in Apache Kafka.
This requires access to a alterConfig to theÂ cluster resource, or Kafka Connect worker, and the ability to create/modify connectors on it with an arbitrary Kafka client SASL JAAS config
and a SASL-based security protocol, which has been possible on Kafka clusters since Apache Kafka 2.0.0 (Kafka Connect 2.3.0).
When configuring the broker via config file or AlterConfig command, or connector via the Kafka Kafka Connect REST API, an authenticated operatorÂ can set the sasl.jaas.config
property for any of the connector's Kafka clients to "com.sun.security.auth.module.LdapLoginModule", which can be done via the
producer.override.sasl.jaas.config, consumer.override.sasl.jaas.config, or admin.override.sasl.jaas.config properties.
This will allow the server to connect to the attacker's LDAP server
and deserialize the LDAP response, which the attacker can use to execute java deserialization gadget chains on the Kafka connect server.
Attacker can cause unrestricted deserialization of untrusted data (or) RCE vulnerability when there are gadgets in the classpath.

Since Apache Kafka 3.0.0, users are allowed to specify these properties in connector configurations for Kafka Connect clusters running with out-of-the-box
configurations. Before Apache Kafka 3.0.0, users may not specify these properties unless the Kafka Connect cluster has been reconfigured with a connector
client override policy that permits them.

Since Apache Kafka 3.9.1/4.0.0, we have added a system property ("-Dorg.apache.kafka.disallowed.login.modules") to disable the problematic login modules usage
in SASL JAAS configuration. Also by default "com.sun.security.auth.module.JndiLoginModule,com.sun.security.auth.module.LdapLoginModule" are disabled in Apache Kafka Connect 3.9.1/4.0.0.

We advise the Kafka users to validate connector configurations and only allow trusted LDAP configurations. Also examine connector dependencies for
vulnerable versions and either upgrade their connectors, upgrading that specific dependency, or removing the connectors as options for remediation. Finally,
in addition to leveraging the "org.apache.kafka.disallowed.login.modules" system property, Kafka Connect users can also implement their own connector
client config override policy, which can be used to control which Kafka client properties can be overridden directly in a connector config and which cannot.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2025-27818 for details

#### References
https://ossindex.sonatype.org/vulnerability/CVE-2025-27818?component-type=maven&component-name=org.apache.kafka%2Fkafka-clients&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-27818
GHSA-76qp-h5mr-frr4

### CVE-2025-48734 (CWE-284) in dependency `commons-beanutils:commons-beanutils:jar:1.9.4:provided`
commons-beanutils - Improper Access Control
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2025-48734?component-type=maven&component-name=commons-beanutils%2Fcommons-beanutils&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-48734
* https://github.com/advisories/GHSA-wxr5-93ph-8wr9
* https://nvd.nist.gov/vuln/detail/CVE-2025-48734


## Features

* #140: Remove broken links from start page

## Security

* #139: Fixed vulnerability CVE-2025-48734 in dependency `commons-beanutils:commons-beanutils:jar:1.9.4:provided`
* #141: Fixed vulnerability CVE-2025-27817 in dependency `org.apache.kafka:kafka-clients:jar:3.9.0:compile`
* #142: Fixed vulnerability CVE-2025-27818 in dependency `org.apache.kafka:kafka-clients:jar:3.9.0:compile`
* #138: Fixed vulnerability CVE-2025-1948 in dependency `org.eclipse.jetty.http2:http2-common:jar:9.4.57.v20241219:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `org.apache.kafka:kafka-clients:3.9.0` to `3.9.1`
* Added `org.eclipse.jetty.http2:http2-common:9.4.57.v20241219`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.0.0` to `5.2.2`
* Added `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.1`
* Removed `io.github.zlika:reproducible-build-maven-plugin:0.17`
* Added `org.apache.maven.plugins:maven-artifact-plugin:3.6.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.2` to `3.5.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.2` to `3.5.3`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.12` to `0.8.13`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.0.0.4389` to `5.1.0.4751`
