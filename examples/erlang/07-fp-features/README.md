# FP Features in Erlang

## Overview

Erlang is a **pure functional language** — all functions are pure,
data is immutable, and there are no side effects. Concurrency
is via actor model, not shared state.

## Code

See \`main.erl\` in this directory.

## How to Run

\`\`\`bash
erlc main.erl && erl -noshell -s main main -s init stop
\`\`\`

## Key Concepts

- **Pure functions** — no side effects, same input → same output
- **Immutable data** — lists, tuples, records cannot be mutated
- **Pattern matching** — function clauses by pattern
- **Recursion** — only iteration mechanism (no loops)
- **Tail recursion** — optimized by BEAM VM
- **Higher-order functions** — lists:map/2/3, lists:filter/2, lists:foldl/3
- **List comprehensions** — [X*2 || X <- Lists, X > 5]
- **Anonymous functions (funs)** — closures capturing scope
- **Lazy evaluation** — only when explicitly requested
- **No shared state** — concurrency via message passing

## Historical Context

Erlang (1986, Joe Armstrong) pioneered pure FP for telecom.
All data is immutable, making reasoning straightforward. The actor
model provides fault-isolated concurrency. Erlang influenced
Elixir, Akka, and OTP patterns.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
