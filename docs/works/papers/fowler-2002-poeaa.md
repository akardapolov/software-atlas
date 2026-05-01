# Patterns of Enterprise Application Architecture

| | |
|---|---|
| **Author** | Martin Fowler |
| **Year** | 2002 |
| **Publication** | Addison-Wesley |
| **Topic(s)** | Enterprise patterns, architecture, database design |

## Summary

This book catalogues **architectural patterns** for building large-scale enterprise applications. Fowler's work extended the **GoF** patterns into the enterprise domain, addressing common challenges in building data-intensive, distributed systems. It became a foundational reference for enterprise application architects.

## Key Ideas

### Enterprise Pattern Catalog

The book presents 51 patterns organized by layer:

#### Data Access Layer (7)

| Pattern | Description | Example |
|---------|-------------|---------|
| **Data Mapper** | Map between domain objects and DB | JPA, Hibernate entities |
| **Data Transfer Object (DTO)** | Transfer data between layers | API response objects |
| **Query Object** | Encapsulate database queries | Search criteria, joins |
| **Repository** | Collection-like interface to data access | OrderRepository with CRUD |
| **Unit of Work** | Single object performing an operation | UpdateTaskService |
| **Active Record** | Find/return domain objects by criteria | CustomerRepository findCustomer() |
| **Row Data Gateway** | Gateway for tabular data access | ReportGateway for reports |
| **Table Data Gateway** | Gateway for CRUD operations | ProductTableGateway |

#### Domain Layer (8)

| Pattern | Description | Example |
|---------|-------------|---------|
| **Domain Model** | Model entire business domain | Order, Customer, Product domains |
| **Service Layer** | Application services | OrderService, PaymentService |
| **Application Service Layer** | Business processes | OrderWorkflow, InvoiceWorkflow |
| **Separation** | Cross-cutting concerns | Transaction management, logging, security |

### Layering Principles

Fowler describes architecture principles:

- **Separate concerns** — each layer has single responsibility
- **Dependencies point inward** — layer depends only on inner layers
- **Domain-driven design** - business logic in domain layer
- **Database independence** - swap persistence implementation
- **Protocol-based interfaces** — layers communicate through well-defined contracts

### Anti-Patterns

The book warns against common mistakes:

- **Anemic Domain Model** - domain objects without behavior
- **Over-generic layers** - too general-purpose services
- **Database-driven design** - domain modeled on DB structure
- **Procedural business logic** - services lacking OO encapsulation

## Historical Significance

### Foundation of DDD

This work, along with **Eric Evans' "Domain-Driven Design"** book:

- **Established DDD** as approach to enterprise systems
- **Layered architecture** - DDD's four canonical layers
- **Ubiquitous language** - domain language for business
- **Strategic design** - business experts work with developers

### Industry Adoption

POEAA influenced:

- **Spring Framework** - implements many POEAA patterns
- **.NET** - repositories and patterns in Entity Framework
- **Java EE** - service layers and transaction management
- **Architectural thinking** - layers, services, domain modeling

## Legacy

POEAA remains:

- **Architectural reference** - for enterprise system design
- **Framework foundation** - Spring, Hibernate influenced by patterns
- **DDD education** - layers and domain modeling taught as standard

## Connections

- **Builds on GoF:** [Design Patterns](../books/gof-1994-design-patterns.md)
- **Preceded by:** [Eric Evans (DDD)](../works/books/evans-2003-ddd.md)
- **Author:** [Martin Fowler](../../authors/martin-fowler.md)
- **Related works:** [Refactoring (1999)](../books/fowler-1999-refactoring.md) · [EAA (2002)](../works/books/fowler-2003-uml-distilled.md)
