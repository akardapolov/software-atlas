# Error Handling in TypeScript

## Overview

TypeScript follows JavaScript's error handling but adds **type safety**.
Uses \`try-catch-finally\` and \`throw\`. TypeScript can
narrow \`unknown\` to specific error types. \`unknown\` can be \`never\`.

## Code

See \`main.ts\` in this directory.

## How to Run

\`\`\`bash
tsc main.ts && node main.js
# Or with ts-node
ts-node main.ts
\`\`\`

## Key Concepts

- **try-catch-finally** — \`try { ... } catch (e: Error) { ... } finally { ... }\`
- **throw** — \`throw new Error("message")\` or \`throw new CustomError()\`
- **Error interface** — \`interface CustomError extends Error { code: number }\`
- **Custom error classes** — extend Error with additional properties
- **Type guards** — \`catch (e: SpecificError)\` narrows error type
- **Type narrowing** — \`if (e instanceof SpecificError)\`
- **Promise rejection** — \`Promise.reject(error)\`
- **Async/await error** — \`try { await ... } catch (e)\`
- **never type** — \`never\` for impossible code paths

## Historical Context

TypeScript (2012) adds types to JavaScript's loose error model.
The \`Error\` interface allows custom error types. \`never\` type
ensures compile-time detection of unreachable code paths. TypeScript
enables catching specific error types that JavaScript cannot.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
