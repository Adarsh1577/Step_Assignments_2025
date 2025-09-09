import java.util.*;

public class PasswordAnalyzer {
    static class Result {
        String password;
        int length, upper, lower, digits, special, score;
        String strength;
        Result(String p, int l, int u, int lo, int d, int s, int sc, String st) {
            password = p; length = l; upper = u; lower = lo; digits = d; special = s; score = sc; strength = st;
        }
    }

    static Result analyzePassword(String password) {
        int upper = 0, lower = 0, digits = 0, special = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int ascii = (int)c;
            if (ascii >= 65 && ascii <= 90) upper++;
            else if (ascii >= 97 && ascii <= 122) lower++;
            else if (ascii >= 48 && ascii <= 57) digits++;
            else if (ascii >= 33 && ascii <= 126) special++;
        }
        int score = 0;
        if (password.length() > 8) score += (password.length() - 8) * 2;
        if (upper > 0) score += 10;
        if (lower > 0) score += 10;
        if (digits > 0) score += 10;
        if (special > 0) score += 10;
        String[] patterns = {"123","abc","qwerty"};
        for (String p : patterns) {
            if (password.toLowerCase().contains(p)) score -= 10;
        }
        String strength;
        if (score <= 20) strength = "Weak";
        else if (score <= 50) strength = "Medium";
        else strength = "Strong";
        return new Result(password, password.length(), upper, lower, digits, special, score, strength);
    }

    static String generatePassword(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{};:,.<>?/|";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));
        String all = upper + lower + digits + special;
        for (int i = 4; i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }
        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);
        StringBuilder finalPw = new StringBuilder();
        for (char c : chars) finalPw.append(c);
        return finalPw.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of passwords to analyze:");
        int n = sc.nextInt(); sc.nextLine();
        ArrayList<Result> results = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter password " + (i+1) + ":");
            String pw = sc.nextLine();
            results.add(analyzePassword(pw));
        }
        System.out.printf("%-15s %-7s %-7s %-9s %-7s %-12s %-7s %-10s\n",
                "Password","Length","Upper","Lower","Digits","SpecialChars","Score","Strength");
        for (Result r : results) {
            System.out.printf("%-15s %-7d %-7d %-9d %-7d %-12d %-7d %-10s\n",
                    r.password, r.length, r.upper, r.lower, r.digits, r.special, r.score, r.strength);
        }
        System.out.println("Enter length for new strong password:");
        int len = sc.nextInt();
        String newPw = generatePassword(len);
        System.out.println("Generated Strong Password: " + newPw);
    }
}
