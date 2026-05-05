# Software Engineering Atlas

A navigable knowledge map of software engineering — ideas, authors, works,
languages, paradigms, architecture, and practices.

---

## Contents

- [Maps](#maps) — visual timelines and relationship diagrams
- [Topics](#topics) — knowledge organized by domain
- [Languages](#languages) — programming language profiles
- [Authors](#authors) — biography cards of key contributors
- [Works](#works) — papers, books, and talks
- [Reading Paths](#reading-paths) — guided study sequences

---

## Maps

Maps give the broad picture of how ideas, people, and movements relate to each other.
They are the fastest entry point if you want orientation before diving into individual pages.

The atlas includes several types of maps:

- **Overview lineage graph** — four major tracks (architecture, design/OOP/FP/types, process, distributed systems) shown as a connected graph with cross-track edges
- **Timeline maps** — chronological view of key milestones grouped by era and lineage
- **Ideas evolution** — how specific concepts developed and branched over time
- **Languages genealogy** — how programming languages influenced each other
- **Paradigms map** — relationships between programming paradigms
- **Concurrency and process maps** — focused views on specific tracks

Maps are not strict taxonomies. They are navigational tools that show likely connections, historical sequence, and conceptual proximity.

---

## Topics

Topic pages collect related ideas into one domain. They are meant for focused study without losing the wider context of the atlas.

**Paradigms** covers the fundamental models of computation: imperative versus declarative, procedural, object-oriented, functional, logic, and concurrent. It explains what distinguishes each paradigm conceptually and historically, and how they overlap in modern languages.

**Architecture and Modularity** traces how engineers learned to structure systems at scale — from Dijkstra's structured programming and Parnas's information hiding, through layered architectures and domain-driven design, to microservices and team topology thinking. The central theme is the management of complexity through boundaries and separation of concerns.

**OOP and Design** covers object-oriented programming as a set of ideas rather than a language feature: encapsulation, message passing, responsibility assignment, the SOLID principles, design patterns, and refactoring. It spans from Simula and Smalltalk through the GoF book to modern interpretations.

**Type Systems** examines how types help us reason about programs — static vs. dynamic typing, nominal vs. structural systems, abstract data types, generics, type inference, and ownership types. It connects formal theory (Hindley–Milner, LSP) to practical language design.

**Functional Programming** covers the core ideas of pure functions, immutability, composition, higher-order functions, and algebraic data types. It traces the lineage from lambda calculus through ML and Haskell to modern functional idioms in languages like Clojure and F#.

**Concurrency** covers different models for handling parallelism and asynchrony: shared memory and threads, communicating sequential processes (CSP), the actor model, and async/await. It shows how design choices in this area reflect deeper trade-offs between safety, performance, and expressiveness.

**Process and Testing** covers how teams organize work and verify software: agile methods, extreme programming, test-driven development, continuous integration and delivery, and the DORA metrics for measuring delivery performance. It connects practices to the ideas and people behind them.

**Distributed Systems** covers the fundamental challenges of systems that span multiple machines: logical time and causality, ACID transactions, the CAP theorem, consensus algorithms, consistency models, and event streaming. It traces the lineage from Lamport's early papers through modern cloud-era practice.

---

## Languages

Language pages describe programming languages as concrete embodiments of ideas. Each page connects the language to its historical context, its design model, and the concepts it makes visible or natural.

The atlas covers languages across a wide range of paradigms and eras — from Lisp (1958) and Simula (1967) to TypeScript (2012) and Rust (2010). The goal is not to teach syntax, but to show what each language represents as a vehicle for ideas: what it enables, what it emphasizes, and what trade-offs its designers made.

The companion code examples directory contains runnable programs in each language, organized by topic so that the same idea can be compared across different languages side by side.

---

## Authors

Author pages bring the people behind the ideas into view. Each page connects a person to the concepts they introduced or shaped, the historical moment they worked in, and the works they produced.

The atlas treats authors not as isolated geniuses but as participants in ongoing conversations. Many ideas in the atlas were developed collaboratively, refined by successors, or arrived at independently by multiple people. Author pages try to capture these connections honestly, including the intellectual debts and mutual influences that shaped the field.

The author index includes biography cards for over sixty contributors, spanning the full chronological range of the atlas — from Alonzo Church and Alan Turing in the 1930s to practitioners still active today.

---

## Works

Works pages cover the primary sources behind the ideas in the atlas: papers, books, and talks that introduced or formalized important concepts.

**Papers** include foundational texts such as Dijkstra's Go To Considered Harmful, Parnas's modules paper, Hoare's CSP, Lamport's logical clocks, and Liskov and Wing's behavioral subtyping. These are the original arguments behind ideas that are now treated as common knowledge.

**Books** range from The Mythical Man-Month and Design Patterns through Domain-Driven Design and Continuous Delivery to Designing Data-Intensive Applications. Each book page situates the work in its historical moment, summarizes its core contribution, and connects it to related ideas in the atlas.

**Talks** cover a smaller but important set of recorded presentations — including Rich Hickey's Simple Made Easy and Gary Bernhardt's Boundaries — that shaped how practitioners think about design even when the ideas did not originate in written form.

Works pages are not substitutes for reading the originals. They are orientation guides that help you decide what to read and understand what you are reading when you do.

---

## Reading Paths

Reading paths provide guided sequences through the atlas for those who prefer a structured route over free exploration. Each path has a clear starting point, a direction of travel, and a destination.

**Architecture path** runs from Dijkstra's structured programming through information hiding, layered systems, hexagonal architecture, domain-driven design, and microservices, ending with Team Topologies. It traces how the field's understanding of system structure evolved across five decades.

**OOP and Design path** starts with Simula and Smalltalk, moves through the Gang of Four patterns, SOLID principles, refactoring practice, and responsibility-driven design, and ends with practical modern interpretations. It is a path through the ideas that shaped most working codebases today.

**Functional Programming path** starts with lambda calculus and Lisp, moves through ML and Haskell, covers the core ideas of purity and composition, and ends with applied functional design in languages like Clojure and F#. It is suitable both for those new to FP and for those wanting to understand its theoretical foundations.

**Testing and Delivery path** starts with extreme programming and test-driven development, moves through continuous integration, continuous delivery, and DevOps, and ends with the DORA metrics and evidence-based delivery improvement. It covers the practices that connect writing code to shipping working software reliably.

**Distributed Systems path** starts with Lamport's logical clocks, moves through ACID, the CAP theorem, Paxos, Raft, and consistency models, and ends with the synthesis in Designing Data-Intensive Applications. It is a path through the most mathematically demanding part of the atlas, but also one of the most practically relevant for modern systems work.

---

<p align="center">
  <i>
    "The purpose of abstraction is not to be vague,
    but to create a new semantic level in which one can be
    absolutely precise."
  </i>
  <br/>— Edsger Dijkstra
</p>
