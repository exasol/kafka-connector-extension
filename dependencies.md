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
| [EqualsVerifier \| release normal jar][25] | [Apache License, Version 2.0][17]         |
| [Test containers for Exasol on Docker][26] | [MIT License][27]                         |
| [JUnit][28]                                | [Eclipse Public License 1.0][14]          |
| [Test Database Builder for Java][29]       | [MIT License][30]                         |
| [Matcher for SQL Result Sets][31]          | [MIT License][32]                         |
| [Extension integration tests library][33]  | [MIT License][34]                         |
| [embedded-kafka-schema-registry][35]       | [MIT][24]                                 |
| [kafka-streams-avro-serde][36]             | [Apache License 2.0][9]                   |
| [avro4s-core][37]                          | [MIT][24]                                 |
| [Testcontainers :: Kafka][38]              | [MIT][39]                                 |
| [Maven Project Version Getter][40]         | [MIT License][41]                         |
| [Apache MINA Core][42]                     | [Apache 2.0 License][17]                  |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][43]                       | [GNU LGPL 3][44]                               |
| [Apache Maven Toolchains Plugin][45]                    | [Apache-2.0][17]                               |
| [Apache Maven Compiler Plugin][46]                      | [Apache-2.0][17]                               |
| [Apache Maven Enforcer Plugin][47]                      | [Apache-2.0][17]                               |
| [Maven Flatten Plugin][48]                              | [Apache Software License][17]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][49] | [ASL2][7]                                      |
| [scala-maven-plugin][50]                                | [Public domain (Unlicense)][51]                |
| [ScalaTest Maven Plugin][52]                            | [the Apache License, ASL Version 2.0][19]      |
| [Maven Surefire Plugin][53]                             | [Apache-2.0][17]                               |
| [Versions Maven Plugin][54]                             | [Apache License, Version 2.0][17]              |
| [duplicate-finder-maven-plugin Maven Mojo][55]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][56]                      | [Apache-2.0][17]                               |
| [Apache Maven Assembly Plugin][57]                      | [Apache-2.0][17]                               |
| [Apache Maven JAR Plugin][58]                           | [Apache-2.0][17]                               |
| [Project Keeper Maven plugin][59]                       | [The MIT License][60]                          |
| [OpenFastTrace Maven Plugin][61]                        | [GNU General Public License v3.0][62]          |
| [Scalastyle Maven Plugin][63]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][64]                             | [The Apache Software License, Version 2.0][17] |
| [scalafix-maven-plugin][65]                             | [BSD-3-Clause][66]                             |
| [Exec Maven Plugin][67]                                 | [Apache License 2][17]                         |
| [Artifact reference checker and unifier][68]            | [MIT License][69]                              |
| [Maven Failsafe Plugin][70]                             | [Apache-2.0][17]                               |
| [JaCoCo :: Maven Plugin][71]                            | [EPL-2.0][72]                                  |
| [Quality Summarizer Maven Plugin][73]                   | [MIT License][74]                              |
| [error-code-crawler-maven-plugin][75]                   | [MIT License][76]                              |
| [Git Commit Id Maven Plugin][77]                        | [GNU Lesser General Public License 3.0][78]    |
| [Apache Maven Clean Plugin][79]                         | [Apache-2.0][17]                               |
| [Apache Maven Resources Plugin][80]                     | [Apache-2.0][17]                               |
| [Apache Maven Install Plugin][81]                       | [Apache-2.0][17]                               |
| [Apache Maven Site Plugin][82]                          | [Apache-2.0][17]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][83] | MIT     |

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
[25]: https://www.jqno.nl/equalsverifier
[26]: https://github.com/exasol/exasol-testcontainers/
[27]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[28]: http://junit.org
[29]: https://github.com/exasol/test-db-builder-java/
[30]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[31]: https://github.com/exasol/hamcrest-resultset-matcher/
[32]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[33]: https://github.com/exasol/extension-manager/
[34]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[35]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[36]: http://confluent.io/kafka-streams-avro-serde
[37]: https://github.com/sksamuel/avro4s
[38]: https://java.testcontainers.org
[39]: http://opensource.org/licenses/MIT
[40]: https://github.com/exasol/maven-project-version-getter/
[41]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[42]: https://mina.apache.org/mina-core/
[43]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[44]: http://www.gnu.org/licenses/lgpl.txt
[45]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[46]: https://maven.apache.org/plugins/maven-compiler-plugin/
[47]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[48]: https://www.mojohaus.org/flatten-maven-plugin/
[49]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[50]: https://github.com/davidB/scala-maven-plugin
[51]: https://unlicense.org/
[52]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[53]: https://maven.apache.org/surefire/maven-surefire-plugin/
[54]: https://www.mojohaus.org/versions/versions-maven-plugin/
[55]: https://basepom.github.io/duplicate-finder-maven-plugin
[56]: https://maven.apache.org/plugins/maven-artifact-plugin/
[57]: https://maven.apache.org/plugins/maven-assembly-plugin/
[58]: https://maven.apache.org/plugins/maven-jar-plugin/
[59]: https://github.com/exasol/project-keeper/
[60]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[61]: https://github.com/itsallcode/openfasttrace-maven-plugin
[62]: https://www.gnu.org/licenses/gpl-3.0.html
[63]: http://www.scalastyle.org
[64]: https://github.com/diffplug/spotless
[65]: https://github.com/evis/scalafix-maven-plugin
[66]: https://opensource.org/licenses/BSD-3-Clause
[67]: https://www.mojohaus.org/exec-maven-plugin
[68]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[69]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[70]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[71]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[72]: https://www.eclipse.org/legal/epl-2.0/
[73]: https://github.com/exasol/quality-summarizer-maven-plugin/
[74]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[75]: https://github.com/exasol/error-code-crawler-maven-plugin/
[76]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[77]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[78]: http://www.gnu.org/licenses/lgpl-3.0.txt
[79]: https://maven.apache.org/plugins/maven-clean-plugin/
[80]: https://maven.apache.org/plugins/maven-resources-plugin/
[81]: https://maven.apache.org/plugins/maven-install-plugin/
[82]: https://maven.apache.org/plugins/maven-site-plugin/
[83]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
