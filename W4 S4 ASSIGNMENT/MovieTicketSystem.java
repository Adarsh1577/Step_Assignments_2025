// 🎬 Movie Ticket Booking System
class MovieTicket {
    private String movieName;
    private String theatreName;
    private int seatNumber;
    private double price;

    // 1. Default constructor → assigns "Unknown" movie
    public MovieTicket() {
        this("Unknown", "Not Assigned", 0, 0.0);
    }

    // 2. Constructor with movie name → assigns default price = 200
    public MovieTicket(String movieName) {
        this(movieName, "Not Assigned", 0, 200.0);
    }

    // 3. Constructor with movie name and seat number → assigns default theatre "PVR"
    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 250.0);
    }

    // 4. Full constructor → sets all details
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Method to print ticket details
    public void printTicket() {
        System.out.println("\n🎟️ Movie Ticket Details:");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat No: " + seatNumber);
        System.out.println("Price: ₹" + price);
    }
}

// === MAIN CLASS ===
public class MovieTicketSystem {
    public static void main(String[] args) {
        System.out.println("=== MOVIE TICKET BOOKING SYSTEM ===");

        // Using different constructors
        MovieTicket t1 = new MovieTicket(); // default
        MovieTicket t2 = new MovieTicket("Inception"); // movie name only
        MovieTicket t3 = new MovieTicket("Avengers: Endgame", 45); // movie + seat
        MovieTicket t4 = new MovieTicket("Interstellar", "IMAX", 12, 500.0); // full

        // Print tickets
        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
