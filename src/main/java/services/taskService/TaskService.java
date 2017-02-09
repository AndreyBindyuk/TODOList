package services.taskService;

import entities.Task;
import exceptions.TaskServiceValidationException;

import java.util.Map;
import java.util.Set;

public interface TaskService {

    public void updateFile(Task task,String status) throws TaskServiceValidationException;

    public Map<String, String> getAllTaskInfo() throws TaskServiceValidationException;

    public String getSpecificTaskInfo(String id) throws TaskServiceValidationException;

    public boolean removeSpecificTask(String id) throws TaskServiceValidationException;

    public boolean removeAllTasks();
}
