# Life beyond Distributed Transactions: an Apostate's Opinion

| | |
|---|---|
| **Author(s)** | Pat Helland |
| **Year** | 2007 |
| **Publication** | CIDR (Conference on Innovative Data Systems Research) |
| **Topic(s)** | Distributed Systems, Transactions, Scalability |
| **PDF** | [Local copy](../../library/open-access-papers/helland-2007-beyond-dt.pdf) · [Original](https://www.cidrdb.org/cidr2007/papers/cidr07p15.pdf) |

## Summary

Pat Helland, an industry veteran with experience at Tandem, Microsoft, and Amazon, wrote a provocative paper asserting that **distributed transactions don't scale**. Attempts to use two-phase commit (2PC) in large-scale systems are doomed to failure.

Instead, Helland proposes an architectural approach based on:
- **Entities** — units of data with unique keys
- **Activities** — business operations that can affect multiple entities
- **Messages** — asynchronous exchange between entities

The key idea: design systems that work **without** real-time coordination between nodes.

## Key Ideas

1. **Entities as Transaction Boundary** — transactions should be limited to a single entity (aggregate). Operations between entities are performed through messages and eventual consistency.

2. **Idempotency is Essential** — since messages may be delivered repeatedly, all operations must be idempotent. Repeated execution should not change the result.

3. **Apologies over Permission** — sometimes it's easier to allow incorrect state and fix it later (compensation) than to block the system for consistency.

## Historical Context

By 2007, Amazon, Google, and other giants were already building systems on thousands of servers. Classic distributed transactions (XA, 2PC) were becoming bottlenecks. Amazon Dynamo (2007) and Google Bigtable (2006) demonstrated alternative approaches.

Helland generalized practical experience and gave it theoretical justification. His paper became a manifesto for a new generation of distributed systems.

## Impact and Legacy

The paper influenced:

- **Event Sourcing and CQRS** — patterns where state is restored from events
- **Saga Pattern** — orchestration of long business processes without distributed transactions
- **DDD Aggregates** — concept of an aggregate as a transactional consistency boundary
- **Microservices** — each service owns its data, synchronization through events

This work remains required reading for distributed system architects.

## Connections

- **Builds on:** [Brewer — CAP Conjecture](../papers/brewer-2000-cap.md)
- **Led to:** [Event Sourcing Pattern](../../topics/architecture/event-sourcing.md)
- **Related author:** [Pat Helland](../../authors/pat-helland.md)
- **Related topic:** [Distributed Systems](../../topics/distributed/index.md)

## Personal Notes

_The title "Apostate's Opinion" reflects how radical this idea was. Helland "renounced" faith in distributed transactions that the industry had practiced for decades. Today his approach has become mainstream._
