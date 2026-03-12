# CL09: Data Structures Across Languages

| Language | Collections |
|----------|-------------|
| Java | `ArrayList`, `LinkedList`, `HashMap`, `HashSet`, `ArrayDeque`, `PriorityQueue` |
| Python | `list`, `dict`, `set`, `tuple`, `collections.deque` |
| JavaScript | `Array`, `Object`, `Map`, `Set` (ES6+) |
| TypeScript | Same as JS: `Array<T>`, `Map<K,V>`, `Set<T>` |
| C# | `List<T>`, `Dictionary<K,V>`, `HashSet<T>`, `Stack<T>`, `Queue<T>` |

---

## 1. Dynamic Array / List

**Java**
```java
List<String> list = new ArrayList<>();
list.add("apple");
list.remove("banana");
list.get(0);     // "apple"
list.size();     // method
```

**Python**
```python
lst = ["apple", "banana", "cherry"]
lst.append("date")
lst.remove("banana")
lst[0]       # "apple"
lst[-1]      # last element (negative indexing)
lst[1:3]     # slicing — no Java equivalent
len(lst)     # function, not method
```

**JavaScript**
```javascript
const arr = ["apple", "banana", "cherry"];
arr.push("date");
arr.splice(arr.indexOf("banana"), 1);
arr[0];         // "apple"
arr.length;     // property, not method
arr.at(-1);     // last element (ES2022)
```

**TypeScript**
```typescript
const arr: string[] = ["apple", "banana"];  // or Array<string>
arr.push("cherry");
```

**C#**
```csharp
var list = new List<string> { "apple", "banana", "cherry" };
list.Add("date");
list.Remove("banana");
list[0];      // indexer
list.Count;   // property (not .size() like Java)
```

---

## 2. HashMap / Dictionary

**Java**
```java
Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 95);
scores.get("Alice");           // 95
scores.containsKey("Bob");     // true/false
for (Map.Entry<String, Integer> e : scores.entrySet())
    System.out.println(e.getKey() + " -> " + e.getValue());
```

**Python**
```python
scores = {"Alice": 95, "Bob": 87}
scores["Alice"]             # 95
scores.get("Dave", 0)       # 0 default — no KeyError
"Bob" in scores             # True
for key, val in scores.items():
    print(f"{key} -> {val}")
```

**JavaScript**
```javascript
// Object (string/symbol keys):
const scores = { Alice: 95, Bob: 87 };
scores["Alice"];  // 95
"Bob" in scores;  // true

// Map (any key type, ES6):
const map = new Map([["Alice", 95]]);
map.get("Alice"); // 95
map.has("Bob");   // true
for (const [k, v] of map) { ... }
```

**TypeScript**
```typescript
const scores: Record<string, number> = { Alice: 95 };
const map = new Map<string, number>([["Alice", 95]]);
```

**C#**
```csharp
var scores = new Dictionary<string, int> { ["Alice"] = 95, ["Bob"] = 87 };
scores["Alice"];            // 95
scores.ContainsKey("Bob");  // true
foreach (var kvp in scores)
    Console.WriteLine($"{kvp.Key} -> {kvp.Value}");
```

---

## 3. Set

**Java**
```java
Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c", "a")); // dupes removed
set.add("d");
set.contains("a"); // true
```

**Python**
```python
s = {"a", "b", "c", "a"}  # dupes removed automatically
s.add("d")
"a" in s      # True
s1 & s2       # intersection
s1 | s2       # union
s1 - s2       # difference
```

**JavaScript**
```javascript
const s = new Set(["a", "b", "c", "a"]);
s.add("d");
s.has("a");  // true
s.size;      // 4
```

**C#**
```csharp
var s = new HashSet<string> { "a", "b", "c" };
s.Add("d");
s.Contains("a");    // true
s.IntersectWith(other);
```

---

## 4. Stack & Queue

**Java**
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1); stack.push(2);
stack.peek(); stack.pop(); // LIFO

Queue<Integer> queue = new LinkedList<>();
queue.offer(1); queue.offer(2);
queue.peek(); queue.poll(); // FIFO
```

**Python**
```python
# Stack: list
stack = []; stack.append(1); stack.pop()

# Queue: deque (thread-safe)
from collections import deque
q = deque([1, 2, 3])
q.appendleft(0); q.pop(); q.popleft()
```

**JavaScript**
```javascript
// Stack: Array
const stack = []; stack.push(1); stack.pop();
// Queue: Array (shift is O(n) — not ideal for large queues)
const q = []; q.push(1); q.shift();
```

**C#**
```csharp
var stack = new Stack<int>(); stack.Push(1); stack.Pop();
var queue = new Queue<int>(); queue.Enqueue(1); queue.Dequeue();
```

---

## 5. Priority Queue

**Java**
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // min-heap
pq.offer(5); pq.offer(1); pq.offer(3);
pq.poll(); // 1 (min first)
```

**Python**
```python
import heapq
pq = [5, 1, 3]
heapq.heapify(pq)       # in-place min-heap
heapq.heappop(pq)       # 1
# Max-heap: negate values → heapq.heappush(pq, -10)
```

**C# (.NET 6+)**
```csharp
var pq = new PriorityQueue<string, int>();
pq.Enqueue("task", 3); // (element, priority)
pq.Dequeue();
```

**JavaScript / TypeScript** — no built-in PriorityQueue; implement manually or use a library.
