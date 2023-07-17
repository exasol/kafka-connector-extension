<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                                  | License                              |
| ------------------------------------------- | ------------------------------------ |
| [Scala Library][0]                          | [Apache-2.0][1]                      |
| [Import Export UDF Common Scala][2]         | [MIT License][3]                     |
| [error-reporting-java][4]                   | [MIT License][5]                     |
| [Apache Kafka][6]                           | [The Apache License, Version 2.0][7] |
| [kafka-avro-serializer][8]                  | [Apache License 2.0][9]              |
| [scala-collection-compat][10]               | [Apache-2.0][1]                      |
| [Guava: Google Core Libraries for Java][11] | [Apache License, Version 2.0][7]     |

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][12]                            | [the Apache License, ASL Version 2.0][13] |
| [scalatestplus-mockito][14]                | [Apache-2.0][13]                          |
| [mockito-core][15]                         | [The MIT License][16]                     |
| [Test containers for Exasol on Docker][17] | [MIT License][18]                         |
| [Test Database Builder for Java][19]       | [MIT License][20]                         |
| [Matcher for SQL Result Sets][21]          | [MIT License][22]                         |
| [embedded-kafka-schema-registry][23]       | [MIT][24]                                 |
| [kafka-streams-avro-serde][25]             | [Apache License 2.0][9]                   |
| [avro4s-core][26]                          | [MIT][24]                                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][27]                       | [GNU LGPL 3][28]                               |
| [Apache Maven Compiler Plugin][29]                      | [Apache-2.0][30]                               |
| [Apache Maven Enforcer Plugin][31]                      | [Apache-2.0][30]                               |
| [Maven Flatten Plugin][32]                              | [Apache Software Licenese][30]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][33] | [ASL2][7]                                      |
| [scala-maven-plugin][34]                                | [Public domain (Unlicense)][35]                |
| [ScalaTest Maven Plugin][36]                            | [the Apache License, ASL Version 2.0][13]      |
| [Apache Maven Javadoc Plugin][37]                       | [Apache-2.0][30]                               |
| [Maven Surefire Plugin][38]                             | [Apache-2.0][30]                               |
| [Versions Maven Plugin][39]                             | [Apache License, Version 2.0][30]              |
| [duplicate-finder-maven-plugin Maven Mojo][40]          | [Apache License 2.0][9]                        |
| [Apache Maven Assembly Plugin][41]                      | [Apache-2.0][30]                               |
| [Apache Maven JAR Plugin][42]                           | [Apache License, Version 2.0][30]              |
| [Artifact reference checker and unifier][43]            | [MIT License][44]                              |
| [Maven Failsafe Plugin][45]                             | [Apache-2.0][30]                               |
| [JaCoCo :: Maven Plugin][46]                            | [Eclipse Public License 2.0][47]               |
| [error-code-crawler-maven-plugin][48]                   | [MIT License][49]                              |
| [Reproducible Build Maven Plugin][50]                   | [Apache 2.0][7]                                |
| [Project keeper maven plugin][51]                       | [The MIT License][52]                          |
| [OpenFastTrace Maven Plugin][53]                        | [GNU General Public License v3.0][54]          |
| [Scalastyle Maven Plugin][55]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][56]                             | [The Apache Software License, Version 2.0][30] |
| [scalafix-maven-plugin][57]                             | [BSD-3-Clause][58]                             |
| [Maven Clean Plugin][59]                                | [The Apache Software License, Version 2.0][7]  |
| [Maven Resources Plugin][60]                            | [The Apache Software License, Version 2.0][7]  |
| [Maven Install Plugin][61]                              | [The Apache Software License, Version 2.0][7]  |
| [Maven Deploy Plugin][62]                               | [The Apache Software License, Version 2.0][7]  |
| [Maven Site Plugin 3][63]                               | [The Apache Software License, Version 2.0][7]  |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: https://kafka.apache.org
[7]: http://www.apache.org/licenses/LICENSE-2.0.txt
[8]: http://confluent.io/kafka-avro-serializer
[9]: http://www.apache.org/licenses/LICENSE-2.0.html
[10]: http://www.scala-lang.org/
[11]: https://github.com/google/guava
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
[23]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[24]: https://opensource.org/licenses/MIT
[25]: http://confluent.io/kafka-streams-avro-serde
[26]: https://github.com/sksamuel/avro4s
[27]: http://sonarsource.github.io/sonar-scanner-maven/
[28]: http://www.gnu.org/licenses/lgpl.txt
[29]: https://maven.apache.org/plugins/maven-compiler-plugin/
[30]: https://www.apache.org/licenses/LICENSE-2.0.txt
[31]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[32]: https://www.mojohaus.org/flatten-maven-plugin/
[33]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[34]: http://github.com/davidB/scala-maven-plugin
[35]: http://unlicense.org/
[36]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[37]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[38]: https://maven.apache.org/surefire/maven-surefire-plugin/
[39]: https://www.mojohaus.org/versions/versions-maven-plugin/
[40]: https://basepom.github.io/duplicate-finder-maven-plugin
[41]: https://maven.apache.org/plugins/maven-assembly-plugin/
[42]: https://maven.apache.org/plugins/maven-jar-plugin/
[43]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[44]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[45]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[46]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[47]: https://www.eclipse.org/legal/epl-2.0/
[48]: https://github.com/exasol/error-code-crawler-maven-plugin/
[49]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[50]: http://zlika.github.io/reproducible-build-maven-plugin
[51]: https://github.com/exasol/project-keeper/
[52]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[53]: https://github.com/itsallcode/openfasttrace-maven-plugin
[54]: https://www.gnu.org/licenses/gpl-3.0.html
[55]: http://www.scalastyle.org
[56]: https://github.com/diffplug/spotless
[57]: https://github.com/evis/scalafix-maven-plugin
[58]: https://opensource.org/licenses/BSD-3-Clause
[59]: http://maven.apache.org/plugins/maven-clean-plugin/
[60]: http://maven.apache.org/plugins/maven-resources-plugin/
[61]: http://maven.apache.org/plugins/maven-install-plugin/
[62]: http://maven.apache.org/plugins/maven-deploy-plugin/
[63]: http://maven.apache.org/plugins/maven-site-plugin/
