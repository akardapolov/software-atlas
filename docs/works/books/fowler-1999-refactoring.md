# Refactoring: Improving the Design of Existing Code

| | |
|---|---|
| **Author** | Martin Fowler (with Kent Beck) |
| **Year** | 1999 (2nd ed. 2018) |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | Refactoring, design improvement, code smells |
| **ISBN** | 978-0-201-48567-7 |

## Summary

Fowler systematized the practice of **refactoring** — changing the internal
structure of code **without changing external behavior**. The core idea:
design degrades under the pressure of change, and it must be **constantly
restored** in small steps.

The book gave the industry:
- a precise definition of refactoring
- a catalog of popular refactorings (Extract Method, Move Method, …)
- the language of "code smells" — signs of design problems
- emphasis on tests as a "safety net" for safe changes

## Key Ideas

### 1) Refactoring ≠ rewriting

**Refactoring** is not "let's rewrite the module from scratch," but a sequence
of small mechanical transformations.

Signs of proper refactoring:
- steps are small and reversible
- tests pass after each step
- system behavior doesn't change

### 2) Tests are a prerequisite

Refactoring is safe only with:
- automated tests (unit/integration)
- or "characterization tests" (see Feathers 2004)

Without tests, refactoring becomes risky rewriting.

### 3) Code Smells as heuristics

Smells are not "errors" but signals that design can be improved:

| Smell | What it often means | Typical refactorings |
|---|---|---|
| Long Method | too much responsibility | Extract Method |
| Large Class | SRP violated | Extract Class |
| Duplicated Code | low modularity | Extract Method / Pull Up Method |
| Feature Envy | method lives in the wrong place | Move Method |
| Divergent Change | "class changes for different reasons" | Split responsibility |
| Shotgun Surgery | "to change, you need to fix 10 places" | Move Method / Introduce Facade |

### 4) Refactoring catalog

The catalog is a "movement dictionary":

- **Extract Method** — the main "atom" of refactoring
- **Inline Method** — the reverse operation
- **Move Method/Field** — "bringing data closer to behavior"
- **Replace Conditional with Polymorphism** — moving away from `switch`
- **Introduce Parameter Object** — fighting long parameter lists
- **Encapsulate Field** — control over invariants

(In the 2nd edition (2018), the catalog was updated for modern languages.)

### 5) Refactoring as part of daily work

Fowler emphasizes: refactoring is not a separate "phase" but constant
activity:
- made a feature → cleaned up a bit nearby
- noticed duplication → removed it
- tests green → safely improved structure

## Historical Context

In the 1990s:
- OOP dominates (C++/Java)
- code bases grow
- xUnit makes automated testing mainstream
- real pain: "code works, but scary to touch"

The book became a bridge between:
- GoF (patterns as static structures)
- TDD/XP (small steps + tests)
- SOLID (principles) → into practical change mechanics

## Impact and Legacy

- "Refactoring" became a standard part of engineering culture
- IDEs learned to automate refactorings
- refactoring became closely tied to CI and code review
- it became clear: design is not what you "designed at the start," but what
  you constantly maintain

## Connections

- **Related:** [Beck — TDD (2002)](beck-2002-tdd.md) ·
  [Feathers — Legacy Code (2004)](feathers-2004-legacy.md) ·
  [GoF — Design Patterns (1994)](gof-1994-design-patterns.md)
- **Author:** [Martin Fowler](../../authors/martin-fowler.md)
- **Related topics:** [OOP & Design](../../topics/design/) ·
  [Process & Testing](../../topics/process/)
