# Active Record

**Category:** Data Access  
**Source:** Martin Fowler — *Patterns of Enterprise Application Architecture* (2002)

> An object that wraps a row in a database table, encapsulating the database access and adding domain logic on that data.

In Active Record, the domain object itself knows how to load, save, and delete itself from the database. The class provides static finder methods and instance methods for persistence operations.

```ruby
# Rails-style Active Record
user = User.find(1)
user.name = "Alice"
user.save

# Querying
User.where(active: true).order(:created_at)
```

**Strengths:**

- Extremely simple and fast to get started
- Minimal boilerplate — no separate mapper or repository needed
- Convention over configuration reduces decision fatigue
- Excellent for prototypes and simple CRUD applications

**Weaknesses:**

- Domain logic and persistence are tightly coupled
- Hard to unit test without a database
- Complex queries become awkward (SQL bleeds into model code)
- Does not scale well to rich domain models with complex object graphs

**When to use:**

- Simple schemas where tables map cleanly to objects
- CRUD-heavy applications with little business logic
- Rapid prototyping
- Frameworks that optimise for this pattern (Rails, Laravel, Django ORM)

→ [Martin Fowler](../../../authors/martin-fowler.md)

## See Also

- [Data Mapper](data-mapper.md) — separates persistence from the domain object
- [Repository](repository.md) — a higher-level abstraction that may wrap Active Record
- [Anemic Domain Model](../../../works/papers/fowler-2002-poeaa.md) — the anti-pattern Active Record risks becoming
