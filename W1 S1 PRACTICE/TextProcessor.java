import java.util.*;

public class TextProcessor {

    public static String cleanInput(String input) {
        input = input.trim().replaceAll("\\s+", " ");
        String[] words = input.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                  .append(w.substring(1))
                  .append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void analyzeText(String text) {
        String[] words = text.split("\\s+");
        int wordCount = words.length;
        int sentenceCount = text.split("[.!?]").length;
        int charCount = text.replace(" ", "").length();

        String longestWord = "";
        for (String w : words) {
            String clean = w.replaceAll("[^a-zA-Z]", "");
            if (clean.length() > longestWord.length()) {
                longestWord = clean;
            }
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }
        char mostCommonChar = ' ';
        int maxFreq = 0;
        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if (e.getValue() > maxFreq) {
                maxFreq = e.getValue();
                mostCommonChar = e.getKey();
            }
        }

        System.out.println("\n--- TEXT ANALYSIS ---");
        System.out.println("Word Count: " + wordCount);
        System.out.println("Sentence Count: " + sentenceCount);
        System.out.println("Character Count (no spaces): " + charCount);
        System.out.println("Longest Word: " + longestWord);
        System.out.println("Most Common Character: '" + mostCommonChar + "' (" + maxFreq + " times)");
    }

    public static String[] getWordsSorted(String text) {
        String clean = text.replaceAll("[^a-zA-Z ]", "");
        String[] words = clean.split("\\s+");
        Arrays.sort(words, String.CASE_INSENSITIVE_ORDER);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== TEXT PROCESSOR ===");
        System.out.print("Enter a paragraph of text: ");
        String input = scanner.nextLine();

        String cleaned = cleanInput(input);
        System.out.println("\nCleaned Text: " + cleaned);

        analyzeText(cleaned);

        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("\n--- SORTED WORDS ---");
        for (String w : sortedWords) {
            System.out.println(w);
        }

        System.out.print("\nEnter a word to search: ");
        String search = scanner.nextLine().trim();
        boolean found = Arrays.stream(sortedWords).anyMatch(w -> w.equalsIgnoreCase(search));

        if (found) {
            System.out.println("The word \"" + search + "\" is found in the text.");
        } else {
            System.out.println("The word \"" + search + "\" is NOT found in the text.");
        }

        scanner.close();
    }
}
