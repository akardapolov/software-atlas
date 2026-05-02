# Reading Path — OOP & Design

Goal: get a "through-line" of object design: from the origins of OOP
to patterns, SOLID, refactoring, and testability practices.

## Steps

### 1) Origins of OOP
- Watch/read: [Simula](../languages/simula/index.md) and [Smalltalk](../languages/smalltalk/index.md) language pages
- Understand: Simula = modeling and classification; Kay = messaging and late binding

### 2) Patterns as a design vocabulary
- Read: [GoF — Design Patterns (1994)](../works/books/gof-1994-design-patterns.md)
- Practice: for 3 patterns (Strategy/Observer/Decorator), write down:
  - the problem
  - forces/trade-offs
  - FP alternative (function/stream/composition)

### 3) Behavioral subtyping (to not break inheritance)
- Read: [Liskov & Wing (1994)](../works/papers/liskov-1994-subtyping.md)
- Outcome: distinguish "extends" from "is a subtype by contract"

### 4) Refactoring as daily practice
- Read: [Fowler — Refactoring (1999)](../works/books/fowler-1999-refactoring.md)
- Practice: take one "smell" in your code and do 3 small refactoring steps

### 5) TDD as a design tool
- Read: [Beck — TDD by Example (2002)](../works/books/beck-2002-tdd.md)
- Practice: do 5 Red/Green/Refactor cycles on a small task

### 6) Legacy code without tests
- Read: [Feathers — Legacy Code (2004)](../works/books/feathers-2004-legacy.md)
- Practice: write 1–2 characterization tests around a "scary" code section

### 7) "Module depth" as a design quality criterion
- Read: [Ousterhout — Philosophy (2018)](../works/books/ousterhout-2018-philosophy.md)
- Practice: find 1 shallow module in the codebase and redesign the interface (not implementation)

### 8) (Optional) modern practical OOP
- Read: [Metz — POODR (2012)](../works/books/metz-2012-poodr.md)

### Supplementary: Polymorphism deep dive
- Read: [OOP Deep Dive: Polymorphism, Subtyping & Mechanics](../topics/design/oop-deep-dive.md)
- Understand: the four forms of polymorphism (Cardelli & Wegner 1985) and how they compose

## Related

- [OOP & Design topic](../topics/design/index.md)
- [Type Systems topic](../topics/types/index.md)
