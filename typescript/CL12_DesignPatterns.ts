/**
 * CL12: Design Patterns in TypeScript
 * Companion to CL12_DesignPatternsAcrossLanguages.java
 *
 * TypeScript patterns are the closest to Java — classes, interfaces,
 * and access modifiers work the same way.
 * private constructor enforces Singleton; typed generics improve Observer.
 *
 * Run: ts-node CL12_DesignPatterns.ts
 */

console.log("=== 1. SINGLETON ===\n");

class DatabaseConnection {
    private static instance: DatabaseConnection;
    private readonly url: string;

    // private constructor — prevents new DatabaseConnection()
    private constructor(url: string) { this.url = url; }

    static getInstance(): DatabaseConnection {
        if (!DatabaseConnection.instance) {
            DatabaseConnection.instance = new DatabaseConnection("postgres://localhost/db");
        }
        return DatabaseConnection.instance;
    }

    query(sql: string): string { return `result of: ${sql}`; }
    toString(): string { return `DB[${this.url}]`; }
}

const db1 = DatabaseConnection.getInstance();
const db2 = DatabaseConnection.getInstance();
console.log("Same instance: " + (db1 === db2));
console.log(db1.query("SELECT *"));

console.log("\n=== 2. STRATEGY ===\n");

interface SortStrategy {
    sort(data: number[]): number[];
}

class BubbleSort implements SortStrategy {
    sort(data: number[]): number[] {
        const arr = [...data].sort((a, b) => a - b);
        console.log("  BubbleSort: " + arr);
        return arr;
    }
}

class QuickSort implements SortStrategy {
    sort(data: number[]): number[] {
        const arr = [...data].sort((a, b) => a - b);
        console.log("  QuickSort: " + arr);
        return arr;
    }
}

class Sorter {
    private strategy: SortStrategy;
    constructor(strategy: SortStrategy) { this.strategy = strategy; }
    setStrategy(s: SortStrategy): void { this.strategy = s; }
    sort(data: number[]): number[] { return this.strategy.sort(data); }
}

const sorter = new Sorter(new BubbleSort());
sorter.sort([5, 2, 8, 1]);
sorter.setStrategy(new QuickSort());
sorter.sort([5, 2, 8, 1]);

console.log("\n=== 3. BUILDER ===\n");

class Pizza {
    private constructor(
        public readonly size: string,
        public readonly crust: string,
        public readonly cheese: boolean,
        public readonly pepperoni: boolean
    ) {}

    toString(): string {
        return `Pizza[${this.size},${this.crust},cheese=${this.cheese},pepperoni=${this.pepperoni}]`;
    }

    static builder(size: string): PizzaBuilder { return new PizzaBuilder(size); }
}

class PizzaBuilder {
    private _crust = "thin";
    private _cheese = false;
    private _pepperoni = false;

    constructor(private _size: string) {}
    crust(c: string): this { this._crust = c; return this; }
    withCheese(): this { this._cheese = true; return this; }
    withPepperoni(): this { this._pepperoni = true; return this; }
    build(): Pizza { return new (Pizza as any)(this._size, this._crust, this._cheese, this._pepperoni); }
}

const pizza = Pizza.builder("large").crust("thick").withCheese().withPepperoni().build();
console.log(pizza.toString());

console.log("\n=== 4. OBSERVER WITH GENERICS ===\n");

// Typed generic Observer
type Observer<T> = (event: T) => void;

class EventBus<T> {
    private observers: Observer<T>[] = [];

    subscribe(observer: Observer<T>): void {
        this.observers.push(observer);
    }

    unsubscribe(observer: Observer<T>): void {
        this.observers = this.observers.filter(o => o !== observer);
    }

    publish(event: T): void {
        this.observers.forEach(o => o(event));
    }
}

interface LoginEvent { userId: string; timestamp: number; }

const loginBus = new EventBus<LoginEvent>();
const handlerA: Observer<LoginEvent> = e => console.log(`  Listener A: ${e.userId}`);
const handlerB: Observer<LoginEvent> = e => console.log(`  Listener B: ${e.userId}`);

loginBus.subscribe(handlerA);
loginBus.subscribe(handlerB);
loginBus.publish({ userId: "alice", timestamp: Date.now() });

loginBus.unsubscribe(handlerA);
loginBus.publish({ userId: "bob", timestamp: Date.now() });

console.log("\n=== 5. FACTORY ===\n");

interface Animal { speak(): string; }
class Dog implements Animal { speak(): string { return "Woof!"; } }
class Cat implements Animal { speak(): string { return "Meow!"; } }

function createAnimal(type: "dog" | "cat"): Animal {
    const map: Record<"dog" | "cat", new () => Animal> = { dog: Dog, cat: Cat };
    return new map[type]();
}

const dog = createAnimal("dog");
const cat = createAnimal("cat");
console.log("dog: " + dog.speak());
console.log("cat: " + cat.speak());

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Syntax:    Very similar — private constructor, implements, interface");
console.log("Observer:  Generic EventBus<T> works better than Java's raw Observer");
console.log("Builder:   'return this' type works well with TypeScript's type system");
console.log("Factory:   Record<K, Constructor> is a clean TS alternative");
console.log("No event:  No C# event keyword — use typed callback arrays");
