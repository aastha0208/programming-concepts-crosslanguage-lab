package crosslang;

/**
 * CL02: Strings Across Languages
 *
 * Java    → Immutable String class, StringBuilder for mutation
 * Python  → Immutable str, f-strings for formatting
 * JS      → Immutable string primitive, template literals
 * C#      → Immutable string class (identical to Java), StringBuilder
 *
 * Run: java -cp bin com.sample.BasicsRefresh.CrossLanguage.CL02_StringsAcrossLanguages
 */
public class CL02_StringsAcrossLanguages {

    public static void main(String[] args) {

        System.out.println("=== 1. STRING CREATION ===\n");

        String s1 = "Hello, World!";
        String s2 = new String("Hello"); // avoids string pool (rarely needed)
        System.out.println(s1 + " | " + s2);

        /*
         * PYTHON:
         *   s1 = 'Hello, World!'   # single or double quotes, both fine
         *   s2 = "Hello"
         *   s3 = '''Multi
         *   line'''                # triple quotes for multiline
         *
         * JAVASCRIPT:
         *   let s1 = "Hello, World!";
         *   let s2 = 'Hello';      // single or double quotes
         *   let s3 = `Multi
         *   line`;                 // backtick template literal supports multiline
         *
         * C#:
         *   string s1 = "Hello, World!";   // same as Java
         *   string s2 = @"C:\Users\file";  // verbatim string (no escape needed)
         *   string s3 = """
         *       Multi line          // raw string (C# 11+)
         *   """;
         */

        System.out.println("\n=== 2. STRING INTERPOLATION / FORMATTING ===\n");

        String name = "Alice";
        int age = 30;

        // JAVA: String.format() or concatenation
        String formatted1 = String.format("Name: %s, Age: %d", name, age);
        String formatted2 = "Name: " + name + ", Age: " + age;
        System.out.println(formatted1);
        System.out.println(formatted2);

        /*
         * PYTHON (f-strings are the cleanest):
         *   f"Name: {name}, Age: {age}"          # f-string (Python 3.6+)
         *   "Name: {}, Age: {}".format(name, age) # .format()
         *   "Name: %s, Age: %d" % (name, age)    # old % style
         *
         * JAVASCRIPT (template literals):
         *   `Name: ${name}, Age: ${age}`          # template literal (ES6+)
         *   "Name: " + name + ", Age: " + age     # concatenation
         *
         * C# (string interpolation, very similar to Python f-strings):
         *   $"Name: {name}, Age: {age}"            # interpolation (C# 6+)
         *   string.Format("Name: {0}, Age: {1}", name, age)  # Format()
         *   $"Pi = {Math.PI:F2}"                   # format specifier inside {}
         */

        System.out.println("\n=== 3. COMMON STRING METHODS ===\n");

        String str = "  Hello, World!  ";

        // JAVA
        System.out.println("length():      " + str.trim().length());
        System.out.println("toUpperCase(): " + str.trim().toUpperCase());
        System.out.println("substring(0,5):" + str.trim().substring(0, 5));
        System.out.println("contains():    " + str.contains("World"));
        System.out.println("replace():     " + str.trim().replace("World", "Java"));
        System.out.println("split():       " + java.util.Arrays.toString(str.trim().split(", ")));
        System.out.println("startsWith():  " + str.trim().startsWith("Hello"));
        System.out.println("indexOf():     " + str.trim().indexOf('o'));
        System.out.println("trim():        [" + str.trim() + "]");
        System.out.println("strip():       [" + str.strip() + "]"); // Java 11+ (Unicode-aware)

        /*
         * PYTHON (very similar method names):
         *   str.upper()             # toUpperCase()
         *   str.lower()             # toLowerCase()
         *   str[0:5]                # substring(0,5) - Python uses slicing
         *   str[-3:]                # last 3 chars — no Java equivalent directly
         *   "World" in str          # contains() — Python uses 'in' operator
         *   str.replace("x","y")    # same
         *   str.split(", ")         # same
         *   str.startswith("Hello") # startsWith() — lowercase 'with'
         *   str.find('o')           # indexOf()
         *   str.strip()             # trim()
         *   str.lstrip()            # trimLeft / stripLeading
         *   str.rstrip()            # trimRight / stripTrailing
         *
         * JAVASCRIPT:
         *   str.toUpperCase()       # same as Java
         *   str.slice(0, 5)         # substring — prefer slice over substr
         *   str.includes("World")   # contains()
         *   str.replace("x","y")    # replaces FIRST occurrence only!
         *   str.replaceAll("x","y") # replaces ALL (ES2021+)
         *   str.split(", ")         # same
         *   str.startsWith("Hello") # same
         *   str.indexOf('o')        # same
         *   str.trim()              # same
         *   str.padStart(10, '0')   # no Java equivalent without format
         *
         * C# (very close to Java, different naming convention):
         *   str.ToUpper()           # toUpperCase()
         *   str.ToLower()           # toLowerCase()
         *   str.Substring(0, 5)     # substring(0, 5) — capital S
         *   str.Contains("World")   # contains() — capital C
         *   str.Replace("x","y")    # replace() — capital R
         *   str.Split(", ")         # split()
         *   str.StartsWith("Hello") # startsWith()
         *   str.IndexOf('o')        # indexOf()
         *   str.Trim()              # trim()
         *   str.TrimStart()         # stripLeading()
         *   str.TrimEnd()           # stripTrailing()
         */

        System.out.println("\n=== 4. STRING IMMUTABILITY ===\n");

        String original = "Hello";
        String modified = original.toUpperCase(); // creates NEW string
        System.out.println("original unchanged: " + original);
        System.out.println("new string:         " + modified);

        /*
         * All 4 languages have IMMUTABLE strings.
         * Every modification returns a NEW string — the original is unchanged.
         *
         * PYTHON:
         *   s = "hello"
         *   s.upper()   # returns new string, s still "hello"
         *
         * JAVASCRIPT:
         *   let s = "hello";
         *   s.toUpperCase();  # returns new, s still "hello"
         *
         * C#: identical behavior to Java
         *   string s = "hello";
         *   s.ToUpper();  # returns new, s still "hello"
         */

        System.out.println("\n=== 5. MUTABLE STRING BUILDERS ===\n");

        // JAVA: StringBuilder (not thread-safe) / StringBuffer (thread-safe)
        StringBuilder sb = new StringBuilder();
        sb.append("Hello");
        sb.append(", ");
        sb.append("World!");
        sb.insert(5, "...");
        System.out.println("StringBuilder: " + sb);
        System.out.println("Length: " + sb.length());

        /*
         * PYTHON: strings are immutable — use join() for efficiency
         *   parts = ["Hello", ", ", "World!"]
         *   result = "".join(parts)   # efficient, no intermediate strings
         *   # Or use io.StringIO for builder-like pattern
         *
         * JAVASCRIPT: use array + join() or template literals
         *   const parts = [];
         *   parts.push("Hello");
         *   parts.push(", ");
         *   parts.push("World!");
         *   const result = parts.join("");
         *
         * C#: StringBuilder has IDENTICAL API to Java!
         *   var sb = new StringBuilder();
         *   sb.Append("Hello");
         *   sb.Append(", ");
         *   sb.Append("World!");
         *   sb.Insert(5, "...");
         *   Console.WriteLine(sb.ToString());
         */

        System.out.println("\n=== 6. STRING COMPARISON ===\n");

        String a = new String("hello");
        String b = new String("hello");
        System.out.println("== (reference):   " + (a == b));         // false
        System.out.println(".equals():        " + a.equals(b));      // true
        System.out.println("compareTo():      " + a.compareTo("world")); // negative (h < w)
        System.out.println("equalsIgnoreCase: " + a.equalsIgnoreCase("HELLO")); // true

        /*
         * PYTHON:
         *   a == b             # content equality (like .equals() in Java)
         *   a is b             # reference equality (like == in Java)
         *   a < b              # lexicographic comparison (like compareTo)
         *
         * JAVASCRIPT:
         *   a == b             # loose equality (may coerce types!)
         *   a === b            # strict equality (no coercion) — use this!
         *   a < b              # lexicographic
         *   a.localeCompare(b) # locale-aware comparison (like compareTo)
         *
         * C#:
         *   a == b             # content equality for strings (unlike Java!)
         *   a.Equals(b)        # also content equality
         *   string.Compare(a, b) # like compareTo
         *   a.Equals(b, StringComparison.OrdinalIgnoreCase) # like equalsIgnoreCase
         *
         * KEY DIFFERENCE: In C#, == on strings compares CONTENT (like .equals() in Java)
         *                 In Java, == compares REFERENCES
         */
    }
}
