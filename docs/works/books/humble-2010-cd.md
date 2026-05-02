# Continuous Delivery

| | |
|---|---|
| **Authors** | Jez Humble, David Farley |
| **Year** | 2010 |
| **Publisher** | Addison-Wesley |
| **Topic(s)** | Continuous Delivery, deployment automation |
| **ISBN** | 978-0-321-60191-9 |

## Summary

Humble & Farley formulated CD as an engineering discipline:
a way to release changes **quickly, frequently, and safely** through
automation of build, test, and deployment.

CD is not "release often," but a system of practices:
- version everything (code, configs, infrastructure)
- automatic pipeline
- fast feedback
- reducing batch size (small changes)

## Key Ideas

### 1) Deployment Pipeline

Pipeline turns a "commit" into a potentially releasable artifact:

1. Commit stage: build + fast tests
2. Test levels (component/integration/acceptance)
3. Quality checks (linters, security scanning)
4. Run in staging-like environment
5. Deploy to production (on button click / automatically)

### 2) Build once, deploy many

The artifact must be the same across all environments.
Otherwise, staging isn't representative.

### 3) Trunk-based development and small changes

Big long branches = integration hell.
CD drives toward:
- small PRs
- frequent merges
- feature toggles

### 4) Configuration management

Configuration is code too:
- store in VCS
- review
- environment reproducibility

### 5) Release strategies

The book provides the foundation for what became standard later:
- blue/green
- canary
- rollback strategies
- "stop-the-line" on red pipeline

## Impact and Legacy

- CD became the foundation of DevOps
- pipeline as the "main artery" of development
- connection between "technical" practices and business speed

## Connections

- **Builds on:** XP/TDD, CI
- **Leads to:** [Forsgren et al. — Accelerate (2018)](forsgren-2018-accelerate.md)
- **Related topic:** [Process & Testing](../../topics/process/index.md)
