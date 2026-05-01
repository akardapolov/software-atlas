# Eric Brewer

| | |
|---|---|
| **Born** | 1967 |
| **Fields** | Distributed systems, CAP theorem, cloud computing, database systems |
| **Known for** | CAP theorem, Dynamo, AP systems |

## Biography

Eric Brewer is a computer scientist known for formulating the **CAP theorem**, a fundamental result in distributed systems theory. He is a professor of computer science at the University of California, Berkeley and a principal engineer at Google.

Brewer's work at Amazon led to the creation of **Dynamo**, a highly distributed key-value store that demonstrated the practical application of the CAP theorem's trade-offs.

## Key Contributions

### CAP Theorem (2000)

Brewer's conjecture, later formalized by Gilbert & Lynch (2002), states that a distributed data store can simultaneously provide at most two of three properties:

| Property | Meaning |
|----------|---------|
| **Consistency (C)** | All nodes see the same data at the same time |
| **Availability (A)** | Every request receives a response (success or error) |
| **Partition Tolerance (P)** | System continues operating when network between nodes is lost |

**Key insight:** During a network partition, you must choose between C and A. You cannot have all three simultaneously.

**Implications:**
- **CP systems** (ZooKeeper, etcd) — reject writes during partitions to maintain consistency
- **AP systems** (Dynamo, Cassandra, Riak) — accept writes, may serve stale data
- Real systems operate in one mode normally but can degrade gracefully during partitions

### Dynamo (2007)

Brewer led development of Amazon's Dynamo, which pioneered:
- **Leaderless replication** — clients can write to any node
- **Tunable consistency** — trade-off between consistency and availability via "N, R, W" configuration
- **Gossip protocol** — membership and failure detection
- **Vector clocks** — causal consistency across replicas
- **Merkle trees** — distributed hash ring topology

Dynamo inspired an entire generation of distributed databases including Cassandra, Riak, and influenced the design of many modern cloud-native databases.

## Influence

### Influenced by

- **Jim Gray** — Transactions and ACID properties
- **Leslie Lamport** — Logical clocks, Paxos

### Influenced

- **Dynamo-style databases** — Cassandra, Riak, Voldemort
- **AP systems design** — Eventual consistency patterns
- **Cloud computing practices** — CAP theorem as a design heuristic
- **Modern distributed databases** — ScyllaDB, Dynomite, and others

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2000 | CAP conjecture (keynote) | Talk | [→](../works/papers/brewer-2000-cap.md) |

## Related Pages

- [Distributed Systems topic](../topics/distributed/) — CAP theorem and distributed systems
- [Amazon Dynamo paper](../works/papers/dynamo-2007-paper.md) — Dynamo architecture
- [Jim Gray](jim-gray.md) — Transactions and ACID
- [Leslie Lamport](leslie-lamport.md) — Distributed systems foundations

## Further Reading

- Brewer — *CAP conjecture* (2000)
- Gilbert & Lynch — *Brewer's conjecture* (2002)
- DeCandia et al. — *Dynamo: Amazon's Highly Available Key-value Store* (2007)
- Amazon — *Dynamo: Amazon's Highly Available Key-value Store* (paper, 2007)

## Quotes

> "During a network partition, you can't have all three."
> — Eric Brewer on CAP

> "The key to designing distributed systems is understanding what you can give up and what you must keep."
> — Eric Brewer
