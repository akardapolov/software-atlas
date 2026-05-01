# Reading Path — Testing & Delivery

Goal: connect "tests" with design, and "deployment" with engineering practices.

## Steps

### 1) TDD as design
- Read: [Beck — TDD (2002)](../works/books/beck-2002-tdd.md)
- Practice: Red/Green/Refactor as the basic development cycle

### 2) Refactoring is impossible without tests
- Read: [Fowler — Refactoring (1999)](../works/books/fowler-1999-refactoring.md)
- Focus: tests as insurance for improving structure

### 3) Legacy as reality
- Read: [Feathers — Legacy Code (2004)](../works/books/feathers-2004-legacy.md)
- Focus: seams + characterization tests

### 4) CD as a system of practices
- Read: [Humble & Farley — Continuous Delivery (2010)](../works/books/humble-2010-cd.md)
- Focus: pipeline, build once deploy many, automation

### 5) Science and metrics
- Read: [Forsgren et al. — Accelerate (2018)](../works/books/forsgren-2018-accelerate.md)
- Practice: choose 1 DORA metric and determine how to measure it in your system

## Final checklist

- Is there a fast commit stage?
- Are PRs small and frequent?
- Are tests stable (not flaky)?
- Is deployment reproducible?
- Is there rollback and observability?
- Are there flow metrics (DORA)?

## Related

- [Process & Testing topic](../topics/process/)
