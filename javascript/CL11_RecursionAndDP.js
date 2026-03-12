/**
 * CL11: Recursion and Dynamic Programming in JavaScript
 * Companion to CL11_RecursionAndDPAcrossLanguages.java
 *
 * JS supports recursion but has no tail-call optimization (in most engines).
 * Memoization uses a plain object {} or Map.
 *
 * Run: node CL11_RecursionAndDP.js
 */

console.log("=== 1. NAIVE RECURSION ===\n");

function fibNaive(n) {
    if (n <= 1) return n;
    return fibNaive(n - 1) + fibNaive(n - 2);  // O(2^n) — very slow
}

console.log("fib(10) naive: " + fibNaive(10));

console.log("\n=== 2. MEMOIZATION (Top-Down DP) ===\n");

// Using plain object as cache
const memo = {};
function fibMemo(n) {
    if (n in memo) return memo[n];
    if (n <= 1) return n;
    return memo[n] = fibMemo(n - 1) + fibMemo(n - 2);
}

console.log("fib(40) memoized: " + fibMemo(40));

// Generic memoize utility — wraps any function
function memoize(fn) {
    const cache = new Map();
    return function(...args) {
        const key = JSON.stringify(args);
        if (cache.has(key)) return cache.get(key);
        const result = fn.apply(this, args);
        cache.set(key, result);
        return result;
    };
}

const fibFast = memoize(function fib(n) {
    if (n <= 1) return n;
    return fibFast(n - 1) + fibFast(n - 2);
});

console.log("fib(45) generic memoize: " + fibFast(45));

console.log("\n=== 3. BOTTOM-UP DP (Iterative) ===\n");

function fibDP(n) {
    if (n <= 1) return n;
    const dp = new Array(n + 1).fill(0);
    dp[1] = 1;
    for (let i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}

console.log("fib(50) bottom-up: " + fibDP(50));

// Space-optimized: only keep last 2 values
function fibOptimal(n) {
    if (n <= 1) return n;
    let prev = 0, curr = 1;
    for (let i = 2; i <= n; i++) [prev, curr] = [curr, prev + curr];
    return curr;
}

console.log("fib(50) space-opt: " + fibOptimal(50));

console.log("\n=== 4. CLASSIC DP — COIN CHANGE ===\n");

function coinChange(coins, amount) {
    const dp = new Array(amount + 1).fill(Infinity);
    dp[0] = 0;
    for (let i = 1; i <= amount; i++) {
        for (const coin of coins) {
            if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
    }
    return dp[amount] === Infinity ? -1 : dp[amount];
}

console.log("coinChange([1,5,10], 27): " + coinChange([1, 5, 10], 27)); // 4 coins
console.log("coinChange([2], 3):       " + coinChange([2], 3));          // -1 impossible

console.log("\n=== 5. KNAPSACK DP ===\n");

function knapsack(weights, values, capacity) {
    const n = weights.length;
    const dp = Array.from({ length: n + 1 }, () => new Array(capacity + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        for (let w = 0; w <= capacity; w++) {
            dp[i][w] = dp[i-1][w];
            if (weights[i-1] <= w)
                dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
        }
    }
    return dp[n][capacity];
}

const weights = [2, 3, 4, 5];
const values  = [3, 4, 5, 6];
console.log("knapsack max value: " + knapsack(weights, values, 8));

console.log("\n=== 6. TAIL RECURSION NOTE ===\n");

// JS spec (ES6) includes TCO but most engines (V8/Node) DON'T implement it.
// Deep recursion will still cause: "Maximum call stack size exceeded"

console.log("JS has NO reliable tail call optimization.");
console.log("Use iterative DP or explicit stack for deep recursion.");
