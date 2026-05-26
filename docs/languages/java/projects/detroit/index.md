# Project Detroit

> **Status:** 🔴 Early proposal — no implemented features in JDK as of March 2026 (JavaOne 2026).  
> **Goal:** Fast interoperability between Java, JavaScript, and Python via the `javax.script` API and embedded language runtimes.

Project Detroit aims to provide fast, seamless interoperability between Java and other popular languages — specifically JavaScript and Python. Unlike previous approaches (Nashorn, GraalVM polyglot) that required separate tooling or complex native bridges, Detroit embeds V8 and CPython runtimes directly inside the JVM process using the Foreign Function & Memory (FFM) API, enabling native-language compatibility with clear heap isolation.

The key insight: with the rise of AI and data science, there is strong demand to access Python-based AI libraries and JavaScript ecosystems from Java applications. Detroit bridges that gap by providing a first-class, high-performance polyglot interop layer built on standard OpenJDK APIs.

---

## Planned Technologies

| # | Technology | Java version | Status | Page |
|---|---|---|---|---|
| 01 | Java ↔ JavaScript Interop | N/A | Proposal | [01-js-interop.md](01-js-interop.md) |
| 02 | Java ↔ Python Interop | N/A | Proposal | [02-python-interop.md](02-python-interop.md) |
| 03 | `javax.script` API Bridge | N/A | Proposal | [03-script-api-bridge.md](03-script-api-bridge.md) |

---

## Architectural Overview

### The Problem Before Detroit

Current polyglot interop in the Java ecosystem requires developers to choose between suboptimal approaches:

- **GraalVM Polyglot** – Powerful but requires GraalVM distribution, not available in standard OpenJDK.
- **JEP 454 (FFM API) + Manual Bindings** – Flexible but requires hand-written native bindings for every language runtime.
- **ProcessBuilder / JNI** – High overhead, complex memory management, fragile cross-platform support.
- **Nashorn (removed in JDK 15)** – No longer available; previous `javax.script` implementation abandoned.

The result: Java developers lack a standard, high-performance way to call JavaScript and Python code from within the JVM.

### The Three Pillars of Detroit

```mermaid
graph TD
    Detroit["🔴 Project Detroit"]

    JS["JavaScript Interop\n(Embedded V8 via FFM)"]
    PY["Python Interop\n(Embedded CPython via FFM)"]
    API["javax.script API\n(Standard bridge)"]

    Detroit --> JS
    Detroit --> PY
    Detroit --> API

    JS --- note1["V8 runtime inside JVM\nDirect memory sharing"]
    PY --- note2["CPython inside JVM\nAccess to NumPy, PyTorch, etc."]
    API --- note3["Standard JSR-223 API\nDrop-in replacement for Nashorn"]

    style Detroit fill:#C0392B,color:#fff
    style JS fill:#F39C12,color:#fff
    style PY fill:#2980B9,color:#fff
    style API fill:#27AE60,color:#fff
```

### How Cross-Language Calls Work

```mermaid
sequenceDiagram
    participant Java as Java Application
    participant Bridge as Detroit Bridge (FFM API)
    participant V8 as Embedded V8 / CPython
    participant Heap as Isolated Native Heap

    Java->>Bridge: Invoke JavaScript / Python function
    Bridge->>V8: Marshall arguments via FFM
    V8->>Heap: Allocate native objects
    V8->>V8: Execute guest language code
    V8-->>Bridge: Return result
    Bridge-->>Java: Marshall back to Java objects
```

---

## Evolution of Polyglot Support in Java

```mermaid
timeline
    title Evolution of Polyglot Support in Java
    2006 : Java 6 — javax.script API (JSR-223) introduced
    2014 : Java 8 — Nashorn JavaScript engine ships
    2018 : Java 11 — Nashorn deprecated
    2021 : Java 15 — Nashorn removed from JDK
    2022 : GraalVM polyglot gains traction (non-standard)
    2024 : JEP 454 — FFM API finalized (JDK 22)
    2026 : Project Detroit proposed (JavaOne 2026)
    2027 : Target — Experimental V8 / CPython interop
```

---

## Comparison of Polyglot Approaches

```mermaid
quadrantChart
    title Polyglot Interop Approaches for Java
    x-axis "High complexity" --> "Low complexity"
    y-axis "Low performance" --> "High performance"
    quadrant-1 "Ideal (low complexity, high performance)"
    quadrant-2 "Low complexity, low performance"
    quadrant-3 "High complexity, high performance"
    quadrant-4 "High complexity, low performance"

    "Detroit (planned)": [0.2, 0.9]
    "GraalVM Polyglot": [0.7, 0.85]
    "FFM API + manual bindings": [0.85, 0.7]
    "ProcessBuilder / JNI": [0.9, 0.3]
    "Nashorn (historical)": [0.3, 0.4]
```

---

## Relationship with Other OpenJDK Projects

| Project | Area | Interaction with Detroit |
|---|---|---|
| **Panama** | Native interop | Detroit builds directly on the FFM API (JEP 454) for embedding V8 and CPython. |
| **Loom** | Virtual threads | Guest language callbacks can be scheduled on virtual threads for massive concurrency. |
| **Amber** | Language features | Pattern matching and records simplify marshalling data between Java and guest languages. |
| **Leyden** | Startup | Pre-linked native runtimes can be cached in AOT images for sub-second polyglot startup. |

---

## See Also

- [FFM API](../../11-foreign-function-memory-api.md) — Foreign Function & Memory API (Panama)
- [JEP 454](https://openjdk.org/jeps/454) — FFM API final specification
- [javax.script documentation](https://docs.oracle.com/en/java/javase/21/docs/api/java.script/javax/script/package-summary.html) — Standard scripting API
- [GraalVM Polyglot](https://www.graalvm.org/latest/reference-manual/polyglot-programming/) — Comparison with GraalVM approach
