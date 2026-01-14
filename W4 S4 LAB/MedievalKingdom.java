// 🏰 Medieval Kingdom Builder with Magic System
abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    // Default constructor
    public MagicalStructure() {
        this("Unknown Structure", 50, "Unknown", true);
    }

    // Two-parameter constructor
    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Central Kingdom", true);
    }

    // Full constructor
    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
        System.out.println("Constructed: " + structureName + " at " + location);
    }

    // Abstract method
    public abstract void castMagicSpell();

    @Override
    public String toString() {
        return structureName + " [Power=" + magicPower + ", Location=" + location + ", Active=" + isActive + "]";
    }
}

// === WizardTower ===
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    // Empty tower
    public WizardTower() {
        this("Wizard Tower", 100, "Hilltop", true, 5, new String[]{"Fireball"});
    }

    // Tower with custom spells
    public WizardTower(String[] spells) {
        this("Wizard Tower", 120, "Hilltop", true, spells.length, spells);
    }

    // Fully equipped tower
    public WizardTower(String structureName, int magicPower, String location, boolean isActive, int spellCapacity, String[] knownSpells) {
        super(structureName, magicPower, location, isActive);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " casts " + knownSpells[0] + "!");
    }

    public int getSpellCapacity() {
        return spellCapacity;
    }
}

// === EnchantedCastle ===
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    public EnchantedCastle() {
        this("Enchanted Castle", 150, "Riverbank", true, 300, true);
    }

    public EnchantedCastle(int defenseRating) {
        this("Enchanted Castle", 200, "Riverbank", true, defenseRating, false);
    }

    public EnchantedCastle(String structureName, int magicPower, String location, boolean isActive, int defenseRating, boolean hasDrawbridge) {
        super(structureName, magicPower, location, isActive);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " activates magical shield with defense rating " + defenseRating);
    }

    public int getDefenseRating() {
        return defenseRating;
    }
}

// === MysticLibrary ===
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    public MysticLibrary() {
        this("Mystic Library", 80, "Forest Edge", true, 200, "Latin");
    }

    public MysticLibrary(int bookCount) {
        this("Mystic Library", 100, "Forest Edge", true, bookCount, "Ancient Greek");
    }

    public MysticLibrary(String structureName, int magicPower, String location, boolean isActive, int bookCount, String ancientLanguage) {
        super(structureName, magicPower, location, isActive);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " unlocks wisdom from " + ancientLanguage + " texts!");
    }
}

// === DragonLair ===
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    public DragonLair() {
        this("Dragon Lair", 300, "Volcano", true, "Fire Dragon", 1000);
    }

    public DragonLair(String dragonType) {
        this("Dragon Lair", 250, "Cave", true, dragonType, 700);
    }

    public DragonLair(String structureName, int magicPower, String location, boolean isActive, String dragonType, int treasureValue) {
        super(structureName, magicPower, location, isActive);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " with " + dragonType + " breathes fire!");
    }

    public int getTreasureValue() {
        return treasureValue;
    }
}

// === Kingdom Manager ===
class KingdomManager {
    // Special interactions
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
               (s1 instanceof EnchantedCastle && s2 instanceof DragonLair);
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower) {
            return attacker.structureName + " wins over " + defender.structureName;
        } else {
            return defender.structureName + " withstands the attack!";
        }
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) {
            total += s.magicPower;
        }
        return total;
    }
}

// === Main Simulation ===
public class MedievalKingdom {
    public static void main(String[] args) {
        System.out.println("=== Medieval Kingdom Builder Simulation ===");

        WizardTower tower = new WizardTower();
        EnchantedCastle castle = new EnchantedCastle();
        MysticLibrary library = new MysticLibrary();
        DragonLair lair = new DragonLair("Ice Dragon");

        // Cast spells
        tower.castMagicSpell();
        castle.castMagicSpell();
        library.castMagicSpell();
        lair.castMagicSpell();

        // Interactions
        System.out.println("\nInteractions:");
        System.out.println("Tower + Library interact? " + KingdomManager.canStructuresInteract(tower, library));
        System.out.println("Castle + Lair interact? " + KingdomManager.canStructuresInteract(castle, lair));

        // Battle
        System.out.println("\nBattle:");
        System.out.println(KingdomManager.performMagicBattle(tower, lair));

        // Total power
        MagicalStructure[] allStructures = {tower, castle, library, lair};
        System.out.println("\nTotal Kingdom Magic Power: " + KingdomManager.calculateKingdomMagicPower(allStructures));
    }
}
