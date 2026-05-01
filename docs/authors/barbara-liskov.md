# Barbara Liskov

| | |
|---|---|
| **Born** | 1939, Los Angeles, California, USA |
| **Fields** | Programming languages, type systems, distributed systems |
| **Known for** | CLU, abstract data types, Liskov Substitution Principle |
| **Turing Award** | 2008 |

## Biography

Barbara Liskov is an American computer scientist whose work shaped both
programming language theory and practical software design. She studied
mathematics at the University of California, Berkeley, and later earned a PhD
from Stanford University in 1968, becoming one of the first women in the
United States to receive a doctorate in computer science.

Liskov spent most of her career at MIT, where she led influential research
in programming languages and distributed systems. Her work combined theoretical
clarity with engineering practicality — a recurring pattern in the history of
great computing ideas.

She received the 2008 Turing Award for contributions to the practical and
theoretical foundations of programming language and system design.

## Key Contributions

### Abstract Data Types (ADTs)

Liskov was one of the major figures behind the concept of the
**abstract data type**: a type defined by the operations it supports,
not by its internal representation.

This is one of the key foundations of software modularity:

- Separate interface from implementation
- Hide representation details
- Permit implementation changes without changing users
- Support reasoning at the right level of abstraction

ADTs are central to modern software design, from OOP classes to modules,
interfaces, and typed FP.

### CLU (1974)

Liskov led the design of **CLU**, a programming language created for
writing clear, modular software. CLU introduced or popularised several
important ideas:

- Data abstraction
- Iterators
- Exception handling
- Parameterised types (generics)
- Clear module boundaries

CLU strongly influenced later languages such as Ada, C++, Java, Python,
and many teaching languages.

### Liskov Substitution Principle (LSP)

The principle informally states:

> if `S` is a subtype of `T`, then objects of type `T` should be
> replaceable with objects of type `S` without breaking program correctness.

This idea was formalised in the 1994 paper with Jeannette Wing,
**"A Behavioral Notion of Subtyping."**

LSP became one of the five principles in **SOLID**, but its roots are
deeper than software slogans: it is really about **behavioural compatibility**.

### Distributed Systems Research

Liskov also made major contributions to distributed computing, including:

- Replication techniques
- Fault tolerance
- Byzantine fault tolerance
- Practical distributed system design

This side of her work is less famous in mainstream software circles,
but extremely important in systems research.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1974 | CLU | Language / research project | — |
| 1977 | "Abstraction Mechanisms in CLU" | Paper | — |
| 1994 | "A Behavioral Notion of Subtyping" (with Jeannette Wing) | Paper | — |
| 1999 | "Practical Byzantine Fault Tolerance" (with Miguel Castro) | Paper | — |

## Influence

### Influenced by

- **John McCarthy** — early language and abstraction research
- **Simula** — object and type-related language evolution

### Influenced

- **Bertrand Meyer** — contract thinking and interface discipline
- **Robert C. Martin** — SOLID includes LSP
- **Modern typed OOP** — subtype correctness, interface design
- **Distributed systems** — fault-tolerant replicated systems

## Why Liskov Matters

Many developers know Liskov only through the letter **L** in SOLID.
That undersells her importance. Her work sits at the intersection of:

- Modularity
- Abstraction
- Types
- Correctness
- Language design

She helped make software design more rigorous without making it less practical.

## Further Reading

- [Wikipedia: Barbara Liskov](https://en.wikipedia.org/wiki/Barbara_Liskov)
- [Turing Award: Barbara Liskov](https://amturing.acm.org/award_winners/liskov_1108679.cfm)
- [CLU](https://en.wikipedia.org/wiki/CLU_(programming_language))

## Related Pages

- [Type Systems](../topics/types/)
- [OOP & Design](../topics/design/)
- [John McCarthy](john-mccarthy.md)
- [Robert C. Martin](robert-c-martin.md)
- [Python](../languages/python/)
