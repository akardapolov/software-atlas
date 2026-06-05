# Concepts

Fundamental ideas and abstractions that shape how we think about software.

Concepts are the **building blocks** of software engineering — the mental models, paradigms, and formal systems that precede any specific architecture, design pattern, or tool. They answer the question: *how do we think about computation?*

---

## What belongs here

A concept belongs in this section when it:

- Describes a **way of thinking** about programs (paradigms)
- Provides a **formal model** of computation or concurrency
- Defines how we **reason** about code correctness (type systems)
- Offers a **compositional approach** to building software (functional programming)

Concepts are **timeless** — they outlive specific languages, frameworks, and tools.

---

## Contents

| # | Topic | Key ideas | What it answers |
|---|-------|-----------|-----------------|
| 1 | [**Paradigms**](paradigms/index.md) | Imperative↔Declarative, Procedural/OOP/FP/Logic, Sequential/Concurrent | What is a program? |
| 2 | [**Concurrency**](concurrency/index.md) | Threads, CSP, Actors, async/await, scheduling | How do multiple activities coexist? |
| 3 | [**Type Systems**](types/index.md) | Static/dynamic, nominal/structural, ADTs, generics | How do we classify and reason about values? |
| 4 | [**Functional Programming**](functional/index.md) | Purity, immutability, composition, monads | How do we build programs from functions? |

---

## How concepts connect

These four topics are deeply intertwined:

- **Paradigms** provide the *organizational frame* — they define what a program is made of (objects, functions, facts, procedures)
- **Concurrency** adds the *temporal dimension* — how multiple activities interleave or run simultaneously
- **Type systems** provide the *reasoning apparatus* — how we prove properties about programs before they run
- **Functional programming** is both a paradigm and a set of techniques that influence type systems, concurrency models, and architecture

The boundaries are porous. CSP (concurrency) is also an execution model within paradigms. ADTs (types) are central to functional programming. Actor model (concurrency) was born from OOP thinking.

---

## Relationship to other sections

| This section | Connected to | How |
|--------------|--------------|-----|
| Paradigms | [Architecture](../architecture/index.md) | Conway's Law, team structure |
| Concurrency | [Distributed Systems](../distributed/index.md) | Consensus, CAP, consistency |
| Types | [Design](../design/index.md) | SOLID, LSP, contracts |
| Functional Programming | [Architecture](../architecture/index.md) | FC/IS pattern, immutability |

---

## Further Reading

- Van Roy & Haridi — *Concepts, Techniques, and Models of Computer Programming* (2004)
- Pierce — *Types and Programming Languages* (2002)
- Hoare — *Communicating Sequential Processes* (1985)
