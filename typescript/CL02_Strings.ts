/**
 * CL02: Strings in TypeScript
 * Companion to CL02_StringsAcrossLanguages.java
 *
 * TypeScript strings are typed wrappers over JavaScript strings.
 * Immutable primitives — same as Java.
 * Template literals provide typed interpolation.
 * Literal types allow strings as type-safe enums.
 *
 * Run: ts-node CL02_Strings.ts
 */

console.log("=== 1. STRING DECLARATION ===\n");

const name: string   = "Alice";
const greeting: string = 'Hello';          // single quotes also fine
const multiline: string = `Line 1
Line 2
Line 3`;                                   // template literal (backticks)

console.log(name + ", " + greeting);
console.log("multiline:\n" + multiline);

console.log("\n=== 2. TEMPLATE LITERALS (typed interpolation) ===\n");

const age: number  = 25;
const score: number = 95.5;

// Template literal — TS checks types
const msg1: string = `Name: ${name}, Age: ${age}`;
const msg2: string = `Score: ${score.toFixed(1)}%`;
const expr: string = `2 + 2 = ${2 + 2}`;

console.log(msg1);
console.log(msg2);
console.log(expr);

/*
 * Java:   "Name: " + name + ", Age: " + age
 *         String.format("Name: %s, Age: %d", name, age)
 * Python: f"Name: {name}, Age: {age}"
 * C#:     $"Name: {name}, Age: {age}"
 * TS/JS:  `Name: ${name}, Age: ${age}`
 */

console.log("\n=== 3. COMMON STRING METHODS ===\n");

const str: string = "  Hello, TypeScript!  ";

console.log("length:      " + str.length);
console.log("trim:        '" + str.trim() + "'");
console.log("toUpper:     " + str.trim().toUpperCase());
console.log("toLower:     " + str.trim().toLowerCase());
console.log("includes:    " + str.includes("TypeScript"));
console.log("startsWith:  " + str.trim().startsWith("Hello"));
console.log("endsWith:    " + str.trim().endsWith("!"));
console.log("indexOf:     " + str.indexOf("Type"));
console.log("replace:     " + str.trim().replace("TypeScript", "World"));
console.log("split:       " + str.trim().split(", "));
console.log("slice:       " + str.trim().slice(7, 17));   // like Java substring

/*
 * Java vs TypeScript method comparison:
 * Java .substring(7,17)  → TS .slice(7,17)
 * Java .contains("x")   → TS .includes("x")
 * Java .charAt(0)        → TS [0] or .charAt(0)
 * Java .equals()         → TS === (strict equality)
 * Java .trim()           → TS .trim()
 */

console.log("\n=== 4. STRING IMMUTABILITY ===\n");

let s: string = "hello";
s = s.toUpperCase();    // creates a NEW string — same as Java
console.log("new string: " + s);

// String as primitive vs String object
console.log("primitive equality: " + ("abc" === "abc")); // true
console.log("typeof string:      " + typeof "hello");    // "string"

console.log("\n=== 5. LITERAL TYPES (TS-specific) ===\n");

// String literals as types — no Java equivalent
type Direction = "north" | "south" | "east" | "west";
type Status    = "pending" | "active" | "inactive";

function move(dir: Direction): string {
    return `Moving ${dir}`;
}

let status: Status = "active";
console.log(move("north"));
console.log("status: " + status);
// move("up");        // TS Error — "up" not assignable to Direction
// status = "done";   // TS Error — "done" not assignable to Status

console.log("\n=== 6. STRING PARSING AND CONVERSION ===\n");

const numStr: string = "42";
const floatStr: string = "3.14";
const boolStr: string = "true";

const parsed: number  = parseInt(numStr, 10);   // always specify radix
const parsedF: number = parseFloat(floatStr);
const num: number     = Number(numStr);          // alternative

console.log(`parseInt:   ${parsed}  (${typeof parsed})`);
console.log(`parseFloat: ${parsedF} (${typeof parsedF})`);
console.log(`Number():   ${num}     (${typeof num})`);

// Number to string
const n: number = 255;
console.log("toString():    " + n.toString());
console.log("toFixed(2):    " + n.toFixed(2));
console.log("toString(16):  " + n.toString(16));  // hex: "ff"
console.log("toString(2):   " + n.toString(2));   // binary

console.log("\n=== 7. TAGGED TEMPLATE LITERALS ===\n");

// TypeScript can type tag functions for template literals
function highlight(strings: TemplateStringsArray, ...values: unknown[]): string {
    return strings.reduce((result, str, i) =>
        result + str + (values[i] !== undefined ? `[${values[i]}]` : ""), "");
}

const item: string  = "TypeScript";
const version: number = 5;
console.log(highlight`Language: ${item}, Version: ${version}`);
