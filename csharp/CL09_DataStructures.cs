// CL09: Data Structures in C#
// Companion to CL09_DataStructuresAcrossLanguages.java
//
// C# collections are in System.Collections.Generic.
// Naming is similar to Java but: Count not size(), Add not add().
// PriorityQueue<T,P> added in .NET 6.
//
// Run: dotnet-script CL09_DataStructures.cs

using System;
using System.Collections.Generic;
using System.Linq;

class CL09_DataStructures
{
    static void Main()
    {
        Console.WriteLine("=== 1. LIST<T> (dynamic array) ===\n");

        var list = new List<string> { "apple", "banana", "cherry" };
        list.Add("date");
        list.Remove("banana");
        Console.WriteLine("list:      " + string.Join(", ", list));
        Console.WriteLine("Count:     " + list.Count);    // .Count not .size()
        Console.WriteLine("first:     " + list[0]);       // indexer
        Console.WriteLine("last:      " + list[^1]);      // C# 8+ index from end
        Console.WriteLine("contains:  " + list.Contains("date"));
        Console.WriteLine("indexOf:   " + list.IndexOf("date"));

        Console.WriteLine("\n=== 2. DICTIONARY<K,V> (hash map) ===\n");

        var scores = new Dictionary<string, int>
        {
            ["Alice"]   = 95,
            ["Bob"]     = 87,
            ["Charlie"] = 92
        };

        Console.WriteLine("Alice:    " + scores["Alice"]);
        Console.WriteLine("Has Bob:  " + scores.ContainsKey("Bob"));

        // TryGetValue — safe access (no KeyNotFoundException)
        if (scores.TryGetValue("Dave", out int daveScore))
            Console.WriteLine("Dave: " + daveScore);
        else
            Console.WriteLine("Dave not found");

        scores["Dave"] = 88;
        scores.Remove("Charlie");

        foreach (var kvp in scores)
            Console.WriteLine($"  {kvp.Key} -> {kvp.Value}");

        Console.WriteLine("\n=== 3. HASHSET<T> ===\n");

        var set = new HashSet<string> { "a", "b", "c", "a", "b" }; // dupes removed
        set.Add("d");
        Console.WriteLine("set:      " + string.Join(", ", set.OrderBy(x => x)));
        Console.WriteLine("has 'a':  " + set.Contains("a"));

        var s1 = new HashSet<int> { 1, 2, 3, 4 };
        var s2 = new HashSet<int> { 3, 4, 5, 6 };

        var intersection = new HashSet<int>(s1); intersection.IntersectWith(s2);
        var union        = new HashSet<int>(s1); union.UnionWith(s2);
        var difference   = new HashSet<int>(s1); difference.ExceptWith(s2);

        Console.WriteLine("intersection: " + string.Join(", ", intersection.OrderBy(x=>x)));
        Console.WriteLine("union:        " + string.Join(", ", union.OrderBy(x=>x)));
        Console.WriteLine("difference:   " + string.Join(", ", difference.OrderBy(x=>x)));

        Console.WriteLine("\n=== 4. STACK<T> AND QUEUE<T> ===\n");

        var stack = new Stack<int>();
        stack.Push(1); stack.Push(2); stack.Push(3);
        Console.WriteLine("stack peek: " + stack.Peek());
        Console.WriteLine("stack pop:  " + stack.Pop());  // LIFO

        var queue = new Queue<int>();
        queue.Enqueue(1); queue.Enqueue(2); queue.Enqueue(3);
        Console.WriteLine("queue peek:    " + queue.Peek());
        Console.WriteLine("queue dequeue: " + queue.Dequeue());  // FIFO

        Console.WriteLine("\n=== 5. PRIORITYQUEUE<T,P> (.NET 6+) ===\n");

        var pq = new PriorityQueue<string, int>();
        pq.Enqueue("low priority task",    3);
        pq.Enqueue("high priority task",   1);
        pq.Enqueue("medium priority task", 2);

        while (pq.Count > 0)
            Console.WriteLine("  " + pq.Dequeue());  // dequeues in priority order

        Console.WriteLine("\n=== KEY DIFFERENCES FROM JAVA ===\n");
        Console.WriteLine("Java .size()       → C# .Count (property)");
        Console.WriteLine("Java .get(i)       → C# [i] (indexer)");
        Console.WriteLine("Java HashMap       → C# Dictionary<K,V>");
        Console.WriteLine("Java LinkedList    → C# LinkedList<T> (or Queue<T>)");
        Console.WriteLine("Java PriorityQueue → C# PriorityQueue<T,P> (.NET 6+)");
    }
}
