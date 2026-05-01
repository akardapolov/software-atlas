# Concurrency in Erlang

## Overview

Erlang implements the **Actor Model**: lightweight processes communicating
via asynchronous message passing. Processes are isolated — no shared memory.
Fault tolerance is achieved through "let it crash" supervision trees.

## Code

See `main.erl` in this directory.

## How to Run

```bash
erlc main.erl && erl -noshell -s main main -s init stop
```

## Key Concepts

- **spawn** — create lightweight processes
- **Message passing** — `Pid ! Msg` for asynchronous communication
- **receive** — pattern-matching receive block for messages
- **Selective receive** — multiple patterns with timeouts
- **Process mailbox** — each process has a message queue
- **Link and monitor** — process monitoring for fault detection
- **Supervision trees** — hierarchical process restart strategies
- **OTP behaviours** — gen_server, gen_fsm for structured processes
- **No shared state** — pure message passing prevents data races

## Historical Context

Erlang (1986, Joe Armstrong) was designed for telecom systems
requiring 99.999% uptime. The actor model, inspired by Hewitt (1973),
provides fault isolation and easy reasoning about concurrency. Erlang's
approach influenced Elixir, Akka, and modern distributed systems.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
