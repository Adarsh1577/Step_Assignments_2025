// Parent class: Box
class Box {
    public void pack() {
        System.out.println("Packing the box with standard materials.");
    }

    public void unpack() {
        System.out.println("Unpacking the box safely.");
    }
}

// Child class: GiftBox
class GiftBox extends Box {
    @Override
    public void pack() {
        super.pack(); // Call parent version first
        System.out.println("Adding colorful wrapping paper and ribbons for the gift.");
    }

    @Override
    public void unpack() {
        super.unpack(); // Call parent version first
        System.out.println("Removing decorative wrapping before accessing the gift.");
    }
}

// Main class
public class GiftBoxTest {
    public static void main(String[] args) {
        System.out.println("=== Regular Box ===");
        Box b = new Box();
        b.pack();
        b.unpack();

        System.out.println("\n=== Gift Box ===");
        GiftBox gb = new GiftBox();
        gb.pack();
        gb.unpack();
    }
}
