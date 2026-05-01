# Variables and Types in Erlang

## Overview

Erlang uses **dynamic, strong typing** — types are checked at runtime,
but implicit conversions are not allowed. All variables are
**single-assignment** (immutable after binding).

## Code

See `main.erl` in this directory.

## How to Run

```bash
# Compile and run
erlc main.erl && erl -noshell -s main start -s init stop

# Or in Erlang shell
erl
> c(main).
> main:start().
```

## Key Concepts

- **Dynamic typing** — no type declarations; types are checked at runtime
- **Strong typing** — `1 + "2"` is a runtime error (no implicit coercion)
- **Single assignment** — variables can only be bound once (immutable!)
- **Pattern matching** — primary mechanism for binding and branching
- **Atoms** — symbolic constants (like Ruby symbols or Lisp keywords)
- **Tuples** — fixed-size collections: `{ok, Value}`, `{error, Reason}`
- **Lists** — variable-length linked lists
- **No null** — atoms like `undefined` or tuples like `{error, not_found}`

## Historical Context

Erlang was designed by Joe Armstrong at Ericsson (1986) for telecom
switches — systems requiring 99.999% uptime. Its type system reflects
this: immutability and pattern matching prevent many classes of bugs,
while dynamic typing allows hot code upgrades on running systems.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
