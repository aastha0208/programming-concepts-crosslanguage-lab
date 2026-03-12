// CL10: Sorting in C#
// Companion to CL10_SortingAcrossLanguages.java
//
// C# offers Array.Sort, List.Sort (in-place) and LINQ OrderBy (returns new).
// IComparable<T> = Java Comparable<T>; Comparison<T> = Java Comparator<T>.
// LINQ ThenBy handles multi-key sorting cleanly.
//
// Run: dotnet-script CL10_Sorting.cs

using System;
using System.Collections.Generic;
using System.Linq;

class CL10_Sorting
{
    class Person : IComparable<Person>
    {
        public string Name { get; }
        public int Age { get; }
        public Person(string name, int age) { Name = name; Age = age; }

        // Natural ordering: by age
        public int CompareTo(Person other) => this.Age.CompareTo(other.Age);
        public override string ToString() => $"{Name}({Age})";
    }

    static void Main()
    {
        Console.WriteLine("=== 1. BASIC SORT ===\n");

        int[] arr = { 5, 2, 8, 1, 9, 3 };
        Array.Sort(arr);    // in-place
        Console.WriteLine("Array.Sort: " + string.Join(", ", arr));

        var list = new List<string> { "banana", "apple", "cherry", "date" };
        list.Sort();        // in-place alphabetical
        Console.WriteLine("List.Sort:  " + string.Join(", ", list));

        // LINQ: returns new sorted sequence (original unchanged)
        var sortedNew = list.OrderBy(s => s).ToList();
        Console.WriteLine("OrderBy:    " + string.Join(", ", sortedNew));

        Console.WriteLine("\n=== 2. CUSTOM COMPARATOR ===\n");

        list.Sort((a, b) => a.Length.CompareTo(b.Length));    // by length
        Console.WriteLine("by length:       " + string.Join(", ", list));

        var descLength = list.OrderByDescending(w => w.Length).ToList();
        Console.WriteLine("by length desc:  " + string.Join(", ", descLength));

        Console.WriteLine("\n=== 3. ICOMPARABLE (natural ordering) ===\n");

        var people = new List<Person>
        {
            new Person("Charlie", 30),
            new Person("Alice",   25),
            new Person("Dave",    25),
            new Person("Bob",     35)
        };

        people.Sort();   // uses Person.CompareTo (age)
        Console.WriteLine("IComparable: " + string.Join(", ", people));

        Console.WriteLine("\n=== 4. MULTI-KEY SORT (LINQ) ===\n");

        var byAgeThenName = people
            .OrderBy(p => p.Age)
            .ThenBy(p => p.Name)
            .ToList();
        Console.WriteLine("age then name: " + string.Join(", ", byAgeThenName));

        var byNameDescAge = people
            .OrderBy(p => p.Name)
            .ThenByDescending(p => p.Age)
            .ToList();
        Console.WriteLine("name, age desc:" + string.Join(", ", byNameDescAge));

        Console.WriteLine("\n=== 5. COMPARISON<T> DELEGATE ===\n");

        // Comparison<T> is like Java's Comparator as a lambda
        Comparison<Person> byAgeDesc = (a, b) => b.Age.CompareTo(a.Age);
        people.Sort(byAgeDesc);
        Console.WriteLine("age desc: " + string.Join(", ", people));

        Console.WriteLine("\n=== 6. ARRAY.BINARYSEARCH ===\n");

        int[] sorted = { 1, 3, 5, 7, 9, 11 };
        int idx = Array.BinarySearch(sorted, 7);
        Console.WriteLine($"BinarySearch(7):  {idx}");

        int notFound = Array.BinarySearch(sorted, 6);
        Console.WriteLine($"BinarySearch(6):  {notFound} (negative = not found)");

        // List<T> also has BinarySearch
        var sortedList = new List<int> { 1, 3, 5, 7, 9 };
        Console.WriteLine($"List BinarySearch(5): {sortedList.BinarySearch(5)}");

        Console.WriteLine("\n=== KEY DIFFERENCES FROM JAVA ===\n");
        Console.WriteLine("Java Comparable<T>    → C# IComparable<T>");
        Console.WriteLine("Java Comparator<T>    → C# Comparison<T> delegate or IComparer<T>");
        Console.WriteLine("Java Collections.sort → C# list.Sort() or LINQ OrderBy");
        Console.WriteLine("Java stream.sorted()  → C# LINQ OrderBy (returns new)");
        Console.WriteLine("Java .thenComparing() → C# LINQ .ThenBy()");
    }
}
