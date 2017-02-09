package services.taskService.impl;

import entities.Task;
import exceptions.TaskServiceValidationException;
import services.taskService.TaskService;
import services.taskServiceFile.RelationshipFileService;
import services.taskServiceFile.impl.RelationshipFileServiceImpl;

import java.io.*;
import java.util.*;

public class TaskServiceImpl implements TaskService {
    private RelationshipFileService relationshipFileService = new RelationshipFileServiceImpl();


    public void updateFile(Task task) throws IOException {
            relationshipFileService.updateFile(task);
    }

    public Set<Map.Entry<Object, Object>> getAllTaskInfo() throws TaskServiceValidationException, IOException {
        Set<Map.Entry<Object, Object>> set;
            set = relationshipFileService.getAllTaskInfoFromFile();
            if(set.isEmpty()){
                throw new TaskServiceValidationException("no tasks in file!");
            }
        return set;
    }


    public String getSpecificTaskInfo(String id) throws TaskServiceValidationException, IOException {
        String s = relationshipFileService.getSpecificTaskInfoFromFile(id);
            if(s==null){
                throw new TaskServiceValidationException("task is not exist!");
            }
        return s;

    }


    public boolean removeSpecificTask(String id) throws IOException, TaskServiceValidationException {
        Boolean isremoved = relationshipFileService.removeSpecificTaskFromFile(id);
        if(!isremoved){
            throw new TaskServiceValidationException("this task is not exist in file!");
        }
        return isremoved;
    }


    public boolean removeAllTasks() {
        return relationshipFileService.removeAllTasksFromFile();
    }
}
