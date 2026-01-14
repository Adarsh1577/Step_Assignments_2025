// 📚 Library Book Management
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // 1. Default constructor → empty book
    public Book() {
        this("Unknown", "Unknown", "0000000000", true);
    }

    // 2. Constructor with title and author
    public Book(String title, String author) {
        this(title, author, "0000000000", true);
    }

    // 3. Constructor with all details
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // Borrow book
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("✅ \"" + title + "\" borrowed successfully.");
        } else {
            System.out.println("❌ \"" + title + "\" is already borrowed.");
        }
    }

    // Return book
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("✅ \"" + title + "\" returned successfully.");
        } else {
            System.out.println("❌ \"" + title + "\" was not borrowed.");
        }
    }

    // Display book info
    public void displayBookInfo() {
        System.out.println("\n--- Book Info ---");
        System.out.println("Title       : " + title);
        System.out.println("Author      : " + author);
        System.out.println("ISBN        : " + isbn);
        System.out.println("Availability: " + (isAvailable ? "Available ✅" : "Borrowed ❌"));
    }
}

// === MAIN CLASS ===
public class LibraryManagement {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY BOOK MANAGEMENT ===");

        // Create books with different constructors
        Book b1 = new Book();
        Book b2 = new Book("The Alchemist", "Paulo Coelho");
        Book b3 = new Book("Clean Code", "Robert C. Martin", "9780132350884", true);

        // Borrow and return actions
        b2.borrowBook();
        b2.borrowBook(); // already borrowed
        b2.returnBook();

        b3.borrowBook();

        // Display info
        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();
    }
}
