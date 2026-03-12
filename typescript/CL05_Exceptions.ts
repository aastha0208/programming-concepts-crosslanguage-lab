/**
 * CL05: Exception Handling in TypeScript
 * Companion to CL05_ExceptionsAcrossLanguages.java
 *
 * TypeScript has NO checked exceptions (like JavaScript).
 * catch(err) type is 'unknown' in strict mode — must narrow before use.
 * Custom errors extend Error class. No multi-catch — use instanceof instead.
 *
 * Run: ts-node CL05_Exceptions.ts
 */

console.log("=== 1. BASIC TRY/CATCH/FINALLY ===\n");

try {
    const result: number = 10 / 0;
    console.log("10/0 = " + result);           // Infinity — NOT an exception!
    throw new Error("Something went wrong");
} catch (err) {
    // In TS strict mode, err is 'unknown' — must narrow before using
    if (err instanceof Error) {
        console.log("caught: " + err.message);
    }
} finally {
    console.log("finally always runs");
}

/*
 * Java: catch (Exception e) { e.getMessage(); }  — e is typed as Exception
 * TS:   catch (err) { }                          — err is 'unknown' — must check!
 *
 * IMPORTANT: 10 / 0 = Infinity in TS/JS, NOT ArithmeticException like Java
 */

console.log("\n=== 2. UNKNOWN vs ANY IN CATCH ===\n");

function riskyOperation(): void {
    throw new TypeError("bad type");
}

try {
    riskyOperation();
} catch (err: unknown) {
    // Must narrow 'unknown' before using
    if (err instanceof TypeError) {
        console.log("TypeError: " + err.message);
    } else if (err instanceof Error) {
        console.log("Error: " + err.message);
    } else if (typeof err === "string") {
        console.log("String error: " + err);
    } else {
        console.log("Unknown error: " + String(err));
    }
}

console.log("\n=== 3. CUSTOM ERROR CLASSES ===\n");

// Custom error — extend Error (same pattern as Java extends Exception)
class ValidationError extends Error {
    readonly field: string;

    constructor(field: string, message: string) {
        super(message);
        this.name = "ValidationError";  // important — set name explicitly
        this.field = field;
    }
}

class NetworkError extends Error {
    readonly statusCode: number;

    constructor(statusCode: number, message: string) {
        super(message);
        this.name = "NetworkError";
        this.statusCode = statusCode;
    }
}

function validateAge(age: number): void {
    if (age < 0 || age > 150) {
        throw new ValidationError("age", `Invalid age: ${age}`);
    }
}

try {
    validateAge(-5);
} catch (err) {
    if (err instanceof ValidationError) {
        console.log(`ValidationError on '${err.field}': ${err.message}`);
    }
}

console.log("\n=== 4. NO CHECKED EXCEPTIONS ===\n");

// Java forces you to declare: void readFile() throws IOException
// TypeScript has NO such requirement — any function can throw anything

async function fetchData(url: string): Promise<string> {
    // No 'throws' declaration — caller doesn't know what might be thrown
    if (!url.startsWith("http")) throw new NetworkError(400, "Invalid URL");
    return `data from ${url}`;
}

// Must wrap in try/catch yourself — no compiler reminder
fetchData("ftp://bad")
    .then(data => console.log(data))
    .catch(err => {
        if (err instanceof NetworkError) {
            console.log(`NetworkError ${err.statusCode}: ${err.message}`);
        }
    });

console.log("\n=== 5. ERROR HANDLING PATTERNS ===\n");

// Pattern 1: Result type (functional alternative to exceptions)
type Result<T, E = Error> =
    | { success: true;  value: T }
    | { success: false; error: E };

function divide(a: number, b: number): Result<number> {
    if (b === 0) return { success: false, error: new Error("Division by zero") };
    return { success: true, value: a / b };
}

const r1 = divide(10, 2);
const r2 = divide(10, 0);

if (r1.success) console.log("10/2 = " + r1.value);
if (!r2.success) console.log("error: " + r2.error.message);

// Pattern 2: Optional return with undefined
function findUser(id: number): { name: string } | undefined {
    if (id === 1) return { name: "Alice" };
    return undefined;
}

const user = findUser(99);
if (user === undefined) console.log("user not found");

console.log("\n=== 6. ASSERTIONS ===\n");

// Non-null assertion (!) — tells TS "trust me, this is not null"
const element: string | null = "hello";
console.log("non-null assertion: " + element!.toUpperCase()); // use sparingly

// Type assertion
function getLength(value: unknown): number {
    if (typeof value === "string") return value.length;
    if (Array.isArray(value)) return value.length;
    return 0;
}
console.log("getLength('hello'): " + getLength("hello"));
console.log("getLength([1,2,3]): " + getLength([1, 2, 3]));

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("No checked exceptions — no 'throws' declaration on functions");
console.log("catch(err) is 'unknown' — must use instanceof before accessing");
console.log("10/0 = Infinity (not ArithmeticException like Java)");
console.log("Result<T,E> pattern common in TS — alternative to exceptions");
console.log("No multi-catch syntax — use if/else instanceof chain");
