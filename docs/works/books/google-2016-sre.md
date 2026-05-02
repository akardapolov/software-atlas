# Site Reliability Engineering

| | |
|---|---|
| **Author** | Google SRE Team (edited by Betsy Beyer, Chris Jones, Jennifer Petoff, Niall Richard Murphy) |
| **Year** | 2016 |
| **Publisher** | O'Reilly Media |
| **Topic(s)** | SRE, operations, reliability |
| **URL** | https://sre.google/books/ |

## Summary

Google's *Site Reliability Engineering* (SRE) codified the practices Google
developed to run large-scale, highly reliable services. The book established
SRE as a discipline bridging software engineering and operations.

Core thesis: **SREs are software engineers who operate as ops.** The focus
is on automation, reducing toil, and using engineering to solve
operational problems.

## Key Ideas

### 1. SRE vs Traditional Ops

| Traditional Ops | SRE |
|----------------|-----|
| Manual operations | Automate everything |
| Firefighting mode | Proactive engineering |
| Ops is separate from dev | Ops is software engineering |
| "Keep the lights on" | "Reduce toil, improve reliability" |
| Pager rotation for everyone | Limited pager load, error budgets |

### 2. Error Budgets

**Error budget** — allowable amount of downtime before reliability becomes
a problem.

```
Error budget = 100% - SLO (Service Level Objective)

Example:
- SLO = 99.9% uptime
- Error budget = 0.1% downtime per period (43.2 minutes/month)

If error budget is spent, stop feature releases, focus on reliability.
If error budget has room, can take more risks.
```

**Key insight:** Reliability is a trade-off with development speed. Error
budgets make this trade-off explicit and quantifiable.

### 3. Service Level Indicators (SLIs)

**SLI** — a metric that measures some aspect of service level.

Common SLIs:
- **Availability** — fraction of successful requests
- **Latency** — response time (p50, p95, p99)
- **Error rate** — HTTP 5xx responses
- **Throughput** — requests per second
- **Freshness** — data staleness for caching

### 4. Service Level Objectives (SLOs)

**SLO** — target value for an SLI.

```
SLI: 99.9% of requests return within 100ms
SLO: 95% of requests return within 100ms (target)
```

SLOs should be:
- **Measurable** — based on actual metrics
- **Achievable** — not aspirational
- **Meaningful** — reflect user experience

### 5. Service Level Agreements (SLAs)

**SLA** — a contract with customers about service levels, often with
financial penalties for failure.

```
SLA: "We guarantee 99.9% uptime. If not, you get 10% credit."
```

**Hierarchy:** SLI → SLO (internal target) → SLA (external contract)

### 6. Reducing Toil

**Toil** — operational work that tends to be manual, repetitive,
automatable, and provides no enduring value.

Examples of toil:
- Manual deployment steps
- Responding to routine alerts
- Manual failover procedures
- Manual certificate rotation

SRE principles:
- Automate toil away
- If toil can't be automated, question if the work should exist
- Engineer solutions, don't just fix problems

### 7. Incident Management

SRE establishes clear incident response processes:

**On-call responsibilities:**
- Primary on-call responds to pages
- Escalation paths defined
- Maximum weekly pager duty (e.g., 12 hours)

**Incident response:**
1. Detect (alert fires)
2. Acknowledge (someone takes ownership)
3. Diagnose (gather information)
4. Mitigate (reduce impact)
5. Resolve (restore service)
6. Postmortem (learn from incident)

### 8. Blameless Postmortems

**Blameless postmortem** — incident analysis focused on systemic causes,
not individual blame.

Key principles:
- Focus on *what* happened, not *who* did it
- Identify process failures, not human errors
- Actionable improvements, not finger-pointing

```
NOT blameless:
"John forgot to restart the server."

Blameless:
"The deployment process didn't include a restart step.
We should automate restarts after deployment."
```

### 9. Monitoring and Alerting

**Monitoring** — collecting and analyzing metrics.

**Alerting** — notifying humans when something is wrong.

SRE's alerting philosophy:
- **Boring alerts are better than exciting alerts** — reliable alerts mean
  engineers trust them
- Alert on symptoms, not causes — alert on "slow response time", not
  "high CPU usage"
- Reduce alert fatigue — too many alerts lead to ignoring all alerts

### 10. Risk Management

SRE approaches risk quantitatively:

| Approach | Description |
|----------|-------------|
| **Accept** | Live with the risk (e.g., single-region deployment) |
| **Mitigate** | Reduce impact (e.g., caching, read replicas) |
| **Avoid** | Don't do risky things (e.g., deploy on Fridays) |
| **Transfer** | Move risk elsewhere (e.g., use third-party SaaS) |

## Historical Context

### Origins at Google

Google developed SRE practices internally in the early 2000s:
- 2003: First SRE team formed
- 2008: SRE discipline formalized
- 2016: Book published, industry adoption

### From Ops to SRE

SRE evolved from traditional operations:
- **Ops (2000s)** — manual sysadmin work
- **DevOps (2009)** — dev+ops collaboration
- **SRE (2016)** — apply software engineering to operations

## Impact and Legacy

### Industry Adoption

SRE principles are now widely adopted:
- **Amazon** has similar reliability engineering teams
- **Facebook** has SRE-like teams
- **Microsoft** adopted SRE practices
- **Smaller companies** adapt SRE principles at scale

### SRE as a Discipline

The book established SRE as a distinct discipline:
- **SRE role** — software engineer who focuses on reliability
- **SRE tools** — specialized tooling for monitoring, alerting, automation
- **SRE practices** — error budgets, SLIs/SLOs, blameless postmortems

### Follow-up Books

Google published additional books:
- *The Site Reliability Workbook* (2018) — practical exercises
- *Site Reliability Engineering: The Second Edition* (2023)

## Key Concepts Summary

| Concept | Definition |
|----------|------------|
| **SLI** | Service Level Indicator — metric measuring service level |
| **SLO** | Service Level Objective — target value for SLI |
| **SLA** | Service Level Agreement — contract with customers |
| **Error budget** | Allowable downtime before reliability becomes issue |
| **Toil** | Manual, repetitive, automatable operational work |
| **Blameless postmortem** | Incident analysis focused on system, not blame |

## Connections

- **Builds on:** DevOps · Monitoring practices · System administration
- **Complements:** DevOps culture, Continuous Delivery
- **Related work:** [Accelerate (Forsgren et al., 2018)](forsgren-2018-accelerate.md)
- **Related topic:** [Process & Testing](../../topics/process/index.md) · [Distributed Systems](../../topics/distributed/index.md)
