# The Mythical Man-Month: Essays on Software Engineering

| | |
|---|---|
| **Author(s)** | Frederick P. Brooks Jr. |
| **Year** | 1975 (Anniversary Edition: 1995) |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | Software Engineering, Project Management, Architecture |
| **ISBN** | 978-0-201-83595-3 |

## Summary

"The Mythical Man-Month" is one of the most influential books in the history of software engineering. Fred Brooks wrote it based on his experience leading the development of OS/360 at IBM — one of the largest software projects of its time.

The book is a collection of essays in which Brooks analyzes the causes of software project failures and offers practical recommendations. Despite being written in 1975, most of its conclusions remain relevant.

Central idea: software development is not a production process but a creative activity, and attempts to apply industrial metrics (man-months) to it lead to disasters.

## Key Ideas

1. **Brooks's Law** — "Adding manpower to a late software project makes it later." New participants require time for training and increase communication costs (N people create N×(N-1)/2 connections).

2. **The Tar Pit** — large system projects are like tar pits: the more you struggle, the deeper you sink. The complexity of software systems grows non-linearly.

3. **No Silver Bullet** — there is no single solution that would improve development productivity by an order of magnitude. Software complexity is essential, not accidental.

4. **Conceptual Integrity** — a successful system should reflect a unified architectural vision. Better if the architect is one person (or a small group) than a committee.

5. **The Second-System Effect** — the second system an architect designs is often overloaded with features. The first is too simple, the second is bloated.

## Historical Context

OS/360 (1964-1966) was a project of unprecedented scale: millions of lines of code, thousands of developers. The project was released with huge delays and numerous bugs. Brooks managed the team and personally observed how traditional management approaches failed.

By the time he wrote the book, Brooks had already left IBM and was teaching at the University of North Carolina. This gave him the opportunity to objectively analyze his experience.

## Impact and Legacy

The book influenced:

- **Agile movement** — recognition of uncertainty and the need for iterative approach
- **Architectural thinking** — understanding the importance of conceptual integrity
- **DevOps and Small Teams** — awareness of communication costs
- **Estimation** — skepticism towards accurate deadline estimates

"No Silver Bullet" became a standalone essay added to the 1995 anniversary edition and sparked decades of discussion.

## Key Takeaways

- **Plan iterations** — "Plan to throw one away; you will, anyhow"
- **Small teams** — a surgical team is more effective than an army
- **Unified vision** — architecture should be consistent
- **Communication is overhead** — each new participant increases overhead
- **Complexity is inevitable** — essential complexity cannot be eliminated

## Connections

- **Author:** [Fred Brooks](../../authors/fred-brooks.md)
- **Related topic:** [Architecture](../../topics/architecture/index.md)
- **Related work:** [Parnas — Information Hiding](../papers/parnas-1972-modules.md)
- **Influenced by:** [Conway's Law](../../topics/architecture/conways-law.md)

## Personal Notes

_Every time a manager suggests "adding people" to meet a deadline, I recall Brooks's Law. The book was written 50 years ago, but every chapter resonates with modern problems. Required reading for anyone working in software teams._
