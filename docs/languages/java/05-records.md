# Records

Introduced in **Java 16** (stable). Records are immutable data carriers —
the compiler auto-generates constructor, accessors, `equals`, `hashCode`,
and `toString`:

## Declaration

```java
// Compact declaration — no boilerplate
record Point(double x, double y) {}

// Usage
Point p = new Point(3.0, 4.0);
System.out.println(p.x());           // 3.0 — accessor (not getX())
System.out.println(p);               // Point[x=3.0, y=4.0]
```

## Compact constructor

Use a compact constructor for validation or normalization without repeating parameters:

```java
record Range(int min, int max) {
    // Compact constructor — parameters are implicit
    Range {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max");
        }
    }
}
```

## Custom methods

Records can declare additional methods and static fields:

```java
record Point(double x, double y) {
    // Custom method
    double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    // Static factory
    static Point origin() {
        return new Point(0, 0);
    }
}
```

## Key restrictions

- Records **cannot extend** other classes (they implicitly extend `java.lang.Record` and are `final`).
- Records **can implement** interfaces.
- All record components are implicitly **final** — records are shallowly immutable.
- Records are ideal for DTOs, value objects, and tuple-like data structures.
