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

| Dependency                                 | License                           |
| ------------------------------------------ | --------------------------------- |
| [mockito-junit-jupiter][21]                | [MIT][22]                         |
| [EqualsVerifier \| release normal jar][23] | [Apache License, Version 2.0][17] |
| [Test containers for Exasol on Docker][24] | [MIT License][25]                 |
| [Test Database Builder for Java][26]       | [MIT License][27]                 |
| [Matcher for SQL Result Sets][28]          | [MIT License][29]                 |
| [Extension integration tests library][30]  | [MIT License][31]                 |
| [embedded-kafka-schema-registry][32]       | [MIT][22]                         |
| [kafka-streams-avro-serde][33]             | [Apache License 2.0][9]           |
| [Testcontainers :: Kafka][34]              | [MIT][35]                         |
| [Maven Project Version Getter][36]         | [MIT License][37]                 |
| [Apache MINA Core][38]                     | [Apache 2.0 License][17]          |

### Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][39]                       | [GNU LGPL 3][40]                            |
| [Apache Maven Toolchains Plugin][41]                    | [Apache-2.0][17]                            |
| [Apache Maven Compiler Plugin][42]                      | [Apache-2.0][17]                            |
| [Apache Maven Enforcer Plugin][43]                      | [Apache-2.0][17]                            |
| [Maven Flatten Plugin][44]                              | [Apache Software License][17]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][45] | [ASL2][7]                                   |
| [Maven Surefire Plugin][46]                             | [Apache-2.0][17]                            |
| [Versions Maven Plugin][47]                             | [Apache License, Version 2.0][17]           |
| [duplicate-finder-maven-plugin Maven Mojo][48]          | [Apache License 2.0][9]                     |
| [Apache Maven Artifact Plugin][49]                      | [Apache-2.0][17]                            |
| [Apache Maven Assembly Plugin][50]                      | [Apache-2.0][17]                            |
| [Apache Maven JAR Plugin][51]                           | [Apache-2.0][17]                            |
| [Artifact reference checker and unifier][52]            | [MIT License][53]                           |
| [Maven Failsafe Plugin][54]                             | [Apache-2.0][17]                            |
| [JaCoCo :: Maven Plugin][55]                            | [EPL-2.0][56]                               |
| [Quality Summarizer Maven Plugin][57]                   | [MIT License][58]                           |
| [error-code-crawler-maven-plugin][59]                   | [MIT License][60]                           |
| [Git Commit Id Maven Plugin][61]                        | [GNU Lesser General Public License 3.0][62] |
| [Project Keeper Maven plugin][63]                       | [The MIT License][64]                       |
| [Exec Maven Plugin][65]                                 | [Apache License 2][17]                      |
| [Apache Maven Clean Plugin][66]                         | [Apache-2.0][17]                            |
| [Apache Maven Resources Plugin][67]                     | [Apache-2.0][17]                            |
| [Apache Maven Install Plugin][68]                       | [Apache-2.0][17]                            |
| [Apache Maven Site Plugin][69]                          | [Apache-2.0][17]                            |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][70] | MIT     |

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
[21]: https://github.com/mockito/mockito
[22]: https://opensource.org/licenses/MIT
[23]: https://www.jqno.nl/equalsverifier
[24]: https://github.com/exasol/exasol-testcontainers/
[25]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[26]: https://github.com/exasol/test-db-builder-java/
[27]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[28]: https://github.com/exasol/hamcrest-resultset-matcher/
[29]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[30]: https://github.com/exasol/extension-manager/
[31]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[32]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[33]: http://confluent.io/kafka-streams-avro-serde
[34]: https://java.testcontainers.org
[35]: http://opensource.org/licenses/MIT
[36]: https://github.com/exasol/maven-project-version-getter/
[37]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[38]: https://mina.apache.org/mina-core/
[39]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[40]: http://www.gnu.org/licenses/lgpl.txt
[41]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[42]: https://maven.apache.org/plugins/maven-compiler-plugin/
[43]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[44]: https://www.mojohaus.org/flatten-maven-plugin/
[45]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[46]: https://maven.apache.org/surefire/maven-surefire-plugin/
[47]: https://www.mojohaus.org/versions/versions-maven-plugin/
[48]: https://basepom.github.io/duplicate-finder-maven-plugin
[49]: https://maven.apache.org/plugins/maven-artifact-plugin/
[50]: https://maven.apache.org/plugins/maven-assembly-plugin/
[51]: https://maven.apache.org/plugins/maven-jar-plugin/
[52]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[53]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[54]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[55]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[56]: https://www.eclipse.org/legal/epl-2.0/
[57]: https://github.com/exasol/quality-summarizer-maven-plugin/
[58]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[59]: https://github.com/exasol/error-code-crawler-maven-plugin/
[60]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[61]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[62]: http://www.gnu.org/licenses/lgpl-3.0.txt
[63]: https://github.com/exasol/project-keeper/
[64]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[65]: https://www.mojohaus.org/exec-maven-plugin
[66]: https://maven.apache.org/plugins/maven-clean-plugin/
[67]: https://maven.apache.org/plugins/maven-resources-plugin/
[68]: https://maven.apache.org/plugins/maven-install-plugin/
[69]: https://maven.apache.org/plugins/maven-site-plugin/
[70]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
