package services.taskServiceFile;

import entities.Task;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrey_Bindyuk on 2/9/2017.
 */
public interface RelationshipFileService {

    public void updateFile(Task task) throws IOException;

    public Set<Map.Entry<Object, Object>> getAllTaskInfoFromFile() throws IOException;

    public String getSpecificTaskInfoFromFile(String id) throws IOException;

    public boolean removeSpecificTaskFromFile(String id) throws IOException;

    public boolean removeAllTasksFromFile();
}
