import java.util.Scanner;

public class CharFrequency {

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

        String[][] result = new String[length][2];
        boolean[] visited = new boolean[256];
        int index = 0;

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (!visited[c]) {
                visited[c] = true;
                result[index][0] = String.valueOf(c);
                result[index][1] = String.valueOf(freq[c]);
                index++;
            }
        }

        String[][] finalResult = new String[index][2];
        for (int i = 0; i < index; i++) {
            finalResult[i][0] = result[i][0];
            finalResult[i][1] = result[i][1];
        }

        return finalResult;
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
