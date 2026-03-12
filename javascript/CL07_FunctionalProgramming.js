/**
 * CL07: Functional Programming in JavaScript
 * Companion to CL07_FunctionalProgrammingAcrossLanguages.java
 *
 * JavaScript has first-class functions — functions are values.
 * Array methods (.map, .filter, .reduce) are the primary FP tools.
 * Arrow functions provide concise lambda syntax.
 *
 * Run: node CL07_FunctionalProgramming.js
 */

console.log("=== 1. ARROW FUNCTIONS (lambdas) ===\n");

const square     = x => x * x;
const shout      = s => s.toUpperCase() + "!";
const add        = (a, b) => a + b;
const isEven     = x => x % 2 === 0;
const greet      = name => `Hello, ${name}`;

console.log("square(5):    " + square(5));
console.log("shout(hello): " + shout("hello"));
console.log("add(3,4):     " + add(3, 4));
console.log("isEven(4):    " + isEven(4));
console.log("greet:        " + greet("Alice"));

console.log("\n=== 2. MAP ===\n");

const nums = [1, 2, 3, 4, 5];

const squared  = nums.map(x => x * x);
const doubled  = nums.map(x => x * 2);
const asString = nums.map(String);    // method reference style

console.log("squared:  " + squared);
console.log("doubled:  " + doubled);
console.log("asString: " + asString);

console.log("\n=== 3. FILTER ===\n");

const evens    = nums.filter(x => x % 2 === 0);
const odds     = nums.filter(x => x % 2 !== 0);
const words    = ["apple", "fig", "banana", "kiwi", "cherry"];
const longWords = words.filter(w => w.length > 4);

console.log("evens:      " + evens);
console.log("odds:       " + odds);
console.log("longWords:  " + longWords);

console.log("\n=== 4. REDUCE ===\n");

const sum     = nums.reduce((acc, x) => acc + x, 0);
const product = nums.reduce((acc, x) => acc * x, 1);
const max     = nums.reduce((best, x) => x > best ? x : best);

// Build object from array using reduce
const wordLengths = words.reduce((obj, w) => {
    obj[w] = w.length;
    return obj;
}, {});

console.log("sum:         " + sum);
console.log("product:     " + product);
console.log("max:         " + max);
console.log("wordLengths: " + JSON.stringify(wordLengths));

console.log("\n=== 5. CHAINING ===\n");

const result = ["  hello  ", "world", "  java  ", "", "  js  "]
    .map(s => s.trim())
    .filter(s => s.length > 0)
    .map(s => s.toUpperCase())
    .sort();

console.log("chained: " + result);

console.log("\n=== 6. FIND, SOME, EVERY ===\n");

const people = [
    { name: "Alice", age: 25 },
    { name: "Bob",   age: 17 },
    { name: "Carol", age: 30 }
];

const firstAdult  = people.find(p => p.age >= 18);
const anyMinor    = people.some(p => p.age < 18);
const allAdults   = people.every(p => p.age >= 18);
const names       = people.map(p => p.name);
const totalAge    = people.reduce((sum, p) => sum + p.age, 0);

console.log("firstAdult:  " + JSON.stringify(firstAdult));
console.log("anyMinor:    " + anyMinor);
console.log("allAdults:   " + allAdults);
console.log("names:       " + names);
console.log("totalAge:    " + totalAge);

console.log("\n=== 7. FUNCTION COMPOSITION ===\n");

// Manual composition
const compose = (...fns) => x => fns.reduceRight((v, f) => f(v), x);
const pipe    = (...fns) => x => fns.reduce((v, f) => f(v), x);

const trim      = s => s.trim();
const toUpper   = s => s.toUpperCase();
const exclaim   = s => s + "!";

const shoutTrimmed = pipe(trim, toUpper, exclaim);
console.log("pipe result: " + shoutTrimmed("  hello  ")); // "HELLO!"

// Predicate composition
const and = (...preds) => x => preds.every(p => p(x));
const or  = (...preds) => x => preds.some(p => p(x));

const isPositive = x => x > 0;
const isEvenAndPositive = and(isEven, isPositive);
console.log("4 isEvenAndPositive:  " + isEvenAndPositive(4));
console.log("-2 isEvenAndPositive: " + isEvenAndPositive(-2));

console.log("\n=== 8. SPREAD & DESTRUCTURING ===\n");

// Immutable array operations using spread
const original = [1, 2, 3];
const withFour = [...original, 4];       // add without mutating
const withoutFirst = original.slice(1);  // remove first

console.log("original:    " + original);
console.log("withFour:    " + withFour);
console.log("withoutFirst:" + withoutFirst);

// Destructuring in function params
const sumCoords = ({ x, y }) => x + y;
console.log("sumCoords:   " + sumCoords({ x: 3, y: 4 }));
