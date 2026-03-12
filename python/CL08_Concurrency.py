"""
CL08: Concurrency in Python
Companion to CL08_ConcurrencyAcrossLanguages.java

Python has 3 concurrency approaches:
- threading: GIL limits CPU parallelism — best for I/O-bound tasks
- multiprocessing: true CPU parallelism (separate processes)
- asyncio: async/await for I/O concurrency — single-threaded event loop

Run: python3 CL08_Concurrency.py
"""

import threading
import time
import asyncio
from concurrent.futures import ThreadPoolExecutor, ProcessPoolExecutor

print("=== 1. THREAD BASICS ===\n")

def worker(name, delay=0.01):
    time.sleep(delay)
    print(f"  Thread {name} done (thread: {threading.current_thread().name})")

t1 = threading.Thread(target=worker, args=("A",))
t2 = threading.Thread(target=worker, args=("B",))
t1.start(); t2.start()
t1.join();  t2.join()   # wait for both to finish
print("Both threads completed")

print("\n=== 2. GIL LIMITATION ===\n")

# GIL = Global Interpreter Lock
# Only ONE thread runs Python bytecode at a time
# Threading helps for I/O-bound tasks (file, network) — NOT CPU-bound
print("Python GIL: only one thread runs at a time for CPU work")
print("Use threading for I/O-bound tasks (sleep, network, file)")
print("Use multiprocessing for CPU-bound tasks (computation)")

print("\n=== 3. THREAD POOL EXECUTOR ===\n")

def fetch_data(task_id):
    time.sleep(0.01)  # simulate I/O
    return f"data_{task_id}"

with ThreadPoolExecutor(max_workers=3) as executor:
    futures = [executor.submit(fetch_data, i) for i in range(5)]
    results = [f.result() for f in futures]
print(f"ThreadPool results: {results}")

print("\n=== 4. ASYNCIO — ASYNC/AWAIT ===\n")

async def async_fetch(task_id):
    await asyncio.sleep(0.01)   # non-blocking wait (unlike time.sleep)
    return f"async_data_{task_id}"

async def main():
    # Sequential (slow):
    r1 = await async_fetch(1)
    r2 = await async_fetch(2)
    print(f"Sequential: {[r1, r2]}")

    # Parallel with asyncio.gather (fast):
    results = await asyncio.gather(
        async_fetch(3),
        async_fetch(4),
        async_fetch(5)
    )
    print(f"Parallel:   {list(results)}")

asyncio.run(main())

print("\n=== 5. SYNCHRONIZATION — LOCK ===\n")

counter = 0
lock = threading.Lock()

def increment():
    global counter
    with lock:          # context manager — auto-releases on exit
        counter += 1

threads = [threading.Thread(target=increment) for _ in range(100)]
for t in threads: t.start()
for t in threads: t.join()
print(f"Counter with lock: {counter}")  # should always be 100

print("\n=== 6. THREAD-SAFE COUNTER WITH CONDITION ===\n")

class AtomicCounter:
    def __init__(self):
        self._value = 0
        self._lock = threading.Lock()

    def increment(self):
        with self._lock:
            self._value += 1

    @property
    def value(self):
        return self._value

atomic = AtomicCounter()
threads = [threading.Thread(target=atomic.increment) for _ in range(100)]
for t in threads: t.start()
for t in threads: t.join()
print(f"AtomicCounter: {atomic.value}")

print("\n=== KEY COMPARISON WITH JAVA ===\n")
print("Java: Thread → Python: threading.Thread")
print("Java: ExecutorService → Python: ThreadPoolExecutor")
print("Java: CompletableFuture → Python: asyncio coroutines")
print("Java: synchronized → Python: with lock:")
print("Java: AtomicInteger → Python: manual lock (no built-in atomic int)")
print("Java: no GIL → Python: GIL limits CPU thread parallelism")
