import java.util.*;

// Base Vehicle class
class Vehicle {
    protected String vehicleId;
    protected String brand;
    protected String model;
    protected int year;
    protected double mileage;
    protected String fuelType;
    protected String currentStatus;

    // Static variables (shared by all vehicles)
    protected static int totalVehicles = 0;
    protected static double fleetValue = 0;
    protected static String companyName = "XYZ Transport Ltd";
    protected static double totalFuelConsumption = 0;

    public Vehicle(String vehicleId, String brand, String model, int year,
                   double mileage, String fuelType, double value) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = "Available";

        totalVehicles++;
        fleetValue += value;
    }

    public void updateMileage(double km, double fuelUsed) {
        mileage += km;
        totalFuelConsumption += fuelUsed;
    }

    public void checkServiceDue() {
        if (mileage >= 10000) {
            System.out.println("⚠️ Service due for Vehicle " + vehicleId);
        } else {
            System.out.println("✅ Vehicle " + vehicleId + " does not need service yet.");
        }
    }

    public void displayInfo() {
        System.out.println("[" + vehicleId + "] " + brand + " " + model +
                " (" + year + "), Mileage: " + mileage + " km, Fuel: " + fuelType +
                ", Status: " + currentStatus);
    }

    // Static methods
    public static void getFleetUtilization() {
        System.out.println("🚍 Total Vehicles in Fleet: " + totalVehicles);
        System.out.println("💰 Fleet Value: $" + fleetValue);
        System.out.println("⛽ Total Fuel Consumption: " + totalFuelConsumption + " liters");
    }
}

// Car class
class Car extends Vehicle {
    private int seatingCapacity;

    public Car(String vehicleId, String brand, String model, int year,
               double mileage, String fuelType, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   🚗 Seating Capacity: " + seatingCapacity);
    }
}

// Bus class
class Bus extends Vehicle {
    private int seatingCapacity;

    public Bus(String vehicleId, String brand, String model, int year,
               double mileage, String fuelType, double value, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, value);
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   🚌 Seating Capacity: " + seatingCapacity);
    }
}

// Truck class
class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String vehicleId, String brand, String model, int year,
                 double mileage, String fuelType, double value, double loadCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType, value);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("   🚛 Load Capacity: " + loadCapacity + " tons");
    }
}

// Driver class
class Driver {
    String driverId;
    String driverName;
    String licenseType;
    Vehicle assignedVehicle;
    int totalTrips;

    public Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
        this.totalTrips = 0;
    }

    public void assignVehicle(Vehicle v) {
        this.assignedVehicle = v;
        v.currentStatus = "Assigned to Driver " + driverName;
        System.out.println("👨‍✈️ Driver " + driverName + " assigned to " + v.vehicleId);
    }

    public void completeTrip(double km, double fuelUsed) {
        if (assignedVehicle != null) {
            totalTrips++;
            assignedVehicle.updateMileage(km, fuelUsed);
            System.out.println("✅ Trip completed by " + driverName +
                    " | Distance: " + km + " km, Fuel: " + fuelUsed + " liters");
        } else {
            System.out.println("⚠️ Driver " + driverName + " has no vehicle assigned!");
        }
    }

    public void displayDriverInfo() {
        System.out.println("Driver: " + driverName + " (ID: " + driverId + "), License: " + licenseType +
                ", Trips: " + totalTrips +
                (assignedVehicle != null ? ", Assigned Vehicle: " + assignedVehicle.vehicleId : ""));
    }
}

// Main Class
public class FleetManagementSystem {
    public static void main(String[] args) {
        // Create Vehicles
        Car car1 = new Car("C101", "Toyota", "Corolla", 2022, 8500, "Petrol", 20000, 5);
        Bus bus1 = new Bus("B201", "Volvo", "CityBus", 2020, 15000, "Diesel", 50000, 40);
        Truck truck1 = new Truck("T301", "Tata", "Hauler", 2021, 12000, "Diesel", 80000, 15);

        // Display Vehicle Info
        car1.displayInfo();
        bus1.displayInfo();
        truck1.displayInfo();

        System.out.println("\n===== DRIVER MANAGEMENT =====");
        // Create Drivers
        Driver d1 = new Driver("D001", "Ramesh", "LMV");
        Driver d2 = new Driver("D002", "Suresh", "HMV");

        d1.assignVehicle(car1);
        d2.assignVehicle(truck1);

        // Trips
        d1.completeTrip(120, 8);
        d2.completeTrip(300, 40);

        // Show driver info
        d1.displayDriverInfo();
        d2.displayDriverInfo();

        System.out.println("\n===== SERVICE CHECK =====");
        car1.checkServiceDue();
        bus1.checkServiceDue();
        truck1.checkServiceDue();

        System.out.println("\n===== FLEET REPORT =====");
        Vehicle.getFleetUtilization();
    }
}
