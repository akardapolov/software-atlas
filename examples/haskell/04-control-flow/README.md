# Control Flow in Haskell

## Overview

Haskell is expression-oriented: `if` is an expression, branching is often
done with guards or `case`. Loops are usually expressed via recursion or
higher-order functions (`map`, `filter`, folds).

## How to Run

```bash
runghc Main.hs
# or
ghc -o control Main.hs && ./control
```

## Key Concepts

- **`if`/`case`/guards** — expression-based conditionals
- **Recursion** — looping via recursion (tail-recursive for efficiency)
- **Higher-order functions** — `map`, `filter`, folds (`foldr`/`foldl`)
- **List comprehensions** — concise sequence generation