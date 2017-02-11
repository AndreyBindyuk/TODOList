package epam.com.services.taskservice.impl;

import epam.com.entities.Task;
import epam.com.enums.Status;
import epam.com.exceptions.TaskServiceValidationException;
import epam.com.services.taskservice.TaskService;
import epam.com.services.taskservicefile.RelationshipFileService;
import epam.com.services.taskservicefile.impl.RelationshipFileServiceImpl;

import java.util.*;

public class TaskServiceImpl implements TaskService {
    private RelationshipFileService relationshipFileService = new RelationshipFileServiceImpl();


    public boolean updateFile(Task task,String s) throws TaskServiceValidationException {
        Boolean isUpdated = false;
        if(!Arrays.toString(Status.values()).contains(s)){
            throw new TaskServiceValidationException("status is incorrect! status should has following values:  " + Arrays.toString(Status.class.getEnumConstants()));
        }else {
            task.setStatus(Status.valueOf(s));
            isUpdated = relationshipFileService.updateFile(task);
        }

        return isUpdated;

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
