# OOP and Modules in Haskell

## Overview

Haskell uses **type classes** and **type constructors** for OOP.
**Data types** (ADTs) for algebraic data types.
Modules are files with qualified imports. No inheritance.

## How to Run

```bash
# Compile and run
ghc Main.hs -o modules

# Or with GHCi (interactive)
ghci Main.hs
```

## Key Concepts

- **Type classes** — `data` keyword with constructor, field access
- **Data types** — ADTs for sum types with pattern matching
- **Newtype** — zero-cost type wrapper
- **Type signatures** — `::` for function types
- **Modules** — `module` declarations, qualified imports
- **Records** — field access with record syntax
- **Deriving** — `deriving` for automatic type class generation
- **Lazy evaluation** — by default, strict with `seq`
- **Monads** — for side effects and sequencing
- **Lenses** — functional data structures
- **No inheritance** — Haskell has no class inheritance
- **Type classes** — default type parameters

## Historical Context

Haskell (1990, committee) pioneered pure functional programming. Type classes and ADTs enable expressive algebraic data types. Modules provide encapsulation and namespace control. Lazy evaluation by default enables infinite data structures.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
