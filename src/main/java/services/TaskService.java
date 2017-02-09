package services;

import entities.Task;

import java.util.Set;

public interface TaskService {

    public void updateFile(Task task);

    public Set getAllTaskInfo();

    public String getSpecificTaskInfo(String id);

    public boolean removeSpecificTask(String id);

    public boolean removeAllTasks();
}
