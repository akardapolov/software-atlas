# Transactions: Concepts and Facilities

| | |
|---|---|
| **Authors** | Jim Gray |
| **Year** | 1981 |
| **Topic** | Distributed systems, databases, transactions |

## Summary

Gray's paper introduced the concept of **transactions** as fundamental abstractions for database and distributed systems. This work established the theoretical foundation for **ACID properties** — a set of guarantees that define reliable transaction processing.

Gray's work formalized how concurrent transactions should behave in distributed systems, addressing challenges like concurrency, recovery, and fault tolerance.

## Key Concepts

### ACID Properties

| Property | Meaning | Guarantees |
|---------|---------|----------|
| **Atomicity** | All-or-nothing: transaction completes fully or not at all | Complete or rollback |
| **Consistency** | Database moves from one valid state to another | Valid state transitions only |
| **Isolation** | Concurrent transactions don't interfere with each other | Serializable isolation |
| **Durability** | Once committed, changes persist despite failures | Survives system failures |

### Two-Phase Commit (2PC)

Gray described **two-phase commit** as a protocol for atomic transactions across distributed nodes:

**Phase 1 — Prepare:**
- Coordinator asks all participants: "Can you commit?"
- Each participant votes YES or NO and prepares locally

**Phase 2 — Commit:**
- If all vote YES: coordinator sends "COMMIT"
- If any vote NO: coordinator sends "ABORT"
- All participants execute and release locks

**Problems with 2PC:**
- **Blocking** — Participants may block while waiting
- **Coordinator failure** — If coordinator crashes after Phase 1, participants are blocked indefinitely
- **Resource locks** - Long-held locks can cause timeouts

### Recovery Techniques

Gray documented mechanisms for handling failures:

| Technique | Description |
|----------|-------------|
| **Write-ahead logging** | Log operations before committing | Enables rollback |
| **Savepoints** | Periodic checkpoints to restore state | Faster recovery |
| **Idempotence** | Operations designed to be retryable | Minimizes side effects |
| **Compensating transactions** | Combine multiple operations into single atomic unit |

## Distributed Transaction Challenges

**CAP Theorem (Brewer, 2000):**
During network partitions, cannot have all three ACID properties:
- **CP systems** — Prioritize consistency, sacrifice availability
- **AP systems** - Prioritize availability, accept temporary inconsistency

**Conflict resolution:**
- **Optimistic locking** - Assume conflict, detect on commit
- **Timestamp ordering** - Detect conflicts by version
- **Compensating** - Merge conflicting operations atomically

## Influence

Gray's work directly influenced:
- **Leslie Lamport** - Logical clocks, Paxos, distributed systems
- **Brewer** - CAP theorem

## Related Pages

- [Distributed Systems topic](../topics/distributed/) - CAP, consensus, transactions
- [Leslie Lamport](../authors/leslie-lamport.md) - Distributed systems
- [Jim Gray](../authors/jim-gray.md) — Transactions author

## Further Reading

- Gray — *Transactions: Concepts and Facilities* (1981)
- Gray & Lorie — *The Transaction Concept* (1981) - Book chapter
- Lamport — *Time, Clocks, and the Ordering of Events* (1978)
- Brewer — *CAP conjecture* (2000)
- Gilbert & Lynch — *Brewer's conjecture* (2002)
