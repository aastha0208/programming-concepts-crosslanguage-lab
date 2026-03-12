"""
CL07: Functional Programming in Python
Companion to CL07_FunctionalProgrammingAcrossLanguages.java

Python has excellent FP support: first-class functions, lambdas,
list comprehensions, map/filter/reduce, and functools.
List comprehensions are preferred over map/filter for readability.

Run: python3 CL07_FunctionalProgramming.py
"""

from functools import reduce, partial
from typing import Callable, TypeVar, List

T = TypeVar('T')

print("=== 1. LAMBDA FUNCTIONS ===\n")

square   = lambda x: x * x
shout    = lambda s: s.upper() + "!"
add      = lambda a, b: a + b
is_even  = lambda x: x % 2 == 0

print(f"square(5):    {square(5)}")
print(f"shout(hello): {shout('hello')}")
print(f"add(3,4):     {add(3, 4)}")
print(f"is_even(4):   {is_even(4)}")

print("\n=== 2. MAP ===\n")

nums = [1, 2, 3, 4, 5]

# List comprehension (preferred in Python)
squared   = [x * x for x in nums]
doubled   = [x * 2 for x in nums]

# map() equivalent (returns iterator — wrap in list())
squared2  = list(map(lambda x: x * x, nums))
as_string = list(map(str, nums))           # method reference style

print(f"comprehension:  {squared}")
print(f"map():          {squared2}")
print(f"as strings:     {as_string}")

print("\n=== 3. FILTER ===\n")

evens = [x for x in nums if x % 2 == 0]        # comprehension (preferred)
odds  = list(filter(lambda x: x % 2 != 0, nums)) # filter() equivalent

words     = ["apple", "fig", "banana", "kiwi", "cherry"]
long_words = [w for w in words if len(w) > 4]

print(f"evens:      {evens}")
print(f"odds:       {odds}")
print(f"longWords:  {long_words}")

print("\n=== 4. REDUCE ===\n")

total   = reduce(lambda acc, x: acc + x, nums, 0)
product = reduce(lambda acc, x: acc * x, nums, 1)

# Python has built-in shortcuts:
total2  = sum(nums)
max_val = max(nums)
min_val = min(nums)

print(f"reduce sum:     {total}")
print(f"sum() shortcut: {total2}")
print(f"max:            {max_val}")
print(f"product:        {product}")

print("\n=== 5. CHAINING WITH COMPREHENSION ===\n")

words_raw = ["  hello  ", "world", "  python  ", "", "  fp  "]
result = sorted([w.strip().upper() for w in words_raw if w.strip()])
print(f"chained: {result}")

print("\n=== 6. SORTED WITH KEY ===\n")

print(f"by length:        {sorted(words, key=len)}")
print(f"by length desc:   {sorted(words, key=len, reverse=True)}")
print(f"alphabetical:     {sorted(words)}")

# Multi-key sort with tuple
people = [("Charlie", 30), ("Alice", 25), ("Dave", 25), ("Bob", 35)]
by_age_name = sorted(people, key=lambda p: (p[1], p[0]))  # age, then name
print(f"by age then name: {by_age_name}")

print("\n=== 7. PARTIAL APPLICATION ===\n")

def power(base, exp):
    return base ** exp

square_fn = partial(power, exp=2)
cube_fn   = partial(power, exp=3)

print(f"square_fn(4): {square_fn(4)}")
print(f"cube_fn(3):   {cube_fn(3)}")

print("\n=== 8. FUNCTION COMPOSITION ===\n")

def compose(*fns):
    return reduce(lambda f, g: lambda x: f(g(x)), fns)

trim    = str.strip
to_upper = str.upper
exclaim  = lambda s: s + "!"

shout_trimmed = compose(exclaim, to_upper, trim)
print(f"composed: {shout_trimmed('  hello  ')}")

# Predicate composition
def all_of(*predicates: Callable) -> Callable:
    return lambda x: all(p(x) for p in predicates)

is_positive = lambda x: x > 0
is_even_and_positive = all_of(is_even, is_positive)
print(f"4 is even and positive:  {is_even_and_positive(4)}")
print(f"-2 is even and positive: {is_even_and_positive(-2)}")
