# Ole-Johan Dahl

| | |
|---|---|
| **Born** | 1931, Mandal, Norway |
| **Died** | 2002, Oslo, Norway |
| **Fields** | Programming languages, object-oriented programming, simulation |
| **Known for** | Simula (with Kristen Nygaard), invention of OOP |
| **Turing Award** | 2001 (with Nygaard) |

## Biography

Ole-Johan Dahl was a Norwegian computer scientist who, together with
Kristen Nygaard, created **Simula** — the first object-oriented programming
language. Dahl spent most of his career at the University of Oslo, where
he was professor of computer science from 1968 until his death.

Dahl was the primary language designer and implementer of Simula, while
Nygaard contributed the conceptual framework from his background in
operations research and simulation. Their partnership produced one of the
most consequential inventions in computing history.

Dahl and Nygaard received the Turing Award in 2001 "for ideas fundamental
to the emergence of object-oriented programming, through their design of
the programming languages Simula I and Simula 67." Tragically, both died
in 2002 before they could deliver their Turing Award lectures.

## Key Contributions

### Simula I (1962) and Simula 67 (1967)

Simula began as a language for **discrete event simulation** — modelling
systems like queuing networks, traffic flows, and manufacturing processes.
The need to model real-world entities with state and behaviour naturally
led Dahl and Nygaard to invent:

- **Classes** — blueprints for creating objects with data and procedures
- **Objects** — instances of classes with their own state
- **Inheritance** — subclasses that extend and specialise superclasses
- **Virtual methods** — methods that can be overridden in subclasses,
  enabling runtime polymorphism
- **Coroutines** — a mechanism for cooperative multitasking between
  simulation entities

Simula 67 was the version that formalised these concepts into a coherent
language design. It was implemented as an extension of ALGOL 60.

### The Insight That Generalised

The genius of Simula was that its creators realised their simulation
concepts were **general-purpose**. A class doesn't have to represent a
queue or a customer — it can represent any concept. An object doesn't
have to be a simulation entity — it can be any entity. Inheritance
doesn't have to model simulation specialisation — it can model any
"is-a" relationship.

This generalisation — from simulation technique to universal programming
paradigm — is what made Simula the ancestor of all OOP languages.

### Structured Programming Contribution

Dahl co-authored *Structured Programming* (1972) with Dijkstra and Hoare,
contributing the chapter "Hierarchical Program Structures" which discussed
how classes and hierarchies organise programs.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1966 | "SIMULA — an ALGOL-based Simulation Language" (with Nygaard) | Paper | — |
| 1967 | Simula 67 language specification | Specification | — |
| 1972 | "Hierarchical Program Structures" (in *Structured Programming*) | Book chapter | — |

## Influence

### Influenced by

- **ALGOL 60** — Simula was built as an extension of ALGOL
- **Operations research** — simulation needs drove language design
- **Nygaard's conceptual vision** — modelling real-world systems in code

### Influenced

- **Alan Kay** — saw Simula at Utah; it inspired Smalltalk's objects (though Kay took messaging in a different direction)
- **Bjarne Stroustrup** — created C++ explicitly as "C with Simula-like classes"
- **James Gosling** — Java's class-based OOP descends from Simula via C++
- **All class-based OOP languages** — C#, Python, Ruby, Kotlin, Swift

## Further Reading

- [Wikipedia: Ole-Johan Dahl](https://en.wikipedia.org/wiki/Ole-Johan_Dahl)
- [Turing Award: Dahl and Nygaard](https://amturing.acm.org/award_winners/dahl_0163007.cfm)
- [Simula history at Ifi, University of Oslo](https://www.mn.uio.no/ifi/english/about/ole-johan-dahl/)

## Related Pages

- [Simula](../languages/simula/index.md)
- [Paradigms](../topics/paradigms/index.md)
- [OOP & Design](../topics/design/index.md)
- [Kristen Nygaard](kristen-nygaard.md)
- [Alan Kay](alan-kay.md)
