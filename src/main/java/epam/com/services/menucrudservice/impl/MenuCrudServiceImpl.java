package epam.com.services.menucrudservice.impl;

import epam.com.services.actions.FileActions;
import epam.com.services.actions.impl.FileActionsImpl;
import epam.com.services.menucrudservice.MenuCrudService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuCrudServiceImpl implements MenuCrudService {
    private static final String taskManagerMenu = "taskManagerMenu.txt";
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private FileActions fileActions = new FileActionsImpl();

    @Override
    public void menuCrudOperations() throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(taskManagerMenu)), StandardCharsets.UTF_8);
        System.out.println(text);
        while (true) {
            String input = br.readLine();
            switch (input) {
                case "1":
                    fileActions.updateFile();
                    System.out.println(text);
                    break;
                case "2":
                    fileActions.showAllTasks();
                    System.out.println(text);
                    break;
                case "3":
                    fileActions.showSpecificTaskInfo();
                    System.out.println(text);
                    break;
                case "4":
                    fileActions.removeAllTasks();
                    System.out.println(text);
                    break;
                case "5":
                    fileActions.removeSpecificTask();
                    System.out.println(text);
                    break;
            }
        }
    }

}
