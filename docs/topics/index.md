# Topics

Knowledge organized by domain. Each topic traces how ideas evolved from foundational concepts to modern practice.

---

## 🧠 Concepts

Fundamental ideas and abstractions that shape how we think about software.

| # | Topic                                                         | Key ideas                                                              | Details                            |
|---|---------------------------------------------------------------|------------------------------------------------------------------------|------------------------------------|
| 1 | [**Paradigms**](concepts/paradigms/index.md)                  | Imperative↔Declarative, Procedural/OOP/FP/Logic, Sequential/Concurrent | How we think about computation     |
| 2 | [**Concurrency**](concepts/concurrency/index.md)              | Threads, CSP, Actors, async/await                                      | How we handle parallelism          |
| 3 | [**Type Systems**](concepts/types/index.md)                   | Static/dynamic, nominal/structural, ADTs, generics                     | How types help us reason           |
| 4 | [**Functional Programming**](concepts/functional/index.md)    | Purity, immutability, composition, monads                              | How we avoid accidental complexity |

### Deep dives
- [Sync vs Async](concepts/concurrency/sync-async-axes.md) — axes of concurrent execution
- [Memoization](concepts/functional/memoization.md) — caching function results

---

## 🏗 Architecture & Structure

How we organize systems and modules.

| # | Topic                                                              | Key ideas                                  | Details                  |
|---|--------------------------------------------------------------------|--------------------------------------------|--------------------------|
| 5 | [**Architecture & Modularity**](architecture/index.md)             | Layered, Hexagonal, DDD, Microservices, C4 | How we structure systems |

### Communication patterns
- [Event-Driven Architecture](architecture/communication/event-driven-architecture.md)
- [Message-Driven Architecture](architecture/communication/message-driven-architecture.md)
- [Publish-Subscribe](architecture/communication/publish-subscribe.md)
- [Service-Oriented Architecture](architecture/communication/service-oriented-architecture.md)

---

## 🎨 Design

How we design components and write code.

| # | Topic                                              | Key ideas                        | Details                  |
|---|----------------------------------------------------|----------------------------------|--------------------------|
| 6 | [**OOP & Design**](design/index.md)                | SOLID, GoF patterns, Refactoring | How we design components |

---

## ⚙️ Engineering

Verifiable principles for building and operating software systems.

| # | Topic                                                                   | Key ideas                                                | Details                         |
|---|-------------------------------------------------------------------------|----------------------------------------------------------|---------------------------------|
| 7 | [**Testing**](testing/index.md)                                         | TDD, Pyramid, PBT, BDD, mutation, fuzzing, contracts     | How we verify code behavior     |
| 8 | [**Verifiable Engineering**](engineering/verifiable-engineering.md)     | Three questions, falsifiability, system vs event         | How we know what we claim       |

---

## 🌐 Systems & Data

How we scale, distribute, and persist state.

| # | Topic                                                       | Key ideas                                                  | Details                      |
|---|-------------------------------------------------------------|------------------------------------------------------------|------------------------------|
| 9 | [**Distributed Systems**](distributed/index.md)             | Clocks, CAP, consensus, consistency, streaming             | How we scale across machines |
| 10 | [**Databases**](databases/index.md)                         | Relational model, transactions, NoSQL, OLTP/OLAP, indexing | How we store and query data  |

### Deep dives
- [Idempotency](distributed/idempotency.md) — safe retries in distributed systems

---

## 🧪 Process & Methodologies

How teams organize to build software.

| #  | Topic                                                       | Key ideas                                            | Details               |
|----|-------------------------------------------------------------|------------------------------------------------------|-----------------------|
| 11 | [**Process**](process/index.md)                             | Agile, XP, Scrum, Kanban, DevOps, SRE                | How we organize work  |

### CI/CD pipelines
- [Overview](process/ci-cd/index.md)
- [Jenkins](process/ci-cd/jenkins.md)
- [GitLab CI](process/ci-cd/gitlab-ci.md)
- [GitHub Actions](process/ci-cd/github-actions.md)
- [CircleCI](process/ci-cd/circleci.md)
- [Azure DevOps](process/ci-cd/azure-devops.md)
- [Bitbucket Pipelines](process/ci-cd/bitbucket-pipelines.md)

---

## 🛠 Tools

The instruments we use to build, deploy, and operate software.

| #  | Topic                                                             | Key ideas                                              | Details                           |
|----|-------------------------------------------------------------------|--------------------------------------------------------|-----------------------------------|
| 12 | [**Build Systems**](tools/build-systems/index.md)                 | Make, Maven, Gradle, Bazel, npm, Cargo                 | How we turn source into artifacts |
| 13 | [**Containers & Orchestration**](tools/containers/index.md)       | Docker, Podman, Kubernetes, OCI, runtimes              | How we package and run workloads  |
| 14 | [**Developer Tools**](tools/dev-tools/index.md)                   | IDE, HTTP clients, terminal, debuggers                 | Tools developers use every day    |
| 15 | [**Version Control**](vcs/index.md)                               | RCS → CVS → SVN → Git, branching strategies, monorepos | How we manage change over time    |

### Build Systems deep dives
- [Overview](tools/build-systems/index.md)
- [Make](tools/build-systems/make.md)
- [Maven](tools/build-systems/maven.md)
- [Gradle](tools/build-systems/gradle.md)
- [npm](tools/build-systems/npm.md)
- [Cargo](tools/build-systems/cargo.md)
- [CMake](tools/build-systems/cmake.md)
- [Bazel](tools/build-systems/bazel.md)
- [sbt](tools/build-systems/sbt.md)

---

## 🤖 Machine Learning & LLMs

How learned models are built, understood, and integrated into software systems.

| #  | Topic                                            | Key ideas                                                           | Details                                  |
|----|--------------------------------------------------|---------------------------------------------------------------------|------------------------------------------|
| 16 | [**Large Language Models**](llm/index.md)        | Transformers, prompting, RAG, agents, evaluation, safety            | How we build and integrate LLM systems   |

### Deep dives
- [Transformer Architecture](llm/transformer.md) — attention, tokens, and how LLMs process text
- [Prompting Strategies](llm/prompting.md) — zero-shot, few-shot, chain-of-thought, structured output
- [RAG](llm/rag.md) — retrieval-augmented generation patterns
- [Agents & Tool Use](llm/agents.md) — LLMs that plan, call tools, and act
- [Evaluation](llm/evaluation.md) — benchmarks, human feedback, and LLM-as-judge
- [Safety & Alignment](llm/safety.md) — hallucination, bias, RLHF, and responsible deployment

---

## Cross-cutting Themes

Many ideas appear across multiple topics:

- **Modularity** touches architecture, design, and FP
- **Types** connect to design, FP, and languages
- **[Testing](testing/index.md)** relates to process, design, and engineering
- **Data** connects databases (models, transactions), distributed systems (replication, sharding), and architecture (CQRS, Event Sourcing)
- **LLMs** connect to architecture through RAG and agent patterns, to developer tools through AI-assisted coding, and to distributed systems through the infrastructure needed to serve models at scale
- **Simplicity** is a recurring theme from Dijkstra to Hickey
