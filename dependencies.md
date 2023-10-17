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

| Dependency                                  | License                                                                                |
| ------------------------------------------- | -------------------------------------------------------------------------------------- |
| [scalatest][14]                             | [the Apache License, ASL Version 2.0][15]                                              |
| [scalatestplus-mockito][16]                 | [Apache-2.0][15]                                                                       |
| [mockito-core][17]                          | [MIT][18]                                                                              |
| [Test containers for Exasol on Docker][19]  | [MIT License][20]                                                                      |
| [Test Database Builder for Java][21]        | [MIT License][22]                                                                      |
| [Matcher for SQL Result Sets][23]           | [MIT License][24]                                                                      |
| [Extension integration tests library][25]   | [MIT License][26]                                                                      |
| [embedded-kafka-schema-registry][27]        | [MIT][28]                                                                              |
| [JSON in Java][29]                          | [Public Domain][30]                                                                    |
| [Apache ZooKeeper - Server][31]             | [Apache License, Version 2.0][32]                                                      |
| [Jetty :: HTTP2 :: Server][33]              | [Eclipse Public License - Version 2.0][34]; [Apache Software License - Version 2.0][1] |
| [Jetty :: Utility Servlets and Filters][35] | [Eclipse Public License - Version 2.0][34]; [Apache Software License - Version 2.0][1] |
| [kafka-streams-avro-serde][36]              | [Apache License 2.0][7]                                                                |
| [avro4s-core][37]                           | [MIT][28]                                                                              |
| [Apache Avro][38]                           | [Apache-2.0][32]                                                                       |
| [Netty/Handler][39]                         | [Apache License, Version 2.0][1]                                                       |
| [Testcontainers :: Kafka][40]               | [MIT][41]                                                                              |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][42]                       | [GNU LGPL 3][43]                               |
| [Apache Maven Compiler Plugin][44]                      | [Apache-2.0][32]                               |
| [Apache Maven Enforcer Plugin][45]                      | [Apache-2.0][32]                               |
| [Maven Flatten Plugin][46]                              | [Apache Software Licenese][32]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][47] | [ASL2][10]                                     |
| [scala-maven-plugin][48]                                | [Public domain (Unlicense)][49]                |
| [ScalaTest Maven Plugin][50]                            | [the Apache License, ASL Version 2.0][15]      |
| [Apache Maven Javadoc Plugin][51]                       | [Apache-2.0][32]                               |
| [Maven Surefire Plugin][52]                             | [Apache-2.0][32]                               |
| [Versions Maven Plugin][53]                             | [Apache License, Version 2.0][32]              |
| [duplicate-finder-maven-plugin Maven Mojo][54]          | [Apache License 2.0][7]                        |
| [Apache Maven Assembly Plugin][55]                      | [Apache-2.0][32]                               |
| [Apache Maven JAR Plugin][56]                           | [Apache License, Version 2.0][32]              |
| [Artifact reference checker and unifier][57]            | [MIT License][58]                              |
| [Maven Failsafe Plugin][59]                             | [Apache-2.0][32]                               |
| [JaCoCo :: Maven Plugin][60]                            | [Eclipse Public License 2.0][34]               |
| [error-code-crawler-maven-plugin][61]                   | [MIT License][62]                              |
| [Reproducible Build Maven Plugin][63]                   | [Apache 2.0][10]                               |
| [Project keeper maven plugin][64]                       | [The MIT License][65]                          |
| [OpenFastTrace Maven Plugin][66]                        | [GNU General Public License v3.0][67]          |
| [Scalastyle Maven Plugin][68]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][69]                             | [The Apache Software License, Version 2.0][32] |
| [scalafix-maven-plugin][70]                             | [BSD-3-Clause][71]                             |
| [Exec Maven Plugin][72]                                 | [Apache License 2][32]                         |
| [Maven Clean Plugin][73]                                | [The Apache Software License, Version 2.0][10] |
| [Maven Resources Plugin][74]                            | [The Apache Software License, Version 2.0][10] |
| [Maven Install Plugin][75]                              | [The Apache Software License, Version 2.0][10] |
| [Maven Deploy Plugin][76]                               | [The Apache Software License, Version 2.0][10] |
| [Maven Site Plugin 3][77]                               | [The Apache Software License, Version 2.0][10] |

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
[18]: https://github.com/mockito/mockito/blob/main/LICENSE
[19]: https://github.com/exasol/exasol-testcontainers/
[20]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[21]: https://github.com/exasol/test-db-builder-java/
[22]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[23]: https://github.com/exasol/hamcrest-resultset-matcher/
[24]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[25]: https://github.com/exasol/extension-manager/
[26]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[27]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[28]: https://opensource.org/licenses/MIT
[29]: https://github.com/douglascrockford/JSON-java
[30]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[31]: http://zookeeper.apache.org/zookeeper
[32]: https://www.apache.org/licenses/LICENSE-2.0.txt
[33]: https://eclipse.dev/jetty/http2-parent/http2-server
[34]: https://www.eclipse.org/legal/epl-2.0/
[35]: https://eclipse.dev/jetty/jetty-servlets
[36]: http://confluent.io/kafka-streams-avro-serde
[37]: https://github.com/sksamuel/avro4s
[38]: https://avro.apache.org
[39]: https://netty.io/netty-handler/
[40]: https://java.testcontainers.org
[41]: http://opensource.org/licenses/MIT
[42]: http://sonarsource.github.io/sonar-scanner-maven/
[43]: http://www.gnu.org/licenses/lgpl.txt
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
[61]: https://github.com/exasol/error-code-crawler-maven-plugin/
[62]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[63]: http://zlika.github.io/reproducible-build-maven-plugin
[64]: https://github.com/exasol/project-keeper/
[65]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[66]: https://github.com/itsallcode/openfasttrace-maven-plugin
[67]: https://www.gnu.org/licenses/gpl-3.0.html
[68]: http://www.scalastyle.org
[69]: https://github.com/diffplug/spotless
[70]: https://github.com/evis/scalafix-maven-plugin
[71]: https://opensource.org/licenses/BSD-3-Clause
[72]: https://www.mojohaus.org/exec-maven-plugin
[73]: http://maven.apache.org/plugins/maven-clean-plugin/
[74]: http://maven.apache.org/plugins/maven-resources-plugin/
[75]: http://maven.apache.org/plugins/maven-install-plugin/
[76]: http://maven.apache.org/plugins/maven-deploy-plugin/
[77]: http://maven.apache.org/plugins/maven-site-plugin/
