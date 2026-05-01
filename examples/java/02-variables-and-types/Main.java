/**
 * Variables and Types in Java
 * ===========================
 * Java uses static, strong, nominal typing.
 * Primitive types live on stack; reference types on heap.
 */
public class Main {
    public static void main(String[] args) {

        // ── Primitive types ──────────────────────────────

        byte   tiny     = 127;          // 8-bit,  [-128, 127]
        short  small    = 32_767;       // 16-bit
        int    count    = 42;           // 32-bit
        long   large    = 9_000_000_000_000_000L;  // 64-bit
        float  approx   = 3.14f;       // 32-bit IEEE 754
        double pi       = 3.14159;     // 64-bit IEEE 754
        boolean active  = true;
        char   letter   = 'A';         // 16-bit Unicode (UTF-16)

        System.out.println("count = " + count + " (int)");
        System.out.println("pi = " + pi + " (double)");
        System.out.println("active = " + active + " (boolean)");
        System.out.println("letter = " + letter + " (char, value " + (int) letter + ")");

        // ── Widening vs narrowing ────────────────────────

        System.out.println("\n--- Widening vs narrowing ---");

        // Widening: smaller → larger (implicit, safe)
        int    a = 42;
        double b = a;   // int → double, automatic
        System.out.println("int " + a + " → double " + b + " (widening, implicit)");

        // Narrowing: larger → smaller (requires cast, may lose data)
        double d = 3.99;
        int    i = (int) d;  // truncates to 3!
        System.out.println("double " + d + " → int " + i + " (narrowing, explicit cast)");

        // String to number — explicit parsing required
        int parsed = Integer.parseInt("42");
        System.out.println("Integer.parseInt(\"42\") = " + parsed);

        // "3" + 4 → "34" (string concatenation, not addition!)
        String surprise = "3" + 4;
        System.out.println("\"3\" + 4 = \"" + surprise + "\" (concatenation!)");

        // ── Reference types ──────────────────────────────

        System.out.println("\n--- Reference types ---");

        String name = "Software Atlas";
        System.out.println("name = " + name);
        System.out.println("name.length() = " + name.length());
        System.out.println("name.charAt(0) = " + name.charAt(0));

        // Strings are immutable
        String upper = name.toUpperCase();  // returns NEW string
        System.out.println("name = " + name + " (unchanged)");
        System.out.println("upper = " + upper);

        // ── Autoboxing (primitive ↔ wrapper) ─────────────

        System.out.println("\n--- Autoboxing ---");

        Integer boxed = count;        // int → Integer (autoboxing)
        int unboxed = boxed;          // Integer → int (auto-unboxing)
        System.out.println("boxed = " + boxed + " (Integer)");
        System.out.println("unboxed = " + unboxed + " (int)");

        // Careful: Integer can be null, int cannot!
        Integer nullable = null;
        // int crash = nullable;  // NullPointerException at runtime!

        // ── var (Java 10+) ───────────────────────────────

        System.out.println("\n--- var (local type inference) ---");

        var message = "Hello, Atlas!";  // inferred as String
        var number = 42;                // inferred as int
        var list = java.util.List.of("a", "b", "c");  // inferred as List<String>

        System.out.println("message: " + message + " (" + message.getClass().getSimpleName() + ")");
        System.out.println("list: " + list + " (" + list.getClass().getSimpleName() + ")");

        // var cannot be used for fields, parameters, or return types

        // ── Arrays ───────────────────────────────────────

        System.out.println("\n--- Arrays ---");

        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("arr.length = " + arr.length);
        System.out.println("arr[0] = " + arr[0]);

        // Bounds checking! (unlike C)
        try {
            int oob = arr[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("arr[10] → " + e.getClass().getSimpleName());
        }

        // ── Collections ──────────────────────────────────

        System.out.println("\n--- Collections ---");

        // List (mutable)
        var fruits = new java.util.ArrayList<>(java.util.List.of("apple", "banana"));
        fruits.add("cherry");
        System.out.println("fruits = " + fruits);

        // Map
        var ages = java.util.Map.of("Ada", 36, "Grace", 85);
        System.out.println("ages = " + ages);

        // Set
        var tags = java.util.Set.of("java", "types", "static");
        System.out.println("tags = " + tags);

        // ── Records (Java 16+) ───────────────────────────

        System.out.println("\n--- Records (Java 16+) ---");

        record Point(double x, double y) {
            double distanceFromOrigin() {
                return Math.sqrt(x * x + y * y);
            }
        }

        var p = new Point(3.0, 4.0);
        System.out.println("point = " + p);
        System.out.println("distance = " + p.distanceFromOrigin());
        System.out.println("x = " + p.x() + ", y = " + p.y());

        // Records are immutable!
        // p.x = 5.0;  // COMPILE ERROR

        // ── Null — billion-dollar mistake ────────────

        System.out.println("\n--- Null ---");

        String s = null;
        System.out.println("s = " + s);  // prints "null"
        try {
            int len = s.length();  // NullPointerException!
        } catch (NullPointerException e) {
            System.out.println("s.length() → NullPointerException");
        }

        // Optional (Java 8+) — explicit "maybe a value"
        var opt = java.util.Optional.ofNullable(s);
        String result = opt.orElse("(default)");
        System.out.println("Optional: " + result);

        // ── Nominal typing ───────────────────────────────

        System.out.println("\n--- Nominal typing ---");

        // Java uses nominal typing: types must explicitly declare relationships
        // Even if two classes have identical methods, they're not interchangeable

        interface Quacker {
            String quack();
        }

        class Duck implements Quacker {
            public String quack() { return "Quack!"; }
        }

        class Person implements Quacker {
            public String quack() { return "I'm quacking!"; }
        }

        // Both must explicitly "implements Quacker"
        Quacker q = new Duck();
        System.out.println("Duck: " + q.quack());
        q = new Person();
        System.out.println("Person: " + q.quack());

        // ── Summary ──────────────────────────────────────

        System.out.println("\n--- Summary ---");
        System.out.println("Java: static + strong + nominal typing");
        System.out.println("  - Types checked at compile time (static)");
        System.out.println("  - No implicit narrowing (strong)");
        System.out.println("  - Must declare 'implements' (nominal)");
        System.out.println("  - Primitive types (stack) + reference types (heap)");
        System.out.println("  - Null exists (billion-dollar mistake)");
        System.out.println("  - Records for immutable data (16+)");
        System.out.println("  - var for local type inference (10+)");
    }
}
