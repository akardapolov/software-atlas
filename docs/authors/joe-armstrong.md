# Joe Armstrong

| | |
|---|---|
| **Born** | 1950 |
| **Died** | 2019 |
| **Fields** | Programming languages, concurrent programming, fault-tolerant systems |
| **Known for** | Erlang, OTP, "let it crash" philosophy, actor model in practice |

## Biography

Robert Joseph Armstrong was a British-Swedish computer scientist who
created **Erlang** programming language at Ericsson in the 1980s.
His work was driven by a specific and demanding requirement: building
telecom systems that must handle millions of concurrent connections
with 99.9999999% uptime ("nine nines").

Armstrong studied physics at Imperial College London, then worked at
Ericsson's Computer Science Laboratory from 1986. He completed his PhD
thesis "Making Reliable Distributed Systems in the Presence of Software
Errors" at the Royal Institute of Technology (KTH) in Stockholm in 2003.
He later became a professor at KTH.

Armstrong died in April 2019. His work continues through Erlang, the
OTP framework, and the Elixir language.

## Key Contributions

### Erlang (1986)

Armstrong created Erlang to solve a problem no existing language could
handle: telecom switching systems that must:

- Handle hundreds of thousands of concurrent calls
- Never go down (hot code swapping)
- Recover gracefully from failures
- Scale across multiple machines

Erlang's design:

- **Lightweight processes** — not OS threads. An Erlang system can run
  millions of processes, each with its own heap and garbage collection.
  Process creation takes microseconds.
- **Message passing** — processes communicate by sending asynchronous
  messages. No shared memory. This eliminates data races by design.
- **Process isolation** — a crash in one process cannot corrupt another.
  Each process has its own memory.
- **Pattern matching** — in function heads, case expressions, and message
  receive blocks. Elegant and concise for protocol handling.
- **Hot code swapping** — update running code without stopping the
  system. Critical for telecom switches that can't be taken offline.
- **Distribution** — built-in support for running across multiple machines
  with transparent message passing.

### "Let It Crash" Philosophy

Armstrong's most influential design philosophy. Instead of trying to
handle every possible error (defensive programming), Erlang encourages:

1. **Let the process crash** — don't try to recover from unexpected errors
   within a process. Just let it die.
2. **Supervision trees** — organise processes in hierarchies where
   **supervisor** processes monitor **worker** processes. When a worker
   crashes, its supervisor restarts it with known-good state.
3. **Isolation guarantees recovery** — because processes don't share
   state, restarting a crashed process doesn't corrupt anything else.

This philosophy is counterintuitive but remarkably effective:

- Code is simpler (no defensive error handling for unexpected cases)
- Recovery is automatic and consistent
- The system self-heals

Armstrong often cited Erlang systems at Ericsson running for years
without downtime.

### OTP — Open Telecom Platform

OTP is Erlang's standard library of battle-tested concurrent patterns:

- **gen_server** — generic client-server pattern
- **gen_statem** — generic state machine
- **supervisor** — process supervision and restart strategies
- **application** — packaging and lifecycle management

OTP turns Erlang from a language into a **framework for building
fault-tolerant distributed systems**.

### PhD Thesis (2003)

"Making Reliable Distributed Systems in the Presence of Software Errors"
is both Armstrong's thesis and a comprehensive guide to building
fault-tolerant systems. It argues that:

- Software errors are inevitable
- The goal is not to eliminate errors but to **contain and recover from them**
- Process isolation and supervision provide containment

### Connection to Alan Kay's OOP

Armstrong observed that Erlang processes are closer to Alan Kay's
original vision of OOP than mainstream OOP languages:

> "I think the lack of OOP is a feature in Erlang. Object-oriented
> programming is overrated. The important thing is messaging. [...]
> Joe Armstrong's Erlang processes are more 'object-oriented' than
> Java objects."

Kay himself agreed:

> "Erlang is the only mainstream language that got OOP right."
> (paraphrased — Kay praised Erlang's messaging model)

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1993 | "Concurrent Programming in Erlang" (with others) | Book | — |
| 2003 | "Making Reliable Distributed Systems in the Presence of Software Errors" | PhD thesis | — |
| 2007 | *Programming Erlang: Software for a Concurrent World* | Book | — |
| 2013 | *Programming Erlang* (2nd edition) | Book | — |

## Influence

### Influenced by

- **Tony Hoare** — CSP provided the theoretical foundation for concurrent processes
- **Alan Kay** — message-passing OOP vision; Armstrong realised it in practice
- **Prolog** — Erlang's syntax and pattern matching derive from Prolog
- **Telecom engineering** — the requirement for "five nines" uptime drove every design decision

### Influenced

- **José Valim** — created Elixir (2011), a modern language on the
  Erlang VM (BEAM) with Ruby-like syntax
- **Akka** — actor framework for Scala/Java, directly inspired by Erlang's actor model
- **Go** — goroutines and channels were influenced by CSP and Erlang's lightweight processes
- **WhatsApp** — built on Erlang, handling 2 billion users with a tiny engineering team
- **Discord, Rabbit MQ, CouchDB** — built on Erlang/BEAM
- **"Let it crash"** — adopted as a design pattern beyond Erlang (e.g., Akka supervision)

## Quotes

> "The problem with object-oriented languages is they've got all
> this implicit environment that they carry around with them.
> You wanted a banana but what you got was a gorilla holding
> the banana and the entire jungle."

> "Make it work, then make it beautiful, then if you really, really
> have to, make it fast. 90% of the time, if you make it beautiful,
> it will already be fast."

> "If Java is the answer, it must have been a very strange question."

> "The world is concurrent. Things in the world don't share data.
> Things communicate with messages. They fail independently.
> That's how Erlang works."

## Further Reading

- [Wikipedia: Joe Armstrong](https://en.wikipedia.org/wiki/Joe_Armstrong_(programmer))
- [PhD Thesis (PDF)](https://erlang.org/download/armstrong_thesis_2003.pdf)
- [Erlang.org](https://www.erlang.org/)
- [Coders at Work interview (2009)](https://codersatwork.com/)

## Related Pages

- [Erlang](../languages/erlang/)
- [Concurrency](../topics/concurrency/)
- [Distributed Systems](../topics/distributed/)
- [Tony Hoare](tony-hoare.md)
- [Alan Kay](alan-kay.md)
