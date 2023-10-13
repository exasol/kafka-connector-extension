/* eslint-disable @typescript-eslint/no-explicit-any */
import { Context, QueryResult, SqlClient } from '@exasol/extension-manager-interface';
import { ExaScriptsRow } from '@exasol/extension-manager-interface/dist/exasolSchema';
import * as jestMock from "jest-mock";

const EXTENSION_SCHEMA_NAME = "ext-schema"

export function getInstalledExtension(): any {
    // eslint-disable-next-line @typescript-eslint/no-unsafe-member-access
    return (global as any).installedExtension
}

export type ContextMock = Context & {
    mocks: {
        sqlExecute: jestMock.Mock<(query: string, ...args: any) => void>,
        sqlQuery: jestMock.Mock<(query: string, ...args: any) => QueryResult>
        getScriptByName: jestMock.Mock<(scriptName: string) => ExaScriptsRow | null>
        simulateScripts: (scripts: ExaScriptsRow[]) => void
    }
}

export function createMockContext(): ContextMock {
    const mockedScripts: Map<string, ExaScriptsRow> = new Map()
    const execute = jestMock.fn<(query: string, ...args: any) => void>().mockName("sqlClient.execute()")
    const query = jestMock.fn<(query: string, ...args: any) => QueryResult>().mockName("sqlClient.query()")
    const getScriptByName = jestMock.fn<(scriptName: string) => ExaScriptsRow | null>().mockName("metadata.getScriptByName()")
    getScriptByName.mockImplementation((scriptName) => mockedScripts.get(scriptName) ?? null)
    const sqlClient: SqlClient = {
        execute: execute,
        query: query
    }

    return {
        extensionSchemaName: EXTENSION_SCHEMA_NAME,
        sqlClient,
        bucketFs: {
            resolvePath(fileName: string) {
                return "/bucketfs/" + fileName;
            },
        },
        metadata: {
            getScriptByName
        },
        mocks: {
            sqlExecute: execute,
            sqlQuery: query,
            getScriptByName: getScriptByName,
            simulateScripts(scripts: ExaScriptsRow[]) {
                mockedScripts.clear()
                scripts.forEach(script => mockedScripts.set(script.name, script))
            },
        }
    }
}

