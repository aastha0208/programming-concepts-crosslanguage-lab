package crosslang;

/**
 * CL12: Design Patterns Across Languages
 *
 * Java       → Classic GoF patterns with classes and interfaces
 * Python     → Often simpler — first-class functions reduce need for some patterns
 * JavaScript → Prototype-based; patterns often use closures or modules
 * TypeScript → Class-based like Java, with interfaces; closest to Java patterns
 * C#         → Very similar to Java; some patterns built into the language
 *
 * Patterns covered: Singleton, Factory, Builder, Observer, Strategy
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL12_DesignPatternsAcrossLanguages
 */
public class CL12_DesignPatternsAcrossLanguages {

    // =====================================================================
    // PATTERN 1: SINGLETON
    // =====================================================================

    // JAVA: thread-safe singleton with double-checked locking
    static class DatabaseConnection {
        private static volatile DatabaseConnection instance;
        private String url;

        private DatabaseConnection(String url) { this.url = url; }

        static DatabaseConnection getInstance() {
            if (instance == null) {
                synchronized (DatabaseConnection.class) {
                    if (instance == null)
                        instance = new DatabaseConnection("jdbc:mysql://localhost/db");
                }
            }
            return instance;
        }

        @Override public String toString() { return "DB[" + url + "]"; }
    }

    /*
     * PYTHON (module-level is already a singleton in Python):
     *   class DatabaseConnection:
     *       _instance = None
     *       def __new__(cls, *args, **kwargs):
     *           if not cls._instance:
     *               cls._instance = super().__new__(cls)
     *           return cls._instance
     *
     *   # Simpler Python approach — just use a module-level variable:
     *   # db_connection.py
     *   _instance = None
     *   def get_instance():
     *       global _instance
     *       if _instance is None: _instance = DatabaseConnection(...)
     *       return _instance
     *
     * TYPESCRIPT:
     *   class DatabaseConnection {
     *     private static instance: DatabaseConnection;
     *     private constructor(private url: string) {}
     *     static getInstance(): DatabaseConnection {
     *       if (!DatabaseConnection.instance)
     *         DatabaseConnection.instance = new DatabaseConnection("...");
     *       return DatabaseConnection.instance;
     *     }
     *   }
     *
     * C#:
     *   public sealed class DatabaseConnection {
     *     private static readonly Lazy<DatabaseConnection> _instance =
     *         new Lazy<DatabaseConnection>(() => new DatabaseConnection("..."));
     *     public static DatabaseConnection Instance => _instance.Value;
     *     private DatabaseConnection(string url) { }
     *   }
     *   // Lazy<T> handles thread safety — cleaner than Java's double-checked locking
     *
     * JAVASCRIPT (module singleton):
     *   // db.js — module is cached after first import — already a singleton!
     *   let instance = null;
     *   export function getInstance() {
     *     if (!instance) instance = { url: "..." };
     *     return instance;
     *   }
     */

    // =====================================================================
    // PATTERN 2: STRATEGY
    // =====================================================================

    // JAVA: strategy interface + implementations
    interface SortStrategy {
        void sort(int[] data);
    }

    static class BubbleSort implements SortStrategy {
        @Override
        public void sort(int[] data) {
            // simplified — just prints which strategy was used
            System.out.println("  BubbleSort applied to " + data.length + " elements");
        }
    }

    static class QuickSort implements SortStrategy {
        @Override
        public void sort(int[] data) {
            System.out.println("  QuickSort applied to " + data.length + " elements");
        }
    }

    static class Sorter {
        private SortStrategy strategy;
        Sorter(SortStrategy strategy) { this.strategy = strategy; }
        void setStrategy(SortStrategy s) { this.strategy = s; }
        void sort(int[] data) { strategy.sort(data); }
    }

    /*
     * PYTHON — strategy can just be a function (no interface needed):
     *   def bubble_sort(data): print("bubble sort")
     *   def quick_sort(data): print("quick sort")
     *
     *   class Sorter:
     *       def __init__(self, strategy): self.strategy = strategy
     *       def sort(self, data): self.strategy(data)
     *
     *   sorter = Sorter(bubble_sort)
     *   sorter.strategy = quick_sort   # swap strategy
     *
     * TYPESCRIPT:
     *   interface SortStrategy { sort(data: number[]): void; }
     *   class BubbleSort implements SortStrategy { sort(data: number[]) { ... } }
     *   class Sorter {
     *     constructor(private strategy: SortStrategy) {}
     *     sort(data: number[]) { this.strategy.sort(data); }
     *   }
     *
     * C#:
     *   interface ISortStrategy { void Sort(int[] data); }
     *   class BubbleSort : ISortStrategy { public void Sort(int[] data) { ... } }
     *   // Or use delegates instead of interface (C# specific):
     *   class Sorter {
     *     public Action<int[]> Strategy { get; set; }
     *     public void Sort(int[] data) => Strategy(data);
     *   }
     *
     * JAVASCRIPT — same as Python, pass functions directly:
     *   class Sorter {
     *     constructor(strategy) { this.strategy = strategy; }
     *     sort(data) { this.strategy(data); }
     *   }
     *   const sorter = new Sorter(data => data.sort((a,b)=>a-b));
     */

    // =====================================================================
    // PATTERN 3: BUILDER
    // =====================================================================

    static class Pizza {
        private String size;
        private String crust;
        private boolean cheese;
        private boolean pepperoni;

