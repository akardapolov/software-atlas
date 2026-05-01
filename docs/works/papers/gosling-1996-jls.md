# The Java Language Specification

| | |
|---|---|
| **Author** | James Gosling (with Bill Joy and Guy Steele) |
| **Year** | 1996 |
| **Publication** | Addison-Wesley |
| **Topic(s)** | Java, language specification, virtual machine |

## Summary

The **Java Language Specification (JLS)** is the definitive technical reference for the Java programming language. This first edition, authored by James Gosling with Bill Joy and Guy Steele, established the formal specification that Java's compiler and runtime must implement.

## Key Ideas

### Language Foundation

The JLS specifies:

- **Syntax and grammar** — formal BNF specification
- **Semantics** — how Java programs behave
- **Types and values** — primitive and reference types
- **Packages and access** - modular organization and visibility
- **Threads and synchronization** - concurrent programming model
- **Exceptions** - error handling mechanism

### Bytecode and JVM

Java's compilation model:

```
Java source (.java)
    ↓
    bytecodes (.class)
    ↓
    JVM execution
```

- **Platform independence** — bytecodes run on any JVM implementation
- **Verification** - bytecode format can be verified
- **Security** - bytecode can be signed and sandboxed

### Memory Model

The JLS defines Java's memory semantics:

- **Heap and stack** — objects on heap, primitives on stack
- **Garbage collection** — automatic memory management
- **Reference types** — strong vs weak references
- **Thread safety** - memory model for concurrent code

### Concurrency

Java provides:

- **Synchronized blocks** — intrinsic locks
- **volatile keyword** — visibility across threads
- **wait/notify** — simple coordination mechanisms
- **ThreadLocal** — thread-local storage

## Historical Significance

### Standard Foundation

The JLS became:

- **Official specification** — Java compatibility guaranteed
- **Compiler reference** — what all Java compilers must implement
- **Education standard** — taught in universities worldwide

### "Write Once, Run Anywhere"

Java's portability model:

- **Cross-platform** — compile once, run anywhere with JVM
- **Enterprise adoption** - Java became standard for servers
- **Mobile success** - Android originally Java-only
- **Community growth** — multiple compatible JVM implementations

## Legacy

The JLS continues to be:

- **Authoritative reference** - for Java 9, 11, 17, 21, etc.
- **Community resource** - language lawyers, compiler writers
- **Educational material** - formal semantics in textbooks
- **Specification evolution** - with every Java version

## Connections

- **Builds on:** [Mesa/Cedar](../languages/) (Java ancestor)
- **Influenced:** .NET, Kotlin, Scala (JVM languages)
- **Related works:** [Java Programming Language (2000)](../books/gosling-2000-java-pl.md)
- **Author:** [James Gosling](../../authors/james-gosling.md)
- **Related:** [Java language](../../languages/java/)
