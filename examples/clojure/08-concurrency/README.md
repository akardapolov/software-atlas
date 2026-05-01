# Concurrency in Clojure

## Overview

Clojure provides multiple concurrency primitives: atoms, refs, agents,
and futures. Built on Java's concurrency but with immutable data by default
and explicit synchronization for shared state.

## Code

See `main.clj` in this directory.

## How to Run

```bash
clj main.clj
# Or with leiningen
lein run
```

## Key Concepts

- **atoms** — uncoordinated, thread-safe, idempotent updates
- **refs** — coordinated, synchronous, shared mutable state
- **dosync** — transaction for coordinated ref updates (STM)
- **agents** — asynchronous, independent state updates
- **futures** — run computation in thread pool, deref for result
- **promises** — deliver a value once to multiple derefs
- **pmap/pvalues** — parallel versions of map and vector
- **core.async channels** — CSP-style concurrency (go blocks, <!, >!)
- **Immutability by default** — most data structures are immutable

## Historical Context

Clojure (2007, Rich Hickey) adopted STM from Haskell, adapted for the
JVM. The coordination model (refs, atoms, agents) provides clear
semantics for different concurrency patterns. core.async (2013) added CSP
to Clojure, influenced by Go and Occam.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
