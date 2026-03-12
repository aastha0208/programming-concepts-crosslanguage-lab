# CL08: Concurrency Across Languages

| Language | Model |
|----------|-------|
| Java | True multi-threading, `synchronized`, `ExecutorService`, `CompletableFuture` |
| Python | `threading` (GIL-limited for CPU tasks), `multiprocessing`, `asyncio` |
| JavaScript | Single-threaded event loop, `Promise`, `async/await` |
| TypeScript | Same as JS — typed `Promise<T>` and `async` functions |
| C# | `Thread`, `Task`, `async/await`, `Parallel`, `lock` keyword |

---

## 1. Creating a Thread

**Java**
```java
Thread t = new Thread(() -> System.out.println("running: " + Thread.currentThread().getName()));
t.start();
t.join(); // wait for completion
```

**Python**
```python
import threading
t = threading.Thread(target=lambda: print("thread running"))
t.start()
t.join()
# WARNING: GIL limits true CPU parallelism — use multiprocessing for CPU-bound tasks
```

**C#**
```csharp
var t = new Thread(() => Console.WriteLine("Thread running"));
t.Start();
t.Join();
```

**JavaScript / TypeScript** *(no threads — use Web Workers or async)*
```javascript
// Node.js worker_threads:
const { Worker } = require('worker_threads');
const worker = new Worker('./worker.js');
// Main thread stays single — use async/await for most concurrency needs
```

---

## 2. Thread Pool / Executor

**Java**
```java
ExecutorService pool = Executors.newFixedThreadPool(3);
for (int i = 0; i < 3; i++) {
    final int id = i;
    pool.submit(() -> System.out.println("Task " + id));
}
pool.shutdown();
```

**Python**
```python
from concurrent.futures import ThreadPoolExecutor
with ThreadPoolExecutor(max_workers=3) as executor:
    futures = [executor.submit(my_task, i) for i in range(3)]
```

**C#**
```csharp
// Task Parallel Library (preferred over manual ThreadPool):
Parallel.For(0, 3, i => Console.WriteLine($"Task {i}"));
```

**JavaScript / TypeScript** *(event loop — no pool needed)*
```javascript
const results = await Promise.all([task1(), task2(), task3()]);
```

---

## 3. Future / Promise / Task

**Java**
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "async result")
    .thenApply(String::toUpperCase);

String result = future.get(); // blocks until done
```

**Python (asyncio)**
```python
import asyncio

async def fetch_data():
    await asyncio.sleep(1)  # non-blocking wait
    return "data"

result = asyncio.run(fetch_data())
```

**JavaScript**
```javascript
// Promise:
const promise = new Promise((resolve) => setTimeout(() => resolve("data"), 1000));

// async/await (preferred):
async function fetchData() {
    const result = await someAsyncCall();
    return result;
}
```

**TypeScript**
```typescript
async function fetchData(): Promise<string> {
    const result: string = await someAsyncCall();
    return result;
}
```

**C#**
```csharp
async Task<string> FetchDataAsync() {
    await Task.Delay(1000); // non-blocking
    return "data";
}
string result = await FetchDataAsync();
```

---

## 4. Synchronization

**Java**
```java
// synchronized block:
synchronized (lock) { counter++; }

// Lock-free with AtomicInteger:
AtomicInteger counter = new AtomicInteger(0);
counter.incrementAndGet();
```

**Python**
```python
import threading
lock = threading.Lock()
with lock:   # context manager
    counter += 1
```

**C#**
```csharp
lock (_lock) { counter++; }              // same as Java synchronized
Interlocked.Increment(ref counter);     // same as Java AtomicInteger
```

**JavaScript / TypeScript** *(single-threaded — no locks needed for event loop)*
```javascript
// SharedArrayBuffer + Atomics for shared memory between Workers:
Atomics.add(sharedArray, 0, 1);
```

---

## 5. Key Differences Summary

| | Java | Python | JavaScript | TypeScript | C# |
|-|------|--------|------------|------------|-----|
| Threads | ✅ True | ⚠️ GIL-limited | ❌ Event loop only | ❌ Same as JS | ✅ True |
| async/await | ✅ CompletableFuture | ✅ asyncio | ✅ Native | ✅ Typed | ✅ First-class |
| Lock syntax | `synchronized` | `with lock:` | `Atomics` (Workers) | same as JS | `lock` keyword |
