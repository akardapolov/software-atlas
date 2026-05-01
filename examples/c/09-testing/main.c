/* Testing in C
 * ============
 * No built-in framework - use assert() and external tools */

#include <stdio.h>
#include <assert.h>

/* ── Simple assertion ───────────────────────────── */

void test_assert() {
    int a = 5, b = 3;
    assert(a + b == 8);
    printf("test_assert passed\n");
}

/* ── Custom assertion macro ─────────────────────── */

#define ASSERT_EQUAL(expected, actual) \\
    if ((expected) != (actual)) { \\
        printf("FAIL: %s != %s (expected %d, got %d)\\n", \\
               #expected, #actual, (expected), (actual)); \\
    } else { \\
        printf("PASS: %s == %d\\n", #expected, (actual)); \\
    }

void test_custom_assert() {
    ASSERT_EQUAL(10, 5 + 5);
}

/* ── Test fixture pattern ───────────────────────── */

typedef struct {
    int input;
    int expected;
} TestCase;

void run_test_case(TestCase *tc) {
    int result = tc->input * 2;
    ASSERT_EQUAL(tc->expected, result);
}

void test_with_fixtures() {
    printf("\n--- Test with fixtures ---\n");
    TestCase tests[] = {
        {5, 10},
        {3, 6},
        {7, 14},
        {0, 0},
    };
    int n = sizeof(tests) / sizeof(TestCase);
    for (int i = 0; i < n; i++) {
        run_test_case(&tests[i]);
    }
}

/* ── Edge case testing ───────────────────────────── */

int divide(int a, int b) {
    return a / b;  /* BUG: no division by zero check */
}

void test_edge_cases() {
    printf("\n--- Edge case testing ---\n");
    printf("divide(10, 2) = %d\n", divide(10, 2));
    /* divide(10, 0) would crash - should have test for this */
    /* In practice: add validation and tests */
}

/* ── Summary ───────────────────────────────────── */

int main(void) {
    printf("--- Testing in C ---\n");

    test_assert();
    test_custom_assert();
    test_with_fixtures();
    test_edge_cases();

    printf("\n--- Summary ---\n");
    printf("C testing:");
    printf("  - No built-in framework - external tools needed");
    printf("  - assert() macro for basic runtime checks");
    printf("  - Custom macros for domain-specific assertions");
    printf("  - Manual fixture setup/teardown");
    printf("  - Popular frameworks: Check, Unity, Criterion");
    printf("  - Memory tools: Valgrind, AddressSanitizer");
    printf("  - Coverage: GCC gcov, LLVM llvm-cov");

    return 0;
}
