/**
 * CL08: Concurrency in JavaScript
 * Companion to CL08_ConcurrencyAcrossLanguages.java
 *
 * JavaScript is SINGLE-THREADED with an event loop.
 * No threads, no locks needed for normal code.
 * Concurrency is achieved via async/await and Promises.
 *
 * Run: node CL08_Concurrency.js
 */

console.log("=== 1. EVENT LOOP — SINGLE THREAD ===\n");

// JS executes one thing at a time — the event loop queues callbacks
console.log("1 - synchronous start");

setTimeout(() => console.log("3 - timeout callback (async)"), 0);

Promise.resolve().then(() => console.log("2 - microtask (runs before timeout)"));

console.log("1 - synchronous end");
// Output order: start, end, microtask, timeout — even with 0ms delay!

console.log("\n=== 2. PROMISE ===\n");

function fetchData(id) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            if (id > 0) resolve(`data for id=${id}`);
            else reject(new Error("Invalid id"));
        }, 10);
    });
}

fetchData(1)
    .then(data => console.log("Promise resolved: " + data))
    .catch(err => console.log("Promise rejected: " + err.message));

fetchData(-1)
    .then(data => console.log("Should not reach: " + data))
    .catch(err => console.log("Promise rejected: " + err.message));

console.log("\n=== 3. ASYNC / AWAIT ===\n");

async function loadUser(id) {
    const data = await fetchData(id);   // waits without blocking the thread
    return data.toUpperCase();
}

// async functions always return a Promise
loadUser(42).then(result => console.log("async result: " + result));

// Error handling with async/await
async function safeLoad(id) {
    try {
        const data = await fetchData(id);
        return data;
    } catch (err) {
        return "fallback: " + err.message;
    }
}

safeLoad(-1).then(r => console.log("safeLoad: " + r));

console.log("\n=== 4. PROMISE.ALL — PARALLEL TASKS ===\n");

async function runParallel() {
    const start = Date.now();

    // Run all 3 fetches at the same time (not sequentially)
    const [a, b, c] = await Promise.all([
        fetchData(1),
        fetchData(2),
        fetchData(3)
    ]);

    const elapsed = Date.now() - start;
    console.log("Parallel results: " + [a, b, c]);
    console.log("Elapsed (all parallel, ~10ms): " + elapsed + "ms");
}

runParallel();

console.log("\n=== 5. PROMISE.ALLSETTLED — HANDLE MIXED RESULTS ===\n");

async function runMixed() {
    const results = await Promise.allSettled([
        fetchData(1),
        fetchData(-1),  // will reject
        fetchData(3)
    ]);

    results.forEach((r, i) => {
        if (r.status === "fulfilled") console.log(`Task ${i}: OK — ${r.value}`);
        else console.log(`Task ${i}: FAILED — ${r.reason.message}`);
    });
}

runMixed();

console.log("\n=== 6. SEQUENTIAL vs PARALLEL ===\n");

async function sequential() {
    const a = await fetchData(1);  // waits for 1 before starting 2
    const b = await fetchData(2);
    return [a, b];
}

async function parallel() {
    const [a, b] = await Promise.all([fetchData(1), fetchData(2)]); // both start at once
    return [a, b];
}

sequential().then(r => console.log("sequential: " + r));
parallel().then(r => console.log("parallel:   " + r));

console.log("\n=== 7. KEY DIFFERENCES FROM JAVA ===\n");

console.log("Java: true multi-threading, synchronized blocks, locks");
console.log("JS:   single-threaded event loop, no locks needed");
console.log("JS:   async I/O never blocks — await suspends only the async function");
console.log("JS:   Promise.all = Java CompletableFuture.allOf()");
console.log("JS:   for CPU work: use worker_threads (Node.js) or Web Workers (browser)");
