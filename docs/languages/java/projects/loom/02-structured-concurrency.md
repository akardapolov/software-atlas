# Structured Concurrency

> **Project:** Loom
> **Java version:** 25 (final)
> **Status:** Released

Structured concurrency treats a group of related concurrent tasks as a single unit of work. Tasks are forked, joined, and cleaned up together within a well-defined scope, eliminating the "fire and forget" anti-pattern that leads to resource leaks and orphaned threads.

For the complete API reference and usage patterns, see the main feature page: [Scoped Values and Structured Concurrency](../../12-structured-concurrency.md).

---

## History and Evolution

### The Unstructured Concurrency Problem

Traditional concurrent Java code looks like this:

```java
Future<String> f1 = executor.submit(() -> fetchUser());
Future<String> f2 = executor.submit(() -> fetchOrders());
// What if f1 fails? f2 keeps running, consuming resources.
// What if the caller is interrupted? f1 and f2 are orphaned.
```

This is **unstructured concurrency**: tasks are launched into the void with no lifecycle relationship to their parent. Problems abound:

- **Orphaned tasks** — A failed parent leaves children running forever
- **Resource leaks** — Cancelled tasks don't clean up connections, file handles
- **Lost errors** — An exception in one task may never propagate to the caller
- **No composition** — Nested concurrency creates a tangle of `Future` objects

### The Structured Programming Analogy

In the 1960s, Dijkstra's "Go To Statement Considered Harmful" argued that unstructured control flow (`goto`) made programs impossible to reason about. The solution was structured programming: `if/then/else`, `while`, `for` — control flows with explicit entry and exit points.

Structured concurrency applies the same principle to concurrent code:
- **Entry**: Enter a scope
- **Body**: Fork tasks within the scope
- **Exit**: All tasks complete (successfully or with cancellation) before scope exit

No task can outlive its scope. Just as a `for` loop's counter variable cannot escape the loop, a structured task cannot escape its scope.

### Development Timeline

| Milestone | Release | Status |
|---|---|---|
| First incubator | Java 19 | `StructuredTaskScope` in `jdk.incubator.concurrent` |
| Second incubator | Java 20 | API refinements, `ShutdownOnFailure` / `ShutdownOnSuccess` |
| Preview | Java 21 | Moved to `java.util.concurrent` |
| Finalized | Java 25 | Permanent API, refinements from feedback |

## Implementation Deep Dive

### The Scope as a Lifetime Boundary

```java
try (var scope = new StructuredTaskScope<String>()) {
    Future<String> f1 = scope.fork(() -> fetchUser());    // starts immediately
    Future<String> f2 = scope.fork(() -> fetchOrders());  // starts immediately

    scope.join();  // waits for ALL forked tasks

    // At this point, both f1 and f2 are done
    return f1.resultNow() + ", " + f2.resultNow();
}  // scope.close() guarantees cleanup
```

The `try-with-resources` block defines the lifetime. Even if:
- `fetchUser()` throws an exception
- The thread is interrupted
- `join()` times out

...the scope ensures all forked tasks are cancelled and resources released before exiting the block.

### Shutdown Policies

Loom provides three built-in shutdown policies:

#### 1. ShutdownOnFailure ("fail fast")

If any task fails, immediately cancel all other tasks:

```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    scope.fork(() -> chargePayment());   // must succeed
    scope.fork(() -> updateInventory()); // must succeed
    scope.fork(() -> sendEmail());       // must succeed

    scope.join();
    scope.throwIfFailed();  // throws if ANY task failed
}
```

Use case: All-or-nothing transactions where partial success is unacceptable.

#### 2. ShutdownOnSuccess ("first wins")

Cancel remaining tasks as soon as one succeeds:

```java
try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
    scope.fork(() -> queryPrimaryServer());
    scope.fork(() -> queryBackupServer());
    scope.fork(() -> queryCache());

    String result = scope.result();  // returns first success, cancels others
}
```

Use case: Redundant queries where any successful response is sufficient.

#### 3. Plain StructuredTaskScope ("wait for all")

Wait for all tasks regardless of individual outcomes:

```java
try (var scope = new StructuredTaskScope<String>()) {
    Future<String> f1 = scope.fork(() -> task1());
    Future<String> f2 = scope.fork(() -> task2());

    scope.join();
    // Check each Future individually for success/failure
}
```

Use case: Parallel aggregation where partial results are still useful.

### Cancellation Propagation

Structured concurrency defines a clear cancellation hierarchy:

```
Parent Task
└── StructuredTaskScope
    ├── Child Task 1 ──► cancelled if sibling fails
    ├── Child Task 2 ──► cancelled if sibling fails
    └── Nested Scope
        ├── Grandchild 1
        └── Grandchild 2
```

Cancellation is cooperative: a task receives an `InterruptedException` at the next blocking point. Well-behaved tasks should periodically check `Thread.interrupted()` and clean up.

## Relationship to Virtual Threads

Structured concurrency and virtual threads are designed to work together:

- **Virtual threads** provide the raw material — cheap, abundant threads
- **Structured concurrency** provides the structure — safe lifecycle management

```java
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    // Virtual threads + structured scope = scalable, safe concurrency
    try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
        scope.fork(() -> handleRequest1());
        scope.fork(() -> handleRequest2());
        scope.join();
    }
}
```

This combination is the recommended replacement for:
- Thread pools with manual `Future` management
- Reactive frameworks for I/O-bound concurrent work
- `CompletableFuture.allOf()` / `anyOf()` patterns

## See Also

- [Scoped Values and Structured Concurrency — main feature page](../../12-structured-concurrency.md)
- [Virtual Threads](01-virtual-threads.md) — the other Loom pillar
- [Scoped Values](03-scoped-values.md) — context propagation within scopes
- [CompletableFuture](../../09-completable-future.md) — comparison with async style
- [Examples: Structured Concurrency](../../../examples/java/13-concurrency-structured/README.md)
