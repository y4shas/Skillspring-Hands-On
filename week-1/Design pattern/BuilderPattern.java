public class BuilderPattern {

    public static void main(String[] args) {

        System.out.println("Exercise 3: Implementing the Builder Pattern");
        System.out.println("Scenario:");
        System.out.println("You are developing a system to create complex objects such as a Computer with multiple optional parts. Use the Builder Pattern to manage the construction process.");
        System.out.println("Steps:");
        System.out.println("Create a New Java Project:");
        System.out.println("Create a new Java project named BuilderPatternExample.");
        System.out.println("Define a Product Class:");
        System.out.println("Create a class Computer with attributes like CPU, RAM, Storage, etc.");
        System.out.println("Implement the Builder Class:");
        System.out.println("Create a static nested Builder class inside Computer with methods to set each attribute.");
        System.out.println("Provide a build() method in the Builder class that returns an instance of Computer.");
        System.out.println("Implement the Builder Pattern:");
        System.out.println("Ensure that the Computer class has a private constructor that takes the Builder as a parameter.");
        System.out.println("Test the Builder Implementation:");
        System.out.println("Create a test class to demonstrate the creation of different configurations of Computer using the Builder pattern.");

    }

}
