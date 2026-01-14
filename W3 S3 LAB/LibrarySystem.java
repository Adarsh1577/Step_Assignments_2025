class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookCounter = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    private static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public boolean issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId + 
                           ", Title: " + title + 
                           ", Author: " + author + 
                           ", Available: " + isAvailable);
    }

    public String getBookId() {
        return bookId;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int memberCounter = 0;

    public Member(String memberName, int maxBooks) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[maxBooks];
        this.bookCount = 0;
    }

    private static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }

    public void borrowBook(Book book) {
        if (book.getAvailability() && bookCount < booksIssued.length) {
            if (book.issueBook()) {
                booksIssued[bookCount++] = book.getBookId();
                System.out.println(memberName + " borrowed book: " + book.getBookId());
            }
        } else {
            System.out.println(memberName + " cannot borrow book: " + book.getBookId());
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (Book b : books) {
                    if (b.getBookId().equals(bookId)) {
                        b.returnBook();
                        System.out.println(memberName + " returned book: " + bookId);
                        booksIssued[i] = booksIssued[bookCount - 1];
                        booksIssued[bookCount - 1] = null;
                        bookCount--;
                        return;
                    }
                }
            }
        }
        System.out.println(memberName + " does not have book: " + bookId);
    }

    public void displayMemberInfo() {
        System.out.print("Member ID: " + memberId + ", Name: " + memberName + ", Books Issued: ");
        for (int i = 0; i < bookCount; i++) {
            System.out.print(booksIssued[i] + " ");
        }
        System.out.println();
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("The Alchemist", "Paulo Coelho");
        books[1] = new Book("1984", "George Orwell");
        books[2] = new Book("Clean Code", "Robert C. Martin");

        Member[] members = new Member[2];
        members[0] = new Member("Alice", 2);
        members[1] = new Member("Bob", 1);

        System.out.println("\n--- Initial Book Info ---");
        for (Book b : books) {
            b.displayBookInfo();
        }

        members[0].borrowBook(books[0]);
        members[0].borrowBook(books[1]);
        members[1].borrowBook(books[1]); 
        members[1].borrowBook(books[2]); 

        System.out.println("\n--- After Borrowing ---");
        for (Book b : books) {
            b.displayBookInfo();
        }
        for (Member m : members) {
            m.displayMemberInfo();
        }

        members[0].returnBook("B001", books);

        System.out.println("\n--- After Returning ---");
        for (Book b : books) {
            b.displayBookInfo();
        }
        for (Member m : members) {
            m.displayMemberInfo();
        }

        System.out.println("\nTotal Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}
