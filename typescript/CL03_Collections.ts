/**
 * CL03: Collections in TypeScript
 * Companion to CL03_CollectionsAcrossLanguages.java
 *
 * TypeScript adds type safety to JavaScript's Array, Map, and Set.
 * Utility types (ReadonlyArray, Record, Partial) provide extra constraints.
 * No separate LinkedList, TreeMap, etc. — use Array and Map.
 *
 * Run: ts-node CL03_Collections.ts
 */

console.log("=== 1. TYPED ARRAY ===\n");

// Two syntaxes — both equivalent
const fruits: string[]       = ["apple", "banana", "cherry"];
const nums:   Array<number>  = [1, 2, 3, 4, 5];

fruits.push("date");
fruits.splice(fruits.indexOf("banana"), 1);
console.log("fruits:   " + fruits);
console.log("length:   " + fruits.length);
console.log("first:    " + fruits[0]);
console.log("last:     " + fruits.at(-1));
console.log("includes: " + fruits.includes("date"));

// ReadonlyArray — prevents all mutation
const immutable: ReadonlyArray<number> = [1, 2, 3];
// immutable.push(4);  // TS Error: Property 'push' does not exist on ReadonlyArray<number>
console.log("immutable: " + immutable);

/*
 * Java: List<String> list = new ArrayList<>();
 * TS:   const list: string[] = [];
 *
 * Java: Collections.unmodifiableList(list)
 * TS:   ReadonlyArray<string>
 */

console.log("\n=== 2. MAP<K,V> ===\n");

const scores = new Map<string, number>();
scores.set("Alice", 95);
scores.set("Bob", 87);
scores.set("Charlie", 92);

console.log("Alice:   " + scores.get("Alice"));
console.log("has Bob: " + scores.has("Bob"));
console.log("size:    " + scores.size);

scores.delete("Charlie");
for (const [key, val] of scores) {
    console.log(`  ${key} -> ${val}`);
}

// Map with complex value type
interface Student { grade: string; gpa: number; }
const students = new Map<number, Student>();
students.set(1, { grade: "A", gpa: 3.9 });
students.set(2, { grade: "B", gpa: 3.2 });
console.log("student 1: " + JSON.stringify(students.get(1)));

console.log("\n=== 3. RECORD<K,V> (typed object) ===\n");

// Record<K,V> = typed plain object (simpler than Map for string keys)
const wordLengths: Record<string, number> = {
    apple: 5, fig: 3, banana: 6
};
wordLengths["cherry"] = 6;
console.log("Record: " + JSON.stringify(wordLengths));

// Partial<T> and Required<T>
interface Config { host: string; port: number; debug: boolean; }
const partial: Partial<Config>  = { host: "localhost" };      // all optional
const full: Required<Config>    = { host: "localhost", port: 8080, debug: false };
console.log("partial: " + JSON.stringify(partial));
console.log("full:    " + JSON.stringify(full));

console.log("\n=== 4. SET<T> ===\n");

const set = new Set<string>(["a", "b", "c", "a", "b"]);  // dupes removed
set.add("d");
console.log("set:     " + [...set]);
console.log("has 'a': " + set.has("a"));
console.log("size:    " + set.size);

// Set operations
const s1 = new Set<number>([1, 2, 3, 4]);
const s2 = new Set<number>([3, 4, 5, 6]);
const intersection = new Set<number>([...s1].filter(x => s2.has(x)));
const union        = new Set<number>([...s1, ...s2]);
const difference   = new Set<number>([...s1].filter(x => !s2.has(x)));
console.log("intersection: " + [...intersection]);
console.log("union:        " + [...union]);
console.log("difference:   " + [...difference]);

console.log("\n=== 5. ARRAY AS STACK AND QUEUE ===\n");

// Stack (LIFO)
const stack: number[] = [];
stack.push(1); stack.push(2); stack.push(3);
console.log("stack peek: " + stack[stack.length - 1]);
console.log("stack pop:  " + stack.pop());

// Queue (FIFO) — shift() is O(n)
const queue: string[] = [];
queue.push("first"); queue.push("second"); queue.push("third");
console.log("queue front:   " + queue[0]);
console.log("queue dequeue: " + queue.shift());

console.log("\n=== 6. ARRAY FUNCTIONAL METHODS (typed) ===\n");

const data: number[] = [1, 2, 3, 4, 5, 6];

const evens:   number[] = data.filter((x: number) => x % 2 === 0);
const squared: number[] = data.map((x: number) => x * x);
const sum:     number   = data.reduce((acc: number, x: number) => acc + x, 0);
const found:   number | undefined = data.find((x: number) => x > 3);

console.log("evens:   " + evens);
console.log("squared: " + squared);
console.log("sum:     " + sum);
console.log("find >3: " + found);

// Type guard in filter — narrows type
const mixed: (string | number)[] = [1, "two", 3, "four", 5];
const numbersOnly: number[] = mixed.filter((x): x is number => typeof x === "number");
console.log("numbers only: " + numbersOnly);

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Java List<T>       → TS string[] or Array<T>");
console.log("Java HashMap<K,V>  → TS Map<K,V> or Record<K,V>");
console.log("Java HashSet<T>    → TS Set<T>");
console.log("Java unmodifiable  → TS ReadonlyArray<T> / Readonly<T>");
console.log("No LinkedList/TreeMap — use Array and Map");
