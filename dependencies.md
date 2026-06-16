<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                     | License                                                                                 |
| ------------------------------ | --------------------------------------------------------------------------------------- |
| [Scala Library][0]             | [Apache-2.0][1]                                                                         |
| import-export-udf-common-scala |                                                                                         |
| [error-reporting-java][2]      | [MIT License][3]                                                                        |
| [Apache Kafka][4]              | [The Apache License, Version 2.0][5]                                                    |
| [kafka-avro-serializer][6]     | [Apache License 2.0][7]                                                                 |
| [scala-collection-compat][8]   | [Apache-2.0][1]                                                                         |
| [SLF4J API Module][9]          | [MIT][10]                                                                               |
| [Logback Classic Module][11]   | [EPL-2.0][12]; [LGPL-2.1-only][13]                                                      |
| [Gson][14]                     | [Apache-2.0][15]                                                                        |
| [Jetty :: HTTP2 :: Common][16] | [Apache Software License - Version 2.0][17]; [Eclipse Public License - Version 1.0][18] |

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][19]                            | [the Apache License, ASL Version 2.0][17] |
| [scalatestplus-mockito][20]                | [Apache-2.0][17]                          |
| [mockito-core][21]                         | [MIT][22]                                 |
| [EqualsVerifier \| release normal jar][23] | [Apache License, Version 2.0][15]         |
| [Test containers for Exasol on Docker][24] | [MIT License][25]                         |
| [JUnit][26]                                | [Eclipse Public License 1.0][27]          |
| [Test Database Builder for Java][28]       | [MIT License][29]                         |
| [Matcher for SQL Result Sets][30]          | [MIT License][31]                         |
| [Extension integration tests library][32]  | [MIT License][33]                         |
| [embedded-kafka-schema-registry][34]       | [MIT][22]                                 |
| [kafka-streams-avro-serde][35]             | [Apache License 2.0][7]                   |
| [avro4s-core][36]                          | [MIT][22]                                 |
| [Testcontainers :: Kafka][37]              | [MIT][38]                                 |
| [Maven Project Version Getter][39]         | [MIT License][40]                         |
| [Apache MINA Core][41]                     | [Apache 2.0 License][15]                  |
| [Apache Kafka][4]                          | [The Apache License, Version 2.0][5]      |
| [Apache ZooKeeper - Server][42]            | [Apache License, Version 2.0][15]         |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][43]                       | [GNU LGPL 3][44]                               |
| [Apache Maven Toolchains Plugin][45]                    | [Apache-2.0][15]                               |
| [Apache Maven Compiler Plugin][46]                      | [Apache-2.0][15]                               |
| [Apache Maven Enforcer Plugin][47]                      | [Apache-2.0][15]                               |
| [Maven Flatten Plugin][48]                              | [Apache Software License][15]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][49] | [ASL2][5]                                      |
| [scala-maven-plugin][50]                                | [Public domain (Unlicense)][51]                |
| [ScalaTest Maven Plugin][52]                            | [the Apache License, ASL Version 2.0][17]      |
| [Maven Surefire Plugin][53]                             | [Apache-2.0][15]                               |
| [Versions Maven Plugin][54]                             | [Apache License, Version 2.0][15]              |
| [duplicate-finder-maven-plugin Maven Mojo][55]          | [Apache License 2.0][7]                        |
| [Apache Maven Artifact Plugin][56]                      | [Apache-2.0][15]                               |
| [Apache Maven Assembly Plugin][57]                      | [Apache-2.0][15]                               |
| [Apache Maven JAR Plugin][58]                           | [Apache-2.0][15]                               |
| [Project Keeper Maven plugin][59]                       | [The MIT License][60]                          |
| [OpenFastTrace Maven Plugin][61]                        | [GNU General Public License v3.0][62]          |
| [Scalastyle Maven Plugin][63]                           | [Apache 2.0][7]                                |
| [spotless-maven-plugin][64]                             | [The Apache Software License, Version 2.0][15] |
| [scalafix-maven-plugin][65]                             | [BSD-3-Clause][66]                             |
| [Exec Maven Plugin][67]                                 | [Apache License 2][15]                         |
| [Artifact reference checker and unifier][68]            | [MIT License][69]                              |
| [Maven Failsafe Plugin][70]                             | [Apache-2.0][15]                               |
| [JaCoCo :: Maven Plugin][71]                            | [EPL-2.0][72]                                  |
| [Quality Summarizer Maven Plugin][73]                   | [MIT License][74]                              |
| [error-code-crawler-maven-plugin][75]                   | [MIT License][76]                              |
| [Git Commit Id Maven Plugin][77]                        | [GNU Lesser General Public License 3.0][78]    |
| [Apache Maven Clean Plugin][79]                         | [Apache-2.0][15]                               |
| [Apache Maven Resources Plugin][80]                     | [Apache-2.0][15]                               |
| [Apache Maven Install Plugin][81]                       | [Apache-2.0][15]                               |
| [Apache Maven Site Plugin][82]                          | [Apache-2.0][15]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][83] | MIT     |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/error-reporting-java/
[3]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[4]: https://kafka.apache.org
[5]: http://www.apache.org/licenses/LICENSE-2.0.txt
[6]: http://confluent.io/kafka-avro-serializer
[7]: http://www.apache.org/licenses/LICENSE-2.0.html
[8]: http://www.scala-lang.org/
[9]: http://www.slf4j.org
[10]: https://opensource.org/license/mit
[11]: http://logback.qos.ch/logback-classic
[12]: https://www.eclipse.org/legal/epl-v20.html
[13]: https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[14]: https://github.com/google/gson
[15]: https://www.apache.org/licenses/LICENSE-2.0.txt
[16]: https://jetty.org/http2-parent/http2-common/
[17]: http://www.apache.org/licenses/LICENSE-2.0
[18]: https://www.eclipse.org/org/documents/epl-v10.php
[19]: http://www.scalatest.org
[20]: https://github.com/scalatest/scalatestplus-mockito
[21]: https://github.com/mockito/mockito
[22]: https://opensource.org/licenses/MIT
[23]: https://www.jqno.nl/equalsverifier
[24]: https://github.com/exasol/exasol-testcontainers/
[25]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[26]: http://junit.org
[27]: http://www.eclipse.org/legal/epl-v10.html
[28]: https://github.com/exasol/test-db-builder-java/
[29]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[30]: https://github.com/exasol/hamcrest-resultset-matcher/
[31]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[32]: https://github.com/exasol/extension-manager/
[33]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[34]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[35]: http://confluent.io/kafka-streams-avro-serde
[36]: https://github.com/sksamuel/avro4s
[37]: https://java.testcontainers.org
[38]: http://opensource.org/licenses/MIT
[39]: https://github.com/exasol/maven-project-version-getter/
[40]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[41]: https://mina.apache.org/mina-core/
[42]: http://zookeeper.apache.org/zookeeper
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
