# Luca Cardelli

| | |
|---|---|
| **Born** | 1954, Italy |
| **Fields** | Type theory, OOP, language design |
| **Known for** | Theory of objects, polymorphism classification |

## Biography

Luca Cardelli is an Italian computer scientist known for his foundational work on **type theory** and **object-oriented programming**. His 1985 paper with Peter Wegner, *"On Understanding Types, Data Abstraction, and Polymorphism,"* is one of the most cited papers in programming language theory.

Cardelli's work formalized what it means for something to be an **object**, a **subtype**, or to support **inheritance**. His theoretical foundations underpin modern typed object-oriented languages like Java, C#, and Scala.

## Key Contributions

### Classification of Polymorphism

Cardelli and Wegner (1985) identified four fundamental kinds of polymorphism:

1. **Parametric polymorphism** — generics, templates (Milner's contribution)
2. **Inclusion polymorphism** — subtypes, inheritance (`Dog` is a subtype of `Animal`)
3. **Overloading polymorphism** — multiple methods with same name, different signatures
4. **Coercion polymorphism** — implicit type conversion

This classification gave the field a vocabulary for discussing type systems precisely.

### Formal Theory of Objects

Cardelli provided a formal model for objects:

- **Object = Record + Recursive Type** — objects are records with methods and a type
- **Structural subtyping** — subtyping based on structure, not name
  - If type A has all members of type B, then A is a subtype of B
  - This differs from nominal typing (common in Java)
- **Mixin composition** — formal model of multiple inheritance

### Structural Typing

Cardelli's work on **structural subtyping** influenced:

- **TypeScript** — type compatibility based on structure
- **OCaml** — structural object types
- The understanding of duck typing in OOP

### "A Theory of Objects" (1996, with Abadi)

This book provided a comprehensive formal treatment of object-oriented concepts:
- Objects, classes, inheritance
- Self types and binary methods
- Calculus of objects (Ob, Ob1<)

It became a reference for language designers and type theorists.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 1985 | *On Understanding Types, Data Abstraction, and Polymorphism* (with Wegner) | Paper | [→](../works/papers/cardelli-wegner-1985-types.md) |
| 1996 | *A Theory of Objects* (with Abadi) | Book | [→](../works/books/cardelli-abadi-1996-theory-objects.md) |

## Influence

### Influenced by

- **Robin Milner** — parametric polymorphism, type inference
- **Barbara Liskov** — abstract data types, subtyping

### Influenced

- **Typed OOP languages** — Java, C#, Scala, F#
- **TypeScript** — structural typing approach
- **Type system design** — foundations for modern language designers

## Further Reading

- [Wikipedia: Luca Cardelli](https://en.wikipedia.org/wiki/Luca_Cardelli)
- [Microsoft Research: Luca Cardelli](https://www.microsoft.com/en-us/research/people/cardelli-luca)

## Related Pages

- [Barbara Liskov](barbara-liskov.md)
- [Robin Milner](robin-milner.md)
- [Type Systems topic](../topics/type-systems/)
