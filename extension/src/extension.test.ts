import { ExaMetadata, Installation, PreconditionFailedError } from '@exasol/extension-manager-interface';
import { ExaScriptsRow } from '@exasol/extension-manager-interface/dist/exasolSchema';
import { describe, expect, it } from '@jest/globals';
import { createExtension } from "./extension";
import { EXTENSION_DESCRIPTION } from './extension-description';
import { createMockContext, getInstalledExtension, scriptWithVersion } from './test-utils';

const currentVersion = EXTENSION_DESCRIPTION.version

describe("Kafka Connector Extension", () => {

    describe("installableVersions", () => {
        it("contains exactly one 'latest', non deprecated version", () => {
            const latestVersions = createExtension().installableVersions.filter(version => version.latest)
            expect(latestVersions).toHaveLength(1)
            expect(latestVersions[0].deprecated).toEqual(false)
        })
    })

    describe("extension registration", () => {
        it("creates an extension", () => {
            const ext = createExtension();
            expect(ext).not.toBeNull()
        })

        it("creates a new object for every call", () => {
            const ext1 = createExtension();
            const ext2 = createExtension();
            expect(ext1).not.toBe(ext2)
        })

        it("registers when loaded", () => {
            const installedExtension = getInstalledExtension();
            expect(installedExtension.extension).not.toBeNull()
            expect(typeof installedExtension.apiVersion).toBe('string');
            expect(installedExtension.apiVersion).not.toBe('');
        })
    })

    describe("findInstallations()", () => {
        function findInstallations(allScripts: ExaScriptsRow[]): Installation[] {
            const metadata: ExaMetadata = {
                allScripts: { rows: allScripts },
                virtualSchemaProperties: { rows: [] },
                virtualSchemas: { rows: [] }
            }
            const installations = createExtension().findInstallations(createMockContext(), metadata)
            expect(installations).toBeDefined()
            return installations
        }

        function text(name: string, className: string, version: string): string {
            return `CREATE ${name} ...
        %scriptclass ${className};
        %jar /buckets/bfsdefault/default/exasol-kafka-connector-extension-${version}.jar;`
        }
        function script({ schema = "schema", name = "name", inputType, resultType = "EMITS", type = "UDF", text = "", comment }: Partial<ExaScriptsRow>): ExaScriptsRow {
            return { schema, name, inputType, resultType, type, text, comment }
        }
        function setScript(name: string, className: string, version = EXTENSION_DESCRIPTION.version): ExaScriptsRow {
            return script({ name, inputType: "SET", text: text(name, className, version) })
        }

        it("returns empty list when no adapter script is available", () => {
            expect(findInstallations([])).toHaveLength(0)
        })

        it("returns single item when all scripts are available", () => {
            const scripts: ExaScriptsRow[] = [
                setScript("KAFKA_METADATA", "com.exasol.cloudetl.kafka.KafkaTopicMetadataReader"),
                setScript("KAFKA_IMPORT", "com.exasol.cloudetl.kafka.KafkaTopicDataImporter"),
                setScript("KAFKA_CONSUMER", "com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator"),
            ]
            expect(findInstallations(scripts)).toStrictEqual([{ name: "Cloud Storage Extension", version: EXTENSION_DESCRIPTION.version }])
        })

        it("fails for inconsistent version", () => {
            const scripts: ExaScriptsRow[] = [
                setScript("KAFKA_METADATA", "com.exasol.cloudetl.kafka.KafkaTopicMetadataReader"),
                setScript("KAFKA_IMPORT", "com.exasol.cloudetl.kafka.KafkaTopicDataImporter"),
                setScript("KAFKA_CONSUMER", "com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator"),
            ]
            expect(() => findInstallations(scripts)).toThrowError(new PreconditionFailedError(`Not all scripts use the same version. Found 2 different versions: '${currentVersion}, 0.0.0'`))
        })

        describe("returns expected installations", () => {
        })
    })

    describe("install()", () => {
        it("executes expected statements", () => {
            const context = createMockContext();
            createExtension().install(context, EXTENSION_DESCRIPTION.version);
            const executeCalls = context.mocks.sqlExecute.mock.calls
            expect(executeCalls.length).toBe(10)

            const expectedScriptNames = ["KAFKA_METADATA", "KAFKA_IMPORT", "KAFKA_CONSUMER"]

            const createScriptStatements = executeCalls.slice(0, 5).map(args => args[0])
            const createCommentStatements = executeCalls.slice(5, 10).map(args => args[0])

            expect(createScriptStatements).toHaveLength(5)
            expect(createCommentStatements).toHaveLength(5)

            const expectedComment = `Created by Extension Manager for Kafka Connector Extension ${EXTENSION_DESCRIPTION.version}`
            for (let i = 0; i < expectedScriptNames.length; i++) {
                const name = expectedScriptNames[i];
                expect(createScriptStatements[i]).toContain(`CREATE OR REPLACE JAVA`)
                expect(createScriptStatements[i]).toContain(`SCRIPT "ext-schema"."${name}"`)
                expect(createScriptStatements[i]).toContain(`%scriptclass com.exasol.cloudetl.kafka.`)
                expect(createCommentStatements[i]).toEqual(`COMMENT ON SCRIPT "ext-schema"."${name}" IS '${expectedComment}'`)
            }
        })
        it("fails for wrong version", () => {
            expect(() => { createExtension().install(createMockContext(), "wrongVersion") })
                .toThrow(`Installing version 'wrongVersion' not supported, try '${EXTENSION_DESCRIPTION.version}'.`)
        })
    })

    describe("upgrade()", () => {
        const version = "1.2.3"
        const metadataScript = scriptWithVersion("KAFKA_METADATA", version)
        const importScript = scriptWithVersion("KAFKA_IMPORT", version)
        const consumerScript = scriptWithVersion("KAFKA_CONSUMER", version)
        const allScripts = [metadataScript, importScript, consumerScript]

        it("success", () => {
            const context = createMockContext()
            context.mocks.simulateScripts(allScripts)
            expect(createExtension().upgrade(context)).toStrictEqual({
                previousVersion: version, newVersion: currentVersion
            })
            const executeCalls = context.mocks.sqlExecute.mock.calls
            expect(executeCalls.length).toBe(allScripts.length * 2)
        })
        describe("failure", () => {
            const tests: { name: string; scripts: ExaScriptsRow[], expectedMessage: string }[] = [
                { name: "no script", scripts: [], expectedMessage: "Not all required scripts are installed: Validation failed: Script 'KAFKA_METADATA' is missing, Script 'KAFKA_IMPORT' is missing, Script 'KAFKA_CONSUMER' is missing" },
                { name: "one missing script", scripts: [metadataScript, importScript], expectedMessage: "Not all required scripts are installed: Validation failed: Script 'KAFKA_CONSUMER' is missing" },
                { name: "inconsistent versions", scripts: [metadataScript, importScript, scriptWithVersion("KAFKA_CONSUMER", "1.2.4")], expectedMessage: "Failed to validate script versions: Not all scripts use the same version. Found 2 different versions: '1.2.3, 1.2.4'" },
                {
                    name: "version already up-to-date", scripts: [scriptWithVersion("KAFKA_METADATA", currentVersion), scriptWithVersion("KAFKA_IMPORT", currentVersion),
                    scriptWithVersion("KAFKA_CONSUMER", currentVersion)],
                    expectedMessage: `Extension is already installed in latest version ${currentVersion}`
                },
            ]
            tests.forEach(test => it(test.name, () => {
                const context = createMockContext()
                context.mocks.simulateScripts(test.scripts)
                expect(() => createExtension().upgrade(context)).toThrowError(new PreconditionFailedError(test.expectedMessage))
                const executeCalls = context.mocks.sqlExecute.mock.calls
                expect(executeCalls.length).toBe(0)
            }))
        })
    })

    describe("uninstall()", () => {
        it("executes query to check if schema exists", () => {
            const context = createMockContext()
            context.mocks.sqlQuery.mockReturnValue({ columns: [], rows: [] });
            createExtension().uninstall(context, EXTENSION_DESCRIPTION.version)
            const calls = context.mocks.sqlQuery.mock.calls
            expect(calls.length).toEqual(1)
            expect(calls[0]).toEqual(["SELECT 1 FROM SYS.EXA_ALL_SCHEMAS WHERE SCHEMA_NAME=?", "ext-schema"])
        })
        it("skips drop statements when schema does not exist", () => {
            const context = createMockContext()
            context.mocks.sqlQuery.mockReturnValue({ columns: [], rows: [] });
            createExtension().uninstall(context, EXTENSION_DESCRIPTION.version)
            expect(context.mocks.sqlExecute.mock.calls.length).toEqual(0)
        })
        it("executes expected statements", () => {
            const context = createMockContext()
            context.mocks.sqlQuery.mockReturnValue({ columns: [], rows: [[1]] });
            createExtension().uninstall(context, EXTENSION_DESCRIPTION.version)
            const calls = context.mocks.sqlExecute.mock.calls
            const expectedScriptNames = ["KAFKA_METADATA", "KAFKA_IMPORT", "KAFKA_CONSUMER"]
            expect(calls.length).toEqual(expectedScriptNames.length)
            for (let i = 0; i < expectedScriptNames.length; i++) {
                expect(calls[i]).toEqual([`DROP SCRIPT "ext-schema"."${expectedScriptNames[i]}"`])
            }
        })
        it("fails for wrong version", () => {
            expect(() => { createExtension().uninstall(createMockContext(), "wrongVersion") })
                .toThrow(`Uninstalling version 'wrongVersion' not supported, try '${EXTENSION_DESCRIPTION.version}'.`)
        })
    })


    describe("getInstanceParameters()", () => {
        it("is not supported", () => {
            expect(() => { createExtension().getInstanceParameters(createMockContext(), "version") })
                .toThrow("Creating instances not supported")
        })
    })

    describe("addInstance()", () => {
        it("is not supported", () => {
            expect(() => { createExtension().addInstance(createMockContext(), "version", { values: [] }) })
                .toThrow("Creating instances not supported")
        })
    })

    describe("findInstances()", () => {
        it("is not supported", () => {
            expect(() => { createExtension().findInstances(createMockContext(), "version") })
                .toThrow("Finding instances not supported")
        })
    })

    describe("deleteInstance()", () => {
        it("is not supported", () => {
            expect(() => { createExtension().deleteInstance(createMockContext(), "version", "instId") })
                .toThrow("Deleting instances not supported")
        })
    })

    describe("readInstanceParameterValues()", () => {
        it("is not supported", () => {
            expect(() => { createExtension().readInstanceParameterValues(createMockContext(), "version", "instId") })
                .toThrow("Reading instance parameter values not supported")
        })
    })
})

