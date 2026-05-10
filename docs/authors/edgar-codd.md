# Edgar F. Codd

| | |
|---|---|
| **Born** | 1923, Fortuneswell, Dorset, England |
| **Died** | 2003, Williams Island, Florida, USA |
| **Fields** | Database systems, relational theory |
| **Known for** | Relational model, normal forms, data independence |
| **Turing Award** | 1981 |

## Biography

Edgar Frank Codd was a British computer scientist who spent most of his
career at IBM Research. Before computing he served as a pilot in the Royal
Air Force during World War II and earned degrees in mathematics and
chemistry at Oxford.

At IBM in the early 1970s, Codd became frustrated with the then-dominant
navigational databases (IMS, CODASYL), which required programmers to
manually traverse pointers and records. He proposed a radical alternative:
treat data as mathematical relations (tables), manipulate it with a formal
algebra, and let the system decide how to physically store and retrieve it.

His 1970 paper, *A Relational Model of Data for Large Shared Data Banks*,
laid the foundation for virtually every database system in use today — from
PostgreSQL and MySQL to SQL Server and Oracle.

## Key Contributions

### The Relational Model (1970)

Codd's insight was to separate the **logical** view of data (what tables,
rows, and columns mean) from its **physical** representation (how it is
stored on disk). This separation is called **data independence**.

The model is built on:

- **Relations** (tables) — unordered sets of tuples (rows)
- **Attributes** (columns) — named domains of values
- **Relational algebra** — formal operations: select, project, join, union
- **Declarative queries** — ask *what*, not *how*

Before Codd, database queries were procedural: programmers specified
navigation paths through linked records. After Codd, queries became
declarative: describe the desired result and let the query optimizer find
the most efficient execution plan.

### Normal Forms

Codd defined a series of **normal forms** that eliminate data redundancy
and prevent update anomalies:

| Normal Form | Eliminates | Means |
|-------------|-----------|-------|
| **1NF** | Repeating groups | Atomic values in cells |
| **2NF** | Partial dependencies | Non-key attributes depend on full key |
| **3NF** | Transitive dependencies | Non-key attributes depend only on key |
| **BCNF** | All non-trivial dependencies | Every determinant is a candidate key |

Normalisation trades query simplicity for data integrity. A fully
normalised schema avoids update anomalies but may require more joins.

### Data Independence

Codd distinguished two levels:

- **Physical independence** — change storage (add an index, partition a
table) without changing applications
- **Logical independence** — change schema (add a column, split a table)
without rewriting all queries

This separation is what makes relational databases maintainable over
decades. It is also what Codd argued navigational systems could never
achieve.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1970 | "A Relational Model of Data for Large Shared Data Banks" | Paper | [→](../works/papers/codd-1970-relational.md) |
| 1971 | "Further Normalization of the Data Base Relational Model" | Paper | — |
| 1972 | "Relational Completeness of Data Base Sublanguages" | Paper | — |
| 1990 | *The Relational Model for Database Management: Version 2* | Book | — |

## Influence

### Influenced by

- **Mathematical logic and set theory** — relations as sets of tuples
- **Ted Codd's own work on cellular automata** — formal systems applied to computation

### Influenced

- **Jim Gray** — transactions protecting relational data
- **Michael Stonebraker** — Ingres and PostgreSQL implementations
- **Donald Chamberlin and Raymond Boyce** — SQL language design (System R at IBM)
- **Every RDBMS** — Oracle, SQL Server, PostgreSQL, MySQL, SQLite
- **NoSQL movement** — even reactions against SQL cite Codd as the origin

## Quotes

> "Future users of large data banks must be protected from having to know
> how the data is organized in the machine."

> "The ability to change the physical representation without affecting
> the application programs is a very important feature of the relational
> model."

## Further Reading

- [Wikipedia: Edgar F. Codd](https://en.wikipedia.org/wiki/Edgar_F._Codd)
- [Turing Award: Codd](https://amturing.acm.org/award_winners/codd_1000892.cfm)
- [CACM: A Relational Model of Data (1970)](https://dl.acm.org/doi/10.1145/362384.362685)

## Related Pages

- [Databases](../topics/databases/index.md)
- [Jim Gray](jim-gray.md)
- [Michael Stonebraker](michael-stonebraker.md)
