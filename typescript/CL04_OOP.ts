/**
 * CL04: OOP in TypeScript
 * Companion to CL04_OOPAcrossLanguages.java
 *
 * TypeScript OOP is the closest to Java of all the languages here.
 * Classes, interfaces, access modifiers, and generics work similarly.
 * Key differences: structural typing, no abstract keyword (wait — it exists!),
 * multiple interface implementation, and no checked exceptions.
 *
 * Run: ts-node CL04_OOP.ts
 */

console.log("=== 1. CLASS AND CONSTRUCTOR ===\n");

class Animal {
    // Access modifiers: public, private, protected (same as Java)
    private name: string;
    protected sound: string;
    public species: string;

    constructor(name: string, sound: string, species: string) {
        this.name    = name;
        this.sound   = sound;
        this.species = species;
    }

    // Shorthand constructor (TS-specific — no Java equivalent)
    // constructor(private name: string, protected sound: string) {}

    speak(): string { return `${this.name} says ${this.sound}`; }
    getName(): string { return this.name; }
    toString(): string { return `${this.species}(${this.name})`; }
}

const animal = new Animal("Rex", "Roar", "Lion");
console.log(animal.speak());
console.log("" + animal);

console.log("\n=== 2. INHERITANCE ===\n");

class Dog extends Animal {
    private breed: string;

    constructor(name: string, breed: string) {
        super(name, "Woof", "Dog");  // must call super first — same as Java
        this.breed = breed;
    }

    // Override method
    speak(): string {
        return super.speak() + ` (${this.breed})`;
    }

    fetch(): string { return `${this.getName()} fetches the ball!`; }
}

const dog = new Dog("Buddy", "Labrador");
console.log(dog.speak());
console.log(dog.fetch());
console.log("is Animal: " + (dog instanceof Animal));  // true

console.log("\n=== 3. INTERFACE ===\n");

interface Flyable {
    fly(): string;
    altitude: number;
}

interface Swimmable {
    swim(): string;
}

// TypeScript supports implementing multiple interfaces (like Java)
class Duck extends Animal implements Flyable, Swimmable {
    altitude: number = 100;

    constructor(name: string) { super(name, "Quack", "Duck"); }

    fly(): string  { return `${this.getName()} flies at ${this.altitude}m`; }
    swim(): string { return `${this.getName()} swims`; }
}

const duck = new Duck("Donald");
console.log(duck.fly());
console.log(duck.swim());
console.log(duck.speak());

// Interface as type
const flyer: Flyable = duck;
console.log("flyer: " + flyer.fly());

console.log("\n=== 4. ABSTRACT CLASS ===\n");

abstract class Shape {
    abstract area(): number;       // must be implemented
    abstract perimeter(): number;

    describe(): string {
        return `Area: ${this.area().toFixed(2)}, Perimeter: ${this.perimeter().toFixed(2)}`;
    }
}

class Circle extends Shape {
    constructor(private radius: number) { super(); }
    area(): number      { return Math.PI * this.radius ** 2; }
    perimeter(): number { return 2 * Math.PI * this.radius; }
}

class Rectangle extends Shape {
    constructor(private width: number, private height: number) { super(); }
    area(): number      { return this.width * this.height; }
    perimeter(): number { return 2 * (this.width + this.height); }
}

const shapes: Shape[] = [new Circle(5), new Rectangle(4, 6)];
shapes.forEach(s => console.log(s.describe()));

console.log("\n=== 5. STRUCTURAL TYPING (unique to TS) ===\n");

// TS uses structural typing — if it has the right shape, it fits
// Java uses nominal typing — must explicitly extend/implement

interface Printable { print(): void; }

class Document {
    print(): void { console.log("  Printing document"); }
}

class Invoice {
    print(): void { console.log("  Printing invoice"); }
}

// Both work as Printable WITHOUT explicitly implementing Printable!
function printAll(items: Printable[]): void {
    items.forEach(item => item.print());
}

printAll([new Document(), new Invoice()]);
// In Java, both classes would need: implements Printable

console.log("\n=== 6. GETTERS AND SETTERS ===\n");

class BankAccount {
    private _balance: number = 0;

    get balance(): number { return this._balance; }

    set balance(amount: number) {
        if (amount < 0) throw new Error("Balance cannot be negative");
        this._balance = amount;
    }

    deposit(amount: number): void { this._balance += amount; }
}

const account = new BankAccount();
account.deposit(1000);
console.log("balance: " + account.balance);  // getter
account.balance = 500;                        // setter
console.log("after set: " + account.balance);

console.log("\n=== KEY DIFFERENCES FROM JAVA ===\n");
console.log("Structural typing: shape matters, not class name — Java is nominal");
console.log("Constructor param shorthand: constructor(private x: T) — no Java equiv");
console.log("Multiple interfaces: same as Java implements A, B, C");
console.log("Abstract classes: identical syntax to Java");
console.log("No 'final' keyword: use 'readonly' for properties");
