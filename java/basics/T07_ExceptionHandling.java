package basics;

/**
 * Topic 7: Exception Handling
 *
 * Covers try-catch-finally, exception hierarchy, checked vs unchecked,
 * custom exceptions, multi-catch, and try-with-resources.
 */
public class T07_ExceptionHandling {

    // Custom checked exception
    static class InsufficientFundsException extends Exception {
        private double amount;

        InsufficientFundsException(double amount) {
            super("Insufficient funds. Short by $" + amount);
            this.amount = amount;
        }

        double getShortfall() { return amount; }
    }

    // Custom unchecked exception
    static class InvalidAgeException extends RuntimeException {
        InvalidAgeException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {

        System.out.println("=== 1. EXCEPTION HIERARCHY ===\n");
        System.out.println("Throwable");
        System.out.println("  ├── Error (don't catch: OutOfMemoryError, StackOverflowError)");
        System.out.println("  └── Exception");
        System.out.println("        ├── RuntimeException (unchecked)");
        System.out.println("        │     ├── NullPointerException");
        System.out.println("        │     ├── ArrayIndexOutOfBoundsException");
        System.out.println("        │     ├── ArithmeticException");
        System.out.println("        │     ├── ClassCastException");
        System.out.println("        │     └── NumberFormatException");
        System.out.println("        └── Checked Exceptions (must handle or declare)");
        System.out.println("              ├── IOException");
        System.out.println("              ├── SQLException");
        System.out.println("              └── FileNotFoundException");

        System.out.println("\n=== 2. TRY-CATCH-FINALLY ===\n");

        try {
            int result = 10 / 0; // ArithmeticException
            System.out.println("This won't print");
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally ALWAYS runs (even after catch)");
        }

        System.out.println("\n=== 3. MULTIPLE CATCH BLOCKS ===\n");

        // Order matters: catch specific exceptions before general ones
        try {
            String s = null;
            s.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException");
        } catch (RuntimeException e) {
            System.out.println("Caught RuntimeException"); // Less specific
        } catch (Exception e) {
            System.out.println("Caught Exception"); // Most general
        }

        // Multi-catch (Java 7+): catch multiple unrelated exceptions
        try {
            String text = "abc";
            int num = Integer.parseInt(text); // NumberFormatException
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Multi-catch: " + e.getClass().getSimpleName());
        }

        System.out.println("\n=== 4. CHECKED vs UNCHECKED ===\n");

        // Unchecked (RuntimeException): compiler doesn't force handling
        // You CAN catch them, but don't have to
        String nullStr = null;
        try {
            nullStr.length();
        } catch (NullPointerException e) {
            System.out.println("Unchecked: NullPointerException (optional to catch)");
        }

        // Checked: compiler FORCES you to handle or declare (throws)
        try {
            riskyMethod(); // Must handle or declare throws
        } catch (InsufficientFundsException e) {
            System.out.println("Checked: " + e.getMessage());
            System.out.println("  Shortfall: $" + e.getShortfall());
        }

        System.out.println("\n=== 5. THROW & THROWS ===\n");

        // throw: actually throws an exception
        // throws: declares that a method CAN throw an exception
        try {
            validateAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        }

        System.out.println("\n=== 6. FINALLY BEHAVIOR ===\n");

        // Finally runs even with return!
        System.out.println("methodWithFinally() returns: " + methodWithFinally());

        // Finally runs even with uncaught exception (before propagating)
        try {
            methodThatThrows();
        } catch (Exception e) {
            System.out.println("Caught after finally ran");
        }

        System.out.println("\n=== 7. COMMON EXCEPTIONS TO KNOW ===\n");

        // NullPointerException
        demonstrateException("NullPointer", () -> {
            String s = null;
            s.length();
        });

        // ArrayIndexOutOfBoundsException
        demonstrateException("ArrayIndexOutOfBounds", () -> {
            int[] arr = {1, 2, 3};
            int x = arr[5];
        });

        // ClassCastException
        demonstrateException("ClassCast", () -> {
            Object obj = "Hello";
            Integer num = (Integer) obj;
        });

        // NumberFormatException
        demonstrateException("NumberFormat", () -> {
            int x = Integer.parseInt("abc");
        });

        // StackOverflowError
        demonstrateException("StackOverflow", () -> {
            infiniteRecursion();
        });

        System.out.println("\n=== 8. BEST PRACTICES ===\n");
        System.out.println("1. Catch specific exceptions, not generic Exception");
        System.out.println("2. Don't use exceptions for flow control");
        System.out.println("3. Always clean up resources (use try-with-resources)");
        System.out.println("4. Don't swallow exceptions (empty catch blocks)");
        System.out.println("5. Log exceptions with context");
        System.out.println("6. Prefer unchecked exceptions for programming errors");
        System.out.println("7. Use checked exceptions for recoverable conditions");
    }

    // throws declares a checked exception
    static void riskyMethod() throws InsufficientFundsException {
        throw new InsufficientFundsException(150.75);
    }

    // throw throws an unchecked exception (no throws needed)
    static void validateAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        }
    }

    static String methodWithFinally() {
        try {
            return "from try";
        } finally {
            System.out.println("  Finally runs before return!");
            // If finally has a return, it overrides try's return (bad practice!)
        }
    }

    static void methodThatThrows() {
        try {
            throw new RuntimeException("oops");
        } finally {
            System.out.println("  Finally runs before exception propagates!");
        }
    }

    static void infiniteRecursion() {
        infiniteRecursion();
    }

    static void demonstrateException(String name, Runnable action) {
        try {
            action.run();
        } catch (Throwable t) {
            System.out.println(name + ": " + t.getClass().getSimpleName() + " - " + t.getMessage());
        }
    }
}
