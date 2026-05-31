# Software Engineering Atlas

A navigable knowledge map of software engineering — ideas, authors, works,
languages, paradigms, architecture, and practices.

---

## Contents

- [Maps](maps/index.md) — visual timelines and relationship diagrams
- [Topics](topics/index.md) — knowledge organized by domain
- [Languages](languages/index.md) — programming language profiles
- [Authors](authors/index.md) — biography cards of key contributors
- [Works](works/index.md) — papers, books, and talks
- [Reading Paths](reading-paths/index.md) — guided study sequences

---

## Maps

Maps give the broad picture of how ideas, people, and movements relate to each other.
They are the fastest entry point if you want orientation before diving into individual pages.

The atlas includes several types of maps:

- **Overview lineage graph** — four major tracks (architecture, design/OOP/FP/types, process, distributed systems) shown as a connected graph with cross-track edges
- **Timeline maps** — chronological view of key milestones grouped by era and lineage
- **Ideas evolution** — how specific concepts developed and branched over time
- **Languages genealogy** — how programming languages influenced each other
- **Paradigms map** — relationships between programming paradigms
- **Concurrency and process maps** — focused views on specific tracks

Maps are not strict taxonomies. They are navigational tools that show likely connections, historical sequence, and conceptual proximity.

---

## Topics

Topic pages collect related ideas into one domain. They are meant for focused study without losing the wider context of the atlas. Topics are organized into four groups that reflect how knowledge clusters in practice.

---

### Concepts & Design

How we think, design, and reason about code.

**Paradigms** covers the fundamental models of computation: imperative versus declarative, procedural, object-oriented, functional, logic, and concurrent. It explains what distinguishes each paradigm conceptually and historically, and how they overlap in modern languages.

**OOP and Design** covers object-oriented programming as a set of ideas rather than a language feature: encapsulation, message passing, responsibility assignment, the SOLID principles, design patterns, and refactoring. It spans from Simula and Smalltalk through the GoF book to modern interpretations.

**Type Systems** examines how types help us reason about programs — static vs. dynamic typing, nominal vs. structural systems, abstract data types, generics, type inference, and ownership types. It connects formal theory (Hindley–Milner, LSP) to practical language design.

**Functional Programming** covers the core ideas of pure functions, immutability, composition, higher-order functions, and algebraic data types. It traces the lineage from lambda calculus through ML and Haskell to modern functional idioms in languages like Clojure and F#.

**Concurrency** covers different models for handling parallelism and asynchrony: shared memory and threads, communicating sequential processes (CSP), the actor model, and async/await. It shows how design choices in this area reflect deeper trade-offs between safety, performance, and expressiveness.

---

### Architecture & Structure

How we organize systems and modules.

**Architecture and Modularity** traces how engineers learned to structure systems at scale — from Dijkstra's structured programming and Parnas's information hiding, through layered architectures and domain-driven design, to microservices and team topology thinking. The central theme is the management of complexity through boundaries and separation of concerns.

---

### Systems & Data

How we scale, distribute, and persist state.

**Distributed Systems** covers the fundamental challenges of systems that span multiple machines: logical time and causality, ACID transactions, the CAP theorem, consensus algorithms, consistency models, and event streaming. It traces the lineage from Lamport's early papers through modern cloud-era practice.

**Databases** covers how we store, structure, and query data — from Codd's relational model through SQL, NoSQL data models (document, graph, key-value), storage engines (B-trees, LSM-trees), transactions and isolation levels, indexing strategies, and the distributed database era (Dynamo, Spanner, NewSQL). It connects to distributed systems through replication and consensus, and to architecture through data patterns like CQRS and Event Sourcing.

---

### Process, Delivery & Tools

How teams organize, ship, and operate software.

**Process** covers how teams organize work and ship software: agile methods, extreme programming, continuous integration and delivery, DevOps, SRE, and the DORA metrics for measuring delivery performance. It connects practices to the ideas and people behind them.

**Testing** covers how we gain confidence that software does what it should: TDD, the testing pyramid, test doubles, property-based testing, BDD, mutation testing, fuzzing, contract testing, and snapshot/approval testing. It also traces the deeper theoretical thread from Hoare's axiomatic basis and Dijkstra's critique of testing to modern property-based approaches.

**Build Systems** covers the tools that turn source code and declarations into runnable, testable, distributable artifacts — Make, CMake, Maven, Gradle, sbt, npm, Cargo, and Bazel. The chapter traces how build tools evolved from Make's timestamp-based dependency graph (1976) through convention-driven Maven (2004) and incremental Gradle (2007) to hermetic, polyglot, remote-cached Bazel (2015), and gives per-tool guides covering project structure, lifecycles, dependency models, and common patterns.

**Version Control** traces the evolution of how teams manage change over time — from early file-locking systems (SCCS, RCS) through centralized models (CVS, Subversion) to distributed workflows (Git, Mercurial). It covers the Git object model, branching strategies (Git Flow, GitHub Flow, trunk-based development), modern practices like commit hygiene and pull requests, and trade-offs between monorepo and polyrepo approaches.

**Containers and Orchestration** covers how applications are packaged, distributed, and run at scale on shared infrastructure — from early Linux primitives like cgroups and namespaces, through Docker's portable images in 2013 and Kubernetes in 2014, to OCI standards and modern runtimes such as containerd, Podman, and CRI-O. It is the operational substrate that ties together CI/CD pipelines, microservice architectures, and the DevOps and SRE practices built around them.

