# Component-Based Architecture

**Category:** Presentation  
**Source:** Industry pattern; formalised by Web Components (W3C, 2011) and popularised by React (2013)

> Decompose the user interface into independent, reusable, self-contained components that manage their own state and rendering.

A component is a cohesive unit of UI: it bundles markup, styles, and behaviour. Components compose into trees, with data flowing downward (props) and events bubbling upward (callbacks). Each component decides when to re-render based on its props and internal state.

```
        ┌─────────────────┐
        │   App (root)    │
        └────────┬────────┘
                 │
    ┌────────────┼────────────┐
    ▼            ▼            ▼
┌────────┐  ┌────────┐  ┌────────┐
│Header  │  │ Sidebar│  │ Content│
└────────┘  └────────┘  └───┬────┘
                            │
                 ┌──────────┼──────────┐
                 ▼          ▼          ▼
            ┌────────┐ ┌────────┐ ┌────────┐
            │ Card   │ │ Card   │ │ Card   │
            └───┬────┘ └────────┘ └────────┘
                │
        ┌───────┴───────┐
        ▼               ▼
    ┌────────┐    ┌────────┐
    │ Image  │    │  Text  │
    └────────┘    └────────┘
```

**Strengths:**

- Reusability: the same component can appear in multiple contexts
- Isolation: changes to one component rarely affect others
- Composability: complex UIs built from simple, testable pieces
- Parallel development: teams own components independently

**Weaknesses:**

- Prop drilling: deeply nested trees pass data through many layers
- State scattered across many components can be hard to trace
- Component boundaries are design decisions that are costly to change
- Over-componentisation splits related logic across many files

**State management in component trees:**

| Approach | When to use |
|----------|-------------|
| Lift state up | Two sibling components share data |
| Context API | Moderate depth, avoid prop drilling |
| External store (Redux, Pinia) | Many components, complex state |

## See Also

- [Unidirectional Data Flow](unidirectional-data-flow.md) — how state moves between components
- [MVC](mvc.md) — predecessor: components replaced the monolithic View
- [Micro-frontends](micro-frontends.md) — components at the application level
