# Yukihiro Matsumoto

| | |
|---|---|
| **Born** | 1965, Japan |
| **Known as** | "Matz" |
| **Fields** | Ruby, language design, OOP |
| **Known for** | Creator of Ruby |

## Biography

Yukihiro "Matz" Matsumoto is a Japanese computer programmer and creator of **Ruby programming language**. He developed Ruby in 1993 as an interpreted, dynamically-typed, object-oriented language that embodies the **Smalltalk philosophy** while being practical for scripting.

Matsumoto's goal was to create a language that made programmers "happy" — where expression is natural and joy comes from the act of programming itself.

## Key Contributions

### Design Philosophy

Ruby's design focuses on:

- **"Programmer happiness"** — the language should feel good to write
- **Principle of Least Surprise (POLS)** — things should behave as expected
- **Multiple ways to do things** — explicit contrast to Python's "one obvious way"
- **Expression** — code should read like a natural language

### Everything is an Object

Ruby takes Smalltalk's "everything is an object" literally:

```ruby
5.times { puts 'hello' }  # 5 is an object with times method
nil.class  # => NilClass
[1, 2, 3].map(&:to_s)  # methods are objects
```

There are no primitive types — everything inherits from `Object`.

### Mixins via Modules

Ruby provides **multiple inheritance through modules**:
- Modules can be mixed into classes
- No single inheritance hierarchy constraints
- Enables shared behavior across disparate classes

```ruby
module Debuggable
  def debug; puts "Called: #{method_name}" end
end

class User
  include Debuggable
  def login; puts "Logging in..." end
end
```

### Open Classes

Ruby's classes are always **open** — can add methods anywhere:

```ruby
class String
  def reverse_words
    split.reverse.join
  end
end
```

This "monkey patching" enables:
- **Library extension** without forking
- **Prototype development** — quick experimentation
- **Danger** — can lead to fragile code if overused

### Blocks (Closures)

Ruby's **blocks** are influenced by Smalltalk blocks:
```ruby
[1, 2, 3].each { |n| puts n * 2 }
users.select { |u| u.active? }
File.open(path) { |f| f.read }
```

Blocks are first-class closures with:
- **Implicit yield** — methods can accept blocks
- **Procs** — blocks can be assigned to variables
- **Lambda syntax** — modern Ruby also supports `->`

## Key Works

| Year | Title | Type | Page |
|------|-------|------|
| 2001 | *Ruby in a Nutshell* | Book | [→](../works/books/matsumoto-2001-ruby.md) |
| 2007 | *The Ruby Programming Language* (with Flanagan) | Book | [→](../works/books/matsumoto-2007-ruby-pl.md) |

## Influence

### Influenced by

- **Smalltalk** — "everything is an object," blocks, live environment
- **Perl** — text processing, scripting language tradition
- **Lisp** — closures, everything is an object

### Influenced

- **Ruby ecosystem** — Rails, Sinatra, RSpec, Bundler
- **Modern languages** — Elixir, Crystal inspired by Ruby's syntax and philosophy
- **Web development** — Ruby on Rails popularized MVC and convention over configuration

## Quotes

> "Ruby is designed to make programmers happy."

> "The Ruby programming language has been made popular by the success of Rails, but it existed before Rails, and will continue to exist after Rails."

> "I hope to see Ruby help every programmer in the world to be productive, and to enjoy programming, and to be happy."

## Further Reading

- [Wikipedia: Ruby](https://en.wikipedia.org/wiki/Ruby_(programming_language))
- [Ruby-Lang.org](https://www.ruby-lang.org/)
- [Matz's blog](https://www.rubyist.net/~matz/)

## Related Pages

- [Alan Kay](alan-kay.md)
- [Ruby language](../languages/ruby/) (to be created)
- [Smalltalk](../languages/smalltalk/)
