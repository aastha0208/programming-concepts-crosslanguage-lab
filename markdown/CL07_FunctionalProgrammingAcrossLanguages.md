# CL07: Functional Programming (Streams & Lambdas) Across Languages

| Language | Approach |
|----------|----------|
| Java | Lambdas + Stream API (Java 8+), `Function<T,R>`, `Predicate<T>` |
| Python | First-class functions, list comprehensions, `map`/`filter`/`reduce` |
| JavaScript | First-class functions, `Array.map`/`filter`/`reduce` |
| TypeScript | Same as JS with typed function signatures |
| C# | LINQ, lambda expressions, `Func<>`/`Action<>` delegates |

---

## 1. Lambda / Anonymous Function

**Java**
```java
Function<Integer, Integer> square = x -> x * x;
Function<String, String>   shout  = s -> s.toUpperCase() + "!";
square.apply(5);    // 25
shout.apply("hi");  // "HI!"
```

**TypeScript**
```typescript
const square = (x: number): number => x * x;
const shout  = (s: string): string => s.toUpperCase() + "!";
```

**C#**
```csharp
Func<int, int>       square = x => x * x;
Func<string, string> shout  = s => s.ToUpper() + "!";
```

**Python**
```python
square = lambda x: x * x
shout  = lambda s: s.upper() + "!"
```

**JavaScript**
```javascript
const square = x => x * x;
const shout  = s => s.toUpperCase() + "!";
```

---

## 2. Map

**Java**
```java
List<Integer> squared = nums.stream()
    .map(x -> x * x)
    .collect(Collectors.toList());
```

**Python** *(list comprehension preferred)*
```python
squared = [x * x for x in nums]            # preferred
squared = list(map(lambda x: x * x, nums)) # map() equivalent
```

**JavaScript**
```javascript
const squared = nums.map(x => x * x);
```

**TypeScript**
```typescript
const squared: number[] = nums.map((x: number) => x * x);
```

**C# (LINQ)**
```csharp
var squared = nums.Select(x => x * x).ToList();
```

---

## 3. Filter

**Java**
```java
List<Integer> evens = nums.stream()
    .filter(x -> x % 2 == 0)
    .collect(Collectors.toList());
```

**Python**
```python
evens = [x for x in nums if x % 2 == 0]
```

**JavaScript**
```javascript
const evens = nums.filter(x => x % 2 === 0);
```

**C# (LINQ)**
```csharp
var evens = nums.Where(x => x % 2 == 0).ToList();
```

---

## 4. Reduce

**Java**
```java
int sum = nums.stream().reduce(0, Integer::sum);
```

**Python**
```python
from functools import reduce
total = reduce(lambda acc, x: acc + x, nums, 0)
total = sum(nums)  # built-in shorthand
```

**JavaScript**
```javascript
const total = nums.reduce((acc, x) => acc + x, 0);
```

**C# (LINQ)**
```csharp
int total = nums.Aggregate(0, (acc, x) => acc + x);
int total = nums.Sum(); // built-in shorthand
```

---

## 5. Chaining

**Java**
```java
List<String> result = words.stream()
    .map(String::trim)
    .filter(s -> !s.isEmpty())
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());
```

**Python**
```python
result = sorted([w.strip().upper() for w in words if w.strip()])
```

**JavaScript**
```javascript
const result = words
    .map(s => s.trim())
    .filter(s => s.length > 0)
    .map(s => s.toUpperCase())
    .sort();
```

**C# (LINQ)**
```csharp
var result = words
    .Select(s => s.Trim())
    .Where(s => s.Length > 0)
    .Select(s => s.ToUpper())
    .OrderBy(s => s)
    .ToList();
```

---

## 6. Predicate Composition

**Java**
```java
Predicate<Integer> isEven     = x -> x % 2 == 0;
Predicate<Integer> isPositive = x -> x > 0;
Predicate<Integer> both = isEven.and(isPositive); // built-in composition
```

**TypeScript / JavaScript**
```typescript
const isEven = (x: number) => x % 2 === 0;
const isPositive = (x: number) => x > 0;
const both = (x: number) => isEven(x) && isPositive(x); // compose manually
```

**C#**
```csharp
Func<int, bool> isEven = x => x % 2 == 0;
Func<int, bool> isPositive = x => x > 0;
Func<int, bool> both = x => isEven(x) && isPositive(x); // no built-in .And()
```

**Python**
```python
is_even = lambda x: x % 2 == 0
is_positive = lambda x: x > 0
both = lambda x: is_even(x) and is_positive(x)
```
