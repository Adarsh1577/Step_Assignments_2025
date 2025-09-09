public class StringManipulation {
    public static void main(String[] args) {
        // 1. String literal
        String str1 = "Java Programming";

        // 2. Using new String() constructor
        String str2 = new String("Java Programming");

        // 3. Using character array
        char[] charArray = {'J', 'a', 'v', 'a', ' ', 'P', 'r', 'o', 'g', 'r', 'a', 'm', 'm', 'i', 'n', 'g'};
        String str3 = new String(charArray);

        // Print all the strings
        System.out.println("String 1 (literal): " + str1);
        System.out.println("String 2 (new constructor): " + str2);
        System.out.println("String 3 (char array): " + str3);

        // Compare using '==' (checks reference equality)
        System.out.println("\nComparisons using == :");
        System.out.println("str1 == str2: " + (str1 == str2)); // false, different memory locations
        System.out.println("str1 == str3: " + (str1 == str3)); // false
        System.out.println("str2 == str3: " + (str2 == str3)); // false

        // Compare using .equals() (checks content equality)
        System.out.println("\nComparisons using .equals() :");
        System.out.println("str1.equals(str2): " + str1.equals(str2)); // true
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true
        System.out.println("str2.equals(str3): " + str2.equals(str3)); // true

        // Explanation
        System.out.println("\nExplanation:");
        System.out.println("== checks if two references point to the same memory location.");
        System.out.println(".equals() checks if the content of the strings are the same.");

        // String with escape sequences
        String quote = "Programming Quote:\n\"Code is poetry\" - Unknown\nPath: C:\\Java\\Projects";

        // Print escape sequence string
        System.out.println("\n" + quote);
    }
}
