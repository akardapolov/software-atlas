# Glossary

Key terms and definitions used throughout Software Engineering Atlas.

!!! note "Work in progress"
    This glossary is being built incrementally as content is added.

## A

**ACID**
:   Atomicity, Consistency, Isolation, Durability — the four properties
    of reliable transactions. Formalised by Jim Gray (1981), terminology
    by Haerder and Reuter (1983).

**Abstract Data Type (ADT)**
:   A type defined by its operations rather than its implementation.
    Introduced by Barbara Liskov (CLU, 1974).

**Actor Model**
:   A concurrency model where actors communicate via asynchronous messages.
    Popularised by Erlang.

**Agile**
:   A set of values and principles for software development,
    formalised in the Agile Manifesto (2001).

**Active Record**
:   A pattern where a domain object encapsulates both data and
    the logic to access the database (CRUD). The object saves,
    loads, and deletes itself. Popularised by Rails.

## C

**CAP Theorem**
:   A distributed system can provide at most two of three guarantees:
    Consistency, Availability, Partition tolerance.
    Conjectured by Eric Brewer (2000).

**CQS (Command-Query Separation)**
:   A method should either command (change state, return nothing) or
    query (return data, change nothing), but not both.
    Introduced by Bertrand Meyer.

**CSP (Communicating Sequential Processes)**
:   A formal language for describing concurrent systems where processes
    communicate via synchronous channels.
    Introduced by Tony Hoare (1978). Influenced Go.

**CQRS**
:   Command Query Responsibility Segregation — separating read and write models
    for improved performance and scalability in distributed systems.

**Component-Based Architecture**
:   Decomposing a UI into independent, reusable, self-contained components
    that manage their own state and rendering. Popularised by React, Vue,
    Angular, and Web Components.

## D

**DbC (Design by Contract)**
:   A methodology where software components define formal
    preconditions, postconditions, and invariants.
    Introduced by Bertrand Meyer (1988).

**DDD (Domain-Driven Design)**
:   An approach to software design that centres the model on
    business domain. Introduced by Eric Evans (2003).

**Data Mapper**
:   A layer that moves data between in-memory objects and a
    database while keeping them independent. Used by Hibernate,
    Entity Framework, SQLAlchemy.

## B

**B-Tree**
:   A self-balancing tree data structure optimised for disk-based storage.
    Keeps data sorted and enables efficient insertion, deletion, and
    range queries. Used by most traditional relational databases
    (PostgreSQL, MySQL, SQL Server).

## G

**Gateway**
:   A pattern that encapsulates access to an external system or
    database table behind a simple interface. Table Data Gateway
    provides one object per table; Row Data Gateway provides one
    per record.

## H

**Hexagonal Architecture**
:   Also called Ports & Adapters. The application core defines ports
    (interfaces) and the outside world connects via adapters.
    Introduced by Alistair Cockburn (2005).

**Homoiconicity**
:   A property of languages where code and data share the same
    representation. Lisp and Clojure are homoiconic.

## L

**Lambda Calculus**
:   A formal system for expressing computation based on function
    abstraction and application. Created by Alonzo Church (1936).
    Foundation of all functional programming.

**LSP (Liskov Substitution Principle)**
:   Objects of a supertype should be replaceable with objects of
    a subtype without altering correctness. Formalised by
    Barbara Liskov and Jeannette Wing (1994).

**LSM-Tree (Log-Structured Merge Tree)**
:   A write-optimised storage structure that batches writes sequentially
    and merges sorted files over time. Used by RocksDB, Cassandra,
    LevelDB.

## M

**Monad**
:   An abstraction for sequencing computations with context (effects,
    failure, state, IO). Widely used in Haskell. Informally: a design
    pattern for composable computation chains.

**MVCC (Multi-Version Concurrency Control)**
:   A concurrency control method where each transaction sees a consistent
    snapshot of data, enabling readers and writers to proceed without
    blocking each other. Used by PostgreSQL, MySQL (InnoDB), SQL Server.

**MVC (Model-View-Controller)**
:   A UI architecture pattern separating data (Model), presentation
    (View), and input handling (Controller). Originated in Smalltalk-80.

**MVP (Model-View-Presenter)**
:   A UI pattern where a Presenter mediates all communication between
    the passive View and the Model. The View contains no logic.

**MVVM (Model-View-ViewModel)**
:   A UI pattern using declarative data binding between the View and
    a ViewModel that exposes data from the Model. Popular in WPF,
    SwiftUI, Jetpack Compose, Vue, Angular.

**MVI (Model-View-Intent)**
:   A reactive UI pattern where the View emits Intents as a stream,
    a Processor reduces them into state, and the View observes the
    resulting state stream. Popular in Android with Jetpack Compose.

**Micro-frontend**
:   An independently deployable frontend module that composes with
    other modules to form a complete web application. The frontend
    analogue of microservices.

## O

**OOP (Object-Oriented Programming)**
:   An approach where data and behaviour are bundled into objects
    with methods. Originated with Simula (1967).

**OSS (Open Source Software)**
:   Software distributed under licenses that allow modification and
    redistribution. Many foundational tools (GCC, Git, Linux) are OSS.

## P

**Presenter**
:   In MVP, the component that contains all presentation logic and
    mediates between the passive View and the Model.

## N

**NoSQL**
:   A broad category of non-relational databases emphasising scalability,
    flexible schemas, and specialised data models. Emerged in the late 2000s
    as a response to the limitations of relational systems at web scale.

**Normalization**
:   Organising data to reduce redundancy and improve integrity.
    Codd defined normal forms (1NF, 2NF, 3NF, BCNF) that progressively
    eliminate data duplication and prevent update anomalies.

## S

**Sharding**
:   Partitioning data across multiple machines. Each shard holds a subset
    of the data, enabling horizontal scaling. See also: partitioning
    in distributed systems.

**SQL (Structured Query Language)**
:   Declarative language for querying and manipulating relational data.
    First standardised in 1986 (ANSI). Based on Codd's relational algebra.

**SOLID**
:   Five design principles for object-oriented software:
    Single Responsibility, Open/Closed, Liskov Substitution,
    Interface Segregation, Dependency Inversion.
    Compiled by Robert C. Martin.

**STM (Software Transactional Memory)**
:   A concurrency primitive allowing atomic operations on shared memory.
    Key part of Clojure's concurrency model.

## V

**ViewModel**
:   In MVVM, an abstraction of the View that exposes observable
    properties and commands. The UI framework binds View elements
    to ViewModel properties automatically.

## T

**TDD (Test-Driven Development)**
:   A development practice: write a failing test, make it pass,
    refactor. Popularised by Kent Beck (2002).
## U

**Use Case**
:   A description of how a user interacts with a system to achieve
    a goal. Used in requirements gathering and system design.

**Unit of Work**
:   A pattern that maintains a list of objects affected by a
    business transaction and coordinates writing out changes
    atomically. Introduced by Martin Fowler.

**Unidirectional Data Flow**
:   A UI architecture where all state changes follow a single
    direction: actions are dispatched to a central store, which
    updates state and notifies views to re-render. Redux, Flux,
    Vuex, and NgRx implement this pattern.
