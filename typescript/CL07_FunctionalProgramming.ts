/**
 * CL07: Functional Programming in TypeScript
 * Companion to CL07_FunctionalProgrammingAcrossLanguages.java
 *
 * TypeScript adds types to JavaScript's functional features.
 * Function type aliases and generic higher-order functions
 * make FP code safer without losing expressiveness.
 *
 * Run: ts-node CL07_FunctionalProgramming.ts
 */

console.log("=== 1. TYPED ARROW FUNCTIONS ===\n");

// Type aliases for function types
type Transform<T, R> = (value: T) => R;
type Predicate<T>    = (value: T) => boolean;
type BinaryOp<T>     = (a: T, b: T) => T;

const square:   Transform<number, number> = x => x * x;
const shout:    Transform<string, string> = s => s.toUpperCase() + "!";
const add:      BinaryOp<number>          = (a, b) => a + b;
const isEven:   Predicate<number>         = x => x % 2 === 0;

console.log("square(5):    " + square(5));
console.log("shout(hello): " + shout("hello"));
console.log("add(3,4):     " + add(3, 4));
console.log("isEven(4):    " + isEven(4));

console.log("\n=== 2. TYPED MAP ===\n");

const nums: number[] = [1, 2, 3, 4, 5];

const squared:  number[] = nums.map((x: number) => x * x);
const asString: string[] = nums.map((x: number) => x.toString());

console.log("squared:  " + squared);
console.log("asString: " + asString);

// Generic map function
function mapArray<T, R>(arr: T[], fn: (item: T) => R): R[] {
    return arr.map(fn);
}

console.log("generic map: " + mapArray(["a", "bb", "ccc"], s => s.length));

console.log("\n=== 3. TYPED FILTER ===\n");

const evens: number[] = nums.filter((x: number) => x % 2 === 0);
const words: string[] = ["apple", "fig", "banana", "kiwi"];
const longWords: string[] = words.filter(w => w.length > 4);

console.log("evens:      " + evens);
console.log("longWords:  " + longWords);

// Type guard in filter — narrows the type
const mixed: (string | number | null)[] = [1, "hello", null, 2, "world", null];
const strings: string[] = mixed.filter((x): x is string => typeof x === "string");
console.log("type-guarded strings: " + strings);

console.log("\n=== 4. TYPED REDUCE ===\n");

const sum: number = nums.reduce((acc: number, x: number) => acc + x, 0);
const wordLengths: Record<string, number> = words.reduce(
    (obj: Record<string, number>, w: string) => ({ ...obj, [w]: w.length }),
    {}
);

console.log("sum:         " + sum);
console.log("wordLengths: " + JSON.stringify(wordLengths));

console.log("\n=== 5. CHAINING ===\n");

const result: string[] = ["  hello  ", "world", "  ts  ", ""]
    .map((s: string) => s.trim())
    .filter((s: string) => s.length > 0)
    .map((s: string) => s.toUpperCase())
    .sort();

console.log("chained: " + result);

console.log("\n=== 6. GENERIC HIGHER-ORDER FUNCTIONS ===\n");

function compose<T>(...fns: Array<(x: T) => T>): (x: T) => T {
    return x => fns.reduceRight((v, f) => f(v), x);
}

function pipe<T>(...fns: Array<(x: T) => T>): (x: T) => T {
    return x => fns.reduce((v, f) => f(v), x);
}

const trim      = (s: string) => s.trim();
const toUpper   = (s: string) => s.toUpperCase();
const exclaim   = (s: string) => s + "!";

const shoutTrimmed = pipe(trim, toUpper, exclaim);
console.log("pipe result: " + shoutTrimmed("  hello  "));

console.log("\n=== 7. READONLY & IMMUTABILITY ===\n");

// TypeScript enforces immutability at compile time
const readonlyNums: ReadonlyArray<number> = [1, 2, 3];
// readonlyNums.push(4); // TS Error: Property 'push' does not exist on ReadonlyArray

const frozen: Readonly<{name: string; age: number}> = { name: "Alice", age: 25 };
// frozen.age = 26;  // TS Error: Cannot assign to 'age' because it is a read-only property

console.log("readonly array sum: " + readonlyNums.reduce((a, b) => a + b, 0));
console.log("frozen object:      " + JSON.stringify(frozen));
