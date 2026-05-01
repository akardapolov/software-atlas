# Michael Feathers

| | |
|---|---|
| **Fields** | Legacy code, testing, refactoring |
| **Known for** | *Working Effectively with Legacy Code* |

## Biography

Michael Feathers is a software engineer known for his influential work on
legacy code and testing. His 2004 book *Working Effectively with Legacy Code*
provided a practical methodology for safely changing code that lacks tests.

## Key Contributions

### "Legacy Code" Definition

Feathers provided a concise, practical definition:

> **Legacy code is code without tests.**

This definition shifted focus from age or style to testability — the
real barrier to change.

### Characterization Tests

A key technique from Feathers: when system behavior is unclear, write
tests that document the *current* behavior ("as is"), even if that
behavior is strange. These tests then serve as a safety net for
refactoring.

### Seams

Feathers introduced the concept of **seams** — places where program behavior
can be changed without modifying the call site. Types of seams include:

- **Link seam** — substituting at link time
- **Object seam** — substituting an object
- **Preprocessor seam** — C/C++ conditional compilation
- **Polymorphic seam** — virtual methods/interfaces

Seams enable breaking heavy dependencies (database, network, time,
filesystem) to make code testable.

### Dependency-Breaking Techniques

The book catalogues surgical techniques for making code testable:

- Extract and Override
- Introduce Instance Delegator
- Parameterize Constructor / Method
- Extract Interface
- Wrap Static Method / Global
- Sprout Class / Sprout Method

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2004 | *Working Effectively with Legacy Code* | Book | [→](../works/books/feathers-2004-legacy.md) |

## Influence

### Influenced by

- **Martin Fowler** — Refactoring methodology
- **Kent Beck** — TDD principles

### Influenced

- **Legacy code practices** — systematic approaches to working with untested code
- **Testing practices** — characterization tests as a migration tool
- **Strangler Fig pattern** — incremental rewrites based on stabilizing first

## Quotes

> "Legacy code is code without tests."

> "When you change code, you should have tests in place to make sure
> you don't break anything."

> "The goal is to get the code under test, not to rewrite it."

## Further Reading

- [Working Effectively with Legacy Code](https://www.oreilly.com/library/view/working-effectively-with/0131177052/)

## Related Pages

- [Working Effectively with Legacy Code](../works/books/feathers-2004-legacy.md)
- [Refactoring](../works/books/fowler-1999-refactoring.md)
- [TDD](../works/books/beck-2002-tdd.md)
- [Testing](../../topics/process/)
