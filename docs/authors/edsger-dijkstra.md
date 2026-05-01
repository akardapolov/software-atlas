# Edsger W. Dijkstra

| | |
|---|---|
| **Born** | 1930, Rotterdam, Netherlands |
| **Died** | 2002, Nuenen, Netherlands |
| **Fields** | Structured programming, algorithms, formal methods |
| **Known for** | "Go To" letter, shortest path algorithm, semaphores, structured programming |
| **Turing Award** | 1972 |

## Biography

Edsger Wybe Dijkstra was a Dutch computer scientist and one of the most
influential figures in the history of the discipline. He studied theoretical
physics and mathematics at the University of Leiden and became the
Netherlands' first programmer in 1952 at the Mathematical Center in Amsterdam.

Dijkstra made foundational contributions in almost every area of computer
science: algorithms, programming languages, operating systems, distributed
computing, and formal verification. He received the Turing Award in 1972
"for fundamental contributions to programming as a high, intellectual
challenge."

From 1984 until his retirement in 1999, Dijkstra held the Schlumberger
Centennial Chair in Computer Sciences at the University of Texas at Austin.
He was famous for his handwritten manuscripts (known as "EWDs"), of which
he produced over 1,300.

## Key Contributions

### "Go To Statement Considered Harmful" (1968)

Perhaps the most influential short letter in the history of computer science.
Dijkstra argued that uncontrolled use of `goto` statements makes programs
extremely difficult to reason about and debug. This letter catalysed the
**structured programming** movement, leading to:

- Adoption of `if/else`, `while`, `for` as primary control structures
- Removal or deprecation of `goto` in most modern languages
- The idea that program structure should reflect logical structure

### Dijkstra's Algorithm (1959)

An algorithm for finding the shortest path between nodes in a weighted graph.
Still widely used in routing, navigation, and network protocols over 60 years
later.

### Semaphores (1965)

A synchronisation primitive for controlling access to shared resources in
concurrent programming. Semaphores became the foundation for OS-level
concurrency and influenced all subsequent synchronisation mechanisms
(mutexes, monitors, locks).

### THE Multiprogramming System (1968)

One of the first operating systems designed with a clear layered architecture.
Demonstrated that software systems benefit from hierarchical decomposition —
a principle that influenced all later architecture thinking.

### Structured Programming (with Dahl and Hoare, 1972)

Co-authored the seminal book *Structured Programming* with Ole-Johan Dahl
and Tony Hoare, establishing the intellectual foundation for disciplined
program construction.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1959 | "A Note on Two Problems in Connexion with Graphs" | Paper | — |
| 1965 | "Cooperating Sequential Processes" (semaphores) | Paper | — |
| 1968 | "Go To Statement Considered Harmful" | Paper | [→](../works/papers/dijkstra-1968-goto.md) |
| 1972 | *Structured Programming* (with Dahl, Hoare) | Book | — |
| 1976 | *A Discipline of Programming* | Book | — |

## Influence

### Influenced by

- **Adriaan van Wijngaarden** — mentor at the Mathematical Center in Amsterdam

### Influenced

- **David Parnas** — information hiding extended Dijkstra's modular thinking
- **Tony Hoare** — co-author on structured programming; CSP
- **Niklaus Wirth** — Pascal and Modula languages embodied structured programming
- **Fred Brooks** — referenced Dijkstra's ideas on conceptual integrity
- **All modern programming** — structured control flow is now universal

## Quotes

> "The purpose of abstraction is not to be vague, but to create
> a new semantic level in which one can be absolutely precise."

> "Simplicity is a prerequisite for reliability."

> "Computer science is no more about computers than astronomy
> is about telescopes."

> "If debugging is the process of removing software bugs,
> then programming must be the process of putting them in."

## EWD Manuscripts

Dijkstra's handwritten manuscripts are archived at the University of Texas:
[E.W. Dijkstra Archive](https://www.cs.utexas.edu/~EWD/)

## Further Reading

- [Wikipedia: Edsger W. Dijkstra](https://en.wikipedia.org/wiki/Edsger_W._Dijkstra)
- [Turing Award: Dijkstra](https://amturing.acm.org/award_winners/dijkstra_1053701.cfm)
- [EWD Archive](https://www.cs.utexas.edu/~EWD/)

## Related Pages

- [Go To Considered Harmful](../works/papers/dijkstra-1968-goto.md)
- [Architecture & Modularity](../topics/architecture/)
- [Paradigms](../topics/paradigms/)
- [David Parnas](david-parnas.md)
- [Tony Hoare](tony-hoare.md)
- [Fred Brooks](fred-brooks.md)
