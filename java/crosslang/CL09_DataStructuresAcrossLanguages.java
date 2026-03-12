package crosslang;

import java.util.*;

/**
 * CL09: Data Structures Across Languages
 *
 * Java       → Collections framework: ArrayList, LinkedList, HashMap, HashSet, Stack, Queue
 * Python     → Built-ins: list, dict, set, tuple; collections module
 * JavaScript → Array, Object, Map, Set (ES6+)
 * TypeScript → Same as JS with typed generics: Array<T>, Map<K,V>, Set<T>
 * C#         → List<T>, Dictionary<K,V>, HashSet<T>, Stack<T>, Queue<T>
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL09_DataStructuresAcrossLanguages
 */
public class CL09_DataStructuresAcrossLanguages {

    public static void main(String[] args) {

        System.out.println("=== 1. DYNAMIC ARRAY / LIST ===\n");

        // JAVA: ArrayList
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.remove("banana");
        System.out.println("ArrayList: " + list);
        System.out.println("Size: " + list.size() + ", Get(0): " + list.get(0));

        /*
         * PYTHON: list (built-in, most common)
         *   lst = ["apple", "banana", "cherry"]
         *   lst.append("date")
         *   lst.remove("banana")
         *   lst[0]          # "apple"
         *   len(lst)        # 3
         *   lst[-1]         # last element (negative indexing!)
         *   lst[1:3]        # slicing — no Java equivalent
         *
         * JAVASCRIPT: Array
         *   const arr = ["apple", "banana", "cherry"];
         *   arr.push("date");
         *   arr.splice(arr.indexOf("banana"), 1);
         *   arr[0];             // "apple"
         *   arr.length;         // property, not method
         *   arr.at(-1);         // last element (ES2022)
         *
         * TYPESCRIPT:
         *   const arr: string[] = ["apple", "banana"];   // or Array<string>
         *   arr.push("cherry");
         *
         * C#:
         *   var list = new List<string> { "apple", "banana", "cherry" };
         *   list.Add("date");
         *   list.Remove("banana");
         *   list[0];            // indexer syntax
         *   list.Count;         // property (not .size() like Java)
         */

        System.out.println("\n=== 2. HASH MAP / DICTIONARY ===\n");

        // JAVA: HashMap
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        System.out.println("HashMap: " + scores);
        System.out.println("Alice's score: " + scores.get("Alice"));
        System.out.println("Contains Bob: " + scores.containsKey("Bob"));

        // JAVA: iterate entries
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        /*
         * PYTHON: dict (built-in)
         *   scores = {"Alice": 95, "Bob": 87, "Charlie": 92}
         *   scores["Alice"]      # 95
         *   scores.get("Dave", 0) # 0 (default, no KeyError)
         *   "Bob" in scores      # True
         *   for key, val in scores.items():   # iterate
         *       print(f"{key} -> {val}")
         *
         * JAVASCRIPT: Object or Map
         *   // Object (simple, keys are strings/symbols):
         *   const scores = { Alice: 95, Bob: 87 };
         *   scores["Alice"];     // 95
         *   "Bob" in scores;     // true
         *
         *   // Map (ES6, keys can be any type):
         *   const map = new Map([["Alice", 95], ["Bob", 87]]);
         *   map.get("Alice");    // 95
         *   map.has("Bob");      // true
         *   for (const [k, v] of map) { ... }
         *
         * TYPESCRIPT:
         *   const scores: Record<string, number> = { Alice: 95 };
         *   const map = new Map<string, number>([["Alice", 95]]);
         *
         * C#:
         *   var scores = new Dictionary<string, int> { ["Alice"] = 95, ["Bob"] = 87 };
         *   scores["Alice"];             // 95
         *   scores.ContainsKey("Bob");   // true
         *   foreach (var kvp in scores)  // KeyValuePair<K,V>
         *       Console.WriteLine($"{kvp.Key} -> {kvp.Value}");
         */

        System.out.println("\n=== 3. SET ===\n");

        // JAVA: HashSet — no duplicates, unordered
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c", "a", "b"));
        System.out.println("HashSet (no dupes): " + set);
        set.add("d");
        System.out.println("Contains 'a': " + set.contains("a"));

        /*
         * PYTHON: set
         *   s = {"a", "b", "c", "a", "b"}   # dupes removed automatically
         *   s.add("d")
         *   "a" in s      # True
         *   s1 & s2       # intersection
         *   s1 | s2       # union
         *   s1 - s2       # difference
         *
         * JAVASCRIPT: Set (ES6)
         *   const s = new Set(["a", "b", "c", "a"]);
         *   s.add("d");
         *   s.has("a");   // true
         *   s.size;       // 4
         *
         * TYPESCRIPT:
         *   const s = new Set<string>(["a", "b"]);
         *
         * C#:
         *   var s = new HashSet<string> { "a", "b", "c" };
         *   s.Add("d");
         *   s.Contains("a");     // true
         *   s.IntersectWith(other);
         */

        System.out.println("\n=== 4. STACK & QUEUE ===\n");

        // JAVA: Deque used as Stack
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); stack.push(2); stack.push(3);
        System.out.println("Stack peek: " + stack.peek() + ", pop: " + stack.pop());

        // JAVA: Queue (FIFO)
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1); queue.offer(2); queue.offer(3);
        System.out.println("Queue peek: " + queue.peek() + ", poll: " + queue.poll());

        /*
         * PYTHON:
         *   # Stack: use list
         *   stack = []; stack.append(1); stack.append(2)
         *   stack.pop()        # LIFO
         *
         *   # Queue: use collections.deque (thread-safe)
         *   from collections import deque
         *   q = deque([1, 2, 3])
         *   q.appendleft(0)
         *   q.pop()            # right end
         *   q.popleft()        # left end (FIFO)
         *
         * JAVASCRIPT:
         *   // Stack: use Array
         *   const stack = []; stack.push(1); stack.pop();    // LIFO
         *   // Queue: use Array (less efficient for large queues)
         *   const q = []; q.push(1); q.shift();             // FIFO (shift is O(n)!)
         *
         * C#:
         *   var stack = new Stack<int>(); stack.Push(1); stack.Pop();
         *   var queue = new Queue<int>(); queue.Enqueue(1); queue.Dequeue();
         */

        System.out.println("\n=== 5. PRIORITY QUEUE ===\n");

        // JAVA: PriorityQueue — min-heap by default
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5); pq.offer(1); pq.offer(3);
        System.out.println("PriorityQueue poll order (min first): " + pq.poll() + ", " + pq.poll() + ", " + pq.poll());

        /*
         * PYTHON:
         *   import heapq
         *   pq = [5, 1, 3]
         *   heapq.heapify(pq)        # in-place min-heap
         *   heapq.heappop(pq)        # 1 (min)
         *   # Max-heap: negate values
         *   heapq.heappush(pq, -10)
         *
         * JAVASCRIPT: no built-in — implement manually or use library
         *
         * TYPESCRIPT: same — no built-in PriorityQueue
         *
         * C#:
         *   // .NET 6+:
         *   var pq = new PriorityQueue<string, int>();
         *   pq.Enqueue("task", 3);   // (element, priority)
         *   pq.Dequeue();
         */
    }
}
