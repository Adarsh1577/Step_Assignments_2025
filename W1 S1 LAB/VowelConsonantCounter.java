import java.util.Scanner;

public class VowelConsonantCounter {

    // Method to check if a character is vowel or consonant
    public static String checkCharType(char ch) {
        // Convert uppercase to lowercase using ASCII values
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char)(ch + 32); // ASCII trick to convert uppercase to lowercase
        }

        if (ch >= 'a' && ch <= 'z') {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                return "Vowel";
            } else {
                return "Consonant";
            }
        }
        return "Not a Letter";
    }

    // Method to count vowels and consonants
    public static int[] countVowelsAndConsonants(String str) {
        int vowels = 0, consonants = 0;

        try {
            int i = 0;
            while (true) {
                char ch = str.charAt(i);
                String type = checkCharType(ch);

                if (type.equals("Vowel")) {
                    vowels++;
                } else if (type.equals("Consonant")) {
                    consonants++;
                }
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string
        }

        return new int[]{vowels, consonants};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        int[] result = countVowelsAndConsonants(input);

        System.out.println("\nTotal Vowels: " + result[0]);
        System.out.println("Total Consonants: " + result[1]);

        sc.close();
    }
}
