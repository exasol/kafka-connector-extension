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

## Test Dependencies

| Dependency                                 | License                                                                                |
| ------------------------------------------ | -------------------------------------------------------------------------------------- |
| [scalatest][12]                            | [the Apache License, ASL Version 2.0][13]                                              |
| [scalatestplus-mockito][14]                | [Apache-2.0][13]                                                                       |
| [mockito-core][15]                         | [MIT][16]                                                                              |
| [Test containers for Exasol on Docker][17] | [MIT License][18]                                                                      |
| [Test Database Builder for Java][19]       | [MIT License][20]                                                                      |
| [Matcher for SQL Result Sets][21]          | [MIT License][22]                                                                      |
| [Extension integration tests library][23]  | [MIT License][24]                                                                      |
| [embedded-kafka-schema-registry][25]       | [MIT][26]                                                                              |
| [JSON in Java][27]                         | [Public Domain][28]                                                                    |
| [Apache ZooKeeper - Server][29]            | [Apache License, Version 2.0][30]                                                      |
| [Jetty :: HTTP2 :: Server][31]             | [Eclipse Public License - Version 2.0][32]; [Apache Software License - Version 2.0][1] |
| [kafka-streams-avro-serde][33]             | [Apache License 2.0][7]                                                                |
| [avro4s-core][34]                          | [MIT][26]                                                                              |
| [Apache Avro][35]                          | [Apache-2.0][30]                                                                       |
| [Netty/Handler][36]                        | [Apache License, Version 2.0][1]                                                       |
| [Testcontainers :: Kafka][37]              | [MIT][38]                                                                              |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][39]                       | [GNU LGPL 3][40]                               |
| [Apache Maven Compiler Plugin][41]                      | [Apache-2.0][30]                               |
| [Apache Maven Enforcer Plugin][42]                      | [Apache-2.0][30]                               |
| [Maven Flatten Plugin][43]                              | [Apache Software Licenese][30]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][44] | [ASL2][10]                                     |
| [scala-maven-plugin][45]                                | [Public domain (Unlicense)][46]                |
| [ScalaTest Maven Plugin][47]                            | [the Apache License, ASL Version 2.0][13]      |
| [Apache Maven Javadoc Plugin][48]                       | [Apache-2.0][30]                               |
| [Maven Surefire Plugin][49]                             | [Apache-2.0][30]                               |
| [Versions Maven Plugin][50]                             | [Apache License, Version 2.0][30]              |
| [duplicate-finder-maven-plugin Maven Mojo][51]          | [Apache License 2.0][7]                        |
| [Apache Maven Assembly Plugin][52]                      | [Apache-2.0][30]                               |
| [Apache Maven JAR Plugin][53]                           | [Apache License, Version 2.0][30]              |
| [Artifact reference checker and unifier][54]            | [MIT License][55]                              |
| [Maven Failsafe Plugin][56]                             | [Apache-2.0][30]                               |
| [JaCoCo :: Maven Plugin][57]                            | [Eclipse Public License 2.0][32]               |
| [error-code-crawler-maven-plugin][58]                   | [MIT License][59]                              |
| [Reproducible Build Maven Plugin][60]                   | [Apache 2.0][10]                               |
| [Project keeper maven plugin][61]                       | [The MIT License][62]                          |
| [OpenFastTrace Maven Plugin][63]                        | [GNU General Public License v3.0][64]          |
| [Scalastyle Maven Plugin][65]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][66]                             | [The Apache Software License, Version 2.0][30] |
| [scalafix-maven-plugin][67]                             | [BSD-3-Clause][68]                             |
| [Exec Maven Plugin][69]                                 | [Apache License 2][30]                         |
| [Maven Clean Plugin][70]                                | [The Apache Software License, Version 2.0][10] |
| [Maven Resources Plugin][71]                            | [The Apache Software License, Version 2.0][10] |
| [Maven Install Plugin][72]                              | [The Apache Software License, Version 2.0][10] |
| [Maven Deploy Plugin][73]                               | [The Apache Software License, Version 2.0][10] |
| [Maven Site Plugin 3][74]                               | [The Apache Software License, Version 2.0][10] |

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
[12]: http://www.scalatest.org
[13]: http://www.apache.org/licenses/LICENSE-2.0
[14]: https://github.com/scalatest/scalatestplus-mockito
[15]: https://github.com/mockito/mockito
[16]: https://github.com/mockito/mockito/blob/main/LICENSE
[17]: https://github.com/exasol/exasol-testcontainers/
[18]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[19]: https://github.com/exasol/test-db-builder-java/
[20]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[21]: https://github.com/exasol/hamcrest-resultset-matcher/
[22]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[23]: https://github.com/exasol/extension-manager/
[24]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[25]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[26]: https://opensource.org/licenses/MIT
[27]: https://github.com/douglascrockford/JSON-java
[28]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[29]: http://zookeeper.apache.org/zookeeper
[30]: https://www.apache.org/licenses/LICENSE-2.0.txt
[31]: https://eclipse.dev/jetty/http2-parent/http2-server
[32]: https://www.eclipse.org/legal/epl-2.0/
[33]: http://confluent.io/kafka-streams-avro-serde
[34]: https://github.com/sksamuel/avro4s
[35]: https://avro.apache.org
[36]: https://netty.io/netty-handler/
[37]: https://java.testcontainers.org
[38]: http://opensource.org/licenses/MIT
[39]: http://sonarsource.github.io/sonar-scanner-maven/
[40]: http://www.gnu.org/licenses/lgpl.txt
[41]: https://maven.apache.org/plugins/maven-compiler-plugin/
[42]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[43]: https://www.mojohaus.org/flatten-maven-plugin/
[44]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[45]: http://github.com/davidB/scala-maven-plugin
[46]: http://unlicense.org/
[47]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[48]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[49]: https://maven.apache.org/surefire/maven-surefire-plugin/
[50]: https://www.mojohaus.org/versions/versions-maven-plugin/
[51]: https://basepom.github.io/duplicate-finder-maven-plugin
[52]: https://maven.apache.org/plugins/maven-assembly-plugin/
[53]: https://maven.apache.org/plugins/maven-jar-plugin/
[54]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[55]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[56]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[57]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[58]: https://github.com/exasol/error-code-crawler-maven-plugin/
[59]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[60]: http://zlika.github.io/reproducible-build-maven-plugin
[61]: https://github.com/exasol/project-keeper/
[62]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[63]: https://github.com/itsallcode/openfasttrace-maven-plugin
[64]: https://www.gnu.org/licenses/gpl-3.0.html
[65]: http://www.scalastyle.org
[66]: https://github.com/diffplug/spotless
[67]: https://github.com/evis/scalafix-maven-plugin
[68]: https://opensource.org/licenses/BSD-3-Clause
[69]: https://www.mojohaus.org/exec-maven-plugin
[70]: http://maven.apache.org/plugins/maven-clean-plugin/
[71]: http://maven.apache.org/plugins/maven-resources-plugin/
[72]: http://maven.apache.org/plugins/maven-install-plugin/
[73]: http://maven.apache.org/plugins/maven-deploy-plugin/
[74]: http://maven.apache.org/plugins/maven-site-plugin/
