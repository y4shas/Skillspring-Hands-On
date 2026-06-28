import java.util.HashMap;

public class InventoryManagementSystem {

    static class Product {
        int productId;
        String productName;
        int quantity;
        double price;

        Product(int productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Product[id=" + productId + ", name=" + productName + ", qty=" + quantity + ", price=" + price + "]";
        }
    }

    static HashMap<Integer, Product> inventory = new HashMap<>();

    static void addProduct(int productId, String productName, int quantity, double price) {
        if (inventory.containsKey(productId)) {
            System.out.println("Product with ID " + productId + " already exists.");
            return;
        }
        inventory.put(productId, new Product(productId, productName, quantity, price));
        System.out.println("Added: " + inventory.get(productId));
    }

    static void updateProduct(int productId, int newQuantity, double newPrice) {
        if (!inventory.containsKey(productId)) {
            System.out.println("Product ID " + productId + " not found.");
            return;
        }
        Product p = inventory.get(productId);
        p.quantity = newQuantity;
        p.price = newPrice;
        System.out.println("Updated: " + p);
    }

    static void deleteProduct(int productId) {
        if (!inventory.containsKey(productId)) {
            System.out.println("Product ID " + productId + " not found.");
            return;
        }
        System.out.println("Deleted: " + inventory.remove(productId));
    }

    static void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("--- Inventory ---");
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Inventory Management System ===");

        addProduct(101, "Laptop", 50, 999.99);
        addProduct(102, "Mouse", 200, 25.49);
        addProduct(103, "Keyboard", 150, 45.00);
        addProduct(104, "Monitor", 75, 299.99);

        System.out.println();
        displayAll();

        System.out.println();
        updateProduct(102, 180, 22.99);
        updateProduct(999, 10, 5.00);

        System.out.println();
        deleteProduct(103);
        deleteProduct(999);

        System.out.println();
        displayAll();

        System.out.println();
        System.out.println("=== Time Complexity Analysis ===");
        System.out.println("addProduct    : O(1) average - HashMap put");
        System.out.println("updateProduct : O(1) average - HashMap get + field update");
        System.out.println("deleteProduct : O(1) average - HashMap remove");
        System.out.println("displayAll    : O(n)         - iterate over all n entries");
        System.out.println("Space         : O(n)         - n products stored in HashMap");
    }
}
