<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                                  | License                                                                       |
| ------------------------------------------- | ----------------------------------------------------------------------------- |
| [Scala Library][0]                          | [Apache-2.0][1]                                                               |
| [Import Export UDF Common Scala][2]         | [MIT License][3]                                                              |
| [Apache Avro][4]                            | [Apache-2.0][5]                                                               |
| [Jackson-core][6]                           | [The Apache Software License, Version 2.0][5]                                 |
| [error-reporting-java][7]                   | [MIT License][8]                                                              |
| [Apache Commons Compress][9]                | [Apache-2.0][5]                                                               |
| [Apache Kafka][10]                          | [The Apache License, Version 2.0][11]                                         |
| [kafka-avro-serializer][12]                 | [Apache License 2.0][13]                                                      |
| [scala-collection-compat][14]               | [Apache-2.0][1]                                                               |
| [Guava: Google Core Libraries for Java][15] | [Apache License, Version 2.0][11]                                             |
| [SLF4J API Module][16]                      | [MIT License][17]                                                             |
| [Logback Classic Module][18]                | [Eclipse Public License - v 1.0][19]; [GNU Lesser General Public License][20] |

### Test Dependencies

| Dependency                                 | License                                   |
| ------------------------------------------ | ----------------------------------------- |
| [scalatest][21]                            | [the Apache License, ASL Version 2.0][22] |
| [scalatestplus-mockito][23]                | [Apache-2.0][22]                          |
| [mockito-core][24]                         | [MIT][25]                                 |
| [Test containers for Exasol on Docker][26] | [MIT License][27]                         |
| [Test Database Builder for Java][28]       | [MIT License][29]                         |
| [Matcher for SQL Result Sets][30]          | [MIT License][31]                         |
| [Extension integration tests library][32]  | [MIT License][33]                         |
| [embedded-kafka-schema-registry][34]       | [MIT][25]                                 |
| [Netty/Codec][35]                          | [Apache License, Version 2.0][1]          |
| [Hibernate Validator Engine][36]           | [Apache License 2.0][11]                  |
| [kafka-streams-avro-serde][37]             | [Apache License 2.0][13]                  |
| [avro4s-core][38]                          | [MIT][25]                                 |
| [Testcontainers :: Kafka][39]              | [MIT][40]                                 |
| [ClassGraph][41]                           | [The MIT License (MIT)][40]               |
| [Protocol Buffers [Core]][42]              | [BSD-3-Clause][43]                        |
| [Maven Project Version Getter][44]         | [MIT License][45]                         |
| [Apache MINA Core][46]                     | [Apache 2.0 License][5]                   |

### Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [Apache Maven Clean Plugin][47]                         | [Apache-2.0][5]                               |
| [Apache Maven Install Plugin][48]                       | [Apache-2.0][5]                               |
| [Apache Maven Resources Plugin][49]                     | [Apache-2.0][5]                               |
| [Apache Maven Site Plugin][50]                          | [Apache-2.0][5]                               |
| [SonarQube Scanner for Maven][51]                       | [GNU LGPL 3][52]                              |
| [Apache Maven Toolchains Plugin][53]                    | [Apache-2.0][5]                               |
| [Apache Maven Compiler Plugin][54]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][55]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][56]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][57] | [ASL2][11]                                    |
| [scala-maven-plugin][58]                                | [Public domain (Unlicense)][59]               |
| [ScalaTest Maven Plugin][60]                            | [the Apache License, ASL Version 2.0][22]     |
| [Apache Maven Javadoc Plugin][61]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][62]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][63]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][64]          | [Apache License 2.0][13]                      |
| [Apache Maven Assembly Plugin][65]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][66]                           | [Apache-2.0][5]                               |
| [Project Keeper Maven plugin][67]                       | [The MIT License][68]                         |
| [OpenFastTrace Maven Plugin][69]                        | [GNU General Public License v3.0][70]         |
| [Scalastyle Maven Plugin][71]                           | [Apache 2.0][13]                              |
| [spotless-maven-plugin][72]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][73]                             | [BSD-3-Clause][43]                            |
| [Exec Maven Plugin][74]                                 | [Apache License 2][5]                         |
| [Artifact reference checker and unifier][75]            | [MIT License][76]                             |
| [Maven Failsafe Plugin][77]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][78]                            | [EPL-2.0][79]                                 |
| [Quality Summarizer Maven Plugin][80]                   | [MIT License][81]                             |
| [error-code-crawler-maven-plugin][82]                   | [MIT License][83]                             |
| [Reproducible Build Maven Plugin][84]                   | [Apache 2.0][11]                              |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][85] | MIT     |

