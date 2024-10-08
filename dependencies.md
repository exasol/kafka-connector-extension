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
| [kafka-avro-serializer][10]                 | [Apache License 2.0][11]                                                      |
| [scala-collection-compat][12]               | [Apache-2.0][1]                                                               |
| [Guava: Google Core Libraries for Java][13] | [Apache License, Version 2.0][14]                                             |
| [SLF4J API Module][15]                      | [MIT License][16]                                                             |
| [Logback Classic Module][17]                | [Eclipse Public License - v 1.0][18]; [GNU Lesser General Public License][19] |
| [Apache Kafka][20]                          | [The Apache License, Version 2.0][14]                                         |
| [snappy-java][21]                           | [Apache-2.0][22]                                                              |

### Test Dependencies

| Dependency                                 | License                                                                                 |
| ------------------------------------------ | --------------------------------------------------------------------------------------- |
| [scalatest][23]                            | [the Apache License, ASL Version 2.0][24]                                               |
| [scalatestplus-mockito][25]                | [Apache-2.0][24]                                                                        |
| [mockito-core][26]                         | [MIT][27]                                                                               |
| [Test containers for Exasol on Docker][28] | [MIT License][29]                                                                       |
| [Test Database Builder for Java][30]       | [MIT License][31]                                                                       |
| [Matcher for SQL Result Sets][32]          | [MIT License][33]                                                                       |
| [Extension integration tests library][34]  | [MIT License][35]                                                                       |
| [embedded-kafka-schema-registry][36]       | [MIT][27]                                                                               |
| [Apache Kafka][20]                         | [The Apache License, Version 2.0][14]                                                   |
| [JSON in Java][37]                         | [Public Domain][38]                                                                     |
| [Apache ZooKeeper - Server][39]            | [Apache License, Version 2.0][5]                                                        |
| [jose4j][40]                               | [The Apache Software License, Version 2.0][14]                                          |
| [Jetty :: HTTP2 :: Server][41]             | [Apache Software License - Version 2.0][24]; [Eclipse Public License - Version 1.0][42] |
| [kafka-streams-avro-serde][43]             | [Apache License 2.0][11]                                                                |
| [avro4s-core][44]                          | [MIT][27]                                                                               |
| [Testcontainers :: Kafka][45]              | [MIT][46]                                                                               |
| [Joda-Time][47]                            | [Apache License, Version 2.0][5]                                                        |
| [ClassGraph][48]                           | [The MIT License (MIT)][46]                                                             |
| [Protocol Buffers [Core]][49]              | [BSD-3-Clause][50]                                                                      |

### Plugin Dependencies

| Dependency                                              | License                                       |
| ------------------------------------------------------- | --------------------------------------------- |
| [SonarQube Scanner for Maven][51]                       | [GNU LGPL 3][52]                              |
| [Apache Maven Toolchains Plugin][53]                    | [Apache-2.0][5]                               |
| [Apache Maven Compiler Plugin][54]                      | [Apache-2.0][5]                               |
| [Apache Maven Enforcer Plugin][55]                      | [Apache-2.0][5]                               |
| [Maven Flatten Plugin][56]                              | [Apache Software Licenese][5]                 |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][57] | [ASL2][14]                                    |
| [scala-maven-plugin][58]                                | [Public domain (Unlicense)][59]               |
| [ScalaTest Maven Plugin][60]                            | [the Apache License, ASL Version 2.0][24]     |
| [Apache Maven Javadoc Plugin][61]                       | [Apache-2.0][5]                               |
| [Maven Surefire Plugin][62]                             | [Apache-2.0][5]                               |
| [Versions Maven Plugin][63]                             | [Apache License, Version 2.0][5]              |
| [duplicate-finder-maven-plugin Maven Mojo][64]          | [Apache License 2.0][11]                      |
| [Apache Maven Assembly Plugin][65]                      | [Apache-2.0][5]                               |
| [Apache Maven JAR Plugin][66]                           | [Apache-2.0][5]                               |
| [Artifact reference checker and unifier][67]            | [MIT License][68]                             |
| [Maven Failsafe Plugin][69]                             | [Apache-2.0][5]                               |
| [JaCoCo :: Maven Plugin][70]                            | [EPL-2.0][71]                                 |
| [error-code-crawler-maven-plugin][72]                   | [MIT License][73]                             |
| [Reproducible Build Maven Plugin][74]                   | [Apache 2.0][14]                              |
| [Project Keeper Maven plugin][75]                       | [The MIT License][76]                         |
| [OpenFastTrace Maven Plugin][77]                        | [GNU General Public License v3.0][78]         |
| [Scalastyle Maven Plugin][79]                           | [Apache 2.0][11]                              |
| [spotless-maven-plugin][80]                             | [The Apache Software License, Version 2.0][5] |
| [scalafix-maven-plugin][81]                             | [BSD-3-Clause][50]                            |
| [Exec Maven Plugin][82]                                 | [Apache License 2][5]                         |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][83] | MIT     |

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
[15]: http://www.slf4j.org
[16]: http://www.opensource.org/licenses/mit-license.php
[17]: http://logback.qos.ch/logback-classic
[18]: http://www.eclipse.org/legal/epl-v10.html
[19]: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
[20]: https://kafka.apache.org
[21]: https://github.com/xerial/snappy-java
[22]: https://www.apache.org/licenses/LICENSE-2.0.html
[23]: http://www.scalatest.org
[24]: http://www.apache.org/licenses/LICENSE-2.0
[25]: https://github.com/scalatest/scalatestplus-mockito
[26]: https://github.com/mockito/mockito
[27]: https://opensource.org/licenses/MIT
[28]: https://github.com/exasol/exasol-testcontainers/
[29]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[30]: https://github.com/exasol/test-db-builder-java/
[31]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[32]: https://github.com/exasol/hamcrest-resultset-matcher/
[33]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[34]: https://github.com/exasol/extension-manager/
[35]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[36]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[37]: https://github.com/douglascrockford/JSON-java
[38]: https://github.com/stleary/JSON-java/blob/master/LICENSE
[39]: http://zookeeper.apache.org/zookeeper
[40]: https://bitbucket.org/b_c/jose4j/
[41]: https://eclipse.org/jetty/http2-parent/http2-server
[42]: https://www.eclipse.org/org/documents/epl-v10.php
[43]: http://confluent.io/kafka-streams-avro-serde
[44]: https://github.com/sksamuel/avro4s
[45]: https://java.testcontainers.org
[46]: http://opensource.org/licenses/MIT
[47]: https://www.joda.org/joda-time/
[48]: https://github.com/classgraph/classgraph
[49]: https://developers.google.com/protocol-buffers/protobuf-java/
[50]: https://opensource.org/licenses/BSD-3-Clause
[51]: http://sonarsource.github.io/sonar-scanner-maven/
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
[67]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[68]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[69]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[70]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[71]: https://www.eclipse.org/legal/epl-2.0/
[72]: https://github.com/exasol/error-code-crawler-maven-plugin/
[73]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[74]: http://zlika.github.io/reproducible-build-maven-plugin
[75]: https://github.com/exasol/project-keeper/
[76]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[77]: https://github.com/itsallcode/openfasttrace-maven-plugin
[78]: https://www.gnu.org/licenses/gpl-3.0.html
[79]: http://www.scalastyle.org
[80]: https://github.com/diffplug/spotless
[81]: https://github.com/evis/scalafix-maven-plugin
[82]: https://www.mojohaus.org/exec-maven-plugin
[83]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.4.1.tgz
