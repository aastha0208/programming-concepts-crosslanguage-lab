package crosslang;

import java.util.HashMap;
import java.util.Map;

/**
 * CL11: Recursion & Dynamic Programming Across Languages
 *
 * Java       → Recursion, memoization with HashMap, bottom-up DP with arrays
 * Python     → Recursion, @functools.lru_cache for memoization, clean DP
 * JavaScript → Recursion, memoization with plain objects/Map
 * TypeScript → Same as JS with typed memo maps
 * C#         → Recursion, Dictionary<K,V> memoization, same DP patterns as Java
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL11_RecursionAndDPAcrossLanguages
 */
public class CL11_RecursionAndDPAcrossLanguages {

    // JAVA: memoization cache
    static Map<Integer, Long> memo = new HashMap<>();

    // JAVA: naive recursion (exponential time — slow for large n)
    static long fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // JAVA: memoized recursion (top-down DP)
    static long fibMemo(int n) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        long result = fibMemo(n - 1) + fibMemo(n - 2);
        memo.put(n, result);
        return result;
    }

    // JAVA: bottom-up DP (iterative, most efficient)
    static long fibDP(int n) {
        if (n <= 1) return n;
        long[] dp = new long[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }

    // JAVA: classic DP — 0/1 Knapsack
    static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                dp[i][w] = dp[i-1][w]; // don't take item i
                if (weights[i-1] <= w) {
                    dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
                }
            }
        }
        return dp[n][capacity];
    }

    public static void main(String[] args) {

        System.out.println("=== 1. NAIVE RECURSION ===\n");

        System.out.println("fib(10) naive: " + fibNaive(10));

        /*
         * PYTHON:
         *   def fib_naive(n):
         *       if n <= 1: return n
         *       return fib_naive(n-1) + fib_naive(n-2)
         *
         * JAVASCRIPT:
         *   function fibNaive(n) {
         *     if (n <= 1) return n;
         *     return fibNaive(n-1) + fibNaive(n-2);
         *   }
         *
         * TYPESCRIPT:
         *   function fibNaive(n: number): number {
         *     if (n <= 1) return n;
         *     return fibNaive(n-1) + fibNaive(n-2);
         *   }
         *
         * C#:
         *   long FibNaive(int n) {
         *     if (n <= 1) return n;
         *     return FibNaive(n-1) + FibNaive(n-2);
         *   }
         */

        System.out.println("\n=== 2. MEMOIZATION (Top-Down DP) ===\n");

        System.out.println("fib(40) memoized: " + fibMemo(40));

        /*
         * PYTHON — easiest memoization of all languages:
         *   from functools import lru_cache
         *
         *   @lru_cache(maxsize=None)   # one decorator — done!
         *   def fib(n):
         *       if n <= 1: return n
         *       return fib(n-1) + fib(n-2)
         *
         *   # Or manually with a dict:
         *   memo = {}
         *   def fib(n):
         *       if n in memo: return memo[n]
         *       if n <= 1: return n
         *       memo[n] = fib(n-1) + fib(n-2)
         *       return memo[n]
         *
         * JAVASCRIPT:
         *   const memo = {};
         *   function fib(n) {
         *     if (n in memo) return memo[n];
         *     if (n <= 1) return n;
         *     return memo[n] = fib(n-1) + fib(n-2);
         *   }
         *
         * TYPESCRIPT:
         *   const memo = new Map<number, number>();
         *   function fib(n: number): number {
         *     if (memo.has(n)) return memo.get(n)!;
         *     if (n <= 1) return n;
         *     const result = fib(n-1) + fib(n-2);
         *     memo.set(n, result);
         *     return result;
         *   }
         *
         * C#:
         *   var memo = new Dictionary<int, long>();
         *   long Fib(int n) {
         *     if (memo.ContainsKey(n)) return memo[n];
         *     if (n <= 1) return n;
         *     return memo[n] = Fib(n-1) + Fib(n-2);
         *   }
         */

        System.out.println("\n=== 3. BOTTOM-UP DP (Iterative) ===\n");

        System.out.println("fib(50) bottom-up: " + fibDP(50));

        /*
         * PYTHON:
         *   def fib_dp(n):
         *       dp = [0] * (n + 1)
         *       dp[1] = 1
         *       for i in range(2, n + 1):
         *           dp[i] = dp[i-1] + dp[i-2]
         *       return dp[n]
         *
         * JAVASCRIPT:
         *   function fibDP(n) {
         *     const dp = new Array(n + 1).fill(0);
         *     dp[1] = 1;
         *     for (let i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
         *     return dp[n];
         *   }
         *
         * TYPESCRIPT:
         *   function fibDP(n: number): number {
         *     const dp: number[] = new Array(n + 1).fill(0);
         *     dp[1] = 1;
         *     for (let i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
         *     return dp[n];
         *   }
         *
         * C#:
         *   long FibDP(int n) {
         *     long[] dp = new long[n + 1];
         *     dp[1] = 1;
         *     for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
         *     return dp[n];
         *   }
         */

        System.out.println("\n=== 4. KNAPSACK DP ===\n");

        int[] weights = {2, 3, 4, 5};
        int[] values  = {3, 4, 5, 6};
        int capacity  = 8;
        System.out.println("Knapsack max value: " + knapsack(weights, values, capacity));

        /*
         * Pattern is the same across all languages — only syntax differs:
         *
         * PYTHON:
         *   def knapsack(weights, values, capacity):
         *       n = len(weights)
         *       dp = [[0] * (capacity + 1) for _ in range(n + 1)]
         *       for i in range(1, n + 1):
         *           for w in range(capacity + 1):
         *               dp[i][w] = dp[i-1][w]
         *               if weights[i-1] <= w:
         *                   dp[i][w] = max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1])
         *       return dp[n][capacity]
         *
         * JAVASCRIPT:
         *   function knapsack(weights, values, capacity) {
         *     const n = weights.length;
         *     const dp = Array.from({length: n+1}, () => new Array(capacity+1).fill(0));
         *     for (let i = 1; i <= n; i++)
         *       for (let w = 0; w <= capacity; w++) {
         *         dp[i][w] = dp[i-1][w];
         *         if (weights[i-1] <= w)
         *           dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
         *       }
         *     return dp[n][capacity];
         *   }
         *
         * KEY INSIGHT: DP logic is language-agnostic — only the collection
         * initialization syntax and Math.max vs max() differ.
         */

        System.out.println("\n=== 5. TAIL RECURSION NOTE ===\n");

        System.out.println("Java does NOT optimize tail recursion.");
        System.out.println("Use iterative DP or explicit stack for deep recursion.");

        /*
         * PYTHON:   also no tail call optimization (TCO) — same risk of RecursionError
         *           sys.setrecursionlimit(10000) to raise limit
         *
         * JAVASCRIPT: TCO was in ES6 spec but most engines don't implement it
         *
         * TYPESCRIPT: same as JS
         *
         * C#:        no TCO — same recommendation: use iteration for deep recursion
         *
         * ONLY some functional languages (Haskell, Scala, Kotlin with tailrec) support TCO.
         */
    }
}
