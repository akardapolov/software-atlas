# LISP 1.5 Programmer's Manual

| | |
|---|---|
| **Author** | John McCarthy |
| **Year** | 1962 |
| **Publication** | MIT Press |
| **Topic(s)** | Lisp, functional programming, language specification |

## Summary

The **LISP 1.5 Programmer's Manual** is the definitive reference manual for the first widely used version of Lisp. Written by McCarthy and the MIT AI Lab, it documents the language that defined functional programming for a generation.

## Key Ideas

### Core Lisp Concepts

The manual defines the Lisp 1.5 language:

- **S-expressions** — symbolic expressions as core data structure
- **`eval` and `apply`** — functions that can evaluate and call code
- **Quote** — treat code as data
- **Dynamic typing** — no compile-time type declarations
- **Garbage collection** — automatic memory management (one of the first!)

### `eval` and `apply`

Lisp 1.5 introduced **`eval`** and **`apply`**:

```lisp
(eval '(+ 1 2 3))  ; → 6
(apply (lambda (x y) (+ x y)) '(1 2))  ; → 3
```

This **late binding** approach became foundational for:
- **Dynamic languages** — Ruby, Python, JavaScript
- **REPL** environments — interactive development
- **Metaprogramming** — code that manipulates code

### Garbage Collection

Lisp 1.5 was one of the first languages with **automatic memory management**:

- Programmer never frees memory manually
- System identifies and reclaims unreachable objects
- Foundation for GC in modern languages (Java, Python, Go)

### Homoiconicity

**"Code is data"** principle:
- Programs are S-expressions, data is S-expressions
- `eval` treats code as data
- Metaprogramming becomes natural (macros operate on code as data)

## Historical Significance

### Foundation for AI Research

Lisp 1.5 became the **primary language for AI research**:

- Symbolic reasoning about problems
- Knowledge representation
- Expert systems
- Early natural language processing

### Language Family Foundation

Lisp 1.5 spawned numerous dialects:
- **MacLisp** — Lisp Machines, early Lisp workstations
- **Interlisp** — Xerox Lisp environment
- **Scheme** — minimal Lisp, continuations
- **Common Lisp** — standardization (1984)
- **Emacs Lisp** — extension language for Emacs editor

## Legacy

The LISP 1.5 manual established patterns that became standard:

- **Prefix notation** — `(+ 1 2)` instead of `1 + 2`
- **Parentheses** — extensive nesting
- **Functional thinking** — recursion over loops
- **Interactive development** — REPL-driven workflows

## Connections

- **Preceded by:** [Recursive Functions of Symbolic Expressions (1960)](../papers/mccarthy-1960-lisp.md)
- **Influenced:** Alan Kay (Smalltalk eval/apply), Scheme, Common Lisp
- **Related topic:** [Paradigms](../../topics/paradigms/index.md) · [Lisp language](../../languages/lisp/index.md) (if exists)

## Author

[John McCarthy](../../authors/john-mccarthy.md)
