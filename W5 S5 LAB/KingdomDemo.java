import java.util.*;

// ---------------- Immutable Config ----------------
final class KingdomConfig {
    private final String kingdomName;
    private final int foundingYear;
    private final String[] allowedStructureTypes;
    private final Map<String, Integer> resourceLimits;

    public KingdomConfig(String kingdomName, int foundingYear, String[] allowedStructureTypes, Map<String, Integer> resourceLimits) {
        if (kingdomName == null || kingdomName.isBlank() || foundingYear <= 0 ||
            allowedStructureTypes == null || allowedStructureTypes.length == 0 ||
            resourceLimits == null || resourceLimits.isEmpty()) {
            throw new IllegalArgumentException("Invalid kingdom configuration!");
        }
        this.kingdomName = kingdomName;
        this.foundingYear = foundingYear;
        this.allowedStructureTypes = Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length);
        this.resourceLimits = new HashMap<>(resourceLimits);
    }

    public String getKingdomName() { return kingdomName; }
    public int getFoundingYear() { return foundingYear; }
    public String[] getAllowedStructureTypes() { return Arrays.copyOf(allowedStructureTypes, allowedStructureTypes.length); }
    public Map<String, Integer> getResourceLimits() { return new HashMap<>(resourceLimits); }

    public static KingdomConfig createDefaultKingdom() {
        return new KingdomConfig("Default Kingdom", 1000,
                new String[]{"WizardTower", "EnchantedCastle", "MysticLibrary", "DragonLair"},
                Map.of("gold", 10000, "mana", 5000));
    }

    public static KingdomConfig createFromTemplate(String type) {
        switch (type.toLowerCase()) {
            case "magic":
                return new KingdomConfig("Arcane Empire", 1200,
                        new String[]{"WizardTower", "MysticLibrary"},
                        Map.of("mana", 10000, "gold", 3000));
            case "military":
                return new KingdomConfig("Iron Realm", 900,
                        new String[]{"EnchantedCastle", "DragonLair"},
                        Map.of("gold", 15000, "iron", 8000));
            default:
                return createDefaultKingdom();
        }
    }

    @Override
    public String toString() {
        return "KingdomConfig{" +
                "kingdomName='" + kingdomName + '\'' +
                ", foundingYear=" + foundingYear +
                ", allowedStructureTypes=" + Arrays.toString(allowedStructureTypes) +
                ", resourceLimits=" + resourceLimits +
                '}';
    }
}

// ---------------- Base Magical Structure ----------------
class MagicalStructure {
    private final String structureId;
    private final long constructionTimestamp;
    private final String structureName;
    private final String location;

    private int magicPower;
    private boolean isActive;
    private String currentMaintainer;

    static final int MIN_MAGIC_POWER = 0;
    static final int MAX_MAGIC_POWER = 1000;
    public static final String MAGIC_SYSTEM_VERSION = "3.0";

    public MagicalStructure(String name, String location) {
        this(name, location, 100, true);
    }

    public MagicalStructure(String name, String location, int power) {
        this(name, location, power, true);
    }

    public MagicalStructure(String name, String location, int power, boolean active) {
        if (name == null || name.isBlank() || location == null || location.isBlank()) {
            throw new IllegalArgumentException("Invalid structure data");
        }
        this.structureId = UUID.randomUUID().toString();
        this.constructionTimestamp = System.currentTimeMillis();
        this.structureName = name;
        this.location = location;
        this.magicPower = validatePower(power);
        this.isActive = active;
        this.currentMaintainer = "Unknown";
    }

    private int validatePower(int p) {
        return Math.max(MIN_MAGIC_POWER, Math.min(MAX_MAGIC_POWER, p));
    }

    public String getStructureName() { return structureName; }
    public String getLocation() { return location; }
    public int getMagicPower() { return magicPower; }
    public boolean isActive() { return isActive; }
    public String getCurrentMaintainer() { return currentMaintainer; }

    public void setMagicPower(int power) { this.magicPower = validatePower(power); }
    public void setActive(boolean active) { this.isActive = active; }
    public void setCurrentMaintainer(String maintainer) { this.currentMaintainer = maintainer; }

    @Override
    public String toString() {
        return "MagicalStructure{" +
                "structureId='" + structureId + '\'' +
                ", structureName='" + structureName + '\'' +
                ", location='" + location + '\'' +
                ", magicPower=" + magicPower +
                ", isActive=" + isActive +
                ", currentMaintainer='" + currentMaintainer + '\'' +
                '}';
    }
}

// ---------------- Specialized Structures ----------------
class WizardTower {
    private final int maxSpellCapacity;
    private List<String> knownSpells;
    private String currentWizard;
    private final MagicalStructure base;

    public WizardTower(String name, String location) {
        this(name, location, 500, new ArrayList<>(), "None");
    }

