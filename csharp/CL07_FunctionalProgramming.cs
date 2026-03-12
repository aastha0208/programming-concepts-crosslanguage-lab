// CL07: Functional Programming in C# (LINQ)
// Companion to CL07_FunctionalProgrammingAcrossLanguages.java
//
// C# has Func<>, Action<>, and LINQ for functional programming.
// LINQ provides Select/Where/Aggregate — Java's map/filter/reduce equivalents.
// Lambda syntax is identical to Java: x => x * x
//
// Run: dotnet-script CL07_FunctionalProgramming.cs

using System;
using System.Collections.Generic;
using System.Linq;

class CL07_FunctionalProgramming
{
    static void Main()
    {
        Console.WriteLine("=== 1. LAMBDA EXPRESSIONS ===\n");

        Func<int, int>       square = x => x * x;
        Func<string, string> shout  = s => s.ToUpper() + "!";
        Func<int, int, int>  add    = (a, b) => a + b;
        Func<int, bool>      isEven = x => x % 2 == 0;
        Action<string>       print  = s => Console.WriteLine("  " + s);

        Console.WriteLine("square(5):    " + square(5));
        Console.WriteLine("shout(hello): " + shout("hello"));
        Console.WriteLine("add(3,4):     " + add(3, 4));
        Console.WriteLine("isEven(4):    " + isEven(4));
        print("hello from Action");

        Console.WriteLine("\n=== 2. MAP (Select) ===\n");

        var nums = new List<int> { 1, 2, 3, 4, 5 };

        var squared  = nums.Select(x => x * x).ToList();
        var doubled  = nums.Select(x => x * 2).ToList();
        var asString = nums.Select(x => x.ToString()).ToList();

        Console.WriteLine("squared:  " + string.Join(", ", squared));
        Console.WriteLine("doubled:  " + string.Join(", ", doubled));
        Console.WriteLine("asString: " + string.Join(", ", asString));

        Console.WriteLine("\n=== 3. FILTER (Where) ===\n");

        var evens = nums.Where(x => x % 2 == 0).ToList();
        var odds  = nums.Where(x => x % 2 != 0).ToList();

        var words     = new List<string> { "apple", "fig", "banana", "kiwi", "cherry" };
        var longWords = words.Where(w => w.Length > 4).ToList();

        Console.WriteLine("evens:     " + string.Join(", ", evens));
        Console.WriteLine("odds:      " + string.Join(", ", odds));
        Console.WriteLine("longWords: " + string.Join(", ", longWords));

        Console.WriteLine("\n=== 4. REDUCE (Aggregate) ===\n");

        int sum     = nums.Aggregate(0, (acc, x) => acc + x);
        int product = nums.Aggregate(1, (acc, x) => acc * x);

        // Built-in shortcuts:
        int sum2 = nums.Sum();
        int max  = nums.Max();
        int min  = nums.Min();

        Console.WriteLine("Aggregate sum:  " + sum);
        Console.WriteLine("Sum() shortcut: " + sum2);
        Console.WriteLine("Max:            " + max);
        Console.WriteLine("product:        " + product);

        Console.WriteLine("\n=== 5. CHAINING (LINQ method syntax) ===\n");

        var result = new List<string> { "  hello  ", "world", "  csharp  ", "", "  linq  " }
            .Select(s => s.Trim())
            .Where(s => s.Length > 0)
            .Select(s => s.ToUpper())
            .OrderBy(s => s)
            .ToList();

        Console.WriteLine("chained: " + string.Join(", ", result));

        Console.WriteLine("\n=== 6. LINQ QUERY SYNTAX (alternative to method syntax) ===\n");

        // Same as above, in SQL-like query syntax
        var result2 = (from s in new[] { "  hello  ", "world", "  csharp  ", "" }
                       let t = s.Trim()
                       where t.Length > 0
                       orderby t
                       select t.ToUpper()).ToList();

        Console.WriteLine("query syntax: " + string.Join(", ", result2));

        Console.WriteLine("\n=== 7. FUNC COMPOSITION ===\n");

        Func<int, bool> isPositive = x => x > 0;
        Func<int, bool> isEvenAndPositive = x => isEven(x) && isPositive(x);

        Console.WriteLine("4 isEvenAndPositive:  " + isEvenAndPositive(4));
        Console.WriteLine("-2 isEvenAndPositive: " + isEvenAndPositive(-2));

        // Method group — use existing methods as Func<>
        var words2 = new[] { "banana", "APPLE", "Cherry" };
        var lower  = words2.Select(string.IsNullOrEmpty).ToList(); // method group
        var sorted = words2.OrderBy(w => w, StringComparer.OrdinalIgnoreCase).ToList();
        Console.WriteLine("case-insensitive sort: " + string.Join(", ", sorted));

        Console.WriteLine("\n=== KEY DIFFERENCES FROM JAVA ===\n");
        Console.WriteLine("Java:  Stream API with Collectors.toList()");
        Console.WriteLine("C#:    LINQ with .ToList() — very similar!");
        Console.WriteLine("C#:    Func<in,out> = Java Function<T,R>");
        Console.WriteLine("C#:    Action<T> = Java Consumer<T> (void return)");
        Console.WriteLine("C#:    no .and()/.or() on Func — compose manually");
    }
}
