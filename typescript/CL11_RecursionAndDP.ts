/**
 * CL11: Recursion and Dynamic Programming in TypeScript
 * Companion to CL11_RecursionAndDPAcrossLanguages.java
 *
 * TypeScript adds types to memoization maps and DP arrays.
 * A generic memoize<T, R> utility is easy to write and reuse.
 *
 * Run: ts-node CL11_RecursionAndDP.ts
 */

console.log("=== 1. NAIVE RECURSION ===\n");

function fibNaive(n: number): number {
    if (n <= 1) return n;
    return fibNaive(n - 1) + fibNaive(n - 2);
}

console.log("fibNaive(10): " + fibNaive(10));

console.log("\n=== 2. MEMOIZATION WITH TYPED MAP ===\n");

const memo = new Map<number, number>();

function fibMemo(n: number): number {
    if (memo.has(n)) return memo.get(n)!;   // ! = non-null assertion
    if (n <= 1) return n;
    const result = fibMemo(n - 1) + fibMemo(n - 2);
    memo.set(n, result);
    return result;
}

console.log("fibMemo(40): " + fibMemo(40));
console.log("fibMemo(50): " + fibMemo(50));

console.log("\n=== 3. GENERIC MEMOIZE UTILITY ===\n");

// Reusable typed memoize function
function memoize<Args extends unknown[], R>(
    fn: (...args: Args) => R
): (...args: Args) => R {
    const cache = new Map<string, R>();
    return (...args: Args): R => {
        const key = JSON.stringify(args);
        if (cache.has(key)) return cache.get(key)!;
        const result = fn(...args);
        cache.set(key, result);
        return result;
    };
}

const fibFast = memoize((n: number): number => {
    if (n <= 1) return n;
    return fibFast(n - 1) + fibFast(n - 2);
});

console.log("memoized fib(45): " + fibFast(45));

console.log("\n=== 4. BOTTOM-UP DP ===\n");

function fibDP(n: number): number {
    if (n <= 1) return n;
    const dp: number[] = new Array(n + 1).fill(0);
    dp[1] = 1;
    for (let i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}

// Space-optimized
function fibOptimal(n: number): number {
    if (n <= 1) return n;
    let [prev, curr] = [0, 1];
    for (let i = 2; i <= n; i++) [prev, curr] = [curr, prev + curr];
    return curr;
}

console.log("fibDP(50):      " + fibDP(50));
console.log("fibOptimal(50): " + fibOptimal(50));

console.log("\n=== 5. COIN CHANGE DP ===\n");

function coinChange(coins: number[], amount: number): number {
    const dp: number[] = new Array(amount + 1).fill(Infinity);
    dp[0] = 0;
    for (let i = 1; i <= amount; i++) {
        for (const coin of coins) {
            if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
    }
    return dp[amount] === Infinity ? -1 : dp[amount];
}

console.log("coinChange([1,5,10], 27): " + coinChange([1, 5, 10], 27));
console.log("coinChange([2], 3):       " + coinChange([2], 3));

console.log("\n=== 6. KNAPSACK DP ===\n");

function knapsack(weights: number[], values: number[], capacity: number): number {
    const n = weights.length;
    const dp: number[][] = Array.from({ length: n + 1 }, () =>
        new Array(capacity + 1).fill(0));
    for (let i = 1; i <= n; i++) {
        for (let w = 0; w <= capacity; w++) {
            dp[i][w] = dp[i-1][w];
            if (weights[i-1] <= w)
                dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
        }
    }
    return dp[n][capacity];
}

const weights: number[] = [2, 3, 4, 5];
const values: number[]  = [3, 4, 5, 6];
console.log("knapsack max value: " + knapsack(weights, values, 8));

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Map<number,number> = Java HashMap<Integer,Long>");
console.log("number[]           = Java long[] (no int/long distinction)");
console.log("Generic memoize<Args,R> utility — Java requires separate classes");
console.log("No @lru_cache like Python — implement manually");
