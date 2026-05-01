# In Search of an Understandable Consensus Algorithm (Raft)

| | |
|---|---|
| **Author(s)** | Diego Ongaro, John Ousterhout |
| **Year** | 2014 |
| **Publication** | USENIX Annual Technical Conference |
| **Topic(s)** | Distributed Systems, Consensus |

## Summary

Raft is a consensus algorithm designed to be more understandable than Paxos while providing equivalent safety and fault-tolerance guarantees. It divides the consensus problem into three relatively independent subproblems: leader election, log replication, and safety.

## Key Ideas

1. **Strong leadership** — Raft uses a strong leader; only the leader handles client requests
2. **Leader election** — nodes elect a leader through a randomized timeout mechanism
3. **Log replication** — the leader replicates its log to followers
4. **Safety** — committed entries are durable and will be executed by all state machines

## Historical Context

Paxos, the dominant consensus algorithm, was notoriously difficult to understand and implement correctly. Raft was explicitly designed with understandability as a primary goal.

## Impact and Legacy

Raft became the de facto standard for consensus in distributed systems:
- **etcd** — Kubernetes' backing store
- **Consul** — HashiCorp's service discovery
- **TiKV** — distributed transactional key-value store

## Connections

- **Builds on:** [Lamport — Paxos](lamport-1978-clocks.md)
- **Related author:** [Diego Ongaro](../../authors/diego-ongaro.md)
- **Related topic:** [Distributed Systems](../../topics/distributed/)
