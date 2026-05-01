# Hello World in Erlang

## Overview

A simple Hello World program in Erlang, a concurrent functional language.

## Code

```erlang
-module(hello).
-export([start/0]).

start() ->
    io:format("Hello, World!~n").
```

## How to Run

```bash
# Compile
erlc hello.erl

# Run in Erlang shell
erl
1> hello:start().
Hello, World!
ok
```

## Key Concepts

- `-module(hello)` - Module declaration
- `-export([start/0])` - Export start function with 0 arguments
- `io:format/2` - Formatted output (similar to printf)
- Pattern matching and function clauses
- Concurrent actor model design

## Historical Context

Erlang was created by Joe Armstrong at Ericsson in 1986. Key features:
- Designed for telecom systems (high availability, concurrency)
- Actor model for concurrency
- "Let it crash" philosophy with supervision trees
- Hot code swapping in production

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
