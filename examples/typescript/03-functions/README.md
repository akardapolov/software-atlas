# Functions in TypeScript

## Overview

TypeScript functions are **first-class** and inherit JavaScript's
flexibility while adding static typing. TypeScript supports arrow functions,
generics, function overloads, and optional/default parameters.

## Code

See `main.ts` in this directory.

## How to Run

```bash
tsc main.ts && node main.js
# Or with ts-node
ts-node main.ts
```

## Key Concepts

- **First-class functions** — assignable, passable, returnable
- **Arrow functions** — `const f = (x: number) => x * x` syntax
- **Type annotations** — optional static types for parameters and returns
- **Generics** — `<T>(x: T): T` for type parameters
- **Optional parameters** — `name?: string` with `?` modifier
- **Default parameters** — `count: number = 0` syntax
- **Rest parameters** — `...args: number[]` for variadic
- **Function overloads** — multiple signatures for same function
- **Union types** — `(string | number)[]` for flexible types
- **`this` typing** — explicit `this` parameter for method binding

## Historical Context

TypeScript (2012, Anders Hejlsberg) added static typing to JavaScript.
The function model follows JavaScript ES6+ arrow functions while adding
compile-time type checking. TypeScript popularized optional typing in the
JavaScript ecosystem.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
