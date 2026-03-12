/**
 * CL06: Generics in JavaScript
 * Companion to CL06_GenericsAcrossLanguages.java
 *
 * JavaScript has NO generics — it uses duck typing instead.
 * Any container or function works with any type automatically.
 * JSDoc @template provides documentation hints but no enforcement.
 *
 * Run: node CL06_Generics.js
 */

console.log("=== 1. GENERIC-LIKE CONTAINER (duck typing) ===\n");

// JS: no type parameter needed — works for any type automatically
class Box {
    constructor(value) { this.value = value; }
    get() { return this.value; }
    toString() { return `Box[${this.value}]`; }
}

const intBox = new Box(42);
const strBox = new Box("Hello");
const arrBox = new Box([1, 2, 3]);

console.log("int box:    " + intBox);
console.log("string box: " + strBox);
console.log("array box:  " + arrBox);

// Nothing stops mixing types — no compile-time check
const mixedBox = new Box({ name: "Alice", age: 25 });
console.log("object box: " + JSON.stringify(mixedBox.get()));

console.log("\n=== 2. GENERIC-LIKE FUNCTION (works on any type) ===\n");

// JS: no <T extends Comparable> — just write the function
function max(a, b) {
    return a >= b ? a : b;
}

console.log("max(3, 7):          " + max(3, 7));
console.log("max('apple','banana'): " + max("apple", "banana"));
console.log("max(true, false):   " + max(true, false)); // works — no type guard

// With JSDoc hint (documentation only — not enforced):
/**
 * @template T
 * @param {T[]} list
 * @param {function(T, T): number} compareFn
 * @returns {T}
 */
function maxBy(list, compareFn) {
    return list.reduce((best, curr) => compareFn(curr, best) > 0 ? curr : best);
}

const nums = [3, 1, 9, 2, 7];
console.log("maxBy nums:  " + maxBy(nums, (a, b) => a - b));
console.log("maxBy words: " + maxBy(["banana", "fig", "apple"], (a, b) => a.length - b.length));

console.log("\n=== 3. GENERIC COLLECTIONS — Map and Array ===\n");

// JS Map works with any key/value type — no type parameter needed
const scores = new Map();
scores.set("Alice", 95);
scores.set("Bob", 87);
scores.set(42, "numeric key also works");

console.log("Alice: " + scores.get("Alice"));
console.log("Has Bob: " + scores.has("Bob"));
console.log("Numeric key: " + scores.get(42));

// Array is already generic-like
const typedArray = [1, 2, 3];          // behaves like int[]
const stringArray = ["a", "b", "c"];   // behaves like String[]
// No enforcement — can mix:
const mixed = [1, "two", true, null];
console.log("Mixed array: " + JSON.stringify(mixed)); // totally valid

console.log("\n=== 4. TYPE SAFETY COMPARISON ===\n");

// Java catches this at compile time:
//   Box<Integer> intBox = new Box<>(42);
//   intBox.get().length(); // compile error — Integer has no length()

// JS: no protection — runtime error instead
const numBox = new Box(42);
try {
    numBox.get().toUpperCase(); // runtime TypeError
} catch (e) {
    console.log("Runtime error (no compile-time check): " + e.message);
}

console.log("\n=== 5. WORKAROUND — Runtime type checking ===\n");

// If you need type safety in JS, add manual runtime checks
class TypedBox {
    constructor(value, expectedType) {
        if (typeof value !== expectedType) {
            throw new TypeError(`Expected ${expectedType}, got ${typeof value}`);
        }
        this.value = value;
    }
    get() { return this.value; }
}

const safeBox = new TypedBox(42, "number");
console.log("Safe box: " + safeBox.get());

try {
    new TypedBox("hello", "number"); // throws
} catch (e) {
    console.log("TypedBox caught: " + e.message);
}

console.log("\nKEY POINT: Use TypeScript if you want real generic type safety.");
