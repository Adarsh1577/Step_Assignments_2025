// 🍔 Food Delivery System
class FoodOrder {
    private String customerName;
    private String foodItem;
    private int quantity;
    private double price;

    private static final double FIXED_RATE = 150.0; // fixed price per item

    // 1. Default constructor → assigns "Unknown" order
    public FoodOrder() {
        this("Unknown", "Unknown", 0, 0.0);
    }

    // 2. Constructor with food item → sets quantity = 1, price = default
    public FoodOrder(String foodItem) {
        this("Customer", foodItem, 1, FIXED_RATE);
    }

    // 3. Constructor with food item and quantity → price = quantity × fixedRate
    public FoodOrder(String foodItem, int quantity) {
        this("Customer", foodItem, quantity, quantity * FIXED_RATE);
    }

    // Full constructor (used internally)
    public FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    // Method to print bill
    public void printBill() {
        System.out.println("\n--- FOOD ORDER BILL ---");
        System.out.println("Customer : " + customerName);
        System.out.println("Food Item: " + foodItem);
        System.out.println("Quantity : " + quantity);
        System.out.println("Total Price: ₹" + price);
    }
}

// === MAIN CLASS ===
public class FoodDeliverySystem {
    public static void main(String[] args) {
        System.out.println("=== FOOD DELIVERY SYSTEM ===");

        // Different types of orders
        FoodOrder order1 = new FoodOrder();                     // Default order
        FoodOrder order2 = new FoodOrder("Burger");              // Single burger
        FoodOrder order3 = new FoodOrder("Pizza", 3);            // 3 pizzas

        // Print bills
        order1.printBill();
        order2.printBill();
        order3.printBill();
    }
}
