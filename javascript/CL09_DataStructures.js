/**
 * CL09: Data Structures in JavaScript
 * Companion to CL09_DataStructuresAcrossLanguages.java
 *
 * JS built-ins: Array, Object, Map, Set (ES6+)
 * No built-in Stack, Queue, PriorityQueue — use Array or implement manually.
 *
 * Run: node CL09_DataStructures.js
 */

console.log("=== 1. ARRAY (dynamic list) ===\n");

const arr = ["apple", "banana", "cherry"];
arr.push("date");           // add to end
arr.unshift("avocado");     // add to start
arr.splice(2, 1);           // remove 1 element at index 2
console.log("array:      " + arr);
console.log("length:     " + arr.length);   // property, not method
console.log("first:      " + arr[0]);
console.log("last:       " + arr.at(-1));   // negative index (ES2022)
console.log("slice [1,3]:" + arr.slice(1, 3)); // returns new array — non-destructive
console.log("includes:   " + arr.includes("date"));
console.log("indexOf:    " + arr.indexOf("date"));

console.log("\n=== 2. OBJECT as MAP (simple key-value) ===\n");

// Object: keys are strings (or symbols)
const scores = { Alice: 95, Bob: 87, Charlie: 92 };
console.log("Alice:       " + scores["Alice"]);
console.log("Has Bob:     " + ("Bob" in scores));
scores["Dave"] = 88;                    // add
delete scores["Charlie"];              // remove
console.log("keys:        " + Object.keys(scores));
console.log("values:      " + Object.values(scores));
for (const [key, val] of Object.entries(scores)) {
    console.log("  " + key + " -> " + val);
}

console.log("\n=== 3. MAP (ES6 — any key type) ===\n");

const map = new Map();
map.set("Alice", 95);
map.set("Bob", 87);
map.set(42, "numeric key works");    // Object keys can't do this cleanly

console.log("Alice:   " + map.get("Alice"));
console.log("has Bob: " + map.has("Bob"));
console.log("size:    " + map.size);   // .size not .length

map.delete("Bob");
for (const [k, v] of map) {
    console.log("  " + k + " -> " + v);
}

console.log("\n=== 4. SET ===\n");

const set = new Set(["a", "b", "c", "a", "b"]); // dupes removed
set.add("d");
console.log("set:      " + [...set]);     // spread to array to print
console.log("has 'a':  " + set.has("a"));
console.log("size:     " + set.size);
set.delete("a");

// Set operations (manual — no built-in operators like Python)
const s1 = new Set([1, 2, 3, 4]);
const s2 = new Set([3, 4, 5, 6]);
const intersection = new Set([...s1].filter(x => s2.has(x)));
const union        = new Set([...s1, ...s2]);
const difference   = new Set([...s1].filter(x => !s2.has(x)));
console.log("intersection: " + [...intersection]);
console.log("union:        " + [...union]);
console.log("difference:   " + [...difference]);

console.log("\n=== 5. STACK (use Array) ===\n");

const stack = [];
stack.push(1); stack.push(2); stack.push(3);  // push = Java stack.push
console.log("peek: " + stack[stack.length - 1]);
console.log("pop:  " + stack.pop());           // LIFO
console.log("stack after pop: " + stack);

console.log("\n=== 6. QUEUE (use Array — shift is O(n)) ===\n");

const queue = [];
queue.push(1); queue.push(2); queue.push(3);   // enqueue
console.log("peek:   " + queue[0]);
console.log("dequeue:" + queue.shift());        // FIFO — shift() is O(n)!
console.log("queue after dequeue: " + queue);

// For large queues, implement a proper circular buffer or use a library.
// Java's LinkedList as Queue is O(1) for both ends — JS shift() is not.

console.log("\n=== 7. NO BUILT-IN PRIORITY QUEUE ===\n");

// Simple min-heap implementation
class MinHeap {
    constructor() { this.heap = []; }
    push(val) {
        this.heap.push(val);
        this.heap.sort((a, b) => a - b); // naive — not O(log n) but simple
    }
    pop()  { return this.heap.shift(); }
    peek() { return this.heap[0]; }
    size() { return this.heap.length; }
}

const pq = new MinHeap();
pq.push(5); pq.push(1); pq.push(3);
console.log("PriorityQueue poll order: " + pq.pop() + ", " + pq.pop() + ", " + pq.pop());
