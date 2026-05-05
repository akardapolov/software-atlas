# Glossary

Key terms and definitions used throughout Software Engineering Atlas.

!!! note "Work in progress"
    This glossary is being built incrementally as content is added.

## A

**Abstract Data Type (ADT)**
:   A type defined by its operations rather than its implementation.
    Introduced by Barbara Liskov (CLU, 1974).

**Actor Model**
:   A concurrency model where actors communicate via asynchronous messages.
    Popularised by Erlang.

**Agile**
:   A set of values and principles for software development,
    formalised in the Agile Manifesto (2001).

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

## D

**DbC (Design by Contract)**
:   A methodology where software components define formal
    preconditions, postconditions, and invariants.
    Introduced by Bertrand Meyer (1988).

**DDD (Domain-Driven Design)**
:   An approach to software design that centres the model on
    business domain. Introduced by Eric Evans (2003).

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

## M

**Monad**
:   An abstraction for sequencing computations with context (effects,
    failure, state, IO). Widely used in Haskell. Informally: a design
    pattern for composable computation chains.

**MVC (Model-View-Controller)**
:   A UI architecture pattern separating data (Model), presentation
    (View), and input handling (Controller). Originated in Smalltalk-80.

## O

**OOP (Object-Oriented Programming)**
:   An approach where data and behaviour are bundled into objects
    with methods. Originated with Simula (1967).

**OSS (Open Source Software)**
:   Software distributed under licenses that allow modification and
    redistribution. Many foundational tools (GCC, Git, Linux) are OSS.

## S

**SOLID**
:   Five design principles for object-oriented software:
    Single Responsibility, Open/Closed, Liskov Substitution,
    Interface Segregation, Dependency Inversion.
    Compiled by Robert C. Martin.

**STM (Software Transactional Memory)**
:   A concurrency primitive allowing atomic operations on shared memory.
    Key part of Clojure's concurrency model.

## T

**TDD (Test-Driven Development)**
:   A development practice: write a failing test, make it pass,
    refactor. Popularised by Kent Beck (2002).
## U

**Use Case**
:   A description of how a user interacts with a system to achieve
    a goal. Used in requirements gathering and system design.
