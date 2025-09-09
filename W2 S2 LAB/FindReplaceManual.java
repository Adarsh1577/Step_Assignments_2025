import java.util.*;

public class FindReplaceManual {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter main text:");
        String text = sc.nextLine();
        System.out.println("Enter substring to find:");
        String find = sc.nextLine();
        System.out.println("Enter substring to replace:");
        String replace = sc.nextLine();

        int[] positions = findOccurrences(text, find);
        String manualResult = manualReplace(text, find, replace, positions);
        String builtInResult = text.replace(find, replace);

        System.out.println("Manual Result: " + manualResult);
        System.out.println("Built-in Result: " + builtInResult);
        System.out.println("Both same? " + compareResults(manualResult, builtInResult));
    }

    static int[] findOccurrences(String text, String find) {
        List<Integer> pos = new ArrayList<>();
        int index = text.indexOf(find);
        while (index != -1) {
            pos.add(index);
            index = text.indexOf(find, index + find.length());
        }
        int[] result = new int[pos.size()];
        for (int i = 0; i < pos.size(); i++) {
            result[i] = pos.get(i);
        }
        return result;
    }

    static String manualReplace(String text, String find, String replace, int[] positions) {
        StringBuilder sb = new StringBuilder();
        int i = 0, posIndex = 0;
        while (i < text.length()) {
            if (posIndex < positions.length && i == positions[posIndex]) {
                sb.append(replace);
                i += find.length();
                posIndex++;
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

    static boolean compareResults(String a, String b) {
        return a.equals(b);
    }
}
