/**
 * CL01: Type System in JavaScript
 * Companion to CL01_TypeSystem.java
 *
 * JavaScript is dynamically typed and WEAKLY typed (coercion happens automatically).
 * Use 'let' and 'const' (never 'var').
 *
 * Run: node CL01_TypeSystem.js
 */

console.log("=== 1. VARIABLE DECLARATION ===\n");

let age = 25;                  // block-scoped, reassignable
const name = "Alice";          // block-scoped, cannot be reassigned
let salary = 75000.50;
let isActive = true;

console.log(`age=${age}, name=${name}, salary=${salary}, isActive=${isActive}`);
console.log(`typeof age: ${typeof age}`);         // "number"
console.log(`typeof name: ${typeof name}`);       // "string"
console.log(`typeof isActive: ${typeof isActive}`); // "boolean"

// var (function-scoped, hoisted — avoid in modern JS)
// var x = 10; // x is accessible throughout the function

console.log("\n=== 2. JAVASCRIPT'S TYPES ===\n");

// JS has only ONE numeric type for ALL numbers
console.log(typeof 42);        // "number"
console.log(typeof 3.14);      // "number"  (no int/double distinction!)
console.log(typeof 42n);       // "bigint"  (ES2020 for huge integers)

// number limits
console.log(`MAX_SAFE_INTEGER: ${Number.MAX_SAFE_INTEGER}`);  // 9007199254740991
console.log(`MAX_VALUE: ${Number.MAX_VALUE}`);

// string — single, double, or backtick (template literal)
const s1 = 'single quotes';
const s2 = "double quotes";
const s3 = `template literal: ${name}`;   // like Python f-strings
console.log(s3);

// undefined vs null — JS has TWO empty values!
let declared;
console.log(`declared but not assigned: ${declared}`);   // undefined
console.log(`typeof undefined: ${typeof undefined}`);    // "undefined"
console.log(`typeof null: ${typeof null}`);              // "object" <- famous JS bug!

console.log("\n=== 3. TYPE COERCION (JS-only quirk!) ===\n");

// JS tries to convert types automatically — often surprising!
console.log("'5' + 3 =", '5' + 3);       // "53"  (string wins with +)
console.log("'5' - 3 =", '5' - 3);       // 2     (numeric with -)
console.log("'5' * '3' =", '5' * '3');   // 15    (both converted to number)
console.log("true + 1 =", true + 1);     // 2     (true = 1)
console.log("false + 1 =", false + 1);   // 1     (false = 0)
console.log("'' == false:", '' == false); // true  (loose equality coerces!)
console.log("0 == false:", 0 == false);  // true

// Always use === (strict equality, no coercion)
console.log("'' === false:", '' === false); // false (strict!)
console.log("0 === false:", 0 === false);  // false

console.log("\n=== 4. EXPLICIT CONVERSION ===\n");

// To number
console.log(Number("42"));        // 42
console.log(Number("3.14"));      // 3.14
console.log(Number("abc"));       // NaN (Not a Number)
console.log(Number(true));        // 1
console.log(Number(false));       // 0
console.log(Number(null));        // 0
console.log(Number(undefined));   // NaN
console.log(parseInt("3.99"));    // 3   (truncates like Java (int) cast)
console.log(parseFloat("3.99"));  // 3.99

// To string
console.log(String(42));          // "42"
console.log(String(true));        // "true"
console.log((42).toString());     // "42"
console.log(`${42}`);             // "42" (template literal)

// To boolean — falsy values: 0, "", null, undefined, NaN, false
console.log(Boolean(0));          // false
console.log(Boolean(""));         // false
console.log(Boolean(null));       // false
console.log(Boolean(undefined));  // false
console.log(Boolean("hello"));    // true
console.log(Boolean(42));         // true

console.log("\n=== 5. NULL & UNDEFINED ===\n");

let x = null;           // intentionally empty
let y;                  // undefined — declared but not assigned

console.log(`null == undefined:  ${null == undefined}`);    // true (loose)
console.log(`null === undefined: ${null === undefined}`);   // false (strict)

// Optional chaining (?.) — safely access nested properties
const user = null;
console.log(`user?.name: ${user?.name}`);                  // undefined, no error
console.log(`user?.address?.city: ${user?.address?.city}`);// undefined, no error

// Nullish coalescing (??) — default if null/undefined
const username = null ?? "Guest";
console.log(`username: ${username}`);                      // "Guest"

console.log("\n=== 6. TYPE CHECKING ===\n");

console.log(typeof "hello");           // "string"
console.log(typeof 42);                // "number"
console.log(typeof true);              // "boolean"
console.log(typeof undefined);         // "undefined"
console.log(typeof null);              // "object" <- BUG in JS!
console.log(typeof {});                // "object"
console.log(typeof []);                // "object" (arrays are objects!)
console.log(typeof function(){});      // "function"

// instanceof for objects
console.log([] instanceof Array);      // true
console.log({} instanceof Object);     // true
console.log(Array.isArray([]));        // true  (preferred for arrays)

console.log("\n=== 7. CONSTANTS ===\n");

const TAX_RATE = 0.08;
const MAX_SIZE = 100;
console.log(`TAX_RATE=${TAX_RATE}, MAX_SIZE=${MAX_SIZE}`);

// const prevents reassignment, but objects can still mutate!
const arr = [1, 2, 3];
arr.push(4);                           // ALLOWED — modifying content
console.log(`const array after push: ${arr}`);
// arr = [5, 6, 7];                    // TypeError — reassignment not allowed
