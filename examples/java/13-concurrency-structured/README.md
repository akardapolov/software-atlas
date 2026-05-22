# Java Concurrency Evolution
## Future → CompletableFuture → Structured Concurrency

Demonstrates how the same three concurrency patterns are expressed with each
generation of the Java concurrency API.

---

## Patterns

| Pattern                     | Description                                       | Steps                                       |
|-----------------------------|---------------------------------------------------|---------------------------------------------|
| **A — Sequential Pipeline** | Tasks depend on each other; run one after another | fetchValue → transform → save               |
| **B — Async Processing**    | Non-blocking chain of transformations             | supply → apply → run                        |
| **C — Parallel Pipelines**  | Two independent pipelines combined at the end     | (fetchUser → role) + (fetchProduct → price) |

---

## API Comparison at a Glance

| Aspect                  | `Future`                         | `CompletableFuture`             | Structured Concurrency               |
|-------------------------|----------------------------------|---------------------------------|--------------------------------------|
| Composition             | Manual `get()` chaining          | Callback chain (`.thenApply`)   | Linear code inside `fork()`          |
| Blocking between steps  | Always (main thread)             | Optional (callbacks are async)  | Owner thread only (`scope.join`)     |
| Exception propagation   | `ExecutionException` wrapping    | `.exceptionally()` / `handle()` | `FailedException` at `join()`        |
| Cancellation            | Manual `future.cancel()`         | Manual on each CF               | Automatic when scope closes          |
| Parallel result combine | `get()` + `get()` + manual merge | `.thenCombine()`                | `subtask.get()` after `scope.join()` |
| Readability             | Low (callback-free but verbose)  | Medium (callback nesting)       | High (linear, top-to-bottom)         |

---

## `get()` / `join()` — Tests vs. Production

```text
┌──────────────────────────────────────────────────────────────────────────┐
│  IN TESTS / main():                                                       │
│    future.get() / cf.join() / scope.join()                                │
│    → blocks the main thread so the JVM does not exit before results are  │
│      printed. Perfectly fine here.                                        │
├──────────────────────────────────────────────────────────────────────────┤
│  IN PRODUCTION:                                                           │
│                                                                           │
│  Future          → wrap entire pipeline in ONE virtual-thread task;      │
│                    call get() only inside that virtual thread.            │
│                                                                           │
│  CompletableFuture → never call join()/get() on a platform thread;      │
│                      attach callbacks: .thenAccept() / .whenComplete().  │
│                                                                           │
│  Structured Concurrency → scope.join() IS the production API;            │
│                           the scope owner must be a virtual thread.      │
└──────────────────────────────────────────────────────────────────────────┘
```

---

## Running

Requires **Java 26** (Structured Concurrency is preview in Java 24–26).

```bash
# Compile with preview features enabled
javac --enable-preview --release 26 Main.java

# Run
java --enable-preview Main
```

### Expected output structure

```
╔══════════════════════════════════════════════════╗
║  PATTERN A — SEQUENTIAL PIPELINE                ║
╚══════════════════════════════════════════════════╝

--- A1: Future (sequential, manual chaining) ---
  fetchValue on: virtual-...
  transform on:  virtual-...
  save on:       virtual-... value=new value

--- A2: CompletableFuture (single virtual thread) ---
  fetchValue on: virtual-...
  ...

--- A3: Structured Concurrency (single virtual thread via scope.fork) ---
  fetchValue on: virtual-...
  ...

╔══════════════════════════════════════════════════╗
║  PATTERN B — ASYNC PROCESSING                   ║
╚══════════════════════════════════════════════════╝
...

╔══════════════════════════════════════════════════╗
║  PATTERN C — PARALLEL PIPELINES                 ║
╚══════════════════════════════════════════════════╝
...
  Result: Role=Ivan_ADMIN, Price=999.99   ← all three variants
```

---

## Key Takeaways

### Use `Future` when …
- You need fire-and-forget with no result.
- The calling code is already inside a virtual thread (blocking `get()` is cheap).
- You do not need non-blocking composition.

### Use `CompletableFuture` when …
- You need non-blocking callback chains (`.thenApply`, `.thenCombine`).
- You must remain compatible with Java 8+ APIs.
- You need fine-grained control over which executor each stage uses.
- **Always provide an explicit executor** (virtual-thread pool); avoid the
  default `ForkJoinPool.commonPool()` for blocking I/O stages.

### Use Structured Concurrency when …
- You want the **clearest, safest** concurrency code.
- You need automatic cancellation on failure.
- You run on Java 21+ and can enable preview features.
- Your owner thread is a virtual thread (natural fit).
- You want thread hierarchy visible in debuggers/profilers.

---

## Best Practices

1. **Virtual threads + blocking I/O** — always prefer virtual threads over
   platform threads for I/O-bound work. Blocking inside a virtual thread is free.
2. **Explicit executors for CF** — always pass `executor` to `supplyAsync` /
   `thenApplyAsync`. Never rely on `ForkJoinPool.commonPool()` for I/O.
3. **Restore interrupt flag** — catch `InterruptedException` and call
   `Thread.currentThread().interrupt()` before re-throwing.
4. **`try-with-resources` for scopes** — `StructuredTaskScope` implements
   `AutoCloseable`; always open it in a `try` block.
5. **`scope.join()` owner = virtual thread in production** — opening a scope
   on a platform thread and calling `join()` defeats the purpose; wrap it in
   a virtual-thread task.