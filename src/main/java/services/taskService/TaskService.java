package services.taskService;

import entities.Task;
import exceptions.TaskServiceValidationException;

import java.io.IOException;
import java.util.Set;

public interface TaskService {

    public void updateFile(Task task) throws IOException;

    public Set getAllTaskInfo() throws TaskServiceValidationException, IOException;

    public String getSpecificTaskInfo(String id) throws TaskServiceValidationException, IOException;

    public boolean removeSpecificTask(String id) throws IOException, TaskServiceValidationException;

    public boolean removeAllTasks();
}
