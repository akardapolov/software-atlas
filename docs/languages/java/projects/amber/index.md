# Project Amber

> **Status:** Ongoing — delivers incremental language productivity improvements across releases.
> **Goal:** Reduce Java verbosity and boilerplate through expressive language features.

Project Amber is the OpenJDK incubator for language-level improvements that make Java code more concise and expressive without sacrificing readability or type safety. Unlike projects that focus on a single major subsystem (concurrency, native interop, modules), Amber is a continuous stream of smaller, carefully designed language features.

Each Amber feature goes through a rigorous preview cycle: developers can try the feature behind a `--enable-preview` flag, provide feedback, and the design is refined before finalization.

---

## Delivered Technologies

| # | Technology | Java version | Status | Page |
|---|---|---|---|---|
| 01 | Records | 16 (final) | Released | [01-records.md](01-records.md) |
| 02 | Sealed Classes | 17 (final) | Released | [02-sealed-classes.md](02-sealed-classes.md) |
| 03 | Pattern Matching (`instanceof`) | 16 (final) | Released | [03-pattern-matching-instanceof.md](03-pattern-matching-instanceof.md) |
| 04 | Pattern Matching (`switch`) | 21 (final) | Released | [04-pattern-matching-switch.md](04-pattern-matching-switch.md) |
| 05 | Text Blocks | 15 (final) | Released | [05-text-blocks.md](05-text-blocks.md) |
| 06 | `var` (local type inference) | 10 (final) | Released | [06-var.md](06-var.md) |
| 07 | String Templates | 25 (preview) | Preview | [07-string-templates.md](07-string-templates.md) |
| 08 | Unnamed Variables & Patterns | 22 (final) | Released | [08-unnamed-variables.md](08-unnamed-variables.md) |

---

## Overview

### The Amber Philosophy

Amber features share a common design philosophy:

1. **Readability over terseness** — Less boilerplate, but not cryptic
2. **Static type safety preserved** — No dynamic typing or type erasure expansion
3. **Composability** — Features work together (records + pattern matching + sealed classes)
4. **Gradual adoption** — Old code keeps working; new features are opt-in

### The Records → Sealed Classes → Pattern Matching Arc

The most impactful Amber features form a coherent trilogy:

**Records** (Java 16) provide a compact way to declare immutable data carriers. They eliminate boilerplate for DTOs and value objects.

**Sealed Classes** (Java 17) let you declare a closed type hierarchy, specifying exactly which classes can extend a given class or interface.

**Pattern Matching** (Java 16–21) combines type testing, casting, and deconstruction into a single operation.

Together, these three features enable **algebraic data types** in Java:

```java
sealed interface Expr permits Add, Mul, Val {}
record Val(int value) implements Expr {}
record Add(Expr left, Expr right) implements Expr {}
record Mul(Expr left, Expr right) implements Expr {}

int eval(Expr e) {
    return switch (e) {
        case Val(int v) -> v;
        case Add(var l, var r) -> eval(l) + eval(r);
        case Mul(var l, var r) -> eval(l) * eval(r);
    }; // compiler verifies exhaustiveness — no default needed
}
```

### Preview Process

Every Amber feature follows this lifecycle:

1. **JEP published** — design document open for community review
2. **Preview in release N** — available with `--enable-preview`
3. **Feedback collection** — developer experience, edge cases, tooling
4. **Refinement** — syntax or semantics may change
5. **Finalization** — becomes a permanent language feature

Some features (like String Templates) go through multiple preview rounds before the design stabilizes.

---

## See Also

- [Records](../../05-records.md) — main feature page
- [Sealed Classes](../../06-sealed-classes.md) — main feature page
- [Pattern Matching](../../07-pattern-matching.md) — main feature page
- [Lambdas and Functional Interfaces](../../03-lambdas.md)
- [String API and Text Processing](../../20-string-api.md)
