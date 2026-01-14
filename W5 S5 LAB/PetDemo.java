import java.util.*;

// Immutable PetSpecies
final class PetSpecies {
    private final String speciesName;
    private final String[] evolutionStages;
    private final int maxLifespan;
    private final String habitat;

    public PetSpecies(String speciesName, String[] evolutionStages, int maxLifespan, String habitat) {
        if (speciesName == null || speciesName.isBlank() ||
            evolutionStages == null || evolutionStages.length == 0 ||
            maxLifespan <= 0 || habitat == null || habitat.isBlank()) {
            throw new IllegalArgumentException("Invalid species data");
        }
        this.speciesName = speciesName;
        this.evolutionStages = Arrays.copyOf(evolutionStages, evolutionStages.length);
        this.maxLifespan = maxLifespan;
        this.habitat = habitat;
    }

    public String getSpeciesName() { return speciesName; }
    public String[] getEvolutionStages() { return Arrays.copyOf(evolutionStages, evolutionStages.length); }
    public int getMaxLifespan() { return maxLifespan; }
    public String getHabitat() { return habitat; }

    @Override
    public String toString() {
        return "PetSpecies{" +
                "speciesName='" + speciesName + '\'' +
                ", evolutionStages=" + Arrays.toString(evolutionStages) +
                ", maxLifespan=" + maxLifespan +
                ", habitat='" + habitat + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PetSpecies)) return false;
        PetSpecies that = (PetSpecies) o;
        return maxLifespan == that.maxLifespan &&
                speciesName.equals(that.speciesName) &&
                Arrays.equals(evolutionStages, that.evolutionStages) &&
                habitat.equals(that.habitat);
    }

    @Override
    public int hashCode() {
        int result = speciesName.hashCode();
        result = 31 * result + Arrays.hashCode(evolutionStages);
        result = 31 * result + maxLifespan;
        result = 31 * result + habitat.hashCode();
        return result;
    }
}

// Core VirtualPet
class VirtualPet {
    private final String petId;
    private final PetSpecies species;
    private final long birthTimestamp;

    private String petName;
    private int age;
    private int happiness;
    private int health;

    protected static final String[] DEFAULT_EVOLUTION_STAGES = {"Egg","Baby","Teen","Adult"};
    static final int MAX_HAPPINESS = 100;
    static final int MAX_HEALTH = 100;
    public static final String PET_SYSTEM_VERSION = "2.0";

    public VirtualPet() {
        this("Unnamed", new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 10, "Unknown"), 0, 50, 50);
    }

    public VirtualPet(String petName) {
        this(petName, new PetSpecies("Default", DEFAULT_EVOLUTION_STAGES, 10, "Unknown"), 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species) {
        this(petName, species, 0, 50, 50);
    }

    public VirtualPet(String petName, PetSpecies species, int age, int happiness, int health) {
        this.petId = generatePetId();
        this.species = Objects.requireNonNull(species);
        this.birthTimestamp = System.currentTimeMillis();
        this.petName = petName;
        setAge(age);
        setHappiness(happiness);
        setHealth(health);
    }

    public String getPetId() { return petId; }
    public PetSpecies getSpecies() { return species; }
    public long getBirthTimestamp() { return birthTimestamp; }
    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = Math.max(0, age); }
    public int getHappiness() { return happiness; }
    public void setHappiness(int happiness) { this.happiness = validateStat(happiness); }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = validateStat(health); }

    public void feedPet(String foodType) {
        modifyHealth(calculateFoodBonus(foodType));
    }

    public void playWithPet(String gameType) {
        modifyHappiness(calculateGameEffect(gameType));
    }

    protected int calculateFoodBonus(String foodType) {
        return foodType.equalsIgnoreCase("fruit") ? 10 : 5;
    }

    protected int calculateGameEffect(String gameType) {
        return gameType.equalsIgnoreCase("fetch") ? 15 : 7;
    }

    private void modifyHappiness(int delta) {
        happiness = validateStat(happiness + delta);
        checkEvolution();
    }

    private void modifyHealth(int delta) {
        health = validateStat(health + delta);
        checkEvolution();
    }

    private void updateEvolutionStage() {
        System.out.println(petName + " is evolving!");
    }

    int[] getInternalState() {
        return new int[]{age, happiness, health};
    }

    private int validateStat(int stat) {
        if (stat < 0) return 0;
        if (stat > 100) return 100;
        return stat;
    }

    private String generatePetId() {
        return UUID.randomUUID().toString();
    }

    private void checkEvolution() {
        if (happiness == MAX_HAPPINESS || health == MAX_HEALTH) {
            updateEvolutionStage();
        }
    }

    @Override
    public String toString() {
        return "VirtualPet{" +
                "petId='" + petId + '\'' +
                ", species=" + species.getSpeciesName() +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", happiness=" + happiness +
                ", health=" + health +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VirtualPet)) return false;
        VirtualPet that = (VirtualPet) o;
        return petId.equals(that.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId);
    }
}

// DragonPet with composition
class DragonPet {
    private final String dragonType;
    private final String breathWeapon;
    private final VirtualPet basePet;

    public DragonPet(String dragonType, String breathWeapon, String petName) {
        this.dragonType = dragonType;
        this.breathWeapon = breathWeapon;
        this.basePet = new VirtualPet(petName, new PetSpecies("Dragon", VirtualPet.DEFAULT_EVOLUTION_STAGES, 200, "Caves"));
    }

    public String getDragonType() { return dragonType; }
    public String getBreathWeapon() { return breathWeapon; }
    public VirtualPet getBasePet() { return basePet; }

    @Override
    public String toString() {
        return "DragonPet{" +
                "dragonType='" + dragonType + '\'' +
                ", breathWeapon='" + breathWeapon + '\'' +
                ", basePet=" + basePet +
                '}';
    }
}

// RobotPet with composition
class RobotPet {
    private boolean needsCharging;
    private int batteryLevel;
    private final VirtualPet basePet;

    public RobotPet(String petName) {
        this.needsCharging = false;
        this.batteryLevel = 100;
        this.basePet = new VirtualPet(petName, new PetSpecies("Robot", VirtualPet.DEFAULT_EVOLUTION_STAGES, 500, "Laboratory"));
    }

    public boolean isNeedsCharging() { return needsCharging; }
    public void setNeedsCharging(boolean needsCharging) { this.needsCharging = needsCharging; }
    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = Math.max(0, Math.min(100, batteryLevel));
    }
    public VirtualPet getBasePet() { return basePet; }

    @Override
    public String toString() {
        return "RobotPet{" +
                "needsCharging=" + needsCharging +
                ", batteryLevel=" + batteryLevel +
                ", basePet=" + basePet +
                '}';
    }
}

// Main demo class
public class PetDemo {
    public static void main(String[] args) {
        PetSpecies cat = new PetSpecies("Cat", new String[]{"Kitten","Adult","Elder"}, 20, "House");
        VirtualPet pet1 = new VirtualPet("Whiskers", cat, 2, 80, 70);

        pet1.feedPet("fruit");
        pet1.playWithPet("fetch");
        System.out.println(pet1);

        DragonPet dragon = new DragonPet("Fire Dragon", "Flame Breath", "Smaug");
        System.out.println(dragon);

        RobotPet robot = new RobotPet("RoboPet");
        robot.setBatteryLevel(50);
        robot.setNeedsCharging(true);
        System.out.println(robot);

        System.out.println("Internal State of pet1: " + Arrays.toString(pet1.getInternalState()));
    }
}
