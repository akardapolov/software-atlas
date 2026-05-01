# Data Structures in Lisp

## Overview

Lisp provides **lists** (linked), **vectors** (1D arrays), **alists** (association lists), and **hash tables**. Lisp is homoiconic: code and data share same representation (lists).

## How to Run

```bash
# Using SBCL
sbcl --script main.lisp

# Using Emacs Lisp (SLIME)
M-x slime-eval-file
```

## Key Concepts

- **List** — linked list, `car`/`cdr` for head/tail
- **Vector** — 1D array, O(1) indexing
- **Alist** — association list of `(key . value)` pairs
- **Hash table** — `make-hash-table`, `gethash`, `puthash`
- **Array** — `make-array`, `aset`, `aref` (multi-dimensional)
- **Homoiconicity** — code and data are both lists
- **Cons** — prepend to list (append is O(n), use `nconc`)
- **Quoting** — `'` prevents evaluation, enabling code-as-data
- **Dynamic evaluation** — `eval` for runtime code execution

## Historical Context

Lisp (1958, John McCarthy) pioneered many data structure concepts. Vectors provide efficient O(1) access. Alists pre-date modern hash tables. Homoiconicity enables powerful meta-programming and macros.

For more on Lisp, see [docs/languages/lisp/](../../../docs/languages/lisp/)
