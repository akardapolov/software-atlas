# Project Loom

> **Status:** Released — core features finalized in Java 21 LTS, further consolidated in Java 25 LTS.
> **Goal:** Scalable concurrency with the simple thread-per-request model, without reactive plumbing.

Project Loom introduces lightweight concurrency constructs to the Java platform, enabling developers to write highly concurrent code using familiar imperative, blocking I/O patterns. Rather than forcing adoption of complex reactive or callback-based APIs, Loom makes the traditional "one thread per request" model viable at massive scale.

The key insight: virtual threads are cheap enough to create millions of them, so blocking a thread is no longer an expensive operation. This eliminates the primary motivation for async/reactive programming in I/O-bound applications.

---

## Delivered Technologies

| # | Technology | Java version | Status | Page |
|---|---|---|---|---|
| 01 | Virtual Threads | 21 (final) | Released | [01-virtual-threads.md](01-virtual-threads.md) |
| 02 | Structured Concurrency | 25 (final) | Released | [02-structured-concurrency.md](02-structured-concurrency.md) |
| 03 | Scoped Values | 25 (final) | Released | [03-scoped-values.md](03-scoped-values.md) |

---

## Overview

### The Problem Before Loom

Traditional Java concurrency relies on platform threads, which map 1:1 to operating system threads. OS threads are expensive:

- **Memory**: Each thread consumes ~1 MB of stack space
- **Creation**: Slow, system-call heavy
- **Context switching**: Kernel-mediated, costly

This led to two undesirable outcomes:
1. **Thread pools everywhere** — complex sizing, tuning, and saturation policies
2. **Reactive programming** — callback chains, `CompletableFuture` composition, hard-to-debug stack traces

### The Loom Solution

Virtual threads decouple Java threads from OS threads:

- A virtual thread runs on a **carrier thread** (platform thread)
- When a virtual thread blocks (I/O, `sleep`, `park`), it **yields** the carrier
- The JVM scheduler (built on `ForkJoinPool`) assigns another virtual thread to the carrier
- Millions of virtual threads can coexist within a modest number of platform threads

This is cooperative scheduling at the JVM level: the JVM knows every blocking point and can suspend/resume virtual threads efficiently.

### Relationship to Other Projects

| Project | Concern | Complements Loom |
|---|---|---|
| **Panama** | Native interop | FFM API + virtual threads for native I/O |
| **Valhalla** | Value types | Lightweight data carriers for concurrent code |

---

## See Also

- [Virtual Threads](../../08-virtual-threads.md) — main feature page
- [Structured Concurrency](../../12-structured-concurrency.md) — main feature page
- [CompletableFuture](../../09-completable-future.md) — contrast with async style
- [Concurrency Utilities](../../15-concurrent.md) — `java.util.concurrent` primitives
- [Examples: Concurrency](../../../examples/java/09-concurrency/README.md)
- [Examples: Structured Concurrency](../../../examples/java/13-concurrency-structured/README.md)
