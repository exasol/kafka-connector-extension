<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                 | License                           |
| ------------------------------------------ | --------------------------------- |
| [Scala Library][0]                         | [Apache-2.0][1]                   |
| [Import Export UDF Common Scala][2]        | [MIT License][3]                  |
| [error-reporting-java][4]                  | [MIT License][5]                  |
| [kafka-avro-serializer][6]                 | [Apache License 2.0][7]           |
| [scala-collection-compat][8]               | [Apache-2.0][1]                   |
| [Guava: Google Core Libraries for Java][9] | [Apache License, Version 2.0][10] |
| kafka-clients                              |                                   |
| [snappy-java][11]                          | [Apache-2.0][12]                  |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][13]                            | [the Apache License, ASL Version 2.0][14] |
| [scalatestplus-mockito][15]                | [Apache-2.0][14]                          |
| [mockito-core][16]                         | [MIT][17]                                 |
| [Test containers for Exasol on Docker][18] | [MIT License][19]                         |
| [Test Database Builder for Java][20]       | [MIT License][21]                         |
| [Matcher for SQL Result Sets][22]          | [MIT License][23]                         |
| [Extension integration tests library][24]  | [MIT License][25]                         |
| [embedded-kafka-schema-registry][26]       | [MIT][17]                                 |
| [JSON in Java][27]                         | [Public Domain][28]                       |
| [Apache ZooKeeper - Server][29]            | [Apache License, Version 2.0][30]         |
| [kafka-streams-avro-serde][31]             | [Apache License 2.0][7]                   |
| [avro4s-core][32]                          | [MIT][17]                                 |
| [Apache Avro][33]                          | [Apache-2.0][30]                          |
| [Netty/Handler][34]                        | [Apache License, Version 2.0][1]          |
| [Testcontainers :: Kafka][35]              | [MIT][36]                                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][37]                       | [GNU LGPL 3][38]                               |
| [Apache Maven Compiler Plugin][39]                      | [Apache-2.0][30]                               |
| [Apache Maven Enforcer Plugin][40]                      | [Apache-2.0][30]                               |
| [Maven Flatten Plugin][41]                              | [Apache Software Licenese][30]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][42] | [ASL2][10]                                     |
| [scala-maven-plugin][43]                                | [Public domain (Unlicense)][44]                |
| [ScalaTest Maven Plugin][45]                            | [the Apache License, ASL Version 2.0][14]      |
| [Apache Maven Javadoc Plugin][46]                       | [Apache-2.0][30]                               |
| [Maven Surefire Plugin][47]                             | [Apache-2.0][30]                               |
| [Versions Maven Plugin][48]                             | [Apache License, Version 2.0][30]              |
| [duplicate-finder-maven-plugin Maven Mojo][49]          | [Apache License 2.0][7]                        |
| [Apache Maven Assembly Plugin][50]                      | [Apache-2.0][30]                               |
| [Apache Maven JAR Plugin][51]                           | [Apache License, Version 2.0][30]              |
| [Artifact reference checker and unifier][52]            | [MIT License][53]                              |
| [Maven Failsafe Plugin][54]                             | [Apache-2.0][30]                               |
| [JaCoCo :: Maven Plugin][55]                            | [Eclipse Public License 2.0][56]               |
| [error-code-crawler-maven-plugin][57]                   | [MIT License][58]                              |
| [Reproducible Build Maven Plugin][59]                   | [Apache 2.0][10]                               |
| [Project keeper maven plugin][60]                       | [The MIT License][61]                          |
| [OpenFastTrace Maven Plugin][62]                        | [GNU General Public License v3.0][63]          |
| [Scalastyle Maven Plugin][64]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][65]                             | [The Apache Software License, Version 2.0][30] |
| [scalafix-maven-plugin][66]                             | [BSD-3-Clause][67]                             |
| [Exec Maven Plugin][68]                                 | [Apache License 2][30]                         |

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
[11]: https://github.com/xerial/snappy-java
[12]: https://www.apache.org/licenses/LICENSE-2.0.html
[13]: http://www.scalatest.org
[14]: http://www.apache.org/licenses/LICENSE-2.0
[15]: https://github.com/scalatest/scalatestplus-mockito
[16]: https://github.com/mockito/mockito
[17]: https://opensource.org/licenses/MIT
[18]: https://github.com/exasol/exasol-testcontainers/
[19]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[20]: https://github.com/exasol/test-db-builder-java/
[21]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[22]: https://github.com/exasol/hamcrest-resultset-matcher/
[23]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[24]: https://github.com/exasol/extension-manager/
[25]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[26]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[27]: https://github.com/douglascrockford/JSON-java
[28]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[29]: http://zookeeper.apache.org/zookeeper
[30]: https://www.apache.org/licenses/LICENSE-2.0.txt
[31]: http://confluent.io/kafka-streams-avro-serde
[32]: https://github.com/sksamuel/avro4s
[33]: https://avro.apache.org
[34]: https://netty.io/netty-handler/
[35]: https://java.testcontainers.org
[36]: http://opensource.org/licenses/MIT
[37]: http://sonarsource.github.io/sonar-scanner-maven/
[38]: http://www.gnu.org/licenses/lgpl.txt
[39]: https://maven.apache.org/plugins/maven-compiler-plugin/
[40]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[41]: https://www.mojohaus.org/flatten-maven-plugin/
[42]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[43]: http://github.com/davidB/scala-maven-plugin
[44]: http://unlicense.org/
[45]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[46]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[47]: https://maven.apache.org/surefire/maven-surefire-plugin/
[48]: https://www.mojohaus.org/versions/versions-maven-plugin/
[49]: https://basepom.github.io/duplicate-finder-maven-plugin
[50]: https://maven.apache.org/plugins/maven-assembly-plugin/
[51]: https://maven.apache.org/plugins/maven-jar-plugin/
[52]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[53]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[54]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[55]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[56]: https://www.eclipse.org/legal/epl-2.0/
[57]: https://github.com/exasol/error-code-crawler-maven-plugin/
[58]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[59]: http://zlika.github.io/reproducible-build-maven-plugin
[60]: https://github.com/exasol/project-keeper/
[61]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[62]: https://github.com/itsallcode/openfasttrace-maven-plugin
[63]: https://www.gnu.org/licenses/gpl-3.0.html
[64]: http://www.scalastyle.org
[65]: https://github.com/diffplug/spotless
[66]: https://github.com/evis/scalafix-maven-plugin
[67]: https://opensource.org/licenses/BSD-3-Clause
[68]: https://www.mojohaus.org/exec-maven-plugin
