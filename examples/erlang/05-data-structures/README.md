# Data Structures in Erlang

## Overview

Erlang provides **lists** (linked), **tuples** (fixed-size), **maps** (key-value), and **records** (named tuples). Data is immutable by default. **Processes** provide actor-based concurrency, and **ETS** provides in-memory storage.

## How to Run

```bash
# Compile
erlc data_structures.erl

# Run
erl -n data_structures -s main
```

## Key Concepts

- **List** — linked list, head/tail operations, O(n) random access
- **Tuple** — fixed-size heterogeneous collection
- **Map** — hash table for key-value pairs
- **Set** — unique elements
- **Record** — named tuple with field access (`#record.field`)
- **Binary** — packed binary data for efficiency
- **Process** — lightweight actor for concurrency
- **ETS** — in-memory term storage (hash table)
- **Pattern matching** — primary destructuring mechanism
- **Immutability** — all data is immutable by default
- **Let it crash** — fault tolerance through isolation

## Historical Context

Erlang (1986, Joe Armstrong at Ericsson) was designed for building concurrent, fault-tolerant systems. Immutable data and the actor model make Erlang suitable for distributed, highly-available systems. Pattern matching replaces traditional loops in idiomatic Erlang code.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
