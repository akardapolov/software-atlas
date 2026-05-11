# Model-View-Presenter (MVP)

**Category:** Presentation  
**Source:** Mike Potel — Taligent (1996); popularised by Martin Fowler

> The Presenter assumes the responsibility of mediating all communication between the View and the Model. The View is passive and delegates all user input to the Presenter.

In MVP, the Presenter contains all presentation logic. The View is a thin, passive interface that forwards user events to the Presenter and exposes methods for the Presenter to update the display. The Model remains unaware of the UI.

```
     User input
        │
        ▼
┌───────────────┐
│     View      │ ──► forwards ──► ┌─────────────┐
│  (passive,    │                   │  Presenter  │
│   dumb)       │ ◄── updates ──── │ (all logic) │
└───────────────┘                   └──────┬──────┘
                                           │
                                    reads/writes
                                           │
                                           ▼
                                      ┌─────────┐
                                      │  Model  │
                                      │ (data)  │
                                      └─────────┘
```

**Supervising Controller variant:** The Presenter handles complex interactions; simple data binding goes directly View→Model.

**Passive View variant:** The View is extremely dumb — no data binding at all. The Presenter updates every UI element explicitly. Maximises testability.

**Strengths:**

- The View is trivial to swap or mock — ideal for unit testing
- All presentation logic lives in one place (the Presenter)
- Clear contract between View and Presenter via an interface
- Works well when the UI framework does not support data binding

**Weaknesses:**

- More boilerplate than MVC or MVVM
- Presenter can become a "god class" for complex screens
- Two-way communication between View and Presenter adds indirection

**When to use:**

- Android development (before Jetpack Compose / ViewModel)
- Applications where the View must be extremely simple
- Testing is the top priority

→ [Martin Fowler](../../../authors/martin-fowler.md)

## See Also

- [MVC](mvc.md) — View observes Model directly; Controller handles input
- [MVVM](mvvm.md) — declarative binding replaces manual Presenter updates
- [Repository](../data-access/repository.md) — how the Presenter typically accesses data
