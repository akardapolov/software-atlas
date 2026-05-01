# Data Structures in Clojure

## Overview

Clojure provides **persistent data structures**: vectors (indexed), lists (linked),
maps (hash/tree), and sets. Data is immutable by default with structural
sharing, making Clojure safe for concurrent use.

## How to Run

```bash
# With Leiningen
lein run

# Or directly with clojure
clojure -M data-structures.core/-main
```

## Key Concepts

- **Vector** — indexed collection, O(log n) access
- **List** — linked collection, head/tail operations
- **Map** — hash map (unordered) or sorted map (ordered)
- **Set** — hash set or sorted set
- **Persistent!** — immutable, structural sharing
- **Transient** — mutable for batch operations
- **StructMaps** — typed records with field access
- **Zippers** — structural navigation and modification
- **Lazy sequences** — computed on demand (`range`, `lazy-cat`)
- **Keywords** — preferred map keys (`:key`)

## Historical Context

Clojure (2007, Rich Hickey) is a modern Lisp dialect for the JVM. Persistent data structures enable safe concurrent programming. The focus on immutability and structural sharing distinguishes Clojure from other JVM languages like Java.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
