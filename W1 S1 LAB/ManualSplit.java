import java.util.*;

public class ManualSplit {

    // Method to find string length without using length()
    public static int findLength(String str) {
        int count = 0;
        try {
            while (true) {
                str.charAt(count);
                count++;
            }
        } catch (IndexOutOfBoundsException e) {
            return count;
        }
    }

    // Method to split string into words without using split()
    public static String[] manualSplit(String text) {
        int len = findLength(text);
        
        // Count words
        int wordCount = 1; 
        for (int i = 0; i < len; i++) {
            if (text.charAt(i) == ' ') {
                wordCount++;
            }
        }

        // Store indexes of spaces
        int[] spaceIndexes = new int[wordCount - 1];
        int idx = 0;
        for (int i = 0; i < len; i++) {
            if (text.charAt(i) == ' ') {
                spaceIndexes[idx++] = i;
            }
        }

        // Extract words
        String[] words = new String[wordCount];
        int start = 0;
        for (int i = 0; i < wordCount - 1; i++) {
            words[i] = text.substring(start, spaceIndexes[i]);
            start = spaceIndexes[i] + 1;
        }
        words[wordCount - 1] = text.substring(start, len);

        return words;
    }

    // Method to compare two string arrays
    public static boolean compareArrays(String[] arr1, String[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String text = sc.nextLine();

        // Using manual split
        String[] manualWords = manualSplit(text);

        // Using built-in split
        String[] builtInWords = text.split(" ");

        // Display results
        System.out.println("\nManual Split:");
        for (String w : manualWords) {
            System.out.println(w);
        }

        System.out.println("\nBuilt-in Split:");
        for (String w : builtInWords) {
            System.out.println(w);
        }

        // Compare results
        boolean same = compareArrays(manualWords, builtInWords);
        System.out.println("\nDo both methods give the same result? " + same);

        sc.close();
    }
}
