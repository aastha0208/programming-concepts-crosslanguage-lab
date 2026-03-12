/**
 * CL01: Type System in TypeScript
 * Companion to CL01_TypeSystem.java
 *
 * TypeScript is statically typed like Java, but types are erased at runtime.
 * It adds a compile-time type layer on top of JavaScript.
 * Key difference from Java: structural typing (not nominal), union types, type inference.
 *
 * Run: ts-node CL01_TypeSystem.ts
 * Or:  tsc CL01_TypeSystem.ts && node CL01_TypeSystem.js
 */

console.log("=== 1. VARIABLE DECLARATION ===\n");

// TypeScript infers types — explicit annotation optional
let age: number = 25;
const name: string = "Alice";
let salary: number = 75000.50;
let isActive: boolean = true;

// Type inference — TS figures it out
let city = "New York";       // inferred as string
let count = 0;               // inferred as number

console.log(`age=${age}, name=${name}, salary=${salary}, isActive=${isActive}`);
console.log(`city type: ${typeof city}, count type: ${typeof count}`);

/*
 * COMPARISON:
 * Java:   int age = 25;         (must declare type)
 * C#:     int age = 25;         (same as Java)
 * Python: age = 25              (no declaration)
 * JS:     let age = 25;         (no type annotation)
 * TS:     let age: number = 25; (annotation optional — inference works)
 */

console.log("\n=== 2. TYPESCRIPT TYPES vs JAVA PRIMITIVES ===\n");

// TS has ONE number type for all numerics (like JS)
let integer: number = 42;
let float: number   = 3.14;
let bigint: bigint  = 9007199254740991n;  // BigInt for large numbers

console.log(`integer: ${integer}, float: ${float}`);
console.log(`bigint:  ${bigint}`);
console.log(`max safe int: ${Number.MAX_SAFE_INTEGER}`);

/*
 * Java         TypeScript
 * int          number
 * long         number / bigint
 * double       number
 * float        number
 * boolean      boolean
 * char         string (single char)
 * byte/short   number
 * String       string (lowercase)
 */

console.log("\n=== 3. TYPE ANNOTATIONS ===\n");

// Explicit annotations
let scores: number[]         = [95, 87, 92];
let pairs: [string, number]  = ["Alice", 95];   // tuple — fixed-length typed array
let anything: unknown        = 42;              // safer than 'any'
let flexible: any            = "could be anything"; // escape hatch — avoid!

console.log(`scores: ${scores}`);
console.log(`tuple:  ${pairs}`);

// Type assertion (like Java cast)
const input: unknown = "hello world";
const str = input as string;   // TS: 'as' keyword  |  Java: (String) cast
console.log(`asserted: ${str.toUpperCase()}`);

console.log("\n=== 4. UNION TYPES (no Java equivalent) ===\n");

// A value can be one of several types
let id: number | string = 123;
console.log("id as number: " + id);
id = "ABC-456";
console.log("id as string: " + id);

function printId(id: number | string): void {
    if (typeof id === "string") {
        console.log("string id: " + id.toUpperCase());
    } else {
        console.log("number id: " + id.toFixed(0));
    }
}
printId(42);
printId("xyz");

/*
 * Java has no union types — closest is Object or sealed classes (Java 17+)
 * TypeScript: number | string | null  — very common pattern
 */

console.log("\n=== 5. NULL AND UNDEFINED ===\n");

let nullable: string | null      = null;
let optional: string | undefined = undefined;

console.log("null:      " + nullable);
console.log("undefined: " + optional);

// Optional chaining (safe navigation)
const user = { profile: { name: "Alice" } };
console.log("optional chain: " + user?.profile?.name);    // "Alice"
console.log("nullish coalesce:" + (nullable ?? "default")); // "default"

/*
 * Java: String s = null; s.length() → NullPointerException
 * TS:   string | null forces you to handle null at compile time
 * TS:   ?. operator avoids null checks (Java has Optional<T>)
 */

console.log("\n=== 6. TYPE ALIASES AND INTERFACES ===\n");

// Type alias
type UserId = number | string;
type Point  = { x: number; y: number };

// Interface
interface User {
    id: UserId;
    name: string;
    email?: string;   // optional property
}

const point: Point = { x: 10, y: 20 };
const user2: User  = { id: 1, name: "Alice" };
const user3: User  = { id: "U-001", name: "Bob", email: "bob@example.com" };

console.log(`point: (${point.x}, ${point.y})`);
console.log(`user2: ${JSON.stringify(user2)}`);
console.log(`user3: ${JSON.stringify(user3)}`);

console.log("\n=== 7. CONSTANTS ===\n");

const MAX_SIZE: number  = 100;
const TAX_RATE: number  = 0.08;
const PI = Math.PI;      // inferred as number

// Literal type — even more specific than const
const direction: "north" | "south" | "east" | "west" = "north";
console.log(`direction: ${direction}`);
// direction = "up";  // TS Error — not in the literal union

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Structural typing: TS checks shape, not class name");
console.log("Union types:       number | string — no Java equivalent");
console.log("Optional chaining: ?. — Java uses Optional<T>");
console.log("Type erasure:      TS types gone at runtime (like Java generics)");
console.log("number:            one type for all numerics (no int/double split)");
