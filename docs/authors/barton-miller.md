# Barton Miller

| | |
|---|---|
| **Born** | 1953 |
| **Fields** | Computer security, software engineering, distributed systems |
| **Known for** | Fuzz testing, Paradyn performance tools |

## Biography

Barton P. Miller is a professor at the University of Wisconsin–Madison,
where he has led research in software engineering and security since
the 1980s. He is best known for coining "**fuzz testing**" — the practice
of bombarding programs with random or malformed input to find
robustness and security failures.

The origin story is famous in the field: during a 1988 thunderstorm,
line noise on a dial-up modem repeatedly crashed the standard UNIX
utilities Miller was using. Rather than dismiss it, he turned the
observation into a research programme on the **reliability of widely
deployed software under random input** — a problem that mainstream
engineering had largely ignored.

Miller has also led the Paradyn project on dynamic instrumentation for
parallel and distributed program performance analysis.

## Key Contributions

### Fuzz Testing (1990)

Miller, Fredriksen, and So published *An Empirical Study of the
Reliability of UNIX Utilities* in **CACM, December 1990**. The study:

- Generated random byte streams as input to 88 UNIX utilities across
  seven platforms.
- Found that **24% to 33%** of utilities crashed or hung outright.
- Identified concrete root causes: dangerous functions like `gets()`,
  unchecked array indices, format-string assumptions.

The paper coined the term "fuzz" and established a research method
that, decades later, became part of mainstream toolchains
(libFuzzer, AFL, OSS-Fuzz, native `go test -fuzz`).

A follow-up in **1995** repeated the experiment with similar findings;
a third round in **2006** showed substantial improvement in Linux and
GNU utilities — partly *because* Miller's earlier studies had become
required reading in operating-systems courses.

### Paradyn (1990s+)

Paradyn is a performance-analysis tool for parallel and distributed
programs. It pioneered **dynamic instrumentation** — modifying a
running program's binary to insert performance probes on demand,
without recompilation or restart. Concepts from Paradyn live on in
modern dynamic-binary-analysis frameworks such as Dyninst.

### Foundational Security Education

Miller's fuzz studies became canonical material in undergraduate
security and software-engineering courses worldwide, helping make
robustness against malformed input a baseline expectation rather than
an afterthought.

## Key Works

| Year | Title | Type |
|------|-------|------|
| 1990 | *An Empirical Study of the Reliability of UNIX Utilities* | Paper (CACM) |
| 1995 | *Fuzz Revisited: A Re-examination of the Reliability of UNIX Utilities and Services* | Technical report |
| 2006 | *An Empirical Study of the Reliability of UNIX Utilities and Services* (third re-run) | Technical report |
| 1990s+ | Paradyn parallel performance tools | Software project |

## Influence

### Influenced by

- The empirical / measurement tradition in software-engineering research
- Real-world UNIX systems (the artefact under study)

### Influenced

- **Michał Zalewski** — AFL (American Fuzzy Lop), the coverage-guided
  fuzzer that turned Miller's idea into a mainstream security tool
- **Kostya Serebryany** — libFuzzer (LLVM's in-process fuzzer),
  AddressSanitizer
- **Google OSS-Fuzz** — continuous fuzzing for open-source projects
- **Go native fuzzing** (Go 1.18, 2022)
- **Cargo-fuzz / cargo-afl** — Rust's fuzzing ecosystem
- The entire modern security tooling lineage that pairs fuzzing with
  sanitisers (ASan, UBSan, MSan, TSan)

## Quotes

> "We were stunned. Why hadn't anyone done this before?"
>
> *— On the 1990 results showing that a quarter of UNIX utilities
> crashed on random input.*

## Further Reading

- [Original 1990 paper (PDF)](http://pages.cs.wisc.edu/~bart/fuzz/fuzz.html)
- [Barton Miller — UW–Madison faculty page](https://pages.cs.wisc.edu/~bart/)
- [Wikipedia — Fuzzing](https://en.wikipedia.org/wiki/Fuzzing)
- [Wikipedia — Paradyn](https://en.wikipedia.org/wiki/Paradyn)

## Related Pages

- [Testing](../topics/testing/index.md) — Fuzzing section
