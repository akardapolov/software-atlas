# Rebecca Wirfs-Brock

| | |
|---|---|
| **Born** | ~1953 |
| **Fields** | OOP, RDD, responsibility-driven design |
| **Known for** | Creator of Responsibility-Driven Design (RDD) |
| **Awards** - ACM Software System Award (2003, for contributions to OOD) |

## Biography

Rebecca Wirfs-Brock is an American software engineer and educator who developed **Responsibility-Driven Design (RDD)** in the late 1980s. Her work shifted the focus of object-oriented design from "what data" to "what responsibilities."

Wirfs-Brock's thinking directly influenced **Robert C. Martin's SOLID principles** and is foundational to **Sandi Metz's "Practical Object-Oriented Design in Ruby" (POODR).**

## Key Contributions

### Responsibility-Driven Design (RDD)

RDD asks a different question than traditional OOD:

- **Traditional:** "What data does this class have?"
- **RDD:** "What are this object's responsibilities?"

This shift leads to better design:
- **Clearer boundaries** — each object has a well-defined role
- **Easier testing** — test responsibilities, not data structures
- **Better communication** — talk about behavior, not fields

### Object Stereotypes

Wirfs-Brock identified common object archetypes:

| Stereotype | Responsibilities | Example |
|-------------|----------------|---------|
| **Information Holder** | Stores and provides data | `Customer`, `Product` |
| **Coordinator** | Manages interactions between objects | `OrderManager`, `TransactionHandler` |
| **Service Provider** | Performs specific service | `Calculator`, `Validator` |
| **Interfacer** | External interface boundary | `DatabaseAdapter`, `APIClient` |
| **Controller** | Orchestrates system behavior | `WorkflowController`, `GameState` |
| **Structurer** | Organizes other objects | `Composite`, `Builder` |

These stereotypes guide design decisions and are precursors to SOLID thinking.

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

### Influence on SOLID

RDD thinking directly influenced SOLID principles:
- **SRP** — Single Responsibility = RDD core principle
- **OCP** — objects open for new responsibilities, closed for modification
- **DIP** — depend on responsibility providers, not concrete classes

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 1990 | *Designing Object-Oriented Software* | Book | [→](../works/books/wirfs-brock-1990-designing-oo.md) |
| 2003 | *Object Design: Roles, Responsibilities, and Collaborations* | Book | [→](../works/books/wirfs-brock-2003-object-design.md) |

## Influence

### Influenced by

- **Alan Kay** — objects as responsible agents with messaging
- **CRC Cards** — Kent Beck and Ward Cunningham's card-based design

### Influenced

- **SOLID principles** — Martin's principles formalize RDD thinking
- **Sandi Metz** — POODR builds directly on RDD concepts
- **Object-oriented design education** — responsibility thinking is now taught in OOP courses

## Legacy

Wirfs-Brock's impact:
- **Design philosophy shift** — from data modeling to responsibility thinking
- **Tools and techniques** — CRC cards still used for learning OOD
- **Foundation for modern OOP** — SOLID, POODR, and Clean Code principles

## Further Reading

- [Wikipedia: CRC Cards](https://en.wikipedia.org/wiki/Class_Responsibility_Collaborator_card)
- [Rebecca Wirfs-Brock's homepage](http://www.wirfs-brock.com/)

## Related Pages

- [Alan Kay](alan-kay.md)
- [Ward Cunningham](ward-cunningham.md)
- [Sandi Metz](sandi-metz.md)
- [SOLID principles](../topics/design/index.md) (if exists)
