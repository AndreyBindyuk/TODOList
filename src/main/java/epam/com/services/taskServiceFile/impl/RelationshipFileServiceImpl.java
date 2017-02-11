package epam.com.services.taskservicefile.impl;

import epam.com.entities.Task;
import epam.com.services.taskservicefile.RelationshipFileService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Andrey_Bindyuk on 2/9/2017.
 */
public class RelationshipFileServiceImpl implements RelationshipFileService {
    private static final String tasksData = "tasksData.properties";

    @Override
    public boolean updateFile(Task task) {
        Properties props = new Properties();
        Boolean isUpdated = false;
        try {
        String text = getResourceContent();
        Writer output = new BufferedWriter(new FileWriter(tasksData, true));
        if (!text.contains(task.getId())) {
            props.setProperty(task.getId(), task.toString());
            props.store(output, null);
            isUpdated = true;
        }

            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }

    @Override
    public Map<String, String> getAllTaskInfoFromFile() {
        Map stringTaskMap = new HashMap();
        Properties props = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(tasksData);
            props.load(input);
            for(Map.Entry<Object,Object> map: props.entrySet()){
                stringTaskMap.put(map.getKey(),map.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        return stringTaskMap;
    }

    @Override
    public String getSpecificTaskInfoFromFile(String id) {
        String getSpecificTask = null;
        Properties props = new Properties();

        try {
            String text = getResourceContent();
            InputStream input = new FileInputStream(tasksData);
            props.load(input);
            if (text.contains(id)) {
                getSpecificTask = props.getProperty(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getSpecificTask;
    }

    @Override
    public boolean removeSpecificTaskFromFile(String id) {
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