    public WizardTower(String name, String location, int capacity, List<String> spells, String wizard) {
        this.base = new MagicalStructure(name, location, 200, true);
        this.maxSpellCapacity = capacity;
        this.knownSpells = new ArrayList<>(spells);
        this.currentWizard = wizard;
    }

    public void addSpell(String spell) {
        if (knownSpells.size() < maxSpellCapacity) {
            knownSpells.add(spell);
        }
    }

    @Override
    public String toString() {
        return "WizardTower{" +
                "maxSpellCapacity=" + maxSpellCapacity +
                ", knownSpells=" + knownSpells +
                ", currentWizard='" + currentWizard + '\'' +
                ", base=" + base +
                '}';
    }
}

class EnchantedCastle {
    private final String castleType;
    private int defenseRating;
    private boolean hasDrawbridge;
    private final MagicalStructure base;

    public EnchantedCastle(String name, String location, String castleType) {
        this.base = new MagicalStructure(name, location, 300, true);
        this.castleType = castleType;
        this.defenseRating = 100;
        this.hasDrawbridge = true;
    }

    @Override
    public String toString() {
        return "EnchantedCastle{" +
                "castleType='" + castleType + '\'' +
                ", defenseRating=" + defenseRating +
                ", hasDrawbridge=" + hasDrawbridge +
                ", base=" + base +
                '}';
    }
}

class MysticLibrary {
    private final Map<String, String> bookCollection;
    private int knowledgeLevel;
    private final MagicalStructure base;

    public MysticLibrary(String name, String location) {
        this.base = new MagicalStructure(name, location, 400, true);
        this.bookCollection = new HashMap<>();
        this.knowledgeLevel = 50;
    }

    public void addBook(String title, String content) {
        bookCollection.put(title, content);
        knowledgeLevel += 10;
    }

    @Override
    public String toString() {
        return "MysticLibrary{" +
                "knowledgeLevel=" + knowledgeLevel +
                ", books=" + bookCollection.keySet() +
                ", base=" + base +
                '}';
    }
}

class DragonLair {
    private final String dragonType;
    private long treasureValue;
    private int territorialRadius;
    private final MagicalStructure base;

    public DragonLair(String name, String location, String dragonType) {
        this.base = new MagicalStructure(name, location, 600, true);
        this.dragonType = dragonType;
        this.treasureValue = 10000;
        this.territorialRadius = 50;
    }

    @Override
    public String toString() {
        return "DragonLair{" +
                "dragonType='" + dragonType + '\'' +
                ", treasureValue=" + treasureValue +
                ", territorialRadius=" + territorialRadius +
                ", base=" + base +
                '}';
    }
}

// ---------------- Kingdom Manager ----------------
class KingdomManager {
    private final List<Object> structures;
    private final KingdomConfig config;

    public KingdomManager(KingdomConfig config) {
        this.config = config;
        this.structures = new ArrayList<>();
    }

    public void addStructure(Object s) {
        structures.add(s);
    }

    public static boolean canStructuresInteract(Object s1, Object s2) {
        return (s1 instanceof WizardTower && s2 instanceof MysticLibrary) ||
               (s1 instanceof EnchantedCastle && s2 instanceof DragonLair);
    }

    public static String performMagicBattle(Object attacker, Object defender) {
        if (attacker instanceof WizardTower && defender instanceof DragonLair) {
            return "WizardTower defeats DragonLair with spells!";
        } else if (attacker instanceof DragonLair && defender instanceof EnchantedCastle) {
            return "DragonLair burns the castle!";
        }
        return "No battle occurs.";
    }

    public static int calculateKingdomPower(Object[] structures) {
        return structures.length * 100;
    }
}

// ---------------- Demo ----------------
public class KingdomDemo {
    public static void main(String[] args) {
        KingdomConfig config = KingdomConfig.createDefaultKingdom();
        System.out.println(config);

        WizardTower tower = new WizardTower("Sky Tower", "North Peak");
        EnchantedCastle castle = new EnchantedCastle("IronKeep", "Valley", "Royal");
        MysticLibrary library = new MysticLibrary("Grand Archive", "East Wing");
        DragonLair lair = new DragonLair("Smaug's Lair", "Mountain", "Fire Dragon");

        System.out.println(tower);
        System.out.println(castle);
        System.out.println(library);
        System.out.println(lair);

        KingdomManager km = new KingdomManager(config);
        km.addStructure(tower);
        km.addStructure(castle);

        System.out.println(KingdomManager.canStructuresInteract(tower, library));
        System.out.println(KingdomManager.performMagicBattle(tower, lair));
        System.out.println("Total Power: " + KingdomManager.calculateKingdomPower(new Object[]{tower, castle, library, lair}));
    }
}
