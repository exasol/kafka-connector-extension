<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                          | License                              |
| ----------------------------------- | ------------------------------------ |
| [Scala Library][0]                  | [Apache-2.0][1]                      |
| [Import Export UDF Common Scala][2] | [MIT License][3]                     |
| [error-reporting-java][4]           | [MIT License][5]                     |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7] |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]              |
| [scala-collection-compat][10]       | [Apache-2.0][1]                      |
| [SLF4J API Module][11]              | [MIT][12]                            |
| [Logback Classic Module][13]        | [EPL-2.0][14]; [LGPL-2.1-only][15]   |

### Test Dependencies

| Dependency                                 | License                              |
| ------------------------------------------ | ------------------------------------ |
| [mockito-junit-jupiter][16]                | [MIT][17]                            |
| [EqualsVerifier \| release normal jar][18] | [Apache License, Version 2.0][19]    |
| [Test containers for Exasol on Docker][20] | [MIT License][21]                    |
| [Test Database Builder for Java][22]       | [MIT License][23]                    |
| [Matcher for SQL Result Sets][24]          | [MIT License][25]                    |
| [Extension integration tests library][26]  | [MIT License][27]                    |
| [embedded-kafka-schema-registry][28]       | [MIT][17]                            |
| [rest-utils][29]                           | [Apache License 2.0][9]              |
| [kafka-streams-avro-serde][30]             | [Apache License 2.0][9]              |
| [Testcontainers :: Kafka][31]              | [MIT][32]                            |
| [Maven Project Version Getter][33]         | [MIT License][34]                    |
| [Apache MINA Core][35]                     | [Apache 2.0 License][19]             |
| [Apache Kafka][6]                          | [The Apache License, Version 2.0][7] |
| [Apache ZooKeeper - Server][36]            | [Apache License, Version 2.0][19]    |

### Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][37]                       | [GNU LGPL 3][38]                            |
| [Apache Maven Toolchains Plugin][39]                    | [Apache-2.0][19]                            |
| [Apache Maven Compiler Plugin][40]                      | [Apache-2.0][19]                            |
| [Apache Maven Enforcer Plugin][41]                      | [Apache-2.0][19]                            |
| [Maven Flatten Plugin][42]                              | [Apache Software License][19]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][43] | [ASL2][7]                                   |
| [Maven Surefire Plugin][44]                             | [Apache-2.0][19]                            |
| [Versions Maven Plugin][45]                             | [Apache License, Version 2.0][19]           |
| [duplicate-finder-maven-plugin Maven Mojo][46]          | [Apache License 2.0][9]                     |
| [Apache Maven Artifact Plugin][47]                      | [Apache-2.0][19]                            |
| [Apache Maven Assembly Plugin][48]                      | [Apache-2.0][19]                            |
| [Apache Maven JAR Plugin][49]                           | [Apache-2.0][19]                            |
| [Artifact reference checker and unifier][50]            | [MIT License][51]                           |
| [Maven Failsafe Plugin][52]                             | [Apache-2.0][19]                            |
| [JaCoCo :: Maven Plugin][53]                            | [EPL-2.0][54]                               |
| [Quality Summarizer Maven Plugin][55]                   | [MIT License][56]                           |
| [error-code-crawler-maven-plugin][57]                   | [MIT License][58]                           |
| [Git Commit Id Maven Plugin][59]                        | [GNU Lesser General Public License 3.0][60] |
| [Project Keeper Maven plugin][61]                       | [The MIT License][62]                       |
| [Exec Maven Plugin][63]                                 | [Apache License 2][19]                      |
| [Apache Maven Clean Plugin][64]                         | [Apache-2.0][19]                            |
| [Apache Maven Resources Plugin][65]                     | [Apache-2.0][19]                            |
| [Apache Maven Install Plugin][66]                       | [Apache-2.0][19]                            |
| [Apache Maven Site Plugin][67]                          | [Apache-2.0][19]                            |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][68] | MIT     |

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
[16]: https://github.com/mockito/mockito
[17]: https://opensource.org/licenses/MIT
[18]: https://www.jqno.nl/equalsverifier
[19]: https://www.apache.org/licenses/LICENSE-2.0.txt
[20]: https://github.com/exasol/exasol-testcontainers/
[21]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[22]: https://github.com/exasol/test-db-builder-java/
[23]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[24]: https://github.com/exasol/hamcrest-resultset-matcher/
[25]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[26]: https://github.com/exasol/extension-manager/
[27]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[28]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[29]: https://confluent.io/rest-utils
[30]: http://confluent.io/kafka-streams-avro-serde
[31]: https://java.testcontainers.org
[32]: http://opensource.org/licenses/MIT
[33]: https://github.com/exasol/maven-project-version-getter/
[34]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[35]: https://mina.apache.org/mina-core/
[36]: http://zookeeper.apache.org/zookeeper
[37]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[38]: http://www.gnu.org/licenses/lgpl.txt
[39]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[40]: https://maven.apache.org/plugins/maven-compiler-plugin/
[41]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[42]: https://www.mojohaus.org/flatten-maven-plugin/
[43]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[44]: https://maven.apache.org/surefire/maven-surefire-plugin/
[45]: https://www.mojohaus.org/versions/versions-maven-plugin/
[46]: https://basepom.github.io/duplicate-finder-maven-plugin
[47]: https://maven.apache.org/plugins/maven-artifact-plugin/
[48]: https://maven.apache.org/plugins/maven-assembly-plugin/
[49]: https://maven.apache.org/plugins/maven-jar-plugin/
[50]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[51]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[52]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[53]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[54]: https://www.eclipse.org/legal/epl-2.0/
[55]: https://github.com/exasol/quality-summarizer-maven-plugin/
[56]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[57]: https://github.com/exasol/error-code-crawler-maven-plugin/
[58]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[59]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[60]: http://www.gnu.org/licenses/lgpl-3.0.txt
[61]: https://github.com/exasol/project-keeper/
[62]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[63]: https://www.mojohaus.org/exec-maven-plugin
[64]: https://maven.apache.org/plugins/maven-clean-plugin/
[65]: https://maven.apache.org/plugins/maven-resources-plugin/
[66]: https://maven.apache.org/plugins/maven-install-plugin/
[67]: https://maven.apache.org/plugins/maven-site-plugin/
[68]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
