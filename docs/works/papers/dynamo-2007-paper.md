# Dynamo: Amazon's Highly Available Key-value Store

| | |
|---|---|
| **Author(s)** | Giuseppe DeCandia, Deniz Hastorun, Madan Jampani, Gunavardhan Kakulapati, Avinash Lakshman, Alex Pilchin, Swaminathan Sivasubramanian, Peter Vosshall, Werner Vogels |
| **Year** | 2007 |
| **Publication** | ACM Symposium on Operating Systems Principles (SOSP) |
| **Topic(s)** | Distributed Systems, Databases, Availability |

## Summary

Dynamo is a highly available key-value storage system that sacrifices consistency under certain failure scenarios to achieve high availability. It was developed by Amazon to handle their massive e-commerce infrastructure.

## Key Ideas

1. **Eventual Consistency** — reads may not reflect the most recent write, but will converge
2. **Consistent Hashing** — distributes data across nodes with minimal reorganization
3. **Vector Clocks** — track causality between versions of data
4. **Gossip Protocol** — nodes share state information through epidemic propagation

## Historical Context

By 2007, Amazon's infrastructure required a storage system that remained available even during network partitions and server failures. Dynamo was designed as an AP system (in CAP terms) that could always accept writes.

## Impact and Legacy

Dynamo inspired:
- **Amazon DynamoDB** — managed NoSQL database service
- **Cassandra** — Apache's distributed database
- **Voldemort** — LinkedIn's key-value store
- **Riak** — Basho's distributed database

## Connections

- **Builds on:** [Brewer — CAP Conjecture](brewer-2000-cap.md)
- **Related topic:** [Distributed Systems](../../topics/distributed/)
