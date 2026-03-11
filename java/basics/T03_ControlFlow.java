package basics;

/**
 * Topic 3: Control Flow Statements
 *
 * Covers if/else, switch, for, while, do-while, break, continue,
 * and labeled loops.
 */
public class T03_ControlFlow {

    public static void main(String[] args) {

        System.out.println("=== 1. IF / ELSE IF / ELSE ===\n");

        int temperature = 25;
        if (temperature > 30) {
            System.out.println("Hot");
        } else if (temperature > 20) {
            System.out.println("Warm");  // This prints
        } else if (temperature > 10) {
            System.out.println("Cool");
        } else {
            System.out.println("Cold");
        }

        System.out.println("\n=== 2. SWITCH STATEMENT ===\n");

        // Classic switch (works with byte, short, char, int, String, enums)
        String day = "MONDAY";
        switch (day) {
            case "MONDAY":
            case "TUESDAY":
            case "WEDNESDAY":
            case "THURSDAY":
            case "FRIDAY":
                System.out.println(day + " is a weekday");
                break; // Without break, falls through to next case!
            case "SATURDAY":
            case "SUNDAY":
                System.out.println(day + " is a weekend");
                break;
            default:
                System.out.println("Invalid day");
        }

        // Fall-through demo
        System.out.println("\nFall-through demo (no breaks):");
        int num = 1;
        switch (num) {
            case 1: System.out.println("One");
            case 2: System.out.println("Two");   // Falls through!
            case 3: System.out.println("Three"); // Falls through!
            default: System.out.println("Default"); // Falls through!
        }

        System.out.println("\n=== 3. FOR LOOP ===\n");

        // Standard for loop
        System.out.print("Standard for: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Multiple variables in for loop
        System.out.print("Multi-var for: ");
        for (int i = 0, j = 10; i < j; i++, j--) {
            System.out.print("(" + i + "," + j + ") ");
        }
        System.out.println();

        // Enhanced for-each loop
        int[] numbers = {10, 20, 30, 40, 50};
        System.out.print("For-each: ");
        for (int n : numbers) {
            System.out.print(n + " ");
        }
        System.out.println();

        System.out.println("\n=== 4. WHILE & DO-WHILE ===\n");

        // while: checks condition BEFORE executing
        int count = 0;
        System.out.print("While: ");
        while (count < 5) {
            System.out.print(count + " ");
            count++;
        }
        System.out.println();

        // do-while: executes AT LEAST ONCE, then checks condition
        int val = 100;
        System.out.print("Do-while (starts at 100, condition < 5): ");
        do {
            System.out.print(val + " "); // Prints once even though 100 < 5 is false
            val++;
        } while (val < 5);
        System.out.println("(executed once!)");

        System.out.println("\n=== 5. BREAK & CONTINUE ===\n");

        // break: exits the loop entirely
        System.out.print("Break at 3: ");
        for (int i = 0; i < 10; i++) {
            if (i == 3) break;
            System.out.print(i + " ");
        }
        System.out.println();

        // continue: skips current iteration
        System.out.print("Continue (skip evens): ");
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) continue;
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("\n=== 6. LABELED LOOPS ===\n");

        // Labels let you break/continue outer loops from inner loops
        System.out.println("Labeled break (find first pair summing to 6):");
        outer:
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                if (i + j == 6) {
                    System.out.println("Found: i=" + i + ", j=" + j);
                    break outer; // Breaks out of BOTH loops
                }
            }
        }

        System.out.println("\n=== 7. NESTED LOOP PATTERN (Classic!) ===\n");

        // Print a triangle pattern
        int rows = 5;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println("\n=== 8. INFINITE LOOP FORMS (know for exams) ===\n");
        System.out.println("// for(;;) { }         -- infinite for loop");
        System.out.println("// while(true) { }     -- infinite while loop");
        System.out.println("// do { } while(true); -- infinite do-while");
    }
}