        private Pizza(Builder b) {
            this.size = b.size; this.crust = b.crust;
            this.cheese = b.cheese; this.pepperoni = b.pepperoni;
        }

        @Override public String toString() {
            return "Pizza[" + size + ", " + crust + ", cheese=" + cheese + ", pepperoni=" + pepperoni + "]";
        }

        static class Builder {
            private String size;
            private String crust = "thin";
            private boolean cheese = false;
            private boolean pepperoni = false;

            Builder(String size) { this.size = size; }
            Builder crust(String c) { this.crust = c; return this; }
            Builder cheese()        { this.cheese = true; return this; }
            Builder pepperoni()     { this.pepperoni = true; return this; }
            Pizza build()           { return new Pizza(this); }
        }
    }

    /*
     * PYTHON — keyword arguments often replace Builder entirely:
     *   class Pizza:
     *       def __init__(self, size, crust="thin", cheese=False, pepperoni=False):
     *           self.size, self.crust = size, crust
     *           self.cheese, self.pepperoni = cheese, pepperoni
     *
     *   p = Pizza("large", crust="thick", cheese=True)   # no Builder needed!
     *
     *   # For complex cases: use dataclass with default values
     *   from dataclasses import dataclass, field
     *   @dataclass
     *   class Pizza:
     *       size: str
     *       crust: str = "thin"
     *       cheese: bool = False
     *
     * TYPESCRIPT:
     *   class PizzaBuilder {
     *     private size: string;
     *     private crust = "thin";
     *     private cheese = false;
     *     constructor(size: string) { this.size = size; }
     *     withCrust(c: string): this { this.crust = c; return this; }
     *     withCheese(): this { this.cheese = true; return this; }
     *     build(): Pizza { return new Pizza(this.size, this.crust, this.cheese); }
     *   }
     *
     * C# — object initializer syntax reduces need for Builder:
     *   var pizza = new Pizza { Size = "large", Crust = "thick", Cheese = true };
     *   // For immutable objects: use record types (C# 9+)
     *   record Pizza(string Size, string Crust = "thin", bool Cheese = false);
     *   var p = new Pizza("large") with { Crust = "thick", Cheese = true };
     *
     * JAVASCRIPT — use object spread:
     *   const defaults = { crust: "thin", cheese: false, pepperoni: false };
     *   const pizza = { ...defaults, size: "large", cheese: true };
     */

    // =====================================================================
    // PATTERN 4: OBSERVER
    // =====================================================================

    interface Observer { void update(String event); }

    static class EventBus {
        private java.util.List<Observer> observers = new java.util.ArrayList<>();
        void subscribe(Observer o)   { observers.add(o); }
        void unsubscribe(Observer o) { observers.remove(o); }
        void publish(String event)   { observers.forEach(o -> o.update(event)); }
    }

    /*
     * PYTHON:
     *   class EventBus:
     *       def __init__(self): self._observers = []
     *       def subscribe(self, fn): self._observers.append(fn)
     *       def publish(self, event):
     *           for fn in self._observers: fn(event)
     *
     *   bus = EventBus()
     *   bus.subscribe(lambda e: print(f"Got: {e}"))  # lambda as observer
     *
     * TYPESCRIPT:
     *   type Observer = (event: string) => void;
     *   class EventBus {
     *     private observers: Observer[] = [];
     *     subscribe(o: Observer) { this.observers.push(o); }
     *     publish(event: string) { this.observers.forEach(o => o(event)); }
     *   }
     *
     * C# — events are a first-class language feature:
     *   class EventBus {
     *     public event Action<string> OnEvent;
     *     public void Publish(string e) => OnEvent?.Invoke(e);
     *   }
     *   bus.OnEvent += e => Console.WriteLine($"Got: {e}");  // += to subscribe
     *   bus.OnEvent -= handler;                              // -= to unsubscribe
     *
     * JAVASCRIPT — EventEmitter (Node.js) or CustomEvent (browser):
     *   const emitter = new EventEmitter();
     *   emitter.on("data", (e) => console.log(e));
     *   emitter.emit("data", "hello");
     */

    public static void main(String[] args) {

        System.out.println("=== 1. SINGLETON ===\n");
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("Same instance: " + (db1 == db2));
        System.out.println(db1);

        System.out.println("\n=== 2. STRATEGY ===\n");
        int[] data = {5, 2, 8, 1};
        Sorter sorter = new Sorter(new BubbleSort());
        sorter.sort(data);
        sorter.setStrategy(new QuickSort());
        sorter.sort(data);

        System.out.println("\n=== 3. BUILDER ===\n");
        Pizza pizza = new Pizza.Builder("large")
            .crust("thick")
            .cheese()
            .pepperoni()
            .build();
        System.out.println(pizza);

        System.out.println("\n=== 4. OBSERVER ===\n");
        EventBus bus = new EventBus();
        bus.subscribe(e -> System.out.println("  Listener A: " + e));
        bus.subscribe(e -> System.out.println("  Listener B: " + e));
        bus.publish("user_logged_in");

        System.out.println("\n=== 5. PATTERN LANGUAGE NOTES ===\n");
        System.out.println("Singleton  → C# Lazy<T> cleanest; JS modules are singleton by default");
        System.out.println("Strategy   → Python/JS: just pass functions; no interface needed");
        System.out.println("Builder    → Python: kwargs; C#: object initializers/records");
        System.out.println("Observer   → C# events are built-in; JS has EventEmitter");
        System.out.println("Factory    → All languages support; Python/JS use functions over classes");
    }
}
