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

| Dependency                                  | License                                                                                 |
| ------------------------------------------- | --------------------------------------------------------------------------------------- |
| [scalatest][21]                             | [the Apache License, ASL Version 2.0][22]                                               |
| [scalatestplus-mockito][23]                 | [Apache-2.0][22]                                                                        |
| [mockito-core][24]                          | [MIT][25]                                                                               |
| [Test containers for Exasol on Docker][26]  | [MIT License][27]                                                                       |
| [Test Database Builder for Java][28]        | [MIT License][29]                                                                       |
| [Matcher for SQL Result Sets][30]           | [MIT License][31]                                                                       |
| [Extension integration tests library][32]   | [MIT License][33]                                                                       |
| [embedded-kafka-schema-registry][34]        | [MIT][25]                                                                               |
| [Jetty :: Http Utility][35]                 | [Apache Software License - Version 2.0][22]; [Eclipse Public License - Version 1.0][36] |
| [Jetty :: Server Core][37]                  | [Apache Software License - Version 2.0][22]; [Eclipse Public License - Version 1.0][36] |
| [Jetty :: Utility Servlets and Filters][38] | [Apache Software License - Version 2.0][22]; [Eclipse Public License - Version 1.0][36] |
| [kafka-streams-avro-serde][39]              | [Apache License 2.0][11]                                                                |
| [avro4s-core][40]                           | [MIT][25]                                                                               |
| [Testcontainers :: Kafka][41]               | [MIT][42]                                                                               |
| [ClassGraph][43]                            | [The MIT License (MIT)][42]                                                             |
| [Protocol Buffers [Core]][44]               | [BSD-3-Clause][45]                                                                      |

### Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [Apache Maven Clean Plugin][46]                         | [Apache-2.0][5]                               |
| [Apache Maven Install Plugin][47]                       | [Apache-2.0][5]                               |
| [Apache Maven Resources Plugin][48]                     | [Apache-2.0][5]                               |
| [Apache Maven Site Plugin][49]                          | [Apache License, Version 2.0][5]              |
| [SonarQube Scanner for Maven][50]                       | [GNU LGPL 3][51]                              |
| [Apache Maven Toolchains Plugin][52]                    | [Apache-2.0][5]                               |
| [Apache Maven Compiler Plugin][53]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][54]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][55]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][56] | [ASL2][14]                                    |
| [scala-maven-plugin][57]                                | [Public domain (Unlicense)][58]               |
| [ScalaTest Maven Plugin][59]                            | [the Apache License, ASL Version 2.0][22]     |
| [Apache Maven Javadoc Plugin][60]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][61]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][62]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][63]          | [Apache License 2.0][11]                      |
| [Apache Maven Assembly Plugin][64]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][65]                           | [Apache-2.0][5]                               |
| [Artifact reference checker and unifier][66]            | [MIT License][67]                             |
| [Maven Failsafe Plugin][68]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][69]                            | [EPL-2.0][70]                                 |
| [Quality Summarizer Maven Plugin][71]                   | [MIT License][72]                             |
| [error-code-crawler-maven-plugin][73]                   | [MIT License][74]                             |
| [Reproducible Build Maven Plugin][75]                   | [Apache 2.0][14]                              |
| [Project Keeper Maven plugin][76]                       | [The MIT License][77]                         |
| [OpenFastTrace Maven Plugin][78]                        | [GNU General Public License v3.0][79]         |
| [Scalastyle Maven Plugin][80]                           | [Apache 2.0][11]                              |
| [spotless-maven-plugin][81]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][82]                             | [BSD-3-Clause][45]                            |
| [Exec Maven Plugin][83]                                 | [Apache License 2][5]                         |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][84] | MIT     |

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
[35]: https://jetty.org/jetty-http/
[36]: https://www.eclipse.org/org/documents/epl-v10.php
[37]: https://jetty.org/jetty-server/
[38]: https://jetty.org/jetty-servlets/
[39]: http://confluent.io/kafka-streams-avro-serde
[40]: https://github.com/sksamuel/avro4s
[41]: https://java.testcontainers.org
[42]: http://opensource.org/licenses/MIT
[43]: https://github.com/classgraph/classgraph
[44]: https://developers.google.com/protocol-buffers/protobuf-java/
[45]: https://opensource.org/licenses/BSD-3-Clause
[46]: https://maven.apache.org/plugins/maven-clean-plugin/
[47]: https://maven.apache.org/plugins/maven-install-plugin/
[48]: https://maven.apache.org/plugins/maven-resources-plugin/
[49]: https://maven.apache.org/plugins/maven-site-plugin/
[50]: http://sonarsource.github.io/sonar-scanner-maven/
[51]: http://www.gnu.org/licenses/lgpl.txt
[52]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[53]: https://maven.apache.org/plugins/maven-compiler-plugin/
[54]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[55]: https://www.mojohaus.org/flatten-maven-plugin/
[56]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[57]: http://github.com/davidB/scala-maven-plugin
[58]: http://unlicense.org/
[59]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[60]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[61]: https://maven.apache.org/surefire/maven-surefire-plugin/
[62]: https://www.mojohaus.org/versions/versions-maven-plugin/
[63]: https://basepom.github.io/duplicate-finder-maven-plugin
[64]: https://maven.apache.org/plugins/maven-assembly-plugin/
[65]: https://maven.apache.org/plugins/maven-jar-plugin/
[66]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[67]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[68]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[69]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[70]: https://www.eclipse.org/legal/epl-2.0/
[71]: https://github.com/exasol/quality-summarizer-maven-plugin/
[72]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[73]: https://github.com/exasol/error-code-crawler-maven-plugin/
[74]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[75]: http://zlika.github.io/reproducible-build-maven-plugin
[76]: https://github.com/exasol/project-keeper/
[77]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[78]: https://github.com/itsallcode/openfasttrace-maven-plugin
[79]: https://www.gnu.org/licenses/gpl-3.0.html
[80]: http://www.scalastyle.org
[81]: https://github.com/diffplug/spotless
[82]: https://github.com/evis/scalafix-maven-plugin
[83]: https://www.mojohaus.org/exec-maven-plugin
[84]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.1.tgz
