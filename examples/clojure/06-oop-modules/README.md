# OOP and Modules in Clojure

## Overview

Clojure uses **protocols** (interfaces), **records** (data types),
and **multimethods** (method overloading). Namespaces use `ns` keyword.
OOP is through **protocols** and **composition**, not inheritance.

## How to Run

```bash
# With Leiningen
lein run

# Or directly with clojure
clojure -M oop-and-modules.core/-main
```

## Key Concepts

- **Protocols** — `defprotocol` for interfaces with type constraints
- **Records** — `defrecord` for named tuples with field access
- **deftype** — forward declarations for type hints
- **Multimethods** — dispatch by type or value, method overloading
- **Namespaces** — `ns` keyword, `require`/`import`
- **Composition** — use `contains?`, `assoc-in`, `update-in`
- **Persistent!** — immutable data structures by default
- **Dynamic polymorphism** — `defmulti` for runtime dispatch

## Historical Context

Clojure (2007, Rich Hickey) is a modern Lisp dialect for JVM.
Protocols provide interfaces with default implementations.
Records replace classes with simpler data structures.
Multimethods enable powerful runtime polymorphism.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
