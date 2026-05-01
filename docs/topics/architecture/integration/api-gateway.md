# API Gateway

**Category:** Integration  
**Source:** Industry pattern, widely adopted in microservices architectures (2010s)

> A single entry point that routes client requests to backend services, handling cross-cutting concerns.

An API Gateway sits between clients and backend services, acting as a reverse proxy. It routes requests, composes responses, and centralises concerns that would otherwise be duplicated across services.

**Responsibilities:**

| Concern | Description |
|---------|-------------|
| **Routing** | Map external URLs to internal service endpoints |
| **Composition** | Aggregate data from multiple services into one response |
| **Authentication / Authorisation** | Verify tokens, enforce access control |
| **Rate limiting** | Protect backends from overload |
| **SSL termination** | Handle HTTPS at the edge |
| **Caching** | Cache responses to reduce backend load |
| **Request transformation** | Convert protocols or formats (REST to gRPC, JSON to XML) |

**Gateway vs Load Balancer:**

A load balancer distributes traffic at the transport layer (L4). An API Gateway operates at the application layer (L7), understanding HTTP paths, headers, and request bodies.

## See Also

- [Backend for Frontend](backend-for-frontend.md)
- [Microservices](../structural/microservices.md)
- [Service-Oriented Architecture](../communication/service-oriented-architecture.md)
