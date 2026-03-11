package basics;

/**
 * Topic 1: Java Primitive Data Types & Literals
 *
 * Java has 8 primitive types. Understanding their sizes, ranges,
 * and default values is fundamental to writing correct Java code.
 *
 * Run this file and try to predict the output before checking!
 */
public class T01_DataTypes {

    // Instance variables get default values
    static byte defaultByte;
    static short defaultShort;
    static int defaultInt;
    static long defaultLong;
    static float defaultFloat;
    static double defaultDouble;
    static char defaultChar;
    static boolean defaultBoolean;

    public static void main(String[] args) {

        System.out.println("=== 1. PRIMITIVE DATA TYPES & SIZES ===\n");

        // byte: 8-bit signed integer (-128 to 127)
        byte b = 127;
        System.out.println("byte max: " + b);
        // b = 128; // Compile error! Out of range

        // short: 16-bit signed integer (-32768 to 32767)
        short s = 32767;
        System.out.println("short max: " + s);

        // int: 32-bit signed integer (most commonly used)
        int i = 2_147_483_647; // underscores allowed in numeric literals (Java 7+)
        System.out.println("int max: " + i);

        // long: 64-bit signed integer (use L suffix)
        long l = 9_223_372_036_854_775_807L;
        System.out.println("long max: " + l);

        // float: 32-bit floating point (use F suffix)
        float f = 3.14F;
        System.out.println("float: " + f);

        // double: 64-bit floating point (default for decimal literals)
        double d = 3.141592653589793;
        System.out.println("double: " + d);

        // char: 16-bit Unicode character
        char c = 'A';
        char cUnicode = '\u0041'; // Also 'A'
        System.out.println("char: " + c + ", unicode: " + cUnicode);

        // boolean: true or false
        boolean flag = true;
        System.out.println("boolean: " + flag);

        System.out.println("\n=== 2. DEFAULT VALUES (for fields, NOT local vars) ===\n");
        System.out.println("byte default:    " + defaultByte);
        System.out.println("short default:   " + defaultShort);
        System.out.println("int default:     " + defaultInt);
        System.out.println("long default:    " + defaultLong);
        System.out.println("float default:   " + defaultFloat);
        System.out.println("double default:  " + defaultDouble);
        System.out.println("char default:    [" + defaultChar + "] (null character \\u0000)");
        System.out.println("boolean default: " + defaultBoolean);

        System.out.println("\n=== 3. NUMERIC LITERAL FORMATS ===\n");
        int decimal = 26;
        int binary = 0b11010;      // Binary (prefix 0b)
        int octal = 032;           // Octal (prefix 0)
        int hex = 0x1A;            // Hexadecimal (prefix 0x)
        System.out.println("All equal 26: " + decimal + ", " + binary + ", " + octal + ", " + hex);

        System.out.println("\n=== 4. TYPE CASTING ===\n");

        // Widening (implicit) - no data loss
        int myInt = 100;
        long myLong = myInt;      // int -> long (automatic)
        double myDouble = myLong; // long -> double (automatic)
        System.out.println("Widening: int " + myInt + " -> long " + myLong + " -> double " + myDouble);

        // Narrowing (explicit) - possible data loss
        double pi = 3.99;
        int truncated = (int) pi; // Truncates, does NOT round!
        System.out.println("Narrowing: double " + pi + " -> int " + truncated + " (truncated!)");

        // Overflow behavior
        byte overflow = (byte) 128; // Wraps around!
        System.out.println("byte overflow (128): " + overflow + " (wraps to -128)");

        System.out.println("\n=== 5. TRICKY QUIZ QUESTIONS ===\n");

        // Q1: What happens with integer division?
        System.out.println("10 / 3 = " + (10 / 3));       // 3, not 3.33!
        System.out.println("10.0 / 3 = " + (10.0 / 3));   // 3.333...

        // Q2: char arithmetic
        char ch = 'A';
        System.out.println("'A' + 1 = " + (ch + 1));      // 66 (int!)
        System.out.println("(char)('A' + 1) = " + (char)(ch + 1)); // 'B'

        // Q3: String concatenation with +
        System.out.println("1 + 2 + \"3\" = " + (1 + 2 + "3"));   // "33"
        System.out.println("\"1\" + 2 + 3 = " + ("1" + 2 + 3));   // "123"
    }
}
