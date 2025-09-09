import java.util.Scanner;

public class CharFrequencyWithUnique {

    public static char[] uniqueCharacters(String text) {
        int length = 0;
        try {
            while (true) {
                text.charAt(length);
                length++;
            }
        } catch (Exception e) {
        }

        char[] temp = new char[length];
        int uniqueCount = 0;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            boolean isUnique = true;
            for (int j = 0; j < i; j++) {
                if (text.charAt(j) == c) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                temp[uniqueCount] = c;
                uniqueCount++;
            }
        }

        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    public static String[][] findFrequency(String text) {
        int[] freq = new int[256];
        int length = 0;

        try {
            while (true) {
                char c = text.charAt(length);
                freq[c]++;
                length++;
            }
        } catch (Exception e) {
        }

        char[] uniqueChars = uniqueCharacters(text);
        String[][] result = new String[uniqueChars.length][2];

        for (int i = 0; i < uniqueChars.length; i++) {
            result[i][0] = String.valueOf(uniqueChars[i]);
            result[i][1] = String.valueOf(freq[uniqueChars[i]]);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] frequencies = findFrequency(input);

        System.out.println("\nCharacter   Frequency");
        System.out.println("---------------------");
        for (String[] row : frequencies) {
            System.out.printf("%-10s %s%n", row[0], row[1]);
        }

        sc.close();
    }
}
