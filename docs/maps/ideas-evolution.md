# Ideas Evolution Map

Visual diagram showing how ideas influenced each other across decades.

## The Big Picture

```mermaid
flowchart TD
    subgraph Foundation["🔬 Foundation: 1930s-1950s"]
        Godel["Gödel\nIncompleteness\n1931"]
        Church["Church\nLambda Calculus\n1936"]
        Turing["Turing\nUniversal Machine\n1936"]
        McCarthy["McCarthy\nLisp\n1958"]

        Godel --> Church
        Church --> McCarthy
    end

    subgraph Structure["🏗 Structure: 1960s-1970s"]
        Conway["Conway\nConway's Law\n1967"]
        Dijkstra["Dijkstra\nStructured Programming\n1968"]
        Parnas["Parnas\nInformation Hiding\n1972"]
        Brooks["Brooks\nMythical Man-Month\n1975"]
        Hoare["Hoare\nCSP\n1978"]

        Dijkstra --> Parnas
        Parnas --> Brooks
        Conway --> Brooks
    end

    subgraph OOP["📦 OOP: 1960s-1990s"]
        Simula["Dahl & Nygaard\nSimula 67\n1967"]
        Smalltalk["Kay\nSmalltalk\n1972"]
        GoF["GoF\nDesign Patterns\n1995"]

        Simula --> Smalltalk
        Smalltalk --> GoF
    end

    subgraph FP["λ Functional: 1970s-2000s"]
        ML["Milner\nML\n1978"]
        Hughes["Hughes\nWhy FP Matters\n1989"]
        Haskell["Committee\nHaskell\n1990"]
        Hickey["Hickey\nClojure\n2007"]

        McCarthy --> ML
        ML --> Haskell
        Hughes --> Haskell
        Church --> Hickey
        Haskell --> Hickey
    end

    subgraph Distributed["🌐 Distributed: 2000s"]
        Brewer["Brewer\nCAP Theorem\n2000"]
        Helland["Helland\nBeyond DT\n2007"]

        Brewer --> Helland
    end

    subgraph Modern["🚀 Modern: 2000s-2020s"]
        Evans["Evans\nDDD\n2003"]
        Cockburn["Cockburn\nHexagonal\n2005"]
        Newman["Newman\nMicroservices\n2015"]
        TeamTopo["Skelton & Pais\nTeam Topologies\n2019"]

        Parnas --> Evans
        Evans --> Cockburn
        Cockburn --> Newman
        Conway --> TeamTopo
        Brooks --> TeamTopo
        Helland --> Newman
    end

    subgraph CV["👁 Computer Vision: 2000s–2020s"]
        Haar["Viola & Jones\nHaar Cascades\n2001"]
        HOG["Dalal & Triggs\nHOG\n2005"]
        AlexNet["Krizhevsky et al.\nAlexNet\n2012"]
        RCNN["Girshick et al.\nR-CNN\n2014"]
        FasterRCNN["Ren et al.\nFaster R-CNN\n2015"]
        YOLO["Redmon et al.\nYOLO\n2016"]
        SSD["Liu et al.\nSSD\n2016"]

        Haar --> HOG
        HOG --> AlexNet
        AlexNet --> RCNN
        RCNN --> FasterRCNN
        FasterRCNN --> YOLO
        FasterRCNN --> SSD
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
    style CV fill:#fff8e1
```

## Timeline of Key Ideas

| Era | Key Ideas | Authors | Legacy |
|------|-----------|----------|--------|
| 1930-1950 | Incompleteness, Lambda calculus, Universal Machine | Gödel, Church, Turing | Theoretical CS foundation |
| 1950-1970 | Lisp, ALGOL 60, Structured programming | McCarthy, Dijkstra | First programming paradigms |
| 1970-1980 | Information Hiding, OOP, CSP | Parnas, Kay, Hoare | Modularity, concurrency |
| 1980-1990 | Why FP Matters, Erlang, ML | Hughes, Armstrong | FP renaissance seeds |
| 1990-2000 | Design Patterns, Agile, Refactoring | GoF, Beck, Fowler | OOP maturity, process revolution |
| 2000-2010 | DDD, CAP, Hexagonal | Evans, Brewer, Cockburn | Architecture patterns |
| 2010-2020 | Microservices, Team Topologies, YOLO | Newman, Skelton, Redmon | Org + tech co-design; real-time vision |

## Category Breakdown

