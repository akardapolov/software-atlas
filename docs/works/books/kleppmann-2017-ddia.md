# Designing Data-Intensive Applications

| | |
|---|---|
| **Author** | Martin Kleppmann |
| **Year** | 2017 |
| **Publisher** | O'Reilly Media |
| **Topic(s)** | Distributed systems, databases, consistency, streaming |
| **ISBN** | 978-1-449-37332-0 |

## Summary

Kleppmann wrote the definitive guide to principles underlying
modern data systems — databases, caches, message queues, stream
processors, and batch processing frameworks. The book doesn't teach
specific tools; it teaches of **ideas** behind of tools, enabling
readers to evaluate trade-offs and choose the right approach for
their systems.

DDIA is widely regarded as the most important book for software
engineers working with distributed systems since its publication.

## Key Ideas

### 1. Data Models and Query Languages

Different data models make different trade-offs:

| Model | Strengths | Weaknesses | Examples |
|-------|-----------|------------|---------|
| **Relational** | Joins, schema enforcement, mature tooling | Rigid schema, impedance mismatch | PostgreSQL, MySQL |
| **Document** | Flexible schema, locality | Poor joins, denormalisation | MongoDB, CouchDB |
| **Graph** | Relationship-heavy queries | Less mature, complex queries | Neo4j, Dgraph |
| **Column-family** | Analytical queries, compression | Write amplification | Cassandra, HBase |

No model is universally best. The choice depends on the access patterns
of your application.

### 2. Storage and Retrieval

How databases actually store and retrieve data:

**Log-Structured Merge Trees (LSM):**
- Write to in-memory buffer → flush to sorted immutable files
- Optimised for write-heavy workloads
- Used by: LevelDB, RocksDB, Cassandra

**B-Trees:**
- Fixed-size pages, in-place updates
- Optimised for read-heavy workloads
- Used by: PostgreSQL, MySQL (InnoDB), most traditional RDBMS

**Column-Oriented Storage:**
- Store each column separately
- Enables aggressive compression
- Optimised for analytical queries
- Used by: Parquet, ClickHouse, Redshift

### 3. Replication

Copying data across machines for fault tolerance and performance:

| Strategy | Consistency | Availability | Use case |
|----------|------------|--------------|----------|
| **Single-leader** | Strong (reads from leader) | Leader is SPOF | Most OLTP databases |
| **Multi-leader** | Eventual (conflict resolution needed) | High (each DC has a leader) | Multi-datacenter |
| **Leaderless** | Tunable (quorum reads/writes) | High | Dynamo-style (Cassandra, Riak) |

### 4. Partitioning (Sharding)

Splitting data across machines to scale beyond a single node:

| Strategy | How it works | Trade-off |
|----------|-------------|-----------|
| **Key range** | Keys A–M on node 1, N–Z on node 2 | Hot spots if keys are uneven |
| **Hash** | Hash the key, assign to partition | No range queries |
| **Compound** | Hash partition key, range within | Balance of both |

The fundamental challenge: **rebalancing** partitions when nodes are
added or removed, without downtime.

### 5. Transactions

Kleppmann demystified transaction isolation levels:

| Level | Prevents | Allows | Performance |
|-------|----------|--------|-------------|
| **Read Uncommitted** | Nothing | Dirty reads, everything | Fastest |
| **Read Committed** | Dirty reads | Non-repeatable reads | Fast |
| **Snapshot Isolation** | Non-repeatable reads | Write skew | Moderate |
| **Serialisable** | Everything | Nothing anomalous | Slowest |

The key insight: most databases labelled "serialisable" actually
implement snapshot isolation. True serialisability is expensive and
rare.

### 6. Consistency and Consensus

The fundamental problems of distributed systems:

**CAP Theorem** — in the presence of a network partition, you must
choose between consistency and availability. Kleppmann explains why
CAP is often misunderstood and why the actual trade-off is more nuanced.

**Linearisability** — the strongest consistency model: operations appear
to take effect instantaneously at some point between invocation and
response. Expensive and hard to achieve.

**Consensus** — getting all nodes to agree on a value. Solved by
algorithms like Paxos and Raft, but fundamentally limited by
FLP impossibility result (no deterministic consensus in an asynchronous
system with even one faulty process).

### 7. Batch and Stream Processing

The book's final part covers data processing paradigms:

**Batch processing** (MapReduce, Spark):
- Process large datasets in bounded jobs
- High throughput, high latency
- Derived datasets are recomputable

**Stream processing** (Kafka, Flink):
- Process unbounded event streams in real time
- Lower latency, more complex fault tolerance
- Event logs as a source of truth

**The unifying idea:** both batch and stream processing derive new
datasets from existing ones. The input log is immutable; derived
views can be recomputed. This is analogous to functional programming:
immutable inputs, pure transformations, derived outputs.

### 8. The Log as the Unifying Abstraction

Kleppmann's deepest insight: an **append-only log** of immutable events
is the fundamental data structure for distributed systems:

- Database write-ahead logs
- Kafka topics
- Event sourcing
- Change data capture

All of these are variations on the same idea: record what happened
(events), derive current state from event history.

## Historical Context

### The Data Systems Landscape in 2017

By 2017, the explosion of distributed databases, stream processors,
and "NoSQL" systems had left engineers overwhelmed. Each tool had its
own terminology, its own trade-offs, and its own marketing claims.

Kleppmann's contribution was to show that all these systems are built
on **the same fundamental principles** — replication, partitioning,
serialisation, consistency models — and to explain those principles
clearly enough to evaluate any system.

### Intellectual Lineage

| Concept | Source | How Kleppmann builds on it |
|---------|--------|--------------------------|
| Logical clocks | [Lamport, 1978](../papers/lamport-1978-clocks.md) | Ordering events in distributed logs |
| Transactions | [Gray, 1981](../../authors/jim-gray.md) | Isolation levels and their trade-offs |
| CAP theorem | [Brewer, 2000](../papers/brewer-2000-cap.md) | Nuanced explanation beyond the buzzword |
| Beyond DT | [Helland, 2007](../papers/helland-2007-beyond-dt.md) | Designing without distributed transactions |

## Impact and Legacy

### Industry Adoption

DDIA became the standard reference for:

- Designing distributed systems at scale
- System design interviews (widely cited in interview prep)
- Understanding database internals
- Evaluating data processing architectures

### The "Thinking in Logs" Movement

Kleppmann's emphasis on immutable event logs influenced:

- Event sourcing adoption
- Kafka's rise as the central nervous system of data architectures
- Change data capture (CDC) patterns
- The shift from "state-first" to "event-first" thinking

## Connections

- **Builds on:** [Lamport — Logical Clocks (1978)](../papers/lamport-1978-clocks.md) ·
  [Gray — Transactions (1981)](../../authors/jim-gray.md) ·
  [Brewer — CAP (2000)](../papers/brewer-2000-cap.md) ·
  [Helland — Beyond DT (2007)](../papers/helland-2007-beyond-dt.md)
- **Related to:** [Evans — DDD (2003)](evans-2003-ddd.md) (bounded contexts for data ownership) ·
  [Humble & Farley — CD (2010)](humble-2010-cd.md) (deploying data systems)
- **Author:** [Martin Kleppmann](../../authors/martin-kleppmann.md)
- **Related topic:** [Distributed Systems](../../topics/distributed/) ·
  [Architecture & Modularity](../../topics/architecture/)
