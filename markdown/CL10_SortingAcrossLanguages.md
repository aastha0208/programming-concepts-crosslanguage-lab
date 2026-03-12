# CL10: Sorting & Searching Across Languages

| Language | Sort API |
|----------|----------|
| Java | `Arrays.sort`, `Collections.sort`, `Comparator`, `Stream.sorted` |
| Python | `sorted()` (new list), `list.sort()` (in-place), `key=` param |
| JavaScript | `Array.sort()` — ⚠️ sorts as strings by default! |
| TypeScript | Same as JS with typed comparator |
| C# | `Array.Sort`, `List.Sort`, LINQ `OrderBy`/`ThenBy` |

---

## 1. Basic Sort

**Java**
```java
int[] arr = {5, 2, 8, 1};
Arrays.sort(arr);  // in-place, Dual-Pivot Quicksort for primitives

List<String> words = new ArrayList<>(Arrays.asList("banana", "apple"));
Collections.sort(words);  // in-place, TimSort for objects
```

**Python**
```python
nums = [5, 2, 8, 1]
nums.sort()               # in-place (modifies list)
sorted_nums = sorted(nums) # returns new list (original unchanged)
```

**JavaScript** ⚠️
```javascript
// GOTCHA: default sort converts to string!
[10, 9, 2, 1, 100].sort()           // → [1, 10, 100, 2, 9]  WRONG for numbers!
[10, 9, 2, 1, 100].sort((a, b) => a - b)  // → [1, 2, 9, 10, 100]  correct
```

**TypeScript**
```typescript
const arr: number[] = [5, 2, 8, 1];
arr.sort((a, b) => a - b);  // always provide comparator for numbers
```

**C#**
```csharp
int[] arr = { 5, 2, 8, 1 };
Array.Sort(arr);  // in-place
var list = new List<string> { "banana", "apple" };
list.Sort();      // in-place
```

---

## 2. Custom Comparator

**Java**
```java
// Sort by string length:
words.sort(Comparator.comparingInt(String::length));
// Descending:
words.sort(Comparator.comparingInt(String::length).reversed());
```

**Python**
```python
words.sort(key=len)                  # sort by length
words.sort(key=len, reverse=True)    # descending
words.sort(key=lambda w: (len(w), w)) # multi-key: length then alpha
```

**JavaScript**
```javascript
words.sort((a, b) => a.length - b.length);          // ascending by length
words.sort((a, b) => b.length - a.length);          // descending
```

**C#**
```csharp
list.Sort((a, b) => a.Length.CompareTo(b.Length));      // lambda
var sorted = list.OrderBy(w => w.Length).ToList();       // LINQ (returns new)
var desc   = list.OrderByDescending(w => w.Length).ToList();
```

---

## 3. Comparable / Natural Ordering

**Java**
```java
class Person implements Comparable<Person> {
    int age;
    public int compareTo(Person other) { return Integer.compare(this.age, other.age); }
}
Collections.sort(people); // uses compareTo
```

**Python**
```python
class Person:
    def __lt__(self, other): return self.age < other.age
people.sort()  # uses __lt__
```

**C#**
```csharp
class Person : IComparable<Person> {
    public int CompareTo(Person other) => this.Age.CompareTo(other.Age);
}
list.Sort(); // uses IComparable
```

**TypeScript / JavaScript** — no interface method; always use a comparator function.

---

## 4. Multi-Key Sort

**Java**
```java
people.sort(Comparator.comparingInt((Person p) -> p.age)
                       .thenComparing(p -> p.name));
```

**Python** *(most concise)*
```python
people.sort(key=lambda p: (p.age, p.name))  # tuple key
```

**JavaScript / TypeScript**
```javascript
people.sort((a, b) => a.age - b.age || a.name.localeCompare(b.name));
```

**C# (LINQ)**
```csharp
var sorted = people.OrderBy(p => p.Age).ThenBy(p => p.Name).ToList();
```

---

## 5. Binary Search

**Java**
```java
int[] sorted = {1, 3, 5, 7, 9};
Arrays.binarySearch(sorted, 7);  // returns index (3)
Arrays.binarySearch(sorted, 6);  // returns negative → not found
```

**Python**
```python
import bisect
bisect.bisect_left(lst, 7)   # index where 7 is/would be
bisect.insort(lst, 6)        # insert 6 in sorted position
```

**JavaScript / TypeScript** — no built-in; implement manually:
```javascript
function binarySearch(arr, target) {
    let lo = 0, hi = arr.length - 1;
    while (lo <= hi) {
        const mid = (lo + hi) >> 1;
        if (arr[mid] === target) return mid;
        arr[mid] < target ? (lo = mid + 1) : (hi = mid - 1);
    }
    return -1;
}
```

**C#**
```csharp
int idx = Array.BinarySearch(arr, 7);   // same semantics as Java
int idx2 = list.BinarySearch(7);        // List<T> method
```
