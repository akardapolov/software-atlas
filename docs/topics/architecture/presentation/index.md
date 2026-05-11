# Presentation Patterns

Patterns for structuring the user interface layer. These patterns address the same fundamental problem — separating presentation from domain logic — but differ in how they divide responsibilities and manage data flow.

---

## The Core Problem

A user interface must:

1. Display data to the user
2. Accept and interpret user input
3. Keep the display synchronised with the underlying model

Presentation patterns provide a structure for these responsibilities so that UI code remains testable, reusable, and independent of the domain model.

## The Patterns

| Pattern | Responsibility split | Data flow | Best for |
|---------|---------------------|-----------|----------|
| [MVC](mvc.md) | Model / View / Controller | View observes Model; Controller handles input | Web frameworks, desktop GUIs |
| [MVP](mvp.md) | Model / View / Presenter | Presenter mediates all communication; View is passive | Testing-heavy UIs, Android |
| [MVVM](mvvm.md) | Model / View / ViewModel | Declarative data binding between View and ViewModel | Modern UI frameworks (WPF, SwiftUI, Jetpack Compose) |
| [Unidirectional Data Flow](unidirectional-data-flow.md) | Action → Store → View | Actions dispatched to central store | React, Redux, Vuex |
| [MVI](mvi.md) | Intent → Model → View | Reactive stream of Intents to State | Android (Compose), Cycle.js |
| [Component-Based](component-based.md) | Self-contained components | Props down, events up | React, Vue, Angular |
| [Micro-frontends](micro-frontends.md) | Independent frontend modules | Module federation / Web Components | Large teams, multi-framework |

## Evolution

```
Smalltalk-80 (1979)          Web era (2000s)              Modern (2010s+)
     │                             │                            │
     ▼                             ▼                            ▼
┌─────────┐               ┌─────────────┐              ┌─────────────┐
│  MVC    │ ────────────► │   Web MVC   │ ───────────► │  MVVM       │
│ classic │               │ (Rails etc) │              │ (declarative│
└─────────┘               └─────────────┘              │  binding)   │
                                                       └─────────────┘
                              │
                              ▼
                       ┌─────────────┐
                       │    MVP      │
                       │ (Presenter) │
                       └─────────────┘
```

## How to Choose

- **Use MVC** when the framework natively supports it (Rails, Django, ASP.NET MVC, Spring MVC).
- **Use MVP** when you need maximum testability and the View should be as dumb as possible.
- **Use MVVM** when the UI framework supports declarative data binding (WPF, SwiftUI, Jetpack Compose, Vue, Angular).
- **Use Unidirectional Data Flow** when you need centralised, traceable state (React, Redux, Vuex).
- **Use MVI** when building reactive UIs with streams (Android Compose, RxJS).
- **Use Component-Based** as the default decomposition strategy for modern frontend.
- **Use Micro-frontends** when multiple independent teams own different parts of the same product.

## See Also

- [Layered Architecture](../domain-centric/layered-architecture.md) — where the Presentation layer sits
- [Observer](../../design/behavioral/observer.md) — the mechanism behind MVC's View→Model updates
- [Smalltalk](../../../languages/smalltalk/index.md) — where MVC originated
- [OOP & Design](../../design/index.md) — SOLID principles apply to presentation layer design
