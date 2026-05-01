# Concurrency in Haskell

## Overview

Haskell concurrency is built on **software transactional memory (STM)**,
lightweight threads, and immutable data. The combination of pure functions
and STM makes concurrent code easier to reason about and less prone to
race conditions.

## Code

See `Main.hs` in this directory.

## How to Run

```bash
ghc -threaded Main.hs && ./Main
# Or with runhaskell
runhaskell -threaded Main.hs
```

## Key Concepts

- **forkIO** — spawn lightweight threads
- **MVar** — shared mutable state with blocking operations
- **STM** — software transactional memory for composable transactions
- **TVar** — transactional variables for STM
- **async/wait** — capture async computation results
- **STM primitives** — atomically, retry, orElse
- **Thread safety through immutability** — no accidental shared mutation

## Historical Context

Haskell (1990) pioneered STM (2005) as an alternative to locks.
STM allows composable atomic transactions, inspired by database transactions.
GHC's lightweight green threads enable millions of concurrent operations.
Haskell's purity guarantees make data races impossible without explicit
IO or unsafe operations.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
