# Variables and Types in Haskell

## Overview

Haskell uses **static, strong, inferred typing** — types are checked at
compile time, there are no implicit conversions, and the compiler infers
types using Hindley–Milner type inference.

## Code

See `Main.hs` in this directory.

## How to Run

```bash
# With GHC
ghc -o variables Main.hs && ./variables

# Or interpreted
runghc Main.hs

# Or in GHCi
ghci Main.hs
```

## Key Concepts

- **Everything is immutable** — there are no mutable variables
- **Type inference** — compiler deduces types; annotations are optional but encouraged
- **No null** — `Maybe a` represents optional values
- **Algebraic data types** — sum types (`Either`, `Maybe`, custom) and product types (records, tuples)
- **Type classes** — ad-hoc polymorphism (like interfaces, but more powerful)
- **Lazy evaluation** — values are computed only when needed
- **Purity** — functions have no side effects (IO is tracked in types)

## Historical Context

Haskell was designed by a committee (1990) to be a standard for lazy,
pure functional programming research. Its type system descends from
Robin Milner's ML (1978) and adds type classes (Wadler & Blott, 1989).
Haskell's type system has influenced Rust, Scala, TypeScript, and Swift.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
