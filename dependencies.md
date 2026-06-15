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
| [Gson][16]                          | [Apache-2.0][17]                                                              |

### Test Dependencies

| Dependency                                 | License                                                                                                                                                                                            |
| ------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [scalatest][18]                            | [the Apache License, ASL Version 2.0][19]                                                                                                                                                          |
| [scalatestplus-mockito][20]                | [Apache-2.0][19]                                                                                                                                                                                   |
| [mockito-core][21]                         | [MIT][22]                                                                                                                                                                                          |
| [Test containers for Exasol on Docker][23] | [MIT License][24]                                                                                                                                                                                  |
| [Test Database Builder for Java][25]       | [MIT License][26]                                                                                                                                                                                  |
| [Matcher for SQL Result Sets][27]          | [MIT License][28]                                                                                                                                                                                  |
| [Extension integration tests library][29]  | [MIT License][30]                                                                                                                                                                                  |
| [embedded-kafka][31]                       | [MIT][22]                                                                                                                                                                                          |
| [embedded-kafka-schema-registry][32]       | [MIT][22]                                                                                                                                                                                          |
| [kafka-streams-avro-serde][33]             | [Apache License 2.0][9]                                                                                                                                                                            |
| [avro4s-core][34]                          | [MIT][22]                                                                                                                                                                                          |
| [Testcontainers :: Kafka][35]              | [MIT][36]                                                                                                                                                                                          |
| [Maven Project Version Getter][37]         | [MIT License][38]                                                                                                                                                                                  |
| [Apache MINA Core][39]                     | [Apache 2.0 License][17]                                                                                                                                                                           |
| [Jakarta XML Binding API][40]              | [Eclipse Distribution License - v 1.0][41]                                                                                                                                                         |
| [javax.ws.rs-api][42]                      | [EPL 2.0][43]; [GPL2 w/ CPE][44]                                                                                                                                                                   |
| [jersey-core-client][45]                   | [EPL 2.0][43]; [GPL2 w/ CPE][44]; [EDL 1.0][41]; [BSD 2-Clause][46]; [Apache License, 2.0][9]; [Public Domain][47]; [Modified BSD][48]; [jQuery license][49]; [MIT license][50]; [W3C license][51] |

### Plugin Dependencies

| Dependency                                              | License                                        |
| ------------------------------------------------------- | ---------------------------------------------- |
| [SonarQube Scanner for Maven][52]                       | [GNU LGPL 3][53]                               |
| [Apache Maven Toolchains Plugin][54]                    | [Apache-2.0][17]                               |
| [Apache Maven Compiler Plugin][55]                      | [Apache-2.0][17]                               |
| [Apache Maven Enforcer Plugin][56]                      | [Apache-2.0][17]                               |
| [Maven Flatten Plugin][57]                              | [Apache Software License][17]                  |
| [org.sonatype.ossindex.maven:ossindex-maven-plugin][58] | [ASL2][7]                                      |
| [scala-maven-plugin][59]                                | [Public domain (Unlicense)][60]                |
| [ScalaTest Maven Plugin][61]                            | [the Apache License, ASL Version 2.0][19]      |
| [Apache Maven Javadoc Plugin][62]                       | [Apache-2.0][17]                               |
| [Maven Surefire Plugin][63]                             | [Apache-2.0][17]                               |
| [Versions Maven Plugin][64]                             | [Apache License, Version 2.0][17]              |
| [duplicate-finder-maven-plugin Maven Mojo][65]          | [Apache License 2.0][9]                        |
| [Apache Maven Artifact Plugin][66]                      | [Apache-2.0][17]                               |
| [Apache Maven Assembly Plugin][67]                      | [Apache-2.0][17]                               |
| [Apache Maven JAR Plugin][68]                           | [Apache-2.0][17]                               |
| [Project Keeper Maven plugin][69]                       | [The MIT License][70]                          |
| [OpenFastTrace Maven Plugin][71]                        | [GNU General Public License v3.0][72]          |
| [Scalastyle Maven Plugin][73]                           | [Apache 2.0][9]                                |
| [spotless-maven-plugin][74]                             | [The Apache Software License, Version 2.0][17] |
| [scalafix-maven-plugin][75]                             | [BSD-3-Clause][76]                             |
| [Exec Maven Plugin][77]                                 | [Apache License 2][17]                         |
| [Artifact reference checker and unifier][78]            | [MIT License][79]                              |
| [Maven Failsafe Plugin][80]                             | [Apache-2.0][17]                               |
| [JaCoCo :: Maven Plugin][81]                            | [EPL-2.0][82]                                  |
| [Quality Summarizer Maven Plugin][83]                   | [MIT License][84]                              |
| [error-code-crawler-maven-plugin][85]                   | [MIT License][86]                              |
| [Git Commit Id Maven Plugin][87]                        | [GNU Lesser General Public License 3.0][88]    |
| [Apache Maven Clean Plugin][89]                         | [Apache-2.0][17]                               |
| [Apache Maven Resources Plugin][90]                     | [Apache-2.0][17]                               |
| [Apache Maven Install Plugin][91]                       | [Apache-2.0][17]                               |
| [Apache Maven Site Plugin][92]                          | [Apache-2.0][17]                               |

