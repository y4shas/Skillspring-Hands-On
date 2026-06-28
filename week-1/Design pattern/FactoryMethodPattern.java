public class FactoryMethodPattern {

    public static void main(String[] args) {

        System.out.println("Exercise 2: Implementing the Factory Method Pattern");
        System.out.println("Scenario:");
        System.out.println("You are developing a document management system that needs to create different types of documents (e.g., Word, PDF, Excel). Use the Factory Method Pattern to achieve this.");
        System.out.println("Steps:");
        System.out.println("Create a New Java Project:");
        System.out.println("Create a new Java project named FactoryMethodPatternExample.");
        System.out.println("Define Document Classes:");
        System.out.println("Create interfaces or abstract classes for different document types such as WordDocument, PdfDocument, and ExcelDocument.");
        System.out.println("Create Concrete Document Classes:");
        System.out.println("Implement concrete classes for each document type that implements or extends the above interfaces or abstract classes.");
        System.out.println("Implement the Factory Method:");
        System.out.println("Create an abstract class DocumentFactory with a method createDocument().");
        System.out.println("Create concrete factory classes for each document type that extends DocumentFactory and implements the createDocument() method.");
        System.out.println("Test the Factory Method Implementation:");
        System.out.println("Create a test class to demonstrate the creation of different document types using the factory method.");

    }

}
