# The Smalltalk-76 Programming System: Design and Implementation

| | |
|---|---|
| **Author** | Dan Ingalls |
| **Year** | 1978 |
| **Publication** | PhD Thesis, University of Utah |
| **Topic(s)** | Smalltalk, virtual machines, bytecode |

## Summary

Ingalls' thesis describes the design and implementation of **Smalltalk-76**, an early version of Alan Kay's Smalltalk that introduced key innovations in virtual machine design and graphics operations.

## Key Ideas

### Bytecode Virtual Machine

Ingalls designed one of the first practical bytecode VMs:

- **Instruction set** - compact bytecode for Smalltalk operations
- **Stack-based execution** - operands and results on operand stack
- **Object representation** - objects as pointers in object memory
- **Message dispatch** - dynamic method lookup at runtime

This VM architecture directly influenced:
- **Java Virtual Machine (JVM)** - similar bytecode and execution model
- **.NET CLR** - bytecode execution with garbage collection

### BitBlt Graphics Operation

Ingalls created **BitBlt** (Bit Block Transfer):

- **Fast raster operations** - copy rectangular pixel regions
- **Hardware acceleration** - use hardware features when available
- **Portable** - software fallback for unsupported hardware

BitBlt became the standard for:
- **GUI drawing** - windowing systems, bitmap graphics
- **Game development** - sprite blitting in games
- **Modern graphics** - foundation of 2D graphics libraries

### Reflection System

Smalltalk-76 implemented **runtime reflection**:

- **Classes are objects** - can query class metadata
- **Objects are self-inspectable** - examine methods and variables
- **Dynamic modification** - add methods to classes at runtime

This enabled:
- **Live development** - modify running system
- **Interactive exploration** - browse system structure
- **Metaprogramming** - code that manipulates code

## Historical Significance

### Foundation of JVM

Ingalls' VM design is the direct predecessor to:

- **JVM architecture** - bytecode, stack execution, GC
- **Modern language VMs** - Python, Ruby, JavaScript runtimes
- **Cross-platform execution** - compile once, run anywhere

### Graphics Operations

BitBlt's influence extends to:

- **Modern graphics APIs** - Direct2D, Cairo, Skia
- **GUI frameworks** - Swing, WPF, graphics libraries
- **Game engines** - sprite blitting remains core technique

## Legacy

Ingalls' innovations remain foundational:

- **Language VMs** - all modern bytecode runtimes
- **Graphics programming** - 2D raster operations everywhere
- **Reflection** - dynamic languages and IDEs

## Connections

- **Influenced:** JVM, graphics systems, reflection
- **Followed by:** [Smalltalk-80](./goldberg-1983-smalltalk80-blue.md)
- **Author:** [Dan Ingalls](../../authors/dan-ingalls.md)
- **Related language:** [Smalltalk](../../languages/smalltalk/)
