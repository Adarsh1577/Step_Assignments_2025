import java.time.LocalDateTime;
import java.util.*;

final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer,
                    double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || name == null || category == null || manufacturer == null || basePrice < 0 || weight < 0) {
            throw new IllegalArgumentException("Invalid product details");
        }
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features.clone();
        this.specifications = new HashMap<>(specifications);
    }

    public static Product createElectronics(String id, String name, String manufacturer, double price, double weight) {
        return new Product(id, name, "Electronics", manufacturer, price, weight,
                new String[]{"Warranty", "Battery"}, Map.of("Voltage", "220V", "Warranty", "2 years"));
    }

    public static Product createClothing(String id, String name, String manufacturer, double price, double weight) {
        return new Product(id, name, "Clothing", manufacturer, price, weight,
                new String[]{"Washable", "Comfortable"}, Map.of("Size", "M", "Fabric", "Cotton"));
    }

    public static Product createBooks(String id, String name, String manufacturer, double price, double weight) {
        return new Product(id, name, "Books", manufacturer, price, weight,
                new String[]{"Paperback", "English"}, Map.of("Pages", "350", "Genre", "Fiction"));
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    public final double calculateTax(String region) {
        switch (region.toUpperCase()) {
            case "US": return basePrice * 0.07;
            case "EU": return basePrice * 0.20;
            case "IN": return basePrice * 0.18;
            default: return basePrice * 0.10;
        }
    }

    @Override
    public String toString() {
        return String.format("Product[%s, %s, %.2f]", productId, name, basePrice);
    }
}

class Customer {
    private final String customerId;
    private final String email;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private final String accountCreationDate;

    public Customer(String customerId, String email, String name, String phone, String lang) {
        this.customerId = customerId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phone;
        this.preferredLanguage = lang;
        this.accountCreationDate = LocalDateTime.now().toString();
    }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    String getCreditRating() { return "Good"; } // package-private

    public String getPublicProfile() { return "Customer[name=" + name + ", language=" + preferredLanguage + "]"; }

    @Override
    public String toString() {
        return "Customer[id=" + customerId + ", email=" + email + ", name=" + name + "]";
    }
}

class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private List<Product> items;
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.items = new ArrayList<>();
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product) || quantity <= 0) return false;
        Product p = (Product) product;
        for (int i = 0; i < quantity; i++) {
            items.add(p);
        }
        itemCount += quantity;
        totalAmount += (p.getBasePrice() * quantity);
        return true;
    }

    private double calculateDiscount() {
        return totalAmount > 500 ? totalAmount * 0.1 : 0;
    }

    String getCartSummary() {
        return "Cart[items=" + itemCount + ", total=" + (totalAmount - calculateDiscount()) + "]";
    }

    @Override
    public String toString() {
        return getCartSummary();
    }
}

class Order {
    private final String orderId;
    private final LocalDateTime orderTime;

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }

    @Override
    public String toString() { return "Order[id=" + orderId + ", time=" + orderTime + "]"; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    public boolean processPayment(double amount) {
        return amount > 0;
    }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator() {
        this.shippingRates = new HashMap<>();
        shippingRates.put("US", 20.0);
        shippingRates.put("EU", 30.0);
        shippingRates.put("IN", 10.0);
    }

    public double calculate(String region, double weight) {
        return shippingRates.getOrDefault(region, 25.0) + (weight * 2);
    }
}

final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static boolean processOrder(Object order, Object customer) {
        return (order instanceof Order) && (customer instanceof Customer);
    }

    public static void addProduct(Product p) {
        productCatalog.put(p.getProductId(), p);
    }

    public static void showCatalog() {
        System.out.println("Catalog: " + productCatalog.values());
    }
}

public class Main {
    public static void main(String[] args) {
        Product laptop = Product.createElectronics("P001", "Laptop", "Dell", 800, 2.5);
        Product tshirt = Product.createClothing("P002", "T-Shirt", "Nike", 40, 0.5);
        Product novel = Product.createBooks("P003", "Novel", "Penguin", 15, 0.3);

        ECommerceSystem.addProduct(laptop);
        ECommerceSystem.addProduct(tshirt);
        ECommerceSystem.addProduct(novel);
        ECommerceSystem.showCatalog();

        Customer c1 = new Customer("C001", "john@example.com", "John Doe", "1234567890", "English");
        System.out.println(c1.getPublicProfile());

        ShoppingCart cart = new ShoppingCart("CART001", c1.getCustomerId());
        cart.addItem(laptop, 1);
        cart.addItem(tshirt, 2);
        cart.addItem(novel, 3);
        System.out.println(cart);

        Order order = new Order("O001");
        PaymentProcessor payment = new PaymentProcessor("PAY001", "SEC123");
        ShippingCalculator ship = new ShippingCalculator();

        if (ECommerceSystem.processOrder(order, c1) && payment.processPayment(1000)) {
            System.out.println("Order processed: " + order);
            System.out.println("Shipping cost: " + ship.calculate("IN", laptop.getWeight() + tshirt.getWeight() + novel.getWeight()));
        } else {
            System.out.println("Order failed");
        }
    }
}
