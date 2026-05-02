# Martin Kleppmann

| | |
|---|---|
| **Fields** | Distributed systems, databases, data engineering |
| **Known for** | *Designing Data-Intensive Applications* (DDIA) |

## Biography

Martin Kleppmann is a German-British computer scientist and researcher.
He was a researcher at the University of Cambridge and has worked as a
software engineer at LinkedIn and Rapportive. He is best known for his
book *Designing Data-Intensive Applications* (2017), which has become
the standard reference for distributed systems practitioners.

## Key Contributions

### Designing Data-Intensive Applications (2017)

DDIA is widely considered the best modern book on distributed systems
for practitioners. It synthesises decades of research into an accessible,
practical guide covering:

**Part I — Foundations of Data Systems:**

- Reliability, scalability, and maintainability
- Data models and query languages (relational, document, graph)
- Storage engines (B-trees, LSM-trees, column-oriented)
- Encoding and evolution (JSON, Protocol Buffers, Avro)

**Part II — Distributed Data:**

- Replication (leader-based, multi-leader, leaderless)
- Partitioning (hash-based, range-based, secondary indexes)
- Transactions (ACID, isolation levels, serializability)
- The trouble with distributed systems (unreliable networks, clocks, nodes)
- Consistency and consensus (linearizability, ordering, Paxos, Raft)

**Part III — Derived Data:**

- Batch processing (MapReduce, dataflow engines)
- Stream processing (event sourcing, change data capture)
- The future of data systems (combining paradigms)

What makes DDIA exceptional:

- It covers both **theory** (Lamport's clocks, CAP, consensus) and **practice** (how real databases and systems work internally)
- It is **fair and balanced** — every technology is evaluated honestly,
  with clear trade-offs
- It connects **foundational papers** to modern implementations
- It is **well-written** — complex topics made accessible without
  oversimplification

### Academic Research

Kleppmann's research focuses on:

- **CRDTs (Conflict-free Replicated Data Types)** — data structures that
  can be updated independently and merged automatically
- **Local-first software** — applications that work offline and sync
  when connectivity returns
- **Automerge** — an open-source CRDT library for building collaborative
  applications

### Conference Talks

Kleppmann is an engaging speaker whose talks complement DDIA:

- "Turning the database inside out" (2014) — rethinking databases as
  streams of events
- "Transactions: myths, surprises, and opportunities" (2015) — what
  isolation levels really mean
- "CRDTs and the quest for distributed consistency" (2018)

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2017 | *Designing Data-Intensive Applications* | Book | [→](../works/books/kleppmann-2017-ddia.md) |
| 2017 | "Conflict-free Replicated Data Types" (with others) | Paper | — |

## Influence

### Influenced by

- **Leslie Lamport** — logical clocks, consensus, formal reasoning about distributed systems
- **Jim Gray** — transactions, ACID, the foundation Kleppmann builds on
- **Pat Helland** — "Life beyond Distributed Transactions" is a key reference in DDIA
- **Decades of distributed systems research** — Kleppmann synthesises, not invents

### Influenced

- **Distributed systems practitioners worldwide** — DDIA is the
  most recommended book for learning distributed systems
- **System design interviews** — DDIA has become a
  standard preparation resource
- **Local-first software movement** — Kleppmann's research is shaping this emerging field
- **CRDTs in practice** — Automerge and related projects

## Quotes

> "Data is at the center of many challenges in system design today."

> "You need to develop a good intuition for what your systems are
> doing under the hood, so that you can reason about their behavior,
> make good design decisions, and track down any problems."

## Further Reading

- [Martin Kleppmann's website](https://martin.kleppmann.com/)
- [DDIA website](https://dataintensive.net/)
- [Automerge](https://automerge.org/)
- [Martin Kleppmann's talks](https://martin.kleppmann.com/talks.html)

## Related Pages

- [DDIA](../works/books/kleppmann-2017-ddia.md)
- [Distributed Systems](../topics/distributed/index.md)
- [Leslie Lamport](leslie-lamport.md)
- [Jim Gray](jim-gray.md)
