# Functions in Erlang

## Overview

Erlang functions are defined within modules with pattern matching on arguments.
Functions are not first-class in the traditional sense, but **anonymous
functions** (funs) provide closures and higher-order programming. Erlang
emphasizes pattern matching and recursion over iteration.

## Code

See `main.erl` in this directory.

## How to Run

```bash
erlc main.erl && erl -noshell -s main main -s init stop
# Or in the Erlang shell
erl
> c(main).
> main:main().
```

## Key Concepts

- **Module-based** — functions defined in modules with `-module` and `-export`
- **Pattern matching** — multiple clauses with different patterns
- **Guard expressions** — conditions in function heads: `when N > 0`
- **Recursion** — primary control flow (no traditional loops)
- **Anonymous functions (funs)** — closures capturing scope
- **Higher-order functions** — `lists:map`, `lists:filter`, `lists:foldl`
- **Tail recursion** — optimized by BEAM VM (constant stack space)
- **Head/tail pattern** — `[H|T]` for list deconstruction

## Historical Context

Erlang (1986, Joe Armstrong) was designed for telecom systems
requiring high concurrency and fault tolerance. The functional approach
with immutable data and pattern matching enables robust, concurrent
systems. The let-it-crash philosophy treats functions as pure units.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
