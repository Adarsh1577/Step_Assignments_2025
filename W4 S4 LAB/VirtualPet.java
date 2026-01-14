import java.util.Random;
import java.util.UUID;

public class VirtualPet {
    // ====== Fields ======
    private final String petId;
    private String petName;
    private String species;
    private int age;
    private int happiness;
    private int health;
    private String stage;
    private boolean isDead;

    // Static variables
    public static final String[] EVOLUTION_STAGES =
            {"Egg", "Baby", "Child", "Teen", "Adult", "Elder"};
    private static int totalPetsCreated = 0;

    // ====== Constructors ======

    // Default constructor -> mysterious egg
    public VirtualPet() {
        this("Unknown", getRandomSpecies(), 0, 50, 50, "Egg");
    }

    // Constructor with name only -> starts as Baby
    public VirtualPet(String petName) {
        this(petName, getRandomSpecies(), 0, 60, 60, "Baby");
    }

    // Constructor with name + species -> starts as Child
    public VirtualPet(String petName, String species) {
        this(petName, species, 1, 70, 70, "Child");
    }

    // Full constructor (main one with this() chaining)
    public VirtualPet(String petName, String species, int age,
                      int happiness, int health, String stage) {
        this.petId = generatePetId();
        this.petName = petName;
        this.species = species;
        this.age = age;
        this.happiness = happiness;
        this.health = health;
        this.stage = stage;
        this.isDead = false;

        totalPetsCreated++;
        System.out.println("New Pet Created: " + this.petName + " (" + this.stage + ")");
    }

    // ====== Unique Methods ======

    public void feedPet() {
        if (!isDead) {
            health = Math.min(100, health + 10);
            System.out.println(petName + " was fed. Health: " + health);
        }
    }

    public void playWithPet() {
        if (!isDead) {
            happiness = Math.min(100, happiness + 15);
            health -= 5; // playing tires the pet
            System.out.println(petName + " played happily! Happiness: " + happiness + ", Health: " + health);
            checkIfDead();
        }
    }

    public void healPet() {
        if (!isDead) {
            health = Math.min(100, health + 20);
            System.out.println(petName + " has been healed. Health: " + health);
        }
    }

    public void simulateDay() {
        if (isDead) {
            System.out.println(petName + " is haunting other pets as a ghost 👻...");
            return;
        }

        age++;
        Random rand = new Random();
        happiness -= rand.nextInt(10);
        health -= rand.nextInt(10);

        System.out.println("A new day for " + petName + " (Age " + age + "). Stats: Happiness " + happiness + ", Health " + health);
        evolvePet();
        checkIfDead();
    }

    public void evolvePet() {
        if (isDead) return;

        if (age >= 0 && age < 2) stage = EVOLUTION_STAGES[1]; // Baby
        else if (age < 5) stage = EVOLUTION_STAGES[2]; // Child
        else if (age < 10) stage = EVOLUTION_STAGES[3]; // Teen
        else if (age < 15) stage = EVOLUTION_STAGES[4]; // Adult
        else stage = EVOLUTION_STAGES[5]; // Elder
    }

    private void checkIfDead() {
        if (health <= 0) {
            isDead = true;
            stage = "Ghost";
            System.out.println("💀 " + petName + " has died and become a Ghost!");
        }
    }

    public String getPetStatus() {
        return petName + " [" + species + "] | Stage: " + stage + " | Age: " + age + " | Happiness: " + happiness + " | Health: " + health;
    }

    // ====== Static Methods ======
    public static String generatePetId() {
        return "PET-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public static int getTotalPetsCreated() {
        return totalPetsCreated;
    }

    private static String getRandomSpecies() {
        String[] speciesList = {"Dragon", "Cat", "Dog", "Phoenix", "Turtle"};
        Random rand = new Random();
        return speciesList[rand.nextInt(speciesList.length)];
    }

    // ====== MAIN ======
    public static void main(String[] args) {
        System.out.println("=== VIRTUAL PET DAYCARE SIMULATION ===");

        // Create pets using different constructors
        VirtualPet pet1 = new VirtualPet(); // Egg
        VirtualPet pet2 = new VirtualPet("Fluffy"); // Baby
        VirtualPet pet3 = new VirtualPet("Rex", "Dragon"); // Child
        VirtualPet pet4 = new VirtualPet("Luna", "Phoenix", 10, 80, 80, "Teen"); // Custom

        // Simulate daycare
        for (int day = 1; day <= 5; day++) {
            System.out.println("\n--- Day " + day + " ---");
            pet1.simulateDay();
            pet2.simulateDay();
            pet3.simulateDay();
            pet4.simulateDay();

            pet2.feedPet();
            pet3.playWithPet();
            pet4.healPet();

            System.out.println(pet1.getPetStatus());
            System.out.println(pet2.getPetStatus());
            System.out.println(pet3.getPetStatus());
            System.out.println(pet4.getPetStatus());
        }

        System.out.println("\nTotal Pets Created: " + VirtualPet.getTotalPetsCreated());
    }
}
