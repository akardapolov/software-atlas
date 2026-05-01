# Variables and Types in TypeScript

## Overview

TypeScript uses **static, structural typing** — types are checked at
compile time and compatibility is determined by shape (structure), not
by declared names (nominal).

## Code

See `main.ts` in this directory.

## How to Run

```bash
# With ts-node
npx ts-node main.ts

# Or compile and run
npx tsc main.ts && node main.js

# Or with Deno
deno run main.ts
```

## Key Concepts

- **Static typing** — types are checked at compile time (erased at runtime)
- **Structural typing** — two types are compatible if their shapes match
- **Type inference** — compiler infers types when possible
- **Union types** — `string | number` — a value can be one of several types
- **Literal types** — `"hello"` is a type (not just a value)
- **Discriminated unions** — algebraic data types via tagged unions
- **`null` and `undefined`** — handled with `strictNullChecks`
- **Generics** — parameterised types

## Historical Context

TypeScript was designed by Anders Hejlsberg at Microsoft (2012) to add
gradual, structural types to JavaScript. Its type system is unusually
expressive for a mainstream language — featuring union/intersection types,
mapped types, conditional types, and template literal types. TypeScript
proves that a powerful type system can be adopted incrementally.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
