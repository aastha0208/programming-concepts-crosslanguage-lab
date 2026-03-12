/**
 * CL09: Data Structures in TypeScript
 * Companion to CL09_DataStructuresAcrossLanguages.java
 *
 * TypeScript adds type safety to JavaScript's Array, Map, and Set.
 * Utility types (Record, ReadonlyArray) provide additional constraints.
 *
 * Run: ts-node CL09_DataStructures.ts
 */

console.log("=== 1. TYPED ARRAY ===\n");

const fruits: string[] = ["apple", "banana", "cherry"];  // or Array<string>
fruits.push("date");
fruits.splice(fruits.indexOf("banana"), 1);

console.log("array:    " + fruits);
console.log("length:   " + fruits.length);
console.log("first:    " + fruits[0]);
console.log("last:     " + fruits.at(-1));
console.log("includes: " + fruits.includes("date"));

// Readonly array — prevents mutation
const immutable: ReadonlyArray<number> = [1, 2, 3, 4, 5];
// immutable.push(6); // TS Error!
console.log("readonly: " + immutable);

console.log("\n=== 2. MAP<K,V> ===\n");

const scores = new Map<string, number>();
scores.set("Alice", 95);
scores.set("Bob", 87);
scores.set("Charlie", 92);

console.log("Alice:    " + scores.get("Alice"));
console.log("has Bob:  " + scores.has("Bob"));
console.log("size:     " + scores.size);

for (const [key, val] of scores) {
    console.log(`  ${key} -> ${val}`);
}

// Map of arrays
const groups = new Map<string, number[]>();
groups.set("evens", [2, 4, 6]);
groups.set("odds",  [1, 3, 5]);
console.log("groups: " + JSON.stringify(Array.from(groups.entries())));

console.log("\n=== 3. SET<T> ===\n");

const set = new Set<string>(["a", "b", "c", "a", "b"]);
set.add("d");
console.log("set:     " + [...set]);
console.log("has 'a': " + set.has("a"));
console.log("size:    " + set.size);

const s1 = new Set<number>([1, 2, 3, 4]);
const s2 = new Set<number>([3, 4, 5, 6]);
const intersection = new Set<number>([...s1].filter(x => s2.has(x)));
const union        = new Set<number>([...s1, ...s2]);
console.log("intersection: " + [...intersection]);
console.log("union:        " + [...union]);

console.log("\n=== 4. RECORD<K,V> UTILITY TYPE ===\n");

// Record<K,V> = typed plain object (like Java HashMap but inline)
const wordLengths: Record<string, number> = {
    apple: 5, fig: 3, banana: 6
};
console.log("Record: " + JSON.stringify(wordLengths));

// Partial<T> — all properties optional
interface Config { host: string; port: number; debug: boolean; }
const partialConfig: Partial<Config> = { host: "localhost" };
console.log("Partial: " + JSON.stringify(partialConfig));

console.log("\n=== 5. TYPED STACK ===\n");

class Stack<T> {
    private items: T[] = [];
    push(item: T): void { this.items.push(item); }
    pop(): T | undefined { return this.items.pop(); }
    peek(): T | undefined { return this.items[this.items.length - 1]; }
    isEmpty(): boolean { return this.items.length === 0; }
    size(): number { return this.items.length; }
    toString(): string { return `Stack[${this.items}]`; }
}

const numStack = new Stack<number>();
numStack.push(1); numStack.push(2); numStack.push(3);
console.log("stack:  " + numStack);
console.log("peek:   " + numStack.peek());
console.log("pop:    " + numStack.pop());
console.log("after:  " + numStack);

console.log("\n=== 6. TYPED QUEUE ===\n");

class Queue<T> {
    private items: T[] = [];
    enqueue(item: T): void { this.items.push(item); }
    dequeue(): T | undefined { return this.items.shift(); }
    front(): T | undefined { return this.items[0]; }
    isEmpty(): boolean { return this.items.length === 0; }
    size(): number { return this.items.length; }
}

const strQueue = new Queue<string>();
strQueue.enqueue("first"); strQueue.enqueue("second"); strQueue.enqueue("third");
console.log("dequeue: " + strQueue.dequeue());
console.log("front:   " + strQueue.front());
