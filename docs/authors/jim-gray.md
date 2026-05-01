# Jim Gray

| | |
|---|---|
| **Born** | 1944, San Francisco, California, USA |
| **Died** | 2007 (lost at sea near San Francisco) |
| **Fields** | Database systems, transaction processing, distributed systems |
| **Known for** | ACID properties, transaction processing, five-minute rule |
| **Turing Award** | 1998 |

## Biography

James Nicholas Gray was an American computer scientist who made
fundamental contributions to database and transaction processing
technology. He worked at IBM Research, Tandem Computers, Digital
Equipment Corporation, and Microsoft Research.

Gray formalised the concept of **database transactions** and the **ACID
properties** that guarantee their reliability. His work made it possible
to build systems where thousands of concurrent users can safely modify
shared data — the foundation of banking, airline reservations, and all
modern transactional systems.

He received the Turing Award in 1998 "for seminal contributions to
database and transaction processing research and technical leadership
in system implementation."

Gray disappeared at sea on January 28, 2007, while sailing alone near
the Farallon Islands outside San Francisco. Despite an unprecedented
search effort — including crowdsourced satellite image analysis — he
was never found.

## Key Contributions

### ACID Properties

Gray formalised the four properties that define a reliable transaction:

- **Atomicity** — a transaction either completes entirely or has no effect
  (all or nothing)
- **Consistency** — a transaction moves the database from one valid state
  to another
- **Isolation** — concurrent transactions don't interfere with each other
  (each appears to run alone)
- **Durability** — once committed, a transaction's effects survive system
  crashes

These properties, first articulated in Gray's work in the late 1970s and
codified in the 1981 book *The Transaction Concept*, remain the standard
for transactional systems.

### Transaction Processing

Gray's contributions to transaction processing include:

- **Write-Ahead Logging (WAL)** — the technique of writing changes to a
  log before applying them, enabling crash recovery
- **Two-Phase Locking (2PL)** — a protocol guaranteeing serializable
  isolation
- **Two-Phase Commit (2PC)** — a protocol for atomic transactions across
  multiple nodes
- **Lock granularity** — the theory of fine-grained vs coarse-grained
  locking and its performance implications

### The Five-Minute Rule (1987)

A cost-based heuristic for deciding whether data should be kept in memory
or on disk: if a page is accessed more frequently than once every five
minutes, it should be cached in memory. Gray updated this rule periodically
as hardware economics changed (the "five-minute rule revisited").

### Benchmark Methodology

Gray championed rigorous **performance benchmarking** for database systems,
leading to the creation of the TPC (Transaction Processing Performance
Council) benchmarks. His insistence on fair, reproducible benchmarks raised
the standard for the entire industry.

### Data-Intensive Science

In his later career at Microsoft Research, Gray advocated for a "fourth
paradigm" of science — data-intensive scientific discovery — alongside
theory, experiment, and simulation. This vision anticipated the big data
and data science movements.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1976 | "Granularity of Locks and Degrees of Consistency" (with others) | Paper | — |
| 1981 | "The Transaction Concept: Virtues and Limitations" | Paper | — |
| 1987 | "The 5 Minute Rule for Trading Memory for Disk Accesses" (with Putzolu) | Paper | — |
| 1993 | *Transaction Processing: Concepts and Techniques* (with Reuter) | Book | — |

## Influence

### Influenced by

- **Edgar Codd** — relational model gave transactions a formal data model to protect
- **Leslie Lamport** — ordering and consistency in distributed systems

### Influenced

- **Pat Helland** — "Life beyond Distributed Transactions" extends Gray's work to internet-scale systems
- **Martin Kleppmann** — DDIA devotes extensive coverage to transactions, citing Gray throughout
- **All database systems** — every SQL and NoSQL database implements some version of Gray's transaction model
- **Cloud computing** — distributed transactions, consistency models, and the CAP theorem all build on Gray's foundations

## Quotes

> "Transactions are contracts that bind an application to a
> data management system."

> "One should study enduring fundamentals, because fads are
> just fads."

## Further Reading

- [Wikipedia: Jim Gray](https://en.wikipedia.org/wiki/Jim_Gray_(computer_scientist))
- [Turing Award: Gray](https://amturing.acm.org/award_winners/gray_3649936.cfm)
- [Microsoft Research tribute](https://www.microsoft.com/en-us/research/people/gray/)

## Related Pages

- [Distributed Systems](../topics/distributed/)
- [Leslie Lamport](leslie-lamport.md)
- [Martin Kleppmann](martin-kleppmann.md)
