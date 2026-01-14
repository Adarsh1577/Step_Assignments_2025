// Parent class: Fruit
class Fruit {
    protected String color;
    protected String taste;

    // Constructor for Fruit
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Method to display Fruit details
    public void displayFruit() {
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }
}

// Child class: Apple
class Apple extends Fruit {
    private String variety;

    // Constructor for Apple
    public Apple(String color, String taste, String variety) {
        super(color, taste);   // Call parent constructor
        this.variety = variety;
    }

    // Method to display Apple details
    public void displayApple() {
        displayFruit(); // Accessing inherited method
        System.out.println("Variety: " + variety);
    }
}

// Main class
public class FruitTest {
    public static void main(String[] args) {
        // Create Apple object
        Apple myApple = new Apple("Red", "Sweet", "Fuji");

        // Access and display fields
        myApple.displayApple();
    }
}
