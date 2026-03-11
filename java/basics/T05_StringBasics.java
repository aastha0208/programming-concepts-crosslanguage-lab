package basics;

/**
 * Topic 5: Strings, StringBuilder, and String Pool
 *
 * Strings are immutable in Java. Every modification creates a new object.
 * StringBuilder is mutable and efficient for repeated modifications.
 */
public class T05_StringBasics {

    public static void main(String[] args) {

        System.out.println("=== 1. STRING CREATION & IMMUTABILITY ===\n");

        // String literal (goes to String Pool)
        String s1 = "Hello";

        // new String (creates new object on heap, NOT in pool)
        String s2 = new String("Hello");

        // Strings are IMMUTABLE - every operation returns a new String
        String s3 = s1.concat(" World");
        System.out.println("s1 after concat: " + s1);  // Still "Hello"!
        System.out.println("s3 (new string): " + s3);  // "Hello World"

        System.out.println("\n=== 2. STRING POOL & COMPARISON ===\n");

        String a = "Java";
        String b = "Java";
        String c = new String("Java");

        System.out.println("a == b (both from pool): " + (a == b));           // true
        System.out.println("a == c (pool vs heap): " + (a == c));             // false
        System.out.println("a.equals(c) (content): " + a.equals(c));         // true
        System.out.println("a == c.intern() (interned): " + (a == c.intern())); // true

        System.out.println("\n=== 3. ESSENTIAL STRING METHODS ===\n");

        String str = "Hello, World!";
        System.out.println("Original:    \"" + str + "\"");
        System.out.println("length():    " + str.length());
        System.out.println("charAt(0):   " + str.charAt(0));
        System.out.println("indexOf('o'):   " + str.indexOf('o'));
        System.out.println("lastIndexOf('o'): " + str.lastIndexOf('o'));
        System.out.println("substring(7):    \"" + str.substring(7) + "\"");
        System.out.println("substring(0,5):  \"" + str.substring(0, 5) + "\"");
        System.out.println("toUpperCase():   \"" + str.toUpperCase() + "\"");
        System.out.println("toLowerCase():   \"" + str.toLowerCase() + "\"");
        System.out.println("trim():          \"" + "  spaces  ".trim() + "\"");
        System.out.println("replace('l','L'): \"" + str.replace('l', 'L') + "\"");
        System.out.println("contains(\"World\"): " + str.contains("World"));
        System.out.println("startsWith(\"Hello\"): " + str.startsWith("Hello"));
        System.out.println("endsWith(\"!\"): " + str.endsWith("!"));
        System.out.println("isEmpty():     " + str.isEmpty());
        System.out.println("isEmpty(\"\"): " + "".isEmpty());

        System.out.println("\n=== 4. STRING SPLITTING & JOINING ===\n");

        String csv = "apple,banana,cherry,date";
        String[] parts = csv.split(",");
        System.out.println("Split \"" + csv + "\":");
        for (int i = 0; i < parts.length; i++) {
            System.out.println("  [" + i + "] " + parts[i]);
        }

        // Join (Java 8+)
        String joined = String.join(" | ", parts);
        System.out.println("Joined: " + joined);

        System.out.println("\n=== 5. STRING CONVERSION ===\n");

        // Primitive to String
        int num = 42;
        String fromInt1 = String.valueOf(num);
        String fromInt2 = Integer.toString(num);
        String fromInt3 = "" + num; // Concatenation trick
        System.out.println("int to String: " + fromInt1);

        // String to Primitive
        int parsed = Integer.parseInt("123");
        double parsedD = Double.parseDouble("3.14");
        System.out.println("String to int: " + parsed);
        System.out.println("String to double: " + parsedD);

        // char array to/from String
        char[] chars = {'J', 'a', 'v', 'a'};
        String fromChars = new String(chars);
        char[] toChars = fromChars.toCharArray();
        System.out.println("char[] to String: " + fromChars);

        System.out.println("\n=== 6. STRINGBUILDER (Mutable!) ===\n");

        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");        // Modifies in place!
        sb.insert(5, ",");          // Insert at index
        sb.replace(0, 5, "Hi");     // Replace range
        sb.delete(2, 3);            // Delete range
        sb.reverse();               // Reverse
        System.out.println("After operations: " + sb);
        sb.reverse(); // Reverse back for readability
        System.out.println("Reversed back:    " + sb);
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());

        // Why StringBuilder matters: performance
        System.out.println("\nString concatenation in loop (bad):");
        String slow = "";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            slow = slow + "a"; // Creates a new String each time!
        }
        long stringTime = System.currentTimeMillis() - start;
        System.out.println("  String: " + stringTime + "ms");

        System.out.println("StringBuilder in loop (good):");
        StringBuilder fast = new StringBuilder();
        start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            fast.append("a"); // Modifies in place
        }
        long sbTime = System.currentTimeMillis() - start;
        System.out.println("  StringBuilder: " + sbTime + "ms");

        System.out.println("\n=== 7. COMMON STRING PROBLEMS ===\n");

        // Palindrome check
        String word = "racecar";
        String reversed = new StringBuilder(word).reverse().toString();
        System.out.println("\"" + word + "\" is palindrome: " + word.equals(reversed));

        // Count character occurrences
        String text = "mississippi";
        int count = 0;
        for (char ch : text.toCharArray()) {
            if (ch == 's') count++;
        }
        System.out.println("'s' count in \"" + text + "\": " + count);
    }
}
