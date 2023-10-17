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

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][12]                            | [the Apache License, ASL Version 2.0][13] |
| [scalatestplus-mockito][14]                | [Apache-2.0][13]                          |
| [mockito-core][15]                         | [MIT][16]                                 |
| [Test containers for Exasol on Docker][17] | [MIT License][18]                         |
| [Test Database Builder for Java][19]       | [MIT License][20]                         |
| [Matcher for SQL Result Sets][21]          | [MIT License][22]                         |
| [Extension integration tests library][23]  | [MIT License][24]                         |
| [embedded-kafka-schema-registry][25]       | [MIT][26]                                 |
| [kafka-streams-avro-serde][27]             | [Apache License 2.0][7]                   |
| [avro4s-core][28]                          | [MIT][26]                                 |
| [Netty/Handler][29]                        | [Apache License, Version 2.0][1]          |
| [Testcontainers :: Kafka][30]              | [MIT][31]                                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][32]                       | [GNU LGPL 3][33]                               |
| [Apache Maven Compiler Plugin][34]                      | [Apache-2.0][35]                               |
| [Apache Maven Enforcer Plugin][36]                      | [Apache-2.0][35]                               |
| [Maven Flatten Plugin][37]                              | [Apache Software Licenese][35]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][38] | [ASL2][10]                                     |
| [scala-maven-plugin][39]                                | [Public domain (Unlicense)][40]                |
| [ScalaTest Maven Plugin][41]                            | [the Apache License, ASL Version 2.0][13]      |
| [Apache Maven Javadoc Plugin][42]                       | [Apache-2.0][35]                               |
| [Maven Surefire Plugin][43]                             | [Apache-2.0][35]                               |
| [Versions Maven Plugin][44]                             | [Apache License, Version 2.0][35]              |
| [duplicate-finder-maven-plugin Maven Mojo][45]          | [Apache License 2.0][7]                        |
| [Apache Maven Assembly Plugin][46]                      | [Apache-2.0][35]                               |
| [Apache Maven JAR Plugin][47]                           | [Apache License, Version 2.0][35]              |
| [Artifact reference checker and unifier][48]            | [MIT License][49]                              |
| [Maven Failsafe Plugin][50]                             | [Apache-2.0][35]                               |
| [JaCoCo :: Maven Plugin][51]                            | [Eclipse Public License 2.0][52]               |
| [error-code-crawler-maven-plugin][53]                   | [MIT License][54]                              |
| [Reproducible Build Maven Plugin][55]                   | [Apache 2.0][10]                               |
| [Project keeper maven plugin][56]                       | [The MIT License][57]                          |
| [OpenFastTrace Maven Plugin][58]                        | [GNU General Public License v3.0][59]          |
| [Scalastyle Maven Plugin][60]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][61]                             | [The Apache Software License, Version 2.0][35] |
| [scalafix-maven-plugin][62]                             | [BSD-3-Clause][63]                             |
| [Exec Maven Plugin][64]                                 | [Apache License 2][35]                         |
| [Maven Clean Plugin][65]                                | [The Apache Software License, Version 2.0][10] |
| [Maven Resources Plugin][66]                            | [The Apache Software License, Version 2.0][10] |
| [Maven Install Plugin][67]                              | [The Apache Software License, Version 2.0][10] |
| [Maven Deploy Plugin][68]                               | [The Apache Software License, Version 2.0][10] |
| [Maven Site Plugin 3][69]                               | [The Apache Software License, Version 2.0][10] |

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
[27]: http://confluent.io/kafka-streams-avro-serde
[28]: https://github.com/sksamuel/avro4s
[29]: https://netty.io/netty-handler/
[30]: https://java.testcontainers.org
[31]: http://opensource.org/licenses/MIT
[32]: http://sonarsource.github.io/sonar-scanner-maven/
[33]: http://www.gnu.org/licenses/lgpl.txt
[34]: https://maven.apache.org/plugins/maven-compiler-plugin/
[35]: https://www.apache.org/licenses/LICENSE-2.0.txt
[36]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[37]: https://www.mojohaus.org/flatten-maven-plugin/
[38]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[39]: http://github.com/davidB/scala-maven-plugin
[40]: http://unlicense.org/
[41]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[42]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[43]: https://maven.apache.org/surefire/maven-surefire-plugin/
[44]: https://www.mojohaus.org/versions/versions-maven-plugin/
[45]: https://basepom.github.io/duplicate-finder-maven-plugin
[46]: https://maven.apache.org/plugins/maven-assembly-plugin/
[47]: https://maven.apache.org/plugins/maven-jar-plugin/
[48]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[49]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[50]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[51]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[52]: https://www.eclipse.org/legal/epl-2.0/
[53]: https://github.com/exasol/error-code-crawler-maven-plugin/
[54]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[55]: http://zlika.github.io/reproducible-build-maven-plugin
[56]: https://github.com/exasol/project-keeper/
[57]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[58]: https://github.com/itsallcode/openfasttrace-maven-plugin
[59]: https://www.gnu.org/licenses/gpl-3.0.html
[60]: http://www.scalastyle.org
[61]: https://github.com/diffplug/spotless
[62]: https://github.com/evis/scalafix-maven-plugin
[63]: https://opensource.org/licenses/BSD-3-Clause
[64]: https://www.mojohaus.org/exec-maven-plugin
[65]: http://maven.apache.org/plugins/maven-clean-plugin/
[66]: http://maven.apache.org/plugins/maven-resources-plugin/
[67]: http://maven.apache.org/plugins/maven-install-plugin/
[68]: http://maven.apache.org/plugins/maven-deploy-plugin/
[69]: http://maven.apache.org/plugins/maven-site-plugin/
