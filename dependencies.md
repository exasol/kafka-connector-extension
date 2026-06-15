<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                          | License                                                                       |
| ----------------------------------- | ----------------------------------------------------------------------------- |
| [Scala Library][0]                  | [Apache-2.0][1]                                                               |
| [Import Export UDF Common Scala][2] | [MIT License][3]                                                              |
| [error-reporting-java][4]           | [MIT License][5]                                                              |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7]                                          |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]                                                       |
| [scala-collection-compat][10]       | [Apache-2.0][1]                                                               |
| [SLF4J API Module][11]              | [MIT][12]                                                                     |
| [Logback Classic Module][13]        | [Eclipse Public License - v 1.0][14]; [GNU Lesser General Public License][15] |
| [Gson][16]                          | [Apache-2.0][17]                                                              |

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][18]                            | [the Apache License, ASL Version 2.0][19] |
| [scalatestplus-mockito][20]                | [Apache-2.0][19]                          |
| [mockito-core][21]                         | [MIT][22]                                 |
| [Test containers for Exasol on Docker][23] | [MIT License][24]                         |
| [Test Database Builder for Java][25]       | [MIT License][26]                         |
| [Matcher for SQL Result Sets][27]          | [MIT License][28]                         |
| [Extension integration tests library][29]  | [MIT License][30]                         |
| [embedded-kafka][31]                       | [MIT][22]                                 |
| [embedded-kafka-schema-registry][32]       | [MIT][22]                                 |
| [kafka-streams-avro-serde][33]             | [Apache License 2.0][9]                   |
| [avro4s-core][34]                          | [MIT][22]                                 |
| [Testcontainers :: Kafka][35]              | [MIT][36]                                 |
| [Maven Project Version Getter][37]         | [MIT License][38]                         |
| [Apache MINA Core][39]                     | [Apache 2.0 License][17]                  |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][40]                       | [GNU LGPL 3][41]                               |
| [Apache Maven Toolchains Plugin][42]                    | [Apache-2.0][17]                               |
| [Apache Maven Compiler Plugin][43]                      | [Apache-2.0][17]                               |
| [Apache Maven Enforcer Plugin][44]                      | [Apache-2.0][17]                               |
| [Maven Flatten Plugin][45]                              | [Apache Software License][17]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][46] | [ASL2][7]                                      |
| [scala-maven-plugin][47]                                | [Public domain (Unlicense)][48]                |
| [ScalaTest Maven Plugin][49]                            | [the Apache License, ASL Version 2.0][19]      |
| [Apache Maven Javadoc Plugin][50]                       | [Apache-2.0][17]                               |
| [Maven Surefire Plugin][51]                             | [Apache-2.0][17]                               |
| [Versions Maven Plugin][52]                             | [Apache License, Version 2.0][17]              |
| [duplicate-finder-maven-plugin Maven Mojo][53]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][54]                      | [Apache-2.0][17]                               |
| [Apache Maven Assembly Plugin][55]                      | [Apache-2.0][17]                               |
| [Apache Maven JAR Plugin][56]                           | [Apache-2.0][17]                               |
| [Project Keeper Maven plugin][57]                       | [The MIT License][58]                          |
| [OpenFastTrace Maven Plugin][59]                        | [GNU General Public License v3.0][60]          |
| [Scalastyle Maven Plugin][61]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][62]                             | [The Apache Software License, Version 2.0][17] |
| [scalafix-maven-plugin][63]                             | [BSD-3-Clause][64]                             |
| [Exec Maven Plugin][65]                                 | [Apache License 2][17]                         |
| [Artifact reference checker and unifier][66]            | [MIT License][67]                              |
| [Maven Failsafe Plugin][68]                             | [Apache-2.0][17]                               |
| [JaCoCo :: Maven Plugin][69]                            | [EPL-2.0][70]                                  |
| [Quality Summarizer Maven Plugin][71]                   | [MIT License][72]                              |
| [error-code-crawler-maven-plugin][73]                   | [MIT License][74]                              |
| [Git Commit Id Maven Plugin][75]                        | [GNU Lesser General Public License 3.0][76]    |
| [Apache Maven Clean Plugin][77]                         | [Apache-2.0][17]                               |
| [Apache Maven Resources Plugin][78]                     | [Apache-2.0][17]                               |
| [Apache Maven Install Plugin][79]                       | [Apache-2.0][17]                               |
| [Apache Maven Site Plugin][80]                          | [Apache-2.0][17]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][81] | MIT     |

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
[11]: http://www.slf4j.org
[12]: https://opensource.org/license/mit
[13]: http://logback.qos.ch/logback-classic
[14]: http://www.eclipse.org/legal/epl-v10.html
[15]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[16]: https://github.com/google/gson
[17]: https://www.apache.org/licenses/LICENSE-2.0.txt
[18]: http://www.scalatest.org
[19]: http://www.apache.org/licenses/LICENSE-2.0
[20]: https://github.com/scalatest/scalatestplus-mockito
[21]: https://github.com/mockito/mockito
[22]: https://opensource.org/licenses/MIT
[23]: https://github.com/exasol/exasol-testcontainers/
[24]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[25]: https://github.com/exasol/test-db-builder-java/
[26]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[27]: https://github.com/exasol/hamcrest-resultset-matcher/
[28]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[29]: https://github.com/exasol/extension-manager/
[30]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[31]: https://github.com/embeddedkafka/embedded-kafka
[32]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[33]: http://confluent.io/kafka-streams-avro-serde
[34]: https://github.com/sksamuel/avro4s
[35]: https://java.testcontainers.org
[36]: http://opensource.org/licenses/MIT
[37]: https://github.com/exasol/maven-project-version-getter/
[38]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[39]: https://mina.apache.org/mina-core/
[40]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[41]: http://www.gnu.org/licenses/lgpl.txt
[42]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[43]: https://maven.apache.org/plugins/maven-compiler-plugin/
[44]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[45]: https://www.mojohaus.org/flatten-maven-plugin/
[46]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[47]: https://github.com/davidB/scala-maven-plugin
[48]: https://unlicense.org/
[49]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[50]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[51]: https://maven.apache.org/surefire/maven-surefire-plugin/
[52]: https://www.mojohaus.org/versions/versions-maven-plugin/
[53]: https://basepom.github.io/duplicate-finder-maven-plugin
[54]: https://maven.apache.org/plugins/maven-artifact-plugin/
[55]: https://maven.apache.org/plugins/maven-assembly-plugin/
[56]: https://maven.apache.org/plugins/maven-jar-plugin/
[57]: https://github.com/exasol/project-keeper/
[58]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[59]: https://github.com/itsallcode/openfasttrace-maven-plugin
[60]: https://www.gnu.org/licenses/gpl-3.0.html
[61]: http://www.scalastyle.org
[62]: https://github.com/diffplug/spotless
[63]: https://github.com/evis/scalafix-maven-plugin
[64]: https://opensource.org/licenses/BSD-3-Clause
[65]: https://www.mojohaus.org/exec-maven-plugin
[66]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[67]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[68]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[69]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[70]: https://www.eclipse.org/legal/epl-2.0/
[71]: https://github.com/exasol/quality-summarizer-maven-plugin/
[72]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[73]: https://github.com/exasol/error-code-crawler-maven-plugin/
[74]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[75]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[76]: http://www.gnu.org/licenses/lgpl-3.0.txt
[77]: https://maven.apache.org/plugins/maven-clean-plugin/
[78]: https://maven.apache.org/plugins/maven-resources-plugin/
[79]: https://maven.apache.org/plugins/maven-install-plugin/
[80]: https://maven.apache.org/plugins/maven-site-plugin/
[81]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
