"""
CL05: Exception Handling in Python
Companion to CL05_ExceptionsAcrossLanguages.java

Python has NO checked exceptions — all exceptions are unchecked.
Python adds an 'else' block that Java/JS/C# don't have.

Run: python3 CL05_Exceptions.py
"""

print("=== 1. BASIC TRY/EXCEPT/ELSE/FINALLY ===\n")

try:
    result = 10 / 2       # no exception here
    print(f"Result: {result}")
except ZeroDivisionError as e:
    print(f"Caught: {e}")
else:
    print("Else runs ONLY if NO exception was raised (no Java equivalent!)")
finally:
    print("Finally always runs")

print()
try:
    result = 10 / 0       # ZeroDivisionError
except ZeroDivisionError as e:
    print(f"Caught ZeroDivisionError: {e}")
finally:
    print("Finally runs after exception too")

print("\n=== 2. EXCEPTION HIERARCHY ===\n")

print("BaseException")
print("  SystemExit, KeyboardInterrupt, GeneratorExit  <- don't catch these normally")
print("  Exception")
print("    ValueError    <- int('abc'), bad value for a function")
print("    TypeError     <- wrong type, like adding str + int")
print("    KeyError      <- dict key not found")
print("    IndexError    <- list index out of range  (Java: ArrayIndexOutOfBoundsException)")
print("    AttributeError<- accessing missing attribute  (Java: NullPointerException-ish)")
print("    NameError     <- using undefined variable")
print("    ZeroDivisionError <- 10 / 0  (Java: ArithmeticException)")
print("    FileNotFoundError <- file missing  (Java: FileNotFoundException)")
print("    StopIteration <- end of iterator")
print("    OSError       <- OS-related errors")
print("  ALL are 'unchecked' — compiler never forces you to handle them")

print("\n=== 3. MULTIPLE EXCEPT BLOCKS ===\n")

def parse_and_divide(s, divisor):
    try:
        num = int(s)           # may raise ValueError
        result = num / divisor # may raise ZeroDivisionError
        return result
    except ValueError:
        print(f"  ValueError: '{s}' is not a valid integer")
    except ZeroDivisionError:
        print(f"  ZeroDivisionError: cannot divide by zero")
    except Exception as e:
        print(f"  Unexpected: {type(e).__name__}: {e}")

parse_and_divide("abc", 2)
parse_and_divide("10", 0)
parse_and_divide("10", 2)

# Catching multiple exceptions in one line (like Java multi-catch)
try:
    int("abc")
except (ValueError, TypeError) as e:    # tuple = multi-catch
    print(f"Multi-except: {type(e).__name__}")

print("\n=== 4. CUSTOM EXCEPTIONS ===\n")

class InsufficientFundsError(Exception):    # extend Exception
    def __init__(self, shortfall: float):
        super().__init__(f"Insufficient funds. Short by ${shortfall:.2f}")
        self.shortfall = shortfall


class InvalidInputError(ValueError):        # extend ValueError (unchecked)
    def __init__(self, message: str):
        super().__init__(message)


def withdraw(balance, amount):
    if amount > balance:
        raise InsufficientFundsError(amount - balance)   # throw new ...
    return balance - amount

try:
    withdraw(100, 250)
except InsufficientFundsError as e:
    print(f"Caught custom exception: {e}")
    print(f"Shortfall: ${e.shortfall:.2f}")

print("\n=== 5. RAISE (throw) ===\n")

def validate_age(age: int):
    if age < 0:
        raise ValueError(f"Age cannot be negative: {age}")   # throw new
    if age > 150:
        raise ValueError(f"Age unrealistically large: {age}")
    return age

try:
    validate_age(-5)
except ValueError as e:
    print(f"Validation failed: {e}")

# Re-raise
try:
    try:
        int("bad")
    except ValueError:
        print("  Caught inner, re-raising...")
        raise              # re-raise same exception (like 'throw' in Java catch)
except ValueError as e:
    print(f"  Caught outer: {e}")

print("\n=== 6. FINALLY BEHAVIOR ===\n")

def method_with_return():
    try:
        return "from try"
    finally:
        print("  Finally runs before return!")   # same as Java

print(f"returned: {method_with_return()}")

print("\n=== 7. CONTEXT MANAGERS (try-with-resources equivalent) ===\n")

# Python's 'with' statement = Java's try-with-resources
# Automatically calls __exit__ (like AutoCloseable.close())

import io

# Writing to a StringIO buffer with automatic cleanup
with io.StringIO() as buf:
    buf.write("Hello from context manager")
    content = buf.getvalue()
print(f"Content: {content}")

# File example (would be: with open("file.txt") as f: ...)
print("with open('file.txt') as f: f.read()  <- auto-closes file")

print("\n=== 8. KEY DIFFERENCES FROM JAVA ===\n")
print("1. No checked exceptions — all exceptions are unchecked")
print("2. No 'throws' in method signatures")
print("3. 'except' instead of 'catch'")
print("4. Extra 'else' block: runs only if no exception raised")
print("5. Catch multiple: except (Ex1, Ex2) vs Java's Ex1 | Ex2")
print("6. 'with' statement = try-with-resources")
print("7. 10 / 0 raises ZeroDivisionError (same as Java, unlike JS which returns Infinity)")
