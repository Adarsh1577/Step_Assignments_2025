// Base class: Instrument
class Instrument {
    protected String name;
    protected String material;

    // Constructor
    public Instrument(String name, String material) {
        this.name = name;
        this.material = material;
    }

    // Method to display common info
    public void displayInfo() {
        System.out.println("Instrument: " + name);
        System.out.println("Material: " + material);
    }

    // Method to play sound (will be overridden)
    public void play() {
        System.out.println("Playing some generic instrument sound...");
    }
}

// Child class: Piano
class Piano extends Instrument {
    private int numberOfKeys;

    public Piano(String name, String material, int numberOfKeys) {
        super(name, material);
        this.numberOfKeys = numberOfKeys;
    }

    @Override
    public void play() {
        System.out.println("Piano is playing melodious tunes.");
    }

    public void displayPiano() {
        displayInfo();
        System.out.println("Number of Keys: " + numberOfKeys);
    }
}

// Child class: Guitar
class Guitar extends Instrument {
    private int numberOfStrings;

    public Guitar(String name, String material, int numberOfStrings) {
        super(name, material);
        this.numberOfStrings = numberOfStrings;
    }

    @Override
    public void play() {
        System.out.println("Guitar is strumming chords.");
    }

    public void displayGuitar() {
        displayInfo();
        System.out.println("Number of Strings: " + numberOfStrings);
    }
}

// Child class: Drum
class Drum extends Instrument {
    private String type; // e.g., Bass Drum, Snare Drum

    public Drum(String name, String material, String type) {
        super(name, material);
        this.type = type;
    }

    @Override
    public void play() {
        System.out.println("Drum is producing rhythmic beats.");
    }

    public void displayDrum() {
        displayInfo();
        System.out.println("Drum Type: " + type);
    }
}

// Main class
public class InstrumentTest {
    public static void main(String[] args) {
        // Array of Instrument references (polymorphism)
        Instrument[] instruments = new Instrument[3];
        instruments[0] = new Piano("Grand Piano", "Wood", 88);
        instruments[1] = new Guitar("Acoustic Guitar", "Wood", 6);
        instruments[2] = new Drum("Snare Drum", "Metal", "Snare");

        System.out.println("=== Instrument Family ===");
        for (Instrument inst : instruments) {
            inst.displayInfo(); // Shows common info
            inst.play();        // Calls overridden method
            System.out.println("-----------------------");
        }
    }
}
