# Michael Stonebraker

| | |
|---|---|
| **Born** | 1943, Newburyport, Massachusetts, USA |
| **Fields** | Database systems, open-source databases, data warehousing |
| **Known for** | Ingres, PostgreSQL, Vertica, VoltDB, "one size does not fit all" |
| **Turing Award** | 2014 |

## Biography

Michael Stonebraker is an American computer scientist and entrepreneur who
has founded or co-founded nine database startups. He spent his academic
career at the University of California, Berkeley, and later at MIT.

Stonebraker's career is defined by a single conviction: **one size does
not fit all**. Where Codd proved the relational model was a powerful
general-purpose abstraction, Stonebraker showed that specialised workloads
need specialised engines — and built many of them.

He received the Turing Award in 2014 "for fundamental contributions to
the concepts and practices underlying modern database systems."

## Key Contributions

### Ingres (1970s)

Stonebraker's first major project, built at UC Berkeley. Ingres was an
early relational database prototype that proved the relational model could
be implemented efficiently. Key innovations:

- **Query modification** — a technique for handling views and integrity constraints
- **Multi-version concurrency control** — readers don't block writers
- **Extensible type system** — user-defined types and operators

Ingres directly influenced:
- **PostgreSQL** — Ingres codebase evolved into Postgres, then PostgreSQL
- **Informix** — commercial descendant
- **Sybase** — commercial descendant (later SQL Server)

### PostgreSQL (1986–present)

Stonebraker led the **Postgres** project at Berkeley, designed to address
limitations in Ingres:

- **Object-relational features** — complex types, inheritance, user-defined functions
- **Extensibility** — custom types, operators, index methods, languages
- **Rule system** — triggers and active database capabilities

PostgreSQL's extensibility model is unique among major databases. It powers
everything from geospatial systems (PostGIS) to vector databases (pgvector)
through custom extensions — a direct descendant of Stonebraker's design.

### Commercial Database Startups

| Company | Product | Focus | Year |
|---------|---------|-------|------|
| Illustra | Object-relational DB | Complex data types | 1992 |
| Cohera | Cache-conscious DB | Main-memory optimisation | 1997 |
| StreamBase | Stream processing | Real-time analytics | 2003 |
| Vertica | Column store | Analytical workloads | 2005 |
| VoltDB | In-memory NewSQL | High-throughput OLTP | 2009 |
| Paradigm4 | SciDB | Scientific data | 2010 |
| Tamr | Data curation | Enterprise data unification | 2013 |

### "One Size Does Not Fit All" (2005)

Stonebraker's influential paper argued that trying to build a single
database for all workloads is a mistake. Different workloads need
different architectures:

| Workload | Optimal engine | Why |
|----------|---------------|-----|
| OLTP | In-memory, row-oriented | Many small writes |
| OLAP | Column-oriented | Large analytical scans |
| Streaming | Stream processor | Continuous ingestion |
| Scientific | Array database | Multi-dimensional data |
| Text search | Inverted index | Full-text retrieval |

This philosophy directly enabled the explosion of specialised databases
in the 2010s: time-series, graph, vector, document, and key-value stores.

### NewSQL (2010s)

Stonebraker's VoltDB embodied the NewSQL idea: give developers the scale
of NoSQL with the guarantees of SQL. NewSQL systems like CockroachDB and
TiDB later popularised the concept of horizontally scalable, strongly
consistent relational databases.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1976 | "The Design and Implementation of INGRES" | Paper | — |
| 1990 | "The Implementation of POSTGRES" | Paper | — |
| 2005 | "One Size Fits All: An Idea Whose Time Has Come and Gone" | Paper | — |
| 2007 | "The End of an Architectural Era" | Paper | — |
| 2010 | "SQL Databases v. NoSQL Databases" | Paper | — |

## Influence

### Influenced by

- **Edgar Codd** — relational model provided the theoretical foundation
- **Jim Gray** — transaction processing shaped Stonebraker's reliability thinking

### Influenced

- **PostgreSQL community** — extensibility model, open-source culture
- **NewSQL movement** — VoltDB, CockroachDB, TiDB, YugabyteDB
- **Specialised databases** — column stores, time-series DBs, vector DBs
- **Database researchers worldwide** — Berkeley became the global centre of database research

## Quotes

> "One size does not fit all."

> "The traditional RDBMS wisdom is all wrong."

> "If you have a hammer, everything looks like a nail. If you have an
> RDBMS, every application looks like a business data processing problem."

## Further Reading

- [Wikipedia: Michael Stonebraker](https://en.wikipedia.org/wiki/Michael_Stonebraker)
- [Turing Award: Stonebraker](https://amturing.acm.org/award_winners/stonebraker_1172121.cfm)
- [MIT CSAIL: Stonebraker](https://www.csail.mit.edu/research/database-group)

## Related Pages

- [Databases](../topics/databases/index.md)
- [Edgar Codd](edgar-codd.md)
- [Jim Gray](jim-gray.md)
