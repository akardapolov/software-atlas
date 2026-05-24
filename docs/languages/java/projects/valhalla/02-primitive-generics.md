# Primitive / Specialized Generics

> **Project:** Valhalla
> **Java version:** TBD
> **Status:** In progress

Specialized generics extend Java's type system to support primitive type arguments directly, eliminating the overhead of boxing and enabling flat, efficient data structures.

---

## History and Evolution

### The Boxing Problem

Java generics (Java 5) use **type erasure** for compatibility with pre-Java 5 code. This means generic type parameters are replaced with `Object` (or their first bound) at runtime:

```java
// Source code
List<Integer> list = new ArrayList<>();
list.add(42);           // autoboxing: int -> Integer
int value = list.get(0); // auto-unboxing: Integer -> int

// Compiled code (conceptual)
List list = new ArrayList();
list.add(Integer.valueOf(42));  // boxing allocation
int value = ((Integer) list.get(0)).intValue(); // cast + unboxing
```

Every primitive in a generic collection:
1. **Boxes** to a wrapper object (`Integer`, `Long`, etc.) on insertion
2. **Allocates** heap memory for the wrapper
3. **Unboxes** on retrieval
4. **Creates GC pressure** from short-lived wrapper objects

### The Performance Cost

For numerical workloads, boxing is devastating:

| Operation | `int[]` (primitive) | `List<Integer>` (boxed) | Slowdown |
|---|---|---|---|
| Sequential sum | 1× | 3–5× | Object overhead + cache misses |
| Random access | 1× | 5–10× | Pointer chasing |
| Sorting | 1× | 2–4× | Comparators + boxing |
| Stream reduce | 1× | 5–20× | `IntStream` avoids this, but APIs diverge |

Specialized stream APIs (`IntStream`, `LongStream`, `DoubleStream`) were added in Java 8 as a workaround, but they fragment the API and don't work for custom value types.

### C++ Templates as a Comparison

C++ templates generate specialized code for each type argument:

```cpp
template<typename T>
class List { T* data; /* specialized per T */ };

List<int> intList;      // generates List<int> with inline int storage
List<double> doubleList; // generates List<double> with inline double storage
```

This gives C++ both type safety and zero-cost abstractions. Java's type erasure trades performance for compatibility.

## Implementation Deep Dive

### The Valhalla Approach

Valhalla introduces **specialized generics** that combine the best of both worlds:

```java
// With Valhalla: List<int> is legal and efficient
List<int> intList = new ArrayList<>();
intList.add(42);           // no boxing!
int value = intList.get(0); // no unboxing!
```

The JVM generates specialized bytecode for each primitive specialization:

| Generic Type | Specialization | Storage |
|---|---|---|
| `List<int>` | `List${int}` | Flat `int[]` array |
| `List<double>` | `List${double}` | Flat `double[]` array |
| `List<Point>` | `List${Point}` | Flat inline `Point` array |

### The Migration Challenge

The key challenge: Java has 25+ years of bytecode in the wild. Valhalla must not break existing code:

1. **Existing `List<Integer>`** — Still works via boxing backward compatibility
2. **New `List<int>`** — Uses specialized, unboxed implementation
3. **Reference generics** (`List<String>`) — Unchanged

The JVM uses **template specialization at the class file level**:
- A generic class is compiled once with type variables
- The JVM creates specialized variants on first use with primitive/value types
- Shared code (not type-dependent) is reused across specializations

### Value Classes in Generics

Specialized generics also work with value classes:

```java
value class Point { int x, y; }

List<Point> points = new ArrayList<>();
// Points stored inline in array: no object headers, no references
```

This is the full realization of Valhalla's vision: generic collections that are both type-safe and cache-efficient for any type.

### The `any` Type Variable

Early Valhalla designs used `any T` to indicate "any type, primitive or reference":

```java
// Early design (may change)
class Box<any T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T get() { return value; }
}

Box<int> intBox = new Box<>(42);    // specialized, no boxing
Box<String> strBox = new Box<>("hi"); // reference type, unchanged
```

The exact syntax is still under discussion. The key point is that the same generic class works for both primitives and references.

## Timeline and Blockers

Valhalla is the longest-running OpenJDK project because it touches every layer:

| Layer | Challenge |
|---|---|
| Language | New syntax for primitive generics |
| Bytecode | New class file format for specialization |
| JVM | Template instantiation, specialization caching |
| GC | Objects without identity, inline in arrays |
| JIT | Optimizing specialized code paths |
| Reflection | Representing `List<int>` at runtime |
| JNI | Calling conventions for value types |

The JVM team is taking an incremental approach:
1. **Value classes first** — Simpler, fewer moving parts
2. **Primitive classes** — Unifying primitives with the object model
3. **Specialized generics** — The final piece, building on the above

## See Also

- [Generics — main feature page](../../01-generics.md) — current type erasure model
- [Value Classes](01-value-classes.md) — Valhalla's other pillar
- [Records — main feature page](../../05-records.md) — immutable data carriers today
- [Collections Framework — main feature page](../../14-collections.md)