## Extension

### Compile Dependencies

| Dependency                                | License |
| ----------------------------------------- | ------- |
| [@exasol/extension-manager-interface][93] | MIT     |

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
[16]: https://github.com/google/gson
[17]: https://www.apache.org/licenses/LICENSE-2.0.txt
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
[31]: https://github.com/embeddedkafka/embedded-kafka
[32]: https://github.com/embeddedkafka/embedded-kafka-schema-registry
[33]: http://confluent.io/kafka-streams-avro-serde
[34]: https://github.com/sksamuel/avro4s
[35]: https://java.testcontainers.org
[36]: http://opensource.org/licenses/MIT
[37]: https://github.com/exasol/maven-project-version-getter/
[38]: https://github.com/exasol/maven-project-version-getter/blob/main/LICENSE
[39]: https://mina.apache.org/mina-core/
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
[52]: https://docs.sonarsource.com/sonarqube-server/latest/extension-guide/developing-a-plugin/plugin-basics/sonar-scanner-maven/sonar-maven-plugin/
[53]: http://www.gnu.org/licenses/lgpl.txt
[54]: https://maven.apache.org/plugins/maven-toolchains-plugin/
[55]: https://maven.apache.org/plugins/maven-compiler-plugin/
[56]: https://maven.apache.org/enforcer/maven-enforcer-plugin/
[57]: https://www.mojohaus.org/flatten-maven-plugin/
[58]: https://sonatype.github.io/ossindex-maven/maven-plugin/
[59]: https://github.com/davidB/scala-maven-plugin
[60]: https://unlicense.org/
[61]: https://www.scalatest.org/user_guide/using_the_scalatest_maven_plugin
[62]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[63]: https://maven.apache.org/surefire/maven-surefire-plugin/
[64]: https://www.mojohaus.org/versions/versions-maven-plugin/
[65]: https://basepom.github.io/duplicate-finder-maven-plugin
[66]: https://maven.apache.org/plugins/maven-artifact-plugin/
[67]: https://maven.apache.org/plugins/maven-assembly-plugin/
[68]: https://maven.apache.org/plugins/maven-jar-plugin/
[69]: https://github.com/exasol/project-keeper/
[70]: https://github.com/exasol/project-keeper/blob/main/LICENSE
[71]: https://github.com/itsallcode/openfasttrace-maven-plugin
[72]: https://www.gnu.org/licenses/gpl-3.0.html
[73]: http://www.scalastyle.org
[74]: https://github.com/diffplug/spotless
[75]: https://github.com/evis/scalafix-maven-plugin
[76]: https://opensource.org/licenses/BSD-3-Clause
[77]: https://www.mojohaus.org/exec-maven-plugin
[78]: https://github.com/exasol/artifact-reference-checker-maven-plugin/
[79]: https://github.com/exasol/artifact-reference-checker-maven-plugin/blob/main/LICENSE
[80]: https://maven.apache.org/surefire/maven-failsafe-plugin/
[81]: https://www.jacoco.org/jacoco/trunk/doc/maven.html
[82]: https://www.eclipse.org/legal/epl-2.0/
[83]: https://github.com/exasol/quality-summarizer-maven-plugin/
[84]: https://github.com/exasol/quality-summarizer-maven-plugin/blob/main/LICENSE
[85]: https://github.com/exasol/error-code-crawler-maven-plugin/
[86]: https://github.com/exasol/error-code-crawler-maven-plugin/blob/main/LICENSE
[87]: https://github.com/git-commit-id/git-commit-id-maven-plugin
[88]: http://www.gnu.org/licenses/lgpl-3.0.txt
[89]: https://maven.apache.org/plugins/maven-clean-plugin/
[90]: https://maven.apache.org/plugins/maven-resources-plugin/
[91]: https://maven.apache.org/plugins/maven-install-plugin/
[92]: https://maven.apache.org/plugins/maven-site-plugin/
[93]: https://registry.npmjs.org/@exasol/extension-manager-interface/-/extension-manager-interface-0.5.0.tgz
