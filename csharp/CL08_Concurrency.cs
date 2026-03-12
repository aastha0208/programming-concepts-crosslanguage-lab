// CL08: Concurrency in C#
// Companion to CL08_ConcurrencyAcrossLanguages.java
//
// C# has first-class async/await support.
// Task<T> is the closest equivalent to Java's CompletableFuture<T>.
// lock keyword = Java synchronized; Interlocked = Java AtomicInteger.
//
// Run: dotnet-script CL08_Concurrency.cs

using System;
using System.Collections.Generic;
using System.Threading;
using System.Threading.Tasks;

class CL08_Concurrency
{
    static int counter = 0;
    static readonly object lockObj = new object();

    static void Main()
    {
        Console.WriteLine("=== 1. THREAD BASICS ===\n");

        var t1 = new Thread(() =>
            Console.WriteLine($"  Thread A: {Thread.CurrentThread.ManagedThreadId}"));
        var t2 = new Thread(() =>
            Console.WriteLine($"  Thread B: {Thread.CurrentThread.ManagedThreadId}"));

        t1.Start(); t2.Start();
        t1.Join(); t2.Join();
        Console.WriteLine("Both threads done");

        Console.WriteLine("\n=== 2. TASK (Thread Pool) ===\n");

        // Task.Run uses the thread pool — preferred over new Thread()
        var tasks = new List<Task>();
        for (int i = 0; i < 3; i++)
        {
            int id = i;
            tasks.Add(Task.Run(() =>
                Console.WriteLine($"  Task {id} on thread {Thread.CurrentThread.ManagedThreadId}")));
        }
        Task.WaitAll(tasks.ToArray());
        Console.WriteLine("All tasks done");

        Console.WriteLine("\n=== 3. ASYNC / AWAIT ===\n");

        RunAsync().Wait();   // blocking wait in Main (use await in async Main)

        Console.WriteLine("\n=== 4. SYNCHRONIZATION ===\n");

        counter = 0;
        var threads = new Thread[100];
        for (int i = 0; i < 100; i++)
        {
            threads[i] = new Thread(() =>
            {
                lock (lockObj) { counter++; }   // same as Java synchronized
            });
            threads[i].Start();
        }
        foreach (var t in threads) t.Join();
        Console.WriteLine($"lock counter: {counter}");

        Console.WriteLine("\n=== 5. INTERLOCKED (atomic operations) ===\n");

        int atomicCounter = 0;
        var atomicThreads = new Thread[100];
        for (int i = 0; i < 100; i++)
        {
            atomicThreads[i] = new Thread(() =>
                Interlocked.Increment(ref atomicCounter));
            atomicThreads[i].Start();
        }
        foreach (var t in atomicThreads) t.Join();
        Console.WriteLine($"Interlocked counter: {atomicCounter}");

        Console.WriteLine("\n=== KEY DIFFERENCES FROM JAVA ===\n");
        Console.WriteLine("Java CompletableFuture  → C# Task<T>");
        Console.WriteLine("Java synchronized       → C# lock keyword");
        Console.WriteLine("Java AtomicInteger      → C# Interlocked.Increment");
        Console.WriteLine("Java ExecutorService    → C# Task.Run / ThreadPool");
        Console.WriteLine("Java Thread.join()      → C# task.Wait() / await task");
    }

    static async Task RunAsync()
    {
        Console.WriteLine("=== ASYNC METHODS ===\n");

        // Async method returning Task<T>
        string result = await FetchDataAsync(1);
        Console.WriteLine("  awaited: " + result);

        // Task.WhenAll — run multiple tasks in parallel
        var tasks = await Task.WhenAll(
            FetchDataAsync(1),
            FetchDataAsync(2),
            FetchDataAsync(3)
        );
        Console.WriteLine("  parallel: " + string.Join(", ", tasks));

        // Error handling
        try
        {
            await FetchDataAsync(-1);
        }
        catch (ArgumentException ex)
        {
            Console.WriteLine("  caught: " + ex.Message);
        }
    }

    static async Task<string> FetchDataAsync(int id)
    {
        await Task.Delay(10);   // non-blocking wait (like Java Thread.sleep but async)
        if (id < 0) throw new ArgumentException("Invalid id");
        return $"data_{id}";
    }
}
