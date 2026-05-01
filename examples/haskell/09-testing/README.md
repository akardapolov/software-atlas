# Testing in Haskell

## Overview

Haskell uses **Hspec** and **QuickCheck** as primary testing tools.
QuickCheck pioneered **property-based testing** — testing properties
instead of specific examples. Haskell's purity makes testing predictable.

## Code

See `Main.hs` in this directory.

## How to Run

```bash
cabal test
# Or with stack
stack test
```

## Key Concepts

- **Hspec** — BDD-style testing with `describe`, `it`, `should`
- **QuickCheck** — property-based testing with `quickcheck`
- **Properties** — generic properties tested against random inputs
- **Arbitraries** — generators for test data
- **Golden tests** — compare output against known-good results
- **Test coverage** — HPC (Haskell Program Coverage)
- **SmallCheck** — exhaustive testing for small data types

## Historical Context

Haskell (1990) pioneered property-based testing with QuickCheck (1999,
Koen Claessen, John Hughes). This influenced property-based testing
in other languages (ScalaCheck, FsCheck, Java's jqwik).
Hspec (2011) added BDD-style testing to Haskell.

For more on Haskell, see [docs/languages/haskell/](../../../docs/languages/haskell/)
