# Tools

The instruments we use to build, deploy, and operate software.

Tools are **means, not ends**. They embody engineering decisions and make certain practices easy or hard — but they are distinct from the concepts, architectures, and methodologies that guide *why* we build what we build.

---

## What belongs here

A tool belongs in this section when it is:

- A **specific technology** or product (Docker, Kubernetes, Git)
- Used to **automate** or **support** a development or operational task
- Replaceable — another tool could serve the same purpose

Tools are **context-dependent** — they evolve, are deprecated, and are replaced. The principles behind them (build reproducibility, version control, container isolation) are more durable and belong in [Process](../process/index.md) or [Engineering](../engineering/index.md).

---

## Contents

| # | Topic | Key ideas | What it answers |
|---|-------|-----------|-----------------|
| 1 | [**Build Systems**](build-systems/index.md) | Make, Maven, Gradle, Bazel, npm, Cargo | How do we turn source into artifacts? |
| 2 | [**Containers & Orchestration**](containers/index.md) | Docker, Podman, Kubernetes, OCI, runtimes | How do we package and run workloads? |
| 3 | [**Developer Tools**](dev-tools/index.md) | IDE, HTTP clients, terminal, debuggers | What do developers use every day? |
| 4 | [**Version Control & Git**](../vcs/index.md) | RCS → CVS → SVN → Git, branching strategies | How do we manage change over time? |

### CI/CD

CI/CD pipelines are covered in [Process → CI/CD Providers](../process/ci-cd/index.md), as they sit at the intersection of tooling and team workflow.

---

## Build Systems

How source code becomes a runnable artifact.

| Tool | Year | Ecosystem | Config file | Best for |
|------|------|-----------|-------------|----------|
| **Make** | 1976 | Native (C/C++) | `Makefile` | Small native projects, ad-hoc task runners |
| **CMake** | 2000 | Native (C/C++) | `CMakeLists.txt` | Cross-platform native projects |
| **Maven** | 2004 | JVM | `pom.xml` | Conventional Java/JVM projects |
| **Gradle** | 2007 | JVM, polyglot | `build.gradle(.kts)` | Large JVM / Android, incremental builds |
| **npm** | 2010 | JavaScript / TypeScript | `package.json` | Node apps and libraries |
| **Cargo** | 2012 | Rust | `Cargo.toml` | Anything Rust |
| **Bazel** | 2015 | Polyglot | `BUILD.bazel` | Monorepos, polyglot, large scale |

→ [Build Systems deep dives](build-systems/index.md)

---

## Containers & Orchestration

| Technology | Role | Key concept |
|-----------|------|-------------|
| **Docker** | Container runtime & image format | Immutable application packages |
| **Podman** | Daemonless container engine | Rootless, OCI-compatible |
| **containerd** | Low-level container runtime | Used by Docker and Kubernetes |
| **Kubernetes** | Container orchestration | Declarative desired state |
| **Helm** | Package manager for K8s | Templated deployments |
| **Nomad** | Lightweight orchestration | Multi-workload (containers, binaries, Java) |

→ [Containers & Orchestration deep dives](containers/index.md)

---

## Developer Tools

| Category | Tools | Purpose |
|----------|-------|---------|
| **IDE** | VS Code, IntelliJ, Vim, Emacs | Code editing, navigation, refactoring |
| **HTTP Clients** | curl, HTTPie, Postman, Insomnia | API testing and exploration |
| **Terminals** | tmux, zsh, fish, PowerShell | Shell environment and multiplexing |
| **Debuggers** | gdb, lldb, Chrome DevTools, IDE debuggers | Runtime inspection |

→ [Developer Tools deep dives](dev-tools/index.md)

---

## Relationship to other sections

| This section | Connected to | How |
|--------------|--------------|-----|
| Build Systems | [Process](../process/index.md) | CI/CD pipelines, team workflow |
| Containers | [Architecture](../architecture/index.md) | Deployment patterns, microservices |
| Dev Tools | [Languages](../../languages/index.md) | Language-specific tooling |
| Version Control | [Process](../process/index.md) | Code review, branching strategies |

---

## Further Reading

- [Build Systems](build-systems/index.md) — detailed guides per tool
- [Containers](containers/index.md) — Docker, Kubernetes, and ecosystem
- [Developer Tools](dev-tools/index.md) — daily-use tools
- [Version Control](../vcs/index.md) — Git and branching strategies
