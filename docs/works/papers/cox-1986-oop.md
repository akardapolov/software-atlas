# Object-Oriented Programming: An Evolutionary Approach

| | |
|---|---|
| **Author** | Brad Cox |
| **Year** | 1986 |
| **Publication** | Addison-Wesley |
| **Topic(s)** | Objective-C, OOP, language design |

## Summary

Cox's book *"Object-Oriented Programming: An Evolutionary Approach"* describes his vision for bringing **Smalltalk's object-oriented concepts** to the **world of C systems programming**. The book introduced the `[object message:arg]` syntax that became foundational to Objective-C and, through NeXT and Apple, influenced an entire platform ecosystem.

## Key Ideas

### Smalltalk Messages in C

Cox's core innovation was combining two paradigms:

```objectivec
// Smalltalk-style message sending in C syntax:
[object message:argument];

// Equivalent to:
Smalltalk send: "message" to: object with: argument
```

This approach provides:

- **C efficiency** — compile to native code
- **OOP concepts** — dynamic dispatch, runtime typing
- **Familiar syntax** — C programmers comfortable
- **Bridge between worlds** — connected two programming communities

### Dynamic Dispatch

Objective-C uses **runtime message passing**:

- **Object receives message** — `respondsToSelector:`
- **Runtime lookup** — object decides how to handle
- **Forwarding** — messages can be forwarded
- **No compile-time binding** — flexibility at cost of safety

This differs from C++:
- C++: static binding via vtable (faster, but rigid)
- Obj-C: dynamic dispatch (slower, but flexible)

### Software ICs Metaphor

Cox introduced concept of **"Software ICs"** — reusable components as "integrated circuits":

- **Binary components** — compiled, plug-and-play
- **Well-defined interfaces** — contracts between components
- **Composability** — build systems from ICs
- **Analogy to hardware** — ICs like VLSI chips

This foreshadowed:
- **JavaBeans** — component model
- **.NET assemblies** — similar concept
- **Apple frameworks** — Cocoa built on IC model

### Categories

Cox contributed to language extensions:

```objectivec
// Add methods to existing class without subclassing
@interface NSString (MyExtensions)
- (NSString *)reversedString;
@end
```

Categories enable:
- **Open classes** — extend system classes
- **Modular design** — organize related functionality
- **Mixins** — alternative to multiple inheritance
- **"Monkey patching"** — powerful but dangerous flexibility

## Historical Significance

### NeXTSTEP and Mac OS X

Cox's Objective-C became the primary language for:

- **NeXTSTEP** — NeXT's object-oriented framework (founded by Steve Jobs)
- **OpenStep** — standardized version after NeXT acquisition by Apple
- **Mac OS X** - Cocoa framework in Objective-C
- **iOS** - iPhone OS initially used Objective-C exclusively

### Legacy in Modern Systems

Objective-C's influence persists:

- **Cocoa frameworks** - macOS development still largely Obj-C
- **macOS apps** - decades of App Store apps built with Obj-C
- **iOS development** - first 10+ years of iOS apps
- **Foundation for Swift** - Swift inherited many Obj-C concepts

## Connections

- **Influenced by:** [Smalltalk](../../languages/smalltalk/index.md) · [C language](../../languages/c/index.md)
- **Preceded by:** [Simula](../../languages/simula/index.md)
- **Influenced:** NeXTSTEP, macOS, iOS, Swift
- **Related:** [Objective-C language](../../languages/objective-c/index.md) (to be created)
- **Author:** [Brad Cox](../../authors/brad-cox.md)
