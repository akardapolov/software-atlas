# Practical Object-Oriented Design in Ruby

| | |
|---|---|
| **Author** | Sandi Metz |
| **Year** | 2012 (1st ed.), 2018 (2nd ed.) |
| **Publication** | Addison-Wesley |
| **Topic(s)** | Object-oriented design, Ruby, RDD |

## Summary

This book is **the definitive guide** to practical object-oriented design in Ruby. Metz builds directly on **Wirfs-Brock's Responsibility-Driven Design (RDD)** and **Robert C. Martin's SOLID principles**, demonstrating through real Ruby examples how to create maintainable, flexible OO systems. POODR became the **canonical OO design book** for Ruby developers.

## Key Ideas

### Ruby-Specific OOP

Metz presents OOP as Ruby developers use it:

- **Everything is an object** — `nil.class` → `NilClass`, `5.times { }`
- **Duck typing** — "if it quacks like a duck..." — behavior over type
- **Blocks** — Ruby closures similar to Smalltalk blocks
- **Mixins** — modules can extend classes without inheritance
- **Open classes** — can add methods to any class

### Four Rules for Small Objects

Metz establishes guidelines for object design:

1. **Classes ≤ 100 LOC** — keep classes short and focused
2. **Methods ≤ 5 LOC** — methods do one thing well
3. **≤ 4 parameters** — too many parameters indicate complexity
4. **Controllers know 1 instance var** — manage single object state

These rules enforce:
- **Single Responsibility** — each class has clear role
- **Comprehensibility** — small code is easier to understand
- **Maintainability** — local changes, minimal impact

### Composition Over Inheritance

Metz advocates:

- **Favor composition** — build complex objects from simple parts
- **Prefer modules/mixins** — add behaviors without deep inheritance
- **"Has-a" vs "is-a"** — composition uses "has-a" relationship
- **Avoid deep hierarchies** — complex inheritance trees are fragile

### Design Patterns in Ruby

The book shows applying patterns:

- **Singleton** — one instance of configuration class
- **Factory** - create objects without specifying concrete class
- **Strategy** - pluggable algorithms
- **Observer** - publish/subscribe pattern for events
- **Decorator** - add behavior dynamically with modules

## Historical Significance

### Ruby OO Design Foundation

POODR established:

- **Ruby best practices** - became community standard
- **Responsibility thinking** - standard Ruby design approach
- **Composition preference** - influenced modern Ruby design
- **Educational impact** - taught Rubyists good OO design

### Practical Influence

The book influenced:

- **Ruby community** - moved from heavy inheritance to composition
- **Framework design** - Rails influenced by POODR principles
- **Testing approaches** - objects are testable when designed with RDD

## Legacy

POODR remains:

- **Essential reading** - for any Ruby OO developer
- **Design philosophy** - practical, pragmatic OOP thinking
- **Community influence** - Ruby's "happy path" style
- **Translations** - available in multiple languages

## Connections

- **Builds on:** [Wirfs-Brock's RDD](../books/wirfs-brock-1990-designing-oo.md)
- **Preceded by:** [Robert C. Martin](../../authors/robert-c-martin.md) (SOLID)
- **Influenced:** Ruby community, Rails framework
- **Author:** [Sandi Metz](../../authors/sandi-metz.md)
- **Related Works:** [99 Bottles](../books/metz-2016-99bottles.md)
- **Related:** [Ruby language](../../languages/ruby/index.md) (to be created)
