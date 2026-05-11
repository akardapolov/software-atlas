# Unidirectional Data Flow

**Category:** Presentation  
**Source:** Facebook — Flux architecture (2014); Dan Abramov — Redux (2015)

> All data changes follow a single direction: actions trigger updates to a central store, which then notifies views to re-render.

In unidirectional data flow, the View never mutates state directly. Instead, it dispatches an action — a plain object describing what happened. A reducer (pure function) processes the action and produces a new state. The store holds this state and notifies subscribers.

```
     User clicks button
            │
            ▼
    ┌───────────────┐
    │    Action     │  { type: "ADD_TODO", text: "Buy milk" }
    └───────┬───────┘
            │
            ▼
    ┌───────────────┐
    │   Dispatcher  │  (optional in Redux)
    └───────┬───────┘
            │
            ▼
    ┌───────────────┐
    │    Reducer    │  pure function: (state, action) → newState
    └───────┬───────┘
            │
            ▼
    ┌───────────────┐
    │     Store     │  single source of truth
    └───────┬───────┘
            │ notifies
            ▼
    ┌───────────────┐
    │     View      │  re-renders from new state
    └───────────────┘
```

**Strengths:**

- State changes are predictable and traceable
- Time-travel debugging: replay actions to reconstruct any state
- Easy to test: reducers are pure functions
- Centralised state simplifies reasoning about data flow

**Weaknesses:**

- Boilerplate: every state change requires action types, action creators, reducers
- Can be overkill for simple UIs
- Normalising relational data in a flat store is non-trivial
- Asynchronous side effects (API calls) require additional abstractions (thunks, sagas, epics)

**Implementations:** Redux (JavaScript), Vuex/Pinia (Vue), NgRx (Angular), Zustand, Recoil, Riverpod (Flutter).

## See Also

- [MVVM](mvvm.md) — two-way data binding as an alternative to explicit actions
- [Observer](../../design/behavioral/observer.md) — the notification mechanism behind Store→View updates
- [CQRS](../../data/cqrs.md) — unidirectional flow at the system level
