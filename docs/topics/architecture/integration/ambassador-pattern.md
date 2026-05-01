# Ambassador Pattern

**Category:** Integration  
**Source:** Microsoft — *Cloud Design Patterns* (2014)

> A helper service that sends network requests on behalf of an application, offloading client connectivity concerns.

The Ambassador is a sidecar process or library that handles cross-cutting network concerns — retries, circuit breaking, load balancing, service discovery — so the application code remains simple and focused on business logic.

```
┌─────────────────────────────────────┐
│          Application Pod            │
│  ┌─────────────┐  ┌─────────────┐  │
│  │   App       │  │  Ambassador │  │
│  │  (simple    │◀─│  (retries,  │  │
│  │   HTTP)     │──│   circuit   │  │
│  └─────────────┘  │   break)    │  │
│                   └──────┬──────┘  │
└──────────────────────────┼─────────┘
                           │
                    ┌──────┴──────┐
                    │  External   │
                    │  Service    │
                    └─────────────┘
```

**Ambassador vs Proxy:**

An Ambassador is co-located with the client and shares its lifecycle. A Proxy (or API Gateway) is a standalone infrastructure component serving many clients.

## See Also

- [Circuit Breaker](../resilience/circuit-breaker.md)
- [Retry Pattern](../resilience/retry-pattern.md)
- [API Gateway](api-gateway.md)
