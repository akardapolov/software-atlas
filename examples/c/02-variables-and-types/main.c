/*
 * Variables and Types in C
 * ========================
 * C uses static, weak typing.
 * Every variable must be declared with its type.
 * Implicit conversions happen freely — be careful!
 */

#include <stdio.h>
#include <string.h>
#include <limits.h>
#include <float.h>
#include <stdbool.h>  /* C99: bool, true, false */
#include <stdint.h>   /* C99: int8_t, int32_t, etc. */

int main(void) {
    /* ── Basic types ───────────────────────────────────── */

    int count = 42;
    printf("count = %d, size = %zu bytes\n", count, sizeof(count));

    double pi = 3.14159;
    printf("pi = %f, size = %zu bytes\n", pi, sizeof(pi));

    /* C99 bool (before C99, int 0/1 was used) */
    bool active = true;
    printf("active = %d, size = %zu bytes\n", active, sizeof(active));

    char letter = 'A';
    printf("letter = %c (ASCII %d), size = %zu bytes\n",
           letter, letter, sizeof(letter));

    /* ── Integer sizes ─────────────────────────────────── */

    printf("\n--- Integer sizes (platform-dependent!) ---\n");
    printf("char:      %zu byte,  range [%d, %d]\n",
           sizeof(char), CHAR_MIN, CHAR_MAX);
    printf("short:     %zu bytes, range [%d, %d]\n",
           sizeof(short), SHRT_MIN, SHRT_MAX);
    printf("int:       %zu bytes, range [%d, %d]\n",
           sizeof(int), INT_MIN, INT_MAX);
    printf("long:      %zu bytes, range [%ld, %ld]\n",
           sizeof(long), LONG_MIN, LONG_MAX);
    printf("long long: %zu bytes, range [%lld, %lld]\n",
           sizeof(long long), LLONG_MIN, LLONG_MAX);

    /* Fixed-width integers (C99) — use these for portability */
    printf("\n--- Fixed-width integers (C99) ---\n");
    int8_t  tiny   = 127;
    int32_t medium = 2000000000;
    int64_t large  = 9000000000000000000LL;
    printf("int8_t:  %d\n", tiny);
    printf("int32_t: %d\n", medium);
    printf("int64_t: %lld\n", (long long)large);

    /* ── Weak typing: implicit conversions ─────────────── */

    printf("\n--- Weak typing (implicit conversions) ---\n");

    /* int → double (safe, automatic) */
    int    x = 42;
    double y = x;   /* implicit conversion */
    printf("int %d → double %f (implicit)\n", x, y);

    /* double → int (truncates! no warning by default) */
    double d = 3.99;
    int    i = d;   /* truncates to 3 */
    printf("double %f → int %d (truncated!)\n", d, i);

    /* char is just a small integer */
    char c = 'A';
    int  n = c + 1;  /* 'A' is 65, so n = 66 */
    printf("'%c' + 1 = %d = '%c'\n", c, n, n);

    /* Comparing signed and unsigned — a classic trap */
    int          signed_val   = -1;
    unsigned int unsigned_val = 1;
    if (signed_val < unsigned_val) {
        printf("-1 < 1 → true (expected)\n");
    } else {
        /* This branch executes! -1 is converted to a large unsigned */
        printf("-1 < 1 → false (SURPRISE! -1 becomes %u as unsigned)\n",
               (unsigned int)signed_val);
    }

    /* ── Strings (null-terminated char arrays) ─────────── */

    printf("\n--- Strings ---\n");

    /* String literal — stored in read-only memory */
    const char *greeting = "Hello, World!";
    printf("greeting = \"%s\"\n", greeting);
    printf("strlen = %zu, sizeof(ptr) = %zu\n",
           strlen(greeting), sizeof(greeting)); /* strlen=13, sizeof=8 (pointer!) */

    /* Mutable string — char array */
    char buffer[64] = "Hello";
    strcat(buffer, ", Atlas!");  /* concatenation modifies buffer */
    printf("buffer = \"%s\"\n", buffer);

    /* No bounds checking! This is a buffer overflow waiting to happen: */
    /* char small[4]; strcpy(small, "This is way too long"); */
    /* ← undefined behaviour, possible crash or security vulnerability */

    /* ── Arrays ────────────────────────────────────────── */

    printf("\n--- Arrays ---\n");

    int arr[5] = {10, 20, 30, 40, 50};
    printf("arr = {");
    for (int idx = 0; idx < 5; idx++) {
        printf("%s%d", idx ? ", " : "", arr[idx]);
    }
    printf("}\n");

    /* Array size is NOT carried with the array */
    printf("sizeof(arr) = %zu bytes (%zu ints)\n",
           sizeof(arr), sizeof(arr) / sizeof(arr[0]));

    /* No bounds checking! arr[10] compiles but is undefined behaviour */

    /* ── Pointers ──────────────────────────────────────── */

    printf("\n--- Pointers ---\n");

    int value = 42;
    int *ptr = &value;  /* ptr holds ADDRESS of value */

    printf("value = %d\n", value);
    printf("&value = %p\n", (void *)&value);
    printf("ptr = %p\n", (void *)ptr);
    printf("*ptr = %d\n", *ptr);   /* dereference: get value at address */

    *ptr = 100;  /* modify value through pointer */
    printf("after *ptr = 100: value = %d\n", value);

    /* Pointer arithmetic (unlike Go and Rust, C allows this) */
    int nums[] = {10, 20, 30};
    int *p = nums;
    printf("\nnums[0] = %d, *(p+1) = %d, *(p+2) = %d\n",
           *p, *(p + 1), *(p + 2));

    /* NULL pointer */
    int *null_ptr = NULL;
    printf("null_ptr = %p\n", (void *)null_ptr);
    /* *null_ptr = 42; ← segmentation fault! */

    /* ── Structs ───────────────────────────────────────── */

    printf("\n--- Structs ---\n");

    struct Point {
        double x;
        double y;
    };

    struct Point pt = {3.0, 4.0};
    printf("point = (%f, %f)\n", pt.x, pt.y);

    /* ── Enums ─────────────────────────────────────────── */

    printf("\n--- Enums ---\n");

    enum Color { RED, GREEN, BLUE };
    enum Color c2 = GREEN;
    printf("GREEN = %d\n", c2);  /* Enums are just ints! */

    /* This compiles fine — enums provide no type safety */
    int color_as_int = RED + 42;
    printf("RED + 42 = %d (no type safety!)\n", color_as_int);

    /* ── Typedef ───────────────────────────────────────── */

    printf("\n--- Typedef ---\n");

    typedef unsigned long size_type;
    size_type sz = 1024;
    printf("sz = %lu\n", sz);

    /* ── Summary ───────────────────────────────────────── */

    printf("\n--- Summary ---\n");
    printf("C: static + weak typing\n");
    printf("  - Types declared explicitly (static)\n");
    printf("  - Implicit conversions everywhere (weak)\n");
    printf("  - No strings — just char arrays\n");
    printf("  - No bounds checking on arrays\n");
    printf("  - Pointer arithmetic allowed\n");
    printf("  - Platform-dependent sizes (use stdint.h)\n");
    printf("  - Manual memory management\n");

    return 0;
}
