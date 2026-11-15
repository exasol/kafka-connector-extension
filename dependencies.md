<!-- @formatter:off -->
# Dependencies

## Compile Dependencies

| Dependency                          | License                                    |
| ----------------------------------- | ------------------------------------------ |
| [scala-library-bootstrapped][0]     | [Apache-2.0][1]                            |
| [Import Export UDF Common Scala][2] | [MIT License][3]                           |
| [error-reporting-java][4]           | [MIT License][5]                           |
| [Confluent Server][6]               | [Confluent License Agreement][7]           |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]                    |
| [scala-collection-compat][10]       | [Apache-2.0][1]                            |
| [SLF4J API Module][11]              | [MIT][12]                                  |
| [Jakarta XML Binding API][13]       | [Eclipse Distribution License - v 1.0][14] |
| [Logback Classic Module][15]        | [EPL-2.0][16]; [LGPL-2.1-only][17]         |

## Test Dependencies

| Dependency                                 | License                                                    |
| ------------------------------------------ | ---------------------------------------------------------- |
| [mockito-junit-jupiter][18]                | [MIT][19]                                                  |
| [EqualsVerifier \| release normal jar][20] | [Apache License, Version 2.0][21]                          |
| [Test containers for Exasol on Docker][22] | [MIT License][23]                                          |
| [Test Database Builder for Java][24]       | [MIT License][25]                                          |
| [Matcher for SQL Result Sets][26]          | [MIT License][27]                                          |
| [udf-debugging-java][28]                   | [MIT License][29]                                          |
| [embedded-kafka-schema-registry][30]       | [MIT][19]                                                  |
| [kafka-schema-registry][31]                | [Confluent Community License][32]; [Apache License 2.0][9] |
| [wire-runtime][33]                         | [Apache-2.0][1]                                            |
| [Gson][34]                                 | [Apache-2.0][21]                                           |
| [Apache Commons Lang][35]                  | [Apache-2.0][21]                                           |
| [kafka-streams-avro-serde][36]             | [Apache License 2.0][9]                                    |
| [Testcontainers :: Kafka][37]              | [MIT][38]                                                  |
| [Maven Project Version Getter][39]         | [MIT License][40]                                          |
| [Apache MINA Core][41]                     | [Apache 2.0 License][21]                                   |
| [Confluent Server][6]                      | [Confluent License Agreement][7]                           |
| [Apache ZooKeeper - Server][42]            | [Apache License, Version 2.0][21]                          |
| [JaCoCo :: Agent][43]                      | [EPL-2.0][44]                                              |

## Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][45]                       | [GNU LGPL 3][46]                               |
| [Apache Maven Toolchains Plugin][47]                    | [Apache-2.0][21]                               |
| [Apache Maven Compiler Plugin][48]                      | [Apache-2.0][21]                               |
| [Apache Maven Enforcer Plugin][49]                      | [Apache-2.0][21]                               |
| [Maven Flatten Plugin][50]                              | [Apache Software License][21]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][51] | [ASL2][52]                                     |
| [Maven Surefire Plugin][53]                             | [Apache-2.0][21]                               |
| [Versions Maven Plugin][54]                             | [Apache License, Version 2.0][21]              |
| [duplicate-finder-maven-plugin Maven Mojo][55]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][56]                      | [Apache-2.0][21]                               |
| [Apache Maven Assembly Plugin][57]                      | [Apache-2.0][21]                               |
| [Apache Maven JAR Plugin][58]                           | [Apache-2.0][21]                               |
| [Artifact reference checker and unifier][59]            | [MIT License][60]                              |
| [spdx-maven-plugin Maven Plugin][61]                    | [The Apache Software License, Version 2.0][52] |
| [Apache Maven Dependency Plugin][62]                    | [Apache-2.0][21]                               |
| [Maven Failsafe Plugin][63]                             | [Apache-2.0][21]                               |
| [JaCoCo :: Maven Plugin][64]                            | [EPL-2.0][44]                                  |
| [error-code-crawler-maven-plugin][65]                   | [MIT License][66]                              |
| [Git Commit Id Maven Plugin][67]                        | [GNU Lesser General Public License 3.0][68]    |
| [Project Keeper Maven plugin][69]                       | [The MIT License][70]                          |
| [Apache Maven Clean Plugin][71]                         | [Apache-2.0][21]                               |
| [Apache Maven Resources Plugin][72]                     | [Apache-2.0][21]                               |
| [Apache Maven Install Plugin][73]                       | [Apache-2.0][21]                               |
| [Apache Maven Site Plugin][74]                          | [Apache-2.0][21]                               |

[0]: https://scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://github.com/exasol/error-reporting-java/
[5]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[6]: https://docs.confluent.io/platform/current/get-started/platform.html
[7]: https://www.confluent.io/legal/confluent-license-agreement/
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
[31]: http://confluent.io/kafka-schema-registry
[32]: http://www.confluent.io/confluent-community-license
[33]: https://github.com/square/wire/
[34]: https://github.com/google/gson
[35]: https://commons.apache.org/proper/commons-lang/
[36]: http://confluent.io/kafka-streams-avro-serde
[37]: https://java.testcontainers.org
[38]: http://opensource.org/licenses/MIT
[39]: https://github.com/exasol/maven-project-version-getter/
[40]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[41]: https://mina.apache.org/mina-core/
[42]: http://zookeeper.apache.org/zookeeper
[43]: https://www.eclemma.org/jacoco/index.html
[44]: https://www.eclipse.org/legal/epl-2.0/
[45]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[46]: http://www.gnu.org/licenses/lgpl.txt
[47]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[48]: https://maven.apache.org/plugins/maven-compiler-plugin/
[49]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[50]: https://www.mojohaus.org/flatten-maven-plugin/
[51]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[52]: http://www.apache.org/licenses/LICENSE-2.0.txt
[53]: https://maven.apache.org/surefire/maven-surefire-plugin/
[54]: https://www.mojohaus.org/versions/versions-maven-plugin/
[55]: https://basepom.github.io/duplicate-finder-maven-plugin
[56]: https://maven.apache.org/plugins/maven-artifact-plugin/
[57]: https://maven.apache.org/plugins/maven-assembly-plugin/
[58]: https://maven.apache.org/plugins/maven-jar-plugin/
[59]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[60]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[61]: https://github.com/spdx/spdx-maven-plugin
[62]: https://maven.apache.org/plugins/maven-dependency-plugin/
[63]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[64]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[65]: https://github.com/exasol/error-code-crawler-maven-plugin/
[66]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[67]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[68]: http://www.gnu.org/licenses/lgpl-3.0.txt
[69]: https://github.com/exasol/project-keeper/
[70]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[71]: https://maven.apache.org/plugins/maven-clean-plugin/
[72]: https://maven.apache.org/plugins/maven-resources-plugin/
[73]: https://maven.apache.org/plugins/maven-install-plugin/
[74]: https://maven.apache.org/plugins/maven-site-plugin/
