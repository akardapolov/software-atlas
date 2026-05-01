// Error Handling in Java
// ============================
// try-catch-finally, checked exceptions, custom exceptions

import java.io.*;

public class Main {

    // ── Basic exception ─────────────────────────────

    static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return a / b;
    }

    // ── Custom exception ───────────────────────────────

    static class BusinessException extends Exception {
        private int code;

        public BusinessException(int code, String message) {
            super(message);
            this.code = code;
        }

        public int getCode() { return code; }
    }

    // ── Multi-catch ─────────────────────────────────

    static void processInput(String input) throws IOException, NumberFormatException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IOException("Invalid number: " + input, e);
        } catch (Exception e) {
            throw new IOException("Unexpected error: " + input);
        }
    }


    // ── try-catch-finally ─────────────────────────

    public static void demoTryFinally() {
        System.out.println("--- try-catch-finally ---");

        File file = null;
        try {
            file = new File("test.txt");
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        } finally {
            if (file != null) {
                file.delete();
            }
            System.out.println("Finally executed");
        }
    }


    // ── Checked exception example ───────────────────

    static void checkedExceptionExample() throws IOException {
        System.out.println("\n--- Checked exception ---");

        // FileReader throws FileNotFoundException (checked)
        try (FileReader reader = new FileReader("nonexistent.txt")) {
            reader.read();
        } catch (FileNotFoundException e) {
            System.out.println("Caught expected: " + e.getMessage());
        }
    }


    // ── Throw and catch multiple types ─────────────

    public static void throwAndCatch() {
        System.out.println("\n--- Throw and catch ---");

        try {
            throw new NullPointerException("Value is null");
        } catch (NullPointerException e) {
            System.out.println("Caught NPE: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IAE: " + e.getMessage());
        }
    }


    // ── Unchecked runtime exception ───────────────

    public static void uncheckedException() {
        System.out.println("\n--- Unchecked exception ---");

        try {
            int[] arr = new int[5];
            int value = arr[10]; // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
        }
    }


    // ── Custom exception handling ─────────────────────

    public static void customException() throws BusinessException {
        System.out.println("\n--- Custom exception ---");

        try {
            throw new BusinessException(404, "Not found");
        } catch (BusinessException e) {
            System.out.println("Business error: " + e.getMessage() + " (code: " + e.getCode() + ")");
        }
    }


    // ── Try with resources (Java 7+) ───────────

    public static void tryWithResources() {
        System.out.println("\n--- try-with-resources ---");

        // Auto-closing of Closeable resources
        try (FileReader reader = new FileReader("test.txt")) {
            int ch = reader.read();
            System.out.println("Read: " + (char)ch);
        } // reader.close() called automatically
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // ── throws declaration ───────────────────────────

    public static int readFile() throws IOException {
        throw new IOException("File read error");
    }


    public static void main(String[] args) {

        // ── Divide by zero ─────────────────────────

        System.out.println("divide(10, 2) = " + divide(10, 2));


        // ── Try-finally ───────────────────────────────

        demoTryFinally();


        // ── Checked exception ───────────────────────────

        checkedExceptionExample();


        // ── Multiple catch types ───────────────────────

        throwAndCatch();


        // ── Unchecked runtime exception ─────────────

        uncheckedException();


        // ── Custom exception ─────────────────────────────

        try {
            customException();
        } catch (BusinessException e) {
            System.out.println("Handled business error");
        }


        // ── Try-with-resources ─────────────────────────

        tryWithResources();


        // ── throws usage ───────────────────────────────

        System.out.println("\n--- Summary ---");
        System.out.println("Java error handling:");
        System.out.println("  - try-catch-finally: structured error handling");
        System.out.println("  - Checked exceptions: compile-time enforcement");
        System.out.println("  - Unchecked exceptions: RuntimeException");
        System.out.println("  - Custom exceptions: extend Exception/RuntimeException");
        System.out.println("  - Multi-catch: catch (IOException | ...)");
        System.out.println("  - Finally: always executes, exception or not");
        System.out.println("  - Try-with-resources: auto-closing (Java 7+)");
        System.out.println("  - throws: declares exceptions in method signature");
    }
}
