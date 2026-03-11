package basics;

import java.util.*;

/**
 * Topic 8: Collections Framework
 *
 * Covers List, Set, Map, Queue, and their common implementations.
 * Understanding when to use which collection is key!
 */
public class T08_CollectionsBasics {

    public static void main(String[] args) {

        System.out.println("=== 1. COLLECTIONS HIERARCHY ===\n");
        System.out.println("Iterable");
        System.out.println("  └── Collection");
        System.out.println("        ├── List (ordered, allows duplicates)");
        System.out.println("        │     ├── ArrayList  (fast random access)");
        System.out.println("        │     └── LinkedList (fast insert/remove)");
        System.out.println("        ├── Set (no duplicates)");
        System.out.println("        │     ├── HashSet    (unordered, fastest)");
        System.out.println("        │     ├── LinkedHashSet (insertion order)");
        System.out.println("        │     └── TreeSet    (sorted)");
        System.out.println("        └── Queue (FIFO)");
        System.out.println("              ├── LinkedList");
        System.out.println("              └── PriorityQueue (sorted)");
        System.out.println("Map (key-value, NOT part of Collection)");
        System.out.println("  ├── HashMap    (unordered, fastest)");
        System.out.println("  ├── LinkedHashMap (insertion order)");
        System.out.println("  └── TreeMap    (sorted by key)");

        System.out.println("\n=== 2. ARRAYLIST ===\n");

        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Banana"); // Duplicates OK!
        System.out.println("List: " + fruits);
        System.out.println("Size: " + fruits.size());
        System.out.println("Get index 1: " + fruits.get(1));
        System.out.println("Contains 'Apple': " + fruits.contains("Apple"));
        System.out.println("Index of 'Banana': " + fruits.indexOf("Banana"));

        fruits.set(0, "Avocado"); // Replace
        fruits.remove("Banana");  // Removes first occurrence
        System.out.println("After set & remove: " + fruits);

        // Sorting
        Collections.sort(fruits);
        System.out.println("Sorted: " + fruits);

        // Iteration
        System.out.print("For-each: ");
        for (String f : fruits) {
            System.out.print(f + " ");
        }
        System.out.println();

        System.out.println("\n=== 3. HASHSET ===\n");

        Set<Integer> numbers = new HashSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(2); // Duplicate ignored!
        numbers.add(5); // Duplicate ignored!
        System.out.println("Set: " + numbers + " (no duplicates, unordered)");
        System.out.println("Contains 8: " + numbers.contains(8));
        System.out.println("Size: " + numbers.size());

        // TreeSet: sorted automatically
        Set<Integer> sorted = new TreeSet<>(numbers);
        System.out.println("TreeSet (sorted): " + sorted);

        // LinkedHashSet: maintains insertion order
        Set<String> ordered = new LinkedHashSet<>();
        ordered.add("C");
        ordered.add("A");
        ordered.add("B");
        System.out.println("LinkedHashSet (insertion order): " + ordered);

        System.out.println("\n=== 4. HASHMAP ===\n");

        Map<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 30);
        ages.put("Bob", 25);
        ages.put("Charlie", 35);
        ages.put("Bob", 26); // Overwrites previous value!
        System.out.println("Map: " + ages);
        System.out.println("Bob's age: " + ages.get("Bob"));
        System.out.println("Contains 'Alice': " + ages.containsKey("Alice"));
        System.out.println("Contains value 35: " + ages.containsValue(35));
        System.out.println("Size: " + ages.size());

        // getOrDefault
        System.out.println("Dave's age: " + ages.getOrDefault("Dave", 0));

        // Iteration
        System.out.println("\nIterating Map:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // Keys and values
        System.out.println("Keys: " + ages.keySet());
        System.out.println("Values: " + ages.values());

        System.out.println("\n=== 5. QUEUE ===\n");

        Queue<String> queue = new LinkedList<>();
        queue.offer("First");  // add to end
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue);
        System.out.println("Peek (front): " + queue.peek());   // Look at front
        System.out.println("Poll (remove): " + queue.poll());  // Remove from front
        System.out.println("After poll: " + queue);

        // PriorityQueue: elements come out in sorted order
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(30);
        pq.offer(10);
        pq.offer(20);
        System.out.print("PriorityQueue poll order: ");
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " "); // 10 20 30
        }
        System.out.println();

        System.out.println("\n=== 6. STACK (use Deque instead) ===\n");

        Deque<String> stack = new ArrayDeque<>();
        stack.push("Bottom");
        stack.push("Middle");
        stack.push("Top");
        System.out.println("Stack: " + stack);
        System.out.println("Peek: " + stack.peek());
        System.out.println("Pop: " + stack.pop());
        System.out.println("After pop: " + stack);

        System.out.println("\n=== 7. COLLECTIONS UTILITY METHODS ===\n");

        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Original: " + nums);

        Collections.sort(nums);
        System.out.println("Sorted: " + nums);

        Collections.reverse(nums);
        System.out.println("Reversed: " + nums);

        Collections.shuffle(nums);
        System.out.println("Shuffled: " + nums);

        System.out.println("Min: " + Collections.min(nums));
        System.out.println("Max: " + Collections.max(nums));
        System.out.println("Frequency of 5: " + Collections.frequency(nums, 5));

        // Unmodifiable collection
        List<String> immutable = Collections.unmodifiableList(fruits);
        try {
            immutable.add("New");
        } catch (UnsupportedOperationException e) {
            System.out.println("Can't modify unmodifiable list!");
        }

        System.out.println("\n=== 8. WHEN TO USE WHAT? ===\n");
        System.out.println("Need ordered + duplicates + fast access by index? -> ArrayList");
        System.out.println("Need ordered + frequent insert/remove?             -> LinkedList");
        System.out.println("Need unique elements?                              -> HashSet");
        System.out.println("Need unique + sorted?                              -> TreeSet");
        System.out.println("Need key-value pairs?                              -> HashMap");
        System.out.println("Need key-value + sorted keys?                      -> TreeMap");
        System.out.println("Need FIFO processing?                              -> LinkedList/Queue");
        System.out.println("Need LIFO processing (stack)?                      -> ArrayDeque");
        System.out.println("Need priority-based processing?                    -> PriorityQueue");
    }
}