[0]: https://www.scala-lang.org/
[1]: https://www.apache.org/licenses/LICENSE-2.0
[2]: https://github.com/exasol/import-export-udf-common-scala/
[3]: https://github.com/exasol/import-export-udf-common-scala/blob/main/LICENSE
[4]: https://avro.apache.org
[5]: https://www.apache.org/licenses/LICENSE-2.0.txt
[6]: https://github.com/FasterXML/jackson-core
[7]: https://github.com/exasol/error-reporting-java/
[8]: https://github.com/exasol/error-reporting-java/blob/main/LICENSE
[9]: https://commons.apache.org/proper/commons-compress/
[10]: https://kafka.apache.org
[11]: http://www.apache.org/licenses/LICENSE-2.0.txt
[12]: http://confluent.io/kafka-avro-serializer
[13]: http://www.apache.org/licenses/LICENSE-2.0.html
[14]: http://www.scala-lang.org/
[15]: https://github.com/google/guava
[16]: http://www.slf4j.org
[17]: http://www.opensource.org/licenses/mit-license.php
[18]: http://logback.qos.ch/logback-classic
[19]: http://www.eclipse.org/legal/epl-v10.html
[20]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[21]: http://www.scalatest.org
[22]: http://www.apache.org/licenses/LICENSE-2.0
[23]: https://github.com/scalatest/scalatestplus-mockito
[24]: https://github.com/mockito/mockito
[25]: https://opensource.org/licenses/MIT
[26]: https://github.com/exasol/exasol-testcontainers/
[27]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[28]: https://github.com/exasol/test-db-builder-java/
[29]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[30]: https://github.com/exasol/hamcrest-resultset-matcher/
[31]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[32]: https://github.com/exasol/extension-manager/
[33]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[34]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[35]: https://netty.io/netty-codec/
[36]: http://hibernate.org/validator/hibernate-validator
[37]: http://confluent.io/kafka-streams-avro-serde
[38]: https://github.com/sksamuel/avro4s
[39]: https://java.testcontainers.org
[40]: http://opensource.org/licenses/MIT
[41]: https://github.com/classgraph/classgraph
[42]: https://developers.google.com/protocol-buffers/protobuf-java/
[43]: https://opensource.org/licenses/BSD-3-Clause
[44]: https://github.com/exasol/maven-project-version-getter/
[45]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[46]: https://mina.apache.org/mina-core/
[47]: https://maven.apache.org/plugins/maven-clean-plugin/
[48]: https://maven.apache.org/plugins/maven-install-plugin/
[49]: https://maven.apache.org/plugins/maven-resources-plugin/
[50]: https://maven.apache.org/plugins/maven-site-plugin/
[51]: http://docs.sonarqube.org/display/PLUG/Plugin+Library/sonar-maven-plugin
[52]: http://www.gnu.org/licenses/lgpl.txt
[53]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[54]: https://maven.apache.org/plugins/maven-compiler-plugin/
[55]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[56]: https://www.mojohaus.org/flatten-maven-plugin/
[57]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[58]: http://github.com/davidB/scala-maven-plugin
[59]: http://unlicense.org/
[60]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[61]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[62]: https://maven.apache.org/surefire/maven-surefire-plugin/
[63]: https://www.mojohaus.org/versions/versions-maven-plugin/
[64]: https://basepom.github.io/duplicate-finder-maven-plugin
[65]: https://maven.apache.org/plugins/maven-assembly-plugin/
[66]: https://maven.apache.org/plugins/maven-jar-plugin/
[67]: https://github.com/exasol/project-keeper/
[68]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[69]: https://github.com/itsallcode/openfasttrace-maven-plugin
[70]: https://www.gnu.org/licenses/gpl-3.0.html
[71]: http://www.scalastyle.org
[72]: https://github.com/diffplug/spotless
[73]: https://github.com/evis/scalafix-maven-plugin
[74]: https://www.mojohaus.org/exec-maven-plugin
[75]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[76]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[77]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[78]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[79]: https://www.eclipse.org/legal/epl-2.0/
[80]: https://github.com/exasol/quality-summarizer-maven-plugin/
[81]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[82]: https://github.com/exasol/error-code-crawler-maven-plugin/
[83]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[84]: http://zlika.github.io/reproducible-build-maven-plugin
[85]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.3.tgz
