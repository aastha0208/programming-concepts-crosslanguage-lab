package crosslang;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CL08: Concurrency Across Languages
 *
 * Java       → Threads, synchronized, ExecutorService, CompletableFuture
 * Python     → threading (GIL-limited), multiprocessing, asyncio
 * JavaScript → Single-threaded, event loop, Promises, async/await
 * TypeScript → Same as JS (compiles to JS), typed Promises
 * C#         → Thread, Task, async/await, Parallel, lock keyword
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL08_ConcurrencyAcrossLanguages
 */
public class CL08_ConcurrencyAcrossLanguages {

    // JAVA: shared mutable state — needs synchronization
    static int unsafeCounter = 0;
    static AtomicInteger safeCounter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        System.out.println("=== 1. CREATING A THREAD ===\n");

        // JAVA: extend Thread or implement Runnable
        Thread t1 = new Thread(() -> System.out.println("Thread via lambda: " + Thread.currentThread().getName()));
        t1.start();
        t1.join(); // wait for completion

        /*
         * PYTHON:
         *   import threading
         *   t = threading.Thread(target=lambda: print("thread running"))
         *   t.start()
         *   t.join()
         *   # WARNING: Python GIL limits true CPU parallelism in threads
         *   # Use multiprocessing for CPU-bound tasks
         *
         * JAVASCRIPT (no threads — Web Workers in browser, worker_threads in Node):
         *   // Main thread is single — use async instead
         *   const { Worker } = require('worker_threads');
         *   const worker = new Worker('./worker.js');
         *
         * TYPESCRIPT: same as JS, typed Worker messages
         *
         * C#:
         *   var t = new Thread(() => Console.WriteLine("Thread running"));
         *   t.Start();
         *   t.Join();
         */

        System.out.println("\n=== 2. THREAD POOL / EXECUTOR ===\n");

        // JAVA: ExecutorService manages a pool of threads
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 3; i++) {
            final int taskId = i;
            pool.submit(() -> System.out.println("Task " + taskId + " on " + Thread.currentThread().getName()));
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        /*
         * PYTHON:
         *   from concurrent.futures import ThreadPoolExecutor
         *   with ThreadPoolExecutor(max_workers=3) as executor:
         *       futures = [executor.submit(my_task, i) for i in range(3)]
         *
         * JAVASCRIPT (no pool needed — event loop handles concurrency):
         *   // Use Promise.all for parallel async tasks
         *   const results = await Promise.all([task1(), task2(), task3()]);
         *
         * C#:
         *   var pool = new ThreadPool();
         *   // Preferred: Task Parallel Library
         *   Parallel.For(0, 3, i => Console.WriteLine($"Task {i}"));
         */

        System.out.println("\n=== 3. FUTURE / PROMISE / TASK ===\n");

        // JAVA: CompletableFuture for async operations
        CompletableFuture<String> future = CompletableFuture
            .supplyAsync(() -> "result from async task")
            .thenApply(s -> s.toUpperCase());

        System.out.println("Future result: " + future.get());

        /*
         * PYTHON (asyncio):
         *   import asyncio
         *   async def fetch_data():
         *       await asyncio.sleep(1)   # non-blocking wait
         *       return "data"
         *   result = asyncio.run(fetch_data())
         *
         * JAVASCRIPT (Promise):
         *   const promise = new Promise((resolve, reject) => {
         *     setTimeout(() => resolve("data"), 1000);
         *   });
         *   promise.then(data => console.log(data));
         *
         *   // async/await syntax (preferred):
         *   async function fetchData() {
         *     const result = await someAsyncCall();
         *     return result;
         *   }
         *
         * TYPESCRIPT:
         *   async function fetchData(): Promise<string> {
         *     const result: string = await someAsyncCall();
         *     return result;
         *   }
         *
         * C#:
         *   async Task<string> FetchDataAsync() {
         *     await Task.Delay(1000);   // non-blocking
         *     return "data";
         *   }
         *   string result = await FetchDataAsync();
         */

        System.out.println("\n=== 4. SYNCHRONIZATION ===\n");

        // JAVA: synchronized keyword prevents race conditions
        Object lock = new Object();
        Runnable increment = () -> {
            synchronized (lock) {
                unsafeCounter++;
            }
        };

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(increment);
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("synchronized counter: " + unsafeCounter);

        // JAVA: AtomicInteger — lock-free, faster for simple increments
        safeCounter.set(0);
        Thread[] atomicThreads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            atomicThreads[i] = new Thread(() -> safeCounter.incrementAndGet());
            atomicThreads[i].start();
        }
        for (Thread t : atomicThreads) t.join();
        System.out.println("atomic counter:       " + safeCounter.get());

        /*
         * PYTHON:
         *   import threading
         *   lock = threading.Lock()
         *   counter = 0
         *   def increment():
         *       global counter
         *       with lock:         # context manager for lock
         *           counter += 1
         *
         * JAVASCRIPT (no shared memory in event loop — race conditions rare):
         *   // SharedArrayBuffer + Atomics for shared memory between Workers:
         *   Atomics.add(sharedArray, 0, 1);
         *
         * C#:
         *   private readonly object _lock = new object();
         *   lock (_lock) { counter++; }           // same as Java synchronized
         *   Interlocked.Increment(ref counter);   // same as Java AtomicInteger
         */

        System.out.println("\n=== 5. KEY DIFFERENCES SUMMARY ===\n");

        System.out.println("Java:       True multi-threading, synchronized/locks, CompletableFuture");
        System.out.println("Python:     GIL limits CPU threads; use multiprocessing or asyncio");
        System.out.println("JavaScript: Single-threaded event loop; async/await for I/O concurrency");
        System.out.println("TypeScript: Same as JS — types on Promises and async functions");
        System.out.println("C#:         async/await is first-class; Task = Java CompletableFuture");
    }
}
