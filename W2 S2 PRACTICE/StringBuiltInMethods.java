public class StringBuiltInMethods {
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Original length
        int originalLength = sampleText.length();

        // 2. Trimmed version
        String trimmed = sampleText.trim();
        int trimmedLength = trimmed.length();

        // 3. Character at index 5
        char charAt5 = sampleText.charAt(5);

        // 4. Substring "Programming"
        String subProgramming = sampleText.substring(6, 17);

        // 5. Index of "Fun"
        int indexFun = sampleText.indexOf("Fun");

        // 6. Contains "Java"
        boolean containsJava = sampleText.contains("Java");

        // 7. Starts with "Java" (after trimming)
        boolean startsWithJava = trimmed.startsWith("Java");

        // 8. Ends with "!"
        boolean endsWithExclamation = trimmed.endsWith("!");

        // 9. Uppercase
        String upper = sampleText.toUpperCase();

        // 10. Lowercase
        String lower = sampleText.toLowerCase();

        // Custom method: vowel count
        int vowelCount = countVowels(sampleText);

        // Custom method: occurrences of a char
        System.out.println("=== String Analysis Results ===");
        System.out.println("Original String: \"" + sampleText + "\"");
        System.out.println("1. Original Length (with spaces): " + originalLength);
        System.out.println("2. Trimmed Length: " + trimmedLength);
        System.out.println("3. Character at Index 5: " + charAt5);
        System.out.println("4. Substring 'Programming': " + subProgramming);
        System.out.println("5. Index of 'Fun': " + indexFun);
        System.out.println("6. Contains 'Java': " + containsJava);
        System.out.println("7. Starts with 'Java' (after trim): " + startsWithJava);
        System.out.println("8. Ends with '!': " + endsWithExclamation);
        System.out.println("9. Uppercase: " + upper);
        System.out.println("10. Lowercase: " + lower);
        System.out.println("11. Vowel Count: " + vowelCount);
        System.out.print("12. Occurrences of 'a': ");
        findAllOccurrences(sampleText, 'a');
    }

    public static int countVowels(String text) {
        int count = 0;
        String vowels = "AEIOUaeiou";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    public static void findAllOccurrences(String text, char target) {
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
                found = true;
            }
        }
        if (!found) {
            System.out.print("No occurrences found");
        }
        System.out.println();
    }
}
