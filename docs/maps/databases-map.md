# Databases Map

Visual timeline and concept evolution of database technology — from Codd's
relational model through storage engines to distributed databases.

---

## The Big Picture

```mermaid
flowchart TD
    Codd["Codd 1970\nRelational Model"]
    Gray["Gray 1981\nTransactions"]
    SQL["SQL Standard\n1986"]
    Rel["Relational Era\n1970s-1990s\nOracle, PostgreSQL, MySQL"]
    NoSQL["NoSQL Movement\n2006-2010\nBigtable, Dynamo, Cassandra, MongoDB"]
    NewSQL["NewSQL\n2010s\nSpanner, CockroachDB"]
    DDIA["Kleppmann 2017\nDDIA"]

    Codd --> SQL
    Gray --> SQL
    SQL --> Rel
    Rel --> NoSQL
    NoSQL --> NewSQL
    Rel --> DDIA
    NoSQL --> DDIA
    NewSQL --> DDIA
```

---

## A Brief History

```mermaid
timeline
    title Database Technology Evolution
    1970 : Codd — Relational Model
    1974 : System R — first SQL implementation
    1979 : Oracle V2 — one of the first commercial RDBMS
    1981 : Gray — Transaction Concept
    1983 : Haerder & Reuter — ACID terminology
    1986 : SQL-86 standard
    1989 : PostgreSQL project begins
    1995 : MySQL released
    2006 : Google Bigtable paper
    2007 : Amazon Dynamo paper
    2008 : Cassandra (Facebook)
    2009 : MongoDB released
    2012 : Google Spanner
    2015 : CockroachDB
    2017 : Kleppmann — DDIA
```

---

## Data Model Decision Tree

```mermaid
flowchart TD
    Start["What is your access pattern?"]

    Start --> Q1{"Complex relationships?"}
    Q1 -->|Yes| Graph["Graph DB\nNeo4j, Dgraph"]
    Q1 -->|No| Q2{"Flexible schema, document-like?"}

    Q2 -->|Yes| Doc["Document DB\nMongoDB, CouchDB"]
    Q2 -->|No| Q3{"Simple key lookups?"}

    Q3 -->|Yes| KV["Key-Value\nRedis, DynamoDB"]
    Q3 -->|No| Q4{"Analytical aggregations?"}

    Q4 -->|Yes| Column["Column Store\nClickHouse, DuckDB"]
    Q4 -->|No| Rel["Relational\nPostgreSQL, MySQL"]
```

---

## Storage Engine Comparison

```mermaid
flowchart TB
    subgraph BTree["B-Tree Engine"]
        BT1["In-place updates"] --> BT2["Fixed-size pages"]
        BT2 --> BT3["Read-optimized\nPostgreSQL, MySQL"]
    end

    subgraph LSM["LSM-Tree Engine"]
        L1["Sequential writes"] --> L2["Immutable SSTables"]
        L2 --> L3["Compaction merges"]
        L3 --> L4["Write-optimized\nRocksDB, Cassandra"]
    end

    subgraph Column["Column-Oriented"]
        C1["Columns stored separately"] --> C2["Aggressive compression"]
        C2 --> C3["Batch-optimized\nClickHouse, DuckDB"]
    end
```

---

## Cross-Track Connections

Databases do not exist in isolation. They connect to every other lineage
in the atlas:

| From                          | To                              | Relationship                                                              |
|-------------------------------|---------------------------------|---------------------------------------------------------------------------|
| Codd (Databases)              | Relational Model (Architecture) | Data model shapes system structure                                        |
| Gray (Databases)              | ACID (Distributed)              | Single-node transactions are the baseline distributed systems depart from |
| Dynamo (Databases)            | Brewer (Distributed)            | Leaderless design is an AP choice under CAP                               |
| SQL (Databases)               | Type Systems                    | Relational algebra shares roots with type theory                          |
| PostgreSQL (Databases)        | Containers                      | Stateful workloads need persistent volumes                                |
| Event Sourcing (Architecture) | Databases                       | The log is a unifying abstraction                                         |

---

## Node Inventory

| Year | Node                       | Type | Lineage                | Atlas role     |
|------|----------------------------|------|------------------------|----------------|
| 1970 | Codd — Relational Model    | [P]  | Databases              | foundation     |
| 1974 | System R                   | [L]  | Databases              | embodiment     |
| 1981 | Gray — Transaction Concept | [P]  | Databases              | foundation     |
| 1983 | Haerder & Reuter — ACID    | [R]  | Databases              | formalization  |
| 1986 | SQL standard               | [R]  | Databases              | formalization  |
| 1989 | PostgreSQL                 | [L]  | Databases              | embodiment     |
| 1995 | MySQL                      | [L]  | Databases              | popularization |
| 2006 | Google Bigtable            | [P]  | Databases              | foundation     |
| 2007 | Amazon Dynamo              | [P]  | Databases              | foundation     |
| 2008 | Cassandra                  | [L]  | Databases              | popularization |
| 2009 | MongoDB                    | [L]  | Databases              | popularization |
| 2012 | Google Spanner             | [P]  | Databases              | synthesis      |
| 2017 | Kleppmann — DDIA           | [B]  | Databases, Distributed | synthesis      |

---

## Related

- [Databases topic](../topics/databases/index.md)
- [Distributed Systems map](distributed-map.md)
- [Architecture map](architecture-map.md)
- [Master Timeline](master-timeline.md)