import java.util.*;

public class CaseConversionASCII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = sc.nextLine();

        String upper = toUpper(text);
        String lower = toLower(text);
        String title = toTitle(text);

        String builtInUpper = text.toUpperCase();
        String builtInLower = text.toLowerCase();

        System.out.println("------------------------------------------------");
        System.out.printf("%-20s %-30s\n", "Conversion", "Result");
        System.out.println("------------------------------------------------");
        System.out.printf("%-20s %-30s\n", "Manual Uppercase", upper);
        System.out.printf("%-20s %-30s\n", "Built-in Uppercase", builtInUpper);
        System.out.printf("%-20s %-30s\n", "Manual Lowercase", lower);
        System.out.printf("%-20s %-30s\n", "Built-in Lowercase", builtInLower);
        System.out.printf("%-20s %-30s\n", "Manual Title Case", title);
        System.out.println("------------------------------------------------");
    }

    static String toUpper(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'a' && c <= 'z') {
                sb.append((char)(c - 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String toLower(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                sb.append((char)(c + 32));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    static String toTitle(String text) {
        StringBuilder sb = new StringBuilder();
        boolean newWord = true;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                sb.append(c);
                newWord = true;
            } else {
                if (newWord) {
                    if (c >= 'a' && c <= 'z') {
                        sb.append((char)(c - 32));
                    } else {
                        sb.append(c);
                    }
                    newWord = false;
                } else {
                    if (c >= 'A' && c <= 'Z') {
                        sb.append((char)(c + 32));
                    } else {
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
