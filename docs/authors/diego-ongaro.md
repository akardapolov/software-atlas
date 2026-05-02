# Diego Ongaro

| | |
|---|---|
| **Fields** | Distributed systems, Raft consensus algorithm |
| **Known for** | Raft consensus algorithm |

## Biography

Diego Ongaro is a computer scientist known for creating the **Raft consensus algorithm** during his PhD studies at Stanford (published 2014). Raft was designed as "an understandable consensus algorithm" — a more approachable alternative to Leslie Lamport's notoriously difficult Paxos algorithm.

Ongaro's work made consensus algorithms accessible to a broader audience and led to widespread adoption of consensus-based coordination systems.

## Key Contributions

### Raft Consensus Algorithm (2014)

Ongaro designed Raft to address the key problem of consensus in distributed systems: **how can multiple machines agree on a single value** when machines can fail?

**Raft's design principles:**
- **Understandability** — algorithm designed to be easier to learn and implement than Paxos
- **Leader election** — one node becomes leader for each term
- **Log replication** — consensus is about agreeing on a sequence of entries (a log)
- **Safety** — at most one value is ever chosen
- **Liveness** — if a majority of nodes are reachable, a value will eventually be chosen

**How Raft works:**

1. **Leader election** — nodes request votes, highest term wins
2. **Append entries** — leader appends log entries, replicates to followers
3. **Commit** — once majority has entry, it's committed
4. **Log matching** — followers discard inconsistent entries

**Why Raft matters:**
- Paxos, while theoretically sound, is notoriously difficult to understand and implement correctly
- Raft decomposes consensus into clearer sub-problems
- Many production systems use Raft (etcd, Consul, Nomad)

## Influence

### Influenced by

- **Leslie Lamport** — Paxos provided the theoretical foundation
- **John Ousterhout** — A Philosophy of Software Design provided systems thinking

### Influenced

Raft became the dominant consensus algorithm for production systems:

| System | How it uses Raft |
|---------|-------------------|
| **etcd** | Distributed key-value store for Kubernetes configuration |
| **Consul** | Service discovery and configuration |
| **HashiCorp Nomad** | Cluster scheduler |
| **TiKV** | Distributed transactional key-value store |
| **CockroachDB** | Distributed SQL database |
| **CoreDNS** | DNS server |

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2014 | In Search of an Understandable Consensus Algorithm | PhD thesis | — |

## Related Pages

- [Distributed Systems topic](../topics/distributed/index.md) — Consensus, CAP, and distributed systems
- [Leslie Lamport](leslie-lamport.md) — Paxos and distributed systems foundations
- [John Ousterhout](john-ousterhout.md) — Systems thinking and design philosophy
- [Paxos paper](../works/papers/lamport-1989-paxos.md) — Original consensus algorithm

## Further Reading

- Ongaro — *In Search of an Understandable Consensus Algorithm* (PhD thesis, 2014)
- Lamport — *The Part-Time Parliament* (1989) — Paxos paper
- Ousterhout — *A Philosophy of Software Design* (2018)
- Raft website — [https://raft.github.io/](https://raft.github.io/)

## Quotes

> "The key insight was to decompose the consensus problem into separate subproblems."
> — Diego Ongaro on Raft design

> "Paxos is notoriously difficult to understand and implement. Raft was designed to be understandable."
> — Ongaro comparing Raft to Paxos
