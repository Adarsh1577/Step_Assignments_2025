import java.util.*;

public class CaesarCipherASCII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to encrypt:");
        String text = sc.nextLine();
        System.out.println("Enter shift value:");
        int shift = sc.nextInt();

        String encrypted = encrypt(text, shift);
        String decrypted = decrypt(encrypted, shift);

        System.out.println("\nOriginal Text with ASCII:");
        displayAscii(text);
        System.out.println("\nEncrypted Text with ASCII:");
        displayAscii(encrypted);
        System.out.println("\nDecrypted Text with ASCII:");
        displayAscii(decrypted);

        System.out.println("\nValidation: " + (text.equals(decrypted) ? "Successful ✅" : "Failed ❌"));
    }

    static String encrypt(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                char e = (char) ((c - 'A' + shift) % 26 + 'A');
                sb.append(e);
            } else if (c >= 'a' && c <= 'z') {
                char e = (char) ((c - 'a' + shift) % 26 + 'a');
                sb.append(e);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    static void displayAscii(String text) {
        for (int i = 0; i < text.length(); i++) {
            System.out.println("'" + text.charAt(i) + "' -> " + (int) text.charAt(i));
        }
    }
}
