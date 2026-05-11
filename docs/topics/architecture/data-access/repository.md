# Repository

**Category:** Data Access  
**Source:** Eric Evans — *Domain-Driven Design* (2003); Martin Fowler — *Patterns of Enterprise Application Architecture* (2002)

> Mediate between the domain and data mapping layers using a collection-like interface for accessing domain objects.

A Repository presents domain objects as if they were an in-memory collection. Client code asks the Repository for objects by criteria, and the Repository handles the query execution, object construction, and caching. The domain layer remains ignorant of database details.

```java
// Domain code knows nothing about SQL
Order order = orderRepository.findById(orderId);
order.addItem(product, qty);
orderRepository.save(order);
```

**Strengths:**

- Decouples domain logic from persistence technology
- Easy to substitute a fake in-memory implementation for tests
- Centralises query logic in one place
- Natural fit for DDD and hexagonal architectures

**Weaknesses:**

- Adds an abstraction layer that can be overkill for simple CRUD
- Repositories can grow bloated with many query methods
- Complex queries may leak persistence concerns into the interface

**Repository vs DAO:**

A DAO is a lower-level abstraction focused on database access. A Repository is domain-centric — its interface speaks in domain terms (`findByCustomerId`) rather than storage terms (`selectByForeignKey`).

→ [Eric Evans](../../../authors/eric-evans.md) · [Martin Fowler](../../../authors/martin-fowler.md)

## See Also

- [Unit of Work](unit-of-work.md) — often paired with Repository for change tracking
- [Active Record](active-record.md) — a simpler alternative when the model is the persistence mechanism
- [Data Mapper](data-mapper.md) — the mapping layer a Repository may delegate to
