import java.util.Scanner;

class Product {
    private String productId;
    private String productName;
    private double price;
    private String category;
    private int stockQuantity;

    static int totalProducts = 0;
    static String[] categories = {"Electronics", "Clothing", "Books", "Groceries"};

    public Product(String productName, double price, String category, int stockQuantity) {
        this.productId = "P" + (++totalProducts);
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }

    public void reduceStock(int qty) { stockQuantity -= qty; }
    public void increaseStock(int qty) { stockQuantity += qty; }

    public void displayProduct() {
        System.out.printf("%-5s %-20s %-12s %-10.2f %-10d\n", 
                          productId, productName, category, price, stockQuantity);
    }

    public static Product findProductById(Product[] products, String productId) {
        for (Product p : products) {
            if (p != null && p.getProductId().equals(productId)) return p;
        }
        return null;
    }

    public static void getProductsByCategory(Product[] products, String category) {
        System.out.println("\nProducts in Category: " + category);
        System.out.printf("%-5s %-20s %-12s %-10s %-10s\n", "ID", "Name", "Category", "Price", "Stock");
        for (Product p : products) {
            if (p != null && p.getCategory().equalsIgnoreCase(category)) {
                p.displayProduct();
            }
        }
    }
}

class ShoppingCart {
    private String cartId;
    private String customerName;
    private Product[] products;
    private int[] quantities;
    private double cartTotal;
    private int itemCount;

    public ShoppingCart(String customerName) {
        this.cartId = "CART" + System.currentTimeMillis() % 10000;
        this.customerName = customerName;
        this.products = new Product[50];
        this.quantities = new int[50];
        this.cartTotal = 0.0;
        this.itemCount = 0;
    }

    public void addProduct(Product product, int quantity) {
        if (product.getStockQuantity() < quantity) {
            System.out.println("Not enough stock available!");
            return;
        }
        products[itemCount] = product;
        quantities[itemCount] = quantity;
        product.reduceStock(quantity);
        itemCount++;
        calculateTotal();
        System.out.println(quantity + " " + product.getProductName() + "(s) added to cart.");
    }

    public void removeProduct(String productId) {
        for (int i = 0; i < itemCount; i++) {
            if (products[i].getProductId().equals(productId)) {
                products[i].increaseStock(quantities[i]);
                System.out.println(products[i].getProductName() + " removed from cart.");
                for (int j = i; j < itemCount - 1; j++) {
                    products[j] = products[j + 1];
                    quantities[j] = quantities[j + 1];
                }
                itemCount--;
                calculateTotal();
                return;
            }
        }
        System.out.println("Product not found in cart!");
    }

    public void calculateTotal() {
        cartTotal = 0.0;
        for (int i = 0; i < itemCount; i++) {
            cartTotal += products[i].getPrice() * quantities[i];
        }
    }

    public void displayCart() {
        System.out.println("\nShopping Cart for " + customerName);
        if (itemCount == 0) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.printf("%-5s %-20s %-10s %-10s\n", "ID", "Name", "Qty", "Price");
        for (int i = 0; i < itemCount; i++) {
            System.out.printf("%-5s %-20s %-10d %-10.2f\n",
                              products[i].getProductId(), products[i].getProductName(),
                              quantities[i], products[i].getPrice() * quantities[i]);
        }
        System.out.println("Cart Total: " + cartTotal);
    }

    public void checkout() {
        System.out.println("\n===== Checkout Summary =====");
        displayCart();
        System.out.println("Thank you for shopping, " + customerName + "!");
    }
}

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = {
            new Product("Laptop", 55000, "Electronics", 5),
            new Product("Smartphone", 30000, "Electronics", 10),
            new Product("T-Shirt", 500, "Clothing", 20),
            new Product("Jeans", 1200, "Clothing", 15),
            new Product("Novel", 300, "Books", 25),
            new Product("Notebook", 50, "Books", 100),
            new Product("Rice", 40, "Groceries", 200),
            new Product("Oil", 150, "Groceries", 100),
            new Product("Milk", 50, "Groceries", 50),
            new Product("Headphones", 2000, "Electronics", 8)
        };

        System.out.print("Enter your name: ");
        String customerName = sc.nextLine();
        ShoppingCart cart = new ShoppingCart(customerName);

        int choice;
        do {
            System.out.println("\n===== Online Shopping Menu =====");
            System.out.println("1. View All Products");
            System.out.println("2. Search Product by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.printf("%-5s %-20s %-12s %-10s %-10s\n", "ID", "Name", "Category", "Price", "Stock");
                    for (Product p : products) {
                        if (p != null) p.displayProduct();
                    }
                    break;

                case 2:
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    Product.getProductsByCategory(products, category);
                    break;

                case 3:
                    System.out.print("Enter product ID to add: ");
                    String pid = sc.nextLine();
                    Product found = Product.findProductById(products, pid);
                    if (found != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(found, qty);
                    } else {
                        System.out.println("Product not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter product ID to remove: ");
                    String rid = sc.nextLine();
                    cart.removeProduct(rid);
                    break;

                case 5:
                    cart.displayCart();
                    break;

                case 6:
                    cart.checkout();
                    break;

                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);

        sc.close();
    }
}
