/**
 * CL12: Design Patterns in JavaScript
 * Companion to CL12_DesignPatternsAcrossLanguages.java
 *
 * JS patterns often use closures and functions instead of classes.
 * ES modules provide free singletons. Functions replace many interfaces.
 *
 * Run: node CL12_DesignPatterns.js
 */

console.log("=== 1. SINGLETON ===\n");

// JS approach 1: closure-based singleton
const DatabaseConnection = (() => {
    let instance = null;
    function createInstance() {
        return { url: "mongodb://localhost/db", query: (q) => `result of: ${q}` };
    }
    return {
        getInstance() {
            if (!instance) instance = createInstance();
            return instance;
        }
    };
})();

const db1 = DatabaseConnection.getInstance();
const db2 = DatabaseConnection.getInstance();
console.log("Same instance: " + (db1 === db2));  // true
console.log(db1.query("SELECT *"));

// JS approach 2: ES module (the simplest singleton)
// In a real module file (db.js):
//   let _instance = null;
//   export const getInstance = () => _instance ?? (_instance = createInstance());
// The module is cached after first import — automatically a singleton.

console.log("\n=== 2. STRATEGY ===\n");

// JS: just pass functions — no interface/class needed
const bubbleSort = data => {
    const arr = [...data].sort((a, b) => a - b); // simplified
    console.log("  BubbleSort: " + arr);
    return arr;
};

const quickSort = data => {
    const arr = [...data].sort((a, b) => a - b); // simplified
    console.log("  QuickSort: " + arr);
    return arr;
};

class Sorter {
    constructor(strategy) { this.strategy = strategy; }
    sort(data) { return this.strategy(data); }
}

const sorter = new Sorter(bubbleSort);
sorter.sort([5, 2, 8, 1]);
sorter.strategy = quickSort;  // swap strategy at runtime
sorter.sort([5, 2, 8, 1]);

console.log("\n=== 3. BUILDER ===\n");

// JS approach: object spread with defaults
function buildPizza(overrides = {}) {
    const defaults = { size: "medium", crust: "thin", cheese: false, pepperoni: false };
    return { ...defaults, ...overrides };
}

const pizza1 = buildPizza({ size: "large", cheese: true, pepperoni: true });
const pizza2 = buildPizza({ size: "small", crust: "thick" });
console.log("pizza1: " + JSON.stringify(pizza1));
console.log("pizza2: " + JSON.stringify(pizza2));

// Class-based builder (more verbose — usually not needed in JS)
class PizzaBuilder {
    #size; #crust = "thin"; #cheese = false; #pepperoni = false;
    constructor(size) { this.#size = size; }
    crust(c)     { this.#crust = c; return this; }
    withCheese() { this.#cheese = true; return this; }
    withPepperoni() { this.#pepperoni = true; return this; }
    build() {
        return { size: this.#size, crust: this.#crust, cheese: this.#cheese, pepperoni: this.#pepperoni };
    }
}

const pizza3 = new PizzaBuilder("large").crust("thick").withCheese().build();
console.log("pizza3: " + JSON.stringify(pizza3));

console.log("\n=== 4. OBSERVER ===\n");

// JS: store callbacks in an array
class EventBus {
    #listeners = {};

    on(event, callback) {
        if (!this.#listeners[event]) this.#listeners[event] = [];
        this.#listeners[event].push(callback);
    }

    off(event, callback) {
        if (this.#listeners[event])
            this.#listeners[event] = this.#listeners[event].filter(cb => cb !== callback);
    }

    emit(event, data) {
        (this.#listeners[event] || []).forEach(cb => cb(data));
    }
}

const bus = new EventBus();
const handler1 = data => console.log("  Listener A: " + data);
const handler2 = data => console.log("  Listener B: " + data);

bus.on("login", handler1);
bus.on("login", handler2);
bus.emit("login", "user_alice");

bus.off("login", handler1);
bus.emit("login", "user_bob");  // only handler2 fires

console.log("\n=== 5. FACTORY ===\n");

// JS: factory function (no class needed)
function createAnimal(type, name) {
    const sounds = { dog: "Woof", cat: "Meow", bird: "Tweet" };
    return {
        name,
        type,
        speak() { return `${name} says ${sounds[type] || "..."}`; }
    };
}

const dog  = createAnimal("dog", "Rex");
const cat  = createAnimal("cat", "Whiskers");
const bird = createAnimal("bird", "Tweety");

console.log(dog.speak());
console.log(cat.speak());
console.log(bird.speak());

console.log("\n=== KEY INSIGHTS ===\n");
console.log("Singleton  → ES modules are singletons for free");
console.log("Strategy   → Just pass functions — no interface/class needed");
console.log("Builder    → Object spread {...defaults, ...overrides} replaces Builder");
console.log("Observer   → Array of callbacks, or use Node's EventEmitter");
console.log("Factory    → Factory functions are idiomatic — classes optional");
