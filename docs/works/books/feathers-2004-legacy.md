# Working Effectively with Legacy Code

| | |
|---|---|
| **Author** | Michael Feathers |
| **Year** | 2004 |
| **Publisher** | Prentice Hall |
| **Topic(s)** | Legacy code, testing, refactoring, seams |
| **ISBN** | 978-0-131-17705-5 |

## Summary

Feathers provided a practical methodology for working with "legacy":
> **Legacy code is code without tests.**

The main problem with legacy code isn't style, but that it:
- isn't protected by tests
- is heavily coupled
- is difficult to change

The book teaches: how to safely **make changes** by first creating a minimal
"safety perimeter" with tests and "breaking dependencies."

## Key Ideas

### 1) Characterization Tests

If system behavior is unclear — first document "as is":

- write tests that verify current behavior
- even if behavior is strange — the test documents the fact
- then you can refactor and change without fear of breaking unnoticed

### 2) Seams

**Seam** — a place where you can change program behavior **without changing
the call site itself**.

Types of seams:
- link seam (linking/substituting implementation)
- object seam (substituting an object)
- preprocessor seam (C/C++)
- polymorphic seam (virtual methods/interfaces)

Seams are needed to inject test doubles and break heavy dependencies
(database, network, time, file system).

### 3) Dependency-breaking techniques

The book provides a set of "surgical" techniques:

- Extract and Override
- Introduce Instance Delegator
- Parameterize Constructor / Method
- Extract Interface
- Wrap Static Method / Global
- Sprout Class / Sprout Method (grow new alongside, don't break old)

### 4) Order of actions

Practical workflow:
1. Identify change (what needs to be changed)
2. Find "test insertion point" (seam)
3. Create characterization tests around needed behavior
4. Break dependencies exactly enough to test
5. Make change in small steps

## Historical Context

In 2004:
- refactoring is already in culture (Fowler 1999)
- TDD is spreading, but huge systems are already written "before tests"
- business demands changes, but engineers are afraid to touch code

Feathers closed the "gap": how to apply TDD/Refactoring to existing
systems that aren't ready for it.

## Impact and Legacy

- the term "seam" became standard
- characterization tests became a common migration tool
- the approach became the foundation for "Strangler Fig" practices and
  incremental rewrites (stabilize first, improve later)

## Connections

- **Builds on:** [Fowler — Refactoring (1999)](fowler-1999-refactoring.md) ·
  [Beck — TDD (2002)](beck-2002-tdd.md)
- **Leads to:** safe migrations, incremental rewrites, Strangler pattern
- **Related topic:** [Process & Testing](../../topics/process/) ·
  [OOP & Design](../../topics/design/)
