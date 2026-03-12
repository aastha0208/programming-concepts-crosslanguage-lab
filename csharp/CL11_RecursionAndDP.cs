// CL11: Recursion and Dynamic Programming in C#
// Companion to CL11_RecursionAndDPAcrossLanguages.java
//
// C# memoization uses Dictionary<K,V>.
// No tail-call optimization — use iterative DP for deep recursion.
// Pattern is nearly identical to Java — only syntax differences.
//
// Run: dotnet-script CL11_RecursionAndDP.cs

using System;
using System.Collections.Generic;

class CL11_RecursionAndDP
{
    static readonly Dictionary<int, long> memo = new Dictionary<int, long>();

    static long FibNaive(int n)
    {
        if (n <= 1) return n;
        return FibNaive(n - 1) + FibNaive(n - 2);   // O(2^n)
    }

    static long FibMemo(int n)
    {
        if (memo.ContainsKey(n)) return memo[n];
        if (n <= 1) return n;
        return memo[n] = FibMemo(n - 1) + FibMemo(n - 2);
    }

    static long FibDP(int n)
    {
        if (n <= 1) return n;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }

    static long FibOptimal(int n)
    {
        if (n <= 1) return n;
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) (prev, curr) = (curr, prev + curr);
        return curr;
    }

    static int CoinChange(int[] coins, int amount)
    {
        int[] dp = new int[amount + 1];
        Array.Fill(dp, amount + 1);  // infinity substitute
        dp[0] = 0;
        for (int i = 1; i <= amount; i++)
            foreach (int coin in coins)
                if (coin <= i) dp[i] = Math.Min(dp[i], dp[i - coin] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    static int Knapsack(int[] weights, int[] values, int capacity)
    {
        int n = weights.Length;
        int[,] dp = new int[n + 1, capacity + 1];
        for (int i = 1; i <= n; i++)
            for (int w = 0; w <= capacity; w++)
            {
                dp[i, w] = dp[i-1, w];
                if (weights[i-1] <= w)
                    dp[i, w] = Math.Max(dp[i, w], dp[i-1, w - weights[i-1]] + values[i-1]);
            }
        return dp[n, capacity];
    }

    static void Main()
    {
        Console.WriteLine("=== 1. NAIVE RECURSION ===\n");
        Console.WriteLine("FibNaive(10): " + FibNaive(10));

        Console.WriteLine("\n=== 2. MEMOIZATION (Top-Down DP) ===\n");
        Console.WriteLine("FibMemo(40):  " + FibMemo(40));
        Console.WriteLine("FibMemo(50):  " + FibMemo(50));

        Console.WriteLine("\n=== 3. BOTTOM-UP DP (Iterative) ===\n");
        Console.WriteLine("FibDP(50):      " + FibDP(50));
        Console.WriteLine("FibOptimal(50): " + FibOptimal(50));

        Console.WriteLine("\n=== 4. COIN CHANGE ===\n");
        Console.WriteLine("CoinChange([1,5,10], 27): " + CoinChange(new[]{1,5,10}, 27));
        Console.WriteLine("CoinChange([2], 3):        " + CoinChange(new[]{2}, 3));

        Console.WriteLine("\n=== 5. KNAPSACK ===\n");
        int[] weights = { 2, 3, 4, 5 };
        int[] values  = { 3, 4, 5, 6 };
        Console.WriteLine("Knapsack max value: " + Knapsack(weights, values, 8));

        Console.WriteLine("\n=== 6. TAIL RECURSION NOTE ===\n");
        Console.WriteLine("C# has no guaranteed tail-call optimization.");
        Console.WriteLine("Use iterative DP to avoid StackOverflowException.");

        Console.WriteLine("\n=== KEY SIMILARITIES WITH JAVA ===\n");
        Console.WriteLine("Memoization: Java HashMap → C# Dictionary<K,V>");
        Console.WriteLine("DP arrays:   Java int[] → C# int[]  (identical!)");
        Console.WriteLine("Math.max:    Java Math.max → C# Math.Max");
        Console.WriteLine("Pattern:     Nearly identical — mainly naming conventions differ");
    }
}
