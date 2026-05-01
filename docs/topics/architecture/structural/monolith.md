# Monolith

**Category:** Structural  
Source: Traditional architecture pattern

> A single-tiered application where all components are combined into one program running as a single process.

The monolith is the default architecture: all functionality — UI, business logic, data access — lives in a single codebase and deploys as a single unit.

**Types:**

| Type | Description |
|------|-------------|
| **Big Ball of Mud** | No structure; everything depends on everything |
| **Layered Monolith** | Code organised by layer (UI, business, data) |
| **Modular Monolith** | Code organised by domain module within one deployable unit |

**When to use:**

- Small team (< 8 developers)
- Simple domain
- Rapid prototyping
- Low operational complexity required

**When to evolve away:**

- Team size makes code ownership unclear
- Different parts need different scaling profiles
- Deployment cadence varies across domains
- Bounded contexts are well understood

## See Also

- [Modular Monolith](modular-monolith.md)
- [Microservices](microservices.md)
