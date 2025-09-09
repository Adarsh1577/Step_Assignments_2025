import java.util.*;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to format:");
        String text = sc.nextLine();
        System.out.println("Enter line width:");
        int width = sc.nextInt();

        String[] words = splitWords(text);

        long start1 = System.nanoTime();
        List<String> justified = justifyText(words, width);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<String> justifiedConcat = justifyTextConcat(words, width);
        long end2 = System.nanoTime();

        List<String> centered = centerAlign(words, width);

        System.out.println("\nOriginal Text:");
        System.out.println(text);

        System.out.println("\nLeft-Justified Text:");
        displayLines(justified);

        System.out.println("\nCenter-Aligned Text:");
        displayLines(centered);

        System.out.println("\nPerformance Comparison:");
        System.out.println("StringBuilder Justify Time: " + (end1 - start1) + " ns");
        System.out.println("String Concatenation Time : " + (end2 - start2) + " ns");
    }

    static String[] splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words.toArray(new String[0]);
    }

    static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            StringBuilder sb = new StringBuilder();
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                while (sb.length() < width) sb.append(" ");
            } else {
                int spaces = (width - (lineLen - gaps)) / gaps;
                int extra = (width - (lineLen - gaps)) % gaps;
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spaces; s++) sb.append(" ");
                        if (extra-- > 0) sb.append(" ");
                    }
                }
            }
            lines.add(sb.toString());
            i = j;
        }
        return lines;
    }

    static List<String> justifyTextConcat(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }

            int gaps = j - i - 1;
            String line = "";
            if (j == words.length || gaps == 0) {
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k < j - 1) line += " ";
                }
                while (line.length() < width) line += " ";
            } else {
                int spaces = (width - (lineLen - gaps)) / gaps;
                int extra = (width - (lineLen - gaps)) % gaps;
                for (int k = i; k < j; k++) {
                    line += words[k];
                    if (k < j - 1) {
                        for (int s = 0; s < spaces; s++) line += " ";
                        if (extra-- > 0) line += " ";
                    }
                }
            }
            lines.add(line);
            i = j;
        }
        return lines;
    }

    static List<String> centerAlign(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int lineLen = words[i].length();
            int j = i + 1;
            while (j < words.length && lineLen + 1 + words[j].length() <= width) {
                lineLen += 1 + words[j].length();
                j++;
            }

            StringBuilder sb = new StringBuilder();
            for (int k = i; k < j; k++) {
                sb.append(words[k]);
                if (k < j - 1) sb.append(" ");
            }
            int totalSpaces = width - sb.length();
            int leftPad = totalSpaces / 2;
            int rightPad = totalSpaces - leftPad;
            StringBuilder centered = new StringBuilder();
            for (int s = 0; s < leftPad; s++) centered.append(" ");
            centered.append(sb);
            for (int s = 0; s < rightPad; s++) centered.append(" ");
            lines.add(centered.toString());
            i = j;
        }
        return lines;
    }

    static void displayLines(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            System.out.printf("Line %d (%d chars): %s\n", i + 1, lines.get(i).length(), lines.get(i));
        }
    }
}
