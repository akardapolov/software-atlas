# OOP and Modules in TypeScript

## Overview

TypeScript adds **static typing** to JavaScript OOP. Supports
**classes**, **interfaces**, **abstract classes**, **generics**, and
**type modifiers**. Modules are files with `export` keyword.

## How to Run

```bash
# Compile and run
npx ts-node main.ts

# Or compile first
tsc main.ts
node main.js
```

## Key Concepts

- **Classes** — `class` keyword, `constructor`, `public`/`private`/`protected`
- **Inheritance** — `extends` keyword, method `override`
- **Interfaces** — `interface` keyword, `implements` keyword
- **Abstract classes** — `abstract` keyword, `abstract` methods
- **Generics** — `<T>` type parameters
- **Access modifiers** — `public`, `private`, `protected`, `readonly`
- **Decorators** — `@sealed`, `@log`, `@deprecated`
- **Type inference** — automatic with optional typing
- **Enums** — `enum` keyword with values
- **Modules** — `export` keyword, named imports
- **Property accessors** — `get`/`set` methods
- **Method overloading** — same name, different parameters
- **Type assertions** — `as Type` for type casting

## Historical Context

TypeScript (2012, Microsoft) adds optional static typing to JavaScript.
Type safety catches errors at compile time while maintaining JavaScript compatibility.

For more on TypeScript, see [docs/languages/typescript/](../../../docs/languages/typescript/)
