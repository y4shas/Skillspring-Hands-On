public class DependencyInjection {

    interface CustomerRepository {
        String findCustomerById(int id);
    }

    static class CustomerRepositoryImpl implements CustomerRepository {
        public String findCustomerById(int id) {
            if (id == 1) return "Alice Johnson (ID: 1, Tier: Gold)";
            if (id == 2) return "Bob Smith (ID: 2, Tier: Silver)";
            if (id == 3) return "Carol Williams (ID: 3, Tier: Platinum)";
            return "Customer not found for ID: " + id;
        }
    }

    static class CustomerService {
        private CustomerRepository customerRepository;

        public CustomerService(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public void printCustomerInfo(int id) {
            String customer = customerRepository.findCustomerById(id);
            System.out.println("Customer Info -> " + customer);
        }

        public boolean customerExists(int id) {
            String result = customerRepository.findCustomerById(id);
            return !result.startsWith("Customer not found");
        }
    }

    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        System.out.println("Looking up customers:");
        service.printCustomerInfo(1);
        service.printCustomerInfo(2);
        service.printCustomerInfo(3);
        service.printCustomerInfo(99);

        System.out.println("Existence check for ID 2: " + service.customerExists(2));
        System.out.println("Existence check for ID 99: " + service.customerExists(99));
    }
}
