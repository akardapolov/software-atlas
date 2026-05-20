# Optional

`Optional<T>` is a container that may or may not hold a value —
a type-safe alternative to returning `null`, mainly for return types.

## Creation

```java
Optional<String> present = Optional.of("hello");
Optional<String> empty   = Optional.empty();
Optional<String> maybe   = Optional.ofNullable(possiblyNull);
```

## Consuming

```java
present.ifPresent(System.out::println);        // prints "hello"

String result = maybe.orElse("default");       // "default" if empty
String result2 = maybe.orElseGet(() -> computeDefault());
String result3 = maybe.orElseThrow(() -> new IllegalStateException("missing"));
```

## Transforming

```java
Optional<Integer> length = present.map(String::length);   // Optional[5]

// flatMap avoids nested Optionals
Optional<String> nested = Optional.of(Optional.of("hi"));
Optional<String> flat = nested.flatMap(o -> o);           // Optional[hi]

// Filter values
Optional<String> filtered = present.filter(s -> s.length() > 3);  // Optional[hello]
```

## Best practices

In idiomatic Java, `Optional` is most commonly used as a **return type**,
not as a field type or a method parameter:

| Usage | Recommendation |
|---|---|
| **Return type** | ✅ Recommended — documents that a value may be absent |
| **Method parameter** | ❌ Avoid — use overloading or explicit null checks instead |
| **Class field** | ❌ Avoid — adds wrapper overhead; use nullable fields |
| **Collection element** | ❌ Avoid — `List<Optional<T>>` is an anti-pattern; filter instead |

```java
// ✅ Good: Optional as return type
public Optional<User> findById(long id) { ... }

// ❌ Bad: Optional as parameter
public void process(Optional<String> maybeName) { ... }
```
