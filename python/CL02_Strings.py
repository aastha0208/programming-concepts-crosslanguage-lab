"""
CL02: Strings in Python
Companion to CL02_StringsAcrossLanguages.java

Python strings are immutable like Java.
f-strings (Python 3.6+) are the cleanest interpolation syntax.

Run: python3 CL02_Strings.py
"""

print("=== 1. STRING CREATION ===\n")

s1 = 'Hello, World!'   # single quotes
s2 = "Hello, World!"   # double quotes — both are identical
s3 = """Multi
line
string"""              # triple quotes for multiline

print(s1)
print(s3)

print("\n=== 2. STRING INTERPOLATION ===\n")

name = "Alice"
age = 30
score = 98.5

# f-strings (Python 3.6+) — most readable
print(f"Name: {name}, Age: {age}")
print(f"Score: {score:.1f}")          # format specifier inside {}
print(f"Pi = {3.14159:.3f}")
print(f"Upper: {name.upper()}")       # expressions inside {}

# .format() method
print("Name: {}, Age: {}".format(name, age))
print("Name: {0}, Age: {1}, Again: {0}".format(name, age))

# % formatting (old style, avoid)
print("Name: %s, Age: %d, Score: %.1f" % (name, age, score))

print("\n=== 3. COMMON STRING METHODS ===\n")

s = "  Hello, World!  "
print(f"original:       '{s}'")
print(f"len():          {len(s)}")           # length — function, not method
print(f"upper():        {s.upper()}")
print(f"lower():        {s.lower()}")
print(f"strip():        '{s.strip()}'")      # trim()
print(f"lstrip():       '{s.lstrip()}'")     # trimLeft
print(f"rstrip():       '{s.rstrip()}'")     # trimRight
print(f"replace():      {s.replace('World', 'Python')}")
print(f"'World' in s:   {'World' in s}")     # contains()
print(f"startswith():   {s.strip().startswith('Hello')}")
print(f"endswith():     {s.strip().endswith('!')}")
print(f"find():         {s.find('o')}")      # indexOf() — returns -1 if not found
print(f"index():        {s.index('o')}")     # indexOf() — raises ValueError if not found
print(f"count('l'):     {s.count('l')}")

print("\n=== 4. SLICING (Python's superpower) ===\n")

s = "Hello, World!"
print(f"s[0:5]:    '{s[0:5]}'")     # substring(0,5)
print(f"s[7:]:     '{s[7:]}'")      # substring(7)
print(f"s[-6:]:    '{s[-6:]}'")     # last 6 chars — no direct Java equivalent
print(f"s[::2]:    '{s[::2]}'")     # every other character
print(f"s[::-1]:   '{s[::-1]}'")    # reversed string!

print("\n=== 5. SPLIT & JOIN ===\n")

csv = "apple,banana,cherry,date"
parts = csv.split(",")
print(f"split: {parts}")

joined = " | ".join(parts)
print(f"join:  {joined}")

# join is MUCH faster than concatenation in a loop
words = ["Hello", "World", "from", "Python"]
sentence = " ".join(words)
print(f"sentence: {sentence}")

print("\n=== 6. IMMUTABILITY ===\n")

original = "hello"
modified = original.upper()
print(f"original unchanged: {original}")
print(f"new string:         {modified}")

# Strings are immutable — can't do original[0] = 'H'
try:
    original[0] = 'H'
except TypeError as e:
    print(f"Can't modify string: {e}")

print("\n=== 7. STRING BUILDER EQUIVALENT ===\n")

# Use join() for efficient building — NOT repeated concatenation
parts = []
for i in range(5):
    parts.append(f"item{i}")
result = ", ".join(parts)
print(f"Efficient: {result}")

# io.StringIO for mutable buffer (like StringBuilder)
import io
buf = io.StringIO()
buf.write("Hello")
buf.write(", ")
buf.write("World!")
print(f"StringIO: {buf.getvalue()}")

print("\n=== 8. USEFUL EXTRAS ===\n")

# Check if string is numeric
print(f"'123'.isdigit(): {'123'.isdigit()}")
print(f"'12.3'.isdigit(): {'12.3'.isdigit()}")
print(f"'abc'.isalpha(): {'abc'.isalpha()}")

# Palindrome check
word = "racecar"
print(f"'{word}' palindrome: {word == word[::-1]}")

# Count occurrences
text = "mississippi"
print(f"'s' in '{text}': {text.count('s')}")
