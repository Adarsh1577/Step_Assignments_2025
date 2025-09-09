import java.util.*;

public class ASCIIProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\n=== Character Analysis ===");
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;

            System.out.println("Character: " + ch + " | ASCII: " + ascii);
            String type = classifyCharacter(ch);
            System.out.println("Type: " + type);

            if (Character.isLetter(ch)) {
                char toggled = toggleCase(ch);
                System.out.println("Toggled Case: " + toggled + " | ASCII: " + (int) toggled);
                if (Character.isUpperCase(ch)) {
                    int diff = ch - Character.toLowerCase(ch);
                    System.out.println("ASCII Difference (Upper - Lower): " + diff);
                } else {
                    int diff = Character.toUpperCase(ch) - ch;
                    System.out.println("ASCII Difference (Upper - Lower): " + diff);
                }
            }
            System.out.println();
        }

        System.out.println("\n=== ASCII Table (65 to 90) ===");
        displayASCIITable(65, 90);  // uppercase letters

        System.out.println("\n=== Caesar Cipher (Shift 3) ===");
        String cipher = caesarCipher(input, 3);
        System.out.println("Encrypted: " + cipher);
        System.out.println("Decrypted: " + caesarCipher(cipher, -3));

        System.out.println("\n=== ASCII Conversion ===");
        int[] asciiArray = stringToASCII(input);
        System.out.println("ASCII Array: " + Arrays.toString(asciiArray));
        String backToString = asciiToString(asciiArray);
        System.out.println("Back to String: " + backToString);

        scanner.close();
    }

    public static String classifyCharacter(char ch) {
        if (ch >= 'A' && ch <= 'Z') return "Uppercase Letter";
        else if (ch >= 'a' && ch <= 'z') return "Lowercase Letter";
        else if (ch >= '0' && ch <= '9') return "Digit";
        else return "Special Character";
    }

    public static char toggleCase(char ch) {
        if (ch >= 'A' && ch <= 'Z') return (char) (ch + 32);
        else if (ch >= 'a' && ch <= 'z') return (char) (ch - 32);
        else return ch;
    }

    public static String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                sb.append((char) (((ch - 'A' + shift + 26) % 26) + 'A'));
            } else if (Character.isLowerCase(ch)) {
                sb.append((char) (((ch - 'a' + shift + 26) % 26) + 'a'));
            } else {
                sb.append(ch); // leave digits/specials unchanged
            }
        }
        return sb.toString();
    }

    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }

    public static int[] stringToASCII(String text) {
        int[] arr = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            arr[i] = (int) text.charAt(i);
        }
        return arr;
    }

    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
