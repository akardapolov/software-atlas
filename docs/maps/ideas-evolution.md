# Ideas Evolution Map

Visual diagram showing how ideas influenced each other across decades.

## The Big Picture

```mermaid
flowchart TD
    subgraph Foundation["🔬 Foundation: 1930s-1950s"]
        Godel["Gödel<br/>Incompleteness<br/>1931"]
        Church["Church<br/>Lambda Calculus<br/>1936"]
        Turing["Turing<br/>Universal Machine<br/>1936"]
        McCarthy["McCarthy<br/>Lisp<br/>1958"]

        Godel --> Church
        Church --> McCarthy
    end

    subgraph Structure["🏗 Structure: 1960s-1970s"]
        Conway["Conway<br/>Conway's Law<br/>1967"]
        Dijkstra["Dijkstra<br/>Structured Programming<br/>1968"]
        Parnas["Parnas<br/>Information Hiding<br/>1972"]
        Brooks["Brooks<br/>Mythical Man-Month<br/>1975"]
        Hoare["Hoare<br/>CSP<br/>1978"]

        Dijkstra --> Parnas
        Parnas --> Brooks
        Conway --> Brooks
    end

    subgraph OOP["📦 OOP: 1960s-1990s"]
        Simula["Dahl & Nygaard<br/>Simula<br/>1962"]
        Smalltalk["Kay<br/>Smalltalk<br/>1972"]
        GoF["GoF<br/>Design Patterns<br/>1995"]

        Simula --> Smalltalk
        Smalltalk --> GoF
    end

    subgraph FP["λ Functional: 1970s-2000s"]
        ML["Milner<br/>ML<br/>1978"]
        Hughes["Hughes<br/>Why FP Matters<br/>1989"]
        Haskell["Committee<br/>Haskell<br/>1990"]
        Hickey["Hickey<br/>Clojure<br/>2007"]

        McCarthy --> ML
        ML --> Haskell
        Hughes --> Haskell
        Church --> Hickey
        Haskell --> Hickey
    end

    subgraph Distributed["🌐 Distributed: 2000s"]
        Brewer["Brewer<br/>CAP Theorem<br/>2000"]
        Helland["Helland<br/>Beyond DT<br/>2007"]

        Brewer --> Helland
    end

    subgraph Modern["🚀 Modern: 2000s-2020s"]
        Evans["Evans<br/>DDD<br/>2003"]
        Cockburn["Cockburn<br/>Hexagonal<br/>2005"]
        Newman["Newman<br/>Microservices<br/>2015"]
        TeamTopo["Skelton & Pais<br/>Team Topologies<br/>2019"]

        Parnas --> Evans
        Evans --> Cockburn
        Cockburn --> Newman
        Conway --> TeamTopo
        Brooks --> TeamTopo
        Helland --> Newman
    end

    %% Cross-subgraph connections
    Parnas --> OOP
    Smalltalk --> Evans
    Hoare --> Distributed

    style Foundation fill:#e3f2fd
    style Structure fill:#f3e5f5
    style OOP fill:#fff3e0
    style FP fill:#e8f5e9
    style Distributed fill:#fce4ec
    style Modern fill:#e0f7fa
```

## Timeline of Key Ideas

| Era | Key Ideas | Authors | Legacy |
|------|-----------|----------|--------|
| 1930-1950 | Incompleteness, Lambda calculus, Universal Machine | Gödel, Church, Turing | Theoretical CS foundation |
| 1950-1970 | Lisp, Algol, Structured programming | McCarthy, Dijkstra | First programming paradigms |
| 1970-1980 | Information Hiding, OOP, CSP | Parnas, Kay, Hoare | Modularity, concurrency |
| 1980-1990 | Why FP Matters, Erlang, ML | Hughes, Armstrong | FP renaissance seeds |
| 1990-2000 | Design Patterns, Agile, Refactoring | GoF, Beck, Fowler | OOP maturity, process revolution |
| 2000-2010 | DDD, CAP, Hexagonal | Evans, Brewer, Cockburn | Architecture patterns |
| 2010-2020 | Microservices, Team Topologies | Newman, Skelton | Org + tech co-design |

