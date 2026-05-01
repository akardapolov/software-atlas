# Functions in Haskell

## Overview

Haskell functions are **pure** — no side effects, same input always
produces same output. Functions are first-class citizens with strong
support for higher-order functions, currying, and composition.

## Code

See `Main.hs` in this directory.

## How to Run

```bash
ghc Main.hs && ./Main
# Or with runhaskell
runhaskell Main.hs
# Or in GHCi
ghci Main.hs
```

## Key Concepts

- **Pure functions** — no side effects, referentially transparent
- **First-class** — assign, pass, return, store functions
- **Currying** — all functions take one argument, multi-arg is curried
- **Partial application** — apply fewer arguments than required
- **Function composition** — `(f . g) x = f (g x)`
- **Higher-order functions** — `map`, `filter`, `foldr`, `foldl`
- **Pattern matching** — define functions by matching patterns
- **Type inference** — types inferred by Hindley-Milner algorithm
- **Type classes** — ad hoc polymorphism, like interfaces
- **Lambdas** — anonymous functions `\x -> x * x`

## Historical Context

Haskell (1990, committee) is a pure functional language based on
Hindley-Milner type system and lambda calculus. It pioneered
type classes (ad hoc polymorphism) and lazy evaluation. Haskell
strongly influenced modern FP in mainstream languages (Java streams,
JavaScript array methods, etc.).

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
