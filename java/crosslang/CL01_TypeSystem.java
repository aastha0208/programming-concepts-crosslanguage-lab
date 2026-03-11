package crosslang;

/**
 * CL01: Type Systems Across Languages
 *
 * Java    → Statically typed, strongly typed, explicit declarations
 * Python  → Dynamically typed, strongly typed, no declarations needed
 * JS      → Dynamically typed, weakly typed (coercion), no declarations needed
 * C#      → Statically typed, strongly typed (closest to Java)
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL01_TypeSystem
 */
public class CL01_TypeSystem {

    public static void main(String[] args) {

        System.out.println("=== 1. VARIABLE DECLARATION ===\n");

        // JAVA: must declare type explicitly
        int age = 25;
        String name = "Alice";
        double salary = 75000.50;
        boolean isActive = true;

        // JAVA (Java 10+): var for type inference (like C# var)
        // var city = "New York"; // requires Java 10+ — project uses Java 8
        String city = "New York"; // explicit type (Java 8 compatible)

        System.out.println(age + ", " + name + ", " + salary + ", " + isActive + ", " + city);

        /*
         * PYTHON (no declaration needed):
         *   age = 25
         *   name = "Alice"
         *   salary = 75000.50
         *   is_active = True      # True/False capitalised in Python
         *
         * JAVASCRIPT:
         *   let age = 25;         # let (block scoped, preferred)
         *   const name = "Alice"; # const (immutable binding)
         *   var salary = 75000.50 # var (function scoped, avoid)
         *
         * C# (nearly identical to Java):
         *   int age = 25;
         *   string name = "Alice";   // lowercase 'string' alias for String
         *   double salary = 75000.50;
         *   bool isActive = true;    // 'bool' not 'boolean'
         *   var city = "New York";   // var works same as Java 10+
         */

        System.out.println("\n=== 2. PRIMITIVE TYPES COMPARISON ===\n");

        // JAVA primitives → C# is almost identical
        // Java      C#          Python        JavaScript
        // int       int         int           number
        // long      long        int (no limit) number / BigInt
        // double    double      float         number
        // float     float       float         number
        // boolean   bool        bool          boolean (true/false)
        // char      char        str (1 char)  string (1 char)
        // byte      byte        int           number
        // short     short       int           number

        System.out.println("Java int range:    " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("Java long range:   " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);
        System.out.println("Java double max:   " + Double.MAX_VALUE);

        /*
         * PYTHON has no fixed-size integers — they grow automatically:
         *   big = 99999999999999999999999  # works fine!
         *
         * JAVASCRIPT has one number type for all numbers:
         *   typeof 42        → "number"
         *   typeof 3.14      → "number"
         *   Number.MAX_SAFE_INTEGER → 9007199254740991
         *
         * C# has identical ranges to Java for int/long/double
         *   int.MinValue  → -2147483648
         *   int.MaxValue  →  2147483647
         */

        System.out.println("\n=== 3. TYPE CASTING ===\n");

        // JAVA: explicit narrowing cast required
        double pi = 3.99;
        int truncated = (int) pi;          // explicit cast
        long bigNum = 100L;
        int smaller = (int) bigNum;        // explicit narrowing
        double widened = smaller;          // implicit widening (no cast needed)

        System.out.println("double->int (truncates): " + pi + " -> " + truncated);
        System.out.println("widening (implicit): int -> double: " + widened);

        /*
         * PYTHON (duck typing, mostly automatic):
         *   int(3.99)      → 3   (explicit conversion function)
         *   float(5)       → 5.0
         *   str(42)        → "42"
         *   int("42")      → 42
         *   # No implicit numeric widening — Python just handles it
         *
         * JAVASCRIPT (coercion — beware!):
         *   "5" + 3        → "53"  (string wins with +)
         *   "5" - 3        → 2     (numeric with -)
         *   parseInt("3.99") → 3
         *   Number("42")   → 42
         *   +"42"          → 42    (unary + coerces to number)
         *   Boolean(0)     → false (0, "", null, undefined, NaN are falsy)
         *
         * C# (very similar to Java):
         *   (int)3.99      → 3     // explicit cast (same syntax!)
         *   Convert.ToInt32(3.99) → 4  // rounds (different from Java!)
         *   double d = 5;  → implicit widening (same as Java)
         */

        System.out.println("\n=== 4. NULL / NONE / UNDEFINED ===\n");

        // JAVA: null for object references, primitives can't be null
        String str = null;
        System.out.println("null reference: " + str);
        try {
            str.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
        }

        // JAVA: Integer (wrapper) CAN be null; int cannot
        Integer boxed = null;
        int primitive = 5;
        // int bad = null; // compile error!

        /*
         * PYTHON:
         *   x = None       # Python's null equivalent
         *   if x is None:  # use 'is None', not == None
         *       print("nothing here")
         *
         * JAVASCRIPT has TWO empty values:
         *   let x = null;       # intentionally empty object
         *   let y;              # undefined — declared but not assigned
         *   null == undefined   → true  (loose equality)
         *   null === undefined  → false (strict equality)
         *
         * C#:
         *   string s = null;    # same as Java
         *   int? nullable = null; # nullable int using ? syntax (no Java equivalent)
         *   s?.Length           # null-safe operator (no Java equivalent, Java uses Optional)
         */

        System.out.println("\n=== 5. TYPE CHECKING ===\n");

        Object obj = "Hello";
        System.out.println("Java instanceof: " + (obj instanceof String));
        System.out.println("Java getClass:   " + obj.getClass().getSimpleName());

        /*
         * PYTHON:
         *   isinstance(obj, str)   → True
         *   type(obj)              → <class 'str'>
         *   type(obj) == str       → True
         *
         * JAVASCRIPT:
         *   typeof "hello"         → "string"
         *   typeof 42              → "number"
         *   typeof null            → "object"  ← famous JS bug!
         *   "hello" instanceof String → false  ← primitives aren't objects
         *   Array.isArray([])      → true
         *
         * C# (pattern matching, Java 16+ has similar):
         *   obj is string          → true
         *   obj is string s        → true AND assigns to s
         *   obj.GetType().Name     → "String"
         */

        System.out.println("\n=== 6. CONSTANTS ===\n");

        // JAVA: final keyword
        final double TAX_RATE = 0.08;
        final int MAX_SIZE = 100;
        System.out.println("TAX_RATE=" + TAX_RATE + ", MAX_SIZE=" + MAX_SIZE);

        /*
         * PYTHON: convention only (ALL_CAPS), not enforced by compiler
         *   TAX_RATE = 0.08   # "please don't change this" — no enforcement
         *
         * JAVASCRIPT:
         *   const TAX_RATE = 0.08;  // binding is immutable, but objects can mutate
         *   const arr = [1,2,3];
         *   arr.push(4);            // this is ALLOWED — const ≠ deep immutable
         *
         * C#:
         *   const double TAX_RATE = 0.08;   // compile-time constant (same as Java final)
         *   readonly int maxSize = 100;      // runtime constant (set in constructor)
         */
    }
}
