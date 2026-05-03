# Java Structured Concurrency Examples

This package demonstrates Java's Structured Concurrency API (final in Java 24).

## What is Structured Concurrency?

Structured Concurrency treats related concurrent tasks as a single unit of work. When you fork tasks within a scope, the scope ensures all tasks complete (successfully or not) before proceeding.

## Key Benefits

- **Automatic lifecycle management**: All forked tasks are joined before scope exit
- **Fail-fast behavior**: Policies like `ShutdownOnFailure` cancel remaining tasks on error
- **Improved error handling**: Exception propagation is more predictable
- **Better observability**: Task hierarchy is explicit and traceable
- **Cancellation propagation**: Cancelled scopes propagate cancellation to subtasks

## Scope Types

| Scope Type | Behavior | Use Case |
|------------|----------|----------|
| `StructuredTaskScope` | Wait for all tasks | General parallel execution |
| `StructuredTaskScope.ShutdownOnFailure` | Fail fast on first error | All-or-nothing operations |
| `StructuredTaskScope.ShutdownOnSuccess` | Take first success | Redundancy/fallback patterns |

## Examples

### 1. Basic Structured Concurrency
Simple parallel execution pattern:
```java
try (var scope = new StructuredTaskScope<String>()) {
    Future<String> f1 = scope.fork(() -> task1());
    Future<String> f2 = scope.fork(() -> task2());
    scope.join(); // Wait for both
    process(f1.resultNow(), f2.resultNow());
}
```

### 2. Shutdown on First Failure
All-or-nothing pattern where partial success is unacceptable:
```java
try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    scope.fork(() -> serviceA());
    scope.fork(() -> serviceB());
    scope.join();
    scope.throwIfFailed(); // Throws if any failed
}
```

### 3. Shutdown on First Success
Race pattern where only the first successful result matters:
```java
try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
    scope.fork(() -> primary());
    scope.fork(() -> secondary());
    scope.fork(() -> fallback());
    String result = scope.result(); // First success wins
}
```

### 4. Handling Timeouts
Timeout-based execution with manual shutdown:
```java
try (var scope = new StructuredTaskScope<String>()) {
    Future<String> future = scope.fork(() -> longOperation());
    scope.joinUntil(Instant.now().plusSeconds(3));
    if (future.state() != SUCCESS) {
        scope.shutdown(); // Cancel timed-out tasks
    }
}
```

## Running the Examples

Requires Java 21+ (final in Java 24):

```bash
javac Main.java
java Main
```

## Comparison: Traditional vs Structured Concurrency

| Aspect | Traditional (ExecutorService) | Structured Concurrency |
|--------|------------------------------|------------------------|
| Task lifecycle | Manual management | Automatic via scope |
| Error handling | Complex | Built-in policies |
| Cancellation | Manual propagation | Automatic |
| Readability | Can be unclear | Explicit structure |
| Resource cleanup | Error-prone | Guaranteed via try-with-resources |

## Best Practices

1. **Use try-with-resources** with scopes for guaranteed cleanup
2. **Choose the right scope policy** for your use case
3. **Handle InterruptedException** by restoring interrupt status
4. **Keep forked tasks short** for better responsiveness
5. **Consider virtual threads** together with structured concurrency for high-throughput I/O
