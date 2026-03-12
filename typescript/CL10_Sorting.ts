/**
 * CL10: Sorting in TypeScript
 * Companion to CL10_SortingAcrossLanguages.java
 *
 * TypeScript types the comparator function, preventing the JS number/string gotcha.
 * A typed comparator (a: number, b: number) => number makes the intent explicit.
 *
 * Run: ts-node CL10_Sorting.ts
 */

console.log("=== 1. BASIC SORT WITH TYPED COMPARATOR ===\n");

const nums: number[] = [10, 9, 2, 1, 100];

// TypeScript types the comparator — makes numeric intent clear
const ascending  = (a: number, b: number): number => a - b;
const descending = (a: number, b: number): number => b - a;

console.log("ascending:  " + [...nums].sort(ascending));
console.log("descending: " + [...nums].sort(descending));

// String sort
const words: string[] = ["banana", "apple", "cherry", "date"];
console.log("strings:    " + [...words].sort());

console.log("\n=== 2. SORT BY PROPERTY ===\n");

interface Person {
    name: string;
    age:  number;
}

const people: Person[] = [
    { name: "Charlie", age: 30 },
    { name: "Alice",   age: 25 },
    { name: "Dave",    age: 25 },
    { name: "Bob",     age: 35 }
];

const byAge  = [...people].sort((a: Person, b: Person) => a.age - b.age);
const byName = [...people].sort((a: Person, b: Person) => a.name.localeCompare(b.name));

console.log("by age:  " + byAge.map(p => `${p.name}(${p.age})`));
console.log("by name: " + byName.map(p => p.name));

console.log("\n=== 3. MULTI-KEY SORT ===\n");

const byAgeThenName = [...people].sort(
    (a: Person, b: Person) => a.age - b.age || a.name.localeCompare(b.name)
);
console.log("age then name: " + byAgeThenName.map(p => `${p.name}(${p.age})`));

console.log("\n=== 4. GENERIC SORT FUNCTION ===\n");

// Type-safe generic sort utility
function sortBy<T>(arr: T[], keyFn: (item: T) => number | string): T[] {
    return [...arr].sort((a, b) => {
        const ka = keyFn(a), kb = keyFn(b);
        return ka < kb ? -1 : ka > kb ? 1 : 0;
    });
}

console.log("sortBy age:  " + sortBy(people, p => p.age).map(p => `${p.name}(${p.age})`));
console.log("sortBy name: " + sortBy(people, p => p.name).map(p => p.name));
console.log("sortBy len:  " + sortBy(words, w => w.length));

console.log("\n=== 5. TYPED BINARY SEARCH ===\n");

function binarySearch<T>(arr: T[], target: T, compareFn: (a: T, b: T) => number): number {
    let lo = 0, hi = arr.length - 1;
    while (lo <= hi) {
        const mid = (lo + hi) >> 1;
        const cmp = compareFn(arr[mid], target);
        if (cmp === 0) return mid;
        cmp < 0 ? (lo = mid + 1) : (hi = mid - 1);
    }
    return -1;
}

const sortedNums: number[] = [1, 3, 5, 7, 9, 11];
const numCmp = (a: number, b: number) => a - b;

console.log("binarySearch(7):  " + binarySearch(sortedNums, 7, numCmp));
console.log("binarySearch(6):  " + binarySearch(sortedNums, 6, numCmp) + " (not found)");

const sortedWords: string[] = ["apple", "banana", "cherry", "date"];
const strCmp = (a: string, b: string) => a.localeCompare(b);
console.log("binarySearch('cherry'): " + binarySearch(sortedWords, "cherry", strCmp));

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("TS comparator: (a: number, b: number) => number  (typed — safer)");
console.log("Java:          Comparator.comparingInt(...)       (method reference)");
console.log("No Comparable interface in TS — always use comparator function");
console.log("No Arrays.binarySearch — implement with generics");
console.log("Sort is always in-place — spread first to preserve original");
