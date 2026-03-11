package crosslang;

import java.util.*;

/**
 * CL03: Collections Across Languages
 *
 * Java    → Generics-typed collections, rich Collections framework
 * Python  → Built-in list, dict, set, tuple
 * JS      → Array, Object, Map, Set (ES6+)
 * C#      → Generic collections in System.Collections.Generic (nearly identical to Java)
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL03_CollectionsAcrossLanguages
 */
public class CL03_CollectionsAcrossLanguages {

    public static void main(String[] args) {

        System.out.println("=== 1. DYNAMIC LISTS ===\n");

        // JAVA: ArrayList<T>
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Banana"); // duplicates OK
        System.out.println("Java ArrayList: " + fruits);
        System.out.println("  get(1):       " + fruits.get(1));
        System.out.println("  size():       " + fruits.size());
        System.out.println("  contains():   " + fruits.contains("Apple"));
        fruits.remove("Banana");  // removes first occurrence
        System.out.println("  after remove: " + fruits);

        /*
         * PYTHON: list (built-in, no import needed)
         *   fruits = ["Apple", "Banana", "Cherry", "Banana"]
         *   fruits[1]               # get(1) — indexing
         *   len(fruits)             # size()
         *   "Apple" in fruits       # contains()
         *   fruits.append("Date")   # add()
         *   fruits.remove("Banana") # remove() — removes first occurrence
         *   fruits.pop()            # remove last element
         *   fruits.pop(0)           # remove by index
         *   fruits[1:3]             # slicing — no Java equivalent directly
         *
         * JAVASCRIPT: Array
         *   const fruits = ["Apple", "Banana", "Cherry"];
         *   fruits[1]               // get(1)
         *   fruits.length           // size() — property, not method!
         *   fruits.includes("Apple") // contains()
         *   fruits.push("Date")     // add to end
         *   fruits.splice(1, 1)     // remove 1 element at index 1
         *   fruits.filter(f => f !== "Banana") // returns new filtered array
         *   fruits.map(f => f.toUpperCase())   // transform elements
         *
         * C#: List<T> (identical to Java's ArrayList!)
         *   var fruits = new List<string>();
         *   fruits.Add("Apple");     // add() — capital A
         *   fruits[1]               // get(1) — indexer syntax
         *   fruits.Count            // size() — property, not method!
         *   fruits.Contains("Apple") // contains() — capital C
         *   fruits.Remove("Banana") // remove() — capital R
         *   fruits.RemoveAt(0)      // remove by index
         *   fruits.Sort()           // sort in place
         */

        System.out.println("\n=== 2. KEY-VALUE MAPS / DICTIONARIES ===\n");

        // JAVA: HashMap<K,V>
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("Bob", 90);  // overwrites previous value
        System.out.println("Java HashMap: " + scores);
        System.out.println("  get(Bob):         " + scores.get("Bob"));
        System.out.println("  getOrDefault:     " + scores.getOrDefault("Dave", 0));
        System.out.println("  containsKey:      " + scores.containsKey("Alice"));
        System.out.println("  keySet:           " + scores.keySet());
        for (Map.Entry<String, Integer> e : scores.entrySet()) {
            System.out.println("  " + e.getKey() + " -> " + e.getValue());
        }

        /*
         * PYTHON: dict (built-in)
         *   scores = {"Alice": 95, "Bob": 87, "Charlie": 92}
         *   scores["Bob"]              # get()
         *   scores.get("Dave", 0)      # getOrDefault()
         *   "Alice" in scores          # containsKey()
         *   scores.keys()              # keySet()
         *   scores.values()            # values()
         *   scores.items()             # entrySet()
         *   for k, v in scores.items():
         *       print(f"{k} -> {v}")
         *   scores["Bob"] = 90         # put() / overwrite
         *   del scores["Bob"]          # remove()
         *
         * JAVASCRIPT:
         *   // Option 1: Object (string keys only)
         *   const scores = { Alice: 95, Bob: 87 };
         *   scores["Bob"] / scores.Bob  // get
         *   "Alice" in scores           // containsKey
         *   Object.keys(scores)         // keySet
         *   Object.entries(scores)      // entrySet
         *
         *   // Option 2: Map (any key type, ordered)
         *   const map = new Map();
         *   map.set("Alice", 95);
         *   map.get("Alice");
         *   map.has("Alice");           // containsKey
         *   map.delete("Alice");
         *   for (const [k, v] of map) { console.log(k, v); }
         *
         * C#: Dictionary<K,V>
         *   var scores = new Dictionary<string, int>();
         *   scores["Alice"] = 95;           // put()
         *   scores.Add("Bob", 87);          // put() alternative (throws if key exists)
         *   scores["Bob"]                   // get() — throws KeyNotFoundException if missing!
         *   scores.GetValueOrDefault("Dave", 0)  // getOrDefault()
         *   scores.ContainsKey("Alice")     // containsKey()
         *   scores.Keys                     // keySet()
         *   foreach (var kvp in scores)
         *       Console.WriteLine($"{kvp.Key} -> {kvp.Value}");
         */

        System.out.println("\n=== 3. SETS (UNIQUE ELEMENTS) ===\n");

        // JAVA: HashSet<T>
        Set<String> tags = new HashSet<>();
        tags.add("java");
        tags.add("programming");
        tags.add("java");   // duplicate ignored!
        tags.add("coding");
        System.out.println("Java HashSet: " + tags + " (no duplicates)");
        System.out.println("  contains: " + tags.contains("java"));
        System.out.println("  size:     " + tags.size());

        // Set operations
        Set<Integer> setA = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> setB = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        Set<Integer> union = new HashSet<>(setA); union.addAll(setB);
        Set<Integer> intersection = new HashSet<>(setA); intersection.retainAll(setB);
        Set<Integer> difference = new HashSet<>(setA); difference.removeAll(setB);
        System.out.println("  union:        " + union);
        System.out.println("  intersection: " + intersection);
        System.out.println("  difference:   " + difference);

        /*
         * PYTHON: set (cleanest set operations of all languages)
         *   tags = {"java", "programming", "coding"}  # set literal
         *   tags = set()                # empty set (can't use {} — that's a dict!)
         *   tags.add("java")
         *   "java" in tags             # contains
         *   len(tags)                  # size
         *   setA | setB                # union
         *   setA & setB                # intersection
         *   setA - setB                # difference
         *   setA ^ setB                # symmetric difference
         *
         * JAVASCRIPT: Set (ES6+)
         *   const tags = new Set(["java", "programming", "java"]);
         *   tags.add("java");
         *   tags.has("java");          // contains()
         *   tags.size;                 // property!
         *   tags.delete("java");
         *   // No built-in set operations:
         *   const union = new Set([...setA, ...setB]);
         *   const intersection = new Set([...setA].filter(x => setB.has(x)));
         *
         * C#: HashSet<T> (nearly identical to Java)
         *   var tags = new HashSet<string> { "java", "programming" };
         *   tags.Add("java");
         *   tags.Contains("java")
         *   tags.Count                 // property!
         *   setA.UnionWith(setB)       // union (modifies in place)
         *   setA.IntersectWith(setB)   // intersection
         *   setA.ExceptWith(setB)      // difference
         */

        System.out.println("\n=== 4. QUEUES & STACKS ===\n");

        // JAVA: Queue (FIFO)
        Queue<String> queue = new LinkedList<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");
        System.out.println("Queue: " + queue);
        System.out.println("  poll (dequeue): " + queue.poll());
        System.out.println("  peek (front):   " + queue.peek());

        // JAVA: Stack via Deque (LIFO)
        Deque<String> stack = new ArrayDeque<>();
        stack.push("bottom");
        stack.push("middle");
        stack.push("top");
        System.out.println("Stack pop: " + stack.pop());

        /*
         * PYTHON:
         *   from collections import deque
         *   queue = deque(["first", "second"])
         *   queue.append("third")      # enqueue (right)
         *   queue.popleft()            # dequeue (left — FIFO)
         *   stack = []                 # list as stack
         *   stack.append("top")        # push
         *   stack.pop()                # pop
         *
         * JAVASCRIPT: Array acts as both queue and stack
         *   const queue = ["first", "second"];
         *   queue.push("third");       // enqueue
         *   queue.shift();            // dequeue (from front)
         *   const stack = [];
         *   stack.push("top");        // push
         *   stack.pop();              // pop
         *
         * C#: dedicated Queue<T> and Stack<T>
         *   var queue = new Queue<string>();
         *   queue.Enqueue("first");   // offer()
         *   queue.Dequeue();          // poll()
         *   queue.Peek();             // peek()
         *   var stack = new Stack<string>();
         *   stack.Push("top");        // push()
         *   stack.Pop();              // pop()
         *   stack.Peek();             // peek()
         */

        System.out.println("\n=== 5. SORTING ===\n");

        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        Collections.sort(nums);
        System.out.println("Sorted ascending:  " + nums);
        Collections.sort(nums, Collections.reverseOrder());
        System.out.println("Sorted descending: " + nums);

        /*
         * PYTHON:
         *   nums.sort()                   # in-place ascending
         *   nums.sort(reverse=True)       # in-place descending
         *   sorted(nums)                  # returns new list
         *   nums.sort(key=lambda x: -x)   # custom key
         *
         * JAVASCRIPT:
         *   nums.sort((a, b) => a - b);   // ascending (comparator required!)
         *   nums.sort((a, b) => b - a);   // descending
         *   // WARNING: [10, 9, 2].sort() → [10, 2, 9] (lexicographic!)
         *
         * C#:
         *   nums.Sort();                        // ascending
         *   nums.Sort((a, b) => b - a);         // descending with comparator
         *   var sorted = nums.OrderBy(x => x);  // LINQ: new sorted sequence
         */

        System.out.println("\n=== 6. WHEN TO USE WHAT? ===\n");

        System.out.println("Ordered + duplicates + fast index access?  ArrayList  | list       | Array        | List<T>");
        System.out.println("Unique elements only?                       HashSet    | set        | Set          | HashSet<T>");
        System.out.println("Key-value pairs?                            HashMap    | dict       | Map/Object   | Dictionary<K,V>");
        System.out.println("Sorted unique elements?                     TreeSet    | sorted()   | (manual)     | SortedSet<T>");
        System.out.println("Sorted key-value pairs?                     TreeMap    | dict+sort  | (manual)     | SortedDictionary<K,V>");
        System.out.println("FIFO queue?                                 LinkedList | deque      | Array.shift  | Queue<T>");
        System.out.println("LIFO stack?                                 ArrayDeque | list.pop   | Array.pop    | Stack<T>");
        System.out.println("Fixed-size, immutable list?                 Arrays.asList| tuple   | Object.freeze| (none built-in)");
    }
}
