package exceptions;

public class NoRelevantException extends RuntimeException{
    /**
     * constructor
     * @param message
     */
    public NoRelevantException(String message){
        super(message);
    }
}
