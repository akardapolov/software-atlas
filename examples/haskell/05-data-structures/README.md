# Data Structures in Haskell

## Overview

Haskell provides **lists** (lazy, linked), `Data.Map` (balanced tree), `Data.Set` (balanced tree), and **algebraic data types**. Haskell uses **lazy evaluation** and **pure functional** semantics with type classes for polymorphism.

## How to Run

```bash
# Compile and run
ghc Main.hs -o data-structures
./data-structures

# Or with GHCi (interactive)
ghci Main.hs
```

## Key Concepts

- **List** — lazy, linked list, cons (`:`) for prepend
- **Map** — ordered balanced tree (`Data.Map`, `Data.IntMap`)
- **Set** — ordered balanced tree (`Data.Set`, `Data.IntSet`)
- **Tuple** — fixed-size heterogeneous
- **Algebraic types** — sum types with pattern matching
- **Records** — field access with dot syntax (`record.field`)
- **Maybe** — optional values (no null pointer)
- **Either** — value or error (`Left` / `Right`)
- **Lazy evaluation** — computed on demand (`[1..]` is infinite)
- **Type classes** — ad-hoc polymorphism (`Num`, `Eq`, `Show`)
- **Newtype** — zero-overhead type wrapper

## Historical Context

Haskell (1990, committee) pioneered pure functional programming with lazy evaluation. Algebraic data types enable expressive pattern matching. The combination of type classes and lazy evaluation distinguishes Haskell from most languages.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
