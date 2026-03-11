// CL02: Strings in C#
// Companion to CL02_StringsAcrossLanguages.java
//
// C# strings are immutable like Java.
// String interpolation ($"...") is very similar to Python f-strings.
// StringBuilder has nearly identical API to Java's StringBuilder.
//
// Run: dotnet-script CL02_Strings.cs

using System;
using System.Text;

class CL02_Strings
{
    static void Main()
    {
        Console.WriteLine("=== 1. STRING CREATION ===\n");

        string s1 = "Hello, World!";
        string s2 = new string('*', 5);   // "-----" — repeat character
        string verbatim = @"C:\Users\Alice\Documents";  // verbatim: no escape needed
        string multiline = @"Line 1
Line 2
Line 3";

        Console.WriteLine(s1);
        Console.WriteLine(verbatim);      // no need for C:\\Users\\Alice
        Console.WriteLine(multiline);

        Console.WriteLine("\n=== 2. STRING INTERPOLATION ===\n");

        string name = "Alice";
        int age = 30;
        double score = 98.5;

        // $ interpolation — nearly identical to Python f-strings
        Console.WriteLine($"Name: {name}, Age: {age}");
        Console.WriteLine($"Score: {score:F1}");          // format specifier
        Console.WriteLine($"Pi = {Math.PI:F3}");
        Console.WriteLine($"Upper: {name.ToUpper()}");    // expressions inside {}
        Console.WriteLine($"Today: {DateTime.Now:yyyy-MM-dd}");

        // string.Format() — like Java's String.format()
        Console.WriteLine(string.Format("Name: {0}, Age: {1}", name, age));

        // Combine verbatim and interpolation
        string path = $@"C:\Users\{name}\Documents";
        Console.WriteLine($"Path: {path}");

        Console.WriteLine("\n=== 3. COMMON STRING METHODS ===\n");

        string str = "  Hello, World!  ";
        Console.WriteLine($"original:        '{str}'");
        Console.WriteLine($"Length:          {str.Trim().Length}");          // property!
        Console.WriteLine($"ToUpper():       {str.ToUpper()}");
        Console.WriteLine($"ToLower():       {str.ToLower()}");
        Console.WriteLine($"Trim():          '{str.Trim()}'");
        Console.WriteLine($"TrimStart():     '{str.TrimStart()}'");
        Console.WriteLine($"TrimEnd():       '{str.TrimEnd()}'");
        Console.WriteLine($"Contains():      {str.Contains("World")}");
        Console.WriteLine($"StartsWith():    {str.Trim().StartsWith("Hello")}");
        Console.WriteLine($"EndsWith():      {str.Trim().EndsWith("!")}");
        Console.WriteLine($"IndexOf():       {str.IndexOf('o')}");
        Console.WriteLine($"Substring(7,5):  '{str.Trim().Substring(7, 5)}'");  // (start, LENGTH) not end!
        Console.WriteLine($"Replace():       {str.Replace("World", "C#")}");

        // Split
        string csv = "apple,banana,cherry";
        string[] parts = csv.Split(',');
        Console.WriteLine($"Split: [{string.Join(", ", parts)}]");

        // Join
        string joined = string.Join(" | ", parts);
        Console.WriteLine($"Join: {joined}");

        Console.WriteLine("\n=== 4. KEY DIFFERENCE: Substring(start, LENGTH) ===\n");

        string s = "Hello, World!";
        // Java:  substring(7, 12) = indices 7 to 11
        // C#:    Substring(7, 5)  = start=7, LENGTH=5  (not end index!)
        Console.WriteLine($"Java: substring(7,12) = 'World'");
        Console.WriteLine($"C#:   Substring(7, 5) = '{s.Substring(7, 5)}'");  // "World"

        // C# also supports slicing with Range (C# 8+)
        Console.WriteLine($"s[7..12] = '{s[7..12]}'");   // same as Substring(7,5)
        Console.WriteLine($"s[^1]    = '{s[^1]}'");       // last char
        Console.WriteLine($"s[^6..]  = '{s[^6..]}'");     // last 6 chars

        Console.WriteLine("\n=== 5. IMMUTABILITY ===\n");

        string original = "hello";
        string modified = original.ToUpper();
        Console.WriteLine($"original unchanged: {original}");
        Console.WriteLine($"new string:         {modified}");

        // In C#, == compares CONTENT for strings (unlike Java where == compares reference!)
        string a = new string("hello".ToCharArray());
        string b = new string("hello".ToCharArray());
        Console.WriteLine($"a == b (content!):     {a == b}");      // true  (unlike Java!)
        Console.WriteLine($"ReferenceEquals(a,b):  {ReferenceEquals(a, b)}"); // false

        Console.WriteLine("\n=== 6. STRINGBUILDER (identical API to Java!) ===\n");

        // C# StringBuilder is almost identical to Java's
        StringBuilder sb = new StringBuilder();
        sb.Append("Hello");           // append()
        sb.Append(", ");
        sb.Append("World!");
        sb.Insert(5, "...");          // insert()
        sb.Replace("World", "C#");    // replace (replaces ALL occurrences)
        Console.WriteLine($"StringBuilder: {sb}");
        Console.WriteLine($"Length: {sb.Length}");

        // Performance: string concatenation in loop (bad) vs StringBuilder (good)
        var sw = System.Diagnostics.Stopwatch.StartNew();
        string slow = "";
        for (int i = 0; i < 50000; i++) slow += "a";
        sw.Stop();
        Console.WriteLine($"String concat: {sw.ElapsedMilliseconds}ms");

        sw.Restart();
        var fast = new StringBuilder();
        for (int i = 0; i < 50000; i++) fast.Append("a");
        sw.Stop();
        Console.WriteLine($"StringBuilder: {sw.ElapsedMilliseconds}ms");

        Console.WriteLine("\n=== 7. USEFUL EXTRAS ===\n");

        // String.IsNullOrEmpty / IsNullOrWhiteSpace (no Java equivalent built-in)
        Console.WriteLine($"IsNullOrEmpty(\"\"): {string.IsNullOrEmpty("")}");
        Console.WriteLine($"IsNullOrWhiteSpace(\"  \"): {string.IsNullOrWhiteSpace("  ")}");

        // Palindrome
        string word = "racecar";
        char[] chars = word.ToCharArray();
        Array.Reverse(chars);
        Console.WriteLine($"'{word}' palindrome: {word == new string(chars)}");
    }
}
