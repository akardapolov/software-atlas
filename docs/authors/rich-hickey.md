# Rich Hickey

| | |
|---|---|
| **Born** | 1961 |
| **Fields** | Programming languages, functional programming, software design |
| **Known for** | Clojure, "Simple Made Easy", Datomic, values-oriented programming |

## Biography

Rich Hickey is an American software developer and the creator of
**Clojure** (2007), a modern Lisp that runs on the JVM. He spent two
years designing Clojure without income before releasing it, drawing on
decades of experience with C++, Java, and Common Lisp.

Hickey's talks — particularly "Simple Made Easy" (2011) — have
influenced software design thinking far beyond the Clojure community.
His emphasis on distinguishing **simplicity** from **easiness**, and
**values** from **state**, has become part of the standard design vocabulary.

## Key Contributions

### Clojure (2007)

Clojure is a functional Lisp designed for practical, concurrent
programming on the JVM. Key design decisions:

- **Immutable data structures by default** — Clojure's core data
  structures (lists, vectors, maps, sets) are persistent and immutable.
  "Mutation" returns a new structure that shares most of its memory with
  the old one (structural sharing).
- **Controlled mutation** — when you need state, Clojure provides
  four reference types, each with different semantics:
    - **Atoms** — uncoordinated, synchronous updates (compare-and-swap)
    - **Refs** — coordinated, synchronous updates (Software Transactional Memory)
    - **Agents** — uncoordinated, asynchronous updates
    - **Vars** — thread-local bindings
- **Homoiconicity** — code is data (Lisp heritage). Clojure programs
  are Clojure data structures, enabling powerful macros.
- **JVM interop** — seamless access to the Java ecosystem. Clojure can
  call any Java library, and Java can call Clojure.
- **REPL-driven development** — interactive, exploratory programming
  with a live REPL connected to the running system.
- **Lazy sequences** — potentially infinite sequences evaluated on demand.
- **Spec** — a library for describing the shape of data and functions,
  used for validation, generative testing, and documentation.

### "Simple Made Easy" (2011)

Hickey's most influential talk, delivered at Strange Loop. The central
argument:

**Simple ≠ Easy**

- **Simple** means "not complected" — not intertwined with other things.
  Simplicity is an objective property of design.
- **Easy** means "close at hand" — familiar, convenient, low effort.
  Easiness is subjective and relative to the person.

We often choose **easy** tools (the ones we already know,
the ones with less ceremony) when we should be choosing **simple** tools (the ones that
don't intertwine unrelated concerns).

Examples of **complecting** (braiding together things that should be separate):

| Complected | Simple alternative |
|---|---|
| State (variables) | Values (immutable data) |
| Objects (data + methods) | Data + functions (separate) |
| ORM (objects + database) | Data transformation |
| Inheritance (type + reuse) | Composition |
| Locks (concurrency + coordination) | Queues, actors |

Hickey argues that complecting leads to software that is hard to
understand, change, and debug — regardless of how easy the
tools feel initially.

### Values-Oriented Programming

Across multiple talks, Hickey developed a philosophy of programming
centred on **values**:

- Values are immutable. Once created, they never change.
- Values can be freely shared (no need for defensive copying).
- Time is a sequence of values, not a mutable entity.
- Identity is a logical entity that has different values over time
  (like a person who ages).

This philosophy draws on Alfred North Whitehead's process philosophy
and stands in contrast to the "place-oriented programming" of
traditional OOP (where objects are mutable places that change over time).

### Datomic (2012)

A database designed around Hickey's values philosophy:

- The database accumulates **facts** (datoms) that are never overwritten
- Queries run against an immutable **snapshot** of the database at a point in time
- The database remembers everything (no destructive updates)
- Query processing happens in the application, not in the database server

Datomic embodies the idea that a database should be a growing collection
of facts, not a mutable set of current states.

### Other Talks

Hickey's talks form a coherent body of design thinking:

- **"Are We There Yet?"** (2009) — time, state, identity, and values
- **"Hammer Driven Development"** (2010) — think deeply before coding
- **"The Value of Values"** (2012) — why immutability matters
- **"Design, Composition, and Performance"** (2013) — design as composition of simple parts
- **"Spec-ulation"** (2016) — semantic versioning and breaking changes
- **"Effective Programs"** (2017) — simplicity in practice

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2007 | Clojure language release | Language | [→](../languages/clojure/) |
| 2009 | "Are We There Yet?" (JVM Language Summit) | Talk | — |
| 2011 | "Simple Made Easy" (Strange Loop) | Talk | [→](../works/talks/hickey-2011-simple-made-easy.md) |
| 2012 | Datomic database | Software | — |

## Influence

### Influenced by

- **John McCarthy** — Lisp heritage (homoiconicity, dynamic typing, REPL)
- **John Hughes** — "Why FP Matters" (modularity through composition)
- **Joe Armstrong** — Erlang's concurrency model, process isolation
- **Haskell** — immutability, lazy sequences, persistent data structures
- **Alfred North Whitehead** — process philosophy (time as a series of values)

### Influenced

- **Clojure community** — a vibrant community that values simplicity and data-oriented design
- **Broader FP adoption** — "Simple Made Easy" convinced many non-FP developers to reconsider their defaults
- **Data-oriented design** — Hickey's emphasis on plain data over objects influenced thinking in many languages
- **Gary Bernhardt** — "Boundaries" talk extends simple/easy thinking to architecture
- **Immutability trend** — JavaScript (Immutable.js, Redux), Kotlin (data classes), React (functional components)

## Quotes

> "Simplicity is a prerequisite for reliability."
> *(echoing Dijkstra, which Hickey quotes approvingly)*

> "Programmers know the benefits of everything and the
> tradeoffs of nothing."

> "Simple is often erroneously mistaken for easy. 'Easy' means
> 'near.' Simple is the opposite of complex."

> "State. You're doing it wrong."

> "Information is simple. Don't ruin it."

> "A change to a value does not happen in place. A new value is
> a function of the old."

## Further Reading

- [Wikipedia: Rich Hickey](https://en.wikipedia.org/wiki/Rich_Hickey)
- [Clojure.org](https://clojure.org/)
- [Simple Made Easy (video)](https://www.infoq.com/presentations/Simple-Made-Easy/)
- [All Hickey talks list](https://github.com/tallesl/Rich-Hickey-fanclub)

## Related Pages

- [Clojure](../languages/clojure/)
- [Simple Made Easy](../works/talks/hickey-2011-simple-made-easy.md)
- [Functional Programming](../topics/functional/)
- [OOP & Design](../topics/design/)
- [John McCarthy](john-mccarthy.md)
- [John Hughes](john-hughes.md)
- [Gary Bernhardt](gary-bernhardt.md)
