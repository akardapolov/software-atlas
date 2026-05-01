# The WyCash Portfolio Management System

| | |
|---|---|
| **Author** | Ward Cunningham |
| **Year** | 1992 |
| **Publication** | OOPSLA (Object-Oriented Programming, Systems, Languages and Applications) |
| **Topic(s)** | Technical debt, refactoring, software engineering |

## Summary

In this OOPSLA experience report, Cunningham presents the **WyCash portfolio management system** — a real-world application built in Smalltalk. The paper is particularly notable for introducing the metaphor of **"technical debt"** — borrowing development time to create working code quickly, with the understanding that rapid development often creates debt that must be paid back later through refactoring.

## Key Ideas

### Technical Debt Metaphor

Cunningham's famous metaphor:

> "A little debt speeds development so long as it is paid back promptly with refactoring."

He elaborates:

- **Debt as financial analogy** — like borrowing money
- **Interest** - the "interest" is time spent paying off debt
- **Principal** - refactoring is the "payment" process

### WyCash System

The portfolio system demonstrated:

- **Real application** - financial portfolio tracking software
- **Object-oriented** - built with Smalltalk's object model
- **Live development** - system could be modified while running
- **Graphical interface** - visual portfolio management

### Debt vs Investment

Cunningham contrasted:

| Debt | Investment |
|------|----------|
| Speed | Quick | Slow but manageable |
| Visibility | Code is measurable | Returns are uncertain |
| Control | Team owns tech | External factors dominate |

### Refactoring Examples

WyCash refactoring showed:

- **Simplify complex algorithms** - reduce debt principal
- **Extract methods** - improve code organization
- **Optimize data structures** - reduce computational cost
- **Improve naming** - clearer, self-documenting code

## Historical Significance

### WikiWiki Birth

Cunningham created the first wiki:

- **WikiWikiWeb** — collaborative pattern database
- **Community knowledge** - living documentation
- **Hyperlink connections** - wiki links between pages

This directly influenced:

- **Pattern Languages of Programming** - patterns wiki evolved from this
- **Modern wikis** - Wikipedia, MediaWiki, Confluence
- **Collaborative documentation** - living docs for teams

### Debt Awareness

The paper raised awareness:

- **Common problem** - shipping code faster creates hidden costs
- **Trade-offs** - speed vs quality must be managed
- **Systematic approach** - debt as design decision

## Legacy

The debt metaphor became standard:

- **Team discussions** - "this has technical debt"
- **Code reviews** - consider debt in refactoring decisions
- **Tools** - SonarQ and similar tools
- **Architecture** - debt affects design choices

## Connections

- **Preceded by:** [CRC Cards](../papers/beck-cunningham-1989-crc.md)
- **Inspired:** [Martin Fowler](../authors/martin-fowler.md)
- **Influenced:** Refactoring practices, design discussions
- **Related:** [Technical Debt article](../series/das.md)
- **Related works:** [Refactoring (1999)](../books/fowler-1999-refactoring.md)
- **Author:** [Ward Cunningham](../../authors/ward-cunningham.md)