### 🏗 Architecture & Modularity

The lineage of thinking about system structure.

```mermaid
flowchart LR
    Conway67["Conway's Law\n1967"] --> Parnas72["Information Hiding\n1972"]
    Parnas72 --> Brooks75["Mythical Man-Month\n1975"]
    Brooks75 --> DDD03["DDD\n2003"]
    DDD03 --> Hex05["Hexagonal\n2005"]
    Hex05 --> Micro15["Microservices\n2015"]
    Micro15 --> TT19["Team Topologies\n2019"]

    style Conway67 fill:#e1bee7
    style TT19 fill:#a5d6a7
```

**Core insight:** Organizations and systems co-evolve. Good boundaries require understanding both.

### 📦 OOP & Design

From Simula to modern design patterns.

```mermaid
flowchart LR
    Simula67["Simula 67\n1967"] --> Smalltalk72["Smalltalk\n1972"]
    Smalltalk72 --> GoF95["Design Patterns\n1995"]
    GoF95 --> SOLID["SOLID Principles\n2000s"]
    Smalltalk72 --> Refactoring99["Refactoring\n1999"]
    GoF95 --> DDD03["DDD\n2003"]

    style Simula67 fill:#ffcc80
    style DDD03 fill:#81c784
```

**Core insight:** OOP is about messages between objects, not about inheritance hierarchies.

### λ Functional Programming

The long journey from lambda calculus to mainstream.

```mermaid
flowchart LR
    Lambda36["Lambda Calculus\n1936"] --> Lisp58["Lisp\n1958"]
    Lisp58 --> ML78["ML\n1978"]
    ML78 --> Haskell90["Haskell\n1990"]
    Lisp58 --> Scheme75["Scheme\n1975"]
    Scheme75 --> SICP["SICP\n1985"]
    Haskell90 --> Clojure07["Clojure\n2007"]
    Lisp58 --> Clojure07

    style Lambda36 fill:#c8e6c9
    style Clojure07 fill:#4caf50,color:#fff
```

**Core insight:** Pure functions and immutability make programs easier to reason about.

### 🌐 Distributed Systems

Understanding the limits and patterns of distributed computing.

```mermaid
flowchart LR
    CSP78["CSP\n1978"] --> Erlang86["Erlang\n1986"]
    CAP00["CAP Theorem\n2000"] --> Beyond07["Beyond DT\n2007"]
    Beyond07 --> EventSourcing["Event Sourcing\n2010s"]
    Erlang86 --> ActorModel["Actor Model\nmainstream"]
    CAP00 --> NoSQL["NoSQL Movement\n2009+"]

    style CAP00 fill:#f8bbd9
    style EventSourcing fill:#ce93d8
```

**Core insight:** Distributed transactions don't scale; design around eventual consistency.

### 📋 Process & Practices

How we work together on software.

```mermaid
flowchart LR
    MMM75["Mythical Man-Month\n1975"] --> XP96["XP\n1996"]
    XP96 --> Agile01["Agile Manifesto\n2001"]
    XP96 --> TDD["TDD\n2003"]
    Agile01 --> Scrum["Scrum mainstream"]
    Agile01 --> DevOps09["DevOps\n2009"]
    DevOps09 --> TT19["Team Topologies\n2019"]

    style MMM75 fill:#b2dfdb
    style TT19 fill:#4db6ac
```

**Core insight:** Small teams, short iterations, continuous feedback.

### 👁 Computer Vision

From handcrafted features to unified real-time deep detectors.

```mermaid
flowchart LR
    Haar01["Haar Cascades\n2001"] --> HOG05["HOG\n2005"]
    HOG05 --> AlexNet12["AlexNet\n2012"]
    AlexNet12 --> RCNN14["R-CNN\n2014"]
    RCNN14 --> FasterRCNN15["Faster R-CNN\n2015"]
    FasterRCNN15 --> YOLO16["YOLO\n2016"]
    FasterRCNN15 --> SSD16["SSD\n2016"]

    style Haar01 fill:#fff8e1
    style YOLO16 fill:#ffcc80
```

**Core insight:** Detection moved from slow pipelines of handcrafted features and region proposals to single-pass learned regressions, enabling real-time perception.

## See Also

- [Master Timeline](./master-timeline.md)
- [Languages Genealogy](./languages-genealogy.md)
- [Paradigms Map](./paradigms-map.md)
- [Computer Vision](../topics/computer-vision/index.md)
