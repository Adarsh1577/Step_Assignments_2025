class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName = "Default Rentals";
    private static int rentalDays = 0;
    private static int vehicleCounter = 0;

    public Vehicle(String brand, String model, double rentPerDay) {
        this.vehicleId = generateVehicleId();
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        totalVehicles++;
    }

    private static String generateVehicleId() {
        vehicleCounter++;
        return String.format("V%03d", vehicleCounter);
    }

    public double rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is not available.");
            return 0;
        }
        double rent = calculateRent(days);
        isAvailable = false;
        rentalDays += days;
        System.out.println(vehicleId + " rented for " + days + " days. Rent: " + rent);
        return rent;
    }

    public void returnVehicle() {
        if (isAvailable) {
            System.out.println(vehicleId + " is already available.");
        } else {
            isAvailable = true;
            System.out.println(vehicleId + " returned and is now available.");
        }
    }

    private double calculateRent(int days) {
        double rent = days * rentPerDay;
        totalRevenue += rent;
        return rent;
    }

    public void displayVehicleInfo() {
        System.out.println("=================================");
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent per Day: " + rentPerDay);
        System.out.println("Available: " + isAvailable);
        System.out.println("=================================");
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("\n=== " + companyName + " - Company Stats ===");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent per Day: " + getAverageRentPerDay());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("Zoomy Rentals");

        Vehicle v1 = new Vehicle("Toyota", "Innova", 2000);
        Vehicle v2 = new Vehicle("Honda", "City", 1500);
        Vehicle v3 = new Vehicle("Suzuki", "Swift", 1000);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        v1.rentVehicle(3); 
        v2.rentVehicle(5);
        v1.returnVehicle();
        v3.rentVehicle(2);

        System.out.println("\nAfter Transactions:");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        Vehicle.displayCompanyStats();
    }
}
