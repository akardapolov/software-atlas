# Anti-Corruption Layer (ACL)

**Category:** Integration  
**Source:** Eric Evans — *Domain-Driven Design* (2003)

> A translation layer that insulates a bounded context from changes and incompatible models in external systems.

When integrating with legacy systems or external services, their data models and APIs may not align with your domain model. The ACL sits at the boundary, translating external concepts into your domain's ubiquitous language and vice versa.

```
┌─────────────────┐     ┌─────────────────────┐     ┌─────────────────┐
│   Our Domain    │────▶│  Anti-Corruption    │────▶│ External System │
│  (clean model)  │◀────│       Layer         │◀────│  (legacy model) │
└─────────────────┘     │  - Translate        │     └─────────────────┘
                        │  - Adapt            │
                        │  - Isolate          │
                        └─────────────────────┘
```

**Components of an ACL:**

- **Translator** — maps external models to internal models
- **Adapter** — wraps external API to match internal interfaces
- **Facade** — simplifies a complex external interface

## See Also

- [Domain-Driven Design](../index.md#domain-driven-design-ddd-2003)
- [Adapter](../../design/structural/adapter.md)
- [Facade](../../design/structural/facade.md)
