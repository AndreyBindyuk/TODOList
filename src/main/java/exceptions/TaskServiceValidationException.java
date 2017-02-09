package exceptions;

/**
 * Created by Andrey_Bindyuk on 2/9/2017.
 */
public class TaskServiceValidationException extends Exception {
    public TaskServiceValidationException(String exceptionsType) {
        super(exceptionsType);
    }
}
