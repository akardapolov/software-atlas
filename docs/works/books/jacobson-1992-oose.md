# Object-Oriented Software Engineering: A Use Case Driven Approach

| | |
|---|---|
| **Author** | Ivar Jacobson |
| **Year** | 1992 |
| **Publication** | ACM Press / Addison-Wesley |
| **Topic(s)** | UML, use cases, OOSE, requirements engineering |

## Summary

This book introduces the **OOSE (Object-Oriented Software Engineering)** methodology, which became one of the **three approaches** that formed **UML** (Unified Modeling Language) in 1997. Jacobson's work focused on **use cases** as a way to elicit and document software requirements from the user's perspective.

## Key Ideas

### Use Cases

Jacobson's primary contribution was the **use case** concept:

- **Actor** — external entity interacting with the system
- **Use case** — specific sequence of interactions to achieve a goal
- **Scenario** - narrative describing how actor uses system
- **Flow** - primary and alternate paths through a use case

This approach shifted focus from "what data structures?" to "what functions does the system provide?"

### Use Case Template

Jacobson defined a structured format:

```
Use Case: Withdraw Cash

Actor: ATM Customer
Goal: Get cash amount from account
Main flow:
  1. Customer inserts card
  2. ATM validates card
  3. Customer enters amount
  4. ATM dispenses cash
  5. Customer takes card and cash

Alternative flows:
  - Insufficient funds → Cancel transaction
  - Invalid card → Reject and prompt
```

### Object-Oriented Software Engineering

Jacobson's methodology applied OO thinking to requirements:

- **Use case objects** — entities, attributes, relationships
- **Domain modeling** - business concepts represented as objects
- **Traceability** — trace from use cases to design elements
- **Interfaces** - use cases define object interfaces

### OOSE Method

OOSE provided:

- **Use case modeling** - complete scenario documentation
- **Object analysis** - identify classes from use case scenarios
- **Interaction diagrams** - visualize actor-system interaction
- **State diagrams** - model object behavior over time

## Historical Significance

### UML Use Case Diagrams

Jacobson's work directly influenced **UML use case notation**:

- **Stick figure actor** - standard representation
- **Oval use case** - standard representation
- **Relationships** - include, extend, generalization connections
- **Sequence diagrams** - show interaction flow over time

Use case diagrams became primary way to:

- **Document requirements** - capture user scenarios visually
- **System analysis** - identify actors and their goals
- **Test scenarios** - use cases as test cases

### Influence on Requirements Engineering

OOSE established:

- **Scenario-based elicitation** - talk to users about their workflows
- **Actor identification** - find all external entities
- **Functional requirements** - define what system must do
- **Non-functional requirements** - performance, security, reliability

## Legacy

Jacobson's influence continues:

- **Requirements engineering** - use case methodology remains standard
- **Agile user stories** - simplified use cases (INVEST criteria)
- **Behavior-driven development** - extends use case thinking
- **Alistair Cockburn** - "Writing Effective Use Cases"

## Connections

- **Co-created UML:** [Booch (Class diagrams)](../books/booch-1994-ooad.md) · [Rumbaugh (OMT)](../books/rumbaugh-1991-omt.md)
- **Influenced:** User Stories (Agile), BDD scenarios
- **Related topic:** [Requirements](../topics/process/) (if exists)
- **Author:** [Ivar Jacobson](../../authors/ivar-jacobson.md)
