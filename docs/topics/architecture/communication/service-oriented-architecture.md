# Service-Oriented Architecture (SOA)

**Category:** Communication  
**Source:** Industry standardisation efforts (OASIS, 2000s)

> Services are self-contained, loosely coupled units of functionality exposed through standardised interfaces.

SOA organises software as a collection of **services** — discrete units of functionality that are:

- **Self-contained** — each service owns its logic and data
- **Loosely coupled** — services interact through contracts, not implementation details
- **Interoperable** — standard protocols (SOAP, REST) and formats (XML, JSON)
- **Reusable** — same service can be consumed by multiple applications

SOA is often implemented with an **Enterprise Service Bus (ESB)** — a central middleware that handles routing, transformation, and protocol mediation between services.

**SOA vs Microservices:**

| Aspect | SOA | Microservices |
|--------|-----|---------------|
| Granularity | Coarse (enterprise-level) | Fine (bounded-context level) |
| Communication | ESB, SOAP, XML | Direct, REST/gRPC, JSON |
| Database | Often shared | Database per service |
| Governance | Centralised, standards-heavy | Decentralised, team autonomy |
| Deployment | Usually monolithic services | Independently deployable |

## See Also

- [Microservices](../structural/microservices.md)
- [Event-Driven Architecture](event-driven-architecture.md)
- [API Gateway](../integration/api-gateway.md)
