# Scoped Values

> **Project:** Loom
> **Java version:** 25 (final)
> **Status:** Released

Scoped values provide immutable, inheritable context for a bounded execution scope. They are a modern replacement for `ThreadLocal` that works correctly with virtual threads and structured concurrency, eliminating the memory leaks and mutability pitfalls of the legacy approach.

---

## History and Evolution

### The ThreadLocal Problem

`ThreadLocal` has been part of Java since 1.2, providing thread-local variables:

```java
private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

currentUser.set("alice");
// ... later in the same thread ...
String user = currentUser.get(); // "alice"
```

This works for platform threads but breaks down with virtual threads:

1. **Memory leaks** — `ThreadLocal` values persist for the thread's lifetime. With virtual threads (millions, short-lived), this accumulates garbage.
2. **Mutable state** — Values can be changed (`set()` called multiple times), making reasoning about state hard.
3. **No inheritance control** — `InheritableThreadLocal` copies values to child threads eagerly, whether needed or not.
4. **Cleanup burden** — `remove()` must be called to avoid leaks, but it's often forgotten in `finally` blocks.

### The Loom Solution

Scoped values were designed specifically to address these issues:

| Aspect | `ThreadLocal` | `ScopedValue` |
|---|---|---|
| Mutability | Mutable (`set()` anytime) | Immutable (set once per scope) |
| Lifetime | Thread lifetime | Bounded scope (`run()` / `call()`) |
| Cleanup | Manual `remove()` | Automatic on scope exit |
| Memory leaks | Common | Impossible (scope-bounded) |
| Child thread inheritance | Eager copy | Lazy, unidirectional inheritance |
| Virtual thread friendly | No | Yes |

### Development Timeline

| Milestone | Release | Status |
|---|---|---|
| First preview | Java 20 | `ScopedValue` in `java.lang` |
| Second preview | Java 21 | API refinements |
| Finalized | Java 25 | Permanent API |

Scoped values were developed in parallel with structured concurrency, as the two features are complementary: scoped values provide the *data context*, while structured concurrency provides the *execution context*.

## Implementation Deep Dive

### The Scope-Binding Model

```java
private static final ScopedValue<String> USER = ScopedValue.newInstance();

// Bind a value to a scope
ScopedValue.where(USER, "alice").run(() -> {
    // Inside this scope, USER.get() returns "alice"
    System.out.println(USER.get()); // alice

    // Child threads (virtual or platform) inherit the value
    Thread.ofVirtual().start(() -> {
        System.out.println(USER.get()); // alice — inherited!
    });

    // Nested scopes can rebind
    ScopedValue.where(USER, "bob").run(() -> {
        System.out.println(USER.get()); // bob — shadowed in inner scope
    });

    System.out.println(USER.get()); // alice — outer scope unchanged
});

// Outside the scope, USER.get() throws NoSuchElementException
```

Key properties:
- **Immutable**: Once bound in a scope, the value never changes
- **Hierarchical**: Inner scopes can shadow outer bindings, but never mutate them
- **Automatic cleanup**: The binding disappears when the scope's `run()`/`call()` completes
- **Efficient inheritance**: Child threads read through a pointer chain; no eager copying

### Multiple Scoped Values

You can bind multiple values simultaneously:

```java
ScopedValue.where(USER, "alice")
           .where(REQUEST_ID, "req-123")
           .where(TRACE_ID, "trace-456")
           .run(() -> {
               // All three are accessible here
               processRequest();
           });
```

### Integration with Structured Concurrency

Scoped values shine when combined with structured concurrency:

```java
private static final ScopedValue<String> USER = ScopedValue.newInstance();

public UserData getUserData(String userId) {
    return ScopedValue.where(USER, userId).call(() -> {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<Profile> profile = scope.fork(() -> fetchProfile(USER.get()));
            Future<List<Order>> orders = scope.fork(() -> fetchOrders(USER.get()));

            scope.join();
            scope.throwIfFailed();

            return new UserData(profile.resultNow(), orders.resultNow());
        }
    });
}
```

The `USER` scoped value is automatically inherited by all forked tasks within the structured scope. No manual passing, no `ThreadLocal` pollution.

### Performance Model

Scoped values are optimized for the common case:

- **Read is fast**: A pointer chase through a stack of bindings (usually 1–2 hops)
- **No allocation per read**: Unlike `ThreadLocal`, which may allocate `ThreadLocalMap` entries
- **No cleanup cost**: Bindings are stack-disciplined; no `remove()` needed
- **Lazy inheritance**: Child threads don't copy values; they read from parent's binding table

## Comparison with Alternatives

| Approach | Use when | Limitation |
|---|---|---|
| Explicit parameter passing | Small call chains | Pollutes every method signature |
| `ThreadLocal` | Legacy code, platform threads only | Memory leaks, mutable, not virtual-thread-safe |
| `ScopedValue` | Modern code with virtual threads / structured concurrency | Requires bounding all reads within a scope |
| Context objects | Complex multi-value context | Manual propagation, no automatic inheritance |

## See Also

- [Scoped Values and Structured Concurrency — main feature page](../../12-structured-concurrency.md)
- [Virtual Threads](01-virtual-threads.md) — designed to work with scoped values
- [Structured Concurrency](02-structured-concurrency.md) — execution context companion
- [Examples: Structured Concurrency](../../../examples/java/13-concurrency-structured/README.md)
