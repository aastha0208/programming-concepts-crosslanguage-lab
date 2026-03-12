/**
 * CL08: Concurrency in TypeScript
 * Companion to CL08_ConcurrencyAcrossLanguages.java
 *
 * TypeScript adds type safety to JavaScript's async/await.
 * Promise<T> with explicit return types catches async errors early.
 * Same single-threaded event loop as JavaScript underneath.
 *
 * Run: ts-node CL08_Concurrency.ts
 */

console.log("=== 1. TYPED PROMISE ===\n");

// Promise<T> — explicit return type
function fetchData(id: number): Promise<string> {
    return new Promise<string>((resolve, reject) => {
        setTimeout(() => {
            if (id > 0) resolve(`data_${id}`);
            else reject(new Error(`Invalid id: ${id}`));
        }, 10);
    });
}

fetchData(1)
    .then((data: string) => console.log("resolved: " + data))
    .catch((err: Error) => console.log("rejected: " + err.message));

console.log("\n=== 2. ASYNC / AWAIT WITH TYPES ===\n");

async function loadUser(id: number): Promise<string> {
    const data: string = await fetchData(id);
    return data.toUpperCase();
}

loadUser(42).then((result: string) => console.log("async result: " + result));

// Error handling
async function safeLoad(id: number): Promise<string> {
    try {
        return await fetchData(id);
    } catch (err) {
        if (err instanceof Error) return "fallback: " + err.message;
        return "unknown error";
    }
}

safeLoad(-1).then(r => console.log("safeLoad: " + r));

console.log("\n=== 3. PROMISE.ALL WITH TYPED TUPLE ===\n");

async function runParallel(): Promise<void> {
    // TypeScript infers tuple type: [string, string, string]
    const [a, b, c]: [string, string, string] = await Promise.all([
        fetchData(1),
        fetchData(2),
        fetchData(3)
    ]);
    console.log("Parallel results: " + [a, b, c]);
}

runParallel();

console.log("\n=== 4. TYPED ASYNC INTERFACES ===\n");

interface DataService {
    fetch(id: number): Promise<string>;
    fetchAll(ids: number[]): Promise<string[]>;
}

class ApiService implements DataService {
    async fetch(id: number): Promise<string> {
        return fetchData(id);
    }

    async fetchAll(ids: number[]): Promise<string[]> {
        return Promise.all(ids.map(id => this.fetch(id)));
    }
}

const service = new ApiService();
service.fetchAll([1, 2, 3]).then(results => console.log("fetchAll: " + results));

console.log("\n=== 5. TYPED CALLBACK PATTERN ===\n");

type EventHandler<T> = (event: T) => void;

interface TypedEventBus<T> {
    on(handler: EventHandler<T>): void;
    emit(event: T): void;
}

class LoginBus implements TypedEventBus<{ userId: string; timestamp: number }> {
    private handlers: EventHandler<{ userId: string; timestamp: number }>[] = [];

    on(handler: EventHandler<{ userId: string; timestamp: number }>): void {
        this.handlers.push(handler);
    }

    emit(event: { userId: string; timestamp: number }): void {
        this.handlers.forEach(h => h(event));
    }
}

const bus = new LoginBus();
bus.on(e => console.log(`  User ${e.userId} logged in at ${e.timestamp}`));
bus.emit({ userId: "alice", timestamp: Date.now() });

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Java CompletableFuture<T> → TypeScript Promise<T>");
console.log("Java async is multi-thread → TS async is single-thread event loop");
console.log("Java checked exceptions   → TS: catch(err) is always 'unknown' type");
console.log("TypeScript adds: typed Promises, typed callbacks, typed async interfaces");
