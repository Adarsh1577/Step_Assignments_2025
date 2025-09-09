import java.util.*;

public class TextCompression {
    static char[] getUniqueChars(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (sb.indexOf(String.valueOf(c)) == -1) sb.append(c);
        }
        return sb.toString().toCharArray();
    }

    static int[] getFrequencies(String text, char[] chars) {
        int[] freq = new int[chars.length];
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] == c) {
                    freq[j]++;
                    break;
                }
            }
        }
        return freq;
    }

    static String[][] generateCodes(char[] chars, int[] freq) {
        String[][] mapping = new String[chars.length][2];
        Character[] indices = new Character[chars.length];
        for (int i = 0; i < chars.length; i++) indices[i] = (char)(i);
        for (int i = 0; i < chars.length; i++) {
            for (int j = i+1; j < chars.length; j++) {
                if (freq[j] > freq[i]) {
                    int tmpF = freq[i]; freq[i] = freq[j]; freq[j] = tmpF;
                    char tmpC = chars[i]; chars[i] = chars[j]; chars[j] = tmpC;
                }
            }
        }
        for (int i = 0; i < chars.length; i++) {
            mapping[i][0] = String.valueOf(chars[i]);
            mapping[i][1] = Integer.toString(i, 36); 
        }
        return mapping;
    }

    static String compressText(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String ch = String.valueOf(text.charAt(i));
            for (String[] map : mapping) {
                if (map[0].equals(ch)) {
                    sb.append(map[1]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    static String decompressText(String compressed, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < compressed.length(); i++) {
            String ch = String.valueOf(compressed.charAt(i));
            for (String[] map : mapping) {
                if (map[1].equals(ch)) {
                    sb.append(map[0]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    static void displayAnalysis(String text, char[] chars, int[] freq, String[][] mapping, String compressed, String decompressed) {
        System.out.println("Character Frequency Table:");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " : " + freq[i]);
        }
        System.out.println("\nCompression Mapping:");
        for (String[] map : mapping) {
            System.out.println(map[0] + " -> " + map[1]);
        }
        System.out.println("\nOriginal Text: " + text);
        System.out.println("Compressed Text: " + compressed);
        System.out.println("Decompressed Text: " + decompressed);
        double ratio = ((double)compressed.length() / text.length()) * 100;
        System.out.println("Compression Efficiency: " + String.format("%.2f", 100 - ratio) + "%");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to compress:");
        String text = sc.nextLine();
        char[] chars = getUniqueChars(text);
        int[] freq = getFrequencies(text, chars);
        String[][] mapping = generateCodes(chars, freq);
        String compressed = compressText(text, mapping);
        String decompressed = decompressText(compressed, mapping);
        displayAnalysis(text, chars, freq, mapping, compressed, decompressed);
    }
}
