package epam.com.services.taskservice;

import epam.com.entities.Task;
import epam.com.exceptions.TaskServiceValidationException;

import java.util.Map;

public interface TaskService {

    public boolean updateFile(Task task,String status) throws TaskServiceValidationException;

    public Map<String, String> getAllTaskInfo() throws TaskServiceValidationException;

    public String getSpecificTaskInfo(String id) throws TaskServiceValidationException;

    public boolean removeSpecificTask(String id) throws TaskServiceValidationException;

    public boolean removeAllTasks();
}
