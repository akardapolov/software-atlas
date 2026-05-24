# Project Panama

> **Status:** Released — FFM API finalized in Java 22, consolidated in Java 25 LTS.
> **Goal:** Efficient, safe interop between Java and native code / off-heap memory, replacing JNI.

Project Panama modernizes Java's relationship with the world outside the JVM. Before Panama, Java developers who needed to call C libraries or manage off-heap memory had only one official option: the Java Native Interface (JNI). JNI is verbose, error-prone, and requires writing C glue code. Panama replaces this with a pure-Java API that is both safer and faster.

---

## Delivered Technologies

| # | Technology | Java version | Status | Page |
|---|---|---|---|---|
| 01 | Foreign Function & Memory API | 22 (final) | Released | [01-ffm-api.md](01-ffm-api.md) |
| 02 | Vector API | 25 (incubator → preview) | Preview | [02-vector-api.md](02-vector-api.md) |

---

## Overview

### The JNI Problem

The Java Native Interface has been the bridge to native code since Java 1.1. Its design reflects 1990s assumptions:

- **C boilerplate required** — every native call needs a C wrapper function
- **Manual memory management** — native heap allocations are invisible to the GC
- **Fragile type signatures** — string-encoded method signatures that break silently
- **No off-heap safety** — raw pointers with zero bounds checking

### The Panama Solution

Panama provides two complementary APIs:

1. **Foreign Function API** — Call C functions directly from Java without writing C code
2. **Foreign Memory API** — Allocate, access, and manage off-heap memory with runtime safety

Both APIs are **fully Java** — no C compilers, no header files, no JNI glue. The `jextract` tool can auto-generate Java bindings from C header files.

### Memory Safety Model

| Aspect | JNI | Panama FFM |
|---|---|---|
| Memory access | Raw pointer (`long`) | `MemorySegment` with bounds checking |
| Lifetime management | Manual (leak-prone) | `Arena` with deterministic cleanup |
| Native calls | C wrapper required | `MethodHandle` generated at runtime |
| Type safety | None | `ValueLayout` describes C types |
| Performance | Good | Better (no JNI transition overhead) |

### Vector API

The Vector API (second Panama deliverable) brings SIMD (Single Instruction, Multiple Data) operations to Java. It allows expressing data-parallel computations that compile to hardware vector instructions (SSE, AVX, NEON), dramatically accelerating numerical workloads.

> **Note:** The Vector API remains in preview/incubator status as of Java 25, as the JVM team awaits the stabilization of Valhalla's value types, which will significantly improve the API's performance characteristics.

---

## See Also

- [Foreign Function & Memory API](../../11-ffm-api.md) — main feature page
- [Virtual Threads](../../08-virtual-threads.md) — using FFM with virtual threads
- [Reflection API](../../16-reflection.md) — `MethodHandle` and `VarHandle`
