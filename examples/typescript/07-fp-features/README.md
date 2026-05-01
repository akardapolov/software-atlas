# FP Features in TypeScript

## Overview

TypeScript has JavaScript's FP features with type safety.
Functions are first-class with arrow syntax. Array methods provide
map, filter, reduce. TypeScript adds types and generics.

## Code

See \`main.ts\` in this directory.

## How to Run

\`\`\`bash
tsc main.ts && node main.js
# Or with ts-node
ts-node main.ts
\`\`\`

## Key Concepts

- **Arrow functions** — \`const f = (x: number) => x * 2\`
- **First-class functions** — assignable, passable, returnable
- **Array methods** — map, filter, reduce, forEach, find
- **Method references** — \`obj.method\` shorthand
- **Generics** — \`<T>(x: T): T\` type parameters
- **Type inference** — explicit types optional but recommended
- **Optional chaining** — \`?.\` for null-safe access
- **Function composition** — manual or pipe libraries
- **Currying** — partial application with bind/curry
- **Promise** — async function result wrapper
- **Async/await** — await Promise results
- **Immutable by convention** — \`readonly\` and \`const\`
- **Union types** — \`string | number\` for flexible types
- **Tuple types** — \`[string, number]\` fixed-length arrays

## Historical Context

TypeScript (2012, Anders Hejlsberg) added types to JavaScript.
Arrow functions from ES6 (2015) enabled FP-style coding.
Async/await (ES8 2017) improved async patterns. TypeScript's
types provide compile-time safety for JS FP code.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
