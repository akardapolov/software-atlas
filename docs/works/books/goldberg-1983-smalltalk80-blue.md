# Smalltalk-80: The Language and Its Implementation

| | |
|---|---|
| **Author** | Adele Goldberg (with David Robson) |
| **Year** | 1983 |
| **Publication** | Addison-Wesley ("Blue Book") |
| **Topic(s)** | Smalltalk, programming language design, virtual machines |

## Summary

Known as the **"Blue Book,"** this is the definitive reference manual for **Smalltalk-80**, describing both the language specification and the implementation of its virtual machine. It was the primary way the broader programming community learned about Smalltalk.

## Key Ideas

### Smalltalk-80 Language Specification

The Blue Book provides complete language definition:

- **Everything is an object** — integers, strings, blocks, classes themselves
- **Message passing** — objects send messages, not method calls
- **Dynamic typing** — types determined at runtime
- **Blocks** — anonymous functions as first-class objects

```smalltalk
3 + 4 "send message: to: 4 with: 3
Transcript show: (3 + 4) "print string"
```

### Virtual Machine Implementation

The book documents the **bytecode VM architecture**:

- **Bytecode instructions** — intermediate representation, not native code
- **Stack-based execution** — operands and results on operand stack
- **Method lookup** — dynamic dispatch through class hierarchy
- **Garbage collection** — automatic memory management

This VM architecture directly influenced:
- **Java Virtual Machine (JVM)** — bytecode + stack + GC
- **Modern language VMs** — CLR, V8, Python runtime

### Development Environment

The book describes the **integrated environment**:

- **Class browser** — navigate and edit class definitions
- **Inspector** — examine object state interactively
- **Debugger** — step through execution, inspect stack
- **File system** — persistent storage for objects

## Historical Significance

### Canonical Smalltalk Reference

The Blue Book became:

- **The standard reference** — definitive Smalltalk-80 specification
- **Language learning** — how programmers worldwide learned Smalltalk
- **VM design foundation** — influenced all subsequent bytecode VMs

### Impact on Language Design

Smalltalk-80's influence is evident in:

- **Objective-C** — messaging model with dynamic dispatch
- **Ruby** — everything is object, block closures
- **Java** — bytecode VM, garbage collection, everything is object

## Legacy

The Blue Book remains influential:

- **Smalltalk dialects** — Squeak, Pharo still reference it
- **Language design** — integrated environment became standard goal
- **VM architectures** — blueprint for modern language runtimes

## Connections

- **Companion to:** [Orange Book (1984)](./goldberg-1984-smalltalk80-orange.md)
- **Preceded by:** [Smalltalk-72/76](../papers/ingalls-1978-smalltalk76.md)
- **Influenced:** JVM, modern language VMs, dynamic languages
- **Author:** [Adele Goldberg](../../authors/adele-goldberg.md)
- **Related language:** [Smalltalk](../../languages/smalltalk/)
