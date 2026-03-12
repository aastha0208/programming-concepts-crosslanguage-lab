/**
 * CL06: Generics in TypeScript
 * Companion to CL06_GenericsAcrossLanguages.java
 *
 * TypeScript generics are the closest to Java generics in syntax.
 * Types are enforced at COMPILE TIME but erased to plain JS at runtime.
 * Like Java (not like C#), typeof(T) does NOT work at runtime.
 *
 * Run: ts-node CL06_Generics.ts
 * Or:  tsc CL06_Generics.ts && node CL06_Generics.js
 */

console.log("=== 1. GENERIC CLASS ===\n");

class Box<T> {
    private value: T;
    constructor(value: T) { this.value = value; }
    get(): T { return this.value; }
    toString(): string { return `Box[${this.value}]`; }
}

const intBox = new Box<number>(42);
const strBox = new Box<string>("Hello");
console.log("int box:    " + intBox);
console.log("string box: " + strBox);

// TypeScript catches type errors at compile time:
// const wrong: Box<number> = new Box<string>("oops"); // TS Error!

console.log("\n=== 2. GENERIC FUNCTION WITH CONSTRAINT ===\n");

// T extends Comparable equivalent — T must have a compareTo-like op
function max<T extends number | string>(a: T, b: T): T {
    return a >= b ? a : b;
}

console.log("max(3, 7):            " + max(3, 7));
console.log("max('apple','banana'): " + max("apple", "banana"));

// Generic with interface constraint
interface Comparable<T> {
    compareTo(other: T): number;
}

function maxComparable<T extends Comparable<T>>(a: T, b: T): T {
    return a.compareTo(b) >= 0 ? a : b;
}

console.log("\n=== 3. BOUNDED TYPE PARAMETER ===\n");

// Equivalent of Java's <? extends Number>
function sumList<T extends number>(list: T[]): number {
    return list.reduce((acc, n) => acc + n, 0);
}

const ints: number[]  = [1, 2, 3, 4, 5];
const floats: number[] = [1.5, 2.5, 3.0];
console.log("sumList ints:   " + sumList(ints));
console.log("sumList floats: " + sumList(floats));

console.log("\n=== 4. GENERIC COLLECTIONS ===\n");

const scores = new Map<string, number[]>();
scores.set("Alice", [95, 87, 92]);
scores.set("Bob",   [78, 85]);

for (const [key, vals] of scores) {
    console.log(`  ${key}: ${vals.join(", ")}`);
}

// Record<K,V> — typed object map
const wordLengths: Record<string, number> = {};
["apple", "fig", "banana"].forEach(w => wordLengths[w] = w.length);
console.log("Record: " + JSON.stringify(wordLengths));

console.log("\n=== 5. GENERIC INTERFACE ===\n");

interface Repository<T> {
    findById(id: number): T | undefined;
    save(item: T): void;
    getAll(): T[];
}

interface User { id: number; name: string; }

class InMemoryUserRepo implements Repository<User> {
    private store: Map<number, User> = new Map();

    findById(id: number): User | undefined { return this.store.get(id); }
    save(user: User): void { this.store.set(user.id, user); }
    getAll(): User[] { return Array.from(this.store.values()); }
}

const repo = new InMemoryUserRepo();
repo.save({ id: 1, name: "Alice" });
repo.save({ id: 2, name: "Bob" });
console.log("findById(1): " + JSON.stringify(repo.findById(1)));
console.log("getAll:      " + JSON.stringify(repo.getAll()));

console.log("\n=== 6. TYPE ERASURE (same as Java) ===\n");

const b1 = new Box<number>(1);
const b2 = new Box<string>("x");
// At runtime (JS), both are just Box — types erased
console.log("Same constructor: " + (b1.constructor === b2.constructor)); // true

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Syntax:    Very similar — <T extends Bound> identical to Java");
console.log("Erasure:   Same as Java — types erased at runtime (unlike C#)");
console.log("Wildcards: No ? extends / ? super — use union types instead");
console.log("Utilities: Record<K,V>, Partial<T>, Readonly<T> built-in");
