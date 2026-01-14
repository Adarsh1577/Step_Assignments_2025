// Parent class: Phone
class Phone {
    protected String brand;
    protected String model;

    // Default constructor
    public Phone() {
        System.out.println("Phone default constructor called.");
    }

    // Parameterized constructor
    public Phone(String brand, String model) {
        this.brand = brand;
        this.model = model;
        System.out.println("Phone parameterized constructor called.");
    }

    public void displayPhone() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
    }
}

// Child class: SmartPhone
class SmartPhone extends Phone {
    private String operatingSystem;

    // Default constructor
    public SmartPhone() {
        super(); // Calls Phone default constructor
        System.out.println("SmartPhone default constructor called.");
    }

    // Parameterized constructor
    public SmartPhone(String brand, String model, String operatingSystem) {
        super(brand, model); // Calls Phone parameterized constructor
        this.operatingSystem = operatingSystem;
        System.out.println("SmartPhone parameterized constructor called.");
    }

    public void displaySmartPhone() {
        displayPhone(); // From parent
        System.out.println("Operating System: " + operatingSystem);
    }
}

// Main class
public class PhoneTest {
    public static void main(String[] args) {
        System.out.println("=== Creating SmartPhone with default constructor ===");
        SmartPhone s1 = new SmartPhone();

        System.out.println("\n=== Creating SmartPhone with parameterized constructor ===");
        SmartPhone s2 = new SmartPhone("Apple", "iPhone 15", "iOS");

        System.out.println("\nDisplaying details of parameterized object:");
        s2.displaySmartPhone();
    }
}
