package main;

import entities.Task;
import enums.Status;
import services.TaskService;
import services.impl.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    private static final String taskManagerMenu = "taskManagerMenu.txt";


    public static void main(String[] args) throws IOException {
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = new String(Files.readAllBytes(Paths.get(taskManagerMenu)), StandardCharsets.UTF_8);
        System.out.println(text);

        while (true) {
            String input = br.readLine();
            switch (input) {
                case "1":
                    System.out.println("Enter Task Name: ");
                    input = br.readLine();
                    task.setId(input);
                    System.out.println("Enter Task Description: ");
                    input = br.readLine();
                    task.setDescription(input);
                    System.out.println("Enter Task Status: ");
                    input = br.readLine();
                    try {
                        task.setStatus(Status.valueOf(input));
                        taskService.updateFile(task);

                    } catch (Exception e) {
                        System.out.println("status is incorrect! status should has following values:  " + Arrays.toString(Status.class.getEnumConstants()));
                    }
                    System.out.println(text);
                    break;
                case "2":
                    System.out.println(taskService.getAllTaskInfo());
                    System.out.println(text);
                    break;
                case "3":
                    System.out.println("Enter task id: ");
                    input = br.readLine();
                    System.out.println(taskService.getSpecificTaskInfo(input));
                    System.out.println(text);
                    break;
                case "4":
                    if (taskService.removeAllTasks()) {
                        System.out.println("All tasks have been removed");
                    }
                    System.out.println(text);
                    break;
                case "5":
                    System.out.println("Enter task id: ");
                    input = br.readLine();
                    if (taskService.removeSpecificTask(input)) {
                        System.out.println(input + " - task has been removed");
                    }
                    System.out.println(text);
                    break;
            }
        }
    }
}
