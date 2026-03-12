"""
CL09: Data Structures in Python
Companion to CL09_DataStructuresAcrossLanguages.java

Python built-ins: list, dict, set, tuple
Standard library: collections.deque, heapq, collections.Counter

Run: python3 CL09_DataStructures.py
"""

from collections import deque, defaultdict, Counter
import heapq

print("=== 1. LIST (dynamic array) ===\n")

lst = ["apple", "banana", "cherry"]
lst.append("date")          # add to end
lst.insert(1, "avocado")    # insert at index
lst.remove("banana")        # remove by value
print(f"list:      {lst}")
print(f"length:    {len(lst)}")         # function, not method
print(f"first:     {lst[0]}")
print(f"last:      {lst[-1]}")          # negative indexing!
print(f"slice:     {lst[1:3]}")         # [1,3) — exclusive end
print(f"reversed:  {lst[::-1]}")        # step -1 reverses
print(f"index:     {lst.index('date')}")
print(f"contains:  {'date' in lst}")

print("\n=== 2. DICT (hash map) ===\n")

scores = {"Alice": 95, "Bob": 87, "Charlie": 92}
scores["Dave"] = 88           # add
del scores["Charlie"]         # remove
print(f"scores:    {scores}")
print(f"Alice:     {scores['Alice']}")
print(f"default:   {scores.get('Zara', 0)}")   # .get with default — no KeyError
print(f"has Bob:   {'Bob' in scores}")
print(f"keys:      {list(scores.keys())}")
print(f"values:    {list(scores.values())}")

for key, val in scores.items():
    print(f"  {key} -> {val}")

# Dict comprehension
squared = {x: x*x for x in range(1, 6)}
print(f"squared dict: {squared}")

# defaultdict — auto-initializes missing keys
word_count = defaultdict(int)
for word in ["apple", "banana", "apple", "cherry", "apple"]:
    word_count[word] += 1
print(f"word count: {dict(word_count)}")

print("\n=== 3. SET ===\n")

s = {"a", "b", "c", "a", "b"}   # dupes removed automatically
s.add("d")
s.discard("z")                   # remove safely (no error if missing)
print(f"set:          {s}")
print(f"has 'a':      {'a' in s}")

s1 = {1, 2, 3, 4}
s2 = {3, 4, 5, 6}
print(f"intersection: {s1 & s2}")    # {3, 4}
print(f"union:        {s1 | s2}")    # {1,2,3,4,5,6}
print(f"difference:   {s1 - s2}")    # {1, 2}

# Set comprehension
even_set = {x for x in range(10) if x % 2 == 0}
print(f"even set:     {even_set}")

print("\n=== 4. TUPLE (immutable list) ===\n")

point = (10, 20)
rgb   = (255, 128, 0)
x, y  = point   # unpacking
print(f"point: {point}, x={x}, y={y}")
print(f"rgb:   {rgb}, red={rgb[0]}")
# point[0] = 5  # TypeError — tuples are immutable

print("\n=== 5. DEQUE (stack + queue) ===\n")

# deque is O(1) for both ends — better than list for queues
dq = deque([1, 2, 3])

# Use as stack (LIFO)
dq.append(4)          # push right
print(f"pop right:  {dq.pop()}")

# Use as queue (FIFO)
dq.appendleft(0)      # enqueue left
print(f"pop right:  {dq.pop()}")    # dequeue right
print(f"deque:      {list(dq)}")

print("\n=== 6. PRIORITY QUEUE (heapq — min-heap) ===\n")

pq = [5, 1, 3, 2, 4]
heapq.heapify(pq)              # convert list to heap in-place O(n)
print(f"min pop: {heapq.heappop(pq)}")   # 1
print(f"min pop: {heapq.heappop(pq)}")   # 2
heapq.heappush(pq, 0)
print(f"min pop: {heapq.heappop(pq)}")   # 0

# Max-heap: negate values
max_pq = [-5, -1, -3]
heapq.heapify(max_pq)
print(f"max pop: {-heapq.heappop(max_pq)}")  # 5

print("\n=== 7. COUNTER ===\n")

words = ["apple", "banana", "apple", "cherry", "apple", "banana"]
counter = Counter(words)
print(f"Counter:      {counter}")
print(f"most common:  {counter.most_common(2)}")
print(f"apple count:  {counter['apple']}")
