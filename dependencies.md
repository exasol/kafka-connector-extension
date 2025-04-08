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

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][16]                            | [the Apache License, ASL Version 2.0][17] |
| [scalatestplus-mockito][18]                | [Apache-2.0][17]                          |
| [mockito-core][19]                         | [MIT][20]                                 |
| [Test containers for Exasol on Docker][21] | [MIT License][22]                         |
| [Test Database Builder for Java][23]       | [MIT License][24]                         |
| [Matcher for SQL Result Sets][25]          | [MIT License][26]                         |
| [Extension integration tests library][27]  | [MIT License][28]                         |
| [embedded-kafka-schema-registry][29]       | [MIT][20]                                 |
| [kafka-streams-avro-serde][30]             | [Apache License 2.0][9]                   |
| [avro4s-core][31]                          | [MIT][20]                                 |
| [Testcontainers :: Kafka][32]              | [MIT][33]                                 |
| [Maven Project Version Getter][34]         | [MIT License][35]                         |
| [Apache MINA Core][36]                     | [Apache 2.0 License][37]                  |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [Apache Maven Clean Plugin][38]                         | [Apache-2.0][37]                               |
| [Apache Maven Install Plugin][39]                       | [Apache-2.0][37]                               |
| [Apache Maven Resources Plugin][40]                     | [Apache-2.0][37]                               |
| [Apache Maven Site Plugin][41]                          | [Apache-2.0][37]                               |
| [SonarQube Scanner for Maven][42]                       | [GNU LGPL 3][43]                               |
| [Apache Maven Toolchains Plugin][44]                    | [Apache-2.0][37]                               |
| [Apache Maven Compiler Plugin][45]                      | [Apache-2.0][37]                               |
| [Apache Maven Enforcer Plugin][46]                      | [Apache-2.0][37]                               |
| [Maven Flatten Plugin][47]                              | [Apache Software Licenese][37]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][48] | [ASL2][7]                                      |
| [scala-maven-plugin][49]                                | [Public domain (Unlicense)][50]                |
| [ScalaTest Maven Plugin][51]                            | [the Apache License, ASL Version 2.0][17]      |
| [Apache Maven Javadoc Plugin][52]                       | [Apache-2.0][37]                               |
| [Maven Surefire Plugin][53]                             | [Apache-2.0][37]                               |
| [Versions Maven Plugin][54]                             | [Apache License, Version 2.0][37]              |
| [duplicate-finder-maven-plugin Maven Mojo][55]          | [Apache License 2.0][9]                        |
| [Apache Maven Assembly Plugin][56]                      | [Apache-2.0][37]                               |
| [Apache Maven JAR Plugin][57]                           | [Apache-2.0][37]                               |
| [Project Keeper Maven plugin][58]                       | [The MIT License][59]                          |
| [OpenFastTrace Maven Plugin][60]                        | [GNU General Public License v3.0][61]          |
| [Scalastyle Maven Plugin][62]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][63]                             | [The Apache Software License, Version 2.0][37] |
| [scalafix-maven-plugin][64]                             | [BSD-3-Clause][65]                             |
| [Exec Maven Plugin][66]                                 | [Apache License 2][37]                         |
| [Artifact reference checker and unifier][67]            | [MIT License][68]                              |
| [Maven Failsafe Plugin][69]                             | [Apache-2.0][37]                               |
| [JaCoCo :: Maven Plugin][70]                            | [EPL-2.0][71]                                  |
| [Quality Summarizer Maven Plugin][72]                   | [MIT License][73]                              |
| [error-code-crawler-maven-plugin][74]                   | [MIT License][75]                              |
| [Reproducible Build Maven Plugin][76]                   | [Apache 2.0][7]                                |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][77] | MIT     |

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
[16]: http://www.scalatest.org
[17]: http://www.apache.org/licenses/LICENSE-2.0
[18]: https://github.com/scalatest/scalatestplus-mockito
[19]: https://github.com/mockito/mockito
[20]: https://opensource.org/licenses/MIT
[21]: https://github.com/exasol/exasol-testcontainers/
[22]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[23]: https://github.com/exasol/test-db-builder-java/
[24]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[25]: https://github.com/exasol/hamcrest-resultset-matcher/
[26]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[27]: https://github.com/exasol/extension-manager/
[28]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[29]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[30]: http://confluent.io/kafka-streams-avro-serde
[31]: https://github.com/sksamuel/avro4s
[32]: https://java.testcontainers.org
[33]: http://opensource.org/licenses/MIT
[34]: https://github.com/exasol/maven-project-version-getter/
[35]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[36]: https://mina.apache.org/mina-core/
[37]: https://www.apache.org/licenses/LICENSE-2.0.txt
[38]: https://maven.apache.org/plugins/maven-clean-plugin/
[39]: https://maven.apache.org/plugins/maven-install-plugin/
[40]: https://maven.apache.org/plugins/maven-resources-plugin/
[41]: https://maven.apache.org/plugins/maven-site-plugin/
[42]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-maven-plugin
[43]: http://www.gnu.org/licenses/lgpl.txt
[44]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[45]: https://maven.apache.org/plugins/maven-compiler-plugin/
[46]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[47]: https://www.mojohaus.org/flatten-maven-plugin/
[48]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[49]: https://github.com/davidB/scala-maven-plugin
[50]: https://unlicense.org/
[51]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[52]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[53]: https://maven.apache.org/surefire/maven-surefire-plugin/
[54]: https://www.mojohaus.org/versions/versions-maven-plugin/
[55]: https://basepom.github.io/duplicate-finder-maven-plugin
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
[76]: http://zlika.github.io/reproducible-build-maven-plugin
[77]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
