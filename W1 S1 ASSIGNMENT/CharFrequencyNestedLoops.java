import java.util.Scanner;

public class CharFrequencyNestedLoops {

    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int[] freq = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') continue;
            freq[i] = 1;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0';
                }
            }
        }

        int count = 0;
        for (int f : freq) {
            if (f > 0) count++;
        }

        String[] result = new String[count];
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                result[index] = chars[i] + " -> " + freq[i];
                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[] frequencies = findFrequency(input);

        System.out.println("\nCharacter   Frequency");
        System.out.println("---------------------");
        for (String row : frequencies) {
            String[] parts = row.split(" -> ");
            System.out.printf("%-10s %s%n", parts[0], parts[1]);
        }

        sc.close();
    }
}
