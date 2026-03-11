package basics;

/**
 * Topic 2: Java Operators
 *
 * Covers arithmetic, relational, logical, bitwise, assignment,
 * and ternary operators with common pitfalls.
 *
 * Try to predict the output before running!
 */
public class T02_Operators {

    public static void main(String[] args) {

        System.out.println("=== 1. ARITHMETIC OPERATORS ===\n");

        int a = 17, b = 5;
        System.out.println("a=17, b=5");
        System.out.println("a + b = " + (a + b));   // 22
        System.out.println("a - b = " + (a - b));   // 12
        System.out.println("a * b = " + (a * b));   // 85
        System.out.println("a / b = " + (a / b));   // 3 (integer division!)
        System.out.println("a % b = " + (a % b));   // 2 (remainder)

        // Modulus with negative numbers
        System.out.println("-17 % 5 = " + (-17 % 5)); // -2 (sign follows dividend)

        System.out.println("\n=== 2. INCREMENT & DECREMENT ===\n");

        int x = 5;
        System.out.println("x = " + x);
        System.out.println("x++ = " + x++); // Post: prints 5, THEN increments
        System.out.println("x is now: " + x); // 6
        System.out.println("++x = " + ++x); // Pre: increments FIRST, then prints 7

        // Classic tricky question
        int y = 10;
        y = y++;  // y gets old value (10), then increment is lost!
        System.out.println("After y = y++: " + y); // Still 10!

        System.out.println("\n=== 3. RELATIONAL OPERATORS ===\n");

        System.out.println("5 == 5: " + (5 == 5));   // true
        System.out.println("5 != 3: " + (5 != 3));   // true
        System.out.println("5 > 3:  " + (5 > 3));    // true
        System.out.println("5 < 3:  " + (5 < 3));    // false
        System.out.println("5 >= 5: " + (5 >= 5));   // true
        System.out.println("5 <= 4: " + (5 <= 4));   // false

        // == vs .equals() for objects
        String s1 = new String("hello");
        String s2 = new String("hello");
        String s3 = "hello";
        String s4 = "hello";
        System.out.println("\ns1 == s2 (new String): " + (s1 == s2));       // false (different objects)
        System.out.println("s1.equals(s2): " + s1.equals(s2));              // true (same content)
        System.out.println("s3 == s4 (string pool): " + (s3 == s4));       // true (same pool reference)

        System.out.println("\n=== 4. LOGICAL OPERATORS ===\n");

        boolean t = true, f = false;
        System.out.println("true && false: " + (t && f));  // false
        System.out.println("true || false: " + (t || f));  // true
        System.out.println("!true: " + (!t));               // false

        // Short-circuit evaluation
        int val = 0;
        boolean result = (val != 0) && (10 / val > 1); // Second part NOT evaluated!
        System.out.println("Short-circuit prevents ArithmeticException: " + result);

        // Non-short-circuit (bitwise on booleans)
        // boolean crash = (val != 0) & (10 / val > 1); // Would throw ArithmeticException!

        System.out.println("\n=== 5. BITWISE OPERATORS ===\n");

        int p = 5;  // 0101 in binary
        int q = 3;  // 0011 in binary
        System.out.println("5 & 3 (AND):  " + (p & q));  // 0001 = 1
        System.out.println("5 | 3 (OR):   " + (p | q));  // 0111 = 7
        System.out.println("5 ^ 3 (XOR):  " + (p ^ q));  // 0110 = 6
        System.out.println("~5 (NOT):     " + (~p));      // -6
        System.out.println("5 << 1 (left shift):  " + (p << 1)); // 1010 = 10
        System.out.println("5 >> 1 (right shift): " + (p >> 1)); // 0010 = 2

        System.out.println("\n=== 6. TERNARY OPERATOR ===\n");

        int age = 20;
        String status = (age >= 18) ? "Adult" : "Minor";
        System.out.println("Age " + age + ": " + status);

        // Nested ternary (avoid in production code, but know for exams)
        int score = 75;
        String grade = (score >= 90) ? "A" : (score >= 80) ? "B" : (score >= 70) ? "C" : "F";
        System.out.println("Score " + score + ": Grade " + grade);

        System.out.println("\n=== 7. COMPOUND ASSIGNMENT (implicit casting!) ===\n");

        byte bb = 10;
        // bb = bb + 5; // Compile error! bb + 5 promotes to int
        bb += 5;        // OK! Compound assignment includes implicit cast
        System.out.println("byte += works: " + bb);

        System.out.println("\n=== 8. OPERATOR PRECEDENCE TRAPS ===\n");

        // Multiplication before addition
        System.out.println("2 + 3 * 4 = " + (2 + 3 * 4)); // 14, not 20

        // Assignment is right-associative
        int r1, r2, r3;
        r1 = r2 = r3 = 100;
        System.out.println("r1=r2=r3=100: " + r1 + ", " + r2 + ", " + r3);
    }
}
