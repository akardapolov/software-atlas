# Build Systems

Overview of build systems — the tools that take source code and a set of
declarations and produce a runnable, testable, distributable artifact.

## Contents

- [What Is a Build System?](#what-is-a-build-system)
- [Choosing a Build Tool](#choosing-a-build-tool)
- [Comparison](#comparison)
- [Core Concepts Across Tools](#core-concepts-across-tools)
- [Tools](#tools)
- [Adjacent Tools](#adjacent-tools)

---

## What Is a Build System?

A build system is a tool that:

1. **Declares** what your project consists of (sources, modules, dependencies)
2. **Resolves** external dependencies (libraries, plugins, transitive graphs)
3. **Executes** a graph of tasks (compile, test, package, publish) in the right order
4. **Produces** reproducible, distributable artifacts (JAR, binary, container, npm package)

The build definition lives in the repository — this is **build as code**:
configuration is versioned, reviewed, and evolved alongside application code.
Modern build tools also cache intermediate results, run only what's affected
by a change, and integrate with CI/CD providers and IDEs.

---

## Choosing a Build Tool

| Factor | Considerations |
|--------|---------------|
| **Language ecosystem** | Native fit for your language (Cargo for Rust, sbt for Scala) usually wins over a generic tool |
| **Convention vs flexibility** | Maven prescribes layout; Make/Gradle/Bazel let you define your own |
| **Incremental builds** | Tracking what changed and rebuilding only that — table stakes for fast feedback |
| **Hermeticity** | Are builds reproducible across machines? (Bazel yes, Make no) |
| **Polyglot support** | Single repository with multiple languages? Bazel and Gradle handle this; Maven and sbt don't |
| **IDE integration** | First-class import in IntelliJ / VS Code / Eclipse |
| **Remote cache / RBE** | Share build outputs across the team or CI fleet |
| **Wrapper distribution** | `mvnw` / `gradlew` / `cargo` install themselves; `make` and Bazel typically don't |
| **Lockfile** | `Cargo.lock`, `package-lock.json` pin transitive versions; Maven and Make don't lock by default |

---

## Comparison

Listed in chronological order — reading top to bottom is the evolution
of build tooling itself.

| Tool | Year | Primary ecosystem | Config file | Config language | Dependency model | Incremental / cached | Hermetic? | Best for |
|------|------|-------------------|-------------|-----------------|------------------|---------------------|-----------|----------|
| [Make](make.md) | 1976 | C / C++, generic | `Makefile` | Make DSL + shell | None (manual) | Timestamp-based | No | Small native projects, ad-hoc task runners |
| [CMake](cmake.md) | 2000 | C / C++ | `CMakeLists.txt` | CMake DSL | `find_package` / FetchContent | Via generator (Make/Ninja) | No | Cross-platform native projects |
| [Maven](maven.md) | 2004 | JVM (Java, Kotlin) | `pom.xml` | XML | Coordinates + Maven Central | Per-module | No | Conventional Java projects, enterprise |
| [Gradle](gradle.md) | 2007 | JVM, polyglot | `build.gradle(.kts)` | Groovy / Kotlin DSL | Coordinates + many repos | Task-graph + configuration cache | Partial | Large JVM / Android projects |
| [sbt](sbt.md) | 2008 | Scala | `build.sbt` | Scala DSL | Ivy / Maven repos | Incremental Scala compiler (Zinc) | No | Scala / Akka / Play projects |
| [npm](npm.md) | 2010 | JavaScript / TypeScript | `package.json` | JSON | npm registry + lockfile | Via `node_modules` linking | No | JS / TS apps, libraries |
| [Cargo](cargo.md) | 2012 | Rust | `Cargo.toml` | TOML | crates.io + lockfile | Per-crate fingerprint | No | Anything Rust |
| [Bazel](bazel.md) | 2015 | Polyglot | `BUILD.bazel` | Starlark (Python-like) | Workspace + repository rules | Per-action remote cache | Yes (by design) | Monorepos, polyglot, large scale |

---

## Core Concepts Across Tools

Every build tool uses different vocabulary, but the underlying concepts
overlap. Empty cells (`—`) mean the concept doesn't exist in that tool —
which itself tells you something about its model.

| Concept | Make | CMake | Maven | Gradle | sbt | npm | Cargo | Bazel |
|---------|------|-------|-------|--------|-----|-----|-------|-------|
| **Project / module unit** | Directory | `add_subdirectory` | Module (`pom.xml`) | Subproject | Project / sub-project | Workspace package | Crate | Package (`BUILD` file) |
| **Target / task / goal** | Target | Target | Goal / phase | Task | Task / setting | Script | Subcommand | Target / action |
| **Dependency declaration** | — (manual) | `target_link_libraries` | `<dependency>` | `dependencies { }` | `libraryDependencies` | `dependencies` in `package.json` | `[dependencies]` in `Cargo.toml` | `deps = [...]` |
| **Plugin / extension** | Include | Module / function | Maven plugin | Gradle plugin | sbt plugin | npm package | Cargo subcommand | Rule (Starlark) |
| **Lifecycle phase** | — | configure → generate → build | validate → compile → test → package → install → deploy | Configuration → Execution | update → compile → test → package | install → run scripts | check → build → test → release | analysis → execution |
| **Output artifact** | Binary / object file | Library / executable | JAR / WAR / POM | JAR / AAR / etc. | JAR | Tarball / dist | rlib / binary | Any (declared) |
| **Local cache** | — (relies on `make clean`) | Build directory | `~/.m2/repository` | `~/.gradle/caches` | `~/.ivy2`, `~/.sbt` | `node_modules`, `~/.npm` | `target/`, `~/.cargo/registry` | `~/.cache/bazel` |
| **Remote cache** | — | — | Repository manager (Nexus/Artifactory) | Build cache server | — | npm registry (artifacts only) | sccache (community) | Native (RBE) |
| **Lockfile** | — | — | — (versions in pom) | `gradle.lockfile` (opt-in) | `build.sbt` versions | `package-lock.json` / `pnpm-lock.yaml` | `Cargo.lock` | `MODULE.bazel.lock` |

---

## Tools

Grouped by ecosystem for navigation. The **chronological** order in the
table above shows how ideas propagated; the grouping below shows what
to reach for given your stack.

### JVM

- [Maven](maven.md) — convention over configuration, XML, Maven Central
- [Gradle](gradle.md) — Groovy / Kotlin DSL, task graph, incremental
- [sbt](sbt.md) — Scala-native, interactive REPL, Zinc compiler

### JavaScript / Node

- [npm](npm.md) — default registry and CLI; sections cover pnpm and yarn

### Native (C / C++)

- [Make](make.md) — the original; rules, targets, Makefiles
- [CMake](cmake.md) — meta-build / generator; cross-platform
- [Bazel](bazel.md) — also covers polyglot monorepos; listed here for native too

### Rust

- [Cargo](cargo.md) — built into the toolchain; crates.io

---

## Adjacent Tools

Mentioned for context — full pages not included.

| Tool | What it is | When you meet it |
|------|------------|------------------|
| **Ant** (2000) | XML build for Java, predecessor of Maven | Legacy Java codebases |
| **Ivy** | Dependency manager paired with Ant | Same |
| **Ninja** | Minimal, fast build executor — backend for CMake/Bazel | When `cmake -G Ninja` |
| **MSBuild** | Microsoft's build engine for .NET | Visual Studio, dotnet CLI |
| **Pants / Buck / Buck2** | Bazel-family monorepo build tools | Twitter / Meta / large polyglot repos |
| **Webpack / Vite / esbuild / Rollup** | JS *bundlers* — package modules for the browser. Not build systems in the sense above; they run *inside* a build (often as an npm script) | Frontend projects |
| **Make wrappers** (`mvnw`, `gradlew`, `cargo`) | Self-installing build tool launchers | Inside Maven / Gradle / Cargo projects |

---

## Related

- [CI/CD Providers](../ci-cd/index.md) — build tools run inside CI/CD pipelines; cache paths (`~/.m2`, `~/.gradle`, `~/.cargo`) recur there
- [Containers & Orchestration](../../containers/index.md) — Jib (Maven/Gradle plugin) builds OCI images without a Docker daemon; multi-stage Dockerfiles often invoke a build tool
- [Process Map](../../../maps/process-map.md#build-tools-evolution) — historical view of how build tools evolved
- [Java](../../../languages/java/index.md) — Maven and Gradle are the JVM ecosystem's defaults
- [Process](../index.md) — the surrounding chapter on methodologies, CI/CD, and observability
- [Testing](../../testing/index.md) — TDD, the pyramid, property-based testing
</content>
