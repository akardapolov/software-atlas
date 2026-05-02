# Eric Evans

| | |
|---|---|
| **Fields** | Software architecture, domain modelling |
| **Known for** | Domain-Driven Design (DDD) |

## Biography

Eric Evans is an American software designer and consultant, best known
as the author of *Domain-Driven Design: Tackling Complexity in the
Heart of Software* (2003) — often called simply "the Blue Book." He founded
Domain Language, a consulting firm that helps teams apply DDD.

Evans has spent his career working with teams on complex business
software, observing what makes some projects succeed at managing
complexity while others drown in it. DDD distils those observations
into a set of principles and patterns.

## Key Contributions

### Domain-Driven Design (2003)

DDD is both a philosophy and a set of concrete patterns for building
software that reflects the complexity of the business domain it serves.

**Core philosophy:**

- The **domain model** is the heart of the software, not the database
  or the UI
- Developers and domain experts must share a **ubiquitous language** —
  the same terms in conversation, code, and documentation
- Software should be structured around the business, not around
  technical concerns

**Strategic patterns** (system-level):

- **Bounded Context** — each model is valid only within a specific
  boundary. Different parts of the business may use the same word
  (e.g., "account") to mean different things. Each bounded context
  has its own model.
- **Context Map** — a diagram showing how bounded contexts relate
  to each other (shared kernel, customer-supplier, anticorruption
  layer, etc.)
- **Subdomain classification** — core domain (competitive advantage),
  supporting subdomain, generic subdomain

**Tactical patterns** (code-level):

- **Entity** — an object with identity that persists across time
- **Value Object** — an object defined by its attributes, with no
  identity (immutable, interchangeable)
- **Aggregate** — a cluster of entities and value objects with a
  single root entity that enforces invariants
- **Repository** — provides access to aggregates as if they were
  an in-memory collection
- **Service** — a stateless operation that doesn't naturally belong
  to an entity or value object
- **Factory** — encapsulates complex object creation
- **Domain Event** — a record of something that happened in the domain

### Impact on Architecture

DDD's bounded contexts provided an intellectual framework for
**microservices**: each microservice maps to a bounded context with
its own model, data store, and team. Sam Newman's *Building
Microservices* (2015) explicitly credits DDD as the foundation for
service boundary decisions.

DDD also influenced:

- **Event Sourcing** — storing domain events rather than current state
- **CQRS** — separating command and query models (combining DDD with
  Meyer's CQS)
- **Hexagonal Architecture** — fitting naturally with DDD's emphasis
  on isolating the domain from infrastructure

### DDD + Functional Programming

Evans's tactical patterns were originally expressed in OOP terms. Scott
Wlaschin's *Domain Modeling Made Functional* (2018) showed that FP
(particularly F#) can express DDD patterns more concisely — using
algebraic data types to make illegal states unrepresentable, eliminating
entire categories of bugs.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2003 | *Domain-Driven Design: Tackling Complexity in the Heart of Software* | Book | [→](../works/books/evans-2003-ddd.md) |
| 2014 | "Domain-Driven Design Reference" (summary booklet) | Reference | — |

## Influence

### Influenced by

- **Martin Fowler** — analysis patterns, enterprise architecture patterns
- **Alistair Cockburn** — hexagonal architecture (ports and adapters)
- **Christopher Alexander** — pattern language approach to design
- **Kent Beck** — iterative development, emergent design
- **Real project experience** — Evans explicitly says DDD came from observing what worked on complex projects

### Influenced

- **Sam Newman** — *Building Microservices* uses bounded contexts as the primary decomposition strategy
- **Scott Wlaschin** — *Domain Modeling Made Functional* translates DDD to typed FP
- **Vaughn Vernon** — *Implementing Domain-Driven Design* (the "Red Book") made DDD more practical
- **CQRS and Event Sourcing communities** — these patterns evolved within the DDD community
- **Microservices movement** — bounded contexts → service boundaries
- **Team Topologies** — stream-aligned teams map to bounded contexts

## Quotes

> "The heart of software is its ability to solve domain-related
> problems for its user."

> "When we set out to write software, we never know enough."

> "If the design of a system does not correspond to the domain,
> the software will be fragile and hard to change."

## Further Reading

- [Wikipedia: Domain-Driven Design](https://en.wikipedia.org/wiki/Domain-driven_design)
- [Domain Language (Evans's company)](https://www.domainlanguage.com/)
- [DDD Reference (free PDF)](https://www.domainlanguage.com/ddd/reference/)

## Related Pages

- [Domain-Driven Design (book)](../works/books/evans-2003-ddd.md)
- [Architecture & Modularity](../topics/architecture/index.md)
- [OOP & Design](../topics/design/index.md)
- [Alistair Cockburn](alistair-cockburn.md)
- [Scott Wlaschin](scott-wlaschin.md)
- [Martin Fowler](martin-fowler.md)
