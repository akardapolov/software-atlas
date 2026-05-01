## Overview

Erlang error handling follows the **"let it crash"** philosophy.
Errors are caught in supervisors via links and monitors. Every process
has its own mailbox — no shared exceptions.

## Code

See \`main.erl\` in this directory.

## How to Run

\`\`\`bash
erlc main.erl && erl -noshell -s main main -s init stop
\`\`\`

## Key Concepts

- **let it crash** — small processes that crash are restarted
- **Supervision trees** — OTP supervisors manage process lifecycles
- **link/monitor** — \`link/1, 2)\` or \`monitor(Process)\`
- **'EXIT' messages** — \`{'EXIT', Pid, Reason}\` delivered to linked
- **try...catch** — \`try ... catch ... end\`
- **catch/3** — \`catch Type of Error\` to catch specific errors
- **OTP behaviours** — \`gen_server\`, \`gen_fsm\` for structured error handling
- **System processes** — \`sys:get_status()\` for kernel errors
- **No shared exceptions** — each process manages its own errors

## Historical Context

Erlang (1986, Joe Armstrong) was designed for telecom reliability.
The "let it crash" philosophy with supervisor trees enables 99.999%
uptime. OTP provides robust, production-ready error handling patterns.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
