"""
CL03: Collections in Python
Companion to CL03_CollectionsAcrossLanguages.java

Python has built-in list, dict, set, tuple — no imports needed.
Collections are dynamic and untyped.

Run: python3 CL03_Collections.py
"""

print("=== 1. LIST (Java's ArrayList) ===\n")

fruits = ["Apple", "Banana", "Cherry", "Banana"]   # duplicates OK
print(f"list: {fruits}")
print(f"  [1]:          {fruits[1]}")          # get(1)
print(f"  len():        {len(fruits)}")         # size()
print(f"  'Apple' in:   {'Apple' in fruits}")   # contains()
print(f"  index():      {fruits.index('Banana')}")  # indexOf()

fruits.append("Date")           # add() to end
fruits.insert(1, "Avocado")     # add(index, element)
fruits.remove("Banana")         # remove first occurrence
popped = fruits.pop()           # remove and return last
popped_idx = fruits.pop(0)      # remove and return at index
print(f"after operations: {fruits}")

# Slicing — Python's unique power
nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print(f"nums[2:5]:   {nums[2:5]}")   # subList(2, 5)
print(f"nums[-3:]:   {nums[-3:]}")   # last 3
print(f"nums[::2]:   {nums[::2]}")   # every other element
print(f"nums[::-1]:  {nums[::-1]}")  # reversed

# List comprehension — Python's superpower (no Java equivalent)
squares = [x**2 for x in range(1, 6)]
evens = [x for x in range(10) if x % 2 == 0]
print(f"squares: {squares}")
print(f"evens:   {evens}")

print("\n=== 2. DICT (Java's HashMap) ===\n")

scores = {"Alice": 95, "Bob": 87, "Charlie": 92}
scores["Bob"] = 90          # put() / overwrite
print(f"dict: {scores}")
print(f"  scores['Bob']:          {scores['Bob']}")         # get()
print(f"  scores.get('Dave', 0):  {scores.get('Dave', 0)}") # getOrDefault()
print(f"  'Alice' in scores:      {'Alice' in scores}")      # containsKey()
print(f"  scores.keys():          {list(scores.keys())}")
print(f"  scores.values():        {list(scores.values())}")

# Iterate entries
print("  Entries:")
for k, v in scores.items():                  # entrySet()
    print(f"    {k} -> {v}")

del scores["Bob"]                            # remove()
print(f"after del: {scores}")

# Dict comprehension
squared = {k: v**2 for k, v in {"a": 2, "b": 3}.items()}
print(f"dict comprehension: {squared}")

print("\n=== 3. SET (Java's HashSet) ===\n")

tags = {"java", "programming", "java", "coding"}  # duplicate ignored
print(f"set: {tags}  (no duplicates)")
print(f"  'java' in tags: {'java' in tags}")  # contains()
print(f"  len(tags):      {len(tags)}")        # size()
tags.add("python")
tags.discard("java")    # remove() — no error if missing
print(f"after add/discard: {tags}")

# Set operations — cleanest syntax of all languages!
a = {1, 2, 3, 4, 5}
b = {4, 5, 6, 7, 8}
print(f"a | b (union):        {a | b}")
print(f"a & b (intersection): {a & b}")
print(f"a - b (difference):   {a - b}")
print(f"a ^ b (symmetric diff):{a ^ b}")

print("\n=== 4. TUPLE (immutable list) ===\n")

point = (3, 4)          # immutable — no Java equivalent
x, y = point            # unpacking
print(f"tuple: {point}, x={x}, y={y}")

# Tuple is hashable — can be used as dict key
locations = {(0, 0): "origin", (1, 0): "right"}
print(f"dict with tuple keys: {locations[(0,0)]}")

print("\n=== 5. QUEUE & STACK ===\n")

from collections import deque

# Queue (FIFO)
queue = deque(["first", "second", "third"])
queue.append("fourth")     # enqueue (offer)
front = queue.popleft()    # dequeue (poll)
print(f"dequeued: {front}, remaining: {list(queue)}")

# Stack (LIFO) — use list
stack = []
stack.append("bottom")
stack.append("middle")
stack.append("top")
top = stack.pop()
print(f"popped: {top}, remaining: {stack}")

print("\n=== 6. SORTING ===\n")

nums = [5, 2, 8, 1, 9, 3]
nums.sort()                          # in-place ascending (like Collections.sort)
print(f"sorted asc:  {nums}")
nums.sort(reverse=True)              # in-place descending
print(f"sorted desc: {nums}")

words = ["banana", "apple", "cherry"]
words.sort(key=len)                  # sort by length
print(f"sorted by length: {words}")
words.sort(key=lambda w: w[-1])      # sort by last character
print(f"sorted by last char: {words}")

original = [5, 2, 8, 1]
new_sorted = sorted(original)        # returns new list (doesn't modify original)
print(f"original unchanged: {original}, sorted copy: {new_sorted}")

print("\n=== 7. WHEN TO USE WHAT ===\n")
print("list  → ordered, duplicates OK, mutable               (ArrayList)")
print("tuple → ordered, duplicates OK, IMMUTABLE             (no direct Java equivalent)")
print("dict  → key-value pairs, keys unique, ordered (3.7+)  (HashMap / LinkedHashMap)")
print("set   → unordered, unique elements                    (HashSet)")
