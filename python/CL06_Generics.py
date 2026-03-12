"""
CL06: Generics in Python
Companion to CL06_GenericsAcrossLanguages.java

Python uses type hints (PEP 484) via the typing module.
Hints are checked by tools like mypy — NOT enforced at runtime.
TypeVar and Generic[T] provide the closest equivalent to Java generics.

Run: python3 CL06_Generics.py
"""

from typing import TypeVar, Generic, List, Dict, Tuple

print("=== 1. GENERIC CLASS ===\n")

T = TypeVar('T')

class Box(Generic[T]):
    def __init__(self, value: T) -> None:
        self.value = value
    def get(self) -> T:
        return self.value
    def __repr__(self) -> str:
        return f"Box[{self.value}]"

int_box: Box[int]  = Box(42)
str_box: Box[str]  = Box("Hello")
print(f"int box:    {int_box}")
print(f"string box: {str_box}")

# Type hints NOT enforced at runtime — Box[int] accepts a string:
wrong_box: Box[int] = Box("this is a string")  # mypy flags this, runtime allows it
print(f"'wrong' box at runtime: {wrong_box}")   # works fine!

print("\n=== 2. GENERIC FUNCTION ===\n")

# TypeVar with bound — T must be comparable
C = TypeVar('C', int, float, str)  # constrained TypeVar

def max_val(a: C, b: C) -> C:
    return a if a >= b else b

print(f"max_val(3, 7):            {max_val(3, 7)}")
print(f"max_val('apple','banana'): {max_val('apple', 'banana')}")
print(f"max_val(1.5, 2.5):         {max_val(1.5, 2.5)}")

print("\n=== 3. TYPED COLLECTIONS ===\n")

# List[T], Dict[K,V], Tuple hints
scores: Dict[str, int] = {"Alice": 95, "Bob": 87, "Charlie": 92}
names: List[str] = list(scores.keys())
coords: Tuple[int, int] = (10, 20)

print(f"scores: {scores}")
print(f"names:  {names}")
print(f"coords: {coords}")

# Generic function on typed list
def sum_list(items: List[float]) -> float:
    return sum(items)

ints: List[int]   = [1, 2, 3, 4, 5]
floats: List[float] = [1.5, 2.5, 3.0]
print(f"sum ints:   {sum_list(ints)}")
print(f"sum floats: {sum_list(floats)}")

print("\n=== 4. GENERIC STACK ===\n")

class Stack(Generic[T]):
    def __init__(self) -> None:
        self._items: List[T] = []

    def push(self, item: T) -> None:
        self._items.append(item)

    def pop(self) -> T:
        if not self._items:
            raise IndexError("pop from empty stack")
        return self._items.pop()

    def peek(self) -> T:
        return self._items[-1]

    def is_empty(self) -> bool:
        return len(self._items) == 0

    def __repr__(self) -> str:
        return f"Stack{self._items}"

int_stack: Stack[int] = Stack()
int_stack.push(1); int_stack.push(2); int_stack.push(3)
print(f"stack:   {int_stack}")
print(f"peek:    {int_stack.peek()}")
print(f"pop:     {int_stack.pop()}")
print(f"after:   {int_stack}")

print("\n=== 5. TYPE ERASURE (Python is similar to Java) ===\n")

# At runtime, Box[int] and Box[str] are BOTH just Box
b1: Box[int] = Box(1)
b2: Box[str] = Box("x")
print(f"Same class at runtime: {type(b1) == type(b2)}")  # True — type hint is gone

print("\n=== KEY DIFFERENCE FROM JAVA ===\n")
print("Java:   generics enforced at COMPILE time (type erasure at runtime)")
print("Python: type hints are DOCUMENTATION only — mypy checks offline")
print("        runtime does NOT enforce Box[int] vs Box[str]")
print("        use mypy or pyright for static type checking")
