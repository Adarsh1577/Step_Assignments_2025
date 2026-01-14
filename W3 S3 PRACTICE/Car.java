public class Car {
    // Instance variables (attributes) - properties of each car
    String brand;
    String model;
    int year;
    String color;
    boolean isRunning;

    // Constructor to initialize all attributes
    public Car(String brand, String model, int year, String color) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.isRunning = false; // Initially, engine is off
    }

    // Method to start the engine
    public void startEngine() {
        if (!isRunning) {
            isRunning = true;
            System.out.println(brand + " " + model + " engine started.");
        } else {
            System.out.println(brand + " " + model + " engine is already running.");
        }
    }

    // Method to stop the engine
    public void stopEngine() {
        if (isRunning) {
            isRunning = false;
            System.out.println(brand + " " + model + " engine stopped.");
        } else {
            System.out.println(brand + " " + model + " engine is already off.");
        }
    }

    // Method to display car information
    public void displayInfo() {
        System.out.println("Car Info: " + brand + " " + model + " (" + year + "), Color: " + color + ", Running: " + isRunning);
    }

    // Method to calculate car age
    public int getAge(int currentYear) {
        return currentYear - year;
    }

    // Main method - Program entry point
    public static void main(String[] args) {
        // Creating 3 Car objects with different attributes
        Car car1 = new Car("Toyota", "Corolla", 2015, "White");
        Car car2 = new Car("Tesla", "Model 3", 2022, "Red");
        Car car3 = new Car("Ford", "Mustang", 2018, "Black");

        // Demonstrating methods on each object
        car1.displayInfo();
        car1.startEngine();
        System.out.println("Age of Car1: " + car1.getAge(2025) + " years");
        car1.stopEngine();
        System.out.println();

        car2.displayInfo();
        car2.startEngine();
        System.out.println("Age of Car2: " + car2.getAge(2025) + " years");
        car2.stopEngine();
        System.out.println();

        car3.displayInfo();
        car3.startEngine();
        System.out.println("Age of Car3: " + car3.getAge(2025) + " years");
        car3.stopEngine();

        /*
         Real-world analogy explanation:
         - Each Car object (car1, car2, car3) represents a real car.
         - They all belong to the "Car" class (like a blueprint).
         - Each car has its own state (brand, model, year, color, isRunning).
         - Actions (startEngine, stopEngine) affect only that specific car, 
           not others.
         - Just like in real life, cars may look similar but behave independently.
        */
    }
}
