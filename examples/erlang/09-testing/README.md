# Testing in Erlang

## Overview

Erlang has built-in EUnit framework in the \`eunit\` library.
Tests are modules with \`_test\` suffix or \`test()\` functions.
OTP behaviours include test helpers. Common Test Framework (CTF) for
distributed testing.

## Code

See \`main.erl\` in this directory.

## How to Run

\`\`\`bash
rebar3 eunit
# Or with erl
erl -eval "eunit:test([module_name], [verbose])"
\`\`\`

## Key Concepts

- **EUnit macros** — \`?_assert\`, \`?_assertEqual\`, \`?_assertMatch\`
- **Test generators** — \`?_test()\` for multiple cases
- **Fixtures** — \`setup()\`, \`cleanup()\` in test modules
- **Stateful tests** — \`?_testWith()\` with state parameter
- **Mocking** — meck for function mocking
- **Common Test Framework (CTF)** — distributed system testing
- **Code coverage** — \`cover\` module for test coverage

## Historical Context

Erlang (1986) included EUnit in the OTP libraries. Test
organization follows Erlang module conventions. EUnit pioneered automatic
test discovery and assertion macros. CTF added distributed testing
capabilities.

For more on Erlang, see [docs/languages/erlang/](../../../docs/languages/erlang/)
