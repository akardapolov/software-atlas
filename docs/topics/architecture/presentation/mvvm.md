# Model-View-ViewModel (MVVM)

**Category:** Presentation  
**Source:** Microsoft — WPF / Silverlight (2005); John Gossman

> The ViewModel exposes data from the Model in a form optimised for the View, using declarative data binding to keep them synchronised automatically.

MVVM replaces the Controller/Presenter with a ViewModel — a pure data representation of the View's state. The UI framework binds View elements to ViewModel properties and commands, updating the display automatically when data changes.

```
┌─────────────────────────────────────────────────────────────┐
│                         View                                │
│  (declarative UI — XAML, SwiftUI, Jetpack Compose, Vue)     │
│                                                             │
│  <Text text="{{user.name}}" />                             │
│  <Button onClick="{{submit}}" />                           │
└──────────────────────┬──────────────────────────────────────┘
                       │ data binding
                       │ (automatic, two-way)
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                      ViewModel                              │
│  (observable properties + commands)                         │
│                                                             │
│  user: Observable<User>                                    │
│  submit: Command                                            │
└──────────────────────┬──────────────────────────────────────┘
                       │ reads/writes
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                        Model                                │
│                    (domain logic)                           │
└─────────────────────────────────────────────────────────────┘
```

**Strengths:**

- The View contains almost no code — it is pure markup/declaration
- ViewModel is 100% testable without instantiating UI components
- Two-way binding eliminates manual synchronisation boilerplate
- Natural fit for reactive and declarative UI frameworks

**Weaknesses:**

- Debugging data binding can be opaque
- Overuse of observable properties can cause cascading updates
- The "ViewModel per screen" approach can lead to large, fragile classes
- Binding expressions in markup are harder to test and refactor

**Implementations:** WPF/Xamarin (Microsoft), SwiftUI (Apple), Jetpack Compose (Android), Vue.js, Angular, Knockout.js.

## See Also

- [MVC](mvc.md) — the original pattern; View observes Model directly
- [MVP](mvp.md) — Presenter manually updates the View
- [Observer](../../design/behavioral/observer.md) — the mechanism behind data binding
