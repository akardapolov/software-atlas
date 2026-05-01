# Domain Modeling Made Functional

| | |
|---|---|
| **Author** | Scott Wlaschin |
| **Year** | 2018 |
| **Publisher** | Pragmatic Bookshelf |
| **Topic(s)** | DDD + FP, domain modeling, types |
| **ISBN** | 978-1-68050-254-1 |

## Summary

Wlaschin shows that DDD naturally expresses through functional
style and rich types (F#). Core idea:
- domain = types + functions
- invariants encoded in types ("illegal states unrepresentable")
- business processes = composition of pure functions + explicit effects at edges

The book is especially useful as a bridge:
**Evans DDD (2003)** ↔ **FP design (ADTs, Result/Option, composition)**.

## Key Ideas

### 1) Ubiquitous Language through types

If the domain says "EmailAddress," code should have EmailAddress type,
not `string`.

Approach: "newtypes"/wrappers and validating constructors.

### 2) Modeling with ADTs (sum types)

Workflow is described as a set of states/events:

- `UnvalidatedOrder`
- `ValidatedOrder`
- `PricedOrder`
- `OrderPlaced`

Each transition is a function.

### 3) Railway-Oriented Programming (ROP)

Errors are part of the model, not exceptions:
- `Result<Ok, Error>`
- composition through bind/map

This makes error-handling systematic.

### 4) Functional core / imperative shell (in practice)

Pure functions for domain, I/O and infrastructure — outside.
Very "Clean Architecture"-compatible.

## Impact and Legacy

- popularized "DDD through types"
- strengthened the practice of "domain events + immutable models"
- became one of the best "practical" books on FP for engineers

## Connections

- **Builds on:** [Evans — DDD (2003)](evans-2003-ddd.md) · [FP topic](../../topics/functional/) · [Types topic](../../topics/types/)
- **Related:** [Hughes 1989](../papers/hughes-1989-why-fp.md) · Hickey/Bernhardt talks
- **Author:** [Scott Wlaschin](../../authors/scott-wlaschin.md)
