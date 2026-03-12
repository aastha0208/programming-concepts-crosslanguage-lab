"""
CL12: Design Patterns in Python
Companion to CL12_DesignPatternsAcrossLanguages.java

Python often simplifies or eliminates classic GoF patterns:
- Singleton: module-level variable (modules load once)
- Strategy: just pass functions (no interface needed)
- Builder: keyword arguments or @dataclass with defaults
- Observer: list of callables
- Factory: factory functions (no abstract class needed)

Run: python3 CL12_DesignPatterns.py
"""

from dataclasses import dataclass, field
from typing import Callable, List

print("=== 1. SINGLETON ===\n")

# Python approach 1: __new__ based
class DatabaseConnection:
    _instance = None

    def __new__(cls, *args, **kwargs):
        if not cls._instance:
            cls._instance = super().__new__(cls)
            cls._instance.url = "postgresql://localhost/db"
        return cls._instance

    def query(self, sql):
        return f"result of: {sql}"

db1 = DatabaseConnection()
db2 = DatabaseConnection()
print(f"Same instance: {db1 is db2}")   # True
print(db1.query("SELECT *"))

# Python approach 2 (simpler — just a module-level variable):
# In db.py:
#   _instance = None
#   def get_instance():
#       global _instance
#       if _instance is None: _instance = DatabaseConnection()
#       return _instance
# Python modules are loaded once — the variable IS a singleton.

print("\n=== 2. STRATEGY ===\n")

# Python: just pass functions — no interface class needed
def bubble_sort(data):
    result = sorted(data)
    print(f"  BubbleSort: {result}")
    return result

def quick_sort(data):
    result = sorted(data)
    print(f"  QuickSort:  {result}")
    return result

class Sorter:
    def __init__(self, strategy: Callable):
        self.strategy = strategy

    def sort(self, data):
        return self.strategy(data)

sorter = Sorter(bubble_sort)
sorter.sort([5, 2, 8, 1])
sorter.strategy = quick_sort   # swap strategy
sorter.sort([5, 2, 8, 1])

print("\n=== 3. BUILDER ===\n")

# Python: @dataclass with defaults replaces Builder pattern
@dataclass
class Pizza:
    size: str
    crust: str = "thin"
    cheese: bool = False
    pepperoni: bool = False
    toppings: List[str] = field(default_factory=list)

    def __repr__(self):
        extras = [t for t in [
            "cheese" if self.cheese else "",
            "pepperoni" if self.pepperoni else "",
            *self.toppings
        ] if t]
        return f"Pizza[{self.size},{self.crust},{extras}]"

# No builder class needed — keyword args do the job
pizza1 = Pizza("large", crust="thick", cheese=True, pepperoni=True)
pizza2 = Pizza("small")
pizza3 = Pizza("medium", cheese=True, toppings=["mushrooms", "peppers"])

print(pizza1)
print(pizza2)
print(pizza3)

print("\n=== 4. OBSERVER ===\n")

class EventBus:
    def __init__(self):
        self._listeners: dict = {}

    def subscribe(self, event: str, fn: Callable):
        self._listeners.setdefault(event, []).append(fn)

    def unsubscribe(self, event: str, fn: Callable):
        if event in self._listeners:
            self._listeners[event] = [f for f in self._listeners[event] if f != fn]

    def publish(self, event: str, data=None):
        for fn in self._listeners.get(event, []):
            fn(data)

bus = EventBus()

handler_a = lambda data: print(f"  Listener A: {data}")
handler_b = lambda data: print(f"  Listener B: {data}")

bus.subscribe("login", handler_a)
bus.subscribe("login", handler_b)
bus.publish("login", "user_alice")

bus.unsubscribe("login", handler_a)
bus.publish("login", "user_bob")   # only handler_b fires

print("\n=== 5. FACTORY ===\n")

# Python: factory function (no abstract class needed)
def create_animal(animal_type: str, name: str):
    sounds = {"dog": "Woof", "cat": "Meow", "bird": "Tweet"}

    class Animal:
        def speak(self):
            return f"{name} says {sounds.get(animal_type, '...')}"
        def __repr__(self):
            return f"{animal_type.capitalize()}({name})"

    return Animal()

dog  = create_animal("dog", "Rex")
cat  = create_animal("cat", "Whiskers")
print(dog.speak())
print(cat.speak())

print("\n=== KEY PYTHON SIMPLIFICATIONS ===\n")
print("Singleton  → module-level variable (modules load once)")
print("Strategy   → pass functions directly — no interface class")
print("Builder    → @dataclass + keyword args + defaults")
print("Observer   → list of callables — no Observer interface")
print("Factory    → factory function returning an object")
