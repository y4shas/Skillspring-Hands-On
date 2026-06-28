public class DependencyInjection {

    public static void main(String[] args) {

        System.out.println("Exercise 11: Implementing Dependency Injection");
        System.out.println("Scenario:");
        System.out.println("You are developing a customer management application where the service class depends on a repository class. Use Dependency Injection to manage these dependencies.");
        System.out.println("Steps:");
        System.out.println("Create a New Java Project:");
        System.out.println("Create a new Java project named DependencyInjectionExample.");
        System.out.println("Define Repository Interface:");
        System.out.println("Create an interface CustomerRepository with methods like findCustomerById().");
        System.out.println("Implement Concrete Repository:");
        System.out.println("Create a class CustomerRepositoryImpl that implements CustomerRepository.");
        System.out.println("Define Service Class:");
        System.out.println("Create a class CustomerService that depends on CustomerRepository.");
        System.out.println("Implement Dependency Injection:");
        System.out.println("Use constructor injection to inject CustomerRepository into CustomerService.");
        System.out.println("Test the Dependency Injection Implementation:");
        System.out.println("Create a main class to demonstrate creating a CustomerService with CustomerRepositoryImpl and using it to find a customer.");

    }

}
