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
| [kafka-schema-registry-client][16]  | [Apache License 2.0][9]              |

### Test Dependencies

| Dependency                                 | License                                                                                                                                                                                            |
| ------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [scalatest][17]                            | [the Apache License, ASL Version 2.0][18]                                                                                                                                                          |
| [scalatestplus-mockito][19]                | [Apache-2.0][18]                                                                                                                                                                                   |
| [mockito-core][20]                         | [MIT][21]                                                                                                                                                                                          |
| [Test containers for Exasol on Docker][22] | [MIT License][23]                                                                                                                                                                                  |
| [Test Database Builder for Java][24]       | [MIT License][25]                                                                                                                                                                                  |
| [Matcher for SQL Result Sets][26]          | [MIT License][27]                                                                                                                                                                                  |
| [Extension integration tests library][28]  | [MIT License][29]                                                                                                                                                                                  |
| [embedded-kafka-schema-registry][30]       | [MIT][21]                                                                                                                                                                                          |
| [kafka-streams-avro-serde][31]             | [Apache License 2.0][9]                                                                                                                                                                            |
| [avro4s-core][32]                          | [MIT][21]                                                                                                                                                                                          |
| [Testcontainers :: Kafka][33]              | [MIT][34]                                                                                                                                                                                          |
| [Maven Project Version Getter][35]         | [MIT License][36]                                                                                                                                                                                  |
| [Jakarta XML Binding API][37]              | [Eclipse Distribution License - v 1.0][38]                                                                                                                                                         |
| [javax.ws.rs-api][39]                      | [EPL 2.0][40]; [GPL2 w/ CPE][41]                                                                                                                                                                   |
| [jersey-core-client][42]                   | [EPL 2.0][40]; [GPL2 w/ CPE][41]; [EDL 1.0][38]; [BSD 2-Clause][43]; [Apache License, 2.0][9]; [Public Domain][44]; [Modified BSD][45]; [jQuery license][46]; [MIT license][47]; [W3C license][48] |
| [jersey-media-multipart][49]               | [EPL 2.0][40]; [GPL2 w/ CPE][41]; [EDL 1.0][38]; [BSD 2-Clause][43]; [Apache License, 2.0][9]; [Public Domain][44]; [Modified BSD][45]; [jQuery license][46]; [MIT license][47]; [W3C license][48] |
| [jersey-media-json-jackson][50]            | [EPL 2.0][40]; [The GNU General Public License (GPL), Version 2, With Classpath Exception][41]; [Apache License, 2.0][9]                                                                           |
| [jersey-inject-hk2][51]                    | [EPL 2.0][40]; [GPL2 w/ CPE][41]; [EDL 1.0][38]; [BSD 2-Clause][43]; [Apache License, 2.0][9]; [Public Domain][44]; [Modified BSD][45]; [jQuery license][46]; [MIT license][47]; [W3C license][48] |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][52]                       | [GNU LGPL 3][53]                               |
| [Apache Maven Toolchains Plugin][54]                    | [Apache-2.0][55]                               |
| [Apache Maven Compiler Plugin][56]                      | [Apache-2.0][55]                               |
| [Apache Maven Enforcer Plugin][57]                      | [Apache-2.0][55]                               |
| [Maven Flatten Plugin][58]                              | [Apache Software License][55]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][59] | [ASL2][7]                                      |
| [scala-maven-plugin][60]                                | [Public domain (Unlicense)][61]                |
| [ScalaTest Maven Plugin][62]                            | [the Apache License, ASL Version 2.0][18]      |
| [Apache Maven Javadoc Plugin][63]                       | [Apache-2.0][55]                               |
| [Maven Surefire Plugin][64]                             | [Apache-2.0][55]                               |
| [Versions Maven Plugin][65]                             | [Apache License, Version 2.0][55]              |
| [duplicate-finder-maven-plugin Maven Mojo][66]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][67]                      | [Apache-2.0][55]                               |
| [Apache Maven Assembly Plugin][68]                      | [Apache-2.0][55]                               |
| [Apache Maven JAR Plugin][69]                           | [Apache-2.0][55]                               |
| [Project Keeper Maven plugin][70]                       | [The MIT License][71]                          |
| [OpenFastTrace Maven Plugin][72]                        | [GNU General Public License v3.0][73]          |
| [Scalastyle Maven Plugin][74]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][75]                             | [The Apache Software License, Version 2.0][55] |
| [scalafix-maven-plugin][76]                             | [BSD-3-Clause][77]                             |
| [Exec Maven Plugin][78]                                 | [Apache License 2][55]                         |
| [Artifact reference checker and unifier][79]            | [MIT License][80]                              |
| [Maven Failsafe Plugin][81]                             | [Apache-2.0][55]                               |
| [JaCoCo :: Maven Plugin][82]                            | [EPL-2.0][83]                                  |
| [Quality Summarizer Maven Plugin][84]                   | [MIT License][85]                              |
| [error-code-crawler-maven-plugin][86]                   | [MIT License][87]                              |
| [Git Commit Id Maven Plugin][88]                        | [GNU Lesser General Public License 3.0][89]    |
| [Apache Maven Clean Plugin][90]                         | [Apache-2.0][55]                               |
| [Apache Maven Resources Plugin][91]                     | [Apache-2.0][55]                               |
| [Apache Maven Install Plugin][92]                       | [Apache-2.0][55]                               |
| [Apache Maven Site Plugin][93]                          | [Apache-2.0][55]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][94] | MIT     |

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
[16]: http://confluent.io/kafka-schema-registry-client
[17]: http://www.scalatest.org
[18]: http://www.apache.org/licenses/LICENSE-2.0
[19]: https://github.com/scalatest/scalatestplus-mockito
[20]: https://github.com/mockito/mockito
[21]: https://opensource.org/licenses/MIT
[22]: https://github.com/exasol/exasol-testcontainers/
[23]: https://github.com/exasol/exasol-testcontainers/blob/main/LICENSE
[24]: https://github.com/exasol/test-db-builder-java/
[25]: https://github.com/exasol/test-db-builder-java/blob/main/LICENSE
[26]: https://github.com/exasol/hamcrest-resultset-matcher/
[27]: https://github.com/exasol/hamcrest-resultset-matcher/blob/main/LICENSE
[28]: https://github.com/exasol/extension-manager/
[29]: https://github.com/exasol/extension-manager/blob/main/LICENSE
[30]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[31]: http://confluent.io/kafka-streams-avro-serde
[32]: https://github.com/sksamuel/avro4s
[33]: https://java.testcontainers.org
[34]: http://opensource.org/licenses/MIT
[35]: https://github.com/exasol/maven-project-version-getter/
[36]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[37]: https://github.com/jakartaee/jaxb-api/jakarta.xml.bind-api
[38]: http://www.eclipse.org/org/documents/edl-v10.php
[39]: https://github.com/eclipse-ee4j/jaxrs-api
[40]: http://www.eclipse.org/legal/epl-2.0
[41]: https://www.gnu.org/software/classpath/license.html
[42]: https://projects.eclipse.org/projects/ee4j.jersey/jersey-client
[43]: https://opensource.org/licenses/BSD-2-Clause
[44]: https://creativecommons.org/publicdomain/zero/1.0/
[45]: https://asm.ow2.io/license.html
[46]: jquery.org/license
[47]: http://www.opensource.org/licenses/mit-license.php
[48]: https://www.w3.org/Consortium/Legal/copyright-documents-19990405
[49]: https://projects.eclipse.org/projects/ee4j.jersey/project/jersey-media-multipart
[50]: https://projects.eclipse.org/projects/ee4j.jersey/project/jersey-media-json-jackson
[51]: https://projects.eclipse.org/projects/ee4j.jersey/project/jersey-hk2
[52]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[53]: http://www.gnu.org/licenses/lgpl.txt
[54]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[55]: https://www.apache.org/licenses/LICENSE-2.0.txt
[56]: https://maven.apache.org/plugins/maven-compiler-plugin/
[57]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[58]: https://www.mojohaus.org/flatten-maven-plugin/
[59]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[60]: https://github.com/davidB/scala-maven-plugin
[61]: https://unlicense.org/
[62]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[63]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[64]: https://maven.apache.org/surefire/maven-surefire-plugin/
[65]: https://www.mojohaus.org/versions/versions-maven-plugin/
[66]: https://basepom.github.io/duplicate-finder-maven-plugin
[67]: https://maven.apache.org/plugins/maven-artifact-plugin/
[68]: https://maven.apache.org/plugins/maven-assembly-plugin/
[69]: https://maven.apache.org/plugins/maven-jar-plugin/
[70]: https://github.com/exasol/project-keeper/
[71]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[72]: https://github.com/itsallcode/openfasttrace-maven-plugin
[73]: https://www.gnu.org/licenses/gpl-3.0.html
[74]: http://www.scalastyle.org
[75]: https://github.com/diffplug/spotless
[76]: https://github.com/evis/scalafix-maven-plugin
[77]: https://opensource.org/licenses/BSD-3-Clause
[78]: https://www.mojohaus.org/exec-maven-plugin
[79]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[80]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[81]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[82]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[83]: https://www.eclipse.org/legal/epl-2.0/
[84]: https://github.com/exasol/quality-summarizer-maven-plugin/
[85]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[86]: https://github.com/exasol/error-code-crawler-maven-plugin/
[87]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[88]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[89]: http://www.gnu.org/licenses/lgpl-3.0.txt
[90]: https://maven.apache.org/plugins/maven-clean-plugin/
[91]: https://maven.apache.org/plugins/maven-resources-plugin/
[92]: https://maven.apache.org/plugins/maven-install-plugin/
[93]: https://maven.apache.org/plugins/maven-site-plugin/
[94]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
