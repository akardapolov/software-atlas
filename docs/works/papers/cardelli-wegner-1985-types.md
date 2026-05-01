# On Understanding Types, Data Abstraction, and Polymorphism

| | |
|---|---|
| **Author** | Luca Cardelli (with Peter Wegner) |
| **Year** | 1985 |
| **Publication** | ACM Computing Surveys |
| **Topic(s)** | Type theory, data abstraction, polymorphism, OOP |

## Summary

This seminal paper provides a **comprehensive classification of polymorphism** and formal treatment of data abstraction. Cardelli and Wegner identify **four fundamental kinds** of polymorphism and establish the theoretical foundations for understanding object-oriented type systems.

## Key Ideas

### Four Kinds of Polymorphism

The paper's most famous contribution — the classification:

| Type | Description | Example |
|--------|-------------|---------|
| **Parametric** | Generic types/functions work with any type | `List[T]` in Java |
| **Inclusion** | Subtypes can be used where supertype expected | `Animal` → `Dog` |
| **Overloading** | Same name, different signatures | `+` in Java, Python |
| **Coercion** | Implicit type conversion | `1 + "2"` → `3` |

This classification became standard vocabulary for discussing type systems.

### Data Abstraction

Cardelli defines data abstraction formally:

- **Abstract Data Type (ADT)** — type + operations, implementation hidden
- **Invariants** — properties that always hold for ADT
- **Operations** — only way to interact with data
- **Representation independence** — same ADT, multiple implementations

This provides mathematical foundation for:
- **Encapsulation** — ADTs hide implementation details
- **Module boundaries** — clear interfaces between data and code
- **Subtype relationships** — formal behavioral subtyping

### Object Types

The paper introduces formal **object types**:

```
Object = Record + Recursive Type

record(name: "Person", age: Int)
```

This foreshadowed:
- **Record types** — TypeScript, OCaml
- **Structural typing** — prototype-based languages
- **Type inference** — Hindley-Milner extended with objects

## Historical Significance

### Type Theory Foundation

This paper became the reference for:

- **Parametric polymorphism** — Milner's work generalized
- **Object-oriented types** — Cardelli's "A Theory of Objects" (1996)
- **Functional type systems** — Haskell, ML, F#

### Influence on Language Design

The classification directly influenced:

- **Java generics (2004)** — explicit parametric polymorphism
- **C# generics** — similar to ML, Cardelli
- **Haskell type classes** — formal object types
- **Type system design** — understanding trade-offs between polymorphism kinds

## Legacy

Cardelli and Wegner's classification remains foundational:

- **Type theory education** — four polymorphisms taught universally
- **Language design** — choice of polymorphism type is key design decision
- **Research** - formal properties of type systems

## Connections

- **Builds on:** Milner's polymorphism (1978)
- **Co-author:** Peter Wegner
- **Influenced:** Modern type systems, OOP languages
- **Related works:** [A Theory of Objects (1996)](./cardelli-abadi-1996-theory-objects.md)
- **Author:** [Luca Cardelli](../../authors/luca-cardelli.md)
- **Related topic:** [Type Systems](../../topics/type-systems/)
