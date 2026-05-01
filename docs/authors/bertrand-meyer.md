# Bertrand Meyer

| | |
|---|---|
| **Born** | 1950, Paris, France |
| **Fields** | Software engineering, programming languages, OOP |
| **Known for** | Eiffel, Design by Contract, Command-Query Separation, OOSC |

## Biography

Bertrand Meyer is a French computer scientist who has spent his career
bridging the gap between formal methods and practical software engineering.
He created the **Eiffel** programming language (1986), wrote the influential
book *Object-Oriented Software Construction* (OOSC, 1988/1997), and
formulated the principles of **Design by Contract (DbC)** and **Command-Query
Separation (CQS)**.

Meyer studied at École Polytechnique and Stanford University. He founded
Eiffel Software (originally Interactive Software Engineering) and later
became professor at ETH Zürich (2001–2015), where he held the chair of
software engineering previously held by Niklaus Wirth.

## Key Contributions

### Design by Contract — DbC (1986)

Meyer's most influential idea: every software component defines a formal
**contract** consisting of:

- **Preconditions** — what must be true *before* a method is called
  (the caller's obligation)
- **Postconditions** — what will be true *after* the method returns
  (the supplier's obligation)
- **Class invariants** — what must always be true for objects of a class

Contracts make responsibilities explicit. When a bug occurs, the contract
immediately reveals who is at fault:

- Precondition violated → the caller has a bug
- Postcondition violated → the supplier has a bug
- Invariant violated → the class implementation has a bug

DbC extends Tony Hoare's axiomatic semantics (`{P} C {Q}`) from
theoretical proofs to practical daily programming. Contracts are:

- **Executable** — checked at runtime during development and testing
- **Documentary** — serve as precise interface documentation
- **Inheritable** — subclass contracts must be compatible with superclass
  contracts (connecting to Liskov's substitution principle)

### Command-Query Separation — CQS

A principle for method design:

- A **command** changes the state of an object but returns nothing
- A **query** returns information but changes nothing

No method should both change state and return a value. This makes programs
easier to reason about because queries are side-effect-free (like
mathematical functions) and commands have clear effects.

CQS influenced:

- **CQRS** (Command Query Responsibility Segregation) — the architectural
  pattern that separates read and write models
- Functional programming's distinction between pure functions and effects
- API design best practices

### Eiffel Programming Language (1986)

Meyer designed Eiffel to embody his ideas about software engineering:

- **Built-in contracts** — preconditions, postconditions, and invariants
  are language constructs, not comments
- **Multiple inheritance** — with careful rules for conflict resolution
- **Generics** — parameterised types (before Java and C# had them)
- **Garbage collection** — automatic memory management
- **Uniform access principle** — a client cannot tell whether a property
  is stored or computed

Eiffel was not widely adopted commercially, but its ideas permeated other
languages: .NET Code Contracts, Java assertions, Kotlin contracts, and
countless DbC libraries.

### Object-Oriented Software Construction (1988 / 1997)

One of the most comprehensive books on OOP ever written. OOSC covers:

- Design by Contract
- Inheritance and polymorphism
- Genericity and typing
- Exception handling
- Concurrency
- Persistence
- Software engineering methodology

The 1997 second edition (1,200+ pages) is considered the definitive
treatment of classical OOP principles.

### The Open-Closed Principle (1988)

Meyer formulated the Open-Closed Principle:

> "Software entities (classes, modules, functions) should be open for
> extension but closed for modification."

This means you should be able to add new behaviour (open for extension)
without changing existing code (closed for modification). Robert C. Martin
later adopted this as the "O" in SOLID, though Martin's interpretation
emphasises abstraction (interfaces) while Meyer's original focused on
inheritance.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1988 | *Object-Oriented Software Construction* (1st edition) | Book | — |
| 1992 | *Eiffel: The Language* | Book | — |
| 1997 | *Object-Oriented Software Construction* (2nd edition) | Book | — |
| 2009 | *Touch of Class: Learning to Program Well with Objects and Contracts* | Book | — |

## Influence

### Influenced by

- **Tony Hoare** — Hoare logic ({P} C {Q}) is the theoretical foundation of DbC
- **Edsger Dijkstra** — formal methods, correctness by construction
- **Barbara Liskov** — abstract data types, specification

### Influenced

- **Robert C. Martin** — Open-Closed Principle adopted into SOLID
- **.NET Code Contracts** — Microsoft's implementation of DbC for C#/VB.NET
- **F#** — has built-in contract programming
- **Kotlin** — contract functions
- **CQRS pattern** — CQS scaled to architecture level
- **Spec# and Dafny** — contract-based verification at Microsoft Research

## Quotes

> "Correctness is not an add-on quality; it must be built in from
> the start."

> "Ask not first what the system does; ask what it does it TO."

> "A routine that changes state should not return a value; a routine
> that returns a value should not change state."

## Further Reading

- [Wikipedia: Bertrand Meyer](https://en.wikipedia.org/wiki/Bertrand_Meyer)
- [Bertrand Meyer's homepage](https://bertrandmeyer.com/)
- [Eiffel Software](https://www.eiffel.com/)

## Related Pages

- [OOP & Design](../topics/design/)
- [Type Systems](../topics/types/)
- [Tony Hoare](tony-hoare.md)
- [Barbara Liskov](barbara-liskov.md)
- [Robert C. Martin](robert-c-martin.md)
