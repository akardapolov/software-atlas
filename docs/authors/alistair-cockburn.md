# Alistair Cockburn

| | |
|---|---|
| **Born** | 1953 |
| **Fields** | Software architecture, agile methods, use cases |
| **Known for** | Hexagonal Architecture (Ports & Adapters), Crystal methods, Agile Manifesto |

## Biography

Alistair Cockburn (pronounced "CO-burn") is an American-based computer
scientist and one of the original 17 signatories of the **Agile Manifesto**.
He is known for two distinct contributions: the **Hexagonal Architecture**
pattern (also called Ports and Adapters) and the **Crystal** family of
agile methodologies.

Cockburn holds a PhD from the University of Oslo and has worked as a
consultant and researcher, spending significant time at Humans and
Technology technical report series.

## Key Contributions

### Hexagonal Architecture — Ports and Adapters (2005)

Cockburn's most enduring contribution. The key insight: an application
should be **equally controllable** by users, programs, automated tests,
and batch scripts — and **equally able** to work with different databases,
message queues, or external services.

The architecture has three layers:

1. **Application core** — business logic, completely independent of
   external concerns. Defines **ports** (interfaces) for all interactions
   with the outside world.

2. **Ports** — abstractions at the boundary. **Driving ports** (left side)
   are how the outside world calls the application (HTTP controller, CLI,
   test harness). **Driven ports** (right side) are how the application
   calls the outside world (database, email, external API).

3. **Adapters** — concrete implementations of ports. A REST adapter
   implements a driving port; a PostgreSQL adapter implements a driven
   port. Adapters are interchangeable.

```
[User] → [REST Adapter] → |Port| → [Application Core] → |Port| → [DB Adapter] → [PostgreSQL]
[Test] → [Test Adapter] → |Port| → [Application Core] → |Port| → [Mock Adapter] → [In-memory]
```

Benefits:

- **Testability** — the core can be tested without any infrastructure
- **Flexibility** — switch databases, UI frameworks, or messaging systems by swapping adapters
- **Independence** — the core doesn't know or care what drives it or what it drives

Hexagonal Architecture directly influenced:

- **Clean Architecture** (Robert C. Martin) — same concentric dependency rule
- **Onion Architecture** (Jeffrey Palermo) — same inside-out layering
- **DDD** (Eric Evans) — hexagonal fits naturally with bounded contexts

### Crystal Methodologies

Cockburn developed the Crystal family of agile methods, which adapt
based on team size and project criticality:

- **Crystal Clear** — for small teams (up to 6)
- **Crystal Yellow** — for medium teams (up to 20)
- **Crystal Orange** — for larger teams (up to 40)

Crystal's distinctive insight: the most important factor in project
success is **communication** — specifically, richness and frequency
of communication between team members. Cockburn defined the "communication
effectiveness" spectrum from documents (least effective) to face-to-face
conversation at a whiteboard (most effective).

### Agile Manifesto (2001)

Cockburn was one of the 17 signatories and contributed his research on
what makes software projects succeed — particularly the importance of
people, communication, and adaptation over process and documentation.

### Use Cases

Cockburn wrote *Writing Effective Use Cases* (2000), which became the
standard reference for use case methodology. His approach emphasised
goal-driven, actor-focused use cases at multiple levels of abstraction.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2000 | *Writing Effective Use Cases* | Book | — |
| 2001 | *Agile Software Development* | Book | — |
| 2005 | "Hexagonal Architecture" | Article | — |

## Influence

### Influenced by

- **Christopher Alexander** — pattern language, quality without a name
- **Kent Beck** — XP, agile values
- **Ivar Jacobson** — use cases methodology

### Influenced

- **Eric Evans** — DDD's application layer fits hexagonal architecture
- **Robert C. Martin** — Clean Architecture extends hexagonal's ideas
- **Jeffrey Palermo** — Onion Architecture is a variant
- **The entire "ports and adapters" community** — hexagonal has become the default clean architecture pattern

## Quotes

> "The purpose of hexagonal architecture is to allow an
> application to equally be driven by users, programs, automated
> test or batch scripts, and to be developed and tested in isolation
> from its eventual run-time devices and databases."

> "The critical thing about a project is people — their
> working together, their ability to communicate."

## Further Reading

- [Wikipedia: Alistair Cockburn](https://en.wikipedia.org/wiki/Alistair_Cockburn)
- [Hexagonal Architecture original article](https://alistair.cockburn.us/hexagonal-architecture/)
- [Alistair Cockburn's website](https://alistair.cockburn.us/)

## Related Pages

- [Architecture & Modularity](../topics/architecture/index.md)
- [Robert C. Martin](robert-c-martin.md)
- [Eric Evans](eric-evans.md)
- [Kent Beck](kent-beck.md)
