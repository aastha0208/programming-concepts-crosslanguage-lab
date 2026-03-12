// CL06: Generics in C#
// Companion to CL06_GenericsAcrossLanguages.java
//
// C# generics are REIFIED — type info is preserved at runtime.
// This is unlike Java (type erasure) and TypeScript (erased to JS).
// C# can do: typeof(T), new T(), default(T) at runtime.
//
// Run: dotnet-script CL06_Generics.cs
// Or create a console app: dotnet new console, paste into Program.cs

using System;
using System.Collections.Generic;

class CL06_Generics
{
    // Generic class
    class Box<T>
    {
        private T value;
        public Box(T value) { this.value = value; }
        public T Get() => value;
        public override string ToString() => $"Box[{value}]";
    }

    // Generic method with constraint
    static T Max<T>(T a, T b) where T : IComparable<T>
    {
        return a.CompareTo(b) >= 0 ? a : b;
    }

    // Bounded: T must be a numeric type (approximate Java ? extends Number)
    static double SumList<T>(IList<T> list) where T : struct, IConvertible
    {
        double total = 0;
        foreach (var item in list) total += Convert.ToDouble(item);
        return total;
    }

    // Generic with multiple type parameters
    class Pair<TFirst, TSecond>
    {
        public TFirst First { get; }
        public TSecond Second { get; }
        public Pair(TFirst first, TSecond second) { First = first; Second = second; }
        public override string ToString() => $"({First}, {Second})";
    }

    // REIFIED GENERICS: can check T type at runtime — Java cannot!
    static void PrintTypeInfo<T>()
    {
        Console.WriteLine($"  Type: {typeof(T).Name}");
        Console.WriteLine($"  Is value type: {typeof(T).IsValueType}");
        Console.WriteLine($"  Default: {default(T)}");
    }

    static void Main()
    {
        Console.WriteLine("=== 1. GENERIC CLASS ===\n");

        var intBox = new Box<int>(42);
        var strBox = new Box<string>("Hello");
        Console.WriteLine("int box:    " + intBox);
        Console.WriteLine("string box: " + strBox);

        Console.WriteLine("\n=== 2. GENERIC METHOD WITH CONSTRAINT ===\n");

        Console.WriteLine("Max(3, 7):          " + Max(3, 7));
        Console.WriteLine("Max(\"apple\",\"banana\"): " + Max("apple", "banana"));
        Console.WriteLine("Max(1.5, 2.5):      " + Max(1.5, 2.5));

        Console.WriteLine("\n=== 3. GENERIC COLLECTIONS ===\n");

        var scores = new Dictionary<string, List<int>>();
        scores["Alice"] = new List<int> { 95, 87, 92 };
        scores["Bob"]   = new List<int> { 78, 85 };
        foreach (var kvp in scores)
            Console.WriteLine($"  {kvp.Key}: {string.Join(", ", kvp.Value)}");

        var ints    = new List<int>    { 1, 2, 3, 4, 5 };
        var doubles = new List<double> { 1.5, 2.5, 3.0 };
        Console.WriteLine("SumList ints:    " + SumList(ints));
        Console.WriteLine("SumList doubles: " + SumList(doubles));

        Console.WriteLine("\n=== 4. MULTIPLE TYPE PARAMETERS ===\n");

        var pair = new Pair<string, int>("Alice", 95);
        Console.WriteLine("Pair: " + pair);

        Console.WriteLine("\n=== 5. REIFIED GENERICS (unique to C#) ===\n");

        Console.WriteLine("typeof(int):");
        PrintTypeInfo<int>();
        Console.WriteLine("typeof(string):");
        PrintTypeInfo<string>();

        // C#: Box<int> and Box<string> are DIFFERENT types at runtime
        var b1 = new Box<int>(1);
        var b2 = new Box<string>("x");
        Console.WriteLine($"\nBox<int> type:    {b1.GetType().Name}");
        Console.WriteLine($"Box<string> type: {b2.GetType().Name}");
        Console.WriteLine($"Same type?        {b1.GetType() == b2.GetType()}");
        // false! — unlike Java where both would be just Box

        Console.WriteLine("\n=== 6. NULLABLE<T> ===\n");

        int? nullable = null;           // Nullable<int> — C# specific
        Console.WriteLine($"nullable is null: {nullable == null}");
        nullable = 42;
        Console.WriteLine($"nullable value:   {nullable.Value}");
        Console.WriteLine($"GetValueOrDefault:{nullable.GetValueOrDefault(0)}");

        Console.WriteLine("\n=== KEY DIFFERENCE FROM JAVA ===\n");
        Console.WriteLine("Java:  type erasure — Box<int> becomes Box at runtime");
        Console.WriteLine("C#:    reified — Box<int> and Box<string> are different types");
        Console.WriteLine("C#:    typeof(T), default(T), new T() all work at runtime");
    }
}
