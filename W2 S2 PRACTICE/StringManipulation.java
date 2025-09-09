import java.util.*;

public class StringManipulation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();

        // 1. trim()
        String trimmed = input.trim();

        // 2. replace()
        String replacedSpaces = trimmed.replace(" ", "_");

        // 3. replaceAll() - remove digits
        String noDigits = trimmed.replaceAll("\\d", "");

        // 4. split()
        String[] words = trimmed.split("\\s+");

        // 5. join()
        String joined = String.join(" | ", words);

        System.out.println("\n=== Built-in Methods ===");
        System.out.println("Original: " + input);
        System.out.println("Trimmed: " + trimmed);
        System.out.println("Spaces Replaced: " + replacedSpaces);
        System.out.println("No Digits: " + noDigits);
        System.out.println("Split Words: " + Arrays.toString(words));
        System.out.println("Joined with | : " + joined);

        System.out.println("\n=== Custom Methods ===");
        System.out.println("Without Punctuation: " + removePunctuation(trimmed));
        System.out.println("Capitalized Words: " + capitalizeWords(trimmed));
        System.out.println("Reversed Order: " + reverseWordOrder(trimmed));

        System.out.println("\nWord Frequency:");
        countWordFrequency(trimmed);

        scanner.close();
    }

    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", "");
    }

    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1).toLowerCase())
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        Map<String, Integer> freqMap = new LinkedHashMap<>();

        for (String w : words) {
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
