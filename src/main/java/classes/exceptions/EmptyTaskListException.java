package classes.exceptions;

public class EmptyTaskListException extends Exception{
    public EmptyTaskListException(String message) {
        super(message);
    }
}
