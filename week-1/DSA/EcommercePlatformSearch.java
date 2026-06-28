import java.util.Arrays;
import java.util.Comparator;

public class EcommercePlatformSearch {

    static class Product {
        int productId;
        String productName;
        String category;

        Product(int productId, String productName, String category) {
            this.productId = productId;
            this.productName = productName;
            this.category = category;
        }

        @Override
        public String toString() {
            return "Product[id=" + productId + ", name=" + productName + ", category=" + category + "]";
        }
    }

    static int linearSearch(Product[] products, String name) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(Product[] products, String name) {
        int low = 0;
        int high = products.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = products[mid].productName.compareToIgnoreCase(name);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("=== E-commerce Platform Search ===");

        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Running Shoes", "Footwear"),
            new Product(3, "Blender", "Kitchen"),
            new Product(4, "Novel Book", "Books"),
            new Product(5, "Yoga Mat", "Sports"),
            new Product(6, "Headphones", "Electronics"),
            new Product(7, "Coffee Maker", "Kitchen")
        };

        String target = "Yoga Mat";

        System.out.println("\n--- Linear Search ---");
        long start = System.nanoTime();
        int linearResult = linearSearch(products, target);
        long linearTime = System.nanoTime() - start;

        if (linearResult != -1) {
            System.out.println("Found at index " + linearResult + ": " + products[linearResult]);
        } else {
            System.out.println("'" + target + "' not found via linear search.");
        }
        System.out.println("Time taken: " + linearTime + " ns");

        Product[] sortedProducts = Arrays.copyOf(products, products.length);
        Arrays.sort(sortedProducts, Comparator.comparing(p -> p.productName.toLowerCase()));

        System.out.println("\n--- Binary Search (sorted array) ---");
        start = System.nanoTime();
        int binaryResult = binarySearch(sortedProducts, target);
        long binaryTime = System.nanoTime() - start;

        if (binaryResult != -1) {
            System.out.println("Found at index " + binaryResult + ": " + sortedProducts[binaryResult]);
        } else {
            System.out.println("'" + target + "' not found via binary search.");
        }
        System.out.println("Time taken: " + binaryTime + " ns");

        System.out.println("\n--- Not Found Case ---");
        System.out.println("Linear search for 'Tablet': index " + linearSearch(products, "Tablet"));
        System.out.println("Binary search for 'Tablet': index " + binarySearch(sortedProducts, "Tablet"));

        System.out.println("\n=== Time Complexity Comparison ===");
        System.out.println("Linear Search : O(n) - checks each element sequentially");
        System.out.println("Binary Search : O(log n) - halves the search space each step");
        System.out.println("Prerequisite  : Binary search requires a sorted array");
        System.out.println("Best use case : Linear for small/unsorted data; Binary for large sorted data");
    }
}
