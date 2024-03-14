<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                  | License                               |
| ------------------------------------------- | ------------------------------------- |
| [Scala Library][0]                          | [Apache-2.0][1]                       |
| [Import Export UDF Common Scala][2]         | [MIT License][3]                      |
| [Apache Avro][4]                            | [Apache-2.0][5]                       |
| [error-reporting-java][6]                   | [MIT License][7]                      |
| [Apache Commons Compress][8]                | [Apache-2.0][5]                       |
| [kafka-avro-serializer][9]                  | [Apache License 2.0][10]              |
| [scala-collection-compat][11]               | [Apache-2.0][1]                       |
| [Guava: Google Core Libraries for Java][12] | [Apache License, Version 2.0][13]     |
| [Confluent Server][14]                      | [The Apache License, Version 2.0][13] |
| [snappy-java][15]                           | [Apache-2.0][16]                      |

## Test Dependencies

| Dependency                                 | License                                                                                |
| ------------------------------------------ | -------------------------------------------------------------------------------------- |
| [scalatest][17]                            | [the Apache License, ASL Version 2.0][18]                                              |
| [scalatestplus-mockito][19]                | [Apache-2.0][18]                                                                       |
| [mockito-core][20]                         | [MIT][21]                                                                              |
| [Test containers for Exasol on Docker][22] | [MIT License][23]                                                                      |
| [Test Database Builder for Java][24]       | [MIT License][25]                                                                      |
| [Matcher for SQL Result Sets][26]          | [MIT License][27]                                                                      |
| [Extension integration tests library][28]  | [MIT License][29]                                                                      |
| [embedded-kafka-schema-registry][30]       | [MIT][21]                                                                              |
| [JSON in Java][31]                         | [Public Domain][32]                                                                    |
| [Apache ZooKeeper - Server][33]            | [Apache License, Version 2.0][5]                                                       |
| [jose4j][34]                               | [The Apache Software License, Version 2.0][13]                                         |
| [Jetty :: HTTP2 :: Common][35]             | [Eclipse Public License - Version 2.0][36]; [Apache Software License - Version 2.0][1] |
| [Logback Core Module][37]                  | [Eclipse Public License - v 1.0][38]; [GNU Lesser General Public License][39]          |
| [Logback Classic Module][40]               | [Eclipse Public License - v 1.0][38]; [GNU Lesser General Public License][39]          |
| [kafka-streams-avro-serde][41]             | [Apache License 2.0][10]                                                               |
| [avro4s-core][42]                          | [MIT][21]                                                                              |
| [Testcontainers :: Kafka][43]              | [MIT][44]                                                                              |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][45]                       | [GNU LGPL 3][46]                              |
| [Apache Maven Toolchains Plugin][47]                    | [Apache License, Version 2.0][5]              |
| [Apache Maven Compiler Plugin][48]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][49]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][50]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][51] | [ASL2][13]                                    |
| [scala-maven-plugin][52]                                | [Public domain (Unlicense)][53]               |
| [ScalaTest Maven Plugin][54]                            | [the Apache License, ASL Version 2.0][18]     |
| [Apache Maven Javadoc Plugin][55]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][56]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][57]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][58]          | [Apache License 2.0][10]                      |
| [Apache Maven Assembly Plugin][59]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][60]                           | [Apache License, Version 2.0][5]              |
| [Artifact reference checker and unifier][61]            | [MIT License][62]                             |
| [Maven Failsafe Plugin][63]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][64]                            | [Eclipse Public License 2.0][36]              |
| [error-code-crawler-maven-plugin][65]                   | [MIT License][66]                             |
| [Reproducible Build Maven Plugin][67]                   | [Apache 2.0][13]                              |
| [Project Keeper Maven plugin][68]                       | [The MIT License][69]                         |
| [OpenFastTrace Maven Plugin][70]                        | [GNU General Public License v3.0][71]         |
| [Scalastyle Maven Plugin][72]                           | [Apache 2.0][10]                              |
| [spotless-maven-plugin][73]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][74]                             | [BSD-3-Clause][75]                            |
| [Exec Maven Plugin][76]                                 | [Apache License 2][5]                         |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://avro.apache.org
[5]: https://www.apache.org/licenses/LICENSE-2.0.txt
[6]: https://github.com/exasol/error-reporting-java/
[7]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[8]: https://commons.apache.org/proper/commons-compress/
[9]: http://confluent.io/kafka-avro-serializer
[10]: http://www.apache.org/licenses/LICENSE-2.0.html
[11]: http://www.scala-lang.org/
[12]: https://github.com/google/guava
[13]: http://www.apache.org/licenses/LICENSE-2.0.txt
[14]: https://kafka.apache.org
[15]: https://github.com/xerial/snappy-java
[16]: https://www.apache.org/licenses/LICENSE-2.0.html
[17]: http://www.scalatest.org
[18]: http://www.apache.org/licenses/LICENSE-2.0
[19]: https://github.com/scalatest/scalatestplus-mockito
[20]: https://github.com/mockito/mockito
[21]: https://opensource.org/licenses/MIT
[22]: https://github.com/exasol/exasol-testcontainers/
[23]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[24]: https://github.com/exasol/test-db-builder-java/
[25]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[26]: https://github.com/exasol/hamcrest-resultset-matcher/
[27]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[28]: https://github.com/exasol/extension-manager/
[29]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[30]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[31]: https://github.com/douglascrockford/JSON-java
[32]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[33]: http://zookeeper.apache.org/zookeeper
[34]: https://bitbucket.org/b_c/jose4j/
[35]: https://eclipse.dev/jetty/http2-parent/http2-common
[36]: https://www.eclipse.org/legal/epl-2.0/
[37]: http://logback.qos.ch/logback-core
[38]: http://www.eclipse.org/legal/epl-v10.html
[39]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[40]: http://logback.qos.ch/logback-classic
[41]: http://confluent.io/kafka-streams-avro-serde
[42]: https://github.com/sksamuel/avro4s
[43]: https://java.testcontainers.org
[44]: http://opensource.org/licenses/MIT
[45]: http://sonarsource.github.io/sonar-scanner-maven/
[46]: http://www.gnu.org/licenses/lgpl.txt
[47]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[48]: https://maven.apache.org/plugins/maven-compiler-plugin/
[49]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[50]: https://www.mojohaus.org/flatten-maven-plugin/
[51]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[52]: http://github.com/davidB/scala-maven-plugin
[53]: http://unlicense.org/
[54]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[55]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[56]: https://maven.apache.org/surefire/maven-surefire-plugin/
[57]: https://www.mojohaus.org/versions/versions-maven-plugin/
[58]: https://basepom.github.io/duplicate-finder-maven-plugin
[59]: https://maven.apache.org/plugins/maven-assembly-plugin/
[60]: https://maven.apache.org/plugins/maven-jar-plugin/
[61]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[62]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[63]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[64]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[65]: https://github.com/exasol/error-code-crawler-maven-plugin/
[66]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[67]: http://zlika.github.io/reproducible-build-maven-plugin
[68]: https://github.com/exasol/project-keeper/
[69]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[70]: https://github.com/itsallcode/openfasttrace-maven-plugin
[71]: https://www.gnu.org/licenses/gpl-3.0.html
[72]: http://www.scalastyle.org
[73]: https://github.com/diffplug/spotless
[74]: https://github.com/evis/scalafix-maven-plugin
[75]: https://opensource.org/licenses/BSD-3-Clause
[76]: https://www.mojohaus.org/exec-maven-plugin
