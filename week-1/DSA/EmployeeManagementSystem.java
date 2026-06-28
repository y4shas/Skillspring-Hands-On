public class EmployeeManagementSystem {

    static class Employee {
        int employeeId;
        String name;
        String position;
        double salary;

        Employee(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee[id=" + employeeId + ", name=" + name + ", position=" + position + ", salary=" + salary + "]";
        }
    }

    static final int MAX_SIZE = 10;
    static Employee[] employees = new Employee[MAX_SIZE];
    static int count = 0;

    static boolean addEmployee(int employeeId, String name, String position, double salary) {
        if (count >= MAX_SIZE) {
            System.out.println("Storage full. Cannot add employee " + name + ".");
            return false;
        }
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == employeeId) {
                System.out.println("Employee ID " + employeeId + " already exists.");
                return false;
            }
        }
        employees[count] = new Employee(employeeId, name, position, salary);
        count++;
        System.out.println("Added: " + employees[count - 1]);
        return true;
    }

    static Employee searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                return employees[i];
            }
        }
        return null;
    }

    static void traverseAll() {
        if (count == 0) {
            System.out.println("No employees on record.");
            return;
        }
        System.out.println("--- All Employees (" + count + ") ---");
        for (int i = 0; i < count; i++) {
            System.out.println("  " + employees[i]);
        }
    }

    static boolean deleteEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                System.out.println("Deleted: " + employees[i]);
                employees[i] = employees[count - 1];
                employees[count - 1] = null;
                count--;
                return true;
            }
        }
        System.out.println("Employee ID " + id + " not found.");
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===");

        addEmployee(201, "Alice Johnson", "Engineer", 85000.00);
        addEmployee(202, "Bob Smith", "Manager", 105000.00);
        addEmployee(203, "Carol White", "Designer", 72000.00);
        addEmployee(204, "David Brown", "Analyst", 68000.00);
        addEmployee(205, "Eve Davis", "Engineer", 90000.00);

        System.out.println();
        traverseAll();

        System.out.println("\n--- Search ---");
        Employee found = searchEmployee(203);
        System.out.println("Search ID 203: " + (found != null ? found : "Not found"));

        found = searchEmployee(999);
        System.out.println("Search ID 999: " + (found != null ? found : "Not found"));

        System.out.println("\n--- Delete ---");
        deleteEmployee(202);
        deleteEmployee(999);

        System.out.println();
        traverseAll();

        System.out.println("\n--- Duplicate Add ---");
        addEmployee(201, "Duplicate Alice", "HR", 60000.00);

        System.out.println("\n=== Operations Summary ===");
        System.out.println("addEmployee    : O(n) - scans for duplicate ID before inserting");
        System.out.println("searchEmployee : O(n) - linear scan through array");
        System.out.println("deleteEmployee : O(n) - linear scan; swap-with-last deletion O(1)");
        System.out.println("traverseAll    : O(n) - visits every element once");
        System.out.println("Space          : O(1) - fixed-size array of MAX_SIZE=" + MAX_SIZE);
    }
}
