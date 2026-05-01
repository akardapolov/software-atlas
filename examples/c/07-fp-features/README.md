# FP Features in C

## Overview

C has no built-in functional features — no lambdas, map, filter,
or reduce. However, these patterns can be implemented with
function pointers and libraries. C shows how FP concepts require
explicit implementation.

## Code

See \`main.c\` in this directory.

## How to Run

\`\`\`bash
gcc main.c -o fp && ./fp
\`\`\`

## Key Concepts

- **Function pointers** — enable passing functions as arguments
- **Map implementation** — apply function to each array element
- **Filter implementation** — keep elements satisfying predicate
- **Reduce implementation** — combine elements into one value
- **Function composition** — chaining functions together
- **Currying simulation** — partial application via closures
- **Higher-order functions** — functions taking/returning functions
- **Immutable data** — const for read-only, struct copying

## Historical Context

C (1972) predates functional programming movement.
FP patterns in C are implemented manually with function pointers.
Languages like C++ (2011) added lambdas and functional
features, showing evolution of C into FP-capable language.

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
