package exceptions;

public class NoValueException extends RuntimeException{
    /**
     * constructor
     * @param message
     */
    public NoValueException(String message){
        super(message);
    }
}
