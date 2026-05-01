/*
 * Functions in C
 * ==============
 * C has functions but NOT first-class: you cannot create closures
 * or anonymous functions. Function pointers provide limited
 * higher-order programming.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* ── Basic function ────────────────────────────────── */

int add(int a, int b) {
    return a + b;
}

/* Forward declaration (prototype) */
void print_array(const int *arr, int len);

/* ── Multiple "return values" via pointers ─────────── */

void min_max(const int *arr, int len, int *out_min, int *out_max) {
    *out_min = arr[0];
    *out_max = arr[0];
    for (int i = 1; i < len; i++) {
        if (arr[i] < *out_min) *out_min = arr[i];
        if (arr[i] > *out_max) *out_max = arr[i];
    }
}

/* ── Function pointers (C's higher-order functions) ── */

/* A function that takes a function pointer as argument */
void apply(int *arr, int len, int (*f)(int)) {
    for (int i = 0; i < len; i++) {
        arr[i] = f(arr[i]);
    }
}

int square(int x) { return x * x; }
int negate(int x) { return -x; }

/* Filter: returns count of elements satisfying predicate */
int filter(const int *src, int len, int *dest, int (*pred)(int)) {
    int count = 0;
    for (int i = 0; i < len; i++) {
        if (pred(src[i])) {
            dest[count++] = src[i];
        }
    }
    return count;
}

int is_even(int x) { return x % 2 == 0; }
int is_positive(int x) { return x > 0; }

/* Reduce (fold) */
int reduce(const int *arr, int len, int initial, int (*f)(int, int)) {
    int acc = initial;
    for (int i = 0; i < len; i++) {
        acc = f(acc, arr[i]);
    }
    return acc;
}

int add_two(int a, int b) { return a + b; }
int mul_two(int a, int b) { return a * b; }

/* ── qsort comparator (stdlib higher-order function) ─ */

int compare_int_asc(const void *a, const void *b) {
    return *(const int *)a - *(const int *)b;
}

int compare_int_desc(const void *a, const void *b) {
    return *(const int *)b - *(const int *)a;
}

int compare_strlen(const void *a, const void *b) {
    const char *sa = *(const char **)a;
    const char *sb = *(const char **)b;
    return (int)strlen(sa) - (int)strlen(sb);
}

/* ── Simulating closures with structs ─────────────── */

typedef struct {
    int factor;
    int (*call)(struct Multiplier *, int);
} Multiplier;

/* Forward declaration needed due to self-reference */
typedef struct {
    int factor;
} MultiplierData;

int multiplier_call(MultiplierData *self, int x) {
    return x * self->factor;
}

/* ── Recursive functions ──────────────────────────── */

int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}

int fibonacci(int n) {
    if (n < 2) return n;
    return fibonacci(n - 1) + fibonacci(n - 2);
}

/* ── Static variables (persistent state) ──────────── */

int counter(void) {
    static int count = 0;  /* persists across calls */
    return ++count;
}

/* ── Variadic functions (simplified) ──────────────── */

#include <stdarg.h>

int sum_variadic(int count, ...) {
    va_list args;
    va_start(args, count);
    int total = 0;
    for (int i = 0; i < count; i++) {
        total += va_arg(args, int);
    }
    va_end(args);
    return total;
}

/* ── Helper ───────────────────────────────────────── */

void print_array(const int *arr, int len) {
    printf("[");
    for (int i = 0; i < len; i++) {
        printf("%s%d", i ? ", " : "", arr[i]);
    }
    printf("]");
}

/* ── Main ─────────────────────────────────────────── */

int main(void) {
    /* Basic function */
    printf("add(3, 4) = %d\n", add(3, 4));

    /* Multiple return via pointers */
    printf("\n--- Multiple return values (via pointers) ---\n");
    int nums[] = {3, 1, 4, 1, 5, 9, 2, 6};
    int len = sizeof(nums) / sizeof(nums[0]);
    int mn, mx;
    min_max(nums, len, &mn, &mx);
    printf("min = %d, max = %d\n", mn, mx);

    /* Function pointers */
    printf("\n--- Function pointers ---\n");

    int data[] = {1, 2, 3, 4, 5};
    int data_len = 5;

    /* Assign function pointer */
    int (*op)(int) = square;
    printf("op(5) = %d (square)\n", op(5));
    op = negate;
    printf("op(5) = %d (negate)\n", op(5));

    /* apply (map) */
    int mapped[] = {1, 2, 3, 4, 5};
    apply(mapped, 5, square);
    printf("apply(square) = ");
    print_array(mapped, 5);
    printf("\n");

    /* filter */
    int filtered[10];
    int fcount = filter(data, data_len, filtered, is_even);
    printf("filter(is_even) = ");
    print_array(filtered, fcount);
    printf("\n");

    /* reduce */
    int total = reduce(data, data_len, 0, add_two);
    printf("reduce(+, 0) = %d\n", total);

    int product = reduce(data, data_len, 1, mul_two);
    printf("reduce(*, 1) = %d\n", product);

    /* qsort — stdlib higher-order function */
    printf("\n--- qsort (stdlib HOF) ---\n");

    int sortable[] = {5, 2, 8, 1, 9, 3};
    int sort_len = 6;
    qsort(sortable, sort_len, sizeof(int), compare_int_asc);
    printf("sorted asc = ");
    print_array(sortable, sort_len);
    printf("\n");

    qsort(sortable, sort_len, sizeof(int), compare_int_desc);
    printf("sorted desc = ");
    print_array(sortable, sort_len);
    printf("\n");

    const char *words[] = {"banana", "apple", "cherry", "date"};
    qsort(words, 4, sizeof(char *), compare_strlen);
    printf("sorted by length = [");
    for (int i = 0; i < 4; i++) {
        printf("%s\"%s\"", i ? ", " : "", words[i]);
    }
    printf("]\n");

    /* Simulating closures */
    printf("\n--- Simulating closures ---\n");
    MultiplierData doubler = {.factor = 2};
    MultiplierData tripler = {.factor = 3};
    printf("doubler(5) = %d\n", multiplier_call(&doubler, 5));
    printf("tripler(5) = %d\n", multiplier_call(&tripler, 5));
    printf("(C has no real closures — we simulate with structs)\n");

    /* Recursion */
    printf("\n--- Recursion ---\n");
    printf("factorial(10) = %d\n", factorial(10));
    printf("fibonacci(10) = %d\n", fibonacci(10));

    /* Static variables */
    printf("\n--- Static variables (persistent state) ---\n");
    printf("counter() = %d\n", counter());
    printf("counter() = %d\n", counter());
    printf("counter() = %d\n", counter());

    /* Variadic */
    printf("\n--- Variadic ---\n");
    printf("sum(1,2,3) = %d\n", sum_variadic(3, 1, 2, 3));
    printf("sum(10,20,30,40) = %d\n", sum_variadic(4, 10, 20, 30, 40));

    /* Summary */
    printf("\n--- Summary ---\n");
    printf("C functions:\n");
    printf("  - NOT first-class (no closures, no lambdas)\n");
    printf("  - Function pointers for higher-order programming\n");
    printf("  - Multiple returns via pointer parameters\n");
    printf("  - Variadic with <stdarg.h>\n");
    printf("  - qsort/bsearch: stdlib HOF examples\n");
    printf("  - Simulate closures with structs (manual)\n");
    printf("  - Static variables for persistent state\n");

    return 0;
}
