package crosslang;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * CL07: Functional Programming (Streams & Lambdas) Across Languages
 *
 * Java       → Lambdas + Stream API (Java 8+), functional interfaces
 * Python     → First-class functions, list comprehensions, map/filter/reduce
 * JavaScript → First-class functions, array methods (.map/.filter/.reduce)
 * TypeScript → Same as JS with typed function signatures
 * C#         → LINQ, lambda expressions, Func<>/Action<> delegates
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL07_FunctionalProgrammingAcrossLanguages
 */
public class CL07_FunctionalProgrammingAcrossLanguages {

    public static void main(String[] args) {

        System.out.println("=== 1. LAMBDA / ANONYMOUS FUNCTION ===\n");

        // JAVA: lambda assigned to functional interface
        Function<Integer, Integer> square = x -> x * x;
        Function<String, String>   shout  = s -> s.toUpperCase() + "!";

        System.out.println("square(5):      " + square.apply(5));
        System.out.println("shout(hello):   " + shout.apply("hello"));

        /*
         * PYTHON:
         *   square = lambda x: x * x
         *   shout  = lambda s: s.upper() + "!"
         *
         * JAVASCRIPT:
         *   const square = x => x * x;
         *   const shout  = s => s.toUpperCase() + "!";
         *
         * TYPESCRIPT:
         *   const square = (x: number): number => x * x;
         *   const shout  = (s: string): string => s.toUpperCase() + "!";
         *
         * C#:
         *   Func<int, int>       square = x => x * x;
         *   Func<string, string> shout  = s => s.ToUpper() + "!";
         */

        System.out.println("\n=== 2. MAP (transform each element) ===\n");

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

        // JAVA: stream().map()
        List<Integer> squared = nums.stream()
                                    .map(x -> x * x)
                                    .collect(Collectors.toList());
        System.out.println("squared: " + squared);

        /*
         * PYTHON:
         *   squared = [x * x for x in nums]               # list comprehension (preferred)
         *   squared = list(map(lambda x: x * x, nums))    # map() equivalent
         *
         * JAVASCRIPT:
         *   const squared = nums.map(x => x * x);
         *
         * TYPESCRIPT:
         *   const squared: number[] = nums.map((x: number) => x * x);
         *
         * C# (LINQ):
         *   var squared = nums.Select(x => x * x).ToList();
         */

        System.out.println("\n=== 3. FILTER ===\n");

        // JAVA: stream().filter()
        List<Integer> evens = nums.stream()
                                  .filter(x -> x % 2 == 0)
                                  .collect(Collectors.toList());
        System.out.println("evens: " + evens);

        /*
         * PYTHON:
         *   evens = [x for x in nums if x % 2 == 0]          # comprehension
         *   evens = list(filter(lambda x: x % 2 == 0, nums))  # filter()
         *
         * JAVASCRIPT:
         *   const evens = nums.filter(x => x % 2 === 0);
         *
         * TYPESCRIPT:
         *   const evens: number[] = nums.filter((x: number) => x % 2 === 0);
         *
         * C# (LINQ):
         *   var evens = nums.Where(x => x % 2 == 0).ToList();
         */

        System.out.println("\n=== 4. REDUCE ===\n");

        // JAVA: stream().reduce()
        int sum = nums.stream().reduce(0, Integer::sum);
        System.out.println("sum: " + sum);

        /*
         * PYTHON:
         *   from functools import reduce
         *   total = reduce(lambda acc, x: acc + x, nums, 0)
         *   total = sum(nums)   # built-in shorthand
         *
         * JAVASCRIPT:
         *   const total = nums.reduce((acc, x) => acc + x, 0);
         *
         * TYPESCRIPT:
         *   const total: number = nums.reduce((acc: number, x: number) => acc + x, 0);
         *
         * C# (LINQ):
         *   int total = nums.Aggregate(0, (acc, x) => acc + x);
         *   int total = nums.Sum();   // built-in shorthand
         */

        System.out.println("\n=== 5. METHOD REFERENCE ===\n");

        // JAVA: :: method reference
        List<String> words = Arrays.asList("banana", "apple", "cherry");
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        /*
         * PYTHON:
         *   import operator
         *   list(map(str.upper, words))      # unbound method reference
         *
         * JAVASCRIPT:
         *   words.map(w => w.toUpperCase())  # no method reference syntax — use arrow
         *
         * TYPESCRIPT:
         *   words.map(w => w.toUpperCase())  # same as JS
         *
         * C# (method group):
         *   words.Select(string.IsNullOrEmpty)  // method group (limited compared to Java)
         *   words.ForEach(Console.WriteLine)    // method group as Action<T>
         */

        System.out.println("\n=== 6. CHAINING ===\n");

        // JAVA: chain multiple stream operations
        List<String> result = Arrays.asList("  hello  ", "world", "  java  ", "")
            .stream()
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(String::toUpperCase)
            .sorted()
            .collect(Collectors.toList());
        System.out.println("chained: " + result);

        /*
         * PYTHON:
         *   words = ["  hello  ", "world", "  java  ", ""]
         *   result = sorted([w.strip().upper() for w in words if w.strip()])
         *
         * JAVASCRIPT:
         *   const result = ["  hello  ", "world", "  java  ", ""]
         *     .map(s => s.trim())
         *     .filter(s => s.length > 0)
         *     .map(s => s.toUpperCase())
         *     .sort();
         *
         * TYPESCRIPT: identical to JS, just with type annotations if needed
         *
         * C# (LINQ method syntax):
         *   var result = new[] { "  hello  ", "world", "  java  ", "" }
         *     .Select(s => s.Trim())
         *     .Where(s => s.Length > 0)
         *     .Select(s => s.ToUpper())
         *     .OrderBy(s => s)
         *     .ToList();
         */

        System.out.println("\n=== 7. PREDICATE / FUNCTION COMPOSITION ===\n");

        // JAVA: Predicate composition with and()/or()/negate()
        Predicate<Integer> isEven     = x -> x % 2 == 0;
        Predicate<Integer> isPositive = x -> x > 0;
        Predicate<Integer> isEvenAndPositive = isEven.and(isPositive);

        System.out.println("4 is even and positive: " + isEvenAndPositive.test(4));
        System.out.println("-2 is even and positive: " + isEvenAndPositive.test(-2));

        /*
         * TYPESCRIPT:
         *   const isEven = (x: number) => x % 2 === 0;
         *   const isPositive = (x: number) => x > 0;
         *   const isEvenAndPositive = (x: number) => isEven(x) && isPositive(x);
         *
         * C#:
         *   Func<int, bool> isEven = x => x % 2 == 0;
         *   Func<int, bool> isPositive = x => x > 0;
         *   Func<int, bool> both = x => isEven(x) && isPositive(x);
         *   // No built-in .And() — compose manually or use extension methods
         *
         * PYTHON:
         *   is_even = lambda x: x % 2 == 0
         *   is_positive = lambda x: x > 0
         *   both = lambda x: is_even(x) and is_positive(x)
         *
         * JAVASCRIPT:
         *   const isEven = x => x % 2 === 0;
         *   const isPositive = x => x > 0;
         *   const both = x => isEven(x) && isPositive(x);
         */
    }
}
