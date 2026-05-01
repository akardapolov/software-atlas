# Control Flow in Lisp

## Overview

Lisp uses `cond` for conditionals (if/elif/else) and `dolist`/`dotimes`/`do` for iteration. Recursion is idiomatic for loops — Lisp predates `for` loops. `catch`/`throw` provides non-local exits.

## How to Run

```bash
# Using SBCL
sbcl --script main.lisp

# Using Emacs Lisp (SLIME)
M-x slime-eval-file
```

## Key Concepts

- **cond** — conditional (if/elif/else) with multiple tests
- **Recursion** — primary iteration mechanism, tail recursion supported
- **dolist** — iterate over list elements
- **dotimes** — iterate fixed number of times
- **do** — general iteration with termination
- **case** — switch-like value matching
- **catch/throw** — non-local exit (exception handling)
- **tagbody/go** — labeled control blocks (goto-like)
- **unwind-protect** — ensure cleanup runs (finally clause)

## Historical Context

Lisp (1958) used recursion as primary control flow before `for` loops were standard. Recursion with tail-call optimisation enables efficient iteration without mutable state. The functional approach to iteration influenced all later FP languages.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
