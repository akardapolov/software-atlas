# Functions in Lisp

## Overview

Lisp functions are first-class: assignable to variables, storable in data
structures, and passable to other functions. Lisp pioneered concepts
like closures, higher-order functions, and recursion long before "functional
programming" became a term.

## How to Run

```bash
# Using SBCL
sbcl --script main.lisp

# Using Emacs Lisp (SLIME)
M-x slime-eval-file
```

## Key Concepts

- **First-class functions** — functions are objects, assignable to variables
- **Higher-order functions** — `mapcar`, `remove-if`, `fold` (reduce)
- **Lambda** — anonymous functions: `(lambda (x) (* x x))`
- **Closures** — inner functions capture lexical scope
- **Recursion** — primary control flow mechanism
- **Variable arguments** — `&rest` for any number of args
- **Optional arguments** — `&optional` with defaults
- **Composition** — building pipelines of functions

## Historical Context

Lisp (1958, John McCarthy) pioneered many functional concepts:
- First language with recursive functions as primary control flow
- Garbage collection enabled automatic memory management
- Homoiconicity (code = data) enabled powerful meta-programming
- Influenced Scheme, ML, Haskell, and all FP languages

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