## Category Breakdown

### 🏗 Architecture & Modularity

The lineage of thinking about system structure.

```mermaid
flowchart LR
    Conway67["Conway's Law<br/>1967"] --> Parnas72["Information Hiding<br/>1972"]
    Parnas72 --> Brooks75["Mythical Man-Month<br/>1975"]
    Brooks75 --> DDD03["DDD<br/>2003"]
    DDD03 --> Hex05["Hexagonal<br/>2005"]
    Hex05 --> Micro15["Microservices<br/>2015"]
    Micro15 --> TT19["Team Topologies<br/>2019"]

    style Conway67 fill:#e1bee7
    style TT19 fill:#a5d6a7
```

**Core insight:** Organizations and systems co-evolve. Good boundaries require understanding both.

### 📦 OOP & Design

From Simula to modern design patterns.

```mermaid
flowchart LR
    Simula62["Simula<br/>1962"] --> Smalltalk72["Smalltalk<br/>1972"]
    Smalltalk72 --> GoF95["Design Patterns<br/>1995"]
    GoF95 --> SOLID["SOLID Principles<br/>2000s"]
    Smalltalk72 --> Refactoring99["Refactoring<br/>1999"]
    GoF95 --> DDD03["DDD<br/>2003"]

    style Simula62 fill:#ffcc80
    style DDD03 fill:#81c784
```

**Core insight:** OOP is about messages between objects, not about inheritance hierarchies.

### λ Functional Programming

The long journey from lambda calculus to mainstream.

```mermaid
flowchart LR
    Lambda36["Lambda Calculus<br/>1936"] --> Lisp58["Lisp<br/>1958"]
    Lisp58 --> ML78["ML<br/>1978"]
    ML78 --> Haskell90["Haskell<br/>1990"]
    Lisp58 --> Scheme75["Scheme<br/>1975"]
    Scheme75 --> SICP["SICP<br/>1985"]
    Haskell90 --> Clojure07["Clojure<br/>2007"]
    Lisp58 --> Clojure07

    style Lambda36 fill:#c8e6c9
    style Clojure07 fill:#4caf50,color:#fff
```

**Core insight:** Pure functions and immutability make programs easier to reason about.

### 🌐 Distributed Systems

Understanding the limits and patterns of distributed computing.

```mermaid
flowchart LR
    CSP78["CSP<br/>1978"] --> Erlang86["Erlang<br/>1986"]
    CAP00["CAP Theorem<br/>2000"] --> Beyond07["Beyond DT<br/>2007"]
    Beyond07 --> EventSourcing["Event Sourcing<br/>2010s"]
    Erlang86 --> ActorModel["Actor Model<br/>mainstream"]
    CAP00 --> NoSQL["NoSQL Movement<br/>2009+"]

    style CAP00 fill:#f8bbd9
    style EventSourcing fill:#ce93d8
```

**Core insight:** Distributed transactions don't scale; design around eventual consistency.

### 📋 Process & Practices

How we work together on software.

```mermaid
flowchart LR
    MMM75["Mythical Man-Month<br/>1975"] --> XP96["XP<br/>1996"]
    XP96 --> Agile01["Agile Manifesto<br/>2001"]
    XP96 --> TDD["TDD<br/>2003"]
    Agile01 --> Scrum["Scrum mainstream"]
    Agile01 --> DevOps09["DevOps<br/>2009"]
    DevOps09 --> TT19["Team Topologies<br/>2019"]

    style MMM75 fill:#b2dfdb
    style TT19 fill:#4db6ac
```

**Core insight:** Small teams, short iterations, continuous feedback.

## See Also

- [Master Timeline](./master-timeline.md)
- [Languages Genealogy](./languages-genealogy.md)
- [Paradigms Map](./paradigms-map.md)
