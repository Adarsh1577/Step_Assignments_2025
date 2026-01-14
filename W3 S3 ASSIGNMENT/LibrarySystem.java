import java.util.*;

class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean isIssued;
    private String issueDate;
    private String dueDate;

    // Static variables
    private static int totalBooks = 0;
    private static Map<String, Integer> popularityMap = new HashMap<>();

    public Book(String title, String author, String isbn, String category) {
        this.bookId = "B" + (++totalBooks);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public boolean isIssued() { return isIssued; }
    public String getDueDate() { return dueDate; }
    public String getIssueDate() { return issueDate; }

    public void issueBook(String issueDate, String dueDate) {
        this.isIssued = true;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        popularityMap.put(title, popularityMap.getOrDefault(title, 0) + 1);
    }

    public void returnBook() {
        this.isIssued = false;
        this.issueDate = "";
        this.dueDate = "";
    }

    public void renewBook(String newDueDate) {
        this.dueDate = newDueDate;
    }

    public void displayInfo() {
        System.out.println("BookID: " + bookId + " | " + title + " by " + author + " | Category: " + category +
                " | Status: " + (isIssued ? "Issued" : "Available"));
    }

    // Static reporting
    public static String getMostPopularBooks() {
        if (popularityMap.isEmpty()) return "No books issued yet.";
        List<Map.Entry<String, Integer>> list = new ArrayList<>(popularityMap.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());
        return "Most Popular Book: " + list.get(0).getKey() + " (Issued " + list.get(0).getValue() + " times)";
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String memberType; // Student, Faculty, General
    private Book[] booksIssued;
    private int issuedCount;
    private double totalFines;
    private String membershipDate;

    // Static variables
    private static int totalMembers = 0;
    private static String libraryName = "Default Library";
    private static double finePerDay = 2.0;
    private static int maxBooksAllowed = 3;

    public Member(String memberName, String memberType, String membershipDate) {
        this.memberId = "M" + (++totalMembers);
        this.memberName = memberName;
        this.memberType = memberType;
        this.membershipDate = membershipDate;
        this.booksIssued = new Book[maxBooksAllowed];
        this.issuedCount = 0;
        this.totalFines = 0.0;
    }

    public String getMemberName() { return memberName; }
    public double getTotalFines() { return totalFines; }

    // Issue book
    public void issueBook(Book book, String issueDate, String dueDate) {
        if (issuedCount >= maxBooksAllowed) {
            System.out.println("❌ Max book limit reached for " + memberName);
            return;
        }
        if (book.isIssued()) {
            System.out.println("❌ Book already issued: " + book.getTitle());
            return;
        }
        book.issueBook(issueDate, dueDate);
        booksIssued[issuedCount++] = book;
        System.out.println("✅ " + memberName + " issued book: " + book.getTitle());
    }

    // Return book
    public void returnBook(String bookId, String returnDate) {
        for (int i = 0; i < issuedCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                Book book = booksIssued[i];
                double fine = calculateFine(book.getDueDate(), returnDate);
                totalFines += fine;
                book.returnBook();
                System.out.println("✅ " + memberName + " returned " + book.getTitle() + ". Fine: ₹" + fine);
                booksIssued[i] = null;
                return;
            }
        }
        System.out.println("❌ Book not found in issued list for " + memberName);
    }

    // Fine calculation
    private double calculateFine(String dueDate, String returnDate) {
        // For simplicity, use numeric dates like "20250901"
        int due = Integer.parseInt(dueDate);
        int ret = Integer.parseInt(returnDate);
        if (ret <= due) return 0;
        int overdueDays = ret - due;
        return overdueDays * finePerDay;
    }

    // Renew book
    public void renewBook(String bookId, String newDueDate) {
        for (int i = 0; i < issuedCount; i++) {
            if (booksIssued[i] != null && booksIssued[i].getBookId().equals(bookId)) {
                booksIssued[i].renewBook(newDueDate);
                System.out.println("🔄 Book renewed: " + booksIssued[i].getTitle() + " till " + newDueDate);
                return;
            }
        }
        System.out.println("❌ Book not found to renew.");
    }

    // Display issued books
    public void displayIssuedBooks() {
        System.out.println("\n📚 Books issued to " + memberName + ":");
        for (Book b : booksIssued) {
            if (b != null) b.displayInfo();
        }
    }

    // Static reports
    public static void setLibraryName(String name) { libraryName = name; }
    public static void setFinePerDay(double fine) { finePerDay = fine; }
    public static void setMaxBooksAllowed(int max) { maxBooksAllowed = max; }

    public static void generateLibraryReport(Member[] members) {
        System.out.println("\n===== " + libraryName + " Report =====");
        System.out.println("Total Members: " + totalMembers);
        double totalFinesCollected = 0;
        for (Member m : members) {
            totalFinesCollected += m.getTotalFines();
        }
        System.out.println("Total Fines Collected: ₹" + totalFinesCollected);
        System.out.println(Book.getMostPopularBooks());
        System.out.println("=====================================");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        // Set library details
        Member.setLibraryName("SRM University Library");
        Member.setFinePerDay(5.0); // ₹5/day fine
        Member.setMaxBooksAllowed(2);

        // Create Books
        Book b1 = new Book("Java Programming", "Herbert Schildt", "ISBN123", "Programming");
        Book b2 = new Book("C++ Primer", "Stanley Lippman", "ISBN456", "Programming");
        Book b3 = new Book("AI & ML Basics", "Andrew Ng", "ISBN789", "AI");

        // Create Members
        Member m1 = new Member("Arjun", "Student", "20240101");
        Member m2 = new Member("Priya", "Faculty", "20240110");

        // Transactions
        m1.issueBook(b1, "20250901", "20250905");
        m1.issueBook(b2, "20250901", "20250906");
        m2.issueBook(b3, "20250901", "20250910");

        m1.displayIssuedBooks();

        // Return with fine
        m1.returnBook("B1", "20250908"); // Late return

        // Renew
        m2.renewBook("B3", "20250920");

        // Generate report
        Member[] members = {m1, m2};
        Member.generateLibraryReport(members);
    }
}
