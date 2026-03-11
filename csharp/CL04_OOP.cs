// CL04: OOP in C#
// Companion to CL04_OOPAcrossLanguages.java
//
// C# OOP is nearly identical to Java. Key differences:
// - Properties (get/set) instead of explicit getter/setter methods
// - 'virtual' + 'override' keywords required for method overriding
// - ':' instead of 'extends'/'implements'
// - 'base' instead of 'super'
// - Interface names conventionally prefixed with 'I'
//
// Run: dotnet-script CL04_OOP.cs

using System;

// ===================================================
// ENCAPSULATION: properties instead of getters/setters
// ===================================================
class BankAccount
{
    private string _owner;
    private double _balance;

    public BankAccount(string owner, double balance)
    {
        _owner = owner;
        _balance = balance;
    }

    // Properties — C#'s built-in getter/setter syntax
    public string Owner => _owner;        // read-only property (no setter)

    public double Balance                 // property with validation
    {
        get => _balance;
        private set { if (value >= 0) _balance = value; }
    }

    // Auto-property (compiler generates backing field automatically)
    public string AccountType { get; set; } = "Checking";

    public void Deposit(double amount)
    {
        if (amount > 0) {
            _balance += amount;
            Console.WriteLine($"  Deposited ${amount} -> Balance: ${_balance}");
        }
    }

    public void Withdraw(double amount)
    {
        if (amount > 0 && amount <= _balance) {
            _balance -= amount;
            Console.WriteLine($"  Withdrew ${amount} -> Balance: ${_balance}");
        } else {
            Console.WriteLine("  Insufficient funds!");
        }
    }

    public override string ToString() =>    // override ToString (Java's toString())
        $"BankAccount({_owner}, ${_balance})";
}

// ===================================================
// INHERITANCE: ':' instead of 'extends'
// ===================================================
class Animal
{
    protected string Name;

    public Animal(string name)
    {
        Name = name;
        Console.WriteLine($"  Animal constructor: {name}");
    }

    public virtual string Speak()          // 'virtual' = overridable (Java: all methods are virtual by default)
        => $"{Name} makes a sound";

    public void Eat() => Console.WriteLine($"  {Name} eats food");

    public override string ToString()
        => $"{GetType().Name}({Name})";
}

class Dog : Animal                         // ':' instead of 'extends'
{
    private string _breed;

    public Dog(string name, string breed)
        : base(name)                       // ': base()' instead of 'super()'
    {
        _breed = breed;
        Console.WriteLine($"  Dog constructor: {breed}");
    }

    public override string Speak()         // 'override' REQUIRED (optional @Override in Java)
        => $"{Name} barks! [{_breed}]";

    public void Fetch() => Console.WriteLine($"  {Name} fetches!");
}

class Cat : Animal
{
    public Cat(string name) : base(name) { }
    public override string Speak() => $"{Name} meows!";
}

// ===================================================
// INTERFACES: nearly identical to Java, prefix 'I' by convention
// ===================================================
interface IFlyable
{
    void Fly();
    string Description() => "I can fly!";  // default method (C# 8+)
}

interface ISwimmable
{
    void Swim();
}

class Duck : Animal, IFlyable, ISwimmable  // implements multiple interfaces
{
    public Duck(string name) : base(name) { }
    public override string Speak() => $"{Name} quacks!";
    public void Fly()  => Console.WriteLine($"  {Name} flies!");
    public void Swim() => Console.WriteLine($"  {Name} swims!");
}

// ===================================================
// ABSTRACT CLASSES: identical to Java
// ===================================================
abstract class Shape
{
    public string Color;
    protected Shape(string color) { Color = color; }

    public abstract double Area();         // abstract method — subclasses MUST implement

    public void Describe()                 // concrete method
        => Console.WriteLine($"  {Color} shape, area={Area():F2}");
}

class Circle : Shape
{
    private double _radius;
    public Circle(string color, double radius) : base(color) { _radius = radius; }
    public override double Area() => Math.PI * _radius * _radius;
}

class Rectangle : Shape
{
    private double _w, _h;
    public Rectangle(string color, double w, double h) : base(color) { _w = w; _h = h; }
    public override double Area() => _w * _h;
}

class CL04_OOP
{
    static void Main()
    {
        Console.WriteLine("=== 1. ENCAPSULATION & PROPERTIES ===\n");

        var acc = new BankAccount("Alice", 1000);
        acc.Deposit(500);
        acc.Withdraw(200);
        Console.WriteLine($"Balance: {acc.Balance}");       // property access
        Console.WriteLine($"Type: {acc.AccountType}");      // auto-property
        acc.AccountType = "Savings";
        Console.WriteLine($"Type: {acc.AccountType}");

        Console.WriteLine("\n=== 2. INHERITANCE ===\n");

        Console.WriteLine("Creating a Dog:");
        var dog = new Dog("Rex", "Labrador");
        Console.WriteLine($"{dog} says: {dog.Speak()}");

        Console.WriteLine("\n=== 3. POLYMORPHISM ===\n");

        Animal[] animals = { new Dog("Buddy", "Poodle"), new Cat("Whiskers"), new Dog("Max", "Beagle") };
        foreach (var a in animals)
        {
            Console.WriteLine($"  {a}: {a.Speak()}");       // each calls its own Speak()
        }

        // is/as for type checking and casting
        Animal myAnimal = new Dog("Fido", "Beagle");
        Console.WriteLine($"\nisinstance: {myAnimal is Dog}");
        if (myAnimal is Dog myDog)           // pattern matching — declares myDog
        {
            myDog.Fetch();
        }

        Console.WriteLine("\n=== 4. INTERFACES ===\n");

        var duck = new Duck("Donald");
        duck.Fly();
        duck.Swim();
        Console.WriteLine(duck.Description());  // default interface method

        IFlyable flier = duck;
        flier.Fly();

        Console.WriteLine("\n=== 5. ABSTRACT CLASSES ===\n");

        // new Shape("x"); // compile error — can't instantiate abstract class
        Shape circle = new Circle("Red", 5);
        Shape rect   = new Rectangle("Blue", 4, 6);
        circle.Describe();
        rect.Describe();

        Console.WriteLine("\n=== 6. KEY DIFFERENCES FROM JAVA ===\n");
        Console.WriteLine("SYNTAX:");
        Console.WriteLine("  Java: extends ClassName     C#: : ClassName");
        Console.WriteLine("  Java: implements IFoo       C#: : IFoo  (same ':' for both!)");
        Console.WriteLine("  Java: super()               C#: base()");
        Console.WriteLine("  Java: @Override (optional)  C#: override (required)");
        Console.WriteLine("  Java: all methods virtual   C#: must mark 'virtual' to allow override");
        Console.WriteLine("\nPROPERTIES:");
        Console.WriteLine("  Java: getX() / setX()       C#: public int X { get; set; }");
        Console.WriteLine("  Java: no language feature   C#: auto-properties generate backing field");
        Console.WriteLine("\nINTERFACES:");
        Console.WriteLine("  Java: interface IFoo {}     C#: interface IFoo {} (prefix 'I' by convention)");
        Console.WriteLine("  Both support default methods (Java 8+, C# 8+)");
        Console.WriteLine("\nNULL SAFETY:");
        Console.WriteLine("  Java: NullPointerException  C#: NullReferenceException");
        Console.WriteLine("  C# has nullable reference types (string?) and ?. operator");
    }
}
