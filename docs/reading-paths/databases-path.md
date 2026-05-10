# Reading Path — Databases

Goal: understand how databases store, organise, and retrieve data — from
foundational theory to modern distributed systems.

## Steps

### 1) The relational foundation

- Read: [Codd — Relational Model (1970)](../works/papers/codd-1970-relational.md)
- Focus: relations, tuples, attributes, data independence, why relational won
- Exercise: take a denormalised dataset and normalise it to 3NF. What
  anomalies does normalisation prevent? (e.g., update, insertion, deletion
  anomalies)

### 2) Transactions and reliability

- Read: [Gray — "The Transaction Concept" (1981)](../works/papers/gray-1981-transaction.md)
- Focus: ACID properties, write-ahead logging, why transactions matter
- Exercise: list three real-world scenarios where atomicity prevents
  corruption (bank transfer, inventory reservation, etc.)

### 3) Storage engines

- Read: [Kleppmann — DDIA, Chapters 3–4 (2017)](../works/books/kleppmann-2017-ddia.md)
- Focus: B-Trees vs LSM-Trees, indexing, query execution
- Exercise: for a workload with 90% writes and 10% range reads, argue for
  B-Tree or LSM-Tree. What changes if the ratio flips?

### 4) The NoSQL shift

- Read: [Brewer — CAP Conjecture (2000)](../works/papers/brewer-2000-cap.md)
- Focus: the fundamental trade-off between consistency, availability, and
  partition tolerance — and what that means for database design choices
- Read: Dynamo paper (Amazon, 2007) — available in the DDIA references
- Focus: leaderless replication, eventual consistency, quorum reads/writes

### 5) Synthesis

- Read: [Kleppmann — DDIA, full book (2017)](../works/books/kleppmann-2017-ddia.md)
- Focus: how data models, storage engines, replication, and consistency fit
  together into a unified picture

## Practice

Design a schema for a real application (e.g., a blogging platform or
inventory system) using **two different data models** (relational and one
NoSQL). Compare:

- Query patterns supported by each
- Consistency guarantees
- Scaling approach
- Schema evolution strategy

## Related

- [Databases topic](../topics/databases/index.md)
- [Distributed Systems topic](../topics/distributed/index.md)
- [Architecture topic](../topics/architecture/index.md)