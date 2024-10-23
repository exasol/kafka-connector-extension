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

| Dependency                                 | License                                                                                |
| ------------------------------------------ | -------------------------------------------------------------------------------------- |
| [scalatest][21]                            | [the Apache License, ASL Version 2.0][22]                                              |
| [scalatestplus-mockito][23]                | [Apache-2.0][22]                                                                       |
| [mockito-core][24]                         | [MIT][25]                                                                              |
| [Test containers for Exasol on Docker][26] | [MIT License][27]                                                                      |
| [Test Database Builder for Java][28]       | [MIT License][29]                                                                      |
| [Matcher for SQL Result Sets][30]          | [MIT License][31]                                                                      |
| [Extension integration tests library][32]  | [MIT License][33]                                                                      |
| [embedded-kafka-schema-registry][34]       | [MIT][25]                                                                              |
| [Jetty :: Http Utility][35]                | [Eclipse Public License - Version 2.0][36]; [Apache Software License - Version 2.0][1] |
| [Jetty :: Server Core][37]                 | [Eclipse Public License - Version 2.0][36]; [Apache Software License - Version 2.0][1] |
| [kafka-streams-avro-serde][38]             | [Apache License 2.0][11]                                                               |
| [avro4s-core][39]                          | [MIT][25]                                                                              |
| [Testcontainers :: Kafka][40]              | [MIT][41]                                                                              |
| [ClassGraph][42]                           | [The MIT License (MIT)][41]                                                            |
| [Protocol Buffers [Core]][43]              | [BSD-3-Clause][44]                                                                     |

### Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [Apache Maven Clean Plugin][45]                         | [Apache-2.0][5]                               |
| [Apache Maven Install Plugin][46]                       | [Apache-2.0][5]                               |
| [Apache Maven Resources Plugin][47]                     | [Apache-2.0][5]                               |
| [Apache Maven Site Plugin][48]                          | [Apache License, Version 2.0][5]              |
| [SonarQube Scanner for Maven][49]                       | [GNU LGPL 3][50]                              |
| [Apache Maven Toolchains Plugin][51]                    | [Apache-2.0][5]                               |
| [Apache Maven Compiler Plugin][52]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][53]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][54]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][55] | [ASL2][14]                                    |
| [scala-maven-plugin][56]                                | [Public domain (Unlicense)][57]               |
| [ScalaTest Maven Plugin][58]                            | [the Apache License, ASL Version 2.0][22]     |
| [Apache Maven Javadoc Plugin][59]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][60]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][61]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][62]          | [Apache License 2.0][11]                      |
| [Apache Maven Assembly Plugin][63]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][64]                           | [Apache-2.0][5]                               |
| [Artifact reference checker and unifier][65]            | [MIT License][66]                             |
| [Maven Failsafe Plugin][67]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][68]                            | [EPL-2.0][36]                                 |
| [Quality Summarizer Maven Plugin][69]                   | [MIT License][70]                             |
| [error-code-crawler-maven-plugin][71]                   | [MIT License][72]                             |
| [Reproducible Build Maven Plugin][73]                   | [Apache 2.0][14]                              |
| [Project Keeper Maven plugin][74]                       | [The MIT License][75]                         |
| [OpenFastTrace Maven Plugin][76]                        | [GNU General Public License v3.0][77]         |
| [Scalastyle Maven Plugin][78]                           | [Apache 2.0][11]                              |
| [spotless-maven-plugin][79]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][80]                             | [BSD-3-Clause][44]                            |
| [Exec Maven Plugin][81]                                 | [Apache License 2][5]                         |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][82] | MIT     |

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
[35]: https://jetty.org/jetty-http
[36]: https://www.eclipse.org/legal/epl-2.0/
[37]: https://jetty.org/jetty-server
[38]: http://confluent.io/kafka-streams-avro-serde
[39]: https://github.com/sksamuel/avro4s
[40]: https://java.testcontainers.org
[41]: http://opensource.org/licenses/MIT
[42]: https://github.com/classgraph/classgraph
[43]: https://developers.google.com/protocol-buffers/protobuf-java/
[44]: https://opensource.org/licenses/BSD-3-Clause
[45]: https://maven.apache.org/plugins/maven-clean-plugin/
[46]: https://maven.apache.org/plugins/maven-install-plugin/
[47]: https://maven.apache.org/plugins/maven-resources-plugin/
[48]: https://maven.apache.org/plugins/maven-site-plugin/
[49]: http://sonarsource.github.io/sonar-scanner-maven/
[50]: http://www.gnu.org/licenses/lgpl.txt
[51]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[52]: https://maven.apache.org/plugins/maven-compiler-plugin/
[53]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[54]: https://www.mojohaus.org/flatten-maven-plugin/
[55]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[56]: http://github.com/davidB/scala-maven-plugin
[57]: http://unlicense.org/
[58]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[59]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[60]: https://maven.apache.org/surefire/maven-surefire-plugin/
[61]: https://www.mojohaus.org/versions/versions-maven-plugin/
[62]: https://basepom.github.io/duplicate-finder-maven-plugin
[63]: https://maven.apache.org/plugins/maven-assembly-plugin/
[64]: https://maven.apache.org/plugins/maven-jar-plugin/
[65]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[66]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[67]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[68]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[69]: https://github.com/exasol/quality-summarizer-maven-plugin/
[70]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[71]: https://github.com/exasol/error-code-crawler-maven-plugin/
[72]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[73]: http://zlika.github.io/reproducible-build-maven-plugin
[74]: https://github.com/exasol/project-keeper/
[75]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[76]: https://github.com/itsallcode/openfasttrace-maven-plugin
[77]: https://www.gnu.org/licenses/gpl-3.0.html
[78]: http://www.scalastyle.org
[79]: https://github.com/diffplug/spotless
[80]: https://github.com/evis/scalafix-maven-plugin
[81]: https://www.mojohaus.org/exec-maven-plugin
[82]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.1.tgz
