// CL12: Design Patterns in C#
// Companion to CL12_DesignPatternsAcrossLanguages.java
//
// C# specific features simplify several patterns:
// - Singleton: Lazy<T> is thread-safe with one line
// - Builder: object initializers { } and records with 'with'
// - Observer: event keyword is a first-class language feature
// - Strategy: Action<T>/Func<T> delegates can replace interfaces
//
// Run: dotnet-script CL12_DesignPatterns.cs

using System;
using System.Collections.Generic;

class CL12_DesignPatterns
{
    // ================================================================
    // 1. SINGLETON — Lazy<T> (thread-safe, cleaner than Java)
    // ================================================================

    sealed class DatabaseConnection
    {
        // Lazy<T> handles thread safety automatically — no double-checked locking!
        private static readonly Lazy<DatabaseConnection> _instance =
            new Lazy<DatabaseConnection>(() => new DatabaseConnection("server=localhost;db=app"));

        public static DatabaseConnection Instance => _instance.Value;

        private readonly string connectionString;
        private DatabaseConnection(string cs) { connectionString = cs; }
        public string Query(string sql) => $"result of: {sql}";
        public override string ToString() => $"DB[{connectionString}]";
    }

    // ================================================================
    // 2. STRATEGY — interface OR Action<T> delegate
    // ================================================================

    interface ISortStrategy { void Sort(int[] data); }

    class BubbleSort : ISortStrategy
    {
        public void Sort(int[] data) =>
            Console.WriteLine($"  BubbleSort on {data.Length} elements");
    }

    class QuickSort : ISortStrategy
    {
        public void Sort(int[] data) =>
            Console.WriteLine($"  QuickSort on {data.Length} elements");
    }

    // Using Action<T> as strategy — no interface class needed
    class FlexibleSorter
    {
        public Action<int[]> Strategy { get; set; }
        public FlexibleSorter(Action<int[]> strategy) { Strategy = strategy; }
        public void Sort(int[] data) => Strategy(data);
    }

    // ================================================================
    // 3. BUILDER — C# object initializer vs Java Builder pattern
    // ================================================================

    class Pizza
    {
        public string Size    { get; init; } = "medium";
        public string Crust   { get; init; } = "thin";
        public bool   Cheese  { get; init; } = false;
        public bool   Pepperoni { get; init; } = false;
        public override string ToString() =>
            $"Pizza[{Size},{Crust},cheese={Cheese},pepperoni={Pepperoni}]";
    }

    // C# 9+ record with 'with' expression
    record PizzaRecord(string Size, string Crust = "thin", bool Cheese = false, bool Pepperoni = false);

    // ================================================================
    // 4. OBSERVER — C# event keyword (first-class language feature)
    // ================================================================

    class EventBus
    {
        // event is built into C# — subscribe with +=, unsubscribe with -=
        public event Action<string> OnEvent;

        public void Publish(string eventData) => OnEvent?.Invoke(eventData);
    }

    // ================================================================
    // 5. FACTORY
    // ================================================================

    interface IAnimal { string Speak(); }
    class Dog : IAnimal { public string Speak() => "Woof!"; }
    class Cat : IAnimal { public string Speak() => "Meow!"; }

    static IAnimal CreateAnimal(string type) => type switch
    {
        "dog"  => new Dog(),
        "cat"  => new Cat(),
        _ => throw new ArgumentException($"Unknown animal: {type}")
    };

    static void Main()
    {
        Console.WriteLine("=== 1. SINGLETON (Lazy<T>) ===\n");
        var db1 = DatabaseConnection.Instance;
        var db2 = DatabaseConnection.Instance;
        Console.WriteLine("Same instance: " + ReferenceEquals(db1, db2));  // true
        Console.WriteLine(db1.Query("SELECT *"));

        Console.WriteLine("\n=== 2. STRATEGY ===\n");

        // Interface-based
        var sorters = new ISortStrategy[] { new BubbleSort(), new QuickSort() };
        var data = new[] { 5, 2, 8, 1 };
        foreach (var s in sorters) s.Sort(data);

        // Delegate-based (no interface needed)
        var flexSorter = new FlexibleSorter(d => {
            Array.Sort(d);
            Console.WriteLine("  Lambda sort: " + string.Join(", ", d));
        });
        flexSorter.Sort(new[] { 5, 2, 8, 1 });

        Console.WriteLine("\n=== 3. BUILDER / OBJECT INITIALIZER ===\n");

        // Object initializer — no Builder class needed!
        var pizza1 = new Pizza { Size = "large", Crust = "thick", Cheese = true, Pepperoni = true };
        var pizza2 = new Pizza { Size = "small" };
        Console.WriteLine(pizza1);
        Console.WriteLine(pizza2);

        // Record with 'with' expression (C# 9+)
        var basePizza  = new PizzaRecord("medium");
        var cheesyPizza = basePizza with { Size = "large", Cheese = true };
        Console.WriteLine(cheesyPizza);

        Console.WriteLine("\n=== 4. OBSERVER (event keyword) ===\n");

        var bus = new EventBus();

        // += subscribes, -= unsubscribes
        Action<string> listenerA = e => Console.WriteLine($"  Listener A: {e}");
        Action<string> listenerB = e => Console.WriteLine($"  Listener B: {e}");

        bus.OnEvent += listenerA;
        bus.OnEvent += listenerB;
        bus.Publish("user_logged_in");

        bus.OnEvent -= listenerA;
        bus.Publish("user_logged_out");  // only listenerB fires

        Console.WriteLine("\n=== 5. FACTORY ===\n");

        foreach (var type in new[] { "dog", "cat" })
        {
            var animal = CreateAnimal(type);
            Console.WriteLine($"  {type}: {animal.Speak()}");
        }

        Console.WriteLine("\n=== C# SIMPLIFICATIONS vs JAVA ===\n");
        Console.WriteLine("Singleton  → Lazy<T> is thread-safe in one line");
        Console.WriteLine("Builder    → Object initializers { } or records with 'with'");
        Console.WriteLine("Observer   → event keyword is built-in (no Observer interface)");
        Console.WriteLine("Strategy   → Action<T>/Func<T> delegates (no interface needed)");
        Console.WriteLine("Factory    → switch expression (pattern matching) is concise");
    }
}
