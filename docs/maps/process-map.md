# Process Map

How software development processes evolved.

## Process Evolution

```mermaid
flowchart TD
    subgraph Traditional["📋 Traditional: 1970s-1990s"]
        Waterfall["Waterfall<br/>1970"]
        Spiral["Spiral Model<br/>Boehm, 1986"]
        RUP["RUP<br/>1998"]
    end

    subgraph Agile["🔄 Agile: 1990s-2000s"]
        XP["Extreme Programming<br/>Beck, 1996"]
        Scrum["Scrum<br/>1995"]
        Agile01["Agile Manifesto<br/>2001"]
        Kanban["Kanban<br/>2004"]
    end

    subgraph Modern["🚀 Modern: 2010s+"]
        LeanStartup["Lean Startup<br/>Ries, 2011"]
        DevOps["DevOps<br/>2009"]
        Continuous["Continuous Delivery<br/>2010"]
        Docker["Docker / Containers<br/>Hykes, 2013"]
        K8s["Kubernetes<br/>Google, 2014"]
        TeamTopo["Team Topologies<br/>2019"]
    end

    Waterfall --> Spiral
    Spiral --> RUP
    Waterfall -->|"Reaction against"| XP
    XP --> Agile01
    Scrum --> Agile01
    Agile01 --> Kanban
    Agile01 --> DevOps
    DevOps --> Continuous
    Continuous --> Docker
    Docker --> K8s
    K8s --> TeamTopo
    XP --> LeanStartup

    style Traditional fill:#ffcdd2
    style Agile fill:#c8e6c9
    style Modern fill:#bbdefb
```

## Timeline

```mermaid
timeline
    title Evolution of Software Development Processes

    1970 : Waterfall model formalized (Royce)
    1986 : Spiral Model (Boehm)
    1995 : Scrum formalized (Schwaber, Sutherland)
    1996 : Extreme Programming (Beck)
    1998 : Rational Unified Process
    2001 : Agile Manifesto signed
    2003 : Lean Software Development (Poppendieck)
    2004 : Kanban for software (Anderson)
    2009 : DevOps movement begins
    2010 : Continuous Delivery book (Humble, Farley)
    2011 : Lean Startup (Ries)
    2013 : Docker released (Hykes)
    2014 : Kubernetes 1.0 (Google)
    2018 : Accelerate (Forsgren, Humble, Kim)
    2019 : Team Topologies (Skelton, Pais)
```

## Build Tools Evolution

Build systems evolved alongside the methodologies above. Each generation
solved specific pain points: dependency resolution, cross-platform
config, polyglot monorepos, hermetic reproducibility.

```mermaid
flowchart TD
    subgraph Native["🛠 Native (C / C++)"]
        Make["Make<br/>Stallman et al, 1976<br/><i>dependency graph,<br/>timestamps</i>"]
        CMake["CMake<br/>Kitware, 2000<br/><i>cross-platform<br/>generator</i>"]
        Ninja["Ninja<br/>Martin, 2010<br/><i>minimal, fast<br/>backend</i>"]
    end

    subgraph JVM["☕ JVM"]
        Ant["Ant<br/>2000<br/><i>XML build<br/>for Java</i>"]
        Maven["Maven<br/>2004<br/><i>convention over<br/>configuration</i>"]
        Gradle["Gradle<br/>2007<br/><i>Groovy/Kotlin DSL,<br/>incremental</i>"]
        Sbt["sbt<br/>2008<br/><i>Scala REPL,<br/>Zinc compiler</i>"]
    end

    subgraph Lang["📦 Per-language"]
        Npm["npm<br/>2010<br/><i>JS registry,<br/>lockfile</i>"]
        Cargo["Cargo<br/>2012<br/><i>Rust toolchain,<br/>lockfile by default</i>"]
    end

    subgraph Hermetic["🔒 Hermetic / monorepo"]
        Bazel["Bazel<br/>Google, 2015<br/><i>polyglot, hermetic,<br/>remote cache</i>"]
    end

    Make --> CMake
    CMake --> Ninja
    CMake --> Bazel
    Ant --> Maven
    Maven --> Gradle
    Maven -.->|"repos, coords"| Sbt
    Maven --> Bazel
    Make --> Bazel
    Npm -->|"lockfile idea"| Cargo

    style Make fill:#ffcdd2
    style CMake fill:#ffcdd2
    style Ninja fill:#ffcdd2
    style Ant fill:#bbdefb
    style Maven fill:#bbdefb
    style Gradle fill:#bbdefb
    style Sbt fill:#bbdefb
    style Npm fill:#fff59d
    style Cargo fill:#fff59d
    style Bazel fill:#a5d6a7
```

| Year | Tool | Ecosystem | Key innovation |
|------|------|-----------|---------------|
| 1976 | **Make** | Native | Dependency graph + timestamp-based rebuilds |
| 2000 | **Ant** | JVM | XML build script, Java-aware (predecessor of Maven) |
| 2000 | **CMake** | Native | Cross-platform meta-build, generates Make/Ninja/MSBuild |
| 2004 | **Maven** | JVM | Convention over configuration; central repository |
| 2007 | **Gradle** | JVM, polyglot | Groovy/Kotlin DSL, task graph, incremental builds |
| 2008 | **sbt** | Scala | Interactive shell, Zinc incremental Scala compiler |
| 2010 | **Ninja** | Native | Minimal, fast build executor (CMake/Bazel backend) |
| 2010 | **npm** | JavaScript | Public registry + lockfile for transitive deps |
| 2012 | **Cargo** | Rust | First-class package manager bundled with the toolchain |
| 2015 | **Bazel** | Polyglot | Hermetic builds + remote cache + remote execution |

