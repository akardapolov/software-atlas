# A Universal Modular ACTOR Formalism for Artificial Intelligence

| | |
|---|---|
| **Author(s)** | Carl Hewitt, Peter Bishop, Richard Steiger |
| **Year** | 1973 |
| **Publication** | International Joint Conference on Artificial Intelligence (IJCAI) |
| **Topic(s)** | Concurrency, Actor Model, Distributed Systems |

## Summary

This paper introduced the **Actor Model** — a fundamental model of concurrent computation where the primitive unit is an "actor," an entity that can receive messages, send messages, and create other actors.

## Key Ideas

1. **Actors as primitives** — actors are the universal building blocks of computation
2. **Message passing** — actors communicate exclusively through asynchronous messages
3. **No shared state** — each actor has its own private state

## Historical Context

The Actor Model was proposed as an alternative to shared-memory models of concurrency, emphasizing message passing and isolation.

## Impact and Legacy

The Actor Model influenced:
- **Erlang** — Joe Armstrong adopted actor-like principles
- **Akka** — actor framework for JVM
- **Modern distributed systems** — message-passing architectures

## Connections

- **Related author:** [Carl Hewitt](../../authors/carl-hewitt.md)
- **Related topic:** [Concurrency](../../topics/concurrency/)
- **Related language:** [Erlang](../../languages/erlang/)
