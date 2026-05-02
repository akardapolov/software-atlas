# Leslie Lamport

| | |
|---|---|
| **Born** | 1941, New York City, USA |
| **Fields** | Distributed systems, formal verification, concurrency |
| **Known for** | Logical clocks, Paxos, TLA+, LaTeX, bakery algorithm |
| **Turing Award** | 2013 |

## Biography

Leslie Lamport is an American computer scientist whose work has defined
how we think about distributed systems. He has spent his career at
Massachusetts Computer Associates (1970–1977), SRI International
(1977–1985), Digital Equipment Corporation (1985–2001), and Microsoft
Research (2001–present).

Lamport received the Turing Award in 2013 "for fundamental contributions
to the theory and practice of distributed and concurrent systems, notably
the invention of concepts such as causality and logical clocks, safety and
liveness, replicated state machines, and sequential consistency."

He is also the creator of **LaTeX**, the document preparation system used
for virtually all scientific and mathematical publishing.

## Key Contributions

### Logical Clocks and Happened-Before Relation (1978)

Lamport's 1978 paper "Time, Clocks, and the Ordering of Events in a
Distributed System" is one of the most cited papers in computer science.

Its central insight:

> In a distributed system, there is no global clock. The only way to
> order events is by messages between processes.

Lamport defined the **happened-before** relation (→):

- If events a and b occur in the same process, and a comes first, then a → b
- If a is the sending of a message and b is the receipt of that message, then a → b
- The relation is transitive

Events not related by → are **concurrent** — they happen "at the same time"
in the distributed sense.

**Logical clocks** (Lamport clocks) assign timestamps to events such that
if a → b, then C(a) < C(b). This provides a partial ordering sufficient
for many distributed algorithms.

This paper established the entire vocabulary and conceptual framework for
distributed systems theory.

### Paxos Consensus Algorithm (1989 / 1998)

Paxos solves a fundamental problem of distributed consensus: how can
a group of unreliable processes agree on a single value? Lamport described
the algorithm in 1989 using an allegory set on the Greek island of Paxos,
but the paper was not published until 1998 because reviewers found the
allegory confusing.

Paxos (and its variants: Multi-Paxos, Fast Paxos) became the foundation
for:

- **Google Chubby** — distributed lock service
- **Apache ZooKeeper** — coordination service
- **etcd** — key-value store for Kubernetes
- **Raft** (2014) — a more understandable consensus algorithm inspired by Paxos

### TLA+ — Temporal Logic of Actions

A formal specification language for designing and verifying concurrent
and distributed systems. TLA+ allows engineers to:

- Model system behaviour as state machines
- Specify correctness properties (safety and liveness)
- Use a model checker (TLC) to find bugs by exhaustively exploring states

TLA+ is used at Amazon (AWS), Microsoft, and other companies to verify
designs of critical distributed systems before implementation.

### Sequential Consistency (1979)

Lamport defined **sequential consistency** — the strongest practical
consistency model for shared memory multiprocessors: operations appear
to execute in some sequential order consistent with the program order of
each processor. This concept is central to understanding memory models
in modern hardware and programming languages.

### The Bakery Algorithm (1974)

A mutual exclusion algorithm for N processes that uses only shared
registers, without hardware atomic operations. Notable for its simplicity
and for proving that mutual exclusion is possible with minimal hardware
support.

### LaTeX (1984)

Lamport created LaTeX as a set of macros on top of Donald Knuth's TeX
typesetting system, making it accessible for everyday academic use. LaTeX
is now the standard for scientific publications in mathematics, physics,
and computer science.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1974 | "A New Solution of Dijkstra's Concurrent Programming Problem" | Paper | — |
| 1978 | "Time, Clocks, and the Ordering of Events in a Distributed System" | Paper | [→](../works/papers/lamport-1978-clocks.md) |
| 1979 | "How to Make a Multiprocessor Computer That Correctly Executes Multiprocess Programs" | Paper | — |
| 1984 | *LaTeX: A Document Preparation System* | Book | — |
| 1998 | "The Part-Time Parliament" (Paxos) | Paper | — |
| 2001 | "Paxos Made Simple" | Paper | — |
| 2002 | *Specifying Systems: The TLA+ Language and Tools* | Book | — |

## Influence

### Influenced by

- **Edsger Dijkstra** — concurrent programming, formal methods, mathematical rigour
- **Special relativity** — the insight that time is relative inspired logical clocks

### Influenced

- **Jim Gray** — transaction processing builds on Lamport's ordering concepts
- **Martin Kleppmann** — DDIA synthesises the field Lamport founded
- **Diego Ongaro** — Raft consensus algorithm designed to be "understandable Paxos"
- **Amazon/AWS** — uses TLA+ to verify distributed system designs
- **Google** — Chubby, Spanner built on Paxos ideas
- **Every distributed system** — logical clocks and consensus are foundational

## Quotes

> "A distributed system is one in which the failure of a computer
> you didn't even know existed can render your own computer unusable."

> "If you're not writing a program, don't use a programming language."
> *(on why TLA+ uses mathematics, not code)*

> "Writing is nature's way of letting you know how sloppy your
> thinking is."

## Further Reading

- [Wikipedia: Leslie Lamport](https://en.wikipedia.org/wiki/Leslie_Lamport)
- [Turing Award: Lamport](https://amturing.acm.org/award_winners/lamport_1205376.cfm)
- [Lamport's homepage (with all papers)](https://lamport.azurewebsites.net/)
- [TLA+ homepage](https://lamport.azurewebsites.net/tla/tla.html)

## Related Pages

- [Time, Clocks paper](../works/papers/lamport-1978-clocks.md)
- [Distributed Systems](../topics/distributed/index.md)
- [Concurrency](../topics/concurrency/index.md)
- [Jim Gray](jim-gray.md)
- [Martin Kleppmann](martin-kleppmann.md)
- [Edsger Dijkstra](edsger-dijkstra.md)
