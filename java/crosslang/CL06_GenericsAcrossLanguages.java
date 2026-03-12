package crosslang;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * CL06: Generics Across Languages
 *
 * Java       → Generics with type erasure at runtime
 * Python     → Type hints (PEP 484) via typing module — not enforced at runtime
 * JavaScript → No generics — duck typing handles it
 * TypeScript → Generics fully supported, enforced at compile time
 * C#         → Generics with reified types (runtime type info preserved)
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL06_GenericsAcrossLanguages
 */
public class CL06_GenericsAcrossLanguages {

    // JAVA: generic class
    static class Box<T> {
        private T value;
        Box(T value) { this.value = value; }
        T get() { return value; }

        @Override
        public String toString() { return "Box[" + value + "]"; }
    }

    // JAVA: generic method
    static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    // JAVA: bounded wildcard — accepts List of Number or any subtype
    static double sumList(List<? extends Number> list) {
        double total = 0;
        for (Number n : list) total += n.doubleValue();
        return total;
    }

    /*
     * TYPESCRIPT (closest to Java generics):
     *   class Box<T> {
     *     constructor(private value: T) {}
     *     get(): T { return this.value; }
     *   }
     *   const b = new Box<number>(42);
     *
     *   // Generic function
     *   function max<T extends { compareTo?: (other: T) => number }>(a: T, b: T): T { ... }
     *
     *   // Bounded type parameter
     *   function sumList<T extends number>(list: T[]): number {
     *     return list.reduce((acc, n) => acc + n, 0);
     *   }
     *
     * C# (reified generics — type info is preserved at runtime):
     *   class Box<T> {
     *     private T value;
     *     public Box(T value) { this.value = value; }
     *     public T Get() => value;
     *   }
     *   var b = new Box<int>(42);
     *
     *   // C# can do: typeof(T) at runtime — Java cannot (type erasure)
     *   void PrintType<T>() { Console.WriteLine(typeof(T).Name); }
     *   PrintType<int>();  // prints "Int32"
     *
     * PYTHON (type hints — mypy checks, runtime ignores):
     *   from typing import TypeVar, Generic, List
     *   T = TypeVar('T')
     *
     *   class Box(Generic[T]):
     *       def __init__(self, value: T) -> None:
     *           self.value = value
     *       def get(self) -> T:
     *           return self.value
     *
     *   b: Box[int] = Box(42)   # hint only — Box("hello") also works at runtime
     *
     * JAVASCRIPT (no generics — relies on duck typing):
     *   class Box {
     *     constructor(value) { this.value = value; }
     *     get() { return this.value; }
     *   }
     *   const b = new Box(42);   // works for any type — no type checking
     */

    public static void main(String[] args) {

        System.out.println("=== 1. GENERIC CLASS ===\n");

        Box<Integer> intBox = new Box<>(42);
        Box<String>  strBox = new Box<>("Hello");
        System.out.println("int box:    " + intBox);
        System.out.println("string box: " + strBox);

        System.out.println("\n=== 2. GENERIC METHOD ===\n");

        System.out.println("max(3, 7):       " + max(3, 7));
        System.out.println("max(\"apple\", \"banana\"): " + max("apple", "banana"));

        System.out.println("\n=== 3. BOUNDED WILDCARD (? extends) ===\n");

        List<Integer> ints = new ArrayList<>();
        ints.add(1); ints.add(2); ints.add(3);
        System.out.println("sum of [1,2,3]: " + sumList(ints));

        List<Double> doubles = new ArrayList<>();
        doubles.add(1.5); doubles.add(2.5);
        System.out.println("sum of [1.5,2.5]: " + sumList(doubles));

        System.out.println("\n=== 4. GENERIC COLLECTIONS ===\n");

        // JAVA: generics enforce element type at compile time
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("scores", new ArrayList<>());
        map.get("scores").add(95);
        map.get("scores").add(87);
        System.out.println("map: " + map);

        /*
         * TYPESCRIPT:
         *   const map = new Map<string, number[]>();
         *   map.set("scores", [95, 87]);
         *
         * C#:
         *   var map = new Dictionary<string, List<int>>();
         *   map["scores"] = new List<int> { 95, 87 };
         *
         * PYTHON:
         *   from typing import Dict, List
         *   map: Dict[str, List[int]] = {"scores": [95, 87]}
         *   # type hint only — runtime doesn't enforce it
         *
         * JAVASCRIPT:
         *   const map = new Map();
         *   map.set("scores", [95, 87]);   // no type constraint
         */

        System.out.println("\n=== 5. TYPE ERASURE (Java specific) ===\n");

        // JAVA: at runtime, Box<Integer> and Box<String> are BOTH just Box
        Box<Integer> b1 = new Box<>(1);
        Box<String>  b2 = new Box<>("x");
        System.out.println("Same class at runtime: " + (b1.getClass() == b2.getClass())); // true!

        /*
         * KEY DIFFERENCE — Type erasure:
         *   Java:       Box<Integer> and Box<String> → both become Box at runtime
         *   C#:         Box<int> and Box<string> are DIFFERENT types at runtime (reified)
         *   TypeScript: Types erased to JS at compile time (similar to Java)
         *   Python:     Type hints stripped — Box[int] and Box[str] same at runtime
         */
    }
}
