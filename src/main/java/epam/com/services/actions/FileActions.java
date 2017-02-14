package epam.com.services.actions;

import java.io.IOException;

public interface FileActions {

    public void updateFile() throws IOException;

    public void showAllTasks();

    public void showSpecificTaskInfo() throws IOException;

    public void removeAllTasks();

    public void removeSpecificTask() throws IOException;
}
