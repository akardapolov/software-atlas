# A Relational Model of Data for Large Shared Data Banks

| | |
|---|---|
| **Author** | Edgar F. Codd |
| **Year** | 1970 |
| **Publication** | *Communications of the ACM*, 13(6), 377–387 |
| **Topic(s)** | Databases, relational model, data independence |
| **PDF** | [ACM](https://dl.acm.org/doi/10.1145/362384.362685) |

## Summary

Codd's paper introduced the **relational model** — a way to structure and
manipulate data using mathematical relations (tables) rather than
navigational pointers or hierarchies. It is the foundational paper of all
modern database theory.

Before Codd, databases were navigational: programmers traversed linked
records using pointers (IMS) or followed set membership links (CODASYL).
Changing the physical layout meant rewriting application code. Codd's model
separated the logical view of data from its physical storage, making
applications immune to changes in how data was stored.

The paper is remarkably concise — six pages — yet it defined the
intellectual framework for an entire industry.

## Key Ideas

### 1. Data Independence

Codd's central insight: separate **logical** structure from **physical**
implementation.

- **Physical data independence** — change storage (indexes, partitions,
  file organisation) without touching applications
- **Logical data independence** — change schema (add columns, split tables)
  with minimal application changes

This separation was impossible in navigational databases, where the
application code was inseparable from the physical data layout.

### 2. Relations, Tuples, and Attributes

Codd used precise mathematical terminology:

| Codd's term | Modern term | Meaning |
|-------------|-------------|---------|
| **Relation** | Table | A set of tuples (no duplicates, no order) |
| **Tuple** | Row | An ordered set of attribute values |
| **Attribute** | Column | A named domain of values |
| **Domain** | Data type | The set of permissible values |

The word "relation" comes from set theory: a relation is a subset of the
cartesian product of domains. This mathematical foundation gives the model
rigorous semantics.

### 3. Relational Algebra

Codd defined a formal algebra for manipulating relations:

| Operation | Symbol | What it does |
|-----------|--------|-------------|
| **Selection** | σ | Filter rows by predicate |
| **Projection** | π | Select columns, remove duplicates |
| **Join** | ⋈ | Combine relations on matching attributes |
| **Union** | ∪ | Combine two union-compatible relations |
| **Difference** | − | Rows in one relation but not another |

Any query expressible in this algebra has a well-defined, optimisable
meaning. This enables the query optimiser — perhaps the most important
piece of database engineering — to rewrite queries into equivalent but
faster plans.

### 4. Normalisation

Codd introduced the concept of **normal forms** to eliminate data
redundancy and prevent update anomalies:

- **First Normal Form (1NF)** — no repeating groups; atomic values
- **Second Normal Form (2NF)** — no partial dependencies
- **Third Normal Form (3NF)** — no transitive dependencies

A normalised schema ensures that each fact is stored in exactly one place,
making updates safe and consistent.

## Historical Context

### The Problem

In 1970, the dominant database systems were:

- **IBM IMS** (1968) — hierarchical, record-based, navigational
- **CODASYL** (late 1960s) — network model with set relationships

Both required programmers to write procedural code that navigated physical
pointers. Queries were expressed as traversal paths: "go to record A,
follow pointer to record B, check field X."

This had serious consequences:
- **Tight coupling** — application code depended on physical layout
- **No ad-hoc queries** — every access pattern required pre-written code
- **Difficult evolution** — changing storage meant rewriting applications

### Codd's Insight

Codd, working at IBM Research, had a mathematical background in set theory
and logic. He saw that data could be treated as mathematical relations —
sets of tuples — and manipulated with a formal algebra.

The benefits were immediate:
- **Declarative queries** — ask *what*, not *how*
- **Ad-hoc access** — any query can be formulated after the fact
- **Optimisation** — the system, not the programmer, chooses execution plans
- **Evolution** — physical reorganisation doesn't break applications

### Why It Took Time

IBM was deeply invested in IMS and initially resisted Codd's ideas.
System R, IBM's research prototype (1974–1979), proved the model could be
implemented efficiently. Oracle (founded 1977) and Ingres (Berkeley, 1970s)
commercialised the concept. By the mid-1980s, relational databases were
dominant.

## Impact and Legacy

### This Paper Created an Industry

Virtually every database in production today traces back to Codd 1970:

| Lineage | Descendant |
|---------|-----------|
| System R (IBM) | SQL Server, Db2 |
| Ingres (Berkeley) | PostgreSQL, Informix, Sybase |
| Oracle (RSI) | Oracle Database |
| MySQL | MariaDB, Amazon Aurora |

### SQL

Donald Chamberlin and Raymond Boyce at IBM built **SQL** (Structured Query
Language) as a practical interface to the relational model for System R.
SQL became the ANSI standard in 1986 and remains the dominant database
language.

### The NoSQL Reaction

Even systems that explicitly reject SQL — MongoDB, Cassandra, Redis — are
reacting against Codd's model. The document model is a relaxation of
relational constraints; the key-value model is a degenerate case of a
single-table relation. Codd remains the reference point.

### "Codd's 12 Rules" (1985)

In response to vendors misusing the term "relational," Codd published 12
rules that a true relational database must satisfy. The rules reinforced
data independence, set-oriented operations, and the logical/physical
separation that defined his 1970 paper.

## Connections

- **Builds on:** Set theory, first-order logic, mathematical relations
- **Led to:** Chamberlin & Boyce — SQL (1974) ·
  [Gray — Transactions (1981)](../../authors/jim-gray.md) ·
  Stonebraker — Ingres/PostgreSQL (1970s–1990s) ·
  [Kleppmann — DDIA (2017)](../books/kleppmann-2017-ddia.md)
- **Author:** [Edgar F. Codd](../../authors/edgar-codd.md)
- **Related topic:** [Databases](../../topics/databases/index.md)
