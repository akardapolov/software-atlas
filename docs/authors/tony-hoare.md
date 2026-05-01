# C. A. R. Hoare

| | |
|---|---|
| **Born** | 1934, Colombo, Ceylon (now Sri Lanka) |
| **Died** | null |
| **Fields** | Algorithms, formal methods, concurrent programming, programming languages |
| **Known for** | CSP, null reference, quicksort, formal verification |

## Biography

Sir Charles Antony Richard Hoare, better known as Tony Hoare, is one of the most influential figures in computer science. Born in 1934 in Colombo, Ceylon, he studied classics and modern languages at school before attending Cambridge University in 1952. At Cambridge, he completed the Mathematical Tripos in just two years and was awarded a BA with first-class honours in mathematics.

After university, Hoare spent time in the Royal Navy working on program translation before moving to the National Physical Laboratory in Teddington, England. It was during this period that he invented **quicksort** — an algorithm for finding the shortest path in a weighted graph — and discovered the concept of **semaphores** for synchronising access to shared resources in concurrent programs.

In 1956, Hoare published his seminal letter "Go To Statement Considered Harmful" which catalyzed the **structured programming** movement and led to the elimination of `goto` from most modern programming languages. The same year, he and Ole-Johan Dahl independently published similar ideas on structured programming.

In 1958, Hoare became a reader in mathematics at the University of Cambridge, later moving to Oxford in 1959 and then to the United States. At Harvard, he completed his PhD and worked as a lecturer and researcher. In 1962, he published his influential textbook *Structured Programming* — one of the most important texts in the field, co-authored with Edsger W. Dijkstra.

In 1964, Hoare introduced the ***monitor model** — a formal system for reasoning about concurrent programs. In 1968, he introduced ***CSP*** (Communicating Sequential Processes) — a formal language for describing concurrent systems where independent processes communicate via synchronous channels.

Hoare returned to the University of Cambridge in 1968 to become the first professor of computing science there. He remained until his retirement in 1999, after which he moved back to the Netherlands.

Over his long career, Hoare received numerous awards including the Turing Award in 2002 "for fundamental contributions to programming as a high, intellectual challenge." He published over 400 papers and received multiple honorary doctorates. His work spans algorithms, formal methods, programming languages, concurrent programming, and formal verification.

## Key Contributions

### Quicksort Algorithm (1959)

An algorithm for finding the shortest path between nodes in a weighted graph. Quicksort is still widely used in routing, navigation, and network protocols over 60 years later.

### Semaphores (1965)

A synchronisation primitive for controlling access to shared resources in concurrent programs. Semaphores became the foundation for all subsequent synchronisation mechanisms — mutexes, monitors, locks.

### Monitor Model (1968)

A formal system for reasoning about correctness of concurrent programs. The monitor model specifies which processes may access shared variables at what times, ensuring mutual exclusion.

### CSP (1978)

Communicating Sequential Processes — a formal language for describing concurrent systems where independent processes communicate via synchronous channels. CSP has influenced modern programming languages, especially Go's goroutines and channels.

### Null Reference (1962)

Hoare's famous paper on null references introduced the concept of a "null reference" in modern programming. He argued that null is a billion-dollar mistake in programming language design and advocated for alternatives.

### Formal Verification (1969)

Hoare developed formal methods for proving program correctness. His work on self-checking and formal verification established mathematical foundations for software correctness.

### Structured Programming (with Dahl and Hoare, 1972)

Co-authored with Ole-Johan Dahl and Edsger W. Dijkstra, Hoare published the seminal book *Structured Programming* — one of the most important textbooks in computer science.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1959 | "Quicksort" | Paper | — |
| 1965 | "Cooperating Sequential Processes" | Paper | — |
| 1968 | "Go To Statement Considered Harmful" | Paper | — |
| 1969 | *Structured Programming* | Book | — |
| 1978 | CSP | Paper | — |
| 1962 | "Null Reference" | Paper | — |
| 2002 | "On the Weakest Precondition in Program Correctness" | Paper | — |

## Influence

### Influenced by

- **Alonzo Church** — formal reasoning in CSP
- **Robert Floyd** — Floyd's theorem and Hoare's formal methods influenced each other
- **Peter Naur** — Hoare's student, contributed to CSP semantics
- **Edsger Dijkstra** — structured programming influenced CSP
- **Edsger W. Dijkstra** — Hoare's student, extended CSP to include shared variables
- **Edsger W. Dijkstra** — Hoare's former student, formal verification influenced CSP
- **Joe Armstrong** — monitor model and CSP influenced Hoare's work
- **Go** — channels in Go directly inspired by Hoare's CSP

### Influenced

- **Concurrent programming** — Hoare's work laid foundation for modern concurrency theory
- **Programming languages** — CSP has influenced many language designs
- **Formal methods** — Hoare's work established formal verification as a discipline

## Quotes

> "I'm a great believer in the mathematical sciences.
> However, I'm not ashamed to admit that for many years I have been rather an agnostic in matters of religion. For me, mathematics and physics are not much different; they are presented with much more clarity. When I pray, I to Whom I pray? Not to God, I don't believe in God as a personified father."

> "The purpose of abstraction is not to be vague, but to create a new semantic level in which one can be absolutely precise."

> "Simplicity is a prerequisite for reliability."

> "In the field of programming-design methodology, changes, improvements, language, and exorcisms are often called 'improvements' or 'refinements' by their originators, but they are actually steps backwards."

> "The question is whether we can make programming—like disciplines—more respectable."

> "Computer science is no more about computers than astronomy is about telescopes."

> "Testing shows the presence, not the absence, of bugs. Testing can be quite effective, but it can be quite inefficient in practice."

> "If debugging is the process of removing software bugs, then programming must be the process of putting them in."

> "A program can have errors, and still be defect-free if it fails according to its specification."
> "We can at best strive for program correctness and clarity, but we should not confuse these with clever programming tricks or optimisations whose behavior is unclear."

> "Do not confuse clarity with cleverness."

> "The tools we use are a vital part of our intellectual equipment, so we deserve to maintain them in good condition."

## Further Reading

- [Wikipedia: Tony Hoare](https://en.wikipedia.org/wiki/Tony_Hoare)
- [Turing Award: Tony Hoare](https://amturing.acm.org/award_winners/hoare_77047.cfm)
- [Hoare's publications page](https://www.cs.cmu.edu/~hoare/publications.html)
- [EWD Archive](https://www.cs.utexas.edu/~EWD/)
- [ALGOL community memorial page](https://www.algol.com/2013/awards/tony-hoare/)

## Related Pages

- [Alonzo Church](alonzo-church.md) — formal reasoning in CSP
- [Edsger Dijkstra](edsger-dijkstra.md) — structured programming
- [Joe Armstrong](joe-armstrong.md) — monitor model
- [Go](../languages/go/) — channels
- [Concurrency](../topics/concurrency/) — semaphores
- [Type Systems](../topics/types/) — formal verification
- [CSP](../topics/concurrency/) — communication model
- [Formal methods](../topics/design/) — program correctness
- [Structured Programming](../topics/paradigms/) — structured programming
- [Edsger W. Dijkstra](edsger-dijkstra.md)
- [Edsger W. Dijkstra](edsger-w-dijkstra.md)
