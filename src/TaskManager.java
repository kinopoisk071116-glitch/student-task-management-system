import java.util.ArrayList;

public class TaskManager {

    private ArrayList<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        tasks.add(task);
    }

    public void showTasks() {
        System.out.println("Task list:");
        for (String task : tasks) {
            System.out.println("- " + task);
        }
    }
}
