# OOP and Modules in Erlang

## Overview

Erlang uses **modules** as primary unit for code organization.
**Behaviours** (lightweight processes) provide actor-based concurrency.
**Records** provide data structures with pattern matching.
OOP is through protocols and records, not classes.

## How to Run

```bash
# Compile
erlc oop_modules.erl

# Run
erl -n oop_and_modules -s main
```

## Key Concepts

- **Modules** — `-module()` and `-export()` directives
- **Behaviours** — `spawn`/`spawn_link` for actors
- **Records** — `#record{}` for named tuples
- **Pattern matching** — `case` statements for destructuring
- **!** — match-and-create in fun returns
- **Functional** — emphasis on immutability and recursion
- **Supervision trees** — `supervisor` for fault tolerance

## Historical Context

Erlang (1986, Joe Armstrong) was designed for building concurrent, fault-tolerant systems.
Behaviours/actors are isolated processes with mailboxes.
Records replace classes with pattern matching.
The `!` operator enables pattern matching without temporary variables.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
