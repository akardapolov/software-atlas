# Control Flow in Clojure

## Overview

Clojure uses **Lisp's conditional forms**: `if`, `when`, `cond`,
`case`, with `recur` for recursion. Looping is done with
`loop`/`recur` or `doseq`/`map`/`reduce` over sequences.

## Code

See `main.clj` in this directory.

## How to Run

```bash
clj main.clj
```

## Key Concepts

- **if** — conditional with then/else branches
- **when/unless** — conditional with single branch
- **cond** — multi-branch conditional with predicates
- **case** — value-based branching
- **loop/recur** — tail-recursive loops
- **doseq** — iteration over sequences
- **for** — comprehension with :let, :when, :while
- **doseq** — iteration with side effects
- **map/filter/reduce** — functional iteration
- **break** — use reduced, take, or lazy evaluation for early exit

## Historical Context

Clojure (2007, Rich Hickey) inherited Lisp's conditional forms.
The `loop`/`recur` pattern enables tail-call optimization.
`doseq` handles side effects while `map`/`filter` handle
transformations. Clojure prefers functional iteration over imperative loops.

For more on Clojure, see [docs/languages/clojure/](../../../docs/languages/clojure/)
