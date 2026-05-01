# Sam Newman

| | |
|---|---|
| **Known for** | Building Microservices, O'Reilly books |

## Biography

Sam Newman is a software architect and author known for popularizing **microservices architecture** through his book *Building Microservices* (2015). He is a member of the ThoughtWorks technical community and has extensive experience in distributed systems and enterprise integration.

Newman's work helped shape the transition from monolithic architectures to microservices during the 2010s cloud era, providing practical guidance for teams navigating this architectural shift.

## Key Contributions

### Building Microservices (2015)

Newman's book codified the microservices pattern into actionable guidance. Key aspects:

| Concept | Description |
|---------|-------------|
| **Small, independently deployable** | Each service owns its database and deployment |
| **Organized around business capabilities** | Services align with bounded contexts |
| **API-first** | Services communicate via HTTP/REST APIs |
| **Technology diversity** | Different services can use different tech stacks |
| **Data ownership per service** | Each service manages its own data model |

**When to use microservices:**

| Factor | Use microservices when... |
|---------|----------------------|
| **Team size** | Large teams (> 20 developers) benefit from service boundaries |
| **Complexity** | System complexity exceeds what a single team can manage |
| **Independent deployment** | Different parts of system need different release schedules |
| **Technology diversity** | Different subsystems require different languages or frameworks |
| **Scalability** | Different components have different scaling needs |

**When NOT to use microservices:**

| Factor | Stay monolithic when... |
|---------|----------------------|
| **Small team** | Overhead of distributed systems isn't justified |
| **Simple domain** | Business logic doesn't have natural service boundaries |
| **Early stage** | Product is evolving rapidly; monolith allows faster iteration |
| **Shared data model** | Tight coupling between related entities isn't problematic |

### Practical Patterns

Newman documented patterns for common microservices challenges:

- **API Gateway** — single entry point for client requests
- **BFF (Backend for Frontend)** — API optimized for specific client needs
- **Strangler Fig** — gradual migration from monolith to microservices
- **Saga pattern** — distributed transactions across services
- **Event-driven architecture** — services communicate via events

## Influence

### Influenced by

- **Martin Fowler** — EAA patterns, Refactoring
- **Eric Evans** — Domain-Driven Design (bounded contexts)

### Influenced

Newman's work influenced:

- **Cloud-native architecture** — AWS Lambda, Azure Functions, GCP Cloud Functions
- **Service mesh** — Istio, Linkerd, Consul Connect
- **API design practices** — REST, GraphQL, gRPC
- **Observability** — Distributed tracing (Jaeger, Zipkin)

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2015 | Building Microservices | Book | [→](../works/books/newman-2015-microservices.md) |

## Related Pages

- [Distributed Systems topic](../topics/distributed/) — CAP, consistency, and distributed patterns
- [Architecture & Modularity topic](../topics/architecture/) — microservices, Strangler Fig
- [Martin Fowler](martin-fowler.md) — patterns and architecture
- [Eric Evans](eric-evans.md) — DDD and bounded contexts

## Further Reading

- Newman — *Building Microservices* (2015)
- Newman — *Monolith to Microservices* (2019)
- Richardson — *Microservices Patterns* (2018)
- Kleppmann — *Designing Data-Intensive Applications* (2017) — Chapter 4 on data partitioning
- Newman — *Building Event-Driven Microservices* (2020)

## Quotes

> "Microservices are not the default approach. They come with costs and benefits."
> — Sam Newman

> "Start small. Scale what needs scaling. Keep teams small and autonomous."
> — Sam Newman on microservices teams

> "The monolith is not automatically bad. It has advantages too."
> — Sam Newman on architectural trade-offs
