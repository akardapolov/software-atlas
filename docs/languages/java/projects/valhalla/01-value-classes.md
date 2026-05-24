# Value Classes

> **Project:** Valhalla
> **Java version:** TBD (early preview expected post-Java 25)
> **Status:** In progress

Value classes are a new kind of class that lacks identity. They behave like objects (have methods, implement interfaces) but are stored inline like primitives, with no object header overhead and no reference indirection.

---

## History and Evolution

### The Object/Primitive Divide

Java has two fundamentally different kinds of types:

| Aspect | Primitive (`int`, `double`) | Object (`Integer`, `String`) |
|---|---|---|
| Storage | Inline (on stack, in arrays, in objects) | Reference (heap allocated) |
| Identity | None | Every object is unique (`==` compares identity) |
| Header overhead | 0 bytes | 12–16 bytes (mark word + class pointer) |
| Nullability | Cannot be null | Can be null |
| Generics | No (must box) | Yes |

This dichotomy is baked into the JVM from its inception and creates pervasive problems:

1. **Generics don't work with primitives** — `List<int>` is illegal; must use `List<Integer>`
2. **Small objects are expensive** — A `Point` with two `int` fields costs ~24 bytes
3. **Cache locality is poor** — Arrays of objects are arrays of pointers; data is scattered

### The Valhalla Journey

Valhalla has gone through several design iterations:

| Era | Term | Key Idea | Status |
|---|---|---|---|
| 2014–2018 | Value Types | Inline, identity-less, final | Superseded |
| 2018–2021 | Inline Classes | Value types + migration path | Superseded |
| 2021–2023 | Primitive Classes | Unified primitive/object model | Superseded |
| 2023–present | Value Classes | Simpler, migration-friendly | Current design |

Each iteration refined the model based on implementation experience and community feedback.

## Implementation Deep Dive

### What Makes a Value Class?

```java
public value class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { return x; }
    public int y() { return y; }
}
```

Key characteristics:
- **`value` modifier** — declares the class as identity-less
- **Implicitly `final`** — cannot be extended
- **All fields implicitly `final`** — immutable
- **No `synchronized`** — cannot use `synchronized` on value class instances
- **No `==` identity comparison** — `==` compares value equality (like `equals()`)

### Inline Storage

Value classes are stored inline, eliminating reference overhead:

```java
// Today: reference indirection
class Point { int x, y; }
Point[] points = new Point[1000]; // Array of 1000 references + 1000 heap objects

// With Valhalla: inline storage
value class Point { int x, y; }
Point[] points = new Point[1000]; // Flat array of 2000 ints, contiguous in memory
```

Benefits:
- **Zero object header** — 12–16 bytes saved per instance
- **No reference indirection** — Better cache locality
- **No heap allocation** for local variables — Stored on stack or inline
- **Dense arrays** — Arrays contain data, not pointers

### Nullability: The `Point?` Question

Value classes introduce a new nullability model:

```java
Point p = new Point(1, 2);  // Always non-null
Point? np = null;           // Nullable reference (JVMLS syntax, TBD)
```

The design is still evolving, but the direction is:
- Value class instances are **non-null by default**
- Nullable references use a different type (syntax TBD)
- This mirrors how primitives work today (`int` cannot be null, `Integer` can)

### Relationship to Records

Records (Amber, Java 16) and value classes share immutability and data-carrier semantics:

| Aspect | Record (today) | Value Class (future) |
|---|---|---|
| Immutability | Yes | Yes |
| Automatic methods | constructor, accessors, equals, hashCode, toString | TBD (may require explicit) |
| Inlining | No (heap allocated) | Yes (inline) |
| Identity | Has identity | No identity |
| Nullability | Can be null | Non-null by default |

Records give developers experience with identity-less data today. Value classes will be the performance-optimized evolution.

## Use Cases

### Numerical Computing

```java
value class Complex {
    private final double real;
    private final double imag;
    // inline in arrays: 2× denser than object version
}
```

### Graphics

```java
value class Color {
    private final byte r, g, b, a;
    // inline in image buffers: massive cache locality improvement
}
```

### Domain Modeling

```java
value class Money {
    private final long amount;
    private final Currency currency;
    // No nulls, no identity surprises, efficient in collections
}
```

## See Also

- [Records — main feature page](../../05-records.md) — today's closest approximation
- [Primitive / Specialized Generics](02-primitive-generics.md) — Valhalla's other pillar
- [Generics — main feature page](../../01-generics.md) — current type erasure model
- [Collections Framework — main feature page](../../14-collections.md)
