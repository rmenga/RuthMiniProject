package br.com.ruth;

import br.com.ruth.dao.TaskArrayListDao;
import br.com.ruth.dao.TaskDao;
import br.com.ruth.exceptions.TaskNotFoundException;
import br.com.ruth.model.Task;
import br.com.ruth.service.TaskRegistrationService;
import br.com.ruth.service.TaskTransitionStatusService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TaskRegistrationService taskRegistrationService;
    private static TaskTransitionStatusService taskTransitionStatusService;

    public static void main(String[] args) {
        TaskDao taskDao = new TaskArrayListDao();
        taskRegistrationService = new TaskRegistrationService(taskDao);
        taskTransitionStatusService = new TaskTransitionStatusService(taskRegistrationService, taskDao);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Management");
            System.out.println("1. Create Task");
            System.out.println("2. Update Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Complete Task");
            System.out.println("5. Cancel Task");
            System.out.println("6. Delete Task");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> createTask(scanner);
                    case 2 -> updateTask(scanner);
                    case 3 -> listTasks();
                    case 4 -> completeTask(scanner);
                    case 5 -> cancelTask(scanner);
                    case 6 -> deleteTask(scanner);
                    case 0 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (TaskNotFoundException e) {
                scanner.nextLine();
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Error: Digite um integer");
            }
            catch (Exception e) {
                scanner.nextLine();
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void createTask(Scanner scanner) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        Task task = new Task(name, description);
        Task savedTask = taskRegistrationService.createTask(task);

        System.out.println("Task created with ID: " + savedTask.getId());
    }

    private static void updateTask(Scanner scanner) {
        System.out.print("Enter task ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // consumir nova linha
        System.out.print("Enter new task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String description = scanner.nextLine();

        Task updatedTask = new Task(name, description);
        Task task = taskRegistrationService.updateTask(id, updatedTask);

        System.out.println("Task updated: " + task.getId());
    }

    private static void listTasks() {
        List<Task> tasks = taskRegistrationService.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            tasks.forEach(task -> System.out.println(task.getId() + ": " + task.getName() + " - " + task.getStatus()));
        }
    }

    private static void completeTask(Scanner scanner) {
        System.out.print("Enter task ID to complete: ");
        Long id = scanner.nextLong();

        taskTransitionStatusService.complete(id);
        System.out.println("Task completed.");
    }

    private static void cancelTask(Scanner scanner) {
        System.out.print("Enter task ID to cancel: ");
        Long id = scanner.nextLong();

        taskTransitionStatusService.cancel(id);
        System.out.println("Task canceled.");
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Enter task ID to delete: ");
        Long id = scanner.nextLong();

        taskRegistrationService.deleteTask(id);
        System.out.println("Task deleted.");
    }
}