# Jez Humble

| | |
|---|---|
| **Fields** | Continuous delivery, DevOps, software deployment |

## Biography

Jez Humble is a software engineer, author, and consultant known for co-authoring *Continuous Delivery* (2010) with David Farley. Humble has extensive experience in software delivery, deployment automation, and helping organizations transform their development and operations practices.

Humble's work focuses on making software delivery reliable, repeatable, and automated through continuous integration and deployment pipelines, bridging the gap between development and operations.

## Key Contributions

### Continuous Delivery (2010)

With David Farley, Humble codified the approach to continuous delivery, building on DevOps and agile practices:

**Core principles:**
- **Build quality in** — Automated testing at every stage
- **Work in small batches** — Small changes are safer and faster
- **Automate everything** — Manual deployment steps are error-prone
- **Keep it deployable** — Every commit can go to production
- **Decouple deployment from release** — Deploy when ready, release when users need it

**The CD pipeline:**

```
Commit → CI (Build + Test) → Artifact → Deployment Pipeline → Production
```

**Key insights:**
- Deployment should be routine, not scary
- Automation reduces human error and increases speed
- Small, frequent releases reduce risk and blast radius
- Feature flags enable gradual rollouts

### Deployment Strategies

Humble documented various deployment patterns:

| Strategy | Description | Use Case |
|----------|-------------|-----------|
| **Blue-Green Deployment** | Two environments with zero downtime | Database migrations |
| **Canary Release** | Gradual rollout to subset of users | New feature testing |
| **Dark Launch** | All users get new version at once | Major releases |
| **A/B Testing** | Compare versions with user segments | UI experiments |
| **Feature Flags** | Toggle functionality without deployment | Gradual rollout |

### Infrastructure as Code

Humble advocated for treating infrastructure programmatically:

| Aspect | Traditional | IaC |
|---------|-----------|-----|
| **Configuration** | Manual editing of config files | Terraform/Pulumi |
| **Provisioning** | Manual server setup | Ansible/Cloud-init |
| **Networking** | Manual firewall rules | Security groups |
| **Secrets management** | Copying passwords around | Vault/Secrets Manager |

## Influence

### Influenced by

- **Kent Beck** — XP, continuous integration
- **Martin Fowler** — Refactoring and CI/CD
- **Nicole Forsgren** — Accelerate research

### Influenced

- **DevOps transformation** — Widespread adoption of CD practices
- **Cloud-native deployment** — Container orchestration, serverless
- **Infrastructure as Code** — Terraform, Pulumi, Ansible
- **CI/CD platforms** — Jenkins, GitLab CI, GitHub Actions, CircleCI
- **Platform engineering** — Kubernetes, Docker Swarm

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2010 | Continuous Delivery | Book (co-authored) | [→](../works/books/humble-2010-cd.md) |

## Related Pages

- [Process topic](../topics/process/index.md) — DevOps, CI/CD, and deployment
- [Kent Beck](kent-beck.md) — XP and continuous integration
- [David Farley](david-farley.md) — Continuous Delivery
- [Nicole Forsgren](nicole-forsgren.md) — Accelerate and DORA

## Further Reading

- Humble & Farley — *Continuous Delivery* (2010)
- Forsgren, Humble & Kim — *Accelerate* (2018) — DevOps research
- Google — *Site Reliability Engineering* (2016) — SRE practices
- Humble & Farley — *Continuous Delivery Pipelines* (2010)

## Quotes

> "If deploying is scary, you do it rarely. If deploying is routine and automated, you can ship continuously."
> — Jez Humble

> "The goal is not automation for its own sake. The goal is better outcomes: faster delivery, higher quality, less pain."
> — Jez Humble on CD

> "Continuous delivery is about culture, not just tools."
> — Jez Humble on DevOps

> "Automate everything you can. Manual deployment is a sign you haven't scaled."
> — Jez Humble
