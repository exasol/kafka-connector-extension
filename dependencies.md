<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

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

## Test Dependencies

| Dependency                                 | License                              |
| ------------------------------------------ | ------------------------------------ |
| [mockito-junit-jupiter][16]                | [MIT][17]                            |
| [EqualsVerifier \| release normal jar][18] | [Apache License, Version 2.0][19]    |
| [Test containers for Exasol on Docker][20] | [MIT License][21]                    |
| [Test Database Builder for Java][22]       | [MIT License][23]                    |
| [Matcher for SQL Result Sets][24]          | [MIT License][25]                    |
| [udf-debugging-java][26]                   | [MIT License][27]                    |
| [embedded-kafka-schema-registry][28]       | [MIT][17]                            |
| [Gson][29]                                 | [Apache-2.0][19]                     |
| [Apache Commons Lang][30]                  | [Apache-2.0][19]                     |
| [kafka-streams-avro-serde][31]             | [Apache License 2.0][9]              |
| [Testcontainers :: Kafka][32]              | [MIT][33]                            |
| [Maven Project Version Getter][34]         | [MIT License][35]                    |
| [Apache MINA Core][36]                     | [Apache 2.0 License][19]             |
| [Apache Kafka][6]                          | [The Apache License, Version 2.0][7] |
| [Apache ZooKeeper - Server][37]            | [Apache License, Version 2.0][19]    |
| [JaCoCo :: Agent][38]                      | [EPL-2.0][39]                        |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][40]                       | [GNU LGPL 3][41]                            |
| [Apache Maven Toolchains Plugin][42]                    | [Apache-2.0][19]                            |
| [Apache Maven Compiler Plugin][43]                      | [Apache-2.0][19]                            |
| [Apache Maven Enforcer Plugin][44]                      | [Apache-2.0][19]                            |
| [Maven Flatten Plugin][45]                              | [Apache Software License][19]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][46] | [ASL2][7]                                   |
| [Maven Surefire Plugin][47]                             | [Apache-2.0][19]                            |
| [Versions Maven Plugin][48]                             | [Apache License, Version 2.0][19]           |
| [duplicate-finder-maven-plugin Maven Mojo][49]          | [Apache License 2.0][9]                     |
| [Apache Maven Artifact Plugin][50]                      | [Apache-2.0][19]                            |
| [Apache Maven Assembly Plugin][51]                      | [Apache-2.0][19]                            |
| [Apache Maven JAR Plugin][52]                           | [Apache-2.0][19]                            |
| [Artifact reference checker and unifier][53]            | [MIT License][54]                           |
| [Apache Maven Dependency Plugin][55]                    | [Apache-2.0][19]                            |
| [Maven Failsafe Plugin][56]                             | [Apache-2.0][19]                            |
| [JaCoCo :: Maven Plugin][57]                            | [EPL-2.0][39]                               |
| [Quality Summarizer Maven Plugin][58]                   | [MIT License][59]                           |
| [error-code-crawler-maven-plugin][60]                   | [MIT License][61]                           |
| [Git Commit Id Maven Plugin][62]                        | [GNU Lesser General Public License 3.0][63] |
| [Project Keeper Maven plugin][64]                       | [The MIT License][65]                       |
| [Exec Maven Plugin][66]                                 | [Apache License 2][19]                      |
| [Apache Maven Clean Plugin][67]                         | [Apache-2.0][19]                            |
| [Apache Maven Resources Plugin][68]                     | [Apache-2.0][19]                            |
| [Apache Maven Install Plugin][69]                       | [Apache-2.0][19]                            |
| [Apache Maven Site Plugin][70]                          | [Apache-2.0][19]                            |

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
[26]: https://github.com/exasol/udf-debugging-java/
[27]: https://github.com/exasol/udf-debugging-java/blob/main/LICENSE
[28]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[29]: https://github.com/google/gson
[30]: https://commons.apache.org/proper/commons-lang/
[31]: http://confluent.io/kafka-streams-avro-serde
[32]: https://java.testcontainers.org
[33]: http://opensource.org/licenses/MIT
[34]: https://github.com/exasol/maven-project-version-getter/
[35]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[36]: https://mina.apache.org/mina-core/
[37]: http://zookeeper.apache.org/zookeeper
[38]: https://www.eclemma.org/jacoco/index.html
[39]: https://www.eclipse.org/legal/epl-2.0/
[40]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[41]: http://www.gnu.org/licenses/lgpl.txt
[42]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[43]: https://maven.apache.org/plugins/maven-compiler-plugin/
[44]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[45]: https://www.mojohaus.org/flatten-maven-plugin/
[46]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[47]: https://maven.apache.org/surefire/maven-surefire-plugin/
[48]: https://www.mojohaus.org/versions/versions-maven-plugin/
[49]: https://basepom.github.io/duplicate-finder-maven-plugin
[50]: https://maven.apache.org/plugins/maven-artifact-plugin/
[51]: https://maven.apache.org/plugins/maven-assembly-plugin/
[52]: https://maven.apache.org/plugins/maven-jar-plugin/
[53]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[54]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[55]: https://maven.apache.org/plugins/maven-dependency-plugin/
[56]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[57]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[58]: https://github.com/exasol/quality-summarizer-maven-plugin/
[59]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[60]: https://github.com/exasol/error-code-crawler-maven-plugin/
[61]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[62]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[63]: http://www.gnu.org/licenses/lgpl-3.0.txt
[64]: https://github.com/exasol/project-keeper/
[65]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[66]: https://www.mojohaus.org/exec-maven-plugin
[67]: https://maven.apache.org/plugins/maven-clean-plugin/
[68]: https://maven.apache.org/plugins/maven-resources-plugin/
[69]: https://maven.apache.org/plugins/maven-install-plugin/
[70]: https://maven.apache.org/plugins/maven-site-plugin/
