# Design Patterns: Abstraction and Reuse of Object-Oriented Design

| | |
|---|---|
| **Author** | Erich Gamma |
| **Year** | 1993 |
| **Publication** | ECOOP (European Conference on Object-Oriented Programming) |
| **Topic(s)** | Design patterns, OOP design, software reuse |
| **Also Known as** | GoF precursor |

## Summary

This ECOOP paper presents **23 design patterns** that would later be published in the landmark **Gang of Four (GoF) book**. It represents a key step in the development of pattern catalog, moving from individual patterns to a comprehensive system for classifying and communicating about reusable object-oriented design solutions.

## Key Ideas

### Pattern Catalog Preview

The paper introduces patterns from GoF catalog:

#### Creational Patterns (5)

| Pattern | Description |
|---------|-------------|
| **Abstract Factory** | Create objects without specifying concrete class |
| **Builder** | Construct complex objects step by step |
| **Factory Method** | Delegate object creation to subclass |
| **Prototype** | Create objects by cloning a prototype |
| **Singleton** | Ensure single instance of a class |

#### Structural Patterns (7)

| Pattern | Description |
|---------|-------------|
| **Adapter** | Convert interface of one class to another |
| **Bridge** | Separate abstraction from implementation |
| **Composite** | Treat individual and group objects uniformly |
| **Decorator** | Add behavior dynamically |
| **Facade** | Provide simplified interface to complex subsystem |
| **Flyweight** | Share objects to reduce memory |

#### Behavioral Patterns (11)

| Pattern | Description |
|---------|-------------|
| **Chain of Responsibility** | Pass request along chain until handled |
| **Command** | Encapsulate request as object |
| **Interpreter** | Define grammar, evaluate sentences |
| **Iterator** | Traverse collection without exposing internals |
| **Mediator** | Coordinate communication between objects |
| **Memento** | Capture/restore object state |
| **Observer** | Notify objects of state changes |
| **State** | Object changes behavior based on internal state |
| **Strategy** | Interchangeable algorithms at runtime |
| **Template Method** | Define skeleton, let subclasses fill |
| **Visitor** | Separate operations from structure |

### Alexander's Influence

The paper explicitly acknowledges:

> "Our work is directly inspired by Christopher Alexander's ideas about pattern languages..."

Patterns in this catalog are:
- **Documented solutions** — to recurring problems
- **Format consistent with Alexander** — context, problem, solution
- **Captured from practice** — extracted from Smalltalk projects
- **Language of design** — vocabulary for discussing software design

### Pattern Relationships

The paper explores how patterns work together:

- **Composition** — patterns combine (Composite with Iterator, Decorator with Component)
- **Hierarchical composition** — patterns with sub-patterns (Proxy with State)
- **Cooperation** — Mediator coordinates related patterns
- **Encapsulation** - Facade wraps multiple patterns

## Historical Significance

### GoF Foundation

This ECOOP paper directly led to:

- **"Design Patterns"** (1994) — the landmark GoF book
- **Pattern language** — established vocabulary for software design
- **Pattern movement** - PLoP conferences and community
- **OO design education** - patterns taught as standard curriculum

### Influence on Industry

The pattern catalog influenced:

- **Framework design** — patterns embedded in Spring, Java EE, .NET
- **Software architecture** - POSA (Pattern-Oriented Software Architecture)
- **Refactoring** - identifying and applying patterns
- **Enterprise patterns** - Fowler's PoEAA extended GoF catalog

## Legacy

This paper established:

- **Pattern catalog format** — context, problem, solution structure
- **Design patterns** as reusable solutions, not one-off hacks
- **Software quality discourse** — patterns promote discussion of design trade-offs
- **Community movement** - PLoP, pattern sharing, anti-patterns

## Connections

- **Preceded by:** [Smalltalk patterns](../books/beck-1997-smalltalk-patterns.md) · [Using Pattern Languages (1987)](../papers/beck-cunningham-1987-patterns.md)
- **Followed by:** [GoF Design Patterns (1994)](../books/gof-1994-design-patterns.md)
- **Author:** [Erich Gamma](../../authors/erich-gamma.md)
- **Co-authors:** Richard Helm, Ralph Johnson, John Vlissides
- **Related topic:** [Design Patterns](../../topics/design/)
