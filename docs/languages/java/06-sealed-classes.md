# Sealed Classes

Introduced in **Java 17** (stable). Sealed classes restrict which classes
can extend or implement them — enabling better modeling of closed hierarchies
and supporting exhaustive pattern matching.

## Basic syntax

```java
sealed interface Shape permits Circle, Rectangle, Triangle {}

record Circle(double radius) implements Shape {}
record Rectangle(double w, double h) implements Shape {}
record Triangle(double b, double h) implements Shape {}
```

## Permitted subclass requirements

All permitted subclasses must:

1. Belong to the same module (or package in non-modular code).
2. Explicitly declare themselves as `final`, `sealed`, or `non-sealed`.

```java
sealed interface Animal permits Dog, Cat, Bird {}

final class Dog implements Animal {}      // no further subclasses
sealed class Cat implements Animal permits Siamese, Persian {}
non-sealed class Bird implements Animal {} // open for extension
```

## Exhaustive pattern matching

When combined with `switch` expressions, the compiler can verify that all
permitted subtypes are covered:

```java
static double area(Shape shape) {
    return switch (shape) {
        case Circle c       -> Math.PI * c.radius() * c.radius();
        case Rectangle r    -> r.w() * r.h();
        case Triangle t     -> 0.5 * t.b() * t.h();
    }; // no default needed — compiler knows all cases
}
```

## Use cases

- **Algebraic data types** — model sum types (one of several variants).
- **Domain modeling** — represent a closed set of states or types.
- **API design** — control which classes participate in a type hierarchy.
