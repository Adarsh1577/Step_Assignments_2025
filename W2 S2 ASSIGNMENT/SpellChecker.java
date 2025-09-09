import java.util.*;

public class SpellChecker {
    static String[] splitSentence(String sentence) {
        ArrayList<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (c == ' ' || c == '.' || c == ',' || c == '!' || c == '?') {
                if (start < i) {
                    words.add(sentence.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < sentence.length()) {
            words.add(sentence.substring(start));
        }
        return words.toArray(new String[0]);
    }

    static int stringDistance(String w1, String w2) {
        if (w1.length() == w2.length()) {
            int diff = 0;
            for (int i = 0; i < w1.length(); i++) {
                if (w1.charAt(i) != w2.charAt(i)) diff++;
            }
            return diff;
        } else {
            return Math.abs(w1.length() - w2.length());
        }
    }

    static String findClosest(String word, String[] dict) {
        String closest = word;
        int minDist = Integer.MAX_VALUE;
        for (String d : dict) {
            int dist = stringDistance(word, d);
            if (dist < minDist) {
                minDist = dist;
                closest = d;
            }
        }
        if (minDist <= 2 && !closest.equals(word)) return closest;
        return word;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] dictionary = {"hello","world","java","spell","checker","program"};
        System.out.println("Enter a sentence:");
        String sentence = sc.nextLine();
        String[] words = splitSentence(sentence);
        System.out.printf("%-15s %-15s %-10s %-15s\n","Word","Suggestion","Distance","Status");
        for (String w : words) {
            String suggestion = findClosest(w, dictionary);
            int dist = suggestion.equals(w) ? 0 : stringDistance(w,suggestion);
            String status = suggestion.equals(w) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-15s\n",w,suggestion,dist,status);
        }
    }
}
