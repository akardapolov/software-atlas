# Project Jigsaw

> **Status:** Released — finalized in Java 9 (2017), stable since.
> **Goal:** Modularize the JDK itself and provide a module system for application code — strong encapsulation, explicit dependencies, smaller runtime images.

Project Jigsaw delivered the Java Platform Module System (JPMS), the most significant structural change to the Java platform since its inception. It transformed both the JDK (which was split into ~95 modules) and the way Java applications can be structured.

---

## Delivered Technologies

| # | Technology | Java version | Status | Page |
|---|---|---|---|---|
| 01 | Module System (JPMS) | 9 (final) | Released | [01-jpms.md](01-jpms.md) |

---

## Overview

### Pre-Jigsaw World

Before Java 9, the Java ecosystem had no strong encapsulation at the platform level:

- **The JDK was monolithic** — `rt.jar` contained everything from `Object` to Swing to CORBA
- **The classpath was flat** — no duplicate detection, no version enforcement, no isolation
- **Everything public was accessible** — frameworks relied on `sun.misc.Unsafe` and other internal APIs

Build tools (Maven, Gradle) managed dependencies at compile time, but at runtime it was still a flat classpath. This led to "classpath hell": `NoClassDefFoundError` at runtime, version conflicts silently resolved by "first JAR wins," and brittle dependencies on JDK internals.

### What JPMS Delivers

1. **Strong encapsulation** — Packages not explicitly exported are inaccessible, even via reflection
2. **Reliable configuration** — Missing or conflicting dependencies detected at startup, not mid-execution
3. **Smaller runtime images** — `jlink` creates custom JVM distributions containing only needed modules
4. **Cleaner JDK evolution** — Obsolete APIs can be deprecated and removed without breaking the world

### Modules vs Build Tools

JPMS is **not** a replacement for Maven or Gradle:

| Concern | Maven/Gradle | JPMS |
|---|---|---|
| Download artifacts | Yes | No |
| Version resolution | Yes | Single version per module |
| Transitive deps | Yes | `requires transitive` |
| Runtime encapsulation | No | Yes |
| Custom runtime images | No | `jlink` |

Build tools manage the *build*; JPMS manages the *runtime*.

### Adoption Reality

While the JDK itself is fully modularized, application adoption has been gradual:

- **Libraries** — Many popular libraries (Spring, Hibernate, Jackson) now provide `module-info.java`
- **Frameworks** — Spring Boot 3+ supports modules; earlier versions run on the classpath as an unnamed module
- **Applications** — Greenfield projects can adopt modules fully; legacy projects often stay on the classpath

The `--add-opens` and `--add-exports` flags exist specifically to bridge the gap: they let frameworks access internal packages during the transition period.

---

## See Also

- [Module System (JPMS)](../../17-modules.md) — main feature page with full API reference
- [Reflection API](../../16-reflection.md) — `opens` and deep reflection
- [Examples: OOP and Modules](../../../examples/java/06-oop-modules/README.md)
