# Programming with Abstract Data Types

| | |
|---|---|
| **Author** | Barbara Liskov |
| **Year** | 1974 |
| **Publication** | ACM |
| **Topic(s)** | Abstract data types, CLU, language design |

## Summary

This paper introduces **CLU language** and the concept of **Abstract Data Types (ADT)** — data structures defined by their behavior (operations) rather than just their representation. Liskov's work laid groundwork for encapsulation, behavioral subtyping, and modern type systems.

## Key Ideas

### CLU Language

Liskov created CLU to explore ADT concepts:

- **Clusters** — modules exporting a type and its operations
- **Iterators** — first language with `yield`-based iteration
- **Exception handling** — one of the first with `signal`/`except`
- **Parametric polymorphism** — generic types for ADTs

```clu
cluster = cluster[type]   # Type + operations
iterator(yield n)        # Lazy iteration over elements
signal "error"            # Raise exception
```

### Abstract Data Types

Liskov defined ADTs formally:

- **Data + operations** — inseparable unit
- **Invariants** — properties that must always hold
- **Encapsulation** — implementation hidden behind interface
- **Information hiding** — Parnas' principle applied

```
# Not ADT: separate variables
stack = { items: [], push: add, pop: remove }

# ADT: type with invariant
stack = cluster[stack]  # Items must maintain invariant
```

### Behavioral Subtyping

Liskov introduced the concept of **behavioral subtyping**:

> If for each object `o1` of type `S` there is an object `o2` of type `T` such that, for all programs `P` defined in terms of `S`, the behavior of `o1` is unchanged when `o1` is substituted for `o2`, then `S` is a subtype of `T`.

This became famous as **Liskov Substitution Principle (LSP)**.

## Historical Significance

### Foundation of Encapsulation

CLU demonstrated that:

- **Types hide implementation** — users interact through operations only
- **Invariants enforce correctness** — cluster guarantees behavior
- **Interfaces are contracts** — specified behavior is guaranteed

This directly influenced:
- **Java interfaces** — separate specification from implementation
- **.NET interfaces** — contract-based programming
- **Type systems** — theoretical foundation for subtype theory

### LSP Foundation

This paper introduced behavioral subtyping, which later became:

- **LSP in SOLID** — "L" of SOLID principles
- **Generic type constraints** — Java generics, C# constraints
- **Design by Contract** — Eiffel builds on behavioral subtyping

### Iterator Pattern

CLU's `yield` created iterator pattern:

- **Lazy evaluation** — compute next value only when needed
- **Separation of concerns** — iteration logic separate from data
- **Universal in modern languages** — Python `yield`, C# `IEnumerator`

## Legacy

CLU's innovations became standard:

- **Iterator pattern** — in every modern language
- **Exception handling** — structured error management
- **Module system** — clusters as encapsulation units
- **LSP** — foundational OO design principle

## Connections

- **Influenced:** Eiffel (DbC), Java interfaces, type theory
- **Related topic:** [Type Systems](../../topics/type-systems/)
- **Related work:** [Behavioral Notion of Subtyping (1994)](./liskov-wing-1994-subtyping.md)
- **Author:** [Barbara Liskov](../../authors/barbara-liskov.md)
