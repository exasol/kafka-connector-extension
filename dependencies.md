<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License                               |
| ------------------------------------------ | ------------------------------------- |
| [Scala Library][0]                         | [Apache-2.0][1]                       |
| [Import Export UDF Common Scala][2]        | [MIT License][3]                      |
| [error-reporting-java][4]                  | [MIT License][5]                      |
| [kafka-avro-serializer][6]                 | [Apache License 2.0][7]               |
| [scala-collection-compat][8]               | [Apache-2.0][1]                       |
| [Guava: Google Core Libraries for Java][9] | [Apache License, Version 2.0][10]     |
| [Apache Kafka][11]                         | [The Apache License, Version 2.0][10] |
| [snappy-java][12]                          | [Apache-2.0][13]                      |

## Test Dependencies

| Dependency                                 | License                                                                       |
| ------------------------------------------ | ----------------------------------------------------------------------------- |
| [scalatest][14]                            | [the Apache License, ASL Version 2.0][15]                                     |
| [scalatestplus-mockito][16]                | [Apache-2.0][15]                                                              |
| [mockito-core][17]                         | [MIT][18]                                                                     |
| [Test containers for Exasol on Docker][19] | [MIT License][20]                                                             |
| [Test Database Builder for Java][21]       | [MIT License][22]                                                             |
| [Matcher for SQL Result Sets][23]          | [MIT License][24]                                                             |
| [Extension integration tests library][25]  | [MIT License][26]                                                             |
| [embedded-kafka-schema-registry][27]       | [MIT][18]                                                                     |
| [JSON in Java][28]                         | [Public Domain][29]                                                           |
| [Apache ZooKeeper - Server][30]            | [Apache License, Version 2.0][31]                                             |
| [Logback Core Module][32]                  | [Eclipse Public License - v 1.0][33]; [GNU Lesser General Public License][34] |
| [Logback Classic Module][35]               | [Eclipse Public License - v 1.0][33]; [GNU Lesser General Public License][34] |
| [kafka-streams-avro-serde][36]             | [Apache License 2.0][7]                                                       |
| [avro4s-core][37]                          | [MIT][18]                                                                     |
| [Apache Avro][38]                          | [Apache-2.0][31]                                                              |
| [Testcontainers :: Kafka][39]              | [MIT][40]                                                                     |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][41]                       | [GNU LGPL 3][42]                               |
| [Apache Maven Toolchains Plugin][43]                    | [Apache License, Version 2.0][31]              |
| [Apache Maven Compiler Plugin][44]                      | [Apache-2.0][31]                               |
| [Apache Maven Enforcer Plugin][45]                      | [Apache-2.0][31]                               |
| [Maven Flatten Plugin][46]                              | [Apache Software Licenese][31]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][47] | [ASL2][10]                                     |
| [scala-maven-plugin][48]                                | [Public domain (Unlicense)][49]                |
| [ScalaTest Maven Plugin][50]                            | [the Apache License, ASL Version 2.0][15]      |
| [Apache Maven Javadoc Plugin][51]                       | [Apache-2.0][31]                               |
| [Maven Surefire Plugin][52]                             | [Apache-2.0][31]                               |
| [Versions Maven Plugin][53]                             | [Apache License, Version 2.0][31]              |
| [duplicate-finder-maven-plugin Maven Mojo][54]          | [Apache License 2.0][7]                        |
| [Apache Maven Assembly Plugin][55]                      | [Apache-2.0][31]                               |
| [Apache Maven JAR Plugin][56]                           | [Apache License, Version 2.0][31]              |
| [Artifact reference checker and unifier][57]            | [MIT License][58]                              |
| [Maven Failsafe Plugin][59]                             | [Apache-2.0][31]                               |
| [JaCoCo :: Maven Plugin][60]                            | [Eclipse Public License 2.0][61]               |
| [error-code-crawler-maven-plugin][62]                   | [MIT License][63]                              |
| [Reproducible Build Maven Plugin][64]                   | [Apache 2.0][10]                               |
| [Project Keeper Maven plugin][65]                       | [The MIT License][66]                          |
| [OpenFastTrace Maven Plugin][67]                        | [GNU General Public License v3.0][68]          |
| [Scalastyle Maven Plugin][69]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][70]                             | [The Apache Software License, Version 2.0][31] |
| [scalafix-maven-plugin][71]                             | [BSD-3-Clause][72]                             |
| [Exec Maven Plugin][73]                                 | [Apache License 2][31]                         |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: http://confluent.io/kafka-avro-serializer
[7]: http://www.apache.org/licenses/LICENSE-2.0.html
[8]: http://www.scala-lang.org/
[9]: https://github.com/google/guava
[10]: http://www.apache.org/licenses/LICENSE-2.0.txt
[11]: https://kafka.apache.org
[12]: https://github.com/xerial/snappy-java
[13]: https://www.apache.org/licenses/LICENSE-2.0.html
[14]: http://www.scalatest.org
[15]: http://www.apache.org/licenses/LICENSE-2.0
[16]: https://github.com/scalatest/scalatestplus-mockito
[17]: https://github.com/mockito/mockito
[18]: https://opensource.org/licenses/MIT
[19]: https://github.com/exasol/exasol-testcontainers/
[20]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[21]: https://github.com/exasol/test-db-builder-java/
[22]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[23]: https://github.com/exasol/hamcrest-resultset-matcher/
[24]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[25]: https://github.com/exasol/extension-manager/
[26]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[27]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[28]: https://github.com/douglascrockford/JSON-java
[29]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[30]: http://zookeeper.apache.org/zookeeper
[31]: https://www.apache.org/licenses/LICENSE-2.0.txt
[32]: http://logback.qos.ch/logback-core
[33]: http://www.eclipse.org/legal/epl-v10.html
[34]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[35]: http://logback.qos.ch/logback-classic
[36]: http://confluent.io/kafka-streams-avro-serde
[37]: https://github.com/sksamuel/avro4s
[38]: https://avro.apache.org
[39]: https://java.testcontainers.org
[40]: http://opensource.org/licenses/MIT
[41]: http://sonarsource.github.io/sonar-scanner-maven/
[42]: http://www.gnu.org/licenses/lgpl.txt
[43]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[44]: https://maven.apache.org/plugins/maven-compiler-plugin/
[45]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[46]: https://www.mojohaus.org/flatten-maven-plugin/
[47]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[48]: http://github.com/davidB/scala-maven-plugin
[49]: http://unlicense.org/
[50]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[51]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[52]: https://maven.apache.org/surefire/maven-surefire-plugin/
[53]: https://www.mojohaus.org/versions/versions-maven-plugin/
[54]: https://basepom.github.io/duplicate-finder-maven-plugin
[55]: https://maven.apache.org/plugins/maven-assembly-plugin/
[56]: https://maven.apache.org/plugins/maven-jar-plugin/
[57]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[58]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[59]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[60]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[61]: https://www.eclipse.org/legal/epl-2.0/
[62]: https://github.com/exasol/error-code-crawler-maven-plugin/
[63]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[64]: http://zlika.github.io/reproducible-build-maven-plugin
[65]: https://github.com/exasol/project-keeper/
[66]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[67]: https://github.com/itsallcode/openfasttrace-maven-plugin
[68]: https://www.gnu.org/licenses/gpl-3.0.html
[69]: http://www.scalastyle.org
[70]: https://github.com/diffplug/spotless
[71]: https://github.com/evis/scalafix-maven-plugin
[72]: https://opensource.org/licenses/BSD-3-Clause
[73]: https://www.mojohaus.org/exec-maven-plugin
