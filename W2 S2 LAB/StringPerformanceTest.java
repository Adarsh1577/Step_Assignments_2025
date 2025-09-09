import java.util.*;

public class StringPerformanceTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of iterations:");
        int n = sc.nextInt();

        Result stringRes = testStringConcat(n);
        Result builderRes = testStringBuilder(n);
        Result bufferRes = testStringBuffer(n);

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-15s %-20s %-20s\n", "Method", "Time (ms)", "Final Length");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-15s %-20d %-20d\n", "String", stringRes.time, stringRes.length);
        System.out.printf("%-15s %-20d %-20d\n", "StringBuilder", builderRes.time, builderRes.length);
        System.out.printf("%-15s %-20d %-20d\n", "StringBuffer", bufferRes.time, bufferRes.length);
        System.out.println("-------------------------------------------------------------");
    }

    static class Result {
        long time;
        int length;
        Result(long t, int l) {
            time = t;
            length = l;
        }
    }

    static Result testStringConcat(int n) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < n; i++) {
            s = s + "x";
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, s.length());
    }

    static Result testStringBuilder(int n) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("x");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }

    static Result testStringBuffer(int n) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("x");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }
}
