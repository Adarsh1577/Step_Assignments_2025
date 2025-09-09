import java.util.*;

public class EmailAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> emails = new ArrayList<>();
        System.out.println("Enter number of emails:");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            System.out.println("Enter email " + (i + 1) + ":");
            emails.add(sc.nextLine().trim());
        }

        List<EmailInfo> results = new ArrayList<>();
        for (String email : emails) {
            results.add(processEmail(email));
        }

        displayTable(results);
        analyzeStatistics(results);
    }

    static class EmailInfo {
        String email;
        String username;
        String domain;
        String domainName;
        String extension;
        boolean valid;
        EmailInfo(String e, String u, String d, String dn, String ex, boolean v) {
            email = e;
            username = u;
            domain = d;
            domainName = dn;
            extension = ex;
            valid = v;
        }
    }

    static boolean validateEmail(String email) {
        int at = email.indexOf('@');
        int lastAt = email.lastIndexOf('@');
        if (at == -1 || at != lastAt) return false;
        int dot = email.indexOf('.', at);
        if (dot == -1) return false;
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        if (username.isEmpty() || domain.isEmpty()) return false;
        return true;
    }

    static EmailInfo processEmail(String email) {
        if (!validateEmail(email)) {
            return new EmailInfo(email, "-", "-", "-", "-", false);
        }
        int at = email.indexOf('@');
        String username = email.substring(0, at);
        String domain = email.substring(at + 1);
        int dot = domain.lastIndexOf('.');
        String domainName = (dot != -1) ? domain.substring(0, dot) : domain;
        String extension = (dot != -1) ? domain.substring(dot + 1) : "-";
        return new EmailInfo(email, username, domain, domainName, extension, true);
    }

    static void displayTable(List<EmailInfo> results) {
        System.out.println("---------------------------------------------------------------------------------");
        System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                "Email", "Username", "Domain", "Domain Name", "Extension", "Valid?");
        System.out.println("---------------------------------------------------------------------------------");
        for (EmailInfo info : results) {
            System.out.printf("%-25s %-15s %-20s %-15s %-10s %-10s\n",
                    info.email, info.username, info.domain, info.domainName, info.extension,
                    (info.valid ? "Yes" : "No"));
        }
        System.out.println("---------------------------------------------------------------------------------");
    }

    static void analyzeStatistics(List<EmailInfo> results) {
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;
        Map<String, Integer> domainCount = new HashMap<>();

        for (EmailInfo info : results) {
            if (info.valid) {
                validCount++;
                totalUsernameLength += info.username.length();
                domainCount.put(info.domain, domainCount.getOrDefault(info.domain, 0) + 1);
            } else {
                invalidCount++;
            }
        }

        String mostCommonDomain = "-";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : domainCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostCommonDomain = entry.getKey();
            }
        }

        double avgUsernameLength = (validCount > 0) ? (double) totalUsernameLength / validCount : 0;

        System.out.println("\nEmail Statistics:");
        System.out.println("Valid Emails   : " + validCount);
        System.out.println("Invalid Emails : " + invalidCount);
        System.out.println("Most Common Domain : " + mostCommonDomain);
        System.out.printf("Average Username Length : %.2f\n", avgUsernameLength);
    }
}
