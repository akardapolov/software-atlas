# Records

> **Project:** Amber
> **Java version:** 16 (final)
> **Status:** Released

Records are a compact class declaration form designed solely for transporting immutable data. The compiler automatically generates the constructor, getters, `equals()`, `hashCode()`, and `toString()`.

For the complete API reference and usage patterns, see the main feature page: [Records](../../05-records.md).

---

## History and Evolution

### The Boilerplate Problem

Before records, a simple data carrier in Java required significant boilerplate:

```java
// Pre-Java 16: ~50 lines for a simple Point class
public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override public boolean equals(Object o) { /* ... */ }
    @Override public int hashCode() { /* ... */ }
    @Override public String toString() { /* ... */ }
}
```

IDEs could generate this code, but it still cluttered source files, needed maintenance, and diverged from conventions across teams.

### Design Goals

The Amber team set out to solve this with explicit constraints:

1. **Only for data** — Records are not a general "less boilerplate" mechanism
2. **Immutable by default** — All fields are `final`
3. **Transparent** — State is the whole story; no hidden fields
4. **Compatible** — Records work with existing reflection, serialization, frameworks

### Preview Timeline

| Milestone | Release | Status |
|---|---|---|
| First preview | Java 14 | Basic records, no local records |
| Second preview | Java 15 | Compact constructors, local records |
| Finalized | Java 16 | Permanent language feature |

## Implementation Deep Dive

### What the Compiler Generates

```java
public record Point(int x, int y) {}
```

The compiler generates:

```java
public final class Point extends java.lang.Record {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() { return x; }    // not getX()
    public int y() { return y; }    // not getY()

    // equals, hashCode, toString based on all components
}
```

Key generated characteristics:
- `extends java.lang.Record` — all records implicitly extend `Record`
- Component accessors — named after the component (`x()`, not `getX()`)
- `final` class — records cannot be extended
- `final` fields — all components are immutable

### Canonical vs Compact Constructors

```java
public record Range(int low, int high) {
    // Compact constructor — validates before assignment
    public Range {
        if (low > high) {
            throw new IllegalArgumentException("low > high");
        }
    }
}
```

The compact constructor syntax (`public Range { ... }`) lets you validate or normalize parameters without repeating the parameter list.

### Record Patterns (Java 21+)

Records integrate deeply with pattern matching:

```java
// Deconstruction in switch
record Point(int x, int y) {}

String describe(Object obj) {
    return switch (obj) {
        case Point(int x, int y) -> "Point at (" + x + ", " + y + ")";
        default -> "Unknown";
    };
}
```

This synergy between records, sealed classes, and pattern matching enables algebraic data types in Java.

### Limitations

Records are intentionally constrained:

| Feature | Supported | Notes |
|---|---|---|
| Implement interfaces | ✅ Yes | Cannot extend classes (already extends `Record`) |
| Generic parameters | ✅ Yes | `record Pair<T, U>(T first, U second) {}` |
| Nested records | ✅ Yes | Static by default |
| Local records | ✅ Yes | Inside methods |
| Additional fields | ❌ No | Only the declared components |
| Additional constructors | ✅ Yes | Must delegate to canonical constructor |
| `synchronized` methods | ✅ Yes | But not on `this` (identity-less) |
| Native methods | ❌ No | Records are pure Java |

## Records as a Stepping Stone to Valhalla

Records share design DNA with Project Valhalla's value classes:

| Aspect | Record (today) | Value Class (future) |
|---|---|---|
| Identity | Has identity (can use `==`) | No identity |
| Inlining | Heap-allocated | Inlined in arrays/objects |
| Header overhead | 12–16 bytes | Zero |
| Immutability | Yes | Yes |
| Methods/interfaces | Yes | Yes |

Records give Java developers experience with identity-less, immutable data today. When Valhalla arrives, the mental model transfers directly.

## See Also

- [Records — main feature page](../../05-records.md)
- [Sealed Classes](02-sealed-classes.md) — combine with records for ADTs
- [Pattern Matching](03-pattern-matching-instanceof.md) — deconstruct records
- [Project Valhalla](../valhalla/index.md) — future value types
