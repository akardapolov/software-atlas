# Testing in TypeScript

## Overview

TypeScript uses JavaScript testing frameworks with type safety.
**Jest** (Meta/Facebook), **Vitest**, **Mocha** are popular.
Typing helps catch errors before tests run, but runtime testing is
still essential.

## Code

See \`main.ts\` in this directory.

## How to Run

\`\`\`bash
npm test
# Or jest directly
jest
# Or with ts-node
ts-node node_modules/jest/bin/jest
\`\`\`

## Key Concepts

- **Jest** — \`test()\`, \`describe()\`, \`expect()\`
- **Assertions** — \`expect().toBe()\`, \`toEqual()\`, \`toThrow()\`
- **Matchers** — toBe, toEqual, toContain, toThrow
- **Mocking** — \`jest.fn()\`, \`jest.mock()\`
- **Async tests** — \`async\`/\`await\` in tests, \`resolves()\`
- **Snapshots** —toMatchSnapshot() for UI testing
- **Coverage** — Jest's built-in coverage
- **Type safety** — TypeScript catches type errors at compile time

## Historical Context

TypeScript (2012) uses JavaScript testing frameworks. Jest (2014, Facebook)
became popular for zero-config testing. TypeScript's type annotations
provide compile-time testing of code contracts, complementing runtime
tests.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
