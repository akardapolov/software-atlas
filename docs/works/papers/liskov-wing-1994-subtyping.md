# A Behavioral Notion of Subtyping

| | |
|---|---|
| **Author** | Barbara Liskov |
| **Year** | 1994 |
| **Publication** | ACM |
| **Topic(s)** | Behavioral subtyping, LSP, type theory |

## Summary

This paper, co-authored with **Jeannette Wing**, provides formal definition of **behavioral subtyping** — the foundation for the famous **Liskov Substitution Principle (LSP)**. It defines what it means for one type to be a proper subtype of another in terms of program behavior.

## Key Ideas

### Behavioral Subtyping

Liskov and Wing define subtyping behaviorally:

> Let `S` and `T` be types. Then `S` is a behavioral subtype of `T` if for every program `P` defined in terms of `T`, the behavior of `P` is unchanged when any object of type `S` is substituted for an object of type `T`.

This differs from **structural subtyping** (record-based):

- **Behavioral** - focus on what objects do, not structure
- **Contract preservation** - subtypes must uphold all behaviors
- **Pre/post conditions** - formalized in terms of program behavior

### Pre- and Post-Conditions

The formalism uses:

- **Precondition `P(p)`** - what `P` requires before execution
- **Postcondition `Q(p)`** - what `P` guarantees after execution
- **Invariant `I(o)`** - property always true for object `o`

```pseudocode
// S is behavioral subtype of T
requires P(p) => Q(p)      // Preconditions preserved
```

### LSP Definition

The paper defines LSP formally:

> For each object `o1` of type `S` used in program `P`, let `o2` be any object of type `S`. If we replace `o1` with `o2` in all uses of `o1` within `P`, then the program's behavior remains unchanged.

## Historical Significance

### LSP Formalization

This work **formalized LSP**, which had been:

- **Heuristic description** - "subtypes must be substitutable" (earlier work)
- **Precise mathematical definition** - behavior preservation
- **Foundation for type checking** - formal reasoning about subtypes

This enabled:
- **Formal verification** - prove LSP compliance statically
- **Type checker algorithms** - automated behavioral subtyping
- **Design principles** - SOLID made rigorous

### SOLID Foundation

LSP became the **"L"** in SOLID:

- **Robert C. Martin** - popularized LSP in "Agile Software Development"
- **Design principle** - foundation of OO design
- **Code review check** - behavioral substitutability test

### Type Theory Impact

This paper contributed to:

- **Behavioral type systems** - core research area
- **Object-oriented theory** - formal OO type semantics
- **Subtype polymorphism** - understanding inheritance hierarchies

## Legacy

The formal LSP definition remains:

- **SOLID standard** - taught worldwide as fundamental OO principle
- **Type checker algorithms** - based on behavioral subtyping
- **Design guidelines** - used for API design, framework design

## Connections

- **Builds on:** [CLU (1974)](./liskov-1974-adt.md)
- **Co-author:** Jeannette Wing
- **Influenced:** SOLID principles, type checking, OO design
- **Related principle:** [SOLID](../../topics/design/index.md) (if exists)
- **Author:** [Barbara Liskov](../../authors/barbara-liskov.md)
