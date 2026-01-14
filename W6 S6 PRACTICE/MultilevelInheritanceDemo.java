// File: MultilevelInheritanceDemo.java
public class MultilevelInheritanceDemo {
    public static void main(String[] args) {
        // 1. Test constructor chaining with minimal Dog constructor
        System.out.println("\n--- Creating Dog with minimal constructor ---");
        Dog dog1 = new Dog();
        dog1.demonstrateInheritance();

        // 2. Test constructor chaining with detailed Dog constructor
        System.out.println("\n--- Creating Dog with detailed constructor ---");
        Dog dog2 = new Dog(
                "Dog", "Domestic", 12, true, // Animal params
                "Brown", 60,                 // Mammal params
                "Labrador", true, 9, "Playing fetch" // Dog params
        );
        dog2.demonstrateInheritance();

        // 3. Test copy constructor
        System.out.println("\n--- Creating Dog with copy constructor ---");
        Dog dog3 = new Dog(dog2);
        dog3.demonstrateInheritance();

        // 4. Test instanceof relationships
        System.out.println("\n--- Testing instanceof relationships ---");
        System.out.println("dog3 instanceof Dog: " + (dog3 instanceof Dog));
        System.out.println("dog3 instanceof Mammal: " + (dog3 instanceof Mammal));
        System.out.println("dog3 instanceof Animal: " + (dog3 instanceof Animal));
    }
}

// ================= Base Class =================
class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    // Constructor
    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    // Methods
    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }

    public void move() {
        System.out.println("Animal is moving");
    }

    public String getAnimalInfo() {
        return "Species: " + species + ", Habitat: " + habitat +
                ", Lifespan: " + lifespan + " years, Wildlife: " + isWildlife;
    }
}

// ================= Intermediate Class =================
class Mammal extends Animal {
    protected String furColor;
    protected boolean hasWarmBlood;
    protected int gestationPeriod; // days

    // Constructor
    public Mammal(String species, String habitat, int lifespan, boolean isWildlife,
                  String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.gestationPeriod = gestationPeriod;
        this.hasWarmBlood = true; // always true for mammals
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    // Override move()
    @Override
    public void move() {
        super.move(); // Call parent move
        System.out.println("Mammal is walking/running");
    }

    // Mammal-specific methods
    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }

    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

// ================= Specific Class =================
class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel; // 1-10 scale
    private String favoriteActivity;

    // Constructor 1: Basic dog with defaults
    public Dog() {
        super("Dog", "Domestic", 12, true, "Unknown", 60);
        this.breed = "Unknown";
        this.isDomesticated = true;
        this.loyaltyLevel = 5;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating default dog");
    }

    // Constructor 2: Detailed dog
    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod,
               String breed, boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    // Constructor 3: Copy constructor
    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             other.furColor, other.gestationPeriod,
             other.breed, other.isDomesticated, other.loyaltyLevel, other.favoriteActivity);
        System.out.println("Dog constructor: Copying dog of breed " + other.breed);
    }

    // Override methods
    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is wagging tail while eating");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    // Dog-specific methods
    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Dog loyalty level: " + loyaltyLevel + "/10");
    }

    // Demonstrate inheritance chain
    public void demonstrateInheritance() {
        System.out.println("\n--- Demonstrating Inheritance for " + breed + " ---");
        System.out.println(getAnimalInfo()); // from Animal
        eat();                               // overridden in Dog
        move();                              // overridden in Dog
        sleep();                             // overridden in Dog
        nurse();                             // from Mammal
        regulateTemperature();               // from Mammal
        bark();                              // Dog-specific
        fetch();                             // Dog-specific
        showLoyalty();                       // Dog-specific
    }
}
