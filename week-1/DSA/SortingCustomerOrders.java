import java.util.Arrays;

public class SortingCustomerOrders {

    static class Order {
        int orderId;
        String customerName;
        double totalPrice;

        Order(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        @Override
        public String toString() {
            return "Order[id=" + orderId + ", customer=" + customerName + ", total=$" + totalPrice + "]";
        }
    }

    static Order[] copyOrders(Order[] original) {
        Order[] copy = new Order[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new Order(original[i].orderId, original[i].customerName, original[i].totalPrice);
        }
        return copy;
    }

    static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    static void printOrders(Order[] orders) {
        for (Order o : orders) {
            System.out.println("  " + o);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Sorting Customer Orders ===");

        Order[] original = {
            new Order(1001, "Alice",   149.99),
            new Order(1002, "Bob",     599.00),
            new Order(1003, "Charlie",  49.50),
            new Order(1004, "Diana",   320.75),
            new Order(1005, "Eve",     899.99),
            new Order(1006, "Frank",   215.00),
            new Order(1007, "Grace",    75.25)
        };

        System.out.println("\nOriginal Orders:");
        printOrders(original);

        Order[] bubbleOrders = copyOrders(original);
        long start = System.nanoTime();
        bubbleSort(bubbleOrders);
        long bubbleTime = System.nanoTime() - start;

        System.out.println("\nAfter Bubble Sort (ascending by totalPrice):");
        printOrders(bubbleOrders);
        System.out.println("Time taken: " + bubbleTime + " ns");

        Order[] quickOrders = copyOrders(original);
        start = System.nanoTime();
        quickSort(quickOrders, 0, quickOrders.length - 1);
        long quickTime = System.nanoTime() - start;

        System.out.println("\nAfter Quick Sort (ascending by totalPrice):");
        printOrders(quickOrders);
        System.out.println("Time taken: " + quickTime + " ns");

        System.out.println("\n=== Sorting Algorithm Comparison ===");
        System.out.println("Bubble Sort : Time O(n^2) worst/avg | O(n) best (nearly sorted) | Space O(1)");
        System.out.println("Quick Sort  : Time O(n log n) avg   | O(n^2) worst (bad pivot)  | Space O(log n)");
        System.out.println("Preferred   : Quick Sort for large datasets due to better average performance");
        System.out.println("Stable Sort : Bubble Sort is stable; Quick Sort (this impl) is not stable");
    }
}
