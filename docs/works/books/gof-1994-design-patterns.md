# Design Patterns: Elements of Reusable Object-Oriented Software

| | |
|---|---|
| **Authors** | Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides (the "Gang of Four") |
| **Year** | 1994 |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | Design patterns, OOP, reusable design |
| **ISBN** | 978-0-201-63361-0 |

## Summary

The Gang of Four (GoF) catalogued 23 recurring design solutions found
in well-designed object-oriented systems. Each pattern is named, given
a standard structure, and illustrated with examples. The book didn't
invent these patterns — it **discovered** and **documented** them,
giving the industry a shared vocabulary for discussing design.

The book's impact on software development is difficult to overstate.
Before GoF, each team invented its own solutions to common problems
and had no shared language for them. After GoF, a developer in Tokyo
and a developer in Berlin could say "use a Strategy here" and
immediately understand each other.

## Key Ideas

### 1. What Is a Design Pattern?

A pattern is a **reusable solution to a recurring problem** in a given
context. Each pattern has four elements:

| Element | Meaning |
|---------|---------|
| **Name** | A handle for the solution ("Observer", "Strategy") |
| **Problem** | When to apply the pattern |
| **Solution** | The arrangement of classes, objects, and interactions |
| **Consequences** | Trade-offs and results of applying the pattern |

Patterns are not code — they are **design concepts** that can be
implemented differently in different languages.

### 2. The 23 Patterns

Organised into three categories:

**Creational** — how objects are created:

| Pattern | Intent |
|---------|--------|
| Abstract Factory | Create families of related objects without specifying concrete classes |
| Builder | Construct complex objects step by step |
| Factory Method | Let subclasses decide which class to instantiate |
| Prototype | Clone existing objects instead of creating new ones |
| Singleton | Ensure a class has exactly one instance |

**Structural** — how objects are composed:

| Pattern | Intent |
|---------|--------|
| Adapter | Convert one interface to another that clients expect |
| Bridge | Separate abstraction from implementation |
| Composite | Treat individual objects and compositions uniformly |
| Decorator | Add responsibilities to objects dynamically |
| Facade | Provide a simplified interface to a complex subsystem |
| Flyweight | Share fine-grained objects efficiently |
| Proxy | Control access to another object |

**Behavioural** — how objects interact:

| Pattern | Intent |
|---------|--------|
| Chain of Responsibility | Pass requests along a chain of handlers |
| Command | Encapsulate a request as an object |
| Interpreter | Define a grammar and interpret sentences |
| Iterator | Access elements sequentially without exposing internals |
| Mediator | Reduce coupling by centralising communication |
| Memento | Capture and restore object state |
| Observer | Notify dependents when state changes |
| State | Change behaviour when internal state changes |
| Strategy | Encapsulate interchangeable algorithms |
| Template Method | Define a skeleton algorithm; let subclasses fill in steps |
| Visitor | Add operations to objects without modifying them |

### 3. Design Principles Behind the Patterns

The patterns are concrete applications of deeper principles:

> **"Program to an interface, not an implementation."**

Depend on abstractions (interfaces, abstract classes), not on concrete
classes. This enables substitutability and reduces coupling.

> **"Favour object composition over class inheritance."**

Inheritance creates tight coupling. Composition is more flexible —
you can change behaviour at runtime by swapping components.

These two principles anticipate the SOLID principles (Martin, 2003)
and the FP emphasis on composition over hierarchy.

### 4. The Pattern Template

Each pattern follows a consistent template:

1. **Intent** — one-sentence description
2. **Motivation** — a scenario showing the problem
3. **Applicability** — when to use it
4. **Structure** — UML class/object diagram
5. **Participants** — the classes and their roles
6. **Collaborations** — how participants interact
7. **Consequences** — trade-offs
8. **Implementation** — practical considerations
9. **Sample Code** — C++ and Smalltalk examples
10. **Known Uses** — real systems using the pattern
11. **Related Patterns** — connections to other patterns

## Historical Context

### Pattern Languages (Alexander, 1977)

The GoF borrowed the concept of "pattern language" from architect
Christopher Alexander, who catalogued recurring solutions in
building architecture (*A Pattern Language*, 1977). Alexander's insight:
good design is not created from scratch — it emerges from combining
proven solutions to recurring problems.

### The State of OOP in 1994

By 1994, OOP was dominant (C++, emerging Java), but there was no
systematic knowledge of how to design with objects well. Developers
made the same mistakes repeatedly:

- Deep inheritance hierarchies that were brittle
- God classes that did everything
- Tight coupling between components

The GoF patterns provided **proven alternatives** — ways to structure
OOP code that had been validated in real systems (ET++, Interviews,
Smalltalk frameworks).

## Impact and Legacy

### The Shared Vocabulary

The book's most lasting impact is the **vocabulary**. Pattern names
became a lingua franca:

- "Use the Observer pattern for event handling"
- "This is a Strategy — we can swap the algorithm"
- "The Adapter wraps the legacy API"

This shared vocabulary dramatically improved communication between
developers.

### Criticism and Evolution

**Over-application:** After the book's success, many developers applied
patterns mechanically — adding unnecessary abstraction layers. This led
to the critique that patterns produce "over-engineered" code.

**Language limitations:** Many GoF patterns compensate for limitations
of C++ and Java. In languages with first-class functions:

| GoF Pattern | FP equivalent |
|-------------|---------------|
| Strategy | A function parameter |
| Command | A closure |
| Observer | A callback / event stream |
| Template Method | A higher-order function |
| Factory | A constructor function |
| Decorator | Function composition |
| Iterator | A lazy sequence |

Peter Norvig noted that 16 of the 23 patterns are "invisible or
simpler" in Lisp/Dylan. This doesn't invalidate the patterns — it
shows they describe **design needs** that different paradigms address
in different ways.

**Modern perspective:** The patterns remain valuable as a vocabulary and
as solutions in class-based OOP. The deeper principles — program to
interfaces, favour composition — are universally applicable.

## Connections

- **Builds on:** Alexander — *A Pattern Language* (1977) ·
  Smalltalk & C++ design experience
- **Led to:** [Fowler — *Refactoring* (1999)](fowler-1999-refactoring.md) ·
  [Martin — SOLID (2003)](../../authors/robert-c-martin.md) ·
  Fowler — *Patterns of Enterprise Application Architecture* (2002) ·
  Pattern movement in software engineering
- **Authors:** [Alan Kay](../../authors/alan-kay.md) (influenced via Smalltalk heritage)
- **Related topic:** [OOP & Design](../../topics/design/) ·
  [Paradigms](../../topics/paradigms/) ·
  [Functional Programming](../../topics/functional/) (alternative approach)
