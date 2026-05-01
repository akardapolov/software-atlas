# Designing Object-Oriented Software

| | |
|---|---|
| **Author** | Rebecca Wirfs-Brock |
| **Year** | 1990 |
| **Publication** | Prentice-Hall |
| **Topic(s)** | Object-oriented design, RDD, CRC cards |

## Summary

This foundational work introduces **Responsibility-Driven Design (RDD)** — a methodology that shifts focus from "what data structures?" to "what responsibilities do objects have?" Wirfs-Brock's thinking directly influenced **Robert C. Martin's SOLID principles** and is foundational to modern object-oriented design.

## Key Ideas

### Responsibility-Driven Design

RDD asks a fundamental design question:

- **Traditional:** "What data does this class have?"
- **RDD:** "What are this object's responsibilities?"

This shift enables:
- **Clearer boundaries** — each object has well-defined role
- **Easier testing** — test responsibilities, not data structures
- **Better communication** — discuss behavior, not internal fields

### Object Stereotypes

Wirfs-Brock identified common object archetypes:

| Stereotype | Responsibilities | Example |
|-------------|------------------|---------|
| **Information Holder** | Stores and provides data | `Customer`, `Product` |
| **Coordinator** | Manages interactions between objects | `OrderManager`, `TransactionHandler` |
| **Service Provider** | Performs specific service | `Calculator`, `Validator` |
| **Interfacer** | External interface boundary | `DatabaseAdapter`, `APIClient` |
| **Controller** | Orchestrates system behavior | `WorkflowController`, `GameState` |
| **Structurer** | Organizes other objects | `Composite`, `Builder` |

These stereotypes guide design decisions and are precursors to SOLID.

### CRC Cards

Wirfs-Brock popularized **CRC (Class-Responsibility-Collaborator) Cards** as a design tool:

```
┌─────────────────────┐
│  Customer           │
├─────────────────────┤
│ Responsibilities:   │
│ • Add to cart      │
│ • Place order      │
│                   │
│ Collaborators:      │
│ • Order            │
│ • Inventory        │
└─────────────────────┘
```

CRC cards enable:
- **Interactive design** — move cards representing object interactions
- **Team design** — multiple people can collaborate with physical cards
- **Scenario walkthrough** — act out use cases with cards

## Historical Significance

### Foundation of SOLID

RDD thinking directly influenced SOLID principles:

- **SRP** — Single Responsibility = RDD core principle
- **OCP** — Objects open for new responsibilities, closed for modification
- **DIP** — Depend on responsibility providers, not concrete classes

Wirfs-Brock's work was bridge between Kay's object-as-actors concept and Martin's formal SOLID principles.

### Influence on Modern OOP

RDD thinking influenced:

- **Martin Fowler** — Refactoring builds on responsibility separation
- **Sandi Metz** — POODR uses responsibility thinking extensively
- **Clean Code movement** — objects should have clear responsibilities

## Legacy

Wirfs-Brock's impact:

- **Design philosophy shift** — from data modeling to responsibility thinking
- **SOLID foundation** — SRP formalized RDD approach
- **Tools and techniques** — CRC cards still used for learning OOD
- **Object-oriented design education** — responsibility thinking now taught

## Connections

- **Builds on CRC Cards** — [Beck & Cunningham (1989)](../papers/beck-cunningham-1989-crc.md)
- **Preceded by:** [Alan Kay](../../authors/alan-kay.md) — objects as responsible agents
- **Influenced:** [Robert C. Martin](../../authors/robert-c-martin.md) (SOLID)
- **Related:** [Sandi Metz](../../authors/sandi-metz.md) — POODR builds on RDD
- **Related topic:** [Design Patterns](../../topics/design/) (if exists)
- **Author:** [Rebecca Wirfs-Brock](../../authors/rebecca-wirfs-brock.md)
