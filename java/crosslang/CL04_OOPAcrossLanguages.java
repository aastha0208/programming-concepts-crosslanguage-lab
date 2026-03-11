package crosslang;

/**
 * CL04: OOP Across Languages
 *
 * Java    → Strict OOP: class-based, single inheritance, interfaces
 * Python  → Multi-paradigm: class-based, multiple inheritance, duck typing
 * JS      → Prototype-based (ES6 adds class syntax as sugar)
 * C#      → Strict OOP: nearly identical to Java, adds properties & delegates
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL04_OOPAcrossLanguages
 */
public class CL04_OOPAcrossLanguages {

    // =====================================================
    // ENCAPSULATION: private fields + getters/setters
    // =====================================================
    static class Person {
        private String name;   // private field
        private int age;

        // Constructor
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Getter
        public String getName() { return name; }

        // Setter with validation
        public void setAge(int age) {
            if (age >= 0) this.age = age;
        }

        public int getAge() { return age; }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + "}";
        }
    }

    /*
     * PYTHON: no true private (convention: _name = "private-ish", __name = name-mangled)
     *   class Person:
     *       def __init__(self, name, age):
     *           self._name = name   # convention: "don't touch"
     *           self.__age = age    # name-mangled to _Person__age
     *
     *       @property
     *       def name(self): return self._name       # getter as property
     *
     *       @property
     *       def age(self): return self.__age
     *
     *       @age.setter
     *       def age(self, value):                   # setter as property
     *           if value >= 0: self.__age = value
     *
     *       def __str__(self): return f"Person({self._name}, {self.__age})"
     *
     * JAVASCRIPT:
     *   class Person {
     *       #name;              // private field (ES2022, # prefix)
     *       #age;
     *       constructor(name, age) { this.#name = name; this.#age = age; }
     *       get name() { return this.#name; }       // getter
     *       set age(val) { if (val >= 0) this.#age = val; }  // setter
     *       toString() { return `Person(${this.#name}, ${this.#age})`; }
     *   }
     *
     * C# (uses PROPERTIES instead of explicit getters/setters):
     *   class Person {
     *       private string _name;
     *       private int _age;
     *       public Person(string name, int age) { _name = name; _age = age; }
     *
     *       public string Name => _name;          // read-only property
     *       public int Age {                       // property with validation
     *           get => _age;
     *           set { if (value >= 0) _age = value; }
     *       }
     *       // Auto-property (no backing field needed for simple cases):
     *       public string City { get; set; } = "Unknown";
     *   }
     */

    // =====================================================
    // INHERITANCE: extends (Java/C#)
    // =====================================================
    static class Animal {
        protected String name;

        Animal(String name) { this.name = name; }

        public String speak() { return name + " makes a sound"; }

        @Override
        public String toString() { return getClass().getSimpleName() + "(" + name + ")"; }
    }

    static class Dog extends Animal {
        private String breed;

        Dog(String name, String breed) {
            super(name);         // call parent constructor — MUST be first line
            this.breed = breed;
        }

        @Override              // override parent method
        public String speak() { return name + " barks! [" + breed + "]"; }

        public void fetch() { System.out.println(name + " fetches!"); }
    }

    /*
     * PYTHON (supports MULTIPLE inheritance):
     *   class Animal:
     *       def __init__(self, name): self.name = name
     *       def speak(self): return f"{self.name} makes a sound"
     *
     *   class Dog(Animal):              # extends
     *       def __init__(self, name, breed):
     *           super().__init__(name)  # super() call
     *           self.breed = breed
     *       def speak(self):            # override (no annotation needed)
     *           return f"{self.name} barks! [{self.breed}]"
     *
     *   # Multiple inheritance:
     *   class GuideDog(Dog, ServiceAnimal): pass  # Java can't do this!
     *
     * JAVASCRIPT:
     *   class Animal {
     *       constructor(name) { this.name = name; }
     *       speak() { return `${this.name} makes a sound`; }
     *   }
     *   class Dog extends Animal {
     *       constructor(name, breed) {
     *           super(name);           // must call super() first in constructor
     *           this.breed = breed;
     *       }
     *       speak() { return `${this.name} barks! [${this.breed}]`; }  // override
     *   }
     *
     * C# (single inheritance like Java, identical syntax almost):
     *   class Animal {
     *       protected string Name;
     *       public Animal(string name) { Name = name; }
     *       public virtual string Speak() { return $"{Name} makes a sound"; }  // virtual = overridable
     *   }
     *   class Dog : Animal {           // : instead of extends
     *       private string _breed;
     *       public Dog(string name, string breed) : base(name) { _breed = breed; } // : base() = super()
     *       public override string Speak() { return $"{Name} barks! [{_breed}]"; } // override keyword required
     *   }
     *
     * KEY DIFFERENCES:
     *   Java: @Override is optional annotation   C#: override keyword is REQUIRED
     *   Java: all methods overridable by default  C#: must mark method as virtual to allow override
     *   Java: super()                             C#: base()
     *   Java: extends ClassName                   C#: : ClassName
     */

    // =====================================================
    // INTERFACES
    // =====================================================
    interface Flyable {
        void fly();                           // abstract method
        default String description() {        // default method (Java 8+)
            return "I can fly!";
        }
    }

    interface Swimmable {
        void swim();
    }

    // A class can implement MULTIPLE interfaces
    static class Duck extends Animal implements Flyable, Swimmable {
        Duck(String name) { super(name); }

        @Override public String speak() { return name + " quacks!"; }
        @Override public void fly()     { System.out.println(name + " flies!"); }
        @Override public void swim()    { System.out.println(name + " swims!"); }
    }

    /*
     * PYTHON: uses Abstract Base Classes (ABC) or duck typing
     *   from abc import ABC, abstractmethod
     *
     *   class Flyable(ABC):
     *       @abstractmethod
     *       def fly(self): pass
     *
     *   class Duck(Animal, Flyable):    # multiple inheritance = implements + extends
     *       def fly(self): print(f"{self.name} flies!")
     *
     *   # Duck typing: no interface needed — just implement the method!
     *   # If it has a fly() method, it can "fly" — no declaration required
     *
     * JAVASCRIPT: no interfaces — use duck typing or TypeScript
     *   // Duck typing:
     *   function makeItFly(obj) {
     *       if (typeof obj.fly === 'function') obj.fly();
     *   }
     *   // TypeScript (adds interfaces to JS):
     *   interface Flyable { fly(): void; }
     *   class Duck implements Flyable { fly() { console.log("flying!"); } }
     *
     * C# (interfaces identical to Java!):
     *   interface IFlyable {
     *       void Fly();
     *       string Description() => "I can fly!";  // default method (C# 8+)
     *   }
     *   interface ISwimmable { void Swim(); }
     *   class Duck : Animal, IFlyable, ISwimmable {  // implements multiple interfaces
     *       public void Fly() { Console.WriteLine($"{Name} flies!"); }
     *       public void Swim() { Console.WriteLine($"{Name} swims!"); }
     *   }
     *   // NOTE: C# convention is to prefix interfaces with 'I' (IFlyable, IList, etc.)
     */

    // =====================================================
    // ABSTRACT CLASSES
    // =====================================================
    static abstract class Shape {
        String color;
        Shape(String color) { this.color = color; }
        abstract double area();             // must be implemented by subclasses
        void describe() {                   // concrete method
            System.out.printf("  %s shape, area=%.2f%n", color, area());
        }
    }

    static class Circle extends Shape {
        double r;
        Circle(String color, double r) { super(color); this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }

    /*
     * PYTHON:
     *   from abc import ABC, abstractmethod
     *   class Shape(ABC):
     *       def __init__(self, color): self.color = color
     *       @abstractmethod
     *       def area(self): pass      # abstract method
     *       def describe(self):       # concrete method
     *           print(f"{self.color} shape, area={self.area():.2f}")
     *
     * JAVASCRIPT: no abstract classes — simulate with errors:
     *   class Shape {
     *       constructor(color) { this.color = color; }
     *       area() { throw new Error("Must implement area()"); }  // simulate abstract
     *   }
     *
     * C# (identical to Java!):
     *   abstract class Shape {
     *       public string Color;
     *       public Shape(string color) { Color = color; }
     *       public abstract double Area();          // abstract method
     *       public void Describe() {                // concrete method
     *           Console.WriteLine($"{Color} shape, area={Area():F2}");
     *       }
     *   }
     */

    // =====================================================
    // MAIN
    // =====================================================
    public static void main(String[] args) {

        System.out.println("=== 1. ENCAPSULATION ===\n");
        Person p = new Person("Alice", 30);
        System.out.println(p);
        p.setAge(-5);  // validation rejects it
        System.out.println("Age after setAge(-5): " + p.getAge()); // still 30

        System.out.println("\n=== 2. INHERITANCE & POLYMORPHISM ===\n");
        Animal[] animals = { new Dog("Rex", "Lab"), new Duck("Donald") };
        for (Animal a : animals) {
            System.out.println(a + " says: " + a.speak());
        }

        System.out.println("\n=== 3. INTERFACES ===\n");
        Duck duck = new Duck("Daffy");
        duck.fly();
        duck.swim();
        System.out.println(duck.description());  // default interface method

        // Interface as type
        Flyable flier = duck;
        flier.fly();

        System.out.println("\n=== 4. ABSTRACT CLASSES ===\n");
        Shape circle = new Circle("Red", 5);
        circle.describe();
        // new Shape("x"); // compile error — can't instantiate abstract class

        System.out.println("\n=== 5. KEY DIFFERENCES SUMMARY ===\n");
        System.out.println("INHERITANCE:");
        System.out.println("  Java/C#:  single class inheritance (extends / :)");
        System.out.println("  Python:   multiple inheritance allowed");
        System.out.println("  JS:       single inheritance (extends), prototype-based under hood");
        System.out.println("\nINTERFACES:");
        System.out.println("  Java:     interface keyword, implement multiple");
        System.out.println("  C#:       interface keyword (prefix with I), implement multiple");
        System.out.println("  Python:   ABC or duck typing (no formal interface)");
        System.out.println("  JS:       no interfaces (TypeScript adds them)");
        System.out.println("\nOVERRIDING:");
        System.out.println("  Java:     @Override annotation (optional but recommended)");
        System.out.println("  C#:       override keyword REQUIRED; parent method must be virtual");
        System.out.println("  Python:   just redefine the method — no annotation needed");
        System.out.println("  JS:       just redefine the method in subclass");
        System.out.println("\nPROPERTIES:");
        System.out.println("  Java:     explicit getX() / setX() methods");
        System.out.println("  C#:       built-in property syntax: public int Age { get; set; }");
        System.out.println("  Python:   @property decorator");
        System.out.println("  JS:       get/set keywords inside class body");
    }
}
