// Base class: Color
class Color {
    protected String name;

    // Constructor
    public Color(String name) {
        this.name = name;
        System.out.println("Color constructor called.");
    }

    public void displayColor() {
        System.out.println("Color Name: " + name);
    }
}

// Derived class: PrimaryColor (extends Color)
class PrimaryColor extends Color {
    protected int intensity; // percentage 0–100

    // Constructor
    public PrimaryColor(String name, int intensity) {
        super(name); // Calls Color constructor
        this.intensity = intensity;
        System.out.println("PrimaryColor constructor called.");
    }

    public void displayPrimaryColor() {
        displayColor();
        System.out.println("Intensity: " + intensity + "%");
    }
}

// Derived class: RedColor (extends PrimaryColor)
class RedColor extends PrimaryColor {
    private String shade; // e.g., "Light Red", "Dark Red"

    // Constructor
    public RedColor(String name, int intensity, String shade) {
        super(name, intensity); // Calls PrimaryColor constructor
        this.shade = shade;
        System.out.println("RedColor constructor called.");
    }

    public void displayRedColor() {
        displayPrimaryColor();
        System.out.println("Shade: " + shade);
    }
}

// Main class
public class ColorTest {
    public static void main(String[] args) {
        System.out.println("=== Creating RedColor Object ===");
        RedColor red = new RedColor("Red", 90, "Dark Red");

        System.out.println("\n=== Displaying RedColor Details ===");
        red.displayRedColor();
    }
}
