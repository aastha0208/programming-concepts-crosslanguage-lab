/**
 * CL02: Strings in JavaScript
 * Companion to CL02_StringsAcrossLanguages.java
 *
 * JS strings are immutable primitives.
 * Template literals (backticks) are the modern way to format strings.
 *
 * Run: node CL02_Strings.js
 */

console.log("=== 1. STRING CREATION ===\n");

const s1 = "double quotes";
const s2 = 'single quotes';
const s3 = `template literal`;       // backtick — supports interpolation + multiline
const multi = `Line 1
Line 2
Line 3`;                             // multiline — no \n needed
console.log(multi);

console.log("\n=== 2. TEMPLATE LITERALS (interpolation) ===\n");

const name = "Alice";
const age = 30;
const score = 98.5;

// Template literals — like Python f-strings
console.log(`Name: ${name}, Age: ${age}`);
console.log(`Expression: ${2 + 2}`);          // expressions work inside ${}
console.log(`Upper: ${name.toUpperCase()}`);   // method calls too
console.log(`Multiline:
  Name: ${name}
  Age: ${age}`);

// Old style (avoid)
console.log("Name: " + name + ", Age: " + age);

console.log("\n=== 3. COMMON STRING METHODS ===\n");

const str = "  Hello, World!  ";
console.log(`original:        '${str}'`);
console.log(`length:          ${str.trim().length}`);         // property, not method!
console.log(`toUpperCase():   ${str.toUpperCase()}`);
console.log(`toLowerCase():   ${str.toLowerCase()}`);
console.log(`trim():          '${str.trim()}'`);
console.log(`trimStart():     '${str.trimStart()}'`);         // Java: stripLeading()
console.log(`trimEnd():       '${str.trimEnd()}'`);           // Java: stripTrailing()
console.log(`includes():      ${str.includes("World")}`);     // contains()
console.log(`startsWith():    ${str.trim().startsWith("Hello")}`);
console.log(`endsWith():      ${str.trim().endsWith("!")}`);
console.log(`indexOf():       ${str.indexOf("o")}`);
console.log(`lastIndexOf():   ${str.lastIndexOf("o")}`);
console.log(`slice(0,5):      '${str.trim().slice(0, 5)}'`);  // substring(0,5)
console.log(`slice(-6):       '${str.trim().slice(-6)}'`);    // last 6 chars
console.log(`replace():       ${str.replace("World", "JS")}`); // replaces FIRST only
console.log(`replaceAll():    ${"aabba".replaceAll("a","x")}`);// replaces ALL

console.log("\n=== 4. SPLIT & JOIN ===\n");

const csv = "apple,banana,cherry,date";
const parts = csv.split(",");
console.log(`split: ${JSON.stringify(parts)}`);

const joined = parts.join(" | ");
console.log(`join:  ${joined}`);

// Limit split
const limited = csv.split(",", 2);
console.log(`split limit 2: ${JSON.stringify(limited)}`);

console.log("\n=== 5. IMMUTABILITY ===\n");

const original = "hello";
const modified = original.toUpperCase();
console.log(`original unchanged: ${original}`);
console.log(`new string:         ${modified}`);

// Can't modify characters directly
try {
    "use strict";
    // original[0] = 'H'; // silently fails in non-strict, throws in strict
} catch (e) {
    console.log(`Error: ${e.message}`);
}

console.log("\n=== 6. STRING BUILDER EQUIVALENT ===\n");

// Use array + join() for efficient building
const parts2 = [];
for (let i = 0; i < 5; i++) {
    parts2.push(`item${i}`);
}
console.log(`Efficient: ${parts2.join(", ")}`);

console.log("\n=== 7. USEFUL EXTRAS ===\n");

// Repeat
console.log("ha".repeat(3));                  // "hahaha"

// Pad
console.log("42".padStart(6, "0"));           // "000042"
console.log("hi".padEnd(6, "!"));             // "hi!!!!"

// Check content
console.log("123".match(/^\d+$/));            // matches digits only

// Spread to characters (like toCharArray())
const chars = [..."hello"];
console.log(`chars: ${JSON.stringify(chars)}`);

// Palindrome check
const word = "racecar";
const reversed = [...word].reverse().join("");
console.log(`'${word}' palindrome: ${word === reversed}`);

// Count occurrences
const text = "mississippi";
const count = (text.match(/s/g) || []).length;
console.log(`'s' in '${text}': ${count}`);
