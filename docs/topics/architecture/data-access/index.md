# Data Access Patterns

Patterns for how applications interact with persistent storage. These patterns sit at the boundary between domain logic and the database, mediating how objects are saved, loaded, and queried.

---

## The Core Decision

The fundamental choice is **who owns the persistence logic**:

| Pattern | Persistence logic lives in... | Best for |
|---------|------------------------------|----------|
| [Active Record](active-record.md) | The domain object itself | Simple schemas, CRUD-heavy |
| [Data Mapper](data-mapper.md) | A separate mapping layer | Complex domains, rich models |
| [Repository](repository.md) | An abstraction in the domain | DDD, testability |
| [Gateway](gateway.md) | A thin SQL wrapper | Legacy systems, reporting |
| [Unit of Work](unit-of-work.md) | A transaction coordinator | Batch changes, consistency |

## Comparison

```
┌─────────────────────────────────────────────────────────────┐
│  Active Record    │  Data Mapper      │  Repository       │
├─────────────────────────────────────────────────────────────┤
│  User.save()      │  mapper.save(user)│  repo.save(user)  │
│  User.find(1)     │  mapper.find(1)   │  repo.findById(1) │
├─────────────────────────────────────────────────────────────┤
│  Simple, fast     │  Flexible,        │  Domain-centric,  │
│  to write         │  decoupled        │  mockable         │
└─────────────────────────────────────────────────────────────┘
```

## When to Use What

- **Start with Active Record** for simple applications where the domain model closely matches the database schema (Rails, Django, Eloquent).
- **Move to Data Mapper** when the object graph becomes complex and the database structure diverges from the domain model (Hibernate, Entity Framework, SQLAlchemy).
- **Add Repository** when you need to isolate the domain from persistence details, especially in DDD or hexagonal architectures.
- **Use Unit of Work** alongside Repository or Data Mapper when changes span multiple objects and must commit atomically.
- **Use Gateway** when you need raw SQL performance or are working with legacy/reporting queries.

## See Also

- [Layered Architecture](../domain-centric/layered-architecture.md) — where the data access layer sits
- [CQRS](../data/cqrs.md) — an alternative to unified read/write models
- [Domain-Driven Design](../index.md#domain-driven-design-ddd-2003) — Repository as a DDD tactical pattern
- [POEAA](../../works/papers/fowler-2002-poeaa.md) — the canonical reference
