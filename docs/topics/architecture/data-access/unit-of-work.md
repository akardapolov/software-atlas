# Unit of Work

**Category:** Data Access  
**Source:** Martin Fowler — *Patterns of Enterprise Application Architecture* (2002)

> Maintain a list of objects affected by a business transaction and coordinate writing out changes and resolving concurrency problems.

Business transactions often modify multiple objects. Unit of Work tracks which objects are new, dirty, or deleted, and commits all changes in a single atomic operation. This prevents partial updates and reduces database round-trips.

```java
UnitOfWork uow = new UnitOfWork();
OrderRepository repo = new OrderRepository(uow);

Order order = repo.findById(orderId);
order.addItem(product, qty);     // marked dirty by UoW
Customer customer = repo.findCustomer(order.customerId);
customer.addLoyaltyPoints(10);   // also marked dirty

uow.commit();  // INSERT, UPDATE, DELETE batched in one transaction
```

**Strengths:**

- Ensures atomicity across multiple object changes
- Reduces database round-trips by batching writes
- Centralises concurrency handling (optimistic locking)
- Works transparently when integrated with the mapper or repository

**Weaknesses:**

- Adds complexity and can be hard to debug
- Long-lived units of work risk stale data and lock contention
- Requires discipline to register all changes with the unit of work
- Identity Map (caching loaded objects) is usually needed alongside it

**When to use:**

- Complex transactions that touch multiple aggregates
- Systems using Data Mapper or Repository patterns
- Anywhere atomic consistency across objects is required

→ [Martin Fowler](../../../authors/martin-fowler.md)

## See Also

- [Repository](repository.md) — often integrates with Unit of Work
- [Data Mapper](data-mapper.md) — typically paired with Unit of Work in ORMs
- [Saga Pattern](../resilience/saga-pattern.md) — for distributed transactions across services
