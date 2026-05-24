# Scoped Values

> **Project:** Loom
> **Java version:** 25 (final)
> **Status:** Released

Scoped Values provide immutable, inheritable context for a bounded execution scope. They are a modern replacement for `ThreadLocal` that works correctly with virtual threads and Structured Concurrency — without the memory leaks and mutability issues of the legacy approach.

---

## History and Evolution

### The ThreadLocal Problem

`ThreadLocal` has been part of Java since version 1.2, providing thread-local variables:

```java
private static final ThreadLocal<String> currentUser = new ThreadLocal<>();

currentUser.set("alice");
// ... later in the same thread ...
String user = currentUser.get(); // "alice"
```

With platform threads this works. With virtual threads — it doesn't:

```mermaid
graph TD
    subgraph "ThreadLocal Problems with VT"
        P1["💾 Memory leaks\n(values live for the thread's entire lifetime)\nWith millions of VT — a catastrophe"]
        P2["🔀 Mutable state\n(set() can be called again)\nNon-obvious side effects"]
        P3["📋 Uncontrolled inheritance\nInheritableThreadLocal copies\nvalues eagerly"]
        P4["🧹 Manual cleanup\nremove() is often forgotten\nin finally blocks"]
    end
```

### API Evolution

```mermaid
timeline
    title History of Scoped Values
    Java 20 : Preview 1 — ScopedValue in java.lang
    Java 21 : Preview 2 — API refinements
    Java 22 : Preview 3 — API refinements
    Java 23 : Preview 4 — API refinements
    Java 25 LTS : Finalized ✅ — permanent API
```

Scoped Values were developed in parallel with Structured Concurrency, as these two features are complementary: Scoped Values provide the *data context*, Structured Concurrency provides the *execution context*.

---

## Comparison: ThreadLocal vs ScopedValue

```mermaid
graph LR
    subgraph "ThreadLocal"
        TL_SET["set() — at any time"]
        TL_GET["get() — at any time"]
        TL_LIFE["Lifetime: entire thread"]
        TL_CLEAN["Needs remove()"]
        TL_INH["InheritableThreadLocal\n— eager copying"]
    end

    subgraph "ScopedValue"
        SV_BIND["where().run()\n— only in scope"]
        SV_GET["get() — only inside scope"]
        SV_LIFE["Lifetime: scope boundaries"]
        SV_CLEAN["Automatic cleanup"]
        SV_INH["Lazy inheritance\n— read only"]
    end

    TL_SET -.->|"replaces"| SV_BIND
    TL_GET -.->|"replaces"| SV_GET
    TL_LIFE -.->|"improves"| SV_LIFE
    TL_CLEAN -.->|"eliminates"| SV_CLEAN
    TL_INH -.->|"improves"| SV_INH
```

| Aspect | `ThreadLocal` | `ScopedValue` |
|---|---|---|
| Mutability | Mutable (`set()` at any time) | Immutable (once per scope) |
| Lifetime | Entire thread lifetime | Bounded scope (`run()` / `call()`) |
| Cleanup | Manual `remove()` | Automatic on scope exit |
| Memory leaks | Common | Architecturally impossible |
| Child thread inheritance | Eager copying | Lazy, read only |
| Virtual thread friendly | No | Yes |

---

## Architecture: Binding Model

### Scope Hierarchy

```mermaid
graph TD
    ROOT["Root scope\nUSER = alice"]

    subgraph OUTER["Outer scope"]
        GET1["USER.get() → alice ✅"]
        INNER["Inner scope\nUSER = bob"]

        subgraph INNER_SCOPE["Inner scope"]
            GET2["USER.get() → bob ✅\n(shadowed)"]
            VT1["Virtual thread\nUSER.get() → bob ✅\n(inherited)"]
        end
    end

    EXIT["After scope\nUSER.get() → alice ✅\n(restored)"]

    ROOT --> OUTER
    GET1 --> INNER
    INNER --> INNER_SCOPE
    INNER_SCOPE --> EXIT

    style INNER fill:#E67E22,color:#fff
    style ROOT fill:#27AE60,color:#fff
    style EXIT fill:#27AE60,color:#fff
```

