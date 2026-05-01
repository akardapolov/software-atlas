# Programs, Life Cycles, and Laws of Software Evolution

| | |
|---|---|
| **Author** | Meir (Meir M.) Lehman |
| **Year** | 1980 |
| **Publication** | Proceedings of the IEEE |
| **Topic(s)** | Software evolution, maintenance |
| **DOI** | 10.1109/PROC.1980.13 |

## Summary

Lehman established **eight laws of software evolution** based on
empirical observation of real software systems. The laws describe how
software systems naturally change over time, becoming more complex and
harder to maintain unless actively managed.

## Key Ideas

### The Eight Laws

#### Law I: Continuing Change

**A program that is used in a real-world environment must change,
or else become progressively less useful.**

**Implications:**
- Software must adapt to changing requirements
- Static software becomes obsolete
- "Done" is a myth — successful software keeps evolving

**Example:** A payroll system initially handles hourly employees. Company
hires salaried workers and contractors. System must be modified.

#### Law II: Increasing Complexity

**As a program evolves, its complexity increases unless work is done
to maintain or reduce it.**

**Sources of complexity:**
- New features added to existing structure
- Quick fixes that don't follow existing patterns
- Temporary workarounds that become permanent
- Technical debt accumulation

**Consequences:**
- Harder to understand
- More bugs
- Slower development
- Increased maintenance cost

#### Law III: Self-Regulation

**Program evolution is a self-regulating process.**

Systems naturally evolve toward equilibrium:
- **Equilibrium** — stable state where system meets needs and change slows
- **Stagnation** — if change is too difficult, system stops evolving and users leave
- **Active evolution** — if system is flexible, continues to adapt

**Insight:** You can't force evolution speed. It depends on system's
inherent flexibility.

#### Law IV: Organizational Stability

**Program evolution is consistent with organizational stability.**

**Conway's Law in action:**
```
Software structure reflects communication structure of organization

Stable org (hierarchy) → Stable software (hierarchical architecture)
Changing org (matrix) → Changing software (architectural shifts)
Mismatch → Friction, failed projects
```

**Example:** A team-based structure leads to modular architecture.
Reorganizing into product-based teams requires architecture change.

#### Law V: Conservation of Familiarity

**Program evolution is consistent with familiarity.**

Developers maintain what they know:
- Reuse existing patterns
- Preserve familiar structures
- Avoid radical change to mental models

**Implications:**
- **Architectural inertia** — resistance to fundamental change
- **Path dependence** — decisions constrain future options
- **Skill bias** — use techniques team already knows

**Trade-off:** Familiarity enables speed but can prevent better solutions.

#### Law VI: Continuing Growth

**Program evolution causes continued growth.**

**Growth drivers:**
- New business requirements
- Feature requests from users
- Integration with new systems
- Regulatory changes

**Growth limit:** System becomes too complex to maintain, leading to:
- Complete rewrite
- System replacement
- Failure to meet new requirements

#### Law VII: Declining Quality

**Program evolution causes quality to decline.**

**Quality degradation sources:**
- Quick fixes over proper solutions
- Tests lag behind new features
- Documentation becomes outdated
- Inconsistent code styles from multiple developers

**Measuring quality decline:**
- Increasing bug rate
- Longer fix times
- More regressions
- Harder onboarding for new developers

#### Law VIII: Feedback System

**Program evolution is a feedback system.**

**Feedback loops:**
```
Users → Usage → Problems/Requests → Changes → System Update → Users
```

**Without feedback:** System becomes detached from actual needs and users.

**With feedback:** System adapts and remains useful (Law I).

## Software Entropy

Lehman's work relates to **entropy** — natural tendency of
systems toward disorder.

**Entropy in software:**
```
Initial state: Clean, understood
           ↓
Entropy increases: Quick fixes, technical debt, inconsistencies
           ↓
Final state: Complex, hard to maintain, buggy
```

**Second law of thermodynamics:** Entropy increases unless energy
( effort) is applied.

**Managing entropy:**
- Refactoring to reduce complexity (Law II)
- Architecture reviews to prevent structural issues
- Technical debt management
- Code quality standards

## Historical Context

### Empirical Foundation

Lehman's work was based on:
- **Empirical observation** — studying real systems at IBM
- **Data collection** — measuring change, complexity, quality over time
- **Pattern recognition** — identifying consistent behaviors

This contrasted with theoretical approaches to software engineering.

### Influence on Software Engineering

Lehman's laws influenced:
- **Software maintenance discipline** — recognizing that maintenance is primary activity
- **Legacy system understanding** — why old systems are hard to work with
- **Architecture practices** — designing for expected change
- **Agile practices** — embrace continuous change (Law I)

## Modern Implications

### Continuous Delivery

Lehman's Law I (Continuing Change) supports **continuous delivery**:
- Small, frequent changes match law
- System remains useful to users
- Avoids large, risky overhauls

### DevOps and SRE

Lehman's laws align with modern practices:
- **Automation** — reduces human errors that increase entropy
- **Monitoring** — detects quality decline early (Law VII)
- **Incremental updates** — support self-regulation (Law III)

### Legacy Modernisation

Lehman explains why legacy modernisation is difficult:
- High complexity (Law II) — hard to understand
- Declining quality (Law VII) — many bugs
- Organizational familiarity (Law V) — resistance to change

**Strategies:**
- Incremental refactoring (not Big Bang rewrite)
- Strangler Fig pattern — gradual replacement
- Investment in testing and documentation

## Connections

- **Builds on:** Systems theory · Empirical software research
- **Influenced:** Software maintenance practices · Legacy system management · Agile
- **Author:** [Meir Lehman](../../authors/meir-lehman.md)
- **Related work:** [Software Evolution](../../topics/process/) · [Legacy Code](../works/books/feathers-2004-legacy.md)
- **Related topic:** [Architecture & Modularity](../../topics/architecture/) — designing for evolution
