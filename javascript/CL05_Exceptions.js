/**
 * CL05: Exception Handling in JavaScript
 * Companion to CL05_ExceptionsAcrossLanguages.java
 *
 * JS has NO checked exceptions.
 * Only ONE catch block per try (filter inside with instanceof).
 * 10 / 0 returns Infinity, NOT an exception!
 *
 * Run: node CL05_Exceptions.js
 */

console.log("=== 1. BASIC TRY/CATCH/FINALLY ===\n");

try {
    throw new Error("Something went wrong");
} catch (e) {
    console.log(`Caught: ${e.message}`);
    console.log(`Type:   ${e.constructor.name}`);
} finally {
    console.log("Finally always runs");
}

// NOTE: 10 / 0 does NOT throw in JS!
console.log(`\n10 / 0 = ${10 / 0}`);      // Infinity  (unlike Java/Python!)
console.log(`-10 / 0 = ${-10 / 0}`);      // -Infinity
console.log(`0 / 0 = ${0 / 0}`);          // NaN

console.log("\n=== 2. ERROR HIERARCHY ===\n");

console.log("Error");
console.log("  TypeError       <- wrong type (null.property, 'x' * {})");
console.log("  RangeError      <- value out of range ([1,2,3][10] gives undefined, not error!)");
console.log("  ReferenceError  <- using undeclared variable");
console.log("  SyntaxError     <- parse-time errors (caught before execution)");
console.log("  URIError        <- malformed URI");
console.log("  EvalError       <- eval() errors");
console.log("All are 'unchecked' — no forced handling");

console.log("\n=== 3. SINGLE CATCH BLOCK (filter inside) ===\n");

// JS only allows ONE catch block — filter with instanceof
function parseAndDivide(s, divisor) {
    try {
        const num = parseInt(s);
        if (isNaN(num)) throw new TypeError(`'${s}' is not a valid number`);
        if (divisor === 0) throw new RangeError("Cannot divide by zero");
        return num / divisor;
    } catch (e) {
        if (e instanceof TypeError) {
            console.log(`  TypeError: ${e.message}`);
        } else if (e instanceof RangeError) {
            console.log(`  RangeError: ${e.message}`);
        } else {
            throw e;    // re-throw if we can't handle it
        }
    }
}

parseAndDivide("abc", 2);
parseAndDivide("10", 0);
console.log(`parseAndDivide("10", 2) = ${parseAndDivide("10", 2)}`);

console.log("\n=== 4. CUSTOM ERRORS ===\n");

class InsufficientFundsError extends Error {
    constructor(shortfall) {
        super(`Insufficient funds. Short by $${shortfall.toFixed(2)}`);
        this.name = "InsufficientFundsError";   // important! else name = "Error"
        this.shortfall = shortfall;
    }
}

class InvalidInputError extends Error {
    constructor(message) {
        super(message);
        this.name = "InvalidInputError";
    }
}

function withdraw(balance, amount) {
    if (amount > balance) {
        throw new InsufficientFundsError(amount - balance);   // throw new ...
    }
    return balance - amount;
}

try {
    withdraw(100, 250);
} catch (e) {
    console.log(`${e.name}: ${e.message}`);
    console.log(`Shortfall: $${e.shortfall}`);
}

console.log("\n=== 5. ASYNC ERROR HANDLING ===\n");

// Modern JS is heavily async — errors in Promises need special handling
async function fetchData(shouldFail) {
    if (shouldFail) {
        throw new Error("Network request failed");
    }
    return { data: "success" };
}

// async/await with try/catch (like synchronous code)
async function run() {
    try {
        const result = await fetchData(true);
        console.log(`Data: ${result.data}`);
    } catch (e) {
        console.log(`Async error caught: ${e.message}`);
    }

    // Promise .catch() alternative
    await fetchData(true).catch(e => console.log(`Promise.catch: ${e.message}`));
}

run();

console.log("\n=== 6. FINALLY BEHAVIOR ===\n");

function methodWithReturn() {
    try {
        return "from try";
    } finally {
        console.log("  Finally runs before return!");
    }
}

console.log(`returned: ${methodWithReturn()}`);

console.log("\n=== 7. KEY DIFFERENCES FROM JAVA ===\n");
console.log("1. No checked exceptions — all are unchecked");
console.log("2. Only ONE catch block (filter with instanceof inside)");
console.log("3. 10 / 0 returns Infinity, not ArithmeticException!");
console.log("4. Must set error.name manually in custom errors");
console.log("5. No 'throws' in function signatures");
console.log("6. Async errors: use try/catch with await or .catch() on Promises");
