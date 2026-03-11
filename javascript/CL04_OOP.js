/**
 * CL04: OOP in JavaScript
 * Companion to CL04_OOPAcrossLanguages.java
 *
 * JS uses prototype-based inheritance under the hood.
 * ES6 'class' syntax is syntactic sugar over prototypes.
 * No interfaces — use duck typing or TypeScript.
 *
 * Run: node CL04_OOP.js
 */

console.log("=== 1. ENCAPSULATION ===\n");

class BankAccount {
    #owner;              // private field (ES2022, # prefix)
    #balance;

    constructor(owner, balance) {
        this.#owner = owner;
        this.#balance = balance;
    }

    get owner() { return this.#owner; }      // getter property
    get balance() { return this.#balance; }  // getter property

    deposit(amount) {
        if (amount > 0) {
            this.#balance += amount;
            console.log(`  Deposited $${amount} -> Balance: $${this.#balance}`);
        }
    }

    withdraw(amount) {
        if (amount > 0 && amount <= this.#balance) {
            this.#balance -= amount;
            console.log(`  Withdrew $${amount} -> Balance: $${this.#balance}`);
        } else {
            console.log("  Insufficient funds!");
        }
    }

    toString() {
        return `BankAccount(${this.#owner}, $${this.#balance})`;
    }
}

const acc = new BankAccount("Alice", 1000);
acc.deposit(500);
acc.withdraw(200);
console.log(`Balance: ${acc.balance}`);   // uses getter
// acc.#balance = -999;                   // SyntaxError — private field

console.log("\n=== 2. INHERITANCE ===\n");

class Animal {
    constructor(name) {
        this.name = name;
        console.log(`  Animal constructor: ${name}`);
    }

    speak() {
        return `${this.name} makes a sound`;
    }

    eat() {
        console.log(`  ${this.name} eats food`);
    }

    toString() {
        return `${this.constructor.name}(${this.name})`;
    }
}

class Dog extends Animal {
    constructor(name, breed) {
        super(name);               // MUST call super() first
        this.breed = breed;
        console.log(`  Dog constructor: ${breed}`);
    }

    speak() {                      // override parent method
        return `${this.name} barks! [${this.breed}]`;
    }

    fetch() {
        console.log(`  ${this.name} fetches!`);
    }
}

class Cat extends Animal {
    speak() {
        return `${this.name} meows!`;
    }
}

console.log("Creating a Dog:");
const dog = new Dog("Rex", "Labrador");
console.log(`${dog} says: ${dog.speak()}`);

console.log("\n=== 3. POLYMORPHISM ===\n");

const animals = [new Dog("Buddy", "Poodle"), new Cat("Whiskers"), new Dog("Max", "Beagle")];
for (const animal of animals) {
    console.log(`  ${animal}: ${animal.speak()}`);   // each calls its own speak()
}

// instanceof check
const myAnimal = new Dog("Fido", "Beagle");
console.log(`instanceof Dog:    ${myAnimal instanceof Dog}`);
console.log(`instanceof Animal: ${myAnimal instanceof Animal}`);

console.log("\n=== 4. DUCK TYPING (instead of interfaces) ===\n");

// JS has no interfaces — if it has the method, it works
class Duck extends Animal {
    speak() { return `${this.name} quacks!`; }
    fly()   { console.log(`  ${this.name} flies!`); }
    swim()  { console.log(`  ${this.name} swims!`); }
}

function makeItFly(obj) {
    if (typeof obj.fly === 'function') {
        obj.fly();
    } else {
        console.log(`  ${obj.name} can't fly!`);
    }
}

const duck = new Duck("Donald");
makeItFly(duck);              // works — duck has fly()
makeItFly(new Cat("Felix")); // doesn't — Cat has no fly()

console.log("\n=== 5. STATIC METHODS & PROPERTIES ===\n");

class MathUtils {
    static PI = 3.14159;              // static field

    static square(n) {                // static method
        return n * n;
    }

    static cube(n) {
        return n ** 3;
    }
}

console.log(`PI = ${MathUtils.PI}`);
console.log(`square(4) = ${MathUtils.square(4)}`);
console.log(`cube(3) = ${MathUtils.cube(3)}`);

console.log("\n=== 6. GETTERS & SETTERS ===\n");

class Temperature {
    #celsius;

    constructor(celsius) {
        this.#celsius = celsius;
    }

    get fahrenheit() {                           // computed getter
        return this.#celsius * 9/5 + 32;
    }

    set fahrenheit(f) {                          // setter with conversion
        this.#celsius = (f - 32) * 5/9;
    }

    get celsius() { return this.#celsius; }
    set celsius(c) { this.#celsius = c; }

    toString() { return `${this.#celsius}°C / ${this.fahrenheit}°F`; }
}

const temp = new Temperature(100);
console.log(`${temp}`);
temp.fahrenheit = 32;
console.log(`After setting 32°F: ${temp}`);

console.log("\n=== 7. KEY DIFFERENCES FROM JAVA ===\n");
console.log("1. No interfaces — use duck typing or TypeScript");
console.log("2. Private fields use # prefix (ES2022), not 'private' keyword");
console.log("3. No method overriding annotation needed — just redefine");
console.log("4. Prototype-based under the hood (class is syntactic sugar)");
console.log("5. 'this' binding is tricky in callbacks — use arrow functions");
console.log("6. Static fields use 'static' keyword (same as Java)");
console.log("7. get/set keywords for properties (like C# properties)");
