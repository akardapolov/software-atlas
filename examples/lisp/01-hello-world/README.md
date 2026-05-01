# Hello World in Lisp

## Overview

A simple Hello World program in Lisp (Common Lisp).

## Code

```lisp
(write-line "Hello, World!")
```

## How to Run

```bash
# Using SBCL
sbcl --script hello.lisp

# Or in REPL
sbcl
* (load "hello.lisp")
```

## Key Concepts

- Prefix notation (not infix like most languages)
- Parentheses delimiting all expressions
- Code as data - programs are Lisp data structures
- Dynamic typing

## Historical Context

Lisp was created by John McCarthy in 1958. Key characteristics:
- Second-oldest high-level language (after Fortran)
- Foundation for functional programming
- Influenced: Scheme, Clojure, Racket, Emacs Lisp
- Code-is-data paradigm ( homoiconicity )

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
