<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                                  | License                                                                       |
| ------------------------------------------- | ----------------------------------------------------------------------------- |
| [Scala Library][0]                          | [Apache-2.0][1]                                                               |
| [Import Export UDF Common Scala][2]         | [MIT License][3]                                                              |
| [Apache Avro][4]                            | [Apache-2.0][5]                                                               |
| [Jackson-core][6]                           | [The Apache Software License, Version 2.0][5]                                 |
| [error-reporting-java][7]                   | [MIT License][8]                                                              |
| [Apache Commons Compress][9]                | [Apache-2.0][5]                                                               |
| [kafka-avro-serializer][10]                 | [Apache License 2.0][11]                                                      |
| [scala-collection-compat][12]               | [Apache-2.0][1]                                                               |
| [Guava: Google Core Libraries for Java][13] | [Apache License, Version 2.0][14]                                             |
| [SLF4J API Module][15]                      | [MIT License][16]                                                             |
| [Logback Classic Module][17]                | [Eclipse Public License - v 1.0][18]; [GNU Lesser General Public License][19] |
| [Apache Kafka][20]                          | [The Apache License, Version 2.0][14]                                         |

### Test Dependencies

| Dependency                                  | License                                                                                |
| ------------------------------------------- | -------------------------------------------------------------------------------------- |
| [scalatest][21]                             | [the Apache License, ASL Version 2.0][22]                                              |
| [scalatestplus-mockito][23]                 | [Apache-2.0][22]                                                                       |
| [mockito-core][24]                          | [MIT][25]                                                                              |
| [Test containers for Exasol on Docker][26]  | [MIT License][27]                                                                      |
| [Test Database Builder for Java][28]        | [MIT License][29]                                                                      |
| [Matcher for SQL Result Sets][30]           | [MIT License][31]                                                                      |
| [Extension integration tests library][32]   | [MIT License][33]                                                                      |
| [embedded-kafka-schema-registry][34]        | [MIT][25]                                                                              |
| [Core :: HTTP][35]                          | [Eclipse Public License - Version 2.0][36]; [Apache Software License - Version 2.0][1] |
| [Core :: Server][37]                        | [Eclipse Public License - Version 2.0][36]; [Apache Software License - Version 2.0][1] |
| [Netty/Codec][38]                           | [Apache License, Version 2.0][1]                                                       |
| [Hibernate Validator Engine][39]            | [Apache License 2.0][14]                                                               |
| [Jetty :: Utility Servlets and Filters][40] | [Eclipse Public License - Version 2.0][36]; [Apache Software License - Version 2.0][1] |
| [kafka-streams-avro-serde][41]              | [Apache License 2.0][11]                                                               |
| [avro4s-core][42]                           | [MIT][25]                                                                              |
| [Testcontainers :: Kafka][43]               | [MIT][44]                                                                              |
| [ClassGraph][45]                            | [The MIT License (MIT)][44]                                                            |
| [Protocol Buffers [Core]][46]               | [BSD-3-Clause][47]                                                                     |
| [Maven Project Version Getter][48]          | [MIT License][49]                                                                      |

### Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [Apache Maven Clean Plugin][50]                         | [Apache-2.0][5]                               |
| [Apache Maven Install Plugin][51]                       | [Apache-2.0][5]                               |
| [Apache Maven Resources Plugin][52]                     | [Apache-2.0][5]                               |
| [Apache Maven Site Plugin][53]                          | [Apache License, Version 2.0][5]              |
| [SonarQube Scanner for Maven][54]                       | [GNU LGPL 3][55]                              |
| [Apache Maven Toolchains Plugin][56]                    | [Apache-2.0][5]                               |
| [Apache Maven Compiler Plugin][57]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][58]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][59]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][60] | [ASL2][14]                                    |
| [scala-maven-plugin][61]                                | [Public domain (Unlicense)][62]               |
| [ScalaTest Maven Plugin][63]                            | [the Apache License, ASL Version 2.0][22]     |
| [Apache Maven Javadoc Plugin][64]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][65]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][66]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][67]          | [Apache License 2.0][11]                      |
| [Apache Maven Assembly Plugin][68]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][69]                           | [Apache-2.0][5]                               |
| [Project Keeper Maven plugin][70]                       | [The MIT License][71]                         |
| [OpenFastTrace Maven Plugin][72]                        | [GNU General Public License v3.0][73]         |
| [Scalastyle Maven Plugin][74]                           | [Apache 2.0][11]                              |
| [spotless-maven-plugin][75]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][76]                             | [BSD-3-Clause][47]                            |
| [Exec Maven Plugin][77]                                 | [Apache License 2][5]                         |
| [Artifact reference checker and unifier][78]            | [MIT License][79]                             |
| [Maven Failsafe Plugin][80]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][81]                            | [EPL-2.0][36]                                 |
| [Quality Summarizer Maven Plugin][82]                   | [MIT License][83]                             |
| [error-code-crawler-maven-plugin][84]                   | [MIT License][85]                             |
| [Reproducible Build Maven Plugin][86]                   | [Apache 2.0][14]                              |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][87] | MIT     |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://avro.apache.org
[5]: https://www.apache.org/licenses/LICENSE-2.0.txt
[6]: https://github.com/FasterXML/jackson-core
[7]: https://github.com/exasol/error-reporting-java/
[8]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[9]: https://commons.apache.org/proper/commons-compress/
[10]: http://confluent.io/kafka-avro-serializer
[11]: http://www.apache.org/licenses/LICENSE-2.0.html
[12]: http://www.scala-lang.org/
[13]: https://github.com/google/guava
[14]: http://www.apache.org/licenses/LICENSE-2.0.txt
[15]: http://www.slf4j.org
[16]: http://www.opensource.org/licenses/mit-license.php
[17]: http://logback.qos.ch/logback-classic
[18]: http://www.eclipse.org/legal/epl-v10.html
[19]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[20]: https://kafka.apache.org
[21]: http://www.scalatest.org
[22]: http://www.apache.org/licenses/LICENSE-2.0
[23]: https://github.com/scalatest/scalatestplus-mockito
[24]: https://github.com/mockito/mockito
[25]: https://opensource.org/licenses/MIT
[26]: https://github.com/exasol/exasol-testcontainers/
[27]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[28]: https://github.com/exasol/test-db-builder-java/
[29]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[30]: https://github.com/exasol/hamcrest-resultset-matcher/
[31]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[32]: https://github.com/exasol/extension-manager/
[33]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[34]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[35]: https://jetty.org/jetty-core/jetty-http
[36]: https://www.eclipse.org/legal/epl-2.0/
[37]: https://jetty.org/jetty-core/jetty-server
[38]: https://netty.io/netty-codec/
[39]: http://hibernate.org/validator/hibernate-validator
[40]: https://jetty.org/jetty-servlets
[41]: http://confluent.io/kafka-streams-avro-serde
[42]: https://github.com/sksamuel/avro4s
[43]: https://java.testcontainers.org
[44]: http://opensource.org/licenses/MIT
[45]: https://github.com/classgraph/classgraph
[46]: https://developers.google.com/protocol-buffers/protobuf-java/
[47]: https://opensource.org/licenses/BSD-3-Clause
[48]: https://github.com/exasol/maven-project-version-getter/
[49]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[50]: https://maven.apache.org/plugins/maven-clean-plugin/
[51]: https://maven.apache.org/plugins/maven-install-plugin/
[52]: https://maven.apache.org/plugins/maven-resources-plugin/
[53]: https://maven.apache.org/plugins/maven-site-plugin/
[54]: http://sonarsource.github.io/sonar-scanner-maven/
[55]: http://www.gnu.org/licenses/lgpl.txt
[56]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[57]: https://maven.apache.org/plugins/maven-compiler-plugin/
[58]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[59]: https://www.mojohaus.org/flatten-maven-plugin/
[60]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[61]: http://github.com/davidB/scala-maven-plugin
[62]: http://unlicense.org/
[63]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[64]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[65]: https://maven.apache.org/surefire/maven-surefire-plugin/
[66]: https://www.mojohaus.org/versions/versions-maven-plugin/
[67]: https://basepom.github.io/duplicate-finder-maven-plugin
[68]: https://maven.apache.org/plugins/maven-assembly-plugin/
[69]: https://maven.apache.org/plugins/maven-jar-plugin/
[70]: https://github.com/exasol/project-keeper/
[71]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[72]: https://github.com/itsallcode/openfasttrace-maven-plugin
[73]: https://www.gnu.org/licenses/gpl-3.0.html
[74]: http://www.scalastyle.org
[75]: https://github.com/diffplug/spotless
[76]: https://github.com/evis/scalafix-maven-plugin
[77]: https://www.mojohaus.org/exec-maven-plugin
[78]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[79]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[80]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[81]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[82]: https://github.com/exasol/quality-summarizer-maven-plugin/
[83]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[84]: https://github.com/exasol/error-code-crawler-maven-plugin/
[85]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[86]: http://zlika.github.io/reproducible-build-maven-plugin
[87]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.3.tgz
