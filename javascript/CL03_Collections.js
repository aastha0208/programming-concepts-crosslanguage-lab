/**
 * CL03: Collections in JavaScript
 * Companion to CL03_CollectionsAcrossLanguages.java
 *
 * JS has Array (dynamic list), Object/Map (key-value), and Set (unique).
 * Arrays act as both lists, queues, and stacks.
 *
 * Run: node CL03_Collections.js
 */

console.log("=== 1. ARRAY (Java's ArrayList) ===\n");

const fruits = ["Apple", "Banana", "Cherry", "Banana"]; // duplicates OK
console.log(`Array: ${fruits}`);
console.log(`  [1]:            ${fruits[1]}`);              // get(1)
console.log(`  .length:        ${fruits.length}`);          // size — property!
console.log(`  includes():     ${fruits.includes("Apple")}`);  // contains()
console.log(`  indexOf():      ${fruits.indexOf("Banana")}`);  // first occurrence
console.log(`  lastIndexOf():  ${fruits.lastIndexOf("Banana")}`);

fruits.push("Date");            // add to end
fruits.unshift("Avocado");      // add to front (slow — shifts all elements)
fruits.splice(2, 1);            // remove 1 element at index 2
const last = fruits.pop();      // remove and return last
console.log(`after operations: ${fruits}`);

// Functional methods (return NEW arrays — immutable style)
const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9];
console.log(`filter evens:   ${nums.filter(n => n % 2 === 0)}`);
console.log(`map * 2:        ${nums.map(n => n * 2)}`);
console.log(`reduce (sum):   ${nums.reduce((acc, n) => acc + n, 0)}`);
console.log(`find > 5:       ${nums.find(n => n > 5)}`);
console.log(`some > 8:       ${nums.some(n => n > 8)}`);    // anyMatch
console.log(`every > 0:      ${nums.every(n => n > 0)}`);  // allMatch
console.log(`slice(2,5):     ${nums.slice(2, 5)}`);         // subList(2,5)

console.log("\n=== 2. MAP (Java's HashMap) ===\n");

// Option 1: Object (string/symbol keys only, simpler)
const obj = { Alice: 95, Bob: 87, Charlie: 92 };
obj.Bob = 90;                                  // put / overwrite
console.log(`Object: ${JSON.stringify(obj)}`);
console.log(`  obj['Bob']:          ${obj['Bob']}`);        // get()
console.log(`  obj.Alice:           ${obj.Alice}`);         // dot notation
console.log(`  'Alice' in obj:      ${'Alice' in obj}`);    // containsKey()
console.log(`  Object.keys():       ${Object.keys(obj)}`);  // keySet()
console.log(`  Object.values():     ${Object.values(obj)}`);// values()
for (const [k, v] of Object.entries(obj)) {               // entrySet()
    console.log(`    ${k} -> ${v}`);
}

// Option 2: Map (any key type, insertion order preserved)
const map = new Map();
map.set("Alice", 95);
map.set("Bob", 87);
map.set(42, "number key");   // non-string key — can't do this with Object
console.log(`Map size: ${map.size}`);               // size — property!
console.log(`get("Alice"): ${map.get("Alice")}`);
console.log(`has("Bob"):   ${map.has("Bob")}`);     // containsKey()
map.delete("Bob");
for (const [k, v] of map) {
    console.log(`  ${k} -> ${v}`);
}

console.log("\n=== 3. SET (Java's HashSet) ===\n");

const tags = new Set(["java", "programming", "java", "coding"]); // duplicate ignored
console.log(`Set: ${[...tags]}`);               // spread to array to print
console.log(`  has("java"):  ${tags.has("java")}`);  // contains()
console.log(`  size:         ${tags.size}`);          // property!
tags.add("javascript");
tags.delete("java");
console.log(`after add/delete: ${[...tags]}`);

// Set operations (no built-in operators, use spread)
const a = new Set([1, 2, 3, 4, 5]);
const b = new Set([4, 5, 6, 7, 8]);
const union        = new Set([...a, ...b]);
const intersection = new Set([...a].filter(x => b.has(x)));
const difference   = new Set([...a].filter(x => !b.has(x)));
console.log(`union:        ${[...union]}`);
console.log(`intersection: ${[...intersection]}`);
console.log(`difference:   ${[...difference]}`);

console.log("\n=== 4. QUEUE & STACK ===\n");

// Array as Queue (FIFO)
const queue = ["first", "second", "third"];
queue.push("fourth");         // enqueue (offer)
const front = queue.shift();  // dequeue (poll) — removes from front
console.log(`dequeued: ${front}, remaining: ${queue}`);

// Array as Stack (LIFO)
const stack = [];
stack.push("bottom");
stack.push("middle");
stack.push("top");
const top = stack.pop();      // pop from end
console.log(`popped: ${top}, remaining: ${stack}`);

console.log("\n=== 5. SORTING ===\n");

const nums2 = [5, 2, 8, 1, 9, 3];

// WARNING: default sort is LEXICOGRAPHIC (converts to strings!)
const badSort = [...nums2].sort();
console.log(`BAD sort (lexicographic): ${badSort}`); // [1, 2, 3, 5, 8, 9] happens to work here but...
const badSort2 = [10, 9, 2].sort();
console.log(`BAD sort [10,9,2]: ${badSort2}`);       // [10, 2, 9] — WRONG!

// Always use a comparator for numbers
const asc  = [...nums2].sort((a, b) => a - b);
const desc = [...nums2].sort((a, b) => b - a);
console.log(`sorted asc:  ${asc}`);
console.log(`sorted desc: ${desc}`);

const words = ["banana", "apple", "cherry"];
words.sort((a, b) => a.length - b.length);      // sort by length
console.log(`sorted by length: ${words}`);
words.sort((a, b) => a.localeCompare(b));        // sort alphabetically
console.log(`sorted alphabetically: ${words}`);

console.log("\n=== 6. DESTRUCTURING ===\n");

// Array destructuring
const [first, second, ...rest] = [1, 2, 3, 4, 5];
console.log(`first=${first}, second=${second}, rest=${rest}`);

// Object destructuring
const { Alice: aliceScore, Bob: bobScore = 0 } = obj;
console.log(`Alice=${aliceScore}, Bob=${bobScore}`);

// Swap variables
let x = 1, y = 2;
[x, y] = [y, x];
console.log(`after swap: x=${x}, y=${y}`);

console.log("\n=== 7. WHEN TO USE WHAT ===\n");
console.log("Array   → ordered, duplicates, fast end access     (ArrayList)");
console.log("Object  → key-value, string keys, fast access      (HashMap - simple cases)");
console.log("Map     → key-value, any key type, ordered         (LinkedHashMap)");
console.log("Set     → unique elements                          (HashSet)");
