# Control Flow in Erlang

## Overview

Erlang has no traditional loops. Control flow is achieved through
**pattern matching**, **guards**, **recursion**, and **higher-order
functions**. `case` and `if` expressions provide branching.

## Code

See `main.erl` in this directory.

## How to Run

```bash
erlc main.erl && erl -noshell -s main start -s init stop
```

## Key Concepts

- **`case` expression** — pattern matching on a value
- **`if` expression** — guard-based branching
- **Recursion** — primary looping mechanism
- **Tail recursion** — optimized by the BEAM VM
- **Higher-order functions** — `lists:foreach`, `lists:map` for iteration
- **List comprehensions** — concise iteration with filters
