<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                          | License                                    |
| ----------------------------------- | ------------------------------------------ |
| [Scala Library][0]                  | [Apache-2.0][1]                            |
| [Import Export UDF Common Scala][2] | [MIT License][3]                           |
| [error-reporting-java][4]           | [MIT License][5]                           |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7]       |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]                    |
| [scala-collection-compat][10]       | [Apache-2.0][1]                            |
| [SLF4J API Module][11]              | [MIT][12]                                  |
| [Jakarta XML Binding API][13]       | [Eclipse Distribution License - v 1.0][14] |
| [Logback Classic Module][15]        | [EPL-2.0][16]; [LGPL-2.1-only][17]         |

## Test Dependencies

| Dependency                                 | License                              |
| ------------------------------------------ | ------------------------------------ |
| [mockito-junit-jupiter][18]                | [MIT][19]                            |
| [EqualsVerifier \| release normal jar][20] | [Apache License, Version 2.0][21]    |
| [Test containers for Exasol on Docker][22] | [MIT License][23]                    |
| [Test Database Builder for Java][24]       | [MIT License][25]                    |
| [Matcher for SQL Result Sets][26]          | [MIT License][27]                    |
| [udf-debugging-java][28]                   | [MIT License][29]                    |
| [embedded-kafka-schema-registry][30]       | [MIT][19]                            |
| [Gson][31]                                 | [Apache-2.0][21]                     |
| [Apache Commons Lang][32]                  | [Apache-2.0][21]                     |
| [kafka-streams-avro-serde][33]             | [Apache License 2.0][9]              |
| [Testcontainers :: Kafka][34]              | [MIT][35]                            |
| [Maven Project Version Getter][36]         | [MIT License][37]                    |
| [Apache MINA Core][38]                     | [Apache 2.0 License][21]             |
| [Apache Kafka][6]                          | [The Apache License, Version 2.0][7] |
| [Apache ZooKeeper - Server][39]            | [Apache License, Version 2.0][21]    |
| [JaCoCo :: Agent][40]                      | [EPL-2.0][41]                        |

## Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][42]                       | [GNU LGPL 3][43]                              |
| [Apache Maven Toolchains Plugin][44]                    | [Apache-2.0][21]                              |
| [Apache Maven Compiler Plugin][45]                      | [Apache-2.0][21]                              |
| [Apache Maven Enforcer Plugin][46]                      | [Apache-2.0][21]                              |
| [Maven Flatten Plugin][47]                              | [Apache Software License][21]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][48] | [ASL2][7]                                     |
| [Maven Surefire Plugin][49]                             | [Apache-2.0][21]                              |
| [Versions Maven Plugin][50]                             | [Apache License, Version 2.0][21]             |
| [duplicate-finder-maven-plugin Maven Mojo][51]          | [Apache License 2.0][9]                       |
| [Apache Maven Artifact Plugin][52]                      | [Apache-2.0][21]                              |
| [Apache Maven Assembly Plugin][53]                      | [Apache-2.0][21]                              |
| [Apache Maven JAR Plugin][54]                           | [Apache-2.0][21]                              |
| [Artifact reference checker and unifier][55]            | [MIT License][56]                             |
| [spdx-maven-plugin Maven Plugin][57]                    | [The Apache Software License, Version 2.0][7] |
| [Apache Maven Dependency Plugin][58]                    | [Apache-2.0][21]                              |
| [Maven Failsafe Plugin][59]                             | [Apache-2.0][21]                              |
| [JaCoCo :: Maven Plugin][60]                            | [EPL-2.0][41]                                 |
| [error-code-crawler-maven-plugin][61]                   | [MIT License][62]                             |
| [Git Commit Id Maven Plugin][63]                        | [GNU Lesser General Public License 3.0][64]   |
| [Project Keeper Maven plugin][65]                       | [The MIT License][66]                         |
| [Apache Maven Clean Plugin][67]                         | [Apache-2.0][21]                              |
| [Apache Maven Resources Plugin][68]                     | [Apache-2.0][21]                              |
| [Apache Maven Install Plugin][69]                       | [Apache-2.0][21]                              |
| [Apache Maven Site Plugin][70]                          | [Apache-2.0][21]                              |

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
[13]: https://github.com/jakartaee/jaxb-api/jakarta.xml.bind-api
[14]: http://www.eclipse.org/org/documents/edl-v10.php
[15]: http://logback.qos.ch/logback-classic
[16]: https://www.eclipse.org/legal/epl-v20.html
[17]: https://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[18]: https://github.com/mockito/mockito
[19]: https://opensource.org/licenses/MIT
[20]: https://www.jqno.nl/equalsverifier
[21]: https://www.apache.org/licenses/LICENSE-2.0.txt
[22]: https://github.com/exasol/exasol-testcontainers/
[23]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[24]: https://github.com/exasol/test-db-builder-java/
[25]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[26]: https://github.com/exasol/hamcrest-resultset-matcher/
[27]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[28]: https://github.com/exasol/udf-debugging-java/
[29]: https://github.com/exasol/udf-debugging-java/blob/main/LICENSE
[30]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[31]: https://github.com/google/gson
[32]: https://commons.apache.org/proper/commons-lang/
[33]: http://confluent.io/kafka-streams-avro-serde
[34]: https://java.testcontainers.org
[35]: http://opensource.org/licenses/MIT
[36]: https://github.com/exasol/maven-project-version-getter/
[37]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[38]: https://mina.apache.org/mina-core/
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
[57]: https://github.com/spdx/spdx-maven-plugin
[58]: https://maven.apache.org/plugins/maven-dependency-plugin/
[59]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[60]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[61]: https://github.com/exasol/error-code-crawler-maven-plugin/
[62]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[63]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[64]: http://www.gnu.org/licenses/lgpl-3.0.txt
[65]: https://github.com/exasol/project-keeper/
[66]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[67]: https://maven.apache.org/plugins/maven-clean-plugin/
[68]: https://maven.apache.org/plugins/maven-resources-plugin/
[69]: https://maven.apache.org/plugins/maven-install-plugin/
[70]: https://maven.apache.org/plugins/maven-site-plugin/
