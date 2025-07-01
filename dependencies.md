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
| [Jetty :: HTTP2 :: Common][16]      | [Apache Software License - Version 2.0][17]; [Eclipse Public License - Version 1.0][18] |

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][19]                            | [the Apache License, ASL Version 2.0][17] |
| [scalatestplus-mockito][20]                | [Apache-2.0][17]                          |
| [mockito-core][21]                         | [MIT][22]                                 |
| [Test containers for Exasol on Docker][23] | [MIT License][24]                         |
| [Test Database Builder for Java][25]       | [MIT License][26]                         |
| [Matcher for SQL Result Sets][27]          | [MIT License][28]                         |
| [Extension integration tests library][29]  | [MIT License][30]                         |
| [embedded-kafka-schema-registry][31]       | [MIT][22]                                 |
| [kafka-streams-avro-serde][32]             | [Apache License 2.0][9]                   |
| [avro4s-core][33]                          | [MIT][22]                                 |
| [Testcontainers :: Kafka][34]              | [MIT][35]                                 |
| [Maven Project Version Getter][36]         | [MIT License][37]                         |
| [Apache MINA Core][38]                     | [Apache 2.0 License][39]                  |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Apache Maven Clean Plugin][40]                         | [Apache-2.0][39]                               |
| [Apache Maven Install Plugin][41]                       | [Apache-2.0][39]                               |
| [Apache Maven Resources Plugin][42]                     | [Apache-2.0][39]                               |
| [Apache Maven Site Plugin][43]                          | [Apache-2.0][39]                               |
| [SonarQube Scanner for Maven][44]                       | [GNU LGPL 3][45]                               |
| [Apache Maven Toolchains Plugin][46]                    | [Apache-2.0][39]                               |
| [Apache Maven Compiler Plugin][47]                      | [Apache-2.0][39]                               |
| [Apache Maven Enforcer Plugin][48]                      | [Apache-2.0][39]                               |
| [Maven Flatten Plugin][49]                              | [Apache Software Licenese][39]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][50] | [ASL2][7]                                      |
| [scala-maven-plugin][51]                                | [Public domain (Unlicense)][52]                |
| [ScalaTest Maven Plugin][53]                            | [the Apache License, ASL Version 2.0][17]      |
| [Apache Maven Javadoc Plugin][54]                       | [Apache-2.0][39]                               |
| [Maven Surefire Plugin][55]                             | [Apache-2.0][39]                               |
| [Versions Maven Plugin][56]                             | [Apache License, Version 2.0][39]              |
| [duplicate-finder-maven-plugin Maven Mojo][57]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][58]                      | [Apache-2.0][39]                               |
| [Apache Maven Assembly Plugin][59]                      | [Apache-2.0][39]                               |
| [Apache Maven JAR Plugin][60]                           | [Apache-2.0][39]                               |
| [Project Keeper Maven plugin][61]                       | [The MIT License][62]                          |
| [OpenFastTrace Maven Plugin][63]                        | [GNU General Public License v3.0][64]          |
| [Scalastyle Maven Plugin][65]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][66]                             | [The Apache Software License, Version 2.0][39] |
| [scalafix-maven-plugin][67]                             | [BSD-3-Clause][68]                             |
| [Exec Maven Plugin][69]                                 | [Apache License 2][39]                         |
| [Artifact reference checker and unifier][70]            | [MIT License][71]                              |
| [Maven Failsafe Plugin][72]                             | [Apache-2.0][39]                               |
| [JaCoCo :: Maven Plugin][73]                            | [EPL-2.0][74]                                  |
| [Quality Summarizer Maven Plugin][75]                   | [MIT License][76]                              |
| [error-code-crawler-maven-plugin][77]                   | [MIT License][78]                              |
| [Git Commit Id Maven Plugin][79]                        | [GNU Lesser General Public License 3.0][80]    |

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
[16]: https://jetty.org/http2-parent/http2-common/
[17]: http://www.apache.org/licenses/LICENSE-2.0
[18]: https://www.eclipse.org/org/documents/epl-v10.php
[19]: http://www.scalatest.org
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
[31]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[32]: http://confluent.io/kafka-streams-avro-serde
[33]: https://github.com/sksamuel/avro4s
[34]: https://java.testcontainers.org
[35]: http://opensource.org/licenses/MIT
[36]: https://github.com/exasol/maven-project-version-getter/
[37]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[38]: https://mina.apache.org/mina-core/
[39]: https://www.apache.org/licenses/LICENSE-2.0.txt
[40]: https://maven.apache.org/plugins/maven-clean-plugin/
[41]: https://maven.apache.org/plugins/maven-install-plugin/
[42]: https://maven.apache.org/plugins/maven-resources-plugin/
[43]: https://maven.apache.org/plugins/maven-site-plugin/
[44]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-scanner-maven/sonar-maven-plugin
[45]: http://www.gnu.org/licenses/lgpl.txt
[46]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[47]: https://maven.apache.org/plugins/maven-compiler-plugin/
[48]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[49]: https://www.mojohaus.org/flatten-maven-plugin/
[50]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[51]: https://github.com/davidB/scala-maven-plugin
[52]: https://unlicense.org/
[53]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[54]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[55]: https://maven.apache.org/surefire/maven-surefire-plugin/
[56]: https://www.mojohaus.org/versions/versions-maven-plugin/
[57]: https://basepom.github.io/duplicate-finder-maven-plugin
[58]: https://maven.apache.org/plugins/maven-artifact-plugin/
[59]: https://maven.apache.org/plugins/maven-assembly-plugin/
[60]: https://maven.apache.org/plugins/maven-jar-plugin/
[61]: https://github.com/exasol/project-keeper/
[62]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[63]: https://github.com/itsallcode/openfasttrace-maven-plugin
[64]: https://www.gnu.org/licenses/gpl-3.0.html
[65]: http://www.scalastyle.org
[66]: https://github.com/diffplug/spotless
[67]: https://github.com/evis/scalafix-maven-plugin
[68]: https://opensource.org/licenses/BSD-3-Clause
[69]: https://www.mojohaus.org/exec-maven-plugin
[70]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[71]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[72]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[73]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[74]: https://www.eclipse.org/legal/epl-2.0/
[75]: https://github.com/exasol/quality-summarizer-maven-plugin/
[76]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[77]: https://github.com/exasol/error-code-crawler-maven-plugin/
[78]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[79]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[80]: http://www.gnu.org/licenses/lgpl-3.0.txt
[81]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
