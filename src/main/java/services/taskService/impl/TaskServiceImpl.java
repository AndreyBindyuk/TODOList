package services.taskService.impl;

import entities.Task;
import enums.Status;
import exceptions.TaskServiceValidationException;
import services.taskService.TaskService;
import services.taskServiceFile.RelationshipFileService;
import services.taskServiceFile.impl.RelationshipFileServiceImpl;

import java.util.*;

public class TaskServiceImpl implements TaskService {
    private RelationshipFileService relationshipFileService = new RelationshipFileServiceImpl();


    public void updateFile(Task task,String s) throws TaskServiceValidationException {
        if(!Arrays.toString(Status.values()).contains(s)){
            throw new TaskServiceValidationException("status is incorrect! status should has following values:  " + Arrays.toString(Status.class.getEnumConstants()));
        }else {
            task.setStatus(Status.valueOf(s));
            relationshipFileService.updateFile(task);
        }

    }

    public Map<String, String> getAllTaskInfo() throws TaskServiceValidationException {
        Map<String,String> map = relationshipFileService.getAllTaskInfoFromFile();
            if(map.isEmpty()){
                throw new TaskServiceValidationException("no tasks in file!");
            }
        return map;
    }


    public String getSpecificTaskInfo(String id) throws TaskServiceValidationException {
        String s = relationshipFileService.getSpecificTaskInfoFromFile(id);
            if(s==null){
                throw new TaskServiceValidationException("task is not exist!");
            }
        return s;

    }


    public boolean removeSpecificTask(String id) throws TaskServiceValidationException {
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