### Binding Lifecycle

```mermaid
sequenceDiagram
    participant Caller as Caller
    participant SV as ScopedValue
    participant Scope as Execution Scope
    participant Child as Child Thread / VT

    Caller->>SV: ScopedValue.where(USER, "alice")
    SV->>Scope: .run(() -> { ... })

    Note over Scope: Binding is active

    Scope->>SV: USER.get() → "alice"
    Scope->>Child: fork / start virtual thread
    Child->>SV: USER.get() → "alice" (inherited, no copy!)

    Note over Scope: Nested scope

    Scope->>SV: .where(USER, "bob").run(...)
    SV->>Scope: USER.get() → "bob" (shadowed)
    Scope->>SV: exit inner scope

    SV->>Scope: USER.get() → "alice" (restored)
    Scope->>Caller: run() completed

    Note over SV: Binding destroyed automatically
```

---

## Implementation: API and Patterns

### Basic Usage

```java
private static final ScopedValue<String> USER = ScopedValue.newInstance();

// Bind a value to a scope
ScopedValue.where(USER, "alice").run(() -> {
    System.out.println(USER.get()); // alice

    // Child threads inherit the value
    Thread.ofVirtual().start(() -> {
        System.out.println(USER.get()); // alice — inherited!
    });

    // Nested scopes can override (shadowing)
    ScopedValue.where(USER, "bob").run(() -> {
        System.out.println(USER.get()); // bob — shadowed
    });

    System.out.println(USER.get()); // alice — outer scope unchanged
});

// USER.get() throws NoSuchElementException outside the scope
```

### Multiple Scoped Values at Once

```java
ScopedValue.where(USER, "alice")
           .where(REQUEST_ID, "req-123")
           .where(TRACE_ID, "trace-456")
           .run(() -> {
               // All three are available
               processRequest();
           });
```

### Integration with Structured Concurrency

```java
private static final ScopedValue<String> USER = ScopedValue.newInstance();

public UserData getUserData(String userId) {
    return ScopedValue.where(USER, userId).call(() -> {
        try (var scope = StructuredTaskScope.<Object>open()) {
            Subtask<Profile>     profile = scope.fork(() -> fetchProfile(USER.get()));
            Subtask<List<Order>> orders  = scope.fork(() -> fetchOrders(USER.get()));

            scope.join(); // FailedException if something failed

            return new UserData(profile.get(), orders.get());
        }
    });
}
```

`USER` is automatically inherited by all tasks inside the structured scope. No manual parameter passing, no `ThreadLocal` pollution.

---

## Performance Model

```mermaid
graph LR
    subgraph "ThreadLocal read"
        TL1["Thread object"]
        TL2["ThreadLocalMap\n(per thread)"]
        TL3["Entry lookup\n(hash map)"]
        TL1 --> TL2 --> TL3
    end

    subgraph "ScopedValue read"
        SV1["Current binding\n(stack pointer)"]
        SV2["Parent binding\n(1-2 hops max)"]
        SV1 --> SV2
    end
```

- **Fast reads**: walking the binding chain (usually 1–2 steps)
- **No allocation on read**: unlike `ThreadLocal`, which may allocate `ThreadLocalMap` entries
- **No cleanup cost**: bindings are stack-disciplined, `remove()` is not needed
- **Lazy inheritance**: child threads don't copy values, they read from the parent's binding table

---

## Comparison of Context Passing Approaches

| Approach | When to use | Limitation |
|---|---|---|
| Explicit parameter passing | Short call chains | Pollutes every method signature |
| `ThreadLocal` | Legacy code, platform threads only | Memory leaks, mutability, not for VT |
| `ScopedValue` | Modern code with VT / SC | Requires bounding all reads within a scope |
| Context objects | Complex multi-value context | Manual propagation, no automatic inheritance |

---

## See Also

- [Scoped Values and Structured Concurrency — main feature page](../../12-structured-concurrency.md)
- [Virtual Threads](01-virtual-threads.md) — designed to work with Scoped Values
- [Structured Concurrency](02-structured-concurrency.md) — execution context companion
- [Examples: Structured Concurrency](../../../examples/java/13-concurrency-structured/README.md)
