package com.main;

import com.dao.DatabaseConnection;
import com.services.TaskManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Создаем таблицу при запуске
        DatabaseConnection.createTable();

        TaskManager manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== Student Task Management System ===");
            System.out.println("1. Add Task");
            System.out.println("2. Show Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Update Task");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice;

            // Защита от неправильного ввода
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // очистка буфера
            } else {
                System.out.println("Invalid input. Enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine().trim();

                    if (title.isEmpty()) {
                        System.out.println("Task title cannot be empty.");
                    } else {
                        manager.addTask(title);
                    }
                    break;

                case 2:
                    manager.showTasks();
                    break;

                case 3:
                    System.out.print("Enter task ID to delete: ");

                    if (scanner.hasNextInt()) {
                        int deleteId = scanner.nextInt();
                        scanner.nextLine();
                        manager.deleteTask(deleteId);
                    } else {
                        System.out.println("Invalid ID.");
                        scanner.nextLine();
                    }
                    break;

                case 4:
                    System.out.print("Enter task ID to update: ");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Invalid ID.");
                        scanner.nextLine();
                        break;
                    }

                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter new status (NEW / DONE): ");
                    String status = scanner.nextLine().trim().toUpperCase();

                    if (!status.equals("NEW") && !status.equals("DONE")) {
                        System.out.println("Invalid status. Use NEW or DONE.");
                    } else {
                        manager.updateTask(updateId, status);
                    }
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
