// CL01: Type System in C#
// Companion to CL01_TypeSystem.java
//
// C# is the closest language to Java — statically typed, strongly typed.
// Main differences: bool (not boolean), string (not String), var, nullable types.
//
// Run: dotnet-script CL01_TypeSystem.cs
// Or create a console app: dotnet new console, paste into Program.cs

using System;

class CL01_TypeSystem
{
    static void Main()
    {
        Console.WriteLine("=== 1. VARIABLE DECLARATION ===\n");

        // C# types vs Java types:
        // Java         C#
        // int          int       (same!)
        // long         long      (same!)
        // double       double    (same!)
        // float        float     (same!)
        // boolean      bool      (different!)
        // char         char      (same!)
        // byte         byte      (same!)
        // String       string    (lowercase alias — both work)

        int age = 25;
        string name = "Alice";      // lowercase 'string' is preferred in C#
        double salary = 75000.50;
        bool isActive = true;       // 'bool' not 'boolean'
        char grade = 'A';

        Console.WriteLine($"age={age}, name={name}, salary={salary}, isActive={isActive}");

        // C# var — same as Java 10+ var, works in older C# too
        var city = "New York";      // compiler infers string
        var count = 42;             // compiler infers int
        Console.WriteLine($"city={city} ({city.GetType().Name}), count={count} ({count.GetType().Name})");

        Console.WriteLine("\n=== 2. PRIMITIVE RANGES (identical to Java) ===\n");

        Console.WriteLine($"int range:    {int.MinValue} to {int.MaxValue}");
        Console.WriteLine($"long range:   {long.MinValue} to {long.MaxValue}");
        Console.WriteLine($"double max:   {double.MaxValue}");
        Console.WriteLine($"float max:    {float.MaxValue}");

        // C# has uint, ulong, ushort (unsigned types) — Java does not!
        uint unsignedInt = 4294967295;
        Console.WriteLine($"uint max:     {unsignedInt}");

        Console.WriteLine("\n=== 3. TYPE CASTING (same syntax as Java) ===\n");

        double pi = 3.99;
        int truncated = (int)pi;               // explicit narrowing — same as Java
        long bigNum = 100L;
        int smaller = (int)bigNum;
        double widened = smaller;              // implicit widening — same as Java

        Console.WriteLine($"double->int (truncates): {pi} -> {truncated}");
        Console.WriteLine($"implicit widening: {widened}");

        // Convert class (rounds, unlike cast which truncates!)
        int rounded = Convert.ToInt32(pi);     // rounds to 4 (different from (int)pi = 3!)
        Console.WriteLine($"Convert.ToInt32({pi}) = {rounded} (rounds!)");

        // Parse methods
        int parsed = int.Parse("123");
        double parsedD = double.Parse("3.14");
        Console.WriteLine($"int.Parse(\"123\") = {parsed}");

        // TryParse (safe, no exception)
        bool success = int.TryParse("abc", out int result);
        Console.WriteLine($"TryParse(\"abc\"): success={success}, result={result}");

        Console.WriteLine("\n=== 4. NULLABLE TYPES (no Java equivalent built-in) ===\n");

        // In Java: int can't be null. Integer (wrapper) can.
        // In C#: int? makes any value type nullable with '?' syntax
        int? nullableInt = null;
        int? hasValue = 42;

        Console.WriteLine($"nullableInt = {nullableInt}");         // empty
        Console.WriteLine($"hasValue = {hasValue}");
        Console.WriteLine($"hasValue.HasValue = {hasValue.HasValue}");
        Console.WriteLine($"hasValue.Value = {hasValue.Value}");
        Console.WriteLine($"nullableInt ?? 0 = {nullableInt ?? 0}");  // null coalescing

        // Null-conditional operator ?.
        string nullStr = null;
        Console.WriteLine($"nullStr?.Length = {nullStr?.Length}");     // null, no NullReferenceException
        Console.WriteLine($"nullStr?.ToUpper() ?? \"N/A\" = {nullStr?.ToUpper() ?? "N/A"}");

        Console.WriteLine("\n=== 5. NULL HANDLING ===\n");

        string s = null;
        Console.WriteLine($"null string: {s}");

        try {
            int len = s.Length;     // NullReferenceException (like Java's NullPointerException)
        } catch (NullReferenceException e) {
            Console.WriteLine($"NullReferenceException caught: {e.Message}");
        }

        Console.WriteLine("\n=== 6. TYPE CHECKING ===\n");

        object obj = "Hello";
        Console.WriteLine($"obj is string: {obj is string}");         // instanceof
        Console.WriteLine($"GetType: {obj.GetType().Name}");

        // Pattern matching (C# 7+) — more powerful than Java instanceof
        if (obj is string str)                                         // declares 'str' automatically
        {
            Console.WriteLine($"Pattern match: length = {str.Length}");
        }

        // Switch expression with pattern matching (C# 8+)
        string description = obj switch {
            string s2 when s2.Length > 3 => "long string",
            string => "short string",
            int n => $"integer {n}",
            null => "null",
            _ => "something else"
        };
        Console.WriteLine($"Switch pattern: {description}");

        Console.WriteLine("\n=== 7. CONSTANTS ===\n");

        const double TaxRate = 0.08;     // compile-time constant (same as Java final)
        const int MaxSize = 100;
        Console.WriteLine($"TaxRate={TaxRate}, MaxSize={MaxSize}");

        // readonly — runtime constant (set in constructor)
        // readonly int runtimeConst = ComputeValue(); // in class context
    }
}
