<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                                  | License                                       |
| ------------------------------------------- | --------------------------------------------- |
| [Scala Library][0]                          | [Apache-2.0][1]                               |
| [Import Export UDF Common Scala][2]         | [MIT License][3]                              |
| [Apache Avro][4]                            | [Apache-2.0][5]                               |
| [Jackson-core][6]                           | [The Apache Software License, Version 2.0][5] |
| [error-reporting-java][7]                   | [MIT License][8]                              |
| [Apache Commons Compress][9]                | [Apache-2.0][5]                               |
| [kafka-avro-serializer][10]                 | [Apache License 2.0][11]                      |
| [scala-collection-compat][12]               | [Apache-2.0][1]                               |
| [Guava: Google Core Libraries for Java][13] | [Apache License, Version 2.0][14]             |
| [Apache Kafka][15]                          | [The Apache License, Version 2.0][14]         |
| [snappy-java][16]                           | [Apache-2.0][17]                              |

### Test Dependencies

| Dependency                                 | License                                                                                 |
| ------------------------------------------ | --------------------------------------------------------------------------------------- |
| [scalatest][18]                            | [the Apache License, ASL Version 2.0][19]                                               |
| [scalatestplus-mockito][20]                | [Apache-2.0][19]                                                                        |
| [mockito-core][21]                         | [MIT][22]                                                                               |
| [Test containers for Exasol on Docker][23] | [MIT License][24]                                                                       |
| [Test Database Builder for Java][25]       | [MIT License][26]                                                                       |
| [Matcher for SQL Result Sets][27]          | [MIT License][28]                                                                       |
| [Extension integration tests library][29]  | [MIT License][30]                                                                       |
| [embedded-kafka-schema-registry][31]       | [MIT][22]                                                                               |
| [Apache Kafka][15]                         | [The Apache License, Version 2.0][14]                                                   |
| [JSON in Java][32]                         | [Public Domain][33]                                                                     |
| [Apache ZooKeeper - Server][34]            | [Apache License, Version 2.0][5]                                                        |
| [jose4j][35]                               | [The Apache Software License, Version 2.0][14]                                          |
| [Jetty :: HTTP2 :: Server][36]             | [Apache Software License - Version 2.0][19]; [Eclipse Public License - Version 1.0][37] |
| [Logback Core Module][38]                  | [Eclipse Public License - v 1.0][39]; [GNU Lesser General Public License][40]           |
| [Logback Classic Module][41]               | [Eclipse Public License - v 1.0][39]; [GNU Lesser General Public License][40]           |
| [kafka-streams-avro-serde][42]             | [Apache License 2.0][11]                                                                |
| [avro4s-core][43]                          | [MIT][22]                                                                               |
| [Testcontainers :: Kafka][44]              | [MIT][45]                                                                               |
| [Joda-Time][46]                            | [Apache License, Version 2.0][5]                                                        |
| [ClassGraph][47]                           | [The MIT License (MIT)][45]                                                             |

### Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][48]                       | [GNU LGPL 3][49]                              |
| [Apache Maven Toolchains Plugin][50]                    | [Apache-2.0][5]                               |
| [Apache Maven Compiler Plugin][51]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][52]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][53]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][54] | [ASL2][14]                                    |
| [scala-maven-plugin][55]                                | [Public domain (Unlicense)][56]               |
| [ScalaTest Maven Plugin][57]                            | [the Apache License, ASL Version 2.0][19]     |
| [Apache Maven Javadoc Plugin][58]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][59]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][60]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][61]          | [Apache License 2.0][11]                      |
| [Apache Maven Assembly Plugin][62]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][63]                           | [Apache-2.0][5]                               |
| [Artifact reference checker and unifier][64]            | [MIT License][65]                             |
| [Maven Failsafe Plugin][66]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][67]                            | [EPL-2.0][68]                                 |
| [error-code-crawler-maven-plugin][69]                   | [MIT License][70]                             |
| [Reproducible Build Maven Plugin][71]                   | [Apache 2.0][14]                              |
| [Project Keeper Maven plugin][72]                       | [The MIT License][73]                         |
| [OpenFastTrace Maven Plugin][74]                        | [GNU General Public License v3.0][75]         |
| [Scalastyle Maven Plugin][76]                           | [Apache 2.0][11]                              |
| [spotless-maven-plugin][77]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][78]                             | [BSD-3-Clause][79]                            |
| [Exec Maven Plugin][80]                                 | [Apache License 2][5]                         |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][81] | MIT     |

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
[10]: http://confluent.io/kafka-avro-serializer
[11]: http://www.apache.org/licenses/LICENSE-2.0.html
[12]: http://www.scala-lang.org/
[13]: https://github.com/google/guava
[14]: http://www.apache.org/licenses/LICENSE-2.0.txt
[15]: https://kafka.apache.org
[16]: https://github.com/xerial/snappy-java
[17]: https://www.apache.org/licenses/LICENSE-2.0.html
[18]: http://www.scalatest.org
[19]: http://www.apache.org/licenses/LICENSE-2.0
[20]: https://github.com/scalatest/scalatestplus-mockito
[21]: https://github.com/mockito/mockito
[22]: https://opensource.org/licenses/MIT
[23]: https://github.com/exasol/exasol-testcontainers/
[24]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[25]: https://github.com/exasol/test-db-builder-java/
[26]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[27]: https://github.com/exasol/hamcrest-resultset-matcher/
[28]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[29]: https://github.com/exasol/extension-manager/
[30]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[31]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[32]: https://github.com/douglascrockford/JSON-java
[33]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[34]: http://zookeeper.apache.org/zookeeper
[35]: https://bitbucket.org/b_c/jose4j/
[36]: https://eclipse.org/jetty/http2-parent/http2-server
[37]: https://www.eclipse.org/org/documents/epl-v10.php
[38]: http://logback.qos.ch/logback-core
[39]: http://www.eclipse.org/legal/epl-v10.html
[40]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[41]: http://logback.qos.ch/logback-classic
[42]: http://confluent.io/kafka-streams-avro-serde
[43]: https://github.com/sksamuel/avro4s
[44]: https://java.testcontainers.org
[45]: http://opensource.org/licenses/MIT
[46]: https://www.joda.org/joda-time/
[47]: https://github.com/classgraph/classgraph
[48]: http://sonarsource.github.io/sonar-scanner-maven/
[49]: http://www.gnu.org/licenses/lgpl.txt
[50]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[51]: https://maven.apache.org/plugins/maven-compiler-plugin/
[52]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[53]: https://www.mojohaus.org/flatten-maven-plugin/
[54]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[55]: http://github.com/davidB/scala-maven-plugin
[56]: http://unlicense.org/
[57]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[58]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[59]: https://maven.apache.org/surefire/maven-surefire-plugin/
[60]: https://www.mojohaus.org/versions/versions-maven-plugin/
[61]: https://basepom.github.io/duplicate-finder-maven-plugin
[62]: https://maven.apache.org/plugins/maven-assembly-plugin/
[63]: https://maven.apache.org/plugins/maven-jar-plugin/
[64]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[65]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[66]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[67]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[68]: https://www.eclipse.org/legal/epl-2.0/
[69]: https://github.com/exasol/error-code-crawler-maven-plugin/
[70]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[71]: http://zlika.github.io/reproducible-build-maven-plugin
[72]: https://github.com/exasol/project-keeper/
[73]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[74]: https://github.com/itsallcode/openfasttrace-maven-plugin
[75]: https://www.gnu.org/licenses/gpl-3.0.html
[76]: http://www.scalastyle.org
[77]: https://github.com/diffplug/spotless
[78]: https://github.com/evis/scalafix-maven-plugin
[79]: https://opensource.org/licenses/BSD-3-Clause
[80]: https://www.mojohaus.org/exec-maven-plugin
[81]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.1.tgz
