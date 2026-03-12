package crosslang;

import java.util.*;

/**
 * CL10: Sorting & Searching Across Languages
 *
 * Java       → Arrays.sort, Collections.sort, Comparable, Comparator, Stream.sorted
 * Python     → sorted(), list.sort(), key= parameter, operator module
 * JavaScript → Array.prototype.sort(), custom comparator
 * TypeScript → Same as JS with typed comparator functions
 * C#         → Array.Sort, List.Sort, LINQ OrderBy, IComparable, IComparer
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL10_SortingAcrossLanguages
 */
public class CL10_SortingAcrossLanguages {

    static class Person implements Comparable<Person> {
        String name;
        int age;
        Person(String name, int age) { this.name = name; this.age = age; }

        @Override
        public int compareTo(Person other) {
            return Integer.compare(this.age, other.age); // sort by age ascending
        }

        @Override
        public String toString() { return name + "(" + age + ")"; }
    }

    public static void main(String[] args) {

        System.out.println("=== 1. BASIC SORT ===\n");

        // JAVA: Arrays.sort (in-place, Dual-Pivot Quicksort for primitives)
        int[] arr = {5, 2, 8, 1, 9, 3};
        Arrays.sort(arr);
        System.out.println("Arrays.sort: " + Arrays.toString(arr));

        // JAVA: Collections.sort (in-place, TimSort for objects)
        List<String> words = new ArrayList<>(Arrays.asList("banana", "apple", "cherry", "date"));
        Collections.sort(words);
        System.out.println("Collections.sort: " + words);

        /*
         * PYTHON:
         *   nums = [5, 2, 8, 1, 9, 3]
         *   nums.sort()                  # in-place (modifies list)
         *   sorted_nums = sorted(nums)   # returns new list (original unchanged)
         *
         *   words = ["banana", "apple", "cherry"]
         *   words.sort()                 # in-place alphabetical
         *
         * JAVASCRIPT:
         *   const arr = [5, 2, 8, 1];
         *   arr.sort();                  // DANGER: sorts as strings by default!
         *   // [1, 5, 2, 8] -> [1, 2, 5, 8] only if you provide comparator:
         *   arr.sort((a, b) => a - b);   // numeric sort (ascending)
         *
         * TYPESCRIPT:
         *   const arr: number[] = [5, 2, 8, 1];
         *   arr.sort((a, b) => a - b);   // same as JS, typed
         *
         * C#:
         *   int[] arr = { 5, 2, 8, 1 };
         *   Array.Sort(arr);             // in-place
         *   var list = new List<string> { "banana", "apple" };
         *   list.Sort();                 // in-place
         */

        System.out.println("\n=== 2. SORT WITH CUSTOM COMPARATOR ===\n");

        List<String> byLength = new ArrayList<>(Arrays.asList("banana", "fig", "apple", "kiwi"));

        // JAVA: Comparator.comparingInt
        byLength.sort(Comparator.comparingInt(String::length));
        System.out.println("Sort by length: " + byLength);

        // JAVA: reverse order
        byLength.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println("Sort by length desc: " + byLength);

        /*
         * PYTHON:
         *   words.sort(key=len)                        # sort by length
         *   words.sort(key=len, reverse=True)          # descending
         *   words.sort(key=lambda w: (len(w), w))      # multi-key: length then alpha
         *
         * JAVASCRIPT:
         *   words.sort((a, b) => a.length - b.length);          // by length asc
         *   words.sort((a, b) => b.length - a.length);          // by length desc
         *
         * TYPESCRIPT:
         *   words.sort((a: string, b: string) => a.length - b.length);
         *
         * C#:
         *   list.Sort((a, b) => a.Length.CompareTo(b.Length));       // lambda comparator
         *   var sorted = list.OrderBy(w => w.Length).ToList();       // LINQ (returns new)
         *   var sortedDesc = list.OrderByDescending(w => w.Length).ToList();
         */

        System.out.println("\n=== 3. COMPARABLE (natural ordering) ===\n");

        List<Person> people = new ArrayList<>();
        people.add(new Person("Charlie", 30));
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 35));

        Collections.sort(people); // uses Person.compareTo (age)
        System.out.println("Sorted by age (Comparable): " + people);

        /*
         * PYTHON:
         *   class Person:
         *       def __init__(self, name, age): self.name, self.age = name, age
         *       def __lt__(self, other): return self.age < other.age   # enables sort()
         *       def __repr__(self): return f"{self.name}({self.age})"
         *   people.sort()   # uses __lt__
         *
         * TYPESCRIPT:
         *   interface Person { name: string; age: number; }
         *   people.sort((a, b) => a.age - b.age);  // no interface method — always use comparator
         *
         * C#:
         *   class Person : IComparable<Person> {
         *       public int CompareTo(Person other) => this.Age.CompareTo(other.Age);
         *   }
         *   list.Sort();  // uses IComparable
         */

        System.out.println("\n=== 4. MULTI-KEY SORT ===\n");

        // JAVA: sort by age, then by name
        people.add(new Person("Dave", 25)); // same age as Alice
        people.sort(Comparator.comparingInt((Person p) -> p.age)
                               .thenComparing(p -> p.name));
        System.out.println("Sort by age then name: " + people);

        /*
         * PYTHON:
         *   people.sort(key=lambda p: (p.age, p.name))  # tuple key — clean!
         *
         * JAVASCRIPT:
         *   people.sort((a, b) => a.age - b.age || a.name.localeCompare(b.name));
         *
         * TYPESCRIPT:
         *   people.sort((a, b) => a.age - b.age || a.name.localeCompare(b.name));
         *
         * C# (LINQ):
         *   var sorted = people.OrderBy(p => p.Age).ThenBy(p => p.Name).ToList();
         */

        System.out.println("\n=== 5. BINARY SEARCH ===\n");

        // JAVA: Arrays.binarySearch (array must be sorted first!)
        int[] sorted = {1, 3, 5, 7, 9, 11};
        int idx = Arrays.binarySearch(sorted, 7);
        System.out.println("binarySearch(7): index = " + idx);
        System.out.println("binarySearch(6): " + Arrays.binarySearch(sorted, 6) + " (negative = not found)");

        /*
         * PYTHON:
         *   import bisect
         *   lst = [1, 3, 5, 7, 9]
         *   bisect.bisect_left(lst, 7)    # 3 (index where 7 is/would be)
         *   bisect.insort(lst, 6)         # insert 6 in sorted position
         *
         * JAVASCRIPT: no built-in — implement manually:
         *   function binarySearch(arr, target) {
         *     let lo = 0, hi = arr.length - 1;
         *     while (lo <= hi) {
         *       const mid = (lo + hi) >> 1;
         *       if (arr[mid] === target) return mid;
         *       arr[mid] < target ? (lo = mid + 1) : (hi = mid - 1);
         *     }
         *     return -1;
         *   }
         *
         * TYPESCRIPT: same as JS with types:
         *   function binarySearch(arr: number[], target: number): number { ... }
         *
         * C#:
         *   int[] arr = { 1, 3, 5, 7, 9 };
         *   int idx = Array.BinarySearch(arr, 7);   // same semantics as Java
         *   int idx2 = list.BinarySearch(7);        // List<T> method
         */

        System.out.println("\n=== 6. JS GOTCHA — DEFAULT SORT ===\n");

        // JAVA is safe — Arrays.sort(int[]) is always numeric
        int[] nums = {10, 9, 2, 1, 100};
        Arrays.sort(nums);
        System.out.println("Java sort [10,9,2,1,100]: " + Arrays.toString(nums)); // [1,2,9,10,100]

        /*
         * JAVASCRIPT GOTCHA — default sort converts to string!
         *   [10, 9, 2, 1, 100].sort()
         *   → [1, 10, 100, 2, 9]   ← WRONG for numbers!
         *
         *   // Always provide comparator for numbers:
         *   [10, 9, 2, 1, 100].sort((a, b) => a - b)
         *   → [1, 2, 9, 10, 100]   ← correct
         *
         * PYTHON and C# sort numerically by default — no gotcha.
         */
    }
}
