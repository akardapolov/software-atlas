# David Farley

| | |
|---|---|
| **Fields** | Continuous delivery, DevOps, software deployment |

## Biography

David Farley is a software engineer, author, and consultant known for co-authoring *Continuous Delivery* (2010) with Jez Humble. Farley has extensive experience in software delivery, deployment automation, and DevOps practices, helping organizations improve their release cadence and reliability.

Farley's work focuses on the technical and cultural aspects of continuous delivery, bridging development and operations through automation and collaboration.

## Key Contributions

### Continuous Delivery (2010)

With Jez Humble, Farley codified the approach to continuous delivery, building on agile and DevOps practices:

**Core principles:**
- **Build quality in** — Automated testing at every stage
- **Work in small batches** — Smaller changes are safer and faster
- **Automate everything** — Manual steps are error-prone
- **Keep it deployable** — Every commit can be deployed
- **Decouple deployment from release** — Deploy when ready, release when users need it

**The deployment pipeline:**

```
Code → CI → Build Artifact → Deploy → Production
```

**Key concepts:**
- **Deployment pipeline** — Automated sequence of stages
- **Canary releases** — Gradual rollout to detect issues early
- **Blue-Green deployments** — Zero downtime for critical systems
- **Feature toggles** — Enable functionality without full deployments

### Testing Strategies

Farley documented various testing approaches for CD:

| Type | Focus | Tools |
|------|--------|---------|
| **Unit tests** | Component isolation | JUnit, pytest |
| **Integration tests** | Component interaction | TestContainers, WireMock |
| **Contract tests** | API compatibility | Pact, Spring Cloud Contract |
| **End-to-end tests** | User workflows | Selenium, Cypress, Playwright |
| **Performance tests** | Load and response time | JMeter, k6, Gatling |
| **Chaos tests** | System resilience | Chaos Monkey, Gremlin |

## Influence

### Influenced by

- **Kent Beck** — XP, continuous integration
- **Martin Fowler** — Refactoring and CI/CD

### Influenced

- **CD adoption** — Widespread implementation of continuous delivery
- **DevOps transformation** — Automation as cultural shift
- **Cloud platforms** — AWS, Azure, GCP CD services
- **Container orchestration** — Kubernetes, Docker Swarm
- **Infrastructure as Code** — Terraform, Pulumi, CloudFormation

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2010 | Continuous Delivery | Book (co-authored) | [→](../works/books/humble-2010-cd.md) |
| 2010 | Continuous Delivery Pipelines | Book (co-authored) | — |

## Related Pages

- [Process topic](../topics/process/index.md) — DevOps, CI/CD, and deployment
- [Jez Humble](jez-humble.md) — Co-author, Continuous Delivery
- [Kent Beck](kent-beck.md) — XP and continuous integration
- [Martin Fowler](martin-fowler.md) — Refactoring and CI/CD
- [Nicole Forsgren](nicole-forsgren.md) — Accelerate and DORA

## Further Reading

- Humble & Farley — *Continuous Delivery* (2010)
- Humble & Farley — *Continuous Delivery Pipelines* (2010)
- Forsgren, Humble & Kim — *Accelerate* (2018) — DevOps research
- Humble & Farley — *Release It!* (2021) — Deployment automation

## Quotes

> "Continuous delivery is about making releasing software routine, not heroic."
> — David Farley

> "The pipeline is your deployment assembly line. Automate it end to end."
> — David Farley

> "Good CD practices are about culture and collaboration, not just tools."
> — David Farley

> "Don't wait for perfect. Start with 'good enough' and iterate."
> — David Farley
