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
| [Logback Classic Module][13]        | [EPL-2.0][14]; [LGPL-2.1-only][15]                                                      |
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
| [JUnit][28]                                | [Eclipse Public License 1.0][29]          |
| [Test Database Builder for Java][30]       | [MIT License][31]                         |
| [Matcher for SQL Result Sets][32]          | [MIT License][33]                         |
| [Extension integration tests library][34]  | [MIT License][35]                         |
| [embedded-kafka-schema-registry][36]       | [MIT][24]                                 |
| [kafka-streams-avro-serde][37]             | [Apache License 2.0][9]                   |
| [avro4s-core][38]                          | [MIT][24]                                 |
| [Testcontainers :: Kafka][39]              | [MIT][40]                                 |
| [Maven Project Version Getter][41]         | [MIT License][42]                         |
| [Apache MINA Core][43]                     | [Apache 2.0 License][17]                  |
| [Apache Kafka][6]                          | [The Apache License, Version 2.0][7]      |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][44]                       | [GNU LGPL 3][45]                               |
| [Apache Maven Toolchains Plugin][46]                    | [Apache-2.0][17]                               |
| [Apache Maven Compiler Plugin][47]                      | [Apache-2.0][17]                               |
| [Apache Maven Enforcer Plugin][48]                      | [Apache-2.0][17]                               |
| [Maven Flatten Plugin][49]                              | [Apache Software License][17]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][50] | [ASL2][7]                                      |
| [scala-maven-plugin][51]                                | [Public domain (Unlicense)][52]                |
| [ScalaTest Maven Plugin][53]                            | [the Apache License, ASL Version 2.0][19]      |
| [Maven Surefire Plugin][54]                             | [Apache-2.0][17]                               |
| [Versions Maven Plugin][55]                             | [Apache License, Version 2.0][17]              |
| [duplicate-finder-maven-plugin Maven Mojo][56]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][57]                      | [Apache-2.0][17]                               |
| [Apache Maven Assembly Plugin][58]                      | [Apache-2.0][17]                               |
| [Apache Maven JAR Plugin][59]                           | [Apache-2.0][17]                               |
| [Project Keeper Maven plugin][60]                       | [The MIT License][61]                          |
| [OpenFastTrace Maven Plugin][62]                        | [GNU General Public License v3.0][63]          |
| [Scalastyle Maven Plugin][64]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][65]                             | [The Apache Software License, Version 2.0][17] |
| [scalafix-maven-plugin][66]                             | [BSD-3-Clause][67]                             |
| [Exec Maven Plugin][68]                                 | [Apache License 2][17]                         |
| [Artifact reference checker and unifier][69]            | [MIT License][70]                              |
| [Maven Failsafe Plugin][71]                             | [Apache-2.0][17]                               |
| [JaCoCo :: Maven Plugin][72]                            | [EPL-2.0][73]                                  |
| [Quality Summarizer Maven Plugin][74]                   | [MIT License][75]                              |
| [error-code-crawler-maven-plugin][76]                   | [MIT License][77]                              |
| [Git Commit Id Maven Plugin][78]                        | [GNU Lesser General Public License 3.0][79]    |
| [Apache Maven Clean Plugin][80]                         | [Apache-2.0][17]                               |
| [Apache Maven Resources Plugin][81]                     | [Apache-2.0][17]                               |
| [Apache Maven Install Plugin][82]                       | [Apache-2.0][17]                               |
| [Apache Maven Site Plugin][83]                          | [Apache-2.0][17]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][84] | MIT     |

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
[14]: https://www.eclipse.org/legal/epl-v20.html
[15]: https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
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
[29]: http://www.eclipse.org/legal/epl-v10.html
[30]: https://github.com/exasol/test-db-builder-java/
[31]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[32]: https://github.com/exasol/hamcrest-resultset-matcher/
[33]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[34]: https://github.com/exasol/extension-manager/
[35]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[36]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[37]: http://confluent.io/kafka-streams-avro-serde
[38]: https://github.com/sksamuel/avro4s
[39]: https://java.testcontainers.org
[40]: http://opensource.org/licenses/MIT
[41]: https://github.com/exasol/maven-project-version-getter/
[42]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[43]: https://mina.apache.org/mina-core/
[44]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[45]: http://www.gnu.org/licenses/lgpl.txt
[46]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[47]: https://maven.apache.org/plugins/maven-compiler-plugin/
[48]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[49]: https://www.mojohaus.org/flatten-maven-plugin/
[50]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[51]: https://github.com/davidB/scala-maven-plugin
[52]: https://unlicense.org/
[53]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[54]: https://maven.apache.org/surefire/maven-surefire-plugin/
[55]: https://www.mojohaus.org/versions/versions-maven-plugin/
[56]: https://basepom.github.io/duplicate-finder-maven-plugin
[57]: https://maven.apache.org/plugins/maven-artifact-plugin/
[58]: https://maven.apache.org/plugins/maven-assembly-plugin/
[59]: https://maven.apache.org/plugins/maven-jar-plugin/
[60]: https://github.com/exasol/project-keeper/
[61]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[62]: https://github.com/itsallcode/openfasttrace-maven-plugin
[63]: https://www.gnu.org/licenses/gpl-3.0.html
[64]: http://www.scalastyle.org
[65]: https://github.com/diffplug/spotless
[66]: https://github.com/evis/scalafix-maven-plugin
[67]: https://opensource.org/licenses/BSD-3-Clause
[68]: https://www.mojohaus.org/exec-maven-plugin
[69]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[70]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[71]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[72]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[73]: https://www.eclipse.org/legal/epl-2.0/
[74]: https://github.com/exasol/quality-summarizer-maven-plugin/
[75]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[76]: https://github.com/exasol/error-code-crawler-maven-plugin/
[77]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[78]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[79]: http://www.gnu.org/licenses/lgpl-3.0.txt
[80]: https://maven.apache.org/plugins/maven-clean-plugin/
[81]: https://maven.apache.org/plugins/maven-resources-plugin/
[82]: https://maven.apache.org/plugins/maven-install-plugin/
[83]: https://maven.apache.org/plugins/maven-site-plugin/
[84]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
