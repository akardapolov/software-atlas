# A Theory of Objects

| | |
|---|---|
| **Author** | Luca Cardelli (with Martin Abadi) |
| **Year** | 1996 |
| **Publication** | Addison-Wesley |
| **Topic(s)** | Object types, type theory, programming language theory |

## Summary

*"A Theory of Objects"* is Cardelli's comprehensive monograph providing a **formal mathematical treatment of objects**, their types, subtyping, and inheritance. It became a foundational reference for object-oriented type systems and influenced subsequent language research.

## Key Ideas

### Formal Object Model

The book defines objects formally:

```
Object = Record + Recursive Type

record(name: "Person", age: Int)
```

This formalization extends earlier work on **record types** and **recursive types** to create a comprehensive object theory.

### Subtyping

Cardelli formalizes several notions of subtyping:

| Type | Description |
|--------|-------------|
| **Structural** | Subtype determined by structure, not name | `{x, y}` subtype of `{x, y, z}` |
| **Nominal** | Subtype explicitly declared (extends, implements) | Java, C# interfaces |
| **Behavioral** | Objects of subtype can substitute without behavior change | Liskov Substitution Principle |
| **F-Bounded** | Subtyping with function variance (invariant on argument) | Contravariant arrays, covariant functions |

This categorization clarified debates about inheritance in typed languages.

### Binary Methods

The book introduces **binary methods** — methods on pairs:

```
select(first: {x, y}, second: {x, y})
```

Binary methods enable:

- **Pattern matching** — functional languages (ML, Haskell)
- **Type inference** - Hindley-Milner with more expressivity
- **Function objects** - objects representing functions
- **Module boundaries** - explicit imports/exports

### Self Type

Cardelli formalizes the **type** of objects:

```
Type = { methods: MethodType, selftype: Type }
```

An object's type includes:
- **Methods** — what operations it supports
- **Self type** — type returned by sending `self` method

This enables:
- **Polymorphism on self** - type changes with inheritance
- **Meta-objects** - objects describing objects (classes)
- **Reflection** - query object type at runtime

## Historical Significance

### Object Type Theory Foundation

This monograph established:

- **Formal object semantics** — mathematical treatment of OOP types
- **Subtype calculi** - formal reasoning about inheritance
- **Binary method types** - foundation for functional objects

### Influence on Languages

The work directly influenced:

- **Typed OOP** — formal semantics for Java, C#
- **Functional objects** - OCaml objects, Haskell type classes
- **Type theory research** - subsequent theoretical work on objects
- **Language design** — self types, variance annotations

## Legacy

Cardelli's object theory remains:

- **Standard reference** — cited in type system papers
- **Language design guidance** — understanding subtyping trade-offs
- **Research foundation** - formal methods for object languages

## Connections

- **Builds on:** [Types, Data Abstraction (1985)](./cardelli-wegner-1985-types.md)
- **Co-author:** Martin Abadi
- **Influenced:** Typed OOP languages, type theorists
- **Author:** [Luca Cardelli](../../authors/luca-cardelli.md)
- **Related works:** [On Understanding Types (1985)](./cardelli-wegner-1985-types.md)
