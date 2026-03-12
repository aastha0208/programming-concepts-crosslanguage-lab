# CL11: Recursion & Dynamic Programming Across Languages

| Language | Memoization Approach |
|----------|----------------------|
| Java | `HashMap` manually |
| Python | `@functools.lru_cache` — one decorator, done |
| JavaScript | Plain object `{}` or `Map` |
| TypeScript | `Map<number, number>` |
| C# | `Dictionary<K,V>` |

---

## 1. Naive Recursion

**Java**
```java
long fib(int n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);  // O(2^n) — very slow
}
```

**Python**
```python
def fib(n):
    if n <= 1: return n
    return fib(n-1) + fib(n-2)
```

**JavaScript**
```javascript
function fib(n) {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}
```

**TypeScript**
```typescript
function fib(n: number): number {
    if (n <= 1) return n;
    return fib(n-1) + fib(n-2);
}
```

**C#**
```csharp
long Fib(int n) {
    if (n <= 1) return n;
    return Fib(n-1) + Fib(n-2);
}
```

---

## 2. Memoization (Top-Down DP)

**Java**
```java
Map<Integer, Long> memo = new HashMap<>();

long fib(int n) {
    if (n <= 1) return n;
    if (memo.containsKey(n)) return memo.get(n);
    long result = fib(n-1) + fib(n-2);
    memo.put(n, result);
    return result;
}
```

**Python** *(most concise — one decorator)*
```python
from functools import lru_cache

@lru_cache(maxsize=None)
def fib(n):
    if n <= 1: return n
    return fib(n-1) + fib(n-2)
```

**JavaScript**
```javascript
const memo = {};
function fib(n) {
    if (n in memo) return memo[n];
    if (n <= 1) return n;
    return memo[n] = fib(n-1) + fib(n-2);
}
```

**TypeScript**
```typescript
const memo = new Map<number, number>();
function fib(n: number): number {
    if (memo.has(n)) return memo.get(n)!;
    if (n <= 1) return n;
    const result = fib(n-1) + fib(n-2);
    memo.set(n, result);
    return result;
}
```

**C#**
```csharp
var memo = new Dictionary<int, long>();
long Fib(int n) {
    if (memo.ContainsKey(n)) return memo[n];
    if (n <= 1) return n;
    return memo[n] = Fib(n-1) + Fib(n-2);
}
```

---

## 3. Bottom-Up DP (Iterative — most efficient)

**Java**
```java
long fibDP(int n) {
    if (n <= 1) return n;
    long[] dp = new long[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}
```

**Python**
```python
def fib_dp(n):
    dp = [0] * (n + 1)
    dp[1] = 1
    for i in range(2, n + 1):
        dp[i] = dp[i-1] + dp[i-2]
    return dp[n]
```

**JavaScript**
```javascript
function fibDP(n) {
    const dp = new Array(n + 1).fill(0);
    dp[1] = 1;
    for (let i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}
```

**C#**
```csharp
long FibDP(int n) {
    long[] dp = new long[n + 1];
    dp[1] = 1;
    for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
    return dp[n];
}
```

---

## 4. Classic DP — 0/1 Knapsack

The logic is language-agnostic; only syntax differs.

**Java**
```java
int knapsack(int[] weights, int[] values, int capacity) {
    int n = weights.length;
    int[][] dp = new int[n + 1][capacity + 1];
    for (int i = 1; i <= n; i++)
        for (int w = 0; w <= capacity; w++) {
            dp[i][w] = dp[i-1][w];
            if (weights[i-1] <= w)
                dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
        }
    return dp[n][capacity];
}
```

**Python**
```python
def knapsack(weights, values, capacity):
    n = len(weights)
    dp = [[0] * (capacity + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for w in range(capacity + 1):
            dp[i][w] = dp[i-1][w]
            if weights[i-1] <= w:
                dp[i][w] = max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1])
    return dp[n][capacity]
```

**JavaScript**
```javascript
function knapsack(weights, values, capacity) {
    const n = weights.length;
    const dp = Array.from({length: n+1}, () => new Array(capacity+1).fill(0));
    for (let i = 1; i <= n; i++)
        for (let w = 0; w <= capacity; w++) {
            dp[i][w] = dp[i-1][w];
            if (weights[i-1] <= w)
                dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i-1]] + values[i-1]);
        }
    return dp[n][capacity];
}
```

---

## 5. Tail Recursion — Important Note

> **None of Java, Python, JavaScript, TypeScript, or C# optimize tail calls.**
> Use iterative DP or an explicit stack for deep recursion to avoid stack overflow.

| Language | Stack Overflow Risk | Workaround |
|----------|--------------------|----|
| Java | High (`StackOverflowError`) | Use iterative DP |
| Python | High (`RecursionError`) | `sys.setrecursionlimit()` or iteration |
| JavaScript | High | Iteration or trampolining |
| TypeScript | Same as JS | Same as JS |
| C# | High (`StackOverflowException`) | Iteration |
