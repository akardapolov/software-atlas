# ФП виновно в снижении стоимости программ. Вот мои доказательства

## Overview

Arguments for how functional programming reduces development cost, with empirical evidence from commercial projects.

## Key Points

### Why Not JPA?

Among features of Zhidkov's approach, **Spring Data JDBC over JPA** draws the most resistance. The answer is simple: JPA (persistence context, dirty checking) doesn't support immutable data models — an essential part of the functional style.

### Why Functional Programming?

The choice of functional over "normal" imperative programming comes down to one sentence:
> "Functional style helps me reduce development cost for the business and make project managers happy."

### The Cost Reduction Hypothesis

Many disagree with the assertion that functional style reduces cost. Zhidkov presents it as a hypothesis with empirical support.

### Evidence

The article provides evidence from commercial projects demonstrating that:
- Functional style correlates with lower development cost
- Immutable data models reduce bugs and rework
- Pure functions are easier to test and reason about

## Related Pages

- [Alexander Zhidkov](../../authors/alexander-zhidkov.md)
- [Functional Programming](../../topics/functional/index.md)
- [Rich Hickey](../../authors/rich-hickey.md)
