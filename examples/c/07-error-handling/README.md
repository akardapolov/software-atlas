## Overview

C error handling relies on **return codes**, `errno`, and manual
checking. Exceptions are not part of the core language —
they require `setjmp`/`longjmp` or external libraries.

## Code

See \`main.c\` in this directory.

## How to Run

\`\`\`bash
gcc main.c -o error && ./error
\`\`\`

## Key Concepts

- **Return codes** — \`0\` for success, non-zero for failure
- **errno** — global error variable set by system calls
- **perror()** — print error message to stderr
- **setjmp/longjmp** — non-local goto for error handling
- **Check return values** — \`if ((result = ERROR))\`
- **NULL returns** — \`NULL\` pointer for failure
- **External libraries** — Check, Unity for modern approaches
- **Goto vs structured** — avoiding \`goto\` for error paths

## Historical Context

C (1972) has minimal error handling. The \`goto\` statement
was intended for error handling but discouraged (Dijkstra 1968).
Modern C uses return codes and external libraries for robustness.

For more on C, see [docs/languages/c/](../../../docs/languages/c/)
