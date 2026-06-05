# Walter A. Shewhart

| | |
|---|---|
| **Born** | 1891, New Canton, Illinois, USA |
| **Died** | 1967, Troy Hills, New Jersey, USA |
| **Fields** | Statistics, quality control, engineering |
| **Known for** | Control charts, statistical quality control |
| **Key work** | *Economic Control of Quality of Manufactured Product* (1931) |

## Biography

Walter Andrew Shewhart was an American physicist, engineer, and statistician who founded the field of **statistical quality control**. Working at Bell Telephone Laboratories in the 1920s, Shewhart developed the theoretical foundations for using statistical methods to monitor and improve manufacturing processes.

His work emerged from a practical problem: how to distinguish between the natural variation inherent in any process and the variation caused by specific, assignable factors that could be corrected. This distinction became the basis for the **control chart** — a tool still used today in manufacturing, healthcare, and software operations.

Shewhart's influence extended far beyond manufacturing. His ideas directly shaped W. Edwards Deming's work on quality management, which in turn influenced the Toyota Production System, Lean manufacturing, and modern DevOps practices.

## Key Contributions

### Control Charts (1924)

**Core idea:** Plot a process metric over time with statistically derived control limits. Variation within limits is "common cause" (inherent to the system). Points outside limits indicate "special cause" (a specific assignable factor).

```
        ↑
   UCL  ├──── · ·    ← special cause (investigate)
        │      ·
        │  · ·   · ·
        │·   · ·   ·
   CL   ├──────────────
        │  ·   · ·
        │    ·     ·
   LCL  ├──── ·      ← special cause (investigate)
        └────┬──┬──┬──→ Time
```

**The critical insight:** A process "in statistical control" is not necessarily a *good* process — it is a *predictable* process. The variation is from the system itself, not from external shocks.

**Application to software:**

- **Common cause variation:** Latency jitter within historical bounds, memory usage fluctuating around a baseline, error rates that stay within range. The system is behaving as designed.
- **Special cause variation:** A latency spike after a deployment, a sudden jump in error rate, memory climbing steadily over days. Something changed.

**The mistake:** Treating common-cause variation as special cause (over-reacting — "fixing" what isn't broken) or treating special causes as noise (under-reacting — missing real problems).

### The PDCA Cycle (later developed by Deming from Shewhart's ideas)

Shewhart introduced the concept of continuous improvement through iterative cycles:

1. **Plan** — design a change or test
2. **Do** — execute on a small scale
3. **Study** (Shewhart's original term; Deming changed to "Check") — observe effects
4. **Act** — adopt, adapt, or abandon based on results

This cycle underlies modern CI/CD, A/B testing, and iterative development.

### Common Cause vs Special Cause Variation

| | Common Cause | Special Cause |
|---|---|---|
| **Source** | Built into the system | External, assignable |
| **Action** | Change the system itself | Find and remove the specific cause |
| **Example** | p99 latency varies 45–55ms | p99 latency jumps to 500ms |
| **Mistake** | Tweaking individual requests | Redesigning the whole system |

Deming's "funnel experiment" demonstrates what happens when you over-react to common-cause variation: trying to "correct" random noise makes the system perform *worse* than leaving it alone.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 1931 | *Economic Control of Quality of Manufactured Product* | Book | — |
| 1939 | *Statistical Method from the Viewpoint of Quality Control* | Book | — |

## Influence

### Influenced

- **W. Edwards Deming** — Deming studied with Shewhart at Bell Labs and extended his ideas into total quality management. The "Deming Cycle" (PDCA) is directly derived from Shewhart's work.
- **Toyota Production System** — statistical process control influenced Lean manufacturing and Just-In-Time production
- **Modern DevOps** — SLOs, error budgets, and control charts in observability all trace back to Shewhart's distinction between common and special causes

## Quotes

> "The engineer must not only know what to do, but he must also know why he does it."

> "Rule 1 of data analysis: Plot the data."

> "The definition of random in terms of a physical operation is notoriously difficult."

## Further Reading

- [Wikipedia: Walter A. Shewhart](https://en.wikipedia.org/wiki/Walter_A._Shewhart)
- Shewhart, W. A. — *Economic Control of Quality of Manufactured Product* (1931)
- Deming, W. E. — *Out of the Crisis* (1982) — extends Shewhart's ideas to management
- Deming, W. E. — *The New Economics* (1994)

## Related Pages

- [Verifiable Engineering](../topics/engineering/verifiable-engineering.md)
- [Percy Bridgman](percy-bridgman.md)
- [W. Edwards Deming](deming.md)
