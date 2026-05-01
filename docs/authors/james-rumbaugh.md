# James Rumbaugh

| | |
|---|---|
| **Born** | 1947 |
| **Fields** | UML, object modeling, OMT |
| **Known for** | Creator of OMT, one of "three amigos" |
| **Awards** - ACM Fellow, IEEE Fellow |

## Biography

James Rumbaugh is an American computer scientist known for developing the **Object Modeling Technique (OMT)** in the early 1990s. His work focused on **modeling data and behavior** in object-oriented systems, with particular emphasis on the structure of objects and their state.

Rumbaugh, with Grady Booch and Ivar Jacobson, co-created **UML (Unified Modeling Language)**, contributing the **class diagram** notation that became the most widely used UML diagram type.

## Key Contributions

### Object Modeling Technique (OMT)

OMT was one of the first comprehensive OO modeling approaches:

1. **Object Model** — classes, attributes, relationships (associations, inheritance)
2. **Dynamic Model** — state diagrams showing object behavior over time
3. **Functional Model** — data flow diagrams (later less emphasized)

OMT's **object model** became the foundation of UML class diagrams.

### UML Class Diagrams

Rumbaugh's notation for object relationships became UML class diagram standard:

- **Association** — basic line connecting classes
- **Aggregation** — hollow diamond (weak whole-part)
- **Composition** — filled diamond (strong whole-part)
- **Generalization** — hollow triangle arrow (inheritance)
- **Dependency** — dashed arrow with open arrow

### State Diagrams

Rumbaugh's dynamic modeling work introduced formal **state diagrams**:

```
[Idle] -- (event) --> [Processing]
[Processing] -- (complete) --> [Done]
[Done] -- (new event) --> [Idle]
```

State diagrams model:
- **States** — conditions an object can be in
- **Transitions** — events causing state changes
- **Actions** — what happens on transitions

This influenced:
- **State machine pattern** — widely used in game and UI programming
- **UML state diagrams** — standard for modeling object behavior

### Object Thinking

Rumbaugh's approach emphasized thinking in objects:
- **Identity** — objects have distinct identity beyond their values
- **Encapsulation** — objects hide their internal structure
- **Behavior** — objects have methods and state

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 1991 | *Object-Oriented Modeling and Design* | Book | [→](../works/books/rumbaugh-1991-omt.md) |

## Influence

### Influenced by

- **Simula** — class and object concepts
- **Entity-Relationship modeling** — Chen's ER diagrams for data modeling

### Influenced

- **UML standard** — class and state diagrams became core UML
- **State machine pattern** — behavioral design in object systems
- **Object modeling tools** — OMT tools became UML tools

## Legacy

Rumbaugh's impact:
- **Standardized OO notation** — UML class diagrams are universally recognized
- **State modeling** — formal approach to object behavior
- **Tool ecosystem** — Together, Enterprise Architect, Rational tools

## Further Reading

- [Wikipedia: James Rumbaugh](https://en.wikipedia.org/wiki/James_Rumbaugh)
- [UML State Machines](https://www.uml-diagrams.org/state-machine-diagrams.html)

## Related Pages

- [Grady Booch](grady-booch.md)
- [Ivar Jacobson](ivar-jacobson.md)
- [State pattern](../topics/design/) (if exists)