**Developer Tools** covers the software developers use every day to write, test, and interact with code — from text editors and IDEs through HTTP clients and API testing tools, terminal multiplexers and shells, to debuggers and profilers. These tools shape developer productivity and workflow ergonomics as much as language choice or architecture decisions.

---

### Machine Learning & LLMs

How learned models are built, understood, and integrated into software systems.

**Large Language Models** covers transformer architecture, tokenization, pretraining, and scaling laws — the engineering foundations that explain how LLMs work. It then moves to the practitioner layer: prompting strategies, retrieval-augmented generation (RAG), agents and tool use, integration patterns, evaluation, and the safety and alignment challenges that shape how these systems are built and deployed. The angle throughout is that of a software engineer building with or alongside LLMs, not an ML researcher training them from scratch.

---

Many ideas appear across multiple topics. **Modularity** touches architecture, design, and functional programming. **Types** connect to design, FP, and languages. **Testing** relates to process, design, and architecture. **Data** connects databases, distributed systems, and architecture. **LLMs** connect to architecture through RAG and agent patterns, to developer tools through AI-assisted coding, and to distributed systems through the infrastructure needed to serve models at scale. And **simplicity** is a recurring theme from Dijkstra to Hickey.

---

## Languages

Language pages describe programming languages as concrete embodiments of ideas. Each page connects the language to its historical context, its design model, and the concepts it makes visible or natural.

The atlas covers languages across a wide range of paradigms and eras — from Lisp (1958) and Simula (1967) to TypeScript (2012) and Rust (2010). The goal is not to teach syntax, but to show what each language represents as a vehicle for ideas: what it enables, what it emphasizes, and what trade-offs its designers made.

The companion code examples directory contains runnable programs in each language, organized by topic so that the same idea can be compared across different languages side by side.

---

## Authors

Author pages bring the people behind the ideas into view. Each page connects a person to the concepts they introduced or shaped, the historical moment they worked in, and the works they produced.

The atlas treats authors not as isolated geniuses but as participants in ongoing conversations. Many ideas in the atlas were developed collaboratively, refined by successors, or arrived at independently by multiple people. Author pages try to capture these connections honestly, including the intellectual debts and mutual influences that shaped the field.

The author index includes biography cards for over sixty contributors, spanning the full chronological range of the atlas — from Alonzo Church and Alan Turing in the 1930s to practitioners still active today.

---

## Works

Works pages cover the primary sources behind the ideas in the atlas: papers, books, and talks that introduced or formalized important concepts.

**Papers** include foundational texts such as Dijkstra's Go To Considered Harmful, Parnas's modules paper, Hoare's CSP, Lamport's logical clocks, Liskov and Wing's behavioral subtyping, Vaswani et al.'s Attention Is All You Need, and the GPT-3 and chain-of-thought prompting papers. These are the original arguments behind ideas that are now treated as common knowledge.

**Books** range from The Mythical Man-Month and Design Patterns through Domain-Driven Design and Continuous Delivery to Designing Data-Intensive Applications. Each book page situates the work in its historical moment, summarizes its core contribution, and connects it to related ideas in the atlas.

**Talks** cover a smaller but important set of recorded presentations — including Rich Hickey's Simple Made Easy and Gary Bernhardt's Boundaries — that shaped how practitioners think about design even when the ideas did not originate in written form.

Works pages are not substitutes for reading the originals. They are orientation guides that help you decide what to read and understand what you are reading when you do.

---

## Reading Paths

Reading paths provide guided sequences through the atlas for those who prefer a structured route over free exploration. Each path has a clear starting point, a direction of travel, and a destination.

**Architecture path** runs from Dijkstra's structured programming through information hiding, layered systems, hexagonal architecture, domain-driven design, and microservices, ending with Team Topologies. It traces how the field's understanding of system structure evolved across five decades.

**OOP and Design path** starts with Simula and Smalltalk, moves through the Gang of Four patterns, SOLID principles, refactoring practice, and responsibility-driven design, and ends with practical modern interpretations. It is a path through the ideas that shaped most working codebases today.

**Functional Programming path** starts with lambda calculus and Lisp, moves through ML and Haskell, covers the core ideas of purity and composition, and ends with applied functional design in languages like Clojure and F#. It is suitable both for those new to FP and for those wanting to understand its theoretical foundations.

**Testing and Delivery path** starts with extreme programming and test-driven development, moves through continuous integration, continuous delivery, and DevOps, and ends with the DORA metrics and evidence-based delivery improvement. It covers the practices that connect writing code to shipping working software reliably.

**Distributed Systems path** starts with Lamport's logical clocks, moves through ACID, the CAP theorem, Paxos, Raft, and consistency models, and ends with the synthesis in Designing Data-Intensive Applications. It is a path through the most mathematically demanding part of the atlas, but also one of the most practically relevant for modern systems work.

**Databases path** starts with Codd's relational model, moves through storage engines, transactions, and indexing, and ends with distributed databases and the synthesis in Designing Data-Intensive Applications.

**Large Language Models path** starts with the transformer paper and the attention mechanism, moves through pretraining, scaling laws, and instruction tuning, then covers prompting strategies, retrieval-augmented generation, and agent patterns, and ends with evaluation, safety, and practical integration patterns for software engineers. It is a path from understanding how LLMs work to knowing how to build with them responsibly.

---

<p align="center">
  <i>
    "The purpose of abstraction is not to be vague,
    but to create a new semantic level in which one can be
    absolutely precise."
  </i>
  <br/>— Edsger Dijkstra
</p>
