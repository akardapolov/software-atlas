# FP Features in Clojure

## Overview

Clojure is a **Lisp dialect with FP first**. Data structures are
immutable by default. Clojure embraces functional patterns: map, filter,
reduce, and core.async for CSP-style concurrency.

## Code

See \`main.clj\` in this directory.

## How to Run

\`\`\`bash
clj main.clj
# Or with leiningen
lein run
\`\`\`

## Key Concepts

- **Pure functions** — no side effects by default
- **Immutable data** — vectors, maps, sets cannot be mutated
- **Higher-order functions** — map, filter, reduce, transduce
- **Closures** — let/loop bindings capture lexical scope
- **Lazy sequences** — lazy sequences with realized()
- **Destructuring** — bind data structures in function params
- **Function composition** — comp, partial, juxt
- **Thread-last macro** — pipe: -> -> ->>
- **core.async** — CSP-style concurrency with go blocks
- **Atoms** — uncoordinated, synchronous updates
- **Refs** — coordinated updates with dosync
- **Agents** — asynchronous independent updates
- **Sequences API** — unified over lists, vectors, maps

## Historical Context

Clojure (2007, Rich Hickey) brings Lisp to JVM with FP focus.
Immutable data structures (from Java's persistent collections)
enable safe functional programming. core.async (2013) added CSP,
influenced by Go and Occam.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
