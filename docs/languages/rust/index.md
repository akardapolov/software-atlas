# Rust

| | |
|---|---|
| **Year** | 2010 |
| **Creator(s)** | Graydon Hoare (Mozilla Research) |
| **Paradigm(s)** | Multi-paradigm (functional, imperative, OOP-ish) |
| **Typing** | Static, affine (ownership) |
| **Platform** | Native (LLVM-based) |
| **Key features** | Ownership, borrow checker, zero-cost abstractions, pattern matching |
| **Current version** | Rust 1.83 (2024) |

---

## Contents

1. [Overview](#overview)
2. [Historical Context](#historical-context)
3. [Key Ideas](#key-ideas)
   - [Ownership](#ownership)
     - [Drop Flags](#drop-flags)
   - [Borrowing and References](#borrowing-and-references)
     - [Aliasing Models: Stacked vs. Tree Borrows](#aliasing-models-stacked-vs-tree-borrows)
     - [Strict Provenance](#strict-provenance)
   - [Lifetimes](#lifetimes)
   - [Zero-Cost Abstractions](#zero-cost-abstractions)
     - [Compilation Pipeline](#compilation-pipeline)
     - [MIR: Basic Blocks and the Borrow Checker](#mir-basic-blocks-and-the-borrow-checker)
   - [Pattern Matching](#pattern-matching)
4. [Key Features In Depth](#key-features-in-depth)
   - [01. Ownership](#01-ownership)
   - [02. Borrowing](#02-borrowing)
   - [03. Lifetimes](#03-lifetimes)
   - [04. Traits](#04-traits)
   - [05. Pattern Matching](#05-pattern-matching)
   - [06. Error Handling](#06-error-handling)
   - [07. Generics](#07-generics)
   - [08. Concurrency](#08-concurrency)
5. [Core Features](#core-features)
   - [Structs and Enums](#structs-and-enums)
     - [Niche Optimization](#niche-optimization)
   - [Traits](#traits)
   - [Error Handling](#error-handling)
   - [Generics](#generics)
     - [Monomorphization](#monomorphization)
   - [Iterators and Closures](#iterators-and-closures)
   - [Concurrency](#concurrency)
6. [Modern Rust Features](#modern-rust-features)
   - [Async/Await](#asyncawait-rust-139)
     - [Compiler Lowering: async → state machine](#compiler-lowering-async-state-machine)
   - [Const Generics](#const-generics-rust-151)
   - [Const Evaluation](#const-evaluation)
   - [Let-Else](#let-else-rust-165)
6. [Ecosystem and Tools](#ecosystem-and-tools)
7. [Influence](#influence)
8. [Strengths and Weaknesses](#strengths-and-weaknesses)
9. [Code Examples](#code-examples)
10. [Related Authors](#related-authors)
11. [Related Topics](#related-topics)
12. [Further Reading](#further-reading)

---

## Overview

Rust is a systems programming language focused on **safety, concurrency, and
performance**. Created by Graydon Hoare in 2010 and sponsored by Mozilla,
Rust provides memory safety without garbage collection through its novel
**ownership system**.

Rust's distinctive characteristics:
- **Memory safety without GC** — compile-time guarantees via ownership
- **Fearless concurrency** — data races caught at compile time
- **Zero-cost abstractions** — high-level code compiles to efficient machine code
- **Pattern matching** — expressive, exhaustive destructuring
- **Cargo** — integrated package manager and build tool

Rust powers:
- **Firefox** — Stylo (CSS engine), WebRender
- **Servo** — experimental browser engine
- **Linux kernel** — since 6.1 (2022), first non-C language
- **Infrastructure** — AWS, Cloudflare, Microsoft Azure
- **WebAssembly** — leading language for Wasm targets

---

## Historical Context

```mermaid
flowchart TD
    subgraph Predecessors
        C["Dennis Ritchie<br/>C<br/>1972"]
        Cpp["Bjarne Stroustrup<br/>C++<br/>1979"]
        OCaml["OCaml<br/>1996"]
        Haskell["Haskell<br/>1990"]
        Cyclone["Cyclone<br/>2006<br/>Regions for C"]
    end

    subgraph Origins
        Hoare["Graydon Hoare<br/>Mozilla Research<br/>2006-2010"]
    end

    subgraph Rust_Evolution
        Rust09["Rust 0.1<br/>2012<br/>Compiler rewrite"]
        Rust10["Rust 1.0<br/>2015<br/>Stable release"]
        Rust18["Rust 2018<br/>Edition<br/>Module overhaul"]
        Rust21["Rust 2021<br/>Edition<br/>Const generics"]
        Rust24["Rust 2024<br/>Edition<br/>Async stabilization"]
    end

    C --> Cpp
    C --> Cyclone
    OCaml --> Rust09
    Haskell --> Rust09
    Cyclone --> Rust09
    Cpp --> Rust09
    Hoare --> Rust09
    Rust09 --> Rust10
    Rust10 --> Rust18
    Rust18 --> Rust21
    Rust21 --> Rust24

    style Predecessors fill:#e8f5e9,stroke:#388e3c
    style Origins fill:#e1f5fe,stroke:#0288d1
    style Rust_Evolution fill:#fff3e0,stroke:#f4a261
    style C fill:#e8f5e9,stroke:#388e3c
    style Cpp fill:#e8f5e9,stroke:#388e3c
    style OCaml fill:#e8f5e9,stroke:#388e3c
    style Haskell fill:#e8f5e9,stroke:#388e3c
    style Cyclone fill:#e8f5e9,stroke:#388e3c
    style Hoare fill:#e1f5fe,stroke:#0288d1
    style Rust09 fill:#fff3e0,stroke:#f4a261
    style Rust10 fill:#fff3e0,stroke:#f4a261
    style Rust18 fill:#fff3e0,stroke:#f4a261
    style Rust21 fill:#fff3e0,stroke:#f4a261
    style Rust24 fill:#fff3e0,stroke:#f4a261
```

### The Problem Rust Solves

Rust was created to address memory safety issues in C++ while maintaining
control and performance:

| Issue | C++ | Rust |
|-------|-----|------|
| **Use after free** | Undefined behavior | Compile-time error |
| **Dangling pointers** | Undefined behavior | Compile-time error |
| **Data races** | Undefined behavior | Compile-time error |
| **Null pointer** | Segfault / UB | `Option<T>` type |
| **Memory leaks** | Possible (manual) | Compile-time detected |
| **Performance** | Manual management | Same as C/C++ |

---

## Key Ideas

### Ownership

Each value has a single **owner**; when the owner goes out of scope,
the value is dropped:

```rust
// Simple ownership transfer (move)
let s1 = String::from("hello");
let s2 = s1;  // s1 is moved, no longer valid
// println!("{}", s1);  // Error: use of moved value

// Copy types (implement Copy trait)
let x = 5;
let y = x;  // x is copied, still valid
println!("{}", x);  // OK
```

**Ownership Rules:**
1. Each value has an owner
2. There can only be one owner at a time
3. When the owner goes out of scope, the value is dropped

Dropping is governed by the `Drop` trait, whose `drop` method runs automatically
when a value goes out of scope. For composite values where ownership of fields
can be transferred independently, the compiler tracks **drop flags** to decide
which fields still need dropping at scope exit.

#### Drop Flags

Partial moves mean `drop` cannot always be decided statically for every field.
The compiler inserts hidden boolean **drop flags** into the stack frame (outside
`size_of`); at scope exit each field's flag is consulted, and LLVM usually
elides the check entirely when the result is provable.

```mermaid
flowchart LR
    subgraph STACK["Stack frame"]
        VAR["Variable b: { name: String, payload: Vec }"]
        FLAGS["Drop flags — hidden bitmask,<br/>not included in size_of"]
    end

    subgraph CHECK["Check on scope exit"]
        Q1{"flag name == 1?"}
        Q2{"flag payload == 1?"}
        DN["drop(name)"]
        DP["drop(payload)"]
        SKIP["skip"]
    end

    VAR --- FLAGS
    FLAGS --> Q1
    FLAGS --> Q2
    Q1 -->|"yes"| DN
    Q1 -->|"no"| SKIP
    Q2 -->|"yes"| DP
    Q2 -->|"no"| SKIP

    style STACK fill:#fff3e0,stroke:#e07b39,color:#000
    style CHECK fill:#fff3e0,stroke:#e07b39,color:#000
    style VAR fill:#ffffff,stroke:#e07b39,color:#000
    style FLAGS fill:#fff9c4,stroke:#fbc02d,color:#000
    style Q1 fill:#fff9c4,stroke:#fbc02d,color:#000
    style Q2 fill:#fff9c4,stroke:#fbc02d,color:#000
    style DN fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style DP fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style SKIP fill:#fafafa,stroke:#9e9e9e,color:#000
```

### Borrowing and References

You can **borrow** a value without taking ownership:

```rust
// Immutable borrow (multiple allowed)
let s = String::from("hello");
let r1 = &s;  // OK
let r2 = &s;  // OK
// let r3 = &mut s;  // Error: cannot borrow as mutable

// Mutable borrow (only one allowed)
let mut s = String::from("hello");
let r1 = &mut s;
r1.push_str(" world");
// let r2 = &s;  // Error: cannot borrow as immutable
```

**Borrow Checker Rules:**
- Any number of immutable borrows OR exactly one mutable borrow
- Borrows must last no longer than the owner

#### Aliasing Models: Stacked vs. Tree Borrows

Rust's aliasing rules give LLVM `noalias` annotations so it can optimize
aggressively (similar to C's `restrict`, but stricter). The original
**Stacked Borrows** model proved too strict for legitimate raw-pointer
code, so Rust is migrating to the more permissive **Tree Borrows** model
based on a tree of borrow provenances.

```mermaid
flowchart TD
    subgraph SB["Stacked Borrows"]
        SB1["Each reference gets a tag,<br/>tags live on a stack"]
        SB2["New &amp;mut → push tag on top"]
        SB3["Old tag used over new → UB"]
        SB_LIMIT["Too strict: forbids legal<br/>patterns with raw pointers"]
        SB1 --> SB2 --> SB3 --> SB_LIMIT
    end

    subgraph TB["Tree Borrows"]
        TB1["Each reference is a node<br/>in a provenance tree"]
        TB2["New &amp;mut → child node,<br/>parent is not invalidated"]
        TB3["Access through parent allowed<br/>if child is inactive"]
        TB_ADV["Permits self-referential via Pin,<br/>intrusive lists"]
        TB1 --> TB2 --> TB3 --> TB_ADV
    end

    subgraph LLVM_ALIAS["What this gives LLVM"]
        NOALIAS["noalias on parameters —<br/>references do not overlap"]
        RESTRICT["Aggressive optimizations,<br/>like C's restrict but stricter"]
        NOALIAS --> RESTRICT
    end

    SB_LIMIT -->|"replaced by"| TB
    TB_ADV --> NOALIAS

    style SB fill:#fff3e0,stroke:#e07b39,color:#000
    style TB fill:#fff3e0,stroke:#e07b39,color:#000
    style LLVM_ALIAS fill:#fff3e0,stroke:#e07b39,color:#000
    style SB1 fill:#ffebee,stroke:#f44336,color:#b71c1c
    style SB2 fill:#ffebee,stroke:#f44336,color:#b71c1c
    style SB3 fill:#ffebee,stroke:#f44336,color:#b71c1c
    style SB_LIMIT fill:#ffebee,stroke:#f44336,color:#b71c1c
    style TB1 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style TB2 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style TB3 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style TB_ADV fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style NOALIAS fill:#fafafa,stroke:#9e9e9e,color:#000
    style RESTRICT fill:#fafafa,stroke:#9e9e9e,color:#000
```

#### Strict Provenance

Casting a pointer to `usize` and back loses **provenance** and can become
silent UB under `-O2` because LLVM's alias analysis assumes that distinct
provenances never refer to the same object. **Strict Provenance** replaces
such casts with `.addr()` / `.with_addr()`, keeping provenance attached
so alias analysis stays sound.

```mermaid
flowchart LR
    subgraph OLD["Old way — risk of UB"]
        OP1["let p: *const T = &amp;x as *const T"]
        OP2["let n = p as usize<br/>(provenance lost)"]
        OP3["let q = (n+4) as *const T<br/>(no provenance, UB on deref)"]
        OP1 --> OP2 --> OP3
    end

    subgraph NEW["Strict Provenance API"]
        NP1["let p: *const T = &amp;x as *const T"]
        NP2["let addr = p.addr()<br/>(address only)"]
        NP3["let q = p.with_addr(addr+4)<br/>(provenance preserved)"]
        NP1 --> NP2 --> NP3
    end

    subgraph WHY["Why LLVM cares"]
        AA["Alias Analysis:<br/>different provenance = different objects"]
        OPT["GVN and LICM reorder<br/>reads and writes"]
        BUG["Broken usize cast =<br/>hidden UB only at -O2"]
        AA --> OPT --> BUG
    end

    OP3 -.->|"causes"| BUG
    NP3 -.->|"protects from"| BUG

    style OLD fill:#fff3e0,stroke:#e07b39,color:#000
    style NEW fill:#fff3e0,stroke:#e07b39,color:#000
    style WHY fill:#fff3e0,stroke:#e07b39,color:#000
    style OP1 fill:#ffebee,stroke:#f44336,color:#b71c1c
    style OP2 fill:#ffebee,stroke:#f44336,color:#b71c1c
    style OP3 fill:#ffebee,stroke:#f44336,color:#b71c1c
    style NP1 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style NP2 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style NP3 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style AA fill:#fafafa,stroke:#9e9e9e,color:#000
    style OPT fill:#fafafa,stroke:#9e9e9e,color:#000
    style BUG fill:#ffebee,stroke:#f44336,color:#b71c1c
```

### Lifetimes

Lifetimes ensure references remain valid:

```rust
// Explicit lifetime annotation
fn longest<'a>(x: &'a str, y: &'a str) -> &'a str {
    if x.len() > y.len() { x } else { y }
}

// Lifetime elision (compiler infers)
fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();
    for (i, &byte) in bytes.iter().enumerate() {
        if byte == b' ' {
            return &s[0..i];
        }
    }
    &s[..]
}

// Static lifetime
let s: &'static str = "I live forever";
```

### Zero-Cost Abstractions

High-level features compile to efficient code:

```rust
// Iterator: high-level, no runtime overhead
fn sum_squares(numbers: &[i32]) -> i32 {
    numbers.iter()
        .map(|x| x * x)
        .sum()
}
// Compiles to same code as hand-written loop

// Generics: monomorphization at compile time
fn identity<T>(x: T) -> T { x }
// Compiles to specialized versions for each type used
```

#### Compilation Pipeline

Rust compiles through several well-separated stages. The frontend lowers
source to HIR and MIR; the borrow checker, const evaluator and drop-flag
insertion all run on MIR; LLVM then optimizes and emits a native binary.
MIRI is a separate interpreter over MIR used in tests to catch undefined
behavior that the borrow checker cannot.

```mermaid
flowchart TD
    subgraph RUST["🦀 RUST COMPILER PATH"]
        R1["📄 main.rs<br/>source code file"]
        R2["🔤 LEXER / TOKENIZER<br/>text → tokens<br/>─────────────────<br/>fn main ( ) { let x = 1 + 1 ; }"]
        R3["🌳 PARSER → AST<br/>Abstract Syntax Tree<br/>─────────────────<br/>FnDef → LetStmt → BinOp +"]
        R4["⬆️ HIR<br/>High-level IR<br/>─────────────────<br/>• macro expansion<br/>  vec![] → Vec::new + push<br/>• desugaring<br/>  for → loop + match<br/>  ? → match Ok/Err"]
        R5["⚙️ MIR<br/>Mid-level IR<br/>─────────────────<br/>• only primitive steps<br/>• no loops — only goto<br/>• basic blocks"]
        R5A["🔒 BORROW CHECKER<br/>Non-Lexical Lifetimes<br/>─────────────────<br/>• ownership<br/>• lifetimes<br/>• borrowing rules"]
        R5B["📐 CONST EVALUATOR<br/>─────────────────<br/>fib(30) → 832040<br/>computed here"]
        R5C["🚩 DROP FLAGS<br/>─────────────────<br/>inserts flags<br/>for what and when to drop"]
        R5D["🔬 MIRI<br/>cargo miri run<br/>─────────────────<br/>interprets MIR<br/>catches UB at test time"]
        R6["📦 LLVM IR<br/>─────────────────<br/>• noalias from Stacked Borrows<br/>• Niche Opt: Option‹&amp;T› = &amp;T<br/>• monomorphization of generics"]
        R7["⚡ LLVM OPTIMIZER<br/>─────────────────<br/>• constant folding: 1+1 → 2<br/>• inlining<br/>• dead code elimination<br/>• vectorization → SIMD"]
        R8["🖥️ CODEGEN<br/>─────────────────<br/>x86_64 / ARM64 / WASM"]
        R9["✅ binary<br/>./main<br/>native, no runtime"]
    end

    R1 --> R2 --> R3 --> R4 --> R5
    R5 --- R5A
    R5 --- R5B
    R5 --- R5C
    R5 -.- R5D
    R5 --> R6 --> R7 --> R8 --> R9

    style RUST fill:#fff3e0,stroke:#e07b39,color:#000
    style R1  fill:#ffffff,stroke:#e07b39,color:#000
    style R2  fill:#fafafa,stroke:#9e9e9e,color:#000
    style R3  fill:#fafafa,stroke:#9e9e9e,color:#000
    style R4  fill:#fafafa,stroke:#9e9e9e,color:#000
    style R5  fill:#fff9c4,stroke:#fbc02d,color:#000
    style R5A fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style R5B fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style R5C fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style R5D fill:#ffebee,stroke:#f44336,color:#b71c1c
    style R6  fill:#fafafa,stroke:#9e9e9e,color:#000
    style R7  fill:#fafafa,stroke:#9e9e9e,color:#000
    style R8  fill:#fafafa,stroke:#9e9e9e,color:#000
    style R9  fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
```

#### MIR: Basic Blocks and the Borrow Checker

MIR is a control-flow graph of **basic blocks**, each ending in an explicit
terminator (`goto`, `switchInt`, `return`, `drop`). A `while` loop becomes
four blocks; the borrow checker reasons over this graph, which is exactly
why **NLL** (Non-Lexical Lifetimes) accepts patterns the old AST-level
checker rejected.

```rust
const fn fib(n: u32) -> u64 {
    let (mut a, mut b) = (0u64, 1u64);
    let mut i = 0;
    while i < n { let t = a + b; a = b; b = t; i += 1; }
    a
}
```

```mermaid
flowchart TD
    subgraph BB0["BB0 — entry"]
        S0["a = 0u64"]
        S1["b = 1u64"]
        S2["i = 0u32"]
        T0["goto BB1"]
        S0 --> S1 --> S2 --> T0
    end

    subgraph BB1["BB1 — loop condition"]
        S3["cond = i &lt; n"]
        T1["switchInt(cond):<br/>true → BB2, false → BB3"]
        S3 --> T1
    end

    subgraph BB2["BB2 — loop body"]
        S4["t = a + b"]
        S5["a = b"]
        S6["b = t"]
        S7["i = i + 1"]
        T2["goto BB1"]
        S4 --> S5 --> S6 --> S7 --> T2
    end

    subgraph BB3["BB3 — exit"]
        T3["return a"]
    end

    T0 --> BB1
    T1 -->|"true"| BB2
    T1 -->|"false"| BB3
    T2 --> BB1

    style BB0 fill:#fff3e0,stroke:#e07b39,color:#000
    style BB1 fill:#fff3e0,stroke:#e07b39,color:#000
    style BB2 fill:#fff3e0,stroke:#e07b39,color:#000
    style BB3 fill:#fff3e0,stroke:#e07b39,color:#000
    style S0 fill:#ffffff,stroke:#e07b39,color:#000
    style S1 fill:#ffffff,stroke:#e07b39,color:#000
    style S2 fill:#ffffff,stroke:#e07b39,color:#000
    style T0 fill:#fafafa,stroke:#9e9e9e,color:#000
    style S3 fill:#fff9c4,stroke:#fbc02d,color:#000
    style T1 fill:#fff9c4,stroke:#fbc02d,color:#000
    style S4 fill:#fff9c4,stroke:#fbc02d,color:#000
    style S5 fill:#fff9c4,stroke:#fbc02d,color:#000
    style S6 fill:#fff9c4,stroke:#fbc02d,color:#000
    style S7 fill:#fff9c4,stroke:#fbc02d,color:#000
    style T2 fill:#fafafa,stroke:#9e9e9e,color:#000
    style T3 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
```

After borrow checking, MIR is lowered to LLVM IR where the same graph
structure appears as `br`, `switch` and `ret` instructions.

### Pattern Matching

Exhaustive, expressive pattern matching:

```rust
enum Message {
    Quit,
    Move { x: i32, y: i32 },
    Write(String),
    ChangeColor(i32, i32, i32),
}

fn process(msg: Message) {
    match msg {
        Message::Quit => println!("Quit"),
        Message::Move { x, y } => println!("Move to {}, {}", x, y),
        Message::Write(text) => println!("{}", text),
        Message::ChangeColor(r, g, b) => println!("Color: {}, {}, {}", r, g, b),
    }
}

// If-let for partial matching
if let Message::Write(text) = msg {
    println!("Writing: {}", text)
}

// While-let for iteration
while let Some(i) = iterator.next() {
    println!("{}", i);
}
```

---

## Key Features In Depth

### 01. Ownership

| Section | Content |
| :--- | :--- |
| **Description** | Each value has exactly one owner; when the owner goes out of scope, the value is dropped. |
| **API Purpose** | Memory safety without garbage collection. |
| **Terminology** | Owner, move, copy, clone, `Drop` trait. |

Read more: **[Detailed description and examples](./01-ownership.md)**  
Examples: [Variables & Types](../../../examples/rust/02-variables-and-types/README.md)

---

### 02. Borrowing

| Section | Content |
| :--- | :--- |
| **Description** | Temporary access to a value without taking ownership. |
| **API Purpose** | Safe sharing and mutation of data. |
| **Terminology** | Reference (`&T`), mutable reference (`&mut T`), borrow checker. |

Read more: **[Detailed description and examples](./02-borrowing.md)**  
Examples: [Variables & Types](../../../examples/rust/02-variables-and-types/README.md)

---

### 03. Lifetimes

| Section | Content |
| :--- | :--- |
| **Description** | Compile-time annotations ensuring references never outlive their data. |
| **API Purpose** | Guaranteeing reference validity across function boundaries. |
| **Terminology** | Lifetime parameter (`'a`), elision, `'static`. |

Read more: **[Detailed description and examples](./03-lifetimes.md)**  
Examples: [Variables & Types](../../../examples/rust/02-variables-and-types/README.md)

---

### 04. Traits

| Section | Content |
| :--- | :--- |
| **Description** | Shared behavior definitions that types implement implicitly. |
| **API Purpose** | Polymorphism and code reuse through contracts. |
| **Terminology** | Trait, `impl`, trait bound, associated type, `dyn Trait`. |

Read more: **[Detailed description and examples](./04-traits.md)**  
Examples: [OOP/Modules](../../../examples/rust/06-oop-modules/README.md)

---

### 05. Pattern Matching

| Section | Content |
| :--- | :--- |
| **Description** | Exhaustive matching on enums, structs, literals, and guards. |
| **API Purpose** | Destructuring data and handling all cases. |
| **Terminology** | `match`, arm, guard, `if let`, `while let`. |

Read more: **[Detailed description and examples](./05-pattern-matching.md)**  
Examples: [Data Structures](../../../examples/rust/05-data-structures/README.md)

---

### 06. Error Handling

| Section | Content |
| :--- | :--- |
| **Description** | `Result` and `Option` enums with the `?` operator for propagation. |
| **API Purpose** | Explicit, composable error handling. |
| **Terminology** | `Result<T, E>`, `Option<T>`, `?`, `unwrap`, `panic!`. |

Read more: **[Detailed description and examples](./06-error-handling.md)**  
Examples: [Error Handling](../../../examples/rust/07-error-handling/README.md)

---

### 07. Generics

| Section | Content |
| :--- | :--- |
| **Description** | Type-safe code reuse with zero runtime cost via monomorphization. |
| **API Purpose** | Reusable data structures and algorithms. |
| **Terminology** | Type parameter, trait bound, const generics, monomorphization. |

Read more: **[Detailed description and examples](./07-generics.md)**  
Examples: [FP Features](../../../examples/rust/07-fp-features/README.md)

---

### 08. Concurrency

| Section | Content |
| :--- | :--- |
| **Description** | Fearless concurrency via ownership — data races prevented at compile time. |
| **API Purpose** | Safe concurrent programming with `Send` and `Sync` traits. |
| **Terminology** | `thread`, `move` closure, `mpsc`, `Arc`, `Mutex`, `Send`, `Sync`. |

Read more: **[Detailed description and examples](./08-concurrency.md)**  
Examples: [Concurrency](../../../examples/rust/08-concurrency/README.md)

---

## Core Features

### Structs and Enums

```rust
// Struct with named fields
struct Point {
    x: f64,
    y: f64,
}

// Tuple struct
struct Color(i32, i32, i32);

// Unit-like struct
struct Unit;

// Enum with data variants
enum Option<T> {
    Some(T),
    None,
}

enum Result<T, E> {
    Ok(T),
    Err(E),
}

// Pattern matching with struct
let p = Point { x: 0.0, y: 0.0 };
let Point { x, y } = p;  // Destructuring

// Enum with methods
impl Option<i32> {
    fn unwrap_or_default(self) -> i32 {
        match self {
            Some(v) => v,
            None => 0,
        }
    }
}
```

#### Niche Optimization

Rust enums store their discriminant in unused bit patterns ("niches") of
their payload whenever possible. The result: `Option<&T>` is the size of
one pointer (`None` is the null address), and `Option<NonZeroU8>` is one
byte. The same trick stacks recursively — `Option<Option<bool>>` still
fits in a single byte.

```mermaid
flowchart LR
    subgraph TYPES["Type and its niche"]
        REF["&amp;T — no null address,<br/>niche = 0x0"]
        NZU8["NonZeroU8 — range 1..=255,<br/>niche = 0"]
        BOOL["bool — values 0 and 1,<br/>niche = 2..=255"]
        CHAR["char — valid 0..=0x10FFFF,<br/>niche = the rest"]
    end

    subgraph WRAP["Option without discriminant"]
        O_REF["Option&lt;&amp;T&gt; = 1 pointer,<br/>None = 0x0"]
        O_NZU8["Option&lt;NonZeroU8&gt; = 1 byte,<br/>None = 0"]
        O_BOOL["Option&lt;bool&gt; = 1 byte,<br/>None = 2"]
    end

    subgraph NESTED["Nesting"]
        OO_BOOL["Option&lt;Option&lt;bool&gt;&gt; = 1 byte,<br/>None-outer = 3, None-inner = 2"]
        RES["Result&lt;Option&lt;&amp;T&gt;, ()&gt; = 1 pointer,<br/>Err = 1"]
    end

    REF --> O_REF
    NZU8 --> O_NZU8
    BOOL --> O_BOOL
    O_BOOL --> OO_BOOL
    O_REF --> RES

    style TYPES fill:#fff3e0,stroke:#e07b39,color:#000
    style WRAP fill:#fff3e0,stroke:#e07b39,color:#000
    style NESTED fill:#fff3e0,stroke:#e07b39,color:#000
    style REF fill:#ffffff,stroke:#e07b39,color:#000
    style NZU8 fill:#ffffff,stroke:#e07b39,color:#000
    style BOOL fill:#ffffff,stroke:#e07b39,color:#000
    style CHAR fill:#ffffff,stroke:#e07b39,color:#000
    style O_REF fill:#fff9c4,stroke:#fbc02d,color:#000
    style O_NZU8 fill:#fff9c4,stroke:#fbc02d,color:#000
    style O_BOOL fill:#fff9c4,stroke:#fbc02d,color:#000
    style OO_BOOL fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style RES fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
```

Across crate boundaries the compiler treats foreign layouts as **unstable**,
so niche optimization only applies when the outer enum has variants with
no payload. A wrapper that adds a non-trivial variant pays a separate
discriminant byte instead.

```mermaid
flowchart TD
    subgraph CRATE_A["Crate A"]
        INNER["enum Inner { A, B }<br/>unstable layout, niche 2..=255"]
    end

    subgraph CRATE_B["Crate B"]
        OUTER["enum Outer { V1(Inner), V2(Inner) }"]
        RESULT["Size = 2 bytes,<br/>separate discriminant,<br/>compiler is conservative"]
        OUTER2["enum Outer2 { V1, V2(Inner) }"]
        RESULT2["Size = 1 byte,<br/>V1 = value 2 in Inner's byte"]
    end

    INNER --> OUTER --> RESULT
    INNER --> OUTER2 --> RESULT2

    style CRATE_A fill:#fff3e0,stroke:#e07b39,color:#000
    style CRATE_B fill:#fff3e0,stroke:#e07b39,color:#000
    style INNER fill:#ffffff,stroke:#e07b39,color:#000
    style OUTER fill:#fafafa,stroke:#9e9e9e,color:#000
    style OUTER2 fill:#fafafa,stroke:#9e9e9e,color:#000
    style RESULT fill:#ffebee,stroke:#f44336,color:#b71c1c
    style RESULT2 fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
```

### Traits

Rust's way of sharing behavior (like interfaces/type classes):

```rust
// Trait definition
trait Drawable {
    fn draw(&self);
    fn area(&self) -> f64;
}

// Implementation for type
struct Circle {
    radius: f64,
}

impl Drawable for Circle {
    fn draw(&self) {
        println!("Drawing circle r={}", self.radius);
    }

    fn area(&self) -> f64 {
        std::f64::consts::PI * self.radius * self.radius
    }
}

// Trait bounds (generics with constraints)
fn draw_all<T: Drawable>(shapes: &[T]) {
    for shape in shapes {
        shape.draw();
    }
}

// Or "where" clause
fn draw_all2<T>(shapes: &[T])
where
    T: Drawable,
{
    // ...
}

// Default implementation
trait Summary {
    fn summarize(&self) -> String {
        String::from("(Read more...)")
    }
}

// Associated types (type family)
trait Iterator {
    type Item;
    fn next(&mut self) -> Option<Self::Item>;
}
```

### Error Handling

No exceptions — explicit `Result` and `Option`:

```rust
// Returning errors
fn read_file(path: &str) -> Result<String, std::io::Error> {
    let content = std::fs::read_to_string(path)?;
    Ok(content)
}

// Propagating with `?`
fn parse_number(s: &str) -> Result<i32, std::num::ParseIntError> {
    let n: i32 = s.parse()?;
    Ok(n * 2)
}

// Chaining results
fn process() -> Result<String, Box<dyn std::error::Error>> {
    let content = read_file("data.txt")?;
    let number: i32 = content.parse()?;
    Ok(format!("Double: {}", number * 2))
}

// Handling errors
match result {
    Ok(value) => println!("Success: {}", value),
    Err(e) => eprintln!("Error: {}", e),
}

// Panic for unrecoverable errors
panic!("Something went wrong!");
```

### Generics

```rust
// Generic function
fn largest<T: PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];
    for item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

// Generic struct
struct Point<T> {
    x: T,
    y: T,
}

impl<T> Point<T> {
    fn x(&self) -> &T { &self.x }
}

// Method with specific type
impl Point<f32> {
    fn distance_from_origin(&self) -> f32 {
        (self.x.powi(2) + self.y.powi(2)).sqrt()
    }
}

// Const generics (Rust 1.51+)
struct Array<T, const N: usize> {
    data: [T; N],
}

// Generic associated types (GATs, Rust 1.65+)
trait StreamingIterator {
    type Item<'a> where Self: 'a;
    fn next<'a>(&'a mut self) -> Option<Self::Item<'a>>;
}
```

#### Monomorphization

Every concrete use of a generic produces a specialized copy: `process<i32>`,
`process<String>`, `process<f64>`. When the type parameter is unused in
the body, **polymorphization** collapses copies; **share-generics** lets
downstream crates reuse upstream instantiations to fight binary bloat.

```mermaid
flowchart TD
    GEN["fn process&lt;T&gt;(x: T)<br/>one function in source"]

    M1["process_i32(x: i32)"]
    M2["process_String(x: String)"]
    M3["process_f64(x: f64)"]

    POLY["polymorphization:<br/>T unused in body —<br/>one copy for all"]
    SHARE["share-generics:<br/>crates reuse each<br/>other's instantiations"]

    GEN --> M1 & M2 & M3
    M1 & M2 & M3 -->|"T not needed in body"| POLY
    M1 & M2 & M3 -->|"across crates"| SHARE

    style GEN fill:#ffffff,stroke:#e07b39,color:#000
    style M1 fill:#fafafa,stroke:#9e9e9e,color:#000
    style M2 fill:#fafafa,stroke:#9e9e9e,color:#000
    style M3 fill:#fafafa,stroke:#9e9e9e,color:#000
    style POLY fill:#fff9c4,stroke:#fbc02d,color:#000
    style SHARE fill:#fff9c4,stroke:#fbc02d,color:#000
```

### Iterators and Closures

```rust
// Closures capture environment
let x = 4;
let equal_to_x = |z| z == x;  // captures x by reference

let x = vec![1, 2, 3];
let consumes_x = |z| z == x.len();  // captures x by value (move)

// Iterator chain
let numbers = vec![1, 2, 3, 4, 5];
let sum: i32 = numbers.iter()
    .filter(|&&x| x % 2 == 0)
    .map(|x| x * x)
    .sum();

// Collect into different types
let evens: Vec<i32> = numbers.into_iter()
    .filter(|x| x % 2 == 0)
    .collect();

// Lazy evaluation
let v: Vec<_> = (0..10).map(|x| x * x).collect();
```

### Concurrency

Fearless concurrency via ownership:

```rust
// Thread with move closure
use std::thread;

let data = vec![1, 2, 3, 4, 5];

thread::spawn(move || {
    println!("Here's a vector: {:?}", data);
    // data is moved into this thread
}).join().unwrap();

// Message passing (channels)
use std::sync::mpsc;

let (tx, rx) = mpsc::channel();
thread::spawn(move || {
    tx.send(42).unwrap();
});

let received = rx.recv().unwrap();

// Shared state with Arc<Mutex<T>>
use std::sync::{Arc, Mutex};

let counter = Arc::new(Mutex::new(0));
let mut handles = vec![];

for _ in 0..10 {
    let counter = Arc::clone(&counter);
    let handle = thread::spawn(move || {
        let mut num = counter.lock().unwrap();
        *num += 1;
    });
    handles.push(handle);
}

for handle in handles {
    handle.join().unwrap();
}
```

---

## Modern Rust Features

### Async/Await (Rust 1.39+)

```rust
use std::time::Duration;

async fn hello() -> String {
    tokio::time::sleep(Duration::from_secs(1)).await;
    "Hello, world!".to_string()
}

#[tokio::main]
async fn main() {
    let result = hello().await;
    println!("{}", result);
}
```

#### Compiler Lowering: async → state machine

`async fn` is compiled into a state-machine enum implementing
`Future::poll`. Locals that live across `.await` become enum fields,
which is why a future holding a 1 KB stack buffer is itself at least
1 KB; non-overlapping lifetimes can share a slot, and `Pin` keeps the
address stable so self-references in the enum remain valid.

```mermaid
flowchart LR
    subgraph ASYNC_SRC["async fn big()"]
        SRC2["let buf = [0u8; 1024];<br/>small().await;<br/>println!(buf.len())"]
    end

    subgraph TRANSFORM["Compiler expands into enum"]
        E0["State0 — start"]
        E1["State1 — { buf: 1024 B, small_fut }<br/>lives across .await"]
        E2["State2 — { buf }<br/>after small returns"]
        EDONE["Done"]
        E0 --> E1 --> E2 --> EDONE
    end

    subgraph POLL["impl Future"]
        POLL_FN["fn poll(Pin&lt;&amp;mut Self&gt;, cx):<br/>match self.state { ... }"]
    end

    subgraph SIZE["Future size"]
        BIG_SIZE["size_of &gt; 1024 B —<br/>buf sits inside the enum"]
        OPT_LAY["generator layout opt:<br/>non-overlapping lifetimes<br/>share a slot"]
        BOX_FUT["Box&lt;dyn Future&gt; pins the<br/>address — required for Pin"]
    end

    SRC2 --> E0
    TRANSFORM --> POLL_FN
    POLL_FN --> BIG_SIZE --> OPT_LAY
    POLL_FN --> BOX_FUT

    style ASYNC_SRC fill:#fff3e0,stroke:#e07b39,color:#000
    style TRANSFORM fill:#fff3e0,stroke:#e07b39,color:#000
    style POLL fill:#fff3e0,stroke:#e07b39,color:#000
    style SIZE fill:#fff3e0,stroke:#e07b39,color:#000
    style SRC2 fill:#ffffff,stroke:#e07b39,color:#000
    style E0 fill:#fafafa,stroke:#9e9e9e,color:#000
    style E1 fill:#fff9c4,stroke:#fbc02d,color:#000
    style E2 fill:#fff9c4,stroke:#fbc02d,color:#000
    style EDONE fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style POLL_FN fill:#fff9c4,stroke:#fbc02d,color:#000
    style BIG_SIZE fill:#fafafa,stroke:#9e9e9e,color:#000
    style OPT_LAY fill:#fafafa,stroke:#9e9e9e,color:#000
    style BOX_FUT fill:#fafafa,stroke:#9e9e9e,color:#000
```

### Const Generics (Rust 1.51+)

```rust
struct Array<T, const N: usize> {
    data: [T; N],
}

impl<T: Default, const N: usize> Default for Array<T, N> {
    fn default() -> Self {
        Self {
            data: [T::default(); N],
        }
    }
}
```

### Const Evaluation

`const fn` is executed at compile time by a **MIR interpreter** with its
own memory model and UB checks. The interpreter walks the same basic-block
graph used by codegen (see [MIR: Basic Blocks](#mir-basic-blocks-and-the-borrow-checker)
above), then emits the result as a constant directly into LLVM IR.

```mermaid
flowchart TD
    subgraph MIR_LEVEL["MIR graph of the function"]
        BB0_C["BB0: a=0, b=1, i=0, goto BB1"]
        BB1_C["BB1: cond = i &lt; n,<br/>switchInt → BB2 / BB3"]
        BB2_C["BB2: t=a+b; a=b; b=t;<br/>i+=1; goto BB1"]
        BB3_C["BB3: return a"]

        BB0_C --> BB1_C
        BB1_C -->|"true"| BB2_C
        BB1_C -->|"false"| BB3_C
        BB2_C --> BB1_C
    end

    subgraph CONST_VM["Const Evaluator"]
        VM["MIR interpreter —<br/>executes rather than compiles"]
        MEM["Memory model, UB checks,<br/>own allocator"]
        OUT["Result: FIB30 = 832040u64"]

        VM --> MEM --> OUT
    end

    BB3_C -->|"const fn —<br/>goes to interpreter"| VM
    OUT -.->|"embedded as constant<br/>in LLVM IR"| LLVM_IR_C["LLVM IR"]

    style MIR_LEVEL fill:#fff3e0,stroke:#e07b39,color:#000
    style CONST_VM fill:#fff3e0,stroke:#e07b39,color:#000
    style BB0_C fill:#fff9c4,stroke:#fbc02d,color:#000
    style BB1_C fill:#fff9c4,stroke:#fbc02d,color:#000
    style BB2_C fill:#fff9c4,stroke:#fbc02d,color:#000
    style BB3_C fill:#fff9c4,stroke:#fbc02d,color:#000
    style VM fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style MEM fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style OUT fill:#e8f5e9,stroke:#4caf50,color:#1b5e20
    style LLVM_IR_C fill:#fafafa,stroke:#9e9e9e,color:#000
```

### Let-Else (Rust 1.65+)

```rust
let Some(x) = opt else { return };
```

---

## Ecosystem and Tools

| Tool | Purpose |
|------|---------|
| **cargo** | Package manager, build tool, test runner |
| **rustup** | Toolchain installer |
| **rustfmt** | Code formatter |
| **clippy** | Linter with suggestions |
| **miri** | MIR interpreter, detects UB at test time (`cargo miri run`) |
| **rust-analyzer** | Language server for IDEs |

```bash
# New project
cargo new my_project

# Build
cargo build

# Run
cargo run

# Test
cargo test

# Add dependency
cargo add serde

# Format
cargo fmt

# Lint
cargo clippy
```

### Major Crates

| Crate | Domain |
|-------|--------|
| **serde** | Serialization/deserialization |
| **tokio** | Async runtime |
| **clap** | Command-line parsing |
| **reqwest** | HTTP client |
| **axum** | Web framework |
| **tracing** | Structured logging |
| **anyhow** | Error handling |
| **thiserror** | Error deriving |

---

## Influence

### Languages Influenced

| Language | Rust influence |
|-----------|-----------------|
| **Swift** | Ownership-inspired borrowing (Swift 5) |
| **Nim** | Ownership system proposal |
| **Carbon** | Interoperable with C++, Rust-inspired |
| **V** | Option types, error handling |
| **C++** | Borrowing checker (Lifetimes library) |

### Systems Programming Impact

Rust has changed systems programming:
- **Linux kernel** — first non-C language accepted
- **Microsoft** — rewriting Windows components
- **AWS** — Firecracker microVM, Lambda runtime
- **Cloudflare** — networking infrastructure

---

## Strengths and Weaknesses

### Strengths

| Strength | Detail |
|----------|--------|
| **Memory safety** | Compile-time guarantees, no GC |
| **Concurrency** | Data races caught at compile time |
| **Performance** | Comparable to C/C++ |
| **Package manager** | Cargo is excellent |
| **Tooling** | rustfmt, clippy, rust-analyzer |
| **Community** | Helpful, RFC-driven development |

### Weaknesses

| Weakness | Detail |
|----------|--------|
| **Learning curve** | Borrow checker, lifetimes take time |
| **Compilation time** | Slower than Go, improving |
| **IDE maturity** | Good, but less than Java/TypeScript |
| **Ecosystem size** | Smaller than Python/JavaScript |
| **Async complexity** | Multiple runtimes, learning curve |

---

## Code Examples

See [`examples/rust/`](../../examples/rust/index.md) for runnable code:

| Example | Description |
|---------|-------------|
| [01 Hello World](../../examples/rust/01-hello-world/index.md) | Cargo, basics, printing |
| [02 Variables & Types](../../examples/rust/02-variables-and-types/index.md) | Ownership, mutability, types |
| [03 Functions](../../examples/rust/03-functions/index.md) | Closures, higher-order functions |
| [04 Control Flow](../../examples/rust/04-control-flow/index.md) | Loops, match, if-let |
| [05 Data Structures](../../examples/rust/05-data-structures/index.md) | Structs, enums, pattern matching |
| [06 OOP/Modules](../../examples/rust/06-oop-modules/index.md) | Traits, impl blocks, modules |

---

## Related Authors

- [Graydon Hoare](../../authors/graydon-hoare.md) — creator of Rust
- [Brendan Eich](../../authors/brendan-eich.md) — influenced Servo project |
- [Bjarne Stroustrup](../../authors/bjarne-stroustrup.md) — C++, Rust's "better C++" goal |

---

## Related Topics

- [Type Systems](../../topics/types/index.md) — ownership, lifetimes |
- [Concurrency](../../topics/concurrency/index.md) — fearless concurrency |
- [Paradigms](../../topics/paradigms/index.md) — Rust as multi-paradigm |
- [Architecture](../../topics/architecture/index.md) — Rust in systems programming |

---

## Further Reading

| Author | Title | Year | Focus |
|--------|-------|------|-------|
| Klabnik & Nichols | *The Rust Programming Language* | 2019 | Official book, comprehensive |
| Gjengset | *Rust for Rustaceans* | 2020 | Advanced, idiomatic |
| Timm | *Programming Rust* | 2021 | Practical, hands-on |
| McBride | *Rust in Action* | 2021 | Systems programming |

---

## Quotes

> "Rust is a systems programming language that runs blazingly fast,
> prevents segfaults, and guarantees thread safety."
> — Rust Documentation

> "The borrow checker is your friend. It catches bugs before they happen."
> — Anonymous Rustacean

> "Rust doesn't just prevent bugs — it prevents entire classes of bugs."
> — Graydon Hoare

---

*See [Languages Index](../languages/index.md) for other language profiles.*
