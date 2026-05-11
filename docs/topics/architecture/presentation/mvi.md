# Model-View-Intent (MVI)

**Category:** Presentation  
**Source:** André Staltz — Cycle.js (2015); popularised in Android by Hannes Dorfmann (2016)

> The View produces Intents (user events), the Model reduces them into state, and the View observes that state. A closed reactive loop.

MVI extends unidirectional data flow with explicit reactive streams. The View emits Intents as an observable stream. A stream processor (often called a Processor or ViewModel) maps Intents to partial state changes (Results), which are folded into the current state. The View subscribes to the resulting state stream and re-renders.

```
    User input
       │
       ▼
┌─────────────┐     ┌─────────────┐     ┌─────────────┐
│    Intent   │────▶│  Processor  │────▶│   Result    │
│   (stream)  │     │  (reducer)  │     │  (stream)   │
└─────────────┘     └─────────────┘     └──────┬──────┘
                                               │
                                        ┌──────┴──────┐
                                        │    State    │
                                        │  (stream)   │
                                        └──────┬──────┘
                                               │
                                               ▼
                                        ┌─────────────┐
                                        │    View     │
                                        │ (reactive)  │
                                        └─────────────┘
```

**Strengths:**

- The state at any moment is a pure function of all past Intents — fully deterministic
- Excellent for reactive UI frameworks (RxJava, Kotlin Flow, Combine)
- Natural fit for time-travel debugging and state replay
- Encourages immutable, sealed state representations

**Weaknesses:**

- Steep learning curve: reactive streams and functional operators
- Managing concurrent Intents and cancellation is complex
- Can produce excessive re-renders without distinct-until-changed optimisation
- Less mature ecosystem than MVC or MVVM

**When to use:**

- Android with Jetpack Compose + Kotlin Flow
- Reactive web frameworks (Cycle.js, RxJS-based architectures)
- Applications where deterministic, replayable state is critical

## See Also

- [Unidirectional Data Flow](unidirectional-data-flow.md) — Flux/Redux: the same idea without explicit reactive streams
- [MVVM](mvvm.md) — declarative binding without the Intent→State loop
- [Observer](../../design/behavioral/observer.md) — the reactive subscription mechanism