→ See [Build Systems chapter](../topics/process/build-systems/index.md)
for detailed per-tool guides.

## Key Methodologies

### 📋 Waterfall (1970)

```mermaid
flowchart LR
    Req["Requirements"] --> Design["Design"]
    Design --> Impl["Implementation"]
    Impl --> Test["Testing"]
    Test --> Deploy["Deployment"]
    Deploy --> Maint["Maintenance"]
```

| Aspect | Description |
|--------|-------------|
| **Principle** | Sequential phases, complete each before next |
| **Documentation** | Heavy, upfront |
| **Change** | Expensive, discouraged |
| **When to use** | Regulatory requirements, well-understood domains |

### 🔄 Extreme Programming (1996)

| Practice | Description |
|----------|-------------|
| **Pair Programming** | Two developers, one keyboard |
| **TDD** | Write tests first |
| **Continuous Integration** | Integrate frequently |
| **Refactoring** | Improve code constantly |
| **Simple Design** | YAGNI — You Aren't Gonna Need It |
| **Collective Ownership** | Anyone can change any code |

**Key insight:** Embrace change through technical practices.

### 🏃 Scrum (1995)

```mermaid
flowchart LR
    Backlog["Product<br/>Backlog"] --> Sprint["Sprint<br/>(2-4 weeks)"]
    Sprint --> Review["Sprint<br/>Review"]
    Review --> Retro["Sprint<br/>Retrospective"]
    Retro --> Sprint

    style Sprint fill:#a5d6a7
```

| Role | Responsibility |
|------|----------------|
| **Product Owner** | What to build, prioritization |
| **Scrum Master** | Process facilitation, impediment removal |
| **Development Team** | Self-organizing delivery |

**Ceremonies:** Sprint Planning, Daily Standup, Sprint Review, Retrospective.

### 📊 Kanban (2004)

```mermaid
flowchart LR
    subgraph Board["Kanban Board"]
        Todo["To Do"] --> InProg["In Progress<br/>(WIP: 3)"]
        InProg --> Review["Review<br/>(WIP: 2)"]
        Review --> Done["Done"]
    end
```

| Principle | Description |
|-----------|-------------|
| **Visualize work** | Make work visible on board |
| **Limit WIP** | Limit work in progress |
| **Manage flow** | Optimize for throughput |
| **Make policies explicit** | Clear definition of done |
| **Improve collaboratively** | Evolve based on feedback |

**Key insight:** Stop starting, start finishing.

### 🔁 DevOps (2009)

```mermaid
flowchart LR
    Plan --> Code --> Build --> Test --> Release --> Deploy --> Operate --> Monitor
    Monitor --> Plan

    style Plan fill:#bbdefb
    style Deploy fill:#c8e6c9
    style Monitor fill:#fff9c4
```

| Pillar | Description |
|--------|-------------|
| **Culture** | Collaboration between Dev and Ops |
| **Automation** | CI/CD pipelines, infrastructure as code |
| **Measurement** | Metrics, monitoring, feedback loops |
| **Sharing** | Shared responsibility, knowledge transfer |

**Key metrics (DORA):**
- Deployment frequency
- Lead time for changes
- Change failure rate
- Time to restore service

### 📐 Team Topologies (2019)

```mermaid
flowchart TD
    subgraph Teams["Four Team Types"]
        Stream["Stream-aligned<br/>🎯 Business value"]
        Enabling["Enabling<br/>🎓 Capability building"]
        Complicated["Complicated Subsystem<br/>🧠 Deep expertise"]
        Platform["Platform<br/>🛠 Self-service"]
    end

    Platform --> Stream
    Enabling --> Stream
    Complicated --> Stream

    style Stream fill:#a5d6a7
    style Platform fill:#90caf9
```

| Team Type | Purpose | Interaction |
|-----------|---------|-------------|
| **Stream-aligned** | Deliver value for a flow of work | Primary team type |
| **Enabling** | Help stream teams overcome obstacles | Temporary collaboration |
| **Complicated Subsystem** | Own complex components | X-as-a-Service |
| **Platform** | Provide self-service capabilities | X-as-a-Service |

**Key insight:** Design team structures for fast flow, not for org charts.

## Process Selection Guide

| Context | Recommended | Why |
|---------|-------------|-----|
| Startup, uncertain requirements | Kanban + XP practices | Flexibility, speed |
| Established product team | Scrum | Rhythm, predictability |
| Regulated industry | Waterfall + Agile elements | Documentation + adaptability |
| Platform team | DevOps + SRE | Automation, reliability |
| Large organization | Team Topologies | Flow optimization |

## The Agile Manifesto (2001)

> **Individuals and interactions** over processes and tools
>
> **Working software** over comprehensive documentation
>
> **Customer collaboration** over contract negotiation
>
> **Responding to change** over following a plan

_While there is value in the items on the right, we value the items on the left more._

## See Also

- [Team Topologies Authors](../authors/matthew-skelton.md)
- [Brooks — Mythical Man-Month](../works/books/brooks-1975-mmm.md)
- [Build Systems](../topics/process/build-systems/index.md)
- [Containers & Orchestration](../topics/containers/index.md)
- [Architecture Map](./architecture-map.md)
