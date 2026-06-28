public class TaskManagementSystem {

    static class Task {
        int taskId;
        String taskName;
        String status;

        Task(int taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
        }

        @Override
        public String toString() {
            return "Task[id=" + taskId + ", name=" + taskName + ", status=" + status + "]";
        }
    }

    static class Node {
        Task data;
        Node next;

        Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node head = null;
    static int size = 0;

    static void addTask(int taskId, String taskName, String status) {
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.data.taskId == taskId) {
                System.out.println("Task ID " + taskId + " already exists.");
                return;
            }
        }
        Node newNode = new Node(new Task(taskId, taskName, status));
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
        System.out.println("Added: " + newNode.data);
    }

    static Task searchTask(int id) {
        Node curr = head;
        while (curr != null) {
            if (curr.data.taskId == id) {
                return curr.data;
            }
            curr = curr.next;
        }
        return null;
    }

    static void traverseAll() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        System.out.println("--- All Tasks (" + size + ") ---");
        Node curr = head;
        while (curr != null) {
            System.out.println("  " + curr.data);
            curr = curr.next;
        }
    }

    static boolean deleteTask(int id) {
        if (head == null) {
            System.out.println("Task ID " + id + " not found (list is empty).");
            return false;
        }
        if (head.data.taskId == id) {
            System.out.println("Deleted: " + head.data);
            head = head.next;
            size--;
            return true;
        }
        Node prev = head;
        Node curr = head.next;
        while (curr != null) {
            if (curr.data.taskId == id) {
                System.out.println("Deleted: " + curr.data);
                prev.next = curr.next;
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        System.out.println("Task ID " + id + " not found.");
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Task Management System (Singly Linked List) ===");

        addTask(1, "Design Database Schema", "Completed");
        addTask(2, "Build REST API", "In Progress");
        addTask(3, "Write Unit Tests", "Pending");
        addTask(4, "Deploy to Staging", "Pending");
        addTask(5, "Code Review", "In Progress");

        System.out.println();
        traverseAll();

        System.out.println("\n--- Search ---");
        Task found = searchTask(3);
        System.out.println("Search ID 3: " + (found != null ? found : "Not found"));

        found = searchTask(99);
        System.out.println("Search ID 99: " + (found != null ? found : "Not found"));

        System.out.println("\n--- Delete ---");
        deleteTask(1);
        deleteTask(3);
        deleteTask(99);

        System.out.println();
        traverseAll();

        System.out.println("\n--- Duplicate Add ---");
        addTask(2, "Duplicate Task", "Pending");

        System.out.println("\n=== Operations Summary ===");
        System.out.println("addTask    : O(n) - traverse to tail; O(1) duplicate check scan");
        System.out.println("searchTask : O(n) - traverse from head");
        System.out.println("deleteTask : O(n) - traverse to find node; O(1) pointer update");
        System.out.println("traverseAll: O(n) - visits all n nodes once");
        System.out.println("Space      : O(n) - one node per task");
    }
}
