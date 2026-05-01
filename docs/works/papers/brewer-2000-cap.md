# Towards Robust Distributed Systems (CAP Conjecture)

| | |
|---|---|
| **Author(s)** | Eric Brewer |
| **Year** | 2000 |
| **Publication** | ACM Symposium on Principles of Distributed Computing (PODC), Keynote |
| **Topic(s)** | Distributed Systems, Consistency, Availability |
| **PDF** | [Local copy](../../library/open-access-papers/brewer-2000-cap.pdf) · [Original](https://dl.acm.org/doi/10.1145/343477.343502) |

## Summary

In his famous keynote at PODC 2000, Eric Brewer formulated the **CAP theorem** (then a conjecture): a distributed system cannot simultaneously guarantee more than two of three properties:

- **Consistency (C)** — all nodes see the same data at the same time
- **Availability (A)** — every request receives a response (success or error)
- **Partition Tolerance (P)** — the system continues to operate when communication between nodes is lost

This statement was formally proven by Gilbert and Lynch in 2002.

The CAP theorem became a fundamental benchmark for designing distributed systems, helping engineers understand inevitable trade-offs.

## Key Ideas

1. **Impossibility Result** — it's impossible to build a system that remains both available and consistent during a network partition. You must choose.

2. **CP vs AP Systems** — systems divide into CP (sacrificing availability for consistency, e.g., ZooKeeper) and AP (sacrificing consistency for availability, e.g., Cassandra).

3. **Partition is Inevitable** — in real-world networks, partitions are inevitable. Therefore, in practice the choice reduces to C vs A, not "have it all."

## Historical Context

By 2000, internet companies (Amazon, eBay, Google) encountered scaling problems. Traditional ACID databases couldn't handle the load. New approaches were needed.

Brewer worked on the Inktomi system (web search engine) and practically faced the need to choose between consistency and availability. His keynote formalized the intuition that practitioners already had.

## Impact and Legacy

The CAP theorem had a huge impact on:

- **NoSQL movement** — Cassandra, MongoDB, DynamoDB explicitly position themselves as AP or CP systems
- **Eventual Consistency** — acceptance of "eventually consistent" systems as legitimate architecture
- **Microservices** — understanding trade-offs when distributing data across services
- **Cloud Computing** — designing fault-tolerant systems

Later Brewer refined his theorem in the paper "CAP Twelve Years Later" (2012), emphasizing that the choice between C and A is not binary and can vary within a single system.

## Connections

- **Led to:** [Helland — Life beyond Distributed Transactions](../papers/helland-2007-beyond-dt.md)
- **Related author:** [Eric Brewer](../../authors/eric-brewer.md)
- **Related topic:** [Distributed Systems](../../topics/distributed-systems/)

## Personal Notes

_CAP is often misunderstood. Many think you need to choose 2 of 3 properties "forever." In reality, the choice occurs at the moment of partition — and the system can behave differently for different operations. Brewer later regretted that the formulation turned out to be too simplified._
