# Brad Cox

| | |
|---|---|
| **Born** | 1944 |
| **Died** | 2021 |
| **Fields** | Objective-C, OOP, software components |
| **Known for** | Creator of Objective-C |
| **Education** | PhD (Mathematical Biology), University of Chicago |

## Biography

Bradford J. Cox was an American computer scientist and entrepreneur who created **Objective-C** in the early 1980s. His goal was to bring **Smalltalk-style object-oriented messaging** to the efficient world of **C programming**.

Objective-C became the foundation of NeXTSTEP at NeXT (founded by Steve Jobs after leaving Apple), and later returned to Apple as the primary language for **macOS and iOS** development.

## Key Contributions

### Smalltalk Messaging in C

Cox's innovation was combining two paradigms:

- **C syntax and efficiency** — familiar to systems programmers
- **Smalltalk-style messaging** — `[object message:argument]`

The key difference from C++:
- C++ uses **static method calls** via vtable
- Objective-C uses **dynamic message dispatch** at runtime

This made Objective-C more flexible (and dynamically typed) but slightly slower than C++.

### Dynamic Dispatch

Objective-C's message passing works like Smalltalk:
- Send message to object → object decides how to respond
- If object doesn't understand message → forwards to handler (or raises error)
- Enables features like method forwarding and dynamic method resolution

### Categories

Cox introduced **categories** — a way to extend classes:
- Add methods to existing classes without subclassing
- No need to modify original class source
- Pioneered the idea of "monkey patching"

### Software ICs

Cox envisioned reusable software components as **"Software ICs"** (integrated circuits):
- Binary, reusable components
- Well-defined interfaces
- Plug-and-play software architecture

This vision influenced modern component systems (COM, JavaBeans, Swift modules).

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 1986 | *Object-Oriented Programming: An Evolutionary Approach* | Paper | [→](../works/papers/cox-1986-oop.md) |

## Influence

### Influenced by

- **Smalltalk-80** — message passing, dynamic typing
- **C language** — systems programming efficiency

### Influenced

- **NeXTSTEP** — NeXT's application framework
- **macOS and iOS** — Objective-C was primary language until Swift
- **Swift** — Apple's successor to Objective-C, inheriting many ideas

## Legacy

Objective-C's impact:

- **Apple ecosystem** — decades of macOS and iOS development
- **Dynamic runtime** — enabled Cocoa bindings, Key-Value Coding
- **Scripting bridges** — easy interoperability with Python, Ruby, JavaScript

## Further Reading

- [Wikipedia: Brad Cox](https://en.wikipedia.org/wiki/Brad_Cox)
- [Wikipedia: Objective-C](https://en.wikipedia.org/wiki/Objective-C)

## Related Pages

- [Alan Kay](alan-kay.md)
- [Smalltalk](../languages/smalltalk/index.md)
- [Objective-C language](../languages/objective-c/index.md) (to be created)
