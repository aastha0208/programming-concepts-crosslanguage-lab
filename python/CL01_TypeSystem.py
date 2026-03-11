"""
CL01: Type System in Python
Companion to CL01_TypeSystem.java

Python is dynamically typed — no type declarations needed.
Types are checked at runtime, not compile time.

Run: python3 CL01_TypeSystem.py
"""

print("=== 1. VARIABLE DECLARATION (no type needed) ===\n")

age = 25
name = "Alice"
salary = 75000.50
is_active = True        # Note: True/False capitalised in Python
city = "New York"       # just assign — Python infers the type

print(f"age={age}, name={name}, salary={salary}, is_active={is_active}, city={city}")
print(f"Types: {type(age)}, {type(name)}, {type(salary)}, {type(is_active)}")

# Type hints (Python 3.5+) — optional, not enforced at runtime
def greet(name: str, age: int) -> str:
    return f"Hi {name}, you are {age}"

print(greet("Bob", 30))

print("\n=== 2. PYTHON'S TYPES vs JAVA'S PRIMITIVES ===\n")

# Python has no fixed-size integers — they grow automatically!
small = 42
big = 99999999999999999999999999999999  # no overflow!
print(f"small int: {small}")
print(f"huge int:  {big}")
print(f"type: {type(big)}")  # still int

# Python's float is Java's double (64-bit)
pi = 3.141592653589793
print(f"float (64-bit): {pi}")

# Python has no char type — use a 1-character string
ch = 'A'
print(f"char equivalent: '{ch}', type: {type(ch)}")

# Boolean
print(f"True: {True}, False: {False}")
print(f"bool is subclass of int: {isinstance(True, int)}")  # quirky Python fact!
print(f"True + True = {True + True}")  # = 2!

print("\n=== 3. DYNAMIC TYPING IN ACTION ===\n")

# In Java, x = "hello" after x = 5 would be a compile error
# In Python, a variable can change type at runtime
x = 5
print(f"x = {x}, type = {type(x).__name__}")
x = "hello"
print(f"x = {x}, type = {type(x).__name__}")
x = [1, 2, 3]
print(f"x = {x}, type = {type(x).__name__}")

print("\n=== 4. TYPE CONVERSION ===\n")

# Python uses conversion functions (not cast syntax)
pi_str = "3.99"
pi_int = int(float(pi_str))   # "3.99" -> 3.99 -> 3 (truncates like Java)
pi_float = float(pi_str)

print(f'int(float("3.99")) = {pi_int}')   # 3 (truncates, like Java (int) cast)
print(f'float("3.99") = {pi_float}')

# str() converts anything to string
print(f"str(42) = '{str(42)}'")
print(f"str(3.14) = '{str(3.14)}'")
print(f"str(True) = '{str(True)}'")

# Implicit numeric widening (Python just handles it)
result = 5 + 2.0      # int + float -> float automatically
print(f"5 + 2.0 = {result}, type = {type(result).__name__}")

print("\n=== 5. NONE (Python's null) ===\n")

x = None
print(f"x = {x}")
print(f"x is None: {x is None}")   # use 'is None', not == None

# None check
def get_name(user=None):
    if user is None:
        return "Guest"
    return user

print(get_name())          # Guest
print(get_name("Alice"))   # Alice

print("\n=== 6. TYPE CHECKING ===\n")

obj = "hello"
print(f"isinstance(obj, str): {isinstance(obj, str)}")
print(f"type(obj) == str: {type(obj) == str}")
print(f"type(obj).__name__: {type(obj).__name__}")

# isinstance handles inheritance
print(f"isinstance(True, int): {isinstance(True, int)}")  # True! bool is a subclass

print("\n=== 7. CONSTANTS (convention only) ===\n")

# Python has no const keyword — use ALL_CAPS by convention
TAX_RATE = 0.08
MAX_SIZE = 100
print(f"TAX_RATE = {TAX_RATE}")
print(f"MAX_SIZE = {MAX_SIZE}")
# Nothing stops you from doing TAX_RATE = 0.99 — it's just convention
