# CL12: Design Patterns Across Languages

| Pattern | Java | Python | JavaScript | TypeScript | C# |
|---------|------|--------|------------|------------|-----|
| Singleton | double-checked lock | `__new__` or module | ES module cache | private constructor | `Lazy<T>` |
| Strategy | interface + classes | pass functions | pass functions | interface + classes | delegate or interface |
| Builder | inner Builder class | keyword args / dataclass | object spread | Builder class | object initializer / record |
| Observer | interface + list | functions in list | EventEmitter | typed callbacks | `event` keyword |
| Factory | abstract method | function returning instance | factory function | abstract method | abstract method |

---

## 1. Singleton

**Java** *(double-checked locking)*
```java
class Database {
    private static volatile Database instance;
    private Database() {}
    static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) instance = new Database();
            }
        }
        return instance;
    }
}
```

**Python** *(module-level is already singleton — simplest approach)*
```python
# db_connection.py — module is loaded once; just use a module-level variable
_instance = None
def get_instance():
    global _instance
    if _instance is None:
        _instance = DatabaseConnection()
    return _instance
```

**JavaScript** *(ES module is cached after first import — free singleton)*
```javascript
// db.js
let instance = null;
export function getInstance() {
    if (!instance) instance = { url: "..." };
    return instance;
}
```

**TypeScript**
```typescript
class Database {
    private static instance: Database;
    private constructor() {}
    static getInstance(): Database {
        if (!Database.instance) Database.instance = new Database();
        return Database.instance;
    }
}
```

**C#** *(Lazy<T> — cleanest thread-safe singleton)*
```csharp
public sealed class Database {
    private static readonly Lazy<Database> _instance =
        new Lazy<Database>(() => new Database());
    public static Database Instance => _instance.Value;
    private Database() {}
}
```

---

## 2. Strategy

**Java**
```java
interface SortStrategy { void sort(int[] data); }
class BubbleSort implements SortStrategy { public void sort(int[] d) { ... } }

class Sorter {
    private SortStrategy strategy;
    Sorter(SortStrategy s) { this.strategy = s; }
    void sort(int[] data) { strategy.sort(data); }
}
Sorter sorter = new Sorter(new BubbleSort());
```

**Python / JavaScript** *(just pass a function — no interface needed)*
```python
def bubble_sort(data): ...
def quick_sort(data): ...

class Sorter:
    def __init__(self, strategy): self.strategy = strategy
    def sort(self, data): self.strategy(data)

sorter = Sorter(bubble_sort)
sorter.strategy = quick_sort  # swap strategy
```

**TypeScript**
```typescript
interface SortStrategy { sort(data: number[]): void; }
class Sorter {
    constructor(private strategy: SortStrategy) {}
    sort(data: number[]) { this.strategy.sort(data); }
}
```

**C#** *(can use delegate instead of interface)*
```csharp
class Sorter {
    public Action<int[]> Strategy { get; set; }
    public void Sort(int[] data) => Strategy(data);
}
sorter.Strategy = data => Array.Sort(data);
```

---

## 3. Builder

**Java**
```java
Pizza pizza = new Pizza.Builder("large")
    .crust("thick")
    .cheese()
    .pepperoni()
    .build();
```

**Python** *(keyword arguments make Builder unnecessary)*
```python
@dataclass
class Pizza:
    size: str
    crust: str = "thin"
    cheese: bool = False
    pepperoni: bool = False

p = Pizza("large", crust="thick", cheese=True)  # no Builder needed
```

**JavaScript** *(object spread)*
```javascript
const defaults = { crust: "thin", cheese: false, pepperoni: false };
const pizza = { ...defaults, size: "large", cheese: true };
```

**TypeScript**
```typescript
class PizzaBuilder {
    private size: string;
    private crust = "thin";
    constructor(size: string) { this.size = size; }
    withCrust(c: string): this { this.crust = c; return this; }
    build(): Pizza { return new Pizza(this.size, this.crust); }
}
```

**C#** *(object initializer / record — Builder rarely needed)*
```csharp
// Object initializer:
var pizza = new Pizza { Size = "large", Crust = "thick", Cheese = true };

// C# 9+ record with 'with' expression:
record Pizza(string Size, string Crust = "thin", bool Cheese = false);
var p2 = new Pizza("large") with { Crust = "thick", Cheese = true };
```

---

## 4. Observer

**Java**
```java
interface Observer { void update(String event); }
class EventBus {
    List<Observer> observers = new ArrayList<>();
    void subscribe(Observer o) { observers.add(o); }
    void publish(String e)     { observers.forEach(o -> o.update(e)); }
}
bus.subscribe(e -> System.out.println("Got: " + e));
```

**Python**
```python
class EventBus:
    def __init__(self): self._observers = []
    def subscribe(self, fn): self._observers.append(fn)
    def publish(self, event):
        for fn in self._observers: fn(event)

bus.subscribe(lambda e: print(f"Got: {e}"))
```

**JavaScript** *(EventEmitter in Node.js)*
```javascript
const emitter = new EventEmitter();
emitter.on("data", e => console.log(e));
emitter.emit("data", "hello");
```

**TypeScript**
```typescript
type Observer = (event: string) => void;
class EventBus {
    private observers: Observer[] = [];
    subscribe(o: Observer) { this.observers.push(o); }
    publish(event: string) { this.observers.forEach(o => o(event)); }
}
```

**C#** *(events are first-class language feature)*
```csharp
class EventBus {
    public event Action<string> OnEvent;
    public void Publish(string e) => OnEvent?.Invoke(e);
}
bus.OnEvent += e => Console.WriteLine($"Got: {e}");  // subscribe
bus.OnEvent -= handler;                               // unsubscribe
```

---

## 5. Where Patterns Simplify

| Pattern | Simplified in |
|---------|---------------|
| Singleton | JS modules (free); C# `Lazy<T>` (thread-safe, one line) |
| Strategy | Python/JS — just pass functions, no interface |
| Builder | Python — use kwargs; C# — use object initializers or records |
| Observer | C# — `event` is built into the language |
| Factory | Python/JS — factory functions (no class needed) |
