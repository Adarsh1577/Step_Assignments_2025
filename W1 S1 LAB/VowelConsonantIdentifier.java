import java.util.Scanner;

public class VowelConsonantIdentifier {

    // Method to check character type
    public static String checkCharType(char ch) {
        // Convert uppercase to lowercase using ASCII
        if (ch >= 'A' && ch <= 'Z') {
            ch = (char)(ch + 32);
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

    // Method to analyze string and return 2D array [character, type]
    public static String[][] analyzeString(String str) {
        String[][] result = new String[str.length()][2];
        try {
            int i = 0;
            while (true) {
                char ch = str.charAt(i);
                result[i][0] = String.valueOf(ch);
                result[i][1] = checkCharType(ch);
                i++;
            }
        } catch (IndexOutOfBoundsException e) {
            // End of string reached
        }
        return result;
    }

    // Method to display 2D array in tabular format
    public static void displayTable(String[][] data) {
        System.out.println("\nCharacter\tType");
        System.out.println("-------------------------");
        for (String[] row : data) {
            System.out.println(row[0] + "\t\t" + row[1]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] result = analyzeString(input);

        displayTable(result);

        sc.close();
    }
}
