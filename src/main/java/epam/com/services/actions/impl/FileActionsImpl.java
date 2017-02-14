package epam.com.services.actions.impl;

import epam.com.entities.Task;
import epam.com.exceptions.TaskServiceValidationException;
import epam.com.services.actions.FileActions;
import epam.com.services.taskservice.TaskService;
import epam.com.services.taskservice.impl.TaskServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActionsImpl implements FileActions {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private TaskService taskService = new TaskServiceImpl();


    @Override
    public void updateFile() throws IOException {
        Task task = new Task();
        System.out.println("Enter Task Name: ");
        String input = br.readLine();
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
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showAllTasks() {
        try {
            System.out.println(taskService.getAllTaskInfo());
        } catch (TaskServiceValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showSpecificTaskInfo() throws IOException {
        System.out.println("Enter task id: ");
        String input = br.readLine();
        try {
            System.out.println(taskService.getSpecificTaskInfo(input));
        } catch (TaskServiceValidationException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeAllTasks() {
        if (taskService.removeAllTasks()) {
            System.out.println("All tasks have been removed");
        }
    }

    @Override
    public void removeSpecificTask() throws IOException {
        System.out.println("Enter task id: ");
        String input = br.readLine();
        try {
            if (taskService.removeSpecificTask(input)) {
                System.out.println(input + " - task has been removed");
            }
        } catch (TaskServiceValidationException e) {
            System.out.println(e.getMessage());

        }

    }
}
