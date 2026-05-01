# Microservices

**Category:** Structural  
**Source:** Martin Fowler & James Lewis (2014); Sam Newman — *Building Microservices* (2015)

> Small, independently deployable services, each owning its data, communicating over the network.

Martin Fowler and James Lewis coined and defined the term in a widely read article (2014). Sam Newman codified the operational practices in *Building Microservices* (2015). Together they established the microservices pattern: small, independently deployable services, each owning its data, communicating over the network.

Microservices are essentially **bounded contexts deployed independently**:

| Aspect | Monolith | Microservices |
|--------|----------|---------------|
| Deployment | Single unit | Independent per service |
| Data | Shared database | Database per service |
| Communication | Function calls | Network (HTTP, messaging) |
| Consistency | ACID transactions | Eventual consistency |
| Team structure | One large team | Small team per service |
| Complexity | In code | In infrastructure |

**When to use microservices:**

- Team is large enough that Conway's Law pushes toward service boundaries
- Parts of system need to scale independently
- Parts of system need to be deployed independently
- Different parts use fundamentally different technologies

**When NOT to use microservices:**

- Small team (fewer than ~8 developers)
- Simple domain
- Bounded contexts not yet identified
- No investment in deployment automation

→ [Sam Newman](../../authors/sam-newman.md)

## See Also

- [Monolith](monolith.md)
- [Modular Monolith](modular-monolith.md)
- [Event-Driven Architecture](../communication/event-driven-architecture.md)
- [Service-Oriented Architecture](../communication/service-oriented-architecture.md)
