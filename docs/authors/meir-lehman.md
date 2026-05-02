# Meir Lehman

| | |
|---|---|
| **Born** | 1947 |
| **Died** | 2010 |
| **Fields** | Software evolution, maintenance, complex systems |
| **Known for** | Lehman's Laws of Software Evolution |

## Biography

Meir (Meir M.) Lehman was a British computer scientist and academic
who studied **software evolution** — how software systems change over time.
His 1980 paper *Programs, Life Cycles, and Laws of Software
Evolution* established **Lehman's Laws**, fundamental principles
about how software systems naturally evolve.

Lehman worked at Imperial College London and contributed to understanding
of software as an evolving, living system rather than a static
artifact.

## Key Contributions

### Lehman's Laws of Software Evolution

Lehman identified eight laws that describe how software systems
evolve:

#### I. Continuing Change

**A program that is used in a real-world environment must change,
or else become progressively less useful.**

Real-world conditions change (business requirements, technology, environment),
and software must adapt or become obsolete.

#### II. Increasing Complexity

**As a program evolves, its complexity increases unless work is done
to maintain or reduce it.**

Complexity naturally accumulates through:
- New features added
- Bug fixes applied
- Temporary solutions become permanent
- Technical debt accrues

#### III. Self-Regulation

**Program evolution is a self-regulating process.**

The system naturally evolves toward:
- **Equilibrium** — stable state where change slows
- **Stagnation** — if change is too hard, system stops evolving
- **Active evolution** — if system is flexible, change continues

#### IV. Organizational Stability

**Program evolution is consistent with organizational stability.**

Software structure reflects organizational structure:
- Stable org → stable software
- Org changes → software changes
- Mismatched org and software → friction and failure

#### V. Conservation of Familiarity

**Program evolution is consistent with familiarity.**

Developers tend to maintain familiar patterns:
- Keep existing structure even if not optimal
- Reuse familiar techniques
- Preserve mental models

This creates **inertia** — resistance to fundamental change.

#### VI. Continuing Growth

**Program evolution causes continued growth.**

Growth is driven by:
- New requirements
- Feature requests
- Integration needs
- Environmental changes

Growth continues until:
- System becomes too complex to maintain
- Rewrite becomes necessary
- System is replaced

#### VII. Declining Quality

**Program evolution causes quality to decline.**

Quality degrades because:
- Quick fixes prioritize over proper solutions
- Tests lag behind new features
- Documentation becomes outdated
- Technical debt accumulates

**Result:** System becomes harder to maintain, more prone to bugs.

#### VIII. Feedback System

**Program evolution is a feedback system.**

Feedback mechanisms:
- User complaints → requirements change
- Bugs found → fixes applied
- Performance issues → optimisation

**Critical insight:** Without feedback, system becomes detached from reality.

### Software Entropy

Lehman's work relates to **software entropy** — the natural
tendency of systems toward disorder.

**Sources of entropy:**
- Unplanned changes
- Workarounds that become permanent
- Inconsistent styles across codebase
- Accumulating technical debt

**Entropy is inevitable:** The second law of thermodynamics
applies to software — entropy increases unless energy (effort) is
applied to reduce it.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1980 | *Programs, Life Cycles, and Laws of Software Evolution* | Paper | [→](../works/papers/lehman-1980-laws.md) |

## Influence

### Influenced by

- **System dynamics** — understanding complex systems over time
- **Complex systems theory** — how systems evolve
- **Empirical studies** — observing real software evolution

### Influenced

- **Software maintenance practices** — understanding that change is inevitable
- **Legacy system management** — strategies for evolving old systems
- **Software evolution research** — field continues to build on Lehman's work
- **Refactoring practices** — systematic approach to reducing complexity
- **Architecture for evolution** — designing systems to adapt

## Implications for Software Development

### Legacy Systems

Lehman's laws explain why legacy systems are challenging:
- High complexity (Law II)
- Declining quality (Law VII)
- Resistance to change (Law V)
- Need for continued growth (Law VI)

### Maintenance Strategies

Based on Lehman's laws:
- **Proactive complexity management** — refactor before crisis
- **Incremental evolution** — small, continuous change
- **Technical debt awareness** — understand and manage entropy
- **Architecture for change** — design for expected evolution

### The Rewrite Question

Lehman's laws inform the rewrite vs maintain decision:
- **Rewrite when:** Quality has declined too far (Law VII)
- **Maintain when:** System still provides value, can be improved incrementally
- **Strangler pattern:** Gradually replace while maintaining continuity

## Quotes

> "A program that is used in a real-world environment must change,
> or else become progressively less useful."

> "As a program evolves, its complexity increases unless work is done
> to maintain or reduce it."

> "Program evolution is a self-regulating process."

## Further Reading

- [Wikipedia: Meir Lehman](https://en.wikipedia.org/wiki/Meir_Lehman)
- [Lehman's Laws — original paper](https://dl.acm.org/doi/10.1145/321565.321569)

## Related Pages

- [Programs, Life Cycles, and Laws of Software Evolution](../works/papers/lehman-1980-laws.md)
- [Software Evolution](../topics/process/index.md) · [Legacy Code](../works/books/feathers-2004-legacy.md)
- [Refactoring](../works/books/fowler-1999-refactoring.md)
