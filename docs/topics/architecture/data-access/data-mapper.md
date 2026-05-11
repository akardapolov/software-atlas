# Data Mapper

**Category:** Data Access  
**Source:** Martin Fowler — *Patterns of Enterprise Application Architecture* (2002)

> A layer of mappers that moves data between objects and a database while keeping them independent of each other.

Data Mapper delegates all persistence work to a separate layer. The domain object is a plain object with no database knowledge; the mapper handles the translation to and from the relational schema.

```java
// Domain object: pure, no imports from persistence
public class Order { /* ... */ }

// Mapper handles the database work
OrderMapper mapper = new OrderMapper(connection);
Order order = mapper.findById(orderId);
order.addItem(product, qty);
mapper.update(order);
```

**Strengths:**

- Complete separation between domain model and database schema
- Domain objects can evolve independently from the data model
- Supports complex mappings (inheritance, value objects, aggregations)
- Multiple database schemas can map to the same domain model

**Weaknesses:**

- More code and complexity than Active Record
- Requires understanding of both object and relational models
- Configuration-heavy (annotations or XML)
- Performance can be opaque (N+1 query problems, lazy loading surprises)

**Implementations:** Hibernate (Java), Entity Framework (C#), SQLAlchemy (Python), Doctrine (PHP).

→ [Martin Fowler](../../../authors/martin-fowler.md)

## See Also

- [Active Record](active-record.md) — the simpler alternative where the object persists itself
- [Unit of Work](unit-of-work.md) — often used alongside Data Mapper for change tracking
- [Repository](repository.md) — may wrap a Data Mapper to present a collection-like interface
