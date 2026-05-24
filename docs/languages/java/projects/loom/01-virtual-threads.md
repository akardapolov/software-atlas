# Virtual Threads

> **Project:** Loom
> **Java version:** 21 (final)
> **Status:** Released

Virtual threads are the flagship feature of Project Loom. They are lightweight threads managed by the JVM rather than the operating system, enabling applications to handle millions of concurrent tasks with minimal resource overhead.

For the complete API reference and usage patterns, see the main feature page: [Virtual Threads](../../08-virtual-threads.md).

---

## History and Evolution

### The Problem: OS Thread Scarcity

Java's original threading model (since 1.0) uses platform threads — each `Thread` object maps to an OS thread. This 1:1 mapping creates a fundamental scalability ceiling:

- **Memory**: ~1 MB stack per thread × 10,000 threads = ~10 GB
- **Creation time**: Hundreds of microseconds per thread
- **Context switching**: Kernel-mediated, expensive

The traditional response was thread pools. But thread pools introduce their own complexity: sizing, saturation policies, deadlocks from nested pool exhaustion.

### The Reactive Alternative

Around 2013–2015, the Java ecosystem embraced reactive programming (`CompletableFuture`, RxJava, Project Reactor, Vert.x). Reactive frameworks avoid blocking threads by chaining callbacks:

```java
// Reactive style — no blocking, but hard to read and debug
fetchUser(id)
    .thenCompose(user -> fetchOrders(user))
    .thenAccept(orders -> process(orders));
```

Reactive code scales well but has well-known drawbacks:
- **Callback hell** — deeply nested chains
- **Lost stack traces** — errors propagate through framework internals
- **Infectious** — once you start reactive, your whole codebase must adapt

### The Loom Solution (2018–2023)

Project Loom asked: what if we could have the scalability of reactive with the simplicity of blocking code?

The answer: virtual threads. First previewed in Java 19 (2022), finalized in Java 21 (2023), and further refined through Java 25.

Key design decisions:
- **No new language syntax** — `Thread.ofVirtual()` and `Executors.newVirtualThreadPerTaskExecutor()`
- **Compatible with existing code** — any blocking I/O automatically yields the carrier
- **Cooperative scheduling** — the JVM manages suspension/resumption at known blocking points

## Implementation Deep Dive

### The Carrier Thread Model

```
OS Thread 1 ──► Carrier Thread 1 ──► [Virtual Thread A] [Virtual Thread B] ...
OS Thread 2 ──► Carrier Thread 2 ──► [Virtual Thread C] [Virtual Thread D] ...
OS Thread N ──► Carrier Thread N ──► [Virtual Thread ...]
```

Virtual threads are M:N scheduled over a pool of carrier threads (by default, a `ForkJoinPool`). When a virtual thread hits a blocking operation:

1. The JVM's `Continuation` mechanism saves the virtual thread's stack
2. The carrier thread is freed to run another virtual thread
3. When the I/O completes, the virtual thread is queued for rescheduling

### Continuations Under the Hood

Virtual threads are built on a lower-level JVM construct called **continuations** (not exposed in the public API). A continuation is a suspendable computation — a snapshot of a thread's call stack that can be saved and resumed later.

The JVM team modified the interpreter and JIT compiler to support efficient stack copying:
- **Frozen stacks** are copied to the Java heap
- **Hot paths** are optimized by the JIT to minimize copy overhead
- **Pinning** occurs when native code or `synchronized` blocks prevent stack migration

### Thread Pinning

Virtual threads can become "pinned" to their carrier, defeating the purpose:

| Cause | Why it pins | Mitigation |
|---|---|---|
| `synchronized` block | Monitor lock held on carrier thread | Use `ReentrantLock` instead |
| Native method call | Native stack frame cannot be migrated | Minimize blocking in native code |

When pinned, the carrier thread cannot serve other virtual threads until the pinning operation completes. The JVM detects and logs pinning events with `-Djdk.tracePinnedThreads=full`.

## Migration Path

### For Existing Thread-Per-Request Code

Before:
```java
ExecutorService executor = Executors.newFixedThreadPool(200); // carefully tuned
```

After:
```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); // unlimited
```

### For Reactive Code

Reactive code can gradually migrate:
1. Replace `CompletableFuture` chains with direct blocking calls inside virtual threads
2. Keep reactive at the edges (framework integration) where it provides value
3. Use `StructuredTaskScope` (also from Loom) for concurrent sub-tasks

## Performance Characteristics

| Metric | Platform Thread | Virtual Thread |
|---|---|---|
| Creation time | ~1 ms | ~1 μs |
| Memory per thread | ~1 MB | ~200 bytes (stack on heap) |
| Max concurrent | ~10,000 | Millions |
| Context switch | OS kernel | JVM user space |
| Best for | CPU-bound, long-lived | I/O-bound, short-lived |

## See Also

- [Virtual Threads — main feature page](../../08-virtual-threads.md)
- [Structured Concurrency](02-structured-concurrency.md) — Loom's other major feature
- [Scoped Values](03-scoped-values.md) — context propagation for virtual threads
- [Concurrency Utilities](../../15-concurrent.md) — traditional `java.util.concurrent`
- [Examples: Concurrency](../../../examples/java/09-concurrency/README.md)
