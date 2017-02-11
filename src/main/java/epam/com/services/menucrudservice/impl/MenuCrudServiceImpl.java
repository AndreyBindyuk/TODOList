package epam.com.services.menucrudservice.impl;

import epam.com.entities.Task;
import epam.com.exceptions.TaskServiceValidationException;
import epam.com.services.menucrudservice.MenuCrudService;
import epam.com.services.taskservice.TaskService;
import epam.com.services.taskservice.impl.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuCrudServiceImpl implements MenuCrudService {
    private static final String taskManagerMenu = "taskManagerMenu.txt";
    private TaskService taskService = new TaskServiceImpl();
    private Task task = new Task();
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void menuCrudOperations() throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(taskManagerMenu)), StandardCharsets.UTF_8);
        System.out.println(text);
        while (true) {
            String input = br.readLine();
            switch (input) {
                case "1":
                    updateFile(input);
                    System.out.println(text);
                    break;
                case "2":
                    showAllTasks();
                    System.out.println(text);
                    break;
                case "3":
                    showSpecificTaskInfo(input, text);
                    break;
                case "4":
                    removeAllTasks();
                    System.out.println(text);
                    break;
                case "5":
                    removeSpecificTask(input);
                    System.out.println(text);
                    break;
            }
        }
    }

    private void updateFile(String input) throws IOException {
        System.out.println("Enter Task Name: ");
        input = br.readLine();
        task.setId(input);
        System.out.println("Enter Task Description: ");
        input = br.readLine();
        task.setDescription(input);
        System.out.println("Enter Task Status: ");
        input = br.readLine();
        try {
            if (taskService.updateFile(task, input)) {
                System.out.println("file has been updated");
            }

        } catch (TaskServiceValidationException e) {
            e.printStackTrace();
        }
    }

    private void showAllTasks() {
        try {
            System.out.println(taskService.getAllTaskInfo());
        } catch (TaskServiceValidationException e) {
            e.printStackTrace();
        }
    }

    private void showSpecificTaskInfo(String input, String text) throws IOException {
        System.out.println("Enter task id: ");
        input = br.readLine();
        try {
            System.out.println(taskService.getSpecificTaskInfo(input));
            System.out.println(text);
        } catch (TaskServiceValidationException e) {
            e.printStackTrace();
        }
    }

    private void removeSpecificTask(String input) throws IOException {
        System.out.println("Enter task id: ");
        input = br.readLine();
        try {
            if (taskService.removeSpecificTask(input)) {
                System.out.println(input + " - task has been removed");
            }
        } catch (TaskServiceValidationException e) {
            e.printStackTrace();
        }
    }

    private void removeAllTasks() {
        if (taskService.removeAllTasks()) {
            System.out.println("All tasks have been removed");
        }
    }
}
