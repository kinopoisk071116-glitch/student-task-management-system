public class Main {
    public static void main(String[] args) {
        System.out.println("Student Task Management System");

        TaskManager manager = new TaskManager();

        manager.addTask("Study Java");
        manager.addTask("Prepare OOP assignment");

        manager.showTasks();
    }
}
