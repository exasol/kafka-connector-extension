<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                          | License                                                                                |
| ----------------------------------- | -------------------------------------------------------------------------------------- |
| [Scala Library][0]                  | [Apache-2.0][1]                                                                        |
| [Import Export UDF Common Scala][2] | [MIT License][3]                                                                       |
| [error-reporting-java][4]           | [MIT License][5]                                                                       |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7]                                                   |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]                                                                |
| [scala-collection-compat][10]       | [Apache-2.0][1]                                                                        |
| [SLF4J API Module][11]              | [MIT][12]                                                                              |
| [Logback Classic Module][13]        | [Eclipse Public License - v 1.0][14]; [GNU Lesser General Public License][15]          |
| [Gson][16]                          | [Apache-2.0][17]                                                                       |
| [Jetty :: HTTP2 :: Common][18]      | [Eclipse Public License - Version 2.0][19]; [Apache Software License - Version 2.0][1] |

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][20]                            | [the Apache License, ASL Version 2.0][21] |
| [scalatestplus-mockito][22]                | [Apache-2.0][21]                          |
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

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Apache Maven Clean Plugin][41]                         | [Apache-2.0][17]                               |
| [Apache Maven Install Plugin][42]                       | [Apache-2.0][17]                               |
| [Apache Maven Resources Plugin][43]                     | [Apache-2.0][17]                               |
| [Apache Maven Site Plugin][44]                          | [Apache-2.0][17]                               |
| [SonarQube Scanner for Maven][45]                       | [GNU LGPL 3][46]                               |
| [Apache Maven Toolchains Plugin][47]                    | [Apache-2.0][17]                               |
| [Apache Maven Compiler Plugin][48]                      | [Apache-2.0][17]                               |
| [Apache Maven Enforcer Plugin][49]                      | [Apache-2.0][17]                               |
| [Maven Flatten Plugin][50]                              | [Apache Software Licenese][17]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][51] | [ASL2][7]                                      |
| [scala-maven-plugin][52]                                | [Public domain (Unlicense)][53]                |
| [ScalaTest Maven Plugin][54]                            | [the Apache License, ASL Version 2.0][21]      |
| [Apache Maven Javadoc Plugin][55]                       | [Apache-2.0][17]                               |
| [Maven Surefire Plugin][56]                             | [Apache-2.0][17]                               |
| [Versions Maven Plugin][57]                             | [Apache License, Version 2.0][17]              |
| [duplicate-finder-maven-plugin Maven Mojo][58]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][59]                      | [Apache-2.0][17]                               |
| [Apache Maven Assembly Plugin][60]                      | [Apache-2.0][17]                               |
| [Apache Maven JAR Plugin][61]                           | [Apache-2.0][17]                               |
| [Project Keeper Maven plugin][62]                       | [The MIT License][63]                          |
| [OpenFastTrace Maven Plugin][64]                        | [GNU General Public License v3.0][65]          |
| [Scalastyle Maven Plugin][66]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][67]                             | [The Apache Software License, Version 2.0][17] |
| [scalafix-maven-plugin][68]                             | [BSD-3-Clause][69]                             |
| [Exec Maven Plugin][70]                                 | [Apache License 2][17]                         |
| [Artifact reference checker and unifier][71]            | [MIT License][72]                              |
| [Maven Failsafe Plugin][73]                             | [Apache-2.0][17]                               |
| [JaCoCo :: Maven Plugin][74]                            | [EPL-2.0][19]                                  |
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
[16]: https://github.com/google/gson
[17]: https://www.apache.org/licenses/LICENSE-2.0.txt
[18]: https://jetty.org/http2-parent/http2-common
[19]: https://www.eclipse.org/legal/epl-2.0/
[20]: http://www.scalatest.org
[21]: http://www.apache.org/licenses/LICENSE-2.0
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
[41]: https://maven.apache.org/plugins/maven-clean-plugin/
[42]: https://maven.apache.org/plugins/maven-install-plugin/
[43]: https://maven.apache.org/plugins/maven-resources-plugin/
[44]: https://maven.apache.org/plugins/maven-site-plugin/
[45]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-scanner-maven/sonar-maven-plugin
[46]: http://www.gnu.org/licenses/lgpl.txt
[47]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[48]: https://maven.apache.org/plugins/maven-compiler-plugin/
[49]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[50]: https://www.mojohaus.org/flatten-maven-plugin/
[51]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[52]: https://github.com/davidB/scala-maven-plugin
[53]: https://unlicense.org/
[54]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[55]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[56]: https://maven.apache.org/surefire/maven-surefire-plugin/
[57]: https://www.mojohaus.org/versions/versions-maven-plugin/
[58]: https://basepom.github.io/duplicate-finder-maven-plugin
[59]: https://maven.apache.org/plugins/maven-artifact-plugin/
[60]: https://maven.apache.org/plugins/maven-assembly-plugin/
[61]: https://maven.apache.org/plugins/maven-jar-plugin/
[62]: https://github.com/exasol/project-keeper/
[63]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[64]: https://github.com/itsallcode/openfasttrace-maven-plugin
[65]: https://www.gnu.org/licenses/gpl-3.0.html
[66]: http://www.scalastyle.org
[67]: https://github.com/diffplug/spotless
[68]: https://github.com/evis/scalafix-maven-plugin
[69]: https://opensource.org/licenses/BSD-3-Clause
[70]: https://www.mojohaus.org/exec-maven-plugin
[71]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[72]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[73]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[74]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[75]: https://github.com/exasol/quality-summarizer-maven-plugin/
[76]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[77]: https://github.com/exasol/error-code-crawler-maven-plugin/
[78]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[79]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[80]: http://www.gnu.org/licenses/lgpl-3.0.txt
[81]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
