# Object Design: Roles, Responsibilities, and Collaborations

| | |
|---|---|
| **Author** | Rebecca Wirfs-Brock (with Alan McKeith) |
| **Year** | 2003 |
| **Publication** | Addison-Wesley |
| **Topic(s)** | Object-oriented design, RDD, object design |

## Summary

This book extends Wirfs-Brock's foundational work on **Responsibility-Driven Design** by providing deeper guidance on designing objects with clear roles, defined responsibilities, and well-structured collaborations. It became a **key reference** for applying RDD principles in practical object-oriented design.

## Key Ideas

### Role Definition

The book formalizes what an object's **role** means:

- **Responsibilities** — behaviors and services the object provides
- **Collaborations** — other objects this object works with
- **Contracts** — protocols for interaction

A role is a set of related responsibilities:

```pseudocode
Role: OrderProcessing
Responsibilities:
  - Validate order
  - Calculate total
  - Apply discounts
Collaborators:
  - Inventory (check availability)
  - Payment (process payment)
```

### Responsibility Patterns

Wirfs-Brock presents design patterns for responsibilities:

- **Granular roles** — many small, focused objects over few monolithic ones
- **Layered responsibilities** — separate presentation, business, data access
- **Protocol objects** - define interaction contracts between collaborators
- **Controller pattern** — objects coordinate others' actions

### Collaboration Patterns

The book identifies common collaboration patterns:

| Pattern | Description | Example |
|---------|-------------|---------|
| **Delegation** | Object passes request to collaborator | UI delegates to controller |
| **Observer** | Object notifies others of changes | Model notifies Views |
| **Factory** | Object creates collaborators | OrderFactory creates Orders |
| **Strategy** | Object uses pluggable collaborator | PaymentProcessor uses strategy |
| **Mediator** | Object coordinates communication | ChatMediator manages conversations |
| **Chain of Responsibility** | Request passed through chain of handlers | Exception handlers in chain |

## Historical Significance

### Design Pattern Foundation

This work provided:

- **Formal pattern catalog** - responsibilities and collaborations categorized
- **Design guidance** - when to use each pattern
- **OO design evolution** - from monolithic to collaborative systems
- **Pattern language** - vocabulary for discussing OO design

### Influence on Modern Design

The book influenced:

- **Design Patterns community** - formal treatment of collaborations
- **POSA (Pattern-Oriented Software Architecture)** - architectural patterns based on collaboration
- **Domain-Driven Design** - objects as role players in domain

## Legacy

Wirfs-Brock's two books established:

- **RDD methodology** — standard for thinking about objects
- **Collaboration patterns** — vocabulary for OO design
- **Design education** - taught in universities worldwide
- **Design tools** - CRC cards with responsibility fields

## Connections

- **Follows:** [Designing Object-Oriented Software (1990)](../books/wirfs-brock-1990-designing-oo.md)
- **Co-author:** Alan McKeith
- **Influenced:** Domain-Driven Design, architectural patterns
- **Author:** [Rebecca Wirfs-Brock](../../authors/rebecca-wirfs-brock.md)
