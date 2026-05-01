# Ward Cunningham

| | |
|---|---|
| **Born** | 1949 |
| **Fields** | Patterns, agile, wikis |
| **Known for** | CRC Cards, Technical Debt, WikiWikiWeb |
| **Awards** | ACM Fellow (2008) |

## Biography

Ward Cunningham is an American computer programmer who co-developed the **CRC Card** technique with Kent Beck and introduced the concept of **"technical debt"** as a metaphor for software quality. He also created the first **wiki** (WikiWikiWeb) in 1995 as a collaborative platform for documenting patterns and knowledge.

Cunningham's work spans practical design techniques, software quality metaphors, and collaborative tools — all focused on making software development more effective.

## Key Contributions

### CRC Cards

With Kent Beck, Cunningham created **CRC (Class-Responsibility-Collaborator) Cards**:

```
┌─────────────────────┐
│  Customer           │
├─────────────────────┤
│ Responsibilities:   │
│ • Add to cart      │
│                   │
│ Collaborators:      │
│ • Order            │
└─────────────────────┘
```

CRC cards enable:
- **Scenario-based design** — walk through use cases with cards
- **Team collaboration** — multiple people can move cards representing objects
- **Responsibility thinking** — focus on "what does it do," not "what data"

This directly influenced Rebecca Wirfs-Brock's Responsibility-Driven Design.

### Technical Debt Metaphor

Cunningham introduced **"technical debt"** in his 1992 WyCash experience report:

> "Shipping first time code is like going into debt. A little debt speeds development so long as it is paid back promptly with refactoring."

The metaphor provides:

- **Vocabulary** — discuss trade-offs between speed and quality
- **Quantification** — interest accumulates on debt (complexity grows)
- **Actionable concept** — "refactoring" is paying back debt

Technical debt became standard terminology in software development, discussed in:
- Martin Fowler's Refactoring
- Team management and planning
- Technical leadership discussions

### WikiWikiWeb (1995)

Cunningham created the first wiki as a **collaborative pattern database**:

- **Ward's Wiki** — Portland Pattern Repository
- **Easy editing** — anyone can contribute
- **Living documentation** — evolves with community knowledge

WikiWikiWeb led to:
- **Wikipedia** — encyclopedia model
- **Corporate wikis** — Confluence, SharePoint, internal wikis
- **Developer documentation** — living docs instead of static Word docs

### WyCash Portfolio System

Cunningham's 1992 OOPSLA experience report demonstrated:
- **CRC Cards in practice** — real application of the technique
- **Object-oriented benefits** — maintainability in financial software
- **Technical debt lessons** — first publication of the concept

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 1989 | *A Laboratory for Teaching Object-Oriented Thinking* (OOPSLA, with Beck) | Paper | [→](../works/papers/beck-cunningham-1989-crc.md) |
| 1992 | *The WyCash Portfolio Management System* (OOPSLA experience report) | Paper | [→](../works/papers/cunningham-1992-wycash.md) |

## Influence

### Influenced by

- **Christopher Alexander** — pattern language concept
- **Kent Beck** — collaborator on CRC Cards

### Influenced

- **Martin Fowler** — technical debt concept in Refactoring book
- **Rebecca Wirfs-Brock** — RDD builds on CRC card thinking
- **Wiki technology** — Wikipedia, Confluence, all wikis
- **Agile methodologies** — emphasis on collaboration, living documentation

## Quotes

> "What is the simplest thing that could possibly work?"

> "You're borrowing time when you write sloppy code. You're borrowing it from your future self."

> "The best way to get the right answer is to ask the right question."

## Further Reading

- [Wikipedia: Ward Cunningham](https://en.wikipedia.org/wiki/Ward_Cunningham)
- [C2 Wiki (Ward's Wiki)](https://wiki.c2.com/)
- [Technical Debt article](https://martinfowler.com/bliki/TechnicalDebt)

## Related Pages

- [Kent Beck](kent-beck.md)
- [Rebecca Wirfs-Brock](rebecca-wirfs-brock.md)
- [Martin Fowler](martin-fowler.md)
