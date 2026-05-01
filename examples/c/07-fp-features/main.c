/* FP Features in C
* ================
* Implementing map, filter, reduce with function pointers

#include <stdio.h>
#include <stdlib.h>

/* ── Function pointer type ───────────────────────────── */

typedef int (*IntUnary)(int);
typedef int (*IntBinary)(int, int);
typedef int (*IntPredicate)(int);

/* ── Map ───────────────────────────────────────────── */

void map_array(int* arr, int size, IntUnary f, int* out) {
    for (int i = 0; i < size; i++) {
        out[i] = f(arr[i]);
    }
}

int square(int x) { return x * x; }
int double(int x) { return x * 2; }
int increment(int x) { return x + 1; }

/* ── Filter ─────────────────────────────────────────── */

int filter_array(int* arr, int size, IntPredicate p, int* out) {
    int count = 0;
    for (int i = 0; i < size; i++) {
        if (p(arr[i])) {
            out[count++] = arr[i];
        }
    }
    return count;
}

int is_even(int x) { return x % 2 == 0; }
int is_positive(int x) { return x > 0; }
int is_odd(int x) { return x % 2 != 0; }

/* ── Reduce (fold) ───────────────────────────────── */

int reduce_array(int* arr, int size, int initial, IntBinary f) {
    int acc = initial;
    for (int i = 0; i < size; i++) {
        acc = f(acc, arr[i]);
    }
    return acc;
}

int add(int a, int b) { return a + b; }
int mul(int a, int b) { return a * b; }

/* ── Composition ───────────────────────────────────── */

IntBinary compose(IntBinary f, IntBinary g) {
    /* Return a function that composes f and g: f(g(x)) */
    /* In C, we use a closure-like struct or just apply sequentially */
    return /* This would need closures - we'll show inline instead */ 0;
}

/* Inline composition demo */
void composition_demo() {
    int arr[] = {1, 2, 3, 4, 5};

    /* square then add 1: (x^2) + 1 */
    int results[5];
    for (int i = 0; i < 5; i++) {
        results[i] = add(square(arr[i]), 1);
    }

    printf("Composed (x^2)+1: ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", results[i]);
    }
    printf("\n");
}

/* ── Currying simulation (partial application) ───────── */

typedef struct {
    int multiplier;
    int (*apply)(int, int);
} CurryClosure;

int curry_apply(CurryClosure* c, int x) {
    return c->apply(c->multiplier, x);
}

CurryClosure* make_multiplier(int multiplier) {
    CurryClosure* c = malloc(sizeof(CurryClosure));
    c->multiplier = multiplier;
    c->apply = mul;
    return c;
}

/* ── Main ───────────────────────────────────────────── */

int main(void) {
    int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int size = sizeof(numbers) / sizeof(int);

    /* ── Map demo ──────────────────────────────── */

    printf("--- Map ---\n");

    int squares[10];
    map_array(numbers, size, square, squares);

    printf("Squares: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", squares[i]);
    }
    printf("\n");

    int doubled[10];
    map_array(numbers, size, double, doubled);

    printf("Doubled: ");
    for (int i = 0; i < size; i++) {
        printf("%d ", doubled[i]);
    }
    printf("\n");


    /* ── Filter demo ────────────────────────────── */

    printf("\n--- Filter ---\n");

    int evens[10];
    int even_count = filter_array(numbers, size, is_even, evens);

    printf("Evens: ");
    for (int i = 0; i < even_count; i++) {
        printf("%d ", evens[i]);
    }
    printf("\n");

    int positives[10];
    int pos_count = filter_array(numbers, size, is_positive, positives);

    printf("Positives: ");
    for (int i = 0; i < pos_count; i++) {
        printf("%d ", positives[i]);
    }
    printf("\n");


    /* ── Reduce demo ──────────────────────────────── */

    printf("\n--- Reduce ---\n");

    int sum = reduce_array(numbers, size, 0, add);
    printf("Sum: %d\n", sum);

    int product = reduce_array(numbers, size, 1, mul);
    printf("Product: %d\n", product);


    /* ── Chained operations ─────────────────────────── */

    printf("\n--- Chained (map + reduce) ---\n");

    /* Sum of squares */
    int squares2[10];
    map_array(numbers, size, square, squares2);
    int sum_squares = reduce_array(squares2, size, 0, add);
    printf("Sum of squares: %d\n", sum_squares);


    /* ── Composition demo ───────────────────────────── */

    printf("\n--- Composition ---\n");

    composition_demo();


    /* ── Currying demo ───────────────────────────── */

    printf("\n--- Currying (partial application) ---\n");

    CurryClosure* times3 = make_multiplier(3);
    CurryClosure* times5 = make_multiplier(5);

    printf("3 * 10 = %d\n", curry_apply(times3, 10));
    printf("5 * 8 = %d\n", curry_apply(times5, 8));

    free(times3);
    free(times5);


    /* ── Summary ───────────────────────────────────── */

    printf("\n--- Summary ---\n");
    printf("C functional features:");
    printf("  - No built-in: must implement manually");
    printf("  - Function pointers: enable higher-order functions");
    printf("  - Manual map/filter/reduce implementations");
    printf("  - Composition: sequential function application");
    printf("  - Currying: simulated with structs + pointers");
    printf("  - C++ lambdas (2011) and beyond");
    printf("  - C has closures only with compiler extensions");

    return 0;
}
