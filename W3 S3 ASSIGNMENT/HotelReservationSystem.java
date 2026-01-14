import java.util.Scanner;

class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.maxOccupancy = maxOccupancy;
        this.isAvailable = true;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public int getMaxOccupancy() { return maxOccupancy; }

    public void setAvailable(boolean available) { this.isAvailable = available; }

    public void displayRoomInfo() {
        System.out.printf("%-6s %-10s %-12.2f %-8s %-5d\n",
                roomNumber, roomType, pricePerNight, (isAvailable ? "Yes" : "No"), maxOccupancy);
    }
}

class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private String[] bookingHistory;
    private int bookingCount;

    private static int guestCounter = 0;

    public Guest(String guestName, String phoneNumber, String email) {
        this.guestId = "G" + (++guestCounter);
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new String[20];
        this.bookingCount = 0;
    }

    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }

    public void addBookingHistory(String bookingId) {
        if (bookingCount < bookingHistory.length) {
            bookingHistory[bookingCount++] = bookingId;
        }
    }

    public void displayGuestInfo() {
        System.out.println("Guest ID: " + guestId + ", Name: " + guestName +
                ", Phone: " + phoneNumber + ", Email: " + email);
    }
}

class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;

    private static int totalBookings = 0;
    private static double hotelRevenue = 0.0;
    private static String hotelName = "Grand Java Hotel";

    public Booking(Guest guest, Room room, String checkInDate, String checkOutDate, int nights) {
        this.bookingId = "B" + (++totalBookings);
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = nights * room.getPricePerNight();

        room.setAvailable(false);
        guest.addBookingHistory(bookingId);
        hotelRevenue += totalAmount;
    }

    public String getBookingId() { return bookingId; }
    public Room getRoom() { return room; }
    public double getTotalAmount() { return totalAmount; }

    public void cancelReservation() {
        System.out.println("Booking " + bookingId + " canceled.");
        room.setAvailable(true);
        hotelRevenue -= totalAmount;
    }

    public void displayBooking() {
        System.out.println("Booking ID: " + bookingId + ", Guest: " + guest.getGuestName() +
                ", Room: " + room.getRoomNumber() + ", " + room.getRoomType() +
                ", Amount: " + totalAmount + ", From " + checkInDate + " to " + checkOutDate);
    }

    public static double getTotalRevenue() { return hotelRevenue; }
    public static int getTotalBookings() { return totalBookings; }
    public static String getHotelName() { return hotelName; }

    public static double getOccupancyRate(Room[] rooms) {
        int total = rooms.length, booked = 0;
        for (Room r : rooms) if (!r.isAvailable()) booked++;
        return (booked * 100.0) / total;
    }

    public static String getMostPopularRoomType(Booking[] bookings) {
        int single = 0, doubleR = 0, suite = 0;
        for (Booking b : bookings) {
            if (b != null) {
                switch (b.getRoom().getRoomType().toLowerCase()) {
                    case "single": single++; break;
                    case "double": doubleR++; break;
                    case "suite": suite++; break;
                }
            }
        }
        if (single >= doubleR && single >= suite) return "Single";
        else if (doubleR >= single && doubleR >= suite) return "Double";
        else return "Suite";
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Room[] rooms = {
            new Room("101", "Single", 2000, 1),
            new Room("102", "Single", 2000, 1),
            new Room("201", "Double", 3500, 2),
            new Room("202", "Double", 3500, 2),
            new Room("301", "Suite", 6000, 4)
        };

        Guest[] guests = new Guest[10];
        Booking[] bookings = new Booking[20];
        int guestCount = 0, bookingCount = 0;

        int choice;
        do {
            System.out.println("\n===== " + Booking.getHotelName() + " Reservation Menu =====");
            System.out.println("1. View All Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View All Bookings");
            System.out.println("5. Reports");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.printf("%-6s %-10s %-12s %-8s %-5s\n",
                            "Room", "Type", "Price", "Avail?", "Occ");
                    for (Room r : rooms) r.displayRoomInfo();
                    break;

                case 2:
                    System.out.print("Enter Guest Name: ");
                    String gname = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    Guest g = new Guest(gname, phone, email);
                    guests[guestCount++] = g;

                    System.out.print("Enter Room Number: ");
                    String roomNo = sc.nextLine();
                    Room selectedRoom = null;
                    for (Room r : rooms) {
                        if (r.getRoomNumber().equals(roomNo) && r.isAvailable()) {
                            selectedRoom = r;
                            break;
                        }
                    }
                    if (selectedRoom == null) {
                        System.out.println("Room not available!");
                        break;
                    }

                    System.out.print("Check-In Date: ");
                    String checkIn = sc.nextLine();
                    System.out.print("Check-Out Date: ");
                    String checkOut = sc.nextLine();
                    System.out.print("Number of nights: ");
                    int nights = sc.nextInt(); sc.nextLine();

                    Booking b = new Booking(g, selectedRoom, checkIn, checkOut, nights);
                    bookings[bookingCount++] = b;
                    System.out.println("Reservation Successful!");
                    b.displayBooking();
                    break;

                case 3:
                    System.out.print("Enter Booking ID to cancel: ");
                    String bid = sc.nextLine();
                    for (int i = 0; i < bookingCount; i++) {
                        if (bookings[i] != null && bookings[i].getBookingId().equals(bid)) {
                            bookings[i].cancelReservation();
                            bookings[i] = null;
                            break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("All Bookings:");
                    for (Booking bk : bookings) {
                        if (bk != null) bk.displayBooking();
                    }
                    break;

                case 5:
                    System.out.println("Hotel Reports:");
                    System.out.println("Total Bookings: " + Booking.getTotalBookings());
                    System.out.println("Total Revenue: " + Booking.getTotalRevenue());
                    System.out.println("Occupancy Rate: " +
                            Booking.getOccupancyRate(rooms) + "%");
                    System.out.println("Most Popular Room Type: " +
                            Booking.getMostPopularRoomType(bookings));
                    break;

                case 6:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
