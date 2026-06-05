# Verifiable Engineering

**Tags:** epistemology · verification · practice

How we know what we claim about software systems — and why most "metrics" are simulations rather than evidence.

---

## Essence

Software engineering is often confused with software *simulation*. A simulation produces outputs that *look* correct. Engineering produces outputs whose correctness can be *verified* through a chain of evidence linking measurement to claim.

The distinction is not academic. In 2018, PostgreSQL's `fsync` behavior revealed that years of "successful" database operations were built on a misunderstanding of what the OS reported. The numbers were real. Their relationship to durability was not.

This is the core idea of **verifiable engineering**: every claim about a system must be traceable to a procedure that, if it produced a different result, would falsify the claim.

---

## Three Questions

A simple epistemic framework for evaluating any engineering claim:

### 1. What exactly does this number measure?

**The problem:** A metric is not a phenomenon. "Response time" as logged by a framework is a *procedure* (timestamps at two points in code), not the *phenomenon* (user-perceived latency from click to pixel).

**The test:** Can you describe the physical or logical procedure that produces this number, independently of what you want it to mean?

**Example — fsync-gate (PostgreSQL, 2018):**

PostgreSQL called `fsync()` and assumed success meant data was on disk. But on some Linux configurations, `fsync()` reported success while data remained in volatile write-back caches. The *procedure* (system call returned 0) did not connect to the *phenomenon* (data durability).

> "The operating system had been lying to us for years." — PostgreSQL developers

**Operationalism (Bridgman, 1927):** A concept is defined by the operations used to measure it. "Durability" means nothing without the specific procedure that tests it (pull power cord, restart, check data).

→ [Percy Bridgman](../../authors/percy-bridgman.md)

---

### 2. Under what conditions is this claim false?

**The problem:** Unfalsifiable claims are not engineering. "Our system is scalable" is unfalsifiable without a specific load, metric, and threshold. "Our system handles 10,000 RPS at p99 < 100ms" is falsifiable.

**The test:** Can you describe an experimental outcome that would prove the claim wrong?

**Example — Backup restoration testing:**

Claim: "We have daily backups." This is unfalsifiable — it describes an activity, not an outcome.

Claim: "We can restore from backup to production within 4 hours with < 0.1% data loss." This is falsifiable — run the restore and measure.

Most organizations do the first. Engineering organizations do the second.

---

### 3. Are we looking at system behavior or an event?

**The problem:** When something goes wrong, we ask "what changed?" But not all variation has a specific cause. Some variation is inherent to the system.

**Walter Shewhart's insight (1924):**

In manufacturing, some variation is *common cause* (inherent to the process) and some is *special cause* (a specific assignable event). Reacting to common-cause variation as if it were special cause makes the system worse (Deming's "funnel experiment").

**Control charts:**

A Shewhart control chart plots a metric over time with control limits (typically ±3σ). Points within limits represent common-cause variation — the system is "in control" even if individual points fluctuate. Points outside limits signal special causes worth investigating.

```
Metric value
    ↑
UCL ├──── ─ ─ ─ ─ ─ ─ ─ ─ ─
    │         ·   ·
    │       ·       ·   ·
    │     ·   · ·     ·
    │   ·       · ·
CL  ├──────────────────────
    │     · ·       ·
    │       ·   · ·   ·
    │         ·       ·
LCL ├──── ─ ─ ─ ─ ─ ─ ─ ─ ─
    └────┬──┬──┬──┬──┬──┬──→ Time

UCL = Upper Control Limit (μ + 3σ)
CL  = Center Line (mean)
LCL = Lower Control Limit (μ - 3σ)

"In control" ≠ "good"
"In control" = "predictable — the variation is from the system itself"
```

**Application to software:**

- A single slow request is an *event* — investigate if it repeats
- A steady increase in p99 latency over weeks is *system behavior* — the system itself is degrading
- A latency spike after a deployment is a *special cause* — the deployment changed something
- Random latency jitter within historical bounds is *common cause* — the system is behaving as it always has

**The mistake:** Treating every anomaly as a special cause (over-reacting) or treating special causes as noise (under-reacting). The control chart provides a decision rule.

→ [Walter Shewhart](../../authors/walter-shewhart.md) · [W. Edwards Deming](../../authors/deming.md)

---

## Examples from the field

### fsync-gate (PostgreSQL, 2018)

**What happened:** PostgreSQL discovered that `fsync()` success did not guarantee durability on certain Linux/storage configurations.

**Engineering lesson:** The *procedure* (system call return value) was not a valid proxy for the *phenomenon* (data on stable storage). A verifiable claim would have required a destructive test: write data, crash the machine, verify recovery.

→ [Michael Stonebraker](../../authors/michael-stonebraker.md) — PostgreSQL history

### Backup restoration testing

**The simulation:** "We run backups nightly." Activity without verification.

**The engineering:** "We restore from backup to an isolated environment weekly and run validation queries." Falsifiable, reproducible, linked to the actual claim (recoverability).

### Chaos engineering

**The simulation:** "Our system is resilient." Unfalsifiable aspiration.

**The engineering:** "We randomly terminate 5% of production instances monthly and measure recovery time against SLO." Falsifiable, with a defined procedure and acceptance criteria.

---

## The Simulation Trap

| Simulation | Engineering |
|---|---|
| "We have 99.9% uptime" (from monitoring dashboard) | "We measure uptime via an independent probe that matches user experience" |
| "Tests pass" (unit tests in CI) | "We run property-based tests, mutation tests, and canary deployments" |
| "Code coverage is 80%" | "Coverage of critical paths is 100%, and we know which paths are critical" |
| "The build is green" | "The build is green *and* the artifact was deployed to staging and exercised" |

The difference is not effort. It is the **chain of evidence** connecting a claim to a falsifying procedure.

---

## Related

→ [Testing](../testing/index.md) — verification techniques and practices
→ [Observability](observability/) — understanding production behavior
→ [Authors: Walter Shewhart](../../authors/walter-shewhart.md) — Statistical quality control
→ [Authors: Percy Bridgman](../../authors/percy-bridgman.md) — Operationalism
→ [Authors: W. Edwards Deming](../../authors/deming.md) — Systems thinking

---

## References

- Bridgman, P. W. — *The Logic of Modern Physics* (1927)
- Shewhart, W. A. — *Economic Control of Quality of Manufactured Product* (1931)
- Deming, W. E. — *Out of the Crisis* (1982)
- Deming, W. E. — *The New Economics* (1994)
- PostgreSQL fsync issue — [PostgreSQL wiki](https://wiki.postgresql.org/wiki/Fsync_Errors)
