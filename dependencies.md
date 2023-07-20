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

## Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][11]                            | [the Apache License, ASL Version 2.0][12] |
| [scalatestplus-mockito][13]                | [Apache-2.0][12]                          |
| [mockito-core][14]                         | [The MIT License][15]                     |
| [Test containers for Exasol on Docker][16] | [MIT License][17]                         |
| [Test Database Builder for Java][18]       | [MIT License][19]                         |
| [Matcher for SQL Result Sets][20]          | [MIT License][21]                         |
| [embedded-kafka-schema-registry][22]       | [MIT][23]                                 |
| [kafka-streams-avro-serde][24]             | [Apache License 2.0][7]                   |
| [avro4s-core][25]                          | [MIT][23]                                 |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][26]                       | [GNU LGPL 3][27]                               |
| [Apache Maven Compiler Plugin][28]                      | [Apache-2.0][29]                               |
| [Apache Maven Enforcer Plugin][30]                      | [Apache-2.0][29]                               |
| [Maven Flatten Plugin][31]                              | [Apache Software Licenese][29]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][32] | [ASL2][10]                                     |
| [scala-maven-plugin][33]                                | [Public domain (Unlicense)][34]                |
| [ScalaTest Maven Plugin][35]                            | [the Apache License, ASL Version 2.0][12]      |
| [Apache Maven Javadoc Plugin][36]                       | [Apache-2.0][29]                               |
| [Maven Surefire Plugin][37]                             | [Apache-2.0][29]                               |
| [Versions Maven Plugin][38]                             | [Apache License, Version 2.0][29]              |
| [duplicate-finder-maven-plugin Maven Mojo][39]          | [Apache License 2.0][7]                        |
| [Apache Maven Assembly Plugin][40]                      | [Apache-2.0][29]                               |
| [Apache Maven JAR Plugin][41]                           | [Apache License, Version 2.0][29]              |
| [Artifact reference checker and unifier][42]            | [MIT License][43]                              |
| [Maven Failsafe Plugin][44]                             | [Apache-2.0][29]                               |
| [JaCoCo :: Maven Plugin][45]                            | [Eclipse Public License 2.0][46]               |
| [error-code-crawler-maven-plugin][47]                   | [MIT License][48]                              |
| [Reproducible Build Maven Plugin][49]                   | [Apache 2.0][10]                               |
| [Project keeper maven plugin][50]                       | [The MIT License][51]                          |
| [OpenFastTrace Maven Plugin][52]                        | [GNU General Public License v3.0][53]          |
| [Scalastyle Maven Plugin][54]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][55]                             | [The Apache Software License, Version 2.0][29] |
| [scalafix-maven-plugin][56]                             | [BSD-3-Clause][57]                             |
| [Maven Clean Plugin][58]                                | [The Apache Software License, Version 2.0][10] |
| [Maven Resources Plugin][59]                            | [The Apache Software License, Version 2.0][10] |
| [Maven Install Plugin][60]                              | [The Apache Software License, Version 2.0][10] |
| [Maven Deploy Plugin][61]                               | [The Apache Software License, Version 2.0][10] |
| [Maven Site Plugin 3][62]                               | [The Apache Software License, Version 2.0][10] |

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
[11]: http://www.scalatest.org
[12]: http://www.apache.org/licenses/LICENSE-2.0
[13]: https://github.com/scalatest/scalatestplus-mockito
[14]: https://github.com/mockito/mockito
[15]: https://github.com/mockito/mockito/blob/main/LICENSE
[16]: https://github.com/exasol/exasol-testcontainers/
[17]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[18]: https://github.com/exasol/test-db-builder-java/
[19]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[20]: https://github.com/exasol/hamcrest-resultset-matcher/
[21]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[22]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[23]: https://opensource.org/licenses/MIT
[24]: http://confluent.io/kafka-streams-avro-serde
[25]: https://github.com/sksamuel/avro4s
[26]: http://sonarsource.github.io/sonar-scanner-maven/
[27]: http://www.gnu.org/licenses/lgpl.txt
[28]: https://maven.apache.org/plugins/maven-compiler-plugin/
[29]: https://www.apache.org/licenses/LICENSE-2.0.txt
[30]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[31]: https://www.mojohaus.org/flatten-maven-plugin/
[32]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[33]: http://github.com/davidB/scala-maven-plugin
[34]: http://unlicense.org/
[35]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[36]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[37]: https://maven.apache.org/surefire/maven-surefire-plugin/
[38]: https://www.mojohaus.org/versions/versions-maven-plugin/
[39]: https://basepom.github.io/duplicate-finder-maven-plugin
[40]: https://maven.apache.org/plugins/maven-assembly-plugin/
[41]: https://maven.apache.org/plugins/maven-jar-plugin/
[42]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[43]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[44]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[45]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[46]: https://www.eclipse.org/legal/epl-2.0/
[47]: https://github.com/exasol/error-code-crawler-maven-plugin/
[48]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[49]: http://zlika.github.io/reproducible-build-maven-plugin
[50]: https://github.com/exasol/project-keeper/
[51]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[52]: https://github.com/itsallcode/openfasttrace-maven-plugin
[53]: https://www.gnu.org/licenses/gpl-3.0.html
[54]: http://www.scalastyle.org
[55]: https://github.com/diffplug/spotless
[56]: https://github.com/evis/scalafix-maven-plugin
[57]: https://opensource.org/licenses/BSD-3-Clause
[58]: http://maven.apache.org/plugins/maven-clean-plugin/
[59]: http://maven.apache.org/plugins/maven-resources-plugin/
[60]: http://maven.apache.org/plugins/maven-install-plugin/
[61]: http://maven.apache.org/plugins/maven-deploy-plugin/
[62]: http://maven.apache.org/plugins/maven-site-plugin/
