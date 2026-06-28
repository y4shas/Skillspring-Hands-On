import java.util.Arrays;
import java.util.Comparator;

public class LibraryManagementSystem {

    static class Book {
        int bookId;
        String title;
        String author;

        Book(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        @Override
        public String toString() {
            return "Book[id=" + bookId + ", title=\"" + title + "\", author=" + author + "]";
        }
    }

    static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    static Book binarySearch(Book[] books, String title) {
        int low = 0;
        int high = books.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = books[mid].title.compareToIgnoreCase(title);
            if (cmp == 0) {
                return books[mid];
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== Library Management System ===");

        Book[] books = {
            new Book(1,  "The Great Gatsby",             "F. Scott Fitzgerald"),
            new Book(2,  "To Kill a Mockingbird",         "Harper Lee"),
            new Book(3,  "1984",                          "George Orwell"),
            new Book(4,  "Pride and Prejudice",           "Jane Austen"),
            new Book(5,  "The Catcher in the Rye",        "J.D. Salinger"),
            new Book(6,  "Brave New World",               "Aldous Huxley"),
            new Book(7,  "The Hobbit",                    "J.R.R. Tolkien"),
            new Book(8,  "Fahrenheit 451",                "Ray Bradbury"),
            new Book(9,  "Animal Farm",                   "George Orwell"),
            new Book(10, "Of Mice and Men",               "John Steinbeck")
        };

        Book[] sortedBooks = Arrays.copyOf(books, books.length);
        Arrays.sort(sortedBooks, Comparator.comparing(b -> b.title.toLowerCase()));

        System.out.println("\nLibrary catalogue (" + books.length + " books):");
        for (Book b : books) {
            System.out.println("  " + b);
        }

        String target1 = "The Hobbit";
        String target2 = "Dune";

        System.out.println("\n--- Linear Search for \"" + target1 + "\" ---");
        long start = System.nanoTime();
        Book result = linearSearch(books, target1);
        long linearTime = System.nanoTime() - start;
        System.out.println("Result : " + (result != null ? result : "Not found"));
        System.out.println("Time   : " + linearTime + " ns");

        System.out.println("\n--- Binary Search for \"" + target1 + "\" (sorted array) ---");
        start = System.nanoTime();
        result = binarySearch(sortedBooks, target1);
        long binaryTime = System.nanoTime() - start;
        System.out.println("Result : " + (result != null ? result : "Not found"));
        System.out.println("Time   : " + binaryTime + " ns");

        System.out.println("\n--- Searching for non-existent book: \"" + target2 + "\" ---");
        System.out.println("Linear Search : " + (linearSearch(books, target2) != null ? linearSearch(books, target2) : "Not found"));
        System.out.println("Binary Search : " + (binarySearch(sortedBooks, target2) != null ? binarySearch(sortedBooks, target2) : "Not found"));

        System.out.println("\n=== Search Algorithm Comparison ===");
        System.out.println("Linear Search : O(n) time | O(1) space | Works on unsorted data");
        System.out.println("Binary Search : O(log n) time | O(1) space | Requires sorted data");
        System.out.println("Sorting cost  : O(n log n) one-time cost to enable binary search");
        System.out.println("Recommendation: Use linear for small libraries; binary for large sorted catalogues");
    }
}
