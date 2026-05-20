# Foreign Function & Memory API

**Final in Java 22** (Project Panama). It allows Java programs to interoperate
with native code and memory outside the JVM heap in a safer, more explicit way
than traditional JNI.

## Foreign memory access

Allocate and access off-heap memory with explicit lifetime control:

```java
try (Arena arena = Arena.ofConfined()) {
    MemorySegment segment = arena.allocate(8);   // allocate 8 bytes off-heap
    segment.set(ValueLayout.JAVA_LONG, 0, 42L);
    long value = segment.get(ValueLayout.JAVA_LONG, 0);
    System.out.println(value); // 42
} // arena closed → memory freed automatically
```

Arena types:

| Arena | Lifetime | Thread safety |
|---|---|---|
| `Arena.ofConfined()` | Close explicitly or try-with-resources | Single-thread access only |
| `Arena.ofShared()` | Close explicitly | Multi-thread safe |
| `Arena.ofAuto()` | GC + Cleaner | Automatic cleanup |
| `Arena.global()` | JVM lifetime | Never closes |

## Foreign function calls

Call native libraries without writing C wrappers:

```java
// 1. Find a native function
Linker linker = Linker.nativeLinker();
SymbolLookup stdlib = linker.defaultLookup();

MethodHandle strlen = linker.downcallHandle(
    stdlib.find("strlen").orElseThrow(),
    FunctionDescriptor.of(ValueLayout.JAVA_LONG, ValueLayout.ADDRESS)
);

// 2. Call it
try (Arena arena = Arena.ofConfined()) {
    MemorySegment cString = arena.allocateUtf8String("Hello");
    long len = (long) strlen.invoke(cString);
    System.out.println(len);  // 5
}
```

## Comparison with JNI

| Aspect | JNI | FFM API |
|---|---|---|
| Safety | Error-prone C code | Pure Java, type-safe layouts |
| Performance | Call overhead + C glue | Near-direct calls via `MethodHandle` |
| Memory model | Manual `malloc`/`free` | Scoped arenas with auto-cleanup |
| Tooling | Requires C compiler | Standard JDK API |
| Portability | Platform-specific C | Java code, platform-agnostic |

## Use cases

- **Zero-copy I/O** — map files or sockets directly into off-heap memory.
- **Native library integration** — GPU computing (CUDA), ML frameworks (TensorFlow).
- **High-performance data structures** — off-heap caches, columnar storage.
- **JVM interop** — call C/C++ libraries without JNI boilerplate.

## Safety notes

Although safer than JNI, incorrect operations can still cause JVM crashes:

- Accessing memory after its arena is closed → use-after-free.
- Wrong `ValueLayout` for a native type → memory corruption.
- Passing wrong number of arguments to `downcallHandle` → native crash.
