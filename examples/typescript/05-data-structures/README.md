# Data Structures in TypeScript

## Overview

TypeScript adds static typing to JavaScript. Supports **arrays**, **tuples**, **objects**, **Map**, **Set**, and type safety through interfaces. Optional types use `?` for nullable values, and `readonly` for immutability.

## How to Run

```bash
# Compile and run
npx ts-node main.ts

# Or compile first
tsc main.ts
node main.js
```

## Key Concepts

- **Arrays** — typed dynamic arrays with spread operator
- **Tuples** — fixed-size, typed sequences
- **Objects** — key-value with optional chaining (`?.`, `??`)
- **Map** — key-value with any key type (preserves insertion order)
- **Set** — unique values with operations
- **Interfaces** — type contracts and duck typing
- **Type aliases** — named types
- **Union types** — `string | number` for multiple types
- **Optional** — nullable with `?` (e.g., `string | null`)
- **Readonly** — immutable with `readonly` modifier
- **Type guards** — runtime type checking (`value is Type`)

## Historical Context

TypeScript (2012, Microsoft) adds optional static typing to JavaScript. Provides IDE support and compile-time error detection while maintaining JavaScript compatibility. Types erase at compile time, producing pure JavaScript.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
