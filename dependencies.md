<!-- @formatter:off -->
# Dependencies

## Exasol Kafka Connector Extension

### Compile Dependencies

| Dependency                          | License                                                                       |
| ----------------------------------- | ----------------------------------------------------------------------------- |
| [Scala Library][0]                  | [Apache-2.0][1]                                                               |
| [Import Export UDF Common Scala][2] | [MIT License][3]                                                              |
| [error-reporting-java][4]           | [MIT License][5]                                                              |
| [Apache Kafka][6]                   | [The Apache License, Version 2.0][7]                                          |
| [kafka-avro-serializer][8]          | [Apache License 2.0][9]                                                       |
| [scala-collection-compat][10]       | [Apache-2.0][1]                                                               |
| [SLF4J API Module][11]              | [MIT][12]                                                                     |
| [Logback Classic Module][13]        | [Eclipse Public License - v 1.0][14]; [GNU Lesser General Public License][15] |

### Test Dependencies

| Dependency                                 | License                                                                                                                                                                                            |
| ------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [scalatest][16]                            | [the Apache License, ASL Version 2.0][17]                                                                                                                                                          |
| [scalatestplus-mockito][18]                | [Apache-2.0][17]                                                                                                                                                                                   |
| [mockito-core][19]                         | [MIT][20]                                                                                                                                                                                          |
| [Test containers for Exasol on Docker][21] | [MIT License][22]                                                                                                                                                                                  |
| [Test Database Builder for Java][23]       | [MIT License][24]                                                                                                                                                                                  |
| [Matcher for SQL Result Sets][25]          | [MIT License][26]                                                                                                                                                                                  |
| [Extension integration tests library][27]  | [MIT License][28]                                                                                                                                                                                  |
| [embedded-kafka][29]                       | [MIT][20]                                                                                                                                                                                          |
| [embedded-kafka-schema-registry][30]       | [MIT][20]                                                                                                                                                                                          |
| [kafka-schema-registry-client][31]         | [Apache License 2.0][9]                                                                                                                                                                            |
| [kafka-streams-avro-serde][32]             | [Apache License 2.0][9]                                                                                                                                                                            |
| [avro4s-core][33]                          | [MIT][20]                                                                                                                                                                                          |
| [Testcontainers :: Kafka][34]              | [MIT][35]                                                                                                                                                                                          |
| [Maven Project Version Getter][36]         | [MIT License][37]                                                                                                                                                                                  |
| [Apache MINA Core][38]                     | [Apache 2.0 License][39]                                                                                                                                                                           |
| [Jakarta XML Binding API][40]              | [Eclipse Distribution License - v 1.0][41]                                                                                                                                                         |
| [javax.ws.rs-api][42]                      | [EPL 2.0][43]; [GPL2 w/ CPE][44]                                                                                                                                                                   |
| [jersey-core-client][45]                   | [EPL 2.0][43]; [GPL2 w/ CPE][44]; [EDL 1.0][41]; [BSD 2-Clause][46]; [Apache License, 2.0][9]; [Public Domain][47]; [Modified BSD][48]; [jQuery license][49]; [MIT license][50]; [W3C license][51] |
| [jersey-media-multipart][52]               | [EPL 2.0][43]; [GPL2 w/ CPE][44]; [EDL 1.0][41]; [BSD 2-Clause][46]; [Apache License, 2.0][9]; [Public Domain][47]; [Modified BSD][48]; [jQuery license][49]; [MIT license][50]; [W3C license][51] |
| [jersey-media-json-jackson][53]            | [EPL 2.0][43]; [The GNU General Public License (GPL), Version 2, With Classpath Exception][44]; [Apache License, 2.0][9]                                                                           |
| [jersey-inject-hk2][54]                    | [EPL 2.0][43]; [GPL2 w/ CPE][44]; [EDL 1.0][41]; [BSD 2-Clause][46]; [Apache License, 2.0][9]; [Public Domain][47]; [Modified BSD][48]; [jQuery license][49]; [MIT license][50]; [W3C license][51] |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][55]                       | [GNU LGPL 3][56]                               |
| [Apache Maven Toolchains Plugin][57]                    | [Apache-2.0][39]                               |
| [Apache Maven Compiler Plugin][58]                      | [Apache-2.0][39]                               |
| [Apache Maven Enforcer Plugin][59]                      | [Apache-2.0][39]                               |
| [Maven Flatten Plugin][60]                              | [Apache Software License][39]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][61] | [ASL2][7]                                      |
| [scala-maven-plugin][62]                                | [Public domain (Unlicense)][63]                |
| [ScalaTest Maven Plugin][64]                            | [the Apache License, ASL Version 2.0][17]      |
| [Apache Maven Javadoc Plugin][65]                       | [Apache-2.0][39]                               |
| [Maven Surefire Plugin][66]                             | [Apache-2.0][39]                               |
| [Versions Maven Plugin][67]                             | [Apache License, Version 2.0][39]              |
| [duplicate-finder-maven-plugin Maven Mojo][68]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][69]                      | [Apache-2.0][39]                               |
| [Apache Maven Assembly Plugin][70]                      | [Apache-2.0][39]                               |
| [Apache Maven JAR Plugin][71]                           | [Apache-2.0][39]                               |
| [Project Keeper Maven plugin][72]                       | [The MIT License][73]                          |
| [OpenFastTrace Maven Plugin][74]                        | [GNU General Public License v3.0][75]          |
| [Scalastyle Maven Plugin][76]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][77]                             | [The Apache Software License, Version 2.0][39] |
| [scalafix-maven-plugin][78]                             | [BSD-3-Clause][79]                             |
| [Exec Maven Plugin][80]                                 | [Apache License 2][39]                         |
| [Artifact reference checker and unifier][81]            | [MIT License][82]                              |
| [Maven Failsafe Plugin][83]                             | [Apache-2.0][39]                               |
| [JaCoCo :: Maven Plugin][84]                            | [EPL-2.0][85]                                  |
| [Quality Summarizer Maven Plugin][86]                   | [MIT License][87]                              |
| [error-code-crawler-maven-plugin][88]                   | [MIT License][89]                              |
| [Git Commit Id Maven Plugin][90]                        | [GNU Lesser General Public License 3.0][91]    |
| [Apache Maven Clean Plugin][92]                         | [Apache-2.0][39]                               |
| [Apache Maven Resources Plugin][93]                     | [Apache-2.0][39]                               |
| [Apache Maven Install Plugin][94]                       | [Apache-2.0][39]                               |
| [Apache Maven Site Plugin][95]                          | [Apache-2.0][39]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][96] | MIT     |

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
[16]: http://www.scalatest.org
[17]: http://www.apache.org/licenses/LICENSE-2.0
[18]: https://github.com/scalatest/scalatestplus-mockito
[19]: https://github.com/mockito/mockito
[20]: https://opensource.org/licenses/MIT
[21]: https://github.com/exasol/exasol-testcontainers/
[22]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[23]: https://github.com/exasol/test-db-builder-java/
[24]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[25]: https://github.com/exasol/hamcrest-resultset-matcher/
[26]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[27]: https://github.com/exasol/extension-manager/
[28]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[29]: https://github.com/embeddedkafka/embedded-kafka
[30]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[31]: http://confluent.io/kafka-schema-registry-client
[32]: http://confluent.io/kafka-streams-avro-serde
[33]: https://github.com/sksamuel/avro4s
[34]: https://java.testcontainers.org
[35]: http://opensource.org/licenses/MIT
[36]: https://github.com/exasol/maven-project-version-getter/
[37]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[38]: https://mina.apache.org/mina-core/
[39]: https://www.apache.org/licenses/LICENSE-2.0.txt
[40]: https://github.com/jakartaee/jaxb-api/jakarta.xml.bind-api
[41]: http://www.eclipse.org/org/documents/edl-v10.php
[42]: https://github.com/eclipse-ee4j/jaxrs-api
[43]: http://www.eclipse.org/legal/epl-2.0
[44]: https://www.gnu.org/software/classpath/license.html
[45]: https://projects.eclipse.org/projects/ee4j.jersey/jersey-client
[46]: https://opensource.org/licenses/BSD-2-Clause
[47]: https://creativecommons.org/publicdomain/zero/1.0/
[48]: https://asm.ow2.io/license.html
[49]: jquery.org/license
[50]: http://www.opensource.org/licenses/mit-license.php
[51]: https://www.w3.org/Consortium/Legal/copyright-documents-19990405
[52]: https://projects.eclipse.org/projects/ee4j.jersey/project/jersey-media-multipart
[53]: https://projects.eclipse.org/projects/ee4j.jersey/project/jersey-media-json-jackson
[54]: https://projects.eclipse.org/projects/ee4j.jersey/project/jersey-hk2
[55]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[56]: http://www.gnu.org/licenses/lgpl.txt
[57]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[58]: https://maven.apache.org/plugins/maven-compiler-plugin/
[59]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[60]: https://www.mojohaus.org/flatten-maven-plugin/
[61]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[62]: https://github.com/davidB/scala-maven-plugin
[63]: https://unlicense.org/
[64]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[65]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[66]: https://maven.apache.org/surefire/maven-surefire-plugin/
[67]: https://www.mojohaus.org/versions/versions-maven-plugin/
[68]: https://basepom.github.io/duplicate-finder-maven-plugin
[69]: https://maven.apache.org/plugins/maven-artifact-plugin/
[70]: https://maven.apache.org/plugins/maven-assembly-plugin/
[71]: https://maven.apache.org/plugins/maven-jar-plugin/
[72]: https://github.com/exasol/project-keeper/
[73]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[74]: https://github.com/itsallcode/openfasttrace-maven-plugin
[75]: https://www.gnu.org/licenses/gpl-3.0.html
[76]: http://www.scalastyle.org
[77]: https://github.com/diffplug/spotless
[78]: https://github.com/evis/scalafix-maven-plugin
[79]: https://opensource.org/licenses/BSD-3-Clause
[80]: https://www.mojohaus.org/exec-maven-plugin
[81]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[82]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[83]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[84]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[85]: https://www.eclipse.org/legal/epl-2.0/
[86]: https://github.com/exasol/quality-summarizer-maven-plugin/
[87]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[88]: https://github.com/exasol/error-code-crawler-maven-plugin/
[89]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[90]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[91]: http://www.gnu.org/licenses/lgpl-3.0.txt
[92]: https://maven.apache.org/plugins/maven-clean-plugin/
[93]: https://maven.apache.org/plugins/maven-resources-plugin/
[94]: https://maven.apache.org/plugins/maven-install-plugin/
[95]: https://maven.apache.org/plugins/maven-site-plugin/
[96]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
