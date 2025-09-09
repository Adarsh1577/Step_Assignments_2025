import java.util.Scanner;

public class FirstNonRepeatingChar {

    public static char findFirstNonRepeating(String text) {
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

        for (int i = 0; i < length; i++) {
            char c = text.charAt(i);
            if (freq[c] == 1) {
                return c;
            }
        }
        return '\0';
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        char result = findFirstNonRepeating(input);

        if (result != '\0') {
            System.out.println("First non-repeating character: " + result);
        } else {
            System.out.println("No non-repeating character found.");
        }
        sc.close();
    }
}
