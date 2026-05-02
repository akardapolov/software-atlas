# A Laboratory for Teaching Object-Oriented Thinking

|                 |                                                                           |
|-----------------|---------------------------------------------------------------------------|
| **Author**      | Kent Beck, Ward Cunningham                                                |
| **Year**        | 1989                                                                      |
| **Publication** | OOPSLA (Object-Oriented Programming, Systems, Languages and Applications) |
| **Topic(s)**    | CRC cards, OOP teaching, object design                                    |

## Summary

This OOPSLA paper presents **CRC (Class-Responsibility-Collaborator) Cards** — a design technique for teaching **object-oriented thinking** and exploring object interactions. Beck and Cunningham introduced cards as an interactive way for teams to design systems by walking through scenarios with physical cards representing objects.

## Key Ideas

### CRC Card Format

Each CRC card contains three sections:

```
┌─────────────────────┐
│  Customer           │
├─────────────────────┤
│ Responsibilities:   │
│ • Add to cart      │
│                   │
│ Collaborators:      │
│ • Order            │
│ • Inventory        │
└─────────────────────┘
```

- **Class name** — identifies the object
- **Responsibilities** — what the object does
- **Collaborators** — other objects this works with

### Teaching Object-Oriented Thinking

The paper presents **CRC cards as a laboratory** for OOP:

- **Scenario walkthrough** — act out use case by moving cards
- **Team design** — multiple people collaborate with cards
- **Object discovery** — identify objects and relationships from scenarios
- **Responsibility thinking** — focus on "what" not "how"

### Why Cards Work

CRC cards enable:

- **Tangibility** — physical objects can be moved around
- **Visualization** — object relationships visible on table
- **Discussion** — team focuses on design, not tools
- **Iterative** - easy to add/remove cards and responsibilities

### Card-Based Design Process

1. **Identify actors** — external entities interacting with system
2. **Define use cases** — scenarios from actor perspective
3. **Walk through scenarios** — move cards to model interactions
4. **Refine responsibilities** — adjust as understanding grows
5. **Review with team** - validate design collaboratively

## Historical Significance

### Foundation of RDD

CRC cards directly influenced:

- **Wirfs-Brock's RDD** — responsibility-driven design methodology
- **Robert C. Martin** — SRP: "A class should have one responsibility"
- **Clean Code** — clear objects with single responsibilities
- **POODR** — Metz's book extends RDD thinking

### Design Tool Legacy

CRC cards remain influential:

- **Teaching OOP** - standard tool in CS education
- **Team design** - used for object design workshops
- **Card software** - digital CRC card tools
- **Responsibility thinking** - standard OO design question

## Legacy

CRC cards established:

- **Object-oriented education** - hands-on learning of OOP
- **Design methodology** — scenario-based, collaborative approach
- **Pattern thinking** - responsibilities as design unit
- **Agile development** - user stories evolved from CRC use cases

## Connections

- **Influenced by:** [Christopher Alexander](../../authors/christopher-alexander.md) — pattern concept
- **Inspired:** [Wirfs-Brock's RDD](../books/wirfs-brock-1990-designing-oo.md)
- **Author:** [Kent Beck](../../authors/kent-beck.md) · [Ward Cunningham](../../authors/ward-cunningham.md)
- **Related Works:** [Smalltalk Best Practice Patterns](../books/beck-1997-smalltalk-patterns.md) · [WyCash](../papers/cunningham-1992-wycash.md)
