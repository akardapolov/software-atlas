# Java Cheat Sheet — Part II

The deeper parts of the language and core standard-library APIs:
inheritance and polymorphism, access control, exceptions, collections,
generics, lambdas and the Stream API, functional interfaces, file I/O,
multithreading, and a set of useful modern features.

---

## Contents

1. [Inheritance](#inheritance)
2. [Polymorphism / Abstraction](#polymorphism--abstraction)
3. [Encapsulation (Advanced)](#encapsulation-advanced)
4. [Exceptions](#exceptions)
5. [Collections](#collections)
6. [Generics](#generics)
7. [Lambdas / Stream API](#lambdas--stream-api)
8. [Functional Interfaces](#functional-interfaces)
9. [Files / I/O](#files--io)
10. [Multithreading](#multithreading)
11. [Useful Extras](#useful-extras)

---

## Inheritance

> **Key constructs:** `extends`, method overriding, `@Override`, `super`, `super()`, `protected`

### `extends`

`extends` declares that a class inherits from another class. The child
class gets the fields and methods of the parent and can add its own.
Java supports single class inheritance: a class can extend only one
parent.

**Practice:** create a `Student` class extending `Person`. Show that a
`Student` object can use the parent's methods.

### Method Overriding

Overriding means redefining a parent method in a child class with the
same signature. The call is resolved by the object's actual runtime
type, not by the variable type. This mechanism is the foundation of
polymorphism.

**Practice:** add a method `describe()` to `Person`. Override it in
`Student` so the descriptions differ, then call it through a `Person`
variable holding a `Student` object.

### `@Override`

`@Override` is an annotation telling the compiler that a method is
intended to override a parent method. If the signature does not match
any parent method, a compilation error occurs. It protects against
typos in the method name or parameters.

**Practice:** make a deliberate typo in an overridden method's name and
compare the compiler's reaction with and without `@Override`.

### `super`

`super` is a reference to the parent part of the object. It is used to
call the parent's version of an overridden method: `super.describe()`.
This helps extend the parent's behavior instead of replacing it
entirely.

**Practice:** in the overridden `describe()` of `Student`, first call
`super.describe()` and then append the student-specific details.

### `super()`

`super(...)` calls the parent constructor. It must be the first
statement in the child constructor. If omitted, the compiler inserts a
call to the parent's no-argument constructor automatically — which
fails if the parent has none.

**Practice:** give `Person` a constructor taking `name`. In the
`Student` constructor, pass the name to the parent with `super(name)`.

### `protected`

`protected` is an access modifier: the member is visible inside its own
package and in subclasses. It sits between `private` and `public`. Use
it for members intended for extension.

**Practice:** make a `Person` field `protected` and access it from
`Student`. Try the same from an unrelated class in another package and
compare the results.

---

## Polymorphism / Abstraction

> **Key constructs:** polymorphism, `abstract`, `abstract class`, `interface`, `implements`, `default`, `static` (in interfaces)

### Polymorphism

Polymorphism is the ability to work with objects of different types
through one common parent type. A variable of type `Person` can hold a
`Student`, a `Teacher`, and so on; the method actually invoked depends
on the runtime type of the object. It lets code depend on abstractions
rather than concrete classes.

**Practice:** create an array of `Person` holding different subclasses
and call `describe()` on each element in a loop.

### `abstract`

`abstract` marks a class that cannot be instantiated directly.
Abstract classes can declare `abstract` methods — methods without a
body that subclasses must implement. They are used to build a common
base with a partial implementation.

**Practice:** make `Person` abstract and try `new Person(...)` —
examine the error. Then instantiate only the concrete subclasses.

### `abstract class`

An abstract class can hold fields, constructors, and implemented
methods alongside abstract ones. Unlike an interface, it can hold
state. A class extends exactly one abstract class.

**Practice:** add a field and a constructor to the abstract `Person`,
plus one abstract method `describe()`. Implement the method in two
subclasses.

### `interface`

An interface is a contract: a set of method signatures that a class
promises to provide. A class can implement many interfaces, which
compensates for single class inheritance. Interfaces define what an
object can do rather than what it is.

**Practice:** create an interface `Describable` with the method
`String describe()`. Implement it in `Student`.

### `implements`

`implements` connects a class to an interface. The class must provide
an implementation for every abstract method of the interface. Several
interfaces are listed separated by commas.

**Practice:** create two interfaces, for example `Describable` and
`Savable`. Implement both in one class and exercise both behaviors.

### `default` Methods in Interfaces

Since Java 8, interfaces can have `default` methods with a body. They
provide a ready implementation that implementing classes inherit as-is
but may override. They were introduced to evolve interfaces without
breaking existing implementations.

**Practice:** add a `default` method to `Describable` that prints the
result of `describe()`. Override it in one class and not in another,
then compare the behavior.

### `static` Methods in Interfaces

Since Java 8, interfaces can also contain `static` methods. They are
called via the interface name and are not inherited by implementing
classes. They usually hold utility and factory helpers related to the
interface.

**Practice:** add a static method to an interface, for example
`static void greet(Describable d)`, and call it as
`Describable.greet(...)`.

---

## Encapsulation (Advanced)

> **Key constructs:** `private`, `public`, `protected`, package-private, `static`, `final`, `final class`, `final method`

### Access Modifiers: `private`, `public`, `protected`, package-private

Java has four access levels. `public` — visible everywhere;
`protected` — the own package plus subclasses; package-private (no
modifier) — the current package only; `private` — the current class
only. Choosing the narrowest sufficient level keeps a class's internals
safe from uncontrolled changes.

**Practice:** create a class with one field of each visibility level.
Try accessing each field from the same package, from another package,
and from a subclass; record the results in a table.

### `static` Members

`static` fields and methods belong to the class rather than to any
object. A static field is shared by all instances — a common counter, a
constant, a settings object. Static methods can be called without
creating an object but see no instance state.

**Practice:** add a static counter of created `Student` objects.
Increment it in the constructor and print the total after creating
several students.

### `final` Fields

A `final` field can be assigned exactly once — in the declaration or in
the constructor. Combined with `private` and no setters, it makes the
field effectively immutable. Constants are declared as
`public static final`.

**Practice:** declare `final String name` in `Person` and initialize it
in the constructor. Try to change it in a method and examine the error.

### `final` Classes

A `final` class cannot be extended. Marking a class final forbids
subclassing — standard practice for utility classes (`Math`, `String`)
and classes where extension would break invariants. All records are
implicitly final.

**Practice:** mark a small class `final` and try to extend it. Examine
the compilation error.

### `final` Methods

A `final` method cannot be overridden in subclasses. This fixes
behavior where overriding would be dangerous, while the class itself
may still be extended.

**Practice:** make `describe()` in `Person` final. Try to override it
in `Student` and examine the error.

---

## Exceptions

> **Key constructs:** `try`, `catch`, `finally`, `throw`, `throws`, `Exception`, `RuntimeException`, `NullPointerException`, custom exception, multi-catch (`|`)

### `try` / `catch`

`try` wraps code that may fail; `catch` handles the error. When an
exception is thrown inside `try`, execution jumps to the matching
`catch` block, and the program continues instead of crashing. Several
`catch` blocks can follow one `try`.

**Practice:** parse user-entered text into a number with
`Integer.parseInt` inside `try`. Catch `NumberFormatException`, print a
friendly message, and let the program continue.

### `finally`

`finally` holds code that runs whether or not an exception occurred —
usually cleanup: closing files, releasing resources. It executes even
when `try` or `catch` contains `return`. For most modern cleanup needs,
try-with-resources is the better tool.

**Practice:** put a print statement in `finally` and throw an exception
in `try`. Make sure the message appears regardless of the exception.

### `throw`

`throw` raises an exception object explicitly:
`throw new IllegalArgumentException("age < 0")`. It is used when the
code detects an invalid state it cannot fix locally. The method stops
immediately at that point.

**Practice:** in `setAge(int age)`, throw `IllegalArgumentException`
when `age < 0`. Check the behavior by passing a negative value.

### `throws`

`throws` in a method signature declares checked exceptions the method
may throw. The caller must either handle them with `try-catch` or
declare them further up. Unchecked exceptions (`RuntimeException` and
its subclasses) do not have to be declared.

**Practice:** write a method that reads a file and declares
`throws IOException`. Call it both with and without handling.

### Checked Exceptions: `Exception`

Checked exceptions descend from `Exception` but not from
`RuntimeException`. The compiler requires handling them — they
represent recoverable situations: a missing file, a network failure.
This design is unique among mainstream languages and widely debated.

**Practice:** try calling `new FileInputStream("missing.txt")` without
handling. Examine the compiler message, then add handling.

### Unchecked Exceptions: `RuntimeException`

`RuntimeException` and its subclasses are unchecked: no handling is
required. They usually signal programming errors:
`IllegalArgumentException`, `IllegalStateException`,
`ArithmeticException`. Handling them everywhere would clutter code;
fixing the bug is the right response.

**Practice:** divide a number by zero and examine
`ArithmeticException`. Discuss why the compiler does not force handling.

### `NullPointerException`

`NullPointerException` (NPE) occurs when calling a member on `null`. It
is the most famous Java error. Helpful messages that name the variable
that was `null` appeared in Java 14 behind the
`-XX:+ShowCodeDetailsInExceptionMessages` flag and are enabled by
default since Java 15. `Optional` and `Objects.requireNonNull` help
design null-safe APIs.

**Practice:** create a `String` variable with `null` and call
`.length()` on it. Read the NPE message, then guard the call with
`if (s != null)`.

### Custom Exceptions

Your own exception class extends `Exception` (checked) or
`RuntimeException` (unchecked). Custom types let calling code catch
exactly your error, and the name documents the business situation:
`InsufficientFundsException`.

**Practice:** create `InvalidAgeException extends RuntimeException` and
throw it from `setAge` instead of `IllegalArgumentException`. Catch it
separately.

### Multi-catch: `catch (A | B e)`

Multi-catch handles several exception types in one block:
`catch (IOException | SQLException e)`. The variable is treated as the
closest common supertype. Alternatives must not be related by
inheritance.

**Practice:** write one `catch` for
`NumberFormatException | NullPointerException` around parsing code and
print the exception type.

---

## Collections

> **Key constructs:** `List`, `ArrayList`, `LinkedList`, `Set`, `HashSet`, `TreeSet`, `Map`, `HashMap`, `TreeMap`, `Iterator`, `Collections`

### `List` and `ArrayList`

`List` is an ordered collection that allows duplicates and access by
index. `ArrayList` — its main implementation — stores elements in a
growable array: fast `get(i)` and add-at-end, slow inserts in the
middle. In most everyday tasks `ArrayList` is the default choice.

**Practice:** create an `ArrayList<String>` with several names. Add,
replace by index, and remove elements; print the list with a loop and
directly via `System.out.println(list)`.

### `LinkedList`

`LinkedList` is a `List` implemented as a doubly linked list: inserts
and deletes at the ends are cheap, but index access walks the list. It
also implements `Deque` (queue and stack operations). In practice
`ArrayList` is nearly always faster; `LinkedList` wins only for
intensive end-operations as a deque.

**Practice:** use `LinkedList` as a stack (`push` / `pop`) and as a
queue (`offer` / `poll`). Compare indexed `get` performance against
`ArrayList`.

### `Set` and `HashSet`

`Set` stores unique elements without a defined order. `HashSet` — the
standard implementation — hashes elements: O(1) `add` / `contains` /
`remove` on average, but iteration order is unpredictable. Uniqueness
follows `hashCode()` and `equals()`.

**Practice:** add duplicate words to a `HashSet<String>` and print the
result. Show that `contains` works instantly even for a large set.

### `TreeSet`

`TreeSet` stores unique elements in sorted order (a red-black tree).
Add / contains / remove cost O(log n). Sorting follows the natural
order or a provided `Comparator`; navigation methods `first()`,
`last()`, `floor()`, `ceiling()` are available.

**Practice:** add numbers in random order into a `TreeSet` and print
them sorted. Try `first()`, `last()`, and `ceiling()`.

### `Map` and `HashMap`

`Map` stores key→value pairs; keys are unique. `HashMap` — the main
implementation — hashes keys: O(1) `get` / `put` on average, with no
defined iteration order. `get`, `put`, `containsKey`, `getOrDefault`,
and `computeIfAbsent` cover most needs.

**Practice:** build a word-frequency map from a text using
`merge(word, 1, Integer::sum)`. Print the entries.

### `TreeMap`

`TreeMap` keeps keys sorted (a red-black tree): O(log n) operations and
ordered iteration, plus range queries: `headMap`, `tailMap`, `subMap`,
`firstKey`, `lastKey`. Use it when key order matters.

**Practice:** store product prices in a `TreeMap<String, Double>`.
Print the products cheaper than a threshold with `headMap`.

### `Iterator`

`Iterator` walks a collection: `hasNext()`, `next()`, and `remove()`.
It is one of the safe ways to modify a collection while traversing —
alongside `Collection.removeIf()` and the `ListIterator` methods. The
enhanced for loop over collections compiles to iterator calls under the
hood.

**Practice:** remove all negative numbers from a list with an iterator
while traversing. Try the same with a for-each loop and examine the
`ConcurrentModificationException`.

### `Collections` Utility

`Collections` is a utility class with static helpers: `sort`,
`reverse`, `shuffle`, `min`, `max`, `binarySearch`, and immutable
wrappers such as `unmodifiableList`. They operate on the passed
collection in place or return wrappers.

**Practice:** shuffle a list, sort it descending with a comparator, and
find the min and max with `Collections`.

---

## Generics

> **Key constructs:** `<T>`, `<K,V>`, `<?>`, `extends`, `super`, generic class, generic method

### `<T>` — Type Parameter

`<T>` is a type parameter: a placeholder replaced by a real type at
the call site. `List<String>` means "a list of strings", and the
compiler guarantees the type — no casts, no `ClassCastException` from
containers. Generics work by type erasure: `T` is unknown at runtime.

**Practice:** write a method that prints any `List<T>`. Call it with a
`List<String>` and a `List<Integer>`.

### `<K, V>` — Key and Value

`<K, V>` parameterizes a type with two parameters — the standard shape
of maps: `Map<String, Integer>`. Each type parameter plays its own role
and is checked separately at compile time.

**Practice:** implement a tiny `Pair<K, V>` class with `getKey()` and
`getValue()` and use it for a list of name–age pairs.

### `<?>` — Wildcard

`<?>` means "a collection of some unknown type". Use it when the code
only reads elements as `Object` and does not care about the exact
element type: `void printAll(List<?> items)`. You cannot add elements
through `List<?>` — the compiler cannot verify the type.

**Practice:** write `static void printSize(List<?> list)` and call it
with lists of different element types.

### `extends` — Upper Bound

`<T extends Number>` restricts `T` to `Number` or its subclasses;
`List<? extends Number>` accepts `List<Integer>`, `List<Double>`. Such
a list is a producer: you can read `Number` from it, but not write.
This is the PECS principle (Producer Extends).

**Practice:** write `static double sum(List<? extends Number> list)`
and call it with `List<Integer>` and `List<Double>`. Try adding an
element and examine the error.

### `super` — Lower Bound

`List<? super Integer>` accepts `List<Integer>`, `List<Number>`,
`List<Object>`. Such a list is a consumer: you can safely add an
`Integer` to it, while reading yields only `Object`. This is the second
half of PECS (Consumer Super).

**Practice:** write `static void addNumbers(List<? super Integer> list)`
that adds several numbers; call it with `List<Number>` and
`List<Object>`.

### Generic Class

A generic class declares type parameters at the class level:
`class Box<T> { private T value; }`. Every method of the class can then
use `T`. The same class works safely with many types without
duplication.

**Practice:** implement `Box<T>` with `put(T)` and `get()`. Create
`Box<String>` and `Box<Integer>` and check that no cast is needed.

### Generic Method

A generic method declares its own type parameter:
`static <T> T first(List<T> list)`. The type is inferred at each call.
A generic method can live in both generic and plain classes.

**Practice:** write `static <T> void swap(List<T> list, int i, int j)`.
Apply it to lists of different element types.

---

## Lambdas / Stream API

> **Key constructs:** `->`, `::`, `stream()`, `filter()`, `map()`, `sorted()`, `reduce()`, `collect()`, `Collectors`, `forEach()`

### `->` — Lambda

A lambda is a short inline function: `(a, b) -> a + b`. It implements
a functional interface (one with exactly one abstract method) and can
be passed around as a value. Lambdas replace short anonymous classes
and make behavior a first-class value.

**Practice:** create a `Comparator<String>` by string length with a
lambda and sort a list with it.

### `::` — Method Reference

`::` refers to an existing method instead of writing a lambda:
`System.out::println`, `String::toUpperCase`, `this::process`. Four
kinds exist: a static method, an instance method, a method of an
arbitrary object of a type, and a constructor (`ArrayList::new`).
Method references shorten code where the lambda only forwards
arguments.

**Practice:** replace lambdas with method references:
`list.forEach(s -> System.out.println(s))` →
`list.forEach(System.out::println)`, and `map(s -> s.toUpperCase())` →
`map(String::toUpperCase)`.

### `stream()`

`stream()` creates a stream — a pipeline for declarative data
processing — from a collection or array. Streams are lazy: nothing runs
until a terminal operation; and single-use: a stream cannot be
traversed twice. The result of a chain usually lands in a new
collection rather than mutating the source.

**Practice:** turn a list into a stream, run `filter`, `map`, and
`forEach`, then confirm that the source list did not change.

### `filter()`

`filter(Predicate<T>)` keeps only the elements matching the condition.
It is an intermediate operation: it returns a new stream and runs
lazily. Several `filter` calls can be chained.

**Practice:** from a list of numbers select those divisible by 3 and
greater than 10 in one chain.

### `map()`

`map(Function<T, R>)` transforms each element into a new value, possibly
of another type. The resulting stream has the same number of elements.
Use it for extraction (`Person::getName`) and conversion
(`String::length`).

**Practice:** from a list of words get a list of their lengths, then a
list of uppercase words.

### `sorted()`

`sorted()` orders the stream: without arguments — in natural order;
with a `Comparator` — in a custom one. It is a stateful intermediate
operation. Build comparisons with `Comparator.comparing`, `reversed`,
and `thenComparing`.

**Practice:** sort a list of students by grade descending, with ties
broken alphabetically by name.

### `reduce()`

`reduce` folds the stream into a single value: a sum, a product, a
concatenation. Forms: `reduce(identity, BinaryOperator)` and
`reduce(BinaryOperator)` returning an `Optional<T>`. It is a terminal
operation.

**Practice:** compute the sum and the product of a list of integers
with `reduce`. Try `reduce` without an identity value and unwrap the
`Optional`.

### `collect()` and `Collectors`

`collect` is the terminal operation that materializes a stream into a
collection or a summary. The `Collectors` factory provides the recipes:
`toList`, `toSet`, `toMap`, `joining`, `groupingBy`, `partitioningBy`,
`counting`.

**Practice:** from a list of words collect: a `List` of the long ones,
a `Set` of first letters, a `Map` of word → length, and one string
joined by commas.

### `forEach()`

`forEach(Consumer<T>)` performs an action on every element — the
simplest terminal operation. Ordered streams process elements in order;
parallel streams do not guarantee it. It replaces an explicit loop when
the body is a single action.

**Practice:** print all elements of a stream with `forEach`, then
collect them into a list instead and print once.

---

## Functional Interfaces

> **Key constructs:** `@FunctionalInterface`, `Runnable`, `Comparable`, `Comparator`, `Function`, `Predicate`, `Consumer`, `Supplier`

### `@FunctionalInterface`

`@FunctionalInterface` marks an interface intended for lambdas — one
with exactly one abstract method (`default` and `static` methods do not
count). The annotation is optional but makes the compiler verify the
contract. Every lambda target type in the JDK is such an interface.

**Practice:** annotate your `Describable` with `@FunctionalInterface`
and create its instance with a lambda. Try adding a second abstract
method and examine the error.

### `Runnable`

`Runnable` — `void run()` — an action without arguments or a result: a
task for a thread, a callback, a deferred job. It is the oldest
functional interface of the platform (Java 1.0). Its lambda takes no
parameters: `() -> doWork()`.

**Practice:** create a `Runnable` with a lambda and run it directly and
in a new thread.

### `Comparable`

`Comparable<T>` defines the natural order with
`int compareTo(T other)`: negative — less, zero — equal, positive —
greater. It is implemented by the class itself and used by
`Collections.sort`, `TreeSet`, and `Arrays.sort`. It should be
consistent with `equals`.

**Practice:** implement `Comparable` in `Student` by age; sort a list
and print it. Discuss what changes when the sort field changes.

### `Comparator`

`Comparator<T>` is an external, composable order:
`int compare(T a, T b)`. Build one with `comparing`, `thenComparing`,
`reversed`, and `nullsFirst`. Several comparators may coexist for the
same type, unlike the single natural order of `Comparable`.

**Practice:** build a comparator: by grade descending, ties — by name.
Compare it with the previous `Comparable`-based sort.

### `Function`

`Function<T, R>` — `R apply(T t)` — transforms an input into an output.
Composable: `andThen` applies this function and then another;
`compose` runs another one first. Variants: `UnaryOperator<T>` (T → T)
and `BiFunction<T, U, R>` (two inputs).

**Practice:** build a chain of `Function<String, String>` — trim, then
uppercase — with `andThen` and apply it to messy input.

### `Predicate`

`Predicate<T>` — `boolean test(T t)` — a condition as a value.
Composable with `and`, `or`, and `negate`; `Predicate.not(...)` (Java
11) inverts it. Predicates drive `filter`, `removeIf`, and conditional
logic.

**Practice:** build a predicate "starts with A and length > 3" with
`and`, then apply it via `filter` and `removeIf`.

### `Consumer`

`Consumer<T>` — `void accept(T t)` — consumes a value and returns
nothing: printing, saving to a database, sending a message. `andThen`
chains consumers. The `forEach` parameter is a `Consumer`.

**Practice:** build a chain of two consumers — log and save — with
`andThen` and apply it via `forEach`.

### `Supplier`

`Supplier<T>` — `T get()` — produces a value with no input: a factory,
a lazy value, a random generator. It is the standard way to express
deferred computation, for example in `Optional.orElseGet`. Together
with lambdas it forms the lightweight lazy-evaluation toolkit.

**Practice:** create a `Supplier<List<String>>` returning a fresh list.
Compare `orElse(expensive())` with `orElseGet(() -> expensive())` —
make the expensive branch print when it runs.

---

## Files / I/O

> **Key constructs:** `File`, `FileReader`, `FileWriter`, `BufferedReader`, `BufferedWriter`, try-with-resources, `Files`, `Paths`

### `File`

`File` is the legacy representation of a path — a file or directory. It
can check existence (`exists`), create (`createNewFile`, `mkdirs`),
delete, and list directory entries. It does not read or write content
itself — streams do that. The modern `Path` API is preferred for new
code.

**Practice:** create a `File` for a new path, check `exists()`, create
the file, and print `getAbsolutePath()`. List the contents of a folder
with `list()`.

### `FileReader` / `FileWriter`

`FileReader` and `FileWriter` are character streams for files: reading
and writing text character by character. They use the platform-default
encoding and buffer nothing — slow and inflexible in practice. For new
code, `Files.newBufferedReader` with an explicit charset is the right
choice.

**Practice:** write a few characters to a file with `FileWriter` and
read them back with `FileReader`, one `read()` call at a time. Note
that the platform encoding was chosen implicitly.

### `BufferedReader` / `BufferedWriter`

Buffered wrappers add buffering to a character stream: reads and writes
go through an in-memory buffer, cutting OS calls.
`BufferedReader.readLine()` reads a whole line at a time — the classic
way to process text files line by line. For output, `newLine()` writes
a platform line separator.

**Practice:** read a text file line by line with `readLine()` and count
the lines and the total length. Append lines to a file with
`BufferedWriter` and verify the result.

### `try-with-resources`

`try (var in = new BufferedReader(...)) { ... }` — resources declared
in the try header are closed automatically, in reverse order, even on
exception. Any `AutoCloseable` works. This replaces manual
`finally { close() }` and prevents resource leaks.

**Practice:** rewrite an explicit `close()` in `finally` as
try-with-resources. Add a second resource (a writer) and check the
closing order.

### `Files`

`Files` is the NIO.2 utility for file content and metadata:
`readString`, `writeString`, `readAllLines`, `copy`, `move`, `delete`,
`exists`, `size`, and `walk` for directory trees. Short text I/O is one
call: `Files.writeString(path, text)`.

**Practice:** write and read a small text file with `writeString` and
`readString`. Print the size with `Files.size` and the whole file with
`readAllLines`.

### `Paths`

`Paths` creates `Path` objects: `Path p = Paths.get("data", "file.txt")`.
A `Path` is the modern immutable replacement of `File`: normalization,
resolution, and platform-independent joins. Since Java 11,
`Path.of(...)` is the preferred factory; `Paths.get` remains the older
synonym.

**Practice:** build the same path via `Paths.get` and `Path.of`, print
them, resolve a sibling file with `resolve`, and normalize `../`
segments with `normalize`.

---

## Multithreading

> **Key constructs:** `Thread`, `Runnable`, `start()`, `run()`, `join()`, `synchronized`, `sleep()`

### `Thread`

`Thread` is a platform thread — a unit of execution managed by the OS.
Create it with a `Runnable`, or subclass `Thread` and override `run`
(the first way is cleaner). Each thread has its own stack; the main
code runs in the `main` thread. For mass I/O-bound tasks, also look at
virtual threads (Java 21+).

**Practice:** print `Thread.currentThread().getName()` in `main` and in
a created thread. Create several threads and observe the interleaved
output.

### `Runnable`

`Runnable` is the task interface — `void run()`. A `Thread` takes a
`Runnable` and executes it. Thanks to lambdas, a task is written
inline: `new Thread(() -> doWork())`. The same `Runnable` can run in a
thread-pool executor.

**Practice:** create a thread with a lambda `Runnable` that prints the
numbers 1–5. Run the same task via an `ExecutorService`.

### `start()` vs `run()`

`start()` creates a new thread of execution and invokes `run()` in it;
calling `run()` directly executes the code in the current thread — no
concurrency at all. This is the classic beginner mistake. A thread can
be started only once; a second `start()` throws
`IllegalThreadStateException`.

**Practice:** call `run()` directly and observe the total execution
time, then `start()`. Try calling `start()` twice and read the
exception.

### `join()`

`join()` waits for another thread to finish. The calling thread pauses
until the target completes or the timeout expires. It is the simplest
way to wait for parallel work (in real code, pair it with
`Callable`/`ExecutorService` for collecting results).

**Practice:** start two threads printing lines, then `join()` them in
`main` and print "done" strictly after both finish. Try the version
without `join()` and compare.

### `synchronized`

`synchronized` guarantees mutual exclusion: only one thread at a time
executes the block (or method) for a given monitor. It also creates a
happens-before relationship, making writes visible to other threads.
Overuse causes contention and deadlocks; the `java.util.concurrent`
tools are often a better answer.

**Practice:** create a shared counter incremented by 10 threads × 1000
times without and with `synchronized`. Explain the lost updates.

### `sleep()`

`Thread.sleep(ms)` pauses the current thread, releasing the CPU but not
monitors. It throws the checked `InterruptedException` — catching it
should at least restore the interrupt flag:
`Thread.currentThread().interrupt()`. `sleep` is a crude timing tool;
`ScheduledExecutorService` is the production way.

**Practice:** print a 3-2-1 countdown with `Thread.sleep(1000)` between
numbers. Interrupt the sleeping thread and observe the
`InterruptedException`.

---

## Useful Extras

> **Key constructs:** `enum`, `record`, `var`, `instanceof`, switch expressions, `Optional`, `this::method`

### `enum`

An `enum` is a type with a fixed set of named instances. Constants may
have fields, constructors, and methods; `values()`, `valueOf()`, and
`name()` come built in. Enums implement `Comparable` and work in
`switch` cleanly. Ideal for statuses, categories, and days of the week.

**Practice:** create `enum Day { MON, TUE, ... }` with an `isWeekend`
field. Iterate over `values()` and use the enum in a `switch`.

### `record`

A `record` (Java 16+) is a compact immutable data carrier:
`record Point(int x, int y) {}` — the compiler generates the
constructor, accessors, `equals`, `hashCode`, and `toString`. Records
are final and cannot extend classes. Use them for DTOs and value
objects instead of boilerplate classes.

**Practice:** write a `Student(String name, int age)` record. Create
two equal instances and check `equals`. Compare the amount of code with
a classic class.

### `var`

`var` (Java 10+) infers the local variable type from the initializer:
`var list = new ArrayList<String>();`. It works only for local
variables with an initializer; the type is still static — `var` is not
dynamic typing. It shines with long generic types.

**Practice:** rewrite several declarations with explicit types using
`var`. Try `var x;` and confirm it does not compile.

### `instanceof`

`instanceof` checks the runtime type:
`if (o instanceof Student) { ... }`. Since Java 16, the pattern form
casts and binds in one step: `if (o instanceof Student s) {
s.getName(); }` — no explicit cast needed. Combine it with pattern
`switch` for closed hierarchies.

**Practice:** loop over an `Object[]` and handle only `String` and
`Integer` with `instanceof` pattern matching in one `switch`.

### Switch Expressions

Since Java 14, `switch` is an expression that returns a value: arrow
labels, no fall-through, `yield` for block results. Combined with
sealed types, the compiler checks exhaustiveness — covering every
variant becomes mandatory. Much safer than the old statement form.

**Practice:** rewrite an old `switch` returning a day type into an
expression with `->` and `yield`. Remove one case in a switch over a
sealed hierarchy and observe the exhaustiveness error.

### `Optional`

`Optional<T>` is a container that may be empty — a return type that
makes absence explicit instead of `null`. The toolkit: `map`, `filter`,
`orElse`, `orElseGet`, `orElseThrow`, `ifPresent`. It is designed for
return values, not for fields or parameters.

**Practice:** write a `findByName` method returning
`Optional<Student>`. Consume it with `map` / `orElse` and compare with
a `null`-returning version.

### `this::method`

`this::method` is a method reference to the current object's method —
a lambda shorthand for `x -> this.method(x)`. It appears in callbacks,
event handlers, and stream pipelines. Other reference forms:
`Type::staticMethod`, `instance::method`, and `Type::new`.

**Practice:** pass `this::printResult` as a callback into a method that
runs it on completion, then rewrite it as an explicit lambda.

---

*Start from [Part I](part-i.md) — compilation and running, variables and types, operators, strings, conditionals, loops, arrays, methods, OOP basics, and console input/output.*
