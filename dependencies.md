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
| [udf-debugging-java][30]                   | [MIT License][31]                 |
| [Extension integration tests library][32]  | [MIT License][33]                 |
| [embedded-kafka-schema-registry][34]       | [MIT][22]                         |
| [kafka-streams-avro-serde][35]             | [Apache License 2.0][9]           |
| [Testcontainers :: Kafka][36]              | [MIT][37]                         |
| [Maven Project Version Getter][38]         | [MIT License][39]                 |
| [Apache MINA Core][40]                     | [Apache 2.0 License][17]          |
| [JaCoCo :: Agent][41]                      | [EPL-2.0][42]                     |

### Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][43]                       | [GNU LGPL 3][44]                            |
| [Apache Maven Toolchains Plugin][45]                    | [Apache-2.0][17]                            |
| [Apache Maven Compiler Plugin][46]                      | [Apache-2.0][17]                            |
| [Apache Maven Enforcer Plugin][47]                      | [Apache-2.0][17]                            |
| [Maven Flatten Plugin][48]                              | [Apache Software License][17]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][49] | [ASL2][7]                                   |
| [Maven Surefire Plugin][50]                             | [Apache-2.0][17]                            |
| [Versions Maven Plugin][51]                             | [Apache License, Version 2.0][17]           |
| [duplicate-finder-maven-plugin Maven Mojo][52]          | [Apache License 2.0][9]                     |
| [Apache Maven Artifact Plugin][53]                      | [Apache-2.0][17]                            |
| [Apache Maven Assembly Plugin][54]                      | [Apache-2.0][17]                            |
| [Apache Maven JAR Plugin][55]                           | [Apache-2.0][17]                            |
| [Artifact reference checker and unifier][56]            | [MIT License][57]                           |
| [Apache Maven Dependency Plugin][58]                    | [Apache-2.0][17]                            |
| [Maven Failsafe Plugin][59]                             | [Apache-2.0][17]                            |
| [JaCoCo :: Maven Plugin][60]                            | [EPL-2.0][42]                               |
| [Quality Summarizer Maven Plugin][61]                   | [MIT License][62]                           |
| [error-code-crawler-maven-plugin][63]                   | [MIT License][64]                           |
| [Git Commit Id Maven Plugin][65]                        | [GNU Lesser General Public License 3.0][66] |
| [Project Keeper Maven plugin][67]                       | [The MIT License][68]                       |
| [Exec Maven Plugin][69]                                 | [Apache License 2][17]                      |
| [Apache Maven Clean Plugin][70]                         | [Apache-2.0][17]                            |
| [Apache Maven Resources Plugin][71]                     | [Apache-2.0][17]                            |
| [Apache Maven Install Plugin][72]                       | [Apache-2.0][17]                            |
| [Apache Maven Site Plugin][73]                          | [Apache-2.0][17]                            |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][74] | MIT     |

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
[30]: https://github.com/exasol/udf-debugging-java/
[31]: https://github.com/exasol/udf-debugging-java/blob/main/LICENSE
[32]: https://github.com/exasol/extension-manager/
[33]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[34]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[35]: http://confluent.io/kafka-streams-avro-serde
[36]: https://java.testcontainers.org
[37]: http://opensource.org/licenses/MIT
[38]: https://github.com/exasol/maven-project-version-getter/
[39]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[40]: https://mina.apache.org/mina-core/
[41]: https://www.eclemma.org/jacoco/index.html
[42]: https://www.eclipse.org/legal/epl-2.0/
[43]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[44]: http://www.gnu.org/licenses/lgpl.txt
[45]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[46]: https://maven.apache.org/plugins/maven-compiler-plugin/
[47]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[48]: https://www.mojohaus.org/flatten-maven-plugin/
[49]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[50]: https://maven.apache.org/surefire/maven-surefire-plugin/
[51]: https://www.mojohaus.org/versions/versions-maven-plugin/
[52]: https://basepom.github.io/duplicate-finder-maven-plugin
[53]: https://maven.apache.org/plugins/maven-artifact-plugin/
[54]: https://maven.apache.org/plugins/maven-assembly-plugin/
[55]: https://maven.apache.org/plugins/maven-jar-plugin/
[56]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[57]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[58]: https://maven.apache.org/plugins/maven-dependency-plugin/
[59]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[60]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[61]: https://github.com/exasol/quality-summarizer-maven-plugin/
[62]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[63]: https://github.com/exasol/error-code-crawler-maven-plugin/
[64]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[65]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[66]: http://www.gnu.org/licenses/lgpl-3.0.txt
[67]: https://github.com/exasol/project-keeper/
[68]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[69]: https://www.mojohaus.org/exec-maven-plugin
[70]: https://maven.apache.org/plugins/maven-clean-plugin/
[71]: https://maven.apache.org/plugins/maven-resources-plugin/
[72]: https://maven.apache.org/plugins/maven-install-plugin/
[73]: https://maven.apache.org/plugins/maven-site-plugin/
[74]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
