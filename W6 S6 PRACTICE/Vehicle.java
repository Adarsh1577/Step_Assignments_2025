// File: Car.java
import java.util.Random;

class Vehicle {
    // Protected fields (accessible to subclasses)
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;

    // Private fields (accessed via methods)
    private String registrationNumber;
    private boolean isRunning;

    // Default constructor
    public Vehicle() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.year = 0;
        this.engineType = "Unknown";
        this.registrationNumber = generateRegistrationNumber();
        this.isRunning = false;
        System.out.println("Vehicle default constructor called");
    }

    // Parameterized constructor
    public Vehicle(String brand, String model, int year, String engineType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engineType = engineType;
        this.registrationNumber = generateRegistrationNumber();
        this.isRunning = false;
        System.out.println("Vehicle parameterized constructor called");
    }

    // Helper: generate random registration number
    private String generateRegistrationNumber() {
        Random rand = new Random();
        return "REG-" + (1000 + rand.nextInt(9000));
    }

    // Basic vehicle operations
    public void start() {
        isRunning = true;
        System.out.println("Vehicle started");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Vehicle stopped");
    }

    public String getVehicleInfo() {
        return "Brand: " + brand + ", Model: " + model + ", Year: " + year +
                ", Engine: " + engineType + ", Reg#: " + registrationNumber +
                ", Running: " + isRunning;
    }

    public void displaySpecs() {
        System.out.println("=== Vehicle Specifications ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Engine Type: " + engineType);
    }

    // Getters/Setters for private fields
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean isRunning() {
        return isRunning;
    }
}

// ================= Subclass =================
public class Car extends Vehicle {
    // Car-specific fields
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    // Default constructor
    public Car() {
        super(); // Explicit call to Vehicle default constructor
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    // Parameterized constructor
    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType); // Call parent constructor
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    // Override start()
    @Override
    public void start() {
        super.start(); // Call parent start first
        System.out.println("Car-specific startup sequence: Engine warmed up, AC ready");
    }

    // Override displaySpecs()
    @Override
    public void displaySpecs() {
        super.displaySpecs(); // Call parent method
        System.out.println("=== Car Specifications ===");
        System.out.println("Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
    }

    // Car-specific methods
    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    // Main method to test everything
    public static void main(String[] args) {
        // 1. Test constructor chaining with default constructor
        System.out.println("\n--- Testing Default Constructor ---");
        Car defaultCar = new Car();
        defaultCar.displaySpecs();

        // 2. Test constructor chaining with parameterized constructor
        System.out.println("\n--- Testing Parameterized Constructor ---");
        Car myCar = new Car("Toyota", "Corolla", 2022, "Hybrid", 4, "Petrol", "Automatic");
        myCar.displaySpecs();

        // 3. Test inherited methods
        System.out.println("\n--- Testing Inherited Methods ---");
        myCar.start();
        System.out.println(myCar.getVehicleInfo());
        myCar.stop();

        // 4. Test overridden methods & super keyword
        System.out.println("\n--- Testing Overridden Methods ---");
        myCar.start();
        myCar.displaySpecs();

        // 5. Test car-specific methods
        System.out.println("\n--- Testing Car-Specific Methods ---");
        myCar.openTrunk();
        myCar.playRadio();
    }
}
