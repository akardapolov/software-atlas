# Industrial Functional Architecture

**Category:** Domain-Centric  
**Source:** Alexander Zhidkov — practitioner talks (2020–present)

> Pure functional business logic, immutable domain aggregates, and test coverage through public API.

!!! note "Emerging approach"
    This is a practitioner-proposed approach, not yet as widely peer-reviewed as the patterns above. The primary source is a conference talk (2024); treat it as a promising direction rather than an established standard.

As an alternative to Clean/Hexagonal/Onion Architecture for backend applications, Alexander Zhidkov proposes **Industrial Functional Architecture**. He argues that these architectures often bring unnecessary ceremony without providing meaningful benefits in application development contexts.

The approach combines:

- **Pure functional business logic** — separation of business logic from I/O
- **Domain-driven aggregates** — immutable objects forming a DAG
- **Simple repository patterns** — avoiding JPA/Hibernate complexity
- **Test coverage through public API** — behaviour coverage with fast tests

Key principles:

- Aggregates are immutable objects stored in mutable repositories
- Business logic is pure functions without side effects
- Tests work through public API only
- Mocks only for unmanaged dependencies

→ [Alexander Zhidkov](../../authors/alexander-zhidkov.md) ·
[Эргономичный подход](../../works/talks/zhidkov-2024-ergonomichnyy-podkhod.md)

## See Also

- [Clean Architecture](clean-architecture.md)
- [Hexagonal Architecture](hexagonal-architecture.md)
- [Functional Programming](../../functional/index.md)
