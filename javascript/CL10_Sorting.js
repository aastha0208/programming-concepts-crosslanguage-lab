/**
 * CL10: Sorting in JavaScript
 * Companion to CL10_SortingAcrossLanguages.java
 *
 * IMPORTANT GOTCHA: JS default sort() converts elements to STRINGS.
 * Always provide a comparator for numeric sorts.
 *
 * Run: node CL10_Sorting.js
 */

console.log("=== 1. DEFAULT SORT GOTCHA ===\n");

const nums = [10, 9, 2, 1, 100];
console.log("default sort (WRONG for numbers): " + [...nums].sort());
// → [1, 10, 100, 2, 9]  because "10" < "2" lexicographically!

console.log("numeric sort (correct):           " + [...nums].sort((a, b) => a - b));
// → [1, 2, 9, 10, 100]

// String sort is fine with default:
const words = ["banana", "apple", "cherry", "date"];
console.log("string sort (default OK):         " + [...words].sort());

console.log("\n=== 2. NUMERIC SORT ===\n");

const arr = [5, 2, 8, 1, 9, 3];
arr.sort((a, b) => a - b);           // ascending
console.log("ascending:  " + arr);
arr.sort((a, b) => b - a);           // descending
console.log("descending: " + arr);

console.log("\n=== 3. SORT BY PROPERTY ===\n");

const people = [
    { name: "Charlie", age: 30 },
    { name: "Alice",   age: 25 },
    { name: "Bob",     age: 35 },
    { name: "Dave",    age: 25 }
];

// Sort by age
const byAge = [...people].sort((a, b) => a.age - b.age);
console.log("by age: " + byAge.map(p => `${p.name}(${p.age})`));

// Sort by name
const byName = [...people].sort((a, b) => a.name.localeCompare(b.name));
console.log("by name:" + byName.map(p => p.name));

console.log("\n=== 4. MULTI-KEY SORT ===\n");

// Sort by age, then by name (same age → alphabetical)
const multiKey = [...people].sort((a, b) =>
    a.age - b.age || a.name.localeCompare(b.name)
);
console.log("age then name: " + multiKey.map(p => `${p.name}(${p.age})`));

console.log("\n=== 5. SORT BY STRING LENGTH ===\n");

const byLength = [...words].sort((a, b) => a.length - b.length);
console.log("by length asc:  " + byLength);

const byLengthDesc = [...words].sort((a, b) => b.length - a.length);
console.log("by length desc: " + byLengthDesc);

// Multi-key: length then alphabetical
const byLengthThenAlpha = [...words].sort(
    (a, b) => a.length - b.length || a.localeCompare(b)
);
console.log("length then alpha: " + byLengthThenAlpha);

console.log("\n=== 6. SORT IS IN-PLACE — USE SPREAD TO PRESERVE ORIGINAL ===\n");

const original = [3, 1, 4, 1, 5];
const sorted   = [...original].sort((a, b) => a - b); // spread first
console.log("original: " + original); // unchanged
console.log("sorted:   " + sorted);

console.log("\n=== 7. BINARY SEARCH (no built-in — implement manually) ===\n");

function binarySearch(arr, target) {
    let lo = 0, hi = arr.length - 1;
    while (lo <= hi) {
        const mid = (lo + hi) >> 1;
        if (arr[mid] === target) return mid;
        arr[mid] < target ? (lo = mid + 1) : (hi = mid - 1);
    }
    return -1;  // not found (Java returns negative index, JS returns -1)
}

const sortedArr = [1, 3, 5, 7, 9, 11];
console.log("binarySearch(7):  index = " + binarySearch(sortedArr, 7));
console.log("binarySearch(6):  index = " + binarySearch(sortedArr, 6) + " (not found)");
console.log("binarySearch(11): index = " + binarySearch(sortedArr, 11));
