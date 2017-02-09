package services.impl;

import entities.Task;
import services.TaskService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TaskServiceImpl implements TaskService {
    private static final String tasksData = "tasksData.properties";


    public void updateFile(Task task) {
        Properties props = new Properties();
        try {
            String text = getResourceContent();
            Writer output = new BufferedWriter(new FileWriter(tasksData, true));
            if (!text.contains(task.getId())) {
                props.setProperty(task.getId(), task.toString());
                props.store(output, null);
            }
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Map.Entry<Object, Object>> getAllTaskInfo() {
        Properties props = new Properties();
        try {
            InputStream input = new FileInputStream(tasksData);
            props.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.entrySet();
    }


    public String getSpecificTaskInfo(String id) {
        String getSpecificTask = null;
        Properties props = new Properties();
        try {
            String text = getResourceContent();
            InputStream input = new FileInputStream(tasksData);
            props.load(input);
            if (text.contains(id)) {
                getSpecificTask = props.getProperty(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getSpecificTask;
    }


    public boolean removeSpecificTask(String id) {
        Properties props = new Properties();
        Boolean isremoved = false;
        try {
            String text = getResourceContent();
            InputStream input = new FileInputStream(tasksData);
            props.load(input);
            if (text.contains(id)) {
                props.remove(id);
                FileOutputStream out = new FileOutputStream(tasksData);
                props.store(out, null);
                isremoved = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isremoved;
    }


    public boolean removeAllTasks() {
        Properties props = new Properties();
        Boolean isremoved = false;
        try {
            InputStream input = new FileInputStream(tasksData);
            props.load(input);
            props.clear();
            FileOutputStream out = new FileOutputStream(tasksData);
            props.store(out, null);
            isremoved = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isremoved;
    }

    private String getResourceContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(tasksData)), StandardCharsets.UTF_8);
    }


//    public Properties getPropertyResource() throws IOException {
//        ClassLoader loader = Thread.currentThread().getContextClassLoader();
//        Properties props = new Properties();
//        String resourceName = "tasksData.properties";
//        props.load(loader.getResourceAsStream(resourceName));
//        return props;
//    }
}
