# On the Criteria To Be Used in Decomposing Systems into Modules

| | |
|---|---|
| **Author(s)** | David Parnas |
| **Year** | 1972 |
| **Publication** | Communications of the ACM, Vol. 15, No. 12 |
| **Topic(s)** | Modularity, Information Hiding, Software Architecture |
| **PDF** | [Local copy](../../library/open-access-papers/parnas-1972-modules.pdf) · [Original](https://dl.acm.org/doi/10.1145/361598.361623) |

## Summary

This paper is one of the most influential works in the history of software engineering. Parnas demonstrates that the traditional approach to decomposing systems into modules — based on flowcharts and execution sequence — is not optimal.

Instead, he proposes the principle of **information hiding**: each module should hide one "design decision" from the other modules. This decision can relate to an algorithm, data structure, file format, or any other aspect that might change.

Parnas uses the example of the KWIC (Key Word In Context) system to demonstrate two approaches to decomposition and shows why modularity based on information hiding leads to more flexible and maintainable systems.

## Key Ideas

1. **Information Hiding** — a module should hide a design decision, not just group related functions. The module's interface should reveal as little as possible about its internal implementation.

2. **Changeability as Design Goal** — the main criterion for good decomposition is how easily the system adapts to changes. If a change requires modifications across many modules, the decomposition is flawed.

3. **Modules as Responsibilities** — a module is not just a subroutine or a file, but a unit of responsibility. One module is responsible for one decision that might need changes.

## Historical Context

By 1972, programming was experiencing a "software crisis." Projects were becoming increasingly complex, while methods for managing complexity remained primitive. Dijkstra's structured programming approach dominated, but it focused on control flow rather than data and module organization.

Parnas worked at Carnegie Mellon University and observed how large projects failed due to poor modularity. His paper was an answer to the question: "How do we correctly break a system into parts?"

## Impact and Legacy

This paper laid the theoretical foundation for:

- **Object-Oriented Programming** — encapsulation in OOP directly inherits the idea of information hiding
- **Microservice Architecture** — each service hides its internal state and implementation
- **API Design** — principle of minimal interface
- **SOLID Principles** — especially Single Responsibility and Interface Segregation

Parnas's work is cited in nearly every serious work on software architecture.

## Connections

- **Builds on:** [Dijkstra — Structured Programming](../papers/dijkstra-1968-goto.md)
- **Led to:** [Brooks — The Mythical Man-Month](../books/brooks-1975-mmm.md)
- **Related author:** [David Parnas](../../authors/david-parnas.md)
- **Related topic:** [Architecture](../../topics/architecture/)

## Personal Notes

_It's remarkable how ideas from 1972 remain relevant today. When we discuss bounded contexts in DDD or API boundaries in microservices — we're speaking Parnas's language. The paper is short, but every paragraph contains a profound insight._
