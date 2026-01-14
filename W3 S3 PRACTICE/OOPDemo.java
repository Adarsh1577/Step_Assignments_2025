class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    public void startVehicle() {
        System.out.println(make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled by " + amount + ". Current fuel: " + fuelLevel);
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle: " + make + " " + model + " (" + year + "), Fuel: " + fuelLevel);
    }
}

class Car extends Vehicle {
    public Car(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void startVehicle() {
        System.out.println(make + " " + model + " (Car) smoothly started.");
    }
}

class Truck extends Vehicle {
    public Truck(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void startVehicle() {
        System.out.println(make + " " + model + " (Truck) roared to life.");
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String make, String model, int year, double fuelLevel) {
        super(make, model, year, fuelLevel);
    }

    @Override
    public void startVehicle() {
        System.out.println(make + " " + model + " (Motorcycle) zoomed away.");
    }
}

public class OOPDemo {
    public static void main(String[] args) {
        Vehicle v1 = new Car("Toyota", "Corolla", 2020, 50);
        Vehicle v2 = new Truck("Volvo", "FH16", 2018, 120);
        Vehicle v3 = new Motorcycle("Yamaha", "R15", 2022, 15);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        System.out.println("\n--- Starting Vehicles ---");
        v1.startVehicle();
        v2.startVehicle();
        v3.startVehicle();

        System.out.println("\n--- Refueling Vehicles ---");
        v1.refuel(20);
        v2.refuel(50);
        v3.refuel(5);

        System.out.println("\n--- Vehicle Array (Polymorphism) ---");
        Vehicle[] vehicles = {v1, v2, v3};
        for (Vehicle v : vehicles) {
            v.startVehicle(); 
            v.stopVehicle();
        }

        /*
          How does this show reusability?
          - The Vehicle base class defines common properties & methods reused by all types.
          - Car, Truck, Motorcycle inherit and override behavior, without rewriting everything.

          How could this be extended for new vehicle types?
          - Simply create a new subclass (e.g., Bus, ElectricCar) extending Vehicle.
          - Override or add methods specific to that type.

          Benefits over writing separate classes:
          - Avoids code duplication (startVehicle, refuel, displayInfo are reused).
          - Easier maintenance (fixing logic in Vehicle applies to all subclasses).
          - Promotes scalability (new vehicles can be added with minimal changes).
        */
    }
}
