# Ownership

| Section | Content |
| :--- | :--- |
| **Description** | Ownership is Rust's core memory management model. Each value has exactly one owner; when the owner goes out of scope, the value is automatically dropped. This eliminates the need for a garbage collector while preventing memory leaks and use-after-free errors. |
| **API Purpose** | Ensuring memory safety at compile time without runtime garbage collection. |
| **Terminology** | Owner, move, copy, clone, drop, `Copy` trait, `Drop` trait, stack vs heap. |
| **Notes** | Primitive types (`i32`, `f64`, `bool`, `char`) implement `Copy` and are copied by value. Heap-allocated types (`String`, `Vec`, `Box`) are moved by default. After a move, the original variable is no longer valid. |

```mermaid
flowchart LR
    S1["let s1 = String::from(\"hello\")"] -->|"move"| S2["let s2 = s1"]
    S2 -->|"s1 is invalid"| DROP["Drop when s2 goes out of scope"]
    S1 -.->|"use s1"| ERR["Compile Error!"]

    style S1 fill:#e1f5fe,stroke:#0288d1
    style S2 fill:#fff3e0,stroke:#f4a261
    style DROP fill:#e8f5e9,stroke:#388e3c
    style ERR fill:#ffebee,stroke:#c62828
```

## Ownership Rules

1. Each value has a single owner at any time.
2. When the owner goes out of scope, the value is dropped.
3. Ownership can be transferred (moved) to another variable.

## Move Semantics

```rust
fn main() {
    let s1 = String::from("hello");
    let s2 = s1;  // s1 is moved to s2
    // println!("{}", s1);  // ERROR: value borrowed after move
    println!("{}", s2);    // OK
}
```

## Copy Types

Types that implement `Copy` are duplicated rather than moved:

```rust
fn main() {
    let x = 5;
    let y = x;  // x is copied (i32 implements Copy)
    println!("x = {}, y = {}", x, y);  // Both valid
}
```

| Type | Behavior |
|------|----------|
| `i32`, `f64`, `bool`, `char` | `Copy` — duplicated |
| `String`, `Vec`, `Box` | Move — ownership transferred |
| `&T` (reference) | `Copy` — duplicated |
| `&mut T` | Move — ownership transferred |

## Clone

Explicit deep copy for heap-allocated types:

```rust
let s1 = String::from("hello");
let s2 = s1.clone();  // deep copy
println!("s1 = {}, s2 = {}", s1, s2);  // Both valid
```

## Ownership in Functions

```rust
fn take_ownership(s: String) {
    println!("{}", s);
} // s is dropped here

fn main() {
    let s = String::from("hello");
    take_ownership(s);
    // println!("{}", s);  // ERROR: s was moved
}
```

---

Examples: [Variables & Types](../../../examples/rust/02-variables-and-types/README.md)
