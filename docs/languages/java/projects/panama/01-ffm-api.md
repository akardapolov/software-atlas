# Foreign Function & Memory API

> **Project:** Panama
> **Java version:** 22 (final)
> **Status:** Released

The Foreign Function & Memory (FFM) API provides a modern, safe, and efficient interface to invoke native code (C/C++ libraries) and access off-heap memory, replacing the legacy Java Native Interface (JNI).

For the complete API reference and usage patterns, see the main feature page: [Foreign Function & Memory API](../../11-ffm-api.md).

---

## History and Evolution

### The JNI Era (1997–2024)

JNI has been Java's bridge to native code for over 25 years. Its design is rooted in 1990s constraints:

```c
// JNI requires writing C boilerplate for every native method
JNIEXPORT jint JNICALL Java_MyClass_nativeAdd(JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}
```

```java
// Java side
public native int nativeAdd(int a, int b);
static { System.loadLibrary("mylib"); }
```

JNI's pain points:
- **C code required** — every native call needs a C wrapper
- **Fragile string signatures** — `"(II)I"` encodes parameter/return types; typos cause crashes
- **No memory safety** — `GetByteArrayElements` returns raw pointers with manual release
- **Two-call pattern** — `GetStringUTFChars` / `ReleaseStringUTFChars` is easy to get wrong
- **Slow transitions** — JNI boundary crossing has significant overhead

### Panama's Predecessors

Before FFM API, Panama explored several incubator APIs:

| API | Release | What it explored |
|---|---|---|
| Foreign Memory Access API | Java 14–16 | `MemorySegment`, `MemoryAddress` |
| Foreign Linker API | Java 16–17 | `CLinker`, `NativeSymbol` |
| Vector API (incubator) | Java 16+ | SIMD operations |

These were refined and unified into the single FFM API in Java 22.

### Design Goals

1. **Productivity** — Call C libraries from Java without writing C code
2. **Safety** — Bounds-checked memory access, deterministic lifetimes via `Arena`
3. **Performance** — Faster than JNI for common patterns
4. **Generality** — Support any C ABI, any platform

## Implementation Deep Dive

### Core Concepts

The FFM API revolves around four types:

| Type | Purpose | JNI Equivalent |
|---|---|---|
| `Linker` | Looks up and links native functions | `System.loadLibrary()` + C wrapper |
| `SymbolLookup` | Finds symbols in native libraries | `dlsym` / `GetProcAddress` |
| `Arena` | Manages off-heap memory lifetime | Manual `malloc`/`free` |
| `MemorySegment` | A safe view of contiguous memory | Raw `void*` pointer |
| `ValueLayout` | Describes C type size and alignment | Manual size calculations |

### The Arena Model

Memory safety is the biggest improvement over JNI. FFM uses **arenas** for deterministic lifetime management:

```java
// Confined arena — owned by one thread, automatic cleanup
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(100); // 100 bytes
    // use segment...
} // segment is automatically freed here

// Shared arena — can be accessed by multiple threads, manual close
try (Arena arena = Arena.ofShared()) {
    MemorySegment segment = arena.allocate(100);
    // pass segment to other threads...
} // freed after all threads done
```

| Arena type | Thread-safe | Cleanup | Use case |
|---|---|---|---|
| `ofConfined()` | No (owner thread only) | Automatic on close | Single-threaded, bounded scope |
| `ofShared()` | Yes | Automatic on close | Multi-threaded access |
| `ofAuto()` | Yes | GC-driven (Cleaner) | Long-lived, unbounded scope |
| `global()` | Yes | Never (JVM lifetime) | Truly permanent allocations |

### Calling C Functions

```java
// 1. Obtain a linker for the current platform
Linker linker = Linker.nativeLinker();

// 2. Look up a C library function
SymbolLookup stdlib = linker.defaultLookup();
MemorySegment strlenAddr = stdlib.find("strlen").orElseThrow();

// 3. Define the function signature
FunctionDescriptor descriptor = FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);

// 4. Create a MethodHandle
MethodHandle strlen = linker.downcallHandle(strlenAddr, descriptor);

// 5. Call it from Java!
try (Arena arena = Arena.ofConfined()) {
    MemorySegment str = arena.allocateUtf8String("Hello");
    long len = (long) strlen.invoke(str); // 5
}
```

No C code. No `javah`. No fragile string signatures. The Java type system describes the C interface.

### jextract: Auto-Generated Bindings

For complex C libraries, the `jextract` tool (shipped with the JDK) parses C header files and generates Java bindings:

```bash
jextract --source -l curl -t com.example.curl /usr/include/curl/curl.h
```

This generates Java classes with typed methods for every C function, struct, and constant in the header.

### Memory Layouts

FFM can describe and manipulate C structs:

```java
StructLayout pointLayout = MemoryLayout.structLayout(
    ValueLayout.JAVA_DOUBLE.withName("x"),
    ValueLayout.JAVA_DOUBLE.withName("y")
);

try (Arena arena = Arena.ofConfined()) {
    MemorySegment point = arena.allocate(pointLayout);
    point.set(ValueLayout.JAVA_DOUBLE, 0, 3.0); // x
    point.set(ValueLayout.JAVA_DOUBLE, 8, 4.0); // y

    // Or use var handles for named access
    VarHandle xHandle = pointLayout.varHandle(PathElement.groupElement("x"));
    xHandle.set(point, 3.0);
}
```

## Performance

| Operation | JNI | FFM API | Notes |
|---|---|---|---|
| Simple downcall | Baseline | ~1.5× faster | No JNI transition frame |
| Memory access | Raw pointer | Same | Zero-overhead after bounds check is elided |
| String passing | Two copies | One copy | Direct UTF-8 encoding |
| Struct passing | Field-by-field | Direct segment | No serialization overhead |

The performance advantage comes from:
1. **No JNI transition frame** — the JIT can inline FFM calls
2. **Direct memory access** — `MemorySegment` addresses are raw pointers with bounds metadata
3. **Type specialization** — `MethodHandle` invocations are JIT-optimized

## See Also

- [FFM API — main feature page](../../11-ffm-api.md)
- [Vector API](02-vector-api.md) — Panama's other deliverable
- [Reflection API](../../16-reflection.md) — `MethodHandle` and `VarHandle`
- [Virtual Threads](../../08-virtual-threads.md) — using FFM with virtual threads
