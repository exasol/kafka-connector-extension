<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                  | License                               |
| ------------------------------------------- | ------------------------------------- |
| [Scala Library][0]                          | [Apache-2.0][1]                       |
| [Import Export UDF Common Scala][2]         | [MIT License][3]                      |
| [error-reporting-java][4]                   | [MIT License][5]                      |
| [Apache Commons Compress][6]                | [Apache-2.0][7]                       |
| [kafka-avro-serializer][8]                  | [Apache License 2.0][9]               |
| [scala-collection-compat][10]               | [Apache-2.0][1]                       |
| [Guava: Google Core Libraries for Java][11] | [Apache License, Version 2.0][12]     |
| [Apache Kafka][13]                          | [The Apache License, Version 2.0][12] |
| [snappy-java][14]                           | [Apache-2.0][15]                      |

## Test Dependencies

| Dependency                                 | License                                                                       |
| ------------------------------------------ | ----------------------------------------------------------------------------- |
| [scalatest][16]                            | [the Apache License, ASL Version 2.0][17]                                     |
| [scalatestplus-mockito][18]                | [Apache-2.0][17]                                                              |
| [mockito-core][19]                         | [MIT][20]                                                                     |
| [Test containers for Exasol on Docker][21] | [MIT License][22]                                                             |
| [Test Database Builder for Java][23]       | [MIT License][24]                                                             |
| [Matcher for SQL Result Sets][25]          | [MIT License][26]                                                             |
| [Extension integration tests library][27]  | [MIT License][28]                                                             |
| [embedded-kafka-schema-registry][29]       | [MIT][20]                                                                     |
| [JSON in Java][30]                         | [Public Domain][31]                                                           |
| [Apache ZooKeeper - Server][32]            | [Apache License, Version 2.0][7]                                              |
| [Logback Core Module][33]                  | [Eclipse Public License - v 1.0][34]; [GNU Lesser General Public License][35] |
| [Logback Classic Module][36]               | [Eclipse Public License - v 1.0][34]; [GNU Lesser General Public License][35] |
| [kafka-streams-avro-serde][37]             | [Apache License 2.0][9]                                                       |
| [avro4s-core][38]                          | [MIT][20]                                                                     |
| [Apache Avro][39]                          | [Apache-2.0][7]                                                               |
| [Testcontainers :: Kafka][40]              | [MIT][41]                                                                     |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][42]                       | [GNU LGPL 3][43]                              |
| [Apache Maven Toolchains Plugin][44]                    | [Apache License, Version 2.0][7]              |
| [Apache Maven Compiler Plugin][45]                      | [Apache-2.0][7]                               |
| [Apache Maven Enforcer Plugin][46]                      | [Apache-2.0][7]                               |
| [Maven Flatten Plugin][47]                              | [Apache Software Licenese][7]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][48] | [ASL2][12]                                    |
| [scala-maven-plugin][49]                                | [Public domain (Unlicense)][50]               |
| [ScalaTest Maven Plugin][51]                            | [the Apache License, ASL Version 2.0][17]     |
| [Apache Maven Javadoc Plugin][52]                       | [Apache-2.0][7]                               |
| [Maven Surefire Plugin][53]                             | [Apache-2.0][7]                               |
| [Versions Maven Plugin][54]                             | [Apache License, Version 2.0][7]              |
| [duplicate-finder-maven-plugin Maven Mojo][55]          | [Apache License 2.0][9]                       |
| [Apache Maven Assembly Plugin][56]                      | [Apache-2.0][7]                               |
| [Apache Maven JAR Plugin][57]                           | [Apache License, Version 2.0][7]              |
| [Artifact reference checker and unifier][58]            | [MIT License][59]                             |
| [Maven Failsafe Plugin][60]                             | [Apache-2.0][7]                               |
| [JaCoCo :: Maven Plugin][61]                            | [Eclipse Public License 2.0][62]              |
| [error-code-crawler-maven-plugin][63]                   | [MIT License][64]                             |
| [Reproducible Build Maven Plugin][65]                   | [Apache 2.0][12]                              |
| [Project Keeper Maven plugin][66]                       | [The MIT License][67]                         |
| [OpenFastTrace Maven Plugin][68]                        | [GNU General Public License v3.0][69]         |
| [Scalastyle Maven Plugin][70]                           | [Apache 2.0][9]                               |
| [spotless-maven-plugin][71]                             | [The Apache Software License, Version 2.0][7] |
| [scalafix-maven-plugin][72]                             | [BSD-3-Clause][73]                            |
| [Exec Maven Plugin][74]                                 | [Apache License 2][7]                         |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: https://commons.apache.org/proper/commons-compress/
[7]: https://www.apache.org/licenses/LICENSE-2.0.txt
[8]: http://confluent.io/kafka-avro-serializer
[9]: http://www.apache.org/licenses/LICENSE-2.0.html
[10]: http://www.scala-lang.org/
[11]: https://github.com/google/guava
[12]: http://www.apache.org/licenses/LICENSE-2.0.txt
[13]: https://kafka.apache.org
[14]: https://github.com/xerial/snappy-java
[15]: https://www.apache.org/licenses/LICENSE-2.0.html
[16]: http://www.scalatest.org
[17]: http://www.apache.org/licenses/LICENSE-2.0
[18]: https://github.com/scalatest/scalatestplus-mockito
[19]: https://github.com/mockito/mockito
[20]: https://opensource.org/licenses/MIT
[21]: https://github.com/exasol/exasol-testcontainers/
[22]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[23]: https://github.com/exasol/test-db-builder-java/
[24]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[25]: https://github.com/exasol/hamcrest-resultset-matcher/
[26]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[27]: https://github.com/exasol/extension-manager/
[28]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[29]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[30]: https://github.com/douglascrockford/JSON-java
[31]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[32]: http://zookeeper.apache.org/zookeeper
[33]: http://logback.qos.ch/logback-core
[34]: http://www.eclipse.org/legal/epl-v10.html
[35]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[36]: http://logback.qos.ch/logback-classic
[37]: http://confluent.io/kafka-streams-avro-serde
[38]: https://github.com/sksamuel/avro4s
[39]: https://avro.apache.org
[40]: https://java.testcontainers.org
[41]: http://opensource.org/licenses/MIT
[42]: http://sonarsource.github.io/sonar-scanner-maven/
[43]: http://www.gnu.org/licenses/lgpl.txt
[44]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[45]: https://maven.apache.org/plugins/maven-compiler-plugin/
[46]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[47]: https://www.mojohaus.org/flatten-maven-plugin/
[48]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[49]: http://github.com/davidB/scala-maven-plugin
[50]: http://unlicense.org/
[51]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[52]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[53]: https://maven.apache.org/surefire/maven-surefire-plugin/
[54]: https://www.mojohaus.org/versions/versions-maven-plugin/
[55]: https://basepom.github.io/duplicate-finder-maven-plugin
[56]: https://maven.apache.org/plugins/maven-assembly-plugin/
[57]: https://maven.apache.org/plugins/maven-jar-plugin/
[58]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[59]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[60]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[61]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[62]: https://www.eclipse.org/legal/epl-2.0/
[63]: https://github.com/exasol/error-code-crawler-maven-plugin/
[64]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[65]: http://zlika.github.io/reproducible-build-maven-plugin
[66]: https://github.com/exasol/project-keeper/
[67]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[68]: https://github.com/itsallcode/openfasttrace-maven-plugin
[69]: https://www.gnu.org/licenses/gpl-3.0.html
[70]: http://www.scalastyle.org
[71]: https://github.com/diffplug/spotless
[72]: https://github.com/evis/scalafix-maven-plugin
[73]: https://opensource.org/licenses/BSD-3-Clause
[74]: https://www.mojohaus.org/exec-maven-plugin
