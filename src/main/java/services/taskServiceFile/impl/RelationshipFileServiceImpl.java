package services.taskServiceFile.impl;

import entities.Task;
import services.taskServiceFile.RelationshipFileService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Andrey_Bindyuk on 2/9/2017.
 */
public class RelationshipFileServiceImpl implements RelationshipFileService {
    private static final String tasksData = "tasksData.properties";

    @Override
    public void updateFile(Task task) throws IOException {
        Properties props = new Properties();
        String text = getResourceContent();
        Writer output = new BufferedWriter(new FileWriter(tasksData, true));
        if (!text.contains(task.getId())) {
            props.setProperty(task.getId(), task.toString());
            props.store(output, null);
        }
        output.close();

    }

    @Override
    public Set<Map.Entry<Object, Object>> getAllTaskInfoFromFile() throws IOException {
        Properties props = new Properties();
        InputStream input = new FileInputStream(tasksData);
        props.load(input);
        return props.entrySet();
    }

    @Override
    public String getSpecificTaskInfoFromFile(String id) throws IOException {
        String getSpecificTask = null;
        Properties props = new Properties();
        String text = getResourceContent();
        InputStream input = new FileInputStream(tasksData);
        props.load(input);
        if (text.contains(id)) {
            getSpecificTask = props.getProperty(id);
        }
        return getSpecificTask;
    }

    @Override
    public boolean removeSpecificTaskFromFile(String id) throws IOException {
        Properties props = new Properties();
        Boolean isremoved = false;
        String text = getResourceContent();
        InputStream input = new FileInputStream(tasksData);
        props.load(input);
        if (text.contains(id)) {
            props.remove(id);
            FileOutputStream out = new FileOutputStream(tasksData);
            props.store(out, null);
            isremoved = true;
        }
        return isremoved;
    }

    @Override
    public boolean removeAllTasksFromFile() {
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
}
