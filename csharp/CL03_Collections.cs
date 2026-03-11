// CL03: Collections in C#
// Companion to CL03_CollectionsAcrossLanguages.java
//
// C# collections in System.Collections.Generic are nearly identical to Java.
// Main differences: Count (not size()), Add (not add()), Contains (not contains()).
// LINQ provides powerful query operations (no Java equivalent without Streams).
//
// Run: dotnet-script CL03_Collections.cs

using System;
using System.Collections.Generic;
using System.Linq;

class CL03_Collections
{
    static void Main()
    {
        Console.WriteLine("=== 1. LIST<T> (Java's ArrayList) ===\n");

        var fruits = new List<string>();
        fruits.Add("Apple");             // add()     — capital A
        fruits.Add("Banana");
        fruits.Add("Cherry");
        fruits.Add("Banana");            // duplicates OK
        Console.WriteLine($"List: [{string.Join(", ", fruits)}]");
        Console.WriteLine($"  [1]:          {fruits[1]}");          // get(1) — indexer
        Console.WriteLine($"  Count:        {fruits.Count}");       // size() — property!
        Console.WriteLine($"  Contains():   {fruits.Contains("Apple")}");
        Console.WriteLine($"  IndexOf():    {fruits.IndexOf("Banana")}");

        fruits[0] = "Avocado";           // set(0, ...) — indexer assignment
        fruits.Remove("Banana");         // remove first occurrence
        fruits.RemoveAt(0);              // remove by index
        fruits.Insert(0, "Mango");       // add(index, element)
        Console.WriteLine($"after operations: [{string.Join(", ", fruits)}]");

        // Sort
        fruits.Sort();
        Console.WriteLine($"sorted: [{string.Join(", ", fruits)}]");

        // LINQ (like Java Streams — no Java equivalent without import)
        var longNames = fruits.Where(f => f.Length > 5).ToList();
        var upper = fruits.Select(f => f.ToUpper()).ToList();
        Console.WriteLine($"length > 5:  [{string.Join(", ", longNames)}]");
        Console.WriteLine($"upper:       [{string.Join(", ", upper)}]");

        Console.WriteLine("\n=== 2. DICTIONARY<K,V> (Java's HashMap) ===\n");

        var scores = new Dictionary<string, int>();
        scores["Alice"] = 95;            // put()
        scores.Add("Bob", 87);           // put() — throws if key exists
        scores["Charlie"] = 92;
        scores["Bob"] = 90;              // overwrite
        Console.WriteLine($"Dict: {string.Join(", ", scores.Select(kv => $"{kv.Key}={kv.Value}"))}");
        Console.WriteLine($"  scores[\"Bob\"]:          {scores["Bob"]}");   // get()
        Console.WriteLine($"  GetValueOrDefault:      {scores.GetValueOrDefault("Dave", 0)}");
        Console.WriteLine($"  ContainsKey(\"Alice\"):   {scores.ContainsKey("Alice")}");
        Console.WriteLine($"  ContainsValue(92):      {scores.ContainsValue(92)}");
        Console.WriteLine($"  Keys:                   [{string.Join(", ", scores.Keys)}]");

        // Iterate entries
        foreach (var kvp in scores)
        {
            Console.WriteLine($"  {kvp.Key} -> {kvp.Value}");
        }

        // TryGetValue (safe get)
        if (scores.TryGetValue("Alice", out int aliceScore))
        {
            Console.WriteLine($"  Alice's score: {aliceScore}");
        }

        scores.Remove("Bob");
        Console.WriteLine($"after remove: {string.Join(", ", scores.Select(kv => $"{kv.Key}={kv.Value}"))}");

        Console.WriteLine("\n=== 3. HASHSET<T> (Java's HashSet) ===\n");

        var tags = new HashSet<string> { "java", "programming", "java", "coding" };  // duplicate ignored
        Console.WriteLine($"HashSet: [{string.Join(", ", tags)}]");
        Console.WriteLine($"  Contains(\"java\"): {tags.Contains("java")}");
        Console.WriteLine($"  Count:            {tags.Count}");              // property!
        tags.Add("csharp");
        tags.Remove("java");
        Console.WriteLine($"after add/remove: [{string.Join(", ", tags)}]");

        // Set operations (in-place)
        var setA = new HashSet<int> { 1, 2, 3, 4, 5 };
        var setB = new HashSet<int> { 4, 5, 6, 7, 8 };
        var unionSet = new HashSet<int>(setA); unionSet.UnionWith(setB);
        var interSet = new HashSet<int>(setA); interSet.IntersectWith(setB);
        var diffSet  = new HashSet<int>(setA); diffSet.ExceptWith(setB);
        Console.WriteLine($"union:        [{string.Join(", ", unionSet)}]");
        Console.WriteLine($"intersection: [{string.Join(", ", interSet)}]");
        Console.WriteLine($"difference:   [{string.Join(", ", diffSet)}]");

        Console.WriteLine("\n=== 4. QUEUE<T> & STACK<T> (identical to Java) ===\n");

        // Queue (FIFO)
        var queue = new Queue<string>();
        queue.Enqueue("first");    // offer()
        queue.Enqueue("second");
        queue.Enqueue("third");
        Console.WriteLine($"Queue: [{string.Join(", ", queue)}]");
        Console.WriteLine($"  Dequeue (poll): {queue.Dequeue()}");
        Console.WriteLine($"  Peek:           {queue.Peek()}");

        // Stack (LIFO)
        var stack = new Stack<string>();
        stack.Push("bottom");      // push()
        stack.Push("middle");
        stack.Push("top");
        Console.WriteLine($"Stack Pop: {stack.Pop()}");

        Console.WriteLine("\n=== 5. SORTING ===\n");

        var nums = new List<int> { 5, 2, 8, 1, 9, 3 };
        nums.Sort();                                       // ascending in-place
        Console.WriteLine($"sorted asc:  [{string.Join(", ", nums)}]");
        nums.Sort((a, b) => b - a);                        // descending with comparator
        Console.WriteLine($"sorted desc: [{string.Join(", ", nums)}]");

        // LINQ OrderBy — returns new sequence, doesn't modify original
        var words = new List<string> { "banana", "apple", "cherry" };
        var byLength = words.OrderBy(w => w.Length).ToList();
        var byLast   = words.OrderBy(w => w[w.Length - 1]).ToList();
        Console.WriteLine($"by length: [{string.Join(", ", byLength)}]");
        Console.WriteLine($"by last char: [{string.Join(", ", byLast)}]");

        Console.WriteLine("\n=== 6. LINQ OPERATIONS (like Java Streams) ===\n");

        var numbers = new List<int> { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        // Filter, Map, Reduce
        var evens  = numbers.Where(n => n % 2 == 0).ToList();
        var doubled = numbers.Select(n => n * 2).ToList();
        int sum    = numbers.Sum();
        int max    = numbers.Max();
        double avg = numbers.Average();
        bool anyGt8 = numbers.Any(n => n > 8);
        bool allGt0 = numbers.All(n => n > 0);

        Console.WriteLine($"evens:   [{string.Join(", ", evens)}]");
        Console.WriteLine($"doubled: [{string.Join(", ", doubled)}]");
        Console.WriteLine($"sum={sum}, max={max}, avg={avg:F1}");
        Console.WriteLine($"any > 8: {anyGt8}, all > 0: {allGt0}");

        Console.WriteLine("\n=== 7. WHEN TO USE WHAT ===\n");
        Console.WriteLine("List<T>              → ordered, duplicates, fast index   (ArrayList)");
        Console.WriteLine("Dictionary<K,V>      → key-value pairs                   (HashMap)");
        Console.WriteLine("HashSet<T>           → unique elements                   (HashSet)");
        Console.WriteLine("SortedDictionary<K,V>→ sorted key-value                  (TreeMap)");
        Console.WriteLine("SortedSet<T>         → sorted unique                     (TreeSet)");
        Console.WriteLine("Queue<T>             → FIFO                              (LinkedList/Queue)");
        Console.WriteLine("Stack<T>             → LIFO                              (ArrayDeque)");
        Console.WriteLine("LinkedList<T>        → fast insert/remove               (LinkedList)");
    }
}
