# Project Valhalla

> **Status:** In progress — preview features expected in upcoming releases post-Java 25.
> **Goal:** Bring value types and specialized generics to the JVM for better performance and memory layout.

Project Valhalla is one of the most ambitious long-running OpenJDK projects. It aims to unify Java's type system by allowing developers to define types that behave like objects (have methods, implement interfaces) but perform like primitives (no heap allocation, no object header, no reference indirection).

This has profound implications for performance, memory efficiency, and the expressiveness of generic code.

---

## Delivered Technologies

| # | Technology | Java version | Status | Page |
|---|---|---|---|---|
| 01 | Value Classes | TBD (early preview) | In progress | [01-value-classes.md](01-value-classes.md) |
| 02 | Primitive / Specialized Generics | TBD | In progress | [02-primitive-generics.md](02-primitive-generics.md) |

---

## Overview

### The Identity vs Value Distinction

Java has two fundamentally different kinds of types today:

| Aspect | Primitive (`int`, `double`) | Object (`Integer`, `String`) |
|---|---|---|
| Storage | Direct (inline) | Reference (heap allocated) |
| Identity | No (`int` has no identity) | Yes (every object is unique) |
| Header overhead | None | 12–16 bytes (mark word + class pointer) |
| Can be null | No | Yes |
| Generics support | No (boxed) | Yes |

This dichotomy creates several problems:

1. **Generics don't work with primitives** — `List<int>` is illegal; you must use `List<Integer>` with autoboxing overhead
2. **Small objects are expensive** — A `Point` with two `int` fields costs ~24 bytes due to object header + reference
3. **Arrays of objects are scattered in memory** — poor cache locality compared to flat arrays

### Valhalla's Two Pillars

**Value Classes** introduce a new kind of class that lacks identity:

- No object header, no reference indirection
- Inlined directly into containing objects or arrays
- Can still have methods, implement interfaces, be used in generics
- Cannot be synchronized on, cannot use `==` for identity

**Primitive / Specialized Generics** extend the generics system to support primitive type arguments directly:

```java
// Today: boxed, heap-allocated Integer objects
List<Integer> ints = new ArrayList<>();

// With Valhalla: flat, unboxed int array inside the list
List<int> ints = new ArrayList<>(); // no boxing overhead
```

The JVM generates specialized bytecode for each primitive specialization, similar to C++ templates but with type erasure preserved at the bytecode level for compatibility.

### Why It Takes So Long

Valhalla touches the deepest layers of the JVM:

- **Bytecode format** — new class file structures for value types
- **GC** — objects that can be inlined into other objects change heap layout assumptions
- **JIT compiler** — new optimizations for flattened arrays and scalarization
- **JNI** — calling conventions for value types crossing the native boundary
- **Reflection** — representing types that don't have identity

The JVM team has iterated through multiple design prototypes (Value Types → Inline Classes → Primitive Classes → Value Classes) as they discover interaction effects with existing JVM features.

---

## See Also

- [Records](../../05-records.md) — today's closest approximation to value types
- [Generics](../../01-generics.md) — current generics with type erasure
- [Collections Framework](../../14-collections.md) — where specialization matters most
