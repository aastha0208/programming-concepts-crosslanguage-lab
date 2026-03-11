package basics;

/**
 * Topic 6: Object-Oriented Programming Basics
 *
 * Covers classes, objects, constructors, inheritance, polymorphism,
 * abstraction, encapsulation, and interfaces.
 */
public class T06_OOPBasics {

    // === ENCAPSULATION: private fields + public getters/setters ===
    static class BankAccount {
        private String owner;
        private double balance;

        public BankAccount(String owner, double initialBalance) {
            this.owner = owner;
            this.balance = initialBalance;
        }

        public String getOwner() { return owner; }

        public double getBalance() { return balance; }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("  Deposited $" + amount + " -> Balance: $" + balance);
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("  Withdrew $" + amount + " -> Balance: $" + balance);
            } else {
                System.out.println("  Insufficient funds!");
            }
        }
    }

    // === INHERITANCE: extends keyword ===
    static class Animal {
        String name;

        Animal(String name) {
            this.name = name;
            System.out.println("  Animal constructor: " + name);
        }

        void speak() {
            System.out.println("  " + name + " makes a sound");
        }

        void eat() {
            System.out.println("  " + name + " eats food");
        }
    }

    static class Dog extends Animal {
        String breed;

        Dog(String name, String breed) {
            super(name); // MUST be first line in constructor!
            this.breed = breed;
            System.out.println("  Dog constructor: " + breed);
        }

        @Override // Polymorphism: overriding parent method
        void speak() {
            System.out.println("  " + name + " barks! (breed: " + breed + ")");
        }

        void fetch() { // Dog-specific method
            System.out.println("  " + name + " fetches the ball!");
        }
    }

    static class Cat extends Animal {
        Cat(String name) { super(name); }

        @Override
        void speak() {
            System.out.println("  " + name + " meows!");
        }
    }

    // === ABSTRACT CLASS: can't be instantiated ===
    static abstract class Shape {
        String color;

        Shape(String color) { this.color = color; }

        abstract double area(); // Subclasses MUST implement this

        void describe() { // Concrete method - inherited as-is
            System.out.println("  " + color + " shape, area=" + String.format("%.2f", area()));
        }
    }

    static class Circle extends Shape {
        double radius;

        Circle(String color, double radius) {
            super(color);
            this.radius = radius;
        }

        @Override
        double area() { return Math.PI * radius * radius; }
    }

    static class Rectangle extends Shape {
        double width, height;

        Rectangle(String color, double width, double height) {
            super(color);
            this.width = width;
            this.height = height;
        }

        @Override
        double area() { return width * height; }
    }

    // === INTERFACE: contract that classes must follow ===
    interface Drawable {
        void draw(); // implicitly public abstract

        default void erase() { // Default method (Java 8+)
            System.out.println("  Erasing...");
        }
    }

    interface Resizable {
        void resize(double factor);
    }

    // A class can implement MULTIPLE interfaces (but extend only ONE class)
    static class Square extends Shape implements Drawable, Resizable {
        double side;

        Square(String color, double side) {
            super(color);
            this.side = side;
        }

        @Override
        double area() { return side * side; }

        @Override
        public void draw() {
            System.out.println("  Drawing a " + color + " square (side=" + side + ")");
        }

        @Override
        public void resize(double factor) {
            side *= factor;
            System.out.println("  Resized square to side=" + side);
        }
    }

    // === MAIN ===
    public static void main(String[] args) {

        System.out.println("=== 1. ENCAPSULATION ===\n");
        BankAccount acc = new BankAccount("Alice", 1000);
        acc.deposit(500);
        acc.withdraw(200);
        acc.withdraw(5000); // Insufficient
        // acc.balance = -999; // Can't! It's private

        System.out.println("\n=== 2. INHERITANCE & CONSTRUCTOR CHAINING ===\n");
        System.out.println("Creating a Dog:");
        Dog dog = new Dog("Rex", "Labrador");

        System.out.println("\n=== 3. POLYMORPHISM ===\n");

        // Superclass reference can hold subclass object
        Animal myAnimal = new Dog("Buddy", "Poodle");
        myAnimal.speak();  // Calls Dog's speak() -- runtime polymorphism!
        myAnimal.eat();    // Calls Animal's eat() -- inherited
        // myAnimal.fetch(); // Compile error! Animal reference doesn't know about fetch()

        // Casting to access subclass methods
        if (myAnimal instanceof Dog) {
            Dog myDog = (Dog) myAnimal; // Downcasting
            myDog.fetch(); // Now we can call Dog-specific methods
        }

        // Polymorphic array
        System.out.println("\nPolymorphic array:");
        Animal[] animals = {
            new Dog("Rex", "Lab"),
            new Cat("Whiskers"),
            new Dog("Max", "Beagle")
        };
        for (Animal a : animals) {
            a.speak(); // Each calls its own version!
        }

        System.out.println("\n=== 4. ABSTRACT CLASSES ===\n");

        // Shape shape = new Shape("Red"); // Compile error! Can't instantiate abstract class
        Shape circle = new Circle("Red", 5);
        Shape rect = new Rectangle("Blue", 4, 6);
        circle.describe();
        rect.describe();

        System.out.println("\n=== 5. INTERFACES ===\n");

        Square sq = new Square("Green", 5);
        sq.draw();
        sq.resize(2);
        sq.describe();
        sq.erase(); // Default method from interface

        // Interface as type
        Drawable drawable = sq;
        drawable.draw();

        System.out.println("\n=== 6. KEY OOP RULES TO REMEMBER ===\n");
        System.out.println("1. A class can extend only ONE class (no multiple inheritance)");
        System.out.println("2. A class can implement MULTIPLE interfaces");
        System.out.println("3. Constructor chaining: super() must be first line");
        System.out.println("4. @Override is optional but recommended");
        System.out.println("5. 'final' class can't be extended, 'final' method can't be overridden");
        System.out.println("6. 'static' methods belong to class, not instance (can't be overridden)");
        System.out.println("7. Use abstract class for shared state; interface for shared behavior");
    }
}
