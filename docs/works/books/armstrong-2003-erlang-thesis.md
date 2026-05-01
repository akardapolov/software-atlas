# Making Reliable Distributed Systems in the Presence of Software Errors

| | |
|---|---|
| **Author** | Joe Armstrong |
| **Year** | 2003 |
| **Type** | PhD Dissertation |
| **Topic(s)** | Concurrency, Distributed Systems, Fault Tolerance |

## Summary

Joe Armstrong's PhD dissertation presents the theoretical foundations of Erlang and the "let-it-crash" philosophy for building fault-tolerant distributed systems.

## Key Ideas

1. **Let it crash** — instead of defensive programming, isolate failures and restart components
2. **Processes are isolated** — lightweight processes with no shared memory
3. **Supervision trees** — hierarchical process monitoring for fault recovery
4. **Error handling as a separate concern** — business logic and error handling are separated

## Historical Context

Armstrong developed Erlang at Ericsson in the late 1980s for telecommunications systems requiring extreme reliability (99.9999% uptime).

## Impact and Legacy

This thesis formalized the principles behind Erlang/OTP and influenced:
- **Erlang/OTP** — the standard platform for fault-tolerant systems
- **Elixir** — modern language running on the Erlang VM
- **Microservices** — isolation and failure containment patterns
- **Distributed systems design** — resilience engineering practices

## Connections

- **Author:** [Joe Armstrong](../../authors/joe-armstrong.md)
- **Related language:** [Erlang](../../languages/erlang/)
- **Related topic:** [Concurrency](../../topics/concurrency/)
