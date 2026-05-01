# Functions in Clojure

## Overview

Clojure functions are **first-class** — assignable, passable, returnable.
Clojure builds on Lisp's functional heritage with immutable data structures
and explicit sequencing for side effects. Functions are the primary
abstraction.

## Code

See `main.clj` in this directory.

## How to Run

```bash
clj main.clj
# Or with leiningen
lein run
```

## Key Concepts

- **First-class functions** — functions are values
- **`fn` and `defn`** — anonymous and named functions
- **Arity overloading** — multiple function bodies by argument count
- **Closures** — functions capture lexical scope
- **Higher-order functions** — `map`, `filter`, `reduce`, `mapv`
- **Function composition** — `comp` and `partial`
- **Destructuring** — bind data structures in function parameters
- **Variadic functions** — `& args` for any number of arguments
- **Threading macros** — `->` and `->>` for data flow
- **Lazy sequences** — `map`, `filter` are lazy, `mapv` is eager

## Historical Context

Clojure (2007, Rich Hickey) is a modern Lisp dialect running on the JVM.
It retains Lisp's homoiconicity and functional core while adding immutable
persistent data structures and explicit concurrency primitives. Clojure
influenced the return of functional ideas to mainstream Java development.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
