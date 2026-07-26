<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                          | License                              |
| ----------------------------------- | ------------------------------------ |
| [scala-library-bootstrapped][0]     | [Apache-2.0][1]                      |
| [Import Export UDF Common Scala][2] | [MIT License][3]                     |
| [error-reporting-java][4]           | [MIT License][5]                     |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7] |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]              |
| [scala-collection-compat][10]       | [Apache-2.0][1]                      |
| [SLF4J API Module][11]              | [MIT][12]                            |
| [Logback Classic Module][13]        | [EPL-2.0][14]; [LGPL-2.1-only][15]   |

## Test Dependencies

| Dependency                                 | License                           |
| ------------------------------------------ | --------------------------------- |
| [mockito-junit-jupiter][16]                | [MIT][17]                         |
| [EqualsVerifier \| release normal jar][18] | [Apache License, Version 2.0][19] |
| [Test containers for Exasol on Docker][20] | [MIT License][21]                 |
| [Test Database Builder for Java][22]       | [MIT License][23]                 |
| [Matcher for SQL Result Sets][24]          | [MIT License][25]                 |
| [udf-debugging-java][26]                   | [MIT License][27]                 |
| [embedded-kafka-schema-registry][28]       | [MIT][17]                         |
| [Gson][29]                                 | [Apache-2.0][19]                  |
| [Apache Commons Lang][30]                  | [Apache-2.0][19]                  |
| [kafka-streams-avro-serde][31]             | [Apache License 2.0][9]           |
| [Testcontainers :: Kafka][32]              | [MIT][33]                         |
| [Maven Project Version Getter][34]         | [MIT License][35]                 |
| [Apache MINA Core][36]                     | [Apache 2.0 License][19]          |
| [Confluent Server][37]                     | [Confluent License Agreement][38] |
| [Apache ZooKeeper - Server][39]            | [Apache License, Version 2.0][19] |
| [JaCoCo :: Agent][40]                      | [EPL-2.0][41]                     |

## Plugin Dependencies

| Dependency                                              | License                                     |
| ------------------------------------------------------- | ------------------------------------------- |
| [SonarQube Scanner for Maven][42]                       | [GNU LGPL 3][43]                            |
| [Apache Maven Toolchains Plugin][44]                    | [Apache-2.0][19]                            |
| [Apache Maven Compiler Plugin][45]                      | [Apache-2.0][19]                            |
| [Apache Maven Enforcer Plugin][46]                      | [Apache-2.0][19]                            |
| [Maven Flatten Plugin][47]                              | [Apache Software License][19]               |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][48] | [ASL2][7]                                   |
| [Maven Surefire Plugin][49]                             | [Apache-2.0][19]                            |
| [Versions Maven Plugin][50]                             | [Apache License, Version 2.0][19]           |
| [duplicate-finder-maven-plugin Maven Mojo][51]          | [Apache License 2.0][9]                     |
| [Apache Maven Artifact Plugin][52]                      | [Apache-2.0][19]                            |
| [Apache Maven Assembly Plugin][53]                      | [Apache-2.0][19]                            |
| [Apache Maven JAR Plugin][54]                           | [Apache-2.0][19]                            |
| [Artifact reference checker and unifier][55]            | [MIT License][56]                           |
| [Apache Maven Dependency Plugin][57]                    | [Apache-2.0][19]                            |
| [Maven Failsafe Plugin][58]                             | [Apache-2.0][19]                            |
| [JaCoCo :: Maven Plugin][59]                            | [EPL-2.0][41]                               |
| [Quality Summarizer Maven Plugin][60]                   | [MIT License][61]                           |
| [error-code-crawler-maven-plugin][62]                   | [MIT License][63]                           |
| [Git Commit Id Maven Plugin][64]                        | [GNU Lesser General Public License 3.0][65] |
| [Project Keeper Maven plugin][66]                       | [The MIT License][67]                       |
| [Apache Maven Clean Plugin][68]                         | [Apache-2.0][19]                            |
| [Apache Maven Resources Plugin][69]                     | [Apache-2.0][19]                            |
| [Apache Maven Install Plugin][70]                       | [Apache-2.0][19]                            |
| [Apache Maven Site Plugin][71]                          | [Apache-2.0][19]                            |

[0]: https://scala-lang.org/
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
[37]: https://docs.confluent.io/platform/current/get-started/platform.html
[38]: https://www.confluent.io/legal/confluent-license-agreement/
[39]: http://zookeeper.apache.org/zookeeper
[40]: https://www.eclemma.org/jacoco/index.html
[41]: https://www.eclipse.org/legal/epl-2.0/
[42]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[43]: http://www.gnu.org/licenses/lgpl.txt
[44]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[45]: https://maven.apache.org/plugins/maven-compiler-plugin/
[46]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[47]: https://www.mojohaus.org/flatten-maven-plugin/
[48]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[49]: https://maven.apache.org/surefire/maven-surefire-plugin/
[50]: https://www.mojohaus.org/versions/versions-maven-plugin/
[51]: https://basepom.github.io/duplicate-finder-maven-plugin
[52]: https://maven.apache.org/plugins/maven-artifact-plugin/
[53]: https://maven.apache.org/plugins/maven-assembly-plugin/
[54]: https://maven.apache.org/plugins/maven-jar-plugin/
[55]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[56]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[57]: https://maven.apache.org/plugins/maven-dependency-plugin/
[58]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[59]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[60]: https://github.com/exasol/quality-summarizer-maven-plugin/
[61]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[62]: https://github.com/exasol/error-code-crawler-maven-plugin/
[63]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[64]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[65]: http://www.gnu.org/licenses/lgpl-3.0.txt
[66]: https://github.com/exasol/project-keeper/
[67]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[68]: https://maven.apache.org/plugins/maven-clean-plugin/
[69]: https://maven.apache.org/plugins/maven-resources-plugin/
[70]: https://maven.apache.org/plugins/maven-install-plugin/
[71]: https://maven.apache.org/plugins/maven-site-plugin/
