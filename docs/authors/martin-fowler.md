# Martin Fowler

| | |
|---|---|
| **Born** | 1963, Walsall, England |
| **Fields** | Software design, refactoring, enterprise architecture, agile |
| **Known for** | *Refactoring*, *Patterns of EAA*, bliki, ThoughtWorks chief scientist |

## Biography

Martin Fowler is a British-American software developer, author, and
speaker who has served as chief scientist at **ThoughtWorks** since 2000.
He is one of the most influential voices in software design, known for
making complex ideas accessible through clear writing.

Fowler was one of the 17 signatories of the **Agile Manifesto** (2001). His
website (martinfowler.com), written as a "bliki" (blog + wiki), has been
a primary reference for software architecture and design patterns for
over two decades.

## Key Contributions

### Refactoring (1999)

Fowler's book *Refactoring: Improving the Design of Existing Code*
catalogued over 70 specific, named transformations for improving code
structure without changing behaviour:

- **Extract Method** — pull a block of code into a named method
- **Move Method** — move a method to the class where it belongs
- **Replace Conditional with Polymorphism** — use OOP instead of if/else chains
- **Introduce Parameter Object** — group related parameters into an object

The key insight: design improvement is not a one-time activity but a
**continuous discipline** applied in small, safe steps. Each refactoring
is a tiny, behaviour-preserving transformation. Combined with tests, they
allow radical design changes with minimal risk.

The book's impact was enormous: "refactoring" entered the standard
vocabulary of software development, and IDEs added automated refactoring
support (IntelliJ IDEA, Eclipse, VS Code).

### Patterns of Enterprise Application Architecture — PoEAA (2002)

A comprehensive catalogue of architectural patterns for enterprise
applications:

- **Domain Model** vs **Transaction Script** vs **Table Module**
- **Data Mapper** vs **Active Record**
- **Unit of Work**, **Identity Map**, **Repository**
- **Service Layer**, **Remote Facade**

PoEAA gave the industry a shared vocabulary for discussing enterprise
architecture decisions and trade-offs.

### Continuous Integration (2006)

Fowler's article "Continuous Integration" formalised and popularised
the practice (originally from XP) of integrating code changes frequently and
running automated tests on every integration. This article became the
reference definition of CI and influenced all subsequent CI/CD tooling.

### Microservices (2014)

Together with James Lewis, Fowler wrote an influential article
"Microservices" that defined an architectural style. While the ideas
existed before, Fowler's clear articulation of the characteristics
(independently deployable, organised around business capabilities,
decentralised governance) gave the movement a shared definition.

### The Bliki

Fowler's website (martinfowler.com) has published hundreds of articles
defining and clarifying concepts that the industry uses daily:

- Dependency Injection
- Inversion of Control
- Feature Toggles
- Strangler Fig Pattern
- Event Sourcing
- CQRS
- Bounded Context (popularising Evans's DDD concept)

### Other Influential Ideas

- **Code smell** — Fowler popularised the term (from Beck) for code patterns that suggest design problems
- **Technical debt** — helped popularise Cunningham's metaphor
- **Evolutionary architecture** — architecture should evolve incrementally, not be designed up front

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1996 | *Analysis Patterns* | Book | — |
| 1999 | *Refactoring: Improving the Design of Existing Code* | Book | [→](../works/books/fowler-1999-refactoring.md) |
| 2002 | *Patterns of Enterprise Application Architecture* | Book | — |
| 2010 | *Domain-Specific Languages* | Book | — |
| 2018 | *Refactoring* (2nd edition, JavaScript examples) | Book | — |

## Influence

### Influenced by

- **Kent Beck** — XP practices, SUnit, the idea of continuous improvement
- **Ralph Johnson** — GoF co-author, patterns thinking
- **Ward Cunningham** — patterns community, technical debt metaphor
- **Eric Evans** — DDD concepts that Fowler helped popularise

### Influenced

- **The entire industry** — refactoring, CI, code smells, microservices are
  standard vocabulary partly because Fowler wrote clearly about them
- **IDE developers** — automated refactoring tools follow Fowler's catalogue
- **Sam Newman** — *Building Microservices* builds on Fowler's microservices article
- **ThoughtWorks** — Fowler shaped the company's technical culture and Technology Radar

## Quotes

> "Any fool can write code that a computer can understand. Good
> programmers write code that humans can understand."

> "When you feel the need to write a comment, first try to refactor
> code so that any comment becomes superfluous."

> "If it hurts, do it more frequently, and bring the pain forward."
> *(on continuous integration)*

## Further Reading

- [Wikipedia: Martin Fowler](https://en.wikipedia.org/wiki/Martin_Fowler_(software_engineer))
- [martinfowler.com](https://martinfowler.com/)
- [Refactoring.com](https://refactoring.com/)
- [ThoughtWorks Technology Radar](https://www.thoughtworks.com/radar)

## Related Pages

- [Refactoring](../works/books/fowler-1999-refactoring.md)
- [OOP & Design](../topics/design/index.md)
- [Process & Testing](../topics/process/index.md)
- [Architecture](../topics/architecture/index.md)
- [Kent Beck](kent-beck.md)
- [Eric Evans](eric-evans.md)
