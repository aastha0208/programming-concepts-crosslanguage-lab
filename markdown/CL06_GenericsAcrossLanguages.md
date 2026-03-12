# CL06: Generics Across Languages

| Language | Approach |
|----------|----------|
| Java | Generics with **type erasure** — type info lost at runtime |
| Python | Type hints via `typing` module — not enforced at runtime |
| JavaScript | No generics — duck typing handles it |
| TypeScript | Generics fully supported, enforced at **compile time** |
| C# | Generics with **reified types** — runtime type info preserved |

---

## 1. Generic Class

**Java**
```java
class Box<T> {
    private T value;
    Box(T value) { this.value = value; }
    T get() { return value; }
}
Box<Integer> intBox = new Box<>(42);
Box<String>  strBox = new Box<>("Hello");
```

**TypeScript** *(closest to Java)*
```typescript
class Box<T> {
    constructor(private value: T) {}
    get(): T { return this.value; }
}
const b = new Box<number>(42);
```

**C#** *(reified — type preserved at runtime)*
```csharp
class Box<T> {
    private T value;
    public Box(T value) { this.value = value; }
    public T Get() => value;
}
var b = new Box<int>(42);
// typeof(T) works at runtime — Java cannot do this (type erasure)
```

**Python** *(type hint only — not enforced)*
```python
from typing import TypeVar, Generic
T = TypeVar('T')

class Box(Generic[T]):
    def __init__(self, value: T) -> None:
        self.value = value
    def get(self) -> T:
        return self.value

b: Box[int] = Box(42)   # hint only — Box("hello") also works at runtime
```

**JavaScript** *(no generics — duck typing)*
```javascript
class Box {
    constructor(value) { this.value = value; }
    get() { return this.value; }
}
const b = new Box(42);  // works for any type — no type checking
```

---

## 2. Generic Method

**Java**
```java
static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}
max(3, 7);         // 7
max("apple", "banana"); // "banana"
```

**TypeScript**
```typescript
function max<T>(a: T, b: T, compareFn: (a: T, b: T) => number): T {
    return compareFn(a, b) >= 0 ? a : b;
}
```

**C#**
```csharp
static T Max<T>(T a, T b) where T : IComparable<T> {
    return a.CompareTo(b) >= 0 ? a : b;
}
```

---

## 3. Bounded Type Parameter

**Java**
```java
// ? extends Number — accepts List<Integer>, List<Double>, etc.
static double sumList(List<? extends Number> list) {
    double total = 0;
    for (Number n : list) total += n.doubleValue();
    return total;
}
```

**TypeScript**
```typescript
function sumList<T extends number>(list: T[]): number {
    return list.reduce((acc, n) => acc + n, 0);
}
```

**C#**
```csharp
static double SumList<T>(IList<T> list) where T : struct, IConvertible {
    return list.Sum(n => Convert.ToDouble(n));
}
```

---

## 4. Type Erasure vs Reified Types

| | Java | TypeScript | C# | Python |
|-|------|------------|-----|--------|
| `Box<Integer>` == `Box<String>` at runtime? | ✅ Yes (erased) | ✅ Yes (compiles to JS) | ❌ No (different types) | ✅ Yes (hints stripped) |
| Can check `T` type at runtime? | ❌ No | ❌ No | ✅ Yes (`typeof(T)`) | ❌ No |

```java
// Java: both are just Box at runtime
Box<Integer> b1 = new Box<>(1);
Box<String>  b2 = new Box<>("x");
b1.getClass() == b2.getClass() // true!
```

```csharp
// C#: different types at runtime
typeof(Box<int>) != typeof(Box<string>) // true — they are different
```
