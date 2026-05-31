# Topics

Knowledge organized by domain. Each topic traces how ideas evolved from foundational concepts to modern practice.

---

## 🧩 Concepts & Design

How we think, design, and reason about code.

| # | Topic                                                         | Key ideas                                                              | Details                            |
|---|---------------------------------------------------------------|------------------------------------------------------------------------|------------------------------------|
| 1 | [**Paradigms**](paradigms/index.md)                           | Imperative↔Declarative, Procedural/OOP/FP/Logic, Sequential/Concurrent | How we think about computation     |
| 2 | [**OOP & Design**](design/index.md)                           | SOLID, GoF patterns, Refactoring, DbC                                  | How we design components           |
| 3 | [**Type Systems**](types/index.md)                            | Static/dynamic, nominal/structural, ADTs, generics                     | How types help us reason           |
| 4 | [**Functional Programming**](functional/index.md)             | Purity, immutability, composition, monads                              | How we avoid accidental complexity |
| 5 | [**Concurrency**](concurrency/index.md)                       | Threads, CSP, Actors, async/await                                      | How we handle parallelism          |

### Deep dives
- [Sync vs Async](concurrency/sync-async-axes.md) — axes of concurrent execution
- [Memoization](functional/memoization.md) — caching function results

---

## 🏗 Architecture & Structure

How we organize systems and modules.

| # | Topic                                                              | Key ideas                                  | Details                  |
|---|--------------------------------------------------------------------|--------------------------------------------|--------------------------|
| 6 | [**Architecture & Modularity**](architecture/index.md)             | Layered, Hexagonal, DDD, Microservices, C4 | How we structure systems |

### Communication patterns
- [Event-Driven Architecture](architecture/communication/event-driven-architecture.md)
- [Message-Driven Architecture](architecture/communication/message-driven-architecture.md)
- [Publish-Subscribe](architecture/communication/publish-subscribe.md)
- [Service-Oriented Architecture](architecture/communication/service-oriented-architecture.md)

---

## 🌐 Systems & Data

How we scale, distribute, and persist state.

| # | Topic                                                       | Key ideas                                                  | Details                      |
|---|-------------------------------------------------------------|------------------------------------------------------------|------------------------------|
| 7 | [**Distributed Systems**](distributed/index.md)             | Clocks, CAP, consensus, consistency, streaming             | How we scale across machines |
| 8 | [**Databases**](databases/index.md)                         | Relational model, transactions, NoSQL, OLTP/OLAP, indexing | How we store and query data  |

### Deep dives
- [Idempotency](distributed/idempotency.md) — safe retries in distributed systems

---

## 🧪 Process, Delivery & Tools

How teams organize, ship, and operate software.

| #  | Topic                                                             | Key ideas                                              | Details                           |
|----|-------------------------------------------------------------------|--------------------------------------------------------|-----------------------------------|
| 9  | [**Process**](process/index.md)                                   | Agile, XP, CI/CD, DevOps, SRE                          | How we build and ship             |
| 10 | [**Testing**](testing/index.md)                                   | TDD, Pyramid, PBT, BDD, mutation, fuzzing, contracts   | How we verify and harden code     |
| 11 | [**Build Systems**](process/build-systems/index.md)               | Make, Maven, Gradle, Bazel, npm, Cargo                 | How we turn source into artifacts |
| 12 | [**Version Control**](vcs/index.md)                               | RCS → CVS → SVN → Git, branching strategies, monorepos | How we manage change over time    |
| 13 | [**Containers & Orchestration**](containers/index.md)             | Docker, Podman, Kubernetes, OCI, runtimes              | How we package and run workloads  |
| 14 | [**Developer Tools**](dev-tools/index.md)                         | IDE, HTTP clients, terminal, debuggers                 | Tools developers use every day    |

### Build Systems deep dives
- [Overview](process/build-systems/index.md)
- [Make](process/build-systems/make.md)
- [Maven](process/build-systems/maven.md)
- [Gradle](process/build-systems/gradle.md)
- [npm](process/build-systems/npm.md)
- [Cargo](process/build-systems/cargo.md)
- [CMake](process/build-systems/cmake.md)
- [Bazel](process/build-systems/bazel.md)
- [sbt](process/build-systems/sbt.md)

### CI/CD pipelines
- [Overview](process/ci-cd/index.md)
- [Jenkins](process/ci-cd/jenkins.md)
- [GitLab CI](process/ci-cd/gitlab-ci.md)
- [GitHub Actions](process/ci-cd/github-actions.md)
- [CircleCI](process/ci-cd/circleci.md)
- [Azure DevOps](process/ci-cd/azure-devops.md)
- [Bitbucket Pipelines](process/ci-cd/bitbucket-pipelines.md)

---

## 🤖 Machine Learning & LLMs

How learned models are built, understood, and integrated into software systems.

| #  | Topic                                            | Key ideas                                                           | Details                                  |
|----|--------------------------------------------------|---------------------------------------------------------------------|------------------------------------------|
| 15 | [**Large Language Models**](llm/index.md)        | Transformers, prompting, RAG, agents, evaluation, safety            | How we build and integrate LLM systems   |

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
- **[Testing](testing/index.md)** relates to process, design, and architecture
- **Data** connects databases (models, transactions), distributed systems (replication, sharding), and architecture (CQRS, Event Sourcing)
- **LLMs** connect to architecture through RAG and agent patterns, to developer tools through AI-assisted coding, and to distributed systems through the infrastructure needed to serve models at scale
- **Simplicity** is a recurring theme from Dijkstra to Hickey