# Dan Ingalls

| | |
|---|---|
| **Born** | 1944 |
| **Fields** | Smalltalk, VM design, graphics |
| **Known for** | Smalltalk implementation, BitBlt, Squeak |
| **Awards** | ACM Software System Award (1984, for Smalltalk-80) |

## Biography

Daniel H. H. Ingalls is an American computer scientist known for his work on **Smalltalk** at Xerox PARC and Apple. He was the primary implementer of all major Smalltalk versions (Smalltalk-72, -76, -80) and created several foundational technologies.

Ingalls' innovations in virtual machines, graphics operations, and reflective systems directly influenced subsequent language runtimes including the Java Virtual Machine (JVM) and the Common Language Runtime (CLR).

## Key Contributions

### Smalltalk Virtual Machine and Bytecode

Ingalls designed the **bytecode virtual machine** for Smalltalk-76 and Smalltalk-80:
- First practical use of bytecode for a major language
- Separation of compiled representation (bytecode) from execution (VM)
- Cross-platform portability — compiled bytecode runs on any VM

This architecture directly inspired:
- **Java Virtual Machine (JVM)** — "write once, run anywhere"
- **Common Language Runtime (CLR)** — .NET's VM
- Modern language VMs (Python, Ruby, JavaScript V8)

### BitBlt

Ingalls created **BitBlt** (Bit Block Transfer) — a universal, highly optimized operation for raster graphics:
- Blt (pronounced "blit") — copy rectangular regions of pixels
- Hardware acceleration when available, software fallback otherwise
- Became standard in graphics systems and GUI frameworks

BitBlt made practical graphics-intensive interfaces possible on early hardware.

### Reflection

Ingalls implemented **runtime reflection** in Smalltalk:
- Objects can inspect their own structure (classes, methods, variables)
- Classes are objects themselves — can be queried and modified
- "Live" programming — system can be modified while running

This influenced reflection systems in Java, .NET, and dynamic languages.

### Squeak (1996)

With Alan Kay, Ingalls co-created **Squeak** — an open-source Smalltalk:
- Smalltalk as an open research platform
- Active community of educators and developers
- Foundation for **Pharo** and other modern Smalltalk dialects

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 1978 | *The Smalltalk-76 Programming System: Design and Implementation* | Paper | [→](../works/papers/ingalls-1978-smalltalk76.md) |
| 1981 | *Design Principles Behind Smalltalk* (BYTE Magazine) | Paper | [→](../works/papers/ingalls-1981-design-principles.md) |
| 1997 | *Back to the Future: The Story of Squeak* (OOPSLA) | Paper | [→](../works/papers/ingalls-1997-squeak.md) |

## Influence

### Influenced by

- **Alan Kay** — Smalltalk visionary and designer

### Influenced

- **JVM and CLR** — bytecode VM architecture
- **GUI frameworks** — BitBlt became standard for graphics operations
- **Live programming environments** — Pharo, Squeak, modern Smalltalk dialects
- **Reflection systems** — Java reflection, .NET reflection

## Quotes

> "The system should be live — you should be able to change it while it's running."

## Further Reading

- [Wikipedia: Dan Ingalls](https://en.wikipedia.org/wiki/Dan_Ingalls)
- [Squeak.org](https://squeak.org/)

## Related Pages

- [Alan Kay](alan-kay.md)
- [Adele Goldberg](adele-goldberg.md)
- [Smalltalk](../languages/smalltalk/index.md)
