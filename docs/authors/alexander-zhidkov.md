# Alexander Zhidkov (jdev)

| | |
|---|---|
| **Fields** | Software architecture, testing, domain-driven design, functional programming |
| **Known for** | Ergonomic Approach, Structural Design, Effect Diagrams |
| **Online** | [ergowiki.azhidkov.pro](https://ergowiki.azhidkov.pro/) · [Habr](https://habr.com/ru/users/jdev/articles/) |

## Biography

Alexander Zhidkov (jdev) is a Russian software architect and developer known for the **Ergonomic Approach** — a systematic methodology for building maintainable software systems. With commercial experience dating back to 2005, Zhidkov spent years searching for a way to "systematically write good code."

His journey took him through mainstream literature from Uncle Bob's *Clean Code* to Evans' *DDD*, but he found these approaches too subjective — lacking objective criteria for design decisions. In 2016, he explored functional programming and architecture, appreciating its determinism (no side effects = good code, side effects = bad code) but struggling to apply concepts like free monads to commercial projects.

In 2020, Zhidkov turned to "esoteric and ancient books," discovering Larry Constantine's *Structural Design*. There he found the principle of **balanced system form** — an objective, checkable design principle that became the foundation of his Ergonomic Approach.

Since 2020, Zhidkov has implemented the Ergonomic Approach in nine commercial projects, from reengineering key subsystems to full system redesigns. The results include a 300× performance improvement in a chat center routing module and a "simply works" delivery by two junior developers on a major retail project.

## Key Contributions

### The Ergonomic Approach (2020–present)

The Ergonomic Approach is a set of principles, models, techniques, and patterns that enable rapid design and development of codebases with:

- **Complete test coverage** of system behaviour
- **High cohesion** and **low coupling** in implementation

The approach centres on three areas:

#### 1. Testing

- Tests work through **public API only**
- **100% of system behaviour** is covered by tests
- **Mocks are only used** for unmanaged dependencies or hard-to-reproduce behaviour
- **Fast tests**: < 10 seconds per test, < 300 seconds for full test suite

#### 2. Domain Modeling

- Domain is split into **small, loosely-coupled aggregates**
- Aggregates and relationships form a **directed acyclic graph (DAG)**
- Aggregates are **immutable objects** stored in mutable repositories

#### 3. Behaviour Design

- **Business logic and I/O are separated**
- Business logic is written as **pure functions** with no side effects

### Structural Design (balanced form)

Drawing from Larry Constantine's *Structural Design* (1966–1975), Zhidkov applies the principle of **balanced system form** as an objective design criterion. Unlike subjective metrics like "number of abstraction levels," balanced form provides a clear, checkable standard for determining whether code is well-structured.

The principle states that a well-designed system should have balanced coupling and cohesion — a structural property that can be measured and verified.

### Effect Diagrams

Zhidkov invented **Effect Diagrams** as a tool for visualising and designing the **observable behaviour** of information systems. Observable behaviour defines a system's identity — what, where, and when it stores, requests, sends, and receives data.

Effect Diagrams help answer key questions for architects and tech leads:
- How to estimate task effort?
- How to ensure low coupling?
- What order to implement components for parallel work?
- What actually needs to be done?

### Industrial Functional Architecture

As an alternative to Clean/Hexagonal/Onion Architecture, Zhidkov proposes **Industrial Functional Architecture** for backend applications. He argues that Clean Architecture often brings unnecessary ceremony without benefits in application development contexts.

The approach combines:
- Pure functional business logic
- Domain-driven aggregates
- Simple repository patterns (avoiding JPA/Hibernate complexity)

### Aggregate-Based Decomposition

Zhidkov emphasises **aggregates from Domain-Driven Design** as the foundation of maintainable systems. His practical explanations and examples make this DDD concept accessible beyond the DDD community.

### Critique of JPA/Hibernate

Zhidkov advocates for **Spring Data JDBC over JPA/Hibernate** in production. His key argument: JPA's persistence context and dirty checking do not support immutable data models — an essential part of the functional style that reduces development cost.

### Multi-Faced Single Responsibility Principle

Zhidkov explores the ambiguity of SRP, noting that "Single Responsibility" can mean different things depending on interpretation — highlighting the importance of clear terminology in design discussions.

## Key Works

| Year | Title | Type | Page |
|------|-------|------|------|
| 2024 | *Эргономичный подход к разработке информационных систем v1.0M1* | Article | [→](../works/talks/zhidkov-2024-ergonomichnyy-podkhod.md) |
| 2024 | *Структурный дизайн. Древний секрет простого и быстрого кода* | Article | [→](../works/talks/zhidkov-2024-strukturnyy-dizayn.md) |
| 2024 | *Вам не нужна Чистая архитектура. Скорее всего* | Article | [→](../works/talks/zhidkov-2024-ne-nuzhna-chistaya-arkhitektura.md) |
| 2024 | *Агрегаты* | Article | [→](../works/talks/zhidkov-2024-agregaty.md) |
| 2024 | *Диаграмма эффектов: пример построения* | Article | [→](../works/talks/zhidkov-2024-diagramma-effektov.md) |
| 2024 | *ФП виновно в снижении стоимости программ. Вот мои доказательства* | Article | [→](../works/talks/zhidkov-2024-fp-stoimost.md) |
| 2024 | *Почему следует избегать использования JPA/Hibernate в продакшене* | Article | [→](../works/talks/zhidkov-2024-ne-jpa.md) |
| 2024 | *Подходы к декомпозиции бэкендов информационных систем* | Article | — |
| 2024 | *Абстрактные войны: public interface IAbstraction против абстракции* | Article | — |
| 2024 | *Многоликий принцип единственности ответственности* | Article | — |

## Influence

### Influenced by

- **Larry Constantine** — Structural Design and the principle of balanced form provided the objective design criterion Zhidkov was searching for
- **Eric Evans** — Domain-Driven Design, particularly the concept of aggregates as the foundation of maintainable systems
- **Rich Hickey** — Functional programming philosophy and the economic argument that simplicity reduces cost
- **Gang of Four** — Design Patterns; Zhidkov critiques the "program to an interface" maxim, distinguishing between useful abstractions and ceremony
- **Four decades of mainstream software practices** — Zhidkov's approach is largely a reaction against common practices he found problematic: layer packaging, connected JPA entity graphs, global component scanning, mock-based testing

### Influenced

- **Modern Java/Kotlin developers** — Through his Habr articles, talks (JPoint '23), and the Ergowiki site, Zhidkov influences how teams approach architecture, testing, and functional programming in the Java ecosystem
- **Clean Architecture critics** — His arguments against Clean Architecture as a default choice for application development provide an alternative perspective for teams
- **Spring Data JDBC adoption** — His advocacy for JDBC over JPA encourages developers to reconsider their data access layer choices

## Quotes

> "Эргономичный подход — способ быстро создавать эргономичные кодовые базы — кодовые базы, которые легко менять для поддержки новых требований."

> "Мне такое состояние дел не подходит, и для того, чтобы вместе со своими командами систематически и быстро создавать хорошие кодовые базы я разработал Эргономичный подход."

> "Отчаявшись научиться писать стабильно хороший объектно‑ориентированный код, в 2016 году я пошёл в сторону функционального программирования и архитектуры."

> "JPA по своей природе (persistence context и dirty checking) не поддерживает неизменяемую модель данных — неотъемлемую часть функционального стиля программирования."

> "Агрегаты лежат в основе поддерживаемых информационных систем."

> "Идентичность информационной системы определяется тем, как она взаимодействует с внешним миром. Что, где и когда она сохраняет и запрашивает; что, куда и когда отправляет. Это я называю наблюдаемым поведением."

## Further Reading

- [Ergowiki](https://ergowiki.azhidkov.pro/) — Official site with comprehensive documentation
- [Habr Profile](https://habr.com/ru/users/jdev/articles/) — Collection of articles
- [Trainer Adviser](https://github.com/) — Open-source project (16K lines of Kotlin) demonstrating the Ergonomic Approach
- [Project Mariotte](https://github.com/) — Minimal demonstration project
- Talks:
  - JPoint '23: "Рациональный подход к декомпозиции систем на модули или микросервисы"
  - Chat center routing reengineering (300× performance improvement case study)

## Related Pages

- [Architecture & Modularity](../topics/architecture/index.md)
- [OOP & Design](../topics/design/index.md)
- [Functional Programming](../topics/functional/index.md)
- [Eric Evans](eric-evans.md)
- [Rich Hickey](rich-hickey.md)
