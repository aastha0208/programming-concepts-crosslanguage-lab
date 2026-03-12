"""
CL11: Recursion and Dynamic Programming in Python
Companion to CL11_RecursionAndDPAcrossLanguages.java

Python's @lru_cache makes memoization trivially easy.
Python has no tail-call optimization — deep recursion raises RecursionError.
Use sys.setrecursionlimit() or iterative DP for deep problems.

Run: python3 CL11_RecursionAndDP.py
"""

import sys
from functools import lru_cache

print("=== 1. NAIVE RECURSION ===\n")

def fib_naive(n):
    if n <= 1: return n
    return fib_naive(n - 1) + fib_naive(n - 2)   # O(2^n)

print(f"fib_naive(10): {fib_naive(10)}")

print("\n=== 2. MEMOIZATION WITH @lru_cache ===\n")

# One decorator — Python handles all caching automatically
@lru_cache(maxsize=None)
def fib(n):
    if n <= 1: return n
    return fib(n - 1) + fib(n - 2)

print(f"fib(40): {fib(40)}")
print(f"fib(50): {fib(50)}")
print(f"cache info: {fib.cache_info()}")   # hits, misses, size

# Clear cache if needed
fib.cache_clear()

print("\n=== 3. MANUAL MEMOIZATION (dict) ===\n")

memo = {}
def fib_memo(n):
    if n in memo: return memo[n]
    if n <= 1: return n
    memo[n] = fib_memo(n - 1) + fib_memo(n - 2)
    return memo[n]

print(f"fib_memo(45): {fib_memo(45)}")

print("\n=== 4. BOTTOM-UP DP (iterative) ===\n")

def fib_dp(n):
    if n <= 1: return n
    dp = [0] * (n + 1)
    dp[1] = 1
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]

# Space-optimized: only keep last 2
def fib_optimal(n):
    if n <= 1: return n
    prev, curr = 0, 1
    for _ in range(2, n + 1):
        prev, curr = curr, prev + curr
    return curr

print(f"fib_dp(50):      {fib_dp(50)}")
print(f"fib_optimal(50): {fib_optimal(50)}")

print("\n=== 5. CLASSIC DP — COIN CHANGE ===\n")

def coin_change(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    for i in range(1, amount + 1):
        for coin in coins:
            if coin <= i:
                dp[i] = min(dp[i], dp[i - coin] + 1)
    return dp[amount] if dp[amount] != float('inf') else -1

print(f"coin_change([1,5,10], 27): {coin_change([1, 5, 10], 27)}")
print(f"coin_change([2], 3):       {coin_change([2], 3)}")

print("\n=== 6. KNAPSACK DP ===\n")

def knapsack(weights, values, capacity):
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            dp[i][w] = dp[i-1][w]
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1])
    return dp[n][capacity]

weights = [2, 3, 4, 5]
values  = [3, 4, 5, 6]
print(f"knapsack max value: {knapsack(weights, values, 8)}")

print("\n=== 7. RECURSION LIMIT ===\n")

print(f"default recursion limit: {sys.getrecursionlimit()}")
# sys.setrecursionlimit(10000)  # raise if needed

print("\nPython has NO tail-call optimization.")
print("Deep naive recursion raises RecursionError.")
print("Always prefer @lru_cache + recursion or iterative DP.")
