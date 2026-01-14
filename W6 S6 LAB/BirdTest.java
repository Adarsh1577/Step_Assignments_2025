// Parent class: Bird
class Bird {
    public void fly() {
        System.out.println("Bird is flying in a general way.");
    }
}

// Child class: Penguin
class Penguin extends Bird {
    @Override
    public void fly() {
        System.out.println("Penguin cannot fly, it swims instead.");
    }
}

// Child class: Eagle
class Eagle extends Bird {
    @Override
    public void fly() {
        System.out.println("Eagle soars high in the sky.");
    }
}

// Main class
public class BirdTest {
    public static void main(String[] args) {
        // Array of Bird references (polymorphism)
        Bird[] birds = new Bird[3];
        birds[0] = new Bird();
        birds[1] = new Penguin();
        birds[2] = new Eagle();

        // Loop to test polymorphic behavior
        System.out.println("=== Bird Flying Behavior ===");
        for (Bird b : birds) {
            b.fly(); // Dynamic method dispatch
        }
    }
}
