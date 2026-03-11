package crosslang;

/**
 * CL05: Exception Handling Across Languages
 *
 * Java    → Checked + unchecked exceptions, try/catch/finally
 * Python  → Only unchecked, try/except/else/finally, rich built-ins
 * JS      → Only unchecked, try/catch/finally, Error hierarchy
 * C#      → Only unchecked (like Python/JS), try/catch/finally, when clause
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL05_ExceptionsAcrossLanguages
 */
public class CL05_ExceptionsAcrossLanguages {

    // Custom checked exception (only Java has this concept)
    static class InsufficientFundsException extends Exception {
        InsufficientFundsException(double amount) {
            super("Short by $" + amount);
        }
    }

    // Custom unchecked exception
    static class InvalidInputException extends RuntimeException {
        InvalidInputException(String msg) { super(msg); }
    }

    public static void main(String[] args) {

        System.out.println("=== 1. BASIC TRY/CATCH/FINALLY ===\n");

        try {
            int result = 10 / 0; // ArithmeticException: / by zero
            System.out.println(result); // unreachable, silences unused warning
        } catch (ArithmeticException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally always runs");
        }

        /*
         * PYTHON: try/except/else/finally (Python adds 'else' block!)
         *   try:
         *       result = 10 / 0
         *   except ZeroDivisionError as e:
         *       print(f"Caught: {e}")
         *   else:
         *       print("Runs ONLY if no exception was raised")  # no Java equivalent
         *   finally:
         *       print("Always runs")
         *
         * JAVASCRIPT:
         *   try {
         *       let result = 10 / 0;   // NOTE: JS returns Infinity, not an exception!
         *       throw new Error("manual throw");
         *   } catch (e) {
         *       console.log("Caught:", e.message);
         *   } finally {
         *       console.log("Always runs");
         *   }
         *
         * C# (identical to Java syntax):
         *   try {
         *       int result = 10 / 0;
         *   } catch (DivideByZeroException e) {
         *       Console.WriteLine($"Caught: {e.Message}");
         *   } finally {
         *       Console.WriteLine("Always runs");
         *   }
         */

        System.out.println("\n=== 2. EXCEPTION HIERARCHIES ===\n");

        System.out.println("JAVA:");
        System.out.println("  Throwable");
        System.out.println("    Error (don't catch: OutOfMemoryError, StackOverflowError)");
        System.out.println("    Exception");
        System.out.println("      RuntimeException (unchecked)");
        System.out.println("        NullPointerException, ArrayIndexOutOfBoundsException");
        System.out.println("        ClassCastException, NumberFormatException");
        System.out.println("      Checked Exceptions (must handle or declare)");
        System.out.println("        IOException, SQLException, FileNotFoundException");

        /*
         * PYTHON:
         *   BaseException
         *     SystemExit, KeyboardInterrupt, GeneratorExit   # don't catch normally
         *     Exception
         *       ValueError, TypeError, KeyError, IndexError  # like RuntimeException
         *       AttributeError, NameError, ZeroDivisionError
         *       OSError (FileNotFoundError, PermissionError)
         *   # ALL Python exceptions are "unchecked" — no forced handling
         *
         * JAVASCRIPT:
         *   Error
         *     TypeError       # like ClassCastException / NullPointerException
         *     RangeError      # like ArrayIndexOutOfBoundsException
         *     ReferenceError  # accessing undefined variable
         *     SyntaxError     # parse-time errors
         *   # ALL JS exceptions are "unchecked"
         *
         * C#:
         *   Exception
         *     SystemException
         *       NullReferenceException    # NullPointerException
         *       IndexOutOfRangeException  # ArrayIndexOutOfBoundsException
         *       InvalidCastException      # ClassCastException
         *       DivideByZeroException     # ArithmeticException
         *       OverflowException
         *     ApplicationException        # base for custom app exceptions
         *   # ALL C# exceptions are "unchecked" — no forced handling
         */

        System.out.println("\n=== 3. CHECKED vs UNCHECKED (Java-only concept) ===\n");

        // JAVA ONLY: checked exceptions MUST be handled or declared
        try {
            withdraw(100, 500); // checked exception — compiler forces this try/catch
        } catch (InsufficientFundsException e) {
            System.out.println("Checked exception: " + e.getMessage());
        }

        // Unchecked: optional to catch
        try {
            validate(-1);
        } catch (InvalidInputException e) {
            System.out.println("Unchecked exception: " + e.getMessage());
        }

        /*
         * PYTHON/JS/C#: NO concept of checked exceptions
         *   def withdraw(balance, amount):
         *       if amount > balance:
         *           raise ValueError("Insufficient funds")  # no 'throws' declaration
         *   # Caller is NOT forced to handle it — it's their choice
         *
         * C#: same — no 'throws' in method signatures
         *   void Withdraw(double balance, double amount) {
         *       if (amount > balance) throw new InvalidOperationException("Insufficient funds");
         *   }
         *   // Caller decides whether to catch or let it propagate
         */

        System.out.println("\n=== 4. MULTIPLE CATCH BLOCKS ===\n");

        // JAVA: most specific first
        try {
            String s = null;
            s.length();
        } catch (NullPointerException e) {
            System.out.println("NPE caught");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException caught");
        }

        // JAVA multi-catch (Java 7+)
        try {
            System.out.println(Integer.parseInt("abc")); // NumberFormatException
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Multi-catch: " + e.getClass().getSimpleName());
        }

        /*
         * PYTHON: multiple except blocks
         *   try:
         *       ...
         *   except ValueError:
         *       print("ValueError")
         *   except (TypeError, KeyError):   # tuple = multi-catch
         *       print("TypeError or KeyError")
         *   except Exception as e:          # catch-all (like catch(Exception e))
         *       print(f"General: {e}")
         *
         * JAVASCRIPT:
         *   try { ... }
         *   catch (e) {                     // only ONE catch block in JS!
         *       if (e instanceof TypeError) { ... }
         *       else if (e instanceof RangeError) { ... }
         *       else throw e;               // re-throw if not handled
         *   }
         *
         * C#:
         *   try { ... }
         *   catch (NullReferenceException e) { ... }  // specific first
         *   catch (Exception e) when (e.Message.Contains("foo")) { ... }  // 'when' filter (no Java equivalent!)
         *   catch (Exception e) { ... }               // general last
         */

        System.out.println("\n=== 5. CUSTOM EXCEPTIONS ===\n");

        try {
            throw new InvalidInputException("Custom unchecked exception");
        } catch (InvalidInputException e) {
            System.out.println("Custom: " + e.getMessage());
            System.out.println("Class:  " + e.getClass().getSimpleName());
        }

        /*
         * PYTHON:
         *   class InsufficientFundsError(Exception):  # extend Exception
         *       def __init__(self, amount):
         *           super().__init__(f"Short by ${amount}")
         *           self.amount = amount
         *
         *   raise InsufficientFundsError(150)  # like 'throw new ...'
         *
         * JAVASCRIPT:
         *   class InsufficientFundsError extends Error {
         *       constructor(amount) {
         *           super(`Short by $${amount}`);
         *           this.name = "InsufficientFundsError";  // important!
         *           this.amount = amount;
         *       }
         *   }
         *   throw new InsufficientFundsError(150);
         *
         * C#:
         *   class InsufficientFundsException : Exception {  // : Exception = extends Exception
         *       public double Amount { get; }
         *       public InsufficientFundsException(double amount)
         *           : base($"Short by ${amount}") {         // : base() = super()
         *           Amount = amount;
         *       }
         *   }
         *   throw new InsufficientFundsException(150);
         */

        System.out.println("\n=== 6. FINALLY BEHAVIOR ===\n");

        System.out.println("With return: " + methodWithReturn());

        /*
         * Same behavior in Python, JS, and C#:
         * finally always runs, even if there's a return or exception.
         *
         * PYTHON:
         *   def method():
         *       try: return "from try"
         *       finally: print("finally runs!")
         *
         * JAVASCRIPT:
         *   function method() {
         *       try { return "from try"; }
         *       finally { console.log("finally runs!"); }
         *   }
         *
         * C#:
         *   string Method() {
         *       try { return "from try"; }
         *       finally { Console.WriteLine("finally runs!"); }
         *   }
         */

        System.out.println("\n=== 7. KEY DIFFERENCES SUMMARY ===\n");
        System.out.println("Checked exceptions:     Java ONLY — Python/JS/C# have none");
        System.out.println("'throws' declaration:   Java ONLY — others don't declare exceptions");
        System.out.println("catch syntax:           Java/C#/JS: catch(Type e) | Python: except Type as e");
        System.out.println("multi-catch:            Java: Ex1|Ex2 | Python: (Ex1, Ex2) | C#: separate blocks + when");
        System.out.println("extra block:            Python adds 'else' (runs if NO exception)");
        System.out.println("conditional catch:      C# adds 'when' clause | others use if inside catch");
        System.out.println("10 / 0 behavior:        Java/C#/Python: exception | JS: returns Infinity!");
    }

    static void withdraw(double balance, double amount) throws InsufficientFundsException {
        if (amount > balance) throw new InsufficientFundsException(amount - balance);
    }

    static void validate(int value) {
        if (value < 0) throw new InvalidInputException("Value must be non-negative: " + value);
    }

    static String methodWithReturn() {
        try {
            return "from try";
        } finally {
            System.out.println("  Finally runs before return!");
        }
    }
}
