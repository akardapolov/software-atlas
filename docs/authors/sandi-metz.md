# Sandi Metz

| | |
|---|---|
| **Fields** | OOP, Ruby, practical design |
| **Known for** | *Practical Object-Oriented Design in Ruby* (POODR) |
| **Education** - University of Michigan (Music) |

## Biography

Sandi Metz is an American software engineer, educator, and author best known for *"Practical Object-Oriented Design in Ruby" (POODR), published in 2012. Her work focuses on **practical object-oriented design** with concrete examples from Ruby and Rails development.

Metz emerged as a voice of **pragmatic OOP**, emphasizing that composition is generally preferable to inheritance and that small, well-defined objects are preferable to large, complex ones. Her work directly builds on **Rebecca Wirfs-Brock's Responsibility-Driven Design** and **Robert C. Martin's SOLID principles**.

## Key Contributions

### Composition Over Inheritance

Metz advocates composition as the default:

- **Inheritance creates tight coupling** — changes ripple through hierarchy
- **Composition is flexible** — behaviors can be swapped at runtime
- **"Has-a" vs "Is-a"** — prefer "has-a" relationships

```ruby
# Prefer composition
class Order
  def initialize(payment_processor: PaymentProcessor)
    @processor = payment_processor
  end

  def pay(amount)
    @processor.process(amount)
  end
end
```

### Duck Typing for Interfaces

Metz emphasizes **behavior over type**:

```ruby
# Program to interface, not class
def notify(user)
  if user.respond_to?(:email)
    user.email(message)
  elsif user.respond_to?(:phone)
    user.call(message)
  end
end
```

This enables:
- **Flexibility** — any object with right method works
- **Testability** — easy to create mock objects
- **Loose coupling** — no dependency on concrete classes

### Small Objects

Metz's four rules for small objects:

1. **Classes ≤ 100 LOC** — keep classes short and focused
2. **Methods ≤ 5 LOC** — methods should do one thing
3. **≤ 4 parameters** — too many parameters indicate complexity
4. **Controllers know 1 instance variable** — controllers manage one thing

These rules enforce:
- **Single Responsibility** — each object does one thing well
- **Comprehensibility** — small code is easier to understand
- **Maintainability** — small changes, local impact

### "Tell, Don't Ask"

Metz extends Martin's Law of Demeter:

- **Tell objects what to do** — delegate responsibility
- **Don't ask for state** — avoid getters that expose internals
- **Encapsulation** — objects manage their own state

```ruby
# Tell (good)
class Order
  def pay
    @payment_processor.charge(@total)
  end

# Ask (bad - exposes state)
class Order
  def processor
    @payment_processor
  end
end
```

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 2012/2018 | *Practical Object-Oriented Design in Ruby* (1st/2nd ed.) | Book | [→](../works/books/metz-2018-poodr.md) |
| 2016 | *99 Bottles of OOP* (with Katrina Owen) | Book | [→](../works/books/metz-2016-99bottles.md) |

## Influence

### Influenced by

- **Rebecca Wirfs-Brock** — Responsibility-Driven Design
- **Robert C. Martin** — SOLID principles, Clean Code
- **Ruby language** — dynamic typing, object model, Smalltalk heritage

### Influenced

- **Ruby community** — POODR is canonical OO design book for Rubyists
- **Practical OOP thinking** — moved from theory to concrete examples
- **Test-driven design** — emphasis on design, then implementation

## Quotes

> "Objects should be like people: they're better when they have a single responsibility."

> "Inheritance is your code, but composition is your design."

> "Duplication is far cheaper than the wrong abstraction."

> "The goal is design that you can understand tomorrow as well as you do today."

## Further Reading

- [Sandi Metz's blog](https://sandimetz.com/)
- [POODR book](https://www.poodr.com/)

## Related Pages

- [Rebecca Wirfs-Brock](rebecca-wirfs-brock.md)
- [Robert C. Martin](robert-c-martin.md)
- [Ruby language](../languages/ruby/) (to be created)
