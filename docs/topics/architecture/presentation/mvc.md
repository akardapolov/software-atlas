# Model-View-Controller (MVC)

**Category:** Presentation  
**Source:** Trygve Reenskaug — Xerox PARC / Smalltalk-80 (1979)

> Separate an application into three interconnected components: the Model (data), the View (display), and the Controller (input handling).

MVC was introduced in Smalltalk-80 at Xerox PARC. The core idea is that the Model knows nothing about the UI, the View observes the Model and redraws when data changes, and the Controller interprets user input and updates the Model.

```
     User input
        │
        ▼
┌───────────────┐
│  Controller   │ ──► updates ──► ┌─────────┐
│ (interprets   │                  │  Model  │
│   input)      │ ◄── notifies ── │ (data)  │
└───────────────┘                  └────┬────┘
                                        │
                              observes  │
                                        ▼
                                  ┌─────────┐
                                  │  View   │
                                  │ (render)│
                                  └─────────┘
```

**Classic MVC** (Smalltalk): The View registers as an observer of the Model. When the Model changes, it notifies all observers, and the View redraws.

**Web MVC** (Rails, Django, Spring): The Controller receives an HTTP request, interacts with the Model, and selects a View to render. The View does not observe the Model directly — it receives data from the Controller.

**Strengths:**

- Clear separation of concerns
- Multiple Views can observe the same Model
- Model is independent of the UI, making it testable
- Ubiquitous — supported by virtually every web framework

**Weaknesses:**

- Controller can grow bloated with business logic
- In Web MVC, View and Controller are tightly coupled to the request lifecycle
- Classic MVC's observer-based update can be inefficient for complex UIs

## See Also

- [MVP](mvp.md) — Presenter replaces Controller; View is passive
- [MVVM](mvvm.md) — ViewModel with declarative data binding
- [Observer](../../design/behavioral/observer.md) — the mechanism behind View→Model updates
- [Smalltalk](../../../languages/smalltalk/index.md) — the origin of MVC
