<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                          | License                                                                                 |
| ----------------------------------- | --------------------------------------------------------------------------------------- |
| [Scala Library][0]                  | [Apache-2.0][1]                                                                         |
| [Import Export UDF Common Scala][2] | [MIT License][3]                                                                        |
| [error-reporting-java][4]           | [MIT License][5]                                                                        |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7]                                                    |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]                                                                 |
| [scala-collection-compat][10]       | [Apache-2.0][1]                                                                         |
| [SLF4J API Module][11]              | [MIT][12]                                                                               |
| [Logback Classic Module][13]        | [Eclipse Public License - v 1.0][14]; [GNU Lesser General Public License][15]           |
| [Gson][16]                          | [Apache-2.0][17]                                                                        |
| [Jetty :: HTTP2 :: Common][18]      | [Apache Software License - Version 2.0][19]; [Eclipse Public License - Version 1.0][20] |

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][21]                            | [the Apache License, ASL Version 2.0][19] |
| [scalatestplus-mockito][22]                | [Apache-2.0][19]                          |
| [mockito-core][23]                         | [MIT][24]                                 |
| [Test containers for Exasol on Docker][25] | [MIT License][26]                         |
| [Test Database Builder for Java][27]       | [MIT License][28]                         |
| [Matcher for SQL Result Sets][29]          | [MIT License][30]                         |
| [Extension integration tests library][31]  | [MIT License][32]                         |
| [embedded-kafka-schema-registry][33]       | [MIT][24]                                 |
| [kafka-streams-avro-serde][34]             | [Apache License 2.0][9]                   |
| [avro4s-core][35]                          | [MIT][24]                                 |
| [Testcontainers :: Kafka][36]              | [MIT][37]                                 |
| [Maven Project Version Getter][38]         | [MIT License][39]                         |
| [Apache MINA Core][40]                     | [Apache 2.0 License][17]                  |
| [Apache Kafka][6]                          | [The Apache License, Version 2.0][7]      |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][41]                       | [GNU LGPL 3][42]                               |
| [Apache Maven Toolchains Plugin][43]                    | [Apache-2.0][17]                               |
| [Apache Maven Compiler Plugin][44]                      | [Apache-2.0][17]                               |
| [Apache Maven Enforcer Plugin][45]                      | [Apache-2.0][17]                               |
| [Maven Flatten Plugin][46]                              | [Apache Software License][17]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][47] | [ASL2][7]                                      |
| [scala-maven-plugin][48]                                | [Public domain (Unlicense)][49]                |
| [ScalaTest Maven Plugin][50]                            | [the Apache License, ASL Version 2.0][19]      |
| [Apache Maven Javadoc Plugin][51]                       | [Apache-2.0][17]                               |
| [Maven Surefire Plugin][52]                             | [Apache-2.0][17]                               |
| [Versions Maven Plugin][53]                             | [Apache License, Version 2.0][17]              |
| [duplicate-finder-maven-plugin Maven Mojo][54]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][55]                      | [Apache-2.0][17]                               |
| [Apache Maven Assembly Plugin][56]                      | [Apache-2.0][17]                               |
| [Apache Maven JAR Plugin][57]                           | [Apache-2.0][17]                               |
| [Project Keeper Maven plugin][58]                       | [The MIT License][59]                          |
| [OpenFastTrace Maven Plugin][60]                        | [GNU General Public License v3.0][61]          |
| [Scalastyle Maven Plugin][62]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][63]                             | [The Apache Software License, Version 2.0][17] |
| [scalafix-maven-plugin][64]                             | [BSD-3-Clause][65]                             |
| [Exec Maven Plugin][66]                                 | [Apache License 2][17]                         |
| [Artifact reference checker and unifier][67]            | [MIT License][68]                              |
| [Maven Failsafe Plugin][69]                             | [Apache-2.0][17]                               |
| [JaCoCo :: Maven Plugin][70]                            | [EPL-2.0][71]                                  |
| [Quality Summarizer Maven Plugin][72]                   | [MIT License][73]                              |
| [error-code-crawler-maven-plugin][74]                   | [MIT License][75]                              |
| [Git Commit Id Maven Plugin][76]                        | [GNU Lesser General Public License 3.0][77]    |
| [Apache Maven Clean Plugin][78]                         | [Apache-2.0][17]                               |
| [Apache Maven Resources Plugin][79]                     | [Apache-2.0][17]                               |
| [Apache Maven Install Plugin][80]                       | [Apache-2.0][17]                               |
| [Apache Maven Site Plugin][81]                          | [Apache-2.0][17]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][82] | MIT     |

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
[18]: https://jetty.org/http2-parent/http2-common/
[19]: http://www.apache.org/licenses/LICENSE-2.0
[20]: https://www.eclipse.org/org/documents/epl-v10.php
[21]: http://www.scalatest.org
[22]: https://github.com/scalatest/scalatestplus-mockito
[23]: https://github.com/mockito/mockito
[24]: https://opensource.org/licenses/MIT
[25]: https://github.com/exasol/exasol-testcontainers/
[26]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[27]: https://github.com/exasol/test-db-builder-java/
[28]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[29]: https://github.com/exasol/hamcrest-resultset-matcher/
[30]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[31]: https://github.com/exasol/extension-manager/
[32]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[33]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[34]: http://confluent.io/kafka-streams-avro-serde
[35]: https://github.com/sksamuel/avro4s
[36]: https://java.testcontainers.org
[37]: http://opensource.org/licenses/MIT
[38]: https://github.com/exasol/maven-project-version-getter/
[39]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[40]: https://mina.apache.org/mina-core/
[41]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[42]: http://www.gnu.org/licenses/lgpl.txt
[43]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[44]: https://maven.apache.org/plugins/maven-compiler-plugin/
[45]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[46]: https://www.mojohaus.org/flatten-maven-plugin/
[47]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[48]: https://github.com/davidB/scala-maven-plugin
[49]: https://unlicense.org/
[50]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[51]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[52]: https://maven.apache.org/surefire/maven-surefire-plugin/
[53]: https://www.mojohaus.org/versions/versions-maven-plugin/
[54]: https://basepom.github.io/duplicate-finder-maven-plugin
[55]: https://maven.apache.org/plugins/maven-artifact-plugin/
[56]: https://maven.apache.org/plugins/maven-assembly-plugin/
[57]: https://maven.apache.org/plugins/maven-jar-plugin/
[58]: https://github.com/exasol/project-keeper/
[59]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[60]: https://github.com/itsallcode/openfasttrace-maven-plugin
[61]: https://www.gnu.org/licenses/gpl-3.0.html
[62]: http://www.scalastyle.org
[63]: https://github.com/diffplug/spotless
[64]: https://github.com/evis/scalafix-maven-plugin
[65]: https://opensource.org/licenses/BSD-3-Clause
[66]: https://www.mojohaus.org/exec-maven-plugin
[67]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[68]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[69]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[70]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[71]: https://www.eclipse.org/legal/epl-2.0/
[72]: https://github.com/exasol/quality-summarizer-maven-plugin/
[73]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[74]: https://github.com/exasol/error-code-crawler-maven-plugin/
[75]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[76]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[77]: http://www.gnu.org/licenses/lgpl-3.0.txt
[78]: https://maven.apache.org/plugins/maven-clean-plugin/
[79]: https://maven.apache.org/plugins/maven-resources-plugin/
[80]: https://maven.apache.org/plugins/maven-install-plugin/
[81]: https://maven.apache.org/plugins/maven-site-plugin/
[82]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
