"""
CL04: OOP in Python
Companion to CL04_OOPAcrossLanguages.java

Python supports OOP with classes, inheritance (including multiple),
and duck typing instead of interfaces.

Run: python3 CL04_OOP.py
"""

print("=== 1. ENCAPSULATION ===\n")

class BankAccount:
    def __init__(self, owner: str, balance: float):
        self._owner = owner          # _prefix = "private by convention"
        self.__balance = balance     # __prefix = name-mangled (stronger convention)

    @property
    def owner(self):                 # getter as property (no getOwner() needed)
        return self._owner

    @property
    def balance(self):
        return self.__balance

    def deposit(self, amount: float):
        if amount > 0:
            self.__balance += amount
            print(f"  Deposited ${amount} -> Balance: ${self.__balance}")

    def withdraw(self, amount: float):
        if 0 < amount <= self.__balance:
            self.__balance -= amount
            print(f"  Withdrew ${amount} -> Balance: ${self.__balance}")
        else:
            print("  Insufficient funds!")

    def __str__(self):               # toString()
        return f"BankAccount({self._owner}, ${self.__balance})"

acc = BankAccount("Alice", 1000)
acc.deposit(500)
acc.withdraw(200)
print(f"Balance: {acc.balance}")    # uses @property getter
# acc.__balance = -999              # won't work due to name mangling (it's _BankAccount__balance)

print("\n=== 2. INHERITANCE ===\n")

class Animal:
    def __init__(self, name: str):
        self.name = name
        print(f"  Animal.__init__: {name}")

    def speak(self) -> str:
        return f"{self.name} makes a sound"

    def eat(self):
        print(f"  {self.name} eats food")

    def __str__(self):
        return f"{type(self).__name__}({self.name})"


class Dog(Animal):                   # extends Animal
    def __init__(self, name: str, breed: str):
        super().__init__(name)       # super() — must call explicitly
        self.breed = breed
        print(f"  Dog.__init__: {breed}")

    def speak(self) -> str:          # override — no annotation needed
        return f"{self.name} barks! [{self.breed}]"

    def fetch(self):
        print(f"  {self.name} fetches!")


class Cat(Animal):
    def speak(self) -> str:
        return f"{self.name} meows!"


print("Creating a Dog:")
dog = Dog("Rex", "Labrador")
print(f"{dog} says: {dog.speak()}")

print("\n=== 3. POLYMORPHISM ===\n")

# Python uses duck typing — no need to declare a common supertype
animals = [Dog("Buddy", "Poodle"), Cat("Whiskers"), Dog("Max", "Beagle")]
for animal in animals:
    print(f"  {animal}: {animal.speak()}")  # each calls its own speak()

print("\n=== 4. INTERFACES VIA DUCK TYPING ===\n")

# Python doesn't need interfaces — if it has the method, it works
class Duck:
    def __init__(self, name): self.name = name
    def speak(self): return f"{self.name} quacks!"
    def fly(self): print(f"  {self.name} flies!")
    def swim(self): print(f"  {self.name} swims!")

def make_it_fly(obj):              # works for ANY object with a fly() method
    obj.fly()

duck = Duck("Donald")
make_it_fly(duck)                  # Duck typing — no interface declaration needed!

print("\n=== 5. ABSTRACT BASE CLASSES (ABC) ===\n")

from abc import ABC, abstractmethod

class Shape(ABC):                  # abstract class
    def __init__(self, color: str):
        self.color = color

    @abstractmethod
    def area(self) -> float:       # abstract method — subclasses MUST implement
        pass

    def describe(self):            # concrete method
        print(f"  {self.color} shape, area={self.area():.2f}")


class Circle(Shape):
    def __init__(self, color, radius):
        super().__init__(color)
        self.radius = radius

    def area(self) -> float:
        import math
        return math.pi * self.radius ** 2


class Rectangle(Shape):
    def __init__(self, color, w, h):
        super().__init__(color)
        self.width = w
        self.height = h

    def area(self) -> float:
        return self.width * self.height

# Shape("red")    # TypeError: Can't instantiate abstract class
Circle("Red", 5).describe()
Rectangle("Blue", 4, 6).describe()

print("\n=== 6. MULTIPLE INHERITANCE (not in Java!) ===\n")

class Flyable:
    def fly(self): print(f"  {self.name} flies!")

class Swimmable:
    def swim(self): print(f"  {self.name} swims!")

class FlyingFish(Animal, Flyable, Swimmable):  # multiple inheritance!
    def speak(self): return f"{self.name} splashes!"

fish = FlyingFish("Nemo")
fish.fly()
fish.swim()
print(fish.speak())

print("\n=== 7. DUNDER METHODS (magic methods) ===\n")

class Vector:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):             # toString()
        return f"Vector({self.x}, {self.y})"

    def __repr__(self):            # developer repr
        return f"Vector(x={self.x}, y={self.y})"

    def __add__(self, other):      # operator overloading: v1 + v2
        return Vector(self.x + other.x, self.y + other.y)

    def __eq__(self, other):       # equals()
        return self.x == other.x and self.y == other.y

    def __len__(self):             # len(v)
        return int((self.x**2 + self.y**2) ** 0.5)

v1 = Vector(1, 2)
v2 = Vector(3, 4)
print(f"v1 + v2 = {v1 + v2}")
print(f"v1 == v2: {v1 == v2}")
print(f"len(v2): {len(v2)}")
