# Vector API

> **Project:** Panama
> **Java version:** 25 (incubator → preview)
> **Status:** Preview

The Vector API enables expressing data-parallel computations that compile to SIMD (Single Instruction, Multiple Data) hardware instructions. It provides a platform-agnostic way to write vectorized code in Java, achieving performance comparable to hand-optimized C with intrinsics.

---

## History and Evolution

### The SIMD Gap

Modern CPUs provide vector registers that can operate on multiple data elements simultaneously:

| Architecture | Vector Register | Width | Operations |
|---|---|---|---|
| x86 SSE | XMM | 128 bits | 4 floats, 2 doubles |
| x86 AVX2 | YMM | 256 bits | 8 floats, 4 doubles |
| x86 AVX-512 | ZMM | 512 bits | 16 floats, 8 doubles |
| ARM NEON | 128-bit vector | 128 bits | 4 floats, 2 doubles |
| ARM SVE | Scalable vector | 128–2048 bits | Variable width |

Before the Vector API, Java developers had no portable way to access these instructions. The options were:

1. **Rely on the JIT compiler** — HotSpot auto-vectorizes simple loops, but conservatively
2. **Use JNI + C intrinsics** — Maximum performance, maximum complexity
3. **Use libraries** — Apache Commons Math, etc., may or may not use SIMD internally

### The Vector API Journey

| Milestone | Release | Status |
|---|---|---|
| First incubator | Java 16 | Basic vector operations, `VectorSpecies` |
| Second incubator | Java 17 | `VectorShuffle`, `VectorMask` refinements |
| Third incubator | Java 18 | Performance improvements |
| Fourth incubator | Java 19 | `Float16` vector support |
| Fifth incubator | Java 20 | ARM SVE support, new operations |
| Sixth incubator | Java 21 | Continued refinements |
| Seventh incubator | Java 22 | API stabilization |
| Eighth incubator | Java 23 | Further refinements |
| Preview | Java 24 | Promoted from incubator |
| Preview (consolidated) | Java 25 | Awaiting Valhalla value types for finalization |

The API has remained in incubator/preview for so long because its performance depends on **Valhalla's value types**. Currently, `Vector` objects are heap-allocated reference types, which limits the JIT's ability to fully optimize them. With Valhalla, vectors will become inline value types with zero allocation overhead.

## Implementation Deep Dive

### Vector Species

A `VectorSpecies` defines the shape of a vector: element type and vector size:

```java
VectorSpecies<Float> species = FloatVector.SPECIES_256; // 256-bit vector of floats
```

Common species:

| Species | Element Type | Elements (bits) | Hardware Mapping |
|---|---|---|---|
| `ByteVector.SPECIES_128` | `byte` | 16 | SSE / NEON |
| `IntVector.SPECIES_256` | `int` | 8 | AVX2 |
| `DoubleVector.SPECIES_512` | `double` | 8 | AVX-512 |
| `FloatVector.SPECIES_PREFERRED` | `float` | Best for host CPU | Auto-detect |

`SPECIES_PREFERRED` selects the widest vector supported by the current CPU, making code portable across architectures.

### Basic Vector Operations

```java
VectorSpecies<Float> species = FloatVector.SPECIES_256;

float[] a = {1, 2, 3, 4, 5, 6, 7, 8};
float[] b = {8, 7, 6, 5, 4, 3, 2, 1};
float[] c = new float[8];

// Load vectors from arrays
FloatVector va = FloatVector.fromArray(species, a, 0);
FloatVector vb = FloatVector.fromArray(species, b, 0);

// Element-wise addition (SIMD: one instruction, 8 additions)
FloatVector vc = va.add(vb);

// Store result back to array
vc.intoArray(c, 0);
// c = [9, 9, 9, 9, 9, 9, 9, 9]
```

The `.add()` operation compiles to a single `vaddps` (AVX) or `fadd` (NEON) instruction.

### Masked Operations

Not all data fits neatly into vector widths. The Vector API provides **masks** for partial vectors:

```java
float[] data = new float[1000];
float[] result = new float[1000];
VectorSpecies<Float> species = FloatVector.SPECIES_PREFERRED;

int i = 0;
for (; i <= data.length - species.length(); i += species.length()) {
    FloatVector v = FloatVector.fromArray(species, data, i);
    v.mul(2.0f).intoArray(result, i); // full vector
}

// Handle tail elements (< vector width)
if (i < data.length) {
    VectorMask<Float> mask = species.indexInRange(i, data.length - i);
    FloatVector v = FloatVector.fromArray(species, data, i, mask);
    v.mul(2.0f).intoArray(result, i, mask);
}
```

### Comparison: Scalar vs Vector

```java
// Scalar loop — compiled to individual add instructions
for (int i = 0; i < size; i++) {
    c[i] = a[i] + b[i];
}

// Vector loop — compiled to SIMD add instructions
VectorSpecies<Float> species = FloatVector.SPECIES_PREFERRED;
for (int i = 0; i < size; i += species.length()) {
    FloatVector va = FloatVector.fromArray(species, a, i);
    FloatVector vb = FloatVector.fromArray(species, b, i);
    va.add(vb).intoArray(c, i);
}
```

Performance gains vary by workload:
- **Simple arithmetic**: 4–16× speedup (matches vector width)
- **Memory-bound**: Limited by memory bandwidth; still 2–4×
- **Complex control flow**: SIMD works best on straight-line data parallelism

## When to Use

| Scenario | Recommendation |
|---|---|
| Numerical computing (matrix ops, FFT) | ✅ Ideal use case |
| Image/audio processing | ✅ Excellent fit |
| Simple element-wise array operations | ✅ Good, but JIT may auto-vectorize |
| Heavy branching or irregular memory access | ❌ Poor fit |
| Small arrays (< 100 elements) | ❌ Overhead outweighs benefit |

## Future: Valhalla Integration

The Vector API's finalization is blocked on **Project Valhalla** because:

- **Current**: `Vector` is a reference type → heap allocation, GC pressure
- **With Valhalla**: `Vector` becomes a value type → inline in registers/stack, zero allocation

Once Valhalla lands, the Vector API will be promoted from preview to a finalized feature with significantly better performance characteristics.

## See Also

- [Panama Project Overview](index.md)
- [FFM API](01-ffm-api.md) — Panama's other deliverable
- [Valhalla Project Overview](../valhalla/index.md) — blocking dependency for Vector API finalization
