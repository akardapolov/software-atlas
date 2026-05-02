# Reading Path — Distributed Systems

Goal: build a "skeleton" of understanding: event order → consistency →
transactions → CAP → practical architectures.

## Steps

### 1) Event order without global time
- Read: [Lamport — Time, Clocks... (1978)](../works/papers/lamport-1978-clocks.md)
- Focus: happened-before, logical clocks, partial order

### 2) CAP as a trade-off formulation
- Read: Brewer CAP (if you have the page: `brewer-2000-cap.md`)
- Focus: what exactly partition means and why "all at once" is impossible

### 3) Life without distributed transactions
- Read: Helland 2007 (if you have the page: `helland-2007-beyond-dt.md`)
- Focus: why DTs are expensive and what replaces them (sagas, async, idempotency)

### 4) Synthesis into a unified picture
- Read: [Kleppmann — DDIA (2017)](../works/books/kleppmann-2017-ddia.md)
- Focus: replication, partitioning, consistency models, logs, stream/batch

## Practice

Create a "decision matrix" for one service:
- which data is critical for linearizability?
- where can eventual consistency work?
- which operations must be idempotent?
- where do we need outbox/CDC/log?

## Related

- [Distributed topic](../topics/distributed/index.md)
- [Concurrency topic](../topics/concurrency/index.md)
