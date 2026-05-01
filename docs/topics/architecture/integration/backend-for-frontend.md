# Backend for Frontend (BFF)

**Category:** Integration  
**Source:** Sam Newman — *Building Microservices* (2015); popularised by SoundCloud, Netflix

> A dedicated backend service tailored to the needs of a specific frontend application or platform.

Instead of having a single API Gateway serve all clients, each frontend (web, iOS, Android, IoT) gets its own backend. The BFF:

- Understands the specific needs of its frontend
- Aggregates calls to downstream services
- Handles frontend-specific concerns (session, caching, payload shaping)
- Can be owned by the frontend team

```
         ┌─────────┐     ┌─────────┐     ┌─────────┐
         │   Web   │     │  iOS    │     │ Android │
         │  (SPA)  │     │   App   │     │   App   │
         └────┬────┘     └────┬────┘     └────┬────┘
              │               │               │
              ▼               ▼               ▼
         ┌─────────┐     ┌─────────┐     ┌─────────┐
         │ Web BFF │     │ iOS BFF │     │Andr. BFF│
         └────┬────┘     └────┬────┘     └────┬────┘
              │               │               │
              └───────────────┼───────────────┘
                              ▼
                    ┌─────────────────┐
                    │  Core Services  │
                    │  (domain APIs)  │
                    └─────────────────┘
```

## See Also

- [API Gateway](api-gateway.md)
- [Microservices](../structural/microservices.md)
