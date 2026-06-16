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
| [Gson][16]                          | [Apache-2.0][17]                     |

### Test Dependencies

| Dependency                                 | License                              |
| ------------------------------------------ | ------------------------------------ |
| [mockito-junit-jupiter][18]                | [MIT][19]                            |
| [EqualsVerifier \| release normal jar][20] | [Apache License, Version 2.0][17]    |
| [Test containers for Exasol on Docker][21] | [MIT License][22]                    |
| [Test Database Builder for Java][23]       | [MIT License][24]                    |
| [Matcher for SQL Result Sets][25]          | [MIT License][26]                    |
| [Extension integration tests library][27]  | [MIT License][28]                    |
| [embedded-kafka-schema-registry][29]       | [MIT][19]                            |
| [rest-utils][30]                           | [Apache License 2.0][9]              |
| [kafka-streams-avro-serde][31]             | [Apache License 2.0][9]              |
| [Testcontainers :: Kafka][32]              | [MIT][33]                            |
| [Maven Project Version Getter][34]         | [MIT License][35]                    |
| [Apache MINA Core][36]                     | [Apache 2.0 License][17]             |
| [Apache Kafka][6]                          | [The Apache License, Version 2.0][7] |
| [Apache ZooKeeper - Server][37]            | [Apache License, Version 2.0][17]    |

### Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][38]                       | [GNU LGPL 3][39]                            |
| [Apache Maven Toolchains Plugin][40]                    | [Apache-2.0][17]                            |
| [Apache Maven Compiler Plugin][41]                      | [Apache-2.0][17]                            |
| [Apache Maven Enforcer Plugin][42]                      | [Apache-2.0][17]                            |
| [Maven Flatten Plugin][43]                              | [Apache Software License][17]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][44] | [ASL2][7]                                   |
| [Maven Surefire Plugin][45]                             | [Apache-2.0][17]                            |
| [Versions Maven Plugin][46]                             | [Apache License, Version 2.0][17]           |
| [duplicate-finder-maven-plugin Maven Mojo][47]          | [Apache License 2.0][9]                     |
| [Apache Maven Artifact Plugin][48]                      | [Apache-2.0][17]                            |
| [Apache Maven Assembly Plugin][49]                      | [Apache-2.0][17]                            |
| [Apache Maven JAR Plugin][50]                           | [Apache-2.0][17]                            |
| [Artifact reference checker and unifier][51]            | [MIT License][52]                           |
| [Maven Failsafe Plugin][53]                             | [Apache-2.0][17]                            |
| [JaCoCo :: Maven Plugin][54]                            | [EPL-2.0][55]                               |
| [Quality Summarizer Maven Plugin][56]                   | [MIT License][57]                           |
| [error-code-crawler-maven-plugin][58]                   | [MIT License][59]                           |
| [Git Commit Id Maven Plugin][60]                        | [GNU Lesser General Public License 3.0][61] |
| [Project Keeper Maven plugin][62]                       | [The MIT License][63]                       |
| [Exec Maven Plugin][64]                                 | [Apache License 2][17]                      |
| [Apache Maven Clean Plugin][65]                         | [Apache-2.0][17]                            |
| [Apache Maven Resources Plugin][66]                     | [Apache-2.0][17]                            |
| [Apache Maven Install Plugin][67]                       | [Apache-2.0][17]                            |
| [Apache Maven Site Plugin][68]                          | [Apache-2.0][17]                            |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][69] | MIT     |

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
[18]: https://github.com/mockito/mockito
[19]: https://opensource.org/licenses/MIT
[20]: https://www.jqno.nl/equalsverifier
[21]: https://github.com/exasol/exasol-testcontainers/
[22]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[23]: https://github.com/exasol/test-db-builder-java/
[24]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[25]: https://github.com/exasol/hamcrest-resultset-matcher/
[26]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[27]: https://github.com/exasol/extension-manager/
[28]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[29]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[30]: https://confluent.io/rest-utils
[31]: http://confluent.io/kafka-streams-avro-serde
[32]: https://java.testcontainers.org
[33]: http://opensource.org/licenses/MIT
[34]: https://github.com/exasol/maven-project-version-getter/
[35]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[36]: https://mina.apache.org/mina-core/
[37]: http://zookeeper.apache.org/zookeeper
[38]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[39]: http://www.gnu.org/licenses/lgpl.txt
[40]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[41]: https://maven.apache.org/plugins/maven-compiler-plugin/
[42]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[43]: https://www.mojohaus.org/flatten-maven-plugin/
[44]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[45]: https://maven.apache.org/surefire/maven-surefire-plugin/
[46]: https://www.mojohaus.org/versions/versions-maven-plugin/
[47]: https://basepom.github.io/duplicate-finder-maven-plugin
[48]: https://maven.apache.org/plugins/maven-artifact-plugin/
[49]: https://maven.apache.org/plugins/maven-assembly-plugin/
[50]: https://maven.apache.org/plugins/maven-jar-plugin/
[51]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[52]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[53]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[54]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[55]: https://www.eclipse.org/legal/epl-2.0/
[56]: https://github.com/exasol/quality-summarizer-maven-plugin/
[57]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[58]: https://github.com/exasol/error-code-crawler-maven-plugin/
[59]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[60]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[61]: http://www.gnu.org/licenses/lgpl-3.0.txt
[62]: https://github.com/exasol/project-keeper/
[63]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[64]: https://www.mojohaus.org/exec-maven-plugin
[65]: https://maven.apache.org/plugins/maven-clean-plugin/
[66]: https://maven.apache.org/plugins/maven-resources-plugin/
[67]: https://maven.apache.org/plugins/maven-install-plugin/
[68]: https://maven.apache.org/plugins/maven-site-plugin/
[69]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
