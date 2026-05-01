// Testing in Java
// ===============
// JUnit, assertions, mocks

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class Main {
    // ── Basic assertion ─────────────────────────────

    @Test
    void testAddition() {
        int result = 5 + 3;
        assertEquals(8, result, "5 + 3 should be 8");
    }

    @Test
    void testStringEquals() {
        String actual = "hello";
        assertEquals("hello", actual);
    }

    // ── Exception testing ───────────────────────────

    @Test
    void testDivisionByZero() {
        ArithmeticException ex = assertThrows(
            ArithmeticException.class,
            () -> { int x = 10 / 0; }
        );
        assertEquals("/ by zero", ex.getMessage());
    }

    // ── Parameterized test ───────────────────────────

    @ParameterizedTest
    @CsvSource({"1,1", "2,4", "3,9", "5,25"})
    void testSquares(int input, int expected) {
        assertEquals(expected, input * input);
    }

    // ── Test lifecycle ──────────────────────────────

    private int counter;

    @BeforeEach
    void setup() {
        counter = 0;
        System.out.println("Before each test");
    }

    @Test
    void testCounter() {
        counter++;
        assertEquals(1, counter);
    }

    @AfterEach
    void teardown() {
        counter = 0;
        System.out.println("After each test");
    }

    // ── Assertions overview ───────────────────────────

    @Test
    void testAssertionTypes() {
        // Equality
        assertEquals("text", "text");
        assertNotEquals("text", "other");

        // Null
        assertNull(null);
        assertNotNull("value");

        // Boolean
        assertTrue(true);
        assertFalse(false);

        // Array
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        // Floating point with delta
        assertEquals(0.1, 0.1001, 0.001);
    }

    // ── Summary ─────────────────────────────────────

    @Test
    void testSummary() {
        System.out.println("\n--- Summary ---");
        System.out.println("Java testing:");
        System.out.println("  - JUnit 5: @Test, @BeforeEach, @ParameterizedTest");
        System.out.println("  - Assertions: assertEquals, assertTrue, assertNull");
        System.out.println("  - Exception testing: assertThrows");
        System.out.println("  - Parameterized: @CsvSource, @ValueSource");
        System.out.println("  - Mocking: Mockito for object mocking");
        System.out.println("  - Lifecycle: @BeforeEach, @AfterEach");
        System.out.println("  - Coverage: JaCoCo, Cobertura");
    }
}
