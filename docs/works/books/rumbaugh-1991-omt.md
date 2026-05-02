# Object-Oriented Modeling and Design

| | |
|---|---|
| **Author** | James Rumbaugh |
| **Year** | 1991 |
| **Publication** | Prentice-Hall |
| **Topic(s)** | UML, OMT, object modeling, state diagrams |

## Summary

This book presents **OMT (Object Modeling Technique)** — one of the first comprehensive methodologies for **object-oriented analysis and design**. Rumbaugh's work, along with Grady Booch's Booch Method and Ivar Jacobson's use cases, became the foundation for **UML (Unified Modeling Language)** class diagrams.

## Key Ideas

### Three Model Layers

OMT defined three complementary models for OO systems:

1. **Object Model** — static structure: classes, attributes, relationships
2. **Dynamic Model** — object state changes over time
3. **Functional Model** — data flow and transformations

This **layered approach** provided complete OO modeling.

### Object Model

The object model describes:

- **Classes** — with attributes and methods
- **Relationships** — associations, aggregations, compositions, inheritances
- **Generalizations/specializations** — type hierarchies
- **Constraints** — rules objects must satisfy

This became the foundation for **UML class diagrams**.

### Dynamic Model

The dynamic model captures:

- **State diagrams** — states, transitions, events, actions
- **Life cycles** — object creation and destruction
- **Activity diagrams** - system-level workflows
- **Event traces** — sequences of system events

State diagrams in OMT evolved into **UML state machine diagrams**.

### Functional Model

The functional model describes:

- **Data stores** — persistent data repositories
- **Processes** — transformations data flows through
- **Actors** — external entities consuming/producing data
- **Data flow** - how information moves through system

### OMT Notation

Rumbaugh introduced graphical notation with:

| Element | Symbol | Description |
|---------|-------|-------------|
| **Class** | Rectangle with name | Class definition |
| **Object** | Rectangle with underline | Object instance |
| **Relationships** | Various arrow types | Association, aggregation, inheritance, etc. |
| **State** | Rounded rectangle | Object state or activity |
| **Data store** | Open rectangle with "store" text | Persistent data |

OMT notation evolved into UML class diagram notation after 1997.

## Historical Significance

### UML Foundation

Rumbaugh's OMT, with Booch's OOAD and Jacobson's use cases:

- **Formed UML** (1997) — Unified Modeling Language
- **Unified class diagrams** — OMT notation became standard
- **Industry adoption** - tools like Rational Rose, Together
- **Education** - UML taught in CS and software engineering courses

### State Machine Pattern

OMT's **state diagrams** directly influenced:

- **State machine pattern** — widely used in game and UI programming
- **Pattern-Oriented Software Architecture (POSA)** — state-based architectural patterns
- **Workflow modeling** - business process modeling
- **Controller design** - managing state transitions

## Legacy

Rumbaugh's influence continues:

- **UML standard** — class diagrams remain primary OO modeling tool
- **State modeling** - formal approach to object behavior
- **Architectural patterns** - POSA built on state machine concepts
- **Design tools** - Rational Rose, Enterprise Architect, Together

## Connections

- **Co-created UML:** [Booch (OOAD)](../books/booch-1994-ooad.md) · [Jacobson (Use Cases)](../books/jacobson-1992-oose.md)
- **Influenced:** [Pattern-Oriented Software Architecture](../topics/architecture/index.md) (if exists)
- **Related:** [State Machine pattern](../topics/design/index.md) (if exists)
- **Author:** [James Rumbaugh](../../authors/james-rumbaugh.md)
