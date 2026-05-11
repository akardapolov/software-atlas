# Gateway

**Category:** Data Access  
**Source:** Martin Fowler — *Patterns of Enterprise Application Architecture* (2002)

> An object that acts as a gateway to a database table or record, encapsulating all access to the data source.

Gateway patterns provide a thin, SQL-centric wrapper around database tables. Unlike Active Record, the gateway object itself is not a domain object — it is a data access object that returns simple data structures (DTOs, records, or raw result sets).

### Table Data Gateway

One object per database table. All CRUD operations for that table live in one class.

```java
ProductGateway gateway = new ProductGateway(connection);
List<ProductRecord> products = gateway.findByCategory("electronics");
gateway.insert(newProduct);
```

### Row Data Gateway

One object per row. Each instance represents a single record and provides methods to update or delete it.

```java
ProductRecord product = gateway.findById(42);
product.setPrice(99.99);
product.update();
```

**Strengths:**

- Simple, direct, and easy to understand
- Excellent for reporting and analytics queries
- No object-relational impedance mismatch
- Easy to optimise raw SQL performance

**Weaknesses:**

- No domain modelling — business logic must live elsewhere
- SQL scattered across many gateway classes
- Less testable than Repository (requires a real or mock database)
- Tedious boilerplate for simple CRUD

**When to use:**

- Legacy systems with complex existing schemas
- Reporting and read-only queries
- Performance-critical paths where ORM overhead is unacceptable
- Simple applications where full ORM is overkill

→ [Martin Fowler](../../../authors/martin-fowler.md)

## See Also

- [Repository](repository.md) — a higher-level, domain-centric abstraction
- [Active Record](active-record.md) — where the object is its own data access layer
- [CQRS](../data/cqrs.md) — separates read and write models, often using Gateways for reads
