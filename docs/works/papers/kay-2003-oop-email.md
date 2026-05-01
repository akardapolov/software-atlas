# Dr. Alan Kay on the Meaning of OOP

| | |
|---|---|
| **Author** | Alan Kay |
| **Year** | 2003 |
| **Publication** | Email to Stefan Ramil (posted online) |
| **Topic(s)** | Object-oriented programming, software history, Smalltalk |

## Summary

In this widely-circulated email, Alan Kay clarifies his original vision of **object-oriented programming** and explains how it differs from what mainstream languages (C++, Java) implemented. The email is a definitive statement of what OOP was meant to be according to its creator.

## Key Ideas

### Kay's Definition of OOP

Kay defines OOP with three essential concepts:

> "OOP to me means only messaging, local retention and protection
> and hiding of state-process, and extreme late-binding of all things."

1. **Messaging** — objects communicate by sending messages, not calling methods
2. **Local state** — each object owns and protects its state
3. **Late binding** — everything decided as late as possible (runtime)

### What OOP Is NOT

Kay explicitly states what OOP is **not**:

> "OOP to me does NOT mean: classes, inheritance, or methods."

- **NOT** just classes with methods — Java/C++ model
- **NOT** inheritance hierarchies — complex taxonomies
- **NOT** encapsulation via access modifiers — private/public

### The Two Lines of OOP

Kay identifies two fundamentally different approaches:

| Kay's Line (Smalltalk) | Mainstream (C++, Java) |
|--------------------------|---------------------------|
| Objects send messages | Objects have methods, we call them |
| Dynamic, late binding | Static, early binding via vtables |
| "What is object" decides | Compiler decides what object is |
| Actor model | Function call model |
| Composition favored | Inheritance often overused | Composition over inheritance (rarely) |

### Smalltalk vs Java/C++

Kay illustrates the difference:

```smalltalk
"3 + 4" -- send + message to 3 with argument 4
```

```java
3.add(4) -- call method on 3, passing 4
```

The Smalltalk version:
- Object 3 decides how to handle +
- More flexible, potentially more powerful
- True to "messaging" philosophy

## Historical Significance

### Clarification of OOP's Meaning

This email is crucial for understanding:

- **Original intent** — Kay's vision vs industry implementation
- **Misunderstandings** — why OOP often "feels wrong"
- **Alternative approaches** — Erlang, Clojure closer to Kay's vision

### Influence on Language Design

The email influenced discussions about:

- **Messaging systems** — actor models, CSP, event-driven
- **Dynamic vs static typing** — flexibility vs safety trade-offs
- **Composition over inheritance** — design principle emphasized by Kay

## Legacy

Kay's email remains the authoritative reference for:

- **OOP philosophy** — what it was meant to be
- **Language design debates** — static vs dynamic, interfaces vs protocols
- **Software history** — evolution of OOP from vision to industry

## Connections

- **Related works:** [Early History of Smalltalk (1993)](../papers/kay-1993-early-history-smalltalk.md)
- **Influenced:** Understanding of OOP, language design debates
- **Author:** [Alan Kay](../../authors/alan-kay.md)
