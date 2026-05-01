# FP Features in Haskell

## Overview

Haskell is a **pure functional language** where FP is the primary
paradigm, not an addition. All functions are pure by default.
Haskell pioneered many FP features: type classes, lazy evaluation,
and monads.

## Code

See \`Main.hs\` in this directory.

## How to Run

\`\`\`bash
ghc -threaded Main.hs && ./Main
# Or with runhaskell
runhaskell Main.hs
\`\`\`

## Key Concepts

- **Pure functions** — no side effects, same input → same output
- **Type inference** — Hindley-Milner for implicit types
- **Type classes** — ad hoc polymorphism (Eq, Show, Num)
- **Higher-order functions** — map, filter, fold, take, drop
- **Function composition** — (.) operator: f . g
- **Currying** — partial application with fewer arguments
- **Lazy evaluation** — infinite lists, lazy streams
- **Pattern matching** — function definition by case
- **Recursion** — primary control flow (no loops)
- **Monads** — type for sequencing effects (IO, Maybe, Either)
- **List comprehensions** — [x*2 | x <- xs, x > 5]
- **do-notation** — monadic sequencing

## Historical Context

Haskell (1990, committee) pioneered type classes (1996),
lazy evaluation, and monads. Haskell's features influenced
modern languages: Java generics, Swift type system, Rust traits.
QuickCheck (1999, Haskell) pioneered property-based testing.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
