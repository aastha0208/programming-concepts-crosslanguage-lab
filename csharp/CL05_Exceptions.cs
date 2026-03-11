// CL05: Exception Handling in C#
// Companion to CL05_ExceptionsAcrossLanguages.java
//
// C# exception handling is nearly identical to Java EXCEPT:
// - No checked exceptions (all are unchecked like Python/JS)
// - 'when' clause for conditional catch (no Java equivalent)
// - No 'throws' in method signatures
//
// Run: dotnet-script CL05_Exceptions.cs

using System;

// Custom exception — extend Exception (not RuntimeException like Java)
class InsufficientFundsException : Exception
{
    public double Shortfall { get; }

    public InsufficientFundsException(double shortfall)
        : base($"Insufficient funds. Short by ${shortfall:F2}")
    {
        Shortfall = shortfall;
    }
}

class InvalidInputException : ArgumentException    // extend ArgumentException (like RuntimeException)
{
    public InvalidInputException(string message) : base(message) { }
}

class CL05_Exceptions
{
    static void Main()
    {
        Console.WriteLine("=== 1. BASIC TRY/CATCH/FINALLY ===\n");

        try {
            int result = 10 / 0;         // DivideByZeroException (Java: ArithmeticException)
            Console.WriteLine(result);
        } catch (DivideByZeroException e) {
            Console.WriteLine($"Caught: {e.Message}");
        } finally {
            Console.WriteLine("Finally always runs");
        }

        Console.WriteLine("\n=== 2. EXCEPTION HIERARCHY ===\n");

        Console.WriteLine("Exception");
        Console.WriteLine("  SystemException");
        Console.WriteLine("    NullReferenceException   <- Java: NullPointerException");
        Console.WriteLine("    IndexOutOfRangeException <- Java: ArrayIndexOutOfBoundsException");
        Console.WriteLine("    InvalidCastException     <- Java: ClassCastException");
        Console.WriteLine("    DivideByZeroException    <- Java: ArithmeticException");
        Console.WriteLine("    OverflowException        <- Java: no direct equivalent");
        Console.WriteLine("    FormatException          <- Java: NumberFormatException");
        Console.WriteLine("    ArgumentException");
        Console.WriteLine("      ArgumentNullException");
        Console.WriteLine("      ArgumentOutOfRangeException");
        Console.WriteLine("  ApplicationException      <- base for custom app exceptions");
        Console.WriteLine("ALL are unchecked — no forced handling!");

        Console.WriteLine("\n=== 3. MULTIPLE CATCH BLOCKS ===\n");

        // Order matters: most specific first
        try {
            string s = null;
            int len = s.Length;          // NullReferenceException
        } catch (NullReferenceException e) {
            Console.WriteLine($"NullReferenceException: {e.Message}");
        } catch (Exception e) {
            Console.WriteLine($"General: {e.Message}");
        }

        // 'when' clause — conditional catch (no Java equivalent!)
        for (int i = -1; i <= 1; i++) {
            try {
                if (i == 0) throw new ArgumentException("Zero value", nameof(i));
                if (i < 0) throw new ArgumentOutOfRangeException(nameof(i), "Must be positive");
                Console.WriteLine($"  i={i}: success");
            }
            catch (ArgumentOutOfRangeException e) when (e.ParamName == "i") {
                Console.WriteLine($"  i={i}: out of range — {e.Message}");
            }
            catch (ArgumentException e) when (e.Message.Contains("Zero")) {
                Console.WriteLine($"  i={i}: zero exception — {e.Message}");
            }
        }

        Console.WriteLine("\n=== 4. NO CHECKED EXCEPTIONS ===\n");

        // In Java: void withdraw(...) throws InsufficientFundsException { }
        // In C#:   no 'throws' keyword — callers are NOT forced to handle it
        Console.WriteLine("C# has no 'throws' in method signatures.");
        Console.WriteLine("Callers decide whether to catch or let exceptions propagate.");

        try {
            Withdraw(100, 250);
        } catch (InsufficientFundsException e) {
            Console.WriteLine($"Caught: {e.Message}");
            Console.WriteLine($"Shortfall: ${e.Shortfall}");
        }

        Console.WriteLine("\n=== 5. CUSTOM EXCEPTIONS ===\n");

        try {
            ValidateAge(-5);
        } catch (InvalidInputException e) {
            Console.WriteLine($"Invalid: {e.Message}");
        }

        // Re-throw
        try {
            try {
                int.Parse("bad");
            } catch (FormatException) {
                Console.WriteLine("  Inner caught, re-throwing...");
                throw;                    // re-throw (preserves stack trace, unlike 'throw e')
            }
        } catch (FormatException e) {
            Console.WriteLine($"  Outer caught: {e.Message}");
        }

        Console.WriteLine("\n=== 6. FINALLY BEHAVIOR ===\n");

        Console.WriteLine($"returned: {MethodWithReturn()}");

        Console.WriteLine("\n=== 7. USING STATEMENT (try-with-resources) ===\n");

        // C# 'using' = Java's try-with-resources
        // Automatically calls Dispose() (like AutoCloseable.close())
        using (var writer = new System.IO.StringWriter())
        {
            writer.Write("Hello from using statement");
            Console.WriteLine(writer.ToString());
        }  // writer.Dispose() called automatically here

        // C# 8+ using declaration (even cleaner)
        using var writer2 = new System.IO.StringWriter();
        writer2.Write("C# 8+ using declaration");
        Console.WriteLine(writer2.ToString());
        // writer2.Dispose() called at end of scope

        Console.WriteLine("\n=== 8. KEY DIFFERENCES FROM JAVA ===\n");
        Console.WriteLine("1. No checked exceptions — all are unchecked");
        Console.WriteLine("2. No 'throws' in method signatures");
        Console.WriteLine("3. 'catch (Ex e) when (condition)' filter (no Java equivalent)");
        Console.WriteLine("4. 'using' statement = try-with-resources");
        Console.WriteLine("5. DivideByZeroException vs ArithmeticException");
        Console.WriteLine("6. NullReferenceException vs NullPointerException");
        Console.WriteLine("7. FormatException vs NumberFormatException");
        Console.WriteLine("8. 'throw;' (re-throw) vs 'throw e;' (new throw — loses stack trace)");
    }

    static void Withdraw(double balance, double amount)
    // Note: no 'throws' declaration — C# doesn't have this
    {
        if (amount > balance)
            throw new InsufficientFundsException(amount - balance);
    }

    static void ValidateAge(int age)
    {
        if (age < 0)
            throw new InvalidInputException($"Age cannot be negative: {age}");
    }

    static string MethodWithReturn()
    {
        try {
            return "from try";
        } finally {
            Console.WriteLine("  Finally runs before return!");
        }
    }
}
