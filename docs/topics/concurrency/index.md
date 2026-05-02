# Concurrency

How we structure programs that do multiple things "at the same time".

Concurrency is about **composition of activities**, not just performance.

## The Big Picture

```mermaid
flowchart TD
    Shared["Shared-memory<br/><i>threads + locks</i>"]
    Message["Message-passing<br/><i>no shared state</i>"]

    Hoare["Hoare<br/>CSP<br/>1978"]
    Hewitt["Hewitt<br/>Actor model<br/>1973"]
    Armstrong["Armstrong<br/>Erlang<br/>1986 / 2003"]
    Go["Go<br/>goroutines + channels<br/>2009"]
    Lamport["Lamport<br/>Logical clocks<br/>1978"]

    Shared --> Problems["Races, deadlocks,<br/>visibility, contention"]

    Hoare --> CSP_family["CSP family:<br/>Go, core.async"]
    Hewitt --> Actor_family["Actor family:<br/>Erlang, Akka"]

    Message --> Hoare
    Message --> Hewitt

    Hewitt --> Armstrong
    Hoare --> Go
    Lamport --> Distributed["Distributed systems ordering"]

    style Shared fill:#fff3e0
    style Message fill:#e8f5e9
    style Hoare fill:#e1f5fe
    style Hewitt fill:#e1f5fe
```

## Two Families of Models

### 1) Shared Memory: Threads + Locks

**Idea:** multiple threads operate on shared state; synchronization primitives ensure correctness.

Tools:
- mutex / monitor / synchronized block
- semaphores
- condition variables
- atomics (CAS)
- concurrent data structures

**Failure modes:**
- data races
- deadlocks
- priority inversion
- "it works on my machine" timing bugs

This model maps to hardware, but is hard to reason about.

### 2) Message Passing: CSP and Actors

**Idea:** isolate state; communicate by messages. Avoid shared mutable state.

Two major variants:

#### CSP (Hoare 1978): Channels
- Communicating Sequential Processes
- Synchronization via channel operations
- Fits pipelines / dataflow

Go made CSP mainstream via goroutines + channels.

→ [Hoare — CSP (1978)](../../works/papers/hoare-1978-csp.md)

#### Actors (Hewitt 1973 / Erlang 1986): Mailboxes
- Each actor/process has private state
- Communication via asynchronous messages
- Fits distributed & fault-tolerant systems

Erlang embodies this model: "let it crash", supervisors, isolation.

→ [Joe Armstrong](../../authors/joe-armstrong.md)

## Async/Await (Structured Concurrency-ish)

Async/await is typically:
- **single-threaded concurrency** with an event loop (JS, Python asyncio)
- or **task-based concurrency** that may use threads under the hood (Rust tokio, Kotlin)

It changes how you write I/O-bound concurrent code:
- avoid callback hell
- keep sequential style

But it doesn't automatically solve shared-state issues; it just changes scheduling.

## A Practical Rule: Prefer Immutability + Explicit Boundaries

A useful modern default:
- keep state immutable where possible
- isolate mutation behind boundaries
- communicate via messages or queues
- keep concurrency mechanisms local (don't leak locks everywhere)

This is compatible with:
- Functional Core / Imperative Shell
- Hexagonal architecture (ports/adapters)
- DDD bounded contexts (own their data)

## Timeline

| Year | Event | Impact |
|---|---|---|
| 1965 | Dijkstra — semaphores | Classic shared-memory primitive |
| 1973 | Hewitt — Actor model | Message-based concurrency |
| 1978 | Hoare — CSP | Channels + process algebra |
| 1978 | Lamport — logical clocks | Ordering without global time |
| 1986 | Erlang begins | Industrial actor model |
| 2009 | Go | CSP for everyday developers |
| 2014+ | async/await mainstream | Task concurrency everywhere |

## Further Reading

- [Hoare — CSP (1978)](../../works/papers/hoare-1978-csp.md)
- [Lamport — Time, Clocks... (1978)](../../works/papers/lamport-1978-clocks.md)
- *The Little Book of Semaphores* (Allen B. Downey)
- *Java Concurrency in Practice* (Goetz et al.)

## Related Topics

- [Distributed Systems](../distributed/index.md) — reliability, consensus, CAP
- [Functional Programming](../functional/index.md) — immutability prevents data races
- [Paradigms](../paradigms/index.md) — concurrency models in broader context
- [Architecture & Modularity](../architecture/index.md) — system decomposition

## Maps

- [Concurrency Map](../../maps/concurrency-map.md) — visual overview of how concurrency models evolved
