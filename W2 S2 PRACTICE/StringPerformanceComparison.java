public class StringPerformanceComparison {
    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test String concatenation (slow)
        long startTime = System.nanoTime();
        String result1 = concatenateWithString(1000);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        // Test StringBuilder (fast, not thread-safe)
        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        // Test StringBuffer (fast, thread-safe)
        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(1000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        // Demonstrate StringBuilder methods
        System.out.println("\n=== STRINGBUILDER METHODS ===");
        demonstrateStringBuilderMethods();

        // Demonstrate thread safety
        System.out.println("\n=== THREAD SAFETY TEST ===");
        demonstrateThreadSafety();

        // String comparison methods
        System.out.println("\n=== STRING COMPARISON ===");
        compareStringComparisonMethods();

        // Memory efficiency
        System.out.println("\n=== MEMORY EFFICIENCY ===");
        demonstrateMemoryEfficiency();
    }

    // Inefficient String concatenation
    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java " + i + " ";
        }
        return result;
    }

    // Efficient StringBuilder
    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Efficient & Thread-safe StringBuffer
    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java ").append(i).append(" ");
        }
        return sb.toString();
    }

    // Demonstrating StringBuilder methods
    public static void demonstrateStringBuilderMethods() {
        StringBuilder sb = new StringBuilder("Hello World");
        System.out.println("Original: " + sb);

        sb.append(" Java");
        System.out.println("append(): " + sb);

        sb.insert(6, "Beautiful ");
        System.out.println("insert(): " + sb);

        sb.delete(6, 16);
        System.out.println("delete(): " + sb);

        sb.deleteCharAt(5);
        System.out.println("deleteCharAt(): " + sb);

        sb.reverse();
        System.out.println("reverse(): " + sb);
        sb.reverse(); // restore

        sb.replace(6, 11, "Universe");
        System.out.println("replace(): " + sb);

        sb.setCharAt(0, 'h');
        System.out.println("setCharAt(): " + sb);

        System.out.println("capacity(): " + sb.capacity());
        sb.ensureCapacity(50);
        System.out.println("After ensureCapacity(50): " + sb.capacity());
        sb.trimToSize();
        System.out.println("After trimToSize(): " + sb.capacity());
    }

    // Demonstrating thread safety
    public static void demonstrateThreadSafety() {
        StringBuffer sb = new StringBuffer("Start ");
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                sb.append(Thread.currentThread().getName()).append(" ");
            }
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final StringBuffer: " + sb);
    }

    // String comparison
    public static void compareStringComparisonMethods() {
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("== operator (str1 == str2): " + (str1 == str2));
        System.out.println("== operator (str1 == str3): " + (str1 == str3));
        System.out.println("equals(): " + str1.equals(str3));
        System.out.println("equalsIgnoreCase(): " + str1.equalsIgnoreCase("hello"));
        System.out.println("compareTo(): " + str1.compareTo("World"));
        System.out.println("compareToIgnoreCase(): " + str1.compareToIgnoreCase("hello"));
    }

    // Memory efficiency
    public static void demonstrateMemoryEfficiency() {
        String s1 = "Java"; // stored in String pool
        String s2 = "Java"; // refers to same object
        String s3 = new String("Java"); // new object in heap

        System.out.println("s1 == s2: " + (s1 == s2)); // true
        System.out.println("s1 == s3: " + (s1 == s3)); // false
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true

        // Show capacity behavior
        StringBuilder sb = new StringBuilder();
        System.out.println("Initial capacity: " + sb.capacity());
        sb.append("This is a test string for capacity demonstration.");
        System.out.println("Capacity after append: " + sb.capacity());
    }
}
