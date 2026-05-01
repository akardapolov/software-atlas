# Variables and Types in Clojure

## Overview

Clojure uses **dynamic, strong typing** — types are checked at runtime,
implicit conversions are minimal. All core data structures are
**immutable** and **persistent** (structurally shared).

## Code

See `main.clj` in this directory.

## How to Run

```bash
# With Clojure CLI
clj -M main.clj

# Or with leiningen
lein exec main.clj

# Or in REPL
clj
user=> (load-file "main.clj")
```

## Key Concepts

- **Dynamic typing** — no type declarations; values carry their type
- **Strong typing** — `(+ 1 "2")` throws ClassCastException
- **Immutable by default** — all core data structures are persistent
- **Hosted on JVM** — interoperates with Java types
- **Keywords** — `:name`, `:age` — lightweight identifiers (like Erlang atoms)
- **Maps as primary data structure** — `{:name "Ada" :age 36}`
- **nil** — represents absence (but idiomatic code minimises nil-checking)
- **Persistent data structures** — structural sharing for efficient "copies"

## Historical Context

Clojure was designed by Rich Hickey (2007) as a practical, modern Lisp
on the JVM. Its type philosophy: use the host platform's types, keep
data structures simple (maps, vectors, sets), and avoid inventing class
hierarchies. Rich Hickey's "Simple Made Easy" talk articulates why
Clojure avoids static types in favour of simple data.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
