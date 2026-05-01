# Boundaries

| | |
|---|---|
| **Speaker** | Gary Bernhardt |
| **Year** | 2012 |
| **Event** | RubyConf (also SCNA) |
| **Topic(s)** | FP, Architecture, Testing, OOP |
| **Video** | [Watch](https://www.destroyallsoftware.com/talks/boundaries) |

## Summary

In this compact but incredibly dense talk, Gary Bernhardt presents an architectural approach that combines the best of functional and object-oriented programming.

Key idea: split the system into a **Functional Core** and an **Imperative Shell**.

- **Functional Core** contains all business logic as pure functions (without side effects). These functions are easy to unit test.
- **Imperative Shell** manages state, I/O, interaction with the external world. It is minimal and tested with integration tests.

This approach solves the classic problem: how to get the benefits of FP (testability, predictability) in languages that are not purely functional?

## Key Ideas

1. **Functional Core / Imperative Shell** — business logic — pure functions working with values. Shell — a thin layer that reads/writes state and calls the core. Core knows nothing about Shell.

2. **Values, not Objects** — values, not objects with state, are used in the core. This eliminates a whole class of bugs and simplifies reasoning.

3. **Composition over Mocking** — since the core is pure functions, mocks aren't needed. Tests become trivial: input → output. Integration tests only check the shell.

4. **Paths vs Destinations** — traditional OOP focuses on "paths" (sequences of method calls). FP focuses on "destinations" (data transformations). Destinations are easier to test and understand.

## Memorable Quotes

> "The Functional Core knows nothing about the outside world. It just transforms values."

> "Imperative shell, functional core. The shell is like a thin layer of butter on the bread of your functional core."

> "Don't mock the core. Don't unit test the shell."

## Connections

- **Speaker:** [Gary Bernhardt](../../authors/gary-bernhardt.md)
- **Related talk:** [Hickey — Simple Made Easy](../talks/hickey-2011-simple-made-easy.md)
- **Related topic:** [Functional Programming](../../topics/paradigms/functional/)
- **Related topic:** [Hexagonal Architecture](../../topics/architecture/hexagonal.md)
- **Related pattern:** [Ports and Adapters](../../topics/architecture/ports-adapters.md)

## Further Reading

- [Destroy All Software Blog](https://www.destroyallsoftware.com/screencasts)
- [Related: Hexagonal Architecture](../../topics/architecture/hexagonal.md)

## Personal Notes

_This 30-minute talk contains more practical ideas than many books. "Functional Core / Imperative Shell" is a pattern I apply in every project. It works in any language: Ruby, Python, TypeScript, Go. Boundaries between pure and impure code make a system radically simpler to understand and test._
