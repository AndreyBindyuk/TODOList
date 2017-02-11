package services.taskServiceFile;

import entities.Task;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrey_Bindyuk on 2/9/2017.
 */
public interface RelationshipFileService {

    public boolean updateFile(Task task);

    public Map<String, String> getAllTaskInfoFromFile();

    public String getSpecificTaskInfoFromFile(String id);

    public boolean removeSpecificTaskFromFile(String id);

    public boolean removeAllTasksFromFile();
}
